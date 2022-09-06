package com.amazon.clouddrive.cdasdk.cdp;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDPCallsImpl implements CDPCalls {
    @NonNull
    private final CDPCallUtil callUtil;
    @NonNull
    private final CDPRetrofitBinding retrofitBinding;

    public CDPCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        this.retrofitBinding = (CDPRetrofitBinding) retrofit.create(CDPRetrofitBinding.class);
        this.callUtil = new CDPCallUtil(clientConfig);
    }
}
