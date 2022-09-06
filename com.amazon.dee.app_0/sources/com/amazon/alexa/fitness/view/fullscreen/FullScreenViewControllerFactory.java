package com.amazon.alexa.fitness.view.fullscreen;

import android.content.Context;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.AfxBackfillMessageProcessor;
import com.amazon.alexa.fitness.utils.DirectedIDBackfillService;
import com.amazon.alexa.fitness.view.message.CustomToast;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FullScreenViewControllerFactory.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewControllerFactory;", "Lcom/amazon/alexa/viewmanagement/api/ViewControllerFactory;", "Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "()V", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "directedIDBackfillService", "Lcom/amazon/alexa/fitness/utils/DirectedIDBackfillService;", "createView", "context", "Landroid/content/Context;", "routeContext", "Lcom/amazon/alexa/routing/api/RouteContext;", "eventNotifier", "Lcom/amazon/alexa/viewmanagement/api/ViewManagerEventNotifier;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FullScreenViewControllerFactory implements ViewControllerFactory<FullScreenViewController> {
    private final AfitsDataHelper afitsDataHelper = new AfitsDataHelper();
    private final DirectedIDBackfillService directedIDBackfillService = new DirectedIDBackfillService(new AfxBackfillMessageProcessor());

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    @NotNull
    /* renamed from: createView */
    public FullScreenViewController mo1459createView(@NotNull Context context, @NotNull RouteContext routeContext, @NotNull ViewManagerEventNotifier eventNotifier) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(routeContext, "routeContext");
        Intrinsics.checkParameterIsNotNull(eventNotifier, "eventNotifier");
        ThemeUtil.setTheme(context);
        CustomToast.Companion.setContext(context);
        FeatureService featureService = (FeatureService) ComponentRegistry.getInstance().get(FeatureService.class).get();
        AfitsDataHelper afitsDataHelper = this.afitsDataHelper;
        Intrinsics.checkExpressionValueIsNotNull(featureService, "featureService");
        return new FullScreenViewController(routeContext, afitsDataHelper, featureService, this.directedIDBackfillService);
    }
}
