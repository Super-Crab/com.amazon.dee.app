package com.amazon.deecomms.common.util;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.ui.DiagnosticScreen;
import com.amazon.deecomms.contacts.ui.ChildContactCardFragment;
import com.amazon.deecomms.contacts.ui.ContactBlockFragment;
import com.amazon.deecomms.contacts.ui.ContactCardFragment;
import com.amazon.deecomms.contacts.ui.ContactListFragment;
import com.amazon.deecomms.contacts.ui.EditContactFragment;
import com.amazon.deecomms.contacts.ui.EditNicknameFragment;
import com.amazon.deecomms.contacts.ui.ManageContactsFragment;
import com.amazon.deecomms.contacts.ui.RelationshipListFragment;
import com.amazon.deecomms.contacts.ui.WhitelistContactFragment;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.notifications.model.AnnouncementPushNotificationAction;
import com.amazon.deecomms.notifications.model.ConversationPushNotificationAction;
import com.amazon.deecomms.notifications.model.DefaultPushNotificationAction;
import com.amazon.deecomms.notifications.model.ImageDetailViewPushNotificationAction;
import com.amazon.deecomms.notifications.model.PushNotificationAction;
import com.amazon.deecomms.notifications.util.NotificationUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class FragmentNavigationRouter {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, FragmentNavigationRouter.class);

    /* renamed from: com.amazon.deecomms.common.util.FragmentNavigationRouter$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$api$navigation$CommsView;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType = new int[NotificationUtils.NotificationPathType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.CONVERSATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.ANNOUNCEMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.ANNOUNCEMENT_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.IMAGE_DETAIL_VIEW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.CONVERSATION_LIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$notifications$util$NotificationUtils$NotificationPathType[NotificationUtils.NotificationPathType.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$com$amazon$deecomms$api$navigation$CommsView = new int[CommsView.values().length];
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.ContactCard.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.ContactList.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.ManageContactsList.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.BlockContactsList.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.ChildContactCard.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.WhitelistContact.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.RelationshipList.ordinal()] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.EditNickname.ordinal()] = 8;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.Diagnostics.ordinal()] = 9;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.EditContact.ordinal()] = 10;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.ReactNativeContactList.ordinal()] = 11;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.ReactNativeRoute.ordinal()] = 12;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.ReactNativeConversationList.ordinal()] = 13;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$api$navigation$CommsView[CommsView.Default.ordinal()] = 14;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    private FragmentNavigationRouter() {
    }

    static void defaultRouteAction(ApplicationManager applicationManager, String str) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str) && str.startsWith(RouteName.REACT_NATIVE_COMMS)) {
            String routeNameForPath = getRouteNameForPath(str);
            bundle.putString("route", str);
            bundle.putString(Constants.BUNDLE_KEY_REACT_NATIVE_ROUTE_NAME, routeNameForPath);
            applicationManager.navigateToView(CommsView.ReactNativeRoute, bundle, true);
            return;
        }
        applicationManager.navigateToView(CommsView.ReactNativeConversationList, new Bundle(), true);
    }

    private static PushNotificationAction getActionForPathtype(NotificationUtils.NotificationPathType notificationPathType, ApplicationManager applicationManager, String str) {
        int ordinal = notificationPathType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new AnnouncementPushNotificationAction(str, applicationManager, CommsView.ReactNativeAnnouncementList);
            }
            if (ordinal == 2) {
                return new ConversationPushNotificationAction(str, applicationManager);
            }
            if (ordinal != 4) {
                return new DefaultPushNotificationAction(applicationManager);
            }
            return new ImageDetailViewPushNotificationAction(str, applicationManager);
        }
        return new AnnouncementPushNotificationAction(str, applicationManager, CommsView.ReactNativeAnnouncement);
    }

    private static String getRouteNameForPath(String str) {
        return str.substring(str.lastIndexOf(47) + 1);
    }

    public static void goToConversationThread(@NonNull Activity activity, @Nullable String str, boolean z) {
        if (Utils.isOfflineDialogShown(activity, false, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.class.getName()))) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LOG.e("comms id/HG Id not found. Unable to start Messaging");
            return;
        }
        MetricsHelper.recordSingleOccurrenceClickstreamByChild(MetricKeys.MESSAGE_INITIATED_FROM_CONTACT, z);
        navigateToConversationRNView(str, null, null, CommsInternal.getInstance().getCommsId());
    }

    public static void goToWhitelistContacts(@NonNull String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LOG.e("comms id is null,  unable to route to whitelist contacts!");
            return;
        }
        switchToFragment(CommsView.WhitelistContact, GeneratedOutlineSupport1.outline12(Constants.CHILD_COMMS_ID, str, Constants.CHILD_CONTACT_ID, str2), true);
    }

    private static void navigateToConversationRNView(@NonNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        ApplicationManager applicationManager = CommsDaggerWrapper.getComponent().getApplicationManager();
        Bundle bundle = new Bundle();
        if (str2 != null && !str2.contentEquals("UNKNOWN")) {
            bundle.putString(Constants.BUNDLE_KEY_CONVERSATION_ID, str2);
        }
        bundle.putString(Constants.BUNDLE_KEY_RECIPIENT_ID, str);
        if (!TextUtils.isEmpty(str4)) {
            bundle.putString(Constants.BUNDLE_KEY_VIEW_AS_COMMS_ID, str4);
        }
        if (!TextUtils.isEmpty(str3)) {
            bundle.putString(Constants.BUNDLE_KEY_SEND_AS_COMMS_ID, str3);
        }
        applicationManager.navigateToView(CommsView.ReactNativeConversation, bundle, true);
    }

    public static void switchToFragment(CommsView commsView, Bundle bundle) {
        switchToFragment(commsView, bundle, true);
    }

    public static void switchToFragment(CommsView commsView, Bundle bundle, boolean z) {
        Fragment diagnosticScreen;
        if (bundle == null) {
            bundle = new Bundle();
        }
        ApplicationManager applicationManager = CommsDaggerWrapper.getComponent().getApplicationManager();
        int ordinal = commsView.ordinal();
        if (ordinal == 11) {
            applicationManager.navigateToView(CommsView.ReactNativeContactList, bundle, z);
        } else if (ordinal != 19) {
            if (ordinal != 21) {
                switch (ordinal) {
                    case 1:
                        diagnosticScreen = new ContactCardFragment();
                        break;
                    case 2:
                        bundle.putString(Constants.CONTACTLIST_FRAGMENT_TAG, Constants.CONTACTLIST_FRAGMENT_TAG);
                        diagnosticScreen = new ContactListFragment();
                        break;
                    case 3:
                        diagnosticScreen = new ManageContactsFragment();
                        break;
                    case 4:
                        diagnosticScreen = new ContactBlockFragment();
                        break;
                    case 5:
                        diagnosticScreen = new ChildContactCardFragment();
                        break;
                    case 6:
                        diagnosticScreen = new WhitelistContactFragment();
                        break;
                    case 7:
                        diagnosticScreen = new RelationshipListFragment();
                        break;
                    case 8:
                        diagnosticScreen = new EditNicknameFragment();
                        break;
                    case 9:
                        diagnosticScreen = new EditContactFragment();
                        break;
                    default:
                        applicationManager.navigateToView(CommsView.ReactNativeConversationList, bundle, z);
                        return;
                }
            } else {
                diagnosticScreen = new DiagnosticScreen();
            }
            diagnosticScreen.setArguments(bundle);
            applicationManager.fragmentNavigationRequested(commsView, diagnosticScreen, bundle, z);
        } else {
            applicationManager.navigateToView(CommsView.ReactNativeRoute, bundle, z);
        }
    }

    public static void switchToFragment(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ApplicationManager applicationManager = CommsDaggerWrapper.getComponent().getApplicationManager();
        PushNotificationAction actionForPathtype = getActionForPathtype(NotificationUtils.determineNotificationPathType(str), applicationManager, str);
        if (actionForPathtype.validatePath() && actionForPathtype.authenticate()) {
            actionForPathtype.doRouteAction();
        } else {
            defaultRouteAction(applicationManager, str);
        }
    }
}
