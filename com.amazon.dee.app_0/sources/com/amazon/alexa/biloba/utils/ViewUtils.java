package com.amazon.alexa.biloba.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
/* loaded from: classes6.dex */
public final class ViewUtils {
    private ViewUtils() {
    }

    public static void hideView(final View view) {
        int i = Build.VERSION.SDK_INT;
        view.setTranslationX(0.0f);
        view.setAlpha(1.0f);
        view.animate().translationX(view.getWidth()).alpha(0.0f).setDuration(500L).setListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.biloba.utils.ViewUtils.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                view.setVisibility(8);
            }
        }).start();
    }

    public static void interruptAccessibilityIfNeeded(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            accessibilityManager.interrupt();
        }
    }

    public static void showView(final View view) {
        int i = Build.VERSION.SDK_INT;
        view.setTranslationX(view.getWidth());
        view.setAlpha(0.0f);
        view.animate().translationX(0.0f).alpha(1.0f).setDuration(500L).setListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.biloba.utils.ViewUtils.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                view.setVisibility(0);
            }
        }).start();
    }
}
