// FrankencipherV1 by Chris Frank

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class ImageDecipher extends Application{
    // Create Image
    Image cipheredImage;
    Image originalImage;

    ImageDecipher(){
    }

    void setOriginalImage(Image originalImage){
        this.originalImage = originalImage;
    }

    Image getOriginalImage(){
        return originalImage;
    }

    void setCipheredImage(Image cipheredImage){
        this.cipheredImage = cipheredImage;
    }

    Image getCipheredImage(){
        return cipheredImage;
    }


    @Override
    public void start(Stage cipherStage) throws FileNotFoundException, InterruptedException {
        // Create Image

        final int numLocations = 4;
        int numCells = (int) Math.pow(numLocations, 2);

        int iterations = 2;

        // Initialization of variables for image characteristics
        int width = (int) originalImage.getWidth() - (int) (originalImage.getWidth() % 4);
        int height = (int) originalImage.getHeight() - (int) (originalImage.getHeight() % 4);


        // Create a writable image

        // Reading color from loaded image
        PixelReader pixelReader = originalImage.getPixelReader();

        // Creates an array to hold writable image objects
        WritableImage[] wImages = new WritableImage[numCells];
        PixelWriter[] writers = new PixelWriter[numCells];

        for(int i = 0; i < writers.length; i++) {

            wImages[i] = new WritableImage(width / 4, height / 4);
            writers[i] = wImages[i].getPixelWriter();
        }

        // *****
        // PART 1: Read Image into Elements
        // *****

        for(int y = 1; y < height; y++){

            for(int x = 1; x < width; x++){

                Color color = pixelReader.getColor(x, y);

                int element = x / (width / 4) + 4 * (y / (height / 4));
                int writeX = x % (width / 4);
                int writeY = y % (height/ 4);
                writers[element].setColor(writeX, writeY, color);
            }
        }


        // ******
        // PART 2: Scramble element positions
        // ******

        int[] encryptedPositions = new int[numCells];
        int[] tempPositions1 = new int[numCells];
        int[] tempPositions2 = new int[numCells];

        for(int i = 0; i < numCells; i++){
            encryptedPositions[i] = i;
            tempPositions1[i] = i;
            tempPositions2[i] = i;
        }



        // Run iteration times
        while(iterations > 0){
            int ruleIncrement = 0;

            while(ruleIncrement <= 3){
                // Rule 1
                tempPositions2[ruleIncrement] = encryptedPositions[ruleIncrement * 2 + 1];
                // Rule 3
                tempPositions2[ruleIncrement + 12] = encryptedPositions[ruleIncrement * 2 + 8];

                ruleIncrement++;
            }

            // Rule 2
            for(int i = 4; i <= 11; i++){
                switch (i) {
                    case 4:
                        tempPositions1[i] = encryptedPositions[11];
                        break;
                    case 5:
                        tempPositions1[i] = encryptedPositions[2];
                        break;
                    case 6:
                        tempPositions1[i] = encryptedPositions[13];
                        break;
                    case 7:
                        tempPositions1[i] = encryptedPositions[0];
                        break;
                    case 8:
                        tempPositions1[i] = encryptedPositions[15];
                        break;
                    case 9:
                        tempPositions1[i] = encryptedPositions[4];
                        break;
                    case 10:
                        tempPositions1[i] = encryptedPositions[6];
                        break;
                    case 11:
                        tempPositions1[i] = encryptedPositions[9];
                        break;
                }
            }

            for(int i = 0; i < numCells; i++){
                if(i >= 4 && i <= 11)
                    encryptedPositions[i] = tempPositions1[i];
                else
                    encryptedPositions[i] = tempPositions2[i];
            }

            iterations--;
        }



        // ******
        // PART 3: Read Elements onto shared image
        // ******

        // Creates main image that will be written on for final read
        WritableImage wImage = new WritableImage(width, height);
        PixelWriter writer = wImage.getPixelWriter();

        // Read the writable image arrays to the overall image
        for(int y = 0; y < height; y++){

            for(int x = 0; x < width; x++){

                int element = encryptedPositions[(x / (width / 4) + 4 * (y / (height / 4)))];
                PixelReader elementReader = wImages[element].getPixelReader();
                int readX = x % (width / 4);
                int readY = y % (height / 4);
                Color color = elementReader.getColor(readX, readY);

                writer.setColor(x, y, color);
            }

        }


        // Setting the view for the writable image
        ImageView imageView = new ImageView(wImage);
        imageView.fitHeightProperty();
        imageView.fitWidthProperty();

        // Creating a Group object
        Group root = new Group(imageView);

        // Creating a scene object
        Scene scene = new Scene(root, width, height);

        // Setting title to the Stage
        cipherStage.setTitle("Writing Image...");

        // Add button for saving image
        Button saveButton = new Button("Save");




        // Adding scene to the stage
        cipherStage.setScene(scene);

        // Displaying contents of the stage
        cipherStage.show();

    }

}
