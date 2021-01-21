// FrankencipherV1 by Chris Frank

import java.io.FileNotFoundException;

import javafx.application.Application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageCipher extends Application{
    // Create Image
    Image cipherImage;

    ImageCipher(){
    }

    void setCipherImage(Image originalImage){
        this.cipherImage = originalImage;
    }

    Image getCipherImage(){
        return cipherImage;
    }


    @Override
    public void start(Stage cipherStage) throws FileNotFoundException, InterruptedException {
        // Create Image

        final int numLocations = 4;
        int numCells = (int) Math.pow(numLocations, 2);

        int iterations = 2;

        // Initialization of variables for image characteristics
        int width = (int) cipherImage.getWidth() - (int) (cipherImage.getWidth() % 4);
        int height = (int) cipherImage.getHeight() - (int) (cipherImage.getHeight() % 4);


        // Create a writable image

        // Reading color from loaded image
        PixelReader pixelReader = cipherImage.getPixelReader();

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

            // Rule 2
            for(int i = 4; i <= 11; i++){
                switch (i) {
                    case 4:
                        tempPositions1[11] = encryptedPositions[i];
                        break;
                    case 5:
                        tempPositions1[2] = encryptedPositions[i];
                        break;
                    case 6:
                        tempPositions1[13] = encryptedPositions[i];
                        break;
                    case 7:
                        tempPositions1[0] = encryptedPositions[i];
                        break;
                    case 8:
                        tempPositions1[15] = encryptedPositions[i];
                        break;
                    case 9:
                        tempPositions1[4] = encryptedPositions[i];
                        break;
                    case 10:
                        tempPositions1[6] = encryptedPositions[i];
                        break;
                    case 11:
                        tempPositions1[9] = encryptedPositions[i];
                        break;
                }
            }

            int ruleIncrement = 0;

            while(ruleIncrement <= 3){
                // Rule 1
                tempPositions2[ruleIncrement * 2 + 1] = encryptedPositions[ruleIncrement];
                // Rule 3
                tempPositions2[ruleIncrement * 2 + 8] = encryptedPositions[ruleIncrement + 12];

                ruleIncrement++;
            }

            for(int i = 0; i < numCells; i++){
                if(i == 0 || i == 2 || i == 4 || i == 6 || i == 9 || i == 11 || i == 13 || i == 15)
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

        setCipherImage(wImage);

    }


    // End start()
}
