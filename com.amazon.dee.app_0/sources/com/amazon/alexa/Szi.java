package com.amazon.alexa;

import android.util.Log;
import com.amazon.dee.app.BuildConfig;
import java.net.InetAddress;
import java.net.UnknownHostException;
/* compiled from: InternetConnectivityVerifier.java */
/* loaded from: classes.dex */
public class Szi {
    public static final String zZm = "Szi";

    public boolean zZm() {
        try {
            return !"".equals(InetAddress.getByName(BuildConfig.AUTH_HOST).toString());
        } catch (SecurityException e) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Security exception resolving hostname ");
            zZm2.append(e.getMessage());
            Log.w(str, zZm2.toString());
            return false;
        } catch (UnknownHostException e2) {
            String str2 = zZm;
            StringBuilder zZm3 = C0179Pya.zZm("Host not recognized ");
            zZm3.append(e2.getMessage());
            Log.w(str2, zZm3.toString());
            return false;
        }
    }
}
