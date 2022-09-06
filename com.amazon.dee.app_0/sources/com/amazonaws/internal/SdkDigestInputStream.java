package com.amazonaws.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
/* loaded from: classes13.dex */
public class SdkDigestInputStream extends DigestInputStream implements MetricAware {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int SKIP_BUF_SIZE = 2048;

    public SdkDigestInputStream(InputStream inputStream, MessageDigest messageDigest) {
        super(inputStream, messageDigest);
    }

    @Override // com.amazonaws.internal.MetricAware
    @Deprecated
    public final boolean isMetricActivated() {
        if (((DigestInputStream) this).in instanceof MetricAware) {
            return ((MetricAware) ((DigestInputStream) this).in).isMetricActivated();
        }
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        if (j <= 0) {
            return j;
        }
        byte[] bArr = new byte[(int) Math.min((long) PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, j)];
        long j2 = j;
        while (j2 > 0) {
            int read = read(bArr, 0, (int) Math.min(j2, bArr.length));
            if (read == -1) {
                if (j2 != j) {
                    return j - j2;
                }
                return -1L;
            }
            j2 -= read;
        }
        return j;
    }
}
