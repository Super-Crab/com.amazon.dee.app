package com.amazon.tarazed.sessionmanager;

import android.app.NotificationManager;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.session.AppBackgroundChecker;
import com.amazon.tarazed.sessionmanager.HeadsUpNotificationManager;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
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
/* compiled from: HeadsUpNotificationManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.HeadsUpNotificationManager$notificationHandler$1", f = "HeadsUpNotificationManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class HeadsUpNotificationManager$notificationHandler$1 extends SuspendLambda implements Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> {
    int label;
    private TarazedNotificationEvent p$0;
    final /* synthetic */ HeadsUpNotificationManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadsUpNotificationManager$notificationHandler$1(HeadsUpNotificationManager headsUpNotificationManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = headsUpNotificationManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HeadsUpNotificationManager$notificationHandler$1 headsUpNotificationManager$notificationHandler$1 = new HeadsUpNotificationManager$notificationHandler$1(this.this$0, completion);
        headsUpNotificationManager$notificationHandler$1.p$0 = (TarazedNotificationEvent) obj;
        return headsUpNotificationManager$notificationHandler$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(TarazedNotificationEvent tarazedNotificationEvent, Continuation<? super Unit> continuation) {
        return ((HeadsUpNotificationManager$notificationHandler$1) create(tarazedNotificationEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        NotificationManager notificationManager;
        DeviceInfoUtilityAndroid deviceInfoUtilityAndroid;
        boolean z;
        NotificationManager notificationManager2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            switch (HeadsUpNotificationManager.WhenMappings.$EnumSwitchMapping$0[this.p$0.ordinal()]) {
                case 1:
                    this.this$0.notify(HeadsUpNotificationManager.Companion.getAUTO_PAUSE_MESSAGE$TarazedAndroidLibrary_release());
                    break;
                case 2:
                    this.this$0.notify(HeadsUpNotificationManager.Companion.getAUTO_END_MESSAGE$TarazedAndroidLibrary_release());
                    this.this$0.clearNotificationOnSessionEnd = false;
                    break;
                case 3:
                    this.this$0.notify(HeadsUpNotificationManager.Companion.getPERMISSION_REQUEST_MESSAGE$TarazedAndroidLibrary_release());
                    break;
                case 4:
                    notificationManager = this.this$0.getNotificationManager();
                    notificationManager.cancel(847285);
                    break;
                case 5:
                    deviceInfoUtilityAndroid = this.this$0.deviceInfo;
                    if (!deviceInfoUtilityAndroid.is1PDevice() && AppBackgroundChecker.INSTANCE.isAppInBackground()) {
                        this.this$0.notify(HeadsUpNotificationManager.Companion.getRESUME_MESSAGE$TarazedAndroidLibrary_release());
                        break;
                    }
                    break;
                case 6:
                    z = this.this$0.clearNotificationOnSessionEnd;
                    if (z) {
                        notificationManager2 = this.this$0.getNotificationManager();
                        notificationManager2.cancel(847285);
                    }
                    this.this$0.clearNotificationOnSessionEnd = true;
                    break;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
