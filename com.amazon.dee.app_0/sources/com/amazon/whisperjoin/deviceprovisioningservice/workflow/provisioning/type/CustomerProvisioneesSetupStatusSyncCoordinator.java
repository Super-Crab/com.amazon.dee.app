package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.app.job.JobScheduler;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.util.dss.DSSTypesHelper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerProvisioneesSetupStatusRequest;
import com.amazon.whisperjoin.util.rx.RxLog;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
/* loaded from: classes13.dex */
public class CustomerProvisioneesSetupStatusSyncCoordinator extends SyncCoordinator<GetCustomerProvisioneesSetupStatusResponse> {
    @VisibleForTesting
    static final long CACHE_TIMEOUT_MS = 5000;
    private static final String TAG = "CustomerProvisioneesSetupStatusSyncCoordinator";
    private final DSSClient mDSSClient;
    private volatile long mLastFetchEpochMs;

    public CustomerProvisioneesSetupStatusSyncCoordinator(JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Context context, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, DSSClient dSSClient) {
        super(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock);
        this.mLastFetchEpochMs = 0L;
        this.mDSSClient = dSSClient;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected Single<GetCustomerProvisioneesSetupStatusResponse> fetchDataFromNetwork() {
        return this.mDSSClient.getCustomerProvisioneesSetupStatus(new GetCustomerProvisioneesSetupStatusRequest.Builder().createRequest()).observeOn(Schedulers.io()).map(new Function<com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerProvisioneesSetupStatusResponse, GetCustomerProvisioneesSetupStatusResponse>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.CustomerProvisioneesSetupStatusSyncCoordinator.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public GetCustomerProvisioneesSetupStatusResponse mo10358apply(com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse) {
                CustomerProvisioneesSetupStatusSyncCoordinator customerProvisioneesSetupStatusSyncCoordinator = CustomerProvisioneesSetupStatusSyncCoordinator.this;
                customerProvisioneesSetupStatusSyncCoordinator.mLastFetchEpochMs = ((SyncCoordinator) customerProvisioneesSetupStatusSyncCoordinator).mClock.epochTimeMillis();
                return DSSTypesHelper.convertGetCustomerProvisioneesSetupStatusResponse(getCustomerProvisioneesSetupStatusResponse);
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Getting provisionees setup status")).doOnSuccess(RxLog.doOnSuccess(TAG, "Got provisionees setup status")).doOnError(RxLog.doOnError(TAG, "There was an error getting provisionees setup status"));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected SharedPreferences getSharedPreferences() {
        throw new UnsupportedOperationException("Not allowed");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void onDataFetchedFromNetwork(GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void persistDataToSharedPreferences(GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse) {
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected void persistProvisionerClientData(ProvisionerClientData provisionerClientData) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    /* renamed from: readPersistedDataFromSharedPreferences */
    public GetCustomerProvisioneesSetupStatusResponse mo5689readPersistedDataFromSharedPreferences() {
        return null;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected ProvisionerClientData readPersistedProvisionerClientData() {
        return null;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected String tag() {
        return TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public boolean isDataInvalid(GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse) {
        return getCustomerProvisioneesSetupStatusResponse == null || this.mClock.epochTimeMillis() - this.mLastFetchEpochMs > 5000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void onPersistedDataFetched(GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse) {
        throw new UnsupportedOperationException("Data are not persisted, thus can't be fetched");
    }
}
