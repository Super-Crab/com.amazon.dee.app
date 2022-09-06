package com.google.firebase.encoders;

import androidx.annotation.NonNull;
/* compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* loaded from: classes3.dex */
public final class EncodingException extends Exception {
    public EncodingException(@NonNull String str) {
        super(str);
    }

    public EncodingException(@NonNull String str, @NonNull Exception exc) {
        super(str, exc);
    }
}
