package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
/* compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* loaded from: classes3.dex */
public interface ValueEncoder<T> extends Encoder<T, ValueEncoderContext> {
    @Override // com.google.firebase.encoders.Encoder
    /* synthetic */ void encode(@Nullable TValue tvalue, @NonNull TContext tcontext) throws EncodingException, IOException;
}
