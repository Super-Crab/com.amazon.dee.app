package com.amazon.whisperjoin.deviceprovisioningservice.smarthome;

import android.app.job.JobScheduler;
import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.devicesetupservice.smarthome.CredentialRequest;
import com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsV2Output;
import com.amazon.devicesetupservice.smarthome.ProtocolType;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ZigbeeArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerDevicesCredentialsRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Iso8601TimeConverter;
import com.amazon.whisperjoin.util.rx.RxLog;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.math.RandomUtils;
/* loaded from: classes13.dex */
public class AssociatedDeviceCredentialsSyncCoordinator extends SyncCoordinator<AssociatedDeviceCredentialsSyncResult> {
    private static final String SHARED_PREF = "AssociatedDeviceCredentialSync";
    private static final String TAG = "AssociatedDeviceCredentialsSyncCoordinator";
    static final int ZIGBEE_CREDENTIAL_SYNC_REFRESH_JOB_ID = 5034207;
    private final DSSClient mDSSClient;
    private final Gson mGson;
    private final ProvisionerInfo mProvisionerInfo;
    private ZigbeeArcusSettings mZigbeeArcusSettings;

    public AssociatedDeviceCredentialsSyncCoordinator(JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Context context, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, DSSClient dSSClient, ProvisionerInfo provisionerInfo, Gson gson) {
        super(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock);
        this.mDSSClient = dSSClient;
        this.mProvisionerInfo = provisionerInfo;
        this.mGson = gson;
    }

    private GetCustomerDevicesCredentialsRequest buildGetAllSmartHomeCredentialsRequest() {
        CredentialRequest credentialRequest = new CredentialRequest();
        credentialRequest.setProtocolType(ProtocolType.BLE_MESH);
        CredentialRequest credentialRequest2 = new CredentialRequest();
        credentialRequest2.setProtocolType(ProtocolType.ZIGBEE);
        return new GetCustomerDevicesCredentialsRequest(Arrays.asList(credentialRequest, credentialRequest2), this.mProvisionerInfo);
    }

    private void scheduleRefresh(AssociatedDeviceCredentialsSyncResult associatedDeviceCredentialsSyncResult) {
        if (!this.mZigbeeArcusSettings.isPeriodicSyncEnabled()) {
            WJLog.d(TAG, "Periodic Sync not enabled, not scheduling a refresh job in future");
            return;
        }
        long convertDurationToMs = Iso8601TimeConverter.convertDurationToMs(this.mZigbeeArcusSettings.getSyncPeriod()) - (this.mClock.epochTimeMillis() - associatedDeviceCredentialsSyncResult.getTimestamp());
        long millis = TimeUnit.MINUTES.toMillis(RandomUtils.nextInt(60));
        WJLog.d(TAG, String.format(Locale.ENGLISH, "Scheduling refresh. MillisToNextCall: [%d], Jitter: [%d]", Long.valueOf(convertDurationToMs), Long.valueOf(millis)));
        scheduleZigbeeSyncJob(convertDurationToMs + millis);
    }

    private void scheduleZigbeeSyncJob(long j) {
        long convertDurationToMs = Iso8601TimeConverter.convertDurationToMs(this.mZigbeeArcusSettings.getJobScheduleMinLatencyDuration());
        long convertDurationToMs2 = Iso8601TimeConverter.convertDurationToMs(this.mZigbeeArcusSettings.getJobScheduleMaxLatencyDuration());
        long convertDurationToMs3 = Iso8601TimeConverter.convertDurationToMs(this.mZigbeeArcusSettings.getJobScheduleInitialBackOffDuration());
        WJLog.d(TAG, String.format("Scheduling cred sync job with minLatency: %d, maxLatency: %d, initialBackoff: %d", Long.valueOf(convertDurationToMs), Long.valueOf(convertDurationToMs2), Long.valueOf(convertDurationToMs3)));
        this.mJobScheduler.schedule(this.mJobInfoProvider.provide(this.mContext, ZIGBEE_CREDENTIAL_SYNC_REFRESH_JOB_ID, AssociatedCredentialsSyncService.class, j + convertDurationToMs, j + convertDurationToMs2, convertDurationToMs3, true));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected Single<AssociatedDeviceCredentialsSyncResult> fetchDataFromNetwork() {
        return this.mDSSClient.getCustomerDevicesCredentialsV2(buildGetAllSmartHomeCredentialsRequest()).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Getting customer's SmartHome Credentials")).doOnSuccess(RxLog.doOnSuccess(TAG, "Got customer's SmartHome Credentials")).doOnError(RxLog.doOnError(TAG, "There was an error syncing the Customer's SmartHome Credentials")).map(new Function<GetCustomerDevicesCredentialsV2Output, AssociatedDeviceCredentialsSyncResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedDeviceCredentialsSyncCoordinator.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public AssociatedDeviceCredentialsSyncResult mo10358apply(GetCustomerDevicesCredentialsV2Output getCustomerDevicesCredentialsV2Output) throws Exception {
                return new AssociatedDeviceCredentialsSyncResult(AssociatedDeviceCredentialsSyncCoordinator.this.mGson.toJson(getCustomerDevicesCredentialsV2Output), ((SyncCoordinator) AssociatedDeviceCredentialsSyncCoordinator.this).mClock.epochTimeMillis());
            }
        });
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public Single<AssociatedDeviceCredentialsSyncResult> getData() {
        if (this.mZigbeeArcusSettings != null) {
            return super.getData();
        }
        throw new IllegalStateException("Must first initialize with zigbee settings");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected SharedPreferences getSharedPreferences() {
        return this.mSharedPreferencesProvider.get(SHARED_PREF);
    }

    public void initializeWithZigbeeSettings(ZigbeeArcusSettings zigbeeArcusSettings) {
        this.mZigbeeArcusSettings = zigbeeArcusSettings;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected String tag() {
        return TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public boolean isDataInvalid(AssociatedDeviceCredentialsSyncResult associatedDeviceCredentialsSyncResult) {
        return this.mClock.epochTimeMillis() > Iso8601TimeConverter.convertDurationToMs(this.mZigbeeArcusSettings.getCacheExpirationPeriod()) + associatedDeviceCredentialsSyncResult.getTimestamp();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void onDataFetchedFromNetwork(AssociatedDeviceCredentialsSyncResult associatedDeviceCredentialsSyncResult) {
        scheduleRefresh(associatedDeviceCredentialsSyncResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void onPersistedDataFetched(AssociatedDeviceCredentialsSyncResult associatedDeviceCredentialsSyncResult) {
        scheduleRefresh(associatedDeviceCredentialsSyncResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void persistDataToSharedPreferences(AssociatedDeviceCredentialsSyncResult associatedDeviceCredentialsSyncResult) {
        AssociatedDeviceCredentialsSyncResult.writeToSharedPreferences(associatedDeviceCredentialsSyncResult, getSharedPreferences());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    /* renamed from: readPersistedDataFromSharedPreferences */
    public AssociatedDeviceCredentialsSyncResult mo5689readPersistedDataFromSharedPreferences() {
        return AssociatedDeviceCredentialsSyncResult.readFromSharedPreferences(getSharedPreferences());
    }
}
