package model;

public record PixelData(int x,int y,int color) {

    public PixelData{
        if (x < 0) throw new IllegalArgumentException("X coordinate cannot be negative! | X:" + x);
        if (y < 0) throw new IllegalArgumentException("Y coordinate cannot be negative! | Y:" + y);
    }

}
