package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DynamoDBAction implements Serializable {
    private String hashKeyField;
    private String hashKeyType;
    private String hashKeyValue;
    private String operation;
    private String payloadField;
    private String rangeKeyField;
    private String rangeKeyType;
    private String rangeKeyValue;
    private String roleArn;
    private String tableName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DynamoDBAction)) {
            return false;
        }
        DynamoDBAction dynamoDBAction = (DynamoDBAction) obj;
        if ((dynamoDBAction.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (dynamoDBAction.getTableName() != null && !dynamoDBAction.getTableName().equals(getTableName())) {
            return false;
        }
        if ((dynamoDBAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (dynamoDBAction.getRoleArn() != null && !dynamoDBAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((dynamoDBAction.getOperation() == null) ^ (getOperation() == null)) {
            return false;
        }
        if (dynamoDBAction.getOperation() != null && !dynamoDBAction.getOperation().equals(getOperation())) {
            return false;
        }
        if ((dynamoDBAction.getHashKeyField() == null) ^ (getHashKeyField() == null)) {
            return false;
        }
        if (dynamoDBAction.getHashKeyField() != null && !dynamoDBAction.getHashKeyField().equals(getHashKeyField())) {
            return false;
        }
        if ((dynamoDBAction.getHashKeyValue() == null) ^ (getHashKeyValue() == null)) {
            return false;
        }
        if (dynamoDBAction.getHashKeyValue() != null && !dynamoDBAction.getHashKeyValue().equals(getHashKeyValue())) {
            return false;
        }
        if ((dynamoDBAction.getHashKeyType() == null) ^ (getHashKeyType() == null)) {
            return false;
        }
        if (dynamoDBAction.getHashKeyType() != null && !dynamoDBAction.getHashKeyType().equals(getHashKeyType())) {
            return false;
        }
        if ((dynamoDBAction.getRangeKeyField() == null) ^ (getRangeKeyField() == null)) {
            return false;
        }
        if (dynamoDBAction.getRangeKeyField() != null && !dynamoDBAction.getRangeKeyField().equals(getRangeKeyField())) {
            return false;
        }
        if ((dynamoDBAction.getRangeKeyValue() == null) ^ (getRangeKeyValue() == null)) {
            return false;
        }
        if (dynamoDBAction.getRangeKeyValue() != null && !dynamoDBAction.getRangeKeyValue().equals(getRangeKeyValue())) {
            return false;
        }
        if ((dynamoDBAction.getRangeKeyType() == null) ^ (getRangeKeyType() == null)) {
            return false;
        }
        if (dynamoDBAction.getRangeKeyType() != null && !dynamoDBAction.getRangeKeyType().equals(getRangeKeyType())) {
            return false;
        }
        if ((dynamoDBAction.getPayloadField() == null) ^ (getPayloadField() == null)) {
            return false;
        }
        return dynamoDBAction.getPayloadField() == null || dynamoDBAction.getPayloadField().equals(getPayloadField());
    }

    public String getHashKeyField() {
        return this.hashKeyField;
    }

    public String getHashKeyType() {
        return this.hashKeyType;
    }

    public String getHashKeyValue() {
        return this.hashKeyValue;
    }

    public String getOperation() {
        return this.operation;
    }

    public String getPayloadField() {
        return this.payloadField;
    }

    public String getRangeKeyField() {
        return this.rangeKeyField;
    }

    public String getRangeKeyType() {
        return this.rangeKeyType;
    }

    public String getRangeKeyValue() {
        return this.rangeKeyValue;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getTableName() {
        return this.tableName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getOperation() == null ? 0 : getOperation().hashCode())) * 31) + (getHashKeyField() == null ? 0 : getHashKeyField().hashCode())) * 31) + (getHashKeyValue() == null ? 0 : getHashKeyValue().hashCode())) * 31) + (getHashKeyType() == null ? 0 : getHashKeyType().hashCode())) * 31) + (getRangeKeyField() == null ? 0 : getRangeKeyField().hashCode())) * 31) + (getRangeKeyValue() == null ? 0 : getRangeKeyValue().hashCode())) * 31) + (getRangeKeyType() == null ? 0 : getRangeKeyType().hashCode())) * 31;
        if (getPayloadField() != null) {
            i = getPayloadField().hashCode();
        }
        return hashCode + i;
    }

    public void setHashKeyField(String str) {
        this.hashKeyField = str;
    }

    public void setHashKeyType(String str) {
        this.hashKeyType = str;
    }

    public void setHashKeyValue(String str) {
        this.hashKeyValue = str;
    }

    public void setOperation(String str) {
        this.operation = str;
    }

    public void setPayloadField(String str) {
        this.payloadField = str;
    }

    public void setRangeKeyField(String str) {
        this.rangeKeyField = str;
    }

    public void setRangeKeyType(String str) {
        this.rangeKeyType = str;
    }

    public void setRangeKeyValue(String str) {
        this.rangeKeyValue = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTableName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("tableName: ");
            outline1072.append(getTableName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1073.append(getRoleArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getOperation() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("operation: ");
            outline1074.append(getOperation());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getHashKeyField() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("hashKeyField: ");
            outline1075.append(getHashKeyField());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getHashKeyValue() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("hashKeyValue: ");
            outline1076.append(getHashKeyValue());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getHashKeyType() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("hashKeyType: ");
            outline1077.append(getHashKeyType());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getRangeKeyField() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("rangeKeyField: ");
            outline1078.append(getRangeKeyField());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getRangeKeyValue() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("rangeKeyValue: ");
            outline1079.append(getRangeKeyValue());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getRangeKeyType() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("rangeKeyType: ");
            outline10710.append(getRangeKeyType());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getPayloadField() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("payloadField: ");
            outline10711.append(getPayloadField());
            outline107.append(outline10711.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DynamoDBAction withHashKeyField(String str) {
        this.hashKeyField = str;
        return this;
    }

    public DynamoDBAction withHashKeyType(String str) {
        this.hashKeyType = str;
        return this;
    }

    public DynamoDBAction withHashKeyValue(String str) {
        this.hashKeyValue = str;
        return this;
    }

    public DynamoDBAction withOperation(String str) {
        this.operation = str;
        return this;
    }

    public DynamoDBAction withPayloadField(String str) {
        this.payloadField = str;
        return this;
    }

    public DynamoDBAction withRangeKeyField(String str) {
        this.rangeKeyField = str;
        return this;
    }

    public DynamoDBAction withRangeKeyType(String str) {
        this.rangeKeyType = str;
        return this;
    }

    public DynamoDBAction withRangeKeyValue(String str) {
        this.rangeKeyValue = str;
        return this;
    }

    public DynamoDBAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public DynamoDBAction withTableName(String str) {
        this.tableName = str;
        return this;
    }

    public void setHashKeyType(DynamoKeyType dynamoKeyType) {
        this.hashKeyType = dynamoKeyType.toString();
    }

    public void setRangeKeyType(DynamoKeyType dynamoKeyType) {
        this.rangeKeyType = dynamoKeyType.toString();
    }

    public DynamoDBAction withHashKeyType(DynamoKeyType dynamoKeyType) {
        this.hashKeyType = dynamoKeyType.toString();
        return this;
    }

    public DynamoDBAction withRangeKeyType(DynamoKeyType dynamoKeyType) {
        this.rangeKeyType = dynamoKeyType.toString();
        return this;
    }
}
