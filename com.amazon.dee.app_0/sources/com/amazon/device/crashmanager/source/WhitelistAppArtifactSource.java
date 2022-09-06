package com.amazon.device.crashmanager.source;

import android.os.DropBoxManager;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.crashmanager.WhitelistAppArtifact;
import com.amazon.device.crashmanager.utils.CrashTimestampUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class WhitelistAppArtifactSource extends DropBoxArtifactSource {
    private static Map<String, Pattern> mWhitelistedAppPatternPerTag = new HashMap();

    public WhitelistAppArtifactSource(DropBoxManager dropBoxManager, CrashTimestampUtil crashTimestampUtil, Map<String, Pattern> map) {
        super(dropBoxManager, crashTimestampUtil, map.keySet());
        mWhitelistedAppPatternPerTag = map;
    }

    public static Pattern getWhiteListedAppPatternsForTag(String str) {
        return mWhitelistedAppPatternPerTag.get(str);
    }

    @Override // com.amazon.device.crashmanager.source.DropBoxArtifactSource, com.amazon.device.crashmanager.source.ArtifactSource
    public Artifact getNextArtifact(MetricEvent metricEvent, String str) {
        Artifact nextArtifact = super.getNextArtifact(metricEvent, str);
        if (nextArtifact != null) {
            return new WhitelistAppArtifact(nextArtifact.getTag(), nextArtifact.getInputStream(), nextArtifact.getCreationTimeUTCMillis());
        }
        return null;
    }
}
