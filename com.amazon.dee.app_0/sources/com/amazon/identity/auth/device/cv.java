package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.util.Base64;
import com.amazon.identity.auth.device.utils.AccountConstants;
/* compiled from: DCP */
@Deprecated
/* loaded from: classes12.dex */
public class cv extends fw {
    private static final String TAG = "com.amazon.identity.auth.device.cv";
    private final String bP;
    private final gg w;

    public cv(ed edVar, Account account) {
        this(edVar.dV(), hu.a(edVar, account));
    }

    @Override // com.amazon.identity.auth.device.fw
    public byte[] cq() {
        gg ggVar = this.w;
        if (ggVar == null) {
            return null;
        }
        String b = ggVar.b(this.bP, AccountConstants.KEY_TOKEN_ENCRYPT_KEY);
        if (b == null) {
            io.e(TAG, "The current account does not have an encryption key. This is probably because it is not registered.");
            return null;
        }
        return Base64.decode(b, 0);
    }

    public cv(gg ggVar, String str) {
        this.w = ggVar;
        this.bP = str;
    }
}
