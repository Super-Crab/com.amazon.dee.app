package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class InputChunked extends Input {
    private int chunkSize;

    public InputChunked() {
        super(2048);
        this.chunkSize = -1;
    }

    private void readChunkSize() {
        try {
            InputStream inputStream = getInputStream();
            int i = 0;
            for (int i2 = 0; i2 < 32; i2 += 7) {
                int read = inputStream.read();
                if (read == -1) {
                    throw new KryoException("Buffer underflow.");
                }
                i |= (read & 127) << i2;
                if ((read & 128) == 0) {
                    this.chunkSize = i;
                    if (!Log.TRACE) {
                        return;
                    }
                    Log.trace("kryo", "Read chunk: " + this.chunkSize);
                    return;
                }
            }
            throw new KryoException("Malformed integer.");
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.io.Input
    public int fill(byte[] bArr, int i, int i2) throws KryoException {
        int i3 = this.chunkSize;
        if (i3 == -1) {
            readChunkSize();
        } else if (i3 == 0) {
            return -1;
        }
        int fill = super.fill(bArr, i, Math.min(this.chunkSize, i2));
        this.chunkSize -= fill;
        if (this.chunkSize == 0) {
            readChunkSize();
        }
        return fill;
    }

    public void nextChunks() {
        if (this.chunkSize == -1) {
            readChunkSize();
        }
        while (true) {
            int i = this.chunkSize;
            if (i <= 0) {
                break;
            }
            skip(i);
        }
        this.chunkSize = -1;
        if (Log.TRACE) {
            Log.trace("kryo", "Next chunks.");
        }
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void rewind() {
        super.rewind();
        this.chunkSize = -1;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setBuffer(byte[] bArr, int i, int i2) {
        super.setBuffer(bArr, i, i2);
        this.chunkSize = -1;
    }

    @Override // com.esotericsoftware.kryo.io.Input
    public void setInputStream(InputStream inputStream) {
        super.setInputStream(inputStream);
        this.chunkSize = -1;
    }

    public InputChunked(int i) {
        super(i);
        this.chunkSize = -1;
    }

    public InputChunked(InputStream inputStream) {
        super(inputStream, 2048);
        this.chunkSize = -1;
    }

    public InputChunked(InputStream inputStream, int i) {
        super(inputStream, i);
        this.chunkSize = -1;
    }
}
