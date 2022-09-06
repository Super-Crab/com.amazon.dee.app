package com.amazon.alexa.accessory.notificationpublisher.notificationsource;

import androidx.annotation.NonNull;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.google.common.base.Strings;
import java.util.Objects;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class CommsNotificationSource extends NotificationSource {
    private static final int MAXIMUM_CONTACT_LENGTH = 32;
    private String contactIdentifier;

    public CommsNotificationSource(@NonNull String str, @NonNull String str2) throws IllegalArgumentException {
        super(str);
        if (!Strings.isNullOrEmpty(str2)) {
            this.contactIdentifier = str2;
            return;
        }
        throw new IllegalArgumentException("Cannot create a source without valid package and contact");
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    @NonNull
    public String displayString() {
        return String.format("Package: %s, Contact: %s", this.packageId, this.contactIdentifier);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    public boolean equals(Object obj) {
        if (!(obj instanceof CommsNotificationSource)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        CommsNotificationSource commsNotificationSource = (CommsNotificationSource) obj;
        return commsNotificationSource.packageId.equals(this.packageId) && commsNotificationSource.contactIdentifier.equals(this.contactIdentifier);
    }

    public String getContact() {
        return this.contactIdentifier;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    public String getSourceId() {
        return getPackageId() + ProcessIdUtil.DEFAULT_PROCESSID + getContact().substring(0, Math.min(getContact().length(), 32));
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource
    public int hashCode() {
        return Objects.hash(this.packageId, this.contactIdentifier);
    }

    public CommsNotificationSource(@NonNull JSONObject jSONObject) throws IllegalArgumentException {
        super(jSONObject);
        String optString = jSONObject.optString(ContactProviderContract.CONTACT_PATH);
        if (!Strings.isNullOrEmpty(optString)) {
            this.contactIdentifier = optString;
            return;
        }
        throw new IllegalArgumentException("Cannot create a source without valid package and contact");
    }
}
