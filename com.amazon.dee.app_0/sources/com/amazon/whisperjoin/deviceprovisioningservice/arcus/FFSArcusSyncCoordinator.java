package com.amazon.whisperjoin.deviceprovisioningservice.arcus;

import android.app.job.JobScheduler;
import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Iso8601TimeConverter;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class FFSArcusSyncCoordinator extends SyncCoordinator<FFSArcusSyncResult> {
    public static final long DEFAULT_ERROR_TIMEOUT_MILLIS = 300000;
    private static final String SHARED_PREF_NAME = "FFSArcusSyncCoordinator";
    private static final int SYNC_ARCUS_JOB_ID = 82947;
    private static final String TAG = "FFSArcusSyncCoordinator";
    private final FFSArcusClient mFFSArcusClient;

    public FFSArcusSyncCoordinator(JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Context context, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, FFSArcusClient fFSArcusClient) {
        super(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock);
        this.mFFSArcusClient = fFSArcusClient;
    }

    private long getExpireAt(FFSArcusSyncResult fFSArcusSyncResult) {
        return fFSArcusSyncResult.getTimestamp() + Iso8601TimeConverter.convertDurationToMs(fFSArcusSyncResult.getFFSArcusSettings().getArcusSyncPeriod());
    }

    private void scheduleJob(FFSArcusSyncResult fFSArcusSyncResult) {
        long max = Math.max(getExpireAt(fFSArcusSyncResult), FFSArcusSyncState.readFromSharedPreferences(getSharedPreferences()).getSyncBlockedUntilTimestampMillis()) - this.mClock.epochTimeMillis();
        long millis = TimeUnit.MINUTES.toMillis(10L) + max;
        long millis2 = TimeUnit.MINUTES.toMillis(1L);
        WJLog.d(TAG, String.format(Locale.ENGLISH, "Scheduling Arcus Sync Job to be executed in [%d]ms with a deadline of [%d]ms", Long.valueOf(max), Long.valueOf(millis)));
        this.mJobScheduler.schedule(this.mJobInfoProvider.provide(this.mContext, SYNC_ARCUS_JOB_ID, FFSArcusSyncJobService.class, max, millis, millis2, false));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected Single<FFSArcusSyncResult> fetchDataFromNetwork() {
        return this.mFFSArcusClient.syncWithArcus().doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Throwable th) {
                WJLog.e(FFSArcusSyncCoordinator.TAG, "[OnError]: Error occurred syncing with Arcus", th);
                long waitTime = th instanceof ArcusSyncThrottled ? ((ArcusSyncThrottled) th).getWaitTime() : 300000L;
                long epochTimeMillis = ((SyncCoordinator) FFSArcusSyncCoordinator.this).mClock.epochTimeMillis() + waitTime;
                WJLog.d(FFSArcusSyncCoordinator.TAG, String.format("Next sync blocked until %d using delay %d (ms)", Long.valueOf(epochTimeMillis), Long.valueOf(waitTime)));
                FFSArcusSyncState.writeToSharedPreferences(new FFSArcusSyncState(epochTimeMillis), FFSArcusSyncCoordinator.this.getSharedPreferences());
            }
        }).onErrorResumeWith(this.mFFSArcusClient.getStoredConfiguration());
    }

    public Single<FFSArcusSettings> getFFSArcusSettings() {
        return getData().map(new Function<FFSArcusSyncResult, FFSArcusSettings>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public FFSArcusSettings mo10358apply(FFSArcusSyncResult fFSArcusSyncResult) throws Exception {
                return fFSArcusSyncResult.getFFSArcusSettings();
            }
        });
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected SharedPreferences getSharedPreferences() {
        return this.mSharedPreferencesProvider.get("FFSArcusSyncCoordinator");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void initializeWithProvisionerClientData(ProvisionerClientData provisionerClientData) {
        super.initializeWithProvisionerClientData(provisionerClientData);
        this.mFFSArcusClient.initWithProvisionerClientData(provisionerClientData);
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    protected String tag() {
        return TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public boolean isDataInvalid(FFSArcusSyncResult fFSArcusSyncResult) {
        return this.mClock.epochTimeMillis() > getExpireAt(fFSArcusSyncResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void onDataFetchedFromNetwork(FFSArcusSyncResult fFSArcusSyncResult) {
        scheduleJob(fFSArcusSyncResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void onPersistedDataFetched(FFSArcusSyncResult fFSArcusSyncResult) {
        scheduleJob(fFSArcusSyncResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    public void persistDataToSharedPreferences(FFSArcusSyncResult fFSArcusSyncResult) {
        FFSArcusSyncResult.writeToSharedPreferences(fFSArcusSyncResult, getSharedPreferences());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator
    /* renamed from: readPersistedDataFromSharedPreferences */
    public FFSArcusSyncResult mo5689readPersistedDataFromSharedPreferences() {
        return FFSArcusSyncResult.readFromSharedPreferences(getSharedPreferences());
    }
}
