package com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.util.IntentActionConstants;
import java.lang.ref.WeakReference;
import javax.annotation.Nonnull;
/* loaded from: classes13.dex */
public class WhiteListPolicyUpdateListener {
    private static final String TAG = "WhiteListPolicyUpdateListener";
    private final Context mContext;
    private PolicyUpdateReceiver mPolicyUpdateReceiver;
    private final ProvisionerClientData mProvisionerClientData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class PolicyUpdateReceiver extends BroadcastReceiver {
        private static final String TAG = PolicyUpdateReceiver.class.getSimpleName();
        private final ProvisionerClientData mProvisionerClientData;
        private final WeakReference<WhiteListPolicyUpdateCallback> mWhiteListPolicyUpdateCallback;

        public PolicyUpdateReceiver(@Nonnull ProvisionerClientData provisionerClientData, @Nonnull WhiteListPolicyUpdateCallback whiteListPolicyUpdateCallback) {
            this.mProvisionerClientData = provisionerClientData;
            this.mWhiteListPolicyUpdateCallback = new WeakReference<>(whiteListPolicyUpdateCallback);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.mWhiteListPolicyUpdateCallback.get() == null) {
                return;
            }
            if (!intent.getAction().equals(IntentActionConstants.WHITELIST_POLICY_UPDATE_ACTION)) {
                String str = TAG;
                WJLog.e(str, "Received incorrect intent: " + intent);
                return;
            }
            Bundle extras = intent.getExtras();
            WhiteListPolicy readFromBundle = WhiteListPolicy.readFromBundle(extras);
            ProvisionerClientData readFromBundle2 = ProvisionerClientData.readFromBundle(extras);
            if (readFromBundle == null) {
                WJLog.e(TAG, "Received null WhiteListPolicy. Ignoring update.");
            } else if (readFromBundle2 == null) {
                WJLog.e(TAG, "Received null provisionerClientData. Ignoring Update");
            } else if (!this.mProvisionerClientData.equals(readFromBundle2)) {
                WJLog.e(TAG, "WhiteListPolicy's client data doesn't match current client data");
            } else {
                this.mWhiteListPolicyUpdateCallback.get().onUpdate(readFromBundle, readFromBundle2);
            }
        }
    }

    /* loaded from: classes13.dex */
    public interface WhiteListPolicyUpdateCallback {
        void onUpdate(WhiteListPolicy whiteListPolicy, ProvisionerClientData provisionerClientData);
    }

    public WhiteListPolicyUpdateListener(@Nonnull Context context, @Nonnull ProvisionerClientData provisionerClientData) {
        this.mContext = context;
        this.mProvisionerClientData = provisionerClientData;
    }

    public void registerForWhiteListPolicyUpdates(@Nonnull WhiteListPolicyUpdateCallback whiteListPolicyUpdateCallback) {
        WJLog.d(TAG, "registerForWhiteListPolicyUpdates");
        if (this.mPolicyUpdateReceiver != null) {
            WJLog.d(TAG, "Other callback already registered, replacing with new callback");
            unregisterForWhiteListPolicyUpdates();
        }
        this.mPolicyUpdateReceiver = new PolicyUpdateReceiver(this.mProvisionerClientData, whiteListPolicyUpdateCallback);
        this.mContext.registerReceiver(this.mPolicyUpdateReceiver, new IntentFilter(IntentActionConstants.WHITELIST_POLICY_UPDATE_ACTION), IntentActionConstants.WHITELIST_POLICY_UPDATE_PERMISSION, null);
    }

    public void unregisterForWhiteListPolicyUpdates() {
        WJLog.d(TAG, "unregisterForWhiteListPolicyUpdates");
        PolicyUpdateReceiver policyUpdateReceiver = this.mPolicyUpdateReceiver;
        if (policyUpdateReceiver == null) {
            return;
        }
        this.mContext.unregisterReceiver(policyUpdateReceiver);
        this.mPolicyUpdateReceiver = null;
    }
}
