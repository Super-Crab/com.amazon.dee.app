package com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.ui.utils.AnimationHelper;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.ui.views.TopDockedToast;
import com.amazon.alexa.handsfree.ui.views.URLTextView;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class EnrollmentCompleteFragment extends Fragment {
    private static final String TAG = "EnrollmentCompleteFrag";
    private final AnimationHelper mAnimationHelper;
    private EnrollmentCompleteCallback mEnrollmentCompleteCallback;
    private EnrollmentCompletePresenter mEnrollmentCompletePresenter;
    private Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private FinalStepMetricsRecorder mFinalStepMetricsRecorder;
    private boolean mInAisFlow;
    private boolean toastShown;

    /* loaded from: classes8.dex */
    public interface EnrollmentCompleteCallback {
        void onLockScreenSettingFinished();
    }

    public EnrollmentCompleteFragment(@NonNull boolean z) {
        this.toastShown = false;
        this.mAnimationHelper = new AnimationHelper();
        this.mInAisFlow = z;
    }

    private void animateCheckIcon(@NonNull View view) {
        ((AnimationDrawable) view.getBackground()).start();
    }

    private void animateDoneButton(@NonNull View view) {
        Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.done_button_fade_in);
        loadAnimator.setTarget(view);
        loadAnimator.start();
    }

    private void animateSwitchContainer(@NonNull View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.uvr_switch_fade_in);
        loadAnimator.setTarget(view);
        ObjectAnimator duration = new ObjectAnimator().setDuration(getResources().getInteger(R.integer.uvr_finish_switch_animation_duration));
        duration.setTarget(view);
        duration.setStartDelay(getResources().getInteger(R.integer.uvr_lockscreen_animation_delay));
        duration.setInterpolator(AnimationUtils.loadInterpolator(getContext(), R.anim.decelerate_interpolator));
        applySwitchTranslationAnimation(duration, getActivity());
        animatorSet.playTogether(loadAnimator, duration);
        animatorSet.start();
    }

    @NonNull
    private EnrollmentCompletePresenter createEnrollmentCompletePresenter(@NonNull RadioGroup radioGroup) {
        return new EnrollmentCompletePresenter(getContext(), new LockScreenSettingView(radioGroup, getContext(), this.mFinalStepMetricsRecorder));
    }

    @VisibleForTesting
    void applySwitchTranslationAnimation(@NonNull ObjectAnimator objectAnimator, @NonNull Activity activity) {
        this.mAnimationHelper.applyTranslation(objectAnimator, activity, AnimationHelper.RelativeDimension.SCREEN_WIDTH, AnimationHelper.TranslationAxis.VERTICAL, activity.getResources().getFraction(R.fraction.uvr_vertical_translation_screen_width_ratio, 1, 1));
    }

    @VisibleForTesting
    void configureDoneButton(@NonNull View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.EnrollmentCompleteFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                EnrollmentCompleteFragment.this.mFinalStepMetricsRecorder.logClickOnDoneButton();
                if (EnrollmentCompleteFragment.this.mEnrollmentCompleteCallback != null) {
                    EnrollmentCompleteFragment.this.mEnrollmentCompleteCallback.onLockScreenSettingFinished();
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EnrollmentCompleteCallback) {
            this.mEnrollmentCompleteCallback = (EnrollmentCompleteCallback) context;
            return;
        }
        throw new ClassCastException("Attached context has to implement EnrollmentCompleteCallback");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        this.mEnrollmentTypeResolverLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(getActivity().getApplicationContext(), FalcoProtocolComponent.class)).enrollmentTypeResolverLazy();
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        return LayoutInflater.from(new ContextThemeWrapper(getContext(), R.style.Theme_Mosaic_Jasper)).inflate(R.layout.mosaic_fragment_enrollment_complete, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mEnrollmentCompleteCallback = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mEnrollmentCompletePresenter.updateShowOnLockScreenValue();
        EnrollmentTypeResolver mo358get = this.mEnrollmentTypeResolverLazy.mo358get();
        if (mo358get == null || mo358get.getSpeakerVerificationEnrollmentType() != EnrollmentType._1PSV || mo358get.getEnrollmentType() == EnrollmentType._1PSV_DECOUPLED || this.toastShown || this.mInAisFlow) {
            return;
        }
        TopDockedToast.newInstance(getActivity()).setText(getResources().getString(R.string.uvr_enrollment_complete_toast)).setDuration(1).show();
        this.toastShown = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mFinalStepMetricsRecorder = new FinalStepMetricsRecorder(getContext());
        View findViewById = view.findViewById(R.id.done_button);
        View findViewById2 = view.findViewById(R.id.complete_icon);
        View findViewById3 = view.findViewById(R.id.sliding_container);
        URLTextView uRLTextView = (URLTextView) view.findViewById(R.id.disclaimer_text);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Bold, view.findViewById(R.id.title_text), findViewById);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Regular, view.findViewById(R.id.explainer_header_text), view.findViewById(R.id.disclaimer_text), uRLTextView, view.findViewById(R.id.response_while_locked_on), view.findViewById(R.id.response_while_locked_off));
        FontAdapter.setFont(FontAdapter.FontName.AmazonBookerly_Italic, view.findViewById(R.id.utterance_hint_text));
        this.mEnrollmentCompletePresenter = createEnrollmentCompletePresenter((RadioGroup) view.findViewById(R.id.respond_while_locked_radiogroup));
        this.mEnrollmentCompletePresenter.initialize();
        configureDoneButton(findViewById);
        animateDoneButton(findViewById);
        animateCheckIcon(findViewById2);
        if (!this.mEnrollmentCompletePresenter.isBlockSensitiveRequest()) {
            animateSwitchContainer(findViewById3);
            setupDisclaimerView(uRLTextView);
        }
    }

    @VisibleForTesting
    void setupDisclaimerView(@NonNull URLTextView uRLTextView) {
        uRLTextView.setURLText(R.string.uvr_enrollment_complete_disclaimer_insecure);
    }

    @VisibleForTesting
    EnrollmentCompleteFragment(@NonNull AnimationHelper animationHelper, @NonNull FinalStepMetricsRecorder finalStepMetricsRecorder, @NonNull EnrollmentCompletePresenter enrollmentCompletePresenter, @Nullable EnrollmentCompleteCallback enrollmentCompleteCallback, @NonNull Lazy<EnrollmentTypeResolver> lazy, @NonNull boolean z) {
        this.toastShown = false;
        this.mAnimationHelper = animationHelper;
        this.mFinalStepMetricsRecorder = finalStepMetricsRecorder;
        this.mEnrollmentCompletePresenter = enrollmentCompletePresenter;
        this.mEnrollmentCompleteCallback = enrollmentCompleteCallback;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mInAisFlow = z;
    }
}
