package com.amazon.device.crashmanager.processor;

import android.text.TextUtils;
import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.device.crashmanager.utils.CrashDescriptorDedupeUtil;
import com.amazon.device.crashmanager.utils.CrashDescriptorUtil;
import com.amazon.device.crashmanager.utils.CrashReportUtil;
import com.amazon.device.utils.det.DetUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class AnrArtifactProcessor extends AbstractDetArtifactProcessor {
    public static final Set<String> ANR_ARTIFACT_TAGS;
    public static final int MIN_NUM_LINES_TO_BE_READ = 10;
    private static final String REGEX_STACK_TRACE_DATA = "(at\\s.*\\(.*\\))";
    public static final Pattern REGEX_STACK_TRACE_PATTERN = Pattern.compile(REGEX_STACK_TRACE_DATA);
    private final DetUtil.HeaderProcessor mAnrHeaderProcessor;
    private final CrashDescriptorDedupeUtil mCrashDedupeUtil;
    private final MetricsHeaderProcessor mMetricsHeaderProcessor;

    static {
        HashSet hashSet = new HashSet(8);
        hashSet.add("system_app_anr");
        hashSet.add("data_app_anr");
        hashSet.add("system_server_anr");
        ANR_ARTIFACT_TAGS = Collections.unmodifiableSet(hashSet);
    }

    public AnrArtifactProcessor(DetUtil detUtil, String str, Map<String, String> map, MetricsHeaderProcessorFactory metricsHeaderProcessorFactory, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil) {
        super(detUtil, str, map);
        if (metricsHeaderProcessorFactory != null) {
            if (crashDescriptorDedupeUtil != null) {
                this.mMetricsHeaderProcessor = metricsHeaderProcessorFactory.construct(this.mHeaderWriter);
                HashMap hashMap = new HashMap(3);
                hashMap.put("Process", this.mMetricsHeaderProcessor);
                hashMap.put("hasForegroundActivities", this.mMetricsHeaderProcessor);
                hashMap.put("Package", new ExtractJavaVersionHeaderProcessor(this.mHeaderWriter));
                this.mAnrHeaderProcessor = new DetUtil.DefaultHeaderProcessor(hashMap, this.mHeaderWriter);
                this.mCrashDedupeUtil = crashDescriptorDedupeUtil;
                return;
            }
            throw new IllegalArgumentException("Duplicate count must not be null.");
        }
        throw new IllegalArgumentException("Metrics header processor factory must not be null.");
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor
    protected void addSpecificHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception {
        this.mMetricsHeaderProcessor.setProcessTag(artifact.getTag());
        this.mMetricsHeaderProcessor.setAction(str);
        this.mAnrHeaderProcessor.process("ContentType", "JavaCrash", writer);
        String processHeaders = this.mDetUtil.processHeaders(bufferedReader, writer, this.mAnrHeaderProcessor);
        if (TextUtils.isEmpty(processHeaders)) {
            processHeaders = CrashDescriptorUtil.calculateCrashDescriptorFromTrace(bufferedReader, writer, this.mAnrHeaderProcessor, null, CrashManagerActions.ANR, this.mBuffer, this.mMetricsHeaderProcessor, null);
        }
        this.mAnrHeaderProcessor.process("CrashDescriptor", processHeaders, writer);
        CrashReportUtil.addCrashDuplicateHeader(artifact.getTag(), processHeaders, this.mCrashDedupeUtil, writer, this.mAnrHeaderProcessor);
        artifact.setCrashDescriptor(processHeaders);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public boolean canProcessTag(String str) {
        return ANR_ARTIFACT_TAGS.contains(str);
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
