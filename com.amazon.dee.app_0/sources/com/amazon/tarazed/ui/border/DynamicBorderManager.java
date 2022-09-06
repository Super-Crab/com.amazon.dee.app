package com.amazon.tarazed.ui.border;

import android.view.View;
import com.amazon.tarazed.databinding.DynamicSessionBorderBinding;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.menu.databinding.BorderVisibilityObservable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DynamicBorderManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/tarazed/ui/border/DynamicBorderManager;", "", "viewGroupManager", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "borderVisibilityObservable", "Lcom/amazon/tarazed/ui/menu/databinding/BorderVisibilityObservable;", "(Lcom/amazon/tarazed/ui/ViewGroupManager;Lcom/amazon/tarazed/ui/menu/databinding/BorderVisibilityObservable;)V", "border", "Landroid/view/View;", "isBorderViewAdded", "", "addBorder", "", "removeBorder", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DynamicBorderManager {
    private final View border;
    private final BorderVisibilityObservable borderVisibilityObservable;
    private boolean isBorderViewAdded;
    private final ViewGroupManager viewGroupManager;

    public DynamicBorderManager(@NotNull ViewGroupManager viewGroupManager, @NotNull BorderVisibilityObservable borderVisibilityObservable) {
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        Intrinsics.checkParameterIsNotNull(borderVisibilityObservable, "borderVisibilityObservable");
        this.viewGroupManager = viewGroupManager;
        this.borderVisibilityObservable = borderVisibilityObservable;
        DynamicSessionBorderBinding borderBinding$TarazedAndroidLibrary_release = this.viewGroupManager.getBorderBinding$TarazedAndroidLibrary_release();
        this.border = borderBinding$TarazedAndroidLibrary_release != null ? borderBinding$TarazedAndroidLibrary_release.getRoot() : null;
        DynamicSessionBorderBinding borderBinding$TarazedAndroidLibrary_release2 = this.viewGroupManager.getBorderBinding$TarazedAndroidLibrary_release();
        if (borderBinding$TarazedAndroidLibrary_release2 != null) {
            borderBinding$TarazedAndroidLibrary_release2.setBorderVisible(this.borderVisibilityObservable);
        }
    }

    public final void addBorder() {
        if (!this.isBorderViewAdded) {
            this.viewGroupManager.addViewToStaticViewGroup(this.border, ViewGroupManager.ViewLayer.SESSION_BORDER);
            this.isBorderViewAdded = true;
        }
    }

    public final void removeBorder() {
        if (this.isBorderViewAdded) {
            this.viewGroupManager.removeViewFromStaticViewGroup(this.border);
            this.isBorderViewAdded = false;
        }
    }
}
