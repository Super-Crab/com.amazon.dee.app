package com.amazon.alexa.drive.warning;

import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.util.IdentityUtils;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class WarningViewController extends ViewManagerViewController {
    private static final String TAG = "WarningViewController";
    private static final double WARNING_LOGO_MARGIN_PERCENTAGE = 0.19718d;
    private static final String WARNING_PAGE_TITLE = "Driver Interaction Safety Waring Page";
    private static boolean showIngressWarningScreen;
    @Inject
    DriveModeThemeManager mDriveModeThemeManager;

    public WarningViewController(boolean z) {
        showIngressWarningScreen = z;
    }

    private void confirmWithPassenger() {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        if (routingService.getBackstack().length > 0) {
            routingService.navigateBackward();
        } else {
            routingService.navigate(RouteName.HOME);
        }
    }

    private void confirmWithPassengerAndLaunchAutoMode() {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        routingService.navigate("drive-mode/home/index");
    }

    private void setupWarningPageLayout(final View view) {
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_driver_interaction_warning_screen);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.alexa.drive.warning.WarningViewController.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                double measuredHeight = linearLayout.getMeasuredHeight() * WarningViewController.WARNING_LOGO_MARGIN_PERCENTAGE;
                ImageView imageView = (ImageView) view.findViewById(R.id.warning_logo);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                marginLayoutParams.setMargins(0, (int) measuredHeight, 0, 0);
                imageView.setLayoutParams(marginLayoutParams);
            }
        });
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    protected DriveModeThemeManager getDriveModeThemeManager() {
        return this.mDriveModeThemeManager;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return WARNING_PAGE_TITLE;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    protected boolean isDomainView() {
        return false;
    }

    public /* synthetic */ void lambda$makeView$0$WarningViewController(View view) {
        confirmWithPassengerAndLaunchAutoMode();
    }

    public /* synthetic */ void lambda$makeView$1$WarningViewController(View view) {
        confirmWithPassenger();
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        Log.i(TAG, "onCreateView");
        ViewGroup viewGroup2 = (ViewGroup) super.makeView(layoutInflater, viewGroup);
        View inflate = layoutInflater.cloneInContext(new ContextThemeWrapper(getContext(), R.style.Theme_Mosaic_Jasper)).inflate(R.layout.view_controller_driver_interaction_warning_screen, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.dm_warning_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.dm_warning_description);
        Button button = (Button) inflate.findViewById(R.id.passenger_confirm_button);
        if (showIngressWarningScreen) {
            textView.setText(R.string.dm_ingress_warning_title);
            textView2.setText(R.string.dm_ingress_warning_description);
            button.setText(R.string.dm_ingress_warning_button_text);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.warning.-$$Lambda$WarningViewController$SABz9yRXBvfMzLFD4oxMevvAxpo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WarningViewController.this.lambda$makeView$0$WarningViewController(view);
                }
            });
        } else {
            if (IdentityUtils.getUserMarketPlace().equals(Marketplace.JAPAN)) {
                textView2.setTextSize(18.0f);
            }
            textView.setText(R.string.dm_warning_title);
            textView2.setText(R.string.dm_warning_description);
            button.setText(R.string.dm_warning_button_text);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.warning.-$$Lambda$WarningViewController$UBk67ElMIqUuPsyJaTym3kl4rwo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WarningViewController.this.lambda$makeView$1$WarningViewController(view);
                }
            });
        }
        viewGroup2.addView(inflate);
        return viewGroup2;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        super.onAttach(view);
        setupWarningPageLayout(view);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        super.onCreate(context);
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
        this.mDriveModeThemeManager.init(context);
    }
}
