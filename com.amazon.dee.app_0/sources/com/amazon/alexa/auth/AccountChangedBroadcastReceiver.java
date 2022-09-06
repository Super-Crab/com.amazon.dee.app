package com.amazon.alexa.auth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AccountChangedBroadcastReceiver {
    private static final String BROADCAST_PERMISSION = "com.amazon.dcp.sso.permission.account.changed";
    private static final String INTENT_ACCOUNT_ADDED = "com.amazon.dcp.sso.action.account.added";
    private static final String INTENT_ACCOUNT_REMOVED = "com.amazon.dcp.sso.action.account.removed";
    private static final String TAG = "AccountChangedBroadcastReceiver";
    private final InternalAccountChangedBroadcastReceiver broadcastReceiver;
    private final Context context;

    /* loaded from: classes6.dex */
    private static class InternalAccountChangedBroadcastReceiver extends BroadcastReceiver {
        private final AccountChangedListener listener;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == null) {
                Log.e(AccountChangedBroadcastReceiver.TAG, "Unexpectedly received an empty action in broadcast receiver.");
                return;
            }
            String action = intent.getAction();
            char c = 65535;
            int hashCode = action.hashCode();
            if (hashCode != 2037821440) {
                if (hashCode == 2081236832 && action.equals("com.amazon.dcp.sso.action.account.removed")) {
                    c = 1;
                }
            } else if (action.equals("com.amazon.dcp.sso.action.account.added")) {
                c = 0;
            }
            if (c == 0) {
                this.listener.onAccountAdded();
            } else if (c != 1) {
                String str = AccountChangedBroadcastReceiver.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpectedly received an unrecognized action in broadcast receiver. Will ignore it: ");
                outline107.append(intent.getAction());
                Log.e(str, outline107.toString());
            } else {
                this.listener.onAccountRemoved();
            }
        }

        private InternalAccountChangedBroadcastReceiver(AccountChangedListener accountChangedListener) {
            this.listener = accountChangedListener;
        }
    }

    public AccountChangedBroadcastReceiver(Context context, AccountChangedListener accountChangedListener) {
        Objects.requireNonNull(context);
        Objects.requireNonNull(accountChangedListener);
        this.context = context.getApplicationContext();
        this.broadcastReceiver = new InternalAccountChangedBroadcastReceiver(accountChangedListener);
    }

    public void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.amazon.dcp.sso.action.account.added");
        intentFilter.addAction("com.amazon.dcp.sso.action.account.removed");
        this.context.registerReceiver(this.broadcastReceiver, intentFilter, "com.amazon.dcp.sso.permission.account.changed", null);
    }

    public void unregisterReceiver() {
        this.context.unregisterReceiver(this.broadcastReceiver);
    }
}
