package com.amazon.alexa.wakeword.speakerverification.enrollment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.handsfree.protocols.utils.ArtifactDownloadStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.utils.PackageNameProvider;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.alexa.wakeword.AudioCapturerAuthority;
import com.amazon.alexa.wakeword.ClassificationData;
import com.amazon.alexa.wakeword.EnrollmentData;
import com.amazon.alexa.wakeword.InternalLocaleProvider;
import com.amazon.alexa.wakeword.RecordingTracker;
import com.amazon.alexa.wakeword.WakeWordData;
import com.amazon.alexa.wakeword.WakeWordDetectionController;
import com.amazon.alexa.wakeword.WakeWordDetectionListener;
import com.amazon.alexa.wakeword.WakeWordDetector;
import com.amazon.alexa.wakeword.davs.CheckSumUtils;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectorProvider;
import com.amazon.alexa.wakeword.pryon.WakeWordModelAuthority;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationAuthority;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationCallbacksWrapper;
import com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationEngineCallbacks;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentListener;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.mlis.UtterancesUploadToMlisReceiver;
import com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelAuthority;
import com.amazon.alexa.wakeword.speakerverification.pryon.EnrollmentModelProvider;
import com.amazon.alexa.wakeword.speakerverification.pryon.PryonEnroller;
import com.amazon.alexa.wakeword.speakerverification.pryon.SpeakerVerificationConfigProvider;
import com.amazon.pryon.android.asr.PryonLite5000;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes11.dex */
public class EnrollmentController implements WakeWordDetector.AudioCaptureListener, WakeWordDetectionListener, SpeakerVerificationEngineCallbacks {
    private static final int AUDIO_BUFFER_CAPACITY = 3000000;
    @VisibleForTesting
    static final String BLANK_MODEL_ID = "";
    private static final int MIN_ENROLLMENT_SNR = 5;
    private static final String TAG = "EnrollmentController";
    public static final String UPLOAD_UTTERANCES_RETRIES = "UploadRetries";
    public static final int UPLOAD_UTTERANCES_RETRIES_LIMIT = 10;
    private final ArtifactDownloadStateProvider mArtifactDownloadStateProvider;
    private final ShortBuffer mAudioBuffer;
    private final Context mContext;
    private AlexaAudioSink mCurrentAudioSink;
    private EnrollmentUtterance mCurrentUtterance;
    private final EnrollmentModelProvider mEnrollmentModelProvider;
    private final ExecutorService mExecutorService;
    private boolean mIsInitialized;
    private int mNumUtterances;
    private String mPersonId;
    private final PersonIdProvider mPersonIdProvider;
    private byte[] mProfile;
    private PryonLite5000.Config mPryonConfig;
    private final PryonEnroller mPryonEnroller;
    private final SpeakerVerificationAuthority mSpeakerVerificationAuthority;
    private final SpeakerVerificationMetricsListener mSpeakerVerificationMetricsListener;
    private final SpeakerVerificationModelAuthority mSpeakerVerificationModelAuthority;
    private final EnrollmentListener.UtteranceTrainingCallback mUtteranceTrainingCallback;
    private final List<EnrollmentUtterance> mUtterances;
    private final WakeWordDetectionController mWakeWordDetectionController;
    private final WakeWordDetectionMetricsListener mWakeWordDetectionMetricsListener;

    public EnrollmentController(@NonNull Context context, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, @NonNull EnrollmentListener.UtteranceTrainingCallback utteranceTrainingCallback) {
        this.mUtterances = new ArrayList();
        this.mWakeWordDetectionController = createWakeWordDetectionController(context, alexaServicesConnection, wakeWordDetectionMetricsListener);
        this.mPersonIdProvider = (PersonIdProvider) GeneratedOutlineSupport1.outline21(PersonIdProvider.class);
        this.mSpeakerVerificationAuthority = new SpeakerVerificationAuthority(context, speakerVerificationMetricsListener);
        this.mPryonEnroller = new PryonEnroller(new SpeakerVerificationCallbacksWrapper(this, speakerVerificationMetricsListener), speakerVerificationMetricsListener);
        this.mExecutorService = Executors.newSingleThreadExecutor();
        this.mUtteranceTrainingCallback = utteranceTrainingCallback;
        this.mWakeWordDetectionMetricsListener = wakeWordDetectionMetricsListener;
        this.mEnrollmentModelProvider = new EnrollmentModelProvider(context);
        this.mSpeakerVerificationModelAuthority = new SpeakerVerificationModelAuthority(context);
        this.mContext = context;
        this.mSpeakerVerificationMetricsListener = speakerVerificationMetricsListener;
        this.mArtifactDownloadStateProvider = new ArtifactDownloadStateProvider(context);
        this.mAudioBuffer = ShortBuffer.allocate(AUDIO_BUFFER_CAPACITY);
    }

    private WakeWordData createWakeWordData(@NonNull AlexaAudioSink alexaAudioSink, @NonNull EnrollmentUtterance enrollmentUtterance) {
        return new WakeWordData(alexaAudioSink, new AlexaWakeWord(enrollmentUtterance.getWakeWord(), enrollmentUtterance.getStartIndex(), enrollmentUtterance.getEndIndex()), enrollmentUtterance.getMetadata());
    }

    private WakeWordDetectionController createWakeWordDetectionController(@NonNull final Context context, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener) {
        NullWakeWordDownloadManager nullWakeWordDownloadManager = new NullWakeWordDownloadManager();
        ContentResolver contentResolver = context.getContentResolver();
        context.getClass();
        WakeWordDetectionController wakeWordDetectionController = new WakeWordDetectionController(context, AudioCapturerAuthority.create(new RecordingTracker(), new WakeWordDetectorProvider(new WakeWordModelAuthority(new WakeWordModelContentProviderHelper(contentResolver, new PackageNameProvider() { // from class: com.amazon.alexa.wakeword.speakerverification.enrollment.-$$Lambda$g98y9_mpCLTbujPw9Zhl_r--dmA
            @Override // com.amazon.alexa.utils.PackageNameProvider
            public final String getPackageName() {
                return context.getPackageName();
            }
        }), wakeWordDetectionMetricsListener, nullWakeWordDownloadManager, AlexaHandsFreeDeviceInformation.isCurrentDeviceHandsFree(context)), new InternalLocaleProvider(alexaServicesConnection), new TimeProvider(), new Provider() { // from class: com.amazon.alexa.wakeword.speakerverification.enrollment.-$$Lambda$EnrollmentController$ziNojGN0W-AKoiCva4U4ttX7pDs
            @Override // com.amazon.alexa.utils.Provider
            /* renamed from: get */
            public final Object mo2864get() {
                return EnrollmentController.this.lambda$createWakeWordDetectionController$3$EnrollmentController();
            }
        }, wakeWordDetectionMetricsListener, nullWakeWordDownloadManager)));
        wakeWordDetectionController.setWakeWordDetectionListener(this);
        wakeWordDetectionController.setAudioCaptureListener(this);
        return wakeWordDetectionController;
    }

    private void pushExample(@NonNull final EnrollmentUtterance enrollmentUtterance) {
        Log.d(TAG, "pushExample: Attempting to execute...");
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.alexa.wakeword.speakerverification.enrollment.-$$Lambda$EnrollmentController$vos15wD6E8_TEj8HaUuV6AcfuJU
            @Override // java.lang.Runnable
            public final void run() {
                EnrollmentController.this.lambda$pushExample$2$EnrollmentController(enrollmentUtterance);
            }
        });
    }

    public long getAverageVolume() {
        long j = 0;
        for (int i = 0; i < this.mAudioBuffer.position(); i++) {
            short s = this.mAudioBuffer.get(i);
            j += s * s;
        }
        return Math.round(Math.log10(j / this.mAudioBuffer.position()) * 10.0d);
    }

    public String getSpeakerVerificationModelId() {
        PryonLite5000.Config config = this.mPryonConfig;
        return config == null ? "" : CheckSumUtils.getMD5(config.speakerVerificationModel);
    }

    public String getWakeWordModelId() {
        PryonLite5000.Config config = this.mPryonConfig;
        return config == null ? "" : CheckSumUtils.getMD5(config.wakewordModel);
    }

    public void initialize(final int i, boolean z, @NonNull final EnrollmentListener.ResponseCallback responseCallback) {
        Log.d(TAG, "initialize");
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.alexa.wakeword.speakerverification.enrollment.-$$Lambda$EnrollmentController$r2t1J5TJqD31K3864Xsoakg8I9I
            @Override // java.lang.Runnable
            public final void run() {
                EnrollmentController.this.lambda$initialize$0$EnrollmentController(i, responseCallback);
            }
        });
    }

    public boolean isEnrollmentComplete() {
        return this.mProfile != null;
    }

    public /* synthetic */ PryonLite5000.Config lambda$createWakeWordDetectionController$3$EnrollmentController() {
        return this.mPryonConfig;
    }

    public /* synthetic */ void lambda$initialize$0$EnrollmentController(int i, EnrollmentListener.ResponseCallback responseCallback) {
        if (this.mIsInitialized) {
            Log.w(TAG, "Initialize being called without releasing previous resources. Cleaning up first.");
            release();
        }
        this.mPersonId = this.mPersonIdProvider.getPersonId();
        this.mNumUtterances = i;
        if (this.mPersonId == null) {
            responseCallback.onFailure(TrainingFailure.INVALID_PERSON_ID);
            return;
        }
        ArtifactDownloadStateProvider.DownloadState downloadState = this.mArtifactDownloadStateProvider.getDownloadState(ArtifactDownloadStateProvider.ArtifactType.WAKE_WORD_MODEL);
        ArtifactDownloadStateProvider.DownloadStarter downloadStarter = this.mArtifactDownloadStateProvider.getDownloadStarter(ArtifactDownloadStateProvider.ArtifactType.WAKE_WORD_MODEL);
        if (downloadState == ArtifactDownloadStateProvider.DownloadState.SUCCESS) {
            Log.d(TAG, String.format("Downloaded WW Model is available before enrollment. Download starter and state: %s -> %s", downloadStarter, downloadState));
            this.mSpeakerVerificationMetricsListener.onWakeWordModelAvailable(downloadStarter);
        } else {
            Log.w(TAG, String.format("Backup WW model used for enrollment. Download starter and state: %s -> %s", downloadStarter, downloadState));
            this.mSpeakerVerificationMetricsListener.onWakeWordModelUnavailable(downloadStarter, downloadState);
        }
        byte[] profileId = this.mSpeakerVerificationAuthority.getProfileId(this.mPersonId);
        this.mPryonConfig = SpeakerVerificationConfigProvider.createPryonConfig(this.mEnrollmentModelProvider.getWakeWordModel(), this.mEnrollmentModelProvider.getSpeakerVerificationModel(), this.mNumUtterances, 5);
        if (this.mPryonEnroller.initialize(profileId, this.mPryonConfig)) {
            this.mIsInitialized = true;
            responseCallback.onSuccess();
            return;
        }
        responseCallback.onFailure(TrainingFailure.PRYON_INITIALIZATION);
    }

    public /* synthetic */ void lambda$persistData$1$EnrollmentController(EnrollmentListener.ResponseCallback responseCallback) {
        if (!this.mSpeakerVerificationAuthority.saveSpeakerVerificationProfile(this.mPersonId, this.mProfile)) {
            Log.e(TAG, "Unable to save profile to disk.");
            responseCallback.onFailure(TrainingFailure.SAVE_PROFILE);
        } else if (!this.mSpeakerVerificationAuthority.saveEnrollmentUtterances(this.mPersonId, this.mUtterances)) {
            Log.e(TAG, "Failed to persist utterances. Proceeding to delete user data.");
            this.mSpeakerVerificationAuthority.deleteUserData(this.mPersonId);
            responseCallback.onFailure(TrainingFailure.SAVE_ENROLLMENT_UTTERANCES);
        } else if (!this.mSpeakerVerificationModelAuthority.persistRegenerationModelAsClassification()) {
            responseCallback.onFailure(TrainingFailure.PERSIST_SPEAKER_VERIFICATION_MODEL);
        } else {
            responseCallback.onSuccess();
            Log.i(TAG, "onProfileGenerated: check network and schedule utterances upload to Mlis.");
            scheduleJobUploadUtterancesToMlis();
        }
    }

    public /* synthetic */ void lambda$pushExample$2$EnrollmentController(EnrollmentUtterance enrollmentUtterance) {
        Log.d(TAG, "Pushing wake word to PryonLite engine...");
        if (!this.mPryonEnroller.pushExample(enrollmentUtterance)) {
            Log.e(TAG, "pushExample failed");
            stopRecording();
            this.mUtteranceTrainingCallback.onUtteranceTrainingFailure(TrainingFailure.PUSH_FAILED);
        }
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector.AudioCaptureListener
    public void onAudioCaptured(short[] sArr) {
        Log.d(TAG, "onAudioCaptured");
        this.mUtteranceTrainingCallback.onAudioCaptured(sArr);
        try {
            this.mAudioBuffer.put(sArr, 0, sArr.length);
        } catch (IndexOutOfBoundsException | BufferOverflowException unused) {
            Log.e(TAG, "Reaches limit of buffer capacity or audio is null.");
        }
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector.WakeWordDetectionListener
    public void onClassificationEvent(@NonNull ClassificationData classificationData) {
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector.WakeWordDetectionListener
    public void onEnrollmentExampleEvent(@NonNull EnrollmentData enrollmentData) {
        Log.d(TAG, "onEnrollmentExampleEvent");
        AlexaWakeWord alexaWakeWord = enrollmentData.getAlexaWakeWord();
        this.mCurrentUtterance = new EnrollmentUtterance(alexaWakeWord.getWakeWordName(), (int) alexaWakeWord.getStartIndexInSamples(), (int) alexaWakeWord.getEndIndexInSamples(), enrollmentData.getExample(), enrollmentData.getMetadata());
        if (!isEnrollmentComplete()) {
            pushExample(this.mCurrentUtterance);
        }
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationEngineCallbacks
    public void onExampleAccepted(@NonNull EnrollmentUtterance enrollmentUtterance) {
        this.mUtterances.add(enrollmentUtterance);
        if (this.mCurrentAudioSink != null) {
            Log.d(TAG, "onUtteranceTrainingSuccess");
            this.mSpeakerVerificationMetricsListener.onUtteranceTrainedSuccess();
            this.mUtteranceTrainingCallback.onUtteranceTrainingSuccess(createWakeWordData(this.mCurrentAudioSink, enrollmentUtterance));
            return;
        }
        Log.d(TAG, "onUtteranceTrainingFailure - audio sink was null");
        this.mSpeakerVerificationMetricsListener.onUtteranceTrainedFailure(TrainingFailure.EXAMPLE_ACCEPTED_BEFORE_WAKE_WORD_DETECTED);
        this.mUtteranceTrainingCallback.onUtteranceTrainingFailure(TrainingFailure.EXAMPLE_ACCEPTED_BEFORE_WAKE_WORD_DETECTED);
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationEngineCallbacks
    public void onExampleRejected() {
        Log.d(TAG, "onExampleRejected");
        this.mUtteranceTrainingCallback.onUtteranceTrainingFailure(TrainingFailure.EXAMPLE_REJECTED);
        stopRecording();
    }

    @Override // com.amazon.alexa.wakeword.speakerverification.SpeakerVerificationEngineCallbacks
    public void onProfileGenerated(@NonNull byte[] bArr) {
        Log.d(TAG, "onProfileGenerated");
        this.mProfile = bArr;
    }

    @Override // com.amazon.alexa.wakeword.WakeWordDetector.WakeWordDetectionListener
    public void onWakewordDetected(@NonNull WakeWordData wakeWordData) {
        Log.d(TAG, "onWakewordDetected");
        this.mCurrentAudioSink = wakeWordData.getAudioSink();
        this.mUtteranceTrainingCallback.onWakeWordDetected(createWakeWordData(this.mCurrentAudioSink, this.mCurrentUtterance), getWakeWordModelId(), getSpeakerVerificationModelId(), getAverageVolume());
    }

    public void persistData(@NonNull final EnrollmentListener.ResponseCallback responseCallback) {
        Log.d(TAG, "persistData");
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.alexa.wakeword.speakerverification.enrollment.-$$Lambda$EnrollmentController$dbQisr5mVjC_GdWOl4_K3H0zNuw
            @Override // java.lang.Runnable
            public final void run() {
                EnrollmentController.this.lambda$persistData$1$EnrollmentController(responseCallback);
            }
        });
    }

    public void release() {
        Log.d(TAG, "release");
        ExecutorService executorService = this.mExecutorService;
        final PryonEnroller pryonEnroller = this.mPryonEnroller;
        pryonEnroller.getClass();
        executorService.execute(new Runnable() { // from class: com.amazon.alexa.wakeword.speakerverification.enrollment.-$$Lambda$UgAO1FjuhRV3jE-O693bcO-PASY
            @Override // java.lang.Runnable
            public final void run() {
                PryonEnroller.this.release();
            }
        });
        stopRecording();
        this.mProfile = null;
        this.mIsInitialized = false;
        this.mUtterances.clear();
        this.mCurrentUtterance = null;
        this.mCurrentAudioSink = null;
    }

    @VisibleForTesting
    public void scheduleJobUploadUtterancesToMlis() {
        Log.i(TAG, "scheduling utterances upload to MLIS task.");
        Intent intent = new Intent(this.mContext, UtterancesUploadToMlisReceiver.class);
        intent.putExtra(UPLOAD_UTTERANCES_RETRIES, 10);
        this.mContext.sendBroadcast(intent);
    }

    @VisibleForTesting
    protected void setCurrentAudioSink(AlexaAudioSink alexaAudioSink) {
        this.mCurrentAudioSink = alexaAudioSink;
    }

    public void startUtteranceTraining() {
        if (!this.mIsInitialized) {
            return;
        }
        Log.d(TAG, "startUtteranceTraining");
        this.mAudioBuffer.clear();
        try {
            this.mWakeWordDetectionController.stopCapturing();
            this.mWakeWordDetectionController.startDetectingWakeWord(this.mWakeWordDetectionMetricsListener);
        } catch (IOException e) {
            Log.d(TAG, "IOException", e);
            this.mUtteranceTrainingCallback.onUtteranceTrainingFailure(TrainingFailure.WAKE_WORD_DETECTION_IO);
        }
    }

    public void stopRecording() {
        Log.d(TAG, "stopRecording");
        this.mWakeWordDetectionController.stopCapturing();
    }

    @VisibleForTesting
    EnrollmentController(@NonNull WakeWordDetectionController wakeWordDetectionController, @NonNull PersonIdProvider personIdProvider, @NonNull SpeakerVerificationAuthority speakerVerificationAuthority, @NonNull PryonEnroller pryonEnroller, @NonNull ExecutorService executorService, @NonNull EnrollmentListener.UtteranceTrainingCallback utteranceTrainingCallback, @NonNull WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, @NonNull EnrollmentModelProvider enrollmentModelProvider, @NonNull SpeakerVerificationModelAuthority speakerVerificationModelAuthority, @NonNull Context context, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull ArtifactDownloadStateProvider artifactDownloadStateProvider, @NonNull ShortBuffer shortBuffer) {
        this.mUtterances = new ArrayList();
        this.mWakeWordDetectionController = wakeWordDetectionController;
        this.mPersonIdProvider = personIdProvider;
        this.mSpeakerVerificationAuthority = speakerVerificationAuthority;
        this.mPryonEnroller = pryonEnroller;
        this.mExecutorService = executorService;
        this.mUtteranceTrainingCallback = utteranceTrainingCallback;
        this.mEnrollmentModelProvider = enrollmentModelProvider;
        this.mWakeWordDetectionMetricsListener = wakeWordDetectionMetricsListener;
        this.mSpeakerVerificationModelAuthority = speakerVerificationModelAuthority;
        this.mContext = context;
        this.mSpeakerVerificationMetricsListener = speakerVerificationMetricsListener;
        this.mArtifactDownloadStateProvider = artifactDownloadStateProvider;
        this.mAudioBuffer = shortBuffer;
    }
}
