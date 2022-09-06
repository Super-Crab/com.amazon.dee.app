package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.view.recent.models.ActivityMessage;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public class RecentActivityMessageBindingImpl extends RecentActivityMessageBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public RecentActivityMessageBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ActivityMessage activityMessage = this.mActivityMessage;
        String str = null;
        int i = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        if (i != 0 && activityMessage != null) {
            str = activityMessage.getText();
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.recentMessage, str);
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

    @Override // com.amazon.alexa.biloba.databinding.RecentActivityMessageBinding
    public void setActivityMessage(@Nullable ActivityMessage activityMessage) {
        this.mActivityMessage = activityMessage;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.activityMessage);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.activityMessage == i) {
            setActivityMessage((ActivityMessage) obj);
            return true;
        }
        return false;
    }

    private RecentActivityMessageBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LinearLayout) objArr[0], (FontTextView) objArr[1]);
        this.mDirtyFlags = -1L;
        this.recentActivityMessage.setTag(null);
        this.recentMessage.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
