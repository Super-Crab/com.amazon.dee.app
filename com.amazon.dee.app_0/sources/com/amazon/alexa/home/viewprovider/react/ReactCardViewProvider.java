package com.amazon.alexa.home.viewprovider.react;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.event.personalization.InteractionEventData;
import com.amazon.alexa.viewprovider.api.provider.ViewProvider;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.ReactInstanceManager;
import com.google.common.base.Optional;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.util.UUID;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class ReactCardViewProvider implements ViewProvider {
    public static final String REACT_CARD_CLICK = "domain:card:click";
    public static final String REACT_CARD_VIEW_PROVIDER = "rn";
    private final BridgeStatusService bridgeStatusService;
    private final Context context;
    private final EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    private EventCapture eventCapture;
    private final ReactInstanceManager reactInstanceManager;

    public ReactCardViewProvider(Context context, ReactInstanceManager reactInstanceManager, BridgeStatusService bridgeStatusService) {
        this.context = context;
        this.reactInstanceManager = reactInstanceManager;
        this.bridgeStatusService = bridgeStatusService;
        subscribeToReactCardClickEvent();
    }

    private String[] getSubRoutes(JSONObject jSONObject) {
        return jSONObject.optString("customTemplateRoute", "").split("/");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Optional<InteractionEventData> parseInteractionEventDataJson(String str) {
        try {
            return Optional.of((InteractionEventData) new GsonBuilder().create().fromJson(str, (Class<Object>) InteractionEventData.class));
        } catch (JsonSyntaxException unused) {
            return Optional.absent();
        }
    }

    private void subscribeToReactCardClickEvent() {
        this.eventBus.subscribe(new Subscriber() { // from class: com.amazon.alexa.home.viewprovider.react.ReactCardViewProvider.1
            @Override // com.amazon.alexa.eventbus.api.Subscriber
            public UUID getUUID() {
                return UUID.randomUUID();
            }

            @Override // com.amazon.alexa.eventbus.api.Subscriber
            public void onMessageReceived(@NonNull Message message) {
                Optional parseInteractionEventDataJson = ReactCardViewProvider.this.parseInteractionEventDataJson(message.getPayloadAsString());
                if (parseInteractionEventDataJson.isPresent() && ReactCardViewProvider.this.eventCapture != null) {
                    ReactCardViewProvider.this.eventCapture.captureClick((InteractionEventData) parseInteractionEventDataJson.get());
                }
            }

            @Override // com.amazon.alexa.eventbus.api.Subscriber
            public boolean supportsMessage(@NonNull Message message) {
                return ReactCardViewProvider.REACT_CARD_CLICK.equals(message.getEventType());
            }
        });
    }

    @Override // com.amazon.alexa.viewprovider.api.provider.ViewProvider
    @Nullable
    public ViewModule createView(@NonNull JSONObject jSONObject, EventCapture eventCapture) {
        if (!isSupported(jSONObject)) {
            return null;
        }
        String str = getSubRoutes(jSONObject)[1];
        this.eventCapture = eventCapture;
        return new ReactViewModule(str, jSONObject, this.context, this.reactInstanceManager, this.bridgeStatusService, this.eventBus);
    }

    @Override // com.amazon.alexa.viewprovider.api.provider.ViewProvider
    public boolean isSupported(@NonNull JSONObject jSONObject) {
        String[] subRoutes = getSubRoutes(jSONObject);
        return subRoutes[0].equals("rn") && subRoutes.length > 1;
    }
}
