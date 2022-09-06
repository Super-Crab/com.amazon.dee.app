package com.amazon.bluefront.api.common;
/* loaded from: classes11.dex */
public class Hypothesis implements Comparable<Hypothesis> {
    private int mConfidence;
    private String mUtterance;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Hypothesis) && compareTo((Hypothesis) obj) == 0;
    }

    public int getConfidence() {
        return this.mConfidence;
    }

    public String getUtterance() {
        return this.mUtterance;
    }

    public int hashCode() {
        return getConfidence() + 1 + (getUtterance() == null ? 0 : getUtterance().hashCode());
    }

    public void setConfidence(int i) {
        this.mConfidence = i;
    }

    public void setUtterance(String str) {
        this.mUtterance = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(Hypothesis hypothesis) {
        if (hypothesis == null) {
            return -1;
        }
        if (hypothesis == this) {
            return 0;
        }
        if (getConfidence() < hypothesis.getConfidence()) {
            return -1;
        }
        if (getConfidence() > hypothesis.getConfidence()) {
            return 1;
        }
        String utterance = getUtterance();
        String utterance2 = hypothesis.getUtterance();
        if (utterance != utterance2) {
            if (utterance == null) {
                return -1;
            }
            if (utterance2 == null) {
                return 1;
            }
            int compareTo = utterance.compareTo(utterance2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }
}
