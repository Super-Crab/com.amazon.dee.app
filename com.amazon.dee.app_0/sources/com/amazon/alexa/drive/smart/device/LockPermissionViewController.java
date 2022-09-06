package com.amazon.alexa.drive.smart.device;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class LockPermissionViewController extends ViewManagerViewController {
    @Inject
    DriveModeThemeManager driveModeThemeManager;

    private void closeSmartDevicePage() {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        RoutingService.RoutingBuilder route = routingService.route("drive-mode/home/index");
        if (!routingService.popFromBackStack("drive-mode/home/index")) {
            route.addToBackStack().navigate();
        }
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    protected DriveModeThemeManager getDriveModeThemeManager() {
        return this.driveModeThemeManager;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public String getTitle(Context context) {
        return "";
    }

    public /* synthetic */ void lambda$onAttach$0$LockPermissionViewController(View view) {
        closeSmartDevicePage();
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(layoutInflater.getContext(), this.driveModeThemeManager.getTheme()));
        ViewGroup viewGroup2 = (ViewGroup) super.makeView(cloneInContext, viewGroup);
        viewGroup2.addView(cloneInContext.inflate(R.layout.view_controller_lock_permission, viewGroup, false));
        return viewGroup2;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        super.onAttach(view);
        view.findViewById(R.id.smart_device_close_view).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$LockPermissionViewController$wf-y7HyQvTqkJlaHDdvVlIk6i9E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LockPermissionViewController.this.lambda$onAttach$0$LockPermissionViewController(view2);
            }
        });
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
