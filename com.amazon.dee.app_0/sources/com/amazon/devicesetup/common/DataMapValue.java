package com.amazon.devicesetup.common;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class DataMapValue implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.DataMapValue");
    private boolean boolValue;
    private ByteBuffer bytesValue;
    private int integerValue;
    private String stringValue;

    public boolean equals(Object obj) {
        if (!(obj instanceof DataMapValue)) {
            return false;
        }
        DataMapValue dataMapValue = (DataMapValue) obj;
        return Helper.equals(Boolean.valueOf(this.boolValue), Boolean.valueOf(dataMapValue.boolValue)) && Helper.equals(this.stringValue, dataMapValue.stringValue) && Helper.equals(Integer.valueOf(this.integerValue), Integer.valueOf(dataMapValue.integerValue)) && Helper.equals(this.bytesValue, dataMapValue.bytesValue);
    }

    public ByteBuffer getBytesValue() {
        return this.bytesValue;
    }

    public int getIntegerValue() {
        return this.integerValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Boolean.valueOf(this.boolValue), this.stringValue, Integer.valueOf(this.integerValue), this.bytesValue);
    }

    public boolean isBoolValue() {
        return this.boolValue;
    }

    public void setBoolValue(boolean z) {
        this.boolValue = z;
    }

    public void setBytesValue(ByteBuffer byteBuffer) {
        this.bytesValue = byteBuffer;
    }

    public void setIntegerValue(int i) {
        this.integerValue = i;
    }

    public void setStringValue(String str) {
        this.stringValue = str;
    }
}
