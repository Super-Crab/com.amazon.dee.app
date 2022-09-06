package com.amazon.alexa.alertsca;

import androidx.annotation.NonNull;
import com.amazon.alexa.alertsca.utils.StronglyTypedString;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertsToken implements StronglyTypedString {
    public static AlertsToken create(String str) {
        return new AutoValue_AlertsToken(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<AlertsToken> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<AlertsToken>() { // from class: com.amazon.alexa.alertsca.AlertsToken.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.alertsca.utils.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public AlertsToken mo718instantiate(@NonNull String str) {
                return AlertsToken.create(str);
            }
        };
    }
}
