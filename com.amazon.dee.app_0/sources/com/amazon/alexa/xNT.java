package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_AudioItemIdentifier;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.xNT;
import com.google.auto.value.AutoValue;
/* compiled from: AudioItemIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class xNT implements StronglyTypedString {
    public static final xNT zZm = zZm("NONE");
    public static final xNT BIo = zZm("");

    public static xNT zZm(String str) {
        return new AutoValue_AudioItemIdentifier(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<xNT> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<xNT>() { // from class: com.amazon.alexa.client.alexaservice.audioplayer.payload.AudioItemIdentifier$1
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate  reason: collision with other method in class */
            public xNT mo1132instantiate(@NonNull String str) {
                return xNT.zZm(str);
            }
        };
    }
}
