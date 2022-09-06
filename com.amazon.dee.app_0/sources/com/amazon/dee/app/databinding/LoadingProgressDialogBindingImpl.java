package com.amazon.dee.app.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.amazon.dee.app.R;
import com.amazon.dee.app.ui.view.LoadingProgressViewModel;
import com.amazon.dee.app.ui.view.LoadingView;
/* loaded from: classes12.dex */
public class LoadingProgressDialogBindingImpl extends LoadingProgressDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.image, 2);
    }

    public LoadingProgressDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = null;
        LoadingProgressViewModel loadingProgressViewModel = this.mViewModel;
        int i = ((3 & j) > 0L ? 1 : ((3 & j) == 0L ? 0 : -1));
        if (i != 0 && loadingProgressViewModel != null) {
            str = loadingProgressViewModel.getMessage();
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.message, str);
        }
        if ((j & 2) != 0) {
            FontAdapter.setFont(this.message, "ember-regular");
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
            setViewModel((LoadingProgressViewModel) obj);
            return true;
        }
        return false;
    }

    @Override // com.amazon.dee.app.databinding.LoadingProgressDialogBinding
    public void setViewModel(@Nullable LoadingProgressViewModel loadingProgressViewModel) {
        this.mViewModel = loadingProgressViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    private LoadingProgressDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LoadingView) objArr[2], (LinearLayout) objArr[0], (TextView) objArr[1]);
        this.mDirtyFlags = -1L;
        this.loadingProgress.setTag(null);
        this.message.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
