package com.amazon.alexa.drive.service;

import androidx.annotation.NonNull;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.main.routing.DriveModeRoutes;
import com.amazon.alexa.drive.main.v2.DriveModeMainActivityCompanionImpl;
import com.amazon.alexa.drivemode.api.DriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.data.RouteName;
import dagger.Lazy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes7.dex */
public class DefaultDriveModeService implements DriveModeService {
    private static final String TAG = "DefaultDriveModeService";
    private Set<DriveModeCardsProvider> providers = new HashSet();

    public DefaultDriveModeService() {
        DriveModeDependencies.appInitialize();
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeService
    public void addCardsProvider(Lazy<DriveModeCardsProvider> lazy) {
        this.providers.add(lazy.mo358get());
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeService
    public DriveModeMainActivityCompanion createMainActivityCompanion() {
        return new DriveModeMainActivityCompanionImpl();
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeService
    @NonNull
    public List<Route> getAllRoutes() {
        List<Route> allRoutes = DriveModeRoutes.getAllRoutes();
        allRoutes.add(new Route.Builder("alexa-oobe/drive-mode/main", 13).asRoot().withParent(RouteName.MAIN).withContentMode(2).withTemplate("alexa-oobe/drive-mode/main").doNotKeepInBackStack().build());
        allRoutes.add(new Route.Builder("v2/alexa-oobe/drivemode-ftue-startup", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe/drivemode-ftue-startup").build());
        allRoutes.add(new Route.Builder("elements-accessory-echo-auto/oobe/driver-interaction-warning", 1).asRoot().withParent(RouteName.MAIN).withTemplate("elements-accessory-echo-auto/oobe/driver-interaction-warning").build());
        return allRoutes;
    }

    public Set<DriveModeCardsProvider> getProviders() {
        return this.providers;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeService
    public boolean useAMAViewManagerForDriveModeRoutes() {
        return true;
    }
}
