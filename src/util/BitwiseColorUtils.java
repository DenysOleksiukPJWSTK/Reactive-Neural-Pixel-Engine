package util;

public class BitwiseColorUtils {
    public static final int MASK = 0xFF;

    public static int extractAlpha(int color) {
        return (color >>> 24) & MASK;
    }

    public static int extractRed(int color) {
        return (color >> 16) & MASK;
    }

    public static int extractGreen(int color) {
        return (color >> 8) & MASK;
    }

    public static int extractBlue(int color) {
        return color & MASK;
    }

    public static int combine(int alpha,int red,int green,int blue){
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public static int blend(int background,int source){
        int aSrc = extractAlpha(source);

        if (aSrc == 0) return background;
        if (aSrc == 255) return source;

        int rSrc = extractRed(source);
        int gSrc = extractGreen(source);
        int bSrc = extractBlue(source);

        int rBg = extractRed(background);
        int gBg = extractGreen(background);
        int bBg = extractBlue(background);

        int rResult = (rSrc * aSrc + rBg * (255 - aSrc)) >> 8;
        int gResult = (gSrc * aSrc + gBg * (255 - aSrc)) >> 8;
        int bResult = (bSrc * aSrc + bBg * (255 - aSrc)) >> 8;

        return combine(255,rResult,gResult,bResult);
    }


}
