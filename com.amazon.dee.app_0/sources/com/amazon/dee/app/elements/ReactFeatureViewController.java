package com.amazon.dee.app.elements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.ReactRootView;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/* loaded from: classes12.dex */
public class ReactFeatureViewController extends ViewController {
    private static final String LOG_TAG = "com.amazon.dee.app.elements.ReactFeatureViewController";
    public static final String REACT_FEATURE = "REACT_FEATURE";
    public static final String REACT_FEATURE_STATE = "REACT_FEATURE_STATE";
    @Nullable
    ReactFeature reactFeature;

    public static ReactFeatureViewController create(@NonNull ReactFeature reactFeature) {
        Preconditions.checkNotNull(reactFeature, "reactFeature cannot be null");
        ReactFeatureViewController reactFeatureViewController = new ReactFeatureViewController();
        Bundle bundle = new Bundle();
        bundle.putParcelable(REACT_FEATURE, reactFeature);
        reactFeatureViewController.setArguments(bundle);
        return reactFeatureViewController;
    }

    public ReactFeatureManager getReactFeatureManager() {
        return (ReactFeatureManager) getComponent().get(ReactFeatureManager.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    @SuppressFBWarnings(justification = "The null check immediately precedes the function call", value = {"NP_NULL_PARAM_DEREF"})
    public void onAttach(View view) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAttach for feature: ");
        outline107.append(this.reactFeature);
        outline107.toString();
        super.onAttach(view);
        if (this.reactFeature != null) {
            getReactFeatureManager().onFeatureResumed(this.reactFeature);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    @SuppressFBWarnings(justification = "The null check immediately precedes the function call", value = {"NP_NULL_PARAM_DEREF"})
    public void onCreate() {
        super.onCreate();
        ReactFeature reactFeature = (ReactFeature) getArguments().getParcelable(REACT_FEATURE);
        if (reactFeature != null) {
            this.reactFeature = reactFeature;
            return;
        }
        throw new IllegalStateException("ReactFeature was null.");
    }

    @Override // com.amazon.regulator.ViewController
    @Nullable
    @SuppressFBWarnings(justification = "The null check immediately precedes the function call", value = {"NP_NULL_PARAM_DEREF"})
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        if (this.reactFeature != null) {
            ReactRootView onFeatureResumed = getReactFeatureManager().onFeatureResumed(this.reactFeature);
            ViewParent parent = onFeatureResumed.getParent();
            if (parent instanceof ViewManager) {
                ((ViewManager) parent).removeView(onFeatureResumed);
            }
            return onFeatureResumed;
        }
        throw new IllegalStateException("ReactFeature was null.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    @SuppressFBWarnings(justification = "The null check immediately precedes the function call", value = {"NP_NULL_PARAM_DEREF"})
    public void onDestroy() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDestroy for feature: ");
        outline107.append(this.reactFeature);
        outline107.toString();
        super.onDestroy();
        if (this.reactFeature != null) {
            getReactFeatureManager().onFeatureDestroyed(this.reactFeature);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(View view) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDestroyView for feature: ");
        outline107.append(this.reactFeature);
        outline107.toString();
        super.onDestroyView(view);
    }

    @Override // com.amazon.regulator.ViewController
    @SuppressFBWarnings(justification = "The null check immediately precedes the function call", value = {"NP_NULL_PARAM_DEREF"})
    public void onDetach(View view) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDetach for feature: ");
        outline107.append(this.reactFeature);
        outline107.toString();
        super.onDetach(view);
        if (this.reactFeature != null) {
            getReactFeatureManager().onFeatureBackgrounded(this.reactFeature);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onRestoreViewState(View view, Bundle bundle) {
        super.onRestoreViewState(view, bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onSaveViewState(View view, Bundle bundle) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onSaveInstanceState for feature: ");
        outline107.append(this.reactFeature);
        outline107.toString();
        super.onSaveViewState(view, bundle);
        bundle.putParcelable(REACT_FEATURE_STATE, this.reactFeature);
    }
}
