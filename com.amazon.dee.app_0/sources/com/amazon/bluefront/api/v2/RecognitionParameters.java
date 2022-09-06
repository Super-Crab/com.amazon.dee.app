package com.amazon.bluefront.api.v2;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/* loaded from: classes11.dex */
public class RecognitionParameters implements Comparable<RecognitionParameters> {
    private String mApplicationId;
    private DialogHint mDialogHint;
    private int mMaxNBest;
    private String mModelSetVersion;
    private String mOutputFormat;
    private WakewordIndices mWwIndices;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RecognitionParameters) && compareTo((RecognitionParameters) obj) == 0;
    }

    public String getApplicationId() {
        return this.mApplicationId;
    }

    public DialogHint getDialogHint() {
        return this.mDialogHint;
    }

    public int getMaxNBest() {
        return this.mMaxNBest;
    }

    public String getModelSetVersion() {
        return this.mModelSetVersion;
    }

    public String getOutputFormat() {
        return this.mOutputFormat;
    }

    public WakewordIndices getWwIndices() {
        return this.mWwIndices;
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getMaxNBest()).append(getOutputFormat()).append(getApplicationId()).append(getDialogHint()).append(getModelSetVersion()).append(getWwIndices()).hashCode();
    }

    public void setApplicationId(String str) {
        this.mApplicationId = str;
    }

    public void setDialogHint(DialogHint dialogHint) {
        this.mDialogHint = dialogHint;
    }

    public void setMaxNBest(int i) {
        this.mMaxNBest = i;
    }

    public void setModelSetVersion(String str) {
        this.mModelSetVersion = str;
    }

    public void setOutputFormat(String str) {
        this.mOutputFormat = str;
    }

    public void setWwIndices(WakewordIndices wakewordIndices) {
        this.mWwIndices = wakewordIndices;
    }

    @Override // java.lang.Comparable
    public int compareTo(RecognitionParameters recognitionParameters) {
        return new CompareToBuilder().append(getMaxNBest(), recognitionParameters.getMaxNBest()).append(getOutputFormat(), recognitionParameters.getOutputFormat()).append(getApplicationId(), recognitionParameters.getApplicationId()).append(getDialogHint(), recognitionParameters.getDialogHint()).append(getModelSetVersion(), recognitionParameters.getModelSetVersion()).append(getWwIndices(), recognitionParameters.getWwIndices()).toComparison();
    }
}
