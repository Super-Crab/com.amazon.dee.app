package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.view.alerts.AlertConfigRecyclerItem;
import com.amazon.alexa.mosaic.view.ListItemSwitch;
/* loaded from: classes6.dex */
public class AlertItemBindingImpl extends AlertItemBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public AlertItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        String str;
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        View.OnClickListener onClickListener;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        AlertConfigRecyclerItem alertConfigRecyclerItem = this.mAlert;
        int i = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        boolean z2 = false;
        String str2 = null;
        if (i == 0 || alertConfigRecyclerItem == null) {
            z = false;
            str = null;
            onCheckedChangeListener = null;
            onClickListener = null;
        } else {
            boolean isAlertSwitchEnabled = alertConfigRecyclerItem.isAlertSwitchEnabled();
            str2 = alertConfigRecyclerItem.getTitle();
            str = alertConfigRecyclerItem.getDescription();
            boolean isChecked = alertConfigRecyclerItem.isChecked();
            onClickListener = alertConfigRecyclerItem.getClickListener();
            onCheckedChangeListener = alertConfigRecyclerItem.getEnableDisableSwitchListener();
            z = isAlertSwitchEnabled;
            z2 = isChecked;
        }
        if (i != 0) {
            this.alertListItem.setPrimaryText(str2);
            this.alertListItem.setSecondaryText(str);
            this.alertListItem.setViewOnClick(onClickListener);
            this.alertListItem.setSwitchIsChecked(z2);
            this.alertListItem.setSwitchOnCheckedChange(onCheckedChangeListener);
            this.alertListItem.setIsAlertSwitchEnabled(z);
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

    @Override // com.amazon.alexa.biloba.databinding.AlertItemBinding
    public void setAlert(@Nullable AlertConfigRecyclerItem alertConfigRecyclerItem) {
        this.mAlert = alertConfigRecyclerItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.alert);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.alert == i) {
            setAlert((AlertConfigRecyclerItem) obj);
            return true;
        }
        return false;
    }

    private AlertItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ListItemSwitch) objArr[0]);
        this.mDirtyFlags = -1L;
        this.alertListItem.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
