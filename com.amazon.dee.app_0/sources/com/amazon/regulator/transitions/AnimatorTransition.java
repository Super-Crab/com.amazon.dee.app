package com.amazon.regulator.transitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Parcel;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.amazon.regulator.ControllerTransition;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes13.dex */
public abstract class AnimatorTransition implements ControllerTransition {
    private Animator animator;
    private boolean completionRequested;
    private final boolean removeFromView;

    public AnimatorTransition(boolean z) {
        this.removeFromView = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void completeAnimation(ControllerTransition.CompletionListener completionListener, Animator.AnimatorListener animatorListener) {
        completionListener.completeTransition();
        Animator animator = this.animator;
        if (animator != null) {
            animator.removeListener(animatorListener);
            this.animator.cancel();
            this.animator = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performAnimation(final ViewGroup viewGroup, final View view, View view2, final ControllerTransition.CompletionListener completionListener) {
        this.animator = getAnimator(viewGroup, view, view2);
        Animator animator = this.animator;
        if (animator == null) {
            completionListener.completeTransition();
            return;
        }
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.regulator.transitions.AnimatorTransition.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
                View view3;
                if (AnimatorTransition.this.removeFromView && (view3 = view) != null) {
                    viewGroup.removeView(view3);
                }
                AnimatorTransition.this.completeAnimation(completionListener, this);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                View view3;
                if (AnimatorTransition.this.removeFromView && (view3 = view) != null) {
                    viewGroup.removeView(view3);
                }
                AnimatorTransition.this.completeAnimation(completionListener, this);
            }
        });
        this.animator.start();
        if (!this.completionRequested) {
            return;
        }
        this.animator.end();
    }

    @Override // com.amazon.regulator.ControllerTransition
    public final void completeTransition() {
        this.completionRequested = true;
        Animator animator = this.animator;
        if (animator != null) {
            animator.end();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected abstract Animator getAnimator(ViewGroup viewGroup, View view, View view2);

    @Override // com.amazon.regulator.ControllerTransition
    public final void performTransition(final ViewGroup viewGroup, final View view, final View view2, final ControllerTransition.CompletionListener completionListener) {
        Preconditions.nonNull(viewGroup, "container == null");
        Preconditions.nonNull(completionListener, "completionListener == null");
        final View view3 = view2 != null ? view2 : view;
        this.completionRequested = false;
        if (view3 == null) {
            completionListener.completeTransition();
            return;
        }
        if (view2 != null && view2.getParent() == null) {
            int indexOfChild = viewGroup.indexOfChild(view);
            if (indexOfChild >= 0) {
                viewGroup.addView(view2, indexOfChild + 1);
            } else {
                viewGroup.addView(view2);
            }
        }
        if (view3.getHeight() <= 0) {
            view3.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.regulator.transitions.AnimatorTransition.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    ViewTreeObserver viewTreeObserver = view3.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnPreDrawListener(this);
                    }
                    AnimatorTransition.this.performAnimation(viewGroup, view, view2, completionListener);
                    return true;
                }
            });
        } else {
            performAnimation(viewGroup, view, view2, completionListener);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.removeFromView ? 1 : 0);
    }

    public AnimatorTransition() {
        this(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AnimatorTransition(Parcel parcel) {
        this.removeFromView = parcel.readInt() != 0;
    }
}
