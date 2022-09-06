package com.amazon.alexa.smarthomecameras.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.dependencies.components.DaggerSmartAlertEventComponent;
import com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventComponent;
import com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventComponentProvider;
import com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventViewScope;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingServiceModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.SmartAlertEventModule;
import com.amazon.alexa.viewmanagement.api.ViewController;
import dagger.Component;
/* loaded from: classes10.dex */
public class SmartAlertEventViewController implements ViewController {
    private Context context;
    private String imageUrl;
    private RoutingService routingService;

    /* JADX INFO: Access modifiers changed from: package-private */
    @SmartAlertEventViewScope
    @Component(dependencies = {SmartAlertEventComponent.class}, modules = {SmartAlertEventModule.class})
    /* loaded from: classes10.dex */
    public interface Injector {
        void inject(SmartAlertEventViewController smartAlertEventViewController);
    }

    public SmartAlertEventViewController(Context context, RouteContext routeContext, RoutingService routingService) {
        this.context = context;
        this.routingService = routingService;
        this.imageUrl = routeContext.getString("imageUrl");
    }

    @VisibleForTesting
    void injectDependencies() {
        SmartAlertEventComponent build = DaggerSmartAlertEventComponent.builder().contextModule(new ContextModule(this.context)).routingServiceModule(new RoutingServiceModule(this.routingService)).build();
        SmartAlertEventComponentProvider.setSmartAlertEventComponent(build);
        DaggerSmartAlertEventViewController_Injector.builder().smartAlertEventComponent(build).smartAlertEventModule(new SmartAlertEventModule(this.imageUrl)).build().inject(this);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public View makeView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return null;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(Context context) {
        injectDependencies();
        Intent intent = new Intent(context, SmartAlertEventActivity.class);
        intent.putExtra("imageUrl", this.imageUrl);
        this.context.startActivity(intent);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDestroy(Context context) {
        SmartAlertEventComponentProvider.reset();
    }
}
