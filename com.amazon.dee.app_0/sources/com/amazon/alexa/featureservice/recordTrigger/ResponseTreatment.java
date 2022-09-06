package com.amazon.alexa.featureservice.recordTrigger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes7.dex */
class ResponseTreatment {
    private final String allocationId;
    private final String featureName;
    private final String recordStatus;
    private final String treatment;
    private final boolean triggerOnUse;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResponseTreatment(@NonNull String str, @NonNull String str2, @Nullable String str3, boolean z, String str4) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.featureName = str;
        this.treatment = str2;
        this.allocationId = str3;
        this.triggerOnUse = z;
        this.recordStatus = str4;
    }

    @Nullable
    String getAllocationId() {
        return this.allocationId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getFeatureName() {
        return this.featureName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getRecordStatus() {
        return this.recordStatus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getTreatment() {
        return this.treatment;
    }

    boolean isTriggerOnUse() {
        return this.triggerOnUse;
    }

    @NonNull
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(JsonReaderKt.BEGIN_OBJ);
        stringBuffer.append(this.featureName);
        stringBuffer.append(JsonReaderKt.COMMA);
        stringBuffer.append(this.treatment);
        stringBuffer.append(JsonReaderKt.COMMA);
        stringBuffer.append(this.allocationId);
        stringBuffer.append(JsonReaderKt.COMMA);
        stringBuffer.append(this.triggerOnUse);
        stringBuffer.append(JsonReaderKt.COMMA);
        stringBuffer.append(this.recordStatus);
        stringBuffer.append(JsonReaderKt.END_OBJ);
        return stringBuffer.toString();
    }
}
