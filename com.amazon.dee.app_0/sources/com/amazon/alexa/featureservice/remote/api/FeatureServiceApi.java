package com.amazon.alexa.featureservice.remote.api;

import com.amazon.alexa.featureservice.remote.constants.FeatureServiceEndpoint;
import com.amazon.alexa.featureservice.remote.model.FeatureServiceResponse;
import com.amazon.alexa.featureservice.remote.model.RequestBody;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
/* loaded from: classes7.dex */
public interface FeatureServiceApi {
    @POST(FeatureServiceEndpoint.FEATURE_SERVICE_PATH)
    Single<FeatureServiceResponse> getFeaturesForUser(@Body RequestBody requestBody);
}
