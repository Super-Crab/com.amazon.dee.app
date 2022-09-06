package com.amazon.dee.app.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.amazon.dee.app.ui.web.DeviceInfo;
/* loaded from: classes12.dex */
public class MainDeviceItemBindingImpl extends MainDeviceItemBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final TextView mboundView0;

    public MainDeviceItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        DeviceInfo deviceInfo = this.mModel;
        String str = null;
        int i = ((3 & j) > 0L ? 1 : ((3 & j) == 0L ? 0 : -1));
        if (i != 0 && deviceInfo != null) {
            str = deviceInfo.accountName;
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.mboundView0, str);
        }
        if ((j & 2) != 0) {
            FontAdapter.setFont(this.mboundView0, "ember-regular");
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.amazon.dee.app.databinding.MainDeviceItemBinding
    public void setModel(@Nullable DeviceInfo deviceInfo) {
        this.mModel = deviceInfo;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(28);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (28 == i) {
            setModel((DeviceInfo) obj);
            return true;
        }
        return false;
    }

    private MainDeviceItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (TextView) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
