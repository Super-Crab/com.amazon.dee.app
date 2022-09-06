package com.amazon.alexa.smarthomecameras.session;

import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.model.SessionId;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public interface CameraSessionManager {
    void deregisterSessionListener(EntityId entityId, CameraSessionListener cameraSessionListener);

    void didBackground();

    void disconnectSession(EntityId entityId);

    @Nullable
    SessionId getActiveSessionId();

    void registerSessionListener(EntityId entityId, CameraSessionListener cameraSessionListener);

    void requestSessionForDevice(EntityId entityId);

    void sendData(EntityId entityId, String str);

    void sessionEnded(String str, String str2);

    void teardown();
}
