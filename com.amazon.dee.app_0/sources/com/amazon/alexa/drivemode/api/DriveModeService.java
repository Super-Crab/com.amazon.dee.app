package com.amazon.alexa.drivemode.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.Route;
import dagger.Lazy;
import java.util.List;
/* loaded from: classes7.dex */
public interface DriveModeService {
    void addCardsProvider(Lazy<DriveModeCardsProvider> lazy);

    DriveModeMainActivityCompanion createMainActivityCompanion();

    @NonNull
    List<Route> getAllRoutes();

    boolean useAMAViewManagerForDriveModeRoutes();
}
