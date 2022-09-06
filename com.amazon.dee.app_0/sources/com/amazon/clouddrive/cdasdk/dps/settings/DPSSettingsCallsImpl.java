package com.amazon.clouddrive.cdasdk.dps.settings;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.dps.DPSCallUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class DPSSettingsCallsImpl implements DPSSettingsCalls {
    @NonNull
    private final DPSCallUtil callUtil;
    @NonNull
    private final DPSSettingsRetrofitBinding retrofitBinding;

    public DPSSettingsCallsImpl(@NonNull DPSCallUtil dPSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = dPSCallUtil;
        this.retrofitBinding = (DPSSettingsRetrofitBinding) retrofit.create(DPSSettingsRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.settings.DPSSettingsCalls
    @NonNull
    public Single<GetSettingsResponse> getSettings(@NonNull final GetSettingsRequest getSettingsRequest) {
        return this.callUtil.createCallWithQueryParameters("getSettings", getSettingsRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.dps.settings.-$$Lambda$DPSSettingsCallsImpl$ixSTLRfbzr3JhRjD5QCcm_7Pp_c
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DPSSettingsCallsImpl.this.lambda$getSettings$0$DPSSettingsCallsImpl(getSettingsRequest, (Map) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$getSettings$0$DPSSettingsCallsImpl(GetSettingsRequest getSettingsRequest, Map map) throws Throwable {
        return this.retrofitBinding.getSettings(getSettingsRequest.getPrincipalType(), getSettingsRequest.getPrincipalId());
    }
}
