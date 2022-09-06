package com.amazon.alexa.voice.ui.onedesign.transitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.amazon.regulator.transitions.AnimatorTransition;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public final class SlideStartInTransition extends AnimatorTransition {
    public static final Parcelable.Creator<SlideStartInTransition> CREATOR = new Parcelable.Creator<SlideStartInTransition>() { // from class: com.amazon.alexa.voice.ui.onedesign.transitions.SlideStartInTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public SlideStartInTransition mo2766createFromParcel(Parcel parcel) {
            return new SlideStartInTransition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public SlideStartInTransition[] mo2767newArray(int i) {
            return new SlideStartInTransition[i];
        }
    };

    public SlideStartInTransition() {
    }

    @Override // com.amazon.regulator.transitions.AnimatorTransition
    @Nullable
    protected Animator getAnimator(@NonNull ViewGroup viewGroup, @Nullable View view, @Nullable View view2) {
        ArrayList arrayList = new ArrayList();
        if (view != null) {
            arrayList.add(ObjectAnimator.ofFloat(view, View.TRANSLATION_X, -view.getWidth()));
        }
        if (view2 != null) {
            arrayList.add(ObjectAnimator.ofFloat(view2, View.TRANSLATION_X, view2.getWidth(), 0.0f));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new FastOutSlowInInterpolator());
        animatorSet.playTogether(arrayList);
        return animatorSet;
    }

    protected SlideStartInTransition(Parcel parcel) {
        super(parcel);
    }
}
