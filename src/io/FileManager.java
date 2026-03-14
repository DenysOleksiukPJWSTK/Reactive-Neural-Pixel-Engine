package io;

import exception.SaveToBinaryException;
import model.NeuralCanvas;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {

    public void saveToBinary(NeuralCanvas canvas,String path) throws SaveToBinaryException {

        try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(path))) {

            dataOutputStream.writeInt(canvas.getWidth());
            dataOutputStream.writeInt(canvas.getHeight());

            for (int y = 0; y < canvas.getHeight(); y++)
                for (int x = 0; x < canvas.getWidth(); x++) dataOutputStream.writeInt(canvas.getColor(x, y));



        }catch (IOException e){
            throw new SaveToBinaryException("Error saving: " + e.getMessage());
        }
    }


}
