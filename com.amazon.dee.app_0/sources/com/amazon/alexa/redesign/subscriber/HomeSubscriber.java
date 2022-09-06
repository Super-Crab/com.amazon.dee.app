package com.amazon.alexa.redesign.subscriber;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.redesign.DismissedCardDataStore;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.api.EventBusListener;
import com.amazon.alexa.redesign.entity.DismissIdentifier;
import com.amazon.alexa.redesign.entity.templates.DomainCardTemplateModel;
import com.amazon.alexa.redesign.repository.HomeCardsRepository;
import com.amazon.alexa.redesign.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class HomeSubscriber implements Subscriber {
    private static final String EVENT_BUS_ADD_LOCAL_CARD = "local:card:add";
    private static final String EVENT_BUS_INTEREST_PICKER_ROUTE_REQUEST = "request:home:native-route";
    private static final String EVENT_BUS_LIVE_UPDATE = "AlexaMobile::Home";
    private static final String EVENT_BUS_LOG_OUT_KEY = "appreview:userloggedout";
    private static final String EVENT_BUS_MODAL_TOGGLE = "ui:modal:ontoggle";
    private static final String EVENT_BUS_REMOVE_DISCOVER = "ui:dismiss:onclick";
    private static final String EVENT_BUS_REMOVE_LOCAL_CARD = "local:card:remove";
    private static final String EVENT_BUS_REQUEST_HOME_REFRESH = "request:home:refresh";
    private static final String EVENT_BUS_REQUEST_HOME_REFRESH_ON_NEXT_VISIT = "request:home:refresh:onnextvisit";
    private static final String EVENT_BUS_TCOMM_MESSAGE = "tcomm::message";
    private static final String EVENT_BUS_TWA_RESPONSE = "voice::typeToAlexaAvailabilityResponse";
    private static final String PUSH_LIST_CHANGE = "PUSH_LIST_CHANGE";
    private static final String PUSH_LIST_ITEM_CHANGE = "PUSH_LIST_ITEM_CHANGE";
    private static final String TAG = "HomeSubscriber";
    private final HomeContract.CardDismissedListener cardDismissedListener;
    private final DismissedCardDataStore dismissedCardDataStore;
    private final HomeCardsRepository homeCardsRepository;
    private final HomeContract.LiveUpdatesListener liveUpdatesListener;
    private final HomeContract.LocalCardListener localCardListener;
    private final HomeContract.LogOutListener logOutListener;
    private final HomeContract.ModalToggleListener modalToggleListener;
    private final HomeContract.RequestHomeRefreshListener requestHomeRefreshListener;
    private final EventBusListener rnRouteListener;
    private final EventBusListener twaListener;
    @VisibleForTesting
    public List<String> seenPayloads = new ArrayList();
    private final boolean hasLiveUpdates = false;

    public HomeSubscriber(HomeCardsRepository homeCardsRepository, HomeContract.RequestHomeRefreshListener requestHomeRefreshListener, HomeContract.LogOutListener logOutListener, HomeContract.ModalToggleListener modalToggleListener, HomeContract.LocalCardListener localCardListener, HomeContract.CardDismissedListener cardDismissedListener, DismissedCardDataStore dismissedCardDataStore, HomeContract.LiveUpdatesListener liveUpdatesListener, EventBusListener eventBusListener, EventBusListener eventBusListener2) {
        this.requestHomeRefreshListener = requestHomeRefreshListener;
        this.logOutListener = logOutListener;
        this.modalToggleListener = modalToggleListener;
        this.homeCardsRepository = homeCardsRepository;
        this.localCardListener = localCardListener;
        this.dismissedCardDataStore = dismissedCardDataStore;
        this.cardDismissedListener = cardDismissedListener;
        this.liveUpdatesListener = liveUpdatesListener;
        this.twaListener = eventBusListener;
        this.rnRouteListener = eventBusListener2;
    }

    @VisibleForTesting
    public boolean checkPayloadExists(String str) {
        for (String str2 : this.seenPayloads) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public UUID getUUID() {
        return UUID.randomUUID();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull Message message) {
        char c;
        String eventType = message.getEventType();
        switch (eventType.hashCode()) {
            case -1886890077:
                if (eventType.equals(EVENT_BUS_INTEREST_PICKER_ROUTE_REQUEST)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1547693852:
                if (eventType.equals(EVENT_BUS_LIVE_UPDATE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1511142837:
                if (eventType.equals(EVENT_BUS_REQUEST_HOME_REFRESH)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1387844793:
                if (eventType.equals("tcomm::message")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1095845786:
                if (eventType.equals("local:card:add")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -733157069:
                if (eventType.equals(EVENT_BUS_REMOVE_DISCOVER)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -126228960:
                if (eventType.equals("appreview:userloggedout")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -121434682:
                if (eventType.equals(EVENT_BUS_MODAL_TOGGLE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -55187318:
                if (eventType.equals(EVENT_BUS_REQUEST_HOME_REFRESH_ON_NEXT_VISIT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 192603807:
                if (eventType.equals("local:card:remove")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 2084562264:
                if (eventType.equals(EVENT_BUS_TWA_RESPONSE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.requestHomeRefreshListener.onRequestHomeRefreshEvent(false);
                return;
            case 1:
                this.requestHomeRefreshListener.onRequestHomeRefreshEvent(true);
                return;
            case 2:
                this.requestHomeRefreshListener.onRequestHomeRefreshEvent(false);
                return;
            case 3:
                this.seenPayloads.clear();
                this.logOutListener.setFlagOnLogOut();
                return;
            case 4:
                try {
                    this.modalToggleListener.onModalToggle(new JSONObject(message.getPayloadAsString()).getBoolean("isVisible"));
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            case 5:
                try {
                    String payloadAsString = message.getPayloadAsString();
                    JSONObject jSONObject = new JSONObject(payloadAsString);
                    if (checkPayloadExists(payloadAsString)) {
                        return;
                    }
                    this.seenPayloads.add(payloadAsString);
                    DomainCardTemplateModel domainCardTemplateModel = new DomainCardTemplateModel(jSONObject);
                    this.homeCardsRepository.addOrUpdateLocalCard(domainCardTemplateModel);
                    this.localCardListener.onLocalCardEventReceived();
                    this.homeCardsRepository.sendOEMetricsBasedOnDomainCard(domainCardTemplateModel);
                    return;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return;
                }
            case 6:
                try {
                    String payloadAsString2 = message.getPayloadAsString();
                    JSONObject jSONObject2 = new JSONObject(payloadAsString2);
                    if (!checkPayloadExists(payloadAsString2)) {
                        return;
                    }
                    this.seenPayloads.remove(payloadAsString2);
                    this.homeCardsRepository.removeLocalCard(new DomainCardTemplateModel(jSONObject2));
                    this.localCardListener.onLocalCardEventReceived();
                    return;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    return;
                }
            case 7:
                try {
                    String optString = new JSONObject(message.getPayloadAsString()).optString("cardId");
                    this.dismissedCardDataStore.putDismissedCardIds(new DismissIdentifier(System.currentTimeMillis(), optString), Constants.KEY_DISMISS_CARDS);
                    this.cardDismissedListener.displayActiveCardsFromCache();
                    String str = "dismiss discover  event received, card id is " + optString;
                    return;
                } catch (JSONException e4) {
                    Log.e(TAG, "JSON error", e4);
                    return;
                }
            case '\b':
                if (!this.hasLiveUpdates) {
                    return;
                }
                this.liveUpdatesListener.onLiveUpdateEvent();
                return;
            case '\t':
                try {
                    this.twaListener.execute(new JSONObject(message.getPayloadAsString()));
                    return;
                } catch (JSONException e5) {
                    e5.printStackTrace();
                    return;
                }
            case '\n':
                try {
                    this.rnRouteListener.execute(new JSONObject(message.getPayloadAsString()));
                    return;
                } catch (JSONException e6) {
                    e6.printStackTrace();
                    return;
                }
            default:
                Log.e(TAG, "Error. Doesn't seem you got the right message");
                return;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull Message message) {
        char c;
        String eventType = message.getEventType();
        if (eventType != null) {
            switch (eventType.hashCode()) {
                case -1886890077:
                    if (eventType.equals(EVENT_BUS_INTEREST_PICKER_ROUTE_REQUEST)) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case -1547693852:
                    if (eventType.equals(EVENT_BUS_LIVE_UPDATE)) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case -1511142837:
                    if (eventType.equals(EVENT_BUS_REQUEST_HOME_REFRESH)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1387844793:
                    if (eventType.equals("tcomm::message")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1095845786:
                    if (eventType.equals("local:card:add")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -733157069:
                    if (eventType.equals(EVENT_BUS_REMOVE_DISCOVER)) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -126228960:
                    if (eventType.equals("appreview:userloggedout")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -121434682:
                    if (eventType.equals(EVENT_BUS_MODAL_TOGGLE)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -55187318:
                    if (eventType.equals(EVENT_BUS_REQUEST_HOME_REFRESH_ON_NEXT_VISIT)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 192603807:
                    if (eventType.equals("local:card:remove")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 2084562264:
                    if (eventType.equals(EVENT_BUS_TWA_RESPONSE)) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    try {
                        String optString = new JSONObject(message.getPayloadAsString()).optString("command", "");
                        if (!optString.equals(PUSH_LIST_CHANGE)) {
                            if (!optString.equals(PUSH_LIST_ITEM_CHANGE)) {
                                return false;
                            }
                        }
                        return true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        break;
                    }
                case 1:
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    return true;
                case '\b':
                    return this.hasLiveUpdates;
                case '\t':
                case '\n':
                    return true;
                default:
                    return false;
            }
            return true;
        }
        return false;
    }
}
