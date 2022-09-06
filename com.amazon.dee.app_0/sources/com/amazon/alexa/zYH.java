package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.zYH;
import com.google.auto.value.AutoValue;
/* compiled from: PlayerCookie.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class zYH implements StronglyTypedString {
    public static final zYH zZm = zZm("");

    public static zYH zZm(String str) {
        return new pIy(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<zYH> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<zYH>() { // from class: com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.PlayerCookie$1
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate  reason: collision with other method in class */
            public zYH mo1132instantiate(@NonNull String str) {
                return zYH.zZm(str);
            }
        };
    }
}
