package com.amazon.alexa.smarthomecameras.ptz;

import android.view.View;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.dependencies.components.DaggerPtzComponent;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PtzModule;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.ptz.gestures.listeners.PtzListener;
import com.google.common.base.Preconditions;
/* loaded from: classes10.dex */
public class PtzListenerFactory {
    public PtzListener createPtzListener(View view, CameraViewContract.Presenter presenter, DevicePayload devicePayload) {
        Preconditions.checkNotNull(view, "View cannot be null");
        Preconditions.checkNotNull(presenter, "Presenter cannot be null");
        return DaggerPtzComponent.builder().contextModule(new ContextModule(view.getContext())).ptzModule(new PtzModule(view, presenter, devicePayload)).build().getPtzListener();
    }
}
