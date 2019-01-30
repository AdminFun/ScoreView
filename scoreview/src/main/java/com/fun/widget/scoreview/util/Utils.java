package com.fun.widget.scoreview.util;

import android.content.Context;
import android.text.TextUtils;

public class Utils {

    public static float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    /**
     * 字符串转字符数组
     */
    public static String[] toStringArray(String text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        String[] result = new String[text.length()];
        for (int i = 0; i < text.length(); i++) {
            result[i] = String.valueOf(text.charAt(i));
        }
        return result;
    }
}