package com.amazon.alexa.wakeword.speakerverification.profile.appupdate;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.davs.CheckSumUtils;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationAuthority;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsReporter;
import com.amazon.alexa.wakeword.speakerverification.model.ModelType;
import com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority;
import com.amazon.alexa.wakeword.speakerverification.profile.ProfileGenerationListener;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileGenerator;
import com.amazon.alexa.wakeword.speakerverification.pryon.EnrollmentModelProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class AppUpdateService extends SafeDequeueJobIntentService {
    private static final int JOB_ID = 32048;
    private static final String TAG = AppUpdateService.class.getSimpleName();
    private EnrollmentModelProvider mEnrollmentModelProvider;
    private SpeakerVerificationMetricsListener mMetricsListener;
    private SpeakerVerificationModelAuthority mModelAuthority;
    private SpeakerVerificationProfileGenerator mProfileGenerator;
    private SpeakerVerificationAuthority mSpeakerVerificationAuthority;

    public AppUpdateService() {
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, AppUpdateService.class, (int) JOB_ID, intent);
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        InitializerProvider.getInitializer().initialize(this);
        this.mMetricsListener = new SpeakerVerificationMetricsReporter(this, SpeakerVerificationMetricsReporter.MetricsSource.APP_UPDATE);
        this.mEnrollmentModelProvider = new EnrollmentModelProvider(this);
        this.mProfileGenerator = new SpeakerVerificationProfileGenerator(this, this.mMetricsListener);
        this.mModelAuthority = new SpeakerVerificationModelAuthority(this);
        this.mSpeakerVerificationAuthority = new SpeakerVerificationAuthority(this, this.mMetricsListener);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        Log.i(TAG, "Application was updated");
        if (!this.mSpeakerVerificationAuthority.hasProfile()) {
            Log.d(TAG, "There is no need to regenerate SV profile as there is no profile.");
        } else if (this.mModelAuthority.engineCompatibilityIdMatches(ModelType.CLASSIFICATION)) {
            Log.i(TAG, "Don't need to regenerate SV profile. Classification model is for the same engine.");
            this.mMetricsListener.onProfileGenerationEngineSame();
        } else {
            this.mMetricsListener.onProfileGenerationEngineUpdate();
            regenerateProfile();
        }
    }

    @VisibleForTesting
    void regenerateProfile() {
        Log.i(TAG, "Regenerate SV profile. Classification model is for the old engine.");
        this.mMetricsListener.onStartProfileGenerationSuccess(CheckSumUtils.getMD5(this.mEnrollmentModelProvider.getSpeakerVerificationModel()));
        this.mProfileGenerator.performProfileGeneration(new ProfileGenerationListener() { // from class: com.amazon.alexa.wakeword.speakerverification.profile.appupdate.AppUpdateService.1
            @Override // com.amazon.alexa.wakeword.speakerverification.profile.ProfileGenerationListener
            public void onGenerationFail(@NonNull TrainingFailure trainingFailure) {
                String str = AppUpdateService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onGenerationFail: ");
                outline107.append(trainingFailure.name());
                Log.i(str, outline107.toString());
            }

            @Override // com.amazon.alexa.wakeword.speakerverification.profile.ProfileGenerationListener
            public void onGenerationSuccess() {
                Log.i(AppUpdateService.TAG, "onGenerationSuccess");
            }
        });
    }

    @VisibleForTesting
    AppUpdateService(@NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull EnrollmentModelProvider enrollmentModelProvider, @NonNull SpeakerVerificationProfileGenerator speakerVerificationProfileGenerator, @NonNull SpeakerVerificationModelAuthority speakerVerificationModelAuthority, @NonNull SpeakerVerificationAuthority speakerVerificationAuthority) {
        this.mMetricsListener = speakerVerificationMetricsListener;
        this.mEnrollmentModelProvider = enrollmentModelProvider;
        this.mProfileGenerator = speakerVerificationProfileGenerator;
        this.mModelAuthority = speakerVerificationModelAuthority;
        this.mSpeakerVerificationAuthority = speakerVerificationAuthority;
    }
}
