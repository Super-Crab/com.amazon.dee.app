package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
import com.amazon.dee.app.ui.external.ExternalUIHandler;
/* loaded from: classes12.dex */
public abstract class ExternalUiBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout externalUiContent;
    @NonNull
    public final LinearLayout externalUiFrameLayout;
    @NonNull
    public final RelativeLayout externalUiHeader;
    @NonNull
    public final TextView externalUiTitle;
    @Bindable
    protected ExternalUIHandler mHandler;

    /* JADX INFO: Access modifiers changed from: protected */
    public ExternalUiBinding(Object obj, View view, int i, RelativeLayout relativeLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView) {
        super(obj, view, i);
        this.externalUiContent = relativeLayout;
        this.externalUiFrameLayout = linearLayout;
        this.externalUiHeader = relativeLayout2;
        this.externalUiTitle = textView;
    }

    public static ExternalUiBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ExternalUiBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public ExternalUIHandler getHandler() {
        return this.mHandler;
    }

    public abstract void setHandler(@Nullable ExternalUIHandler externalUIHandler);

    @Deprecated
    public static ExternalUiBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ExternalUiBinding) ViewDataBinding.bind(obj, view, R.layout.external_ui);
    }

    @NonNull
    @Deprecated
    public static ExternalUiBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ExternalUiBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.external_ui, viewGroup, z, obj);
    }

    @NonNull
    public static ExternalUiBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ExternalUiBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ExternalUiBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.external_ui, null, false, obj);
    }
}
