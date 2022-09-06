package com.amazon.alexa.sendtoapp.activitycard;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.applink.metrics.MobilyticsMetricsRecorder;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.sendtoapp.activitycard.adapters.AutoValueAdapterFactory;
import com.amazon.alexa.sendtoapp.activitycard.model.Card;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.provider.ViewProvider;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class SendToAppViewProvider implements ViewProvider {
    private static final String CUSTOM_TEMPLATE_ROUTE = "customTemplateRoute";
    private static final String SEND_TO_APP_CUSTOM_TEMPLATE_ROUTE = "sendToApp";
    private static final String SUPPORTED_PAYLOAD_VERSION = "1.0";
    private static final String TAG = "SendToAppViewProvider";
    private final Context context;
    private final Gson gson = new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create();
    private final Optional<EnvironmentService> environmentService = ComponentRegistry.getInstance().get(EnvironmentService.class);

    public SendToAppViewProvider(Context context) {
        this.context = context;
    }

    @Override // com.amazon.alexa.viewprovider.api.provider.ViewProvider
    public ViewModule createView(JSONObject jSONObject, EventCapture eventCapture) {
        String.format("JSON object: %s", jSONObject);
        if (!isSupported(jSONObject)) {
            Log.w(TAG, "createView called for unsupported payload");
            return null;
        }
        Card card = deserializeCardJSON(jSONObject.toString()).get();
        MobilyticsMetricsRecorder.recordCounter(SendToAppCardMetricsConstants.COMPONENT_NAME, SendToAppCardMetricsConstants.SUBCOMPONENT_NAME, "Received", 1, card.getCustomData().getMetricId());
        return new SendToAppViewModule(card, eventCapture, this.context);
    }

    @VisibleForTesting
    Optional<Card> deserializeCardJSON(String str) {
        try {
            return Optional.of((Card) this.gson.fromJson(str, (Class<Object>) Card.class));
        } catch (JsonSyntaxException e) {
            Log.w(TAG, "Failed to parse JSON", e);
            return Optional.absent();
        } catch (Exception e2) {
            Log.w(TAG, "Failed to parse JSON, probably missing required properties", e2);
            return Optional.absent();
        }
    }

    @Override // com.amazon.alexa.viewprovider.api.provider.ViewProvider
    public boolean isSupported(JSONObject jSONObject) {
        String.format("JSON object: %s", jSONObject);
        if (!this.environmentService.isPresent()) {
            Log.e(TAG, "EnvironmentService is not present, cannot get device information");
            return false;
        }
        DeviceInformation deviceInformation = this.environmentService.get().getDeviceInformation();
        if (deviceInformation == null) {
            Log.e(TAG, "Device information is null, cannot get FireOS status");
            return false;
        } else if (deviceInformation.isFireOS()) {
            Log.i(TAG, "Device is FireOS, S2A cards not supported.");
            return false;
        } else if (!jSONObject.optString(CUSTOM_TEMPLATE_ROUTE).equals(SEND_TO_APP_CUSTOM_TEMPLATE_ROUTE)) {
            return false;
        } else {
            Optional<Card> deserializeCardJSON = deserializeCardJSON(jSONObject.toString());
            if (deserializeCardJSON.isPresent()) {
                return deserializeCardJSON.get().getCustomData().getVersion().equals("1.0");
            }
            return false;
        }
    }
}
