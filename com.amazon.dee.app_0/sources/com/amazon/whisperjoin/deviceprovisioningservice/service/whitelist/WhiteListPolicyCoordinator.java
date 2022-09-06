package com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist;

import android.annotation.TargetApi;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.IntentActionConstants;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FFSWhiteListPolicyRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FFSWhiteListPolicyResponse;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.TimeUnit;
@TargetApi(21)
/* loaded from: classes13.dex */
public class WhiteListPolicyCoordinator {
    private static final String ANDROID_PLATFORM = "Android";
    static final int REFRESH_JOB_ID = 8557036;
    private static final String SHARED_PREF_NAME = "WhiteListPolicy";
    private static final String TAG = "WhiteListPolicyCoordinator";
    private final Clock mClock;
    private final Context mContext;
    private final DSSClient mDSSClient;
    private final JobInfoProvider mJobInfoProvider;
    private final JobScheduler mJobScheduler;
    private final ProvisionerClientData mProvisionerClientData;
    private final SharedPreferences mSharedPreferences;

    public WhiteListPolicyCoordinator(Context context, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, DSSClient dSSClient, ProvisionerClientData provisionerClientData) {
        this.mContext = context;
        this.mSharedPreferences = sharedPreferencesProvider.get(SHARED_PREF_NAME);
        this.mJobScheduler = jobScheduler;
        this.mJobInfoProvider = jobInfoProvider;
        this.mClock = clock;
        this.mDSSClient = dSSClient;
        this.mProvisionerClientData = provisionerClientData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastWhiteListPolicyUpdate(WhiteListPolicy whiteListPolicy) {
        WJLog.d(TAG, "Broadcasting WhiteListPolicy Update");
        Bundle bundle = new Bundle();
        WhiteListPolicy.writeToBundle(whiteListPolicy, bundle);
        ProvisionerClientData.writeToBundle(this.mProvisionerClientData, bundle);
        Intent intent = new Intent(IntentActionConstants.WHITELIST_POLICY_UPDATE_ACTION);
        intent.putExtras(bundle);
        this.mContext.sendBroadcast(intent, IntentActionConstants.WHITELIST_POLICY_UPDATE_PERMISSION);
    }

    private FFSWhiteListPolicyRequest buildFFSWhiteListPolicyRequest() {
        return new FFSWhiteListPolicyRequest(this.mProvisionerClientData.getDeviceModel(), this.mProvisionerClientData.getDeviceManufacturer(), "Android", this.mProvisionerClientData.getDeviceFirmwareVersion(), this.mProvisionerClientData.getClientAppName(), this.mProvisionerClientData.getClientAppVersion(), this.mProvisionerClientData.getMarketplace(), this.mProvisionerClientData.getCustomerEcid());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WhiteListPolicy buildWhiteListPolicyFromResponse(FFSWhiteListPolicyResponse fFSWhiteListPolicyResponse) {
        ScanningMode fromValue = ScanningMode.getFromValue(fFSWhiteListPolicyResponse.getFfsScanningEnabled());
        if (fromValue == null) {
            WJLog.w(TAG, "Unrecognized scanning mode, falling back to Low Power scan");
            fromValue = ScanningMode.LOW_POWER_HIGH_LATENCY;
        }
        ScanningMode scanningMode = fromValue;
        long epochTimeMillis = this.mClock.epochTimeMillis();
        return new WhiteListPolicy(scanningMode, fFSWhiteListPolicyResponse.getMinBatteryLevel(), TimeUnit.HOURS.toMillis(fFSWhiteListPolicyResponse.getHoursToNextCall()) + epochTimeMillis, epochTimeMillis);
    }

    private boolean isPersistedPolicyValid(WhiteListPolicy whiteListPolicy, ProvisionerClientData provisionerClientData) {
        if (whiteListPolicy == null) {
            WJLog.v(TAG, "Policy is null");
            return false;
        } else if (provisionerClientData == null) {
            WJLog.d(TAG, "Client data is null");
            return false;
        } else if (this.mClock.epochTimeMillis() > whiteListPolicy.getPolicyExpiresAt()) {
            WJLog.v(TAG, "Current Policy is expired");
            return false;
        } else if (this.mProvisionerClientData.equals(provisionerClientData)) {
            return true;
        } else {
            WJLog.v(TAG, "Policy not for current provisioner client");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleRefreshJob(long j) {
        String str = TAG;
        WJLog.d(str, "Scheduling Job to refresh policy in " + j + "millis");
        this.mJobScheduler.schedule(this.mJobInfoProvider.provide(this.mContext, REFRESH_JOB_ID, FFSWhiteListJobService.class, j, TimeUnit.MINUTES.toMillis(10L) + j, TimeUnit.MINUTES.toMillis(1L), false));
    }

    private void scheduleRefreshJobForPolicy(WhiteListPolicy whiteListPolicy) {
        WJLog.d(TAG, "Rescheduling policy refresh");
        scheduleRefreshJob(whiteListPolicy.getPolicyExpiresAt() - this.mClock.epochTimeMillis());
    }

    public void cancelAnyPendingRefreshJobs() {
        WJLog.d(TAG, "Trying to cancel any pending white list refresh jobs.");
        this.mJobScheduler.cancel(REFRESH_JOB_ID);
    }

    public Single<WhiteListPolicy> getWhiteListPolicy() {
        WhiteListPolicy readFromSharedPreferences = WhiteListPolicy.readFromSharedPreferences(this.mSharedPreferences);
        if (isPersistedPolicyValid(readFromSharedPreferences, ProvisionerClientData.readFromSharedPreferences(this.mSharedPreferences))) {
            WJLog.d(TAG, "Current policy is still valid, continue using it.");
            scheduleRefreshJobForPolicy(readFromSharedPreferences);
            return Single.just(readFromSharedPreferences);
        }
        WJLog.d(TAG, "No valid policy exists, refreshing policy");
        return this.mDSSClient.getFFSWhiteListPolicy(buildFFSWhiteListPolicyRequest()).map(new Function<FFSWhiteListPolicyResponse, WhiteListPolicy>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyCoordinator.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public WhiteListPolicy mo10358apply(@NonNull FFSWhiteListPolicyResponse fFSWhiteListPolicyResponse) throws Exception {
                WhiteListPolicy buildWhiteListPolicyFromResponse = WhiteListPolicyCoordinator.this.buildWhiteListPolicyFromResponse(fFSWhiteListPolicyResponse);
                WhiteListPolicy.writeToSharedPreferences(buildWhiteListPolicyFromResponse, WhiteListPolicyCoordinator.this.mSharedPreferences);
                ProvisionerClientData.writeToSharedPreferences(WhiteListPolicyCoordinator.this.mProvisionerClientData, WhiteListPolicyCoordinator.this.mSharedPreferences);
                WhiteListPolicyCoordinator.this.broadcastWhiteListPolicyUpdate(buildWhiteListPolicyFromResponse);
                WhiteListPolicyCoordinator.this.scheduleRefreshJob(TimeUnit.HOURS.toMillis(fFSWhiteListPolicyResponse.getHoursToNextCall()));
                return buildWhiteListPolicyFromResponse;
            }
        });
    }
}
