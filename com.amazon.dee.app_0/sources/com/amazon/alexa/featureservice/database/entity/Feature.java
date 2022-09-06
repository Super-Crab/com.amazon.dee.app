package com.amazon.alexa.featureservice.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
@Entity
/* loaded from: classes7.dex */
public class Feature {
    @ColumnInfo(name = "allocation_id")
    public String allocationId;
    @NonNull
    @PrimaryKey
    public String featureName;
    @ColumnInfo(name = "should_record_trigger")
    public boolean shouldRecordTrigger;
    @ColumnInfo(name = FeatureConstants.Network.TREATMENT)
    public String treatment;

    @Ignore
    public Feature(@NonNull String str, String str2, boolean z, String str3) {
        this.featureName = str;
        this.treatment = str2;
        this.shouldRecordTrigger = z;
        this.allocationId = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Feature)) {
            return false;
        }
        Feature feature = (Feature) obj;
        return this.featureName.equals(feature.featureName) && this.treatment.equals(feature.treatment) && this.shouldRecordTrigger && feature.shouldRecordTrigger && this.allocationId.equals(feature.allocationId);
    }

    public String getAllocationId() {
        return this.allocationId;
    }

    @NonNull
    public String getFeatureName() {
        return this.featureName;
    }

    public String getTreatment() {
        return this.treatment;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isShouldRecordTrigger() {
        return this.shouldRecordTrigger;
    }

    public void setAllocationId(String str) {
        this.allocationId = str;
    }

    public void setFeatureName(@NonNull String str) {
        this.featureName = str;
    }

    public void setShouldRecordTrigger(boolean z) {
        this.shouldRecordTrigger = z;
    }

    public void setTreatment(String str) {
        this.treatment = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        outline107.append(this.featureName);
        outline107.append(", ");
        outline107.append(this.treatment);
        outline107.append(", ");
        outline107.append(this.shouldRecordTrigger);
        outline107.append(", ");
        return GeneratedOutlineSupport1.outline91(outline107, this.allocationId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public Feature() {
    }
}
