package com.amazon.comms.models.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
/* loaded from: classes11.dex */
public interface SimEvent {
    @JsonIgnore
    String getEventName();

    @JsonIgnore
    String getNamespace();
}
