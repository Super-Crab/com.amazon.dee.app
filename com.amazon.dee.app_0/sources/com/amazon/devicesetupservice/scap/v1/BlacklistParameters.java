package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class BlacklistParameters implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.BlacklistParameters");
    private List<String> deviceIdList;
    private List<BlacklistDevice> devicesToBlacklist;
    private String duration;

    public boolean equals(Object obj) {
        if (!(obj instanceof BlacklistParameters)) {
            return false;
        }
        BlacklistParameters blacklistParameters = (BlacklistParameters) obj;
        return Helper.equals(this.duration, blacklistParameters.duration) && Helper.equals(this.deviceIdList, blacklistParameters.deviceIdList) && Helper.equals(this.devicesToBlacklist, blacklistParameters.devicesToBlacklist);
    }

    public List<String> getDeviceIdList() {
        return this.deviceIdList;
    }

    public List<BlacklistDevice> getDevicesToBlacklist() {
        return this.devicesToBlacklist;
    }

    public String getDuration() {
        return this.duration;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.duration, this.deviceIdList, this.devicesToBlacklist);
    }

    public void setDeviceIdList(List<String> list) {
        this.deviceIdList = list;
    }

    public void setDevicesToBlacklist(List<BlacklistDevice> list) {
        this.devicesToBlacklist = list;
    }

    public void setDuration(String str) {
        this.duration = str;
    }
}
