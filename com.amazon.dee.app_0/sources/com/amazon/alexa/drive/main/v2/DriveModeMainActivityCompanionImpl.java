package com.amazon.alexa.drive.main.v2;

import com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion;
import com.amazon.alexa.routing.api.RoutingAdapter;
/* loaded from: classes7.dex */
public class DriveModeMainActivityCompanionImpl implements DriveModeMainActivityCompanion {
    DriveModeIngressRoutingAdapter ingressRoutingAdapter;
    DriveModeMainActivityViewModel viewModel = new DriveModeMainActivityViewModel();

    public DriveModeMainActivityCompanionImpl() {
        final DriveModeMainActivityViewModel driveModeMainActivityViewModel = this.viewModel;
        driveModeMainActivityViewModel.getClass();
        this.ingressRoutingAdapter = new DriveModeIngressRoutingAdapter(new Runnable() { // from class: com.amazon.alexa.drive.main.v2.-$$Lambda$Y1WkjGsSaB1bXvASMLSyfmi7QxQ
            @Override // java.lang.Runnable
            public final void run() {
                DriveModeMainActivityViewModel.this.navigateToDefaultRoute();
            }
        });
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion
    public RoutingAdapter getDriveModeIngressRoutingAdapter() {
        return this.ingressRoutingAdapter;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeMainActivityCompanion
    public DriveModeMainActivityCompanion.ViewModel getMainActivityCompanionViewModel() {
        return this.viewModel;
    }
}
