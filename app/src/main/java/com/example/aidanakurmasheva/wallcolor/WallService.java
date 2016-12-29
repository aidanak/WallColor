package com.example.aidanakurmasheva.wallcolor;


import android.app.IntentService;
import android.app.WallpaperManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;


public class WallService extends IntentService {
    private static final String EXTRA_COLOR = "com.appgramming.intent.extra.Color";
    private static final int DEFAULT_COLOR = Color.BLACK;
    private static final String COLOR_FORMAT = "#%1$06X";
    private static final int RGB_MASK = 0xFFFFFF;
    private static final int ONE_PIXEL = 1;

    @SuppressWarnings("WeakerAccess")
    public WallService() {
        super("WallService");
    }
    public static void setColorWallpaper(Context context, int color) {
        final Intent intent = new Intent(context, WallService.class);
        intent.putExtra(EXTRA_COLOR, color);
        context.startService(intent);
    }
    private static Bitmap createColorBitmap(int color) {
        final Bitmap colorBitmap = Bitmap.createBitmap(ONE_PIXEL, ONE_PIXEL, Bitmap.Config.ARGB_8888);
        colorBitmap.eraseColor(color);
        return colorBitmap;
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        final int color = intent.getIntExtra(EXTRA_COLOR, DEFAULT_COLOR);

        try {
            final WallpaperManager wpManager = WallpaperManager.getInstance(this);
            final Bitmap colorBitmap = createColorBitmap(color);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wpManager.setBitmap(colorBitmap, null, true, WallpaperManager.FLAG_SYSTEM | WallpaperManager.FLAG_LOCK);
            } else {
                wpManager.setBitmap(colorBitmap);
            }
            statusOK(color);

            Utils.goHome(this);

        } catch (Exception e) {
            e.printStackTrace();
            statusFailure(e.toString());
        }
    }

    private void copyText(CharSequence text) {
        final ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        final ClipData clip = ClipData.newPlainText(getString(R.string.app_name), text);
        clipboard.setPrimaryClip(clip);
    }

    private void statusOK(int color) {
        copyText(String.format(COLOR_FORMAT, RGB_MASK & color));
    }

    private void statusFailure(CharSequence message) {
        copyText(message);
    }
}
