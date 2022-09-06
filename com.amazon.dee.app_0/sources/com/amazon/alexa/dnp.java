package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.dnp;
import com.google.auto.value.AutoValue;
/* compiled from: InteractionInterfaceName.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class dnp implements StronglyTypedString {
    public static final dnp zZm = zZm("");

    public static dnp zZm(String str) {
        return new bEu(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<dnp> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<dnp>() { // from class: com.amazon.alexa.client.alexaservice.interactions.InteractionInterfaceName$1
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate  reason: collision with other method in class */
            public dnp mo1132instantiate(@NonNull String str) {
                return dnp.zZm(str);
            }
        };
    }
}
