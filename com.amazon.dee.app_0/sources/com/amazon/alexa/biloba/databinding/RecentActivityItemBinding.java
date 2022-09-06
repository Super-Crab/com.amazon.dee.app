package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.recent.models.ActivityItem;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public abstract class RecentActivityItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView activityIcon;
    @NonNull
    public final FontTextView activityTime;
    @NonNull
    public final FontTextView activityTitle;
    @Bindable
    protected ActivityItem mActivityItem;
    @NonNull
    public final LinearLayout recentActivityItem;

    /* JADX INFO: Access modifiers changed from: protected */
    public RecentActivityItemBinding(Object obj, View view, int i, ImageView imageView, FontTextView fontTextView, FontTextView fontTextView2, LinearLayout linearLayout) {
        super(obj, view, i);
        this.activityIcon = imageView;
        this.activityTime = fontTextView;
        this.activityTitle = fontTextView2;
        this.recentActivityItem = linearLayout;
    }

    public static RecentActivityItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RecentActivityItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public ActivityItem getActivityItem() {
        return this.mActivityItem;
    }

    public abstract void setActivityItem(@Nullable ActivityItem activityItem);

    @Deprecated
    public static RecentActivityItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RecentActivityItemBinding) ViewDataBinding.bind(obj, view, R.layout.recent_activity_item);
    }

    @NonNull
    @Deprecated
    public static RecentActivityItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RecentActivityItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activity_item, viewGroup, z, obj);
    }

    @NonNull
    public static RecentActivityItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RecentActivityItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RecentActivityItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activity_item, null, false, obj);
    }
}
