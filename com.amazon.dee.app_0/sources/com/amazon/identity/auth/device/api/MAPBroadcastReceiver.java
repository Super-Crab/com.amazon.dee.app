package com.amazon.identity.auth.device.api;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.hu;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ji;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.HashSet;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class MAPBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = MAPBroadcastReceiver.class.getName();
    private static final Set<String> gq;
    private static final Set<String> gr;

    static {
        HashSet hashSet = new HashSet();
        gq = hashSet;
        hashSet.add("com.amazon.dcp.sso.action.account.added");
        gq.add("com.amazon.dcp.sso.action.secondary.account.added");
        HashSet hashSet2 = new HashSet();
        gr = hashSet2;
        hashSet2.add("com.amazon.dcp.sso.action.account.removed");
        gr.add("com.amazon.dcp.sso.action.secondary.account.removed");
    }

    @FireOsSdk
    public static Intent constructBackwardsCompatibleIntent(Context context, Intent intent) {
        if (intent == null) {
            return null;
        }
        Intent intent2 = new Intent(intent);
        String action = intent2.getAction();
        if (!gq.contains(action) && !gr.contains(action)) {
            if ("com.amazon.dcp.sso.action.AmazonAccountPropertyService.property.changed".equals(action)) {
                if (intent2.getStringExtra(CustomerAttributeStore.KEY_DIRECTED_ID) != null) {
                    return intent2;
                }
                String a = hu.a(context, (Account) intent2.getParcelableExtra("account.property.changed"));
                if (a == null) {
                    a = new MAPAccountManager(context).getAccount();
                }
                intent2.putExtra(CustomerAttributeStore.KEY_DIRECTED_ID, a);
                return intent2;
            } else if (!CustomerAttributeStore.COR_PFM_CHANGED_INTENT_ACTION.equals(action) || intent2.getStringExtra(CustomerAttributeStore.KEY_DIRECTED_ID) != null) {
                return intent2;
            } else {
                intent2.putExtra(CustomerAttributeStore.KEY_DIRECTED_ID, new AmazonAccountManager(context).o());
                return intent2;
            }
        } else if (intent2.getStringExtra(MAPAccountManager.KEY_EXTRA_DIRECTED_ID) != null) {
            return intent2;
        } else {
            if (gr.contains(intent2.getAction())) {
                return null;
            }
            intent2.putExtra(MAPAccountManager.KEY_EXTRA_DIRECTED_ID, hu.j(context, intent2.getStringExtra(AccountConstants.EXTRA_AMAZON_ACCOUNT_CHANGED_TYPE), intent2.getStringExtra(AccountConstants.EXTRA_AMAZON_ACCOUNT_CHANGED_NAME)));
            return intent2;
        }
    }

    @Deprecated
    protected abstract void backwardsCompatibleOnReceive(Context context, Intent intent);

    @Override // android.content.BroadcastReceiver
    @FireOsSdk
    public final void onReceive(final Context context, final Intent intent) {
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPBroadcastReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                Intent constructBackwardsCompatibleIntent = MAPBroadcastReceiver.constructBackwardsCompatibleIntent(context, intent);
                if (constructBackwardsCompatibleIntent == null) {
                    String unused = MAPBroadcastReceiver.TAG;
                    io.a("Ignoring broadcast with action %s", intent.getAction());
                    return;
                }
                MAPBroadcastReceiver.this.backwardsCompatibleOnReceive(context, constructBackwardsCompatibleIntent);
            }
        });
    }
}
