package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import com.amazon.identity.auth.device.utils.AccountConstants;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class k {
    private static final String TAG = "com.amazon.identity.auth.device.k";
    private static final Object[] aM = new Object[0];
    private final gp aN;
    private final Context mContext;

    public k(Context context) {
        this.mContext = ed.M(context);
        this.aN = gp.l(this.mContext, "account_removed_flag_store");
    }

    public boolean a(Account account) {
        synchronized (aM) {
            if (AccountConstants.AMAZON_ACCOUNT_TYPE.equals(account.type)) {
                gp gpVar = this.aN;
                if (gpVar.a("Account_To_Remove_" + account.name, Boolean.TRUE)) {
                    return true;
                }
                io.e(TAG, "Could not write account removed flag to disk");
                return false;
            }
            throw new IllegalStateException("Not valid for accounts of type: " + account.type);
        }
    }
}
