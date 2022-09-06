package com.amazon.device.crashmanager.processor;

import android.os.Build;
import com.amazon.client.metrics.common.MetricEvent;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class PinFailuresArtifactProcessor extends AbstractDetArtifactProcessor {
    public static final Set<String> PIN_FAILURE_ARTIFACT_TAGS;
    private final DetUtil.HeaderProcessor mCertificatePinFailureHeaderProcessor;
    private final CrashDescriptorDedupeUtil mCrashDedupeUtil;
    private MetricEvent mMetricEvent;

    static {
        HashSet hashSet = new HashSet(2);
        hashSet.add("exp_det_cert_pin_failure");
        PIN_FAILURE_ARTIFACT_TAGS = Collections.unmodifiableSet(hashSet);
    }

    public PinFailuresArtifactProcessor(DetUtil detUtil, String str, Map<String, String> map, MetricEvent metricEvent, CrashDescriptorDedupeUtil crashDescriptorDedupeUtil) {
        super(detUtil, str, map);
        this.mCertificatePinFailureHeaderProcessor = this.mDefaultHeaderProcessor;
        this.mCrashDedupeUtil = crashDescriptorDedupeUtil;
        this.mMetricEvent = metricEvent;
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor
    protected void addSpecificHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception {
        this.mCertificatePinFailureHeaderProcessor.process("ContentType", "PinFailure", writer);
        this.mCertificatePinFailureHeaderProcessor.process("OsBuildNumber", Build.VERSION.INCREMENTAL, writer);
        String calculateCrashDescriptorFromTrace = CrashDescriptorUtil.calculateCrashDescriptorFromTrace(bufferedReader, writer, this.mCertificatePinFailureHeaderProcessor, null, CrashManagerActions.PIN_FAILURE, this.mBuffer, null, this.mMetricEvent);
        this.mCertificatePinFailureHeaderProcessor.process("CrashDescriptor", calculateCrashDescriptorFromTrace, writer);
        CrashReportUtil.addCrashDuplicateHeader(artifact.getTag(), calculateCrashDescriptorFromTrace, this.mCrashDedupeUtil, writer, this.mCertificatePinFailureHeaderProcessor);
        artifact.setCrashDescriptor(calculateCrashDescriptorFromTrace);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public boolean canProcessTag(String str) {
        return PIN_FAILURE_ARTIFACT_TAGS.contains(str);
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
