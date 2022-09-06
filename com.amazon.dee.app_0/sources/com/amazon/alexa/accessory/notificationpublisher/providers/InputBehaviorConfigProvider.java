package com.amazon.alexa.accessory.notificationpublisher.providers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.protocol.Input;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public final class InputBehaviorConfigProvider {
    private static final String TAG = "InputBehaviorConfigProvider";
    @VisibleForTesting
    static Map<String, List<Input.InputBehaviorConfiguration>> configListMap = new ConcurrentHashMap();

    private InputBehaviorConfigProvider() {
        throw new UnsupportedOperationException("Instantiation of this class is not supported.");
    }

    @Nullable
    public static synchronized List<Input.InputBehaviorConfiguration> getConfigList(@NonNull String str) {
        synchronized (InputBehaviorConfigProvider.class) {
            if (configListMap.containsKey(str.toUpperCase())) {
                return configListMap.get(str.toUpperCase());
            }
            return null;
        }
    }

    public static synchronized void updateInputBehaviorConfig(@NonNull String str, boolean z, List<Input.InputBehaviorConfiguration> list) {
        synchronized (InputBehaviorConfigProvider.class) {
            String str2 = TAG;
            Log.d(str2, "updateInputBehaviorConfig - acccessory address: " + str + ", connected: " + z);
            if (!z) {
                Log.d(TAG, "updateInputBehaviorConfig - accessoryDisconnect, removing entry.");
                configListMap.remove(str);
                return;
            }
            if (list != null && list.size() != 0) {
                ArrayList arrayList = new ArrayList();
                for (Input.InputBehaviorConfiguration inputBehaviorConfiguration : list) {
                    if (inputBehaviorConfiguration.getBehavior() != Input.InputBehavior.UNRECOGNIZED && (inputBehaviorConfiguration.getBehavior().getNumber() == 22 || inputBehaviorConfiguration.getBehavior().getNumber() == 23)) {
                        arrayList.add(inputBehaviorConfiguration);
                    }
                }
                if (arrayList.size() > 0) {
                    Log.d(TAG, "Storing VIP Filter positive/negative config.");
                    configListMap.put(str.toUpperCase(), arrayList);
                } else {
                    Log.i(TAG, "No valid VIP Filter positive/negative config found to store.");
                }
                return;
            }
            Log.e(TAG, "updateInputBehaviorConfig - configList is empty.");
        }
    }
}
