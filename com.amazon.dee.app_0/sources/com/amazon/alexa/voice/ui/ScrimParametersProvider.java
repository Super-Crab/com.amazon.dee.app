package com.amazon.alexa.voice.ui;

import android.content.Intent;
import android.text.TextUtils;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParameters;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParametersModel;
/* loaded from: classes11.dex */
public class ScrimParametersProvider {
    static final String EXTRA_SCRIM_HIDE_CANCEL_BUTTON = "scrim_hide_cancel_button";
    public static final String EXTRA_SCRIM_HINT_TEXT = "hint";
    static final String EXTRA_SCRIM_TRANSPARENT_BACKGROUND = "scrim_tansparent_background";
    private boolean hideCancelButton;
    private String hintText;
    private boolean showHint;
    private boolean transparentBackground;
    private final TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler;

    public ScrimParametersProvider(TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler) {
        this.typeToAlexaFeatureEnabler = typeToAlexaFeatureEnabler;
        reset();
    }

    private void reset() {
        this.hideCancelButton = false;
        this.transparentBackground = false;
        this.showHint = false;
        this.hintText = null;
    }

    public ScrimParametersModel get() {
        return new ScrimParameters.Builder().hideCancelButton(this.hideCancelButton).transparentBackground(this.transparentBackground).showHint(this.showHint).hint(this.hintText).showTTAIngress(this.typeToAlexaFeatureEnabler.isTypeToAlexaAvailable()).build();
    }

    public ScrimParametersModel provideAndReset() {
        ScrimParametersModel scrimParametersModel = get();
        reset();
        return scrimParametersModel;
    }

    public void setShowHint(boolean z) {
        this.showHint = z;
    }

    public void update(Intent intent) {
        this.hideCancelButton = intent.getBooleanExtra(EXTRA_SCRIM_HIDE_CANCEL_BUTTON, this.hideCancelButton);
        this.transparentBackground = intent.getBooleanExtra(EXTRA_SCRIM_TRANSPARENT_BACKGROUND, this.transparentBackground);
        String stringExtra = intent.getStringExtra("hint");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.hintText = stringExtra;
            this.showHint = true;
        }
    }
}
