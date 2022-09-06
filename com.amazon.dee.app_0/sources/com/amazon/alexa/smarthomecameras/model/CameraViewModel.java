package com.amazon.alexa.smarthomecameras.model;

import com.amazon.alexa.smarthomecameras.CameraViewContract;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public class CameraViewModel implements CameraViewContract.Model {
    private static final String DEFAULT_CAMERA_NAME = "";
    private static final boolean DEFAULT_MIC_ACTIVE = false;
    private static final boolean DEFAULT_SPEAKER_ACTIVE = false;
    private boolean cameraPreferencesFetched = false;
    private final Map<EntityId, CameraLabel> cameraLabels = new HashMap();
    private final Map<EntityId, Boolean> micActiveStates = new HashMap();
    private final Map<EntityId, Boolean> speakerActiveStates = new HashMap();
    private final Map<String, Boolean> subfeatureStates = new HashMap();

    private static <K, V> V getOrDefault(Map<K, V> map, K k, V v) {
        V v2 = map.get(k);
        return (v2 != null || map.containsKey(k)) ? v2 : v;
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public synchronized CameraLabel getCameraLabel(EntityId entityId) {
        return (CameraLabel) getOrDefault(this.cameraLabels, entityId, CameraLabel.create(""));
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public boolean getCameraPreferencesFetched() {
        return this.cameraPreferencesFetched;
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public Set<String> getEnabledSubfeatures() {
        HashSet hashSet = new HashSet();
        for (String str : this.subfeatureStates.keySet()) {
            if (this.subfeatureStates.get(str).booleanValue()) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public synchronized boolean isMicActive(EntityId entityId) {
        return ((Boolean) getOrDefault(this.micActiveStates, entityId, false)).booleanValue();
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public synchronized boolean isSpeakerActive(EntityId entityId) {
        return ((Boolean) getOrDefault(this.speakerActiveStates, entityId, false)).booleanValue();
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public synchronized void setCameraName(EntityId entityId, CameraLabel cameraLabel) {
        this.cameraLabels.put(entityId, cameraLabel);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public void setCameraPreferencesFetched(boolean z) {
        this.cameraPreferencesFetched = z;
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public synchronized void setMicActive(EntityId entityId, boolean z) {
        this.micActiveStates.put(entityId, Boolean.valueOf(z));
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public synchronized void setSpeakerActive(EntityId entityId, boolean z) {
        this.speakerActiveStates.put(entityId, Boolean.valueOf(z));
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Model
    public void setSubfeatureState(String str, boolean z) {
        this.subfeatureStates.put(str, Boolean.valueOf(z));
    }
}
