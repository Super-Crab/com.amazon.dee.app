package com.amazon.alexa.protocols.ui;
/* loaded from: classes9.dex */
public interface TabBarAnimator {

    /* loaded from: classes9.dex */
    public enum AnimationSpeed {
        IMMEDIATE,
        SHORT
    }

    void hide(AnimationSpeed animationSpeed);

    void reveal(AnimationSpeed animationSpeed);
}
