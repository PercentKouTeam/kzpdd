package com.hpercent.snail.app.utils;

import android.util.Log;

public class LogUtil {

    private static final boolean isDebug = true;

    public static boolean isDebug() {
        return isDebug;
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            if (msg != null) {
                Log.v(tag, msg);
            }
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            if (msg != null) {
                Log.d(tag, msg);
            }
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            if (msg != null) {
                Log.i(tag, msg);
            }
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            if (msg != null) {
                Log.w(tag, msg);
            }
        }
    }

    public static void w(String tag, String msg, Exception e) {
        if (isDebug) {
            if (msg != null) {
                Log.w(tag, msg, e);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            if (msg != null) {
                Log.e(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg, Exception e) {
        if (isDebug) {
            if (msg != null) {
                Log.e(tag, msg, e);
            }
        }
    }
}
