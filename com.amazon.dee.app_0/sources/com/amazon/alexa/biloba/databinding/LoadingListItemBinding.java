package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.common.LoadingView;
/* loaded from: classes6.dex */
public abstract class LoadingListItemBinding extends ViewDataBinding {
    @NonNull
    public final LoadingView image;

    /* JADX INFO: Access modifiers changed from: protected */
    public LoadingListItemBinding(Object obj, View view, int i, LoadingView loadingView) {
        super(obj, view, i);
        this.image = loadingView;
    }

    public static LoadingListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LoadingListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoadingListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LoadingListItemBinding) ViewDataBinding.bind(obj, view, R.layout.loading_list_item);
    }

    @NonNull
    @Deprecated
    public static LoadingListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LoadingListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.loading_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static LoadingListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LoadingListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LoadingListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.loading_list_item, null, false, obj);
    }
}
