package com.amazon.alexa.drive.smart.device;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.landing.LandingPageRecyclerViewMarginDecoration;
import com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class SmartHomeViewController extends ViewManagerViewController {
    @Inject
    DriveModeThemeManager driveModeThemeManager;
    @VisibleForTesting
    SmartDeviceCardAdapter smartDeviceCardAdapter;
    @Inject
    SmartDevicePresenter smartDevicePresenter;

    private void closeSmartDevicePage() {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        RoutingService.RoutingBuilder route = routingService.route("drive-mode/home/index");
        if (!routingService.popFromBackStack("drive-mode/home/index")) {
            route.addToBackStack().navigate();
        }
    }

    private void initializeSmartDevices(View view) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.driveModeThemeManager.getTheme());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.smart_devices);
        recyclerView.setHasFixedSize(false);
        LandingPageRecyclerViewMarginDecoration landingPageRecyclerViewMarginDecoration = new LandingPageRecyclerViewMarginDecoration();
        landingPageRecyclerViewMarginDecoration.setDrawable(getView().getContext().getResources().getDrawable(R.drawable.landing_page_list_divider, contextThemeWrapper.getTheme()));
        recyclerView.addItemDecoration(landingPageRecyclerViewMarginDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.smartDeviceCardAdapter = new SmartDeviceCardAdapter(contextThemeWrapper, this.driveModeThemeManager);
        recyclerView.setAdapter(this.smartDeviceCardAdapter);
        view.findViewById(R.id.smart_device_close_view).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$SmartHomeViewController$_gwAgtsSTE_mTPzkGWuVPWRfe8Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SmartHomeViewController.this.lambda$initializeSmartDevices$0$SmartHomeViewController(view2);
            }
        });
        this.smartDevicePresenter.addSmartDeviceChangeListener(new SmartDeviceContract.DeviceUpdateListener() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$SmartHomeViewController$wr8HhfpMt_r-6_N8LEVkTjGk-wI
            @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.DeviceUpdateListener
            public final void onUpdate(List list) {
                SmartHomeViewController.this.lambda$initializeSmartDevices$1$SmartHomeViewController(list);
            }
        });
        this.smartDeviceCardAdapter.setOnclickListener(this.smartDevicePresenter);
        this.smartDevicePresenter.getSmartDeviceInfo();
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    protected DriveModeThemeManager getDriveModeThemeManager() {
        return this.driveModeThemeManager;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public String getTitle(Context context) {
        return context.getString(R.string.dm_smart_home_locks_title);
    }

    public /* synthetic */ void lambda$initializeSmartDevices$0$SmartHomeViewController(View view) {
        closeSmartDevicePage();
    }

    public /* synthetic */ void lambda$initializeSmartDevices$1$SmartHomeViewController(List list) {
        this.smartDeviceCardAdapter.setCards(list);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(layoutInflater.getContext(), this.driveModeThemeManager.getTheme()));
        ViewGroup viewGroup2 = (ViewGroup) super.makeView(cloneInContext, viewGroup);
        viewGroup2.addView(cloneInContext.inflate(R.layout.view_controller_smart_device, viewGroup, false));
        return viewGroup2;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        super.onAttach(view);
        initializeSmartDevices(view);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        super.onCreate(context);
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        super.onDetach(view);
    }
}
