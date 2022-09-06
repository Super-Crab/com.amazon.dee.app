package com.amazon.whisperjoin.deviceprovisioningservice.arcus;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.di.DaggerWrapper;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import javax.inject.Inject;
@TargetApi(21)
/* loaded from: classes13.dex */
public class FFSArcusSyncJobService extends JobService {
    private static final String TAG = FFSArcusSyncJobService.class.getSimpleName();
    private Context mContext;
    @Inject
    FFSArcusSyncCoordinator mFFSArcusSyncCoordinator;
    @Inject
    ProvisionerClientData mProvisionerClientData;

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastModifiedArcusSettings(FFSArcusSettings fFSArcusSettings) {
        WJLog.d(TAG, "Broadcasting modified Arcus settings");
        Bundle bundle = new Bundle();
        FFSArcusSettings.writeToBundle(fFSArcusSettings, bundle);
        Intent intent = new Intent(ArcusConstants.ARCUS_UPDATE_INTENT_ACTION);
        intent.putExtras(bundle);
        this.mContext.sendBroadcast(intent, "com.amazon.whisperjoin.deviceprovisioningservice.ArcusUpdate.Permission");
    }

    void internalJobFinished(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, z);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        DaggerWrapper.initializeBaseComponent(this.mContext);
        if (DaggerWrapper.getBaseComponent().getMapAuthProvider().getAccount() == null) {
            WJLog.w(TAG, "Don't refresh arcus since no customer logged in");
            stopSelf();
            return;
        }
        ProvisioningServiceConfiguration fromSharedPreferences = ProvisioningServiceConfiguration.getFromSharedPreferences(DaggerWrapper.getBaseComponent().getSharedPreferencesProvider().getFFSConfigurationPreferences());
        if (fromSharedPreferences == null) {
            WJLog.w(TAG, "No persisted ProvisioningServiceConfiguration found for FFS");
            stopSelf();
            return;
        }
        new DaggerWrapper.ProvisionerServices(DaggerWrapper.getBaseComponent(), fromSharedPreferences, WorkflowConfigurationFactory.createDefault(), ProvisioningMethod.FFS).getDependencyInjector().inject(this);
        this.mFFSArcusSyncCoordinator.initializeWithProvisionerClientData(this.mProvisionerClientData);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        WJLog.i(TAG, "onStartJob");
        FFSArcusSyncCoordinator fFSArcusSyncCoordinator = this.mFFSArcusSyncCoordinator;
        if (fFSArcusSyncCoordinator == null) {
            internalJobFinished(jobParameters, false);
            return true;
        }
        fFSArcusSyncCoordinator.getData().subscribeWith(new DisposableSingleObserver<FFSArcusSyncResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncJobService.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(Throwable th) {
                WJLog.e(FFSArcusSyncJobService.TAG, "There was an error syncing arcus settings", th);
                dispose();
                FFSArcusSyncJobService.this.internalJobFinished(jobParameters, true);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(FFSArcusSyncResult fFSArcusSyncResult) {
                WJLog.i(FFSArcusSyncJobService.TAG, "Sync Successful");
                if (fFSArcusSyncResult.isModified()) {
                    FFSArcusSyncJobService.this.broadcastModifiedArcusSettings(fFSArcusSyncResult.getFFSArcusSettings());
                }
                dispose();
                FFSArcusSyncJobService.this.internalJobFinished(jobParameters, false);
            }
        });
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        WJLog.d(TAG, "onStopJob");
        return true;
    }

    void setContext(Context context) {
        this.mContext = context;
    }
}
