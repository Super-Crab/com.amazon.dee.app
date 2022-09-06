package com.amazon.alexa.accessorykit.metrics;
/* loaded from: classes6.dex */
public interface MetricsConstants {

    /* loaded from: classes6.dex */
    public interface ColdStart {
        public static final String ACCESSORIES_INSTANTIATION_DURATION = "AccessoriesInstantiationDuration";
        public static final String ACCESSORIES_OBJECT = "accessoriesObject";
        public static final String ACCESSORIES_PROCESS_INTEGRATIONS = "accessoriesProcessIntegrations";
        public static final String FEATURE_CHECKER_SYNC_LATENCY = "accessoriesFeatureCheckerSyncLatency";
        public static final String MAIN_PROCESS_INTEGRATIONS = "mainProcessIntegrations";
    }

    /* loaded from: classes6.dex */
    public interface FindMy {
        public static final String BATTERY_EVENT = "AccessoryFindMyXBatteryEvent";
        public static final String BLUETOOTH_LINK_EVENT = "AccessoryFindMyXBlueToothLinkEvent";
        public static final String BLUETOOTH_UNLINK_EVENT = "AccessoryFindMyXBlueToothUnLinkEvent";
        public static final String GET_LOCATION_SUCCESS = "AccessoryFindMyXGetLocation";
        public static final String PRESENCE_EVENT = "AccessoryFindMyXPresenceEvent";
        public static final String QUERY_SETTING_EXCEPTION_PREFIX = "AccessoryFindMyXQuerySettingException";
        public static final String QUERY_SETTING_HAS_DATA = "AccessoryFindMyXQuerySettingHasData";
        public static final String QUERY_SETTING_PARSE_SUCCESS = "AccessoryFindMyXQuerySettingParseData";
        public static final String QUERY_SETTING_PREPARED_REQUEST = "AccessoryFindMyXQuerySettingPreparedRequest";
        public static final String QUERY_SETTING_REQUEST = "AccessoryFindMyXQuerySettingRequest";
        public static final String QUERY_SETTING_STATUS_CODE = "AccessoryFindMyXQuerySettingStatusCode";
        public static final String QUERY_SETTING_SUCCESS = "AccessoryFindMyXQuerySetting";
        public static final String REPORT_LOCATION_EXCEPTION_PREFIX = "AccessoryFindMyXReportLocationException";
        public static final String REPORT_LOCATION_HAS_DATA = "AccessoryFindMyXReportLocationHasData";
        public static final String REPORT_LOCATION_PARSE_SUCCESS = "AccessoryFindMyXReportLocationParseData";
        public static final String REPORT_LOCATION_PREPARED_REQUEST = "AccessoryFindMyXReportLocationPreparedRequest";
        public static final String REPORT_LOCATION_REQUEST = "AccessoryFindMyXReportLocationRequest";
        public static final String REPORT_LOCATION_STATUS_CODE = "AccessoryFindMyXReportLocationStatusCode";
        public static final String REPORT_LOCATION_SUCCESS = "AccessoryFindMyXReportLocation";
        public static final String UNKNOWN_EVENT = "AccessoryFindMyXUnknownEvent";
    }

    /* loaded from: classes6.dex */
    public interface InterProcessFeatureChecker {
        public static final String FEATURE_PARITY = "FeatureValueParity";
        public static final String INTERPROCESS_FEATURE_CHECKER = "InterprocessFeatureChecker";
        public static final String NEW_FEATURE_LATENCY = "NewFeatureCheckerTime";
        public static final String OLD_FEATURE_LATENCY = "OldFeatureCheckerTime";
    }

    /* loaded from: classes6.dex */
    public interface InterprocessMarketplaceSupplier {
        public static final String INTERPROCESS_MARKETPLACE_SUPPLIER = "InterprocessMarketplaceSupplier";
        public static final String MARKETPLACE_AVAILABLE = "MarketplaceAvailable";
        public static final String MARKETPLACE_AVAILABLE_LATENCY = "MarketplaceAvailableLatency";
        public static final String MARKETPLACE_EMPTY = "MarketplaceEmpty";
        public static final String MARKETPLACE_ERROR = "MarketplaceError";
    }

    /* loaded from: classes6.dex */
    public interface InterprocessUserSupplier {
        public static final String ACCESS_TOKEN_RETRIEVED = "AccessTokenRetrieved";
        public static final String INTERPROCESS_USER_SUPPLIER = "InterprocessUserSupplier";
        public static final String USER_AVAILABLE = "UserAvailable";
        public static final String USER_CHANGE_DETECTED = "UserChangeDetected";
    }

    /* loaded from: classes6.dex */
    public interface Oobe {
        public static final String COMPLETE_SETUP_FAILED_NO_ACCESSORY_IN_INTERACTOR = "CompleteSetupFailedNoAccessoryInInteractor";
        public static final String COMPLETE_SETUP_FAILED_NO_SESSION = "CompleteSetupFailedNoSession";
        public static final String COMPLETE_SETUP_REQUEST_SUCCESS = "CompleteSetupRequestSuccess";
        public static final String START_SETUP_FAILED_NO_SESSION = "StartSetupFailedNoSession";
        public static final String START_SETUP_REQUEST_SUCCESS = "StartSetupRequestSuccess";
        public static final String UNKNOWN = "UNKNOWN";
    }
}
