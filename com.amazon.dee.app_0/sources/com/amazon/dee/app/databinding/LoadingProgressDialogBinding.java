package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
import com.amazon.dee.app.ui.view.LoadingProgressViewModel;
import com.amazon.dee.app.ui.view.LoadingView;
/* loaded from: classes12.dex */
public abstract class LoadingProgressDialogBinding extends ViewDataBinding {
    @NonNull
    public final LoadingView image;
    @NonNull
    public final LinearLayout loadingProgress;
    @Bindable
    protected LoadingProgressViewModel mViewModel;
    @NonNull
    public final TextView message;

    /* JADX INFO: Access modifiers changed from: protected */
    public LoadingProgressDialogBinding(Object obj, View view, int i, LoadingView loadingView, LinearLayout linearLayout, TextView textView) {
        super(obj, view, i);
        this.image = loadingView;
        this.loadingProgress = linearLayout;
        this.message = textView;
    }

    public static LoadingProgressDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LoadingProgressDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public LoadingProgressViewModel getViewModel() {
        return this.mViewModel;
    }

    public abstract void setViewModel(@Nullable LoadingProgressViewModel loadingProgressViewModel);

    @Deprecated
    public static LoadingProgressDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LoadingProgressDialogBinding) ViewDataBinding.bind(obj, view, R.layout.loading_progress_dialog);
    }

    @NonNull
    @Deprecated
    public static LoadingProgressDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LoadingProgressDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.loading_progress_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static LoadingProgressDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LoadingProgressDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LoadingProgressDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.loading_progress_dialog, null, false, obj);
    }
}
