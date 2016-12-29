package com.example.aidanakurmasheva.wallcolor;

import android.graphics.Color;
import java.util.Random;

final class RandomParam {
    private static final int MAX_RGB = 256;
    private static final Random sRandom = new Random();


    public static int nextColor() {
        final int red = sRandom.nextInt(MAX_RGB);
        final int green = sRandom.nextInt(MAX_RGB);
        final int blue = sRandom.nextInt(MAX_RGB);
        return Color.rgb(red, green, blue);
    }
}
