package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.recent.models.ActivityHeader;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public abstract class RecentActivityHeaderBinding extends ViewDataBinding {
    @Bindable
    protected ActivityHeader mActivityHeader;
    @NonNull
    public final LinearLayout recentActivityHeader;
    @NonNull
    public final FontTextView recentDate;

    /* JADX INFO: Access modifiers changed from: protected */
    public RecentActivityHeaderBinding(Object obj, View view, int i, LinearLayout linearLayout, FontTextView fontTextView) {
        super(obj, view, i);
        this.recentActivityHeader = linearLayout;
        this.recentDate = fontTextView;
    }

    public static RecentActivityHeaderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RecentActivityHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public ActivityHeader getActivityHeader() {
        return this.mActivityHeader;
    }

    public abstract void setActivityHeader(@Nullable ActivityHeader activityHeader);

    @Deprecated
    public static RecentActivityHeaderBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RecentActivityHeaderBinding) ViewDataBinding.bind(obj, view, R.layout.recent_activity_header);
    }

    @NonNull
    @Deprecated
    public static RecentActivityHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RecentActivityHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activity_header, viewGroup, z, obj);
    }

    @NonNull
    public static RecentActivityHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RecentActivityHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RecentActivityHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activity_header, null, false, obj);
    }
}
