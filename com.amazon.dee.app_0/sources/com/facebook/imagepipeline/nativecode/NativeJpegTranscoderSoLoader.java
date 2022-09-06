package com.facebook.imagepipeline.nativecode;

import android.os.Build;
import com.facebook.soloader.nativeloader.NativeLoader;
/* loaded from: classes2.dex */
public class NativeJpegTranscoderSoLoader {
    private static boolean sInitialized;

    public static synchronized void ensure() {
        synchronized (NativeJpegTranscoderSoLoader.class) {
            if (!sInitialized) {
                int i = Build.VERSION.SDK_INT;
                NativeLoader.loadLibrary("native-imagetranscoder");
                sInitialized = true;
            }
        }
    }
}
