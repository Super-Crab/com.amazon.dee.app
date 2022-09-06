package com.amazon.whisperjoin.deviceprovisioningservice.identity;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationRequest;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.service.CBLGenerationConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
/* loaded from: classes13.dex */
public interface MapAuthenticationProvider {

    /* loaded from: classes13.dex */
    public static class DefaultMapAuthAccountProvider implements MapAuthenticationProvider {
        private static final String TAG = MapAuthenticationProvider.class.getSimpleName();
        private MAPAccountManager mAccountManager;
        private AuthenticationMethodFactory mAuthenticationFactory;

        public DefaultMapAuthAccountProvider(Context context) {
            this.mAccountManager = new MAPAccountManager(context);
            this.mAuthenticationFactory = new AuthenticationMethodFactory(context, getAccount());
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider
        public Single<CBLRegistrationRequest> generatePreAuthLinkCode() {
            return Single.create(new SingleOnSubscribe<CBLRegistrationRequest>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider.DefaultMapAuthAccountProvider.1
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public void subscribe(@NonNull final SingleEmitter<CBLRegistrationRequest> singleEmitter) throws Exception {
                    String str = DefaultMapAuthAccountProvider.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Getting Auth Code only customerId ");
                    outline107.append(Thread.currentThread().toString());
                    WJLog.d(str, outline107.toString());
                    DefaultMapAuthAccountProvider.this.mAccountManager.generatePreAuthorizedLinkCode(GeneratedOutlineSupport1.outline11("com.amazon.dcp.sso.property.account.acctId", DefaultMapAuthAccountProvider.this.mAccountManager.getAccount()), new Callback() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider.DefaultMapAuthAccountProvider.1.1
                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onError(Bundle bundle) {
                            singleEmitter.onError(new MAPCallbackErrorException(bundle));
                        }

                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onSuccess(Bundle bundle) {
                            singleEmitter.onSuccess(new CBLRegistrationRequest(bundle.getString(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE), bundle.getLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE)));
                        }
                    });
                }
            }).subscribeOn(Schedulers.io());
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider
        public Single<CBLRegistrationRequest> generatePreAuthLinkCodeWithPid(final CBLGenerationConfiguration cBLGenerationConfiguration) {
            return Single.create(new SingleOnSubscribe<CBLRegistrationRequest>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider.DefaultMapAuthAccountProvider.2
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public void subscribe(@NonNull final SingleEmitter<CBLRegistrationRequest> singleEmitter) throws Exception {
                    String str = DefaultMapAuthAccountProvider.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Getting Auth Code with profileId ");
                    outline107.append(Thread.currentThread().toString());
                    WJLog.d(str, outline107.toString());
                    DefaultMapAuthAccountProvider.this.mAccountManager.generatePreAuthorizedLinkCode(GeneratedOutlineSupport1.outline12("com.amazon.dcp.sso.property.account.acctId", cBLGenerationConfiguration.getCustomerID(), MAPAccountManager.KEY_ACTOR_ID_FOR_PREAUTHORIZED_LINK_CODE, cBLGenerationConfiguration.getProfileID()), new Callback() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider.DefaultMapAuthAccountProvider.2.1
                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onError(Bundle bundle) {
                            singleEmitter.onError(new MAPCallbackErrorException(bundle));
                        }

                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onSuccess(Bundle bundle) {
                            singleEmitter.onSuccess(new CBLRegistrationRequest(bundle.getString(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE), bundle.getLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE)));
                        }
                    });
                }
            }).subscribeOn(Schedulers.io());
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider
        public String getAccount() {
            return this.mAccountManager.getAccount();
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider
        public AuthenticationMethod getOAuthToken() {
            return this.mAuthenticationFactory.newAuthenticationMethod(AuthenticationType.OAuth);
        }
    }

    Single<CBLRegistrationRequest> generatePreAuthLinkCode();

    Single<CBLRegistrationRequest> generatePreAuthLinkCodeWithPid(CBLGenerationConfiguration cBLGenerationConfiguration);

    String getAccount();

    AuthenticationMethod getOAuthToken();
}
