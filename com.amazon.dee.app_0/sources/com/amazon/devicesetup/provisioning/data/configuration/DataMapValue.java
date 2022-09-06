package com.amazon.devicesetup.provisioning.data.configuration;

import com.google.common.base.Objects;
import java.util.Arrays;
/* loaded from: classes12.dex */
public class DataMapValue {
    private Boolean booleanValue;
    private byte[] bytesValue;
    private Integer integerValue;
    private String stringValue;

    public DataMapValue(byte[] bArr) {
        if (bArr != null) {
            this.bytesValue = (byte[]) bArr.clone();
            return;
        }
        throw new IllegalArgumentException("bytesValue can not be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DataMapValue.class != obj.getClass()) {
            return false;
        }
        DataMapValue dataMapValue = (DataMapValue) obj;
        return Arrays.equals(this.bytesValue, dataMapValue.bytesValue) && Objects.equal(this.stringValue, dataMapValue.stringValue) && Objects.equal(this.integerValue, dataMapValue.integerValue) && Objects.equal(this.booleanValue, dataMapValue.booleanValue);
    }

    public Boolean getBooleanValue() {
        return this.booleanValue;
    }

    public byte[] getBytesValue() {
        byte[] bArr = this.bytesValue;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public Integer getIntegerValue() {
        return this.integerValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public int hashCode() {
        return Objects.hashCode(this.bytesValue, this.stringValue, this.integerValue, this.booleanValue);
    }

    public DataMapValue(String str) {
        if (str != null) {
            this.stringValue = str;
            return;
        }
        throw new IllegalArgumentException("stringValue can not be null");
    }

    public DataMapValue(Integer num) {
        if (num != null) {
            this.integerValue = num;
            return;
        }
        throw new IllegalArgumentException("integerValue can not be null");
    }

    public DataMapValue(Boolean bool) {
        if (bool != null) {
            this.booleanValue = bool;
            return;
        }
        throw new IllegalArgumentException("booleanValue can not be null");
    }
}
