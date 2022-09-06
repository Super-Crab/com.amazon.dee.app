package com.amazon.alexa.statereporting.lib.model.serialization.type;

import com.amazon.alexa.statereporting.lib.model.api.Property;
import com.amazon.alexa.statereporting.lib.model.api.context.StateReportContext;
import java.util.List;
/* loaded from: classes10.dex */
public final class SimpleStateReportContext extends StateReportContext<String> {
    protected SimpleStateReportContext(List<Property<String, ?, ?>> list) {
        super(list);
    }
}
