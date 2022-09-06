package com.amazon.alexa.drive.smart.device.lock.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class Feature {
    @SerializedName("operations")
    private List<Operation> operations;
    @SerializedName("properties")
    private List<Property> properties;

    public List<Operation> getOperations() {
        return this.operations;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setOperations(List<Operation> list) {
        this.operations = list;
    }

    public void setProperties(List<Property> list) {
        this.properties = list;
    }
}
