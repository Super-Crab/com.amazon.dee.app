package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.IllegalViewOperationException;
/* loaded from: classes2.dex */
abstract class BaseLayoutAnimation extends AbstractLayoutAnimation {

    /* renamed from: com.facebook.react.uimanager.layoutanimation.BaseLayoutAnimation$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType = new int[AnimatedPropertyType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType[AnimatedPropertyType.OPACITY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType[AnimatedPropertyType.SCALE_XY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType[AnimatedPropertyType.SCALE_X.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType[AnimatedPropertyType.SCALE_Y.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    Animation createAnimationImpl(View view, int i, int i2, int i3, int i4) {
        AnimatedPropertyType animatedPropertyType = this.mAnimatedProperty;
        if (animatedPropertyType != null) {
            int ordinal = animatedPropertyType.ordinal();
            float f = 0.0f;
            if (ordinal == 0) {
                float alpha = isReverse() ? view.getAlpha() : 0.0f;
                if (!isReverse()) {
                    f = view.getAlpha();
                }
                return new OpacityAnimation(view, alpha, f);
            } else if (ordinal == 1) {
                return new ScaleAnimation(isReverse() ? 1.0f : 0.0f, isReverse() ? 0.0f : 1.0f, 1.0f, 1.0f, 1, 0.5f, 1, 0.0f);
            } else if (ordinal == 2) {
                return new ScaleAnimation(1.0f, 1.0f, isReverse() ? 1.0f : 0.0f, isReverse() ? 0.0f : 1.0f, 1, 0.0f, 1, 0.5f);
            } else if (ordinal == 3) {
                float f2 = isReverse() ? 1.0f : 0.0f;
                float f3 = isReverse() ? 0.0f : 1.0f;
                return new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Missing animation for property : ");
                outline107.append(this.mAnimatedProperty);
                throw new IllegalViewOperationException(outline107.toString());
            }
        }
        throw new IllegalViewOperationException("Missing animated property from animation config");
    }

    abstract boolean isReverse();

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    boolean isValid() {
        return this.mDurationMs > 0 && this.mAnimatedProperty != null;
    }
}
