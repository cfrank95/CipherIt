import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageCipher extends Application{
    // Create Image
    Image originalImage;
    Image cipheredImage;

    ImageCipher(){
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

        int numLocations = 10;
        int numCells = (int) Math.pow(numLocations, 2);
        int[] cells;

        int offset = 4;

        // Initialization of variables for image characteristics
        int width = (int) originalImage.getWidth();
        int height = (int) originalImage.getHeight();
        int copyWidth = width / numLocations;
        int copyHeight = height / numLocations;

        cells = new int[numCells];
        int[] encryptCells = new int[numCells];
        for(int i = 0; i < numCells; i++) {
            cells[i] = i;
        }

//        for(int o = 0; o < offset; o++) {
//            for (int i = 0; i < numLocations; i++) {
//                int test;
//                int delta;
//                int prev = count;
//                int next = count + 1;
//                int pairNums = numLocations / 2;
//
//                // Fix to alternate image
//                for (int j = numLocations; j > 0; j--) {
//                    test = count;
//                    if (test > numCells) {
//                        delta = test - numCells;
//                        count = delta;
//                        encryptCells[elem] = count;
//                    } else
//                        encryptCells[elem] = test;
//                    if(pairNums > 1)
//                        count += 2;
//
//                    else
//                        count += 1;
//                    elem++;
//                }
//                count++;
//            }
//        }

        int count = offset;
        int elem = 0;
        for(int i = 0; i < numCells; i++){
            int test = count + 1;
            int delta;
            if (test > numCells) {
                delta = test - numCells;
                count = i - delta;
                encryptCells[i] = count;
            }else{
                encryptCells[i] = count;
            }
            count++;
        }

        // Create a writable image
        WritableImage wImage = new WritableImage(width, height);


        // Reading color from loaded image
        PixelReader pixelReader = originalImage.getPixelReader();

        // Create Pixel Writer Object
        PixelWriter writer = wImage.getPixelWriter();

        // Reading color of image
        int xElm = 0;
        int yElm = 0;
        int totalElm = 1;

        while(totalElm < numCells + 1) {

            yElm = 0;
            while(yElm < numLocations) {

                xElm = 0;
                while (xElm < numLocations) {

                    int readY = yElm * copyHeight;
                    for (int y = 0; y < copyHeight; y++) {
                        int writeY;

                        int readX = xElm  * copyWidth;
                        int writeX;
                        for (int x = 0; x < copyWidth; x++) {
                            // Retrieving the color of the pixel of the loaded image
                            Color color = pixelReader.getColor(readX, readY);
                            readX++;
                            writeX = (((encryptCells[xElm]) / numLocations) * copyWidth + x);
                            writeY = (((encryptCells[yElm]) / numLocations) * copyHeight + y);
                            writer.setColor(writeX, writeY, color);
                        }
                        readY++;
                    }
                    xElm++;
                    totalElm++;
                }
                yElm++;
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

        // Adding scene to the stage
        cipherStage.setScene(scene);

        // Displaying contents of the stage
        cipherStage.show();
    }
}
