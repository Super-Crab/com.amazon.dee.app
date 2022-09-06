package com.amazon.alexa.accessory.davs;

import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.io.InputStreamSource;
import com.amazon.alexa.accessory.io.RangeSource;
import com.amazon.alexa.accessory.io.Source;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/* loaded from: classes.dex */
final class DeviceArtifactSource implements Source {
    private final Source source;

    public DeviceArtifactSource(File file) throws IOException {
        this.source = new InputStreamSource(new FileInputStream(file));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.closeQuietly(this.source);
    }

    @Override // com.amazon.alexa.accessory.io.Source
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.source.read(bArr, i, i2);
    }

    public DeviceArtifactSource(File file, int i, int i2) throws IOException {
        this.source = new RangeSource(new InputStreamSource(new FileInputStream(file)), i, i2);
    }
}
