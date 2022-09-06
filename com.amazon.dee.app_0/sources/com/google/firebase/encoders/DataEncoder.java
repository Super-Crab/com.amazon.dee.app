package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.Writer;
/* compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* loaded from: classes3.dex */
public interface DataEncoder {
    @NonNull
    String encode(@NonNull Object obj) throws EncodingException;

    void encode(@NonNull Object obj, @NonNull Writer writer) throws IOException, EncodingException;
}
