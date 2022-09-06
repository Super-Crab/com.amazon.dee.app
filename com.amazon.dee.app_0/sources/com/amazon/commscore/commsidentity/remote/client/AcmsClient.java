package com.amazon.commscore.commsidentity.remote.client;

import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commscore.commsidentity.remote.api.AcmsApi;
import com.amazon.commscore.commsidentity.remote.constants.Key;
import com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId;
import com.amazon.commscore.commsidentity.remote.model.IdentityV2;
import io.reactivex.rxjava3.core.Single;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/* loaded from: classes12.dex */
public class AcmsClient {
    private AcmsApi acmsApi;

    @Inject
    public AcmsClient(OkHttpClient okHttpClient, AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService) {
        this.acmsApi = (AcmsApi) new Retrofit.Builder().baseUrl(alexaCommsCoreRemoteConfigurationService.getRemoteConfiguration(Key.ACMS_HOST, "https://alexa-comms-mobile-service.amazon.com").toString()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build().create(AcmsApi.class);
    }

    public Single<AccountForDirectedId> getBasicAccountDetailsForDirectedId(String str) {
        return this.acmsApi.getAccountForDirectedId(str, null);
    }

    public Single<AccountForDirectedId> getFullAccountDetailsForDirectedId(String str) {
        return this.acmsApi.getAccountForDirectedId(str, "full");
    }

    public Single<IdentityV2> getIdentityV2ForDirectedId(String str) {
        return getIdentityV2ForDirectedId(str, true);
    }

    public Single<IdentityV2> getIdentityV2ForDirectedId(String str, Boolean bool) {
        return this.acmsApi.getIdentityV2(str, bool);
    }
}
