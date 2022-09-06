package com.amazon.alexa.tarazed.account;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: IdentityEventListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "message", "Lcom/amazon/alexa/eventbus/api/Message;", "isMatch"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes10.dex */
final class IdentityEventListener$startListeningForEvents$1 implements MessageFilter {
    public static final IdentityEventListener$startListeningForEvents$1 INSTANCE = new IdentityEventListener$startListeningForEvents$1();

    IdentityEventListener$startListeningForEvents$1() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(@NotNull Message message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Intrinsics.areEqual(message.getEventType(), IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS);
    }
}
