package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.startup.StartupViewModel;
import com.amazon.alexa.mosaic.view.ErrorView;
/* loaded from: classes6.dex */
public abstract class StartupViewBinding extends ViewDataBinding {
    @NonNull
    public final ErrorView errorView;
    @NonNull
    public final View loadingView;
    @Bindable
    protected StartupViewModel mViewmodel;

    /* JADX INFO: Access modifiers changed from: protected */
    public StartupViewBinding(Object obj, View view, int i, ErrorView errorView, View view2) {
        super(obj, view, i);
        this.errorView = errorView;
        this.loadingView = view2;
    }

    public static StartupViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static StartupViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public StartupViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setViewmodel(@Nullable StartupViewModel startupViewModel);

    @Deprecated
    public static StartupViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (StartupViewBinding) ViewDataBinding.bind(obj, view, R.layout.startup_view);
    }

    @NonNull
    @Deprecated
    public static StartupViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (StartupViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.startup_view, viewGroup, z, obj);
    }

    @NonNull
    public static StartupViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static StartupViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (StartupViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.startup_view, null, false, obj);
    }
}
