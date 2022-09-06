package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.cMY;
import com.amazon.alexa.client.alexaservice.device.AutoValue_FirmwareVersion;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
/* compiled from: FirmwareVersion.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class cMY implements StronglyTypedString {
    public static final cMY zZm = zZm("UNKNOWN");

    public static cMY zZm(String str) {
        return new AutoValue_FirmwareVersion(str);
    }

    public static TypeAdapter zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<cMY>() { // from class: com.amazon.alexa.client.alexaservice.device.FirmwareVersion$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public cMY mo1132instantiate(@NonNull String str) {
                return cMY.zZm(str);
            }
        };
    }
}
