package com.amazon.whisperjoin.common.sharedtypes.devices;

import java.util.Date;
/* loaded from: classes13.dex */
public class DiscoveredRadio {
    private Date mFreshness;
    private final String mRadio;
    private final Object mRadioHandle;
    private int mSignalStrength;

    public DiscoveredRadio(String str, int i, Object obj) {
        if (obj != null) {
            this.mRadio = str;
            this.mRadioHandle = obj;
            this.mFreshness = new Date();
            this.mSignalStrength = i;
            return;
        }
        throw new IllegalArgumentException("radio handle can not be null");
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof DiscoveredRadio)) {
            if (obj != this) {
                return this.mRadio.equals(((DiscoveredRadio) obj).mRadio);
            }
            return true;
        }
        return false;
    }

    public Date getFreshness() {
        return (Date) this.mFreshness.clone();
    }

    public String getRadio() {
        return this.mRadio;
    }

    public <HandleType> HandleType getRadioHandle() {
        try {
            return (HandleType) this.mRadioHandle;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public int getSignalStrength() {
        return this.mSignalStrength;
    }

    public int hashCode() {
        return this.mRadio.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateFreshness(Date date) {
        this.mFreshness = (Date) date.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateSignalStrength(int i) {
        this.mSignalStrength = i;
    }
}
