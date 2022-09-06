package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dh {
    private static final String TAG = "com.amazon.identity.auth.device.dh";
    private static dh jx;
    private final MAPApplicationInformationQueryer jy;
    private final ed o;
    private final gg w;

    dh(Context context) {
        this.o = ed.M(context);
        this.w = ((gh) this.o.getSystemService("dcp_data_storage_factory")).dV();
        this.jy = MAPApplicationInformationQueryer.E(this.o);
    }

    public static String b(gg ggVar) {
        return ggVar.C("dcp.only.protected.store", "dcp.only.encrypt.key");
    }

    public static String c(gg ggVar) {
        return ggVar.C("dcp.third.party.device.state", "serial.number");
    }

    private String cN() {
        String str = TAG;
        String.format(str, "pkg %s is generating DSN", this.o.getPackageName());
        io.dm(str);
        String c = c(this.w);
        if (c == null) {
            if (mz.f(this.o)) {
                c = this.jy.bn(this.o.getPackageName());
            } else if (mz.bi(this.o)) {
                try {
                    c = cd.a(new ec(this.o), "dsn");
                    String str2 = TAG;
                    "MAP retrieved serial number from Amazon Device Information Component: ".concat(String.valueOf(c));
                    io.dm(str2);
                } catch (RemoteMAPException e) {
                    io.e(TAG, "Unable to retrieve Device Serial Number from Amazon Device Information Component, which is present. Falling back to generating 3P value.", e);
                }
            }
            if (TextUtils.isEmpty(c) && it.aC(this.o)) {
                c = UUID.randomUUID().toString().replace(ProcessIdUtil.DEFAULT_PROCESSID, "");
                GeneratedOutlineSupport1.outline161(c, "Generating UUID serial number for third party: ", TAG);
            }
            this.w.g("dcp.third.party.device.state", "serial.number", c);
            GeneratedOutlineSupport1.outline161(c, "MAP generated serial number: ", TAG);
        }
        return c;
    }

    public static int d(gg ggVar) {
        String C = ggVar.C("dcp.third.party.device.state", "info.version");
        String str = TAG;
        "Get commonInfoVersion: ".concat(String.valueOf(C));
        io.dm(str);
        return je.dz(C);
    }

    public static synchronized dh z(Context context) {
        dh dhVar;
        synchronized (dh.class) {
            if (jx == null) {
                jx = new dh(context.getApplicationContext());
            }
            dhVar = jx;
        }
        return dhVar;
    }

    public synchronized int cM() {
        io.i(TAG, String.format("Generating common info for version %d", 1));
        String str = TAG;
        String.format(str, "pkg %s is generating token key", this.o.getPackageName());
        io.dm(str);
        if (b(this.w) == null && it.aC(this.o)) {
            io.dm(TAG);
            this.w.g("dcp.only.protected.store", "dcp.only.encrypt.key", cr.a(dl.C(this.o)));
            io.dm(TAG);
        }
        io.dm(TAG);
        cN();
        this.w.eS();
        this.w.g("dcp.third.party.device.state", "info.version", Integer.toString(1));
        return 1;
    }
}
