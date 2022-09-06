package com.amazon.alexa.voice.enablement;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.voice.ui.UiComponents;
/* loaded from: classes11.dex */
public class ComponentEnabler {
    private final AlexaServicesConnection alexaServicesConnection;
    private final Context context;
    private final PackageManager packageManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComponentEnabler(Context context, AlexaServicesConnection alexaServicesConnection) {
        this.context = context;
        this.packageManager = context.getPackageManager();
        this.alexaServicesConnection = alexaServicesConnection;
    }

    private void enableExternalUiComponents(boolean z) {
        int i = z ? 1 : 2;
        for (String str : UiComponents.EXTERNAL_COMPONENTS) {
            this.packageManager.setComponentEnabledSetting(new ComponentName(this.context.getPackageName(), str), i, 1);
        }
    }

    public void disableAlexaService() {
        this.alexaServicesConnection.disconnect();
        AlexaServices.disable(this.context);
    }

    public void disableUiComponents() {
        enableExternalUiComponents(false);
    }

    public void enable() {
        AlexaServices.enable(this.context);
        enableExternalUiComponents(true);
    }
}
