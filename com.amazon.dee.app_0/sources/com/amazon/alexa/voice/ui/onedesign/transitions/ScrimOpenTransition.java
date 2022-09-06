package com.amazon.alexa.voice.ui.onedesign.transitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.transitions.AnimatorTransition;
@RequiresApi(api = 21)
/* loaded from: classes11.dex */
public final class ScrimOpenTransition extends AnimatorTransition {
    private LoadAnimator loadAnimator;
    private static final String TAG = ScrimOpenTransition.class.getSimpleName();
    public static final Parcelable.Creator<ScrimOpenTransition> CREATOR = new Parcelable.Creator<ScrimOpenTransition>() { // from class: com.amazon.alexa.voice.ui.onedesign.transitions.ScrimOpenTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ScrimOpenTransition mo2762createFromParcel(Parcel parcel) {
            return new ScrimOpenTransition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ScrimOpenTransition[] mo2763newArray(int i) {
            return new ScrimOpenTransition[i];
        }
    };

    public ScrimOpenTransition(boolean z) {
        super(z);
        this.loadAnimator = $$Lambda$bIOpYajmZaEycFh4jDQtS76aRBk.INSTANCE;
    }

    @Override // com.amazon.regulator.transitions.AnimatorTransition
    @Nullable
    protected Animator getAnimator(@NonNull ViewGroup viewGroup, @Nullable View view, @Nullable View view2) {
        if (view2 == null) {
            return null;
        }
        Animator load = this.loadAnimator.load(view2.getContext(), R.animator.voice_ui_scrim_overlay_open);
        load.setTarget(view2);
        View findViewById = view2.findViewById(R.id.chrome);
        if (findViewById != null) {
            Animator load2 = this.loadAnimator.load(findViewById.getContext(), R.animator.voice_ui_scrim_chome_open);
            load2.setTarget(findViewById);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(load, load2);
            return animatorSet;
        }
        Log.e(TAG, "ChromeView was null so an animation was not applied to it");
        return load;
    }

    public ScrimOpenTransition() {
        this($$Lambda$bIOpYajmZaEycFh4jDQtS76aRBk.INSTANCE);
    }

    @VisibleForTesting
    ScrimOpenTransition(LoadAnimator loadAnimator) {
        this.loadAnimator = loadAnimator;
    }

    protected ScrimOpenTransition(Parcel parcel) {
        super(parcel);
        this.loadAnimator = $$Lambda$bIOpYajmZaEycFh4jDQtS76aRBk.INSTANCE;
    }
}
