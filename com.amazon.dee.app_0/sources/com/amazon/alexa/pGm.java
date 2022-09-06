package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.alexaservice.device.AutoValue_DeviceSerialNumber;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.pGm;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
/* compiled from: DeviceSerialNumber.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class pGm implements StronglyTypedString {
    public static pGm zZm(String str) {
        return new AutoValue_DeviceSerialNumber(str);
    }

    public static TypeAdapter zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<pGm>() { // from class: com.amazon.alexa.client.alexaservice.device.DeviceSerialNumber$1
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate  reason: collision with other method in class */
            public pGm mo1132instantiate(@NonNull String str) {
                return pGm.zZm(str);
            }
        };
    }
}
