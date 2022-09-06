package com.amazon.alexa.featureservice.recordTrigger;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import java.util.Date;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes7.dex */
public class RequestTreatment {
    private final String allocationId;
    private final String featureName;
    @VisibleForTesting
    Date timeUsed;
    private final String treatmentUsed;

    public RequestTreatment(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        Preconditions.checkNotNull(str3);
        this.featureName = str;
        this.treatmentUsed = str2;
        this.allocationId = str3;
        this.timeUsed = new Date();
    }

    @NonNull
    public String getAllocationId() {
        return this.allocationId;
    }

    @NonNull
    public String getFeatureName() {
        return this.featureName;
    }

    @NonNull
    public Date getTimeUsed() {
        return this.timeUsed;
    }

    @NonNull
    public String getTreatmentUsed() {
        return this.treatmentUsed;
    }

    @NonNull
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(JsonReaderKt.BEGIN_OBJ);
        stringBuffer.append(this.featureName);
        stringBuffer.append(JsonReaderKt.COMMA);
        stringBuffer.append(this.treatmentUsed);
        stringBuffer.append(JsonReaderKt.COMMA);
        stringBuffer.append(this.allocationId);
        stringBuffer.append(JsonReaderKt.COMMA);
        stringBuffer.append(this.timeUsed);
        stringBuffer.append(JsonReaderKt.END_OBJ);
        return stringBuffer.toString();
    }
}
