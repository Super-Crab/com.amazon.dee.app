package com.amazon.dee.app.dependencies;

import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.routing.api.RoutingAdapter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
@Module
/* loaded from: classes12.dex */
public class DriveModeMainModule {
    public static final String DRIVE_MODE_INGRESS_ROUTING_ADAPTER = "drive_mode_ingress_routing_adapter";

    @Provides
    @MainScope
    public DriveModeMainActivityCompanion provideDriveModeMainActivityCompanion(DriveModeService driveModeService) {
        return driveModeService.createMainActivityCompanion();
    }

    @Provides
    @MainScope
    @Named(DRIVE_MODE_INGRESS_ROUTING_ADAPTER)
    public RoutingAdapter provideDriveModeRoutingAdapter(DriveModeMainActivityCompanion driveModeMainActivityCompanion) {
        return driveModeMainActivityCompanion.getDriveModeIngressRoutingAdapter();
    }

    @Provides
    @MainScope
    public DriveModeMainActivityCompanion.ViewModel providesDriveModeViewModel(DriveModeMainActivityCompanion driveModeMainActivityCompanion) {
        return driveModeMainActivityCompanion.getMainActivityCompanionViewModel();
    }
}
