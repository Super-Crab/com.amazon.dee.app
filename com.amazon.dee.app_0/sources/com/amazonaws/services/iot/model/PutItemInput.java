package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutItemInput implements Serializable {
    private String tableName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutItemInput)) {
            return false;
        }
        PutItemInput putItemInput = (PutItemInput) obj;
        if ((putItemInput.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        return putItemInput.getTableName() == null || putItemInput.getTableName().equals(getTableName());
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        return 31 + (getTableName() == null ? 0 : getTableName().hashCode());
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTableName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("tableName: ");
            outline1072.append(getTableName());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutItemInput withTableName(String str) {
        this.tableName = str;
        return this;
    }
}
