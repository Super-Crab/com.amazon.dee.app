package com.amazon.org.codehaus.jackson.impl;

import com.amazon.org.codehaus.jackson.JsonEncoding;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.ObjectCodec;
import com.amazon.org.codehaus.jackson.format.InputAccessor;
import com.amazon.org.codehaus.jackson.format.MatchStrength;
import com.amazon.org.codehaus.jackson.io.IOContext;
import com.amazon.org.codehaus.jackson.io.MergedStream;
import com.amazon.org.codehaus.jackson.io.UTF32Reader;
import com.amazon.org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import com.amazon.org.codehaus.jackson.sym.CharsToNameCanonicalizer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
/* loaded from: classes13.dex */
public final class ByteSourceBootstrapper {
    static final byte UTF8_BOM_1 = -17;
    static final byte UTF8_BOM_2 = -69;
    static final byte UTF8_BOM_3 = -65;
    protected boolean _bigEndian;
    private final boolean _bufferRecyclable;
    protected int _bytesPerChar;
    protected final IOContext _context;
    protected final InputStream _in;
    protected final byte[] _inputBuffer;
    private int _inputEnd;
    protected int _inputProcessed;
    private int _inputPtr;

    /* renamed from: com.amazon.org.codehaus.jackson.impl.ByteSourceBootstrapper$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonEncoding = new int[JsonEncoding.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF32_BE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF32_LE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF16_BE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF16_LE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonEncoding[JsonEncoding.UTF8.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public ByteSourceBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._inputProcessed = 0;
        this._bufferRecyclable = true;
    }

    private boolean checkUTF16(int i) {
        if ((65280 & i) == 0) {
            this._bigEndian = true;
        } else if ((i & 255) != 0) {
            return false;
        } else {
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }

    private boolean checkUTF32(int i) throws IOException {
        if ((i >> 8) == 0) {
            this._bigEndian = true;
        } else if ((16777215 & i) == 0) {
            this._bigEndian = false;
        } else if (((-16711681) & i) == 0) {
            reportWeirdUCS4("3412");
        } else if ((i & (-65281)) != 0) {
            return false;
        } else {
            reportWeirdUCS4("2143");
        }
        this._bytesPerChar = 4;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean handleBOM(int r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = -16842752(0xfffffffffeff0000, float:-1.6947657E38)
            r1 = 65534(0xfffe, float:9.1833E-41)
            r2 = 65279(0xfeff, float:9.1475E-41)
            r3 = 0
            r4 = 1
            if (r7 == r0) goto L30
            r0 = -131072(0xfffffffffffe0000, float:NaN)
            r5 = 4
            if (r7 == r0) goto L26
            if (r7 == r2) goto L1c
            if (r7 == r1) goto L16
            goto L35
        L16:
            java.lang.String r0 = "2143"
            r6.reportWeirdUCS4(r0)
            goto L30
        L1c:
            r6._bigEndian = r4
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            return r4
        L26:
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            r6._bigEndian = r3
            return r4
        L30:
            java.lang.String r0 = "3412"
            r6.reportWeirdUCS4(r0)
        L35:
            int r0 = r7 >>> 16
            r5 = 2
            if (r0 != r2) goto L44
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            r6._bigEndian = r4
            return r4
        L44:
            if (r0 != r1) goto L50
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            r6._bigEndian = r3
            return r4
        L50:
            int r7 = r7 >>> 8
            r0 = 15711167(0xefbbbf, float:2.2016034E-38)
            if (r7 != r0) goto L62
            int r7 = r6._inputPtr
            int r7 = r7 + 3
            r6._inputPtr = r7
            r6._bytesPerChar = r4
            r6._bigEndian = r4
            return r4
        L62:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.impl.ByteSourceBootstrapper.handleBOM(int):boolean");
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte nextByte = inputAccessor.nextByte();
        if (nextByte == -17) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -69) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -65) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            nextByte = inputAccessor.nextByte();
        }
        int skipSpace = skipSpace(inputAccessor, nextByte);
        if (skipSpace < 0) {
            return MatchStrength.INCONCLUSIVE;
        }
        if (skipSpace == 123) {
            int skipSpace2 = skipSpace(inputAccessor);
            if (skipSpace2 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace2 != 34 && skipSpace2 != 125) {
                return MatchStrength.NO_MATCH;
            }
            return MatchStrength.SOLID_MATCH;
        } else if (skipSpace == 91) {
            int skipSpace3 = skipSpace(inputAccessor);
            if (skipSpace3 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace3 != 93 && skipSpace3 != 91) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.SOLID_MATCH;
        } else {
            MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
            if (skipSpace == 34) {
                return matchStrength;
            }
            if (skipSpace <= 57 && skipSpace >= 48) {
                return matchStrength;
            }
            if (skipSpace == 45) {
                int skipSpace4 = skipSpace(inputAccessor);
                if (skipSpace4 < 0) {
                    return MatchStrength.INCONCLUSIVE;
                }
                return (skipSpace4 > 57 || skipSpace4 < 48) ? MatchStrength.NO_MATCH : matchStrength;
            } else if (skipSpace == 110) {
                return tryMatch(inputAccessor, "ull", matchStrength);
            } else {
                if (skipSpace == 116) {
                    return tryMatch(inputAccessor, "rue", matchStrength);
                }
                if (skipSpace == 102) {
                    return tryMatch(inputAccessor, "alse", matchStrength);
                }
                return MatchStrength.NO_MATCH;
            }
        }
    }

    private void reportWeirdUCS4(String str) throws IOException {
        throw new CharConversionException(GeneratedOutlineSupport1.outline75("Unsupported UCS-4 endianness (", str, ") detected"));
    }

    private static final int skipSpace(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return -1;
        }
        return skipSpace(inputAccessor, inputAccessor.nextByte());
    }

    private static final MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    public JsonParser constructParser(int i, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, CharsToNameCanonicalizer charsToNameCanonicalizer) throws IOException, JsonParseException {
        JsonEncoding detectEncoding = detectEncoding();
        boolean enabledIn = JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i);
        boolean enabledIn2 = JsonParser.Feature.INTERN_FIELD_NAMES.enabledIn(i);
        if (detectEncoding == JsonEncoding.UTF8 && enabledIn) {
            return new Utf8StreamParser(this._context, i, this._in, objectCodec, bytesToNameCanonicalizer.makeChild(enabledIn, enabledIn2), this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
        }
        return new ReaderBasedParser(this._context, i, constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(enabledIn, enabledIn2));
    }

    public Reader constructReader() throws IOException {
        JsonEncoding encoding = this._context.getEncoding();
        int ordinal = encoding.ordinal();
        if (ordinal != 0 && ordinal != 1 && ordinal != 2) {
            if (ordinal != 3 && ordinal != 4) {
                throw new RuntimeException("Internal error");
            }
            IOContext iOContext = this._context;
            return new UTF32Reader(iOContext, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, iOContext.getEncoding().isBigEndian());
        }
        InputStream inputStream = this._in;
        if (inputStream == null) {
            inputStream = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
        } else {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i < i2) {
                inputStream = new MergedStream(this._context, inputStream, this._inputBuffer, i, i2);
            }
        }
        return new InputStreamReader(inputStream, encoding.getJavaName());
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003f, code lost:
        if (checkUTF16(r1 >>> 16) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005c, code lost:
        if (checkUTF16((r1[r5 + 1] & 255) | ((r1[r5] & 255) << 8)) != false) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.org.codehaus.jackson.JsonEncoding detectEncoding() throws java.io.IOException, com.amazon.org.codehaus.jackson.JsonParseException {
        /*
            r8 = this;
            r0 = 4
            boolean r1 = r8.ensureLoaded(r0)
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L42
            byte[] r1 = r8._inputBuffer
            int r5 = r8._inputPtr
            r6 = r1[r5]
            int r6 = r6 << 24
            int r7 = r5 + 1
            r7 = r1[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r7 = r7 << 16
            r6 = r6 | r7
            int r7 = r5 + 2
            r7 = r1[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r7 = r7 << 8
            r6 = r6 | r7
            int r5 = r5 + 3
            r1 = r1[r5]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r1 | r6
            boolean r5 = r8.handleBOM(r1)
            if (r5 == 0) goto L32
            goto L5e
        L32:
            boolean r5 = r8.checkUTF32(r1)
            if (r5 == 0) goto L39
            goto L5e
        L39:
            int r1 = r1 >>> 16
            boolean r1 = r8.checkUTF16(r1)
            if (r1 == 0) goto L5f
            goto L5e
        L42:
            boolean r1 = r8.ensureLoaded(r2)
            if (r1 == 0) goto L5f
            byte[] r1 = r8._inputBuffer
            int r5 = r8._inputPtr
            r6 = r1[r5]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            int r5 = r5 + r3
            r1 = r1[r5]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r1 | r6
            boolean r1 = r8.checkUTF16(r1)
            if (r1 == 0) goto L5f
        L5e:
            r4 = r3
        L5f:
            if (r4 != 0) goto L64
            com.amazon.org.codehaus.jackson.JsonEncoding r0 = com.amazon.org.codehaus.jackson.JsonEncoding.UTF8
            goto L8a
        L64:
            int r1 = r8._bytesPerChar
            if (r1 == r3) goto L88
            if (r1 == r2) goto L7e
            if (r1 != r0) goto L76
            boolean r0 = r8._bigEndian
            if (r0 == 0) goto L73
            com.amazon.org.codehaus.jackson.JsonEncoding r0 = com.amazon.org.codehaus.jackson.JsonEncoding.UTF32_BE
            goto L8a
        L73:
            com.amazon.org.codehaus.jackson.JsonEncoding r0 = com.amazon.org.codehaus.jackson.JsonEncoding.UTF32_LE
            goto L8a
        L76:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Internal error"
            r0.<init>(r1)
            throw r0
        L7e:
            boolean r0 = r8._bigEndian
            if (r0 == 0) goto L85
            com.amazon.org.codehaus.jackson.JsonEncoding r0 = com.amazon.org.codehaus.jackson.JsonEncoding.UTF16_BE
            goto L8a
        L85:
            com.amazon.org.codehaus.jackson.JsonEncoding r0 = com.amazon.org.codehaus.jackson.JsonEncoding.UTF16_LE
            goto L8a
        L88:
            com.amazon.org.codehaus.jackson.JsonEncoding r0 = com.amazon.org.codehaus.jackson.JsonEncoding.UTF8
        L8a:
            com.amazon.org.codehaus.jackson.io.IOContext r1 = r8._context
            r1.setEncoding(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.impl.ByteSourceBootstrapper.detectEncoding():com.amazon.org.codehaus.jackson.JsonEncoding");
    }

    protected boolean ensureLoaded(int i) throws IOException {
        int read;
        int i2 = this._inputEnd - this._inputPtr;
        while (i2 < i) {
            InputStream inputStream = this._in;
            if (inputStream == null) {
                read = -1;
            } else {
                byte[] bArr = this._inputBuffer;
                int i3 = this._inputEnd;
                read = inputStream.read(bArr, i3, bArr.length - i3);
            }
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
            i2 += read;
        }
        return true;
    }

    private static final int skipSpace(InputAccessor inputAccessor, byte b) throws IOException {
        while (true) {
            int i = b & 255;
            if (i == 32 || i == 13 || i == 10 || i == 9) {
                if (!inputAccessor.hasMoreBytes()) {
                    return -1;
                }
                b = inputAccessor.nextByte();
            } else {
                return i;
            }
        }
    }

    public ByteSourceBootstrapper(IOContext iOContext, byte[] bArr, int i, int i2) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i;
        this._inputEnd = i2 + i;
        this._inputProcessed = -i;
        this._bufferRecyclable = false;
    }
}
