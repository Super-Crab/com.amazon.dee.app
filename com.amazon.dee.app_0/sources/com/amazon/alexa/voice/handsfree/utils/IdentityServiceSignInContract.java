package com.amazon.alexa.voice.handsfree.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceProvider;
/* loaded from: classes11.dex */
public class IdentityServiceSignInContract implements AlexaAppSignInContract {
    private static final String TAG = "IdentityServiceSignInContract";
    private final IdentityServiceProvider mIdentityServiceProvider;

    @VisibleForTesting
    /* loaded from: classes11.dex */
    enum MetricType {
        GET_SIGN_IN_STATE_SUCCESS("GetSignInStateSuccess");
        
        private final String mValue;

        MetricType(@NonNull String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public IdentityServiceSignInContract() {
        this(new IdentityServiceProvider());
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract
    public boolean getSignInState(@NonNull Context context, boolean z) {
        IdentityService provideIdentityService = this.mIdentityServiceProvider.provideIdentityService();
        if (provideIdentityService == null) {
            Log.w(TAG, "isAlexaAppSignedIn: identity service not available.");
            return z;
        }
        UserIdentity user = provideIdentityService.getUser(TAG);
        return user != null && user.hasAcceptedEula() && provideIdentityService.isAuthenticated(TAG);
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract
    public void setup(@NonNull Context context, boolean z) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract
    public void teardown(@NonNull Context context) {
    }

    @VisibleForTesting
    IdentityServiceSignInContract(@NonNull IdentityServiceProvider identityServiceProvider) {
        this.mIdentityServiceProvider = identityServiceProvider;
    }
}
