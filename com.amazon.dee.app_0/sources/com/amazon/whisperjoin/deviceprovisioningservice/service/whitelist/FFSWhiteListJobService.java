package com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.di.DaggerWrapper;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import javax.inject.Inject;
@TargetApi(21)
/* loaded from: classes13.dex */
public class FFSWhiteListJobService extends JobService {
    private static final String TAG = FFSWhiteListJobService.class.getSimpleName();
    private MapAuthenticationProvider mMapAuthenticationProvider;
    @Inject
    WhiteListPolicyCoordinator mWhiteListPolicyCoordinator;
    private Disposable mWhiteListRequestDisposable;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        WJLog.d(TAG, "onCreate");
        DaggerWrapper.initializeBaseComponent(getApplicationContext());
        this.mMapAuthenticationProvider = DaggerWrapper.getBaseComponent().getMapAuthProvider();
        if (this.mMapAuthenticationProvider.getAccount() == null) {
            WJLog.w(TAG, "Don't refresh whitelist since no customer logged in");
            return;
        }
        ProvisioningServiceConfiguration fromSharedPreferences = ProvisioningServiceConfiguration.getFromSharedPreferences(DaggerWrapper.getBaseComponent().getSharedPreferencesProvider().getFFSConfigurationPreferences());
        if (fromSharedPreferences == null) {
            WJLog.w(TAG, "No persisted ProvisioningServiceConfiguration found for FFS");
            this.mWhiteListPolicyCoordinator = null;
            return;
        }
        DaggerWrapper.getProvisioningDependencyInjector(DaggerWrapper.getInitializedProvisioningComponent(fromSharedPreferences, WorkflowConfigurationFactory.createDefault(), ProvisioningMethod.FFS)).inject(this);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        WJLog.d(TAG, "onStartJob");
        if (this.mMapAuthenticationProvider.getAccount() == null) {
            WJLog.w(TAG, "Don't refresh whitelist since no customer logged in");
            return false;
        }
        WhiteListPolicyCoordinator whiteListPolicyCoordinator = this.mWhiteListPolicyCoordinator;
        if (whiteListPolicyCoordinator == null) {
            WJLog.w(TAG, "No Policy Coordinator Found, cancelling job");
            return false;
        }
        this.mWhiteListRequestDisposable = (Disposable) whiteListPolicyCoordinator.getWhiteListPolicy().subscribeWith(new DisposableSingleObserver<WhiteListPolicy>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.FFSWhiteListJobService.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(@NonNull Throwable th) {
                WJLog.d(FFSWhiteListJobService.TAG, "An error occurred during refresh WhiteListPolicy job", th);
                FFSWhiteListJobService.this.jobFinished(jobParameters, true);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(@NonNull WhiteListPolicy whiteListPolicy) {
                WJLog.d(FFSWhiteListJobService.TAG, "Successfully completed refresh WhiteListPolicy job");
                FFSWhiteListJobService.this.jobFinished(jobParameters, false);
            }
        });
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        WJLog.d(TAG, "onStopJob");
        Disposable disposable = this.mWhiteListRequestDisposable;
        if (disposable != null) {
            disposable.dispose();
            this.mWhiteListRequestDisposable = null;
            return true;
        }
        return true;
    }
}
