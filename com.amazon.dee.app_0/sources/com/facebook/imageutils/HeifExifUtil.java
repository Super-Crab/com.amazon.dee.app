package com.facebook.imageutils;

import android.media.ExifInterface;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class HeifExifUtil {
    public static final String TAG = "HeifExifUtil";

    /* loaded from: classes2.dex */
    private static class HeifExifUtilAndroidN {
        private HeifExifUtilAndroidN() {
        }

        @RequiresApi(api = 24)
        static int getOrientation(final InputStream inputStream) {
            try {
                return new ExifInterface(inputStream).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            } catch (IOException e) {
                FLog.d(HeifExifUtil.TAG, "Failed reading Heif Exif orientation -> ignoring", (Throwable) e);
                return 0;
            }
        }
    }

    public static int getOrientation(final InputStream inputStream) {
        int i = Build.VERSION.SDK_INT;
        return HeifExifUtilAndroidN.getOrientation(inputStream);
    }
}
