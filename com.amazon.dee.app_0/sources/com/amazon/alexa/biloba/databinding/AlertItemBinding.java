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
import com.amazon.alexa.biloba.view.alerts.AlertConfigRecyclerItem;
import com.amazon.alexa.mosaic.view.ListItemSwitch;
/* loaded from: classes6.dex */
public abstract class AlertItemBinding extends ViewDataBinding {
    @NonNull
    public final ListItemSwitch alertListItem;
    @Bindable
    protected AlertConfigRecyclerItem mAlert;

    /* JADX INFO: Access modifiers changed from: protected */
    public AlertItemBinding(Object obj, View view, int i, ListItemSwitch listItemSwitch) {
        super(obj, view, i);
        this.alertListItem = listItemSwitch;
    }

    public static AlertItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlertItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public AlertConfigRecyclerItem getAlert() {
        return this.mAlert;
    }

    public abstract void setAlert(@Nullable AlertConfigRecyclerItem alertConfigRecyclerItem);

    @Deprecated
    public static AlertItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AlertItemBinding) ViewDataBinding.bind(obj, view, R.layout.alert_item);
    }

    @NonNull
    @Deprecated
    public static AlertItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AlertItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alert_item, viewGroup, z, obj);
    }

    @NonNull
    public static AlertItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AlertItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AlertItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alert_item, null, false, obj);
    }
}
