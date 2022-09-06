package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.pHD;
import com.google.auto.value.AutoValue;
/* compiled from: PlaybackToken.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class pHD implements StronglyTypedString {
    public static pHD zZm(String str) {
        return new rmc(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<pHD> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<pHD>() { // from class: com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.PlaybackToken$1
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate  reason: collision with other method in class */
            public pHD mo1132instantiate(@NonNull String str) {
                return pHD.zZm(str);
            }
        };
    }
}
