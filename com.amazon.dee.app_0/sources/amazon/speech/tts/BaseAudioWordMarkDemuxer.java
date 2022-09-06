package amazon.speech.tts;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
/* loaded from: classes.dex */
public class BaseAudioWordMarkDemuxer {
    private static final int ID3_FRAME_HEADER_SIZE = 10;
    private static final int ID3_HEADER_SIZE = 10;
    static final int INPUT_STREAM_BUFFER_SIZE = 256;
    private static final int INTERNAL_BUFFER_SIZE = 1048576;
    private static final int MAX_FRAME_LENGTH = 10000;
    static final int MPEG_16000_FRAME_LENGTH = 216;
    static final int MPEG_22050_FRAME_LENGTH = 156;
    static final int MPEG_24000_FRAME_LENGTH = 144;
    static final int MPEG_v1_br128_48000_FRAME_LENGTH = 384;
    static final int MPEG_v1_br256_48000_FRAME_LENGTH = 768;
    static final int PADDING_LENGTH = 1;
    private boolean mFinished = false;
    private final byte[] mHeaderBuffer = new byte[Math.max(10, 10)];
    private final InputStream mInputStream;
    private Emitter mSpeechMarksEmitter;
    private static final byte[] ID3_BEGIN = {73, 68, 51};
    private static final byte[] MP3_BEGIN = {-1, -13};
    private static final byte[] MP3_V1_BEGIN = {-1, -5};
    private static final byte[] TXXX_FRAME_TYPE = {84, 88, 88, 88};
    private static final String TAG = BaseAudioWordMarkDemuxer.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: amazon.speech.tts.BaseAudioWordMarkDemuxer$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$DataType = new int[DataType.values().length];
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$FrameType;

        static {
            try {
                $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$DataType[DataType.ID3_TAG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$DataType[DataType.MP3_22050_NO_PAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$DataType[DataType.MP3_22050_PAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$DataType[DataType.MP3_24000.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$DataType[DataType.MP3_16000.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$DataType[DataType.MP3_48000_256K.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$DataType[DataType.MP3_48000_128K.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$FrameType = new int[FrameType.values().length];
            try {
                $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$FrameType[FrameType.TXXX.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$amazon$speech$tts$BaseAudioWordMarkDemuxer$FrameType[FrameType.UNSUPPORTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum DataType {
        MP3_22050_PAD,
        MP3_22050_NO_PAD,
        MP3_24000,
        MP3_16000,
        MP3_48000_256K,
        MP3_48000_128K,
        ID3_TAG
    }

    /* loaded from: classes.dex */
    public interface Emitter {
        void scheduleSpeechMark(String str) throws Exception;
    }

    /* loaded from: classes.dex */
    public enum FrameType {
        TXXX,
        UNSUPPORTED
    }

    /* loaded from: classes.dex */
    public static class Id3FrameHeader {
        private static final int HEADER_FLAGS_LENGTH = 2;
        private static final int HEADER_ID_LENGTH = 4;
        final int id3FrameLength;
        final byte[] frameId = new byte[4];
        final byte[] flags = new byte[2];

        public Id3FrameHeader(byte[] bArr) {
            ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
            parsableByteArray.readBytes(this.frameId, 0, 4);
            this.id3FrameLength = parsableByteArray.readSynchSafeInt();
            parsableByteArray.readBytes(this.flags, 0, 2);
        }
    }

    /* loaded from: classes.dex */
    public static class Id3Header {
        private static final int HEADER_FLAGS_LENGTH = 1;
        private static final int HEADER_ID_LENGTH = 3;
        private static final int HEADER_VERSION_LENGTH = 2;
        final int id3Length;
        final byte[] id = new byte[3];
        final byte[] version = new byte[2];
        final byte[] flags = new byte[1];

        public Id3Header(byte[] bArr) throws IOException {
            ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
            parsableByteArray.readBytes(this.id, 0, 3);
            parsableByteArray.readBytes(this.version, 0, 2);
            parsableByteArray.readBytes(this.flags, 0, 1);
            this.id3Length = parsableByteArray.readSynchSafeInt();
        }
    }

    public BaseAudioWordMarkDemuxer(InputStream inputStream) {
        this.mInputStream = new BufferedInputStream(inputStream, 256);
    }

    static boolean checkFirstBytes(byte[] bArr, byte[] bArr2) {
        if (bArr2.length < bArr.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean rangeEquals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    public DataType getDataType() throws IOException {
        this.mInputStream.mark(3);
        readBytes(this.mHeaderBuffer, 3, this.mInputStream);
        this.mInputStream.reset();
        if (this.mFinished) {
            return null;
        }
        if (checkFirstBytes(ID3_BEGIN, this.mHeaderBuffer)) {
            return DataType.ID3_TAG;
        }
        if (!checkFirstBytes(MP3_BEGIN, this.mHeaderBuffer) && !checkFirstBytes(MP3_V1_BEGIN, this.mHeaderBuffer)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Data matches neither id3 nor mp3: ");
            outline107.append(Arrays.toString(this.mHeaderBuffer));
            throw new IOException(outline107.toString());
        }
        byte b = (byte) (this.mHeaderBuffer[2] & 255);
        if (b == -108) {
            return DataType.MP3_48000_128K;
        }
        if (b == -44) {
            return DataType.MP3_48000_256K;
        }
        if (b == 96) {
            return DataType.MP3_22050_NO_PAD;
        }
        if (b == 98) {
            return DataType.MP3_22050_PAD;
        }
        if (b == 100) {
            return DataType.MP3_24000;
        }
        if (b == 104) {
            return DataType.MP3_16000;
        }
        throw new IOException(GeneratedOutlineSupport1.outline32(this.mHeaderBuffer[2], GeneratedOutlineSupport1.outline107("Input file/stream seems to be corrupted or unknown mp3 format, cannot estimate length of the next mp3 frame:  data-type=")));
    }

    public FrameType getFrameType(byte[] bArr) {
        if (Arrays.equals(TXXX_FRAME_TYPE, bArr)) {
            return FrameType.TXXX;
        }
        return FrameType.UNSUPPORTED;
    }

    public InputStream getMp3Stream() {
        return new Mp3InputStream(this, null);
    }

    void handleId3() throws IOException {
        readBytes(this.mHeaderBuffer, 10, this.mInputStream);
        int i = new Id3Header(this.mHeaderBuffer).id3Length;
        byte[] bArr = new byte[i];
        readBytes(bArr, i, this.mInputStream);
        handleId3Frames(bArr);
    }

    void handleId3Frames(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        while (byteArrayInputStream.available() > 0) {
            readBytes(this.mHeaderBuffer, 10, byteArrayInputStream);
            Id3FrameHeader id3FrameHeader = new Id3FrameHeader(this.mHeaderBuffer);
            if (id3FrameHeader.id3FrameLength > 10000) {
                Log.w(TAG, "handleId3Frames | frame length exceeded the max frame count");
                byteArrayInputStream.skip(id3FrameHeader.id3FrameLength);
            } else {
                int ordinal = getFrameType(id3FrameHeader.frameId).ordinal();
                if (ordinal != 0) {
                    if (ordinal == 1) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Encountered unknown id3 tag: ");
                        outline107.append(new String(id3FrameHeader.frameId));
                        outline107.toString();
                        byteArrayInputStream.skip(id3FrameHeader.id3FrameLength);
                    }
                } else if (this.mSpeechMarksEmitter != null) {
                    int i = id3FrameHeader.id3FrameLength;
                    byte[] bArr2 = new byte[i];
                    readBytes(bArr2, i, byteArrayInputStream);
                    writeSpeechMarks(bArr2);
                }
            }
        }
        byteArrayInputStream.close();
    }

    int indexOf(byte[] bArr, int i, byte b) {
        while (i < bArr.length) {
            if (bArr[i] == b) {
                return i;
            }
            i++;
        }
        return -1;
    }

    boolean isFinished() {
        return this.mFinished;
    }

    int readBytes(byte[] bArr, int i, InputStream inputStream) throws IOException {
        int i2 = 0;
        int i3 = 0;
        while (i != 0 && i2 != -1) {
            i2 = inputStream.read(bArr, i3, i);
            if (i2 != -1) {
                i -= i2;
                i3 += i2;
            } else {
                this.mFinished = true;
            }
        }
        return i3;
    }

    public void setEmitter(Emitter emitter) {
        this.mSpeechMarksEmitter = emitter;
    }

    void writeSpeechMarks(byte[] bArr) throws IOException {
        byte b = bArr[0];
        byte[] bArr2 = {100, 97, 116, 97};
        int indexOf = indexOf(bArr, 1, (byte) 0) + 1;
        if (rangeEquals(bArr2, 0, bArr, 1, bArr2.length)) {
            if (91 != bArr[indexOf] || 93 != bArr[bArr.length - 2] || bArr[bArr.length - 1] != 0) {
                return;
            }
            String replace = new String(Arrays.copyOfRange(bArr, indexOf, bArr.length - 1)).replace("}{", "},{").replace("\n", "");
            try {
                if (this.mSpeechMarksEmitter == null) {
                    return;
                }
                this.mSpeechMarksEmitter.scheduleSpeechMark(replace);
                return;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                return;
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown full id3 frame is ");
        outline107.append(new String(bArr));
        outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class Mp3InputStream extends InputStream {
        public static final int EOF = -1;
        private final byte[] mLeftOversArray;
        private int mLeftOversLen;
        private int mLeftOversOffset;
        private final byte[] mSingeByteArray;

        private Mp3InputStream() {
            this.mSingeByteArray = new byte[1];
            this.mLeftOversArray = new byte[10000];
        }

        private int flushInternal(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.mLeftOversLen;
            if (i3 <= 0) {
                return BaseAudioWordMarkDemuxer.this.mFinished ? -1 : 0;
            }
            if (i2 >= i3) {
                i2 = i3;
            }
            System.arraycopy(this.mLeftOversArray, this.mLeftOversOffset, bArr, i, i2);
            int i4 = this.mLeftOversLen;
            if (i2 == i4) {
                this.mLeftOversLen = 0;
                this.mLeftOversOffset = 0;
            } else {
                this.mLeftOversLen = i4 - i2;
                this.mLeftOversOffset += i2;
            }
            return i2;
        }

        private int handleMp3(int i, byte[] bArr, int i2, int i3) throws IOException {
            if (i3 >= i && i2 == 0) {
                BaseAudioWordMarkDemuxer baseAudioWordMarkDemuxer = BaseAudioWordMarkDemuxer.this;
                return baseAudioWordMarkDemuxer.readBytes(bArr, i, baseAudioWordMarkDemuxer.mInputStream);
            }
            BaseAudioWordMarkDemuxer baseAudioWordMarkDemuxer2 = BaseAudioWordMarkDemuxer.this;
            int readBytes = baseAudioWordMarkDemuxer2.readBytes(this.mLeftOversArray, i, baseAudioWordMarkDemuxer2.mInputStream);
            if (readBytes <= 0) {
                return readBytes;
            }
            this.mLeftOversLen = readBytes;
            return flushInternal(bArr, i2, i3);
        }

        private int readInternal(byte[] bArr, int i, int i2) throws IOException {
            DataType dataType;
            while (this.mLeftOversLen <= 0) {
                if (BaseAudioWordMarkDemuxer.this.mFinished || (dataType = BaseAudioWordMarkDemuxer.this.getDataType()) == null) {
                    return -1;
                }
                switch (dataType.ordinal()) {
                    case 0:
                        return handleMp3(157, bArr, i, i2);
                    case 1:
                        return handleMp3(156, bArr, i, i2);
                    case 2:
                        return handleMp3(144, bArr, i, i2);
                    case 3:
                        return handleMp3(216, bArr, i, i2);
                    case 4:
                        return handleMp3(768, bArr, i, i2);
                    case 5:
                        return handleMp3(384, bArr, i, i2);
                    case 6:
                        BaseAudioWordMarkDemuxer.this.handleId3();
                        break;
                }
            }
            return flushInternal(bArr, i, i2);
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return BaseAudioWordMarkDemuxer.this.mInputStream.available();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            BaseAudioWordMarkDemuxer.this.mInputStream.close();
        }

        @Override // java.io.InputStream
        public synchronized void mark(int i) {
            BaseAudioWordMarkDemuxer.this.mInputStream.mark(i);
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return BaseAudioWordMarkDemuxer.this.mInputStream.markSupported();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            byte[] bArr = this.mSingeByteArray;
            int read = read(bArr, 0, bArr.length);
            byte[] bArr2 = this.mSingeByteArray;
            return read == bArr2.length ? bArr2[0] & 255 : read;
        }

        @Override // java.io.InputStream
        public synchronized void reset() throws IOException {
            BaseAudioWordMarkDemuxer.this.mInputStream.reset();
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            return BaseAudioWordMarkDemuxer.this.mInputStream.skip(j);
        }

        /* synthetic */ Mp3InputStream(BaseAudioWordMarkDemuxer baseAudioWordMarkDemuxer, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            return readInternal(bArr, i, i2);
        }
    }
}
