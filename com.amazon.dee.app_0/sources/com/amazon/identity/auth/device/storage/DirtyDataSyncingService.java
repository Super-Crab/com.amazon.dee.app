package com.amazon.identity.auth.device.storage;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.api.MAPInit;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.gh;
import com.amazon.identity.auth.device.io;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class DirtyDataSyncingService extends IntentService {
    private static final String TAG = DirtyDataSyncingService.class.getName();
    private ed o;
    private gg w;

    public DirtyDataSyncingService() {
        super(DirtyDataSyncingService.class.getSimpleName());
        setContext(this);
    }

    public static boolean d(ed edVar) {
        return ((gh) edVar.getSystemService("dcp_data_storage_factory")).fe();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        MAPInit.getInstance(this).initialize();
        this.w = ((gh) this.o.getSystemService("dcp_data_storage_factory")).dV();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        io.i(TAG, String.format("Package %s is syncing dirty data to other processes", getPackageName()));
        if (!d(this.o)) {
            io.e(TAG, "Ignoring dirty data sync request because this platform does not use the distributed datastorage");
        } else {
            this.w.eS();
        }
    }

    void setContext(Context context) {
        this.o = ed.M(context);
    }
}
