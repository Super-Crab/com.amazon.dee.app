package com.amazon.tarazed.ui.manager;

import android.content.Context;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.ui.manager.TarazedDrawerUIManager;
import com.amazon.tarazed.ui.menu.MenuDrawerManager;
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
/* compiled from: TarazedDrawerUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.ui.manager.TarazedDrawerUIManager$sessionNotificationHandler$1", f = "TarazedDrawerUIManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class TarazedDrawerUIManager$sessionNotificationHandler$1 extends SuspendLambda implements Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> {
    int label;
    private TarazedNotificationEvent p$0;
    final /* synthetic */ TarazedDrawerUIManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedDrawerUIManager$sessionNotificationHandler$1(TarazedDrawerUIManager tarazedDrawerUIManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedDrawerUIManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedDrawerUIManager$sessionNotificationHandler$1 tarazedDrawerUIManager$sessionNotificationHandler$1 = new TarazedDrawerUIManager$sessionNotificationHandler$1(this.this$0, completion);
        tarazedDrawerUIManager$sessionNotificationHandler$1.p$0 = (TarazedNotificationEvent) obj;
        return tarazedDrawerUIManager$sessionNotificationHandler$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(TarazedNotificationEvent tarazedNotificationEvent, Continuation<? super Unit> continuation) {
        return ((TarazedDrawerUIManager$sessionNotificationHandler$1) create(tarazedNotificationEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        MenuDrawerManager menuDrawerManager;
        Context context;
        boolean z;
        boolean z2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            TarazedNotificationEvent tarazedNotificationEvent = this.p$0;
            if (TarazedDrawerUIManager.WhenMappings.$EnumSwitchMapping$0[tarazedNotificationEvent.ordinal()] == 1 && !this.this$0.isEnabled()) {
                this.this$0.initializeUI();
            }
            menuDrawerManager = this.this$0.menuDrawerManager;
            menuDrawerManager.handleSessionChange$TarazedAndroidLibrary_release(tarazedNotificationEvent);
            if (this.this$0.isEnabled()) {
                int i = 0;
                switch (TarazedDrawerUIManager.WhenMappings.$EnumSwitchMapping$1[tarazedNotificationEvent.ordinal()]) {
                    case 1:
                        z = this.this$0.isPaused;
                        if (!z) {
                            i = R.string.screen_sharing_connecting;
                            break;
                        }
                        break;
                    case 2:
                        z2 = this.this$0.isPaused;
                        if (!z2) {
                            i = R.string.screen_sharing_active;
                            break;
                        }
                        break;
                    case 3:
                    case 4:
                    case 5:
                        this.this$0.isPaused = true;
                        i = R.string.screen_sharing_paused;
                        break;
                    case 6:
                    case 7:
                        this.this$0.isPaused = false;
                        i = R.string.screen_sharing_active;
                        break;
                }
                if (i != 0) {
                    context = this.this$0.context;
                    String string = context.getResources().getString(i);
                    Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getString(toastResourceId)");
                    this.this$0.displayToast(string);
                }
            }
            if ((tarazedNotificationEvent == TarazedNotificationEvent.SESSION_END || tarazedNotificationEvent == TarazedNotificationEvent.SESSION_SUSPENDED) && this.this$0.isEnabled()) {
                this.this$0.teardownUI();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
