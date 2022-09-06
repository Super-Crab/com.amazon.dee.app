package com.amazon.alexa.fitness.view.startTab;

import android.util.Log;
import android.view.View;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionCommandSource;
import com.amazon.alexa.fitness.utils.ActivityViewMetrics;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ActiveView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ActiveView$updateToPausedView$1$3$1 implements View.OnClickListener {
    public static final ActiveView$updateToPausedView$1$3$1 INSTANCE = new ActiveView$updateToPausedView$1$3$1();

    /* compiled from: ActiveView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "error", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "currentState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.fitness.view.startTab.ActiveView$updateToPausedView$1$3$1$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static final class AnonymousClass1 extends Lambda implements Function2<FitnessSessionOrchestrator.CommandProcessingError, FitnessSessionState, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(2);
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
            if (commandProcessingError != null) {
                Log.e("AFX-InProgressView", "error resuming - " + commandProcessingError + ", currentState = " + currentState);
            }
        }
    }

    ActiveView$updateToPausedView$1$3$1() {
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        view.performHapticFeedback(16);
        MetricHelperKt.recordUserInteractionEvent(FullScreenUtil.Companion.getMetricHelper(), ActivityViewMetrics.Companion.getRESUME_BUTTON(), EventType.TAP);
        FullScreenUtil.Companion.getFitnessSessionOrchestrator().receive(new Command.Resume(SessionCommandSource.GUI), AnonymousClass1.INSTANCE);
    }
}
