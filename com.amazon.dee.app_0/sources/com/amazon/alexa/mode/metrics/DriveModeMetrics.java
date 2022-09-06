package com.amazon.alexa.mode.metrics;

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
/* loaded from: classes9.dex */
public class DriveModeMetrics {
    private static final String COMPONENT_NAME = "DriveMode";
    private static final String TAG = "DriveModeMetrics";
    private final LazyComponent<Mobilytics> mMobilytics = ComponentRegistry.getInstance().getLazy(Mobilytics.class);
    private final Map<String, MobilyticsMetricsTimer> mTimerMap = new ConcurrentHashMap();
    private final Map<String, MobilyticsMetricsCounter> mCounterMap = new ConcurrentHashMap();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface AutoModeType {
        public static final String ACCESSORY = "Accessory";
        public static final String AUTOBLUETOOTH = "AutoBluetooth";
        public static final String DEFAULT = "Default";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Channel {
        public static final String COMMS = "Comms";
        public static final String ENTERTAINMENT = "Entertainment";
        public static final String HOME = "Home";
        public static final String NAVIGATION = "Navigation";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface ConnectionStatus {
        public static final String CONNECTED = "Connected";
        public static final String DISCONNECTED = "Disconnected";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface DeviceType {
        public static final String GYCOM = "GYCOM";
        public static final String IOTTIE = "iOttie";
        public static final String IVECO = "Iveco";
        public static final String JVC = "JVC";
        public static final String MUFFIN = "Muffin";
        public static final String NEXTBASE = "Nextbase";
        public static final String PIONEER = "Pioneer";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface EgressType {
        public static final String APPTERMINATIONEGRESS = "AppTerminationEgress";
        public static final String DEVICEDISCONNECTIONEGRESS = "DeviceDisconnectionEgress";
        public static final String MANUALEGRESS = "ManualEgress";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface HeadUnitType {
        public static final String AAOSDEV = "AAOSDev";
        public static final String ALFAROMEO = "AlfaRomeo";
        public static final String AMAZONFIRETABLET = "AmazonFireTablet";
        public static final String AUDI = "Audi";
        public static final String BENTLEY = "Bentley";
        public static final String BMW = "BMW";
        public static final String BUICK = "Buick";
        public static final String CADILLAC = "Cadillac";
        public static final String CHEVROLET = "Chevrolet";
        public static final String CHRYSLER_DODGE_JEEP_RAM = "Chrysler_Dodge_Jeep_RAM";
        public static final String CITROEN = "Citroen";
        public static final String DACIA = "Dacia";
        public static final String DATSUN = "Datsun";
        public static final String DS = "DS";
        public static final String FERRARI = "Ferrari";
        public static final String FIATABARTH = "FiatAbarth";
        public static final String FORD = "Ford";
        public static final String GALAXYTABS4 = "GalaxyTabS4";
        public static final String GENESIS = "Genesis";
        public static final String GMC = "GMC";
        public static final String HINO = "Hino";
        public static final String HOLDEN = "Holden";
        public static final String HONDA = "Honda";
        public static final String HYUNDAI = "Hyundai";
        public static final String ISUZU = "Isuzu";
        public static final String JAGUAR = "Jaguar";
        public static final String JBL = "JBL";
        public static final String JBLGO = "JBLGO";
        public static final String JEEP = "Jeep";
        public static final String KIA = "Kia";
        public static final String LADA = "Lada";
        public static final String LAMBORGHINI = "Lamborghini";
        public static final String LANCIA = "Lancia";
        public static final String LANDROVER = "LandRover";
        public static final String LEXUS = "Lexus";
        public static final String MAN = "MAN";
        public static final String MASERATI = "Maserati";
        public static final String MAZDA = "Mazda";
        public static final String MERCEDESBENZ = "MercedesBenz";
        public static final String MINI = "Mini";
        public static final String MITSUBISHI = "Mitsubishi";
        public static final String NISSAN = "Nissan";
        public static final String OPEL = "Opel";
        public static final String PERODUA = "Perodua";
        public static final String PEUGEOT = "Peugeot";
        public static final String PORSCHE = "Porsche";
        public static final String RANGEROVER = "RangeRover";
        public static final String RENAULT = "Renault";
        public static final String SCANIA = "Scania";
        public static final String SEAT = "SEAT";
        public static final String SEICANE = "Seicane";
        public static final String SKODA = "Skoda";
        public static final String SONY_SPEAKERS = "SONY_Speakers";
        public static final String SSANGYONG = "SsangYong";
        public static final String SUBARU = "Subaru";
        public static final String SUZUKI = "Suzuki";
        public static final String TATA = "Tata";
        public static final String TESLA = "Tesla";
        public static final String TOYOTA = "Toyota";
        public static final String TROLLER = "Troller";
        public static final String UNKNOWN = "Unknown";
        public static final String VAUXHALL = "Vauxhall";
        public static final String VENUCIA = "Venucia";
        public static final String VOLKSWAGEN = "Volkswagen";
        public static final String VOLVO = "Volvo";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface IngressType {
        public static final String AUTOINGRESS = "AutoIngress";
        public static final String CARDINGRESS = "CardIngress";
        public static final String DEVICESETUPINGRESS = "DeviceSetupIngress";
        public static final String NOTIFICATIONINGRESS = "NotificationIngress";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface MetricNames {
        public static final String DRIVEMODEFTUE_ACCESSORYFTUECANCELLED = "DriveModeFTUE_AccessoryFTUECancelled";
        public static final String DRIVEMODEFTUE_ACCESSORYFTUECOMPLETED = "DriveModeFTUE_AccessoryFTUECompleted";
        public static final String DRIVEMODEFTUE_ACCESSORYFTUESTARTED = "DriveModeFTUE_AccessoryFTUEStarted";
        public static final String DRIVEMODEFTUE_AUTOBLUETOOTHFTUECANCELLED = "DriveModeFTUE_AutoBluetoothFTUECancelled";
        public static final String DRIVEMODEFTUE_AUTOBLUETOOTHFTUECOMPLETED = "DriveModeFTUE_AutoBluetoothFTUECompleted";
        public static final String DRIVEMODEFTUE_AUTOBLUETOOTHFTUESTARTED = "DriveModeFTUE_AutoBluetoothFTUEStarted";
        public static final String DRIVEMODEFTUE_DEFAULTFTUECANCELLED = "DriveModeFTUE_DefaultFTUECancelled";
        public static final String DRIVEMODEFTUE_DEFAULTFTUECOMPLETED = "DriveModeFTUE_DefaultFTUECompleted";
        public static final String DRIVEMODEFTUE_DEFAULTFTUESTARTED = "DriveModeFTUE_DefaultFTUEStarted";
        public static final String DRIVEMODE_ACCESSORYCONNECTEDWITHNETWORKSTATUS_NETWORKSTATUS = "DriveMode_AccessoryConnectedWithNetworkStatus%s";
        public static final String DRIVEMODE_AUTOMODETYPE_WAKEWORDINVOCATION = "DriveMode_%sWakeWordInvocation";
        public static final String DRIVEMODE_BACKGROUNDED = "DriveMode_Backgrounded";
        public static final String DRIVEMODE_DAYMODETRIGGERED = "DriveMode_DayModeTriggered";
        public static final String DRIVEMODE_FOREGROUNDED = "DriveMode_Foregrounded";
        public static final String DRIVEMODE_HOMECHANNELCARDREMOVED = "DriveMode_HomeChannelCardRemoved";
        public static final String DRIVEMODE_HOMECHANNELCARDSHOWN = "DriveMode_HomeChannelCardShown";
        public static final String DRIVEMODE_NETWORKCONNECTION_NETWORKSTATUS = "DriveMode_NetworkConnection%s";
        public static final String DRIVEMODE_NIGHTMODETRIGGERED = "DriveMode_NightModeTriggered";
        public static final String DRIVEMODE_NONETWORKDISMISSED = "DriveMode_NoNetworkDismissed";
        public static final String DRIVEMODE_NONETWORKDISPLAYED = "DriveMode_NoNetworkDisplayed";
        public static final String DRIVEMODE_SESSIONENDED_AUTOMODETYPE = "DriveMode_SessionEnded%s";
        public static final String DRIVEMODE_SESSIONENDED_AUTOMODETYPE_EGRESSTYPE_CHANNEL = "DriveMode_SessionEnded%s%s%s";
        public static final String DRIVEMODE_SESSIONSTARTEDWITHACCESSORYSTATUS_AUTOMODETYPE_CONNECTIONSTATUS = "DriveMode_SessionStartedWithAccessoryStatus%s%s";
        public static final String DRIVEMODE_SESSIONSTARTEDWITHHEADUNITSTATUS_AUTOMODETYPE_CONNECTIONSTATUS = "DriveMode_SessionStartedWithHeadUnitStatus%s%s";
        public static final String DRIVEMODE_SESSIONSTARTEDWITHINGRESSTYPE_AUTOMODETYPE_INGRESSTYPE = "DriveMode_SessionStartedWithIngressType%s%s";
        public static final String DRIVEMODE_SESSIONSTARTEDWITHNETWORKSTATUS_NETWORKSTATUS = "DriveMode_SessionStartedWithNetworkStatus%s";
        public static final String DRIVEMODE_SESSIONSTARTEDWITHTHEME_THEME = "DriveMode_SessionStartedWithTheme%s";
        public static final String DRIVEMODE_SESSIONSTARTED_AUTOMODETYPE = "DriveMode_SessionStarted%s";
        public static final String DRIVEMODE_TTTBUTTONPRESSED_CHANNEL = "DriveMode_TTTButtonPressed%s";
        public static final String MODE_ACCESSORYCONNECTED = "Mode_AccessoryConnected";
        public static final String MODE_ACCESSORYCONNECTEDWITHAUTOBLUETOOTHCONNECTED = "Mode_AccessoryConnectedWithAutoBluetoothConnected";
        public static final String MODE_ACCESSORYCONNECTEDWITHAUTOBLUETOOTHDISCONNECTED = "Mode_AccessoryConnectedWithAutoBluetoothDisconnected";
        public static final String MODE_ACCESSORYCONNECTED_DEVICETYPE = "Mode_AccessoryConnected%s";
        public static final String MODE_ACCESSORYCONNECTED_DEVICETYPE_WITHAUTOBLUETOOTHCONNECTED = "Mode_AccessoryConnected%sWithAutoBluetoothConnected";
        public static final String MODE_ACCESSORYCONNECTED_DEVICETYPE_WITHAUTOBLUETOOTHDISCONNECTED = "Mode_AccessoryConnected%sWithAutoBluetoothDisconnected";
        public static final String MODE_ACCESSORYDISCONNECTED = "Mode_AccessoryDisconnected";
        public static final String MODE_ACCESSORYTIMERENDED = "Mode_AccessoryTimerEnded";
        public static final String MODE_AUTOBLUETOOTHCONNECTED = "Mode_AutoBluetoothConnected";
        public static final String MODE_AUTOBLUETOOTHCONNECTEDWITHACCESSORYCONNECTED = "Mode_AutoBluetoothConnectedWithAccessoryConnected";
        public static final String MODE_AUTOBLUETOOTHCONNECTEDWITHACCESSORYCONNECTED_DEVICETYPE = "Mode_AutoBluetoothConnectedWithAccessoryConnected%s";
        public static final String MODE_AUTOBLUETOOTHCONNECTEDWITHACCESSORYDISCONNECTED = "Mode_AutoBluetoothConnectedWithAccessoryDisconnected";
        public static final String MODE_AUTOBLUETOOTHCONNECTED_HEADUNITTYPE_CLASSOFDEVICE = "Mode_AutoBluetoothConnected%s%s";
        public static final String MODE_NOTIFICATIONNOTSHOWN_REASON = "Mode_NotificationNotShown%s";
        public static final String MODE_NOTIFICATIONREMOVED = "Mode_NotificationRemoved";
        public static final String MODE_NOTIFICATIONSHOWN = "Mode_NotificationShown";
        public static final String MODE_SECONDARYNOTIFICATIONNOTSHOWN_REASON = "Mode_SecondaryNotificationNotShown%s";
        public static final String MODE_SECONDARYNOTIFICATIONSHOWN = "Mode_SecondaryNotificationShown";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface NetworkStatus {
        public static final String ONLINE = "Online";
        public static final String UNAVAILABLE = "Unavailable";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Reason {
        public static final String PLATFORMPERMISSIONOFF = "PlatformPermissionOff";
        public static final String POSTNOTIFICATIONOFF = "PostNotificationOff";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface SubComponentType {
        public static final String DRIVEMODE = "DriveMode";
        public static final String DRIVEMODEFTUE = "DriveModeFTUE";
        public static final String MODE = "Mode";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Theme {
        public static final String DARK = "Dark";
        public static final String LIGHT = "Light";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface TimerNames {
        public static final String ACCESSORYTIMER = "Mode_AccessoryTimer";
        public static final String ACCESSORYTIMETOFAILURE = "DriveModeFTUE_AccessoryTimeToFailure";
        public static final String ACCESSORYTIMETOSUCCESS = "DriveModeFTUE_AccessoryTimeToSuccess";
        public static final String AMAAFTERAUTOBTTIMER = "Mode_AMAAfterAutoBtTimer";
        public static final String AUTOBLUETOOTHTIMETOFAILURE = "DriveModeFTUE_AutoBluetoothTimeToFailure";
        public static final String AUTOBLUETOOTHTIMETOSUCCESS = "DriveModeFTUE_AutoBluetoothTimeToSuccess";
        public static final String AUTOBTAFTERAMATIMER = "Mode_AutoBtAfterAMATimer";
        public static final String DEFAULTTIMETOFAILURE = "DriveModeFTUE_DefaultTimeToFailure";
        public static final String DEFAULTTIMETOSUCCESS = "DriveModeFTUE_DefaultTimeToSuccess";
        public static final String SESSIONTIMER_AUTOMODETYPE = "DriveMode_SessionTimer%s";
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

    public void logAccessoryConnected() {
        logMetric(MetricNames.MODE_ACCESSORYCONNECTED, SubComponentType.MODE);
    }

    public void logAccessoryConnectedWithAutoBluetoothConnected(String str) {
        logMetric(String.format(MetricNames.MODE_ACCESSORYCONNECTED_DEVICETYPE_WITHAUTOBLUETOOTHCONNECTED, str), SubComponentType.MODE);
    }

    public void logAccessoryConnectedWithAutoBluetoothConnectedWithTimers() {
        logMetric(MetricNames.MODE_ACCESSORYCONNECTEDWITHAUTOBLUETOOTHCONNECTED, SubComponentType.MODE);
        recordTimer(TimerNames.AMAAFTERAUTOBTTIMER);
    }

    public void logAccessoryConnectedWithAutoBluetoothDisconnected(String str) {
        logMetric(String.format(MetricNames.MODE_ACCESSORYCONNECTED_DEVICETYPE_WITHAUTOBLUETOOTHDISCONNECTED, str), SubComponentType.MODE);
    }

    public void logAccessoryConnectedWithAutoBluetoothDisconnectedWithTimers() {
        logMetric(MetricNames.MODE_ACCESSORYCONNECTEDWITHAUTOBLUETOOTHDISCONNECTED, SubComponentType.MODE);
        createTimer(TimerNames.ACCESSORYTIMER, SubComponentType.MODE);
        createTimer(TimerNames.AUTOBTAFTERAMATIMER, SubComponentType.MODE);
    }

    public void logAccessoryConnectedWithNetworkStatus(String str) {
        logMetric(String.format(MetricNames.DRIVEMODE_ACCESSORYCONNECTEDWITHNETWORKSTATUS_NETWORKSTATUS, str), "DriveMode");
    }

    public void logAccessoryDisconnected() {
        logMetric(MetricNames.MODE_ACCESSORYDISCONNECTED, SubComponentType.MODE);
    }

    public void logAccessoryFTUECancelledWithTimers() {
        logMetric(MetricNames.DRIVEMODEFTUE_ACCESSORYFTUECANCELLED, SubComponentType.DRIVEMODEFTUE);
        recordTimer(TimerNames.ACCESSORYTIMETOFAILURE);
    }

    public void logAccessoryFTUECompletedWithTimers() {
        logMetric(MetricNames.DRIVEMODEFTUE_ACCESSORYFTUECOMPLETED, SubComponentType.DRIVEMODEFTUE);
        recordTimer(TimerNames.ACCESSORYTIMETOSUCCESS);
    }

    public void logAccessoryFTUEStartedWithTimers() {
        logMetric(MetricNames.DRIVEMODEFTUE_ACCESSORYFTUESTARTED, SubComponentType.DRIVEMODEFTUE);
        createTimer(TimerNames.ACCESSORYTIMETOSUCCESS, SubComponentType.DRIVEMODEFTUE);
        createTimer(TimerNames.ACCESSORYTIMETOFAILURE, SubComponentType.DRIVEMODEFTUE);
    }

    public void logAccessoryTimerEndedWithTimers() {
        logMetric(MetricNames.MODE_ACCESSORYTIMERENDED, SubComponentType.MODE);
        recordTimer(TimerNames.ACCESSORYTIMER);
    }

    public void logAutoBluetoothConnected() {
        logMetric(MetricNames.MODE_AUTOBLUETOOTHCONNECTED, SubComponentType.MODE);
    }

    public void logAutoBluetoothConnectedWithAccessoryConnected(String str) {
        logMetric(String.format(MetricNames.MODE_AUTOBLUETOOTHCONNECTEDWITHACCESSORYCONNECTED_DEVICETYPE, str), SubComponentType.MODE);
    }

    public void logAutoBluetoothConnectedWithAccessoryConnectedWithTimers() {
        logMetric(MetricNames.MODE_AUTOBLUETOOTHCONNECTEDWITHACCESSORYCONNECTED, SubComponentType.MODE);
        recordTimer(TimerNames.AUTOBTAFTERAMATIMER);
    }

    public void logAutoBluetoothConnectedWithAccessoryDisconnectedWithTimers() {
        logMetric(MetricNames.MODE_AUTOBLUETOOTHCONNECTEDWITHACCESSORYDISCONNECTED, SubComponentType.MODE);
        createTimer(TimerNames.AMAAFTERAUTOBTTIMER, SubComponentType.MODE);
    }

    public void logAutoBluetoothFTUECancelledWithTimers() {
        logMetric(MetricNames.DRIVEMODEFTUE_AUTOBLUETOOTHFTUECANCELLED, SubComponentType.DRIVEMODEFTUE);
        recordTimer(TimerNames.AUTOBLUETOOTHTIMETOFAILURE);
    }

    public void logAutoBluetoothFTUECompletedWithTimers() {
        logMetric(MetricNames.DRIVEMODEFTUE_AUTOBLUETOOTHFTUECOMPLETED, SubComponentType.DRIVEMODEFTUE);
        recordTimer(TimerNames.AUTOBLUETOOTHTIMETOSUCCESS);
    }

    public void logAutoBluetoothFTUEStartedWithTimers() {
        logMetric(MetricNames.DRIVEMODEFTUE_AUTOBLUETOOTHFTUESTARTED, SubComponentType.DRIVEMODEFTUE);
        createTimer(TimerNames.AUTOBLUETOOTHTIMETOSUCCESS, SubComponentType.DRIVEMODEFTUE);
        createTimer(TimerNames.AUTOBLUETOOTHTIMETOFAILURE, SubComponentType.DRIVEMODEFTUE);
    }

    public void logBackgrounded() {
        logMetric(MetricNames.DRIVEMODE_BACKGROUNDED, "DriveMode");
    }

    public void logDayModeTriggered() {
        logMetric(MetricNames.DRIVEMODE_DAYMODETRIGGERED, "DriveMode");
    }

    public void logDefaultFTUECancelledWithTimers() {
        logMetric(MetricNames.DRIVEMODEFTUE_DEFAULTFTUECANCELLED, SubComponentType.DRIVEMODEFTUE);
        recordTimer(TimerNames.DEFAULTTIMETOFAILURE);
    }

    public void logDefaultFTUECompletedWithTimers() {
        logMetric(MetricNames.DRIVEMODEFTUE_DEFAULTFTUECOMPLETED, SubComponentType.DRIVEMODEFTUE);
        recordTimer(TimerNames.DEFAULTTIMETOSUCCESS);
    }

    public void logDefaultFTUEStartedWithTimers() {
        logMetric(MetricNames.DRIVEMODEFTUE_DEFAULTFTUESTARTED, SubComponentType.DRIVEMODEFTUE);
        createTimer(TimerNames.DEFAULTTIMETOSUCCESS, SubComponentType.DRIVEMODEFTUE);
        createTimer(TimerNames.DEFAULTTIMETOFAILURE, SubComponentType.DRIVEMODEFTUE);
    }

    public void logForegrounded() {
        logMetric(MetricNames.DRIVEMODE_FOREGROUNDED, "DriveMode");
    }

    public void logHomeChannelCardRemoved() {
        logMetric(MetricNames.DRIVEMODE_HOMECHANNELCARDREMOVED, "DriveMode");
    }

    public void logHomeChannelCardShown() {
        logMetric(MetricNames.DRIVEMODE_HOMECHANNELCARDSHOWN, "DriveMode");
    }

    public void logNetworkConnection(String str) {
        logMetric(String.format(MetricNames.DRIVEMODE_NETWORKCONNECTION_NETWORKSTATUS, str), "DriveMode");
    }

    public void logNightModeTriggered() {
        logMetric(MetricNames.DRIVEMODE_NIGHTMODETRIGGERED, "DriveMode");
    }

    public void logNoNetworkDismissed() {
        logMetric(MetricNames.DRIVEMODE_NONETWORKDISMISSED, "DriveMode");
    }

    public void logNoNetworkDisplayed() {
        logMetric(MetricNames.DRIVEMODE_NONETWORKDISPLAYED, "DriveMode");
    }

    public void logNotificationNotShown(String str) {
        logMetric(String.format(MetricNames.MODE_NOTIFICATIONNOTSHOWN_REASON, str), SubComponentType.MODE);
    }

    public void logNotificationRemoved() {
        logMetric(MetricNames.MODE_NOTIFICATIONREMOVED, SubComponentType.MODE);
    }

    public void logNotificationShown() {
        logMetric(MetricNames.MODE_NOTIFICATIONSHOWN, SubComponentType.MODE);
    }

    public void logSecondaryNotificationNotShown(String str) {
        logMetric(String.format(MetricNames.MODE_SECONDARYNOTIFICATIONNOTSHOWN_REASON, str), SubComponentType.MODE);
    }

    public void logSecondaryNotificationShown() {
        logMetric(MetricNames.MODE_SECONDARYNOTIFICATIONSHOWN, SubComponentType.MODE);
    }

    public void logSessionEnded(String str, String str2, String str3) {
        logMetric(String.format(MetricNames.DRIVEMODE_SESSIONENDED_AUTOMODETYPE_EGRESSTYPE_CHANNEL, str, str2, str3), "DriveMode");
    }

    public void logSessionEndedWithTimers(String str) {
        logMetric(String.format(MetricNames.DRIVEMODE_SESSIONENDED_AUTOMODETYPE, str), "DriveMode");
        recordTimer(String.format(TimerNames.SESSIONTIMER_AUTOMODETYPE, str));
    }

    public void logSessionStartedWithAccessoryStatus(String str, String str2) {
        logMetric(String.format(MetricNames.DRIVEMODE_SESSIONSTARTEDWITHACCESSORYSTATUS_AUTOMODETYPE_CONNECTIONSTATUS, str, str2), "DriveMode");
    }

    public void logSessionStartedWithHeadUnitStatus(String str, String str2) {
        logMetric(String.format(MetricNames.DRIVEMODE_SESSIONSTARTEDWITHHEADUNITSTATUS_AUTOMODETYPE_CONNECTIONSTATUS, str, str2), "DriveMode");
    }

    public void logSessionStartedWithIngressType(String str, String str2) {
        logMetric(String.format(MetricNames.DRIVEMODE_SESSIONSTARTEDWITHINGRESSTYPE_AUTOMODETYPE_INGRESSTYPE, str, str2), "DriveMode");
    }

    public void logSessionStartedWithNetworkStatus(String str) {
        logMetric(String.format(MetricNames.DRIVEMODE_SESSIONSTARTEDWITHNETWORKSTATUS_NETWORKSTATUS, str), "DriveMode");
    }

    public void logSessionStartedWithTheme(String str) {
        logMetric(String.format(MetricNames.DRIVEMODE_SESSIONSTARTEDWITHTHEME_THEME, str), "DriveMode");
    }

    public void logSessionStartedWithTimers(String str) {
        logMetric(String.format(MetricNames.DRIVEMODE_SESSIONSTARTED_AUTOMODETYPE, str), "DriveMode");
        createTimer(String.format(TimerNames.SESSIONTIMER_AUTOMODETYPE, str), "DriveMode");
    }

    public void logTTTButtonPressed(String str) {
        logMetric(String.format(MetricNames.DRIVEMODE_TTTBUTTONPRESSED_CHANNEL, str), "DriveMode");
    }

    public void logWakeWordInvocation(String str) {
        logMetric(String.format(MetricNames.DRIVEMODE_AUTOMODETYPE_WAKEWORDINVOCATION, str), "DriveMode");
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

    public void logAccessoryConnected(String str) {
        logMetric(String.format(MetricNames.MODE_ACCESSORYCONNECTED_DEVICETYPE, str), SubComponentType.MODE);
    }

    public void logAutoBluetoothConnected(String str, String str2) {
        logMetric(String.format(MetricNames.MODE_AUTOBLUETOOTHCONNECTED_HEADUNITTYPE_CLASSOFDEVICE, str, str2), SubComponentType.MODE);
    }
}
