package com.amazon.whisperjoin.deviceprovisioningservice.smarthome;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncResult;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ZigbeeArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.di.DaggerWrapper;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;
@TargetApi(21)
/* loaded from: classes13.dex */
public class AssociatedCredentialsSyncService extends JobService {
    public static final String INTENT_EXTRA_ONE_OFF_REFRESH = "oneOffRefresh";
    public static final String INTENT_EXTRA_SOURCE = "source";
    public static final String INTENT_EXTRA_SOURCE_VAL_DSHS = "DSHS";
    public static final String INTENT_EXTRA_SOURCE_VAL_MIDDLEWARE = "MIDDLEWARE";
    public static final String INTENT_EXTRA_SOURCE_VAL_S2DM = "S2DM";
    private static final String TAG = AssociatedCredentialsSyncService.class.getSimpleName();
    @Inject
    AssociatedDeviceCredentialsSyncCoordinator mAssociatedDeviceCredentialsSyncCoordinator;
    @Inject
    CredentialSyncMetricsRecorder mCredentialSyncMetricsRecorder;
    @Inject
    DSHSSetCredentialsAPI mDSHSSetCredentialsAPI;
    @Inject
    FFSArcusSyncCoordinator mFFSArcusSyncCoordinator;
    private Disposable mGetProvisionerConfigurationRequestDisposable;
    @Inject
    ProvisionerClientData mProvisionerClientData;
    @Inject
    ZigbeeCredentialsSyncCoordinator mZigbeeCredentialsSyncCoordinator;

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(JobParameters jobParameters, boolean z) {
        WJLog.i(TAG, "Finished");
        if (jobParameters != null) {
            jobFinished(jobParameters, z);
        } else {
            stopSelf();
        }
    }

    private void recordRequestSourceMetrics(String str) {
        if (!StringUtils.isEmpty(str)) {
            WJLog.d(TAG, "Recording source : " + str);
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -331905646) {
                if (hashCode != 2107866) {
                    if (hashCode == 2522888 && str.equals(INTENT_EXTRA_SOURCE_VAL_S2DM)) {
                        c = 1;
                    }
                } else if (str.equals(INTENT_EXTRA_SOURCE_VAL_DSHS)) {
                    c = 0;
                }
            } else if (str.equals(INTENT_EXTRA_SOURCE_VAL_MIDDLEWARE)) {
                c = 2;
            }
            if (c == 0) {
                this.mCredentialSyncMetricsRecorder.onRequestSyncCredentialsDSHS();
                return;
            } else if (c == 1) {
                this.mCredentialSyncMetricsRecorder.onRequestSyncCredentialsS2DM();
                return;
            } else if (c != 2) {
                this.mCredentialSyncMetricsRecorder.onRequestSyncCredentialsUnrecognizedSource();
                WJLog.w(TAG, "Unknown Source: " + str);
                return;
            } else {
                this.mCredentialSyncMetricsRecorder.onRequestSyncCredentialsMiddleware();
                return;
            }
        }
        WJLog.w(TAG, "No request source to record");
        this.mCredentialSyncMetricsRecorder.onRequestSyncCredentialsUnknown();
    }

    private Disposable syncAssociatedCredentials(final JobParameters jobParameters) {
        WJLog.i(TAG, "Syncing Associated Credentials");
        return (Disposable) this.mFFSArcusSyncCoordinator.getData().flatMapCompletable(new Function<FFSArcusSyncResult, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedCredentialsSyncService.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(FFSArcusSyncResult fFSArcusSyncResult) throws Exception {
                ZigbeeArcusSettings zigbeeArcusSettings = fFSArcusSyncResult.getFFSArcusSettings().getZigbeeArcusSettings();
                if (!zigbeeArcusSettings.isSyncServiceEnabled()) {
                    WJLog.d(AssociatedCredentialsSyncService.TAG, "Associated Credential Syncing is not enabled. No SmartHome Simple Setup will be available on device");
                    return Completable.complete();
                }
                AssociatedCredentialsSyncService associatedCredentialsSyncService = AssociatedCredentialsSyncService.this;
                associatedCredentialsSyncService.mZigbeeCredentialsSyncCoordinator.initializeWithProvisionerClientData(associatedCredentialsSyncService.mProvisionerClientData);
                AssociatedCredentialsSyncService.this.mZigbeeCredentialsSyncCoordinator.initializeWithZigbeeSettings(zigbeeArcusSettings);
                return zigbeeArcusSettings.useV2Api() ? AssociatedCredentialsSyncService.this.syncUsingV2API(zigbeeArcusSettings) : AssociatedCredentialsSyncService.this.syncUsingV1API(zigbeeArcusSettings);
            }
        }).subscribeWith(new DisposableCompletableObserver() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedCredentialsSyncService.1
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                WJLog.i(AssociatedCredentialsSyncService.TAG, "Sync Job Complete. No need to schedule retry.");
                AssociatedCredentialsSyncService.this.mCredentialSyncMetricsRecorder.onSyncCredentialsSuccess();
                AssociatedCredentialsSyncService.this.finish(jobParameters, false);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                WJLog.e(AssociatedCredentialsSyncService.TAG, "An error occurred while Syncing, rescheduling retry job");
                AssociatedCredentialsSyncService.this.mCredentialSyncMetricsRecorder.onSyncCredentialsFailure();
                AssociatedCredentialsSyncService.this.finish(jobParameters, true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public CompletableSource syncUsingV1API(ZigbeeArcusSettings zigbeeArcusSettings) {
        WJLog.d(TAG, "Syncing using V1 API");
        this.mZigbeeCredentialsSyncCoordinator.initializeWithProvisionerClientData(this.mProvisionerClientData);
        this.mZigbeeCredentialsSyncCoordinator.initializeWithZigbeeSettings(zigbeeArcusSettings);
        return this.mZigbeeCredentialsSyncCoordinator.getData().flatMapCompletable(new Function<ZigbeeSyncResult, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedCredentialsSyncService.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(ZigbeeSyncResult zigbeeSyncResult) throws Exception {
                WJLog.i(AssociatedCredentialsSyncService.TAG, "Associated Credential Syncing Successful. Using v1 API");
                AssociatedCredentialsSyncService.this.mDSHSSetCredentialsAPI.setCredentials(zigbeeSyncResult.getZigbeeFFSEntry());
                return Completable.complete();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CompletableSource syncUsingV2API(ZigbeeArcusSettings zigbeeArcusSettings) {
        WJLog.d(TAG, "Syncing using V2 API");
        this.mAssociatedDeviceCredentialsSyncCoordinator.initializeWithZigbeeSettings(zigbeeArcusSettings);
        this.mAssociatedDeviceCredentialsSyncCoordinator.initializeWithProvisionerClientData(this.mProvisionerClientData);
        return this.mAssociatedDeviceCredentialsSyncCoordinator.getData().flatMapCompletable(new Function<AssociatedDeviceCredentialsSyncResult, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedCredentialsSyncService.4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(AssociatedDeviceCredentialsSyncResult associatedDeviceCredentialsSyncResult) throws Exception {
                WJLog.i(AssociatedCredentialsSyncService.TAG, "Associated Credential Syncing V2 Successful.");
                AssociatedCredentialsSyncService.this.mDSHSSetCredentialsAPI.setCredentialsV2(associatedDeviceCredentialsSyncResult.getCredentialsJSON());
                return Completable.complete();
            }
        });
    }

    @Override // android.app.Service
    public void onCreate() {
        WJLog.d(TAG, "onCreate");
        super.onCreate();
        DaggerWrapper.initializeBaseComponent(getApplicationContext());
        if (DaggerWrapper.getBaseComponent().getMapAuthProvider().getAccount() == null) {
            WJLog.w(TAG, "Don't refresh credentials since no customer logged in");
            stopSelf();
            return;
        }
        ProvisioningServiceConfiguration fromSharedPreferences = ProvisioningServiceConfiguration.getFromSharedPreferences(DaggerWrapper.getBaseComponent().getSharedPreferencesProvider().getFFSConfigurationPreferences());
        if (fromSharedPreferences == null) {
            WJLog.w(TAG, "Configuration not saved yet, not running credentials sync service");
            stopSelf();
            return;
        }
        DaggerWrapper.getProvisioningDependencyInjector(DaggerWrapper.getInitializedProvisioningComponent(fromSharedPreferences, WorkflowConfigurationFactory.createDefault(), ProvisioningMethod.FFS)).inject(this);
        this.mFFSArcusSyncCoordinator.initializeWithProvisionerClientData(this.mProvisionerClientData);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null && intent.hasExtra(INTENT_EXTRA_ONE_OFF_REFRESH) && intent.getBooleanExtra(INTENT_EXTRA_ONE_OFF_REFRESH, false)) {
            WJLog.i(TAG, "Doing one off credential sync");
            this.mGetProvisionerConfigurationRequestDisposable = syncAssociatedCredentials(null);
            recordRequestSourceMetrics(intent.getStringExtra("source"));
            return 2;
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        WJLog.i(TAG, "onStartJob");
        this.mCredentialSyncMetricsRecorder.onSyncCredentialsStart();
        if (this.mZigbeeCredentialsSyncCoordinator == null) {
            WJLog.w(TAG, "No Zigbee Sync Coordinator found, cancelling job");
            return false;
        }
        if (jobParameters != null) {
            recordRequestSourceMetrics(jobParameters.getExtras().getString("source"));
        }
        this.mGetProvisionerConfigurationRequestDisposable = syncAssociatedCredentials(jobParameters);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        WJLog.d(TAG, "onStopJob");
        Disposable disposable = this.mGetProvisionerConfigurationRequestDisposable;
        if (disposable != null) {
            disposable.dispose();
            this.mGetProvisionerConfigurationRequestDisposable = null;
            return true;
        }
        return true;
    }
}
