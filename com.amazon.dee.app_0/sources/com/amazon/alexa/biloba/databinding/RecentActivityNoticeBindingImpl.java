package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.view.recent.models.NoticeItem;
import com.amazon.alexa.mosaic.view.NoticeBannerView;
/* loaded from: classes6.dex */
public class RecentActivityNoticeBindingImpl extends RecentActivityNoticeBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mNoticeItemOnClickAndroidViewViewOnClickListener;
    @NonNull
    private final NoticeBannerView mboundView0;

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private NoticeItem value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onClick(view);
        }

        public OnClickListenerImpl setValue(NoticeItem noticeItem) {
            this.value = noticeItem;
            if (noticeItem == null) {
                return null;
            }
            return this;
        }
    }

    public RecentActivityNoticeBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        OnClickListenerImpl onClickListenerImpl;
        String str;
        String str2;
        String str3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        NoticeItem noticeItem = this.mNoticeItem;
        int i = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        String str4 = null;
        if (i == 0 || noticeItem == null) {
            onClickListenerImpl = null;
            str = null;
            str2 = null;
            str3 = null;
        } else {
            str4 = noticeItem.getPrimaryText();
            OnClickListenerImpl onClickListenerImpl2 = this.mNoticeItemOnClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl2 == null) {
                onClickListenerImpl2 = new OnClickListenerImpl();
                this.mNoticeItemOnClickAndroidViewViewOnClickListener = onClickListenerImpl2;
            }
            onClickListenerImpl = onClickListenerImpl2.setValue(noticeItem);
            str = noticeItem.getIconAltText();
            str3 = noticeItem.getIconUrl();
            str2 = noticeItem.getLinkText();
        }
        if (i != 0) {
            this.mboundView0.setPrimaryText(str4);
            this.mboundView0.setIconUrl(str3);
            this.mboundView0.setIconAltText(str);
            this.mboundView0.setLinkText(str2);
            this.mboundView0.setOnClickListener(onClickListenerImpl);
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

    @Override // com.amazon.alexa.biloba.databinding.RecentActivityNoticeBinding
    public void setNoticeItem(@Nullable NoticeItem noticeItem) {
        this.mNoticeItem = noticeItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.noticeItem);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.noticeItem == i) {
            setNoticeItem((NoticeItem) obj);
            return true;
        }
        return false;
    }

    private RecentActivityNoticeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (NoticeBannerView) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
