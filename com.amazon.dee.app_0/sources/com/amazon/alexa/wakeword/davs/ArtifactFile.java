package com.amazon.alexa.wakeword.davs;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.validation.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
/* loaded from: classes11.dex */
public class ArtifactFile {
    private final File artifactFile;

    public ArtifactFile(File file) {
        Preconditions.notNull(file, "the file is null");
        this.artifactFile = file;
    }

    @VisibleForTesting
    FileInputStream createInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    public File getArtifactFile() {
        return this.artifactFile;
    }

    public InputStream getArtifactInputStream() throws FileNotFoundException {
        return createInputStream(this.artifactFile);
    }

    public String getPath() {
        return this.artifactFile.getAbsolutePath();
    }
}
