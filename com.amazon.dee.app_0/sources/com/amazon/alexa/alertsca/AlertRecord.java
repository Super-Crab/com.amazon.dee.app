package com.amazon.alexa.alertsca;

import androidx.annotation.Nullable;
import com.amazon.alexa.alertsca.AutoValue_AlertRecord;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.google.auto.value.AutoValue;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertRecord {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        abstract AlertRecord autoBuild();

        public AlertRecord build() {
            if (autoBuild().getLabel() == null) {
                setLabel(AlertLabel.NONE);
            }
            return autoBuild();
        }

        public abstract Builder setAssetPlayOrder(@Nullable List<AssetIdentifier> list);

        public abstract Builder setAssets(Set<Asset> set);

        public abstract Builder setBackgroundAlertAsset(@Nullable AssetIdentifier assetIdentifier);

        public abstract Builder setLabel(AlertLabel alertLabel);

        public abstract Builder setLoopCount(@Nullable Long l);

        public abstract Builder setLoopPauseInMilliseconds(@Nullable Long l);

        public abstract Builder setScheduledTime(Date date);

        public abstract Builder setToken(AlertsToken alertsToken);

        public abstract Builder setType(AlertType alertType);
    }

    public static Builder builder() {
        return new AutoValue_AlertRecord.Builder().setAssets(new HashSet());
    }

    public boolean equals(Object obj) {
        if (obj instanceof AlertRecord) {
            return getToken().equals(((AlertRecord) obj).getToken());
        }
        return false;
    }

    @Nullable
    public abstract List<AssetIdentifier> getAssetPlayOrder();

    public abstract Set<Asset> getAssets();

    @Nullable
    public abstract AssetIdentifier getBackgroundAlertAsset();

    @Nullable
    public abstract AlertLabel getLabel();

    @Nullable
    public abstract Long getLoopCount();

    @Nullable
    public abstract Long getLoopPauseInMilliseconds();

    public int getNotificationID() {
        return hashCode();
    }

    public abstract Date getScheduledTime();

    public abstract AlertsToken getToken();

    public abstract AlertType getType();

    public boolean hasAssets() {
        return getAssets() != null && !getAssets().isEmpty();
    }

    public int hashCode() {
        return getToken().hashCode();
    }

    public boolean isReminder() {
        return getType() == AlertType.REMINDER;
    }
}
