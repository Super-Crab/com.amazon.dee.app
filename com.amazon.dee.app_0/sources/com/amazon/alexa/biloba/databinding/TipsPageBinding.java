package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.tips.TipsViewModel;
import com.amazon.alexa.mosaic.view.ErrorView;
/* loaded from: classes6.dex */
public abstract class TipsPageBinding extends ViewDataBinding {
    @NonNull
    public final ErrorView errorView;
    @NonNull
    public final View loadingView;
    @Bindable
    protected TipsViewModel mViewmodel;
    @NonNull
    public final RecyclerView tipsPage;
    @NonNull
    public final LinearLayout tipsPageContainer;

    /* JADX INFO: Access modifiers changed from: protected */
    public TipsPageBinding(Object obj, View view, int i, ErrorView errorView, View view2, RecyclerView recyclerView, LinearLayout linearLayout) {
        super(obj, view, i);
        this.errorView = errorView;
        this.loadingView = view2;
        this.tipsPage = recyclerView;
        this.tipsPageContainer = linearLayout;
    }

    public static TipsPageBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TipsPageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public TipsViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setViewmodel(@Nullable TipsViewModel tipsViewModel);

    @Deprecated
    public static TipsPageBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TipsPageBinding) ViewDataBinding.bind(obj, view, R.layout.tips_page);
    }

    @NonNull
    @Deprecated
    public static TipsPageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TipsPageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tips_page, viewGroup, z, obj);
    }

    @NonNull
    public static TipsPageBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TipsPageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TipsPageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tips_page, null, false, obj);
    }
}
