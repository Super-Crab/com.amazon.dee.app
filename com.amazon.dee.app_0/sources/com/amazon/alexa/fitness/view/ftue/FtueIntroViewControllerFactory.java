package com.amazon.alexa.fitness.view.ftue;

import android.content.Context;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FtueIntroViewControllerFactory.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/view/ftue/FtueIntroViewControllerFactory;", "Lcom/amazon/alexa/viewmanagement/api/ViewControllerFactory;", "Lcom/amazon/alexa/fitness/view/ftue/FtueIntroViewController;", "()V", "createView", "context", "Landroid/content/Context;", "permissionsService", "Lcom/amazon/alexa/permissions/api/PermissionsService;", "routeContext", "Lcom/amazon/alexa/routing/api/RouteContext;", "loadingDelegate", "Lcom/amazon/alexa/viewmanagement/api/ViewManagerLoadingDelegate;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FtueIntroViewControllerFactory implements ViewControllerFactory<FtueIntroViewController> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    @NotNull
    /* renamed from: createView */
    public FtueIntroViewController mo2381createView(@NotNull Context context, @NotNull PermissionsService permissionsService, @NotNull RouteContext routeContext, @NotNull ViewManagerLoadingDelegate loadingDelegate) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(permissionsService, "permissionsService");
        Intrinsics.checkParameterIsNotNull(routeContext, "routeContext");
        Intrinsics.checkParameterIsNotNull(loadingDelegate, "loadingDelegate");
        ThemeUtil.setTheme(context);
        return new FtueIntroViewController();
    }
}
