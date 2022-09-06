package com.amazon.alexa.client.alexaservice.networking;

import androidx.annotation.Nullable;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.networking.ComposedMessage;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Collection;
/* loaded from: classes6.dex */
public final class AutoValue_ComposedMessage extends ComposedMessage {
    public final Collection<ComponentState> context;
    public final Message directive;
    public final Message event;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class zZm extends ComposedMessage.Builder {
        public Message BIo;
        public Message zQM;
        public Collection<ComponentState> zZm;

        @Override // com.amazon.alexa.client.alexaservice.networking.ComposedMessage.Builder
        public ComposedMessage.Builder zZm(@Nullable Collection<ComponentState> collection) {
            this.zZm = collection;
            return this;
        }

        @Override // com.amazon.alexa.client.alexaservice.networking.ComposedMessage.Builder
        public ComposedMessage.Builder zZm(@Nullable Message message) {
            this.BIo = message;
            return this;
        }

        @Override // com.amazon.alexa.client.alexaservice.networking.ComposedMessage.Builder
        public ComposedMessage zZm() {
            return new AutoValue_ComposedMessage(this.zZm, this.BIo, this.zQM);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComposedMessage)) {
            return false;
        }
        ComposedMessage composedMessage = (ComposedMessage) obj;
        Collection<ComponentState> collection = this.context;
        if (collection != null ? collection.equals(composedMessage.getContext()) : composedMessage.getContext() == null) {
            Message message = this.event;
            if (message != null ? message.equals(composedMessage.getEvent()) : composedMessage.getEvent() == null) {
                Message message2 = this.directive;
                if (message2 == null) {
                    if (composedMessage.getDirective() == null) {
                        return true;
                    }
                } else if (message2.equals(composedMessage.getDirective())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.client.alexaservice.networking.ComposedMessage
    @Nullable
    public Collection<ComponentState> getContext() {
        return this.context;
    }

    @Override // com.amazon.alexa.client.alexaservice.networking.ComposedMessage
    @Nullable
    public Message getDirective() {
        return this.directive;
    }

    @Override // com.amazon.alexa.client.alexaservice.networking.ComposedMessage
    @Nullable
    public Message getEvent() {
        return this.event;
    }

    public int hashCode() {
        Collection<ComponentState> collection = this.context;
        int i = 0;
        int hashCode = ((collection == null ? 0 : collection.hashCode()) ^ 1000003) * 1000003;
        Message message = this.event;
        int hashCode2 = (hashCode ^ (message == null ? 0 : message.hashCode())) * 1000003;
        Message message2 = this.directive;
        if (message2 != null) {
            i = message2.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("ComposedMessage{context=");
        zZm2.append(this.context);
        zZm2.append(", event=");
        zZm2.append(this.event);
        zZm2.append(", directive=");
        return C0179Pya.BIo(zZm2, this.directive, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public AutoValue_ComposedMessage(@Nullable Collection<ComponentState> collection, @Nullable Message message, @Nullable Message message2) {
        this.context = collection;
        this.event = message;
        this.directive = message2;
    }
}
