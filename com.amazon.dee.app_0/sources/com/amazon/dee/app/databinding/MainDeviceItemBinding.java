package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
import com.amazon.dee.app.ui.web.DeviceInfo;
/* loaded from: classes12.dex */
public abstract class MainDeviceItemBinding extends ViewDataBinding {
    @Bindable
    protected DeviceInfo mModel;

    /* JADX INFO: Access modifiers changed from: protected */
    public MainDeviceItemBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static MainDeviceItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MainDeviceItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public DeviceInfo getModel() {
        return this.mModel;
    }

    public abstract void setModel(@Nullable DeviceInfo deviceInfo);

    @Deprecated
    public static MainDeviceItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MainDeviceItemBinding) ViewDataBinding.bind(obj, view, R.layout.main_device_item);
    }

    @NonNull
    @Deprecated
    public static MainDeviceItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MainDeviceItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_device_item, viewGroup, z, obj);
    }

    @NonNull
    public static MainDeviceItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MainDeviceItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MainDeviceItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_device_item, null, false, obj);
    }
}
