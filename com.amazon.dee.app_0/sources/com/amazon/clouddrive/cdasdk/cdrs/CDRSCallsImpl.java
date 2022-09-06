package com.amazon.clouddrive.cdasdk.cdrs;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDRSCallsImpl implements CDRSCalls {
    @NonNull
    private final CDRSCallUtil callUtil;
    @NonNull
    private final CDRSRetrofitBinding retrofitBinding;

    public CDRSCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        this.callUtil = new CDRSCallUtil(clientConfig);
        this.retrofitBinding = (CDRSRetrofitBinding) retrofit.create(CDRSRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdrs.CDRSCalls
    @NonNull
    public Single<OnboardingContextResponse> createOnboardingContext(@NonNull CreateOnboardingContextRequest createOnboardingContextRequest) {
        CDRSCallUtil cDRSCallUtil = this.callUtil;
        final CDRSRetrofitBinding cDRSRetrofitBinding = this.retrofitBinding;
        cDRSRetrofitBinding.getClass();
        return cDRSCallUtil.createCall("createOnboardingContext", (String) createOnboardingContextRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cdrs.-$$Lambda$OevSt0_e9LDxMx5JyEJGULKs7Po
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDRSRetrofitBinding.this.createOnboardingContext((CreateOnboardingContextRequest) obj);
            }
        });
    }
}
