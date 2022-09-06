package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.AccountChangeEvent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class fo {
    private static final String TAG = "com.amazon.identity.auth.device.fo";
    private static List<MAPAccountManager.MAPAccountChangeObserver> mQ;
    private static volatile String mR;
    private static AtomicBoolean mS = new AtomicBoolean(false);

    private fo() {
    }

    public static void Q(Context context) {
        if (!mS.getAndSet(true)) {
            gp gpVar = new gp(context, "account_change_observer");
            if (!gpVar.cu("initialized").booleanValue()) {
                gpVar.U("last_seen_account", new MAPAccountManager(context).getAccount());
                gpVar.b("initialized", Boolean.TRUE);
            }
            mR = gpVar.cs("last_seen_account");
        }
    }

    public static void a(Context context, MAPAccountManager.MAPAccountChangeObserver mAPAccountChangeObserver) {
        Q(context);
        synchronized (MAPAccountManager.MAPAccountChangeObserver.class) {
            if (mQ == null) {
                mQ = new CopyOnWriteArrayList();
            }
        }
        io.i(TAG, "Registering account change observer");
        mQ.add(mAPAccountChangeObserver);
    }

    public static void b(Context context, MAPAccountManager.MAPAccountChangeObserver mAPAccountChangeObserver) {
        Q(context);
        if (mQ != null) {
            io.i(TAG, "Deregistering account change observer");
            mQ.remove(mAPAccountChangeObserver);
        }
    }

    public static synchronized void i(Context context, String str) {
        synchronized (fo.class) {
            Q(context);
            if (!TextUtils.equals(mR, str)) {
                final AccountChangeEvent accountChangeEvent = new AccountChangeEvent(mR, str);
                String str2 = TAG;
                io.i(str2, "Notifying observers for the account change for app: " + context.getPackageName());
                mR = str;
                new gp(context, "account_change_observer").U("last_seen_account", str);
                if (mQ != null) {
                    ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.fo.1
                        @Override // java.lang.Runnable
                        public void run() {
                            mq.b("NotifyMAPAccountChangeObservers", new String[0]);
                            for (MAPAccountManager.MAPAccountChangeObserver mAPAccountChangeObserver : fo.mQ) {
                                mAPAccountChangeObserver.onAccountChange(AccountChangeEvent.this);
                            }
                        }
                    });
                }
            }
        }
    }
}
