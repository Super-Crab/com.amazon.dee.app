package com.amazon.comms.models.events;

import com.amazon.comms.models.common.CommsConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonIgnoreProperties({"stackTrace", "localizedMessage", "suppressed"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
/* loaded from: classes11.dex */
public class LocalApplicationException extends RuntimeException implements SimEvent {
    @Override // com.amazon.comms.models.events.SimEvent
    public String getEventName() {
        return LocalApplicationException.class.getSimpleName();
    }

    @Override // com.amazon.comms.models.events.SimEvent
    public String getNamespace() {
        return CommsConstants.COMMUNICATIONS_NAMESPACE;
    }

    public Class<?> getType() {
        return LocalApplicationException.class;
    }
}
