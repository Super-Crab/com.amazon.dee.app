package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.view.recent.models.ActivityItem;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public class RecentActivityItemBindingImpl extends RecentActivityItemBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public RecentActivityItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ActivityItem activityItem = this.mActivityItem;
        int i = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        String str4 = null;
        if (i == 0 || activityItem == null) {
            str = null;
            str2 = null;
            str3 = null;
        } else {
            String secondaryText = activityItem.getSecondaryText();
            String iconUrl = activityItem.getIconUrl();
            String iconAltText = activityItem.getIconAltText();
            str3 = activityItem.getPrimaryText();
            str = secondaryText;
            str4 = iconAltText;
            str2 = iconUrl;
        }
        if (i != 0) {
            if (ViewDataBinding.getBuildSdkInt() >= 4) {
                this.activityIcon.setContentDescription(str4);
            }
            ImageViewAdapter.setImageUrl(this.activityIcon, str2);
            TextViewBindingAdapter.setText(this.activityTime, str);
            TextViewBindingAdapter.setText(this.activityTitle, str3);
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

    @Override // com.amazon.alexa.biloba.databinding.RecentActivityItemBinding
    public void setActivityItem(@Nullable ActivityItem activityItem) {
        this.mActivityItem = activityItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.activityItem);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.activityItem == i) {
            setActivityItem((ActivityItem) obj);
            return true;
        }
        return false;
    }

    private RecentActivityItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[1], (FontTextView) objArr[3], (FontTextView) objArr[2], (LinearLayout) objArr[0]);
        this.mDirtyFlags = -1L;
        this.activityIcon.setTag(null);
        this.activityTime.setTag(null);
        this.activityTitle.setTag(null);
        this.recentActivityItem.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
