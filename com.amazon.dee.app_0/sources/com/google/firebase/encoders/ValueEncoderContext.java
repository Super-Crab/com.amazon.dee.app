package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
/* compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* loaded from: classes3.dex */
public interface ValueEncoderContext {
    @NonNull
    /* renamed from: add */
    ValueEncoderContext mo8293add(double d) throws IOException, EncodingException;

    @NonNull
    /* renamed from: add */
    ValueEncoderContext mo8294add(int i) throws IOException, EncodingException;

    @NonNull
    /* renamed from: add */
    ValueEncoderContext mo8295add(long j) throws IOException, EncodingException;

    @NonNull
    /* renamed from: add */
    ValueEncoderContext mo8296add(@Nullable String str) throws IOException, EncodingException;

    @NonNull
    /* renamed from: add */
    ValueEncoderContext mo8302add(boolean z) throws IOException, EncodingException;

    @NonNull
    /* renamed from: add */
    ValueEncoderContext mo8303add(@NonNull byte[] bArr) throws IOException, EncodingException;
}
