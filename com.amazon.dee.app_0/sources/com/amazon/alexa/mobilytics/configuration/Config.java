package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import com.amazon.alexa.mobilytics.configuration.KinesisEndpoint;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.whisperjoin.provisionerSDK.configuration.defaults.Config;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class Config {
    private static final String BI_APP_STATE = "Foreground";
    private static final long BI_FLUSH_SIZE = 60000;
    private static final long BI_FLUSH_TIME = 60000;
    private static final String BI_HP_APP_STATE = "Any";
    private static final long BI_HP_FLUSH_SIZE = 500;
    private static final long BI_HP_FLUSH_TIME = 10000;
    private static final String BI_HP_JSON_STREAM_NAME = "alexa-mobilytics-hp";
    private static final boolean BI_HP_META_METRICS = false;
    private static final String BI_HP_PROTOBUF_STREAM_NAME = "mobilytics-bi-hp";
    private static final String BI_HP_TAG = "BI-HP";
    private static final String BI_JSON_STREAM_NAME = "alexa-mobilytics";
    private static final boolean BI_META_METRICS = false;
    private static final String BI_PROTOBUF_STREAM_NAME = "mobilytics-bi";
    private static final String BI_ROUTE_VALUE = "userInteraction";
    private static final String BI_TAG = "BI";
    private static final String DCM_DEFAULT_PRIORITY = "Normal";
    private static final String DCM_DEFAULT_ROUTE_VALUE = "operational";
    private static final String DCM_DEFAULT_TAG = "dcm-default";
    private static final String OE_APP_STATE = "Foreground";
    private static final long OE_FLUSH_SIZE = 60000;
    private static final long OE_FLUSH_TIME = 60000;
    private static final String OE_HP_APP_STATE = "Any";
    private static final long OE_HP_FLUSH_SIZE = 500;
    private static final long OE_HP_FLUSH_TIME = 10000;
    private static final String OE_HP_JSON_STREAM_NAME = "alexa-mobilytics-oe-hp";
    private static final boolean OE_HP_META_METRICS = false;
    private static final String OE_HP_PROTOBUF_STREAM_NAME = "mobilytics-oe-hp";
    private static final String OE_HP_TAG = "OE-HP";
    private static final String OE_JSON_STREAM_NAME = "alexa-mobilytics-oe";
    private static final boolean OE_META_METRICS = true;
    private static final String OE_PROTOBUF_STREAM_NAME = "mobilytics-oe";
    private static final String OE_ROUTE_VALUE = "operational";
    private static final String OE_TAG = "OE";
    private List<Blacklist> blacklist = null;
    private List<DcmRoute> dcmRoutes;
    private List<DcmStream> dcmStreams;
    private List<Route> routes;
    private List<Stream> streams;
    private static final Map<String, String> FIRST_START_UP_FILTER = ImmutableMap.of(RouteKeys.EVENT_NAME, "^FIRST_START_UP$");
    private static String filterValueFormat = "^%s$";
    private static final Map<String, String> SESSION_START_FILTER = ImmutableMap.of(RouteKeys.EVENT_COMPONENT, String.format(filterValueFormat, DefaultKinesisConnector.COMPONENT), RouteKeys.EVENT_NAME, String.format(filterValueFormat, "_session.start"));
    private static final Map<String, String> AUDIO_PERMISSION_FILTER = ImmutableMap.of(RouteKeys.EVENT_COMPONENT, "^com.magiear.handsfree.assistant$", RouteKeys.EVENT_SUBCOMPONENT, "^SINGLE_PERMISSIONS$", RouteKeys.EVENT_NAME, "^PERMISSION_REQUEST$");
    private static final Map<String, String> SETUP_COMPLETE_FILTER = ImmutableMap.of(RouteKeys.EVENT_COMPONENT, "^HANDSFREE_SETUP$", RouteKeys.EVENT_NAME, "^SETUP_COMPLETE$");
    private static final Map<String, String> LOCK_SCREEN_FILTER = ImmutableMap.of(RouteKeys.EVENT_COMPONENT, "^UVR$", RouteKeys.EVENT_SUBCOMPONENT, "^RESPOND_ON_LOCK_SCREEN$", RouteKeys.EVENT_NAME, "^USER_CLICK$");

    /* loaded from: classes9.dex */
    public static class Blacklist {
        private AppVersion appVersion;
        private String channelName;
        private double emissionFactor;
        private String eventName;
        private String marketplace;
        private String osType;
        private String subcomponent;

        /* loaded from: classes9.dex */
        public static class AppVersion {
            private String compare;
            private String version;

            public AppVersion(@NonNull String str, @NonNull String str2) {
                this.version = str;
                this.compare = str2;
            }

            public String compare() {
                return this.compare;
            }

            public String version() {
                return this.version;
            }
        }

        public AppVersion appVersion() {
            return this.appVersion;
        }

        public String channelName() {
            return this.channelName;
        }

        public double emissionFactor() {
            return this.emissionFactor;
        }

        public String eventName() {
            return this.eventName;
        }

        public String marketplace() {
            return this.marketplace;
        }

        public String osType() {
            return this.osType;
        }

        public String subcomponent() {
            return this.subcomponent;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Compare {
        public static final String EQUAL_TO = "=";
        public static final String GREATER_THAN = ">";
        public static final String LESS_THAN = "<";
    }

    /* loaded from: classes9.dex */
    public static class DcmRoute {
        private String key;
        @Size(max = 20, min = 2)
        private String tag;
        private String value;

        public DcmRoute(@NonNull String str, @NonNull String str2, @NonNull String str3) {
            this.key = (String) Preconditions.checkNotNull(str);
            this.value = (String) Preconditions.checkNotNull(str2);
            this.tag = (String) Preconditions.checkNotNull(str3);
        }

        public String key() {
            return this.key;
        }

        public String tag() {
            return this.tag;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes9.dex */
    public static class DcmStream {
        private String priority;
        @Size(max = 20, min = 2)
        private String tag;

        public DcmStream(@NonNull String str, @NonNull String str2) {
            this.tag = (String) Preconditions.checkNotNull(str);
            this.priority = (String) Preconditions.checkNotNull(str2);
        }

        public String priority() {
            return this.priority;
        }

        public String tag() {
            return this.tag;
        }
    }

    /* loaded from: classes9.dex */
    public static class Route {
        private Map<String, String> eventPropertyMap;
        @Size(max = 20, min = 2)
        private String tag;

        public Route(@NonNull Map<String, String> map, @NonNull String str) {
            this.eventPropertyMap = (Map) Preconditions.checkNotNull(map);
            this.tag = (String) Preconditions.checkNotNull(str);
        }

        public Map<String, String> eventPropertyMap() {
            return this.eventPropertyMap;
        }

        public String tag() {
            return this.tag;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface RouteKeys {
        public static final String EVENT_CHANNEL = "event_channel";
        public static final String EVENT_COMPONENT = "event_component";
        public static final String EVENT_NAME = "event_name";
        public static final String EVENT_SUBCOMPONENT = "event_subcomponent";
        public static final String EVENT_TYPE = "event_type";
    }

    /* loaded from: classes9.dex */
    public static class Stream {
        private String appState;
        private long flushSize;
        private long flushTime;
        private boolean metaMetrics;
        @Size(max = 40, min = Config.Radio.BLE_RADIO_WAIT_TIME_MILLISECONDS)
        private String name;
        private String protobufStreamName;
        @Size(max = 20, min = 2)
        private String tag;

        public Stream(@NonNull String str, @NonNull String str2, @NonNull String str3, long j, long j2, boolean z, @NonNull String str4) {
            this.tag = (String) Preconditions.checkNotNull(str);
            this.name = (String) Preconditions.checkNotNull(str2);
            this.protobufStreamName = (String) Preconditions.checkNotNull(str3);
            this.flushTime = j;
            this.flushSize = j2;
            this.metaMetrics = z;
            this.appState = (String) Preconditions.checkNotNull(str4);
        }

        public String appState() {
            return this.appState;
        }

        public long flushSize() {
            return this.flushSize;
        }

        public long flushTime() {
            return this.flushTime;
        }

        public boolean metaMetrics() {
            return this.metaMetrics;
        }

        public String name() {
            return this.name;
        }

        public String protobufStreamName() {
            return this.protobufStreamName;
        }

        public String tag() {
            return this.tag;
        }
    }

    @Inject
    public Config() {
        this.streams = null;
        this.routes = null;
        this.dcmStreams = null;
        this.dcmRoutes = null;
        this.streams = new ArrayList();
        this.routes = new ArrayList();
        this.dcmRoutes = new ArrayList();
        this.dcmStreams = new ArrayList();
        this.streams.add(new Stream(OE_HP_TAG, OE_HP_JSON_STREAM_NAME, OE_HP_PROTOBUF_STREAM_NAME, 10000L, 500L, false, KinesisEndpoint.AppState.ANY));
        this.streams.add(new Stream(BI_TAG, "alexa-mobilytics", BI_PROTOBUF_STREAM_NAME, 60000L, 60000L, false, KinesisEndpoint.AppState.FOREGROUND));
        this.streams.add(new Stream(OE_TAG, OE_JSON_STREAM_NAME, OE_PROTOBUF_STREAM_NAME, 60000L, 60000L, true, KinesisEndpoint.AppState.FOREGROUND));
        this.streams.add(new Stream(BI_HP_TAG, BI_HP_JSON_STREAM_NAME, BI_HP_PROTOBUF_STREAM_NAME, 10000L, 500L, false, KinesisEndpoint.AppState.ANY));
        this.routes.add(new Route(FIRST_START_UP_FILTER, OE_HP_TAG));
        this.routes.add(new Route(SESSION_START_FILTER, OE_HP_TAG));
        this.routes.add(new Route(AUDIO_PERMISSION_FILTER, OE_HP_TAG));
        this.routes.add(new Route(SETUP_COMPLETE_FILTER, OE_HP_TAG));
        this.routes.add(new Route(LOCK_SCREEN_FILTER, OE_HP_TAG));
        this.routes.add(new Route(ImmutableMap.of("event_type", "userInteraction"), BI_TAG));
        this.routes.add(new Route(ImmutableMap.of("event_type", EventType.OPERATIONAL), OE_TAG));
        this.dcmStreams.add(new DcmStream(DCM_DEFAULT_TAG, "Normal"));
        this.dcmRoutes.add(new DcmRoute("event_type", EventType.OPERATIONAL, DCM_DEFAULT_TAG));
    }

    public List<Blacklist> blacklists() {
        return this.blacklist;
    }

    public void clearBlacklist() {
        this.blacklist.clear();
    }

    public void clearDcmRoutes() {
        this.dcmRoutes.clear();
    }

    public void clearDcmStreams() {
        this.dcmStreams.clear();
    }

    public void clearRoutes() {
        this.routes.clear();
    }

    public void clearStreams() {
        this.streams.clear();
    }

    public List<DcmRoute> dcmRoutes() {
        return this.dcmRoutes;
    }

    public List<DcmStream> dcmStreams() {
        return this.dcmStreams;
    }

    public List<Route> routes() {
        return this.routes;
    }

    public List<Stream> streams() {
        return this.streams;
    }
}
