package com.amazon.alexa.accessory.notificationpublisher.consumption;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioFocusManager;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.InputBehaviorConfigProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper;
import com.amazon.alexa.accessory.notificationpublisher.utils.AlexaServiceHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.protocol.Input;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public final class GestureManager extends BaseComponent {
    public static final int EVENT_GESTURE_PHONE_NOTIFICATIONS_TOGGLE = 5;
    public static final int EVENT_GESTURE_PLAY = 1;
    public static final int EVENT_GESTURE_SDM_TOGGLE = 4;
    public static final int EVENT_GESTURE_STOP = 2;
    public static final int EVENT_GESTURE_TRIGGERED = 3;
    private static final long GESTURE_IGNORE_THRESHOLD_MILLIS = 630;
    private static final int INPUT_BEHAVIOR_VIP_FILTER_PHONE_NOTIFICATION_MODE_VALUE = 25;
    private static final String TAG = "GestureManager";
    private static final String ZION_DEVICE_TYPE_ID = "A3IYPH06PH1HRA";
    private static GestureManager gestureManagerInstance;
    private long lastGestureTimeMillis;

    /* renamed from: com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputBehavior = new int[Input.InputBehavior.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputBehavior[Input.InputBehavior.INPUT_BEHAVIOR_VIP_FILTER_POSITIVE_RESPONSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputBehavior[Input.InputBehavior.INPUT_BEHAVIOR_VIP_FILTER_NEGATIVE_RESPONSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction = new int[Input.InputAction.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_BACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_FORWARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_TAP.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private GestureManager() {
        super(2);
        this.lastGestureTimeMillis = 0L;
    }

    public static synchronized GestureManager getInstance() {
        GestureManager gestureManager;
        synchronized (GestureManager.class) {
            if (gestureManagerInstance == null) {
                gestureManagerInstance = new GestureManager();
            }
            gestureManager = gestureManagerInstance;
        }
        return gestureManager;
    }

    public static synchronized void releaseInstance() {
        synchronized (GestureManager.class) {
            gestureManagerInstance = null;
        }
    }

    private boolean shouldProcessGesture() {
        Log.i(TAG, "shouldProcessGesture");
        long currentTimeMillis = System.currentTimeMillis();
        Log.i(TAG, String.format(Locale.US, "shouldProcessGesture - currentTimeInMillis = %d, lastGestureTimeMillis = %d", Long.valueOf(currentTimeMillis), Long.valueOf(this.lastGestureTimeMillis)));
        if (currentTimeMillis - this.lastGestureTimeMillis < GESTURE_IGNORE_THRESHOLD_MILLIS || AudioFocusManager.getInstance().getHigherPriorityPlaybackState() != AudioFocusManager.HigherPriorityAudioPlaybackState.NONE) {
            return false;
        }
        Log.i(TAG, "shouldProcessGesture return true");
        return true;
    }

    private void updateLastGestureTime() {
        Log.i(TAG, "updateLastGestureTime");
        this.lastGestureTimeMillis = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.BaseComponent
    public void handleEventMessage(int i, @Nullable Object obj) {
        if (obj == null) {
            GeneratedOutlineSupport1.outline151("handleEventMessage - missing payload, discard. eventId: ", i, TAG);
        } else if (i != 3) {
            GeneratedOutlineSupport1.outline151("handleEventMessage - UnsupportedEventId. eventId: ", i, TAG);
        } else {
            try {
                String str = TAG;
                Log.d(str, "received object: " + obj.toString());
                GestureEventPayload gestureEventPayload = (GestureEventPayload) obj;
                if (FeatureAccessChecker.hasOtgVipFilterAccess()) {
                    processInputEventV2(gestureEventPayload.getInputSource(), gestureEventPayload.getInputAction(), gestureEventPayload.getInputBehavior(), gestureEventPayload.getDeviceType(), gestureEventPayload.getDeviceAddress());
                } else {
                    processInputEvent(gestureEventPayload.getInputSource(), gestureEventPayload.getInputAction(), gestureEventPayload.getDeviceType());
                }
            } catch (Exception e) {
                Log.w(TAG, "handleEventMessage - Exception retrieving input event.", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onGestureEventReceived(@NonNull Object obj) {
        Log.d(TAG, "onGestureEventReceived");
        ConsumptionEngine.getInstance().postEventMessage(2, 2, 3, obj);
    }

    void onGesturePlay() {
        if (DistractionModeProvider.getCurrentDistractionMode() == 3) {
            Log.i(TAG, "Silent Mode ON, skipping Gesture Play");
            return;
        }
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.SWIPE_RECEIVED);
        postEventMessage(1);
    }

    void onGestureStop() {
        if (DistractionModeProvider.getCurrentDistractionMode() == 3) {
            Log.i(TAG, "Silent Mode ON, skipping Gesture Stop");
            return;
        }
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.TAP_RECEIVED);
        postEventMessage(2);
    }

    void onGestureTogglePhoneNotificationFeature(String str) {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            boolean z = SettingsStorageModule.getInstance().toggleForwardNotificationToAccessory(str);
            String str2 = TAG;
            Log.i(str2, "onGestureTogglePhoneNotificationFeature: Phone Notifications enabled: " + z);
            FeatureToggleModule.getInstance().onToggleChanged();
            MetricsRecorder.getInstance().recordCounter(z ? MetricsConstants.FEATURE_ON_BY_GESTURE : MetricsConstants.FEATURE_OFF_BY_GESTURE, MetricsRecorder.customAttributesForDeviceType(str));
            postEventMessage(5);
            return;
        }
        onGestureTogglePhoneNotificationFeature();
    }

    void onGestureToggleSDM() {
        int currentDistractionMode = DistractionModeProvider.getCurrentDistractionMode();
        if (currentDistractionMode == 4) {
            Log.i(TAG, "User is in DND mode, can't toggle for SDM");
            return;
        }
        boolean z = currentDistractionMode != 3;
        String str = TAG;
        Log.i(str, "onGestureToggleSDM: SDM Mode enabled: " + z);
        DistractionModeProvider.setSilentDistractionMode(z);
        MetricsRecorder.getInstance().recordCounter(z ? MetricsConstants.GESTURE_SILENT_DISTRACTION_MODE_ON : MetricsConstants.GESTURE_SILENT_DISTRACTION_MODE_OFF);
        postEventMessage(4);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void processInputEvent(int r6, int r7, java.lang.String r8) {
        /*
            r5 = this;
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager.TAG
            java.lang.String r1 = "processInputEvent"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r0, r1)
            boolean r0 = com.google.common.base.Strings.isNullOrEmpty(r8)
            if (r0 != 0) goto Lbb
            java.lang.String r0 = "A3IYPH06PH1HRA"
            boolean r0 = r8.equalsIgnoreCase(r0)
            if (r0 != 0) goto L18
            goto Lbb
        L18:
            java.util.Locale r0 = java.util.Locale.US
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            r3 = 0
            r1[r3] = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)
            r4 = 1
            r1[r4] = r2
            r2 = 2
            r1[r2] = r8
            java.lang.String r8 = "InputSource: %d, InputAction: %d, DeviceTypeId: %s"
            java.lang.String r8 = java.lang.String.format(r0, r8, r1)
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "processInputEvent - "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r0, r8)
            com.amazon.alexa.accessory.protocol.Input$InputSource r6 = com.amazon.alexa.accessory.protocol.Input.InputSource.forNumber(r6)
            com.amazon.alexa.accessory.protocol.Input$InputAction r7 = com.amazon.alexa.accessory.protocol.Input.InputAction.forNumber(r7)
            com.amazon.alexa.accessory.protocol.Input$InputSource r8 = com.amazon.alexa.accessory.protocol.Input.InputSource.INPUT_SOURCE_TOUCHPAD
            if (r6 == r8) goto L60
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager.TAG
            java.lang.String r7 = "processInputEvent - Do not process, source is not touch pad"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r6, r7)
            return
        L60:
            com.amazon.alexa.accessory.protocol.Input$InputAction r6 = com.amazon.alexa.accessory.protocol.Input.InputAction.INPUT_ACTION_TAP
            if (r7 != r6) goto L6f
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager.TAG
            java.lang.String r8 = "processInputEvent - Calling stopAlexa"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r6, r8)
            com.amazon.alexa.accessory.notificationpublisher.utils.AlexaServiceHelper.stopAlexa()
        L6f:
            com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule r6 = com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule.getInstance()
            boolean r6 = r6.isFeatureEnabled()
            if (r6 != 0) goto L82
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager.TAG
            java.lang.String r7 = "processInputEvent - Ignoring input event because feature is disabled"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r6, r7)
            return
        L82:
            int r6 = r7.ordinal()
            if (r6 == r4) goto La2
            r7 = 7
            if (r6 == r7) goto L98
            r7 = 8
            if (r6 == r7) goto L98
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager.TAG
            java.lang.String r7 = "processInputEvent - Unhandled Input Event action."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r6, r7)
            goto Lac
        L98:
            boolean r6 = r5.shouldProcessGesture()
            if (r6 == 0) goto Lac
            r5.onGesturePlay()
            goto Lad
        La2:
            boolean r6 = r5.shouldProcessGesture()
            if (r6 == 0) goto Lac
            r5.onGestureStop()
            goto Lad
        Lac:
            r4 = r3
        Lad:
            if (r4 == 0) goto Lba
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager.TAG
            java.lang.String r7 = "processInputEvent - Gesture processed, update time for last gesture"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(r6, r7)
            r5.updateLastGestureTime()
        Lba:
            return
        Lbb:
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager.TAG
            java.lang.String r7 = "processInputEvent - Abort processing, device type ID is invalid"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.consumption.GestureManager.processInputEvent(int, int, java.lang.String):void");
    }

    @VisibleForTesting
    void processInputEventV2(int i, int i2, int i3, String str, String str2) {
        if (i3 == 24) {
            onGestureToggleSDM(str);
            updateLastGestureTime();
        } else if (i3 == 25) {
            onGestureTogglePhoneNotificationFeature(str);
            updateLastGestureTime();
        } else {
            List<Input.InputBehaviorConfiguration> configList = InputBehaviorConfigProvider.getConfigList(str2);
            if (configList == null) {
                if (str.equalsIgnoreCase("A3IYPH06PH1HRA")) {
                    processInputEvent(i, i2, str);
                    return;
                } else {
                    Log.w(TAG, "processInputEventV2 - Do not process, accessory doesn't have any vip filter positive/negative gesture.");
                    return;
                }
            }
            Input.InputBehavior inputBehavior = null;
            Iterator<Input.InputBehaviorConfiguration> it2 = configList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Input.InputBehaviorConfiguration next = it2.next();
                if (next.getAction().getNumber() == i2 && next.getSource().getNumber() == i) {
                    inputBehavior = next.getBehavior();
                    break;
                }
            }
            if (inputBehavior == null) {
                Log.w(TAG, "processInputEventV2 - Do not process, gesture is not mapped to any vip filter positive/negative gesture.");
            } else if (!FeatureToggleModule.getInstance().isFeatureEnabled()) {
                Log.i(TAG, "processInputEventV2 - Ignoring input event because feature is disabled");
            } else {
                boolean z = false;
                int ordinal = inputBehavior.ordinal();
                if (ordinal != 14) {
                    if (ordinal != 15) {
                        String str3 = TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("processInputEventV2 - Unsupported gesture: ");
                        outline107.append(inputBehavior.name());
                        Log.e(str3, outline107.toString());
                    } else {
                        Log.i(TAG, "processInputEventV2 - Calling stopAlexa");
                        AlexaServiceHelper.stopAlexa();
                        if (shouldProcessGesture()) {
                            onGestureStop();
                            z = true;
                        }
                    }
                } else if (shouldProcessGesture()) {
                    onGesturePlay();
                    z = true;
                }
                if (!z) {
                    return;
                }
                Log.i(TAG, "processInputEventV2 - Gesture processed, update time for last gesture");
                updateLastGestureTime();
            }
        }
    }

    /* renamed from: clone */
    public GestureManager m336clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }

    void onGestureToggleSDM(@Nullable String str) {
        try {
            if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess() && str != null) {
                if (DistractionModeProvider.getCurrentDistractionMode() == 4) {
                    Log.i(TAG, "User is in DND mode, can't toggle for SDM");
                    return;
                }
                boolean z = !SettingsStorageModule.getInstance().getSilentDistractionModeSetting(str).booleanValue();
                DistractionModeProvider.setSilentDistractionMode(str, z);
                MetricsRecorder.getInstance().recordCounter((z ? MetricsConstants.GESTURE_SILENT_DISTRACTION_MODE_ON : MetricsConstants.GESTURE_SILENT_DISTRACTION_MODE_OFF).concat(".").concat(str));
                postEventMessage(4);
                return;
            }
            onGestureToggleSDM();
        } catch (Exception e) {
            Log.i(TAG, GeneratedOutlineSupport1.outline72("Failed to get silent distraction mode setting with device ", str), e);
        }
    }

    void onGestureTogglePhoneNotificationFeature() {
        boolean z = !FeatureToggleModule.getInstance().isFeatureEnabled();
        Log.i(TAG, "onGestureTogglePhoneNotificationFeature: Phone Notifications enabled: " + z);
        FeatureToggleModule.getInstance().onToggleChanged(z);
        MetricsRecorder.getInstance().recordCounter(z ? MetricsConstants.FEATURE_ON_BY_GESTURE : MetricsConstants.FEATURE_OFF_BY_GESTURE);
        try {
            new StorageWrapper().put(SettingsStorageModule.FORWARD_NOTIFICATION_TO_ACCESSORY_KEY, Boolean.valueOf(z), SettingsStorageModule.FORWARD_NOTIFICATION_TO_ACCESSORY_KEY);
        } catch (Exception e) {
            if (e instanceof RxBlockingCallException) {
                MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_GestureManager_onGestureTogglePhoneNotificationFeature", MetricsRecorder.customAttributesForException(e));
            }
        }
        postEventMessage(5);
    }
}
