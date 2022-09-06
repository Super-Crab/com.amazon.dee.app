package com.amazon.identity.auth.device.storage;

import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.cw;
import com.amazon.identity.auth.device.du;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.eh;
import com.amazon.identity.auth.device.framework.AmazonIntentService;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.PendingIntentWrapper;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.gh;
import com.amazon.identity.auth.device.gs;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.je;
import com.amazon.identity.auth.device.jj;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class DatabaseCleaner {
    private static final String TAG = "com.amazon.identity.auth.device.storage.DatabaseCleaner";
    private static final Object[] aM = new Object[0];
    private static long nT = jj.b(1, TimeUnit.MILLISECONDS);
    private final LocalDataStorage gv;
    private final cw nU;
    private final eh nV;
    private final ed o;
    private final gg w;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class DatabaseCleaningService extends AmazonIntentService {
        private ed o;

        public DatabaseCleaningService() {
            super(DatabaseCleaningService.class.getName());
            setContext(this);
        }

        public static boolean d(ed edVar) {
            return ((gh) edVar.getSystemService("dcp_data_storage_factory")).fe();
        }

        @Override // com.amazon.identity.auth.device.framework.AmazonIntentService
        public void cx() {
            if (!d(this.o)) {
                io.e(AmazonIntentService.TAG, "Ignoring Database cleaning request because this platform does not use distributed data storage");
                return;
            }
            io.i(AmazonIntentService.TAG, "Cleaning database of unneeded items");
            new DatabaseCleaner(this.o).fg();
        }

        public void setContext(Context context) {
            this.o = ed.M(context);
        }
    }

    public DatabaseCleaner(Context context) {
        this.o = ed.M(context);
        this.w = ((gh) this.o.getSystemService("dcp_data_storage_factory")).dV();
        this.gv = (LocalDataStorage) this.o.getSystemService("sso_local_datastorage");
        this.nU = (cw) this.o.getSystemService("sso_alarm_maanger");
        this.nV = (eh) this.o.getSystemService("dcp_system");
    }

    private void b(Collection<du> collection, Collection<Map<String, String>> collection2) {
        for (du duVar : collection) {
            if (!new gs(this.o, duVar).e(collection2)) {
                io.e(TAG, "Was not fully successful remotely removing deleted items");
            }
        }
    }

    private Collection<Map<String, String>> e(du duVar) {
        try {
            return new gs(this.o, duVar).fG();
        } catch (RemoteMAPException e) {
            String str = TAG;
            io.w(str, "Failed to get deleted data from " + duVar.getPackageName(), e);
            return null;
        }
    }

    public void ff() {
        PendingIntentWrapper b;
        synchronized (aM) {
            long currentTimeMillis = this.nV.currentTimeMillis();
            Long dB = je.dB(this.w.C("clean_database_store", "clean_database_time_ms_key"));
            boolean z = true;
            if (dB != null && dB.longValue() > currentTimeMillis) {
                z = false;
            }
            if (!z) {
                b = null;
            } else {
                ed edVar = this.o;
                Intent intent = new Intent("com.amazon.identity.action.CLEAN_DATA");
                intent.setClass(edVar, DatabaseCleaningService.class);
                b = PendingIntentWrapper.b(edVar, intent);
            }
            if (b == null) {
                io.dm(TAG);
            } else {
                io.dm(TAG);
                long j = currentTimeMillis + nT;
                this.nU.a(j, b);
                this.w.g("clean_database_store", "clean_database_time_ms_key", String.valueOf(j));
            }
        }
    }

    public void fg() {
        Collection<Map<String, String>> ft = this.gv.ft();
        if (!(ft != null && !ft.isEmpty())) {
            io.i(TAG, "No Deleted items in local app, skipping cleanup.");
            return;
        }
        Collection<du> cX = MAPApplicationInformationQueryer.E(this.o).cX();
        Collection<Map<String, String>> collection = null;
        for (du duVar : cX) {
            Collection<Map<String, String>> e = e(duVar);
            if (e != null) {
                if (collection != null) {
                    collection.retainAll(e);
                    if (collection.isEmpty()) {
                        break;
                    }
                } else {
                    collection = e;
                }
            } else {
                io.w(TAG, String.format("Remote Package %s is unable to provide any deleted data", duVar.toString()));
            }
        }
        String str = TAG;
        new StringBuilder("Deleting Values: ").append(collection != null ? collection.toString() : "None");
        io.dm(str);
        if (collection != null && collection.size() != 0) {
            b(cX, collection);
        } else {
            io.i(TAG, "No Deleted items to clean from the MAP databases");
        }
    }
}
