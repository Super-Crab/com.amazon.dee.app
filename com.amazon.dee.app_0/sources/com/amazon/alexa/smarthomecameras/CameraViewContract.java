package com.amazon.alexa.smarthomecameras;

import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.model.SessionId;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public interface CameraViewContract {

    /* loaded from: classes10.dex */
    public interface Model {
        @Nullable
        CameraLabel getCameraLabel(EntityId entityId);

        boolean getCameraPreferencesFetched();

        Set<String> getEnabledSubfeatures();

        boolean isMicActive(EntityId entityId);

        boolean isSpeakerActive(EntityId entityId);

        void setCameraName(EntityId entityId, CameraLabel cameraLabel);

        void setCameraPreferencesFetched(boolean z);

        void setMicActive(EntityId entityId, boolean z);

        void setSpeakerActive(EntityId entityId, boolean z);

        void setSubfeatureState(String str, boolean z);
    }

    /* loaded from: classes10.dex */
    public interface Presenter {
        void onBackButtonClicked();

        void onMicToggleClicked();

        void onNetworkDisconnected();

        void onPermissionsChanged();

        void onPlayButtonClicked();

        void onSmartAlertsIngressClicked();

        void onSpeakerToggleClicked();

        void onViewAttached();

        void onViewDetached();

        void onViewHidden();

        void onViewPaused(boolean z);

        void onViewStarted();

        void sendData(String str);

        void setCameraView(View view);
    }

    /* loaded from: classes10.dex */
    public interface View {
        boolean areRequiredPermissionsGranted();

        void hideStatusText();

        void initializeCameraView(SessionId sessionId);

        boolean isMicEnabled();

        boolean isSpeakerEnabled();

        void notifyPreferencesFetched();

        void releaseCameraView();

        void requestPermissions();

        void setErrorVisible(boolean z, String str, String str2);

        void setLoadingVisible(boolean z);

        void setMicAndSpeakerLabelsGone();

        void setMicrophoneToggleState(boolean z);

        void setPlayButtonVisible(boolean z);

        void setSmartAlertsIngressVisible(boolean z);

        void setSpeakerToggleState(boolean z);
    }
}
