package com.amazon.alexa.externalnotifications.capability.models;

import com.amazon.alexa.externalnotifications.capability.models.StronglyTypedString;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes7.dex */
public abstract class NotificationId implements StronglyTypedString {
    public static NotificationId create(String str) {
        return new AutoValue_NotificationId(str);
    }

    public static StronglyTypedString.StronglyTypedStringAdapter<NotificationId> getTypeAdapter() {
        return new StronglyTypedString.StronglyTypedStringAdapter<NotificationId>() { // from class: com.amazon.alexa.externalnotifications.capability.models.NotificationId.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.externalnotifications.capability.models.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public NotificationId mo1304instantiate(String str) {
                return NotificationId.create(str);
            }
        };
    }
}
