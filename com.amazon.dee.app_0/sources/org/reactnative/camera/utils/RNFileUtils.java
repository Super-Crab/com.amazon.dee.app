package org.reactnative.camera.utils;

import android.net.Uri;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
/* loaded from: classes5.dex */
public class RNFileUtils {
    public static File ensureDirExists(File file) throws IOException {
        if (file.isDirectory() || file.mkdirs()) {
            return file;
        }
        throw new IOException(GeneratedOutlineSupport1.outline64("Couldn't create directory '", file, "'"));
    }

    public static String getOutputFilePath(File file, String str) throws IOException {
        ensureDirExists(file);
        String uuid = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(file);
        return GeneratedOutlineSupport1.outline92(sb, File.separator, uuid, str);
    }

    public static Uri uriFromFile(File file) {
        return Uri.fromFile(file);
    }
}
