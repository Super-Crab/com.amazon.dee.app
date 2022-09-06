package com.amazon.alexa.biloba.view.recent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.RecentActivitiesBinding;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.biloba.view.common.recycler.RecyclerPaginationListener;
import com.amazon.alexa.biloba.view.common.recycler.RecyclerViewAdapter;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes6.dex */
public class BilobaActivitiesView extends BilobaViewController {
    private static final int LOAD_MORE_ITEM_THRESHOLD = 5;
    private static final String TAG = "BilobaActivitiesView";
    private final Context context;
    private RecyclerPaginationListener paginationListener;
    RecyclerView recentActivityRecyclerView;
    private View recentView;
    RecyclerViewAdapter recyclerViewAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    RecentActivityListViewModel viewModel = new RecentActivityListViewModel();

    public BilobaActivitiesView(Context context, Bundle bundle) {
        this.context = context;
        this.viewModel.create(bundle);
    }

    public void updateListView(List<BaseRecyclerItem> list) {
        LogUtils.i(TAG, "updateListView for generic BaseRecyclerItem");
        this.recyclerViewAdapter.updateItems(list);
        RecyclerPaginationListener recyclerPaginationListener = this.paginationListener;
        if (recyclerPaginationListener != null) {
            recyclerPaginationListener.setIsLoading(false);
            this.paginationListener.setHasMore(this.viewModel.getHasMore());
        }
    }

    public void updateRefreshing(Boolean bool) {
        if (!bool.booleanValue()) {
            this.swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.all_activity_title);
    }

    public /* synthetic */ void lambda$makeView$0$BilobaActivitiesView() {
        this.swipeRefreshLayout.setRefreshing(true);
        this.viewModel.clear();
        this.viewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        RecentActivitiesBinding recentActivitiesBinding = (RecentActivitiesBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.recent_activities, viewGroup, false);
        recentActivitiesBinding.setLifecycleOwner((LifecycleOwner) viewGroup.getContext());
        recentActivitiesBinding.setViewmodel(this.viewModel);
        this.recentView = recentActivitiesBinding.getRoot();
        this.swipeRefreshLayout = (SwipeRefreshLayout) this.recentView.findViewById(R.id.refresh_container);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.alexa.biloba.view.recent.-$$Lambda$BilobaActivitiesView$LcLLx-EuSeD3qHbHcjuBgKq4tTE
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                BilobaActivitiesView.this.lambda$makeView$0$BilobaActivitiesView();
            }
        });
        this.swipeRefreshLayout.setColorSchemeColors(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicBackground));
        this.recentActivityRecyclerView = (RecyclerView) this.recentView.findViewById(R.id.recent_activities_list_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        LogUtils.d(TAG, "setting layout manage to  activityRecyclerView");
        this.recentActivityRecyclerView.setLayoutManager(linearLayoutManager);
        registerViewAttributes((LinearLayout) this.recentView.findViewById(R.id.no_connection_banner), this.viewModel);
        this.paginationListener = new RecyclerPaginationListener(linearLayoutManager, 5) { // from class: com.amazon.alexa.biloba.view.recent.BilobaActivitiesView.1
            {
                BilobaActivitiesView.this = this;
            }

            @Override // com.amazon.alexa.biloba.view.common.recycler.RecyclerPaginationListener
            public void onLoadMore() {
                LogUtils.i(BilobaActivitiesView.TAG, "Loading more activities");
                BilobaActivitiesView.this.viewModel.sendRequest();
            }
        };
        this.recentActivityRecyclerView.addOnScrollListener(this.paginationListener);
        this.recyclerViewAdapter = new RecyclerViewAdapter();
        this.recentActivityRecyclerView.setAdapter(this.recyclerViewAdapter);
        return this.recentView;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric(MetricsConstants.UserInteractionMetrics.ALL_ACTIVITY_VIEW, "");
        subscribeToNetworkChange();
        this.viewModel.create(null);
        this.viewModel.getActivities().observe((LifecycleOwner) this.context, new $$Lambda$BilobaActivitiesView$sZo9qrMgQbhcyuOlOOKFjUHA0_0(this));
        this.viewModel.getIsLoading().observe((LifecycleOwner) this.context, new $$Lambda$BilobaActivitiesView$9mpKhe2Y29rmIhUAO2LDxIP3IqQ(this));
        this.swipeRefreshLayout.setRefreshing(true);
        this.viewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        this.viewModel.getActivities().removeObserver(new $$Lambda$BilobaActivitiesView$sZo9qrMgQbhcyuOlOOKFjUHA0_0(this));
        this.viewModel.getIsLoading().removeObserver(new $$Lambda$BilobaActivitiesView$9mpKhe2Y29rmIhUAO2LDxIP3IqQ(this));
        this.viewModel.destroy();
    }
}
