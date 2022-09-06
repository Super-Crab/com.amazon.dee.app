package com.amazon.alexa.accessory.capabilities.system;

import com.amazon.alexa.accessory.protocol.Accessories;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$SystemCapability$8R79GQKrR8MVp0zZb-sECVU1Ckg  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SystemCapability$8R79GQKrR8MVp0zZbsECVU1Ckg implements Predicate {
    public static final /* synthetic */ $$Lambda$SystemCapability$8R79GQKrR8MVp0zZbsECVU1Ckg INSTANCE = new $$Lambda$SystemCapability$8R79GQKrR8MVp0zZbsECVU1Ckg();

    private /* synthetic */ $$Lambda$SystemCapability$8R79GQKrR8MVp0zZbsECVU1Ckg() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return SystemCapability.lambda$getI18nArtifactName$9((Accessories.Response) obj);
    }
}
