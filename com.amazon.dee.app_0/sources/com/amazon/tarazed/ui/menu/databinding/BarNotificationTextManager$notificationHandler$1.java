package com.amazon.tarazed.ui.menu.databinding;

import android.content.Context;
import androidx.databinding.ObservableField;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.ui.menu.databinding.BarNotificationTextManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BarNotificationTextManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.ui.menu.databinding.BarNotificationTextManager$notificationHandler$1", f = "BarNotificationTextManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class BarNotificationTextManager$notificationHandler$1 extends SuspendLambda implements Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> {
    int label;
    private TarazedNotificationEvent p$0;
    final /* synthetic */ BarNotificationTextManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarNotificationTextManager$notificationHandler$1(BarNotificationTextManager barNotificationTextManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = barNotificationTextManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BarNotificationTextManager$notificationHandler$1 barNotificationTextManager$notificationHandler$1 = new BarNotificationTextManager$notificationHandler$1(this.this$0, completion);
        barNotificationTextManager$notificationHandler$1.p$0 = (TarazedNotificationEvent) obj;
        return barNotificationTextManager$notificationHandler$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(TarazedNotificationEvent tarazedNotificationEvent, Continuation<? super Unit> continuation) {
        return ((BarNotificationTextManager$notificationHandler$1) create(tarazedNotificationEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ObservableField observableField;
        ObservableField observableField2;
        Context context;
        BizMetricsHelper bizMetricsHelper;
        ObservableField observableField3;
        ObservableField observableField4;
        Context context2;
        BizMetricsHelper bizMetricsHelper2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            int i = BarNotificationTextManager.WhenMappings.$EnumSwitchMapping$0[this.p$0.ordinal()];
            if (i == 1) {
                observableField = this.this$0.playbackStateObservable;
                if (Intrinsics.areEqual((String) observableField.get(), "paused")) {
                    observableField2 = this.this$0.barNotificationTextObservable;
                    context = this.this$0.context;
                    observableField2.set(context.getString(BarNotificationTextManager.Companion.getAGENT_PAUSED$TarazedAndroidLibrary_release()));
                    bizMetricsHelper = this.this$0.bizMetricsHelper;
                    BizMetricsHelper.publishBizMetric$default(bizMetricsHelper, BizMetricsConstants.AGENT_PAUSED_NOTIFICATION_EVENT_NAME, null, 2, null);
                }
            } else if (i == 2) {
                observableField3 = this.this$0.playbackStateObservable;
                if (Intrinsics.areEqual((String) observableField3.get(), "paused")) {
                    observableField4 = this.this$0.barNotificationTextObservable;
                    context2 = this.this$0.context;
                    observableField4.set(context2.getString(BarNotificationTextManager.Companion.getAUTO_PAUSED$TarazedAndroidLibrary_release()));
                    bizMetricsHelper2 = this.this$0.bizMetricsHelper;
                    BizMetricsHelper.publishBizMetric$default(bizMetricsHelper2, BizMetricsConstants.SYSTEM_PAUSED_NOTIFICATION_EVENT_NAME, null, 2, null);
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
