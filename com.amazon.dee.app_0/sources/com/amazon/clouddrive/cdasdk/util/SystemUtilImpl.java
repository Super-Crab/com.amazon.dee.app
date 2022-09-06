package com.amazon.clouddrive.cdasdk.util;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Base64;
import androidx.annotation.NonNull;
import java.nio.charset.StandardCharsets;
import java.util.Random;
/* loaded from: classes11.dex */
public class SystemUtilImpl implements SystemUtil {
    private final Random random = new Random();

    @Override // com.amazon.clouddrive.cdasdk.util.SystemUtil
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.amazon.clouddrive.cdasdk.util.SystemUtil
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    @Override // com.amazon.clouddrive.cdasdk.util.SystemUtil
    @NonNull
    public String getBase64(@NonNull String str, int i) {
        return Base64.encodeToString(str.getBytes(StandardCharsets.UTF_8), i);
    }

    @Override // com.amazon.clouddrive.cdasdk.util.SystemUtil
    public void interrupt() {
        Thread.currentThread().interrupt();
    }

    @Override // com.amazon.clouddrive.cdasdk.util.SystemUtil
    public int randomNextInt(int i) {
        return this.random.nextInt(i);
    }

    @Override // com.amazon.clouddrive.cdasdk.util.SystemUtil
    @NonNull
    public String replaceUriPath(@NonNull String str, @NonNull String str2) {
        return Uri.parse(str).buildUpon().path(str2).build().toString() + "/";
    }

    @Override // com.amazon.clouddrive.cdasdk.util.SystemUtil
    public void sleep(long j) throws InterruptedException {
        Thread.sleep(j);
    }
}
