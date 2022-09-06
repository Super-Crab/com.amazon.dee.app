package com.google.android.exoplayer2.transformer;

import android.os.ParcelFileDescriptor;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import java.io.IOException;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
interface Muxer {

    /* loaded from: classes2.dex */
    public interface Factory {
        /* renamed from: create */
        Muxer mo7448create(ParcelFileDescriptor parcelFileDescriptor, String str) throws IOException;

        /* renamed from: create */
        Muxer mo7449create(String str, String str2) throws IOException;

        boolean supportsOutputMimeType(String str);
    }

    int addTrack(Format format);

    void release(boolean z);

    boolean supportsSampleMimeType(@Nullable String str);

    void writeSampleData(int i, ByteBuffer byteBuffer, boolean z, long j);
}
