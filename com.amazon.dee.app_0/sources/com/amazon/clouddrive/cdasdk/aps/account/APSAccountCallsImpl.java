package com.amazon.clouddrive.cdasdk.aps.account;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.aps.APSCallUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class APSAccountCallsImpl implements APSAccountCalls {
    @NonNull
    private final APSCallUtil callUtil;
    @NonNull
    private final APSAccountCallsRetrofitBinding retrofitBinding;

    public APSAccountCallsImpl(@NonNull APSCallUtil aPSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = aPSCallUtil;
        this.retrofitBinding = (APSAccountCallsRetrofitBinding) retrofit.create(APSAccountCallsRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.account.APSAccountCalls
    @NonNull
    public Single<AccountFeaturesOutput> getAccountFeatures(@NonNull final AccountFeaturesInput accountFeaturesInput) {
        return this.callUtil.createCallWithQueryParameters("getAccountFeatures", accountFeaturesInput, new Function() { // from class: com.amazon.clouddrive.cdasdk.aps.account.-$$Lambda$APSAccountCallsImpl$oMa2yvEpB5l7AvZQUNsu8to8Km4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return APSAccountCallsImpl.this.lambda$getAccountFeatures$0$APSAccountCallsImpl(accountFeaturesInput, (Map) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$getAccountFeatures$0$APSAccountCallsImpl(AccountFeaturesInput accountFeaturesInput, Map map) throws Throwable {
        return this.retrofitBinding.getAccountFeatures(accountFeaturesInput.getResourceVersion().name(), accountFeaturesInput.getDevicePlatform().name(), map);
    }
}
