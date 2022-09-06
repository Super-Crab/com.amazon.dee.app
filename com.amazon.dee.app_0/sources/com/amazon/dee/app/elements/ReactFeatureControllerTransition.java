package com.amazon.dee.app.elements;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.dee.app.ui.main.MainActivity;
import com.amazon.regulator.ControllerTransition;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ReactFeatureControllerTransition implements ControllerTransition, OnRenderedListener {
    public static final Parcelable.Creator<ReactFeatureControllerTransition> CREATOR = new Parcelable.Creator<ReactFeatureControllerTransition>() { // from class: com.amazon.dee.app.elements.ReactFeatureControllerTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ReactFeatureControllerTransition mo3588createFromParcel(Parcel parcel) {
            return new ReactFeatureControllerTransition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ReactFeatureControllerTransition[] mo3589newArray(int i) {
            return new ReactFeatureControllerTransition[i];
        }
    };
    @Nullable
    ControllerTransition.CompletionListener completionListener;
    @NonNull
    ReactFeature reactFeature;
    @Nullable
    @Inject
    ReactFeatureManager reactFeatureManager;
    private ReactNativeViewDelegate viewDelegate;
    @Nullable
    Runnable viewRendered;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactFeatureControllerTransition(@NonNull ReactFeature reactFeature, Runnable runnable) {
        this.reactFeature = reactFeature;
        this.viewRendered = runnable;
    }

    @Nullable
    private Activity getActivityForView(@NonNull View view) {
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    @Override // com.amazon.regulator.ControllerTransition
    public void completeTransition() {
        ReactFeatureManager reactFeatureManager = this.reactFeatureManager;
        if (reactFeatureManager != null) {
            reactFeatureManager.onFeatureRendered(this.reactFeature.getInstanceId());
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.dee.app.elements.OnRenderedListener
    public void onDidRender() {
        ControllerTransition.CompletionListener completionListener = this.completionListener;
        if (completionListener != null) {
            completionListener.completeTransition();
        }
    }

    @Override // com.amazon.regulator.ControllerTransition
    public void performTransition(ViewGroup viewGroup, @Nullable View view, View view2, ControllerTransition.CompletionListener completionListener) {
        Activity activityForView = getActivityForView(viewGroup);
        if (activityForView != null && (activityForView instanceof MainActivity)) {
            ((MainActivity) activityForView).getComponent().inject(this);
        }
        this.completionListener = new ReactTransitionCompletionListener(view, view2, completionListener, this.viewRendered, this.viewDelegate);
        ((ReactFeatureManager) Preconditions.checkNotNull(this.reactFeatureManager)).setOnFeatureRenderedListener(this.reactFeature, this);
    }

    public void setReactNativeViewDelegate(ReactNativeViewDelegate reactNativeViewDelegate) {
        this.viewDelegate = reactNativeViewDelegate;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.reactFeature, i);
    }

    protected ReactFeatureControllerTransition(Parcel parcel) {
        this.reactFeature = (ReactFeature) parcel.readParcelable(ReactFeature.class.getClassLoader());
    }
}
