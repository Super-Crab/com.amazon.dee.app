package com.amazon.alexa;

import android.os.ConditionVariable;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.auth.AccessToken;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.auth.AuthorizationException;
import com.amazon.alexa.auth.TokenProvider;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.metrics.core.DirectedIDProvider;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AuthorizationAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class tol implements TokenProvider, DirectedIDProvider {
    public static final long BIo = TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);
    public static final String zZm = "tol";
    public volatile boolean Qle;
    public volatile boolean jiA;
    public final AlexaClientEventBus zQM;
    public final AccountManager zyO;

    @Inject
    public tol(AlexaClientEventBus alexaClientEventBus, AccountManager accountManager) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("using accountManager: ");
        zZm2.append(accountManager.getClass());
        Log.i(str, zZm2.toString());
        this.zQM = alexaClientEventBus;
        this.zyO = accountManager;
        alexaClientEventBus.zZm(this);
    }

    @VisibleForTesting
    public ConditionVariable BIo() {
        return new ConditionVariable(false);
    }

    public void Qle() {
        this.zyO.teardown();
    }

    @Override // com.amazon.alexa.client.metrics.core.DirectedIDProvider
    public String getDirectedID() {
        AtomicReference atomicReference = new AtomicReference();
        AtomicReference atomicReference2 = new AtomicReference();
        ConditionVariable BIo2 = BIo();
        this.zyO.getDirectedID(new MEo(this, atomicReference, BIo2, atomicReference2));
        if (BIo2.block(BIo)) {
            return atomicReference2.get() == null ? (String) atomicReference.get() : "unknown";
        }
        Log.e(zZm, "getDirectedID: Request timed out. Returning null");
        return "unknown";
    }

    @Override // com.amazon.alexa.auth.TokenProvider
    public AccessToken getToken() throws AuthorizationException {
        AtomicReference atomicReference = new AtomicReference();
        AtomicReference atomicReference2 = new AtomicReference();
        boolean z = false;
        ConditionVariable conditionVariable = new ConditionVariable(false);
        zZm(new C0186YjE(this, atomicReference, conditionVariable, atomicReference2));
        if (conditionVariable.block(BIo)) {
            if (atomicReference.get() != null && ((AccessToken) atomicReference.get()).getValue() != null) {
                z = true;
            }
            zZm(z);
        } else {
            Log.e(zZm, "getToken request timed out");
        }
        if (atomicReference2.get() == null) {
            return (AccessToken) atomicReference.get();
        }
        throw new AuthorizationException((Throwable) atomicReference2.get());
    }

    public final void jiA() {
        try {
            getToken();
        } catch (AuthorizationException e) {
            Log.e(zZm, String.format("Failed to refresh account status with error: %s", e));
        }
    }

    @Subscribe
    public void on(diQ diq) {
        jiA();
    }

    public void zQM() {
        this.zyO.clearCache();
        zZm();
        zZm(zyO());
    }

    public boolean zyO() {
        synchronized (this) {
            if (this.jiA) {
                if (this.Qle) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Returning cached value for logged in status: ");
                    sb.append(this.Qle);
                    sb.toString();
                    return this.Qle;
                }
                this.zyO.clearCache();
            }
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            AtomicReference atomicReference = new AtomicReference();
            ConditionVariable BIo2 = BIo();
            this.zyO.isLoggedIn(new sES(this, atomicBoolean, BIo2, atomicReference));
            if (!BIo2.block(BIo)) {
                Log.e(zZm, "isLoggedIn request timed out. Returning false");
                this.zQM.zyO(new FrZ());
                return false;
            } else if (atomicReference.get() != null) {
                return false;
            } else {
                zZm(atomicBoolean.get());
                StringBuilder zZm2 = C0179Pya.zZm("Returning the fetched value for logged in status: ");
                zZm2.append(atomicBoolean.get());
                zZm2.toString();
                return atomicBoolean.get();
            }
        }
    }

    @Subscribe
    public void on(bFb bfb) {
        this.zyO.clearCache();
        zZm(false);
        jiA();
    }

    public void zZm(AccountManager.ResultCallback<AccessToken> resultCallback) {
        this.zyO.getToken(resultCallback);
    }

    @VisibleForTesting
    public synchronized void zZm(boolean z) {
        if (!this.jiA || this.Qle != z) {
            String str = "Caching login status: " + z;
            this.jiA = true;
            this.Qle = z;
        }
    }

    @Subscribe
    public void on(Odp odp) {
        try {
            zQM();
        } catch (Exception unused) {
            zZm();
        }
    }

    public final synchronized void zZm() {
        this.jiA = false;
        this.Qle = false;
    }
}
