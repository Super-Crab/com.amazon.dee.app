package com.amazon.clouddrive.cdasdk.dps.event;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.dps.DPSCallUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class DPSEventCallsImpl implements DPSEventCalls {
    @NonNull
    private final DPSCallUtil callUtil;
    @NonNull
    private final DPSEventRetrofitBinding retrofitBinding;

    public DPSEventCallsImpl(@NonNull DPSCallUtil dPSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = dPSCallUtil;
        this.retrofitBinding = (DPSEventRetrofitBinding) retrofit.create(DPSEventRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.event.DPSEventCalls
    @NonNull
    public Single<RecordEventResponse> recordEvent(@NonNull RecordEventRequest recordEventRequest) {
        DPSCallUtil dPSCallUtil = this.callUtil;
        final DPSEventRetrofitBinding dPSEventRetrofitBinding = this.retrofitBinding;
        dPSEventRetrofitBinding.getClass();
        return dPSCallUtil.createCall("recordEvent", (String) recordEventRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.dps.event.-$$Lambda$T_EILzbz35pEDh6lcr0wPtTkVaU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DPSEventRetrofitBinding.this.recordEvent((RecordEventRequest) obj);
            }
        });
    }
}
