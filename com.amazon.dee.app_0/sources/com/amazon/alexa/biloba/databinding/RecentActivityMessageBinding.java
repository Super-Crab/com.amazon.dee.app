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
import com.amazon.alexa.biloba.view.recent.models.ActivityMessage;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public abstract class RecentActivityMessageBinding extends ViewDataBinding {
    @Bindable
    protected ActivityMessage mActivityMessage;
    @NonNull
    public final LinearLayout recentActivityMessage;
    @NonNull
    public final FontTextView recentMessage;

    /* JADX INFO: Access modifiers changed from: protected */
    public RecentActivityMessageBinding(Object obj, View view, int i, LinearLayout linearLayout, FontTextView fontTextView) {
        super(obj, view, i);
        this.recentActivityMessage = linearLayout;
        this.recentMessage = fontTextView;
    }

    public static RecentActivityMessageBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RecentActivityMessageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public ActivityMessage getActivityMessage() {
        return this.mActivityMessage;
    }

    public abstract void setActivityMessage(@Nullable ActivityMessage activityMessage);

    @Deprecated
    public static RecentActivityMessageBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RecentActivityMessageBinding) ViewDataBinding.bind(obj, view, R.layout.recent_activity_message);
    }

    @NonNull
    @Deprecated
    public static RecentActivityMessageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RecentActivityMessageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activity_message, viewGroup, z, obj);
    }

    @NonNull
    public static RecentActivityMessageBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RecentActivityMessageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RecentActivityMessageBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activity_message, null, false, obj);
    }
}
