package org.bouncycastle.gpg.keybox;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes4.dex */
class KeyBoxByteBuffer {
    private final ByteBuffer buffer;

    public KeyBoxByteBuffer(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyBoxByteBuffer wrap(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof KeyBoxByteBuffer) {
            return (KeyBoxByteBuffer) obj;
        }
        if (obj instanceof ByteBuffer) {
            return new KeyBoxByteBuffer((ByteBuffer) obj);
        }
        if (obj instanceof byte[]) {
            return wrap(ByteBuffer.wrap((byte[]) obj));
        }
        if (obj instanceof ByteArrayOutputStream) {
            return wrap(((ByteArrayOutputStream) obj).toByteArray());
        }
        if (!(obj instanceof InputStream)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not convert ");
            outline107.append(obj.getClass().getCanonicalName());
            outline107.append(" to KeyBoxByteBuffer");
            throw new IllegalStateException(outline107.toString());
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = ((InputStream) obj).read(bArr);
            if (read < 0) {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                return wrap(byteArrayOutputStream);
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public byte[] bN(int i) {
        if (i >= 0) {
            if (i > remaining()) {
                throw new IllegalArgumentException("size exceeds buffer remaining");
            }
            byte[] bArr = new byte[i];
            this.buffer.get(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("size less than 0");
    }

    public void consume(int i) {
        if (i <= remaining()) {
            while (true) {
                i--;
                if (i < 0) {
                    return;
                }
                this.buffer.get();
            }
        } else {
            throw new IllegalArgumentException("size exceeds buffer remaining");
        }
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public boolean hasRemaining() {
        return this.buffer.hasRemaining();
    }

    public int position() {
        return this.buffer.position();
    }

    public void position(int i) {
        this.buffer.position(i);
    }

    public byte[] rangeOf(int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 0 || i < 0) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline53("invalid range ", i, ":", i2));
        }
        if (i2 > this.buffer.limit()) {
            throw new IllegalArgumentException("range exceeds buffer remaining");
        }
        int position = this.buffer.position();
        this.buffer.position(i);
        byte[] bArr = new byte[i3];
        this.buffer.get(bArr);
        this.buffer.position(position);
        return bArr;
    }

    public int remaining() {
        return this.buffer.remaining();
    }

    public int size() {
        return this.buffer.limit() - 20;
    }

    public int u16() {
        return (u8() << 8) | u8();
    }

    public long u32() {
        return (u8() << 24) | (u8() << 16) | (u8() << 8) | u8();
    }

    public int u8() {
        return this.buffer.get() & 255;
    }
}
