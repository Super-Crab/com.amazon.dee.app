package com.amazon.clouddrive.cdasdk.dps.devices;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.dps.DPSCallUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class DPSDevicesCallsImpl implements DPSDevicesCalls {
    @NonNull
    private final DPSCallUtil callUtil;
    @NonNull
    private final DPSDevicesRetrofitBinding retrofitBinding;

    public DPSDevicesCallsImpl(@NonNull DPSCallUtil dPSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = dPSCallUtil;
        this.retrofitBinding = (DPSDevicesRetrofitBinding) retrofit.create(DPSDevicesRetrofitBinding.class);
    }

    public /* synthetic */ Single lambda$listDevices$0$DPSDevicesCallsImpl(ListDevicesRequest listDevicesRequest, Map map) throws Throwable {
        return this.retrofitBinding.listDevices(listDevicesRequest.getDeviceTypeId(), listDevicesRequest.getDsn());
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.devices.DPSDevicesCalls
    @NonNull
    public Single<ListDevicesResponse> listDevices(@NonNull final ListDevicesRequest listDevicesRequest) {
        return this.callUtil.createCallWithQueryParameters("listDevices", listDevicesRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.dps.devices.-$$Lambda$DPSDevicesCallsImpl$krjPaI67w4emf9lK0JMwBf_pWdY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DPSDevicesCallsImpl.this.lambda$listDevices$0$DPSDevicesCallsImpl(listDevicesRequest, (Map) obj);
            }
        });
    }
}
