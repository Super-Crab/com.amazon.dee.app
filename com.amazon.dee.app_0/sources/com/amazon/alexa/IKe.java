package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.IKe;
import com.amazon.alexa.client.alexaservice.device.AutoValue_DeviceType;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
/* compiled from: DeviceType.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class IKe implements StronglyTypedString {
    public static IKe zZm(String str) {
        return new AutoValue_DeviceType(str);
    }

    public static TypeAdapter zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<IKe>() { // from class: com.amazon.alexa.client.alexaservice.device.DeviceType$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public IKe mo1132instantiate(@NonNull String str) {
                return IKe.zZm(str);
            }
        };
    }
}
