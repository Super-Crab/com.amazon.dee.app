package com.amazon.alexa;
/* compiled from: AutoValue_DownchannelIdentifier.java */
/* loaded from: classes.dex */
public final class Mzn extends tux {
    public final String zZm;

    public Mzn(String str) {
        if (str != null) {
            this.zZm = str;
            return;
        }
        throw new NullPointerException("Null value");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof tux)) {
            return false;
        }
        return this.zZm.equals(((tux) obj).getValue());
    }

    @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString
    public String getValue() {
        return this.zZm;
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }
}
