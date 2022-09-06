package com.amazon.alexa.biloba.utils;
/* loaded from: classes6.dex */
public final class WebConstants {

    /* loaded from: classes6.dex */
    public static final class BuildStages {
        public static final String ALPHA_STAGE = "alpha";
        public static final String BETA_STAGE = "beta";
        public static final String GAMMA_STAGE = "gamma";
        public static final String PROD_STAGE = "prod";

        /* loaded from: classes6.dex */
        public @interface BuildStage {
        }

        private BuildStages() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class Endpoints {
        public static final String ALPHA_ENDPOINT = "https://aiwa-mge-pdx-pd.integ.amazon.com";
        public static final String BETA_ENDPOINT = "https://aiwa-mge-pdx-d.integ.amazon.com";
        public static final String GAMMA_ENDPOINT = "https://projectdee-ui-gamma.amazon.com";
        public static final String PROD_ENDPOINT = "https://alexa.amazon.com";

        private Endpoints() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class UriConstants {
        public static final String AMPERSAND_KEY = "&";
        public static final String CURRENT_ACTOR_PERSON_ID = "currentActorPersonId=";
        public static final String DARK = "dark";
        public static final String HIDE_BANNER_QUERY_ARG = "hideBanner=";
        public static final String LIGHT = "light";
        public static final String MOSAIC_THEMING_IN_APP_SETTINGS_QUERY_ARG = "displayTheme=";
        public static final String QUESTIONMARK_KEY = "?";

        /* loaded from: classes6.dex */
        public @interface Theme {
        }

        private UriConstants() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class WebviewConstants {
        public static final String CONTINUE_ON_NON_FATAL_ERRORS = "CONTINUE_ON_NON_FATAL_ERRORS";
        public static final String EXTERNAL_WEBVIEW_BRIDGE_ACTION_KEY = "BRIDGE_ACTION_KEY";
        public static final String EXTERNAL_WEBVIEW_BRIDGE_ACTION_VALUE_LAUNCHEXTERNAL = "launchExternal";
        public static final String EXTERNAL_WEBVIEW_CATCH_ALL_REGEX_VALUE = ".*";
        public static final String EXTERNAL_WEBVIEW_EXCLUDE_APP_LINK_REGEX_VALUE = "^(?!.*alexa.amazon.*/spa/#)(^.*$)";
        public static final String EXTERNAL_WEBVIEW_INTENT = "com.amazon.dee.external.ExternalUIActivity";
        public static final String EXTERNAL_WEBVIEW_REGEX_KEY = "URL_REGEX_KEY";
        public static final String EXTERNAL_WEBVIEW_TITLE_ID_KEY = "WEBVIEW_TITLE_ID_KEY";
        public static final String HTTP = "http";
        public static final String IS_DOM_STORAGE_ENABLED = "IS_DOM_STORAGE_ENABLED";
        public static final String MARKETPLACE_ID = "marketplaceId";
        public static final String SUBSCRIPTION_ID = "subscriptionId";

        private WebviewConstants() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class WebviewPaths {
        public static final String CARE_PLUS_WELCOME_PATH = "/groups/care/plus/welcome?subscriptionId=%s&marketplaceId=%s";
        public static final String COMMS_PRIMER_PATH = "/groups/care/commsPrimer";
        public static final String EMERGENCY_ADDRESS_CHANGE_PATH = "/groups/care/plus/emergencyAddress?subscriptionId=%s&marketplaceId=%s&mode=edit";
        public static final String EMERGENCY_ADDRESS_PATH = "/groups/care/plus/emergencyAddress?subscriptionId=%s&marketplaceId=%s";
        public static final String ENABLE_REMOTE_MANAGEMENT = "/groups/care/dashboard";
        public static final String EXTERNAL_DASHBOARD_PATH = "/groups/care/dashboard";
        public static final String INVITE_CG_MULTI_CG_URL = "/groups/care/careGiverInvite?flowId=81aa28894e71";
        public static final String LONE_CR_CREATE_RELATIONSHIP_URL = "/groups/care/careGiverInvite?flowId=2f414652ae0b";
        public static final String MEMBER_PROFILE_PATH = "/groups/care/dashboard/%s";
        public static final String TEST_EMERGENCY_HELPLINE_PATH = "/groups/care/plus/emergencyHelpline/guide";

        private WebviewPaths() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class WebviewUrls {
        public static final String CARE_PLUS_DISCOVERY_URL = "https://www.amazon.com/b/ref=ods_surl_ac?&node=21390531011";
        public static final String CARE_PLUS_MANAGE_SUBSCRIPTION_URL = "https://www.amazon.com/yourmembershipsandsubscriptions";
        public static final String CARE_PLUS_UPSELL_URL = "https://www.amazon.com/b/ref=ods_surl_ac?&node=21390531011";

        private WebviewUrls() {
        }
    }

    private WebConstants() {
    }
}
