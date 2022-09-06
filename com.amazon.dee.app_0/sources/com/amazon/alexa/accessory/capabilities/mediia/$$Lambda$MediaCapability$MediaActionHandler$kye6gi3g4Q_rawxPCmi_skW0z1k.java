package com.amazon.alexa.accessory.capabilities.mediia;

import com.amazon.alexa.accessory.capabilities.mediia.MediaCapability;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$MediaActionHandler$kye6gi3g4Q_rawxPCmi_skW0z1k  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MediaCapability$MediaActionHandler$kye6gi3g4Q_rawxPCmi_skW0z1k implements MappedResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$MediaCapability$MediaActionHandler$kye6gi3g4Q_rawxPCmi_skW0z1k INSTANCE = new $$Lambda$MediaCapability$MediaActionHandler$kye6gi3g4Q_rawxPCmi_skW0z1k();

    private /* synthetic */ $$Lambda$MediaCapability$MediaActionHandler$kye6gi3g4Q_rawxPCmi_skW0z1k() {
    }

    @Override // com.amazon.alexa.accessory.internal.MappedResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return MediaCapability.MediaActionHandler.lambda$handleIssueMediaControl$0(response);
    }
}
