package com.amazon.clouddrive.cdasdk.cds.account;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface CDSAccountCalls {
    @NonNull
    Single<GetAccountInfoResponse> getAccountInfo(@NonNull GetAccountInfoRequest getAccountInfoRequest);

    @NonNull
    Single<GetAvailableBenefitsResponse> getAvailableBenefits(@NonNull GetAvailableBenefitsRequest getAvailableBenefitsRequest);

    @NonNull
    Single<GetEndpointResponse> getEndpoint(@NonNull GetEndpointRequest getEndpointRequest);

    @NonNull
    Single<GetQuotaResponse> getQuota(@NonNull GetQuotaRequest getQuotaRequest);

    @NonNull
    Single<GetTaggingStatusResponse> getTaggingStatus(@NonNull GetTaggingStatusRequest getTaggingStatusRequest);

    @NonNull
    Single<GetUsageResponse> getUsage(@NonNull GetUsageRequest getUsageRequest);

    @NonNull
    Single<SetPersonalPreferenceResponse> setPersonalPreference(@NonNull SetPersonalPreferenceRequest setPersonalPreferenceRequest);

    @NonNull
    Single<SetupAccountResponse> setupAccount(@NonNull SetupAccountRequest setupAccountRequest);
}
