package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.framework.PendingIntentWrapper;
import com.amazon.identity.auth.device.storage.DirtyDataSyncingService;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gt {
    private final Object[] fD = new Object[0];
    private final cw nU;
    private final eh nV;
    private final ed o;
    static long oU = TimeUnit.MILLISECONDS.convert(15, TimeUnit.SECONDS);
    private static final String TAG = gt.class.getName();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public final class a {
        private final PendingIntentWrapper oV;
        private final Long oW;

        /* synthetic */ a(gt gtVar, PendingIntentWrapper pendingIntentWrapper, Long l, byte b) {
            this(pendingIntentWrapper, l);
        }

        public void fI() {
            synchronized (gt.this.fD) {
                if (this.oV == null) {
                    io.dm(gt.TAG);
                    return;
                }
                gt.this.nU.a(this.oV);
                gt.this.a(this.oW);
            }
        }

        private a(PendingIntentWrapper pendingIntentWrapper, Long l) {
            this.oV = pendingIntentWrapper;
            this.oW = l;
        }
    }

    public gt(Context context) {
        this.o = ed.M(context);
        this.nU = (cw) this.o.getSystemService("sso_alarm_maanger");
        this.nV = (eh) this.o.getSystemService("dcp_system");
    }

    private gp aw() {
        return new gp(this.o, "sync_dirty_data_store");
    }

    public a fH() {
        a aVar;
        synchronized (this.fD) {
            long currentTimeMillis = this.nV.currentTimeMillis();
            gp aw = aw();
            PendingIntentWrapper pendingIntentWrapper = null;
            Long valueOf = aw.contains("sync_dirty_data_store_time") ? Long.valueOf(aw.cv("sync_dirty_data_store_time")) : null;
            boolean z = true;
            if (valueOf != null && valueOf.longValue() > currentTimeMillis) {
                z = false;
            }
            if (z) {
                ed edVar = this.o;
                Intent intent = new Intent("com.amazon.identity.action.SYNC_DIRTY_DATA");
                intent.setClass(edVar, DirtyDataSyncingService.class);
                pendingIntentWrapper = PendingIntentWrapper.b(edVar, intent);
            }
            if (pendingIntentWrapper == null) {
                io.dm(TAG);
            } else {
                io.dm(TAG);
                long j = currentTimeMillis + oU;
                this.nU.a(j, pendingIntentWrapper);
                a(Long.valueOf(j));
            }
            aVar = new a(this, pendingIntentWrapper, valueOf, (byte) 0);
        }
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Long l) {
        gp aw = aw();
        if (l != null) {
            aw.a("sync_dirty_data_store_time", l.longValue());
        } else {
            aw.cw("sync_dirty_data_store_time");
        }
    }
}
