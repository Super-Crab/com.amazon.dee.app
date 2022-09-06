package com.amazon.alexa.accessorykit.mode;

import com.amazon.alexa.accessory.avsclient.mode.ModeStatusChecker;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.mode.ModeService;
/* loaded from: classes6.dex */
public class ModeStatusWrapper implements ModeStatusChecker {
    private final FeatureChecker featureChecker;
    private final ModeService modeService;

    public ModeStatusWrapper(ModeService modeService, FeatureChecker featureChecker) {
        this.modeService = modeService;
        this.featureChecker = featureChecker;
    }

    @Override // com.amazon.alexa.accessory.avsclient.mode.ModeStatusChecker
    public boolean isDriveModeForeground() {
        return this.featureChecker.hasAccess(AccessoryFeature.DRIVE_MODE) && this.modeService.isDriveModeForeground();
    }
}
