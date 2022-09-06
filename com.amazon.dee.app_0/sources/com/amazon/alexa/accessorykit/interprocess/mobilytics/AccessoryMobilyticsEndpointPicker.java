package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import android.content.Context;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.interprocess.environment.AccessoryEnvironmentServicePreferences;
import com.amazon.alexa.mobilytics.identity.MobilyticsEndpointPicker;
import java.util.Map;
/* loaded from: classes6.dex */
public class AccessoryMobilyticsEndpointPicker implements MobilyticsEndpointPicker {
    private final Context context;

    public AccessoryMobilyticsEndpointPicker(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsEndpointPicker
    public <T> T getEndpoint(Map<String, T> map) {
        String aWSRegion = AccessoryEnvironmentServicePreferences.getInstance(this.context).getAWSRegion();
        if (map.containsKey(aWSRegion)) {
            return map.get(aWSRegion);
        }
        return null;
    }
}
