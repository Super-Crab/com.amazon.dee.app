package com.amazon.dee.app.ui.web;

import android.util.Log;
/* loaded from: classes12.dex */
public class FireOsVersion {
    private static final String TAG = "com.amazon.dee.app.ui.web.FireOsVersion";

    public String getVersion() {
        String str;
        Exception e;
        try {
            str = (String) Class.forName("amazon.os.Build$VERSION").getField("FIREOS").get(null);
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Exception e2) {
            str = null;
            e = e2;
        }
        try {
            String str2 = "FireOS version: " + str;
            return str;
        } catch (ClassNotFoundException unused2) {
            return str;
        } catch (Exception e3) {
            e = e3;
            Log.i(TAG, "Could not read FireOS version field - unable to determine FireOS version.", e);
            return str;
        }
    }

    public boolean isFireOS() {
        try {
            Class.forName("amazon.os.Build$VERSION");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
