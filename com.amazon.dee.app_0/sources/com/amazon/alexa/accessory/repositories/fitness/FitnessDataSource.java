package com.amazon.alexa.accessory.repositories.fitness;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.Fitness;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class FitnessDataSource implements Source {
    private final byte[] continuationToken;
    private final Fitness.FitnessDataFormat format;
    private final byte[] sha256Checksum;
    private final Source source;

    public FitnessDataSource(Source source, Fitness.FitnessDataFormat fitnessDataFormat, byte[] bArr, byte[] bArr2) {
        Preconditions.notNull(source, "source");
        Preconditions.notNull(fitnessDataFormat, "format");
        this.source = source;
        this.format = fitnessDataFormat;
        this.sha256Checksum = bArr;
        this.continuationToken = bArr2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.source.close();
    }

    public byte[] getContinuationToken() {
        return this.continuationToken;
    }

    public Fitness.FitnessDataFormat getFormat() {
        return this.format;
    }

    public byte[] getSha256Checksum() {
        return this.sha256Checksum;
    }

    @Override // com.amazon.alexa.accessory.io.Source
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.source.read(bArr, i, i2);
    }
}
