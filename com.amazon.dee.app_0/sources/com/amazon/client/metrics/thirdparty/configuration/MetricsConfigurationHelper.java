package com.amazon.client.metrics.thirdparty.configuration;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class MetricsConfigurationHelper {
    private final Context mContext;

    public MetricsConfigurationHelper(Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        throw new IllegalArgumentException("Context may not be null");
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    return sb.toString();
                }
            }
        } finally {
            bufferedReader.close();
        }
    }

    private JSONObject getMetricsConfigurationJSONObject(InputStream inputStream) throws MetricsConfigurationException {
        try {
            return new JSONObject(convertInputStreamToString(inputStream));
        } catch (IOException e) {
            throw new MetricsConfigurationException("IO Exception while parsing the Metrics Configuration", e);
        } catch (JSONException e2) {
            throw new MetricsConfigurationException("JSON Exception while parsing Metrics Configuration", e2);
        }
    }

    public MetricsConfiguration initializeMetricsConfiguration(MetricsConfigurationParser metricsConfigurationParser) throws MetricsConfigurationException {
        try {
            return metricsConfigurationParser.parseConfiguration(getMetricsConfigurationJSONObject(this.mContext.getAssets().open(MetricsConfigurationConstants.CONFIGURATION_ASSET_FILE_PATH)), isDebugFlagEnabled());
        } catch (IOException e) {
            throw new MetricsConfigurationException("An IOException was thrown loading the metrics configuration", e);
        }
    }

    protected boolean isDebugFlagEnabled() {
        return (this.mContext.getApplicationInfo().flags & 2) != 0;
    }
}
