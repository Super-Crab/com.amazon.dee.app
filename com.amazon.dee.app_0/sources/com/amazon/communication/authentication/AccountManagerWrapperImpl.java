package com.amazon.communication.authentication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
/* loaded from: classes12.dex */
public class AccountManagerWrapperImpl implements AccountManagerWrapper {
    private final AccountManager mAccountManager;

    public AccountManagerWrapperImpl(AccountManager accountManager) {
        this.mAccountManager = accountManager;
    }

    @Override // com.amazon.communication.authentication.AccountManagerWrapper
    public boolean addAccountExplicitly(Account account, String str, Bundle bundle) {
        return this.mAccountManager.addAccountExplicitly(account, str, bundle);
    }

    @Override // com.amazon.communication.authentication.AccountManagerWrapper
    public void addOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener, Handler handler, boolean z) {
        this.mAccountManager.addOnAccountsUpdatedListener(onAccountsUpdateListener, handler, z);
    }

    @Override // com.amazon.communication.authentication.AccountManagerWrapper
    public Account[] getAccountsByType(String str) {
        return this.mAccountManager.getAccountsByType(str);
    }

    @Override // com.amazon.communication.authentication.AccountManagerWrapper
    public String getUserData(Account account, String str) {
        return this.mAccountManager.getUserData(account, str);
    }

    @Override // com.amazon.communication.authentication.AccountManagerWrapper
    public AccountManagerFuture<Boolean> removeAccount(Account account, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        return this.mAccountManager.removeAccount(account, accountManagerCallback, handler);
    }

    @Override // com.amazon.communication.authentication.AccountManagerWrapper
    public void removeOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener) {
        this.mAccountManager.removeOnAccountsUpdatedListener(onAccountsUpdateListener);
    }

    @Override // com.amazon.communication.authentication.AccountManagerWrapper
    public AccountManagerFuture<Bundle> updateCredentials(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mAccountManager.updateCredentials(account, str, bundle, activity, accountManagerCallback, handler);
    }
}
