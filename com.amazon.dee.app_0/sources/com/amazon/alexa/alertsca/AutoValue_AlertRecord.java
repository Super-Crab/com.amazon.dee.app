package com.amazon.alexa.alertsca;

import androidx.annotation.Nullable;
import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Date;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
final class AutoValue_AlertRecord extends AlertRecord {
    private final List<AssetIdentifier> assetPlayOrder;
    private final Set<Asset> assets;
    private final AssetIdentifier backgroundAlertAsset;
    private final AlertLabel label;
    private final Long loopCount;
    private final Long loopPauseInMilliseconds;
    private final Date scheduledTime;
    private final AlertsToken token;
    private final AlertType type;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class Builder extends AlertRecord.Builder {
        private List<AssetIdentifier> assetPlayOrder;
        private Set<Asset> assets;
        private AssetIdentifier backgroundAlertAsset;
        private AlertLabel label;
        private Long loopCount;
        private Long loopPauseInMilliseconds;
        private Date scheduledTime;
        private AlertsToken token;
        private AlertType type;

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        AlertRecord autoBuild() {
            String str = "";
            if (this.token == null) {
                str = GeneratedOutlineSupport1.outline72(str, " token");
            }
            if (this.type == null) {
                str = GeneratedOutlineSupport1.outline72(str, " type");
            }
            if (this.scheduledTime == null) {
                str = GeneratedOutlineSupport1.outline72(str, " scheduledTime");
            }
            if (this.assets == null) {
                str = GeneratedOutlineSupport1.outline72(str, " assets");
            }
            if (str.isEmpty()) {
                return new AutoValue_AlertRecord(this.token, this.type, this.scheduledTime, this.assets, this.assetPlayOrder, this.backgroundAlertAsset, this.loopCount, this.loopPauseInMilliseconds, this.label);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        public AlertRecord.Builder setAssetPlayOrder(@Nullable List<AssetIdentifier> list) {
            this.assetPlayOrder = list;
            return this;
        }

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        public AlertRecord.Builder setAssets(Set<Asset> set) {
            if (set != null) {
                this.assets = set;
                return this;
            }
            throw new NullPointerException("Null assets");
        }

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        public AlertRecord.Builder setBackgroundAlertAsset(@Nullable AssetIdentifier assetIdentifier) {
            this.backgroundAlertAsset = assetIdentifier;
            return this;
        }

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        public AlertRecord.Builder setLabel(@Nullable AlertLabel alertLabel) {
            this.label = alertLabel;
            return this;
        }

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        public AlertRecord.Builder setLoopCount(@Nullable Long l) {
            this.loopCount = l;
            return this;
        }

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        public AlertRecord.Builder setLoopPauseInMilliseconds(@Nullable Long l) {
            this.loopPauseInMilliseconds = l;
            return this;
        }

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        public AlertRecord.Builder setScheduledTime(Date date) {
            if (date != null) {
                this.scheduledTime = date;
                return this;
            }
            throw new NullPointerException("Null scheduledTime");
        }

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        public AlertRecord.Builder setToken(AlertsToken alertsToken) {
            if (alertsToken != null) {
                this.token = alertsToken;
                return this;
            }
            throw new NullPointerException("Null token");
        }

        @Override // com.amazon.alexa.alertsca.AlertRecord.Builder
        public AlertRecord.Builder setType(AlertType alertType) {
            if (alertType != null) {
                this.type = alertType;
                return this;
            }
            throw new NullPointerException("Null type");
        }
    }

    @Override // com.amazon.alexa.alertsca.AlertRecord
    @Nullable
    public List<AssetIdentifier> getAssetPlayOrder() {
        return this.assetPlayOrder;
    }

    @Override // com.amazon.alexa.alertsca.AlertRecord
    public Set<Asset> getAssets() {
        return this.assets;
    }

    @Override // com.amazon.alexa.alertsca.AlertRecord
    @Nullable
    public AssetIdentifier getBackgroundAlertAsset() {
        return this.backgroundAlertAsset;
    }

    @Override // com.amazon.alexa.alertsca.AlertRecord
    @Nullable
    public AlertLabel getLabel() {
        return this.label;
    }

    @Override // com.amazon.alexa.alertsca.AlertRecord
    @Nullable
    public Long getLoopCount() {
        return this.loopCount;
    }

    @Override // com.amazon.alexa.alertsca.AlertRecord
    @Nullable
    public Long getLoopPauseInMilliseconds() {
        return this.loopPauseInMilliseconds;
    }

    @Override // com.amazon.alexa.alertsca.AlertRecord
    public Date getScheduledTime() {
        return this.scheduledTime;
    }

    @Override // com.amazon.alexa.alertsca.AlertRecord
    public AlertsToken getToken() {
        return this.token;
    }

    @Override // com.amazon.alexa.alertsca.AlertRecord
    public AlertType getType() {
        return this.type;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlertRecord{token=");
        outline107.append(this.token);
        outline107.append(", type=");
        outline107.append(this.type);
        outline107.append(", scheduledTime=");
        outline107.append(this.scheduledTime);
        outline107.append(", assets=");
        outline107.append(this.assets);
        outline107.append(", assetPlayOrder=");
        outline107.append(this.assetPlayOrder);
        outline107.append(", backgroundAlertAsset=");
        outline107.append(this.backgroundAlertAsset);
        outline107.append(", loopCount=");
        outline107.append(this.loopCount);
        outline107.append(", loopPauseInMilliseconds=");
        outline107.append(this.loopPauseInMilliseconds);
        outline107.append(", label=");
        outline107.append(this.label);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    private AutoValue_AlertRecord(AlertsToken alertsToken, AlertType alertType, Date date, Set<Asset> set, @Nullable List<AssetIdentifier> list, @Nullable AssetIdentifier assetIdentifier, @Nullable Long l, @Nullable Long l2, @Nullable AlertLabel alertLabel) {
        this.token = alertsToken;
        this.type = alertType;
        this.scheduledTime = date;
        this.assets = set;
        this.assetPlayOrder = list;
        this.backgroundAlertAsset = assetIdentifier;
        this.loopCount = l;
        this.loopPauseInMilliseconds = l2;
        this.label = alertLabel;
    }
}
