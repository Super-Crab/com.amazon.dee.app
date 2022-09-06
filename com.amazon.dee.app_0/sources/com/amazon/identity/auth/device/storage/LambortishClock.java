package com.amazon.identity.auth.device.storage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.eh;
import com.amazon.identity.auth.device.gh;
import com.amazon.identity.auth.device.gp;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ji;
import com.amazon.identity.auth.device.jk;
import java.util.Date;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class LambortishClock {
    private static final String TAG = "com.amazon.identity.auth.device.storage.LambortishClock";
    private static LambortishClock ou;
    private final eh nV;
    private final ed o;
    private Long ov;
    private Long ow;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class ChangeTimestampsBroadcastReceiver extends BroadcastReceiver {
        public static boolean d(ed edVar) {
            return ((gh) edVar.getSystemService("dcp_data_storage_factory")).fe();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            io.i(LambortishClock.TAG, "Broadcast receiver is notified: ChangeTimestampsBroadcastReceiver");
            ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.storage.LambortishClock.ChangeTimestampsBroadcastReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    String action = intent.getAction();
                    if (!"android.intent.action.TIME_SET".equals(action)) {
                        io.c(LambortishClock.TAG, "Cannot Handle intent with action %s", action);
                        return;
                    }
                    ed M = ed.M(context);
                    if (!ChangeTimestampsBroadcastReceiver.d(M)) {
                        io.dm(LambortishClock.TAG);
                    } else {
                        LambortishClock.V(M).fq();
                    }
                }
            });
        }
    }

    LambortishClock(Context context) {
        this.o = ed.M(context);
        this.nV = (eh) this.o.getSystemService("dcp_system");
    }

    public static synchronized LambortishClock V(Context context) {
        LambortishClock lambortishClock;
        synchronized (LambortishClock.class) {
            if (ou == null || jk.gR()) {
                ou = new LambortishClock(context.getApplicationContext());
            }
            lambortishClock = ou;
        }
        return lambortishClock;
    }

    private long a(gp gpVar) {
        if (this.ov == null) {
            this.ov = Long.valueOf(gpVar.cv("greatest_timestamp_ms_seen_key"));
        }
        return this.ov.longValue();
    }

    private gp aw() {
        return new gp(this.o, "Lambortish_Clock_Store");
    }

    public synchronized boolean d(Date date) {
        if (date == null) {
            return false;
        }
        long time = date.getTime();
        gp aw = aw();
        if (time <= a(aw)) {
            return false;
        }
        String str = TAG;
        "Saving greatest timestamp seen : ".concat(String.valueOf(time));
        io.dm(str);
        return a(aw, time);
    }

    public synchronized Date fp() {
        long longValue;
        gp aw = aw();
        long a = a(aw);
        long currentTimeMillis = this.nV.currentTimeMillis();
        if (this.ow == null) {
            this.ow = Long.valueOf(aw.cv("cur_delta_ms_key"));
        }
        longValue = this.ow.longValue() + currentTimeMillis;
        if (longValue <= a) {
            longValue = 100 + a;
            long j = longValue - currentTimeMillis;
            this.ow = Long.valueOf(j);
            aw.a("cur_delta_ms_key", j);
        }
        a(aw, longValue);
        return new Date(longValue);
    }

    public synchronized void fq() {
        io.a(TAG, "Users clock moved. System time is %s and timestamp is %s", Long.toString(this.nV.currentTimeMillis()), Long.toString(fp().getTime()));
    }

    private boolean a(gp gpVar, long j) {
        this.ov = Long.valueOf(j);
        return gpVar.a("greatest_timestamp_ms_seen_key", j);
    }
}
