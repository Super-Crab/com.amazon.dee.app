package com.amazon.alexa.biloba.model;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class DeviceInfo {
    private List<AlertCondition> conditionList = new ArrayList();
    private final String deviceName;
    private final String id;
    private boolean selected;

    public DeviceInfo(String str, String str2, boolean z) {
        this.id = str;
        this.deviceName = str2;
        this.selected = z;
    }

    public void addCondition(AlertCondition alertCondition) {
        this.conditionList.add(alertCondition);
    }

    public List<AlertCondition> getConditions() {
        return this.conditionList;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getId() {
        return this.id;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public String toString() {
        return String.format("id: %s, name: %s, selected: %s", this.id, this.deviceName, Boolean.valueOf(this.selected));
    }
}
