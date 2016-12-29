package com.example.aidanakurmasheva.wallcolor;


import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;

final class ClipParam{
    private static String getClipParameter(Context context) {
        final ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if ((clipboard != null) && clipboard.hasPrimaryClip()) {
            final ClipData clip = clipboard.getPrimaryClip();
            if ((clip != null) && (clip.getItemCount() > 0)) {

                final ClipDescription description = clip.getDescription();

                if (!description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) &&
                        !description.hasMimeType(ClipDescription.MIMETYPE_TEXT_HTML)) {
                    return null;
                }

                final CharSequence label = description.getLabel();
                if ((label != null) && (label.equals(context.getString(R.string.app_name)))) {
                    return null;
                }

                final CharSequence sequence = clip.getItemAt(0).getText();
                if (sequence != null) {
                    return sequence.toString();
                }
            }
        }

        return null;
    }


    public static Integer getColor(Context context) {

        final String clipText = ClipParam.getClipParameter(context);

        if (TextUtils.isEmpty(clipText)) {
            return null;
        }
        try {
            return Color.parseColor(clipText);
        } catch (IllegalArgumentException ignored) {

        }

        try {
            return Color.parseColor('#' + clipText);
        } catch (IllegalArgumentException ignored) {

        }

        return null;
    }
}