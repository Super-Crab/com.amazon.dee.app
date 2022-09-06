package com.amazon.dee.app.elements;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.regulator.ControllerTransition;
/* loaded from: classes12.dex */
public class ReactTransitionCompletionListener implements ControllerTransition.CompletionListener {
    public static final int PAGE_TRANSITION_DURATION = 166;
    private static final String TAG = Log.tag(ReactTransitionCompletionListener.class);
    View from;
    LayoutTransition layoutTransition = new LayoutTransition();
    ControllerTransition.CompletionListener listener;
    View to;
    ReactNativeViewDelegate viewDelegate;
    Runnable viewRendered;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactTransitionCompletionListener(@Nullable View view, View view2, ControllerTransition.CompletionListener completionListener, Runnable runnable, ReactNativeViewDelegate reactNativeViewDelegate) {
        this.listener = completionListener;
        this.from = view;
        this.to = view2;
        this.viewRendered = runnable;
        this.viewDelegate = reactNativeViewDelegate;
        configureLayoutTransition(this.layoutTransition);
        setLayoutTransition((ViewGroup) view, this.layoutTransition);
        setLayoutTransition((ViewGroup) view2, this.layoutTransition);
    }

    public static void configureLayoutTransition(LayoutTransition layoutTransition) {
        layoutTransition.enableTransitionType(2);
        layoutTransition.disableTransitionType(0);
        layoutTransition.disableTransitionType(1);
        layoutTransition.enableTransitionType(3);
        layoutTransition.disableTransitionType(4);
        layoutTransition.setDuration(166L);
    }

    public static void setLayoutTransition(ViewGroup viewGroup, LayoutTransition layoutTransition) {
        if (viewGroup != null) {
            viewGroup.setLayoutTransition(layoutTransition);
        }
    }

    @Override // com.amazon.regulator.ControllerTransition.CompletionListener
    public void completeTransition() {
        this.listener.completeTransition();
        Runnable runnable = this.viewRendered;
        if (runnable != null) {
            runnable.run();
        }
        ReactNativeViewDelegate reactNativeViewDelegate = this.viewDelegate;
        if (reactNativeViewDelegate != null) {
            reactNativeViewDelegate.updateIsReactNative();
        } else {
            Log.w(TAG, "completeTransition: viewDelegate is null; cannot update isReactNative");
        }
        setLayoutTransition((ViewGroup) this.from, this.layoutTransition);
        setLayoutTransition((ViewGroup) this.to, this.layoutTransition);
    }
}
