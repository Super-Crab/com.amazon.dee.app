package com.google.android.exoplayer2.decoder;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.decoder.DecoderException;
/* loaded from: classes2.dex */
public interface Decoder<I, O, E extends DecoderException> {
    @Nullable
    /* renamed from: dequeueInputBuffer */
    I mo7429dequeueInputBuffer() throws DecoderException;

    @Nullable
    /* renamed from: dequeueOutputBuffer */
    O mo7430dequeueOutputBuffer() throws DecoderException;

    void flush();

    String getName();

    void queueInputBuffer(I i) throws DecoderException;

    void release();
}
