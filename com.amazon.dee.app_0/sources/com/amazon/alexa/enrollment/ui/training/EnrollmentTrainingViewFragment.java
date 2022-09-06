package com.amazon.alexa.enrollment.ui.training;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.alexaservices.AlexaStateInteractor;
import com.amazon.alexa.enrollment.api.model.EnrollmentMetadata;
import com.amazon.alexa.enrollment.api.model.EnrollmentStates;
import com.amazon.alexa.enrollment.exception.EnrollmentException;
import com.amazon.alexa.enrollment.exception.InvalidEnrollmentStatusException;
import com.amazon.alexa.enrollment.exception.UserCancelledException;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.amazon.alexa.enrollment.ui.views.EnrollmentTrainingProgressBar;
import com.amazon.alexa.enrollment.utils.ActivityConstants;
import com.amazon.alexa.enrollment.utils.AnimationHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.enrollment.utils.EnrollmentUtil;
import com.amazon.alexa.enrollment.voiceSDK.client.AlexaVoiceSDKClient;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events.EnrollmentTerminateEvent;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events.UpdateTrainingPageUIEvent;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.net.UnknownHostException;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes7.dex */
public class EnrollmentTrainingViewFragment extends AbstractEnrollmentViewFragment {
    private static final String CALL_VOICE_ACTIVITY_EVENT = "kids:enrollment:call:voiceactivity";
    private static final long DELAY_MILLIS = 3000;
    private static final String ENROLLMENT_ALEXA_IDLE_EVENT = "kids:enrollment:alexa:idle";
    protected static final String ENROLLMENT_RETRY_ON_FAILURE = "RETRY_ON_ENROLLMENT_FAILURE";
    private static final int MAX_RETRY_COUNT = 1;
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentTrainingViewFragment.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private static final float TOP_Y_AXIS = 0.0f;
    private static final String UI_UPDATION_EVENT = "kids:enrollment:ui:updation";
    private MultiFilterSubscriber alexaIdleEventSubscriber;
    @Inject
    AlexaStateInteractor alexaStateInteractor;
    @Inject
    AlexaVoiceSDKClient alexaVoiceSDKClient;
    @Inject
    AnimationHelper animationHelper;
    @Inject
    EnrollmentTrainingDialogHelper dialogHelper;
    @Inject
    EnrollmentEventBus enrollmentEventBus;
    @Inject
    EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    @Inject
    EnrollmentThemeUtil enrollmentThemeUtil;
    @Inject
    EventBus eventBus;
    private View rootView;
    private EnrollmentTrainingUiModel uiModel;
    private MultiFilterSubscriber uiUpdationEventSubscriber;
    @Inject
    EnrollmentTrainingViewModel viewModel;
    private MultiFilterSubscriber voiceActivityEventSubscriber;
    private final long voiceActivityDelay = 400;
    private boolean isTrainingActivityWentToBackground = false;
    private boolean isVoiceActivityStarted = false;
    private MobilyticsMetricsTimer voiceActivityTimer = null;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Boolean isUserSpeechProviderEligible = null;

    public EnrollmentTrainingViewFragment() {
    }

    private void bindViewModel() {
        this.compositeDisposable.add(this.viewModel.refreshUiModel(new EnrollmentMetadata(getExtras(), getEnrollmentContext())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$xgXx0GCRoI8iDzQPXW-rj_i4snU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                EnrollmentTrainingViewFragment.this.updateUI((EnrollmentTrainingUiModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$m7NdWxxl47RGWWCUDUpqkbC8h38
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                EnrollmentTrainingViewFragment.this.processError((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createDelayForVoiceActivity() {
        Log.i(TAG, "inside createDelayForVoiceActivity");
        new Handler().postDelayed(new Runnable() { // from class: com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewFragment.3
            @Override // java.lang.Runnable
            public void run() {
                EnrollmentTrainingViewFragment.this.callVoiceActivity();
            }
        }, 400L);
    }

    public static EnrollmentTrainingViewFragment createInstance() {
        return new EnrollmentTrainingViewFragment();
    }

    private String getVoiceActivityTimerEventName(EnrollmentStates enrollmentStates, int i) {
        if (enrollmentStates != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MetricsConstants.OperationalMetrics.VOICE_ACTIVITY_TIMER_EVENT_NAME_PREFIX);
            outline107.append(enrollmentStates.name());
            outline107.append("_");
            outline107.append(i);
            return outline107.toString();
        }
        return "VOICE_ACTIVITY_TIMER_EVENT__Default";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSoundOnToast(RelativeLayout relativeLayout) {
        Log.i(TAG, "hiding the toast layout");
        relativeLayout.setVisibility(8);
        relativeLayout.animate().translationY(0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUiUpdationEvent(Message message) {
        Log.i(TAG, "received UI updation event from event bus");
        bindViewModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onVoiceActivityEvent(Message message) {
        Log.i(TAG, "received Voice Activity event from event bus");
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewFragment.2
            @Override // java.lang.Runnable
            public void run() {
                EnrollmentTrainingViewFragment.this.createDelayForVoiceActivity();
            }
        });
    }

    private void recordCompletionMetric() {
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.ENROLLMENT_COMPLETED);
        if (checkUserSpeechProviderEligiblity()) {
            this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.ENROLLMENT_COMPLETED_FROM_USER_SPEECH_PROVIDER);
        }
    }

    private void recordVoiceActivityTimer() {
        if (this.isVoiceActivityStarted) {
            MobilyticsMetricsTimer mobilyticsMetricsTimer = this.voiceActivityTimer;
            if (mobilyticsMetricsTimer != null) {
                this.enrollmentMetricsRecorder.finishTimer(mobilyticsMetricsTimer);
                this.voiceActivityTimer = null;
            }
            this.isVoiceActivityStarted = false;
        }
    }

    private void releaseConnections() {
        Log.i(TAG, "inside releaseConnections");
        this.alexaStateInteractor.release();
    }

    private void renderShimLayoutAnimation() {
        this.animationHelper.renderPulseAnimation(getContext(), this.rootView.findViewById(R.id.shim_progress_bar));
        this.animationHelper.renderPulseAnimation(getContext(), this.rootView.findViewById(R.id.shim_say));
        this.animationHelper.renderVerticalSideWithPulseAnimation(getContext(), this.rootView.findViewById(R.id.training_phrase_shim_layout));
    }

    private void setMosaicThemeColour() {
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicAction40, this.rootView.findViewById(R.id.sound_on_toast_message));
        this.enrollmentThemeUtil.setTintColorToImageView((ImageView) this.rootView.findViewById(R.id.sound_on_toast_message_img), getContext(), R.attr.mosaicNeutral10);
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicBackground, this.rootView.findViewById(R.id.shim_layout));
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicNeutral50, this.rootView.findViewById(R.id.shim_say), this.rootView.findViewById(R.id.shim_training_phrase), this.rootView.findViewById(R.id.shim_training_phrase1));
        this.enrollmentThemeUtil.setBackgroundToView(getContext(), R.drawable.shim_enrollment_progress_view_mosaic, this.rootView.findViewById(R.id.shim_first_utterence_status), this.rootView.findViewById(R.id.shim_second_utterence_status), this.rootView.findViewById(R.id.shim_third_utterence_status), this.rootView.findViewById(R.id.shim_fourth_utterence_status));
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicBackground, this.rootView.findViewById(R.id.training_layout));
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral10, (TextView) this.rootView.findViewById(R.id.say), (TextView) this.rootView.findViewById(R.id.training_phrase));
    }

    private void showSoundOnToast(final RelativeLayout relativeLayout, long j) {
        Log.i(TAG, "inside showSoundOnToast");
        relativeLayout.setVisibility(0);
        new Handler().postDelayed(new Runnable() { // from class: com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewFragment.1
            @Override // java.lang.Runnable
            public void run() {
                Log.i(EnrollmentTrainingViewFragment.TAG, "shown the toast, hiding the SOUND ON toast");
                EnrollmentTrainingViewFragment.this.hideSoundOnToast(relativeLayout);
            }
        }, j);
    }

    private void startListening() {
        this.eventBus.subscribe(this.uiUpdationEventSubscriber);
        this.eventBus.subscribe(this.voiceActivityEventSubscriber);
        this.eventBus.subscribe(this.alexaIdleEventSubscriber);
        Log.i(TAG, "started listening from event bus");
    }

    private void startVoiceActivityWithTimer(EnrollmentStates enrollmentStates, int i) {
        this.isVoiceActivityStarted = this.viewModel.startVoiceActivity(this);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Inside Voice Activity Timer ");
        outline107.append(this.isVoiceActivityStarted);
        Log.i(str, outline107.toString());
        if (this.isVoiceActivityStarted) {
            String voiceActivityTimerEventName = getVoiceActivityTimerEventName(enrollmentStates, i);
            GeneratedOutlineSupport1.outline163("Starting Voice Activity Timer for event ", voiceActivityTimerEventName, TAG);
            this.voiceActivityTimer = this.enrollmentMetricsRecorder.startTimer(voiceActivityTimerEventName);
        }
    }

    private void startVoiceCapture(EnrollmentTrainingUiModel enrollmentTrainingUiModel) {
        if (checkUserSpeechProviderEligiblity()) {
            if (!enrollmentTrainingUiModel.shouldRequestDialogFromUserSpeechProvider()) {
                return;
            }
            this.alexaVoiceSDKClient.requestDialog(enrollmentTrainingUiModel.getCurrentState().name());
            return;
        }
        startVoiceActivityWithTimer(enrollmentTrainingUiModel.getCurrentState(), enrollmentTrainingUiModel.getCurrentTrainingIndex().intValue());
    }

    private void subscribeToPublishers() {
        this.uiUpdationEventSubscriber = new SimpleMultiFilterSubscriber();
        this.uiUpdationEventSubscriber.subscribe(new EventTypeMessageFilter(UI_UPDATION_EVENT), new MessageHandler() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$EnrollmentTrainingViewFragment$BM_uECy20JPyct1lfaxU6tLFgnI
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EnrollmentTrainingViewFragment.this.onUiUpdationEvent(message);
            }
        });
        this.voiceActivityEventSubscriber = new SimpleMultiFilterSubscriber();
        this.voiceActivityEventSubscriber.subscribe(new EventTypeMessageFilter(CALL_VOICE_ACTIVITY_EVENT), new MessageHandler() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$EnrollmentTrainingViewFragment$QKnH-5nl0DUjyoqbnlOnPzGrOAs
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EnrollmentTrainingViewFragment.this.onVoiceActivityEvent(message);
            }
        });
        this.alexaIdleEventSubscriber = new SimpleMultiFilterSubscriber();
        this.alexaIdleEventSubscriber.subscribe(new EventTypeMessageFilter(ENROLLMENT_ALEXA_IDLE_EVENT), new MessageHandler() { // from class: com.amazon.alexa.enrollment.ui.training.-$$Lambda$EnrollmentTrainingViewFragment$BM_uECy20JPyct1lfaxU6tLFgnI
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EnrollmentTrainingViewFragment.this.onUiUpdationEvent(message);
            }
        });
        Log.i(TAG, "Subscription to publishers successful");
    }

    private void unbindViewModel() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        EnrollmentTrainingViewModel enrollmentTrainingViewModel = this.viewModel;
        if (enrollmentTrainingViewModel != null) {
            enrollmentTrainingViewModel.resetEnrollmentSession();
        }
    }

    private void unsubscribeEvents() {
        this.eventBus.unsubscribe(this.uiUpdationEventSubscriber);
        this.eventBus.unsubscribe(this.voiceActivityEventSubscriber);
        this.eventBus.unsubscribe(this.alexaIdleEventSubscriber);
        Log.i(TAG, "unsubscribed events from event bus");
    }

    private void updateProgressbar(int i, int i2) {
        EnrollmentTrainingProgressBar enrollmentTrainingProgressBar = (EnrollmentTrainingProgressBar) this.rootView.findViewById(R.id.enrollment_progress_bar);
        enrollmentTrainingProgressBar.updateNumPhrases(i);
        enrollmentTrainingProgressBar.updateCurrentTrainingIndex(i2);
    }

    public void callVoiceActivity() {
        if (this.isTrainingCompleted) {
            Log.i(TAG, "enrollment completed and not calling voice activity");
            unsubscribeEvents();
            releaseConnections();
            return;
        }
        Log.i(TAG, "inside callVoiceActivity");
        if (isFeatureEnabledForUser(ActivityConstants.KIDS_ENROLLMENT_EARCON_AUDIO_FEATURE)) {
            Log.i(TAG, "earcon feature enabled, playing earcon sound");
            EnrollmentUtil.playSound(getContext(), R.raw.alexa_start_sound);
        }
        this.viewModel.startVoiceActivity(this);
    }

    public boolean checkUserSpeechProviderEligiblity() {
        GeneratedOutlineSupport1.outline163("checkUserSpeechProviderEligiblity: ", getEnrollmentContext(), TAG);
        if (this.isUserSpeechProviderEligible == null) {
            this.isUserSpeechProviderEligible = Boolean.valueOf(isFeatureEnabledForUser("VOX_ENROLLMENT_ANDROID_VOICE_ACTIVITY_REFACTORING"));
        }
        return this.isUserSpeechProviderEligible.booleanValue() && !isRepeatBackEnabled();
    }

    @VisibleForTesting
    int getDialogBoxRetryCount() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Log.w(TAG, "activity to get intent from exists");
            return activity.getIntent().getIntExtra(ActivityConstants.RETRY_COUNT, 0);
        }
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.TRAINING_DIALOG_ACTIVITY_IS_NULL);
        Log.w(TAG, "no activity to get intent from");
        return 1;
    }

    @VisibleForTesting
    Bundle getExtras() {
        return getActivity().getIntent().getExtras();
    }

    @VisibleForTesting
    void injectDependency() {
        Injector.inject(this);
    }

    public void onBackPressed() {
        this.viewModel.moveToIntroductionScreen(this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        injectDependency();
        this.enrollmentMetricsRecorder.initializeMetricsContext(getEnrollmentContext());
        if (this.viewModel.getMetricsRecorder() != null) {
            this.viewModel.getMetricsRecorder().initializeMetricsContext(getEnrollmentContext());
        }
        this.enrollmentThemeUtil.setTheme(getContext());
        this.rootView = layoutInflater.inflate(R.layout.activity_enrollment_training, viewGroup, false);
        if (isRepeatBackEnabled()) {
            Log.i(TAG, "Kids Enrollment context, showing the SOUND ON toast");
            this.rootView.setFitsSystemWindows(true);
            showSoundOnToast((RelativeLayout) this.rootView.findViewById(R.id.sound_on_toast_message), 3000L);
        }
        setMosaicThemeColour();
        renderShimLayoutAnimation();
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.ENROLLMENT_STARTED);
        return this.rootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        unbindViewModel();
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public synchronized void onEnrollmentTerminationEvent(EnrollmentTerminateEvent enrollmentTerminateEvent) {
        String str = TAG;
        Log.i(str, "Inside onEnrollmentTerminationEvent " + enrollmentTerminateEvent.failureReason());
        this.enrollmentMetricsRecorder.recordCounter(enrollmentTerminateEvent.failureReason());
        finishEnrollmentWithFailureStatus();
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onNegativeButtonTap(String str, int i) {
        super.onNegativeButtonTap(str, i);
        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 6) {
            Log.e(TAG, "Unknown dialog, ignoring button tap");
            return;
        }
        Log.i(TAG, "User cancelled the dialog.");
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.TRAINING_PAGE_CANCELLED_DIALOG);
        finishEnrollmentWithFailureStatus();
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onPositiveButtonTap(String str, int i) {
        super.onPositiveButtonTap(str, i);
        String str2 = TAG;
        Log.i(str2, "Request code is: " + i);
        int dialogBoxRetryCount = getDialogBoxRetryCount();
        if (i == 6 && isFeatureEnabledForUser("RETRY_ON_ENROLLMENT_FAILURE") && dialogBoxRetryCount < 1) {
            updateDialogBoxRetryCount(dialogBoxRetryCount + 1);
            EnrollmentMetricsRecorder enrollmentMetricsRecorder = this.enrollmentMetricsRecorder;
            enrollmentMetricsRecorder.recordCounter("RETRY_ON_ENROLLMENT_FAILURE" + dialogBoxRetryCount);
            this.viewModel.moveToIntroductionScreen(this);
        } else if (i == 1 || i == 2) {
            this.viewModel.moveToIntroductionScreen(this);
        } else if (i != 3 && i != 4 && i != 6) {
            Log.e(TAG, "Unknown dialog, ignoring button tap");
        } else {
            finishEnrollmentWithFailureStatus();
        }
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, androidx.fragment.app.Fragment
    public void onResume() {
        Log.i(TAG, "Inside onResume");
        this.isTrainingActivityWentToBackground = false;
        recordVoiceActivityTimer();
        if (!isRepeatBackEnabled() && !checkUserSpeechProviderEligiblity()) {
            Log.i(TAG, "Both User Speech Provider and repeat back in kids enrollment disabled..");
            bindViewModel();
        }
        super.onResume();
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, androidx.fragment.app.Fragment
    public void onStart() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Inside onStart ");
        outline107.append(this.isTrainingActivityWentToBackground);
        Log.i(str, outline107.toString());
        if (this.isTrainingActivityWentToBackground) {
            this.viewModel.moveToIntroductionScreen(this);
        }
        if (isRepeatBackEnabled()) {
            this.alexaStateInteractor.initialize();
            subscribeToPublishers();
            startListening();
        }
        boolean checkUserSpeechProviderEligiblity = checkUserSpeechProviderEligiblity();
        this.viewModel.setEligibleForUserSpeechProvider(checkUserSpeechProviderEligiblity);
        if (checkUserSpeechProviderEligiblity) {
            this.enrollmentEventBus.register(this);
            this.alexaVoiceSDKClient.connect(getContext(), EnrollmentUtil.getAlexaMobileFrameworkApisInstance(getContext()));
        }
        this.isTrainingCompleted = false;
        this.isVoiceActivityStarted = false;
        this.voiceActivityTimer = null;
        super.onStart();
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, androidx.fragment.app.Fragment
    public void onStop() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Inside onStop ");
        outline107.append(this.isTrainingActivityWentToBackground);
        Log.i(str, outline107.toString());
        this.isTrainingActivityWentToBackground = true;
        if (isRepeatBackEnabled()) {
            Log.i(TAG, "repeatBackEnabled, unsubscribeEvents, releaseConnections");
            unsubscribeEvents();
            releaseConnections();
        }
        if (checkUserSpeechProviderEligiblity()) {
            Log.i(TAG, "userSpeechProvider: deregistering eventbus, disconnecting voice sdk");
            this.enrollmentEventBus.deRegister(this);
            this.alexaVoiceSDKClient.disConnect();
        }
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public synchronized void onTrainingPhraseEvent(UpdateTrainingPageUIEvent updateTrainingPageUIEvent) {
        Log.i(TAG, "Inside onUpdateTrainingPhraseEvent");
        this.enrollmentMetricsRecorder.recordCounter(updateTrainingPageUIEvent.enrollmentState());
        bindViewModel();
    }

    public void processError(Throwable th) {
        String str = TAG;
        Log.e(str, "Error while getting ui model with exception : " + th);
        if (th instanceof InvalidEnrollmentStatusException) {
            if (checkUserSpeechProviderEligiblity()) {
                this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.ENROLLMENT_ERROR_FROM_USER_SPEECH_PROVIDER);
            } else {
                this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.ENROLLMENT_ERROR_FROM_VOICE_ACTIVITY);
            }
        }
        if (th instanceof UserCancelledException) {
            Log.e(TAG, "Invalid enrollment status because user pressed back or user said stop.");
            this.viewModel.moveToIntroductionScreen(this);
        } else if (th instanceof UnknownHostException) {
            startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        } else if ((th instanceof EnrollmentException) && ((EnrollmentException) th).canShowInlineError()) {
            this.animationHelper.renderVerticalFadeInAnimation(getContext(), (TextView) this.rootView.findViewById(R.id.training_phrase));
            ((EnrollmentTrainingProgressBar) this.rootView.findViewById(R.id.enrollment_progress_bar)).updateCurrentTrainingIndex(this.viewModel.getUiModel().getCurrentTrainingIndex().intValue(), true);
            if (isRepeatBackEnabled()) {
                return;
            }
            startVoiceCapture(this.viewModel.getUiModel());
        } else {
            if (isRepeatBackEnabled()) {
                Log.i(TAG, "Repeat back and kids flow");
                releaseConnections();
            }
            this.dialogHelper.showErrorDialog(this, th);
        }
    }

    @VisibleForTesting
    void updateDialogBoxRetryCount(int i) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Log.w(TAG, "activity to get intent from exists");
            activity.getIntent().putExtra(ActivityConstants.RETRY_COUNT, i);
            return;
        }
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.TRAINING_DIALOG_ACTIVITY_IS_NULL);
        Log.w(TAG, "no activity to get intent from");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void updateUI(EnrollmentTrainingUiModel enrollmentTrainingUiModel) {
        Log.i(TAG, "Inside updateUI");
        this.rootView.findViewById(R.id.training_layout).setVisibility(0);
        this.rootView.findViewById(R.id.shim_layout).setVisibility(8);
        if (enrollmentTrainingUiModel.getCurrentState() == EnrollmentStates.COMPLETED) {
            Log.i(TAG, "Enrollment completed, moving on to next screen");
            recordCompletionMetric();
            int size = enrollmentTrainingUiModel.getTrainingPhrases().size();
            updateProgressbar(size, size);
            this.isTrainingCompleted = true;
            this.viewModel.moveToCompletionScreen(this);
            return;
        }
        ((TextView) this.rootView.findViewById(R.id.say)).setText(R.string.training_say);
        int intValue = enrollmentTrainingUiModel.getCurrentTrainingIndex().intValue();
        updateProgressbar(enrollmentTrainingUiModel.getTrainingPhrases().size(), intValue);
        List<String> trainingPhrases = enrollmentTrainingUiModel.getTrainingPhrases();
        if (intValue < trainingPhrases.size()) {
            String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), trainingPhrases.get(intValue), EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            TextView textView = (TextView) this.rootView.findViewById(R.id.training_phrase);
            textView.setText(outline91);
            if (intValue != 0) {
                this.animationHelper.renderVerticalFadeInAnimation(getContext(), textView);
            } else {
                this.animationHelper.renderFadeInAnimation(getContext(), textView);
            }
            if (isRepeatBackEnabled()) {
                return;
            }
            startVoiceCapture(enrollmentTrainingUiModel);
            return;
        }
        Log.i(TAG, "All utterences are spoken by user, moving to completion screen");
        recordCompletionMetric();
        this.isTrainingCompleted = true;
        this.viewModel.moveToCompletionScreen(this);
    }

    public EnrollmentTrainingViewFragment(EnrollmentTrainingViewModel enrollmentTrainingViewModel, EnrollmentTrainingDialogHelper enrollmentTrainingDialogHelper, AnimationHelper animationHelper, EnrollmentThemeUtil enrollmentThemeUtil, AlexaStateInteractor alexaStateInteractor, EventBus eventBus, EnrollmentEventBus enrollmentEventBus, AlexaVoiceSDKClient alexaVoiceSDKClient, View view, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        this.viewModel = enrollmentTrainingViewModel;
        this.dialogHelper = enrollmentTrainingDialogHelper;
        this.animationHelper = animationHelper;
        this.enrollmentThemeUtil = enrollmentThemeUtil;
        this.alexaStateInteractor = alexaStateInteractor;
        this.eventBus = eventBus;
        this.enrollmentEventBus = enrollmentEventBus;
        this.alexaVoiceSDKClient = alexaVoiceSDKClient;
        this.rootView = view;
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }
}
