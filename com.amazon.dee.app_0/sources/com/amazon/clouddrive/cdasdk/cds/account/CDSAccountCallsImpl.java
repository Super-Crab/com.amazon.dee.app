package com.amazon.clouddrive.cdasdk.cds.account;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.CDSCallUtil;
import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDSAccountCallsImpl implements CDSAccountCalls {
    @NonNull
    private final CDSAccountRetrofitBinding accountRetrofitBinding;
    @NonNull
    private final CDSCallUtil<CloudDriveRequest> callUtil;

    public CDSAccountCallsImpl(@NonNull CDSCallUtil<CloudDriveRequest> cDSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = cDSCallUtil;
        this.accountRetrofitBinding = (CDSAccountRetrofitBinding) retrofit.create(CDSAccountRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls
    @NonNull
    public Single<GetAccountInfoResponse> getAccountInfo(@NonNull GetAccountInfoRequest getAccountInfoRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSAccountRetrofitBinding cDSAccountRetrofitBinding = this.accountRetrofitBinding;
        cDSAccountRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("getAccountInfo", getAccountInfoRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.account.-$$Lambda$_ZQ4apKN5mRfhq-ltOINiU8-84M
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSAccountRetrofitBinding.this.getAccountInfo((Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls
    @NonNull
    public Single<GetAvailableBenefitsResponse> getAvailableBenefits(@NonNull GetAvailableBenefitsRequest getAvailableBenefitsRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSAccountRetrofitBinding cDSAccountRetrofitBinding = this.accountRetrofitBinding;
        cDSAccountRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("getAvailableBenefits", getAvailableBenefitsRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.account.-$$Lambda$gyMjwuDP_jLPNJ9fcbohybnb1yI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSAccountRetrofitBinding.this.getAvailableBenefits((Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls
    @NonNull
    public Single<GetEndpointResponse> getEndpoint(@NonNull GetEndpointRequest getEndpointRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSAccountRetrofitBinding cDSAccountRetrofitBinding = this.accountRetrofitBinding;
        cDSAccountRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("getEndpoint", getEndpointRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.account.-$$Lambda$JvACwgXxkOeY_VbWdOy4raHFJXQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSAccountRetrofitBinding.this.getEndpoint((Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls
    @NonNull
    public Single<GetQuotaResponse> getQuota(@NonNull GetQuotaRequest getQuotaRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSAccountRetrofitBinding cDSAccountRetrofitBinding = this.accountRetrofitBinding;
        cDSAccountRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("getQuota", getQuotaRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.account.-$$Lambda$Sz90KKxmFd7fKVZG3blkukewnvQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSAccountRetrofitBinding.this.getQuota((Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls
    @NonNull
    public Single<GetTaggingStatusResponse> getTaggingStatus(@NonNull GetTaggingStatusRequest getTaggingStatusRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSAccountRetrofitBinding cDSAccountRetrofitBinding = this.accountRetrofitBinding;
        cDSAccountRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("getTaggingStatus", getTaggingStatusRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.account.-$$Lambda$V4pEaujGk0JuNMDo4qhMa59fg54
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSAccountRetrofitBinding.this.getTaggingStatus((Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls
    @NonNull
    public Single<GetUsageResponse> getUsage(@NonNull GetUsageRequest getUsageRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSAccountRetrofitBinding cDSAccountRetrofitBinding = this.accountRetrofitBinding;
        cDSAccountRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("getUsage", getUsageRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.account.-$$Lambda$D3vRKKkIRXYiFyINvl1E0SaAfdg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSAccountRetrofitBinding.this.getUsage((Map) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$setPersonalPreference$0$CDSAccountCallsImpl(SetPersonalPreferenceRequest setPersonalPreferenceRequest) throws Throwable {
        return this.accountRetrofitBinding.setPersonalPreference(setPersonalPreferenceRequest.getPreferenceKey().name(), setPersonalPreferenceRequest);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls
    @NonNull
    public Single<SetPersonalPreferenceResponse> setPersonalPreference(@NonNull SetPersonalPreferenceRequest setPersonalPreferenceRequest) {
        return this.callUtil.createCall("setPersonalPreference", (String) setPersonalPreferenceRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.account.-$$Lambda$CDSAccountCallsImpl$qyrJaLk0FTd7VJyWvRXAtin2mvw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSAccountCallsImpl.this.lambda$setPersonalPreference$0$CDSAccountCallsImpl((SetPersonalPreferenceRequest) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls
    @NonNull
    public Single<SetupAccountResponse> setupAccount(@NonNull SetupAccountRequest setupAccountRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSAccountRetrofitBinding cDSAccountRetrofitBinding = this.accountRetrofitBinding;
        cDSAccountRetrofitBinding.getClass();
        return cDSCallUtil.createCall("setupAccount", (String) setupAccountRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.account.-$$Lambda$mcLPr5yDP0a5DSpfwnXm0edEdy8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSAccountRetrofitBinding.this.setupAccount((SetupAccountRequest) obj);
            }
        });
    }
}
