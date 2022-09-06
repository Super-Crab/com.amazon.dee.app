package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.io.InputStreamSource;
import com.amazon.alexa.accessory.io.RangeSource;
import com.amazon.alexa.accessory.io.Source;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* loaded from: classes6.dex */
final class ZipEntrySource implements Source {
    private final Source source;
    private final ZipFile zipFile;

    public ZipEntrySource(File file, String str) throws IOException {
        this.zipFile = new ZipFile(file, 1);
        this.source = new InputStreamSource(this.zipFile.getInputStream(getEntry(this.zipFile, str)));
    }

    private static ZipEntry getEntry(ZipFile zipFile, String str) throws IOException {
        ZipEntry entry = zipFile.getEntry(str);
        if (entry != null) {
            return entry;
        }
        throw new IOException(GeneratedOutlineSupport1.outline75("Entry with a name `", str, "` does not exist!"));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.closeQuietly(this.source);
        IOUtils.closeQuietly(this.zipFile);
    }

    @Override // com.amazon.alexa.accessory.io.Source
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.source.read(bArr, i, i2);
    }

    public ZipEntrySource(File file, String str, int i, int i2) throws IOException {
        this.zipFile = new ZipFile(file, 1);
        this.source = new RangeSource(new InputStreamSource(this.zipFile.getInputStream(getEntry(this.zipFile, str))), i, i2);
    }
}
