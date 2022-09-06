package com.amazon.clouddrive.cdasdk.cdrs;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
/* loaded from: classes11.dex */
public interface CDRSRetrofitBinding {
    @POST("onboarding-context")
    Single<OnboardingContextResponse> createOnboardingContext(@Body CreateOnboardingContextRequest createOnboardingContextRequest);
}
