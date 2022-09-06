package com.amazon.identity.auth.device;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class mb {
    private final String bn;
    private final Long uM;

    public mb(Long l, String str) {
        this.uM = l;
        this.bn = str;
    }

    public boolean equals(Object obj) {
        boolean equals;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof mb)) {
            return false;
        }
        mb mbVar = (mb) obj;
        String str = this.bn;
        if (str == null) {
            equals = mbVar.ix() == null;
        } else {
            equals = str.equals(mbVar.bn);
        }
        if (!equals) {
            return false;
        }
        if (this.uM != null) {
            return this.bn.equals(mbVar.bn);
        }
        return mbVar.ix() == null;
    }

    public int hashCode() {
        String str = this.bn;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        Long l = this.uM;
        if (l != null) {
            i = l.hashCode();
        }
        return hashCode + i;
    }

    public Long iw() {
        return this.uM;
    }

    public String ix() {
        return this.bn;
    }

    public String toString() {
        Object[] objArr = new Object[2];
        Long l = this.uM;
        objArr[0] = l == null ? "None" : l.toString();
        objArr[1] = this.bn;
        return String.format("Version: %s, ComponentId: %s", objArr);
    }
}
