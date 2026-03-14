package model;

import util.BitwiseColorUtils;

import java.util.Arrays;

public class NeuralCanvas {
    private final int width;
    private final int height;

    private final int[] pixels;

    public NeuralCanvas(int width, int height) {
        if (width <= 0) throw  new IllegalArgumentException("Width cannot be negative. | Width:" + width);
        if (height <= 0) throw new IllegalArgumentException("Height cannot be negative. | Height:" + height);

        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];

        for (int pixel : pixels) pixel = 0xFFFFFF;
    }

    public int getIndex(int x, int y) {
        return y * width + x;
    }

    public int getColor(int x, int y) {
        return pixels[y * width + x];
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void setPixel(int x, int y, int color) {
        if (x < 0) throw new IllegalArgumentException("x cannot be negative. | x:" + x);
        if (y < 0) throw new IllegalArgumentException("y cannot be negative. | y:" + y);
        if (x >= width) throw  new IllegalArgumentException("x cannot be greater width. | x:" + x);
        if (y >= height) throw  new IllegalArgumentException("y cannot be greater height . | y:" + y);
        if (color < 0) throw new IllegalArgumentException("color cannot be negative. | color:" + color);

        pixels[getIndex(x, y)] = BitwiseColorUtils.blend(pixels[getIndex(x, y)], color);
    }

    public class RenderContext{

        public void clear(int color) {
            if (color < 0) throw new IllegalArgumentException("color cannot be negative. | color:" + color);
            Arrays.fill(pixels, color);
        }

    }
}
