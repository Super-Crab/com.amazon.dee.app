package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "sensorCommand", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class SessionManagerImpl$sensorCommandReceiver$1 extends Lambda implements Function1<Command, Unit> {
    final /* synthetic */ SessionManagerImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionManagerImpl$sensorCommandReceiver$1(SessionManagerImpl sessionManagerImpl) {
        super(1);
        this.this$0 = sessionManagerImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Command command) {
        invoke2(command);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull Command sensorCommand) {
        Intrinsics.checkParameterIsNotNull(sensorCommand, "sensorCommand");
        this.this$0.receive(sensorCommand);
    }
}
