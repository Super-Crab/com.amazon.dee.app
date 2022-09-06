package com.amazon.device.crashmanager.source;

import android.os.DropBoxManager;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.device.crashmanager.metrics.MetricsConstants;
import com.amazon.device.crashmanager.utils.CrashTimestampUtil;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public class DropBoxArtifactSource implements ArtifactSource {
    private static final DPLogger log = new DPLogger("CrashManager.DropBoxArtifactSource");
    protected final CrashTimestampUtil mCrashTimestampUtil;
    private int mCurrentTagIdx = 0;
    protected final DropBoxManager mDropBoxManager;
    private final List<String> mTags;

    public DropBoxArtifactSource(DropBoxManager dropBoxManager, CrashTimestampUtil crashTimestampUtil, Set<String> set) {
        if (dropBoxManager != null) {
            if (crashTimestampUtil == null) {
                throw new IllegalArgumentException("CrashTimestampUtil cannot be null");
            }
            if (set != null) {
                this.mDropBoxManager = dropBoxManager;
                this.mCrashTimestampUtil = crashTimestampUtil;
                this.mTags = new ArrayList(set);
                Collections.sort(this.mTags);
                return;
            }
            throw new IllegalArgumentException("Tags cannot be null");
        }
        throw new IllegalArgumentException("DropBoxManager cannot be null");
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    public Artifact getNextArtifact(MetricEvent metricEvent, String str) {
        int i = this.mCurrentTagIdx;
        int i2 = 0;
        if (i < 0 || i >= this.mTags.size()) {
            this.mCurrentTagIdx = 0;
        }
        while (this.mCurrentTagIdx < this.mTags.size()) {
            String str2 = this.mTags.get(this.mCurrentTagIdx);
            long lastTimestamp = this.mCrashTimestampUtil.getLastTimestamp(str2, CrashManagerActions.BUILD_MAP);
            DropBoxManager.Entry nextEntry = this.mDropBoxManager.getNextEntry(str2, this.mCrashTimestampUtil.getLastTimestamp(str2, str));
            while (true) {
                if (nextEntry != null) {
                    long timeMillis = nextEntry.getTimeMillis();
                    if (CrashManagerActions.ARTIFACT_UPLOAD.equals(str) && timeMillis > lastTimestamp) {
                        log.info("getNextArtifact", GeneratedOutlineSupport1.outline75("Crash file for tag ", str2, " added to dropbox after the dedupe iteration. No further crashes will be processed for this tag. Continuing with next tag."), new Object[i2]);
                        nextEntry.close();
                        break;
                    }
                    this.mCrashTimestampUtil.updateLastTimestamp(str2, str, timeMillis);
                    if (((nextEntry.getFlags() & 1) == 0 ? 1 : i2) != 0) {
                        try {
                            return new Artifact(nextEntry.getTag(), nextEntry.getInputStream(), nextEntry.getTimeMillis());
                        } catch (IOException e) {
                            log.error("getNextArtifact", "Error retrieving data from DropBox", e);
                            metricEvent.addCounter(MetricsConstants.COUNTER_DROPBOX_FILE_IO_EXCEPTION, 1.0d);
                            metricEvent.addString(MetricsConstants.STRING_DROPBOX_FILE_IO_EXCEPTION, "IOException while fetching DropBox entry InputStream");
                            i2 = 0;
                        } catch (IllegalArgumentException e2) {
                            log.error("getNextArtifact", "Unable to fetch entry from DropBox", e2);
                            i2 = 0;
                        }
                    }
                    nextEntry.close();
                    nextEntry = this.mDropBoxManager.getNextEntry(str2, timeMillis);
                }
            }
            this.mCurrentTagIdx++;
        }
        return null;
    }

    public void saveBuildMapIndex() {
        this.mCrashTimestampUtil.saveBuildMapIndex();
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    public void saveCurrentIndex() {
        this.mCrashTimestampUtil.saveCurrentIndex();
    }
}
