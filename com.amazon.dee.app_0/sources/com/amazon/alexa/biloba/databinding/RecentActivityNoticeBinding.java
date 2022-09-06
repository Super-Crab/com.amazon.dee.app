package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.recent.models.NoticeItem;
/* loaded from: classes6.dex */
public abstract class RecentActivityNoticeBinding extends ViewDataBinding {
    @Bindable
    protected NoticeItem mNoticeItem;

    /* JADX INFO: Access modifiers changed from: protected */
    public RecentActivityNoticeBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static RecentActivityNoticeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RecentActivityNoticeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public NoticeItem getNoticeItem() {
        return this.mNoticeItem;
    }

    public abstract void setNoticeItem(@Nullable NoticeItem noticeItem);

    @Deprecated
    public static RecentActivityNoticeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RecentActivityNoticeBinding) ViewDataBinding.bind(obj, view, R.layout.recent_activity_notice);
    }

    @NonNull
    @Deprecated
    public static RecentActivityNoticeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RecentActivityNoticeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activity_notice, viewGroup, z, obj);
    }

    @NonNull
    public static RecentActivityNoticeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RecentActivityNoticeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RecentActivityNoticeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activity_notice, null, false, obj);
    }
}
