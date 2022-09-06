package com.amazon.clouddrive.cdasdk.onelens;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class OneLensCallsImpl implements OneLensCalls {
    @NonNull
    private final OneLensCallUtil callUtil;
    @NonNull
    private final OneLensRetrofitBinding retrofitBinding;

    public OneLensCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        this.callUtil = new OneLensCallUtil(clientConfig);
        this.retrofitBinding = (OneLensRetrofitBinding) retrofit.create(OneLensRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.onelens.OneLensCalls
    @NonNull
    public Single<GetContactInfoResponse> getContactInfo(@NonNull GetContactInfoRequest getContactInfoRequest) {
        return this.callUtil.createCall("getContactInfo", (String) getContactInfoRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.onelens.-$$Lambda$OneLensCallsImpl$rlTcqYFVWkwMhuhELjks29KJusU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return OneLensCallsImpl.this.lambda$getContactInfo$0$OneLensCallsImpl((GetContactInfoRequest) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$getContactInfo$0$OneLensCallsImpl(GetContactInfoRequest getContactInfoRequest) throws Throwable {
        return this.retrofitBinding.getContactInfo(getContactInfoRequest.getCustomerId());
    }
}
