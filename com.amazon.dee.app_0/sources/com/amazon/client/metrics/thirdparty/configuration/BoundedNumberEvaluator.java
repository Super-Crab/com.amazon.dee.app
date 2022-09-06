package com.amazon.client.metrics.thirdparty.configuration;

import com.amazon.dp.logger.DPLogger;
/* loaded from: classes11.dex */
public class BoundedNumberEvaluator {
    DPLogger log = new DPLogger("BoundedNumberEvaluator");
    final String mFieldName;
    final long mMax;
    final long mMin;
    final long mValue;

    public BoundedNumberEvaluator(String str, long j, long j2, long j3) {
        this.mMin = j;
        this.mMax = j2;
        if (j3 < this.mMin) {
            this.log.verbose("fieldName", "value", Long.valueOf(j3), "less than min value", Long.valueOf(this.mMin), "field name", "using min value");
            this.mValue = this.mMin;
        } else if (j3 > this.mMax) {
            this.log.verbose("fieldName", "value", Long.valueOf(j3), "greater than max value", Long.valueOf(this.mMax), "using max value");
            this.mValue = this.mMax;
        } else {
            this.mValue = j3;
        }
        this.mFieldName = str;
    }

    public long getValue() {
        return this.mValue;
    }
}
