package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.storage.NonCanonicalDataStorage;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class gi implements gh {
    private static final String TAG = "com.amazon.identity.auth.device.gi";
    private static gi nR;
    private final co ax;
    private final ds bb;
    private final AtomicReference<gg> nS = new AtomicReference<>(null);
    private final ed o;

    private gi(Context context) {
        io.i(TAG, "Creating new DataStorageFactoryImpl");
        this.o = ed.M(context.getApplicationContext());
        this.bb = (ds) this.o.getSystemService("sso_platform");
        this.ax = this.o.dW();
    }

    public static synchronized gi T(Context context) {
        gi giVar;
        synchronized (gi.class) {
            if (nR == null) {
                nR = new gi(context);
            }
            giVar = nR;
        }
        return giVar;
    }

    @Override // com.amazon.identity.auth.device.gh
    public gg dV() {
        gg U;
        if (this.nS.get() == null) {
            io.i(TAG, "Initializing new DataStorage");
            if (gu.ab(this.o)) {
                io.i(TAG, "Creating and using RuntimeSwitchableDataStorage");
                U = gu.aa(this.o);
            } else if (NonCanonicalDataStorage.Y(this.o)) {
                io.i(TAG, "Creating and using new NonCanonicalDataStorage");
                U = new NonCanonicalDataStorage(this.o);
            } else if (gc.a(this.bb, this.ax)) {
                io.i(TAG, "Creating and using new CentralLocalDataStorage");
                U = gc.S(this.o);
            } else if (gb.c(this.bb)) {
                io.i(TAG, "Creating and using new CentralAccountManagerDataStorage");
                U = gb.R(this.o);
            } else {
                io.i(TAG, "Creating and using new DistributedDataStorage");
                U = gk.U(this.o);
            }
            this.nS.compareAndSet(null, U);
            return U;
        }
        return this.nS.get();
    }

    @Override // com.amazon.identity.auth.device.gh
    public boolean fe() {
        gg dV = dV();
        if (dV instanceof gk) {
            return true;
        }
        if (!(dV instanceof gu)) {
            return false;
        }
        return ((gu) dV).fK();
    }
}
