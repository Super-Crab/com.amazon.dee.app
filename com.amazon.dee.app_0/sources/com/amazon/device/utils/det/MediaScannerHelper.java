package com.amazon.device.utils.det;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.File;
/* loaded from: classes12.dex */
public class MediaScannerHelper {
    private final Context mContext;

    public MediaScannerHelper(Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    public void scanDirectory(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(Uri.fromFile(file2));
                this.mContext.sendBroadcast(intent);
                String str = "Updating media scanner for file: " + file2.getPath();
            }
        }
    }
}
