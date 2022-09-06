package com.amazon.tarazed.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.tarazed.BR;
import com.amazon.tarazed.ui.menu.databinding.BorderVisibilityObservable;
/* loaded from: classes13.dex */
public class DynamicSessionBorderBindingImpl extends DynamicSessionBorderBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    private final ImageView mboundView1;

    public DynamicSessionBorderBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds));
    }

    private boolean onChangeBorderVisible(BorderVisibilityObservable borderVisibilityObservable, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        BorderVisibilityObservable borderVisibilityObservable = this.mBorderVisible;
        Boolean bool = null;
        int i = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        int i2 = 0;
        if (i != 0) {
            if (borderVisibilityObservable != null) {
                bool = borderVisibilityObservable.get();
            }
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (i != 0) {
                j |= safeUnbox ? 8L : 4L;
            }
            if (!safeUnbox) {
                i2 = 8;
            }
        }
        if ((j & 3) != 0) {
            this.mboundView1.setVisibility(i2);
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
        if (i != 0) {
            return false;
        }
        return onChangeBorderVisible((BorderVisibilityObservable) obj, i2);
    }

    @Override // com.amazon.tarazed.databinding.DynamicSessionBorderBinding
    public void setBorderVisible(@Nullable BorderVisibilityObservable borderVisibilityObservable) {
        updateRegistration(0, borderVisibilityObservable);
        this.mBorderVisible = borderVisibilityObservable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.borderVisible);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.borderVisible == i) {
            setBorderVisible((BorderVisibilityObservable) obj);
            return true;
        }
        return false;
    }

    private DynamicSessionBorderBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (RelativeLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (ImageView) objArr[1];
        this.mboundView1.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
