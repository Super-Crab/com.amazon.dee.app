package com.amazon.alexa.rangecontroller.lib.model;

import com.amazon.deecomms.calling.enums.CallProvider;
import edu.umd.cs.findbugs.annotations.NonNull;
/* loaded from: classes9.dex */
public enum NamespaceName {
    RANGE_CONTROLLER("Alexa.RangeController"),
    ALEXA(CallProvider.Alexa),
    ALEXA_SAFETY("Alexa.Safety");
    
    private final String name;

    NamespaceName(@NonNull String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
