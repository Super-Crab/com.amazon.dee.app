package com.amazon.alexa.voice.ui.onedesign.scrim;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParameters;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.LetterSpacing;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voice.ui.speech.SpeechController;
import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import com.amazon.alexa.voice.ui.widget.ChromeView;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes11.dex */
public final class ScrimController extends ViewController implements ScrimContract.View {
    private static final int INITIAL_ANIMATION_DELAY = 200;
    private static final String SCRIM_PARAMETERS = "scrimParameters";
    private static final Set<Locale> SUPPORTED_LOCALES = new HashSet(Arrays.asList(Locale.US));
    private View cancel;
    private ChromeView chromeView;
    private TextView hintText;
    private TextView hintTitle;
    private View hintView;
    private ScrimContract.Presenter presenter;
    private boolean shouldShowCancelButton = true;
    private boolean shouldShowTTAIngress;
    private View ttaIngress;

    private void animateCancelButton() {
        View findViewById = this.cancel.findViewById(R.id.cancel_background);
        AnimationDrawable animationDrawable = (AnimationDrawable) ((ImageView) this.cancel.findViewById(R.id.cancel_img)).getDrawable();
        animationDrawable.setOneShot(true);
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scrim_scale);
        loadAnimation.setInterpolator(new DecelerateInterpolator(1.72f));
        findViewById.startAnimation(loadAnimation);
        animationDrawable.start();
    }

    public static ScrimController create() {
        return create(getDefaultScrimParameters());
    }

    public static ScrimParametersModel getDefaultScrimParameters() {
        return new ScrimParameters.Builder().transparentBackground(false).hideCancelButton(false).showHint(false).build();
    }

    private boolean isLocaleSupported(Locale locale) {
        return SUPPORTED_LOCALES.contains(locale);
    }

    public /* synthetic */ void lambda$onCreateView$0$ScrimController(View view) {
        this.presenter.ttaIngressClicked();
    }

    public /* synthetic */ void lambda$showCancelButton$1$ScrimController(View view) {
        this.presenter.cancelClicked();
    }

    public /* synthetic */ void lambda$showCancelButton$2$ScrimController() {
        this.cancel.setVisibility(0);
        if (!this.shouldShowTTAIngress) {
            animateCancelButton();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        ScrimInteractor scrimInteractor;
        Bundle arguments = getArguments();
        ScrimParametersModel scrimParametersModel = arguments != null ? (ScrimParametersModel) arguments.getParcelable(SCRIM_PARAMETERS) : null;
        if (scrimParametersModel == null) {
            scrimParametersModel = getDefaultScrimParameters();
        }
        if (scrimParametersModel.getTransparentBackground()) {
            overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Scrim_Transparent);
        } else {
            overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Scrim_Dark);
        }
        boolean showTTAIngress = scrimParametersModel.getShowTTAIngress();
        ScrimMediator scrimMediator = new ScrimMediator(this);
        if (getComponent().isRegistered(AttentionSystemContract.class)) {
            scrimInteractor = new ScrimInteractor((AttentionSystemContract) getComponent().get(AttentionSystemContract.class), scrimMediator, scrimParametersModel, (SpeechRecognizer) getComponent().get(SpeechRecognizer.class));
        } else {
            scrimInteractor = new ScrimInteractor((SpeechController) getComponent().get(SpeechController.class), scrimMediator, scrimParametersModel);
        }
        Locale locale = ComponentUtils.getLocale(getComponent());
        this.shouldShowTTAIngress = showTTAIngress && isLocaleSupported(locale) && isLocaleSupported(Locale.getDefault());
        this.presenter = new ScrimPresenter(this, scrimInteractor, new AndroidResources(getContext(), locale));
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(this.shouldShowTTAIngress ? R.layout.voice_ui_od_scrim_tta : R.layout.voice_ui_od_scrim, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        this.chromeView = (ChromeView) inflate.findViewById(R.id.chrome);
        this.cancel = inflate.findViewById(R.id.cancel);
        this.hintView = inflate.findViewById(R.id.hint);
        this.hintTitle = (TextView) inflate.findViewById(R.id.hint_title);
        this.hintText = (TextView) inflate.findViewById(R.id.hint_text);
        LetterSpacing.apply(R.dimen.voice_ui_od_font_letter_spacing, this.hintTitle);
        FontUtils.apply(Font.AMAZON_BOOKERLY_REGULAR_ITALIC, this.hintText);
        if (this.shouldShowTTAIngress) {
            this.ttaIngress = inflate.findViewById(R.id.tta_ingress);
            this.ttaIngress.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.-$$Lambda$ScrimController$dXgmXbVk50BODfjSdLls59klVes
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ScrimController.this.lambda$onCreateView$0$ScrimController(view);
                }
            });
        }
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.chromeView = null;
        this.hintView = null;
        this.hintTitle = null;
        this.hintText = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.stop();
        if (this.shouldShowCancelButton) {
            this.cancel.removeCallbacks(null);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.View
    public void setChromeLevel(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.chromeView.setLevelAnimated(f);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.View
    public void setChromeLocked(boolean z) {
        this.chromeView.setLockedAnimated(z);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.View
    public void setHint(CharSequence charSequence, CharSequence charSequence2) {
        this.hintTitle.setText(charSequence);
        this.hintText.setText(charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.View
    public void setHintVisible(boolean z) {
        this.hintView.setVisibility(z ? 0 : 8);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.View
    public void showCancelButton() {
        this.shouldShowCancelButton = true;
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.-$$Lambda$ScrimController$nn980SyrJ-Z8v0-SOo1YRqXKf-k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScrimController.this.lambda$showCancelButton$1$ScrimController(view);
            }
        });
        this.cancel.postDelayed(new Runnable() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.-$$Lambda$ScrimController$31SbjEnoERVkh1YoSzQkQcm3_BU
            @Override // java.lang.Runnable
            public final void run() {
                ScrimController.this.lambda$showCancelButton$2$ScrimController();
            }
        }, 200L);
    }

    public static ScrimController create(@NonNull ScrimParametersModel scrimParametersModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SCRIM_PARAMETERS, scrimParametersModel);
        ScrimController scrimController = new ScrimController();
        scrimController.setArguments(bundle);
        return scrimController;
    }
}
