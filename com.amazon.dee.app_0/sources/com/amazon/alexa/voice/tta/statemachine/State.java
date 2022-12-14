package com.amazon.alexa.voice.tta.statemachine;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: StateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\r\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0010H&J\u0014\u0010\u0011\u001a\u00020\u00122\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R0\u0010\u0006\u001a \u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n0\t0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/State;", "", "name", "", "getName", "()Ljava/lang/String;", "transitions", "", "Ljava/lang/Class;", "", "Lcom/amazon/alexa/voice/tta/statemachine/Action;", "getTransitions", "()Ljava/util/Map;", "handle", "D", "event", "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "isEventSupported", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public interface State {
    @NotNull
    String getName();

    @NotNull
    Map<Class<?>, List<Action<?, ?>>> getTransitions();

    @NotNull
    <D> State handle(@NotNull Event<D> event);

    boolean isEventSupported(@NotNull Event<?> event);
}
