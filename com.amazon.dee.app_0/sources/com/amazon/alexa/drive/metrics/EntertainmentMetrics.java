package com.amazon.alexa.drive.metrics;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes7.dex */
public class EntertainmentMetrics {
    private static final String COMPONENT_NAME = "DriveMode";
    private static final String TAG = "EntertainmentMetrics";
    private final LazyComponent<Mobilytics> mMobilytics = ComponentRegistry.getInstance().getLazy(Mobilytics.class);
    private final Map<String, MobilyticsMetricsTimer> mTimerMap = new ConcurrentHashMap();
    private final Map<String, MobilyticsMetricsCounter> mCounterMap = new ConcurrentHashMap();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface ApiStatus {
        public static final String FAILURE = "Failure";
        public static final String SUCCESS = "Success";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface AutoModeType {
        public static final String ACCESSORY = "Accessory";
        public static final String AUTOBLUETOOTH = "AutoBluetooth";
        public static final String DEFAULT = "Default";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface Button {
        public static final String NEXT = "Next";
        public static final String PAUSE = "Pause";
        public static final String PLAY = "Play";
        public static final String PREV = "Prev";
        public static final String PREV30 = "Prev30";
        public static final String REPEAT = "Repeat";
        public static final String SHUFFLE = "Shuffle";
        public static final String SKIP30 = "Skip30";
        public static final String THUMBSDOWN = "ThumbsDown";
        public static final String THUMBSUP = "ThumbsUp";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface Error {
        public static final String GENERIC = "Generic";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface Location {
        public static final String ENT = "Ent";
        public static final String HOME = "Home";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface MetricNames {
        public static final String ENTERTAINMENT_AUTOMODETYPE_MUSICHISTORYSELECTED_POSITION = "Entertainment_%sMusicHistorySelected%s";
        public static final String ENTERTAINMENT_AUTOMODETYPE_NPC_LOCATION_BUTTON_BUTTONSELECTED = "Entertainment_%sNPC%s%sButtonSelected";
        public static final String ENTERTAINMENT_AUTOMODETYPE_NPC_LOCATION_SELECTED = "Entertainment_%sNPC%sSelected";
        public static final String ENTERTAINMENT_AUTOMODETYPE_NPSCLOSED = "Entertainment_%sNPSClosed";
        public static final String ENTERTAINMENT_AUTOMODETYPE_NPS_BUTTON_BUTTONSELECTED = "Entertainment_%sNPS%sButtonSelected";
        public static final String ENTERTAINMENT_FETCHENTERTAINMENTCARDDATAAPI_APISTATUS = "Entertainment_FetchEntertainmentCardDataApi%s";
        public static final String ENTERTAINMENT_MUSICHISTORYDISPLAYED = "Entertainment_MusicHistoryDisplayed";
        public static final String ENTERTAINMENT_MUSICHISTORYEMPTYDISPLAYED = "Entertainment_MusicHistoryEmptyDisplayed";
        public static final String ENTERTAINMENT_MUSICHISTORYERRORDISPLAYED_ERROR = "Entertainment_MusicHistoryErrorDisplayed%s";
        public static final String ENTERTAINMENT_NOWPLAYINGSCREENINFOAPI_APISTATUS = "Entertainment_NowPlayingScreenInfoApi%s";
        public static final String ENTERTAINMENT_NPSDISPLAYED = "Entertainment_NPSDisplayed";
        public static final String ENTERTAINMENT_PLAYERCOMMANDAPI_APISTATUS = "Entertainment_PlayerCommandApi%s";
        public static final String ENTERTAINMENT_PLAYLISTINITAPI_APISTATUS = "Entertainment_PlaylistInitApi%s";
        public static final String ENTERTAINMENT_PLAYQUEUESTARTED_MSP = "Entertainment_PlayQueueStarted%s";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface SubComponentType {
        public static final String ENTERTAINMENT = "Entertainment";
    }

    private void createTimer(String str, String str2) {
        if (this.mMobilytics.mo10268get() == null) {
            return;
        }
        this.mTimerMap.put(str, this.mMobilytics.mo10268get().createTimer(str, "DriveMode", str2, OwnerIdentifier.ALEXA_APP_AUTOMOTIVE_OTHER));
    }

    private void logMetric(String str, String str2) {
        MobilyticsMetricsCounter createCounter;
        if (this.mMobilytics.mo10268get() == null || (createCounter = this.mMobilytics.mo10268get().createCounter(str, "DriveMode", str2, OwnerIdentifier.ALEXA_APP_AUTOMOTIVE_OTHER)) == null) {
            return;
        }
        createCounter.incrementCounter();
        this.mMobilytics.mo10268get().recordCounter(createCounter);
    }

    private void removeTimer(String str) {
        MobilyticsMetricsTimer remove;
        if (this.mMobilytics.mo10268get() == null || (remove = this.mTimerMap.remove(str)) == null) {
            return;
        }
        remove.finishTimer();
    }

    public MobilyticsMetricsCounter createCounter(String str, String str2) {
        if (this.mMobilytics.mo10268get() == null) {
            return null;
        }
        MobilyticsMetricsCounter createCounter = this.mMobilytics.mo10268get().createCounter(str, "DriveMode", str2, OwnerIdentifier.ALEXA_APP_AUTOMOTIVE_OTHER);
        this.mCounterMap.put(str, createCounter);
        return createCounter;
    }

    public void logFetchEntertainmentCardDataApi(String str) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_FETCHENTERTAINMENTCARDDATAAPI_APISTATUS, str), "Entertainment");
    }

    public void logMusicHistoryDisplayed() {
        logMetric(MetricNames.ENTERTAINMENT_MUSICHISTORYDISPLAYED, "Entertainment");
    }

    public void logMusicHistoryEmptyDisplayed() {
        logMetric(MetricNames.ENTERTAINMENT_MUSICHISTORYEMPTYDISPLAYED, "Entertainment");
    }

    public void logMusicHistoryErrorDisplayed(String str) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_MUSICHISTORYERRORDISPLAYED_ERROR, str), "Entertainment");
    }

    public void logMusicHistorySelected(String str, String str2) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_AUTOMODETYPE_MUSICHISTORYSELECTED_POSITION, str, str2), "Entertainment");
    }

    public void logNPCButtonSelected(String str, String str2, String str3) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_AUTOMODETYPE_NPC_LOCATION_BUTTON_BUTTONSELECTED, str, str2, str3), "Entertainment");
    }

    public void logNPCSelected(String str, String str2) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_AUTOMODETYPE_NPC_LOCATION_SELECTED, str, str2), "Entertainment");
    }

    public void logNPSButtonSelected(String str, String str2) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_AUTOMODETYPE_NPS_BUTTON_BUTTONSELECTED, str, str2), "Entertainment");
    }

    public void logNPSClosed(String str) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_AUTOMODETYPE_NPSCLOSED, str), "Entertainment");
    }

    public void logNPSDisplayed() {
        logMetric(MetricNames.ENTERTAINMENT_NPSDISPLAYED, "Entertainment");
    }

    public void logNowPlayingScreenInfoApi(String str) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_NOWPLAYINGSCREENINFOAPI_APISTATUS, str), "Entertainment");
    }

    public void logPlayQueueStarted(String str) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_PLAYQUEUESTARTED_MSP, str), "Entertainment");
    }

    public void logPlayerCommandApi(String str) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_PLAYERCOMMANDAPI_APISTATUS, str), "Entertainment");
    }

    public void logPlaylistInitApi(String str) {
        logMetric(String.format(MetricNames.ENTERTAINMENT_PLAYLISTINITAPI_APISTATUS, str), "Entertainment");
    }

    public void recordCounter(String str) {
        if (this.mMobilytics.mo10268get() == null) {
            return;
        }
        MobilyticsMetricsCounter remove = this.mCounterMap.remove(str);
        if (remove == null) {
            GeneratedOutlineSupport1.outline158("Tried to record a counter that does not exist yet: ", str);
        } else {
            this.mMobilytics.mo10268get().recordCounter(remove);
        }
    }

    public void recordTimer(String str) {
        if (this.mMobilytics.mo10268get() == null) {
            return;
        }
        MobilyticsMetricsTimer remove = this.mTimerMap.remove(str);
        if (remove == null) {
            GeneratedOutlineSupport1.outline158("Tried to record a timer that does not exist yet: ", str);
            return;
        }
        remove.finishTimer();
        this.mMobilytics.mo10268get().recordTimer(remove);
    }
}
