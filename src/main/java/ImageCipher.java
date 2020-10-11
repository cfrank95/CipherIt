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

    void setCipheredImage(Image originalImage){
        this.cipheredImage = cipheredImage;
    }

    Image getCipheredImage(){
        return cipheredImage;
    }



    @Override
    public void start(Stage cipherStage) throws FileNotFoundException, InterruptedException {
        // Create Image
        int width = (int) originalImage.getWidth();
        int height = (int) originalImage.getWidth();

        // Create a writable image
        WritableImage wImage = new WritableImage(width, height);

        // Reading color from loaded image
        PixelReader pixelReader = originalImage.getPixelReader();

        // Create Pixel Writer Object
        PixelWriter writer = wImage.getPixelWriter();
        System.out.println("x: " + width + ", y: " + height);
        // Reading color of image
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                // Retrieving the color of the pixel of the loaded image
                Color color = pixelReader.getColor(x, y);
                // Write color to wImage
                writer.setColor(x, y, color);
            }
        }

        // Setting the view for the writable image
        ImageView imageView = new ImageView(wImage);

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
