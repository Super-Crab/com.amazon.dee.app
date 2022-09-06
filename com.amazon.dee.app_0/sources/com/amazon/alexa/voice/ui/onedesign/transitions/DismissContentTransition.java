package com.amazon.alexa.voice.ui.onedesign.transitions;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.regulator.transitions.AnimatorTransition;
/* loaded from: classes11.dex */
public final class DismissContentTransition extends AnimatorTransition {
    public static final Parcelable.Creator<DismissContentTransition> CREATOR = new Parcelable.Creator<DismissContentTransition>() { // from class: com.amazon.alexa.voice.ui.onedesign.transitions.DismissContentTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DismissContentTransition mo2756createFromParcel(Parcel parcel) {
            return new DismissContentTransition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DismissContentTransition[] mo2757newArray(int i) {
            return new DismissContentTransition[i];
        }
    };

    public DismissContentTransition() {
    }

    @Override // com.amazon.regulator.transitions.AnimatorTransition
    @Nullable
    protected Animator getAnimator(@NonNull ViewGroup viewGroup, @Nullable View view, @Nullable View view2) {
        if (view == null) {
            return null;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, view.getHeight());
        ofFloat.setInterpolator(new LinearInterpolator());
        return ofFloat;
    }

    protected DismissContentTransition(Parcel parcel) {
        super(parcel);
    }
}
