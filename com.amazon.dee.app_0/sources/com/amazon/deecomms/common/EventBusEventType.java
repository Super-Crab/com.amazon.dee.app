package com.amazon.deecomms.common;
/* loaded from: classes12.dex */
public enum EventBusEventType {
    REMOVE_EXPIRED_MEDIA_FROM_CACHE("comms::messaging::remove-expired-media"),
    USER_DEPROVISIONED("comms::app::user-deprovisioned"),
    ACCESSORY_PRIVACY_BUTTON_TOGGLED("accessory::privacy_status"),
    ACCESSORY_CALL_VIP_CONTACT_EVENT("accessory::call_vip_contact"),
    UPDATE_CONVERSATION_INFO("comms::app::update-conversation-info"),
    NOTIFICATION_RECEIVED("comms:app:notification"),
    RECEIVE_SHARING_PARSE("comms::sharing::receive::parse"),
    RECEIVE_SHARING_PAYLOAD("comms::sharing::receive::payload"),
    SEND_SHARING_PARSE("comms::sharing::send::parse"),
    SEND_SHARING_PAYLOAD("comms::sharing::send::payload"),
    START_NATIVE_CALL("comms::native-call::start"),
    HVA_DROP_IN_START("comms::calling::dropin_start"),
    HVA_DROP_IN_END("comms::calling::dropin_end"),
    HVA_ALEXA_CALL_START("comms::calling::a2a_start"),
    HVA_ALEXA_CALL_END("comms::calling::a2a_end");
    
    private final String eventType;

    EventBusEventType(String str) {
        this.eventType = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.eventType;
    }
}
