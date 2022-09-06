package com.amazon.alexa.auth;

import android.content.Context;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes6.dex */
public abstract class AbstractAccountManager implements AccountManager {
    private final AccountChangedBroadcastReceiver accountChangedBroadcastReceiver;
    private final List<AccountChangedListener> accountChangedListeners;
    private boolean isTornDown;

    /* loaded from: classes6.dex */
    private class MAPAccountChangedCallback implements AccountChangedListener {
        private MAPAccountChangedCallback() {
        }

        @Override // com.amazon.alexa.auth.AccountChangedListener
        public void onAccountAdded() {
            synchronized (AbstractAccountManager.this) {
                AbstractAccountManager.this.clearCache();
                for (AccountChangedListener accountChangedListener : AbstractAccountManager.this.accountChangedListeners) {
                    accountChangedListener.onAccountAdded();
                }
            }
        }

        @Override // com.amazon.alexa.auth.AccountChangedListener
        public void onAccountRemoved() {
            synchronized (AbstractAccountManager.this) {
                AbstractAccountManager.this.clearCache();
                for (AccountChangedListener accountChangedListener : AbstractAccountManager.this.accountChangedListeners) {
                    accountChangedListener.onAccountRemoved();
                }
            }
        }
    }

    public AbstractAccountManager(Context context) {
        Objects.requireNonNull(context);
        this.accountChangedListeners = new CopyOnWriteArrayList();
        this.accountChangedBroadcastReceiver = new AccountChangedBroadcastReceiver(context, new MAPAccountChangedCallback());
    }

    @Override // com.amazon.alexa.auth.AccountManager
    public synchronized void addAccountChangedListener(AccountChangedListener accountChangedListener) {
        this.accountChangedListeners.add(accountChangedListener);
        if (this.accountChangedListeners.size() == 1) {
            this.accountChangedBroadcastReceiver.registerReceiver();
        }
    }

    @Override // com.amazon.alexa.auth.AccountManager
    public synchronized void removeAccountChangedListener(AccountChangedListener accountChangedListener) {
        if (this.accountChangedListeners.remove(accountChangedListener) && this.accountChangedListeners.size() == 0) {
            this.accountChangedBroadcastReceiver.unregisterReceiver();
        }
    }

    @Override // com.amazon.alexa.auth.AccountManager
    public synchronized void teardown() {
        if (this.isTornDown) {
            return;
        }
        this.isTornDown = true;
        if (this.accountChangedListeners.size() > 0) {
            this.accountChangedBroadcastReceiver.unregisterReceiver();
        }
    }
}
