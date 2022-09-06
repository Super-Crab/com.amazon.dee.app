package com.amazon.alexa.accessory.notificationpublisher.notificationsource;

import androidx.annotation.NonNull;
import com.google.common.base.Strings;
import org.json.JSONObject;
/* loaded from: classes.dex */
public abstract class NotificationSource {
    protected String packageId;

    /* JADX INFO: Access modifiers changed from: protected */
    public NotificationSource(@NonNull String str) throws IllegalArgumentException {
        if (!Strings.isNullOrEmpty(str)) {
            this.packageId = str;
            return;
        }
        throw new IllegalArgumentException("Cannot create a source without valid package identifier");
    }

    public abstract String displayString();

    public abstract boolean equals(Object obj);

    public String getPackageId() {
        return this.packageId;
    }

    public String getSourceId() {
        return getPackageId();
    }

    public abstract int hashCode();

    /* JADX INFO: Access modifiers changed from: protected */
    public NotificationSource(@NonNull JSONObject jSONObject) throws IllegalArgumentException {
        if (jSONObject != null && !Strings.isNullOrEmpty(jSONObject.optString("packageIdentifier", ""))) {
            this.packageId = jSONObject.optString("packageIdentifier");
            return;
        }
        throw new IllegalArgumentException("Cannot create a source without valid notification object");
    }
}
