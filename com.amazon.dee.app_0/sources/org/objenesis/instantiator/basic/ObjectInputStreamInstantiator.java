package org.objenesis.instantiator.basic;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
/* loaded from: classes5.dex */
public class ObjectInputStreamInstantiator<T> implements ObjectInstantiator<T> {
    private ObjectInputStream inputStream;

    /* loaded from: classes5.dex */
    private static class MockStream extends InputStream {
        private static byte[] HEADER;
        private static final int[] NEXT = {1, 2, 2};
        private static byte[] REPEATING_DATA;
        private final byte[] FIRST_DATA;
        private byte[][] buffers;
        private int pointer = 0;
        private int sequence = 0;
        private byte[] data = HEADER;

        static {
            initialize();
        }

        public MockStream(Class<?> cls) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeByte(115);
                dataOutputStream.writeByte(114);
                dataOutputStream.writeUTF(cls.getName());
                dataOutputStream.writeLong(ObjectStreamClass.lookup(cls).getSerialVersionUID());
                dataOutputStream.writeByte(2);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeByte(120);
                dataOutputStream.writeByte(112);
                this.FIRST_DATA = byteArrayOutputStream.toByteArray();
                this.buffers = new byte[][]{HEADER, this.FIRST_DATA, REPEATING_DATA};
            } catch (IOException e) {
                throw new Error(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("IOException: ")));
            }
        }

        private void advanceBuffer() {
            this.pointer = 0;
            this.sequence = NEXT[this.sequence];
            this.data = this.buffers[this.sequence];
        }

        private static void initialize() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeShort(-21267);
                dataOutputStream.writeShort(5);
                HEADER = byteArrayOutputStream.toByteArray();
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream2);
                dataOutputStream2.writeByte(115);
                dataOutputStream2.writeByte(113);
                dataOutputStream2.writeInt(8257536);
                REPEATING_DATA = byteArrayOutputStream2.toByteArray();
            } catch (IOException e) {
                throw new Error(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("IOException: ")));
            }
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return Integer.MAX_VALUE;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            byte[] bArr = this.data;
            int i = this.pointer;
            this.pointer = i + 1;
            byte b = bArr[i];
            if (this.pointer >= bArr.length) {
                advanceBuffer();
            }
            return b;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int length = this.data.length - this.pointer;
            int i3 = i;
            int i4 = i2;
            while (length <= i4) {
                System.arraycopy(this.data, this.pointer, bArr, i3, length);
                i3 += length;
                i4 -= length;
                advanceBuffer();
                length = this.data.length - this.pointer;
            }
            if (i4 > 0) {
                System.arraycopy(this.data, this.pointer, bArr, i3, i4);
                this.pointer += i4;
            }
            return i2;
        }
    }

    public ObjectInputStreamInstantiator(Class<T> cls) {
        if (Serializable.class.isAssignableFrom(cls)) {
            try {
                this.inputStream = new ObjectInputStream(new MockStream(cls));
                return;
            } catch (IOException e) {
                throw new Error(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("IOException: ")));
            }
        }
        throw new ObjenesisException(new NotSerializableException(cls + " not serializable"));
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return (T) this.inputStream.readObject();
        } catch (ClassNotFoundException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClassNotFoundException: ");
            outline107.append(e.getMessage());
            throw new Error(outline107.toString());
        } catch (Exception e2) {
            throw new ObjenesisException(e2);
        }
    }
}
