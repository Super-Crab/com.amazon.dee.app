package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.ZYY;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
/* compiled from: PlayerVersion.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class ZYY implements StronglyTypedString {
    public static final ZYY zZm = zZm("UNKNOWN");

    public static ZYY zZm(String str) {
        return new ExD(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<ZYY> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<ZYY>() { // from class: com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.PlayerVersion$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public ZYY mo1132instantiate(@NonNull String str) {
                return ZYY.zZm(str);
            }
        };
    }
}
