package com.amazon.deecomms.ui.util;

import android.app.Activity;
import android.text.TextUtils;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsDynamicFeature;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.services.ConversationUIService;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class CommsRoutingHelper {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsRoutingHelper.class);
    private static final String className = CommsRoutingHelper.class.getName();
    CommsManager commsManager;
    CommsServiceV2 commsServiceV2;
    ConversationUIService conversationUIService;
    IdentityService identityService;
    RoutingService routingService;

    public CommsRoutingHelper(ConversationUIService conversationUIService, RoutingService routingService, CommsServiceV2 commsServiceV2, CommsManager commsManager, IdentityService identityService) {
        this.conversationUIService = conversationUIService;
        this.routingService = routingService;
        this.commsServiceV2 = commsServiceV2;
        this.commsManager = commsManager;
        this.identityService = identityService;
    }

    private boolean isRNProfileOobeEnabled() {
        FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class).mo10268get();
        try {
            if (Utils.isFireOS()) {
                return false;
            }
            return featureServiceV2.hasAccess(CommsDynamicFeature.ALEXA_PROFILE_OOBE_DECOUPLING_ANDROID.name(), false);
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isReactNativeContactListActive() {
        RouteContext currentRoute = this.routingService.getCurrentRoute();
        return currentRoute.getRoute().is(RouteName.REACT_NATIVE_COMMS) && "contact-list".equalsIgnoreCase(currentRoute.getString("name", ""));
    }

    private void routeCustomerToOOBEProcess(Activity activity, CommsView commsView) {
        LOG.i("Customer has not completed OOBE.");
        if (isRNProfileOobeEnabled()) {
            boolean hasSelectedProfile = this.commsServiceV2.oobeService().hasSelectedProfile();
            GeneratedOutlineSupport.outline5("routeCustomerToOOBEProcess hasSelectedProfile ? ", hasSelectedProfile, LOG);
            if (hasSelectedProfile) {
                if (TextUtils.isEmpty(this.commsManager.getPhoneNumber())) {
                    LOG.i("Profile exists and phone number doesn't. routing to RN-CVF.");
                    RoutingService.RoutingBuilder match = this.routingService.match(Constants.REACT_NATIVE_CVF_ROUTE);
                    GeneratedOutlineSupport.outline5("RN-CVF Route Found ? ", match != null, LOG);
                    if (match == null) {
                        return;
                    }
                    match.navigate();
                    return;
                }
                LOG.i("Profile and phone number exists. routing to RN-PhoneVerified.");
                this.routingService.match(Constants.REACT_NATIVE_PHONE_VERIFIED_ROUTE).navigate();
                return;
            }
            LOG.i("Profile not found. Routing to RN-Profile OOBE.");
            this.routingService.route("v2/profile-oobe/profile-oobe-start").addToBackStack().fromMainMenu().navigate();
            return;
        }
        LOG.i("Routing to Native-Comms OOBE.");
        this.commsServiceV2.oobeService().routeUserToOobe(activity, commsView);
    }

    private void routeToNativeContactList(CommsProvisionStatus commsProvisionStatus) {
        String str = CommsProvisionStatus.DEPROVISIONED.equals(commsProvisionStatus) ? "conversations" : RouteName.CONVERSATIONS_CONTACT_LIST;
        this.routingService.stopLoadingTimeout();
        if (this.conversationUIService.isContactListAtTopOfBackstack(this.routingService.getBackstack())) {
            this.routingService.popFromBackStack(str);
        } else {
            this.routingService.route(str).with("arguments", GeneratedOutlineSupport1.outline11(Constants.CONTACT_LIST_LAUNCH_FRAGMENT, Constants.FRAGMENT_CONTACT_CARD)).addToBackStack().fromMainMenu().navigate();
        }
    }

    private void routeToReactNativeContactList() {
        if (isReactNativeContactListActive()) {
            LOG.d("RN Contacts was selected from Main Menu but it is the currentRoute.");
            return;
        }
        this.routingService.stopLoadingTimeout();
        LOG.d("RN Contacts was selected from Main Menu.");
        this.routingService.route("v2/comms/contact-list").addToBackStack().fromMainMenu().navigate();
    }

    private boolean shouldShowCommsOOBEForUser() {
        return this.commsServiceV2.oobeService().shouldShowCommsOOBEForUser();
    }

    private boolean shouldShowCommsOOBEForUserFromContactsMenu(CommsProvisionStatus commsProvisionStatus) {
        return !CommsProvisionStatus.AUTO_PROVISIONED.equals(commsProvisionStatus) && shouldShowCommsOOBEForUser();
    }

    private String validateRoute(Activity activity, String str) {
        if (shouldShowCommsOOBEForUser()) {
            routeCustomerToOOBEProcess(activity, CommsView.ReactNativeConversationList);
            return null;
        } else if (this.identityService.getUser(className) == null) {
            return null;
        } else {
            return CommsProvisionStatus.DEPROVISIONED.equals(this.commsManager.getUserProvisionStatus()) ? "conversations" : str;
        }
    }

    public boolean isCommsBlockingEnabled(Activity activity) {
        return this.commsManager.performCommsBlocking(activity);
    }

    public void resolveCommsContactsRoute(Activity activity) {
        CommsProvisionStatus userProvisionStatus = this.commsManager.getUserProvisionStatus();
        if (shouldShowCommsOOBEForUserFromContactsMenu(userProvisionStatus)) {
            routeCustomerToOOBEProcess(activity, CommsView.ContactList);
        } else if (this.commsManager.shouldRouteToReactNativeContactList()) {
            routeToReactNativeContactList();
        } else {
            routeToNativeContactList(userProvisionStatus);
        }
    }

    public void resolveCommsConversationRoute(Activity activity) {
        String validateRoute = validateRoute(activity, "v2/comms/conversation-list");
        if (validateRoute == null) {
            return;
        }
        this.routingService.stopLoadingTimeout();
        if (this.routingService.popFromBackStack(validateRoute)) {
            return;
        }
        this.routingService.route(validateRoute).with("arguments", GeneratedOutlineSupport1.outline13("CONVERSATION_TAB_PRESSED", true)).addToBackStack().navigate();
    }
}
