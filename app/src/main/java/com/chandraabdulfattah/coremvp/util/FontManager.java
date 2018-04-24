package com.chandraabdulfattah.coremvp.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Mobile3 on 1/12/2018.
 */

public class FontManager {

    public static final String ROOT = "",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

}
