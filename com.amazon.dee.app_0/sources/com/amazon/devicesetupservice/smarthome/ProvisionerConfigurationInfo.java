package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class ProvisionerConfigurationInfo implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.ProvisionerConfigurationInfo");
    private List<String> bleScanFilterList;
    private Map<String, String> featureSettings;

    public boolean equals(Object obj) {
        if (!(obj instanceof ProvisionerConfigurationInfo)) {
            return false;
        }
        ProvisionerConfigurationInfo provisionerConfigurationInfo = (ProvisionerConfigurationInfo) obj;
        return Helper.equals(this.featureSettings, provisionerConfigurationInfo.featureSettings) && Helper.equals(this.bleScanFilterList, provisionerConfigurationInfo.bleScanFilterList);
    }

    public List<String> getBleScanFilterList() {
        return this.bleScanFilterList;
    }

    public Map<String, String> getFeatureSettings() {
        return this.featureSettings;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.featureSettings, this.bleScanFilterList);
    }

    public void setBleScanFilterList(List<String> list) {
        this.bleScanFilterList = list;
    }

    public void setFeatureSettings(Map<String, String> map) {
        this.featureSettings = map;
    }
}
