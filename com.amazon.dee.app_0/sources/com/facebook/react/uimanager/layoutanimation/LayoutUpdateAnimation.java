package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
class LayoutUpdateAnimation extends AbstractLayoutAnimation {
    private static final boolean USE_TRANSLATE_ANIMATION = false;

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    @Nullable
    Animation createAnimationImpl(View view, int i, int i2, int i3, int i4) {
        boolean z = false;
        boolean z2 = (view.getX() == ((float) i) && view.getY() == ((float) i2)) ? false : true;
        if (view.getWidth() != i3 || view.getHeight() != i4) {
            z = true;
        }
        if (z2 || z) {
            return new PositionAndSizeAnimation(view, i, i2, i3, i4);
        }
        return null;
    }

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    boolean isValid() {
        return this.mDurationMs > 0;
    }
}
