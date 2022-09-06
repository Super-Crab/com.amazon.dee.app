package com.amazon.alexa.voice.tta.statemachine;

import com.amazon.alexa.voice.tta.statemachine.Inputs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SearchWorkflow.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/Events;", "", "()V", "AVSTextResponseReceivedEvent", "SendTextEvent", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public abstract class Events {

    /* compiled from: SearchWorkflow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0003\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/Events$AVSTextResponseReceivedEvent;", "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "Lcom/amazon/alexa/voice/tta/statemachine/Inputs$TextResponseInput;", "data", "(Lcom/amazon/alexa/voice/tta/statemachine/Inputs$TextResponseInput;)V", "getData", "()Lcom/amazon/alexa/voice/tta/statemachine/Inputs$TextResponseInput;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class AVSTextResponseReceivedEvent implements Event<Inputs.TextResponseInput> {
        @NotNull
        private final Inputs.TextResponseInput data;

        public AVSTextResponseReceivedEvent(@NotNull Inputs.TextResponseInput data) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            this.data = data;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.voice.tta.statemachine.Event
        @NotNull
        /* renamed from: getData */
        public Inputs.TextResponseInput mo2647getData() {
            return this.data;
        }
    }

    /* compiled from: SearchWorkflow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0003\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/Events$SendTextEvent;", "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "Lcom/amazon/alexa/voice/tta/statemachine/Inputs$TextRequestInput;", "data", "(Lcom/amazon/alexa/voice/tta/statemachine/Inputs$TextRequestInput;)V", "getData", "()Lcom/amazon/alexa/voice/tta/statemachine/Inputs$TextRequestInput;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class SendTextEvent implements Event<Inputs.TextRequestInput> {
        @NotNull
        private final Inputs.TextRequestInput data;

        public SendTextEvent(@NotNull Inputs.TextRequestInput data) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            this.data = data;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.voice.tta.statemachine.Event
        @NotNull
        /* renamed from: getData */
        public Inputs.TextRequestInput mo2647getData() {
            return this.data;
        }
    }

    private Events() {
    }
}
