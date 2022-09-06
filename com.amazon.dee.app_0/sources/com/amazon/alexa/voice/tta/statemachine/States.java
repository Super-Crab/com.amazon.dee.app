package com.amazon.alexa.voice.tta.statemachine;

import kotlin.Metadata;
/* compiled from: SearchWorkflow.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/States;", "", "()V", "IdleState", "WaitingForAVSState", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public abstract class States {

    /* compiled from: SearchWorkflow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/States$IdleState;", "Lcom/amazon/alexa/voice/tta/statemachine/DefaultState;", "()V", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class IdleState extends DefaultState {
        public static final IdleState INSTANCE = new IdleState();

        private IdleState() {
            super("idle");
        }
    }

    /* compiled from: SearchWorkflow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/States$WaitingForAVSState;", "Lcom/amazon/alexa/voice/tta/statemachine/DefaultState;", "()V", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class WaitingForAVSState extends DefaultState {
        public static final WaitingForAVSState INSTANCE = new WaitingForAVSState();

        private WaitingForAVSState() {
            super("avs.waitForResponse");
        }
    }

    private States() {
    }
}
