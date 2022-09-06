package com.amazon.alexa.auth.map;

import android.content.Context;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.auth.AuthorizationException;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPInit;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
/* loaded from: classes6.dex */
class MAPAccountManagerWrapper {
    private static final String TAG = "MAPAccountManagerWrapper";
    private static final long TIMEOUT_MS = 3000;
    private final Context context;
    private final CustomerAttributeStore customerAttributeStore;
    private final com.amazon.identity.auth.device.api.MAPAccountManager mapAccountManager;
    private final TokenManagement tokenManagement;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MAPAccountManagerWrapper(Context context) {
        this.context = context.getApplicationContext();
        MAPInit.getInstance(this.context).initialize();
        this.mapAccountManager = new com.amazon.identity.auth.device.api.MAPAccountManager(this.context);
        this.tokenManagement = new TokenManagement(this.context);
        this.customerAttributeStore = CustomerAttributeStore.getInstance(context);
    }

    private void getAccountProperty(@NonNull final String str, @NonNull final AccountManager.ResultCallback<String> resultCallback) {
        this.customerAttributeStore.getAttribute(this.mapAccountManager.getAccount(), str, new Callback() { // from class: com.amazon.alexa.auth.map.MAPAccountManagerWrapper.3
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                if (bundle == null) {
                    resultCallback.onError(new AuthorizationException("Unknown error"));
                    return;
                }
                int i = bundle.getInt("error_code_key");
                String string = bundle.getString("error_message_key");
                resultCallback.onError(new AuthorizationException("Error " + i + RealTimeTextConstants.COLON_SPACE + string));
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                String valueOrAttributeDefault = CustomerAttributeStore.getValueOrAttributeDefault(bundle);
                String unused = MAPAccountManagerWrapper.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("got an account property: ");
                outline107.append(str);
                outline107.append(" value: ");
                outline107.append(valueOrAttributeDefault);
                outline107.toString();
                resultCallback.onResult(valueOrAttributeDefault);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getDirectedID() {
        return this.mapAccountManager.getAccount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getMarketplace() {
        if (!isLoggedIn()) {
            return null;
        }
        final ConditionVariable conditionVariable = new ConditionVariable();
        final AtomicReference atomicReference = new AtomicReference();
        getAccountProperty(CustomerAttributeKeys.KEY_PFM, new AccountManager.ResultCallback<String>() { // from class: com.amazon.alexa.auth.map.MAPAccountManagerWrapper.1
            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onError(@NonNull Exception exc) {
                Log.e(MAPAccountManagerWrapper.TAG, "Error getting marketplace", exc);
                conditionVariable.open();
            }

            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onResult(@NonNull String str) {
                atomicReference.set(str);
                conditionVariable.open();
            }
        });
        conditionVariable.block(3000L);
        return (String) atomicReference.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getToken() {
        if (!isLoggedIn()) {
            return null;
        }
        final AtomicReference atomicReference = new AtomicReference();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String accessTokenKeyForPackage = TokenKeys.getAccessTokenKeyForPackage(this.context.getPackageName());
        final ConditionVariable conditionVariable = new ConditionVariable();
        this.tokenManagement.getToken(this.mapAccountManager.getAccount(), accessTokenKeyForPackage, null, new Callback() { // from class: com.amazon.alexa.auth.map.MAPAccountManagerWrapper.2
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode");
                String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
                Log.e(MAPAccountManagerWrapper.TAG, "Error " + i + RealTimeTextConstants.COLON_SPACE + string);
                conditionVariable.open();
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                atomicReference.set(bundle.getString("value_key"));
                conditionVariable.open();
            }
        });
        conditionVariable.block(3000L);
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        if (atomicReference.get() != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Retrieved token in ");
            outline107.append(elapsedRealtime2 - elapsedRealtime);
            outline107.append(" ms");
            outline107.toString();
        }
        return (String) atomicReference.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLoggedIn() {
        return this.mapAccountManager.getAccount() != null;
    }

    @VisibleForTesting
    MAPAccountManagerWrapper(Context context, com.amazon.identity.auth.device.api.MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, CustomerAttributeStore customerAttributeStore) {
        this.context = context.getApplicationContext();
        this.mapAccountManager = mAPAccountManager;
        this.tokenManagement = tokenManagement;
        this.customerAttributeStore = customerAttributeStore;
    }
}
