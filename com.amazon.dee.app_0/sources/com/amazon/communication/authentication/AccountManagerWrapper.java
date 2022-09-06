package com.amazon.communication.authentication;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
/* loaded from: classes12.dex */
public interface AccountManagerWrapper {
    boolean addAccountExplicitly(Account account, String str, Bundle bundle);

    void addOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener, Handler handler, boolean z);

    Account[] getAccountsByType(String str);

    String getUserData(Account account, String str);

    AccountManagerFuture<Boolean> removeAccount(Account account, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler);

    void removeOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener);

    AccountManagerFuture<Bundle> updateCredentials(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);
}
