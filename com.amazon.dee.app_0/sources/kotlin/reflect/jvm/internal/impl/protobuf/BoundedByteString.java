package kotlin.reflect.jvm.internal.impl.protobuf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class BoundedByteString extends LiteralByteString {
    private final int bytesLength;
    private final int bytesOffset;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class BoundedByteIterator implements ByteString.ByteIterator {
        private final int limit;
        private int position;

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.position < this.limit;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            int i = this.position;
            if (i < this.limit) {
                byte[] bArr = BoundedByteString.this.bytes;
                this.position = i + 1;
                return bArr[i];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private BoundedByteIterator() {
            this.position = BoundedByteString.this.getOffsetIntoBytes();
            this.limit = this.position + BoundedByteString.this.size();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        /* renamed from: next */
        public Byte mo11988next() {
            return Byte.valueOf(nextByte());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundedByteString(byte[] bArr, int i, int i2) {
        super(bArr);
        if (i >= 0) {
            if (i2 >= 0) {
                if (i + i2 <= bArr.length) {
                    this.bytesOffset = i;
                    this.bytesLength = i2;
                    return;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline28(48, "Offset+Length too large: ", i, "+", i2));
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(29, "Length too small: ", i));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(29, "Offset too small: ", i));
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    public byte byteAt(int i) {
        if (i >= 0) {
            if (i < size()) {
                return this.bytes[this.bytesOffset + i];
            }
            throw new ArrayIndexOutOfBoundsException(GeneratedOutlineSupport1.outline28(41, "Index too large: ", i, ", ", size()));
        }
        throw new ArrayIndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(28, "Index too small: ", i));
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString, kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.bytes, getOffsetIntoBytes() + i, bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString
    public int getOffsetIntoBytes() {
        return this.bytesOffset;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString, kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public int size() {
        return this.bytesLength;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.LiteralByteString, kotlin.reflect.jvm.internal.impl.protobuf.ByteString, java.lang.Iterable
    public Iterator<Byte> iterator() {
        return new BoundedByteIterator();
    }
}
