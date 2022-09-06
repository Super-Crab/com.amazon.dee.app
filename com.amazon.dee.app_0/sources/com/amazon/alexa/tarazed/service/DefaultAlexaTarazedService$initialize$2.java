package com.amazon.alexa.tarazed.service;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultAlexaTarazedService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/alexa/eventbus/api/Message;", "isMatch"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes10.dex */
final class DefaultAlexaTarazedService$initialize$2 implements MessageFilter {
    public static final DefaultAlexaTarazedService$initialize$2 INSTANCE = new DefaultAlexaTarazedService$initialize$2();

    DefaultAlexaTarazedService$initialize$2() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(@NotNull Message it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return Intrinsics.areEqual(it2.getEventType(), IdentityEvent.IDENTITY_OOBE_PROFILE_SELECTED) || Intrinsics.areEqual(it2.getEventType(), IdentityEvent.IDENTITY_PROFILE_CHANGED);
    }
}
