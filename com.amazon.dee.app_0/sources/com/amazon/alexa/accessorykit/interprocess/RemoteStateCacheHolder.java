package com.amazon.alexa.accessorykit.interprocess;

import android.content.Context;
import com.amazon.alexa.accessory.persistence.FileBackedJsonRxMapStore;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessorykit.interprocess.PersistedState;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* compiled from: InterprocessPhoneStatePlugin.java */
/* loaded from: classes6.dex */
final class RemoteStateCacheHolder {
    private static final String STATE_CACHE_STORE_PATH = "accessoriesState/ipcStateCache_%d.json";
    private static Map<Integer, RxMapStore<String, PersistedState>> remoteStateCaches;

    private RemoteStateCacheHolder() {
    }

    public static synchronized RxMapStore<String, PersistedState> getRemoteStateCache(Context context, StateFeature stateFeature) {
        RxMapStore<String, PersistedState> rxMapStore;
        synchronized (RemoteStateCacheHolder.class) {
            if (remoteStateCaches == null) {
                remoteStateCaches = new HashMap();
            }
            rxMapStore = remoteStateCaches.get(Integer.valueOf(stateFeature.toInteger()));
            if (rxMapStore == null) {
                File file = new File(context.getFilesDir(), String.format(Locale.US, STATE_CACHE_STORE_PATH, Integer.valueOf(stateFeature.toInteger())));
                PersistedState.Factory factory = PersistedState.Factory.INSTANCE;
                rxMapStore = new FileBackedJsonRxMapStore<>(file, factory, "ipcStateCache_" + stateFeature.toInteger(), "states", "state");
                remoteStateCaches.put(Integer.valueOf(stateFeature.toInteger()), rxMapStore);
            }
        }
        return rxMapStore;
    }
}
