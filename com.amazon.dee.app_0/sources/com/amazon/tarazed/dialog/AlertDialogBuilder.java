package com.amazon.tarazed.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.dialog.AlertDialogBuilder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlertDialogBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 >2\u00020\u0001:\u0001>B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0014\u0010\u0019\u001a\u00020\u00002\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u000bJ\u0015\u0010 \u001a\u00020\u00002\b\u0010!\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\"J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u001dH\u0002J\u0015\u0010#\u001a\u00020\u00002\b\u0010'\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010(J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u000bJ\u001a\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\b2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u001a\u0010/\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\b2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u0012\u00100\u001a\u00020\u00002\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0012\u00103\u001a\u00020\u00002\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u000e\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u00020\u0014J\u001a\u00108\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\b2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u000e\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u000bJ\u0015\u0010;\u001a\u00020\u00002\b\u0010<\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010(J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\bR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017¨\u0006?"}, d2 = {"Lcom/amazon/tarazed/dialog/AlertDialogBuilder;", "Landroid/app/AlertDialog$Builder;", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "themeResId", "", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;I)V", "canceledOnTouchOutside", "", "dialogAutoDismissMs", "", "Ljava/lang/Long;", "forceSystem", "messageList", "", "", "onShowListener", "Landroid/content/DialogInterface$OnShowListener;", "selectPositiveButton", "widthResourceId", "Ljava/lang/Integer;", "windowType", "addMessage", "messageIds", "", "create", "Landroid/app/AlertDialog;", "setCanceledOnTouchOutside", DeviceStateModule.CANCEL, "setDialogAutoDismissMs", "autoDismissMs", "(Ljava/lang/Long;)Lcom/amazon/tarazed/dialog/AlertDialogBuilder;", "setDialogWidth", "", "resourceId", "alertDialog", "newWidthResourceId", "(Ljava/lang/Integer;)Lcom/amazon/tarazed/dialog/AlertDialogBuilder;", "setForceSystem", "forceSystemValue", "setNegativeButton", "textId", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/content/DialogInterface$OnClickListener;", "setNeutralButton", "setOnCancelListener", "onCancelListener", "Landroid/content/DialogInterface$OnCancelListener;", "setOnKeyListener", "onKeyListener", "Landroid/content/DialogInterface$OnKeyListener;", "setOnShowListener", "newOnShowListener", "setPositiveButton", "setSelectPositiveButton", "selectPositiveButtonValue", "setTitle", "titleId", "setWindowType", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AlertDialogBuilder extends AlertDialog.Builder {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AlertDialogBuilder";
    private boolean canceledOnTouchOutside;
    private Long dialogAutoDismissMs;
    private boolean forceSystem;
    private TarazedSessionLogger logger;
    private List<String> messageList;
    private DialogInterface.OnShowListener onShowListener;
    private boolean selectPositiveButton;
    private Integer widthResourceId;
    private Integer windowType;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AlertDialogBuilder.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/dialog/AlertDialogBuilder$Companion;", "", "()V", "TAG", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlertDialogBuilder(@Nullable Context context, @NotNull TarazedSessionLogger logger) {
        super(context);
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.canceledOnTouchOutside = true;
        this.logger = logger;
        this.messageList = new ArrayList();
    }

    @NotNull
    public final AlertDialogBuilder addMessage(@NotNull List<Integer> messageIds) {
        Intrinsics.checkParameterIsNotNull(messageIds, "messageIds");
        for (Integer num : messageIds) {
            int intValue = num.intValue();
            List<String> list = this.messageList;
            String string = getContext().getString(intValue);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(messageId)");
            list.add(string);
        }
        return this;
    }

    @Override // android.app.AlertDialog.Builder
    @NotNull
    public AlertDialog create() {
        Integer num;
        StringBuilder sb = new StringBuilder();
        for (String str : this.messageList) {
            sb.append(str);
            sb.append(" ");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "messageBuilder.toString()");
        int length = sb2.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = sb2.charAt(!z ? i : length) <= ' ';
            if (!z) {
                if (!z2) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!z2) {
                break;
            } else {
                length--;
            }
        }
        setMessage(sb2.subSequence(i, length + 1).toString());
        final AlertDialog alertDialog = super.create();
        alertDialog.setCanceledOnTouchOutside(this.canceledOnTouchOutside);
        if (this.forceSystem) {
            Intrinsics.checkExpressionValueIsNotNull(alertDialog, "alertDialog");
            Window window = alertDialog.getWindow();
            if (window == null || (num = this.windowType) == null) {
                this.logger.w(TAG, "setWindowType - Dialog's window is null");
            } else {
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                window.setType(num.intValue());
            }
        }
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.amazon.tarazed.dialog.AlertDialogBuilder$create$2

            /* compiled from: AlertDialogBuilder.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
            @DebugMetadata(c = "com.amazon.tarazed.dialog.AlertDialogBuilder$create$2$2", f = "AlertDialogBuilder.kt", i = {0}, l = {260}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
            /* renamed from: com.amazon.tarazed.dialog.AlertDialogBuilder$create$2$2  reason: invalid class name */
            /* loaded from: classes13.dex */
            static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;
                private CoroutineScope p$;

                AnonymousClass2(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(completion);
                    anonymousClass2.p$ = (CoroutineScope) obj;
                    return anonymousClass2;
                }

                @Override // kotlin.jvm.functions.Function2
                /* renamed from: invoke */
                public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Long l;
                    TarazedSessionLogger tarazedSessionLogger;
                    Long l2;
                    AlertDialogBuilder.Companion unused;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.p$;
                        l = AlertDialogBuilder.this.dialogAutoDismissMs;
                        if (l == null) {
                            Intrinsics.throwNpe();
                        }
                        long longValue = l.longValue();
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        if (DelayKt.delay(longValue, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    AlertDialog alertDialog = alertDialog;
                    Intrinsics.checkExpressionValueIsNotNull(alertDialog, "alertDialog");
                    if (alertDialog.isShowing()) {
                        tarazedSessionLogger = AlertDialogBuilder.this.logger;
                        unused = AlertDialogBuilder.Companion;
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        l2 = AlertDialogBuilder.this.dialogAutoDismissMs;
                        Object[] objArr = {l2};
                        String format = String.format("AlertDialog shown without user interaction for %s ms, auto-dismissing as part of AlertDialog configuration", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        tarazedSessionLogger.i("AlertDialogBuilder", format);
                        alertDialog.dismiss();
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                Integer num2;
                boolean z3;
                DialogInterface.OnShowListener onShowListener;
                Long l;
                Integer num3;
                num2 = AlertDialogBuilder.this.widthResourceId;
                if (num2 != null) {
                    AlertDialogBuilder alertDialogBuilder = AlertDialogBuilder.this;
                    num3 = alertDialogBuilder.widthResourceId;
                    if (num3 == null) {
                        Intrinsics.throwNpe();
                    }
                    int intValue = num3.intValue();
                    AlertDialog alertDialog2 = alertDialog;
                    Intrinsics.checkExpressionValueIsNotNull(alertDialog2, "alertDialog");
                    alertDialogBuilder.setDialogWidth(intValue, alertDialog2);
                }
                z3 = AlertDialogBuilder.this.selectPositiveButton;
                if (z3) {
                    final Button positiveButton = alertDialog.getButton(-1);
                    Intrinsics.checkExpressionValueIsNotNull(positiveButton, "positiveButton");
                    positiveButton.setFocusableInTouchMode(true);
                    if (positiveButton.isInTouchMode()) {
                        final Drawable background = positiveButton.getBackground();
                        positiveButton.setBackgroundResource(R.drawable.hidden_focus_button);
                        positiveButton.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.amazon.tarazed.dialog.AlertDialogBuilder$create$2.1
                            @Override // android.view.View.OnFocusChangeListener
                            public final void onFocusChange(View view, boolean z4) {
                                Button positiveButton2 = positiveButton;
                                Intrinsics.checkExpressionValueIsNotNull(positiveButton2, "positiveButton");
                                if (!positiveButton2.isInTouchMode()) {
                                    Button positiveButton3 = positiveButton;
                                    Intrinsics.checkExpressionValueIsNotNull(positiveButton3, "positiveButton");
                                    positiveButton3.setBackground(background);
                                }
                            }
                        });
                    }
                    positiveButton.requestFocus();
                }
                onShowListener = AlertDialogBuilder.this.onShowListener;
                if (onShowListener != null) {
                    onShowListener.onShow(dialogInterface);
                }
                l = AlertDialogBuilder.this.dialogAutoDismissMs;
                if (l != null) {
                    BuildersKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass2(null), 2, null);
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(alertDialog, "alertDialog");
        return alertDialog;
    }

    @NotNull
    public final AlertDialogBuilder setCanceledOnTouchOutside(boolean z) {
        this.canceledOnTouchOutside = z;
        return this;
    }

    @NotNull
    public final AlertDialogBuilder setDialogAutoDismissMs(@Nullable Long l) {
        this.dialogAutoDismissMs = l;
        return this;
    }

    @NotNull
    public final AlertDialogBuilder setDialogWidth(@Nullable Integer num) {
        this.widthResourceId = num;
        return this;
    }

    @NotNull
    public final AlertDialogBuilder setForceSystem(boolean z) {
        this.forceSystem = z;
        return this;
    }

    @NotNull
    public final AlertDialogBuilder setOnShowListener(@NotNull DialogInterface.OnShowListener newOnShowListener) {
        Intrinsics.checkParameterIsNotNull(newOnShowListener, "newOnShowListener");
        this.onShowListener = newOnShowListener;
        return this;
    }

    @NotNull
    public final AlertDialogBuilder setSelectPositiveButton(boolean z) {
        this.selectPositiveButton = z;
        return this;
    }

    @NotNull
    public final AlertDialogBuilder setTitle(@Nullable Integer num) {
        if (num != null) {
            super.setTitle(num.intValue());
        }
        return this;
    }

    @NotNull
    public final AlertDialogBuilder setWindowType(int i) {
        this.windowType = Integer.valueOf(i);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDialogWidth(int i, AlertDialog alertDialog) {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = alertDialog.getWindow();
        if (window != null) {
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = dimensionPixelSize;
            Window window2 = alertDialog.getWindow();
            if (window2 == null) {
                return;
            }
            window2.setAttributes(layoutParams);
            return;
        }
        this.logger.w(TAG, "setDialogWidth - Dialog's window is null");
    }

    @Override // android.app.AlertDialog.Builder
    @NotNull
    public AlertDialogBuilder setNegativeButton(int i, @Nullable DialogInterface.OnClickListener onClickListener) {
        if (i != 0 && onClickListener != null) {
            super.setNegativeButton(i, onClickListener);
        }
        return this;
    }

    @Override // android.app.AlertDialog.Builder
    @NotNull
    public AlertDialogBuilder setNeutralButton(int i, @Nullable DialogInterface.OnClickListener onClickListener) {
        if (i != 0 && onClickListener != null) {
            super.setNeutralButton(i, onClickListener);
        }
        return this;
    }

    @Override // android.app.AlertDialog.Builder
    @NotNull
    public AlertDialogBuilder setOnCancelListener(@Nullable DialogInterface.OnCancelListener onCancelListener) {
        if (onCancelListener != null) {
            super.setOnCancelListener(onCancelListener);
        }
        return this;
    }

    @Override // android.app.AlertDialog.Builder
    @NotNull
    public AlertDialogBuilder setOnKeyListener(@Nullable DialogInterface.OnKeyListener onKeyListener) {
        if (onKeyListener != null) {
            super.setOnKeyListener(onKeyListener);
        }
        return this;
    }

    @Override // android.app.AlertDialog.Builder
    @NotNull
    public AlertDialogBuilder setPositiveButton(int i, @Nullable DialogInterface.OnClickListener onClickListener) {
        if (i != 0 && onClickListener != null) {
            super.setPositiveButton(i, onClickListener);
        }
        return this;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlertDialogBuilder(@NotNull Context context, @NotNull TarazedSessionLogger logger, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.canceledOnTouchOutside = true;
        this.logger = logger;
        this.messageList = new ArrayList();
    }
}
