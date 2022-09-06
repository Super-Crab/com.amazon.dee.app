package com.amazon.leaderselection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.ConditionVariable;
import android.os.IBinder;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.leaderselection.l;
/* loaded from: classes12.dex */
class a implements ServiceConnection {
    private static final String f = a.class.getSimpleName();
    private final Context a;
    private final ComponentName b;
    private l c;
    private ConditionVariable d = new ConditionVariable();
    private volatile boolean e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, ComponentName componentName) {
        this.a = context;
        this.b = componentName;
    }

    private void e() {
        Intent intent = new Intent();
        intent.setComponent(this.b);
        if (!this.a.bindService(intent, this, 1)) {
            Log.e(f, "Error binding to leader selection service");
            this.a.unbindService(this);
            this.e = false;
            this.d.open();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.d.block(1000L);
        if (this.e) {
            this.e = false;
            this.c = null;
            this.a.unbindService(this);
            this.d.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public l b() {
        e();
        this.d.block(1000L);
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComponentName c() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.e;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (!this.b.equals(componentName)) {
            Log.e(f, "Connected to an unexpected service: " + componentName);
            return;
        }
        String str = this.a.getPackageName() + ": connected to: " + componentName;
        this.c = l.a.a(iBinder);
        this.e = true;
        this.d.open();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        String str = "Unexpectedly disconnected from: " + componentName;
        this.e = false;
        this.c = null;
        this.d.open();
    }
}
