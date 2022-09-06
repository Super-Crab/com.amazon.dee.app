package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.alerts.AlertSettingsListView;
import com.amazon.alexa.biloba.view.alerts.AlertsListViewModel;
import com.amazon.alexa.mosaic.view.NoticeBannerView;
/* loaded from: classes6.dex */
public abstract class AlertManagementBinding extends ViewDataBinding {
    @NonNull
    public final NoticeBannerView alertNoDevices;
    @NonNull
    public final NoticeBannerView alertNotificationCr;
    @NonNull
    public final NoticeBannerView alertNotificationsDisabled;
    @NonNull
    public final RecyclerView alertsList;
    @Bindable
    protected AlertSettingsListView mHandler;
    @Bindable
    protected AlertsListViewModel mViewmodel;

    /* JADX INFO: Access modifiers changed from: protected */
    public AlertManagementBinding(Object obj, View view, int i, NoticeBannerView noticeBannerView, NoticeBannerView noticeBannerView2, NoticeBannerView noticeBannerView3, RecyclerView recyclerView) {
        super(obj, view, i);
        this.alertNoDevices = noticeBannerView;
        this.alertNotificationCr = noticeBannerView2;
        this.alertNotificationsDisabled = noticeBannerView3;
        this.alertsList = recyclerView;
    }

    public static AlertManagementBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlertManagementBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public AlertSettingsListView getHandler() {
        return this.mHandler;
    }

    @Nullable
    public AlertsListViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setHandler(@Nullable AlertSettingsListView alertSettingsListView);

    public abstract void setViewmodel(@Nullable AlertsListViewModel alertsListViewModel);

    @Deprecated
    public static AlertManagementBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AlertManagementBinding) ViewDataBinding.bind(obj, view, R.layout.alert_management);
    }

    @NonNull
    @Deprecated
    public static AlertManagementBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AlertManagementBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alert_management, viewGroup, z, obj);
    }

    @NonNull
    public static AlertManagementBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AlertManagementBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AlertManagementBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alert_management, null, false, obj);
    }
}
