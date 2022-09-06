package com.amazon.comms.config.util;

import com.amazon.comms.calling.service.UniqueIdentifier;
import java.util.UUID;
/* loaded from: classes11.dex */
public class UniqueIdentifierGeneratedByUUID implements UniqueIdentifier {
    private final UUID uniqueIdentifier = UUID.randomUUID();

    @Override // com.amazon.comms.calling.service.UniqueIdentifier
    public String get() {
        return this.uniqueIdentifier.toString();
    }
}
