package com.amazon.mobile.heremapsexplore;

import android.graphics.Color;
/* loaded from: classes13.dex */
enum UITheme {
    DarkTheme(-16725249, -15394528);
    
    private final int controlActive;
    private final int textPrimaryInverse;

    UITheme(int i, int i2) {
        this.controlActive = i;
        this.textPrimaryInverse = i2;
    }

    public int getControlActive() {
        return this.controlActive;
    }

    public int getControlActiveWithAlpha(float f) {
        return Color.argb(Math.round(Color.alpha(this.controlActive) * f), Color.red(this.controlActive), Color.green(this.controlActive), Color.blue(this.controlActive));
    }

    public int getTextPrimaryInverse() {
        return this.textPrimaryInverse;
    }
}
