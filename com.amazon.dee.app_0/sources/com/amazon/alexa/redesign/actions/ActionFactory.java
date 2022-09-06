package com.amazon.alexa.redesign.actions;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.entity.ViewModelFactory;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class ActionFactory {
    public static final String API_REQUEST_ACTION = "ApiRequestAction";
    public static final String BOTTOM_SHEET_ACTION = "BottomSheetAction";
    public static final String DEEPLINK_ACTION = "DeepLinkAction";
    public static final String DEVICE_PICKER_ACTION = "DevicePickerAction";
    public static final String DISMISS_ACTION = "DismissCardAction";
    public static final String LINKACCOUNT_ACTION = "LinkAccountAction";
    public static final String NAVIGATE_ACTION = "NavigateAction";
    public static final String OPENURL_ACTION = "OpenUrlAction";
    public static final String SETTINGS_ACTION = "SettingsAction";
    private static final Map<String, Factory<Action>> actionMap = new HashMap();
    private final Context context;
    private final EventBus eventBus;
    private final HomeFeedServiceClient homeFeedServiceClient;
    private final HomeOEInteractor homeOEInteractor;
    private final IdentityService identityService;
    private final RoutingService routingService;
    private final UserMessageService userMessageService;

    /* loaded from: classes10.dex */
    public static class Builder {
        private Context context;
        private EventBus eventBus;
        private HomeFeedServiceClient homeFeedServiceClient;
        private HomeOEInteractor homeOEInteractor;
        private IdentityService identityService;
        private RoutingService routingService;
        private UserMessageService userMessageService;

        public ActionFactory build() {
            return new ActionFactory(this);
        }

        public Builder withContext(@NonNull Context context) {
            this.context = context;
            return this;
        }

        public Builder withEventBus(@NonNull EventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }

        public Builder withHomeOEInteractor(@NonNull HomeOEInteractor homeOEInteractor) {
            this.homeOEInteractor = homeOEInteractor;
            return this;
        }

        public Builder withIdentityService(@NonNull IdentityService identityService) {
            this.identityService = identityService;
            return this;
        }

        public Builder withNetworkClient(@NonNull HomeFeedServiceClient homeFeedServiceClient) {
            this.homeFeedServiceClient = homeFeedServiceClient;
            return this;
        }

        public Builder withRoutingService(@NonNull RoutingService routingService) {
            this.routingService = routingService;
            return this;
        }

        public Builder withUserMessageService(@NonNull UserMessageService userMessageService) {
            this.userMessageService = userMessageService;
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public interface Factory<ReturnType> {
        ReturnType create(JSONObject jSONObject) throws IllegalArgumentException;
    }

    ActionFactory(Builder builder) {
        this.routingService = builder.routingService;
        this.eventBus = builder.eventBus;
        this.homeOEInteractor = builder.homeOEInteractor;
        this.userMessageService = builder.userMessageService;
        this.context = builder.context;
        this.homeFeedServiceClient = builder.homeFeedServiceClient;
        this.identityService = builder.identityService;
        final ViewModelFactory viewModelFactory = new ViewModelFactory(this);
        actionMap.put("NavigateAction", new Factory() { // from class: com.amazon.alexa.redesign.actions.-$$Lambda$ActionFactory$ycfKCYJbrMVa8AcmFFKZ1jV5vis
            @Override // com.amazon.alexa.redesign.actions.ActionFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ActionFactory.this.lambda$new$0$ActionFactory(jSONObject);
            }
        });
        actionMap.put(DEVICE_PICKER_ACTION, new Factory() { // from class: com.amazon.alexa.redesign.actions.-$$Lambda$ActionFactory$Idp1bgMjJ03HZWms33RxZvM5TWM
            @Override // com.amazon.alexa.redesign.actions.ActionFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ActionFactory.this.lambda$new$1$ActionFactory(jSONObject);
            }
        });
        actionMap.put(OPENURL_ACTION, new Factory() { // from class: com.amazon.alexa.redesign.actions.-$$Lambda$ActionFactory$1y_nvBO88q2TL2jJlfmDFSLkH1U
            @Override // com.amazon.alexa.redesign.actions.ActionFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ActionFactory.this.lambda$new$2$ActionFactory(jSONObject);
            }
        });
        actionMap.put(LINKACCOUNT_ACTION, new Factory() { // from class: com.amazon.alexa.redesign.actions.-$$Lambda$ActionFactory$OLQy-U3tfbEWzkMq2Rf7LR7d1rM
            @Override // com.amazon.alexa.redesign.actions.ActionFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ActionFactory.this.lambda$new$3$ActionFactory(jSONObject);
            }
        });
        actionMap.put(DEEPLINK_ACTION, new Factory() { // from class: com.amazon.alexa.redesign.actions.-$$Lambda$ActionFactory$jpZ0zzIrHexsaejJ5A1nG3WZ9q8
            @Override // com.amazon.alexa.redesign.actions.ActionFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ActionFactory.this.lambda$new$4$ActionFactory(jSONObject);
            }
        });
        actionMap.put(SETTINGS_ACTION, new Factory() { // from class: com.amazon.alexa.redesign.actions.-$$Lambda$ActionFactory$XncjVGgbi0oTPDUnsBibZIbvtrs
            @Override // com.amazon.alexa.redesign.actions.ActionFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ActionFactory.this.lambda$new$5$ActionFactory(jSONObject);
            }
        });
        actionMap.put(API_REQUEST_ACTION, new Factory() { // from class: com.amazon.alexa.redesign.actions.-$$Lambda$ActionFactory$XNg1XmsUZEZ0TJgdZxiSQGOZaC8
            @Override // com.amazon.alexa.redesign.actions.ActionFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ActionFactory.this.lambda$new$6$ActionFactory(jSONObject);
            }
        });
        actionMap.put(BOTTOM_SHEET_ACTION, new Factory() { // from class: com.amazon.alexa.redesign.actions.-$$Lambda$ActionFactory$4eaBsvz9zwUsxsRfnjZ0YMTADxg
            @Override // com.amazon.alexa.redesign.actions.ActionFactory.Factory
            public final Object create(JSONObject jSONObject) {
                return ActionFactory.lambda$new$7(ViewModelFactory.this, jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Action lambda$new$7(ViewModelFactory viewModelFactory, JSONObject jSONObject) throws IllegalArgumentException {
        return new BottomSheetAction(ActionFactoryUtil.getActionMetaTypeForMetric(jSONObject), ActionFactoryUtil.getAccessibilityLabel(jSONObject), ActionFactoryUtil.getBottomSheetModel(jSONObject, viewModelFactory));
    }

    @Nullable
    public Action getAction(@Nullable JSONObject jSONObject) throws IllegalArgumentException {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE);
        if (!optString.equals("")) {
            Factory<Action> factory = actionMap.get(optString);
            if (factory != null) {
                return factory.create(jSONObject);
            }
            throw new IllegalArgumentException("HomeChannelNative: Action type not found in action map.");
        }
        throw new IllegalArgumentException("HomeChannelNative: Action type not in json payload.");
    }

    public /* synthetic */ Action lambda$new$0$ActionFactory(JSONObject jSONObject) throws IllegalArgumentException {
        return new NavigateAction(ActionFactoryUtil.getRouteFromJson(jSONObject), this.routingService, ActionFactoryUtil.getActionMetaTypeForMetric(jSONObject), ActionFactoryUtil.getAccessibilityLabel(jSONObject), this.homeOEInteractor);
    }

    public /* synthetic */ Action lambda$new$1$ActionFactory(JSONObject jSONObject) throws IllegalArgumentException {
        return new DevicePickerAction(ActionFactoryUtil.getDevicePickerMessageFromJson(jSONObject), ActionFactoryUtil.getHomeDevicePickerMessageFromJson(jSONObject), this.eventBus, this.routingService, ActionFactoryUtil.getActionMetaTypeForMetric(jSONObject), ActionFactoryUtil.getAccessibilityLabel(jSONObject));
    }

    public /* synthetic */ Action lambda$new$2$ActionFactory(JSONObject jSONObject) throws IllegalArgumentException {
        return new EventBusAction(ActionFactoryUtil.getOpenUrlMessageFromJson(jSONObject), this.eventBus, ActionFactoryUtil.getActionMetaTypeForMetric(jSONObject), ActionFactoryUtil.getAccessibilityLabel(jSONObject));
    }

    public /* synthetic */ Action lambda$new$3$ActionFactory(JSONObject jSONObject) throws IllegalArgumentException {
        return new EventBusAction(ActionFactoryUtil.getLinkAccountMessageFromJson(jSONObject), this.eventBus, ActionFactoryUtil.getActionMetaTypeForMetric(jSONObject), ActionFactoryUtil.getAccessibilityLabel(jSONObject));
    }

    public /* synthetic */ Action lambda$new$4$ActionFactory(JSONObject jSONObject) throws IllegalArgumentException {
        return new DeepLinkAction(ActionFactoryUtil.getActionMetaTypeForMetric(jSONObject), ActionFactoryUtil.getAccessibilityLabel(jSONObject), ActionFactoryUtil.getUrl(jSONObject), ActionFactoryUtil.getPlaystoreUrl(jSONObject), ActionFactoryUtil.getPlaystoreName(jSONObject), this.userMessageService, this.context);
    }

    public /* synthetic */ Action lambda$new$5$ActionFactory(JSONObject jSONObject) throws IllegalArgumentException {
        return new SettingsAction(ActionFactoryUtil.getActionMetaTypeForMetric(jSONObject), ActionFactoryUtil.getAccessibilityLabel(jSONObject), ActionFactoryUtil.getAndroidSettingsUrl(jSONObject), this.context);
    }

    public /* synthetic */ Action lambda$new$6$ActionFactory(JSONObject jSONObject) throws IllegalArgumentException {
        return new ApiRequestAction(ActionFactoryUtil.getActionMetaTypeForMetric(jSONObject), ActionFactoryUtil.getAccessibilityLabel(jSONObject), ActionFactoryUtil.getEndpoint(jSONObject), ActionFactoryUtil.getMethod(jSONObject), ActionFactoryUtil.getBody(jSONObject), this.homeFeedServiceClient, this.identityService);
    }
}
