package com.amazon.alexa.voice.tta.statemachine;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StateMachineImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a)\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007\u001aJ\u0010\b\u001a\u00020\u0006\"\u0010\b\u0000\u0010\t\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b*\u00020\f2#\u0010\r\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u000b0\u000e\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0086\b¨\u0006\u000f"}, d2 = {"createStateMachine", "Lcom/amazon/alexa/voice/tta/statemachine/StateMachine;", "name", "", "init", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "on", ExifInterface.LONGITUDE_EAST, "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "D", "Lcom/amazon/alexa/voice/tta/statemachine/State;", "block", "Lcom/amazon/alexa/voice/tta/statemachine/Action;", "AlexaMobileAndroidVoice-TTA_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class StateMachineImplKt {
    @NotNull
    public static final StateMachine createStateMachine(@NotNull String name, @NotNull Function1<? super StateMachine, Unit> init) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(init, "init");
        StateMachineImpl stateMachineImpl = new StateMachineImpl(name);
        init.mo12165invoke(stateMachineImpl);
        return stateMachineImpl;
    }

    public static /* synthetic */ StateMachine createStateMachine$default(String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "StateMachine";
        }
        return createStateMachine(str, function1);
    }

    public static final /* synthetic */ <E extends Event<D>, D> void on(@NotNull State on, @NotNull Function1<? super Action<E, D>, Unit> block) {
        Intrinsics.checkParameterIsNotNull(on, "$this$on");
        Intrinsics.checkParameterIsNotNull(block, "block");
        DefaultAction defaultAction = new DefaultAction(null, null, 3, null);
        block.mo12165invoke(defaultAction);
        Map<Class<?>, List<Action<?, ?>>> transitions = on.getTransitions();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_EAST);
        List<Action<?, ?>> list = transitions.get(Event.class);
        if (list == null) {
            list = new ArrayList<>();
        }
        List<Action<?, ?>> list2 = list;
        list2.add(defaultAction);
        Map<Class<?>, List<Action<?, ?>>> transitions2 = on.getTransitions();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_EAST);
        transitions2.put(Event.class, list2);
    }
}
