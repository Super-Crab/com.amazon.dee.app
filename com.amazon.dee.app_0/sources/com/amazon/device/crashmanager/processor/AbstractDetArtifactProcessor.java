package com.amazon.device.crashmanager.processor;

import com.amazon.device.crashmanager.Artifact;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.device.utils.det.DetUtil;
import com.amazon.device.utils.det.MfbsInputStream;
import com.amazon.dp.logger.DPLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;
/* loaded from: classes12.dex */
abstract class AbstractDetArtifactProcessor implements ArtifactProcessor {
    protected static final int ARTIFACT_BUFFER_SIZE = 25600;
    private static final DPLogger log = new DPLogger("AbstractDetArtifactProcessor");
    protected final char[] mBuffer;
    protected final DetUtil.HeaderProcessor mDefaultHeaderProcessor;
    protected final DetUtil mDetUtil;
    protected final String mDeviceType;
    protected final Map<String, String> mExtraInfo;
    protected final DetUtil.HeaderWriter mHeaderWriter;

    public AbstractDetArtifactProcessor(DetUtil detUtil, String str) {
        this(detUtil, str, null);
    }

    protected void addHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception {
        long creationTimeUTCMillis = artifact.getCreationTimeUTCMillis();
        String formatDate = this.mDetUtil.formatDate(creationTimeUTCMillis);
        this.mDefaultHeaderProcessor.process("StartTime", formatDate, writer);
        this.mDefaultHeaderProcessor.process("EndTime", formatDate, writer);
        this.mDefaultHeaderProcessor.process("CrashTimeUTC", Long.toString(creationTimeUTCMillis), writer);
        this.mDefaultHeaderProcessor.process("CrashType", artifact.getTag(), writer);
        addSpecificHeaders(artifact, bufferedReader, writer, str);
    }

    protected abstract void addSpecificHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer, String str) throws Exception;

    @Override // com.amazon.device.crashmanager.processor.ArtifactProcessor
    public abstract boolean canProcessTag(String str);

    @Override // com.amazon.device.crashmanager.processor.ArtifactProcessor
    public InputStream processArtifact(Artifact artifact) throws Exception {
        return processArtifact(artifact, CrashManagerActions.ARTIFACT_UPLOAD);
    }

    public AbstractDetArtifactProcessor(DetUtil detUtil, String str, Map<String, String> map) {
        this.mBuffer = new char[ARTIFACT_BUFFER_SIZE];
        if (detUtil != null) {
            if (str != null) {
                this.mDetUtil = detUtil;
                this.mDeviceType = str;
                this.mExtraInfo = map;
                this.mHeaderWriter = new DetUtil.HeaderWriter();
                this.mDefaultHeaderProcessor = new DetUtil.DefaultHeaderProcessor(Collections.emptyMap(), this.mHeaderWriter);
                return;
            }
            throw new IllegalArgumentException("DeviceType must not be null.");
        }
        throw new IllegalArgumentException("DetUtil must not be null.");
    }

    @Override // com.amazon.device.crashmanager.processor.ArtifactProcessor
    public InputStream processArtifact(Artifact artifact, String str) throws Exception {
        if (str != null) {
            if (!str.equals(CrashManagerActions.BUILD_MAP) && !str.equals(CrashManagerActions.ARTIFACT_UPLOAD)) {
                throw new IllegalArgumentException("Unknown Action!");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(artifact.getInputStream()), ARTIFACT_BUFFER_SIZE);
            try {
                MfbsInputStream mfbsInputStream = new MfbsInputStream(bufferedReader);
                Writer logFileWriter = mfbsInputStream.getLogFileWriter();
                this.mDetUtil.addMetadataSectionHeader(this.mDefaultHeaderProcessor, this.mDeviceType, this.mExtraInfo, logFileWriter);
                addHeaders(artifact, bufferedReader, logFileWriter, str);
                this.mDetUtil.addEventsSectionHeader(logFileWriter);
                mfbsInputStream.pump();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.warn("constructCrashUploadMessage", "Error closing crash report file", e);
                }
                return mfbsInputStream;
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    log.warn("constructCrashUploadMessage", "Error closing crash report file", e2);
                }
                throw th;
            }
        }
        throw new IllegalArgumentException("Action Cannot be NULL");
    }

    protected void addHeaders(Artifact artifact, BufferedReader bufferedReader, Writer writer) throws Exception {
        addHeaders(artifact, bufferedReader, writer, CrashManagerActions.ARTIFACT_UPLOAD);
    }
}
