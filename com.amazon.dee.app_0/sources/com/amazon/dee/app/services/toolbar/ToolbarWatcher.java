package com.amazon.dee.app.services.toolbar;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.RoutingViewService;
import com.amazon.dee.app.services.logging.Log;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class ToolbarWatcher {
    public static final String TAG = Log.tag(ToolbarWatcher.class);
    private final CrashMetadata crashMetadata;
    private Disposable onToolbarRegisteredSubscription = null;
    private Disposable onViewChangedSubscription = null;
    private final RoutingService routingService;
    private final ToolbarService toolbarService;
    private final RoutingViewService viewService;

    public ToolbarWatcher(RoutingService routingService, ToolbarService toolbarService, RoutingViewService routingViewService, CrashMetadata crashMetadata) {
        this.toolbarService = toolbarService;
        this.routingService = routingService;
        this.viewService = routingViewService;
        this.crashMetadata = crashMetadata;
    }

    @VisibleForTesting
    String getUriFromRoute(RouteContext routeContext) {
        String uri = routeContext.toUri();
        Object obj = routeContext.get(RouteParameter.ROUTE);
        return (uri == null || !uri.equals("web") || obj == null) ? uri : obj.toString();
    }

    @VisibleForTesting
    boolean isActive(String str, ToolbarOptions toolbarOptions, boolean z) {
        if (toolbarOptions == null || toolbarOptions.getVisibilityOptions() == null) {
            return true;
        }
        VisibilityOptions visibilityOptions = toolbarOptions.getVisibilityOptions();
        for (String str2 : visibilityOptions.getBlacklist()) {
            if (Pattern.compile(str2).matcher(str).find()) {
                return false;
            }
        }
        for (String str3 : visibilityOptions.getWhitelist()) {
            if (Pattern.compile(str3).matcher(str).find()) {
                return true;
            }
        }
        if (visibilityOptions.getRootRoutesOnly() == null || !visibilityOptions.getRootRoutesOnly().booleanValue()) {
            if (!visibilityOptions.hasWhitelist()) {
                return true;
            }
        } else if (z) {
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$start$2$ToolbarWatcher(String str) throws Throwable {
        updateToolbars();
    }

    public void start() {
        if (this.toolbarService.getToolbarOptions() != null && this.toolbarService.getToolbarOptions().size() > 0) {
            lambda$start$0$ToolbarWatcher(this.routingService.getCurrentRoute());
        }
        if (this.onViewChangedSubscription == null) {
            this.onViewChangedSubscription = this.viewService.onViewChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.dee.app.services.toolbar.-$$Lambda$ToolbarWatcher$2BLhSI5Yp57FE5VWwWAA0-TDS_c
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    ToolbarWatcher.this.lambda$start$0$ToolbarWatcher((RouteContext) obj);
                }
            }, $$Lambda$ToolbarWatcher$M2iGEm2kZYjq2EUGK8llb1dkus.INSTANCE);
        }
        if (this.onToolbarRegisteredSubscription == null) {
            this.onToolbarRegisteredSubscription = this.toolbarService.onToolbarRegistered().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.dee.app.services.toolbar.-$$Lambda$ToolbarWatcher$uFyCvOWT8kcTsZUWesnR5qwelpM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    ToolbarWatcher.this.lambda$start$2$ToolbarWatcher((String) obj);
                }
            }, $$Lambda$ToolbarWatcher$pjSDBZhprgvl8iAT1pasEzGGHw.INSTANCE);
        }
    }

    public void stop() {
        Disposable disposable = this.onViewChangedSubscription;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.onToolbarRegisteredSubscription;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        this.onViewChangedSubscription = null;
        this.onToolbarRegisteredSubscription = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: updateRoute */
    public void lambda$start$0$ToolbarWatcher(RouteContext routeContext) {
        if (routeContext == null) {
            return;
        }
        String uriFromRoute = getUriFromRoute(routeContext);
        Boolean valueOf = Boolean.valueOf(routeContext.isFromMainMenu() || this.routingService.isMenuRoute(routeContext));
        Map.Entry[] entryArr = (Map.Entry[]) this.toolbarService.getToolbarOptions().entrySet().toArray(new Map.Entry[this.toolbarService.getToolbarOptions().size()]);
        for (int length = entryArr.length - 1; length >= 0; length--) {
            Map.Entry entry = entryArr[length];
            String str = (String) entry.getKey();
            ToolbarOptions toolbarOptions = (ToolbarOptions) entry.getValue();
            if (isActive(uriFromRoute, toolbarOptions, valueOf.booleanValue())) {
                this.crashMetadata.put("toolbar_visible", true);
                this.toolbarService.showToolbar(str, toolbarOptions.getViewOptions());
                return;
            }
        }
        this.toolbarService.hideToolbar();
        this.crashMetadata.put("toolbar_visible", false);
    }

    public void updateToolbars() {
        lambda$start$0$ToolbarWatcher(this.routingService.getCurrentRoute());
    }
}
