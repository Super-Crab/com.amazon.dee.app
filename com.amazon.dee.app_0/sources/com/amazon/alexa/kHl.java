package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.utils.ZipExtractor;
import com.google.common.base.Optional;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
/* compiled from: OfflinePromptsArtifactManager.java */
/* loaded from: classes.dex */
public class kHl {
    public static final String zZm = "kHl";

    public Optional<File> BIo(File file, String str, String str2) {
        if (!file.exists()) {
            return Optional.absent();
        }
        File file2 = new File(file, str2);
        if (file2.exists() && file2.isDirectory()) {
            File file3 = new File(file2, str);
            if (file3.exists()) {
                return Optional.of(file3);
            }
        }
        return Optional.absent();
    }

    public boolean zZm(File file, String str, String str2) {
        String format = String.format("%s/%s.%s", file.getPath(), str, "zip");
        String format2 = String.format("%s%s%s", file.getPath(), File.separator, str2);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("destination location to unzip");
            sb.append(format2);
            sb.toString();
            ZipExtractor.unzip(format, format2, "offline_prompt_");
            return true;
        } catch (IOException e) {
            Log.e(zZm, String.format("Failure in extracting zip from %s location with error: %s", format, e));
            File file2 = new File(format);
            if (file2.exists()) {
                file2.delete();
            }
            return false;
        }
    }

    public boolean zZm(String str, String str2, String str3) {
        File[] listFiles;
        File file = new File(str);
        if (!file.exists()) {
            return true;
        }
        for (File file2 : file.listFiles(new anr(this, str2))) {
            try {
                FileUtils.cleanDirectory(file2);
            } catch (IOException e) {
                String str4 = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("failed to delete artifact files for directory ");
                zZm2.append(file2.getName());
                Log.e(str4, zZm2.toString(), e);
                return false;
            }
        }
        for (File file3 : file.listFiles(new oQb(this, str3))) {
            file3.delete();
        }
        return true;
    }
}
