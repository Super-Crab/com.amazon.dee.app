package com.amazon.alexa.wakeword.speakerverification.profile.retry;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.davs.CheckSumUtils;
import com.amazon.alexa.wakeword.speakerverification.errors.StartProfileGenerationFailure;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsReporter;
import com.amazon.alexa.wakeword.speakerverification.profile.ProfileGenerationListener;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileGenerator;
import com.amazon.alexa.wakeword.speakerverification.pryon.EnrollmentModelProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class ProfileGenerationRetryJobIntentService extends SafeDequeueJobIntentService {
    private static final int JOB_ID = 32047;
    private static final String TAG = ProfileGenerationRetryJobIntentService.class.getSimpleName();
    private EnrollmentModelProvider mEnrollmentModelProvider;
    private final Initializer mInitializer;
    private SpeakerVerificationMetricsListener mMetricsListener;
    private SpeakerVerificationProfileGenerator mProfileGenerator;

    public ProfileGenerationRetryJobIntentService() {
        this.mInitializer = InitializerProvider.getInitializer();
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, ProfileGenerationRetryJobIntentService.class, (int) JOB_ID, intent);
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInitializer.initialize(this);
        this.mMetricsListener = new SpeakerVerificationMetricsReporter(this, SpeakerVerificationMetricsReporter.MetricsSource.RETRY);
        this.mProfileGenerator = new SpeakerVerificationProfileGenerator(this, this.mMetricsListener);
        this.mEnrollmentModelProvider = new EnrollmentModelProvider(this);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        Log.i(TAG, "Retry to generate profile");
        String md5 = CheckSumUtils.getMD5(this.mEnrollmentModelProvider.getSpeakerVerificationModel());
        if (md5.equals("")) {
            this.mMetricsListener.onStartProfileGenerationFailure(StartProfileGenerationFailure.MODELS_NULL, EnrollmentModelProvider.NULL_MODEL_ID);
            Log.i(TAG, "SV Model Id is null");
        } else if (intent.getExtras() == null) {
            this.mMetricsListener.onStartProfileGenerationFailure(StartProfileGenerationFailure.QUOTA_NULL, md5);
            Log.i(TAG, "No intent extra");
        } else {
            this.mMetricsListener.onStartProfileGenerationSuccess(md5);
            this.mProfileGenerator.performProfileGenerationRetry(new ProfileGenerationListener() { // from class: com.amazon.alexa.wakeword.speakerverification.profile.retry.ProfileGenerationRetryJobIntentService.1
                @Override // com.amazon.alexa.wakeword.speakerverification.profile.ProfileGenerationListener
                public void onGenerationFail(@NonNull TrainingFailure trainingFailure) {
                    String str = ProfileGenerationRetryJobIntentService.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onGenerationFail ");
                    outline107.append(trainingFailure.name());
                    Log.e(str, outline107.toString());
                }

                @Override // com.amazon.alexa.wakeword.speakerverification.profile.ProfileGenerationListener
                public void onGenerationSuccess() {
                    Log.i(ProfileGenerationRetryJobIntentService.TAG, "onGenerationSuccess");
                }
            }, intent.getExtras().getInt("ExtraQuotaId", 0) + 1);
        }
    }

    @VisibleForTesting
    ProfileGenerationRetryJobIntentService(@NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull SpeakerVerificationProfileGenerator speakerVerificationProfileGenerator, @NonNull EnrollmentModelProvider enrollmentModelProvider) {
        this();
        this.mMetricsListener = speakerVerificationMetricsListener;
        this.mProfileGenerator = speakerVerificationProfileGenerator;
        this.mEnrollmentModelProvider = enrollmentModelProvider;
    }
}
