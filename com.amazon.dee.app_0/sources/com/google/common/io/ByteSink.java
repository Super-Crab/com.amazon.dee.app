package com.google.common.io;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
@GwtIncompatible
/* loaded from: classes3.dex */
public abstract class ByteSink {

    /* loaded from: classes3.dex */
    private final class AsCharSink extends CharSink {
        private final Charset charset;

        @Override // com.google.common.io.CharSink
        public Writer openStream() throws IOException {
            return new OutputStreamWriter(ByteSink.this.mo8234openStream(), this.charset);
        }

        public String toString() {
            String obj = ByteSink.this.toString();
            String valueOf = String.valueOf(this.charset);
            return GeneratedOutlineSupport1.outline31(valueOf.length() + GeneratedOutlineSupport1.outline6(obj, 13), obj, ".asCharSink(", valueOf, ")");
        }

        private AsCharSink(Charset charset) {
            this.charset = (Charset) Preconditions.checkNotNull(charset);
        }
    }

    public CharSink asCharSink(Charset charset) {
        return new AsCharSink(charset);
    }

    public OutputStream openBufferedStream() throws IOException {
        OutputStream mo8234openStream = mo8234openStream();
        if (mo8234openStream instanceof BufferedOutputStream) {
            return (BufferedOutputStream) mo8234openStream;
        }
        return new BufferedOutputStream(mo8234openStream);
    }

    /* renamed from: openStream */
    public abstract OutputStream mo8234openStream() throws IOException;

    public void write(byte[] bArr) throws IOException {
        Preconditions.checkNotNull(bArr);
        try {
            OutputStream outputStream = (OutputStream) Closer.create().register(mo8234openStream());
            outputStream.write(bArr);
            outputStream.flush();
        } finally {
        }
    }

    @CanIgnoreReturnValue
    public long writeFrom(InputStream inputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        try {
            OutputStream outputStream = (OutputStream) Closer.create().register(mo8234openStream());
            long copy = ByteStreams.copy(inputStream, outputStream);
            outputStream.flush();
            return copy;
        } finally {
        }
    }
}
