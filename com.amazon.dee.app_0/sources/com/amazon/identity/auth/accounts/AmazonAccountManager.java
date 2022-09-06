package com.amazon.identity.auth.accounts;

import android.content.Context;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class AmazonAccountManager {
    private static final String TAG = "com.amazon.identity.auth.accounts.AmazonAccountManager";
    private final gg w;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum AccountRegistrationStatus {
        Registered("Registered"),
        Deregistering("Deregistering"),
        NotFound("NotFound");
        
        private final String mValue;

        AccountRegistrationStatus(String str) {
            this.mValue = str;
        }

        public static AccountRegistrationStatus fromValue(String str) {
            AccountRegistrationStatus[] values;
            for (AccountRegistrationStatus accountRegistrationStatus : values()) {
                if (accountRegistrationStatus.getValue().equals(str)) {
                    return accountRegistrationStatus;
                }
            }
            return null;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public AmazonAccountManager(Context context) {
        this(ed.M(context).dV());
    }

    public boolean A(String str) {
        return b(str, AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT) != null;
    }

    public boolean B(String str) {
        return b(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT) != null;
    }

    public boolean C(String str) {
        return D(str) && !A(str);
    }

    public boolean D(String str) {
        for (String str2 : getAccounts()) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean E(String str) {
        Iterator<String> it2 = getAccounts().iterator();
        while (true) {
            boolean z = false;
            if (it2.hasNext()) {
                if (it2.next().equals(str)) {
                    if (AccountRegistrationStatus.fromValue(b(str, AccountConstants.KEY_ACCOUNT_STATUS)) == AccountRegistrationStatus.Deregistering) {
                        mq.b("AccountRemovedBecauseDeregisteringState", new String[0]);
                        io.i(TAG, "Removing account from database since database is stuck in bad state. Account status is Deregistering and registerAccount API is called");
                        this.w.G(str);
                        z = true;
                    }
                    if (!z) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
    }

    public AccountRegistrationStatus F(String str) {
        AccountRegistrationStatus fromValue = AccountRegistrationStatus.fromValue(b(str, AccountConstants.KEY_ACCOUNT_STATUS));
        if (fromValue != null) {
            return fromValue;
        }
        if (D(str)) {
            return AccountRegistrationStatus.Registered;
        }
        return AccountRegistrationStatus.NotFound;
    }

    public void G(String str) {
        this.w.G(str);
    }

    public void a(String str, String str2, String str3) {
        this.w.a(str, str2, str3);
    }

    public String b(String str, String str2) {
        return this.w.b(str, str2);
    }

    public Set<String> getAccounts() {
        String str = TAG;
        new StringBuilder("MAP Accounts number: ").append(this.w.getAccounts().size());
        io.dm(str);
        return this.w.getAccounts();
    }

    public boolean n() {
        return o() != null;
    }

    public String o() {
        Set<String> p = p();
        if (p.size() <= 0) {
            return null;
        }
        for (String str : p) {
            if (!A(str)) {
                return str;
            }
        }
        return null;
    }

    public Set<String> p() {
        Set<String> accounts = getAccounts();
        HashSet hashSet = new HashSet();
        for (String str : accounts) {
            if (!x(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    public Set<String> q() {
        HashSet hashSet = new HashSet();
        for (String str : getAccounts()) {
            if (B(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    public Set<String> r() {
        Set<String> accounts = getAccounts();
        HashSet hashSet = new HashSet();
        for (String str : accounts) {
            if (A(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    public boolean x(String str) {
        AccountRegistrationStatus F = F(str);
        return F == AccountRegistrationStatus.NotFound || F == AccountRegistrationStatus.Deregistering;
    }

    public void y(String str) {
        a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, "true");
    }

    public void z(String str) {
        a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, null);
    }

    public AmazonAccountManager(gg ggVar) {
        this.w = ggVar;
    }

    public void a(String str, AccountRegistrationStatus accountRegistrationStatus) {
        a(str, AccountConstants.KEY_ACCOUNT_STATUS, accountRegistrationStatus.getValue());
    }
}
