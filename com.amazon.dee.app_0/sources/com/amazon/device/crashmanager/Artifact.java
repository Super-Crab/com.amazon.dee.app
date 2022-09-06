package com.amazon.device.crashmanager;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class Artifact implements Closeable {
    private String mCrashDescriptor;
    private final long mCreationTimeUTCMillis;
    private final InputStream mInputStream;
    private Map<String, String> mMetadataMap = new HashMap();
    private final String mTag;

    public Artifact(String str, InputStream inputStream, long j) {
        if (str != null) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Artifact InputSteam cannot be null");
            }
            if (j >= 0) {
                this.mTag = str;
                this.mInputStream = inputStream;
                this.mCreationTimeUTCMillis = j;
                return;
            }
            throw new IllegalArgumentException("Creation Time cannot be negative");
        }
        throw new IllegalArgumentException("Artifact tag cannot be null");
    }

    public void addMetadata(String str, String str2) {
        this.mMetadataMap.put(str, str2);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mInputStream.close();
    }

    public String getCrashDescriptor() {
        return this.mCrashDescriptor;
    }

    public long getCreationTimeUTCMillis() {
        return this.mCreationTimeUTCMillis;
    }

    public InputStream getInputStream() {
        return this.mInputStream;
    }

    public Map<String, String> getMetadataMap() {
        return this.mMetadataMap;
    }

    public String getTag() {
        return this.mTag;
    }

    public void removeMetadata(String str) {
        this.mMetadataMap.remove(str);
    }

    public void setCrashDescriptor(String str) {
        this.mCrashDescriptor = str;
    }

    public boolean shouldUploadArtifact() {
        return true;
    }
}
