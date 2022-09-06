package com.amazon.client.metrics.thirdparty;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class DataPoint {
    private final String mName;
    private final int mSamples;
    private final DataPointType mType;
    private final String mValue;

    public DataPoint(String str, String str2, int i, DataPointType dataPointType) {
        validateString(str, "name cannot be null or empty");
        validateString(str2, "value cannot be null or empty");
        if (dataPointType != null) {
            this.mName = str;
            this.mValue = str2;
            this.mSamples = i;
            this.mType = dataPointType;
            return;
        }
        throw new IllegalArgumentException("DataPoint type cannot be null");
    }

    private void validateString(String str, String str2) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(str2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (DataPoint.class != obj.getClass()) {
            return false;
        }
        DataPoint dataPoint = (DataPoint) obj;
        return this.mName.equals(dataPoint.mName) && this.mSamples == dataPoint.mSamples && this.mType.equals(dataPoint.mType) && this.mValue.equals(dataPoint.mValue);
    }

    public String getName() {
        return this.mName;
    }

    public int getSamples() {
        return this.mSamples;
    }

    public DataPointType getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }

    public int hashCode() {
        String str = this.mName;
        int i = 0;
        int hashCode = ((((str == null ? 0 : str.hashCode()) + 31) * 31) + this.mSamples) * 31;
        DataPointType dataPointType = this.mType;
        int hashCode2 = (hashCode + (dataPointType == null ? 0 : dataPointType.hashCode())) * 31;
        String str2 = this.mValue;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" [");
        outline107.append(this.mName);
        outline107.append(",");
        outline107.append(this.mValue);
        outline107.append(",");
        return GeneratedOutlineSupport1.outline86(outline107, this.mSamples, "] ");
    }
}
