package com.amazon.dee.app.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.util.UiUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
/* loaded from: classes12.dex */
public final class ResourceUtils {
    private static final String TAG = Log.tag(UiUtils.class);

    private ResourceUtils() {
    }

    public static int getResourceId(String str, Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            return declaredField.getInt(declaredField);
        } catch (IllegalAccessException | NoSuchFieldException | NullPointerException e) {
            Log.e(TAG, e, "Could not find resource named: %s", str);
            return -1;
        }
    }

    @Nullable
    public static Resources getResources(Context context, @NonNull String str) {
        try {
            return context.getPackageManager().getResourcesForApplication(str);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String readRawAsString(Context context, @RawRes int i, @Nullable String str) {
        try {
            InputStream openRawResource = context.getResources().openRawResource(i);
            byte[] bArr = new byte[openRawResource.available()];
            String str2 = new String(bArr, 0, openRawResource.read(bArr));
            openRawResource.close();
            return str2;
        } catch (IOException unused) {
            return str;
        }
    }
}
