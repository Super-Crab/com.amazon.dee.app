package com.amazon.identity.auth.device;

import amazon.os.Build;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.framework.InvalidEnumValueException;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.utils.BuildConfiguration;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class cx extends ea {
    private static final String TAG = ea.class.getName();
    private static final eg iE = new eg();
    protected final ed o;

    public cx(Context context) {
        this.o = ed.M(context.getApplicationContext());
    }

    public static BuildConfiguration cv() {
        if (!mz.iQ()) {
            return null;
        }
        return cw();
    }

    private static final BuildConfiguration cw() {
        String str = Build.TYPE;
        try {
            return BuildConfiguration.fromString(str);
        } catch (InvalidEnumValueException unused) {
            io.e(TAG, "Unable to determine the build type : ".concat(String.valueOf(str)));
            return BuildConfiguration.User;
        }
    }

    public static BuildConfiguration y(Context context) {
        if (!mz.aY(context)) {
            return null;
        }
        return cw();
    }

    public boolean bg(String str) {
        return !TextUtils.isEmpty(str) && !str.toLowerCase(Locale.US).equals("unknown");
    }

    @Override // com.amazon.identity.auth.device.ea
    public long cr() {
        int i;
        if (mz.aY(this.o)) {
            if (mz.iS()) {
                String str = TAG;
                io.i(str, "Amazon Platform is of version: " + Build.VERSION.SERIAL);
                if (Build.VERSION.SERIAL == null) {
                    return 0L;
                }
                i = Integer.parseInt(Build.VERSION.SERIAL);
            } else {
                long dC = je.dC(iE.get("ro.build.version.number"));
                io.i(TAG, "Amazon Platform is of version: ".concat(String.valueOf(dC)));
                return dC;
            }
        } else {
            i = hv.gs().qV;
        }
        return i;
    }

    @Override // com.amazon.identity.auth.device.ea
    public String cs() {
        jt gZ = jt.gZ();
        if (gZ != null) {
            String gX = gZ.gX();
            if (!TextUtils.isEmpty(gX)) {
                return gX;
            }
        }
        io.dm(TAG);
        return null;
    }

    @Override // com.amazon.identity.auth.device.ea
    public boolean ct() {
        jt gZ = jt.gZ();
        if (gZ != null) {
            return gZ.ct();
        }
        io.i(TAG, "This should be a 1p device, DHA is not supported");
        return false;
    }

    @Override // com.amazon.identity.auth.device.ea
    public String cu() {
        String bn = MAPApplicationInformationQueryer.E(this.o).bn(this.o.getPackageName());
        return bn == null ? getDeviceSerialNumber() : bn;
    }

    @Override // com.amazon.identity.auth.device.ea
    public String f() {
        jt gZ = jt.gZ();
        if (gZ != null) {
            String f = gZ.f();
            if (!TextUtils.isEmpty(f)) {
                return f;
            }
        }
        io.i(TAG, "This should be a 3p device or 1p client side, cannot get DeviceSecret");
        return null;
    }

    @Override // com.amazon.identity.auth.device.ea
    public String getDeviceSerialNumber() {
        String aG = jb.aG(this.o);
        if (!bg(aG)) {
            io.dm(TAG);
            return di.A(this.o).cO();
        }
        return aG;
    }

    @Override // com.amazon.identity.auth.device.ea
    public String getDeviceType() {
        io.i(TAG, "Amazon Device Info will try get central device type");
        return iw.c(this.o, DeviceAttribute.CentralDeviceType);
    }
}
