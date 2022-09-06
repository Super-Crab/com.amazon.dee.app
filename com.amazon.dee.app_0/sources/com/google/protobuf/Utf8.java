package com.google.protobuf;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class Processor {
        Processor() {
        }

        private static int partialIsValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            int estimateConsecutiveAscii = i + Utf8.estimateConsecutiveAscii(byteBuffer, i, i2);
            while (estimateConsecutiveAscii < i2) {
                int i3 = estimateConsecutiveAscii + 1;
                byte b = byteBuffer.get(estimateConsecutiveAscii);
                if (b < 0) {
                    if (b >= -32) {
                        if (b < -16) {
                            if (i3 < i2 - 1) {
                                int i4 = i3 + 1;
                                byte b2 = byteBuffer.get(i3);
                                if (b2 > -65 || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || byteBuffer.get(i4) > -65))) {
                                    return -1;
                                }
                                estimateConsecutiveAscii = i4 + 1;
                            }
                        } else if (i3 < i2 - 2) {
                            int i5 = i3 + 1;
                            byte b3 = byteBuffer.get(i3);
                            if (b3 <= -65) {
                                if ((((b3 + 112) + (b << 28)) >> 30) == 0) {
                                    int i6 = i5 + 1;
                                    if (byteBuffer.get(i5) <= -65) {
                                        i3 = i6 + 1;
                                        if (byteBuffer.get(i6) > -65) {
                                        }
                                    }
                                }
                            }
                            return -1;
                        }
                        return Utf8.incompleteStateFor(byteBuffer, b, i3, i2 - i3);
                    } else if (i3 >= i2) {
                        return b;
                    } else {
                        if (b < -62 || byteBuffer.get(i3) > -65) {
                            return -1;
                        }
                        i3++;
                    }
                }
                estimateConsecutiveAscii = i3;
            }
            return 0;
        }

        abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2);

        final void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                byteBuffer.position(Utf8.encode(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
            } else if (byteBuffer.isDirect()) {
                encodeUtf8Direct(charSequence, byteBuffer);
            } else {
                encodeUtf8Default(charSequence, byteBuffer);
            }
        }

        final void encodeUtf8Default(CharSequence charSequence, ByteBuffer byteBuffer) {
            int length = charSequence.length();
            int position = byteBuffer.position();
            int i = 0;
            while (i < length) {
                try {
                    char charAt = charSequence.charAt(i);
                    if (charAt >= 128) {
                        break;
                    }
                    byteBuffer.put(position + i, (byte) charAt);
                    i++;
                } catch (IndexOutOfBoundsException unused) {
                    int position2 = byteBuffer.position();
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed writing ");
                    outline107.append(charSequence.charAt(i));
                    outline107.append(" at index ");
                    outline107.append(Math.max(i, (position - byteBuffer.position()) + 1) + position2);
                    throw new ArrayIndexOutOfBoundsException(outline107.toString());
                }
            }
            if (i == length) {
                byteBuffer.position(position + i);
                return;
            }
            position += i;
            while (i < length) {
                char charAt2 = charSequence.charAt(i);
                if (charAt2 < 128) {
                    byteBuffer.put(position, (byte) charAt2);
                } else if (charAt2 < 2048) {
                    int i2 = position + 1;
                    try {
                        byteBuffer.put(position, (byte) ((charAt2 >>> 6) | 192));
                        byteBuffer.put(i2, (byte) ((charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128));
                        position = i2;
                    } catch (IndexOutOfBoundsException unused2) {
                        position = i2;
                        int position22 = byteBuffer.position();
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Failed writing ");
                        outline1072.append(charSequence.charAt(i));
                        outline1072.append(" at index ");
                        outline1072.append(Math.max(i, (position - byteBuffer.position()) + 1) + position22);
                        throw new ArrayIndexOutOfBoundsException(outline1072.toString());
                    }
                } else if (charAt2 >= 55296 && 57343 >= charAt2) {
                    int i3 = i + 1;
                    if (i3 != length) {
                        try {
                            char charAt3 = charSequence.charAt(i3);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                int i4 = position + 1;
                                try {
                                    byteBuffer.put(position, (byte) ((codePoint >>> 18) | 240));
                                    int i5 = i4 + 1;
                                    byteBuffer.put(i4, (byte) (((codePoint >>> 12) & 63) | 128));
                                    int i6 = i5 + 1;
                                    byteBuffer.put(i5, (byte) (((codePoint >>> 6) & 63) | 128));
                                    byteBuffer.put(i6, (byte) ((codePoint & 63) | 128));
                                    position = i6;
                                    i = i3;
                                } catch (IndexOutOfBoundsException unused3) {
                                    position = i4;
                                    i = i3;
                                    int position222 = byteBuffer.position();
                                    StringBuilder outline10722 = GeneratedOutlineSupport1.outline107("Failed writing ");
                                    outline10722.append(charSequence.charAt(i));
                                    outline10722.append(" at index ");
                                    outline10722.append(Math.max(i, (position - byteBuffer.position()) + 1) + position222);
                                    throw new ArrayIndexOutOfBoundsException(outline10722.toString());
                                }
                            } else {
                                i = i3;
                            }
                        } catch (IndexOutOfBoundsException unused4) {
                        }
                    }
                    throw new UnpairedSurrogateException(i, length);
                } else {
                    int i7 = position + 1;
                    byteBuffer.put(position, (byte) ((charAt2 >>> '\f') | 224));
                    position = i7 + 1;
                    byteBuffer.put(i7, (byte) (((charAt2 >>> 6) & 63) | 128));
                    byteBuffer.put(position, (byte) ((charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128));
                }
                i++;
                position++;
            }
            byteBuffer.position(position);
        }

        abstract void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer);

        final boolean isValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            return partialIsValidUtf8(0, byteBuffer, i, i2) == 0;
        }

        final boolean isValidUtf8(byte[] bArr, int i, int i2) {
            return partialIsValidUtf8(0, bArr, i, i2) == 0;
        }

        final int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
            if (!byteBuffer.hasArray()) {
                return byteBuffer.isDirect() ? partialIsValidUtf8Direct(i, byteBuffer, i2, i3) : partialIsValidUtf8Default(i, byteBuffer, i2, i3);
            }
            int arrayOffset = byteBuffer.arrayOffset();
            return partialIsValidUtf8(i, byteBuffer.array(), i2 + arrayOffset, arrayOffset + i3);
        }

        abstract int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3);

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
            if (r8.get(r9) > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0048, code lost:
            if (r8.get(r9) > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0086, code lost:
            if (r8.get(r7) > (-65)) goto L51;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        final int partialIsValidUtf8Default(int r7, java.nio.ByteBuffer r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L89
                if (r9 < r10) goto L5
                return r7
            L5:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L1a
                r7 = -62
                if (r0 < r7) goto L19
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L8a
            L19:
                return r2
            L1a:
                r4 = -16
                if (r0 >= r4) goto L4b
                int r7 = r7 >> 8
                int r7 = ~r7
                byte r7 = (byte) r7
                if (r7 != 0) goto L34
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r7 < r10) goto L31
                int r7 = com.google.protobuf.Utf8.access$000(r0, r9)
                return r7
            L31:
                r5 = r9
                r9 = r7
                r7 = r5
            L34:
                if (r7 > r3) goto L4a
                r4 = -96
                if (r0 != r1) goto L3c
                if (r7 < r4) goto L4a
            L3c:
                r1 = -19
                if (r0 != r1) goto L42
                if (r7 >= r4) goto L4a
            L42:
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L8a
            L4a:
                return r2
            L4b:
                int r1 = r7 >> 8
                int r1 = ~r1
                byte r1 = (byte) r1
                r4 = 0
                if (r1 != 0) goto L5f
                int r7 = r9 + 1
                byte r1 = r8.get(r9)
                if (r7 < r10) goto L63
                int r7 = com.google.protobuf.Utf8.access$000(r0, r1)
                return r7
            L5f:
                int r7 = r7 >> 16
                byte r4 = (byte) r7
                r7 = r9
            L63:
                if (r4 != 0) goto L73
                int r9 = r7 + 1
                byte r4 = r8.get(r7)
                if (r9 < r10) goto L72
                int r7 = com.google.protobuf.Utf8.access$100(r0, r1, r4)
                return r7
            L72:
                r7 = r9
            L73:
                if (r1 > r3) goto L88
                int r9 = r0 << 28
                int r1 = r1 + 112
                int r1 = r1 + r9
                int r9 = r1 >> 30
                if (r9 != 0) goto L88
                if (r4 > r3) goto L88
                int r9 = r7 + 1
                byte r7 = r8.get(r7)
                if (r7 <= r3) goto L89
            L88:
                return r2
            L89:
                r7 = r9
            L8a:
                int r7 = partialIsValidUtf8(r8, r7, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.Processor.partialIsValidUtf8Default(int, java.nio.ByteBuffer, int, int):int");
        }

        abstract int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3);
    }

    /* loaded from: classes3.dex */
    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        private static int partialIsValidUtf8(byte[] bArr, int i, int i2) {
            while (i < i2 && bArr[i] >= 0) {
                i++;
            }
            if (i >= i2) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bArr, i, i2);
        }

        private static int partialIsValidUtf8NonAscii(byte[] bArr, int i, int i2) {
            while (i < i2) {
                int i3 = i + 1;
                byte b = bArr[i];
                if (b < 0) {
                    if (b < -32) {
                        if (i3 >= i2) {
                            return b;
                        }
                        if (b >= -62) {
                            i = i3 + 1;
                            if (bArr[i3] > -65) {
                            }
                        }
                        return -1;
                    } else if (b < -16) {
                        if (i3 >= i2 - 1) {
                            return Utf8.incompleteStateFor(bArr, i3, i2);
                        }
                        int i4 = i3 + 1;
                        byte b2 = bArr[i3];
                        if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                            i = i4 + 1;
                            if (bArr[i4] > -65) {
                            }
                        }
                        return -1;
                    } else if (i3 >= i2 - 2) {
                        return Utf8.incompleteStateFor(bArr, i3, i2);
                    } else {
                        int i5 = i3 + 1;
                        byte b3 = bArr[i3];
                        if (b3 <= -65) {
                            if ((((b3 + 112) + (b << 28)) >> 30) == 0) {
                                int i6 = i5 + 1;
                                if (bArr[i5] <= -65) {
                                    i3 = i6 + 1;
                                    if (bArr[i6] > -65) {
                                    }
                                }
                            }
                        }
                        return -1;
                    }
                }
                i = i3;
            }
            return 0;
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
            return r10 + r0;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int encodeUtf8(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
            /*
                Method dump skipped, instructions count: 254
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.SafeProcessor.encodeUtf8(java.lang.CharSequence, byte[], int, int):int");
        }

        @Override // com.google.protobuf.Utf8.Processor
        void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            encodeUtf8Default(charSequence, byteBuffer);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
            if (r8[r9] > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0042, code lost:
            if (r8[r9] > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x007a, code lost:
            if (r8[r7] > (-65)) goto L51;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int partialIsValidUtf8(int r7, byte[] r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L7d
                if (r9 < r10) goto L5
                return r7
            L5:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L18
                r7 = -62
                if (r0 < r7) goto L17
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r9 <= r3) goto L7e
            L17:
                return r2
            L18:
                r4 = -16
                if (r0 >= r4) goto L45
                int r7 = r7 >> 8
                int r7 = ~r7
                byte r7 = (byte) r7
                if (r7 != 0) goto L30
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r7 < r10) goto L2d
                int r7 = com.google.protobuf.Utf8.access$000(r0, r9)
                return r7
            L2d:
                r5 = r9
                r9 = r7
                r7 = r5
            L30:
                if (r7 > r3) goto L44
                r4 = -96
                if (r0 != r1) goto L38
                if (r7 < r4) goto L44
            L38:
                r1 = -19
                if (r0 != r1) goto L3e
                if (r7 >= r4) goto L44
            L3e:
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r9 <= r3) goto L7e
            L44:
                return r2
            L45:
                int r1 = r7 >> 8
                int r1 = ~r1
                byte r1 = (byte) r1
                r4 = 0
                if (r1 != 0) goto L57
                int r7 = r9 + 1
                r1 = r8[r9]
                if (r7 < r10) goto L5b
                int r7 = com.google.protobuf.Utf8.access$000(r0, r1)
                return r7
            L57:
                int r7 = r7 >> 16
                byte r4 = (byte) r7
                r7 = r9
            L5b:
                if (r4 != 0) goto L69
                int r9 = r7 + 1
                r4 = r8[r7]
                if (r9 < r10) goto L68
                int r7 = com.google.protobuf.Utf8.access$100(r0, r1, r4)
                return r7
            L68:
                r7 = r9
            L69:
                if (r1 > r3) goto L7c
                int r9 = r0 << 28
                int r1 = r1 + 112
                int r1 = r1 + r9
                int r9 = r1 >> 30
                if (r9 != 0) goto L7c
                if (r4 > r3) goto L7c
                int r9 = r7 + 1
                r7 = r8[r7]
                if (r7 <= r3) goto L7d
            L7c:
                return r2
            L7d:
                r7 = r9
            L7e:
                int r7 = partialIsValidUtf8(r8, r7, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.SafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        @Override // com.google.protobuf.Utf8.Processor
        int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3) {
            return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            super(GeneratedOutlineSupport1.outline53("Unpaired surrogate at index ", i, " of ", i2));
        }
    }

    /* loaded from: classes3.dex */
    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static int partialIsValidUtf8(long r8, int r10) {
            /*
                int r0 = unsafeEstimateConsecutiveAscii(r8, r10)
                long r1 = (long) r0
                long r8 = r8 + r1
                int r10 = r10 - r0
            L7:
                r0 = 0
                r1 = r0
            L9:
                r2 = 1
                if (r10 <= 0) goto L1a
                long r4 = r8 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r8)
                if (r1 < 0) goto L19
                int r10 = r10 + (-1)
                r8 = r4
                goto L9
            L19:
                r8 = r4
            L1a:
                if (r10 != 0) goto L1d
                return r0
            L1d:
                int r10 = r10 + (-1)
                r0 = -32
                r4 = -65
                r5 = -1
                if (r1 >= r0) goto L3a
                if (r10 != 0) goto L29
                return r1
            L29:
                int r10 = r10 + (-1)
                r0 = -62
                if (r1 < r0) goto L39
                long r2 = r2 + r8
                byte r8 = com.google.protobuf.UnsafeUtil.getByte(r8)
                if (r8 <= r4) goto L37
                goto L39
            L37:
                r8 = r2
                goto L7
            L39:
                return r5
            L3a:
                r6 = -16
                if (r1 >= r6) goto L64
                r6 = 2
                if (r10 >= r6) goto L46
                int r8 = unsafeIncompleteStateFor(r8, r1, r10)
                return r8
            L46:
                int r10 = r10 + (-2)
                long r6 = r8 + r2
                byte r8 = com.google.protobuf.UnsafeUtil.getByte(r8)
                if (r8 > r4) goto L63
                r9 = -96
                if (r1 != r0) goto L56
                if (r8 < r9) goto L63
            L56:
                r0 = -19
                if (r1 != r0) goto L5c
                if (r8 >= r9) goto L63
            L5c:
                long r2 = r2 + r6
                byte r8 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r8 <= r4) goto L37
            L63:
                return r5
            L64:
                r0 = 3
                if (r10 >= r0) goto L6c
                int r8 = unsafeIncompleteStateFor(r8, r1, r10)
                return r8
            L6c:
                int r10 = r10 + (-3)
                long r6 = r8 + r2
                byte r8 = com.google.protobuf.UnsafeUtil.getByte(r8)
                if (r8 > r4) goto L8e
                int r9 = r1 << 28
                int r8 = r8 + 112
                int r8 = r8 + r9
                int r8 = r8 >> 30
                if (r8 != 0) goto L8e
                long r8 = r6 + r2
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r0 > r4) goto L8e
                long r2 = r2 + r8
                byte r8 = com.google.protobuf.UnsafeUtil.getByte(r8)
                if (r8 <= r4) goto L37
            L8e:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(long, int):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static int partialIsValidUtf8(byte[] r8, long r9, int r11) {
            /*
                int r0 = unsafeEstimateConsecutiveAscii(r8, r9, r11)
                int r11 = r11 - r0
                long r0 = (long) r0
                long r9 = r9 + r0
            L7:
                r0 = 0
                r1 = r0
            L9:
                r2 = 1
                if (r11 <= 0) goto L1a
                long r4 = r9 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r8, r9)
                if (r1 < 0) goto L19
                int r11 = r11 + (-1)
                r9 = r4
                goto L9
            L19:
                r9 = r4
            L1a:
                if (r11 != 0) goto L1d
                return r0
            L1d:
                int r11 = r11 + (-1)
                r0 = -32
                r4 = -65
                r5 = -1
                if (r1 >= r0) goto L3a
                if (r11 != 0) goto L29
                return r1
            L29:
                int r11 = r11 + (-1)
                r0 = -62
                if (r1 < r0) goto L39
                long r2 = r2 + r9
                byte r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r9)
                if (r9 <= r4) goto L37
                goto L39
            L37:
                r9 = r2
                goto L7
            L39:
                return r5
            L3a:
                r6 = -16
                if (r1 >= r6) goto L64
                r6 = 2
                if (r11 >= r6) goto L46
                int r8 = unsafeIncompleteStateFor(r8, r1, r9, r11)
                return r8
            L46:
                int r11 = r11 + (-2)
                long r6 = r9 + r2
                byte r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r9)
                if (r9 > r4) goto L63
                r10 = -96
                if (r1 != r0) goto L56
                if (r9 < r10) goto L63
            L56:
                r0 = -19
                if (r1 != r0) goto L5c
                if (r9 >= r10) goto L63
            L5c:
                long r2 = r2 + r6
                byte r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r6)
                if (r9 <= r4) goto L37
            L63:
                return r5
            L64:
                r0 = 3
                if (r11 >= r0) goto L6c
                int r8 = unsafeIncompleteStateFor(r8, r1, r9, r11)
                return r8
            L6c:
                int r11 = r11 + (-3)
                long r6 = r9 + r2
                byte r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r9)
                if (r9 > r4) goto L8e
                int r10 = r1 << 28
                int r9 = r9 + 112
                int r9 = r9 + r10
                int r9 = r9 >> 30
                if (r9 != 0) goto L8e
                long r9 = r6 + r2
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r8, r6)
                if (r0 > r4) goto L8e
                long r2 = r2 + r9
                byte r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r9)
                if (r9 <= r4) goto L37
            L8e:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(byte[], long, int):int");
        }

        private static int unsafeEstimateConsecutiveAscii(long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = 8 - (((int) j) & 7);
            long j2 = j;
            int i3 = i2;
            while (i3 > 0) {
                long j3 = 1 + j2;
                if (UnsafeUtil.getByte(j2) < 0) {
                    return i2 - i3;
                }
                i3--;
                j2 = j3;
            }
            int i4 = i - i2;
            while (i4 >= 8 && (UnsafeUtil.getLong(j2) & Utf8.ASCII_MASK_LONG) == 0) {
                j2 += 8;
                i4 -= 8;
            }
            return i - i4;
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j, int i) {
            int i2 = 0;
            if (i < 16) {
                return 0;
            }
            while (i2 < i) {
                long j2 = 1 + j;
                if (UnsafeUtil.getByte(bArr, j) < 0) {
                    return i2;
                }
                i2++;
                j = j2;
            }
            return i;
        }

        private static int unsafeIncompleteStateFor(long j, int i, int i2) {
            if (i2 != 0) {
                if (i2 == 1) {
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(j));
                }
                if (i2 != 2) {
                    throw new AssertionError();
                }
                return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(j), UnsafeUtil.getByte(j + 1));
            }
            return Utf8.incompleteStateFor(i);
        }

        private static int unsafeIncompleteStateFor(byte[] bArr, int i, long j, int i2) {
            if (i2 != 0) {
                if (i2 == 1) {
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(bArr, j));
                }
                if (i2 != 2) {
                    throw new AssertionError();
                }
                return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(bArr, j), UnsafeUtil.getByte(bArr, j + 1));
            }
            return Utf8.incompleteStateFor(i);
        }

        @Override // com.google.protobuf.Utf8.Processor
        int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            char c;
            long j;
            long j2;
            int i3;
            char charAt;
            long j3 = i;
            long j4 = i2 + j3;
            int length = charSequence.length();
            if (length > i2 || bArr.length - i2 < i) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed writing ");
                outline107.append(charSequence.charAt(length - 1));
                outline107.append(" at index ");
                outline107.append(i + i2);
                throw new ArrayIndexOutOfBoundsException(outline107.toString());
            }
            int i4 = 0;
            while (true) {
                c = 128;
                j = 1;
                if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(bArr, j3, (byte) charAt);
                i4++;
                j3 = 1 + j3;
            }
            if (i4 == length) {
                return (int) j3;
            }
            while (i4 < length) {
                char charAt2 = charSequence.charAt(i4);
                if (charAt2 >= c || j3 >= j4) {
                    if (charAt2 < 2048 && j3 <= j4 - 2) {
                        long j5 = j3 + j;
                        UnsafeUtil.putByte(bArr, j3, (byte) ((charAt2 >>> 6) | 960));
                        j2 = j5 + j;
                        UnsafeUtil.putByte(bArr, j5, (byte) ((charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128));
                    } else if ((charAt2 >= 55296 && 57343 >= charAt2) || j3 > j4 - 3) {
                        if (j3 > j4 - 4) {
                            if (55296 <= charAt2 && charAt2 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                                throw new UnpairedSurrogateException(i4, length);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + j3);
                        }
                        int i5 = i4 + 1;
                        if (i5 != length) {
                            char charAt3 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                long j6 = j3 + 1;
                                UnsafeUtil.putByte(bArr, j3, (byte) ((codePoint >>> 18) | 240));
                                long j7 = 1 + j6;
                                UnsafeUtil.putByte(bArr, j6, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j8 = 1 + j7;
                                UnsafeUtil.putByte(bArr, j7, (byte) (((codePoint >>> 6) & 63) | 128));
                                j = 1;
                                UnsafeUtil.putByte(bArr, j8, (byte) ((codePoint & 63) | 128));
                                i4 = i5;
                                c = 128;
                                j3 = j8 + 1;
                            } else {
                                i4 = i5;
                            }
                        }
                        throw new UnpairedSurrogateException(i4 - 1, length);
                    } else {
                        long j9 = j3 + j;
                        UnsafeUtil.putByte(bArr, j3, (byte) ((charAt2 >>> '\f') | 480));
                        long j10 = j + j9;
                        UnsafeUtil.putByte(bArr, j9, (byte) (((charAt2 >>> 6) & 63) | 128));
                        j2 = 1 + j10;
                        UnsafeUtil.putByte(bArr, j10, (byte) ((charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128));
                        j = 1;
                    }
                    long j11 = j2;
                    c = 128;
                    j3 = j11;
                } else {
                    UnsafeUtil.putByte(bArr, j3, (byte) charAt2);
                    j3 += j;
                }
                i4++;
            }
            return (int) j3;
        }

        @Override // com.google.protobuf.Utf8.Processor
        void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            char c;
            long j;
            long j2;
            long j3;
            int i;
            long j4;
            char charAt;
            long addressOffset = UnsafeUtil.addressOffset(byteBuffer);
            long position = byteBuffer.position() + addressOffset;
            long limit = byteBuffer.limit() + addressOffset;
            int length = charSequence.length();
            if (length > limit - position) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed writing ");
                outline107.append(charSequence.charAt(length - 1));
                outline107.append(" at index ");
                outline107.append(byteBuffer.limit());
                throw new ArrayIndexOutOfBoundsException(outline107.toString());
            }
            int i2 = 0;
            while (true) {
                c = 128;
                if (i2 >= length || (charAt = charSequence.charAt(i2)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(position, (byte) charAt);
                i2++;
                position++;
            }
            if (i2 == length) {
                j = position - addressOffset;
            } else {
                while (i2 < length) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 >= c || position >= limit) {
                        if (charAt2 >= 2048 || position > limit - 2) {
                            j2 = addressOffset;
                            if ((charAt2 >= 55296 && 57343 >= charAt2) || position > limit - 3) {
                                if (position > limit - 4) {
                                    if (55296 <= charAt2 && charAt2 <= 57343 && ((i = i2 + 1) == length || !Character.isSurrogatePair(charAt2, charSequence.charAt(i)))) {
                                        throw new UnpairedSurrogateException(i2, length);
                                    }
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + position);
                                }
                                int i3 = i2 + 1;
                                if (i3 != length) {
                                    char charAt3 = charSequence.charAt(i3);
                                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                                        j4 = limit;
                                        long j5 = position + 1;
                                        UnsafeUtil.putByte(position, (byte) ((codePoint >>> 18) | 240));
                                        long j6 = j5 + 1;
                                        c = 128;
                                        UnsafeUtil.putByte(j5, (byte) (((codePoint >>> 12) & 63) | 128));
                                        long j7 = j6 + 1;
                                        UnsafeUtil.putByte(j6, (byte) (((codePoint >>> 6) & 63) | 128));
                                        position = j7 + 1;
                                        UnsafeUtil.putByte(j7, (byte) ((codePoint & 63) | 128));
                                        i2 = i3;
                                    }
                                } else {
                                    i3 = i2;
                                }
                                throw new UnpairedSurrogateException(i3 - 1, length);
                            }
                            long j8 = position + 1;
                            UnsafeUtil.putByte(position, (byte) ((charAt2 >>> '\f') | 480));
                            long j9 = j8 + 1;
                            UnsafeUtil.putByte(j8, (byte) (((charAt2 >>> 6) & 63) | 128));
                            j3 = j9 + 1;
                            UnsafeUtil.putByte(j9, (byte) ((charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128));
                        } else {
                            j2 = addressOffset;
                            long j10 = position + 1;
                            UnsafeUtil.putByte(position, (byte) ((charAt2 >>> 6) | 960));
                            UnsafeUtil.putByte(j10, (byte) ((charAt2 & Constants.DEFAULT_IMAGE_CHAR) | 128));
                            j3 = j10 + 1;
                        }
                        c = 128;
                        j4 = limit;
                        position = j3;
                    } else {
                        UnsafeUtil.putByte(position, (byte) charAt2);
                        j4 = limit;
                        position++;
                        j2 = addressOffset;
                    }
                    i2++;
                    addressOffset = j2;
                    limit = j4;
                }
                j = position - addressOffset;
            }
            byteBuffer.position((int) j);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0056, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0098, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L56;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int partialIsValidUtf8(int r12, byte[] r13, int r14, int r15) {
            /*
                Method dump skipped, instructions count: 199
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0060, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00a2, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L56;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int partialIsValidUtf8Direct(int r11, java.nio.ByteBuffer r12, int r13, int r14) {
            /*
                Method dump skipped, instructions count: 212
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8Direct(int, java.nio.ByteBuffer, int, int):int");
        }
    }

    static {
        processor = UnsafeProcessor.isAvailable() ? new UnsafeProcessor() : new SafeProcessor();
    }

    private Utf8() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encode(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return processor.encodeUtf8(charSequence, bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        processor.encodeUtf8(charSequence, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt >= 2048) {
                    i2 += encodedLengthGeneral(charSequence, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UTF-8 length does not fit in int: ");
        outline107.append(i2 + 4294967296L);
        throw new IllegalArgumentException(outline107.toString());
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) < 65536) {
                        throw new UnpairedSurrogateException(i, length);
                    }
                    i++;
                }
            }
            i++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i, int i2) {
        int i3 = i2 - 7;
        int i4 = i;
        while (i4 < i3 && (byteBuffer.getLong(i4) & ASCII_MASK_LONG) == 0) {
            i4 += 8;
        }
        return i4 - i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(ByteBuffer byteBuffer, int i, int i2, int i3) {
        if (i3 != 0) {
            if (i3 == 1) {
                return incompleteStateFor(i, byteBuffer.get(i2));
            }
            if (i3 != 2) {
                throw new AssertionError();
            }
            return incompleteStateFor(i, byteBuffer.get(i2), byteBuffer.get(i2 + 1));
        }
        return incompleteStateFor(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 != 0) {
            if (i3 == 1) {
                return incompleteStateFor(b, bArr[i]);
            }
            if (i3 != 2) {
                throw new AssertionError();
            }
            return incompleteStateFor(b, bArr[i], bArr[i + 1]);
        }
        return incompleteStateFor(b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidUtf8(ByteBuffer byteBuffer) {
        return processor.isValidUtf8(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return processor.isValidUtf8(bArr, 0, bArr.length);
    }

    public static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        return processor.isValidUtf8(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
        return processor.partialIsValidUtf8(i, byteBuffer, i2, i3);
    }

    public static int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3) {
        return processor.partialIsValidUtf8(i, bArr, i2, i3);
    }
}
