package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class HashKeyRange implements Serializable {
    private String endingHashKey;
    private String startingHashKey;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HashKeyRange)) {
            return false;
        }
        HashKeyRange hashKeyRange = (HashKeyRange) obj;
        if ((hashKeyRange.getStartingHashKey() == null) ^ (getStartingHashKey() == null)) {
            return false;
        }
        if (hashKeyRange.getStartingHashKey() != null && !hashKeyRange.getStartingHashKey().equals(getStartingHashKey())) {
            return false;
        }
        if ((hashKeyRange.getEndingHashKey() == null) ^ (getEndingHashKey() == null)) {
            return false;
        }
        return hashKeyRange.getEndingHashKey() == null || hashKeyRange.getEndingHashKey().equals(getEndingHashKey());
    }

    public String getEndingHashKey() {
        return this.endingHashKey;
    }

    public String getStartingHashKey() {
        return this.startingHashKey;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStartingHashKey() == null ? 0 : getStartingHashKey().hashCode()) + 31) * 31;
        if (getEndingHashKey() != null) {
            i = getEndingHashKey().hashCode();
        }
        return hashCode + i;
    }

    public void setEndingHashKey(String str) {
        this.endingHashKey = str;
    }

    public void setStartingHashKey(String str) {
        this.startingHashKey = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStartingHashKey() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StartingHashKey: ");
            outline1072.append(getStartingHashKey());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getEndingHashKey() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("EndingHashKey: ");
            outline1073.append(getEndingHashKey());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public HashKeyRange withEndingHashKey(String str) {
        this.endingHashKey = str;
        return this;
    }

    public HashKeyRange withStartingHashKey(String str) {
        this.startingHashKey = str;
        return this;
    }
}
