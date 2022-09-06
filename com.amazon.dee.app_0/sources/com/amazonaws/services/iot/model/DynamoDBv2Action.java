package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DynamoDBv2Action implements Serializable {
    private PutItemInput putItem;
    private String roleArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DynamoDBv2Action)) {
            return false;
        }
        DynamoDBv2Action dynamoDBv2Action = (DynamoDBv2Action) obj;
        if ((dynamoDBv2Action.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (dynamoDBv2Action.getRoleArn() != null && !dynamoDBv2Action.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((dynamoDBv2Action.getPutItem() == null) ^ (getPutItem() == null)) {
            return false;
        }
        return dynamoDBv2Action.getPutItem() == null || dynamoDBv2Action.getPutItem().equals(getPutItem());
    }

    public PutItemInput getPutItem() {
        return this.putItem;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31;
        if (getPutItem() != null) {
            i = getPutItem().hashCode();
        }
        return hashCode + i;
    }

    public void setPutItem(PutItemInput putItemInput) {
        this.putItem = putItemInput;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPutItem() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("putItem: ");
            outline1073.append(getPutItem());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DynamoDBv2Action withPutItem(PutItemInput putItemInput) {
        this.putItem = putItemInput;
        return this;
    }

    public DynamoDBv2Action withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }
}
