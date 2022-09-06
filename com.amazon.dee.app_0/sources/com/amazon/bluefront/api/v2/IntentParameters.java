package com.amazon.bluefront.api.v2;
/* loaded from: classes11.dex */
public class IntentParameters implements Comparable<IntentParameters> {
    private int mMaxNBest;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof IntentParameters) && compareTo((IntentParameters) obj) == 0;
    }

    public int getMaxNBest() {
        return this.mMaxNBest;
    }

    public int hashCode() {
        return getMaxNBest() + 1;
    }

    public void setMaxNBest(int i) {
        this.mMaxNBest = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(IntentParameters intentParameters) {
        if (intentParameters == null) {
            return -1;
        }
        if (intentParameters == this) {
            return 0;
        }
        if (getMaxNBest() < intentParameters.getMaxNBest()) {
            return -1;
        }
        return getMaxNBest() > intentParameters.getMaxNBest() ? 1 : 0;
    }
}
