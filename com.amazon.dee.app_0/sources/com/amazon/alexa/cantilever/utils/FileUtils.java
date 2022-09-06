package com.amazon.alexa.cantilever.utils;

import android.content.Context;
import com.amazon.alexa.cantilever.LogConfig;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Logger;
import com.amazon.alexa.logging.Tag;
import java.io.InputStream;
/* loaded from: classes6.dex */
public final class FileUtils {
    private static final String EMPTY_FILE = "";
    private static final Tag TAG = LogConfig.TAGGER.tag(FileUtils.class);
    private static final String UTF_8 = "UTF-8";

    private FileUtils() {
    }

    public static String readFile(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            if (open == null) {
                return "";
            }
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, "UTF-8");
        } catch (Exception e) {
            Logger logger = Lib.Log;
            Tag tag = TAG;
            logger.e(tag, "Failed to load File: " + str, e);
            return "";
        }
    }
}
