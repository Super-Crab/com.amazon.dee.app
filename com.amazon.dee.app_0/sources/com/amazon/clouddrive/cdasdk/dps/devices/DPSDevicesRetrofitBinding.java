package com.amazon.clouddrive.cdasdk.dps.devices;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
/* loaded from: classes11.dex */
public interface DPSDevicesRetrofitBinding {
    @GET("devices/{deviceTypeId}:{dsn}")
    Single<ListDevicesResponse> listDevices(@Path("deviceTypeId") String str, @Path("dsn") String str2);
}
