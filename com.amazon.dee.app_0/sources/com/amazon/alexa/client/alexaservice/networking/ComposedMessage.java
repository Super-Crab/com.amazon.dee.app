package com.amazon.alexa.client.alexaservice.networking;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.networking.AutoValue_ComposedMessage;
import com.amazon.alexa.client.core.messages.Message;
import com.google.auto.value.AutoValue;
import java.util.Collection;
@AutoValue
/* loaded from: classes6.dex */
public abstract class ComposedMessage {

    /* JADX INFO: Access modifiers changed from: package-private */
    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract Builder zZm(Message message);

        public abstract Builder zZm(Collection<ComponentState> collection);

        public abstract ComposedMessage zZm();
    }

    public static Builder builder() {
        return new AutoValue_ComposedMessage.zZm();
    }

    @Nullable
    public abstract Collection<ComponentState> getContext();

    @Nullable
    public abstract Message getDirective();

    @Nullable
    public abstract Message getEvent();
}
