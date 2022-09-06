package com.amazon.alexa.alertsca.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.alertsca.AlertLabel;
import com.amazon.alexa.alertsca.AlertsToken;
import com.amazon.alexa.alertsca.Asset;
import com.amazon.alexa.alertsca.AssetIdentifier;
import com.amazon.alexa.alertsca.payload.AutoValue_SetAlertsPayload;
import com.amazon.alexa.alertsca.payload.C$AutoValue_SetAlertsPayload;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.Date;
import java.util.List;
import java.util.Set;
@AutoValue
/* loaded from: classes6.dex */
public abstract class SetAlertsPayload {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract SetAlertsPayload build();

        public abstract Builder setAssetPlayOrder(@Nullable List<AssetIdentifier> list);

        public abstract Builder setAssets(@Nullable Set<Asset> set);

        public abstract Builder setBackgroundAlertAsset(@Nullable AssetIdentifier assetIdentifier);

        public abstract Builder setLabel(@Nullable AlertLabel alertLabel);

        public abstract Builder setLoopCount(@Nullable Long l);

        public abstract Builder setLoopPauseInMilliSeconds(@Nullable Long l);

        public abstract Builder setScheduledTime(Date date);

        public abstract Builder setToken(AlertsToken alertsToken);

        public abstract Builder setType(AlertType alertType);
    }

    public static Builder builder() {
        return new C$AutoValue_SetAlertsPayload.Builder();
    }

    public static TypeAdapter<SetAlertsPayload> typeAdapter(Gson gson) {
        return new AutoValue_SetAlertsPayload.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract List<AssetIdentifier> getAssetPlayOrder();

    @Nullable
    public abstract Set<Asset> getAssets();

    @Nullable
    public abstract AssetIdentifier getBackgroundAlertAsset();

    @Nullable
    public abstract AlertLabel getLabel();

    @Nullable
    public abstract Long getLoopCount();

    @Nullable
    public abstract Long getLoopPauseInMilliSeconds();

    public abstract Date getScheduledTime();

    public abstract AlertsToken getToken();

    public abstract AlertType getType();
}
