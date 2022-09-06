package com.amazon.deecomms.common.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.CommsMasterFragment;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.conversation.ConversationRouting;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.services.conversation.ConversationUIDelegate;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class ConversationRoutingAdapter implements RoutingAdapter {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ConversationRoutingAdapter.class);
    private final Activity activity;
    private final Context applicationContext;
    private final CommsManager commsManager;
    private final Lazy<CommsServiceV2> commsServiceV2;
    private final int containerId;
    private final ConversationRouting conversationRouting;
    private final ConversationUIDelegate conversationUIDelegate;
    private final FragmentManager fragmentManager;
    private final Handler mainThreadHandler;
    private final MetricsService metricsService;
    private final String conversationContainerTag = "conversationContainerTag";
    private final SimpleArrayMap<String, RoutingAdapter.RouteConfiguration> configurations = new SimpleArrayMap<>();

    public ConversationRoutingAdapter(Activity activity, @IdRes int i, CommsManager commsManager, ConversationUIDelegate conversationUIDelegate, ConversationRouting conversationRouting, MetricsService metricsService, Lazy<CommsServiceV2> lazy) {
        this.activity = activity;
        this.applicationContext = activity.getApplicationContext();
        this.containerId = i;
        this.fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        this.commsManager = commsManager;
        this.conversationUIDelegate = conversationUIDelegate;
        this.conversationRouting = conversationRouting;
        this.metricsService = metricsService;
        this.commsServiceV2 = lazy;
        this.configurations.put("conversations", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.CONVERSATIONS_CONTACT, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.MANAGE_CONTACT_LIST, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.BLOCK_CONTACT_LIST, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.CHILD_CONTACT_CARD, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.WHITELIST_CONTACT, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.RELATIONSHIP_LIST, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.EDIT_NICKNAME, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.CONVERSATIONS_CONTACT_LIST, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.CONVERSATIONS_DIAGNOSTICS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.CONVERSATIONS_PATH, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.EDIT_CONTACT_CARD, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/comms/contact-list", RoutingAdapter.RouteConfiguration.all());
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    private void clearFragmentBackStack(@NonNull Fragment fragment) {
        if (fragment.isResumed() && fragment.getActivity() != null && !fragment.getActivity().isFinishing()) {
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            LOG.d(String.format("There are %s child fragments", Integer.valueOf(childFragmentManager.getBackStackEntryCount())));
            for (int i = 0; i < childFragmentManager.getBackStackEntryCount(); i++) {
                childFragmentManager.popBackStack();
            }
            try {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    LOG.i("App on main thread, not skipping pending transactions.");
                    childFragmentManager.executePendingTransactions();
                } else {
                    LOG.i("Skipping pending transactions, app not on main thread.");
                }
            } catch (IllegalStateException e) {
                String message = e.getMessage();
                LOG.i(message);
                MetricsHelper.recordOperationalMetricWithSource(MetricKeys.MAIN_THREAD_INCORRECT_REPORT, message);
            }
            LOG.d(String.format("There are now %s child fragments", Integer.valueOf(childFragmentManager.getBackStackEntryCount())));
            return;
        }
        LOG.d("Cannot clear backstack when fragment is paused, has no activity, or has finishing activity");
    }

    private void ensureMasterAndPerformRouting(CommsView commsView, RouteContext routeContext, Runnable runnable) {
        Route route = routeContext.getRoute();
        ensureMasterFragmentVisible(commsView != null && this.commsManager.isValidEntryPoint(commsView));
        if (popFromBackStack(route)) {
            runnable.run();
        } else if (commsView == null) {
        } else {
            this.commsManager.requestFragment(commsView, routeContext.getBundle("arguments"), true);
        }
    }

    private void ensureMasterFragmentVisible(boolean z) {
        Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag("conversationContainerTag");
        if (findFragmentByTag == null) {
            Bundle outline13 = GeneratedOutlineSupport1.outline13(CommsMasterFragment.DO_NOT_START_FRAGMENT, z);
            CommsMasterFragment commsMasterFragment = new CommsMasterFragment();
            commsMasterFragment.setArguments(outline13);
            LOG.d("creating a new comms fragment");
            this.fragmentManager.beginTransaction().replace(this.containerId, commsMasterFragment, "conversationContainerTag").commitAllowingStateLoss();
            this.fragmentManager.executePendingTransactions();
        } else if (!findFragmentByTag.isVisible()) {
            LOG.d("showing existing comms fragment");
            clearFragmentBackStack(findFragmentByTag);
            this.fragmentManager.beginTransaction().show(findFragmentByTag).commitAllowingStateLoss();
            this.fragmentManager.executePendingTransactions();
        } else {
            LOG.d("comms fragment already visible");
        }
    }

    private boolean isCommsContactListView(CommsView commsView) {
        return commsView == CommsView.ContactList || commsView == CommsView.ReactNativeContactList;
    }

    private boolean popFromBackStack(Route route) {
        Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag("conversationContainerTag");
        if (findFragmentByTag == null) {
            return false;
        }
        if (findFragmentByTag.isResumed() && findFragmentByTag.getActivity() != null && !findFragmentByTag.getActivity().isFinishing()) {
            return findFragmentByTag.getChildFragmentManager().popBackStackImmediate(route.getName(), 0);
        }
        LOG.d("Cannot clear pop back stack when fragment is paused, has no activity, or has finishing activity");
        return false;
    }

    private void reuseContactListFragmentAndReplaceHeader(RouteContext routeContext) {
        Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag("conversationContainerTag");
        if (findFragmentByTag != null) {
            FragmentManager childFragmentManager = findFragmentByTag.getChildFragmentManager();
            int backStackEntryCount = childFragmentManager.getBackStackEntryCount();
            if (backStackEntryCount > 0 && RouteName.CONVERSATIONS_CONTACT_LIST.equals(childFragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName())) {
                if (Constants.FRAGMENT_CONTACT_CARD.equals(routeContext.getBundle("arguments").getString(Constants.CONTACT_LIST_LAUNCH_FRAGMENT))) {
                    this.conversationUIDelegate.setHeaderTitle(this.applicationContext.getString(R.string.select_contact));
                } else if (Constants.FRAGMENT_CALL_BOTTOM_SHEET.equals(routeContext.getBundle("arguments").getString(Constants.CONTACT_LIST_LAUNCH_FRAGMENT))) {
                    this.conversationUIDelegate.setHeaderTitle(this.applicationContext.getString(R.string.start_call));
                } else {
                    this.conversationUIDelegate.setHeaderTitle(this.applicationContext.getString(R.string.message_a_contact));
                }
            } else if (!Constants.FRAGMENT_START_NEW_CONVERSATION.equals(routeContext.getBundle("arguments").getString(Constants.CONTACT_LIST_LAUNCH_FRAGMENT))) {
            } else {
                this.conversationUIDelegate.setHeaderTitle(this.applicationContext.getString(R.string.message_a_contact));
            }
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void enter(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void exit() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    @Nullable
    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        return this.configurations.get(route.getName());
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public int getId() {
        return 2;
    }

    public /* synthetic */ void lambda$leave$1$ConversationRoutingAdapter(Route route) {
        Fragment findFragmentByTag;
        if ((route == null || (!this.configurations.containsKey(route.getName()) && route.isReactNative())) && (findFragmentByTag = this.fragmentManager.findFragmentByTag("conversationContainerTag")) != null) {
            clearFragmentBackStack(findFragmentByTag);
        }
    }

    public /* synthetic */ void lambda$navigate$0$ConversationRoutingAdapter(RouteContext routeContext, Runnable runnable) {
        CommsView commsView = this.conversationRouting.getCommsView(routeContext.getRoute().getName());
        CommsProvisionStatus userProvisionStatus = this.commsManager.getUserProvisionStatus();
        if (CommsProvisionStatus.AUTO_PROVISIONED.equals(userProvisionStatus) && isCommsContactListView(commsView) && this.commsManager.shouldRouteToReactNativeContactList()) {
            ensureMasterAndPerformRouting(CommsView.ReactNativeContactList, routeContext, runnable);
        } else if (commsView == CommsView.ContactList && CommsProvisionStatus.AUTO_PROVISIONED.equals(userProvisionStatus)) {
            reuseContactListFragmentAndReplaceHeader(routeContext);
            ensureMasterAndPerformRouting(commsView, routeContext, runnable);
        } else if (this.commsServiceV2.mo358get().oobeService().shouldShowCommsOOBEForUser()) {
            Intent intent = new Intent(this.activity, OOBEActivity.class);
            intent.putExtra(OOBEActivity.OOBE_INTENT, commsView == null ? null : commsView.name());
            MetricsHelper.recordSingleOccurrence(CounterMetric.generateOperational(MetricKeys.OOBE_LAUNCH));
            this.activity.startActivityForResult(intent, 120);
        } else if (commsView == CommsView.ContactList) {
            reuseContactListFragmentAndReplaceHeader(routeContext);
        }
        if (routeContext.getRoute().is(RouteName.CONVERSATIONS_PATH)) {
            String decode = Uri.decode(routeContext.getString(RouteParameter.PATH));
            boolean z = routeContext.getBoolean("start", true);
            if (!TextUtils.isEmpty(decode)) {
                ensureMasterFragmentVisible(!z);
                this.commsManager.requestFragment(decode);
            }
        } else {
            ensureMasterAndPerformRouting(commsView, routeContext, runnable);
        }
        runnable.run();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, final Route route2) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.common.ui.main.-$$Lambda$ConversationRoutingAdapter$_83c-4kKk4w8pLjAPfstw9PUtp8
            @Override // java.lang.Runnable
            public final void run() {
                ConversationRoutingAdapter.this.lambda$leave$1$ConversationRoutingAdapter(route2);
            }
        });
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull final RouteContext routeContext, final Runnable runnable) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.common.ui.main.-$$Lambda$ConversationRoutingAdapter$EfGsGoMy1S-qRwOMcJm-kcPGNAg
            @Override // java.lang.Runnable
            public final void run() {
                ConversationRoutingAdapter.this.lambda$navigate$0$ConversationRoutingAdapter(routeContext, runnable);
            }
        });
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void push(RouteContext routeContext) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reenter() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void replace(@NonNull RouteContext routeContext) {
    }
}
