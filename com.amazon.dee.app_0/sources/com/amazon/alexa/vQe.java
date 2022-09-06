package com.amazon.alexa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.YJe;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_ExternalPlayerIdentifier;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.vQe;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
/* compiled from: ExternalPlayerIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class vQe implements StronglyTypedString, YJe.zQM {
    public static final vQe zZm = zZm("");

    public static vQe zZm(@Nullable String str) {
        if (str == null) {
            return new AutoValue_ExternalPlayerIdentifier("");
        }
        return new AutoValue_ExternalPlayerIdentifier(str);
    }

    @Override // com.amazon.alexa.YJe.zQM
    public String name() {
        return getValue();
    }

    public static TypeAdapter zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<vQe>() { // from class: com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.ExternalPlayerIdentifier$1
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate  reason: collision with other method in class */
            public vQe mo1132instantiate(@NonNull String str) {
                return vQe.zZm(str);
            }
        };
    }
}
