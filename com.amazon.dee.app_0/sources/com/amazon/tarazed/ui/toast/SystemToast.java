package com.amazon.tarazed.ui.toast;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.VisibleForTesting;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.ui.WindowParamsHelper;
import com.amazon.tarazed.utility.AccessibilityHelper;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SystemToast.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0003H\u0002J\u0018\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0018H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/tarazed/ui/toast/SystemToast;", "Landroid/widget/Toast;", "context", "Landroid/content/Context;", "view", "Landroid/view/View;", "duration", "", "mainLooperHandler", "Landroid/os/Handler;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "(Landroid/content/Context;Landroid/view/View;ILandroid/os/Handler;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "isDisplayed", "", "isDisplayed$annotations", "()V", "isDisplayed$TarazedAndroidLibrary_release", "()Z", "setDisplayed$TarazedAndroidLibrary_release", "(Z)V", DeviceStateModule.CANCEL, "", "createLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "applicationContext", "presentToast", "windowManager", "Landroid/view/WindowManager;", "layoutParams", "show", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SystemToast extends Toast {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    public static final int LONG_DELAY_MILLIS = 3500;
    public static final int SHORT_DELAY_MILLIS = 2000;
    private final Context context;
    private final DeviceInfoUtility deviceInfoUtility;
    private boolean isDisplayed;
    private final Handler mainLooperHandler;

    /* compiled from: SystemToast.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/ui/toast/SystemToast$Companion;", "", "()V", "LONG_DELAY_MILLIS", "", "SHORT_DELAY_MILLIS", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemToast(@NotNull Context context, @NotNull View view, int i, @NotNull Handler mainLooperHandler, @NotNull DeviceInfoUtility deviceInfoUtility) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(mainLooperHandler, "mainLooperHandler");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        this.context = context;
        this.mainLooperHandler = mainLooperHandler;
        this.deviceInfoUtility = deviceInfoUtility;
        setView(view);
        setDuration(i);
    }

    private final WindowManager.LayoutParams createLayoutParams(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.firetv_toast_margin);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, dimensionPixelSize, dimensionPixelSize, WindowParamsHelper.INSTANCE.getWindowType(this.deviceInfoUtility), 152, -3);
        layoutParams.gravity = 8388693;
        return layoutParams;
    }

    @VisibleForTesting
    public static /* synthetic */ void isDisplayed$annotations() {
    }

    private final void presentToast(final WindowManager windowManager, WindowManager.LayoutParams layoutParams) {
        windowManager.addView(getView(), layoutParams);
        View view = getView();
        TextView textView = view != null ? (TextView) view.findViewById(R.id.system_toast_message) : null;
        if (textView != null) {
            AccessibilityHelper accessibilityHelper = AccessibilityHelper.INSTANCE;
            Context context = this.context;
            CharSequence text = textView.getText();
            Intrinsics.checkExpressionValueIsNotNull(text, "tv.text");
            accessibilityHelper.announceForAccessibility1P(context, text);
        }
        this.isDisplayed = true;
        this.mainLooperHandler.postDelayed(new Runnable() { // from class: com.amazon.tarazed.ui.toast.SystemToast$presentToast$1
            @Override // java.lang.Runnable
            public final void run() {
                if (SystemToast.this.isDisplayed$TarazedAndroidLibrary_release()) {
                    windowManager.removeView(SystemToast.this.getView());
                }
                SystemToast.this.setDisplayed$TarazedAndroidLibrary_release(false);
            }
        }, getDuration() == 1 ? LONG_DELAY_MILLIS : 2000);
    }

    @Override // android.widget.Toast
    public void cancel() {
        Object systemService = this.context.getApplicationContext().getSystemService("window");
        if (systemService != null) {
            WindowManager windowManager = (WindowManager) systemService;
            if (!this.isDisplayed) {
                return;
            }
            windowManager.removeView(getView());
            this.isDisplayed = false;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }

    public final boolean isDisplayed$TarazedAndroidLibrary_release() {
        return this.isDisplayed;
    }

    public final void setDisplayed$TarazedAndroidLibrary_release(boolean z) {
        this.isDisplayed = z;
    }

    @Override // android.widget.Toast
    public void show() {
        Context applicationContext = this.context.getApplicationContext();
        Object systemService = applicationContext.getSystemService("window");
        if (systemService != null) {
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            presentToast((WindowManager) systemService, createLayoutParams(applicationContext));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }
}
