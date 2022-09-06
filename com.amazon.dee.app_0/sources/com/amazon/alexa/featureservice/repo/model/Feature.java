package com.amazon.alexa.featureservice.repo.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes7.dex */
public class Feature {
    private String allocationId;
    private String featureName;
    private String treatment;
    private boolean triggerOnUse;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Feature)) {
            return false;
        }
        Feature feature = (Feature) obj;
        return this.featureName.equals(feature.featureName) && this.treatment.equals(feature.treatment) && this.triggerOnUse == feature.triggerOnUse && this.allocationId.equals(feature.allocationId);
    }

    public String getAllocationId() {
        return this.allocationId;
    }

    public String getFeatureName() {
        return this.featureName;
    }

    public String getTreatment() {
        return this.treatment;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isTriggerOnUse() {
        return this.triggerOnUse;
    }

    public void setAllocationId(String str) {
        this.allocationId = str;
    }

    public void setFeatureName(String str) {
        this.featureName = str;
    }

    public void setTreatment(String str) {
        this.treatment = str;
    }

    public void setTriggerOnUse(boolean z) {
        this.triggerOnUse = z;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Feature{featureName='");
        GeneratedOutlineSupport1.outline176(outline107, this.featureName, Chars.QUOTE, ", treatment='");
        GeneratedOutlineSupport1.outline176(outline107, this.treatment, Chars.QUOTE, ", triggerOnUse=");
        outline107.append(this.triggerOnUse);
        outline107.append(", allocationId='");
        return GeneratedOutlineSupport1.outline90(outline107, this.allocationId, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
