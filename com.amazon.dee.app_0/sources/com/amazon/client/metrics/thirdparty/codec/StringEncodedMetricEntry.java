package com.amazon.client.metrics.thirdparty.codec;
/* loaded from: classes11.dex */
public class StringEncodedMetricEntry implements EncodedMetricEntry {
    private String mEncodedMetricEntry;

    public StringEncodedMetricEntry(String str) {
        this.mEncodedMetricEntry = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StringEncodedMetricEntry.class != obj.getClass()) {
            return false;
        }
        return this.mEncodedMetricEntry.equals(((StringEncodedMetricEntry) obj).mo2992getEncodedMetricEntry());
    }

    @Override // com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry
    public int getEncodedSize() {
        return this.mEncodedMetricEntry.getBytes().length;
    }

    public int hashCode() {
        return this.mEncodedMetricEntry.hashCode();
    }

    public String toString() {
        return this.mEncodedMetricEntry;
    }

    @Override // com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry
    /* renamed from: getEncodedMetricEntry  reason: collision with other method in class */
    public String mo2992getEncodedMetricEntry() {
        return this.mEncodedMetricEntry;
    }
}
