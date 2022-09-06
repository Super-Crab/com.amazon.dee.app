package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import java.util.concurrent.CountDownLatch;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class hq {
    private static final String TAG = "com.amazon.identity.auth.device.hq";
    private static final Object qJ = new Object[0];
    private final Context mContext;
    private final AccountManager qK;
    private final k qL;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a<T> implements AccountManagerCallback<T> {
        private final mv mZ;
        private final AccountManagerCallback<T> qQ;

        a(AccountManagerCallback<T> accountManagerCallback, mv mvVar) {
            this.qQ = accountManagerCallback;
            this.mZ = mvVar;
        }

        @Override // android.accounts.AccountManagerCallback
        public void run(AccountManagerFuture<T> accountManagerFuture) {
            this.mZ.stop();
            AccountManagerCallback<T> accountManagerCallback = this.qQ;
            if (accountManagerCallback != null) {
                accountManagerCallback.run(accountManagerFuture);
            }
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface b {
        void gp();

        void gq();
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class c implements b {
        private final CountDownLatch dH = new CountDownLatch(1);
        private boolean qR = false;

        @Override // com.amazon.identity.auth.device.hq.b
        public void gp() {
            this.qR = true;
            this.dH.countDown();
        }

        @Override // com.amazon.identity.auth.device.hq.b
        public void gq() {
            this.qR = false;
            this.dH.countDown();
        }

        public boolean gr() {
            try {
                this.dH.await();
            } catch (InterruptedException unused) {
                io.e(hq.TAG, "Interrupted waiting for defensive remove account.");
            }
            return this.qR;
        }
    }

    private hq(Context context, AccountManager accountManager) {
        this.mContext = context;
        this.qK = accountManager;
        this.qL = new k(this.mContext);
    }

    public static hq ah(Context context) {
        return new hq(context, AccountManager.get(context));
    }

    public String c(Account account, String str) {
        id.df("unprotectedGetUserData");
        if (account != null) {
            if (this.qK == null) {
                return null;
            }
            mv aE = mq.aE("AccountManagerWrapper", "getUserData");
            try {
                return this.qK.getUserData(account, str);
            } finally {
                aE.stop();
            }
        }
        throw new IllegalStateException("Account cannot be null");
    }

    public boolean d(Account account) {
        if (account == null) {
            return false;
        }
        for (Account account2 : getAccountsByType(account.type)) {
            if (account.equals(account2)) {
                return true;
            }
        }
        return false;
    }

    public Account[] getAccountsByType(String str) {
        id.df("getAccountsByType");
        if (this.qK == null) {
            return new Account[0];
        }
        mv aE = mq.aE("AccountManagerWrapper", "getAccountsByType");
        try {
            return this.qK.getAccountsByType(str);
        } finally {
            aE.stop();
        }
    }

    public String getUserData(Account account, String str) {
        id.df("getUserData");
        if (this.qK == null || !d(account)) {
            return null;
        }
        mv aE = mq.aE("AccountManagerWrapper", "getUserData");
        try {
            return this.qK.getUserData(account, str);
        } finally {
            aE.stop();
        }
    }

    public void invalidateAuthToken(String str, String str2) {
        id.df("invalidateAuthToken");
        if (this.qK == null) {
            return;
        }
        mv aE = mq.aE("AccountManagerWrapper", "invalidateAuthToken");
        try {
            this.qK.invalidateAuthToken(str, str2);
        } finally {
            aE.stop();
        }
    }

    public String peekAuthToken(Account account, String str) {
        id.df("peekAuthToken");
        if (this.qK == null) {
            return null;
        }
        mv aE = mq.aE("AccountManagerWrapper", "peekAuthToken");
        try {
            return this.qK.peekAuthToken(account, str);
        } finally {
            aE.stop();
        }
    }

    public void setAuthToken(Account account, String str, String str2) {
        id.df("setAuthToken");
        if (this.qK == null) {
            return;
        }
        mv aE = mq.aE("AccountManagerWrapper", "setAuthToken");
        try {
            this.qK.setAuthToken(account, str, str2);
        } finally {
            aE.stop();
        }
    }

    public void setUserData(Account account, String str, String str2) {
        id.df("setUserData");
        if (this.qK == null) {
            return;
        }
        mv aE = mq.aE("AccountManagerWrapper", "setUserData");
        try {
            this.qK.setUserData(account, str, str2);
        } finally {
            aE.stop();
        }
    }

    public void a(String str, Bundle bundle, AccountManagerCallback<Bundle> accountManagerCallback) {
        id.df("addAccount");
        this.qK.addAccount(str, null, null, bundle, null, new a(accountManagerCallback, mq.aE("AccountManagerWrapper", "addAccount")), null);
    }

    public hq() {
        this.mContext = null;
        this.qK = null;
        this.qL = null;
    }

    public boolean a(Account account, Bundle bundle) {
        c cVar = new c();
        a(account, bundle, cVar);
        return cVar.gr();
    }

    public void a(final Account account, final Bundle bundle, final b bVar) {
        id.df("addAccountExplicitly");
        a(account, new AccountManagerCallback<Boolean>() { // from class: com.amazon.identity.auth.device.hq.1
            final /* synthetic */ String qM = null;

            @Override // android.accounts.AccountManagerCallback
            public void run(AccountManagerFuture<Boolean> accountManagerFuture) {
                synchronized (hq.qJ) {
                    mv aE = mq.aE("AccountManagerWrapper", "addAccountExplicitly");
                    boolean addAccountExplicitly = hq.this.qK.addAccountExplicitly(account, this.qM, bundle);
                    aE.stop();
                    if (addAccountExplicitly) {
                        bVar.gp();
                    } else {
                        bVar.gq();
                    }
                }
            }
        }, true);
    }

    public AccountManagerFuture<Bundle> a(Account account, String str, AccountManagerCallback<Bundle> accountManagerCallback) {
        id.df("getAuthToken");
        if (this.qK == null) {
            return null;
        }
        return this.qK.getAuthToken(account, str, (Bundle) null, (Activity) null, new a(accountManagerCallback, mq.aE("AccountManagerWrapper", "getAuthToken")), (Handler) null);
    }

    public AccountManagerFuture<Boolean> a(Account account, AccountManagerCallback<Boolean> accountManagerCallback) {
        return a(account, accountManagerCallback, false);
    }

    public AccountManagerFuture<Boolean> a(Account account, AccountManagerCallback<Boolean> accountManagerCallback, boolean z) {
        k kVar;
        id.df("removeAccount");
        if (this.qK == null) {
            return null;
        }
        if (z && (kVar = this.qL) != null) {
            kVar.a(account);
        }
        return this.qK.removeAccount(account, new a(accountManagerCallback, mq.aE("AccountManagerWrapper", "removeAccount")), ji.gQ());
    }

    public AccountManagerFuture<Bundle> a(Account account, String str, Bundle bundle, AccountManagerCallback<Bundle> accountManagerCallback) {
        id.df("updateCredentials");
        if (this.qK == null) {
            return null;
        }
        return this.qK.updateCredentials(account, str, bundle, null, new a(accountManagerCallback, mq.aE("AccountManagerWrapper", "updateCredentials")), null);
    }
}
