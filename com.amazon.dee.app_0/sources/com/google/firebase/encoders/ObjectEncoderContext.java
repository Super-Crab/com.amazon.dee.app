package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
/* compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* loaded from: classes3.dex */
public interface ObjectEncoderContext {
    @NonNull
    /* renamed from: add */
    ObjectEncoderContext mo8297add(@NonNull String str, double d) throws IOException, EncodingException;

    @NonNull
    /* renamed from: add */
    ObjectEncoderContext mo8298add(@NonNull String str, int i) throws IOException, EncodingException;

    @NonNull
    /* renamed from: add */
    ObjectEncoderContext mo8299add(@NonNull String str, long j) throws IOException, EncodingException;

    @NonNull
    /* renamed from: add */
    ObjectEncoderContext mo8300add(@NonNull String str, @Nullable Object obj) throws IOException, EncodingException;

    @NonNull
    /* renamed from: add */
    ObjectEncoderContext mo8301add(@NonNull String str, boolean z) throws IOException, EncodingException;

    @NonNull
    ObjectEncoderContext nested(@NonNull String str) throws IOException;
}
