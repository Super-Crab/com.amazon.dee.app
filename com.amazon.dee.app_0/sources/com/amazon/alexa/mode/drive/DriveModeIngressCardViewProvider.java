package com.amazon.alexa.mode.drive;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.provider.ViewProvider;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class DriveModeIngressCardViewProvider implements ViewProvider {
    private static final String TAG = "DriveModeIngressCardViewProvider";
    private final Context context;

    public DriveModeIngressCardViewProvider(@NonNull Context context) {
        this.context = context;
    }

    private Optional<Card> parseCardJson(@NonNull String str) {
        try {
            return Optional.of((Card) new GsonBuilder().create().fromJson(str, (Class<Object>) Card.class));
        } catch (JsonSyntaxException e) {
            String str2 = TAG;
            Log.w(str2, "Failed to parse card json. Json: " + str + " Error: " + e);
            return Optional.absent();
        }
    }

    @Override // com.amazon.alexa.viewprovider.api.provider.ViewProvider
    public ViewModule createView(@NonNull JSONObject jSONObject, EventCapture eventCapture) {
        if (!isSupported(jSONObject)) {
            String str = TAG;
            Log.w(str, "createView invoked for non supported metadata: " + jSONObject);
            return null;
        }
        return new DriveModeIngressViewModule(this.context, eventCapture);
    }

    @Override // com.amazon.alexa.viewprovider.api.provider.ViewProvider
    public boolean isSupported(@NonNull JSONObject jSONObject) {
        Optional<Card> parseCardJson = parseCardJson(jSONObject.toString());
        if (!parseCardJson.isPresent()) {
            return false;
        }
        return Objects.equal(parseCardJson.get().getCustomTemplateRoute(), Constants.HOME_CHANNEL_DRIVE_MODE_INGRESS_CUSTOM_ROUTE);
    }
}
