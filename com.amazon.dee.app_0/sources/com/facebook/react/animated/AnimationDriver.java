package com.facebook.react.animated;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
abstract class AnimationDriver {
    ValueAnimatedNode mAnimatedValue;
    Callback mEndCallback;
    boolean mHasFinished = false;
    int mId;

    public void resetConfig(ReadableMap readableMap) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Animation config for ");
        outline107.append(getClass().getSimpleName());
        outline107.append(" cannot be reset");
        throw new JSApplicationCausedNativeException(outline107.toString());
    }

    public abstract void runAnimationStep(long j);
}
