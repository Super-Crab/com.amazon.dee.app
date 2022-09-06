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
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.recent.RecentActivityListViewModel;
import com.amazon.alexa.mosaic.view.ErrorView;
/* loaded from: classes6.dex */
public abstract class RecentActivitiesBinding extends ViewDataBinding {
    @NonNull
    public final ErrorView errorView;
    @NonNull
    public final View loadingView;
    @Bindable
    protected RecentActivityListViewModel mViewmodel;
    @NonNull
    public final LinearLayout recentActivities;
    @NonNull
    public final RecyclerView recentActivitiesListView;
    @NonNull
    public final SwipeRefreshLayout refreshContainer;

    /* JADX INFO: Access modifiers changed from: protected */
    public RecentActivitiesBinding(Object obj, View view, int i, ErrorView errorView, View view2, LinearLayout linearLayout, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        super(obj, view, i);
        this.errorView = errorView;
        this.loadingView = view2;
        this.recentActivities = linearLayout;
        this.recentActivitiesListView = recyclerView;
        this.refreshContainer = swipeRefreshLayout;
    }

    public static RecentActivitiesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RecentActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public RecentActivityListViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setViewmodel(@Nullable RecentActivityListViewModel recentActivityListViewModel);

    @Deprecated
    public static RecentActivitiesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RecentActivitiesBinding) ViewDataBinding.bind(obj, view, R.layout.recent_activities);
    }

    @NonNull
    @Deprecated
    public static RecentActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RecentActivitiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activities, viewGroup, z, obj);
    }

    @NonNull
    public static RecentActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RecentActivitiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RecentActivitiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.recent_activities, null, false, obj);
    }
}
