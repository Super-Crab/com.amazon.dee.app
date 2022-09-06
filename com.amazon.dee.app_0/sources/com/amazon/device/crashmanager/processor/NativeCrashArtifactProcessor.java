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
public class NativeCrashArtifactProcessor extends AbstractDetArtifactProcessor {
    public static final Set<String> NATIVE_CRASH_ARTIFACT_TAGS;
    private final CrashDescriptorDedupeUtil mCrashDedupeUtil;
    private final MetricsHeaderProcessor mMetricsHeaderProcessor;
    private final DetUtil.HeaderProcessor mNativeHeaderProcessor;
    public static final Pattern NATIVE_APP_NAME_REGEX = Pattern.compile("^pid: .+>>> (.+?) <<<.*$");
    public static final Pattern STACKFRAME_IDENTIFIER = Pattern.compile("#\\d+\\s+pc\\s+[\\w\\d]+\\s+([^\\+^\\r\\n]+)");

    static {
        HashSet hashSet = new HashSet(4);
        hashSet.add("SYSTEM_TOMBSTONE");
        hashSet.add("data_native_crash");
        hashSet.add("data_native_exception");
        NATIVE_CRASH_ARTIFACT_TAGS = Collections.unmodifiableSet(hashSet);
    }

    public NativeCrashArtifactProcessor(DetUtil detUtil, String str, Map<String, String> map, MetricsHeaderProcessorFactory metricsHeaderProcessorFactory, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil) {
        super(detUtil, str, map);
        if (metricsHeaderProcessorFactory != null) {
            if (crashDescriptorDedupeUtil != null) {
                this.mMetricsHeaderProcessor = metricsHeaderProcessorFactory.construct(this.mHeaderWriter);
                HashMap hashMap = new HashMap(2);
                hashMap.put("Process", this.mMetricsHeaderProcessor);
                hashMap.put("hasForegroundActivities", this.mMetricsHeaderProcessor);
                this.mNativeHeaderProcessor = new DetUtil.DefaultHeaderProcessor(hashMap, this.mHeaderWriter);
                this.mCrashDedupeUtil = crashDescriptorDedupeUtil;
                return;
            }
            throw new IllegalArgumentException("CrashDedupeUtil must not be null.");
        }
        throw new IllegalArgumentException("Metrics header processor factory must not be null.");
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor
    protected void addSpecificHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception {
        this.mMetricsHeaderProcessor.setProcessTag(artifact.getTag());
        this.mMetricsHeaderProcessor.setAction(str);
        this.mNativeHeaderProcessor.process("ContentType", "NativeCrash", writer);
        String processHeaders = this.mDetUtil.processHeaders(bufferedReader, writer, this.mNativeHeaderProcessor);
        if (TextUtils.isEmpty(processHeaders)) {
            processHeaders = CrashDescriptorUtil.calculateCrashDescriptorFromTrace(bufferedReader, writer, this.mNativeHeaderProcessor, null, CrashManagerActions.NATIVE_CRASH, this.mBuffer, this.mMetricsHeaderProcessor, null);
        } else {
            this.mNativeHeaderProcessor.process("Process", CrashDescriptorUtil.extractProcessNameFromNativeCrash(CrashDescriptorUtil.fetchBodyFromReader(bufferedReader, this.mBuffer)), writer);
        }
        this.mNativeHeaderProcessor.process("CrashDescriptor", processHeaders, writer);
        CrashReportUtil.addCrashDuplicateHeader(artifact.getTag(), processHeaders, this.mCrashDedupeUtil, writer, this.mNativeHeaderProcessor);
        artifact.setCrashDescriptor(processHeaders);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public boolean canProcessTag(String str) {
        return NATIVE_CRASH_ARTIFACT_TAGS.contains(str);
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
