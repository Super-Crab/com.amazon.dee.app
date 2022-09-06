package com.amazon.whisperjoin.deviceprovisioningservice.smarthome;

import android.app.job.JobScheduler;
import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.devicesetupservice.smarthome.CredentialRequest;
import com.amazon.devicesetupservice.smarthome.ProtocolType;
import com.amazon.devicesetupservice.smarthome.SmartHomeCredential;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.ZigbeeFFSEntry;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ZigbeeArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerDevicesCredentialsRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerDevicesCredentialsResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Iso8601TimeConverter;
import com.amazon.whisperjoin.util.rx.RxLog;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.math.RandomUtils;
@Deprecated
/* loaded from: classes13.dex */
public class ZigbeeCredentialsSyncCoordinator extends SyncCoordinator<ZigbeeSyncResult> {
    private static final String SHARED_PREF = "DeviceCredentialSync";
    private static final String TAG = "ZigbeeCredentialsSyncCoordinator";
    static final int ZIGBEE_CREDENTIAL_SYNC_REFRESH_JOB_ID = 5034207;
    private final DSSClient mDSSClient;
    private final ProvisionerInfo mProvisionerInfo;
    private ZigbeeArcusSettings mZigbeeArcusSettings;

    public ZigbeeCredentialsSyncCoordinator(JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Context context, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, DSSClient dSSClient, ProvisionerInfo provisionerInfo) {
        super(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock);
        this.mDSSClient = dSSClient;
        this.mProvisionerInfo = provisionerInfo;
    }

    private GetCustomerDevicesCredentialsRequest buildGetAllZigbeeCustomerDevicesCredentialsRequest() {
        CredentialRequest credentialRequest = new CredentialRequest();
        credentialRequest.setProtocolType(ProtocolType.ZIGBEE);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(credentialRequest);
        return new GetCustomerDevicesCredentialsRequest(arrayList, this.mProvisionerInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ZigbeeFFSEntry.ZigbeeAuthMaterial> buildZigbeeAuthMaterial(GetCustomerDevicesCredentialsResponse getCustomerDevicesCredentialsResponse) {
        ArrayList<ZigbeeFFSEntry.ZigbeeAuthMaterial> arrayList = new ArrayList<>();
        for (SmartHomeCredential smartHomeCredential : getCustomerDevicesCredentialsResponse.getAssociatedCredentials()) {
            if (smartHomeCredential.getProtocolType().equals(ProtocolType.ZIGBEE)) {
                String authMaterialId = smartHomeCredential.getAuthMaterialId();
                String authMaterialData = smartHomeCredential.getAuthMaterialData();
                WJLog.d(TAG, String.format(Locale.ENGLISH, "Building ZigbeeFFSEntry: ZigId [%s] ZigInstallCode [%s]", authMaterialId, authMaterialData));
                arrayList.add(new ZigbeeFFSEntry.ZigbeeAuthMaterial(authMaterialId, authMaterialData));
            }
        }
        return arrayList;
    }

    private void scheduleRefresh(ZigbeeSyncResult zigbeeSyncResult) {
        long convertDurationToMs = Iso8601TimeConverter.convertDurationToMs(this.mZigbeeArcusSettings.getSyncPeriod()) - (this.mClock.epochTimeMillis() - zigbeeSyncResult.getTimestamp());
        long millis = TimeUnit.MINUTES.toMillis(RandomUtils.nextInt(60));
        WJLog.d(TAG, String.format(Locale.ENGLISH, "Scheduling refresh. MillisToNextCall: [%d], Jitter: [%d]", Long.valueOf(convertDurationToMs), Long.valueOf(millis)));
        scheduleZigbeeSyncJob(convertDurationToMs + millis);
    }

    private void scheduleZigbeeSyncJob(long j) {
        this.mJobScheduler.schedule(this.mJobInfoProvider.provide(this.mContext, ZIGBEE_CREDENTIAL_SYNC_REFRESH_JOB_ID, AssociatedCredentialsSyncService.class, j, TimeUnit.MINUTES.toMillis(10L) + j, TimeUnit.MINUTES.toMillis(1L), false));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected Single<ZigbeeSyncResult> fetchDataFromNetwork() {
        return this.mDSSClient.getCustomerDevicesCredentials(buildGetAllZigbeeCustomerDevicesCredentialsRequest()).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Getting customer's Zigbee Credentials")).doOnSuccess(RxLog.doOnSuccess(TAG, "Got customer's Zigbee Credentials")).doOnError(RxLog.doOnError(TAG, "There was an error syncing the Customer's Zigbee Credentials")).map(new Function<GetCustomerDevicesCredentialsResponse, ZigbeeSyncResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.smarthome.ZigbeeCredentialsSyncCoordinator.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ZigbeeSyncResult mo10358apply(GetCustomerDevicesCredentialsResponse getCustomerDevicesCredentialsResponse) throws Exception {
                return new ZigbeeSyncResult(new ZigbeeFFSEntry(ZigbeeCredentialsSyncCoordinator.this.buildZigbeeAuthMaterial(getCustomerDevicesCredentialsResponse)), ((SyncCoordinator) ZigbeeCredentialsSyncCoordinator.this).mClock.epochTimeMillis());
            }
        });
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public Single<ZigbeeSyncResult> getData() {
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
    public boolean isDataInvalid(ZigbeeSyncResult zigbeeSyncResult) {
        return this.mClock.epochTimeMillis() > Iso8601TimeConverter.convertDurationToMs(this.mZigbeeArcusSettings.getCacheExpirationPeriod()) + zigbeeSyncResult.getTimestamp();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void onDataFetchedFromNetwork(ZigbeeSyncResult zigbeeSyncResult) {
        scheduleRefresh(zigbeeSyncResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void onPersistedDataFetched(ZigbeeSyncResult zigbeeSyncResult) {
        scheduleRefresh(zigbeeSyncResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void persistDataToSharedPreferences(ZigbeeSyncResult zigbeeSyncResult) {
        ZigbeeSyncResult.writeToSharedPreferences(zigbeeSyncResult, getSharedPreferences());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    /* renamed from: readPersistedDataFromSharedPreferences */
    public ZigbeeSyncResult mo5689readPersistedDataFromSharedPreferences() {
        return ZigbeeSyncResult.readFromSharedPreferences(getSharedPreferences());
    }
}
