package com.amazon.alexa.voice.tta.statemachine;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StateMachineImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u000e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0011H\u0016J\u0014\u0010\u0012\u001a\u00020\u00132\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R2\u0010\u0007\u001a \u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b0\n0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/DefaultState;", "Lcom/amazon/alexa/voice/tta/statemachine/State;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "transitions", "", "Ljava/lang/Class;", "", "Lcom/amazon/alexa/voice/tta/statemachine/Action;", "getTransitions", "()Ljava/util/Map;", "handle", "D", "event", "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "isEventSupported", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public class DefaultState implements State {
    @NotNull
    private final String name;
    @NotNull
    private final Map<Class<?>, List<Action<?, ?>>> transitions;

    public DefaultState(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.transitions = new LinkedHashMap();
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.State
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.State
    @NotNull
    public Map<Class<?>, List<Action<?, ?>>> getTransitions() {
        return this.transitions;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.State
    @NotNull
    public <D> State handle(@NotNull Event<D> event) {
        List<Action<?, ?>> emptyList;
        Intrinsics.checkParameterIsNotNull(event, "event");
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        List<Action<?, ?>> list = getTransitions().get(event.getClass());
        if (list != null) {
            emptyList = list;
        }
        if (emptyList != null) {
            State state = null;
            Iterator<T> it2 = emptyList.iterator();
            while (it2.hasNext()) {
                Action action = (Action) it2.next();
                action.getExecute().mo12248invoke(this, event);
                State targetState = state != null ? state : action.getTargetState();
                if (targetState != null) {
                    state = targetState;
                }
            }
            return state != null ? state : this;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.amazon.alexa.voice.tta.statemachine.Action<com.amazon.alexa.voice.tta.statemachine.Event<D>, D>>");
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.State
    public boolean isEventSupported(@NotNull Event<?> event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        return getTransitions().containsKey(event.getClass());
    }
}
