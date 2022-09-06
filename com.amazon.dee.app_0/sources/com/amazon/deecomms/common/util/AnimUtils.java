package com.amazon.deecomms.common.util;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.core.view.ViewCompat;
import com.amazon.deecomms.R;
import com.amazon.deecomms.core.CommsDaggerWrapper;
/* loaded from: classes12.dex */
public final class AnimUtils {
    private AnimUtils() {
    }

    @TargetApi(21)
    public static void circularRevealEffect(final View view, final int i, final int i2, final float f, final float f2, final int i3) {
        int i4 = Build.VERSION.SDK_INT;
        view.post(new Runnable() { // from class: com.amazon.deecomms.common.util.AnimUtils.1
            @Override // java.lang.Runnable
            public void run() {
                if (ViewCompat.isAttachedToWindow(view)) {
                    Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f, f2);
                    createCircularReveal.setDuration(i3);
                    createCircularReveal.start();
                }
            }
        });
    }

    public static void fadingAnimation(Context context, View view, int i, boolean z) {
        Animation loadAnimation;
        if (z) {
            loadAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        } else {
            loadAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        }
        loadAnimation.setDuration(i);
        view.setAnimation(loadAnimation);
    }

    public static Animation getFadingAnimation(int i, boolean z) {
        Animation loadAnimation;
        Context context = CommsDaggerWrapper.getComponent().getContext();
        if (z) {
            loadAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        } else {
            loadAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        }
        loadAnimation.setDuration(i);
        return loadAnimation;
    }
}
