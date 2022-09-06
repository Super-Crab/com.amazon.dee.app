package com.amazon.alexa.alertsca;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.alertsca.utils.StronglyTypedString;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertLabel implements StronglyTypedString {
    public static final AlertLabel NONE = create("");

    public static AlertLabel create(@Nullable String str) {
        if (str == null) {
            return NONE;
        }
        return new AutoValue_AlertLabel(str);
    }

    public static TypeAdapter<AlertLabel> typeAdapter(Gson gson) {
        return new StronglyTypedString.StronglyTypedStringAdapter<AlertLabel>() { // from class: com.amazon.alexa.alertsca.AlertLabel.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.alertsca.utils.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public AlertLabel mo718instantiate(@NonNull String str) {
                return AlertLabel.create(str);
            }
        };
    }
}
