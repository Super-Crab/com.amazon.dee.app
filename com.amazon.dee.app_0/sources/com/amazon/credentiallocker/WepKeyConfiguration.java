package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class WepKeyConfiguration implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.WepKeyConfiguration");
    private int keyIndex;
    private List<String> wepKeyList;

    public boolean equals(Object obj) {
        if (!(obj instanceof WepKeyConfiguration)) {
            return false;
        }
        WepKeyConfiguration wepKeyConfiguration = (WepKeyConfiguration) obj;
        return Helper.equals(Integer.valueOf(this.keyIndex), Integer.valueOf(wepKeyConfiguration.keyIndex)) && Helper.equals(this.wepKeyList, wepKeyConfiguration.wepKeyList);
    }

    public int getKeyIndex() {
        return this.keyIndex;
    }

    public List<String> getWepKeyList() {
        return this.wepKeyList;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Integer.valueOf(this.keyIndex), this.wepKeyList);
    }

    public void setKeyIndex(int i) {
        this.keyIndex = i;
    }

    public void setWepKeyList(List<String> list) {
        this.wepKeyList = list;
    }
}
