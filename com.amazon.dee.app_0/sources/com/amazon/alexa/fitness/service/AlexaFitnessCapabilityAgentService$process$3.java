package com.amazon.alexa.fitness.service;

import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.model.directive.DirectiveSession;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaFitnessCapabilityAgentService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "error", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "currentState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class AlexaFitnessCapabilityAgentService$process$3 extends Lambda implements Function2<FitnessSessionOrchestrator.CommandProcessingError, FitnessSessionState, Unit> {
    final /* synthetic */ Command.Pause $command;
    final /* synthetic */ DirectiveSession $directiveSession;
    final /* synthetic */ AlexaFitnessCapabilityAgentService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlexaFitnessCapabilityAgentService$process$3(AlexaFitnessCapabilityAgentService alexaFitnessCapabilityAgentService, DirectiveSession directiveSession, Command.Pause pause) {
        super(2);
        this.this$0 = alexaFitnessCapabilityAgentService;
        this.$directiveSession = directiveSession;
        this.$command = pause;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12248invoke(FitnessSessionOrchestrator.CommandProcessingError commandProcessingError, FitnessSessionState fitnessSessionState) {
        invoke2(commandProcessingError, fitnessSessionState);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable FitnessSessionOrchestrator.CommandProcessingError commandProcessingError, @NotNull FitnessSessionState currentState) {
        Intrinsics.checkParameterIsNotNull(currentState, "currentState");
        this.this$0.handleCommandResult(this.$directiveSession.getId(), this.$command, commandProcessingError, currentState);
    }
}
