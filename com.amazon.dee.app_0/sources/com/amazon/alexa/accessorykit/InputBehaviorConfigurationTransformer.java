package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.protocol.Input;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.Map;
/* loaded from: classes6.dex */
public final class InputBehaviorConfigurationTransformer {
    public static final String KEY_ACTION_VALUE = "action";
    public static final String KEY_BEHAVIOR_VALUE = "behavior";
    public static final String KEY_SOURCE_VALUE = "source";

    private InputBehaviorConfigurationTransformer() {
        throw new IllegalStateException("No instances!");
    }

    public static Input.InputBehaviorConfiguration configFromReadable(ReadableMap readableMap) {
        return Input.InputBehaviorConfiguration.newBuilder().setActionValue(readableMap.getInt("action")).setBehaviorValue(readableMap.getInt(KEY_BEHAVIOR_VALUE)).setSourceValue(readableMap.getInt("source")).mo10084build();
    }

    public static ReadableMap nativeSetMapToReadable(Map<Integer, Input.InputBehaviorConfigurationSet> map) {
        return nativeSetMapToReadable(map, new NativeContainerProvider());
    }

    public static ReadableMap nativeSetMapToReadable(Map<Integer, Input.InputBehaviorConfigurationSet> map, ContainerProvider containerProvider) {
        WritableMap map2 = containerProvider.getMap();
        for (Integer num : map.keySet()) {
            int intValue = num.intValue();
            WritableArray array = containerProvider.getArray();
            for (Input.InputBehaviorConfiguration inputBehaviorConfiguration : map.get(Integer.valueOf(intValue)).getConfigurationsList()) {
                WritableMap map3 = containerProvider.getMap();
                map3.putInt("action", inputBehaviorConfiguration.getActionValue());
                map3.putInt(KEY_BEHAVIOR_VALUE, inputBehaviorConfiguration.getBehaviorValue());
                map3.putInt("source", inputBehaviorConfiguration.getSourceValue());
                array.pushMap(map3);
            }
            map2.putArray(String.valueOf(intValue), array);
        }
        return map2;
    }
}
