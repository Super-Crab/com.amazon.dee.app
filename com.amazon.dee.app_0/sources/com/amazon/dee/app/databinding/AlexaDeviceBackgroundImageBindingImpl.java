package com.amazon.dee.app.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
import com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageViewModel;
/* loaded from: classes12.dex */
public class AlexaDeviceBackgroundImageBindingImpl extends AlexaDeviceBackgroundImageBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.toolbar, 2);
        sViewsWithIds.put(R.id.back, 3);
        sViewsWithIds.put(R.id.fragment_container, 4);
    }

    public AlexaDeviceBackgroundImageBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        if ((j & 2) != 0) {
            FontAdapter.setFont(this.title, "ember-regular");
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

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (19 == i) {
            setViewModel((AlexaDeviceBackgroundImageViewModel) obj);
            return true;
        }
        return false;
    }

    @Override // com.amazon.dee.app.databinding.AlexaDeviceBackgroundImageBinding
    public void setViewModel(@Nullable AlexaDeviceBackgroundImageViewModel alexaDeviceBackgroundImageViewModel) {
        this.mViewModel = alexaDeviceBackgroundImageViewModel;
    }

    private AlexaDeviceBackgroundImageBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[3], (FrameLayout) objArr[4], (TextView) objArr[1], (Toolbar) objArr[2]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (LinearLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.title.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
