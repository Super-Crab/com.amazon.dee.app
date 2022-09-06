package com.amazon.alexa.featureservice.remote.client;

import com.amazon.alexa.featureservice.dependencies.annotation.FeatureServiceOkHttpClient;
import com.amazon.alexa.featureservice.remote.api.FeatureServiceApi;
import com.amazon.alexa.featureservice.remote.model.FeatureServiceResponse;
import com.amazon.alexa.featureservice.remote.model.RequestBody;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/* loaded from: classes7.dex */
public class FeatureServiceClient {
    private FeatureServiceApi featureServiceApi;

    @Inject
    public FeatureServiceClient(@FeatureServiceOkHttpClient OkHttpClient okHttpClient, EnvironmentService environmentService) {
        this.featureServiceApi = (FeatureServiceApi) new Retrofit.Builder().baseUrl(environmentService.getCoralEndpoint()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build().create(FeatureServiceApi.class);
    }

    public Single<FeatureServiceResponse> getFeatures(List<String> list) {
        RequestBody requestBody = new RequestBody();
        requestBody.setFeatures(list);
        return this.featureServiceApi.getFeaturesForUser(requestBody);
    }
}
