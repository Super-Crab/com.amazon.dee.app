package com.amazon.alexa.voice.ui.onedesign.transitions;

import android.animation.Animator;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.transitions.AnimatorTransition;
/* loaded from: classes11.dex */
public final class ScrimCloseTransition extends AnimatorTransition {
    public static final Parcelable.Creator<ScrimCloseTransition> CREATOR = new Parcelable.Creator<ScrimCloseTransition>() { // from class: com.amazon.alexa.voice.ui.onedesign.transitions.ScrimCloseTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ScrimCloseTransition mo2760createFromParcel(Parcel parcel) {
            return new ScrimCloseTransition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ScrimCloseTransition[] mo2761newArray(int i) {
            return new ScrimCloseTransition[i];
        }
    };
    private LoadAnimator loadAnimator;

    public ScrimCloseTransition() {
        this($$Lambda$bIOpYajmZaEycFh4jDQtS76aRBk.INSTANCE);
    }

    @Override // com.amazon.regulator.transitions.AnimatorTransition
    @Nullable
    protected Animator getAnimator(@NonNull ViewGroup viewGroup, @Nullable View view, @Nullable View view2) {
        if (view == null) {
            return null;
        }
        Animator load = this.loadAnimator.load(view.getContext(), R.animator.voice_ui_scrim_close);
        load.setTarget(view);
        return load;
    }

    @VisibleForTesting
    ScrimCloseTransition(LoadAnimator loadAnimator) {
        this.loadAnimator = loadAnimator;
    }

    protected ScrimCloseTransition(Parcel parcel) {
        super(parcel);
        this.loadAnimator = $$Lambda$bIOpYajmZaEycFh4jDQtS76aRBk.INSTANCE;
    }
}
