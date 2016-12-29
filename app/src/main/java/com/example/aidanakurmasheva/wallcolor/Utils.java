package com.example.aidanakurmasheva.wallcolor;

import android.content.Context;
import android.content.Intent;

class Utils {
    static void goHome(Context context) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startMain);
    }
}
