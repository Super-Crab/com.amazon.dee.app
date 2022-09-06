package com.amazon.alexa.accessory.notificationpublisher.utils;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.R;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.InputBehaviorConfigProvider;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class InputConfigGestureStringBuilder {
    private static final String TAG = "InputConfigGestureStringBuilder";

    /* renamed from: com.amazon.alexa.accessory.notificationpublisher.utils.InputConfigGestureStringBuilder$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputSource = new int[Input.InputSource.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputSource[Input.InputSource.INPUT_SOURCE_TOUCHPAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputSource[Input.InputSource.INPUT_SOURCE_VOLUME_UP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputSource[Input.InputSource.INPUT_SOURCE_VOLUME_DOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputSource[Input.InputSource.INPUT_SOURCE_ACTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction = new int[Input.InputAction.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_TAP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_DOUBLE_TAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_TAP_HOLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_PRESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_LONG_PRESS.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_RELEASE.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_FORWARD.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_BACK.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_UP.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_DOWN.ordinal()] = 10;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_LEFT.ordinal()] = 11;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_SWIPE_RIGHT.ordinal()] = 12;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Input$InputAction[Input.InputAction.INPUT_ACTION_TRIPLE_TAP.ordinal()] = 13;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class InputConfigGestureText {
        private String action;
        private String source;

        InputConfigGestureText(String str, String str2) {
            this.action = str;
            this.source = str2;
        }

        public String getAction() {
            return this.action;
        }

        public String getSource() {
            return this.source;
        }
    }

    private InputConfigGestureStringBuilder() {
        throw new UnsupportedOperationException("Instantiation of this class is not supported.");
    }

    public static String getAccessoryNegativeActionString() {
        String address = AccessoryProvider.getAccessory().getAddress();
        if (address == null) {
            return null;
        }
        return getNegativeGestureActionSource(InputBehaviorConfigProvider.getConfigList(address)).getAction();
    }

    public static String getAccessoryPositiveActionString() {
        String address = AccessoryProvider.getAccessory().getAddress();
        if (address == null) {
            return null;
        }
        return getPositiveGestureActionSource(InputBehaviorConfigProvider.getConfigList(address)).getAction();
    }

    @VisibleForTesting
    static String getActionString(Input.InputAction inputAction) {
        Context context = DependencyProvider.getContext();
        switch (inputAction.ordinal()) {
            case 1:
                return context.getString(R.string.tap);
            case 2:
                return context.getString(R.string.double_tap);
            case 3:
                return context.getString(R.string.tap_and_hold);
            case 4:
                return context.getString(R.string.press);
            case 5:
                return context.getString(R.string.long_press);
            case 6:
                return context.getString(R.string.release);
            case 7:
                return context.getString(R.string.swipe);
            case 8:
                return context.getString(R.string.swipe_back);
            case 9:
                return context.getString(R.string.swipe_up);
            case 10:
                return context.getString(R.string.swipe_down);
            case 11:
                return context.getString(R.string.swipe_left);
            case 12:
                return context.getString(R.string.swipe_right);
            case 13:
                return context.getString(R.string.triple_tap);
            default:
                String string = context.getString(R.string.use_accessory);
                String str = TAG;
                Log.i(str, "Unmapped action sent for getActionString as: " + inputAction);
                return string;
        }
    }

    public static InputConfigGestureText getNegativeGestureActionSource(List<Input.InputBehaviorConfiguration> list) {
        Input.InputBehaviorConfiguration inputBehaviorConfiguration;
        Preconditions.notNull(list, "configList is empty.");
        Iterator<Input.InputBehaviorConfiguration> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                inputBehaviorConfiguration = null;
                break;
            }
            inputBehaviorConfiguration = it2.next();
            if (inputBehaviorConfiguration.getBehavior().getNumber() == 23) {
                break;
            }
        }
        if (inputBehaviorConfiguration == null) {
            return null;
        }
        return new InputConfigGestureText(getActionString(inputBehaviorConfiguration.getAction()), getSourceString(inputBehaviorConfiguration.getSource()));
    }

    public static InputConfigGestureText getPositiveGestureActionSource(List<Input.InputBehaviorConfiguration> list) {
        Input.InputBehaviorConfiguration inputBehaviorConfiguration;
        Preconditions.notNull(list, "configList is empty.");
        Iterator<Input.InputBehaviorConfiguration> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                inputBehaviorConfiguration = null;
                break;
            }
            inputBehaviorConfiguration = it2.next();
            if (inputBehaviorConfiguration.getBehavior().getNumber() == 22) {
                break;
            }
        }
        if (inputBehaviorConfiguration == null) {
            return null;
        }
        return new InputConfigGestureText(getActionString(inputBehaviorConfiguration.getAction()), getSourceString(inputBehaviorConfiguration.getSource()));
    }

    @VisibleForTesting
    static String getSourceString(Input.InputSource inputSource) {
        Context context = DependencyProvider.getContext();
        int ordinal = inputSource.ordinal();
        if (ordinal != 1) {
            if (ordinal == 2) {
                return context.getString(R.string.volume_up_button);
            }
            if (ordinal == 3) {
                return context.getString(R.string.volume_down_button);
            }
            if (ordinal != 4) {
                String str = TAG;
                Log.e(str, "Unmapped action sent for getSourceString as: " + inputSource);
                return null;
            }
            return context.getString(R.string.action_button);
        }
        return context.getString(R.string.touchpad);
    }

    public static List<Input.InputBehaviorConfiguration> getVoiceConfigList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Input.InputBehaviorConfiguration.newBuilder().setAction(Input.InputAction.INPUT_ACTION_OTHER).setSource(Input.InputSource.INPUT_SOURCE_OTHER).setBehavior(Input.InputBehavior.INPUT_BEHAVIOR_VIP_FILTER_POSITIVE_RESPONSE).mo10084build());
        arrayList.add(Input.InputBehaviorConfiguration.newBuilder().setAction(Input.InputAction.INPUT_ACTION_OTHER).setSource(Input.InputSource.INPUT_SOURCE_OTHER).setBehavior(Input.InputBehavior.INPUT_BEHAVIOR_VIP_FILTER_NEGATIVE_RESPONSE).mo10084build());
        return arrayList;
    }

    public static List<Input.InputBehaviorConfiguration> getZionConfigList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Input.InputBehaviorConfiguration.newBuilder().setAction(Input.InputAction.INPUT_ACTION_SWIPE_FORWARD).setSource(Input.InputSource.INPUT_SOURCE_TOUCHPAD).setBehavior(Input.InputBehavior.INPUT_BEHAVIOR_VIP_FILTER_POSITIVE_RESPONSE).mo10084build());
        arrayList.add(Input.InputBehaviorConfiguration.newBuilder().setAction(Input.InputAction.INPUT_ACTION_SWIPE_BACK).setSource(Input.InputSource.INPUT_SOURCE_TOUCHPAD).setBehavior(Input.InputBehavior.INPUT_BEHAVIOR_VIP_FILTER_POSITIVE_RESPONSE).mo10084build());
        arrayList.add(Input.InputBehaviorConfiguration.newBuilder().setAction(Input.InputAction.INPUT_ACTION_TAP).setSource(Input.InputSource.INPUT_SOURCE_TOUCHPAD).setBehavior(Input.InputBehavior.INPUT_BEHAVIOR_VIP_FILTER_NEGATIVE_RESPONSE).mo10084build());
        return arrayList;
    }
}
