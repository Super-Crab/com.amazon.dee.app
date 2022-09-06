package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardView;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.AlertBannerButtonView;
import com.amazon.alexa.mosaic.view.ErrorView;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.RoundImageButtonView;
import com.amazon.alexa.mosaic.view.SectionHeader;
/* loaded from: classes6.dex */
public abstract class CareDashboardBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout activityPanel;
    @NonNull
    public final FontTextView allRecentActivitiesLink;
    @NonNull
    public final RoundImageButtonView assistButton;
    @NonNull
    public final AlertBannerButtonView bottomLeftButton;
    @NonNull
    public final AlertBannerButtonView bottomRightButton;
    @NonNull
    public final LinearLayout careCardsLayout;
    @NonNull
    public final FontTextView careContactName;
    @NonNull
    public final ListItemLink cgAdminIngress;
    @NonNull
    public final FontTextView changeTimezoneDashboard;
    @NonNull
    public final SectionHeader circleOfSupportHeader;
    @NonNull
    public final LinearLayout commsPanel;
    @NonNull
    public final SectionHeader commsPanelHeader;
    @NonNull
    public final RecyclerView dashboardActivitiesListView;
    @NonNull
    public final RecyclerView dashboardCards;
    @NonNull
    public final SwipeRefreshLayout dashboardRefreshContainer;
    @NonNull
    public final ErrorView errorView;
    @NonNull
    public final View loadingView;
    @Bindable
    protected BilobaDashboardView mHandler;
    @Bindable
    protected BilobaDashboardViewModel mViewmodel;
    @NonNull
    public final SectionHeader manageTitle;
    @NonNull
    public final SectionHeader recentTitle;
    @NonNull
    public final ViewSwitcher switcher;
    @NonNull
    public final FontTextView timezoneDashboard;
    @NonNull
    public final AlertBannerButtonView topLeftButton;
    @NonNull
    public final AlertBannerButtonView topRightButton;
    @NonNull
    public final FontTextView viewYourCircleMembersLink;

    /* JADX INFO: Access modifiers changed from: protected */
    public CareDashboardBinding(Object obj, View view, int i, RelativeLayout relativeLayout, FontTextView fontTextView, RoundImageButtonView roundImageButtonView, AlertBannerButtonView alertBannerButtonView, AlertBannerButtonView alertBannerButtonView2, LinearLayout linearLayout, FontTextView fontTextView2, ListItemLink listItemLink, FontTextView fontTextView3, SectionHeader sectionHeader, LinearLayout linearLayout2, SectionHeader sectionHeader2, RecyclerView recyclerView, RecyclerView recyclerView2, SwipeRefreshLayout swipeRefreshLayout, ErrorView errorView, View view2, SectionHeader sectionHeader3, SectionHeader sectionHeader4, ViewSwitcher viewSwitcher, FontTextView fontTextView4, AlertBannerButtonView alertBannerButtonView3, AlertBannerButtonView alertBannerButtonView4, FontTextView fontTextView5) {
        super(obj, view, i);
        this.activityPanel = relativeLayout;
        this.allRecentActivitiesLink = fontTextView;
        this.assistButton = roundImageButtonView;
        this.bottomLeftButton = alertBannerButtonView;
        this.bottomRightButton = alertBannerButtonView2;
        this.careCardsLayout = linearLayout;
        this.careContactName = fontTextView2;
        this.cgAdminIngress = listItemLink;
        this.changeTimezoneDashboard = fontTextView3;
        this.circleOfSupportHeader = sectionHeader;
        this.commsPanel = linearLayout2;
        this.commsPanelHeader = sectionHeader2;
        this.dashboardActivitiesListView = recyclerView;
        this.dashboardCards = recyclerView2;
        this.dashboardRefreshContainer = swipeRefreshLayout;
        this.errorView = errorView;
        this.loadingView = view2;
        this.manageTitle = sectionHeader3;
        this.recentTitle = sectionHeader4;
        this.switcher = viewSwitcher;
        this.timezoneDashboard = fontTextView4;
        this.topLeftButton = alertBannerButtonView3;
        this.topRightButton = alertBannerButtonView4;
        this.viewYourCircleMembersLink = fontTextView5;
    }

    public static CareDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CareDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public BilobaDashboardView getHandler() {
        return this.mHandler;
    }

    @Nullable
    public BilobaDashboardViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setHandler(@Nullable BilobaDashboardView bilobaDashboardView);

    public abstract void setViewmodel(@Nullable BilobaDashboardViewModel bilobaDashboardViewModel);

    @Deprecated
    public static CareDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CareDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.care_dashboard);
    }

    @NonNull
    @Deprecated
    public static CareDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CareDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.care_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static CareDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CareDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CareDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.care_dashboard, null, false, obj);
    }
}
