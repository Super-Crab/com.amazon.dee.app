package com.amazon.alexa.wakeword.speakerverification.model;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.amazon.alexa.wakeword.davs.CheckSumUtils;
import com.amazon.alexa.wakeword.speakerverification.enrollment.IdentityServiceTokenProvider;
import com.amazon.alexa.wakeword.speakerverification.errors.ModelDownloadReason;
import com.amazon.alexa.wakeword.speakerverification.errors.StartProfileGenerationFailure;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsReporter;
import com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelDownloadJobIntentService;
import com.amazon.alexa.wakeword.speakerverification.profile.ProfileGenerationListener;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileGenerator;
import com.amazon.alexa.wakeword.speakerverification.pryon.EnrollmentModelProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes11.dex */
public class SpeakerVerificationModelDownloadJobIntentService extends SafeDequeueJobIntentService {
    private static final int JOB_ID = 32049;
    private static final String MODEL_DOWNLOAD_CHECK_TIME = "modelDownloadCheckTime";
    private static final long MODEL_UPDATE_TIMEOUT_MS = 3600000;
    private static final String PREFERENCE_FILE_NAME = "com.amazon.alexa.handsfree.uservoicerecognition.edgesv.modeldownload.ModelDownload";
    private static final String TAG = SpeakerVerificationModelDownloadJobIntentService.class.getSimpleName();
    private Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final Initializer mInitializer;
    private final SpeakerVerificationModelDownloadScheduler mModelDownloadScheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelDownloadJobIntentService$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public class AnonymousClass1 implements ResultCallback<Boolean> {
        final /* synthetic */ IdentityService val$identityService;
        final /* synthetic */ SpeakerVerificationMetricsReporter val$speakerVerificationMetricsReporter;

        AnonymousClass1(SpeakerVerificationMetricsReporter speakerVerificationMetricsReporter, IdentityService identityService) {
            this.val$speakerVerificationMetricsReporter = speakerVerificationMetricsReporter;
            this.val$identityService = identityService;
        }

        public /* synthetic */ void lambda$onResult$0$SpeakerVerificationModelDownloadJobIntentService$1(SpeakerVerificationMetricsReporter speakerVerificationMetricsReporter, ArtifactModel artifactModel) {
            if (artifactModel == null) {
                Log.i(SpeakerVerificationModelDownloadJobIntentService.TAG, "SV model already exists, or unsuccessful download");
                return;
            }
            Log.i(SpeakerVerificationModelDownloadJobIntentService.TAG, String.format("SV model downloaded successfully, model id: %s", artifactModel.getArtifactIdentifier()));
            EnrollmentModelProvider enrollmentModelProvider = SpeakerVerificationModelDownloadJobIntentService.this.getEnrollmentModelProvider();
            SpeakerVerificationModelDownloadJobIntentService speakerVerificationModelDownloadJobIntentService = SpeakerVerificationModelDownloadJobIntentService.this;
            speakerVerificationModelDownloadJobIntentService.regenerateSpeakerVerificationProfile(speakerVerificationModelDownloadJobIntentService, speakerVerificationMetricsReporter, enrollmentModelProvider);
        }

        @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
        public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
            String str = SpeakerVerificationModelDownloadJobIntentService.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Hands free state could not be determined: ");
            outline107.append(callbackErrorMetadata.getErrorMessage());
            Log.e(str, outline107.toString());
            this.val$speakerVerificationMetricsReporter.onModelDownloadFailure(ModelDownloadReason.HANDS_FREE_STATE_EXCEPTION, null);
        }

        @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
        public void onResult(@NonNull Boolean bool) {
            if (!bool.booleanValue()) {
                Log.i(SpeakerVerificationModelDownloadJobIntentService.TAG, "Hands free is disabled");
                this.val$speakerVerificationMetricsReporter.onProfileGenerationHandsFreeDisabled();
                return;
            }
            SpeakerVerificationModelAuthority speakerVerificationModelAuthority = SpeakerVerificationModelDownloadJobIntentService.this.getSpeakerVerificationModelAuthority(SpeakerVerificationModelDownloadJobIntentService.this.getIdentityServiceTokenProvider(this.val$identityService));
            final SpeakerVerificationMetricsReporter speakerVerificationMetricsReporter = this.val$speakerVerificationMetricsReporter;
            speakerVerificationModelAuthority.downloadSpeakerVerificationModelAsync(new SpeakerVerificationRegenerationModelCallable() { // from class: com.amazon.alexa.wakeword.speakerverification.model.-$$Lambda$SpeakerVerificationModelDownloadJobIntentService$1$yb7IUZU2YIqQ08CcjMXoOqFvRwU
                @Override // com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationRegenerationModelCallable
                public final void regenerationModelCall(ArtifactModel artifactModel) {
                    SpeakerVerificationModelDownloadJobIntentService.AnonymousClass1.this.lambda$onResult$0$SpeakerVerificationModelDownloadJobIntentService$1(speakerVerificationMetricsReporter, artifactModel);
                }
            });
        }
    }

    public SpeakerVerificationModelDownloadJobIntentService() {
        this.mInitializer = InitializerProvider.getInitializer();
        this.mModelDownloadScheduler = new SpeakerVerificationModelDownloadScheduler();
    }

    public static void enqueueWork(@NonNull Context context) {
        JobIntentService.enqueueWork(context, SpeakerVerificationModelDownloadJobIntentService.class, (int) JOB_ID, new Intent());
    }

    @VisibleForTesting
    ComponentRegistry getComponentRegistry() {
        return ComponentRegistry.getInstance();
    }

    @VisibleForTesting
    EnrollmentModelProvider getEnrollmentModelProvider() {
        return new EnrollmentModelProvider(this);
    }

    @VisibleForTesting
    IdentityServiceTokenProvider getIdentityServiceTokenProvider(IdentityService identityService) {
        return new IdentityServiceTokenProvider(identityService);
    }

    @VisibleForTesting
    SpeakerVerificationMetricsReporter getSpeakerVerificationMetricsReporter() {
        return new SpeakerVerificationMetricsReporter(this, SpeakerVerificationMetricsReporter.MetricsSource.MODEL_DOWNLOAD);
    }

    @VisibleForTesting
    SpeakerVerificationModelAuthority getSpeakerVerificationModelAuthority(IdentityServiceTokenProvider identityServiceTokenProvider) {
        return new SpeakerVerificationModelAuthority(this, identityServiceTokenProvider);
    }

    @VisibleForTesting
    WakeWordSettingsManager getWakeWordSettingsManager() {
        return WakeWordSettingsManagerProvider.getInstance().get();
    }

    @VisibleForTesting
    boolean isModelReadyForUpdate(@NonNull Context context) {
        boolean z = false;
        if (SystemClock.elapsedRealtime() - context.getSharedPreferences(PREFERENCE_FILE_NAME, 0).getLong(MODEL_DOWNLOAD_CHECK_TIME, 0L) > 3600000) {
            z = true;
        }
        String str = TAG;
        Log.i(str, "isModelReadyForUpdate: " + z);
        return z;
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInitializer.initialize(this);
        this.mEnrollmentTypeResolverLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(getApplicationContext(), FalcoProtocolComponent.class)).enrollmentTypeResolverLazy();
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        onHandleWork();
    }

    @VisibleForTesting
    void regenerateSpeakerVerificationProfile(@NonNull Context context, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull EnrollmentModelProvider enrollmentModelProvider) {
        String md5 = CheckSumUtils.getMD5(enrollmentModelProvider.getSpeakerVerificationModel());
        if (md5.equals("")) {
            speakerVerificationMetricsListener.onStartProfileGenerationFailure(StartProfileGenerationFailure.MODELS_NULL, EnrollmentModelProvider.NULL_MODEL_ID);
            Log.i(TAG, "SV Model Id is null");
            return;
        }
        speakerVerificationMetricsListener.onStartProfileGenerationSuccess(md5);
        new SpeakerVerificationProfileGenerator(context, speakerVerificationMetricsListener).performProfileGeneration(new ProfileGenerationListener() { // from class: com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelDownloadJobIntentService.2
            @Override // com.amazon.alexa.wakeword.speakerverification.profile.ProfileGenerationListener
            public void onGenerationFail(@NonNull TrainingFailure trainingFailure) {
                Log.i(SpeakerVerificationModelDownloadJobIntentService.TAG, "onGenerationFail");
            }

            @Override // com.amazon.alexa.wakeword.speakerverification.profile.ProfileGenerationListener
            public void onGenerationSuccess() {
                Log.i(SpeakerVerificationModelDownloadJobIntentService.TAG, "onGenerationSuccess");
            }
        });
    }

    @VisibleForTesting
    void updateModelDownloadCheckTime(@NonNull Context context) {
        context.getSharedPreferences(PREFERENCE_FILE_NAME, 0).edit().putLong(MODEL_DOWNLOAD_CHECK_TIME, SystemClock.elapsedRealtime()).apply();
    }

    @VisibleForTesting
    void onHandleWork() {
        Log.i(TAG, String.format("onStartJob for job id: %d", Integer.valueOf((int) JOB_ID)));
        if (this.mEnrollmentTypeResolverLazy.mo358get().getSpeakerVerificationEnrollmentType() != EnrollmentType._1PSV) {
            Log.i(TAG, "SV Model download shouldn't start for 3PSV");
        } else if (!isModelReadyForUpdate(this)) {
            Log.i(TAG, "SV Model download isn't ready for update. Not enough time has passed");
        } else {
            updateModelDownloadCheckTime(this);
            this.mModelDownloadScheduler.scheduleModelDownload(this);
            SpeakerVerificationMetricsReporter speakerVerificationMetricsReporter = getSpeakerVerificationMetricsReporter();
            speakerVerificationMetricsReporter.onModelDownloadInitiated();
            IdentityService identityService = (IdentityService) getComponentRegistry().get(IdentityService.class).orNull();
            if (identityService == null) {
                Log.e(TAG, "Identity service cannot be retrieved.");
                speakerVerificationMetricsReporter.onModelDownloadFailure(ModelDownloadReason.IDENTITY_SERVICE_NOT_AVAILABLE, null);
            } else if (!identityService.isAuthenticated(TAG)) {
                Log.i(TAG, "User is not authenticated.");
                speakerVerificationMetricsReporter.onModelDownloadFailure(ModelDownloadReason.USER_NOT_AUTHENTICATED, null);
            } else {
                UserIdentity user = identityService.getUser(TAG);
                if (user == null) {
                    Log.i(TAG, "User is not logged in");
                    speakerVerificationMetricsReporter.onModelDownloadFailure(ModelDownloadReason.USER_NOT_LOGGED_IN, null);
                } else if (!user.hasAcceptedEula()) {
                    Log.i(TAG, "User hasn't accepted the EULA.");
                    speakerVerificationMetricsReporter.onModelDownloadFailure(ModelDownloadReason.USER_NOT_ACCEPTED_EULA, null);
                } else {
                    Log.i(TAG, "User is logged in.");
                    getWakeWordSettingsManager().checkHandsFreeState(new AnonymousClass1(speakerVerificationMetricsReporter, identityService));
                }
            }
        }
    }

    @VisibleForTesting
    SpeakerVerificationModelDownloadJobIntentService(@NonNull Initializer initializer, @NonNull SpeakerVerificationModelDownloadScheduler speakerVerificationModelDownloadScheduler, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mInitializer = initializer;
        this.mModelDownloadScheduler = speakerVerificationModelDownloadScheduler;
        this.mEnrollmentTypeResolverLazy = lazy;
    }
}
