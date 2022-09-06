package com.amazon.alexa.voice.ui;

import android.view.Window;
import androidx.annotation.ColorInt;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
/* loaded from: classes11.dex */
public final class DefaultWindowInteractor implements WindowInteractor {
    @ColorInt
    private final int defaultStatusBarColor;
    private final Window window;

    public DefaultWindowInteractor(Window window) {
        this.window = window;
        this.defaultStatusBarColor = window.getStatusBarColor();
    }

    private void setStatusBarBackgroundColor(@ColorInt int i) {
        this.window.clearFlags(67108864);
        this.window.addFlags(Integer.MIN_VALUE);
        this.window.setStatusBarColor(i);
    }

    @Override // com.amazon.alexa.voice.ui.window.WindowInteractor
    public void restoreDefaultStatusBarColor() {
        setStatusBarBackgroundColor(this.defaultStatusBarColor);
    }

    @Override // com.amazon.alexa.voice.ui.window.WindowInteractor
    public void setStatusBarColor(@ColorInt int i) {
        setStatusBarBackgroundColor(i);
    }
}
