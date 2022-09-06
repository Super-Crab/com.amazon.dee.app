package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.WifiScanData;
import java.util.List;
/* loaded from: classes12.dex */
public class GetWifiCredentialsProxyInputData implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetWifiCredentialsProxyInputData");
    private List<WifiScanData> wifiScanDataList;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetWifiCredentialsProxyInputData)) {
            return false;
        }
        return Helper.equals(this.wifiScanDataList, ((GetWifiCredentialsProxyInputData) obj).wifiScanDataList);
    }

    public List<WifiScanData> getWifiScanDataList() {
        return this.wifiScanDataList;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.wifiScanDataList);
    }

    public void setWifiScanDataList(List<WifiScanData> list) {
        this.wifiScanDataList = list;
    }
}
