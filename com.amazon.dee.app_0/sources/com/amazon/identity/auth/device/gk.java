package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.gt;
import com.amazon.identity.auth.device.storage.DatabaseCleaner;
import com.amazon.identity.auth.device.storage.LambortishClock;
import com.amazon.identity.auth.device.storage.LocalDataStorage;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gk extends gg {
    private static gk oa;
    private final LocalDataStorage gv;
    private final LambortishClock gw;
    private final Context mContext;
    private final gt oc;
    private final MAPApplicationInformationQueryer od;
    private static final Set<String> nZ = new HashSet(Arrays.asList(G("dcp.third.party.device.state", "serial.number"), G("dcp.only.protected.store", "dcp.only.encrypt.key")));
    private static final Executor ob = new el(Executors.newFixedThreadPool(1));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        boolean a(gs gsVar);

        void fl();

        String getName();
    }

    gk(Context context) {
        this.mContext = ed.M(context);
        this.gv = (LocalDataStorage) this.mContext.getSystemService("sso_local_datastorage");
        this.oc = new gt(this.mContext);
        this.gw = LambortishClock.V(this.mContext);
        this.od = MAPApplicationInformationQueryer.E(this.mContext);
    }

    private static String G(String str, String str2) {
        return GeneratedOutlineSupport1.outline75(str, MqttTopic.MULTI_LEVEL_WILDCARD, str2);
    }

    private String H(String str, String str2) {
        io.dm("DistributedDataStorage");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (it.aC(this.mContext)) {
                String.format("Fast return, the current app itself should generate device data: %s", this.mContext.getPackageName());
                io.dm("DistributedDataStorage");
                return null;
            }
            io.i("DistributedDataStorage", String.format("Distributed storage fetches remote common data for %s, %s", str, str2));
            List<du> cY = MAPApplicationInformationQueryer.E(this.mContext).cY();
            io.dm("DistributedDataStorage");
            for (du duVar : cY) {
                if (duVar.dK()) {
                    String.format("Skip current package %s, because it's itself", duVar.getPackageName());
                    io.dm("DistributedDataStorage");
                } else {
                    new StringBuilder("Fetching data from ").append(duVar.getPackageName());
                    io.dm("DistributedDataStorage");
                    try {
                        String C = new gs(this.mContext, duVar).C(str, str2);
                        if (!TextUtils.isEmpty(C)) {
                            String.format("Value of %s, %s is %s", str, str2, C);
                            io.dm("DistributedDataStorage");
                            return C;
                        }
                        continue;
                    } catch (Exception e) {
                        io.e("DistributedDataStorage", "Failed to get common info from remote storage, skipping...", e);
                    }
                }
            }
            return null;
        }
        io.e("DistributedDataStorage", "namespace or key is null, just return null");
        return null;
    }

    public static synchronized gk U(Context context) {
        gk gkVar;
        synchronized (gk.class) {
            if (oa == null || jk.gR()) {
                oa = new gk(context.getApplicationContext());
            }
            gkVar = oa;
        }
        return gkVar;
    }

    private void ey() {
        Collection<Map<String, String>> collection = null;
        for (du duVar : MAPApplicationInformationQueryer.E(this.mContext).cY()) {
            if (!duVar.dK()) {
                try {
                    Integer dI = duVar.dI();
                    if (dI != null && 3 <= dI.intValue()) {
                        try {
                            new StringBuilder("Initializing data storage from ").append(duVar.getPackageName());
                            io.dm("DistributedDataStorage");
                            new StringBuilder("The data sync is: ").append(duVar.toString());
                            io.dm("DistributedDataStorage");
                            collection = new gs(this.mContext, duVar).fF();
                            if (collection != null) {
                                break;
                            }
                        } catch (RemoteMAPException unused) {
                            io.w("DistributedDataStorage", "Failed to get all data from the package");
                            MAPApplicationInformationQueryer.E(this.mContext).P();
                        }
                    } else {
                        StringBuilder sb = new StringBuilder("Can't initialize from ");
                        sb.append(duVar.getPackageName());
                        sb.append(" because its MAP init version is ");
                        sb.append(dI);
                        io.dm("DistributedDataStorage");
                    }
                } catch (RemoteMAPException unused2) {
                    io.w("DistributedDataStorage", "Failed to get MAP init version");
                    MAPApplicationInformationQueryer.E(this.mContext).P();
                }
            }
        }
        if (collection == null) {
            io.i("DistributedDataStorage", "Did not find another MAP application to get initial data from.");
            return;
        }
        this.gv.c(collection);
        b(collection);
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized void B(final String str, final String str2) {
        initialize();
        final Date fp = this.gw.fp();
        if (!this.gv.a(str, str2, fp, false)) {
            io.e("DistributedDataStorage", "Expiring the token was not successful");
        } else {
            a(new a() { // from class: com.amazon.identity.auth.device.gk.7
                @Override // com.amazon.identity.auth.device.gk.a
                public boolean a(gs gsVar) {
                    return gsVar.i(str, str2, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public void fl() {
                    gk.this.gv.c(str, str2, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public String getName() {
                    return "ExpireToken";
                }
            }, (gg.a) null);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized String C(String str, String str2) {
        initialize();
        String C = this.gv.C(str, str2);
        if (!TextUtils.isEmpty(C) || !nZ.contains(G(str, str2))) {
            return C;
        }
        io.i("DistributedDataStorage", String.format("Important value of %s, %s should not be null, force sync the distributed storage", str, str2));
        String H = H(str, str2);
        io.dm("DistributedDataStorage");
        this.gv.c(str, str2, H, this.gw.fp(), false);
        return H;
    }

    public void b(Collection<Map<String, String>> collection) {
        long j = -1;
        for (Map<String, String> map : collection) {
            try {
                long parseLong = Long.parseLong(map.get("timestamp_key"));
                if (parseLong > j) {
                    j = parseLong;
                }
            } catch (NumberFormatException unused) {
            }
        }
        if (j == -1) {
            io.e("DistributedDataStorage", "Not able to find a timestamp from the DB snapshot used to initialize the current apps");
            return;
        }
        this.gw.d(new Date(j));
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized Set<String> cc(String str) {
        initialize();
        return this.gv.cc(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Account ce(String str) {
        return null;
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized Set<String> cf(String str) {
        initialize();
        return this.gv.cf(str);
    }

    public void cl(String str) {
        g("dcp.only.protected.store", "dcp.only.encrypt.key", str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized void eS() {
        initialize();
        final Date fp = this.gw.fp();
        final Collection<Map<String, String>> e = this.gv.e(fp);
        if (e.size() == 0) {
            io.dm("DistributedDataStorage");
        } else {
            a(new a() { // from class: com.amazon.identity.auth.device.gk.1
                @Override // com.amazon.identity.auth.device.gk.a
                public boolean a(gs gsVar) {
                    return gsVar.d(e);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public void fl() {
                    gk.this.gv.f(fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public String getName() {
                    return "SetBulkData";
                }
            }, (gg.a) null);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> eT() {
        initialize();
        return this.gv.eT();
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized void f(final String str, final String str2, final String str3) {
        initialize();
        final Date fp = this.gw.fp();
        if (!this.gv.b(str, str2, str3, fp, false)) {
            io.e("DistributedDataStorage", "Setting the token was not successful");
        } else {
            a(new a() { // from class: com.amazon.identity.auth.device.gk.5
                @Override // com.amazon.identity.auth.device.gk.a
                public boolean a(gs gsVar) {
                    return gsVar.b(str, str2, str3, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public void fl() {
                    gk.this.gv.b(str, str2, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public String getName() {
                    return "SetToken";
                }
            }, (gg.a) null);
        }
    }

    public Set<String> fk() {
        HashSet hashSet = new HashSet();
        io.i("DistributedDataStorage", String.format("Distributed storage fetches remote common data for %s, %s", "dcp.only.protected.store", "dcp.only.encrypt.key"));
        for (du duVar : MAPApplicationInformationQueryer.E(this.mContext).cY()) {
            if (!duVar.dK()) {
                new StringBuilder("Fetching encryption key from ").append(duVar.getPackageName());
                io.dm("DistributedDataStorage");
                try {
                    String C = new gs(this.mContext, duVar).C("dcp.only.protected.store", "dcp.only.encrypt.key");
                    if (!TextUtils.isEmpty(C)) {
                        String.format("MAP encryption key in package %s is %s", duVar.getPackageName(), C);
                        io.dm("DistributedDataStorage");
                        hashSet.add(C);
                    }
                } catch (Exception e) {
                    io.e("DistributedDataStorage", "Failed to get encryption key from remote storage, skipping...", e);
                }
            }
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized void g(final String str, final String str2, final String str3) {
        initialize();
        final Date fp = this.gw.fp();
        if (!this.gv.c(str, str2, str3, fp, false)) {
            io.e("DistributedDataStorage", "Setting the token was not successful");
        } else {
            a(new a() { // from class: com.amazon.identity.auth.device.gk.8
                @Override // com.amazon.identity.auth.device.gk.a
                public boolean a(gs gsVar) {
                    return gsVar.c(str, str2, str3, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public void fl() {
                    gk.this.gv.d(str, str2, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public String getName() {
                    return "SetDeviceData";
                }
            }, (gg.a) null);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized Set<String> getAccounts() {
        initialize();
        return this.gv.getAccounts();
    }

    @Override // com.amazon.identity.auth.device.gg
    public String getDeviceSnapshot() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.gv.fz());
        for (du duVar : this.od.cX()) {
            sb.append(duVar.toString());
        }
        return sb.toString();
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized void initialize() {
        if (new gp(this.mContext, "distributed.datastore.info.store").cu("distributed.datastore.init.key").booleanValue()) {
            return;
        }
        new StringBuilder("Initializing distributed data store for").append(this.mContext.getPackageName());
        io.dm("DistributedDataStorage");
        ey();
        new gp(this.mContext, "distributed.datastore.info.store").b("distributed.datastore.init.key", Boolean.TRUE);
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized void setup() {
        io.dm("DistributedDataStorage");
        try {
            if (this.gv.ft().isEmpty()) {
                io.dm("DistributedDataStorage");
                return;
            }
            io.i("DistributedDataStorage", "Data to delete in the local app. Setting up alarm to clean database");
            new DatabaseCleaner(this.mContext).ff();
        } catch (Exception e) {
            io.w("DistributedDataStorage", "Failed to initialize DatabaseCleaner", e);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized String z(String str, String str2) {
        initialize();
        return this.gv.z(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized void G(final String str) {
        initialize();
        final Date fp = this.gw.fp();
        if (!this.gv.a(str, fp, false)) {
            io.e("DistributedDataStorage", "Removing the account was not successful");
        } else {
            a(new a() { // from class: com.amazon.identity.auth.device.gk.3
                @Override // com.amazon.identity.auth.device.gk.a
                public boolean a(gs gsVar) {
                    return gsVar.c(str, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public void fl() {
                    gk.this.gv.a(str, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public String getName() {
                    return "RemovedAccount";
                }
            }, (gg.a) null);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized boolean a(final String str, final fz fzVar, gg.a aVar) {
        boolean a2;
        initialize();
        b(this.gv.fs());
        final Date fp = this.gw.fp();
        a2 = this.gv.a(str, fzVar, fp, false);
        a(new a() { // from class: com.amazon.identity.auth.device.gk.2
            @Override // com.amazon.identity.auth.device.gk.a
            public boolean a(gs gsVar) {
                return gsVar.a(str, fzVar, fp);
            }

            @Override // com.amazon.identity.auth.device.gk.a
            public void fl() {
                gk.this.gv.a(fzVar, fp);
            }

            @Override // com.amazon.identity.auth.device.gk.a
            public String getName() {
                return "AddAccount";
            }
        }, aVar);
        return a2;
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized String b(String str, String str2) {
        initialize();
        return this.gv.b(str, str2);
    }

    private boolean b(a aVar) {
        boolean z = true;
        for (du duVar : this.od.cX()) {
            if (!duVar.dK()) {
                io.a("Propogating action %s to package %s from package %s", aVar.getName(), duVar.getPackageName(), this.mContext.getPackageName());
                gs gsVar = new gs(this.mContext, duVar);
                int i = 0;
                boolean z2 = false;
                boolean z3 = false;
                do {
                    i++;
                    try {
                        z3 = aVar.a(gsVar);
                        z2 = true;
                    } catch (RuntimeException e) {
                        io.e("DistributedDataStorage", String.format("Package threw runtime exception while propogating action %s", aVar.getName()), e);
                    }
                    if (z2) {
                        break;
                    }
                } while (i < 2);
                if (!z3) {
                    io.w("DistributedDataStorage", String.format("Failed action %s with remote package.", aVar.getName()));
                }
                z &= z3;
            }
        }
        if (z) {
            String.format("Action %s was synced to all other MAP instances successfully", aVar.getName());
            io.dm("DistributedDataStorage");
            aVar.fl();
        }
        return z;
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized void a(final String str, final String str2, final String str3) {
        initialize();
        if (TextUtils.equals(str3, this.gv.b(str, str2))) {
            io.dm("DistributedDataStorage");
            return;
        }
        final Date fp = this.gw.fp();
        if (!this.gv.a(str, str2, str3, fp, false)) {
            io.e("DistributedDataStorage", "Setting the userdata was not successful");
        } else {
            a(new a() { // from class: com.amazon.identity.auth.device.gk.4
                @Override // com.amazon.identity.auth.device.gk.a
                public boolean a(gs gsVar) {
                    return gsVar.a(str, str2, str3, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public void fl() {
                    gk.this.gv.a(str, str2, fp);
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public String getName() {
                    return "SetUserdata";
                }
            }, (gg.a) null);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(fz fzVar) {
        boolean z;
        initialize();
        final String directedId = fzVar.getDirectedId();
        final Map<String, String> eP = fzVar.eP();
        final Map<String, String> eQ = fzVar.eQ();
        if (eP.isEmpty()) {
            Iterator<Map.Entry<String, String>> it2 = eQ.entrySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                Map.Entry<String, String> next = it2.next();
                if (!TextUtils.equals(next.getValue(), this.gv.b(directedId, next.getKey()))) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                io.dm("DistributedDataStorage");
                return;
            }
        }
        final Date fp = this.gw.fp();
        if (!this.gv.a(fzVar, fp, false)) {
            io.e("DistributedDataStorage", "Setting the data was not successful");
        } else {
            a(new a() { // from class: com.amazon.identity.auth.device.gk.6
                @Override // com.amazon.identity.auth.device.gk.a
                public boolean a(gs gsVar) {
                    Map map = eP;
                    if (map != null) {
                        for (Map.Entry entry : map.entrySet()) {
                            if (!gsVar.b(directedId, (String) entry.getKey(), (String) entry.getValue(), fp)) {
                                return false;
                            }
                        }
                    }
                    Map map2 = eQ;
                    if (map2 != null) {
                        for (Map.Entry entry2 : map2.entrySet()) {
                            if (!gsVar.a(directedId, (String) entry2.getKey(), (String) entry2.getValue(), fp)) {
                                return false;
                            }
                        }
                        return true;
                    }
                    return true;
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public void fl() {
                    Map map = eP;
                    if (map != null) {
                        for (String str : map.keySet()) {
                            gk.this.gv.b(directedId, str, fp);
                        }
                    }
                    Map map2 = eQ;
                    if (map2 != null) {
                        for (String str2 : map2.keySet()) {
                            gk.this.gv.a(directedId, str2, fp);
                        }
                    }
                }

                @Override // com.amazon.identity.auth.device.gk.a
                public String getName() {
                    return "SetData";
                }
            }, (gg.a) null);
        }
    }

    private void a(final a aVar, final gg.a aVar2) {
        ob.execute(new Runnable() { // from class: com.amazon.identity.auth.device.gk.9
            @Override // java.lang.Runnable
            public void run() {
                gk.this.a(aVar);
                gg.a aVar3 = aVar2;
                if (aVar3 != null) {
                    aVar3.onSuccess();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(a aVar) {
        gt.a fH = this.oc.fH();
        boolean b = b(aVar);
        if (b) {
            fH.fI();
        }
        return b;
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar, List<String> list) {
        io.e("DistributedDataStorage", "Replace accounts not supported");
        return false;
    }
}
