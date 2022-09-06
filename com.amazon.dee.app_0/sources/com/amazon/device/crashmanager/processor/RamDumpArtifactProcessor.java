package com.amazon.device.crashmanager.processor;

import android.os.Build;
import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.crashmanager.source.RamDumpArtifactSource;
import com.amazon.device.utils.det.DetUtil;
import com.amazon.dp.logger.DPLogger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Writer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class RamDumpArtifactProcessor extends AbstractDetArtifactProcessor {
    private static final int KMSG_LOG_MAX_FILE_SIZE = 262144;
    private static final int OOM_MESSAGE_GROUP = 1;
    private static final int OOM_PROCESSNAME_GROUP = 2;
    private final DetUtil.HeaderProcessor mRamDumpHeaderProcessor;
    private static final DPLogger log = new DPLogger("RamDumpArtifactProcessor");
    private static final Pattern RESET_CAUSE_REGEX = Pattern.compile("Oops -.*|(Out of memory:).*\\(([\\w\\.]+)\\)|Kernel panic -.*");

    public RamDumpArtifactProcessor(DetUtil detUtil, String str, Map<String, String> map) {
        super(detUtil, str, map);
        this.mRamDumpHeaderProcessor = this.mDefaultHeaderProcessor;
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor
    protected void addSpecificHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception {
        this.mRamDumpHeaderProcessor.process("OsBuildNumber", Build.VERSION.INCREMENTAL, writer);
        this.mRamDumpHeaderProcessor.process("ContentType", "Diagnostic", writer);
        this.mRamDumpHeaderProcessor.process("Build", Build.VERSION.INCREMENTAL, writer);
        extractResetCauseFromKmsgLog(bufferedReader, writer, this.mRamDumpHeaderProcessor);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public boolean canProcessTag(String str) {
        return str.equals(RamDumpArtifactSource.KMSG_LOG_TAG);
    }

    protected void extractResetCauseFromKmsgLog(BufferedReader bufferedReader, Writer writer, DetUtil.HeaderProcessor headerProcessor) throws Exception {
        Matcher matcher;
        String group;
        bufferedReader.mark(262144);
        do {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    matcher = RESET_CAUSE_REGEX.matcher(readLine);
                } else {
                    bufferedReader.reset();
                    log.info("extractResetCauseFromKmsgLog", "No reset cause found in log file.", new Object[0]);
                    return;
                }
            } finally {
                bufferedReader.reset();
            }
        } while (!matcher.find());
        if (matcher.group(1) != null && matcher.group(2) != null) {
            group = matcher.group(1) + matcher.group(2);
        } else {
            group = matcher.group();
        }
        log.verbose("extractResetCauseFromKmsgLog", "kernel panic reset cause found", "reset cause", group);
        headerProcessor.process("CrashDescriptor", group, writer);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public /* bridge */ /* synthetic */ InputStream processArtifact(Artifact artifact) throws Exception {
        return super.processArtifact(artifact);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public /* bridge */ /* synthetic */ InputStream processArtifact(Artifact artifact, String str) throws Exception {
        return super.processArtifact(artifact, str);
    }
}
