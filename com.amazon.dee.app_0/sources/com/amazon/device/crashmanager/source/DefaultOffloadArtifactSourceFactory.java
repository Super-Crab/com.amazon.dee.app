package com.amazon.device.crashmanager.source;

import android.os.DropBoxManager;
import com.amazon.device.crashmanager.utils.CrashTimestampUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public class DefaultOffloadArtifactSourceFactory implements ArtifactSourceFactory {
    private final CrashTimestampUtil mCrashTimestampUtil;
    private final DropBoxManager mDropBoxManager;
    private final Set<String> mDropBoxTags;

    public DefaultOffloadArtifactSourceFactory(SettingsStore settingsStore, DropBoxManager dropBoxManager, Set<String> set) {
        if (settingsStore != null) {
            if (dropBoxManager == null) {
                throw new IllegalArgumentException("DropBoxManager must not be null.");
            }
            if (set != null) {
                this.mDropBoxManager = dropBoxManager;
                this.mDropBoxTags = set;
                this.mCrashTimestampUtil = CrashTimestampUtil.getInstance(settingsStore);
                return;
            }
            throw new IllegalArgumentException("dropBoxTags must not be null.");
        }
        throw new IllegalArgumentException("SettingsStore must not be null.");
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSourceFactory
    public List<ArtifactSource> constructArtifactSources() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new DropBoxArtifactSource(this.mDropBoxManager, this.mCrashTimestampUtil, this.mDropBoxTags));
        return linkedList;
    }
}
