package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.NetworkDetailsCollector;
import com.amazon.alexa.comms.mediaInsights.service.factories.NetworkDetailsCollectorFactory;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class NetworkDetailsFactory {
    @NonNull
    private Context appContext;

    public NetworkDetailsFactory(@NonNull Context context) {
        if (context != null) {
            this.appContext = context;
            return;
        }
        throw new IllegalArgumentException("appContext is null");
    }

    public Map<String, String> create() {
        HashMap hashMap = new HashMap();
        try {
            SharedPreferences sharedPreferences = this.appContext.getSharedPreferences(NetworkDetailsCollectorFactory.NETOWRK_DETAILS_SHARED_PREF_FILE_NAME, 0);
            for (NetworkDetailsCollector.NetworkDetailKeys networkDetailKeys : (NetworkDetailsCollector.NetworkDetailKeys[]) NetworkDetailsCollector.NetworkDetailKeys.class.getEnumConstants()) {
                String str = networkDetailKeys.toString();
                String string = sharedPreferences.getString(str, null);
                if (string != null) {
                    hashMap.put(str, string);
                }
            }
        } catch (Throwable unused) {
        }
        return hashMap;
    }
}
