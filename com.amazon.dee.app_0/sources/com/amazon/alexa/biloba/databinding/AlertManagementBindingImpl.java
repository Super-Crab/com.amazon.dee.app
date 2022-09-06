package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.alerts.AlertSettingsListView;
import com.amazon.alexa.biloba.view.alerts.AlertsListViewModel;
import com.amazon.alexa.mosaic.view.NoticeBannerView;
/* loaded from: classes6.dex */
public class AlertManagementBindingImpl extends AlertManagementBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final LinearLayoutCompat mboundView0;

    static {
        sViewsWithIds.put(R.id.alert_notifications_disabled, 2);
        sViewsWithIds.put(R.id.alert_no_devices, 3);
        sViewsWithIds.put(R.id.alerts_list, 4);
    }

    public AlertManagementBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        AlertSettingsListView alertSettingsListView = this.mHandler;
        String str = null;
        AlertsListViewModel alertsListViewModel = this.mViewmodel;
        if ((j & 5) != 0 && alertSettingsListView != null) {
            str = alertSettingsListView.getAlertWarningCrMessage();
        }
        int i = ((j & 6) > 0L ? 1 : ((j & 6) == 0L ? 0 : -1));
        int i2 = 0;
        if (i != 0) {
            boolean z = !(alertsListViewModel != null ? alertsListViewModel.isCareGiver() : false);
            if (i != 0) {
                j |= z ? 16L : 8L;
            }
            if (!z) {
                i2 = 8;
            }
        }
        if ((j & 5) != 0) {
            this.alertNotificationCr.setPrimaryText(str);
        }
        if ((j & 6) != 0) {
            this.alertNotificationCr.setVisibility(i2);
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
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.amazon.alexa.biloba.databinding.AlertManagementBinding
    public void setHandler(@Nullable AlertSettingsListView alertSettingsListView) {
        this.mHandler = alertSettingsListView;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((AlertSettingsListView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((AlertsListViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.AlertManagementBinding
    public void setViewmodel(@Nullable AlertsListViewModel alertsListViewModel) {
        this.mViewmodel = alertsListViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private AlertManagementBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (NoticeBannerView) objArr[3], (NoticeBannerView) objArr[1], (NoticeBannerView) objArr[2], (RecyclerView) objArr[4]);
        this.mDirtyFlags = -1L;
        this.alertNotificationCr.setTag(null);
        this.mboundView0 = (LinearLayoutCompat) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
