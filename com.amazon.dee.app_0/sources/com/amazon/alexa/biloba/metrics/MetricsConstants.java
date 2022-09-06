package com.amazon.alexa.biloba.metrics;
/* loaded from: classes6.dex */
public final class MetricsConstants {
    public static final String BILOBA_COMPONENT = "AlexaBiloba";
    public static final String BILOBA_SUB_COMPONENT = "AlexaBiloba";
    public static final String CARE_GIVER = ".CareGiver";
    public static final String CARE_GIVER_ADMIN = ".CareGiverAdmin";
    public static final String CARE_GIVER_VOR = ".CareGiverVOR";
    public static final String CARE_RECEIVER = ".CareReceiver";
    public static final String CLICK_EVENT = ".Click";
    public static final String COUNTER_EVENT = ".Count";
    public static final String DASHBOARD = "Dashboard.";
    public static final String DEBUG = ".Debug";
    public static final String EMPTY_SUFFIX = "";
    public static final String ENTER_EVENT = ".Enter";
    public static final String ERROR_EVENT = ".Error";
    public static final String EXIT_EVENT = ".Exit";
    public static final String FAILURE_EVENT = ".Failure";
    public static final String SUCCESS_EVENT = ".Success";
    public static final String TIME_EVENT = ".Time";
    public static final String VIEW_EVENT = ".View";

    /* loaded from: classes6.dex */
    public static final class OperationalMetrics {
        public static final String DASHBOARD_VIEW_BUILT = "DashboardViewBuilt";
        public static final String GET_ALERT_CONFIGURATIONS_API = "getAlertConfigurationsApi";
        public static final String GET_ALL_ACTIVITY_API = "getAllActivityApi";
        public static final String GET_ALL_DEVICES_API = "getDevicesApi";
        public static final String GET_DASHBOARD_CARDS_API = "getDashboardCardsApi";
        public static final String GET_EMERGENCY_CONTACT_API = "getEmergencyContactApi";
        public static final String GET_EMERGENCY_SETTINGS_API = "getEmergencySettingsApi";
        public static final String GET_GROUPS_API = "getGroupsApi";
        public static final String GET_GROUPS_V2_API = "getGroupsV2Api";
        public static final String GET_PERSON_ID = "getPersonId";
        public static final String GET_SETTINGS_API = "getSettingsApi";
        public static final String GET_TIMEZONE_LIST_API = "getTimeZoneListApi";
        public static final String GET_TIP_CARDS_API = "getTipCardsApi";
        public static final String GET_TODAYS_ACTIVITY_API = "getTodaysActivityApi";
        public static final String GET_USER_IDENTITY = "getUserIdentity";
        public static final String GET_USER_PROFILE = "getUserProfile";
        public static final String LEAVE_GROUP_API = "leaveRelationshipApi";
        public static final String PASSCODE_CHECK = "PasscodeCheck";
        public static final String PROFILE_PIN_ERROR = "ProfilePinError";
        public static final String SKIPPED_CARD = "SkippedCard";
        public static final String STARTUP_VIEW_BUILT = "StartupViewBuilt";
        public static final String UPDATE_ALERT_CONFIGURATIONS_API = "updateAlertConfigurationsApi";
        public static final String UPDATE_SETTINGS_API = "updateSettingsApi";

        private OperationalMetrics() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class TTCFMetrics {
        public static final String BILOBA_DASHBOARD = "BilobaDashboard";
        public static final String BILOBA_GETTING_STARTED = "BilobaGettingStarted";
        public static final String BILOBA_LONE_CR = "BilobaLoneCr";
        public static final String BILOBA_METHOD_NAME = "biloba-init";
        public static final String DASHBOARD_CARDS_RENDER = "BilobaDashboardCards-init";
        public static final String TODAY_ACTIVITY_RENDER = "BilobaTodaysActivity-init";

        /* loaded from: classes6.dex */
        public @interface TTCFMetric {
        }

        private TTCFMetrics() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class UserInteractionMetrics {
        public static final String ADMIN_MEMBER_PROFILE = "AdminMemberProfileWebview";
        public static final String ALERT_CARD = "AlertCard";
        public static final String ALERT_CARD_ACTION_BUTTON = "AlertCardActionButton";
        public static final String ALERT_CARD_COMMS_CALL_BUTTON = "AlertCardCommsCallButton";
        public static final String ALERT_CARD_COMMS_DROP_IN_BUTTON = "AlertCardCommsDropInButton";
        public static final String ALERT_EDIT_VIEW = "AlertEditView";
        public static final String ALERT_SETTINGS_VIEW = "AlertSettingsView";
        public static final String ALL_ACTIVITY_VIEW = "AllActivityView";
        public static final String CARE_MANAGEMENT_VIEW = "CareManagementView";
        public static final String CHANGE_ALERT_TIME_RANGE = "ChangeAlertTimeRange";
        public static final String CHANGE_TIME_ZONE = "TimeZonePickerRow";
        public static final String CHANGE_TIME_ZONE_DASHBOARD = "ChangeTimeZoneButton.Dashboard";
        public static final String CHANGE_TIME_ZONE_SETTINGS = "ChangeTimeZoneButton.Settings";
        public static final String COMMS_SETUP_GUIDE_VIEW = "CommsSetupGuideView";
        public static final String COMMS_SHARE_SETUP_LINK_VIEW = "CommsShareSetupLinkView";
        public static final String CREATE_RELATIONSHIP_BUTTON = "CreateRelationship";
        public static final String DASHBOARD_COMMS_CALL_BUTTON = "DashboardCommsCallButton";
        public static final String DASHBOARD_COMMS_DROP_IN_BUTTON = "DashboardCommsDropInButton";
        public static final String DASHBOARD_COMMS_MESSAGE_BUTTON = "DashboardCommsMessageButton";
        public static final String DASHBOARD_VIEW = "DashboardView";
        public static final String DELETE_CARE = "DeleteCare";
        public static final String DELETE_CARE_VIEW = "DeleteCareView";
        public static final String EH_CALL_ENDED_ACTION_CLICK = "LearnEmergencyHelplineCallStarted";
        public static final String EH_CALL_ENDED_CARD_RENDERED = "EmergencyHelplineCallEnded.AlertCardDashboardView";
        public static final String EH_CALL_ENDED_COMMS_CALL_BUTTON = "EmergencyHelplineCallEnded.CommsCallButton";
        public static final String EH_CALL_ENDED_COMMS_DROP_IN_BUTTON = "EmergencyHelplineCallEnded.CommsDropInButton";
        public static final String EH_CALL_ENDED_COMMS_MESSAGE_BUTTON = "EmergencyHelplineCallEnded.CommsMessageButton";
        public static final String EH_CALL_ENDED_MODAL_RENDERED = "EmergencyHelplineCallEndedModal";
        public static final String EH_CALL_STARTED_ACTION_CLICK = "LearnEmergencyHelplineCallStarted";
        public static final String EH_CALL_STARTED_CARD_RENDERED = "EmergencyHelplineCallStarted.AlertCardDashboardView";
        public static final String EH_CALL_STARTED_MODAL_RENDERED = "EmergencyHelplineCallStartedModal";
        public static final String EH_VERIFIED_AND_UNVERIFIED_FALL_ACTION_CLICK = "LearnVerifiedAndUnverifiedFall";
        public static final String EH_VERIFIED_AND_UNVERIFIED_FALL_CARD_RENDERED = "VerifiedAndUnverifiedFall.AlertCardDashboardView";
        public static final String EH_VERIFIED_AND_UNVERIFIED_FALL_MODAL_RENDERED = "VerifiedAndUnverifiedFallModal";
        public static final String EH_VERIFIED_NO_FALL_ACTION_CLICK = "LearnVerifiedNoFall";
        public static final String EH_VERIFIED_NO_FALL_CARD_RENDERED = "VerifiedNoFall.AlertCardDashboardView";
        public static final String EH_VERIFIED_NO_FALL_COMMS_CALL_BUTTON = "VerfiedNoFall.CommsCallButton";
        public static final String EH_VERIFIED_NO_FALL_COMMS_DROP_IN_BUTTON = "VerfiedNoFall.CommsDropInButton";
        public static final String EH_VERIFIED_NO_FALL_COMMS_MESSAGE_BUTTON = "VerfiedNoFall.CommsMessageButton";
        public static final String EH_VERIFIED_NO_FALL_MODAL_RENDERED = "VerifiedNoFallModal";
        public static final String EMERGENCY_CONTACT_VIEW = "EmergencyContactView";
        public static final String EMERGENCY_SETTINGS_BUTTON = "EmergencySettingsButton";
        public static final String ES_ADD_EMERGENCY_ADDRESS_BUTTON = "EmergencySettings.AddEmergencyAddressButton";
        public static final String ES_ADD_EMERGENCY_CONTACT_BUTTON = "EmergencySettings.AddEmergencyContactButton";
        public static final String ES_CHANGE_EMERGENCY_ADDRESS_BUTTON = "EmergencySettings.ChangeEmergencyAddressButton";
        public static final String ES_CHANGE_EMERGENCY_CONTACT_BUTTON = "ChangeEmergencyContactButton";
        public static final String ES_COMMS_SETUP_BUTTON = "CommsSetupButton";
        public static final String ES_COMMS_SETUP_LINK = "CommsSetupLink";
        public static final String ES_CONTEXT = "EmergencySettings.";
        public static final String ES_EDIT_EMERGENCY_CONTACT_LINK = "EditEmergencyContactLink";
        public static final String ES_EMERGENCY_VIEW = "EmergencyContactButton";
        public static final String ES_EMERGENCY_VIEW_CARE_PLUS = "EmergencyContactButtonCarePlus";
        public static final String ES_ENABLE_NOTIFICATIONS = "EmergencySettings.EnableNotifications";
        public static final String ES_ENABLE_NOTIFICATIONS_MCG = "EnableNotifications";
        public static final String FAILURE_VIEW = "FailureView";
        public static final String GETTING_STARTED_VIEW_V2 = "GettingStartedViewV2";
        public static final String GETTING_STARTED_VIEW_V3 = "GettingStartedViewV3";
        public static final String INTRO_FINISH_BUTTON = "IntroFinishButton";
        public static final String INTRO_LEARN_MORE_BUTTON = "IntroLearnMoreButton";
        public static final String INTRO_NEXT_BUTTON = "IntroNextButton";
        public static final String INTRO_START_BUTTON = "IntroStartButton";
        public static final String LEARN_MORE = "LearnMore";
        public static final String LONE_CR_INVITE_BUTTON = "LoneCrModal.InviteButton";
        public static final String LONE_CR_MEMBERSHIPS_LINK = "LoneCrModal.MembershipsLink";
        public static final String LONE_CR_VIEW = "LoneCrModal";
        public static final String PROFILE_PIN_SETUP = "ProfilePinSetup";
        public static final String RM_ASSIST = "DashboardCommsRemoteManagementButton";
        public static final String RM_ASSIST_FOS_COMING_SOON = "RemoteManagementAssistFOSComingSoon";
        public static final String SETTINGS_ACCOUNT_DETAILS_BUTTON = "AccountDetailsButton";
        public static final String SETTINGS_ACCOUNT_DETAILS_BUTTON_EXTERNAL = "AccountDetailsButtonExternal";
        public static final String SETTINGS_CIRCLE_OF_SUPPORT = "CircleOfSupport";
        public static final String SETTINGS_CONTEXT = "Settings.";
        public static final String SETTINGS_PREFIX = "Settings";
        public static final String SETTINGS_VIEW = "SettingsView";
        public static final String STARTUP_VIEW = "StartupView";
        public static final String SUCCESS_VIEW = "SuccessView";
        public static final String TIPS_CARD = "TipsCard";
        public static final String TIPS_VIEW = "TipsView";
        public static final String WEBVIEW_VIEW = "WebviewView";
        public static final String YOUR_CIRCLE = "YourCircleWebview";

        private UserInteractionMetrics() {
        }
    }

    /* loaded from: classes6.dex */
    public @interface UserRoleSuffix {
    }

    private MetricsConstants() {
    }
}
