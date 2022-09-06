package com.amazon.comms.ringservice.util;

import java.util.List;
/* loaded from: classes12.dex */
public class NameServersResult {
    private boolean defaultNameServerApplied;
    private List<String> nameServers;
    private boolean onlyIPv6ServersPresent;

    public NameServersResult(List<String> list, boolean z, boolean z2) {
        this.nameServers = list;
        this.onlyIPv6ServersPresent = z;
        this.defaultNameServerApplied = z2;
    }

    public List<String> getNameServers() {
        return this.nameServers;
    }

    public boolean isDefaultNameServerApplied() {
        return this.defaultNameServerApplied;
    }

    public boolean isOnlyIPv6ServersPresent() {
        return this.onlyIPv6ServersPresent;
    }

    public void setDefaultNameServerApplied(boolean z) {
        this.defaultNameServerApplied = z;
    }

    public void setNameServers(List<String> list) {
        this.nameServers = list;
    }

    public void setOnlyIPv6ServersPresent(boolean z) {
        this.onlyIPv6ServersPresent = z;
    }
}
