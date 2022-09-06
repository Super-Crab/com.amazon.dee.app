package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.RemoteConfigurationAndroidClientContextDecorator;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class du {
    private static final String TAG = "com.amazon.identity.auth.device.du";
    private String bk;
    private final String bm;
    private final String kC;
    private Integer kD;
    private Integer kE;
    private Integer kF;
    private String kG;
    private Long kH;
    private Integer kI;
    private String kJ;
    private boolean kK;
    private RemoteMAPException kL;
    private volatile boolean kM;
    private final Context mContext;

    public du(Context context, ProviderInfo providerInfo) {
        this.mContext = ed.M(context);
        this.bm = providerInfo.packageName;
        this.kC = providerInfo.authority;
        this.kK = false;
        this.kM = false;
    }

    private synchronized void dL() throws RemoteMAPException {
        if (this.kL == null) {
            if (this.kK) {
                return;
            }
            dM();
            return;
        }
        throw this.kL;
    }

    private synchronized void dM() throws RemoteMAPException {
        this.kK = true;
        final Uri cB = gq.cB(this.kC);
        try {
            new ec(this.mContext).a(cB, new dj<Object>() { // from class: com.amazon.identity.auth.device.du.1
                @Override // com.amazon.identity.auth.device.dj
                public Object b(ContentProviderClient contentProviderClient) throws Exception {
                    Cursor query = contentProviderClient.query(cB, (String[]) gq.oN.toArray(new String[0]), null, null, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                du.this.kD = je.dA(ib.e(query, "map_major_version"));
                                du.this.kE = je.dA(ib.e(query, "map_minor_version"));
                                du.this.kF = je.dA(ib.e(query, "map_sw_version"));
                                du.this.kJ = ib.e(query, "map_brazil_version");
                                du.this.bk = ib.e(query, "current_device_type");
                                if (du.this.bk == null) {
                                    io.c(du.TAG, "Package %s has a null device type. Defaulting to the central device type", du.this.getPackageName());
                                    du.this.bk = iw.c(du.this.mContext, DeviceAttribute.CentralDeviceType);
                                }
                                if (ib.f(query, "dsn_override")) {
                                    du.this.kG = ib.e(query, "dsn_override");
                                } else {
                                    io.a(du.TAG, "Package %s does not provide a custom DSN override", du.this.bm);
                                }
                                du.this.kI = je.dA(ib.e(query, "map_init_version"));
                                return null;
                            }
                        } finally {
                            ib.b(query);
                        }
                    }
                    io.e(du.TAG, String.format("No version info returned from package %s.", du.this.bm));
                    return null;
                }
            });
            this.kL = null;
        } catch (Exception e) {
            String str = TAG;
            io.e(str, "Failed to query " + getPackageName(), e);
            mq.b("RemoteMapInfoFailure:" + getPackageName(), new String[0]);
            this.kL = new RemoteMAPException(e);
            throw this.kL;
        }
    }

    public int cM() throws RemoteMAPException {
        String str = this.kC;
        if (str == null) {
            io.dm(TAG);
            return dh.z(this.mContext).cM();
        }
        Uri cF = gq.cF(str);
        String str2 = TAG;
        StringBuilder sb = new StringBuilder("Querying content provider URI : ");
        sb.append(cF.toString());
        sb.append(" from app : ");
        sb.append(this.mContext.getPackageName());
        io.dm(str2);
        Integer dA = je.dA(ib.a(new ec(this.mContext), cF, "value"));
        if (dA != null) {
            return dA.intValue();
        }
        throw new RemoteMAPException("Common info version String not a valid integer.");
    }

    public boolean dB() {
        return this.kM;
    }

    public void dC() {
        this.kM = true;
    }

    public synchronized Long dD() {
        if (this.kH == null) {
            this.kH = it.x(this.mContext, getPackageName());
        }
        return this.kH;
    }

    public String dE() throws RemoteMAPException {
        String str;
        if (p.b(this.mContext, getPackageName())) {
            synchronized (this) {
                dL();
                str = this.kG;
            }
            return str;
        }
        io.dm(TAG);
        return null;
    }

    public Integer dF() throws RemoteMAPException {
        dL();
        return this.kD;
    }

    public Integer dG() throws RemoteMAPException {
        dL();
        return this.kE;
    }

    public Integer dH() throws RemoteMAPException {
        dL();
        return this.kF;
    }

    public Integer dI() throws RemoteMAPException {
        dM();
        return this.kI;
    }

    public String dJ() throws RemoteMAPException {
        dL();
        return this.kJ;
    }

    public boolean dK() {
        return this.mContext.getPackageName().equals(getPackageName());
    }

    public synchronized String getDeviceType() throws RemoteMAPException {
        if (this.bk != null) {
            return this.bk;
        } else if (p.b(this.mContext, getPackageName())) {
            dL();
            return this.bk;
        } else {
            io.dm(TAG);
            this.bk = ie.r(this.mContext, getPackageName());
            return this.bk;
        }
    }

    public String getPackageName() {
        return this.bm;
    }

    public String getProviderAuthority() {
        return this.kC;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        a(sb, DatabaseHelper.appInfo_PackageName, getPackageName());
        try {
            a(sb, "DeviceType", getDeviceType());
            a(sb, "MajorVersion", dF());
            a(sb, "MinorVersion", dG());
            a(sb, "SWVersion", dH());
            a(sb, "BrazilVersion", dJ());
            a(sb, RemoteConfigurationAndroidClientContextDecorator.DEVICE_SERIAL_NUMBER, dE());
        } catch (RemoteMAPException e) {
            String str = TAG;
            io.w(str, "Failed to query " + getPackageName(), e);
        }
        a(sb, "MAPInitVersion", this.kI);
        sb.append("]");
        return sb.toString();
    }

    public int a(du duVar) {
        if (duVar == null) {
            return 1;
        }
        try {
            dL();
        } catch (RemoteMAPException unused) {
        }
        try {
            duVar.dL();
        } catch (RemoteMAPException unused2) {
        }
        int compare = ih.compare(this.kD, duVar.kD);
        if (compare != 0) {
            return compare;
        }
        int compare2 = ih.compare(this.kE, duVar.kE);
        if (compare2 != 0) {
            return compare2;
        }
        String packageName = getPackageName();
        String packageName2 = duVar.getPackageName();
        if (packageName == null) {
            return packageName2 != null ? -1 : 0;
        } else if (packageName2 != null) {
            return packageName.compareTo(packageName2);
        } else {
            return 1;
        }
    }

    public du(Context context) {
        this.mContext = ed.M(context);
        this.bm = this.mContext.getPackageName();
        this.kC = null;
        this.kK = false;
        this.kM = false;
    }

    public static int a(du duVar, du duVar2) {
        if (duVar == null) {
            return duVar2 != null ? -1 : 0;
        }
        return duVar.a(duVar2);
    }

    private void a(StringBuilder sb, String str, Object obj) {
        if (obj == null) {
            return;
        }
        sb.append(str);
        sb.append(" = ");
        sb.append(obj);
        sb.append(", ");
    }
}
