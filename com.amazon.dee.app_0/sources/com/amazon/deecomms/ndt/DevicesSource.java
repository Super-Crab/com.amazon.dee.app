package com.amazon.deecomms.ndt;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetDevices;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.DeviceTargetingException;
import com.amazon.deecomms.ndt.model.GetDevicesResponse;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class DevicesSource {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DevicesSource.class);
    private long CACHE_TIMEOUT;
    private String mRequestId;
    private GetDevicesResponse mResponse;
    private Observable<GetDevicesResponse> memoryObservable;
    private Observable<GetDevicesResponse> networkObservable;

    public DevicesSource() {
        setupSubscribers();
        setCacheTimeOut();
    }

    private void setCacheTimeOut() {
        this.CACHE_TIMEOUT = TimeUnit.SECONDS.toMillis(CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.GET_DEVICES_CACHE_TIMEOUT).intValue());
    }

    private void setupSubscribers() {
        this.networkObservable = Observable.defer(new Func0() { // from class: com.amazon.deecomms.ndt.-$$Lambda$DevicesSource$XxaI8pNTPZFgYRxqlk_TRm20VVM
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return DevicesSource.this.lambda$setupSubscribers$1$DevicesSource();
            }
        });
        this.memoryObservable = Observable.defer(new Func0() { // from class: com.amazon.deecomms.ndt.-$$Lambda$DevicesSource$fyX83oLs6Qp2dEYowAKAprOwVYI
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return DevicesSource.this.lambda$setupSubscribers$2$DevicesSource();
            }
        });
    }

    public void clearCache() {
        this.mResponse = null;
    }

    public Observable<GetDevicesResponse> getDevicesObservable(boolean z) {
        final boolean[] zArr = {z};
        return Observable.concat(this.memoryObservable, this.networkObservable).first(new Func1() { // from class: com.amazon.deecomms.ndt.-$$Lambda$DevicesSource$qyGGi2_RHmX57Vg5C57JUj0xi-8
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DevicesSource.this.lambda$getDevicesObservable$0$DevicesSource(zArr, (GetDevicesResponse) obj);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    @Nullable
    public GetDevicesResponse getMemoryData() {
        return this.mResponse;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public GetDevicesResponse getServerData() throws ServiceException, DeviceTargetingException, InterruptedException {
        if (!CommsDaggerWrapper.getComponent().getCommsConnectivityMonitor().isConnected()) {
            LOG.e("Cannot get devices: no network connectivity!");
            return this.mResponse;
        }
        GetDevices getDevices = new GetDevices();
        String commsId = CommsInternal.getInstance().getCommsId();
        if (!TextUtils.isEmpty(commsId)) {
            this.mResponse = getDevices.getDevices(commsId);
            this.mRequestId = getDevices.getRequestId();
            this.mResponse.setTimeStamp(Long.valueOf(System.currentTimeMillis()));
            return this.mResponse;
        }
        throw new DeviceTargetingException(new Throwable("CommsId is null while attempting to fetch list of devices."));
    }

    public /* synthetic */ Boolean lambda$getDevicesObservable$0$DevicesSource(boolean[] zArr, GetDevicesResponse getDevicesResponse) {
        if (getDevicesResponse != null && !zArr[0]) {
            return getDevicesResponse.isCacheValid(this.CACHE_TIMEOUT);
        }
        zArr[0] = false;
        return false;
    }

    public /* synthetic */ Observable lambda$setupSubscribers$1$DevicesSource() {
        try {
            this.mResponse = getServerData();
            return Observable.just(this.mResponse);
        } catch (Exception e) {
            return Observable.error(e);
        }
    }

    public /* synthetic */ Observable lambda$setupSubscribers$2$DevicesSource() {
        try {
            this.mResponse = getMemoryData();
            return Observable.just(this.mResponse);
        } catch (Exception e) {
            return Observable.error(e);
        }
    }
}
