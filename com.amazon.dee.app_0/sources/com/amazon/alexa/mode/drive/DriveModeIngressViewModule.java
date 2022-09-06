package com.amazon.alexa.mode.drive;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.R;
import com.amazon.alexa.mode.util.Fonts;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.event.personalization.InteractionEventData;
import com.amazon.alexa.viewprovider.api.event.personalization.PersonalizationData;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
/* loaded from: classes9.dex */
public class DriveModeIngressViewModule implements ViewModule {
    private static final String TAG = "DriveModeIngressViewModule";
    private final Context context;
    private final EventCapture eventCapture;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DriveModeIngressViewModule(@NonNull Context context, EventCapture eventCapture) {
        this.context = context;
        this.eventCapture = eventCapture;
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public void cleanUp() {
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public View getView() {
        View inflate = LayoutInflater.from(new ContextThemeWrapper(this.context, R.style.Theme_Mosaic_Jasper)).inflate(R.layout.drive_mode_ingress_card, (ViewGroup) null);
        Fonts.EMBER_BOLD.apply((TextView) inflate.findViewById(R.id.drive_mode_card_title));
        Fonts.EMBER_REGULAR.apply((TextView) inflate.findViewById(R.id.drive_mode_card_description));
        final ModeService modeService = (ModeService) ComponentRegistry.getInstance().get(ModeService.class).orNull();
        if (modeService == null) {
            Log.w(TAG, "Mode Service not available. Cant create Ingress card");
            return null;
        }
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.mode.drive.-$$Lambda$DriveModeIngressViewModule$4wMX1MR8zD8M4ICgkECGThWhAds
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeIngressViewModule.this.lambda$getView$0$DriveModeIngressViewModule(modeService, view);
            }
        });
        return inflate;
    }

    public /* synthetic */ void lambda$getView$0$DriveModeIngressViewModule(ModeService modeService, View view) {
        if (this.eventCapture != null) {
            this.eventCapture.captureClick(new InteractionEventData.InteractionEventDataBuilder().withContentId("drive_mode_ingress").withContentType("drive_mode_ingress").withContentProvider("DriveMode").withActionType("NavigateAction").withMetaActionType("NavigateAction").withNavigateEvent(true).withPersonalizationData(new PersonalizationData.PersonalizationDataBuilder().withSection(Constants.HOME_CHANNEL_METRIC_SECTION).withIndex(0).withTotalNumberOfItems(0).build()).build());
        }
        modeService.startDriveMode(1);
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public void prepare() {
    }
}
