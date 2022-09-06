package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ck implements ci {
    private final ci ik;
    private cf il;
    private cf im;

    public ck(ci ciVar, Context context) {
        this.il = null;
        this.im = null;
        this.ik = ciVar;
        String az = ie.az(context);
        if (az != null) {
            this.il = new cf(az, true);
        }
        String aG = jb.aG(context);
        if (aG != null) {
            this.im = new cf(aG, true);
        }
    }

    @Override // com.amazon.identity.auth.device.ci
    public cf aP(String str) throws DeviceDataStoreException {
        if (this.il != null && "DeviceType".equals(str)) {
            return this.il;
        }
        if (this.im != null && DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER.equals(str)) {
            return this.im;
        }
        return this.ik.aP(str);
    }
}
