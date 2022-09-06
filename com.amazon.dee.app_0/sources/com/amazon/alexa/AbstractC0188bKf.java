package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.AbstractC0188bKf;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
/* compiled from: SpiVersion.java */
@AutoValue
/* renamed from: com.amazon.alexa.bKf  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0188bKf implements StronglyTypedString {
    public static final AbstractC0188bKf zZm = zZm("");

    public static AbstractC0188bKf zZm(String str) {
        return new BSD(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<AbstractC0188bKf> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<AbstractC0188bKf>() { // from class: com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.SpiVersion$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public AbstractC0188bKf mo1132instantiate(@NonNull String str) {
                return AbstractC0188bKf.zZm(str);
            }
        };
    }
}
