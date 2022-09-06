package com.amazon.alexa.voice.tta.statemachine;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.voice.tta.statemachine.Event;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00030\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0004RJ\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006X¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u0007X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/Action;", ExifInterface.LONGITUDE_EAST, "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "D", "", "execute", "Lkotlin/Function2;", "Lcom/amazon/alexa/voice/tta/statemachine/State;", "Lkotlin/ParameterName;", "name", "state", "event", "", "getExecute", "()Lkotlin/jvm/functions/Function2;", "setExecute", "(Lkotlin/jvm/functions/Function2;)V", "targetState", "getTargetState", "()Lcom/amazon/alexa/voice/tta/statemachine/State;", "setTargetState", "(Lcom/amazon/alexa/voice/tta/statemachine/State;)V", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public interface Action<E extends Event<D>, D> {
    @NotNull
    Function2<State, E, Unit> getExecute();

    @Nullable
    State getTargetState();

    void setExecute(@NotNull Function2<? super State, ? super E, Unit> function2);

    void setTargetState(@Nullable State state);
}
