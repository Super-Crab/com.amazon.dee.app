package com.amazon.bluefront.api.common;

import java.util.List;
/* loaded from: classes11.dex */
public class NBestResult implements Comparable<NBestResult> {
    private List<Hypothesis> mHypotheses;
    private String mId;
    private String mStatus;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof NBestResult) && compareTo((NBestResult) obj) == 0;
    }

    public List<Hypothesis> getHypotheses() {
        return this.mHypotheses;
    }

    public String getId() {
        return this.mId;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getId() == null ? 0 : getId().hashCode()) + 1 + (getStatus() == null ? 0 : getStatus().hashCode());
        if (getHypotheses() != null) {
            i = getHypotheses().hashCode();
        }
        return hashCode + i;
    }

    public void setHypotheses(List<Hypothesis> list) {
        this.mHypotheses = list;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setStatus(String str) {
        this.mStatus = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(NBestResult nBestResult) {
        if (nBestResult == null) {
            return -1;
        }
        if (nBestResult == this) {
            return 0;
        }
        String id = getId();
        String id2 = nBestResult.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int compareTo = id.compareTo(id2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String status = getStatus();
        String status2 = nBestResult.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int compareTo2 = status.compareTo(status2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        List<Hypothesis> hypotheses = getHypotheses();
        List<Hypothesis> hypotheses2 = nBestResult.getHypotheses();
        if (hypotheses != hypotheses2) {
            if (hypotheses == null) {
                return -1;
            }
            if (hypotheses2 == null) {
                return 1;
            }
            if (hypotheses instanceof Comparable) {
                int compareTo3 = ((Comparable) hypotheses).compareTo(hypotheses2);
                if (compareTo3 != 0) {
                    return compareTo3;
                }
            } else if (!hypotheses.equals(hypotheses2)) {
                int hashCode = hypotheses.hashCode();
                int hashCode2 = hypotheses2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
