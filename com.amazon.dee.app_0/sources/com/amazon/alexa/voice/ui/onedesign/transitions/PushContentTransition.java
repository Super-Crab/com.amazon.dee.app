package com.amazon.alexa.voice.ui.onedesign.transitions;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.amazon.regulator.transitions.AnimatorTransition;
/* loaded from: classes11.dex */
public final class PushContentTransition extends AnimatorTransition {
    public static final Parcelable.Creator<PushContentTransition> CREATOR = new Parcelable.Creator<PushContentTransition>() { // from class: com.amazon.alexa.voice.ui.onedesign.transitions.PushContentTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public PushContentTransition mo2758createFromParcel(Parcel parcel) {
            return new PushContentTransition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public PushContentTransition[] mo2759newArray(int i) {
            return new PushContentTransition[i];
        }
    };

    public PushContentTransition(boolean z) {
        super(z);
    }

    @Override // com.amazon.regulator.transitions.AnimatorTransition
    @Nullable
    protected Animator getAnimator(@NonNull ViewGroup viewGroup, @Nullable View view, @Nullable View view2) {
        if (view2 == null) {
            return null;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, View.ALPHA, 0.0f, 1.0f);
        ofFloat.setInterpolator(new FastOutSlowInInterpolator());
        return ofFloat;
    }

    public PushContentTransition() {
    }

    protected PushContentTransition(Parcel parcel) {
        super(parcel);
    }
}
