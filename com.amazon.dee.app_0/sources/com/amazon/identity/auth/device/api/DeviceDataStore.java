package com.amazon.identity.auth.device.api;

import android.content.Context;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.cf;
import com.amazon.identity.auth.device.ch;
import com.amazon.identity.auth.device.ci;
import com.amazon.identity.auth.device.cj;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.io;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class DeviceDataStore {
    private static final String TAG = "com.amazon.identity.auth.device.api.DeviceDataStore";
    private static DeviceDataStore fZ;
    private final ch ga;
    private final ci gb;

    DeviceDataStore(Context context) {
        MAPInit.getInstance(context).initialize();
        this.ga = ch.bP();
        this.gb = cj.w(context);
    }

    @FireOsSdk
    public static void generateNewInstance(Context context) {
        fZ = new DeviceDataStore(context.getApplicationContext());
    }

    @FireOsSdk
    public static synchronized DeviceDataStore getInstance(Context context) {
        DeviceDataStore deviceDataStore;
        synchronized (DeviceDataStore.class) {
            if (fZ == null) {
                generateNewInstance(context);
            }
            deviceDataStore = fZ;
        }
        return deviceDataStore;
    }

    public void clearCache() {
        this.ga.P();
    }

    @FireOsSdk
    public String getValue(String str) throws DeviceDataStoreException {
        if (str != null) {
            if (this.ga.containsKey(str)) {
                return this.ga.get(str);
            }
            ej by = ej.by("DeviceDataStore:getValue");
            try {
                cf aP = this.gb.aP(str);
                if (aP != null) {
                    String str2 = aP.value;
                    if (str2 != null) {
                        if (aP.id) {
                            this.ga.put(str, str2);
                        }
                    } else {
                        by.bA(str + ":Null");
                        io.a(TAG, "Getting null value for key %s ", str);
                    }
                    return str2;
                }
                String format = String.format("Key %s was not found in the device data store", str);
                io.w(TAG, format);
                throw new DeviceDataStoreException(format);
            } finally {
                by.eb();
            }
        }
        String format2 = String.format("Key passed in is null", new Object[0]);
        io.w(TAG, format2);
        throw new DeviceDataStoreException(format2);
    }
}
