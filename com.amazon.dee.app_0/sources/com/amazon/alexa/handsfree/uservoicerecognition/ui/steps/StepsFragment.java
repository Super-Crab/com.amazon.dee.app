package com.amazon.alexa.handsfree.uservoicerecognition.ui.steps;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.utils.AlertDialogBuilderFactory;
import com.amazon.alexa.handsfree.ui.utils.AnimationHelper;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.ui.views.ActivityIndicator;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.audio.ExternalTrainingSoundPlayer;
import com.amazon.alexa.handsfree.uservoicerecognition.audio.TrainingSoundPlayer;
import com.amazon.alexa.handsfree.uservoicerecognition.model.EnrollmentStep;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract;
import com.amazon.alexa.voice.ui.widget.ChromeView;
/* loaded from: classes8.dex */
public class StepsFragment extends Fragment implements StepsContract.View {
    private static final String TAG = StepsFragment.class.getSimpleName();
    private static final int UNUSUAL_LOAD_TIME_IN_MS = 760;
    private ActivityIndicator mActivityIndicator;
    private AlertDialogBuilderFactory mAlertDialogBuilderFactory;
    private final AnimationHelper mAnimationHelper;
    private ChromeView mChromeView;
    private StepsFragmentCallback mFragmentCallback;
    private TextView mInstructionTextView;
    private final Handler mMainThreadHandler;
    private ProgressView mProgressView;
    private Runnable mShowLoadingRunnable;
    private StepsMetricsRecorder mStepsMetricsRecorder;
    private TextView mUtteranceTextView;
    private StepsContract.ViewListener mViewListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public class InternalVoiceChromeProvider implements StepsContract.VoiceChrome {
        private static final float CHROME_COMPRESSION_FACTOR = 2.0f;
        private static final float CHROME_VIEW_RECORDING = 0.15f;
        private static final float CHROME_VIEW_SILENCE = 1.0f;

        /* renamed from: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment$InternalVoiceChromeProvider$1  reason: invalid class name */
        /* loaded from: classes8.dex */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                StepsFragment.this.mChromeView.setLevelAnimated(1.0f);
                StepsFragment.this.mMainThreadHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.InternalVoiceChromeProvider.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        StepsFragment.this.mAnimationHelper.applyAnimatorResource(R.animator.chrome_bar_fade_out, StepsFragment.this.mChromeView, new AnimatorListenerAdapter() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.InternalVoiceChromeProvider.1.1.1
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationEnd(@NonNull Animator animator) {
                                Log.d(StepsFragment.TAG, "dismissVoiceChrome onAnimationEnd called.");
                                StepsFragment.this.mViewListener.onVoiceChromeDismissed();
                            }
                        });
                    }
                }, StepsFragment.this.getResources().getInteger(R.integer.uvr_chrome_bar_pulse_duration));
            }
        }

        InternalVoiceChromeProvider() {
        }

        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
        public void dismissVoiceChrome(@NonNull StepsContract.ViewListener viewListener) {
            Log.d(StepsFragment.TAG, "dismissVoiceChrome");
            StepsFragment.this.runOnUIThreadIfAvailable("dismissVoiceChrome", new AnonymousClass1());
        }

        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
        public void setVoiceChromeLevelOnFeedback(final float f) {
            StepsFragment.this.runOnUIThreadIfAvailable("setVoiceChromeLevelOnFeedback", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.InternalVoiceChromeProvider.2
                @Override // java.lang.Runnable
                public void run() {
                    StepsFragment.this.mChromeView.setLevelAnimated(InternalVoiceChromeProvider.CHROME_VIEW_RECORDING - (f * InternalVoiceChromeProvider.CHROME_COMPRESSION_FACTOR));
                }
            });
        }

        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
        public void setVoiceChromeNotRecording() {
            StepsFragment.this.runOnUIThreadIfAvailable("setVoiceChromeNotRecording", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.InternalVoiceChromeProvider.5
                @Override // java.lang.Runnable
                public void run() {
                    StepsFragment.this.mChromeView.setLevelAnimated(1.0f);
                    StepsFragment.this.mMainThreadHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.InternalVoiceChromeProvider.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            StepsFragment.this.mChromeView.hideCyanOverlay();
                        }
                    }, StepsFragment.this.getResources().getInteger(R.integer.uvr_chrome_bar_pulse_duration));
                }
            });
        }

        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
        public void setVoiceChromeRecording() {
            StepsFragment.this.runOnUIThreadIfAvailable("setVoiceChromeRecording", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.InternalVoiceChromeProvider.4
                @Override // java.lang.Runnable
                public void run() {
                    StepsFragment.this.mChromeView.setLevel(1.0f);
                    StepsFragment.this.mChromeView.setLevelAnimated(InternalVoiceChromeProvider.CHROME_VIEW_RECORDING);
                }
            });
        }

        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
        public void showVoiceChrome() {
            StepsFragment.this.runOnUIThreadIfAvailable("showVoiceChrome", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.InternalVoiceChromeProvider.3
                @Override // java.lang.Runnable
                public void run() {
                    StepsFragment.this.mChromeView.hideCyanOverlay();
                    StepsFragment.this.mAnimationHelper.applyAnimatorResource(R.animator.chrome_bar_fade_in, StepsFragment.this.mChromeView);
                }
            });
        }
    }

    /* loaded from: classes8.dex */
    public interface StepsFragmentCallback {
        void loadStartEnrollmentFragment();

        void onFinishEnrollment();
    }

    public StepsFragment() {
        this.mAnimationHelper = new AnimationHelper();
        this.mMainThreadHandler = new Handler(Looper.getMainLooper());
    }

    private Animator getAnimator(@NonNull View view, @NonNull int i) {
        Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), i);
        loadAnimator.setTarget(view);
        return loadAnimator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getErrorCodeFinalString(@NonNull String str) {
        return (str == null || str.isEmpty()) ? "" : String.format(" (%s #%s)", getContext().getResources().getString(R.string.error_code_prefix), str);
    }

    @VisibleForTesting
    void applyUtteranceTranslationAnimation(@NonNull ObjectAnimator objectAnimator, @NonNull Activity activity) {
        this.mAnimationHelper.applyTranslation(objectAnimator, activity, AnimationHelper.RelativeDimension.SCREEN_WIDTH, AnimationHelper.TranslationAxis.VERTICAL, activity.getResources().getFraction(R.fraction.uvr_vertical_translation_screen_width_ratio, 1, 1));
    }

    @VisibleForTesting
    void fadeInInstructionText() {
        this.mAnimationHelper.applyAnimatorResource(R.animator.fade_in_early, this.mInstructionTextView);
    }

    @VisibleForTesting
    Animator getFadeInAnimator(@NonNull View view) {
        return getAnimator(view, R.animator.fade_in_early);
    }

    Animator getFadeOutAnimator(@NonNull View view) {
        return getAnimator(view, R.animator.exit_fade_out);
    }

    @VisibleForTesting
    AnimatorSet getNewAnimatorSet() {
        return new AnimatorSet();
    }

    @VisibleForTesting
    Runnable getShowLoadingRunnable() {
        return new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.12
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment stepsFragment = StepsFragment.this;
                Animator fadeInAnimator = stepsFragment.getFadeInAnimator(stepsFragment.mActivityIndicator);
                StepsFragment.this.mActivityIndicator.setVisibility(0);
                StepsFragment.this.mActivityIndicator.startAnimation();
                fadeInAnimator.start();
            }
        };
    }

    @VisibleForTesting
    StepsContract.SoundPlayer getSoundPlayer(boolean z) {
        return z ? new ExternalTrainingSoundPlayer() : new TrainingSoundPlayer(getContext());
    }

    @VisibleForTesting
    AnimatorSet getUtteranceAnimatorSet() {
        AnimatorSet newAnimatorSet = getNewAnimatorSet();
        Animator loadAnimator = AnimatorInflater.loadAnimator(getActivity(), R.animator.utterance_fade_in);
        loadAnimator.setTarget(this.mUtteranceTextView);
        ObjectAnimator duration = new ObjectAnimator().setDuration(getResources().getInteger(R.integer.uvr_steps_utterance_animation_duration));
        duration.setTarget(this.mUtteranceTextView);
        duration.setInterpolator(AnimationUtils.loadInterpolator(getActivity(), R.anim.decelerate_interpolator));
        applyUtteranceTranslationAnimation(duration, getActivity());
        newAnimatorSet.playTogether(duration, loadAnimator);
        return newAnimatorSet;
    }

    @VisibleForTesting
    StepsContract.VoiceChrome getVoiceChromeProvider(boolean z) {
        return z ? new UnifiedUVRVoiceChromeProvider() : new InternalVoiceChromeProvider();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void initializeSteps(final int i) {
        runOnUIThreadIfAvailable("initializeSteps", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.1
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment.this.mProgressView.initialize(i, EnrollmentStep.TrainingState.NOT_STARTED);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mStepsMetricsRecorder = new StepsMetricsRecorder(context);
        if (context instanceof StepsFragmentCallback) {
            this.mFragmentCallback = (StepsFragmentCallback) context;
            return;
        }
        throw new ClassCastException(context.toString() + " must implement StepsFragmentCallback");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        boolean z;
        super.onCreate(bundle);
        EnrollmentTypeResolver mo358get = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(getActivity().getApplicationContext(), FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get();
        boolean z2 = false;
        if (mo358get != null) {
            boolean z3 = mo358get.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV;
            if (mo358get.getEnrollmentType() == EnrollmentType._1PSV_DUAL) {
                z2 = true;
            }
            z = z3;
        } else {
            z = false;
        }
        this.mViewListener = new StepsPresenter(this, getVoiceChromeProvider(z2), getSoundPlayer(z2), getContext(), z);
        this.mAlertDialogBuilderFactory = new AlertDialogBuilderFactory(getContext(), R.style.AlertDialogBackground);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        return LayoutInflater.from(new ContextThemeWrapper(getContext(), R.style.Theme_Mosaic_Jasper)).inflate(R.layout.mosaic_fragment_steps, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mFragmentCallback = null;
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void onFinishEnrollment() {
        this.mFragmentCallback.onFinishEnrollment();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        this.mViewListener.pauseEnrollment();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        this.mViewListener.resumeEnrollment();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mProgressView = (ProgressView) view.findViewById(R.id.progress_view);
        this.mUtteranceTextView = (TextView) view.findViewById(R.id.keyword_text);
        this.mInstructionTextView = (TextView) view.findViewById(R.id.instruction_text);
        this.mChromeView = (ChromeView) view.findViewById(R.id.chrome_view);
        this.mActivityIndicator = (ActivityIndicator) view.findViewById(R.id.activity_indicator);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Light, this.mInstructionTextView);
        FontAdapter.setFont(FontAdapter.FontName.AmazonBookerly_Light_Italic, this.mUtteranceTextView);
        this.mAnimationHelper.applyAnimatorResource(R.animator.fade_in_early, this.mProgressView);
        this.mViewListener.initializeView();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void readyToListen(@NonNull final String str) {
        runOnUIThreadIfAvailable("readyToListen", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.14
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment.this.mMainThreadHandler.removeCallbacks(StepsFragment.this.mShowLoadingRunnable);
                StepsFragment.this.mUtteranceTextView.setText(StepsFragment.this.getString(R.string.uvr_steps_keyword, str));
                StepsFragment stepsFragment = StepsFragment.this;
                Animator fadeInAnimator = stepsFragment.getFadeInAnimator(stepsFragment.mInstructionTextView);
                StepsFragment stepsFragment2 = StepsFragment.this;
                Animator fadeInAnimator2 = stepsFragment2.getFadeInAnimator(stepsFragment2.mUtteranceTextView);
                StepsFragment stepsFragment3 = StepsFragment.this;
                Animator fadeOutAnimator = stepsFragment3.getFadeOutAnimator(stepsFragment3.mActivityIndicator);
                AnimatorSet newAnimatorSet = StepsFragment.this.getNewAnimatorSet();
                newAnimatorSet.playTogether(fadeInAnimator, fadeInAnimator2, fadeOutAnimator);
                newAnimatorSet.start();
                newAnimatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.14.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(@NonNull Animator animator) {
                        StepsFragment.this.mActivityIndicator.setVisibility(4);
                    }
                });
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void removeErrorMessageAndUtteranceText() {
        runOnUIThreadIfAvailable("removeErrorMessageAndUtteranceText", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.8
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment stepsFragment = StepsFragment.this;
                Animator fadeOutAnimator = stepsFragment.getFadeOutAnimator(stepsFragment.mInstructionTextView);
                StepsFragment stepsFragment2 = StepsFragment.this;
                Animator fadeOutAnimator2 = stepsFragment2.getFadeOutAnimator(stepsFragment2.mUtteranceTextView);
                AnimatorSet newAnimatorSet = StepsFragment.this.getNewAnimatorSet();
                newAnimatorSet.playTogether(fadeOutAnimator, fadeOutAnimator2);
                newAnimatorSet.start();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void removeUtteranceText() {
        runOnUIThreadIfAvailable("removeUtteranceText", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.4
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment stepsFragment = StepsFragment.this;
                stepsFragment.getFadeOutAnimator(stepsFragment.mUtteranceTextView).start();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void restartTraining() {
        runOnUIThreadIfAvailable("restartTraining", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.11
            @Override // java.lang.Runnable
            public void run() {
                if (StepsFragment.this.isAdded()) {
                    StepsFragment.this.mFragmentCallback.loadStartEnrollmentFragment();
                    StepsFragment.this.mStepsMetricsRecorder.reportMetricsForSuccessfulStepsFragmentCallback();
                    return;
                }
                StepsFragment.this.mStepsMetricsRecorder.reportMetricsForFailedStepsFragmentCallback(AnonymousClass11.class.getEnclosingMethod().getName());
            }
        });
    }

    void runOnUIThreadIfAvailable(@NonNull String str, @NonNull Runnable runnable) {
        if (getActivity() == null) {
            String str2 = TAG;
            Log.i(str2, "runOnUIThreadIfAvailable: Activity has been killed, skip " + str);
            this.mViewListener.cancelEnrollment();
            return;
        }
        getActivity().runOnUiThread(runnable);
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void setStepState(final int i, final EnrollmentStep.TrainingState trainingState) {
        runOnUIThreadIfAvailable("setStepState", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.2
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment.this.mProgressView.setStepState(i, trainingState, true);
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void showAnimatedErrorText(final String str) {
        runOnUIThreadIfAvailable("showAnimatedErrorText", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.6
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment stepsFragment = StepsFragment.this;
                Animator fadeOutAnimator = stepsFragment.getFadeOutAnimator(stepsFragment.mInstructionTextView);
                StepsFragment stepsFragment2 = StepsFragment.this;
                Animator fadeOutAnimator2 = stepsFragment2.getFadeOutAnimator(stepsFragment2.mUtteranceTextView);
                AnimatorSet newAnimatorSet = StepsFragment.this.getNewAnimatorSet();
                newAnimatorSet.playTogether(fadeOutAnimator, fadeOutAnimator2);
                newAnimatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.6.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(@NonNull Animator animator) {
                        StepsFragment.this.mInstructionTextView.setText(str);
                        StepsFragment.this.fadeInInstructionText();
                    }
                });
                newAnimatorSet.start();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void showInstructionAndUtteranceText(@NonNull final String str) {
        runOnUIThreadIfAvailable("showInstructionAndUtteranceText", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.5
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment.this.mInstructionTextView.setText(R.string.uvr_steps_say);
                StepsFragment.this.mUtteranceTextView.setText(StepsFragment.this.getString(R.string.uvr_steps_keyword, str));
                StepsFragment stepsFragment = StepsFragment.this;
                Animator fadeInAnimator = stepsFragment.getFadeInAnimator(stepsFragment.mInstructionTextView);
                AnimatorSet utteranceAnimatorSet = StepsFragment.this.getUtteranceAnimatorSet();
                AnimatorSet newAnimatorSet = StepsFragment.this.getNewAnimatorSet();
                newAnimatorSet.playTogether(fadeInAnimator, utteranceAnimatorSet);
                newAnimatorSet.start();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void showLoadingAfterVoiceTraining() {
        runOnUIThreadIfAvailable("showLoadingAfterVoiceTraining", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.13
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment stepsFragment = StepsFragment.this;
                Animator fadeOutAnimator = stepsFragment.getFadeOutAnimator(stepsFragment.mInstructionTextView);
                StepsFragment stepsFragment2 = StepsFragment.this;
                Animator fadeOutAnimator2 = stepsFragment2.getFadeOutAnimator(stepsFragment2.mUtteranceTextView);
                AnimatorSet newAnimatorSet = StepsFragment.this.getNewAnimatorSet();
                newAnimatorSet.playTogether(fadeOutAnimator, fadeOutAnimator2);
                newAnimatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.13.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(@NonNull Animator animator) {
                        StepsFragment stepsFragment3 = StepsFragment.this;
                        Animator fadeInAnimator = stepsFragment3.getFadeInAnimator(stepsFragment3.mInstructionTextView);
                        StepsFragment stepsFragment4 = StepsFragment.this;
                        Animator fadeInAnimator2 = stepsFragment4.getFadeInAnimator(stepsFragment4.mActivityIndicator);
                        StepsFragment.this.mInstructionTextView.setText(StepsFragment.this.getString(R.string.uvr_steps_building_voice_model_message));
                        StepsFragment.this.mActivityIndicator.setVisibility(0);
                        StepsFragment.this.mActivityIndicator.startAnimation();
                        AnimatorSet newAnimatorSet2 = StepsFragment.this.getNewAnimatorSet();
                        newAnimatorSet2.playTogether(fadeInAnimator, fadeInAnimator2);
                        newAnimatorSet2.start();
                    }
                });
                newAnimatorSet.start();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void showLoadingBeforeVoiceTraining() {
        this.mShowLoadingRunnable = getShowLoadingRunnable();
        this.mMainThreadHandler.postDelayed(this.mShowLoadingRunnable, 760L);
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void showNotWorkingDialog() {
        showNotWorkingDialog(R.string.uvr_steps_not_working_dialog_title, R.string.uvr_steps_not_working_dialog_message, "");
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void showUtteranceText(@NonNull final String str) {
        runOnUIThreadIfAvailable("showUtteranceText", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.3
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment.this.mUtteranceTextView.setText(StepsFragment.this.getString(R.string.uvr_steps_keyword, str));
                StepsFragment.this.getUtteranceAnimatorSet().start();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void showAnimatedErrorText(final int i) {
        runOnUIThreadIfAvailable("showAnimatedErrorText", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.7
            @Override // java.lang.Runnable
            public void run() {
                StepsFragment stepsFragment = StepsFragment.this;
                Animator fadeOutAnimator = stepsFragment.getFadeOutAnimator(stepsFragment.mInstructionTextView);
                StepsFragment stepsFragment2 = StepsFragment.this;
                Animator fadeOutAnimator2 = stepsFragment2.getFadeOutAnimator(stepsFragment2.mUtteranceTextView);
                AnimatorSet newAnimatorSet = StepsFragment.this.getNewAnimatorSet();
                newAnimatorSet.playTogether(fadeOutAnimator, fadeOutAnimator2);
                newAnimatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.7.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(@NonNull Animator animator) {
                        TextView textView = StepsFragment.this.mInstructionTextView;
                        AnonymousClass7 anonymousClass7 = AnonymousClass7.this;
                        textView.setText(StepsFragment.this.getString(i));
                        StepsFragment.this.fadeInInstructionText();
                    }
                });
                newAnimatorSet.start();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void showNotWorkingDialog(@StringRes final int i) {
        runOnUIThreadIfAvailable("showNotWorkingDialog", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.9
            @Override // java.lang.Runnable
            public void run() {
                if (StepsFragment.this.isAdded()) {
                    StepsFragment.this.mAlertDialogBuilderFactory.create().setMessage(i).setPositiveButton(R.string.uvr_steps_not_working_dialog_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.9.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(@NonNull DialogInterface dialogInterface, int i2) {
                            StepsFragment.this.mFragmentCallback.loadStartEnrollmentFragment();
                            StepsFragment.this.mStepsMetricsRecorder.reportMetricsForSuccessfulStepsFragmentCallback();
                        }
                    }).setCancelable(false).show();
                    return;
                }
                StepsFragment.this.mStepsMetricsRecorder.reportMetricsForFailedStepsFragmentCallback(AnonymousClass9.class.getEnclosingMethod().getName());
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.View
    public void showNotWorkingDialog(@StringRes final int i, @StringRes final int i2, final String str) {
        runOnUIThreadIfAvailable("showNotWorkingDialog", new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.10
            @Override // java.lang.Runnable
            public void run() {
                String str2;
                if (StepsFragment.this.isAdded()) {
                    AlertDialog.Builder create = StepsFragment.this.mAlertDialogBuilderFactory.create();
                    int i3 = i;
                    if (i3 == 0) {
                        i3 = R.string.uvr_steps_not_working_dialog_title;
                    }
                    AlertDialog.Builder title = create.setTitle(i3);
                    if (i2 == 0) {
                        str2 = StepsFragment.this.getContext().getResources().getString(R.string.uvr_steps_not_working_dialog_message);
                    } else {
                        str2 = StepsFragment.this.getContext().getResources().getString(i2) + StepsFragment.this.getErrorCodeFinalString(str);
                    }
                    title.setMessage(str2).setPositiveButton(R.string.uvr_steps_not_working_dialog_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsFragment.10.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(@NonNull DialogInterface dialogInterface, int i4) {
                            StepsFragment.this.mFragmentCallback.loadStartEnrollmentFragment();
                            StepsFragment.this.mStepsMetricsRecorder.reportMetricsForSuccessfulStepsFragmentCallback();
                        }
                    }).setCancelable(false).show();
                    return;
                }
                StepsFragment.this.mStepsMetricsRecorder.reportMetricsForFailedStepsFragmentCallback(AnonymousClass10.class.getEnclosingMethod().getName());
            }
        });
    }

    @VisibleForTesting
    StepsFragment(@NonNull AnimationHelper animationHelper, @NonNull StepsContract.ViewListener viewListener, @NonNull AlertDialogBuilderFactory alertDialogBuilderFactory, @NonNull StepsFragmentCallback stepsFragmentCallback, @NonNull ProgressView progressView, @NonNull ChromeView chromeView, @NonNull TextView textView, @NonNull TextView textView2, @NonNull ActivityIndicator activityIndicator, @NonNull StepsMetricsRecorder stepsMetricsRecorder, @NonNull Handler handler, @NonNull Runnable runnable) {
        this.mAnimationHelper = animationHelper;
        this.mViewListener = viewListener;
        this.mAlertDialogBuilderFactory = alertDialogBuilderFactory;
        this.mFragmentCallback = stepsFragmentCallback;
        this.mProgressView = progressView;
        this.mChromeView = chromeView;
        this.mUtteranceTextView = textView;
        this.mInstructionTextView = textView2;
        this.mActivityIndicator = activityIndicator;
        this.mStepsMetricsRecorder = stepsMetricsRecorder;
        this.mMainThreadHandler = handler;
        this.mShowLoadingRunnable = runnable;
    }
}
