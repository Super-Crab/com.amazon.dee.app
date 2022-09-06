package com.amazon.alexa.wakeword.davs;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.validation.Assertions;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
/* loaded from: classes11.dex */
public final class ArtifactNameFactory {
    @VisibleForTesting
    static final String DELIMITER = ".";
    @VisibleForTesting
    static final String EXT = "bin";

    private ArtifactNameFactory() {
        throw new UnsupportedOperationException("don't instantiate");
    }

    public static String getArtifactFilename(ArtifactInfo artifactInfo, String str) {
        Preconditions.notNull(artifactInfo, "artifactInfo is null");
        Preconditions.notNull(str, "artifactId is null");
        return getArtifactsDirectory(artifactInfo) + File.separator + getFilename(str);
    }

    private static String getArtifactsDirectory(ArtifactInfo artifactInfo) {
        String artifactType = artifactInfo.getArtifactType();
        Assertions.notEmpty(artifactType, "artifact type is empty");
        return artifactType;
    }

    private static String getFilename(String str) {
        return GeneratedOutlineSupport1.outline75(str, ".", EXT);
    }
}
