package com.amazon.alexa.api;
/* loaded from: classes6.dex */
class c extends BaseMessagePayload {
    public c(ExtendedClient extendedClient, String str, AlertType alertType) {
        super(extendedClient);
        add(b.ALERT_ID, str);
        add(b.ALERT_TYPE, alertType.ordinal());
    }
}
