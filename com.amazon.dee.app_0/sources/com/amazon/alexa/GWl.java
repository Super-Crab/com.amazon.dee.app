package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.GWl;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
/* compiled from: PlaybackSessionIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class GWl implements StronglyTypedString {
    public static final GWl zZm = zZm("NONE");

    public static GWl zZm(String str) {
        return new SSw(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<GWl> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<GWl>() { // from class: com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.PlaybackSessionIdentifier$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public GWl mo1132instantiate(@NonNull String str) {
                return GWl.zZm(str);
            }
        };
    }
}
