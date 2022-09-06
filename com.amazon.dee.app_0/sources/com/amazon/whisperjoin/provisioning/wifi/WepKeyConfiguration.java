package com.amazon.whisperjoin.provisioning.wifi;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes13.dex */
public class WepKeyConfiguration {
    private final int keyIndex;
    private final List<String> wepKeyList;

    public WepKeyConfiguration(List<String> list, int i) {
        this.wepKeyList = list;
        this.keyIndex = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WepKeyConfiguration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WepKeyConfiguration)) {
            return false;
        }
        WepKeyConfiguration wepKeyConfiguration = (WepKeyConfiguration) obj;
        if (!wepKeyConfiguration.canEqual(this)) {
            return false;
        }
        List<String> wepKeyList = getWepKeyList();
        List<String> wepKeyList2 = wepKeyConfiguration.getWepKeyList();
        if (wepKeyList != null ? !wepKeyList.equals(wepKeyList2) : wepKeyList2 != null) {
            return false;
        }
        return getKeyIndex() == wepKeyConfiguration.getKeyIndex();
    }

    public int getKeyIndex() {
        return this.keyIndex;
    }

    public List<String> getWepKeyList() {
        return this.wepKeyList;
    }

    public int hashCode() {
        List<String> wepKeyList = getWepKeyList();
        return getKeyIndex() + (((wepKeyList == null ? 43 : wepKeyList.hashCode()) + 59) * 59);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WepKeyConfiguration(wepKeyList=");
        outline107.append(getWepKeyList());
        outline107.append(", keyIndex=");
        outline107.append(getKeyIndex());
        outline107.append(")");
        return outline107.toString();
    }
}
