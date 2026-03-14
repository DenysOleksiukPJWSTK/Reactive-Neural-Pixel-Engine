package processing;

import model.NeuralCanvas;
import model.PixelData;
import util.BitwiseColorUtils;
import util.PixelTransformer;

public interface ImageProcessor {

    static PixelTransformer createGrayscale(){
        return (pixelData) -> {
            int color = pixelData.color();

            int red = BitwiseColorUtils.extractRed(color);
            int alpha = BitwiseColorUtils.extractAlpha(color);
            int green = BitwiseColorUtils.extractGreen(color);
            int blue = BitwiseColorUtils.extractBlue(color);

            int grey = (red + green + blue) / 3;
            int newColor = BitwiseColorUtils.combine(alpha,grey,grey,grey);
            return new PixelData(pixelData.x(),pixelData.y(),newColor);
        };
    }

    static PixelTransformer createInvert(){
        return  (pixelData) -> {
            int color = pixelData.color();

            int alpha = BitwiseColorUtils.extractAlpha(color);
            int red = 255 - BitwiseColorUtils.extractRed(color);
            int green = 255 - BitwiseColorUtils.extractGreen(color);
            int blue = 255 - BitwiseColorUtils.extractBlue(color);

            int newColor = BitwiseColorUtils.combine(alpha,red,green,blue);
            return new PixelData(pixelData.x(),pixelData.y(),newColor);
        };
    }

    default void apply(NeuralCanvas canvas,PixelTransformer transformer){
        for (int y = 0; y < canvas.getHeight(); y++) {
            for (int x = 0; x < canvas.getWidth(); x++) {
                int currentColor = canvas.getColor(x, y);

                PixelData input = new PixelData(x,y,currentColor);
                PixelData output = transformer.apply(input);

                canvas.setPixel(x,y,output.color());
            }
        }


    }



}
