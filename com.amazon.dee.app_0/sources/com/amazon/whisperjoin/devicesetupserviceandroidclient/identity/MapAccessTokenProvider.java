package com.amazon.whisperjoin.devicesetupserviceandroidclient.identity;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.GetAccessTokenError;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.NoCustomerAccountFoundError;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
/* loaded from: classes13.dex */
public class MapAccessTokenProvider {
    private final MAPAccountManager mMapAccountManager;
    private final String mTokenKey;
    private final TokenManagement mTokenManagement;

    public MapAccessTokenProvider(MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, String str) {
        this.mTokenKey = str;
        this.mTokenManagement = tokenManagement;
        this.mMapAccountManager = mAPAccountManager;
    }

    public Single<String> getAccessToken() {
        return Single.create(new SingleOnSubscribe<String>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.identity.MapAccessTokenProvider.1
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public void subscribe(@NonNull final SingleEmitter<String> singleEmitter) throws Exception {
                String account = MapAccessTokenProvider.this.mMapAccountManager.getAccount();
                if (account != null) {
                    MapAccessTokenProvider.this.mTokenManagement.getToken(account, MapAccessTokenProvider.this.mTokenKey, null, new Callback() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.identity.MapAccessTokenProvider.1.1
                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onError(Bundle bundle) {
                            int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode");
                            if (!singleEmitter.isDisposed()) {
                                singleEmitter.onError(new GetAccessTokenError(i));
                            }
                        }

                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onSuccess(Bundle bundle) {
                            if (!singleEmitter.isDisposed()) {
                                singleEmitter.onSuccess(bundle.getString("value_key"));
                            }
                        }
                    });
                } else if (singleEmitter.isDisposed()) {
                } else {
                    singleEmitter.onError(new NoCustomerAccountFoundError());
                }
            }
        });
    }
}
