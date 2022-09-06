package com.amazon.alexa.accessory.notificationpublisher.utils;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import java.util.Properties;
/* loaded from: classes.dex */
public class AvsProperties {
    private static final String AVS_PROPERTIES_FILE = "avs.properties";
    private static final String AVS_PROPERTIES_KEY_AVS_ENDPOINT = "avs_endpoint";
    private static final String TAG = "AvsProperties";
    private String aveEndpoint;

    public AvsProperties(Context context) {
        this(context, AVS_PROPERTIES_FILE);
    }

    private void readProperties(Properties properties) {
        this.aveEndpoint = properties.getProperty(AVS_PROPERTIES_KEY_AVS_ENDPOINT);
        if (this.aveEndpoint != null) {
            return;
        }
        throw new IllegalArgumentException(String.format("Unable to read property %s", AVS_PROPERTIES_KEY_AVS_ENDPOINT));
    }

    public String getAveEndpoint() {
        return this.aveEndpoint;
    }

    @VisibleForTesting
    AvsProperties(Context context, String str) {
        try {
            Properties properties = new Properties();
            properties.load(context.getAssets().open(str));
            readProperties(properties);
        } catch (IOException e) {
            Log.e(TAG, String.format("Fatal exception while reading file %s: ", str), e);
            throw new RuntimeException(e);
        }
    }
}
