package com.amazon.alexa.statereporting.lib.model.serialization.type;

import com.amazon.alexa.statereporting.lib.model.api.event.Change;
import com.amazon.alexa.statereporting.lib.model.api.event.ChangeReportEventPayload;
/* loaded from: classes10.dex */
public final class SimpleChangeReportEventPayload extends ChangeReportEventPayload<String> {
    protected SimpleChangeReportEventPayload(Change<String> change) {
        super(change);
    }
}
