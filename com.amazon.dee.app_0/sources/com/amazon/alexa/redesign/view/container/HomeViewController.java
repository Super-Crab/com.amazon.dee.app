package com.amazon.alexa.redesign.view.container;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.redesign.DismissedCardDataStore;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.LatencyReportingDelegate;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.ViewControllerToRoutingInterface;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.api.EventBusListener;
import com.amazon.alexa.redesign.api.Header;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.debug.ActivityLauncher;
import com.amazon.alexa.redesign.debug.menu.DebugMenuService;
import com.amazon.alexa.redesign.dependency.HomeDependencies;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.header.HeaderNavigationDelegate;
import com.amazon.alexa.redesign.interactor.HomeInteractor;
import com.amazon.alexa.redesign.metrics.HomeFeedViewRecorder;
import com.amazon.alexa.redesign.metrics.ViewRecorderViewApi;
import com.amazon.alexa.redesign.presenter.ClickToDismissHandler;
import com.amazon.alexa.redesign.presenter.HomePresenter;
import com.amazon.alexa.redesign.repository.EarlyEventBusMessageRepository;
import com.amazon.alexa.redesign.repository.HomeCardsRepository;
import com.amazon.alexa.redesign.repository.VoxIngressRepository;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.DividerItemDecorator;
import com.amazon.alexa.redesign.utils.HomeChannelLifeCycleObserver;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import com.amazon.alexa.redesign.utils.LatencyInteractor;
import com.amazon.alexa.redesign.view.errorPage.ErrorPageApi;
import com.amazon.alexa.redesign.view.header.HomeHeader;
import com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder;
import com.amazon.alexa.redesign.view.homeFeed.CardScrollListener;
import com.amazon.alexa.redesign.view.homeFeed.HomeAdapter;
import com.amazon.alexa.redesign.view.homeFeed.HomeLinearLayout;
import com.amazon.alexa.redesign.view.homeFeed.RVAccessibilityDelegate;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchController;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchControllerRTL;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.homeFeed.StartSnapHelper;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.amazon.alexa.redesign.view.templates.domainCardTemplate.EventCache;
import com.amazon.alexa.redesign.view.templates.domainCardTemplate.HomePersonalizationEventCapture;
import com.amazon.alexa.redesign.view.templates.domainCardTemplate.PrependedViewTracker;
import com.amazon.alexa.redesign.view.templates.heroTemplate.HeroTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.listTemplate.ListTemplateViewHolder;
import com.amazon.alexa.redesign.view.templates.miniTemplate.MiniTemplateViewHolder;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.registry.ViewProviderRegistry;
import com.amazon.alexa.viewprovider.registry.CardViewProviderRegistry;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.LatencyMarker;
import com.amazon.latencyinfra.LatencyNamespace;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class HomeViewController implements HomeContract.View, ActivityLauncher, ViewRecorderViewApi, ViewController {
    private static boolean offlineMode = false;
    @Inject
    ActionFactory actionFactory;
    @Inject
    BridgeStatusService bridgeStatusService;
    private RecyclerView.OnScrollListener cardscrollListener;
    private Context context;
    private RecyclerView.ItemDecoration dividerItemDecoration;
    private EarlyEventBusMessageRepository earlyEventBusMessageRepository;
    @VisibleForTesting
    ErrorPageApi errorPage;
    ViewStub errorPageViewStub;
    @Inject
    EventBus eventBus;
    private EventCache eventCache;
    private EventCapture eventCapture;
    @Inject
    FeatureServiceV2 featureServiceV2;
    private Header header;
    @Inject
    HeaderNavigationDelegate headerNavigationDelegate;
    private HomeAdapter homeAdapter;
    @Inject
    HomeCardsRepository homeCardsRepository;
    private HomeChannelLifeCycleObserver homeChannelLifeCycleObserver;
    @Inject
    HomeFeedServiceClient homeFeedServiceClient;
    private HomeMetricsRecorder homeMetricsRecorder;
    private HomeContract.OEInteractor homeOEInteractor;
    private RecyclerView homeRecyclerView;
    @Inject
    LatencyInfra latencyInfra;
    private HomeContract.LatencyInteractor latencyInteractor;
    @Inject
    LatencyReportingDelegate latencyReportingDelegate;
    private LinearLayoutManager layoutManager;
    @Inject
    MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar;
    @Inject
    Mobilytics mobilytics;
    @Inject
    NetworkService networkService;
    @VisibleForTesting
    HomeContract.Presenter presenter;
    @Inject
    ReactInstanceManager reactInstanceManager;
    private RelativeLayout refreshButtonWrapper;
    private ViewControllerToRoutingInterface routingInterface;
    @Inject
    RoutingService routingService;
    @Inject
    DismissedCardDataStore sharedPreferencesRepository;
    private StartSnapHelper snapHelper;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerItemTouchController touchController;
    private View view;
    private ViewProviderRegistry viewProviderRegistry;
    @Inject
    VoiceService voiceService;
    @Inject
    VoxIngressRepository voxIngressRepository;
    private boolean userLoggedOut = false;
    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() { // from class: com.amazon.alexa.redesign.view.container.HomeViewController.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            HomeContract.Presenter presenter = HomeViewController.this.presenter;
            if (presenter != null) {
                presenter.onNetworkChanged();
            }
        }
    };
    private BroadcastReceiver configurationChangeReceiver = new BroadcastReceiver() { // from class: com.amazon.alexa.redesign.view.container.HomeViewController.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            HomeContract.Presenter presenter = HomeViewController.this.presenter;
            if (presenter != null) {
                presenter.onConfigurationChange();
            }
        }
    };
    private EventBusListener rnRouteRequestListener = new EventBusListener() { // from class: com.amazon.alexa.redesign.view.container.HomeViewController.3
        @Override // com.amazon.alexa.redesign.api.EventBusListener
        public void execute(JSONObject jSONObject) {
            try {
                RoutingService.RoutingBuilder match = HomeViewController.this.routingService.match(jSONObject.getString("route"));
                if (match == null) {
                    return;
                }
                match.navigate();
            } catch (Exception e) {
                Log.e("[RNRouteListener]", "Failed to route using Native routing service.", e);
            }
        }
    };
    private boolean isColdStart = true;

    private void inflateErrorPage() {
        if (this.errorPage == null) {
            View inflate = this.errorPageViewStub.inflate();
            if (!(inflate instanceof ErrorPageApi)) {
                return;
            }
            this.errorPage = (ErrorPageApi) inflate;
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void displayBottomSheet(BottomSheetDialogFragment bottomSheetDialogFragment) {
        bottomSheetDialogFragment.show(((AppCompatActivity) this.view.getContext()).getSupportFragmentManager(), "bottom_sheet");
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void displayErrorPage() {
        inflateErrorPage();
        this.errorPage.setVisibility(true);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public Pair<Integer, CardModel> findDataByDismissId(String str) {
        return this.homeAdapter.findDataByDismissId(str);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void finishRefresh() {
        this.swipeRefreshLayout.setRefreshing(false);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void forceUpdateViewItem(final int i) {
        ((Activity) this.context).runOnUiThread(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$V3qLX2GbB4Mdr3IGDGudrcjXrQc
            @Override // java.lang.Runnable
            public final void run() {
                HomeViewController.this.lambda$forceUpdateViewItem$13$HomeViewController(i);
            }
        });
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public boolean getCslOverride() {
        return true;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public int getFirstReturnToPosition() {
        return CardScrollListener.returnToPos;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View, com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public int getFirstVisibleItemPosition() {
        return this.layoutManager.findFirstVisibleItemPosition();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public int getLastReturnToPosition() {
        return CardScrollListener.returnToLastVisibleCard;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View, com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public int getLastVisibleItemPosition() {
        return this.layoutManager.findLastVisibleItemPosition();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public boolean getLogoutFlag() {
        return this.userLoggedOut;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View, com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public double getPercentCardOnScreen(boolean z, int i) {
        double d;
        int height;
        Rect rect = new Rect();
        this.homeRecyclerView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        View findViewByPosition = this.layoutManager.findViewByPosition(i);
        if (findViewByPosition != null) {
            findViewByPosition.getGlobalVisibleRect(rect2);
            if (z) {
                d = rect2.bottom - (this.header.getHeight() + rect.top);
                height = findViewByPosition.getHeight();
            } else {
                d = rect.bottom - rect2.top;
                height = findViewByPosition.getHeight();
            }
            return d / height;
        }
        return -1.0d;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public boolean getShouldVoxAnimateOnCacheLoad() {
        int i = CardScrollListener.returnToPos;
        return (i == 0 || i == -1) && !offlineMode;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public String getTitle(Context context) {
        return null;
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public Map<String, Object> getTopLevelMetricsObject(Object obj) {
        return null;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public int getTotalViewItems() {
        return this.layoutManager.getItemCount();
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void goToPosition(int i) {
        if (i < 0 || i >= this.layoutManager.getItemCount()) {
            return;
        }
        this.layoutManager.scrollToPosition(i);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void handleRotation() {
        if (this.view.getDisplay() != null) {
            this.homeAdapter.notifyDataSetChanged();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$cpn4N4vvq6nCk5K8-l_YbffZATM
                    @Override // java.lang.Runnable
                    public final void run() {
                        HomeViewController.this.lambda$handleRotation$14$HomeViewController();
                    }
                });
            } else {
                lambda$returnToLastVisitedCard$12$HomeViewController();
            }
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void hideErrorPage() {
        inflateErrorPage();
        this.errorPage.setVisibility(false);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void initRNPlaceholder(final Bundle bundle) {
        final ReactRootView reactRootView = new ReactRootView(this.context);
        this.bridgeStatusService.registerListener(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$p7erXeFFoUDgSVcq4qEs-zaWlSU
            @Override // java.lang.Runnable
            public final void run() {
                HomeViewController.this.lambda$initRNPlaceholder$18$HomeViewController(reactRootView, bundle);
            }
        });
        ((LinearLayout) this.view.findViewById(R.id.device_picker_container)).addView(reactRootView);
    }

    public void initRefreshPill() {
        Button button = (Button) this.view.findViewById(R.id.live_updates_refresh_button);
        button.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicBackground));
        button.setBackgroundColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        TemplateHelperUtil.scaleTextViewWithFontFireOS(button, this.context, R.integer.amahc_fireos_font_scaling_button);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$JPdm_Xzja8HfzwwMw8sW2tZ7VK0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeViewController.this.lambda$initRefreshPill$15$HomeViewController(view);
            }
        });
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public boolean isViewAttached() {
        return true;
    }

    public /* synthetic */ void lambda$forceUpdateViewItem$13$HomeViewController(int i) {
        this.homeAdapter.forceUpdateViewItem(i);
    }

    public /* synthetic */ void lambda$initRNPlaceholder$18$HomeViewController(final ReactRootView reactRootView, final Bundle bundle) {
        Context context = this.context;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$vrLdJUIGapbAUbfsljhn3bl0nzM
                @Override // java.lang.Runnable
                public final void run() {
                    HomeViewController.this.lambda$null$16$HomeViewController(reactRootView, bundle);
                }
            });
        } else {
            new Handler(context.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$Wgte6jyxfWIiTy3W7elEs0f906U
                @Override // java.lang.Runnable
                public final void run() {
                    HomeViewController.this.lambda$null$17$HomeViewController(reactRootView, bundle);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initRefreshPill$15$HomeViewController(View view) {
        this.presenter.onRefreshButtonClicked();
    }

    public /* synthetic */ void lambda$makeView$7$HomeViewController() {
        if (DebugMenuService.getInstance().getModel().getLocalJSON()) {
            this.presenter.displayMockedCards();
            return;
        }
        this.presenter.fetchHomeCardsFromServer(!offlineMode, Constants.VIEW_UPDATE_TYPE_PULLREFRESH);
        this.homeOEInteractor.recordPullToRefresh();
    }

    public /* synthetic */ void lambda$null$16$HomeViewController(ReactRootView reactRootView, Bundle bundle) {
        reactRootView.startReactApplication(this.reactInstanceManager, "HomeCard", bundle);
    }

    public /* synthetic */ void lambda$null$17$HomeViewController(ReactRootView reactRootView, Bundle bundle) {
        reactRootView.startReactApplication(this.reactInstanceManager, "HomeCard", bundle);
    }

    public /* synthetic */ void lambda$onCancelTimerAction$19$HomeViewController(int i) {
        this.homeAdapter.onCancelTimerAction(i);
    }

    public /* synthetic */ void lambda$onCreate$0$HomeViewController(boolean z) {
        this.presenter.onRequestHomeRefreshEvent(z);
    }

    public /* synthetic */ void lambda$onCreate$1$HomeViewController() {
        this.presenter.onLogOut();
    }

    public /* synthetic */ void lambda$onCreate$2$HomeViewController(boolean z) {
        this.presenter.onModalToggle(z);
    }

    public /* synthetic */ void lambda$onCreate$3$HomeViewController() {
        this.presenter.onLocalCardEventReceived();
    }

    public /* synthetic */ void lambda$onCreate$4$HomeViewController() {
        this.presenter.displayActiveCardsFromCache(Constants.VIEW_UPDATE_TYPE_CACHE);
    }

    public /* synthetic */ void lambda$onCreate$5$HomeViewController() {
        this.presenter.onLiveUpdateEvent();
    }

    public /* synthetic */ void lambda$onCreate$6$HomeViewController(JSONObject jSONObject) {
        this.header.rerenderLeftHeader(jSONObject.optBoolean("enabled", false), offlineMode);
    }

    public /* synthetic */ void lambda$recordViewsForVisibleCarouselItems$20$HomeViewController() {
        this.homeAdapter.recordViewsForVisibleCarouselItems();
    }

    public /* synthetic */ void lambda$removeCardAt$11$HomeViewController(int i) {
        this.homeAdapter.removeCardAt(i);
        RecyclerItemTouchController recyclerItemTouchController = this.touchController;
        if (recyclerItemTouchController != null) {
            recyclerItemTouchController.resetButtonState();
        }
    }

    public /* synthetic */ void lambda$updateCardModels$10$HomeViewController(List list) {
        this.homeRecyclerView.setItemViewCacheSize(list.size());
        this.homeAdapter.updateCardModels(list);
    }

    @Override // com.amazon.alexa.redesign.debug.ActivityLauncher
    public void launchActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this.context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.context.startActivity(intent);
    }

    @Override // com.amazon.alexa.redesign.debug.ActivityLauncher
    public void launchActivityForResult(Class cls, Bundle bundle, int i) {
        Intent intent = new Intent(this.context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ((Activity) this.context).startActivityForResult(intent, i);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        Log.w("HomeViewController", "makeView");
        boolean z = false;
        this.view = layoutInflater.inflate(R.layout.amahc_home_container, viewGroup, false);
        setViewBackgroundColor(this.view);
        this.errorPageViewStub = (ViewStub) this.view.findViewById(R.id.error_view_stub);
        this.homeAdapter = new HomeAdapter(this.context, this.presenter, this.reactInstanceManager, this.bridgeStatusService, this.viewProviderRegistry, this.homeMetricsRecorder, this.eventCapture, this.homeOEInteractor, this.actionFactory, this.homeFeedServiceClient);
        this.swipeRefreshLayout = (SwipeRefreshLayout) this.view.findViewById(R.id.refresh_container);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$Ey2Ps8kHWFtpKddjRBvtn0yNUvk
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                HomeViewController.this.lambda$makeView$7$HomeViewController();
            }
        });
        this.header = new HomeHeader(this.view, this.context, this.headerNavigationDelegate, this.voiceService, this.presenter, this.voxIngressRepository, this.featureServiceV2, this.routingService, offlineMode);
        this.homeRecyclerView = (RecyclerView) this.view.findViewById(R.id.redesign_home_container_main_view);
        setViewBackgroundColor(this.homeRecyclerView);
        RelativeLayout relativeLayout = (RelativeLayout) this.view.findViewById(R.id.home_header);
        this.layoutManager = new HomeLinearLayout(this.context, this.presenter, this.homeAdapter, this, relativeLayout);
        this.homeRecyclerView.setLayoutManager(this.layoutManager);
        this.homeRecyclerView.setAdapter(this.homeAdapter);
        RecyclerView recyclerView = this.homeRecyclerView;
        recyclerView.setAccessibilityDelegateCompat(new RVAccessibilityDelegate(recyclerView));
        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
            z = true;
        }
        if (z) {
            this.touchController = new RecyclerItemTouchController();
        } else {
            this.touchController = new RecyclerItemTouchControllerRTL();
        }
        new ItemTouchHelper(this.touchController).attachToRecyclerView(this.homeRecyclerView);
        this.cardscrollListener = new CardScrollListener(this.layoutManager, this.homeAdapter, this, this.presenter, (RelativeLayout) this.view.findViewById(R.id.home_header_alexa_wrapper), relativeLayout, this.context, offlineMode);
        this.homeRecyclerView.addOnScrollListener(this.cardscrollListener);
        this.homeRecyclerView.setItemAnimator(null);
        if (!TemplateHelperUtil.isFireOS()) {
            this.snapHelper.attachToRecyclerView(this.homeRecyclerView);
        }
        this.refreshButtonWrapper = (RelativeLayout) this.view.findViewById(R.id.live_updates_refresh_button_wrapper);
        initRefreshPill();
        this.presenter.subscribeToEventBus();
        this.presenter.publishHomeInitializedEvent();
        this.latencyInfra.mark(LatencyMarker.HOME_VIEW_CREATE_CSL, LatencyNamespace.PROFILE_COLDSTART);
        this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_CREATED_VIEW);
        return this.view;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        this.reactInstanceManager.onHostResume((Activity) this.context);
        this.mainActivityLifecycleObserverRegistrar.addObserver(this.homeChannelLifeCycleObserver);
        this.presenter.start(this.isColdStart);
        this.presenter.recordCardsSeenOnColdStart(CardScrollListener.returnToPos, CardScrollListener.returnToLastVisibleCard);
        this.isColdStart = false;
        this.dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(this.context, R.drawable.amahc_divider));
        this.homeRecyclerView.addItemDecoration(this.dividerItemDecoration);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void onCancelTimerAction(final int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$Kb8IuBrZSPbP0oX1uL4l284nB6E
            @Override // java.lang.Runnable
            public final void run() {
                HomeViewController.this.lambda$onCancelTimerAction$19$HomeViewController(i);
            }
        });
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(Context context) {
        HomeDependencies.inject(this);
        this.context = context;
        ThemeUtil.setTheme(context);
        this.homeMetricsRecorder = new HomeMetricsRecorder(this.mobilytics);
        this.latencyInteractor = new LatencyInteractor(this.latencyInfra, this.latencyReportingDelegate);
        this.homeOEInteractor = new HomeOEInteractor(this.mobilytics);
        this.snapHelper = new StartSnapHelper();
        this.eventCache = new EventCache();
        HomeInteractor homeInteractor = new HomeInteractor(this.homeCardsRepository, this.voxIngressRepository, this.networkService, this.sharedPreferencesRepository, this.eventCache, this.eventBus, this, new HomeContract.RequestHomeRefreshListener() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$Ui-XokU0Br9TNGYir-Ck0mpd_aY
            @Override // com.amazon.alexa.redesign.HomeContract.RequestHomeRefreshListener
            public final void onRequestHomeRefreshEvent(boolean z) {
                HomeViewController.this.lambda$onCreate$0$HomeViewController(z);
            }
        }, new HomeContract.LogOutListener() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$PoudftRzTVxBTEks3vIHe04TSlU
            @Override // com.amazon.alexa.redesign.HomeContract.LogOutListener
            public final void setFlagOnLogOut() {
                HomeViewController.this.lambda$onCreate$1$HomeViewController();
            }
        }, new HomeContract.ModalToggleListener() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$lAlb11_t2G-JqsXjaoDtWlq6OlU
            @Override // com.amazon.alexa.redesign.HomeContract.ModalToggleListener
            public final void onModalToggle(boolean z) {
                HomeViewController.this.lambda$onCreate$2$HomeViewController(z);
            }
        }, new HomeContract.LocalCardListener() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$OdWCghQ3xgc1Mrd5OtMUAT6l4V0
            @Override // com.amazon.alexa.redesign.HomeContract.LocalCardListener
            public final void onLocalCardEventReceived() {
                HomeViewController.this.lambda$onCreate$3$HomeViewController();
            }
        }, new HomeContract.CardDismissedListener() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$P-jS2CL4PmhwpdMbZrUH2rRO_zo
            @Override // com.amazon.alexa.redesign.HomeContract.CardDismissedListener
            public final void displayActiveCardsFromCache() {
                HomeViewController.this.lambda$onCreate$4$HomeViewController();
            }
        }, new HomeContract.LiveUpdatesListener() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$LvKWrX4KUJgtL-imI-TWW9HlSjw
            @Override // com.amazon.alexa.redesign.HomeContract.LiveUpdatesListener
            public final void onLiveUpdateEvent() {
                HomeViewController.this.lambda$onCreate$5$HomeViewController();
            }
        }, new EventBusListener() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$u1aBcbIgtnPsAuipZyxgYhNr4gs
            @Override // com.amazon.alexa.redesign.api.EventBusListener
            public final void execute(JSONObject jSONObject) {
                HomeViewController.this.lambda$onCreate$6$HomeViewController(jSONObject);
            }
        }, this.rnRouteRequestListener);
        ClickToDismissHandler clickToDismissHandler = new ClickToDismissHandler(this, homeInteractor);
        this.presenter = new HomePresenter(this, homeInteractor, this.homeMetricsRecorder, this.latencyInteractor, this.homeOEInteractor, this.routingService, clickToDismissHandler, new HomeFeedViewRecorder(this, this.homeMetricsRecorder), this.featureServiceV2);
        this.homeChannelLifeCycleObserver = new HomeChannelLifeCycleObserver(this.presenter, this, this.routingInterface);
        this.viewProviderRegistry = new CardViewProviderRegistry(context, this.reactInstanceManager, this.bridgeStatusService);
        this.eventCapture = new HomePersonalizationEventCapture(this.homeMetricsRecorder, new PrependedViewTracker(this.eventCache), clickToDismissHandler);
        EarlyEventBusMessageRepository earlyEventBusMessageRepository = this.earlyEventBusMessageRepository;
        if (earlyEventBusMessageRepository != null) {
            homeInteractor.processEarlyEventBusMessages(earlyEventBusMessageRepository.getMessages());
            this.routingInterface.notifyEarlyEventBusMessagesProcessed();
        }
        registerReceivers();
        this.latencyInteractor.recordProfileEvent(LatencyInteractor.PROFILE_EVENT_INITIALIZED_CLASSES);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDestroy(Context context) {
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        for (int i = 0; i < this.homeRecyclerView.getChildCount(); i++) {
            RecyclerView recyclerView = this.homeRecyclerView;
            ((BaseCardViewHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(i))).detach();
        }
        this.mainActivityLifecycleObserverRegistrar.removeObserver(this.homeChannelLifeCycleObserver);
        this.homeAdapter.onDetach();
        this.presenter.end();
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.device_picker_container);
        for (int i2 = 0; i2 < linearLayout.getChildCount(); i2++) {
            View childAt = linearLayout.getChildAt(i2);
            if (childAt instanceof ReactRootView) {
                final ReactRootView reactRootView = (ReactRootView) childAt;
                this.bridgeStatusService.registerListener(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$wbfw1EZEzGJFmnx3xFWUNYzu9PQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$EON645O8VzguQzjvxNeYu4iS5eg
                            @Override // java.lang.Runnable
                            public final void run() {
                                ReactRootView.this.unmountReactApplication();
                            }
                        });
                    }
                });
            }
        }
        linearLayout.removeAllViews();
        RecyclerView.ItemDecoration itemDecoration = this.dividerItemDecoration;
        if (itemDecoration != null) {
            this.homeRecyclerView.removeItemDecoration(itemDecoration);
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void onErrorPageRetryClicked() {
        this.presenter.fetchHomeCardsFromServer(true, Constants.VIEW_UPDATE_TYPE_ERROR_PAGE_RETRY);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View, com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public void recordCardsSeenOnColdStart(int i, int i2) {
        CardScrollListener.returnToPos = i;
        CardScrollListener.returnToLastVisibleCard = i2;
        this.presenter.recordCardsSeenOnColdStart(i, i2);
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public void recordViewsForVisibleCarouselItems() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$1EScUZ1SDs7t7NlpZE66ziQjFRU
            @Override // java.lang.Runnable
            public final void run() {
                HomeViewController.this.lambda$recordViewsForVisibleCarouselItems$20$HomeViewController();
            }
        });
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void registerReceivers() {
        this.context.registerReceiver(this.networkChangeReceiver, GeneratedOutlineSupport1.outline10(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        this.context.registerReceiver(this.configurationChangeReceiver, intentFilter);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void removeCardAt(final int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$tKW9kql0FYH-AxZwKhb7dBVTwRE
            @Override // java.lang.Runnable
            public final void run() {
                HomeViewController.this.lambda$removeCardAt$11$HomeViewController(i);
            }
        });
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void resetReturnPosition() {
        CardScrollListener.returnToPos = -1;
        CardScrollListener.returnToCardId = "";
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void returnToLastVisitedCard() {
        CardScrollListener.returnToPos = this.homeAdapter.getPositionFromCardId(CardScrollListener.returnToCardId);
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$vHxopfbn4Xn59AeEpk9_0YgSZe8
                @Override // java.lang.Runnable
                public final void run() {
                    HomeViewController.this.lambda$returnToLastVisitedCard$12$HomeViewController();
                }
            });
        } else {
            lambda$returnToLastVisitedCard$12$HomeViewController();
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    /* renamed from: returnToLastVisitedPosition */
    public void lambda$returnToLastVisitedCard$12$HomeViewController() {
        int i = CardScrollListener.returnToPos;
        if (i < 0 || i >= this.layoutManager.getItemCount()) {
            return;
        }
        this.layoutManager.scrollToPositionWithOffset(CardScrollListener.returnToPos, CardScrollListener.returnToOffset);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void revokeSwipeExperience() {
        RecyclerItemTouchController recyclerItemTouchController = this.touchController;
        if (recyclerItemTouchController != null) {
            recyclerItemTouchController.revokeSwipeExperience();
        }
    }

    public HomeViewController setEarlyEventBusMessageRepository(EarlyEventBusMessageRepository earlyEventBusMessageRepository) {
        this.earlyEventBusMessageRepository = earlyEventBusMessageRepository;
        return this;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void setFirstRenderCompleteOnLogOut() {
        LinearLayoutManager linearLayoutManager = this.layoutManager;
        if (linearLayoutManager instanceof HomeLinearLayout) {
            ((HomeLinearLayout) linearLayoutManager).resetFirstRenderCompleteOnLogout();
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void setLogoutFlag(boolean z) {
        this.userLoggedOut = z;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void setOfflineBannerVisibility(boolean z) {
        offlineMode = z;
        CardScrollListener.offlineMode = z;
        if (z) {
            this.header.convertToOffline();
            this.homeRecyclerView.setAccessibilityTraversalAfter(R.id.banner_text);
            return;
        }
        this.header.convertToOnline();
        this.homeRecyclerView.setAccessibilityTraversalAfter(R.id.home_header_help_feedback);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void setOutageAlert(@Nullable String str) {
        if (offlineMode) {
            return;
        }
        this.header.setOutageAlert(str);
        updateAccessibilityOrder(str);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void setRefreshButtonVisibility(boolean z) {
        this.refreshButtonWrapper.setVisibility(z ? 0 : 8);
    }

    public HomeViewController setRoutingInterface(ViewControllerToRoutingInterface viewControllerToRoutingInterface) {
        this.routingInterface = viewControllerToRoutingInterface;
        return this;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void setViewBackgroundColor(View view) {
        view.setBackgroundColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicBackground));
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View, com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public boolean shouldRecordViewForCard(double d, int i) {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.homeRecyclerView.findViewHolderForAdapterPosition(i);
        return findViewHolderForAdapterPosition instanceof MiniTemplateViewHolder ? d >= 1.0d : (!(findViewHolderForAdapterPosition instanceof ListTemplateViewHolder) && !(findViewHolderForAdapterPosition instanceof HeroTemplateViewHolder)) || d >= 0.5d;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void unregisterReceivers() {
        try {
            this.context.unregisterReceiver(this.networkChangeReceiver);
            this.context.unregisterReceiver(this.configurationChangeReceiver);
        } catch (IllegalArgumentException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to unregister receivers: ");
            outline107.append(e.getMessage());
            Log.e(Constants.VIEW_CONTROLLER_TAG, outline107.toString());
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void updateAccessibilityOrder(String str) {
        if (this.refreshButtonWrapper.getVisibility() == 0) {
            this.homeRecyclerView.setAccessibilityTraversalAfter(R.id.live_updates_refresh_button);
        } else if (str != null) {
            this.homeRecyclerView.setAccessibilityTraversalAfter(R.id.banner_text);
        } else {
            this.homeRecyclerView.setAccessibilityTraversalAfter(R.id.home_header_help_feedback);
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void updateCardModel(int i, RecyclerViewItem recyclerViewItem) {
        this.homeAdapter.forceReplaceViewItem(i, recyclerViewItem);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View
    public void updateCardModels(final List<CardModel> list) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.container.-$$Lambda$HomeViewController$fL-wBmJBKpiz7sF0inwAmrAc3QU
                @Override // java.lang.Runnable
                public final void run() {
                    HomeViewController.this.lambda$updateCardModels$10$HomeViewController(list);
                }
            });
            return;
        }
        this.homeRecyclerView.setItemViewCacheSize(list.size());
        this.homeAdapter.updateCardModels(list);
    }

    public void updatePreviousRoute(String str) {
        this.presenter.setLastRouteName(str);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.View, com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    /* renamed from: getViewItemFromPosition */
    public RecyclerViewItem mo2380getViewItemFromPosition(int i) {
        return this.homeAdapter.getViewItem(i);
    }
}
