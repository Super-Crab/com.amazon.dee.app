package com.amazon.device.crashmanager.processor;

import com.amazon.device.crashmanager.Artifact;
import java.io.InputStream;
/* loaded from: classes12.dex */
public interface ArtifactProcessor {
    boolean canProcessTag(String str);

    InputStream processArtifact(Artifact artifact) throws Exception;

    InputStream processArtifact(Artifact artifact, String str) throws Exception;
}
