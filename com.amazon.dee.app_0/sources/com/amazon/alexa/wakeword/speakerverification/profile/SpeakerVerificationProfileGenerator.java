package com.amazon.alexa.wakeword.speakerverification.profile;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.davs.CheckSumUtils;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationAuthority;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationCallbacksWrapper;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationEngineCallbacks;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentUtterance;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.mlis.SpeakerVerificationMlisClient;
import com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority;
import com.amazon.alexa.wakeword.speakerverification.profile.retry.ProfileGenerationRetryScheduler;
import com.amazon.alexa.wakeword.speakerverification.pryon.EnrollmentModelProvider;
import com.amazon.alexa.wakeword.speakerverification.pryon.PryonEnroller;
import com.amazon.alexa.wakeword.speakerverification.pryon.SpeakerVerificationConfigProvider;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@RequiresApi(api = 23)
/* loaded from: classes11.dex */
public class SpeakerVerificationProfileGenerator implements SpeakerVerificationEngineCallbacks {
    private static final int PRYON_PROFILE_REGENERATION_SNR = -50;
    private static final String TAG = "SpeakerVerificationProfileGenerator";
    private boolean isSpeakerVerificationExampleRejected;
    private final Context mContext;
    private final EnrollmentModelProvider mEnrollmentModelProvider;
    private final ExecutorService mExecutorService;
    private final ProfileGenerationRetryScheduler mGenerationRetryScheduler;
    private ProfileGenerationListener mListener;
    private final SpeakerVerificationMetricsListener mMetricsListener;
    private final String mPersonId;
    private final PryonEnroller mPryonEnroller;
    private int mRetryQuotaId;
    private final SpeakerVerificationAuthority mSpeakerVerificationAuthority;
    private final SpeakerVerificationMlisClient mSpeakerVerificationMlisClient;
    private final SpeakerVerificationModelAuthority mSpeakerVerificationModelAuthority;
    private String mSpeakerVerificationModelId;

    public SpeakerVerificationProfileGenerator(@NonNull Context context, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener) {
        this.mRetryQuotaId = 0;
        this.mContext = context;
        this.mSpeakerVerificationAuthority = new SpeakerVerificationAuthority(context, speakerVerificationMetricsListener);
        this.mMetricsListener = speakerVerificationMetricsListener;
        this.mPryonEnroller = new PryonEnroller(new SpeakerVerificationCallbacksWrapper(this, speakerVerificationMetricsListener), speakerVerificationMetricsListener);
        this.mPersonId = this.mSpeakerVerificationAuthority.readPersonId();
        this.mSpeakerVerificationModelAuthority = new SpeakerVerificationModelAuthority(context);
        this.mEnrollmentModelProvider = new EnrollmentModelProvider(context);
        this.mSpeakerVerificationMlisClient = new SpeakerVerificationMlisClient(context);
        this.mExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.mGenerationRetryScheduler = new ProfileGenerationRetryScheduler();
    }

    private void onProfileGenerationFailure(@NonNull TrainingFailure trainingFailure, @NonNull String str) {
        this.mListener.onGenerationFail(trainingFailure);
        this.mMetricsListener.onProfileGenerationFailure(trainingFailure, str);
        this.mGenerationRetryScheduler.scheduleRetry(this.mContext, this.mRetryQuotaId);
    }

    private void onProfileGenerationSuccess(@NonNull String str) {
        Log.d(TAG, "Profile generation succeeded");
        this.mListener.onGenerationSuccess();
        this.mMetricsListener.onProfileGenerationSuccess(str);
    }

    public /* synthetic */ void lambda$onExampleAccepted$0$SpeakerVerificationProfileGenerator(EnrollmentUtterance enrollmentUtterance) {
        this.mSpeakerVerificationMlisClient.startMlisUpload(enrollmentUtterance);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationEngineCallbacks
    public void onExampleAccepted(@NonNull final EnrollmentUtterance enrollmentUtterance) {
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.alexa.wakeword.speakerverification.profile.-$$Lambda$SpeakerVerificationProfileGenerator$z-yz4PnIw00N1WToKkkJA1tEc78
            @Override // java.lang.Runnable
            public final void run() {
                SpeakerVerificationProfileGenerator.this.lambda$onExampleAccepted$0$SpeakerVerificationProfileGenerator(enrollmentUtterance);
            }
        });
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationEngineCallbacks
    public void onExampleRejected() {
        this.isSpeakerVerificationExampleRejected = true;
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationEngineCallbacks
    public void onProfileGenerated(@NonNull byte[] bArr) {
        if (!this.mSpeakerVerificationAuthority.saveSpeakerVerificationProfile(this.mPersonId, bArr)) {
            onProfileGenerationFailure(TrainingFailure.SAVE_PROFILE, this.mSpeakerVerificationModelId);
        } else if (this.mSpeakerVerificationModelAuthority.persistRegenerationModelAsClassification()) {
            this.mMetricsListener.onPersistModelSuccess(this.mSpeakerVerificationModelId);
            onProfileGenerationSuccess(this.mSpeakerVerificationModelId);
        } else {
            this.mMetricsListener.onPersistModelFailure(TrainingFailure.PERSIST_SPEAKER_VERIFICATION_MODEL, this.mSpeakerVerificationModelId);
            onProfileGenerationFailure(TrainingFailure.PERSIST_SPEAKER_VERIFICATION_MODEL, this.mSpeakerVerificationModelId);
        }
    }

    public void performProfileGeneration(@NonNull ProfileGenerationListener profileGenerationListener) {
        this.mListener = profileGenerationListener;
        this.isSpeakerVerificationExampleRejected = false;
        List<EnrollmentUtterance> enrollmentUtterances = this.mSpeakerVerificationAuthority.getEnrollmentUtterances(this.mPersonId);
        if (enrollmentUtterances != null && !enrollmentUtterances.isEmpty()) {
            byte[] speakerVerificationModel = this.mEnrollmentModelProvider.getSpeakerVerificationModel();
            this.mSpeakerVerificationModelId = CheckSumUtils.getMD5(speakerVerificationModel);
            if (!this.mPryonEnroller.initialize(this.mSpeakerVerificationAuthority.getProfileId(this.mPersonId), SpeakerVerificationConfigProvider.createPryonConfig(this.mEnrollmentModelProvider.getWakeWordModel(), speakerVerificationModel, enrollmentUtterances.size(), PRYON_PROFILE_REGENERATION_SNR))) {
                Log.e(TAG, "PryonEnroller initialization failed.");
                onProfileGenerationFailure(TrainingFailure.PRYON_INITIALIZATION, this.mSpeakerVerificationModelId);
                return;
            }
            Iterator<EnrollmentUtterance> it2 = enrollmentUtterances.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (!this.mPryonEnroller.pushExample(it2.next())) {
                    Log.e(TAG, "Push failed");
                    onProfileGenerationFailure(TrainingFailure.PUSH_FAILED, this.mSpeakerVerificationModelId);
                    break;
                } else if (this.isSpeakerVerificationExampleRejected) {
                    Log.e(TAG, "Example rejected");
                    onProfileGenerationFailure(TrainingFailure.EXAMPLE_REJECTED, this.mSpeakerVerificationModelId);
                    break;
                }
            }
            this.mPryonEnroller.release();
            return;
        }
        Log.e(TAG, "List of examples was null");
        onProfileGenerationFailure(TrainingFailure.GET_ENROLLMENT_UTTERANCES, "");
    }

    public void performProfileGenerationRetry(@NonNull ProfileGenerationListener profileGenerationListener, int i) {
        this.mRetryQuotaId = i;
        performProfileGeneration(profileGenerationListener);
    }

    @VisibleForTesting
    SpeakerVerificationProfileGenerator(@NonNull Context context, @NonNull SpeakerVerificationAuthority speakerVerificationAuthority, @NonNull PryonEnroller pryonEnroller, @NonNull String str, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull SpeakerVerificationModelAuthority speakerVerificationModelAuthority, @NonNull EnrollmentModelProvider enrollmentModelProvider, @NonNull SpeakerVerificationMlisClient speakerVerificationMlisClient, @NonNull ExecutorService executorService, @NonNull ProfileGenerationRetryScheduler profileGenerationRetryScheduler) {
        this.mRetryQuotaId = 0;
        this.mContext = context;
        this.mSpeakerVerificationAuthority = speakerVerificationAuthority;
        this.mPersonId = str;
        this.mMetricsListener = speakerVerificationMetricsListener;
        this.mPryonEnroller = pryonEnroller;
        this.mSpeakerVerificationModelAuthority = speakerVerificationModelAuthority;
        this.mEnrollmentModelProvider = enrollmentModelProvider;
        this.mSpeakerVerificationMlisClient = speakerVerificationMlisClient;
        this.mExecutorService = executorService;
        this.mGenerationRetryScheduler = profileGenerationRetryScheduler;
    }
}
