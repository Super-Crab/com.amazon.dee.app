package com.amazon.device.crashmanager.source;

import java.util.LinkedList;
import java.util.List;
/* loaded from: classes12.dex */
public class DefaultUploadArtifactSourceFactory implements ArtifactSourceFactory {
    protected SettingsStore mSettingsStore;

    public DefaultUploadArtifactSourceFactory(SettingsStore settingsStore) {
        if (settingsStore != null) {
            this.mSettingsStore = settingsStore;
            return;
        }
        throw new IllegalArgumentException("SettingsStore must not be null.");
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSourceFactory
    public List<ArtifactSource> constructArtifactSources() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new RamDumpArtifactSource(this.mSettingsStore));
        return linkedList;
    }
}
