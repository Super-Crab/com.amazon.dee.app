package com.amazon.alexa.voice.tta.statemachine;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.voice.tta.statemachine.Event;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StateMachineImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00030\u0002*\u0004\b\u0001\u0010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004BM\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012:\b\u0002\u0010\u0007\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\b¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003J;\u0010\u0018\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\bHÆ\u0003J]\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062:\b\u0002\u0010\u0007\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001RL\u0010\u0007\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/DefaultAction;", ExifInterface.LONGITUDE_EAST, "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "D", "Lcom/amazon/alexa/voice/tta/statemachine/Action;", "targetState", "Lcom/amazon/alexa/voice/tta/statemachine/State;", "execute", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "state", "event", "", "(Lcom/amazon/alexa/voice/tta/statemachine/State;Lkotlin/jvm/functions/Function2;)V", "getExecute", "()Lkotlin/jvm/functions/Function2;", "setExecute", "(Lkotlin/jvm/functions/Function2;)V", "getTargetState", "()Lcom/amazon/alexa/voice/tta/statemachine/State;", "setTargetState", "(Lcom/amazon/alexa/voice/tta/statemachine/State;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class DefaultAction<E extends Event<D>, D> implements Action<E, D> {
    @NotNull
    private Function2<? super State, ? super E, Unit> execute;
    @Nullable
    private State targetState;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: StateMachineImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0001\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u0002H\u0002H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "D", "<anonymous parameter 0>", "Lcom/amazon/alexa/voice/tta/statemachine/State;", "<anonymous parameter 1>", "invoke", "(Lcom/amazon/alexa/voice/tta/statemachine/State;Lcom/amazon/alexa/voice/tta/statemachine/Event;)V"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.voice.tta.statemachine.DefaultAction$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public static final class AnonymousClass1 extends Lambda implements Function2<State, E, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function2
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12248invoke(State state, Object obj) {
            invoke(state, (State) ((Event) obj));
            return Unit.INSTANCE;
        }

        public final void invoke(@NotNull State state, @NotNull E e) {
            Intrinsics.checkParameterIsNotNull(state, "<anonymous parameter 0>");
            Intrinsics.checkParameterIsNotNull(e, "<anonymous parameter 1>");
        }
    }

    public DefaultAction() {
        this(null, null, 3, null);
    }

    public DefaultAction(@Nullable State state, @NotNull Function2<? super State, ? super E, Unit> execute) {
        Intrinsics.checkParameterIsNotNull(execute, "execute");
        this.targetState = state;
        this.execute = execute;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DefaultAction copy$default(DefaultAction defaultAction, State state, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            state = defaultAction.getTargetState();
        }
        if ((i & 2) != 0) {
            function2 = defaultAction.getExecute();
        }
        return defaultAction.copy(state, function2);
    }

    @Nullable
    public final State component1() {
        return getTargetState();
    }

    @NotNull
    public final Function2<State, E, Unit> component2() {
        return getExecute();
    }

    @NotNull
    public final DefaultAction<E, D> copy(@Nullable State state, @NotNull Function2<? super State, ? super E, Unit> execute) {
        Intrinsics.checkParameterIsNotNull(execute, "execute");
        return new DefaultAction<>(state, execute);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DefaultAction)) {
                return false;
            }
            DefaultAction defaultAction = (DefaultAction) obj;
            return Intrinsics.areEqual(getTargetState(), defaultAction.getTargetState()) && Intrinsics.areEqual(getExecute(), defaultAction.getExecute());
        }
        return true;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.Action
    @NotNull
    public Function2<State, E, Unit> getExecute() {
        return (Function2<? super State, ? super E, Unit>) this.execute;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.Action
    @Nullable
    public State getTargetState() {
        return this.targetState;
    }

    public int hashCode() {
        State targetState = getTargetState();
        int i = 0;
        int hashCode = (targetState != null ? targetState.hashCode() : 0) * 31;
        Function2<State, E, Unit> execute = getExecute();
        if (execute != null) {
            i = execute.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.Action
    public void setExecute(@NotNull Function2<? super State, ? super E, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.execute = function2;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.Action
    public void setTargetState(@Nullable State state) {
        this.targetState = state;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DefaultAction(targetState=");
        outline107.append(getTargetState());
        outline107.append(", execute=");
        outline107.append(getExecute());
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ DefaultAction(State state, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : state, (i & 2) != 0 ? AnonymousClass1.INSTANCE : function2);
    }
}
