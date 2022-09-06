package com.amazon.device.crashmanager.source;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.crashmanager.metrics.MetricsConstants;
import com.amazon.device.crashmanager.source.SettingsStore;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public class RamDumpArtifactSource implements ArtifactSource {
    private static final long DEFAULT_LAST_RAMDUMP_TIME_INDEX = 0;
    static final String KMSG_LOG_FILE = "kmsg.log";
    public static final String KMSG_LOG_TAG = "RAMDUMP_KMSG_LOG";
    static final Set<String> RAMDUMP_FOLDER_NAMES;
    private static final String RAMDUMP_PARTITION = "/ramdump";
    private static final DPLogger log = new DPLogger("RamDumpArtifactSource");
    private final File[] mAvailableRamDumpDirectories;
    private long mLastRamDumpTimeIndex;
    private final SettingsStore mSettingsStore;

    /* loaded from: classes12.dex */
    private static final class LastModifiedFileComparator implements Comparator<File> {
        private LastModifiedFileComparator() {
        }

        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
        }
    }

    /* loaded from: classes12.dex */
    private class RamDumpDirectoryFilter implements FilenameFilter {
        private RamDumpDirectoryFilter() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return RamDumpArtifactSource.RAMDUMP_FOLDER_NAMES.contains(str) && new File(file, str).isDirectory();
        }
    }

    static {
        HashSet hashSet = new HashSet(4);
        hashSet.add("rd1");
        hashSet.add("rd2");
        hashSet.add("rd3");
        RAMDUMP_FOLDER_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public RamDumpArtifactSource(SettingsStore settingsStore) {
        this(RAMDUMP_PARTITION, settingsStore);
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    public Artifact getNextArtifact(MetricEvent metricEvent, String str) {
        File[] fileArr = this.mAvailableRamDumpDirectories;
        if (fileArr == null) {
            log.info("getNextArtifact", "no ramdumps exist", new Object[0]);
            return null;
        }
        for (File file : fileArr) {
            if (file.lastModified() > this.mLastRamDumpTimeIndex) {
                this.mLastRamDumpTimeIndex = file.lastModified();
                try {
                    return new Artifact(KMSG_LOG_TAG, new FileInputStream(new File(file, KMSG_LOG_FILE)), file.lastModified());
                } catch (FileNotFoundException unused) {
                    log.warn("getNextArtifact", GeneratedOutlineSupport1.outline63("No kernel message log file exists in ramdump directory", file), new Object[0]);
                    metricEvent.addCounter(MetricsConstants.COUNTER_KMSG_FILE_NOT_FOUND_EXCEPTION, 1.0d);
                }
            }
        }
        return null;
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    public void saveCurrentIndex() {
        this.mSettingsStore.saveLong(SettingsStore.SettingsKey.RAMDUMP_TIME_INDEX_KEY, this.mLastRamDumpTimeIndex);
    }

    public RamDumpArtifactSource(String str, SettingsStore settingsStore) {
        if (settingsStore != null) {
            if (str != null) {
                this.mSettingsStore = settingsStore;
                this.mLastRamDumpTimeIndex = this.mSettingsStore.getLong(SettingsStore.SettingsKey.RAMDUMP_TIME_INDEX_KEY, 0L);
                File file = new File(str);
                if (!file.isDirectory()) {
                    log.info("RamDumpArtifactSource", "RamDump partition is missing", new Object[0]);
                }
                this.mAvailableRamDumpDirectories = file.listFiles(new RamDumpDirectoryFilter());
                File[] fileArr = this.mAvailableRamDumpDirectories;
                if (fileArr == null) {
                    return;
                }
                Arrays.sort(fileArr, new LastModifiedFileComparator());
                return;
            }
            throw new IllegalArgumentException("ramdumpPartition cannot be null");
        }
        throw new IllegalArgumentException("SettingsStore cannot be null");
    }
}
