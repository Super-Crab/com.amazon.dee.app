package com.amazon.ptzcontroller.lib.model.api.ptz.directive;

import com.amazon.alexa.smarthomecameras.ptz.directives.PhysicalPtzDirectiveConstants;
import edu.umd.cs.findbugs.annotations.NonNull;
/* loaded from: classes13.dex */
public enum CameraPtzInstance {
    CAMERA_PAN(PhysicalPtzDirectiveConstants.CAMERA_PAN_INSTANCE_NAME),
    CAMERA_TILT(PhysicalPtzDirectiveConstants.CAMERA_TILT_INSTANCE_NAME),
    CAMERA_ZOOM("Camera.Zoom");
    
    private final String name;

    CameraPtzInstance(@NonNull String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
