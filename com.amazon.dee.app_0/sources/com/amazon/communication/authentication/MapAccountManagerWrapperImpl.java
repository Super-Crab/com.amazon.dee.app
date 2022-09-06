package com.amazon.communication.authentication;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.Set;
/* loaded from: classes12.dex */
public class MapAccountManagerWrapperImpl implements MapAccountManagerWrapper {
    private final MAPAccountManager mAccountManager;

    public MapAccountManagerWrapperImpl(Context context) {
        this.mAccountManager = new MAPAccountManager(context);
    }

    @Override // com.amazon.communication.authentication.MapAccountManagerWrapper
    public String getAccount() {
        return this.mAccountManager.getAccount();
    }

    @Override // com.amazon.communication.authentication.MapAccountManagerWrapper
    public Set<String> getAccounts() {
        return this.mAccountManager.getAccounts();
    }

    @Override // com.amazon.communication.authentication.MapAccountManagerWrapper
    public String getPrimaryAccount() {
        return this.mAccountManager.getPrimaryAccount();
    }
}
