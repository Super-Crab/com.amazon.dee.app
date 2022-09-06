package com.amazon.device.crashmanager.processor;

import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.utils.det.DetUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Writer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class DiagnosticArtifactProcessor extends AbstractDetArtifactProcessor {
    public static final Set<String> DIAGNOSTIC_ARTIFACT_TAGS;
    private final DetUtil.HeaderProcessor mDiagnosticHeaderProcessor;

    static {
        HashSet hashSet = new HashSet(6);
        hashSet.add("SYSTEM_BOOT");
        hashSet.add("SYSTEM_LAST_KMSG");
        hashSet.add("SYSTEM_RECOVERY_LOG");
        hashSet.add("SYSTEM_RESTART");
        hashSet.add("SYSTEM_BSP_DIAG");
        hashSet.add("PERF_WTF");
        DIAGNOSTIC_ARTIFACT_TAGS = Collections.unmodifiableSet(hashSet);
    }

    public DiagnosticArtifactProcessor(DetUtil detUtil, String str, Map<String, String> map) {
        super(detUtil, str, map);
        this.mDiagnosticHeaderProcessor = this.mDefaultHeaderProcessor;
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor
    protected void addSpecificHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception {
        this.mDiagnosticHeaderProcessor.process("ContentType", "Diagnostic", writer);
        this.mDetUtil.processHeaders(bufferedReader, writer, this.mDiagnosticHeaderProcessor);
    }

    @Override // com.amazon.device.crashmanager.processor.AbstractDetArtifactProcessor, com.amazon.device.crashmanager.processor.ArtifactProcessor
    public boolean canProcessTag(String str) {
        return DIAGNOSTIC_ARTIFACT_TAGS.contains(str);
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
