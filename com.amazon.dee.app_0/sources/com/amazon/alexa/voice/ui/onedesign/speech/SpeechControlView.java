package com.amazon.alexa.voice.ui.onedesign.speech;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlContract;
import com.amazon.alexa.voice.ui.onedesign.util.DisplayUtils;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsRequester;
import com.amazon.alexa.voice.ui.permissions.VoicePermissions;
import com.amazon.alexa.voice.ui.speech.SpeechController;
import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
/* loaded from: classes11.dex */
public final class SpeechControlView extends FrameLayout implements SpeechControlContract.View {
    private final ImageView buttonView;
    private Component component;
    private SpeechControlContract.Presenter presenter;

    public SpeechControlView(Context context) {
        this(context, null, 0);
    }

    private boolean hasV1Components() {
        return this.component.isRegistered(SpeechController.class);
    }

    private boolean hasV2Components() {
        return this.component.isRegistered(AndroidPermissionsChecker.class) && this.component.isRegistered(AndroidPermissionsRequester.class) && this.component.isRegistered(SpeechRecognizer.class);
    }

    private void initializePresenter() {
        SpeechControlContract.Interactor speechControlInteractor;
        if (this.presenter != null) {
            return;
        }
        CardMetricsInteractor cardMetricsInteractor = (CardMetricsInteractor) this.component.get(CardMetricsInteractor.class);
        if (hasV2Components()) {
            speechControlInteractor = new SpeechControlInteractorV2((SpeechRecognizer) this.component.get(SpeechRecognizer.class), (AndroidPermissionsChecker) this.component.get(AndroidPermissionsChecker.class), (AndroidPermissionsRequester) this.component.get(AndroidPermissionsRequester.class));
        } else if (hasV1Components()) {
            VoicePermissions voicePermissions = null;
            if (this.component.isRegistered(VoicePermissions.class)) {
                voicePermissions = (VoicePermissions) this.component.get(VoicePermissions.class);
            }
            speechControlInteractor = new SpeechControlInteractor((SpeechController) this.component.get(SpeechController.class), voicePermissions);
        } else {
            throw new IllegalStateException("Missing dependencies");
        }
        this.presenter = new SpeechControlPresenter(this, speechControlInteractor, cardMetricsInteractor);
        this.presenter.start();
        this.buttonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.speech.-$$Lambda$SpeechControlView$JUf2JY-kagZfYY1oyaZRKVeaJY0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpeechControlView.this.lambda$initializePresenter$0$SpeechControlView(view);
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlContract.View
    public void displayVoiceIngressButton(boolean z) {
        if (z) {
            this.buttonView.setVisibility(0);
        } else {
            this.buttonView.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$initializePresenter$0$SpeechControlView(View view) {
        this.presenter.buttonClicked();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.component != null) {
            initializePresenter();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.buttonView.setOnClickListener(null);
    }

    public void setComponent(Component component) {
        if (this.component == null) {
            this.component = component;
            if (!isAttachedToWindow()) {
                return;
            }
            initializePresenter();
            return;
        }
        throw new IllegalStateException("A component is already attached to a speech controller");
    }

    public SpeechControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SpeechControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_voice_ui_od_ingress);
        String string = context.getResources().getString(R.string.voice_ui_od_ingress);
        int transformDpiToPx = DisplayUtils.transformDpiToPx(context, 56);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(transformDpiToPx, transformDpiToPx);
        layoutParams.gravity = 81;
        layoutParams.bottomMargin = DisplayUtils.transformDpiToPx(context, 24);
        this.buttonView = new ImageView(context);
        this.buttonView.setLayoutParams(layoutParams);
        this.buttonView.setScaleType(ImageView.ScaleType.CENTER);
        this.buttonView.setBackground(ContextCompat.getDrawable(context, R.drawable.voice_ui_od_ingress_background));
        this.buttonView.setImageDrawable(drawable);
        this.buttonView.setContentDescription(string);
        this.buttonView.setSoundEffectsEnabled(false);
        addView(this.buttonView);
    }
}
