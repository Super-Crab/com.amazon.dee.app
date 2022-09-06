package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class cg implements ci {
    private static final String TAG = "com.amazon.identity.auth.device.cg";
    private static cg ie;
    private final aq dC;
    private final Context mContext;
    private final gg w;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cg(Context context) {
        this.mContext = ed.M(context);
        this.dC = new aq(this.mContext);
        this.w = ((gh) this.mContext.getSystemService("dcp_data_storage_factory")).dV();
    }

    public static void generateNewInstance(Context context) {
        ie = new cg(context.getApplicationContext());
    }

    public static synchronized cg u(Context context) {
        cg cgVar;
        synchronized (cg.class) {
            if (ie == null || jk.gR()) {
                generateNewInstance(context);
            }
            cgVar = ie;
        }
        return cgVar;
    }

    @Override // com.amazon.identity.auth.device.ci
    public cf aP(String str) throws DeviceDataStoreException {
        im dl = im.dl(str);
        if (dl.getKey().equals(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER)) {
            return bO();
        }
        if (dl.getKey().equals("DeviceType")) {
            return e(dl);
        }
        if (dl.getKey().equals("Default COR")) {
            return bL();
        }
        if (dl.getKey().equals("Default PFM")) {
            return bM();
        }
        if (dl.getKey().equals("Client Id")) {
            return bN();
        }
        if (mz.aW(this.mContext)) {
            String C = this.w.C("device.metadata", str);
            if (C == null) {
                String str2 = TAG;
                io.i(str2, "device attribute " + str + " not found in datastore");
                return null;
            }
            return new cf(C, true);
        }
        String str3 = TAG;
        StringBuilder sb = new StringBuilder("Key : ");
        sb.append(str);
        sb.append(" not found. Generic keys are not supported on this platform.");
        io.dm(str3);
        return null;
    }

    protected cf bL() {
        return new cf(this.dC.at(), false);
    }

    protected cf bM() {
        return new cf(this.dC.au(), false);
    }

    protected cf bN() throws DeviceDataStoreException {
        return new cf(OpenIdRequest.m(bO().value, iw.c(this.mContext, DeviceAttribute.CentralDeviceType)), true);
    }

    protected cf bO() throws DeviceDataStoreException {
        try {
            return new cf(cj.x(this.mContext).getDeviceSerialNumber(), true);
        } catch (UnsupportedOperationException unused) {
            throw new DeviceDataStoreException("Fetching DSN on this device is not supported while unregistered.");
        }
    }

    protected cf e(im imVar) throws DeviceDataStoreException {
        String s = ie.s(this.mContext, imVar.getPackageName());
        if (!TextUtils.isEmpty(s)) {
            return new cf(s, true);
        }
        throw new DeviceDataStoreException("Value of device type is null.  This is expected on Grover V1 for the central device Type when the device is not registered.");
    }
}
