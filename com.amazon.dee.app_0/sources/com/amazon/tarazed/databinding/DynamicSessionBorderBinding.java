package com.amazon.tarazed.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.tarazed.R;
import com.amazon.tarazed.ui.menu.databinding.BorderVisibilityObservable;
/* loaded from: classes13.dex */
public abstract class DynamicSessionBorderBinding extends ViewDataBinding {
    @Bindable
    protected BorderVisibilityObservable mBorderVisible;

    /* JADX INFO: Access modifiers changed from: protected */
    public DynamicSessionBorderBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static DynamicSessionBorderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DynamicSessionBorderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public BorderVisibilityObservable getBorderVisible() {
        return this.mBorderVisible;
    }

    public abstract void setBorderVisible(@Nullable BorderVisibilityObservable borderVisibilityObservable);

    @Deprecated
    public static DynamicSessionBorderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DynamicSessionBorderBinding) ViewDataBinding.bind(obj, view, R.layout.dynamic_session_border);
    }

    @NonNull
    @Deprecated
    public static DynamicSessionBorderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DynamicSessionBorderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dynamic_session_border, viewGroup, z, obj);
    }

    @NonNull
    public static DynamicSessionBorderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DynamicSessionBorderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DynamicSessionBorderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dynamic_session_border, null, false, obj);
    }
}
