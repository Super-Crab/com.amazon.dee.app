package com.amazon.identity.auth.device;

import java.util.ArrayList;
import java.util.List;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dz {
    private List<String> ld = new ArrayList();
    private List<String> le = new ArrayList();
    private String mName;

    public dz(String str) {
        this.mName = str;
    }

    public dz bv(String str) {
        this.le.add(str);
        return this;
    }

    public String dQ() {
        return String.format("CREATE TABLE IF NOT EXISTS %s (%s);", this.mName, jf.a(", ", this.le));
    }

    public dz p(String str, String str2) {
        this.ld.add(str);
        List<String> list = this.le;
        list.add(str + " " + str2);
        return this;
    }

    public String toString() {
        return String.format("CREATE TABLE %s (%s);", this.mName, jf.a(", ", this.le));
    }
}
