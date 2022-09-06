package com.amazon.alexa.accessory.notificationpublisher.notificationsource;

import androidx.annotation.NonNull;
import java.util.Objects;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class GenericNotificationSource extends NotificationSource {
    public GenericNotificationSource(@NonNull String str) throws IllegalArgumentException {
        super(str);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    @NonNull
    public String displayString() {
        return String.format("Package: %s", this.packageId);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    public boolean equals(Object obj) {
        if (!(obj instanceof GenericNotificationSource)) {
            return false;
        }
        if (obj != this) {
            return ((GenericNotificationSource) obj).packageId.equals(this.packageId);
        }
        return true;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    public int hashCode() {
        return Objects.hash(this.packageId);
    }

    public GenericNotificationSource(@NonNull JSONObject jSONObject) throws IllegalArgumentException {
        super(jSONObject.optString("packageIdentifier"));
    }
}
