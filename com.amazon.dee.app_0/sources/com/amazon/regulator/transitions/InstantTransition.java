package com.amazon.regulator.transitions;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.regulator.ControllerTransition;
/* loaded from: classes13.dex */
public final class InstantTransition implements ControllerTransition {
    public static final Parcelable.Creator<InstantTransition> CREATOR = new Parcelable.Creator<InstantTransition>() { // from class: com.amazon.regulator.transitions.InstantTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public InstantTransition mo4485createFromParcel(Parcel parcel) {
            return new InstantTransition();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public InstantTransition[] mo4486newArray(int i) {
            return new InstantTransition[i];
        }
    };

    @Override // com.amazon.regulator.ControllerTransition
    public void completeTransition() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.regulator.ControllerTransition
    public void performTransition(ViewGroup viewGroup, View view, View view2, ControllerTransition.CompletionListener completionListener) {
        completionListener.completeTransition();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }
}
