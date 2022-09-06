package com.amazon.whisperjoin.deviceprovisioningservice.smarthome;

import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ZigbeeArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.di.DaggerWrapper;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Iso8601TimeConverter;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes13.dex */
public class CredSyncJobSchedulerImpl implements CredSyncJobScheduler {
    private static final int CRED_SYNC_JOB_ID = 841735;
    private static final String TAG = "CredSyncJobSchedulerImpl";

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.smarthome.CredSyncJobScheduler
    public void scheduleCredSyncRun(final Context context, final String str) {
        DaggerWrapper.initializeBaseComponent(context);
        if (DaggerWrapper.getBaseComponent().getMapAuthProvider().getAccount() == null) {
            WJLog.w(TAG, "Don't refresh credentials since no customer logged in");
            return;
        }
        ProvisioningServiceConfiguration fromSharedPreferences = ProvisioningServiceConfiguration.getFromSharedPreferences(DaggerWrapper.getBaseComponent().getSharedPreferencesProvider().getFFSConfigurationPreferences());
        if (fromSharedPreferences == null) {
            WJLog.w(TAG, "Configuration not saved yet, not running credentials sync service");
            return;
        }
        ProvisionerServicesComponent initializedProvisioningComponent = DaggerWrapper.getInitializedProvisioningComponent(fromSharedPreferences, WorkflowConfigurationFactory.createDefault(), ProvisioningMethod.FFS);
        FFSArcusSyncCoordinator fFSArcusSyncCoordinator = DaggerWrapper.getBaseComponent().getFFSArcusSyncCoordinator();
        fFSArcusSyncCoordinator.initializeWithProvisionerClientData(initializedProvisioningComponent.providesProvisionerClientData());
        final JobInfoProvider jobInfoProvider = DaggerWrapper.getBaseComponent().getJobInfoProvider();
        final JobScheduler jobScheduler = DaggerWrapper.getBaseComponent().getJobScheduler();
        int i = Build.VERSION.SDK_INT;
        fFSArcusSyncCoordinator.getFFSArcusSettings().flatMapCompletable(new Function<FFSArcusSettings, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.smarthome.CredSyncJobSchedulerImpl.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(FFSArcusSettings fFSArcusSettings) throws Exception {
                ZigbeeArcusSettings zigbeeArcusSettings = fFSArcusSettings.getZigbeeArcusSettings();
                long convertDurationToMs = Iso8601TimeConverter.convertDurationToMs(zigbeeArcusSettings.getJobScheduleMinLatencyDuration());
                long convertDurationToMs2 = Iso8601TimeConverter.convertDurationToMs(zigbeeArcusSettings.getJobScheduleMaxLatencyDuration());
                long convertDurationToMs3 = Iso8601TimeConverter.convertDurationToMs(zigbeeArcusSettings.getJobScheduleInitialBackOffDuration());
                PersistableBundle persistableBundle = new PersistableBundle();
                persistableBundle.putBoolean(AssociatedCredentialsSyncService.INTENT_EXTRA_ONE_OFF_REFRESH, true);
                persistableBundle.putString("source", str);
                WJLog.d(CredSyncJobSchedulerImpl.TAG, String.format("Scheduling cred sync job with minLatency: %d, maxLatency: %d, initialBackoff: %d", Long.valueOf(convertDurationToMs), Long.valueOf(convertDurationToMs2), Long.valueOf(convertDurationToMs3)));
                jobScheduler.schedule(jobInfoProvider.provide(context, CredSyncJobSchedulerImpl.CRED_SYNC_JOB_ID, AssociatedCredentialsSyncService.class, convertDurationToMs, convertDurationToMs2, convertDurationToMs3, false, persistableBundle));
                WJLog.d(CredSyncJobSchedulerImpl.TAG, "Scheduled Refresh Job");
                return Completable.complete();
            }
        }).subscribe(new CompletableObserver() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.smarthome.CredSyncJobSchedulerImpl.1
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                WJLog.e(CredSyncJobSchedulerImpl.TAG, "An error occurred scheduling cred sync job", th);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
            }
        });
    }
}
