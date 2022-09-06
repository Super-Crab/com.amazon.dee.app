package com.amazon.alexa.enrollment.unified.speakerid;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaVisualTask;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.compat.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.compat.SupportedInitiationType;
import com.amazon.alexa.enrollment.unified.api.EnrollmentProvider;
import com.amazon.alexa.enrollment.unified.api.SpeechletTimeoutListener;
import com.amazon.alexa.enrollment.unified.api.StopRecordingListener;
import com.amazon.alexa.enrollment.unified.model.EnrollmentStateProvider;
import com.amazon.alexa.enrollment.unified.speakerid.EnrollmentUserSpeechProvider;
import com.amazon.alexa.enrollment.unified.speakerid.SpeakerIDEnrollmentProvider;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.EnrollmentMetadata;
import com.amazon.alexa.enrollment.unified.speakerid.api.model.EnrollmentStates;
import com.amazon.alexa.enrollment.unified.speakerid.error.EnrollmentComponentNullException;
import com.amazon.alexa.enrollment.unified.speakerid.error.NullTrainingPhrasesException;
import com.amazon.alexa.enrollment.unified.speakerid.error.SpeakerIDEnrollmentError;
import com.amazon.alexa.enrollment.unified.speakerid.error.SpeakerIDErrorParser;
import com.amazon.alexa.enrollment.unified.speakerid.error.TerminalError;
import com.amazon.alexa.enrollment.unified.speakerid.metrics.SpeakerIDEnrollmentMetricsReporter;
import com.amazon.alexa.enrollment.unified.speakerid.provider.SchedulerProvider;
import com.amazon.alexa.enrollment.unified.speakerid.provider.SpeakerIDAPIProvider;
import com.amazon.alexa.enrollment.unified.speakerid.training.EnrollmentTrainingUiModel;
import com.amazon.alexa.enrollment.unified.speakerid.training.EnrollmentTrainingViewModel;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.voice.tta.Constants;
import com.amazon.alexa.wakeword.WakeWordData;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes7.dex */
public class SpeakerIDEnrollmentProvider implements EnrollmentProvider {
    private static final String ALEXA_WAKE_WORD = "alexa";
    private static final long GET_ENROLLMENT_STATUS_DELAY_MILLIS = 500;
    private static final long SPEECHLET_INTERACTIVE_FOCUS_DURATION_MILLIS = 10000;
    private static final String TAG = "SpeakerIDEnrollmentProvider";
    private static final long VISUAL_FOCUS_TIMEOUT_SEC = 60;
    private final AlexaMobileFrameworkApis mAlexaMobileFrameworkApis;
    private final AlexaServicesConnection mAlexaServicesConnection;
    private final AlexaVisualTask mAlexaVisualTask;
    private final CompositeDisposable mCompositeDisposable;
    private int mCurrentTrainingIndex;
    private final EnrollmentStateProvider mEnrollmentStateProvider;
    private EnrollmentUserSpeechProvider mEnrollmentUserSpeechProvider;
    private final FeatureQuery mFeatureQuery;
    private final ScheduledExecutorService mGetVoiceEnrollmentExecutorService;
    private final HandsFreeUserIdentity mHandsFreeUser;
    private boolean mIsFetchingEnrollmentStatus;
    private final SpeakerIDEnrollmentMetricsReporter mMetricsReporter;
    private final SchedulerProvider mSchedulerProvider;
    private final SpeakerIDAPIProvider mSpeakerIDAPIProvider;
    private final SpeakerIDErrorParser mSpeakerIDErrorParser;
    private final ScheduledExecutorService mSpeechletTimeoutExecutorService;
    private ScheduledFuture<?> mSpeechletTimeoutFuture;
    private final SpeechletTimeoutListener mSpeechletTimeoutListener;
    private final StopRecordingListener mStopRecordingListener;
    private EnrollmentTrainingViewModel mViewModel;
    private final ScheduledExecutorService mVisualFocusTimeoutExecutorService;
    private ScheduledFuture<?> mVisualFocusTimeoutFuture;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.enrollment.unified.speakerid.SpeakerIDEnrollmentProvider$2  reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements EnrollmentUserSpeechProvider.DialogListener {
        final /* synthetic */ EnrollmentProvider.UtteranceTrainingCallback val$utteranceTrainingCallback;

        AnonymousClass2(EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
            this.val$utteranceTrainingCallback = utteranceTrainingCallback;
        }

        public /* synthetic */ void lambda$onDialogFinished$0$SpeakerIDEnrollmentProvider$2(EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
            SpeakerIDEnrollmentProvider.this.fetchEnrollmentStatus(utteranceTrainingCallback);
        }

        @Override // com.amazon.alexa.enrollment.unified.speakerid.EnrollmentUserSpeechProvider.DialogListener
        public void onDialogFinished() {
            AlexaServicesApis.UserSpeechProviders.deregister(SpeakerIDEnrollmentProvider.this.mAlexaServicesConnection, SpeakerIDEnrollmentProvider.this.mEnrollmentUserSpeechProvider);
            SpeakerIDEnrollmentProvider.this.mEnrollmentUserSpeechProvider = null;
            SpeakerIDEnrollmentProvider.this.startSpeechletTimer();
            if (!SpeakerIDEnrollmentProvider.this.mFeatureQuery.isActive("VOX_ENROLLMENT_ANDROID_VOICE_ACTIVITY_REFACTORING")) {
                ScheduledExecutorService scheduledExecutorService = SpeakerIDEnrollmentProvider.this.mGetVoiceEnrollmentExecutorService;
                final EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback = this.val$utteranceTrainingCallback;
                scheduledExecutorService.schedule(new Runnable() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDEnrollmentProvider$2$yd-lxmaRHzO8tlIniIi7VwFFwwg
                    @Override // java.lang.Runnable
                    public final void run() {
                        SpeakerIDEnrollmentProvider.AnonymousClass2.this.lambda$onDialogFinished$0$SpeakerIDEnrollmentProvider$2(utteranceTrainingCallback);
                    }
                }, 500L, TimeUnit.MILLISECONDS);
                return;
            }
            SpeakerIDEnrollmentProvider.this.fetchEnrollmentStatus(this.val$utteranceTrainingCallback);
        }

        @Override // com.amazon.alexa.enrollment.unified.speakerid.EnrollmentUserSpeechProvider.DialogListener
        public void onDialogRequestDenied() {
            SpeakerIDEnrollmentProvider.this.mStopRecordingListener.onStopRecording();
            SpeakerIDEnrollmentProvider.this.stopSpeechletTimer();
            this.val$utteranceTrainingCallback.onError(TerminalError.DIALOG_REQUEST_DENIED);
            AlexaServicesApis.UserSpeechProviders.deregister(SpeakerIDEnrollmentProvider.this.mAlexaServicesConnection, SpeakerIDEnrollmentProvider.this.mEnrollmentUserSpeechProvider);
            SpeakerIDEnrollmentProvider.this.mEnrollmentUserSpeechProvider = null;
        }

        @Override // com.amazon.alexa.enrollment.unified.speakerid.EnrollmentUserSpeechProvider.DialogListener
        public void onDialogRequested() {
            SpeakerIDEnrollmentProvider.this.stopSpeechletTimer();
        }

        @Override // com.amazon.alexa.enrollment.unified.speakerid.EnrollmentUserSpeechProvider.DialogListener
        public void onStopRecording() {
            SpeakerIDEnrollmentProvider.this.mStopRecordingListener.onStopRecording();
        }
    }

    public SpeakerIDEnrollmentProvider(@NonNull Context context, @NonNull EnrollmentStateProvider enrollmentStateProvider, @NonNull StopRecordingListener stopRecordingListener, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull SpeechletTimeoutListener speechletTimeoutListener) {
        this(context, stopRecordingListener, new SchedulerProvider(), enrollmentStateProvider, alexaServicesConnection, new SpeakerIDEnrollmentMetricsReporter(context), new SpeakerIDAPIProvider(context), new SpeakerIDErrorParser(context), Executors.newSingleThreadScheduledExecutor(), Executors.newSingleThreadScheduledExecutor(), Executors.newSingleThreadScheduledExecutor(), speechletTimeoutListener, new CompositeDisposable(), handsFreeUserIdentity, (FeatureQuery) GeneratedOutlineSupport1.outline20(FeatureQuery.class));
    }

    private Single<EnrollmentTrainingUiModel> callStartUserVoiceEnrollment() {
        Log.d(TAG, "callStartUserVoiceEnrollment");
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDEnrollmentProvider$3C0PB0zHQK4L0jGnYYoLyk2K87Y
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SpeakerIDEnrollmentProvider.this.lambda$callStartUserVoiceEnrollment$5$SpeakerIDEnrollmentProvider();
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDEnrollmentProvider$pwgzskvRTC4nHvd5d0pZa3JSB64
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return SpeakerIDEnrollmentProvider.this.lambda$callStartUserVoiceEnrollment$7$SpeakerIDEnrollmentProvider((Optional) obj);
            }
        });
    }

    private void cancelVisualFocusTimeoutFutureIfNotDone() {
        ScheduledFuture<?> scheduledFuture = this.mVisualFocusTimeoutFuture;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            return;
        }
        Log.d(TAG, "Visual focus cancel timeout");
        this.mVisualFocusTimeoutFuture.cancel(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleGetEnrollmentStatusError */
    public void lambda$callGetVoiceEnrollmentStatusAPI$3$SpeakerIDEnrollmentProvider(@NonNull Throwable th, @NonNull EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Getting error for ");
        outline107.append(th.getClass().getSimpleName());
        outline107.append(" with message ");
        outline107.append(th.getMessage());
        Log.e(str, outline107.toString());
        this.mIsFetchingEnrollmentStatus = false;
        ScheduledFuture<?> scheduledFuture = this.mSpeechletTimeoutFuture;
        if (scheduledFuture == null || !scheduledFuture.isDone()) {
            stopSpeechletTimer();
            SpeakerIDEnrollmentError speakerIDEnrollmentError = this.mSpeakerIDErrorParser.getSpeakerIDEnrollmentError(th);
            this.mMetricsReporter.reportUtteranceTrainingException(speakerIDEnrollmentError);
            utteranceTrainingCallback.onError(this.mSpeakerIDErrorParser.getEnrollmentErrorContract(speakerIDEnrollmentError));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleGetEnrollmentStatusSuccess */
    public void lambda$callGetVoiceEnrollmentStatusAPI$2$SpeakerIDEnrollmentProvider(@NonNull EnrollmentTrainingUiModel enrollmentTrainingUiModel, @NonNull EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
        Log.d(TAG, "handleGetEnrollmentStatusSuccess");
        this.mIsFetchingEnrollmentStatus = false;
        this.mMetricsReporter.reportSpeakerIDFetchEnrollmentStatusSuccess();
        ScheduledFuture<?> scheduledFuture = this.mSpeechletTimeoutFuture;
        if (scheduledFuture != null && scheduledFuture.isDone()) {
            Log.d(TAG, "Speechlet timeout future is done");
        } else if (enrollmentTrainingUiModel.getCurrentState() == EnrollmentStates.COMPLETED) {
            Log.d(TAG, "Enrollment completed!");
            stopSpeechletTimer();
            taskUnschedule();
            utteranceTrainingCallback.onSuccess();
        } else if (this.mCurrentTrainingIndex == enrollmentTrainingUiModel.getCurrentTrainingIndex().intValue()) {
            Log.d(TAG, "Same index after getEnrollmentStatus success. Calling error callback.");
            stopSpeechletTimer();
            utteranceTrainingCallback.onError(TerminalError.GET_ENROLLMENT_STATUS_SAME_INDEX);
        } else {
            Log.d(TAG, "Successfully advanced to next utterance. Invoking success callback.");
            this.mCurrentTrainingIndex = enrollmentTrainingUiModel.getCurrentTrainingIndex().intValue();
            utteranceTrainingCallback.onSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleStartUserVoiceEnrollmentSuccess */
    public void lambda$startUserVoiceEnrollment$0$SpeakerIDEnrollmentProvider(@NonNull EnrollmentTrainingUiModel enrollmentTrainingUiModel, @NonNull ResponseCallback responseCallback) {
        Log.d(TAG, "handleStartUserVoiceEnrollmentSuccess");
        startSpeechletTimer();
        this.mCurrentTrainingIndex = 0;
        this.mMetricsReporter.reportSpeakerIDStartEnrollmentSuccess();
        this.mEnrollmentStateProvider.setNumUtterances(enrollmentTrainingUiModel.getTrainingPhrases().size());
        responseCallback.onSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleStartVoiceEnrollmentError */
    public void lambda$startUserVoiceEnrollment$1$SpeakerIDEnrollmentProvider(@NonNull Throwable th, @NonNull ResponseCallback responseCallback) {
        taskUnschedule();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Getting error for ");
        outline107.append(th.getClass().getSimpleName());
        outline107.append(" with message ");
        outline107.append(th.getMessage());
        Log.e(str, outline107.toString());
        this.mMetricsReporter.reportStartEnrollmentException(this.mSpeakerIDErrorParser.getSpeakerIDEnrollmentError(th));
        responseCallback.onError(new CallbackErrorMetadata(th.getMessage()));
    }

    private boolean is1PSVVisualFocus() {
        return this.mHandsFreeUser.hasComponent(HandsFreeComponent.EDGESV_VISUAL_FOCUS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ EnrollmentTrainingUiModel lambda$null$6(EnrollmentTrainingUiModel enrollmentTrainingUiModel) throws Throwable {
        if (enrollmentTrainingUiModel.getTrainingPhrases() != null) {
            return enrollmentTrainingUiModel;
        }
        throw new NullTrainingPhrasesException(enrollmentTrainingUiModel.getCurrentState());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSpeechletTimer() {
        Log.d(TAG, "startSpeechletTimer");
        if (is1PSVVisualFocus()) {
            return;
        }
        stopSpeechletTimer();
        this.mSpeechletTimeoutFuture = this.mSpeechletTimeoutExecutorService.schedule(new Runnable() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDEnrollmentProvider$BDYNxRurnW9qU-qDoVwIQIjQ2-A
            @Override // java.lang.Runnable
            public final void run() {
                SpeakerIDEnrollmentProvider.this.lambda$startSpeechletTimer$4$SpeakerIDEnrollmentProvider();
            }
        }, 10000L, TimeUnit.MILLISECONDS);
    }

    private void taskUnschedule() {
        if (!is1PSVVisualFocus()) {
            return;
        }
        Log.d(TAG, "Visual focus taskUnschedule");
        this.mMetricsReporter.reportSpeakerIDVisualFocusUnscheduled();
        this.mAlexaMobileFrameworkApis.getVisualTask().unschedule(this.mAlexaVisualTask);
        this.mAlexaMobileFrameworkApis.stop();
        cancelVisualFocusTimeoutFutureIfNotDone();
    }

    private void timeoutReschedule() {
        if (!is1PSVVisualFocus()) {
            return;
        }
        cancelVisualFocusTimeoutFutureIfNotDone();
        Log.d(TAG, "Visual focus timeoutReschedule");
        this.mVisualFocusTimeoutFuture = this.mVisualFocusTimeoutExecutorService.schedule(new Runnable() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDEnrollmentProvider$ZzomHQyP1Y-3t6bUCqoK638I09A
            @Override // java.lang.Runnable
            public final void run() {
                SpeakerIDEnrollmentProvider.this.lambda$timeoutReschedule$8$SpeakerIDEnrollmentProvider();
            }
        }, 60L, TimeUnit.SECONDS);
    }

    @VisibleForTesting
    void callGetVoiceEnrollmentStatusAPI(@NonNull final EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
        Log.d(TAG, "callGetVoiceEnrollmentStatusAPI");
        this.mCompositeDisposable.add(this.mViewModel.refreshUiModel(new EnrollmentMetadata(new Bundle(), SpeakerIDAPIProvider.EXTRA_VOICE_ENROLLMENT_CONTEXT)).subscribeOn(this.mSchedulerProvider.ioThread()).observeOn(this.mSchedulerProvider.uiThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDEnrollmentProvider$eDBy66O6vNHJpvaiJRw9UyaaNLo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeakerIDEnrollmentProvider.this.lambda$callGetVoiceEnrollmentStatusAPI$2$SpeakerIDEnrollmentProvider(utteranceTrainingCallback, (EnrollmentTrainingUiModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDEnrollmentProvider$e4ZpagnCQtuWvY3KoSUIE-Ylm4s
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeakerIDEnrollmentProvider.this.lambda$callGetVoiceEnrollmentStatusAPI$3$SpeakerIDEnrollmentProvider(utteranceTrainingCallback, (Throwable) obj);
            }
        }));
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void cancelUserVoiceEnrollment(@NonNull ResponseCallback responseCallback) {
        Log.d(TAG, "cancelUserVoiceEnrollment");
        taskUnschedule();
        responseCallback.onSuccess();
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void cancelUtteranceTraining(@NonNull ResponseCallback responseCallback) {
        Log.d(TAG, "cancelUtteranceTraining");
    }

    @VisibleForTesting
    void fetchEnrollmentStatus(@NonNull EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
        if (this.mViewModel == null) {
            Log.e(TAG, "Unable to fetch enrollment status, EnrollmentTrainingViewModel is not present.");
            lambda$callGetVoiceEnrollmentStatusAPI$3$SpeakerIDEnrollmentProvider(new EnrollmentComponentNullException(EnrollmentTrainingViewModel.class.getSimpleName()), utteranceTrainingCallback);
            return;
        }
        EnrollmentStates currentVoiceEnrollmentState = getCurrentVoiceEnrollmentState();
        if (currentVoiceEnrollmentState == EnrollmentStates.NOT_STARTED) {
            String str = "Enrollment should have started before calling this method, current state is " + currentVoiceEnrollmentState;
            Throwable th = new Throwable(str);
            Log.e(TAG, str);
            this.mMetricsReporter.recordNonFatalError(th, SpeakerIDEnrollmentMetricsReporter.UTTERANCE_TRAINING);
            lambda$callGetVoiceEnrollmentStatusAPI$3$SpeakerIDEnrollmentProvider(th, utteranceTrainingCallback);
            return;
        }
        callGetVoiceEnrollmentStatusAPI(utteranceTrainingCallback);
        this.mIsFetchingEnrollmentStatus = true;
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void finishUserVoiceEnrollment(@NonNull ResponseCallback responseCallback) {
        Log.d(TAG, "finishUserVoiceEnrollment");
        taskUnschedule();
        responseCallback.onSuccess();
    }

    @Nullable
    public EnrollmentStates getCurrentVoiceEnrollmentState() {
        EnrollmentTrainingViewModel enrollmentTrainingViewModel = this.mViewModel;
        if (enrollmentTrainingViewModel != null && enrollmentTrainingViewModel.getUiModel() != null) {
            return this.mViewModel.getUiModel().getCurrentState();
        }
        if (this.mViewModel == null) {
            Log.w(TAG, "EnrollmentTrainingViewModel is null");
        } else {
            Log.w(TAG, "EnrollmentTrainingUiModel is null");
        }
        Log.w(TAG, "Enrollment not started");
        return EnrollmentStates.NOT_STARTED;
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    @NonNull
    public List<String> getUtterances() {
        Log.d(TAG, "getUtterances");
        EnrollmentTrainingViewModel enrollmentTrainingViewModel = this.mViewModel;
        if (enrollmentTrainingViewModel != null && enrollmentTrainingViewModel.getUiModel() != null) {
            List<String> trainingPhrases = this.mViewModel.getUiModel().getTrainingPhrases();
            if (trainingPhrases != null && !trainingPhrases.isEmpty()) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Training Phrases: ");
                outline107.append(trainingPhrases.toString());
                Log.d(str, outline107.toString());
                this.mMetricsReporter.reportSpeakerIDGetTrainingPhrasesSuccess();
                return trainingPhrases;
            }
            Log.w(TAG, "Training Phrases not available");
            this.mMetricsReporter.reportSpeakerIDGetTrainingPhrasesFailure();
            return Collections.emptyList();
        }
        Log.e(TAG, "Missing ViewModel or UiModel, returning null training phrases");
        this.mMetricsReporter.reportSpeakerIDGetTrainingPhrasesFailure();
        return Collections.emptyList();
    }

    public /* synthetic */ Optional lambda$callStartUserVoiceEnrollment$5$SpeakerIDEnrollmentProvider() throws Exception {
        return Optional.fromNullable(this.mSpeakerIDAPIProvider.provideEnrollmentTrainingViewModel());
    }

    public /* synthetic */ SingleSource lambda$callStartUserVoiceEnrollment$7$SpeakerIDEnrollmentProvider(Optional optional) throws Throwable {
        this.mViewModel = (EnrollmentTrainingViewModel) optional.orNull();
        EnrollmentTrainingViewModel enrollmentTrainingViewModel = this.mViewModel;
        if (enrollmentTrainingViewModel == null) {
            Log.e(TAG, "Unable to start voice enrollment, EnrollmentTrainingViewModel is not present.");
            return Single.error(new EnrollmentComponentNullException(EnrollmentTrainingViewModel.class.getSimpleName()));
        }
        return enrollmentTrainingViewModel.refreshUiModel(new EnrollmentMetadata(new Bundle(), SpeakerIDAPIProvider.EXTRA_VOICE_ENROLLMENT_CONTEXT)).map($$Lambda$SpeakerIDEnrollmentProvider$EXkQeTYJcgUfAKE_d3fxbC6MFU8.INSTANCE);
    }

    public /* synthetic */ void lambda$startSpeechletTimer$4$SpeakerIDEnrollmentProvider() {
        this.mCompositeDisposable.clear();
        this.mSpeechletTimeoutListener.onSpeechletTimedOut(this.mIsFetchingEnrollmentStatus ? TerminalError.SPEECHLET_TIMEOUT_DURING_API_CALL : TerminalError.SPEECHLET_TIMEOUT_DURING_DETECTION);
    }

    public /* synthetic */ void lambda$timeoutReschedule$8$SpeakerIDEnrollmentProvider() {
        Log.d(TAG, "Visual focus timeout");
        this.mMetricsReporter.reportSpeakerIDVisualFocusTimeout();
        taskUnschedule();
    }

    @VisibleForTesting
    EnrollmentUserSpeechProvider requestDialog(@NonNull WakeWordData wakeWordData, @NonNull EnrollmentUserSpeechProvider.DialogListener dialogListener) {
        EnrollmentUserSpeechProvider enrollmentUserSpeechProvider = new EnrollmentUserSpeechProvider(wakeWordData, dialogListener, this.mMetricsReporter);
        AlexaServicesApis.UserSpeechProviders.register(this.mAlexaServicesConnection, enrollmentUserSpeechProvider, AlexaUserSpeechProviderMetadata.create(Collections.singleton(SupportedInitiationType.WAKE_WORD), Collections.singleton("alexa"), AlexaUserSpeechProviderScope.SYSTEM));
        AlexaServicesApis.UserSpeechRecognizer.requestDialog(this.mAlexaServicesConnection, enrollmentUserSpeechProvider, AlexaDialogRequest.builder().setInvocationType("HandsFree.VoiceEnrollment").build());
        return enrollmentUserSpeechProvider;
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void startUserVoiceEnrollment(@NonNull final ResponseCallback responseCallback) {
        Log.d(TAG, "StartVoiceEnrollment called");
        this.mMetricsReporter.reportSpeakerIDVisualFocusScheduled();
        this.mAlexaMobileFrameworkApis.getVisualTask().schedule(this.mAlexaVisualTask);
        Log.d(TAG, "Visual focus task scheduled");
        timeoutReschedule();
        this.mCompositeDisposable.clear();
        this.mCompositeDisposable.add(callStartUserVoiceEnrollment().subscribeOn(this.mSchedulerProvider.ioThread()).observeOn(this.mSchedulerProvider.uiThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDEnrollmentProvider$b7XgJjk8Yi5gJRyTVgQdSIOX9HQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeakerIDEnrollmentProvider.this.lambda$startUserVoiceEnrollment$0$SpeakerIDEnrollmentProvider(responseCallback, (EnrollmentTrainingUiModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDEnrollmentProvider$Q319rUF7qvXd4MgJjOdA3xdyJgA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeakerIDEnrollmentProvider.this.lambda$startUserVoiceEnrollment$1$SpeakerIDEnrollmentProvider(responseCallback, (Throwable) obj);
            }
        }));
    }

    @Override // com.amazon.alexa.enrollment.unified.api.EnrollmentProvider
    public void startUtteranceTraining(@NonNull EnrollmentProvider.UtteranceTrainingCallback utteranceTrainingCallback) {
        Log.d(TAG, "startUtteranceTraining");
        timeoutReschedule();
        WakeWordData currentWakeWordData = this.mEnrollmentStateProvider.getCurrentWakeWordData();
        if (currentWakeWordData == null) {
            Log.e(TAG, "Null wakeWordData; returning from startUtteranceTraining.");
            EnrollmentComponentNullException enrollmentComponentNullException = new EnrollmentComponentNullException(WakeWordData.class.getSimpleName());
            utteranceTrainingCallback.onError(this.mSpeakerIDErrorParser.getEnrollmentErrorContract(enrollmentComponentNullException));
            this.mMetricsReporter.reportUtteranceTrainingException(this.mSpeakerIDErrorParser.getSpeakerIDEnrollmentError(enrollmentComponentNullException));
            return;
        }
        this.mEnrollmentStateProvider.setCurrentWakeWordData(null);
        this.mEnrollmentUserSpeechProvider = requestDialog(currentWakeWordData, new AnonymousClass2(utteranceTrainingCallback));
    }

    public void stopSpeechletTimer() {
        ScheduledFuture<?> scheduledFuture;
        Log.d(TAG, "stopSpeechletTimer");
        if (!is1PSVVisualFocus() && (scheduledFuture = this.mSpeechletTimeoutFuture) != null) {
            scheduledFuture.cancel(true);
        }
    }

    @VisibleForTesting
    SpeakerIDEnrollmentProvider(@NonNull Context context, @NonNull StopRecordingListener stopRecordingListener, @NonNull SchedulerProvider schedulerProvider, @NonNull EnrollmentStateProvider enrollmentStateProvider, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull SpeakerIDEnrollmentMetricsReporter speakerIDEnrollmentMetricsReporter, @NonNull SpeakerIDAPIProvider speakerIDAPIProvider, @NonNull SpeakerIDErrorParser speakerIDErrorParser, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull ScheduledExecutorService scheduledExecutorService2, @NonNull ScheduledExecutorService scheduledExecutorService3, @NonNull SpeechletTimeoutListener speechletTimeoutListener, @NonNull CompositeDisposable compositeDisposable, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull FeatureQuery featureQuery) {
        this.mAlexaVisualTask = new AlexaVisualTask() { // from class: com.amazon.alexa.enrollment.unified.speakerid.SpeakerIDEnrollmentProvider.1
            @Override // com.amazon.alexa.api.AlexaVisualTask
            public String getTaskComponentName() {
                return Constants.Namespaces.TEXT;
            }

            @Override // com.amazon.alexa.api.AlexaVisualTask
            public void onStop() {
            }
        };
        this.mStopRecordingListener = stopRecordingListener;
        this.mSchedulerProvider = schedulerProvider;
        this.mEnrollmentStateProvider = enrollmentStateProvider;
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mMetricsReporter = speakerIDEnrollmentMetricsReporter;
        this.mSpeakerIDAPIProvider = speakerIDAPIProvider;
        this.mSpeakerIDErrorParser = speakerIDErrorParser;
        this.mVisualFocusTimeoutExecutorService = scheduledExecutorService;
        this.mGetVoiceEnrollmentExecutorService = scheduledExecutorService2;
        this.mSpeechletTimeoutExecutorService = scheduledExecutorService3;
        this.mSpeechletTimeoutListener = speechletTimeoutListener;
        this.mCompositeDisposable = compositeDisposable;
        this.mHandsFreeUser = handsFreeUserIdentity;
        this.mFeatureQuery = featureQuery;
        this.mAlexaMobileFrameworkApis = new AlexaMobileFrameworkApis(context, "1PSV");
        this.mAlexaMobileFrameworkApis.start();
    }
}
