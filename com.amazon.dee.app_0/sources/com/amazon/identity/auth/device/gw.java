package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.WeakHashMap;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gw {
    private static final String TAG = "com.amazon.identity.auth.device.gw";
    private static gw pe;
    private final hq nH;
    private final ed o;
    private final Object[] fD = new Object[0];
    private final WeakHashMap<Account, a> pf = new WeakHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private final Account ci;
        private final Context mContext;
        private final hq nH;
        private final he nK;
        private final String pg;

        public a(Context context, hq hqVar, Account account) {
            this.mContext = context;
            this.ci = account;
            this.nK = new he(this.mContext, this.ci);
            this.nH = hqVar;
            this.pg = this.nH.getUserData(this.ci, AccountConstants.KEY_ACCOUNT_UUID);
        }

        public he fM() {
            return this.nK;
        }

        public boolean isValid() {
            String userData = this.nH.getUserData(this.ci, AccountConstants.KEY_ACCOUNT_UUID);
            if (userData == null) {
                return false;
            }
            return userData.equals(this.pg);
        }
    }

    gw(Context context) {
        this.o = ed.M(context);
        this.nH = (hq) this.o.getSystemService("dcp_account_manager");
    }

    public static synchronized gw ac(Context context) {
        gw gwVar;
        synchronized (gw.class) {
            if (pe == null) {
                pe = new gw(context.getApplicationContext());
            }
            gwVar = pe;
        }
        return gwVar;
    }

    public he b(Account account) {
        synchronized (this.fD) {
            if (!this.nH.d(account)) {
                io.dm(TAG);
                return null;
            }
            return c(account);
        }
    }

    public he c(Account account) {
        he fM;
        synchronized (this.fD) {
            a aVar = this.pf.get(account);
            if (aVar == null || !aVar.isValid()) {
                aVar = new a(this.o, this.nH, account);
                this.pf.put(account, aVar);
            }
            fM = aVar.fM();
        }
        return fM;
    }
}
