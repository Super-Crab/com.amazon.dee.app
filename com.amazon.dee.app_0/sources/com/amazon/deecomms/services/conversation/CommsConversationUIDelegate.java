package com.amazon.deecomms.services.conversation;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteTemplate;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsUIDelegateBase;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.common.CommsMasterFragment;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.conversation.ConversationRouting;
import com.amazon.deecomms.util.Consumer;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import com.google.common.base.Strings;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
@Deprecated
/* loaded from: classes12.dex */
public class CommsConversationUIDelegate extends CommsUIDelegateBase {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsConversationUIDelegate.class);
    private static final String REACT_NATIVE_COMMS_ROUTE = "v2/comms";
    AccountService accountService;
    Activity activity;
    @IdRes
    int containerId;
    ConversationRouting conversationRouting;
    ConversationUIDelegate conversationUIDelegate;
    MetricsService metricsService = (MetricsService) GeneratedOutlineSupport1.outline20(MetricsService.class);
    RoutingRegistryAdapter routingRegistryAdapter;
    RoutingService routingService;

    public CommsConversationUIDelegate(Activity activity, @IdRes int i, ConversationUIDelegate conversationUIDelegate, RoutingService routingService, ConversationRouting conversationRouting, AccountService accountService, RoutingRegistryAdapter routingRegistryAdapter) {
        this.activity = activity;
        this.containerId = i;
        this.conversationUIDelegate = conversationUIDelegate;
        this.routingService = routingService;
        this.conversationRouting = conversationRouting;
        this.accountService = accountService;
        this.routingRegistryAdapter = routingRegistryAdapter;
    }

    private boolean isCommsContactListView(CommsView commsView) {
        return commsView == CommsView.ContactList || commsView == CommsView.ReactNativeContactList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$performRouteAction$2(Integer num) throws Throwable {
        return num.intValue() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$signOut$5(Void r0) {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void addHomeToBackStackIfEmpty() {
        this.routingService.addHomeToBackStackIfEmpty();
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void configurePageForFragment(FragmentRequirements fragmentRequirements) {
        if (!this.conversationUIDelegate.isConversationUIActive()) {
            LOG.v("Ignore fragment configuration request, because conversation UI is not active");
        } else if (this.conversationUIDelegate.isReactNativeCommsActive()) {
            LOG.v("Ignore fragment configuration request, because a react native view is currently active");
        } else {
            this.conversationUIDelegate.setHeaderTitle(fragmentRequirements.getTitle());
            this.conversationUIDelegate.setHeadersAndTabVisible(fragmentRequirements.usesHeader(), fragmentRequirements.usesFooter());
            if (fragmentRequirements.usesMenu()) {
                fragmentRequirements.menuInflatedCallback(this.conversationUIDelegate.setHeaderMenu(fragmentRequirements.getMenuId(), fragmentRequirements.getMenuItemClickListener()));
            } else {
                this.conversationUIDelegate.resetHeaderMenu();
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    RoutingService.RoutingBuilder createBuilder(String str, Bundle bundle) {
        char c;
        String str2;
        int hashCode = str.hashCode();
        String str3 = RouteName.COMMS_ANNOUNCEMENT;
        String str4 = RouteName.COMMS_IMPORT_CONTACT;
        switch (hashCode) {
            case -2033449698:
                if (str.equals("v2/comms/conversation")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1542376136:
                if (str.equals(RouteName.COMMS_CONTACT_CARD)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1542100282:
                if (str.equals("v2/comms/contact-list")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 105633088:
                if (str.equals(RouteName.COMMS_MANAGE_CONTACTS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 874645248:
                if (str.equals(str4)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1527691689:
                if (str.equals("v2/comms/announcement-list")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1627506245:
                if (str.equals(RouteName.COMMUNICATIONS_SETTING)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1678144994:
                if (str.equals(str3)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1682043622:
                if (str.equals(RouteName.EDIT_CONTACT_CARD)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1888180596:
                if (str.equals("v2/comms/image-detail-view")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                ContactEntry contactEntry = (ContactEntry) bundle.get(Constants.CONTACT_ENTRY_KEY);
                String id = contactEntry.getId();
                if (contactEntry.isChild()) {
                    str4 = GeneratedOutlineSupport.outline0("v2/comms/child-contact-card/", id);
                    str2 = GeneratedOutlineSupport.outline0("v2/comms/child-contact-card/", id);
                    break;
                } else {
                    str4 = GeneratedOutlineSupport.outline0("v2/comms/contact-card/", id);
                    str2 = "contact-card";
                    break;
                }
            case 1:
                String str5 = bundle.getBoolean(Constants.BUNDLE_KEY_MY_PROFILE, false) ? "isIdentity" : "isNotIdentity";
                ContactEntry contactEntry2 = (ContactEntry) bundle.get(Constants.CONTACT_ENTRY_KEY);
                str3 = GeneratedOutlineSupport1.outline76("v2/comms/edit-contact/", contactEntry2 != null ? contactEntry2.getId() : "not-set", "?isIdentity=", str5);
                str2 = "edit-contact";
                str4 = str3;
                break;
            case 2:
                str3 = GeneratedOutlineSupport.outline0("v2/comms/conversation/", bundle.getString(Constants.BUNDLE_KEY_CONVERSATION_ID, bundle.getString(Constants.BUNDLE_KEY_RECIPIENT_ID, null)));
                str2 = "comms/conversation";
                str4 = str3;
                break;
            case 3:
                str2 = "manage-contacts";
                str4 = RouteName.COMMS_MANAGE_CONTACTS;
                break;
            case 4:
                str2 = "import-contacts";
                break;
            case 5:
                str2 = "rn-contact-list";
                str4 = "v2/comms/contact-list";
                break;
            case 6:
                str2 = "comms/announcement";
                str4 = str3;
                break;
            case 7:
                String string = bundle.getString(Constants.BUNDLE_ANNOUNCEMENT_ID);
                str4 = Strings.isNullOrEmpty(string) ? "v2/comms/announcement-list" : GeneratedOutlineSupport.outline0("v2/comms/announcement-list/", string);
                str2 = "v2/comms/announcement-list";
                break;
            case '\b':
                str4 = GeneratedOutlineSupport1.outline76(Constants.DEVICE_SETTINGS_ROUTE, bundle.getString(Constants.BUNDLE_SERIAL_NUMBER), "/", bundle.getString("deviceType"));
                str2 = RouteName.COMMUNICATIONS_SETTING;
                break;
            case '\t':
                str3 = GeneratedOutlineSupport.outline0("v2/comms/image-detail-view/", bundle.getString(Constants.BUNDLE_IMAGE_DETAIL_VIEW_PARAMETERS));
                str2 = "v2/comms/image-detail-view";
                str4 = str3;
                break;
            default:
                str2 = str;
                str4 = null;
                break;
        }
        if (str4 != null) {
            RoutingService.RoutingBuilder match = this.routingService.match(str4);
            match.with("name", str2);
            return match;
        }
        LOG.w("No template matches comms route uri, defaulting to route name");
        RoutingService.RoutingBuilder route = this.routingService.route(str);
        route.with("arguments", bundle);
        return route;
    }

    @VisibleForTesting
    void executeRouteAction(CommsView commsView, Bundle bundle, boolean z, Consumer<RoutingService.RoutingBuilder> consumer, String str) {
        RoutingService.RoutingBuilder route;
        if (str != null) {
            RouteContext currentRoute = this.routingService.getCurrentRoute();
            if (isCommsContactListView(commsView) && currentRoute != null && currentRoute.getRoute().getName().equals(str)) {
                z = false;
            }
            if (bundle != null) {
                route = createBuilder(str, bundle);
                if (commsView == CommsView.ContactList && bundle.getBoolean(Constants.ROUTE_AFTER_DELETE_CONTACT, false)) {
                    this.routingService.popUntil(str);
                    return;
                }
            } else {
                route = this.routingService.route(str);
            }
            if (z) {
                route.addToBackStack();
            }
            consumer.accept(route);
        }
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    @Deprecated
    public void fragmentNavigationRequested(CommsView commsView, Fragment fragment, Bundle bundle, boolean z) {
        boolean z2;
        if (!this.conversationUIDelegate.isConversationUIActive()) {
            LOG.v("Ignore fragment navigation request, because conversation UI is not active");
            return;
        }
        FragmentManager supportFragmentManager = ((FragmentActivity) this.activity).getSupportFragmentManager();
        Fragment findFragmentById = supportFragmentManager.findFragmentById(this.containerId);
        if (findFragmentById == null) {
            findFragmentById = new CommsMasterFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean(CommsMasterFragment.DO_NOT_START_FRAGMENT, true);
            findFragmentById.setArguments(bundle2);
            supportFragmentManager.beginTransaction().replace(this.containerId, findFragmentById).commitAllowingStateLoss();
            supportFragmentManager.executePendingTransactions();
        }
        FragmentManager childFragmentManager = findFragmentById.getChildFragmentManager();
        if (!z) {
            z2 = supportFragmentManager.findFragmentById(CommsMasterFragment.CONTAINER_ID) == null;
        } else {
            z2 = z;
        }
        FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
        beginTransaction.replace(CommsMasterFragment.CONTAINER_ID, fragment);
        if (z) {
            beginTransaction.addToBackStack(this.conversationRouting.getRoute(commsView));
        }
        beginTransaction.commitAllowingStateLoss();
        performRouteAction(commsView, bundle, z2, $$Lambda$CommsConversationUIDelegate$tAT7vgjjZAXza7MIdH9B64R6lJA.INSTANCE);
    }

    @VisibleForTesting
    boolean isComms(@NonNull Route route) {
        RouteTemplate template = route.getTemplate();
        String name = route.getName();
        CommsView commsView = name == null ? null : this.conversationRouting.getCommsView(name);
        return (commsView != null && commsView != CommsView.CommunicationsSettings) || (template != null && template.getTemplateName() != null && template.getTemplateName().startsWith("v2/comms"));
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public boolean isCurrentlyOnCommsScreen() {
        return isComms(this.routingService.getCurrentRoute().getRoute());
    }

    public /* synthetic */ void lambda$performRouteAction$3$CommsConversationUIDelegate(CommsView commsView, Bundle bundle, boolean z, Consumer consumer, String str, Integer num) throws Throwable {
        LOG.i("Elements Routing Adapter found by subscriber");
        executeRouteAction(commsView, bundle, z, consumer, str);
    }

    public /* synthetic */ void lambda$performRouteAction$4$CommsConversationUIDelegate(CommsView commsView, Bundle bundle, boolean z, Consumer consumer, String str, Throwable th) throws Throwable {
        LOG.e("Error loading Elements Routing Adapter. Proceeding to route without Elements");
        executeRouteAction(commsView, bundle, z, consumer, str);
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateBackward() {
        this.routingService.navigateBackward();
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateHome() {
        this.routingService.route(RouteName.HOME).clearBackStack().navigate();
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateSettings() {
        this.routingService.route("v2/device-settings").navigate();
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateToView(CommsView commsView, Bundle bundle, boolean z) {
        performRouteAction(commsView, bundle, z, $$Lambda$CommsConversationUIDelegate$s3kFGZ94ZxMjQSKO9jHIwt3MBI.INSTANCE);
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateUpward() {
        this.routingService.navigateUpward();
    }

    @VisibleForTesting
    void performRouteAction(final CommsView commsView, final Bundle bundle, final boolean z, final Consumer<RoutingService.RoutingBuilder> consumer) {
        final String route = this.conversationRouting.getRoute(commsView);
        if (route.startsWith("v2") && this.routingRegistryAdapter.get(1) == null) {
            LOG.i("Elements Routing Adapter is not ready, begin listening for Adapter to be added");
            this.routingRegistryAdapter.onAdapterAdded().filter($$Lambda$CommsConversationUIDelegate$9b3Rs3vaR0qaP6mIxImrEueiZI.INSTANCE).firstOrError().subscribeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.deecomms.services.conversation.-$$Lambda$CommsConversationUIDelegate$5XIsIZI9BpTUSjxRiykiEL4C2bM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    CommsConversationUIDelegate.this.lambda$performRouteAction$3$CommsConversationUIDelegate(commsView, bundle, z, consumer, route, (Integer) obj);
                }
            }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.deecomms.services.conversation.-$$Lambda$CommsConversationUIDelegate$ju792w0y43FThaqZbfeM27oAdHw
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    CommsConversationUIDelegate.this.lambda$performRouteAction$4$CommsConversationUIDelegate(commsView, bundle, z, consumer, route, (Throwable) obj);
                }
            });
            return;
        }
        executeRouteAction(commsView, bundle, z, consumer, route);
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public boolean removeCommsRoutesFromBackstack() {
        ArrayList arrayList = new ArrayList();
        RouteContext[] backstack = this.routingService.getBackstack();
        for (RouteContext routeContext : backstack) {
            Route route = routeContext.getRoute();
            if (isComms(route)) {
                arrayList.addAll(this.routingService.removeRoutesFromBackStack(route));
            }
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Removed: ");
        outline1.append(arrayList.size());
        outline1.append(" comms routes. Backstack size was: ");
        outline1.append(backstack.length);
        outline1.append(" initially.");
        commsLogger.i(outline1.toString());
        return arrayList.size() > 0;
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void setIndicatorIconVisible(boolean z) {
        this.conversationUIDelegate.setMessageIndicatorVisible(z);
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void setUserName(String str) {
        this.conversationUIDelegate.setUserName(str);
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void signOut() {
        this.accountService.signOut().subscribe($$Lambda$CommsConversationUIDelegate$U9iWd3VHLCPDXzWlHmFkXM6EhI.INSTANCE, $$Lambda$CommsConversationUIDelegate$h3ZjdXsTmWmY4cLA624jD6FJGj8.INSTANCE);
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    @Deprecated
    public void voxCallInitiated() {
    }
}
