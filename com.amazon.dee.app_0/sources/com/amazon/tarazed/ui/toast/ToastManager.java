package com.amazon.tarazed.ui.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ToastManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/tarazed/ui/toast/ToastManager;", "", "context", "Landroid/content/Context;", "mainLooperHandler", "Landroid/os/Handler;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "(Landroid/content/Context;Landroid/os/Handler;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "toast", "Landroid/widget/Toast;", "view", "Landroid/view/View;", "displayToast", "", "text", "", "duration", "", "makeToast", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ToastManager {
    private final Context context;
    private final DeviceInfoUtility deviceInfoUtility;
    private final Handler mainLooperHandler;
    private Toast toast;
    private final View view;

    public ToastManager(@NotNull Context context, @NotNull Handler mainLooperHandler, @NotNull DeviceInfoUtility deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(mainLooperHandler, "mainLooperHandler");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        this.context = context;
        this.mainLooperHandler = mainLooperHandler;
        this.deviceInfoUtility = deviceInfoUtility;
        Object systemService = this.context.getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.firetv_toast, new FrameLayout(this.context));
            Intrinsics.checkExpressionValueIsNotNull(inflate, "layoutInflater.inflate(c…st, FrameLayout(context))");
            this.view = inflate;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"ShowToast"})
    public final void makeToast(CharSequence charSequence, int i) {
        Toast makeText;
        Toast toast = this.toast;
        if (toast != null) {
            toast.cancel();
        }
        if (this.deviceInfoUtility.is1PDevice()) {
            TextView tv = (TextView) this.view.findViewById(R.id.system_toast_message);
            Intrinsics.checkExpressionValueIsNotNull(tv, "tv");
            tv.setText(charSequence);
            makeText = new SystemToast(this.context, this.view, i, this.mainLooperHandler, this.deviceInfoUtility);
        } else {
            makeText = Toast.makeText(this.context, charSequence, i);
        }
        this.toast = makeText;
    }

    public final void displayToast(@NotNull final CharSequence text, final int i) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.mainLooperHandler.post(new Runnable() { // from class: com.amazon.tarazed.ui.toast.ToastManager$displayToast$1
            @Override // java.lang.Runnable
            public final void run() {
                Toast toast;
                ToastManager.this.makeToast(text, i);
                toast = ToastManager.this.toast;
                if (toast != null) {
                    toast.show();
                }
            }
        });
    }
}
