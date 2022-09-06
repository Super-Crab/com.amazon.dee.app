package com.amazon.tarazed.ui.border;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import com.amazon.tarazed.R;
import com.amazon.tarazed.ui.ViewGroupManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StaticBorderManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0015\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u000fR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/tarazed/ui/border/StaticBorderManager;", "", "context", "Landroid/content/Context;", "viewGroupManager", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "(Landroid/content/Context;Lcom/amazon/tarazed/ui/ViewGroupManager;)V", "borderView", "Landroid/view/View;", "windowManager", "Landroid/view/WindowManager;", "addBorder", "", "removeBorder", "updateContext", "updateContext$TarazedAndroidLibrary_release", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class StaticBorderManager {
    private final View borderView;
    private final Context context;
    private final ViewGroupManager viewGroupManager;
    private WindowManager windowManager;

    public StaticBorderManager(@NotNull Context context, @NotNull ViewGroupManager viewGroupManager) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        this.context = context;
        this.viewGroupManager = viewGroupManager;
        this.borderView = this.viewGroupManager.inflateStaticView(R.layout.static_session_border);
        updateContext$TarazedAndroidLibrary_release(this.context);
    }

    public final void addBorder() {
        this.viewGroupManager.addViewToStaticViewGroup(this.borderView, ViewGroupManager.ViewLayer.SESSION_BORDER);
    }

    public final void removeBorder() {
        this.viewGroupManager.removeViewFromStaticViewGroup(this.borderView);
    }

    public final void updateContext$TarazedAndroidLibrary_release(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("window");
        if (systemService != null) {
            this.windowManager = (WindowManager) systemService;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }
}
