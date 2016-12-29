package com.example.aidanakurmasheva.wallcolor;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setColorWallpaper();
        finish();
    }
    private void setColorWallpaper() {
        Integer colorParam = null;
        try {
            colorParam = ClipParam.getColor(getApplication());
        } catch (Exception e) {
        }

        final int color = colorParam != null ? colorParam : RandomParam.nextColor();
        WallService.setColorWallpaper(getApplication(), color);
    }
}
