package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
import com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageViewModel;
/* loaded from: classes12.dex */
public abstract class AlexaDeviceBackgroundImageBinding extends ViewDataBinding {
    @NonNull
    public final ImageView back;
    @NonNull
    public final FrameLayout fragmentContainer;
    @Bindable
    protected AlexaDeviceBackgroundImageViewModel mViewModel;
    @NonNull
    public final TextView title;
    @NonNull
    public final Toolbar toolbar;

    /* JADX INFO: Access modifiers changed from: protected */
    public AlexaDeviceBackgroundImageBinding(Object obj, View view, int i, ImageView imageView, FrameLayout frameLayout, TextView textView, Toolbar toolbar) {
        super(obj, view, i);
        this.back = imageView;
        this.fragmentContainer = frameLayout;
        this.title = textView;
        this.toolbar = toolbar;
    }

    public static AlexaDeviceBackgroundImageBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlexaDeviceBackgroundImageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public AlexaDeviceBackgroundImageViewModel getViewModel() {
        return this.mViewModel;
    }

    public abstract void setViewModel(@Nullable AlexaDeviceBackgroundImageViewModel alexaDeviceBackgroundImageViewModel);

    @Deprecated
    public static AlexaDeviceBackgroundImageBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AlexaDeviceBackgroundImageBinding) ViewDataBinding.bind(obj, view, R.layout.alexa_device_background_image);
    }

    @NonNull
    @Deprecated
    public static AlexaDeviceBackgroundImageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AlexaDeviceBackgroundImageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alexa_device_background_image, viewGroup, z, obj);
    }

    @NonNull
    public static AlexaDeviceBackgroundImageBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AlexaDeviceBackgroundImageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AlexaDeviceBackgroundImageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alexa_device_background_image, null, false, obj);
    }
}
