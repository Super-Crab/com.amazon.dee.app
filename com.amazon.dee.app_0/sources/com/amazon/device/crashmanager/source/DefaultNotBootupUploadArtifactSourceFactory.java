package com.amazon.device.crashmanager.source;

import android.os.DropBoxManager;
import com.amazon.device.crashmanager.utils.CrashTimestampUtil;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class DefaultNotBootupUploadArtifactSourceFactory extends DefaultUploadArtifactSourceFactory {
    private final CrashTimestampUtil mCrashTimestampUtil;
    private DropBoxManager mDropBoxManager;
    private final Set<String> mDropBoxTags;
    private final Map<String, Pattern> mWhitelistedAppPatternsPerTag;

    public DefaultNotBootupUploadArtifactSourceFactory(SettingsStore settingsStore, DropBoxManager dropBoxManager, Set<String> set, Map<String, Pattern> map) {
        super(settingsStore);
        if (dropBoxManager != null) {
            if (set != null) {
                this.mDropBoxManager = dropBoxManager;
                this.mDropBoxTags = set;
                this.mWhitelistedAppPatternsPerTag = map;
                this.mCrashTimestampUtil = CrashTimestampUtil.getInstance(settingsStore);
                return;
            }
            throw new IllegalArgumentException("dropBoxTags must not be null.");
        }
        throw new IllegalArgumentException("DropBoxManager must not be null.");
    }

    @Override // com.amazon.device.crashmanager.source.DefaultUploadArtifactSourceFactory, com.amazon.device.crashmanager.source.ArtifactSourceFactory
    public List<ArtifactSource> constructArtifactSources() {
        List<ArtifactSource> constructArtifactSources = super.constructArtifactSources();
        constructArtifactSources.add(new DropBoxArtifactSource(this.mDropBoxManager, this.mCrashTimestampUtil, this.mDropBoxTags));
        constructArtifactSources.add(new WhitelistAppArtifactSource(this.mDropBoxManager, this.mCrashTimestampUtil, this.mWhitelistedAppPatternsPerTag));
        return constructArtifactSources;
    }
}
