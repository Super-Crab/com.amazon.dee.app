package amazon.csm.neoplayer;

import android.media.MediaFormat;
import android.os.Trace;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class Mp3Extractor {
    static final int ALARMS_AUDIO_BITRATE = 256000;
    static final int BUFFER_SIZE = 1440;
    private static final int BYTE_ARRAY_INIT_SIZE = 1024;
    static final String MP3_FORMAT_NAME = "audio/mpeg";
    private static final int MP3_HEADER_SIZE = 4;
    static final int MPEG_2_LAYER_III_SAMPLES_PER_FRAME = 576;
    static final int PADDING_LENGTH = 1;
    private static final String TAG = "Mp3Extractor";
    static final int TTS_AUDIO_BITRATE = 48000;
    private ChannelMode mChannel;
    private IExtractorAlerts mExtractorAlertsListener;
    boolean mFirstReadOccurred;
    final byte[] mHeaderBuffer;
    BufferedInputStream mInputStream;
    private long mLastTrackCurrentTimeInMs;
    private MediaFormat mMediaFormat;
    boolean mSlowReadAlerted;
    MpegDataType mStreamDataType;
    private byte[] mThrowawayBytes;
    private long mTrackCurrentTimeInMs;
    private byte[] mTransferBytes;
    private static final byte[] MP3_BEGIN = {-1, -13};
    private static final byte[] MP3_V1_BEGIN = {-1, -5};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum ChannelMode {
        CHANNEL_MODE_STEREO(2, "Stereo"),
        CHANNEL_MODE_JNTSTEREO(2, "Joint Stereo"),
        CHANNEL_MODE_MONO(1, "Mono"),
        CHANNEL_MODE_DEFAULT(1, "Default (Mono)");
        
        private final int mChannelNum;
        private final String mName;

        ChannelMode(int i, String str) {
            this.mChannelNum = i;
            this.mName = str;
        }

        static ChannelMode fromByte(byte b) {
            int i = (b & 192) >> 6;
            if (i != 0) {
                if (i == 1) {
                    return CHANNEL_MODE_JNTSTEREO;
                }
                if (i != 3) {
                    return CHANNEL_MODE_DEFAULT;
                }
                return CHANNEL_MODE_MONO;
            }
            return CHANNEL_MODE_STEREO;
        }

        int getChannelNum() {
            return this.mChannelNum;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Mp3Extractor() {
        Trace.beginSection("Mp3Extractor create");
        try {
            this.mHeaderBuffer = new byte[4];
            this.mThrowawayBytes = new byte[1024];
        } finally {
            Trace.endSection();
        }
    }

    private boolean advance(int i) throws IOException {
        Trace.beginSection("Mp3Extractor advance");
        try {
            if (this.mInputStream == null) {
                return false;
            }
            if (this.mThrowawayBytes.length < i) {
                this.mThrowawayBytes = new byte[i];
            }
            if (readBytesInternal(this.mThrowawayBytes, i, this.mInputStream) < i) {
                return false;
            }
            return true;
        } finally {
            Trace.endSection();
        }
    }

    private boolean advanceToMp3Type(MpegDataType mpegDataType) throws IOException {
        Trace.beginSection("Mp3Extractor advanceToMp3Type");
        try {
            if (this.mInputStream == null) {
                return false;
            }
            String str = "Advancing to " + mpegDataType;
            MpegDataType dataType = getDataType();
            while (true) {
                if (dataType != null) {
                    if (dataType.getSampleRate() == mpegDataType.getSampleRate()) {
                        return true;
                    }
                }
                if (!advance(1)) {
                    return false;
                }
                dataType = getDataType();
            }
        } finally {
            Trace.endSection();
        }
    }

    private boolean advanceToValidMp3() throws IOException {
        Trace.beginSection("Mp3Extractor advanceToValidMp3");
        try {
            if (this.mInputStream == null) {
                return false;
            }
            MpegDataType dataType = getDataType();
            while (dataType == null) {
                if (!advance(1)) {
                    return false;
                }
                dataType = getDataType();
            }
            return true;
        } finally {
            Trace.endSection();
        }
    }

    private boolean checkFirstBytes(byte[] bArr, byte[] bArr2) {
        Trace.beginSection("Mp3Extractor checkFirstBytes");
        try {
            if (bArr2.length < bArr.length) {
                return false;
            }
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
            }
            Trace.endSection();
            return true;
        } finally {
            Trace.endSection();
        }
    }

    private void closeInputStream() {
        BufferedInputStream bufferedInputStream = this.mInputStream;
        if (bufferedInputStream == null) {
            return;
        }
        try {
            bufferedInputStream.close();
        } catch (IOException e) {
            Log.w(TAG, "Couldn't close input stream.", e);
        }
    }

    private int readBytesInternal(byte[] bArr, int i, InputStream inputStream) throws IOException {
        Trace.beginSection("Mp3Extractor readBytesInternal");
        try {
            if (this.mInputStream == null) {
                return -1;
            }
            int i2 = 0;
            int i3 = 0;
            while (i != 0 && i2 != -1) {
                if (this.mInputStream == null) {
                    break;
                }
                i2 = inputStream.read(bArr, i3, i);
                if (i2 != -1) {
                    i -= i2;
                    i3 += i2;
                } else {
                    closeInputStream();
                    this.mInputStream = null;
                }
            }
            return i3;
        } finally {
            Trace.endSection();
        }
    }

    private void switchFormats() throws IOException {
        this.mStreamDataType = getDataType();
        this.mTransferBytes = new byte[this.mStreamDataType.getFrameLength() + 1];
    }

    BufferedInputStream createBufferedInputStream(InputStream inputStream, int i) {
        return new BufferedInputStream(inputStream, 1440);
    }

    void fireSlowReadAlert(long j, long j2) {
        if (j > 0 && !this.mSlowReadAlerted) {
            Log.w(TAG, "The input stream read was too slow.");
            this.mSlowReadAlerted = true;
            IExtractorAlerts iExtractorAlerts = this.mExtractorAlertsListener;
            if (iExtractorAlerts == null) {
                return;
            }
            iExtractorAlerts.onSlowRead(j, j2);
        }
    }

    public long getCurrentPosition() {
        return this.mTrackCurrentTimeInMs;
    }

    protected long getCurrentSystemTime() {
        return System.currentTimeMillis();
    }

    MpegDataType getDataType() throws IOException {
        Trace.beginSection("Mp3Extractor getDataType");
        try {
            if (this.mInputStream == null) {
                Log.w(TAG, "No input stream to get data type from. Returning null.");
                return null;
            }
            Trace.beginSection("Mp3Extractor getDataType mark");
            this.mInputStream.mark(4);
            Trace.endSection();
            if (readBytesInternal(this.mHeaderBuffer, 4, this.mInputStream) < 4) {
                return null;
            }
            Trace.beginSection("Mp3Extractor getDataType reset");
            this.mInputStream.reset();
            Trace.endSection();
            if (!checkFirstBytes(MP3_BEGIN, this.mHeaderBuffer) && !checkFirstBytes(MP3_V1_BEGIN, this.mHeaderBuffer)) {
                return null;
            }
            this.mChannel = ChannelMode.fromByte(this.mHeaderBuffer[3]);
            return MpegDataType.fromByte(this.mHeaderBuffer[2]);
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IExtractorAlerts getExtractorAlertsListener() {
        return this.mExtractorAlertsListener;
    }

    public int getFrameLength() {
        MpegDataType mpegDataType = this.mStreamDataType;
        if (mpegDataType == null) {
            return -2;
        }
        return mpegDataType.getFrameLength();
    }

    public int getSampleRate() {
        MpegDataType mpegDataType = this.mStreamDataType;
        if (mpegDataType == null) {
            return -2;
        }
        return mpegDataType.getSampleRate();
    }

    public MediaFormat getTrackFormat() {
        Trace.beginSection("Mp3Extractor getTrackFormat");
        try {
            int channelNum = this.mChannel == null ? 1 : this.mChannel.getChannelNum();
            this.mMediaFormat = MediaFormat.createAudioFormat("audio/mpeg", this.mStreamDataType.getSampleRate() * channelNum, channelNum);
            if (this.mStreamDataType.getSampleRate() == 48000) {
                this.mMediaFormat.setInteger("bitrate", 256000);
            } else {
                this.mMediaFormat.setInteger("bitrate", 48000);
            }
            return this.mMediaFormat;
        } finally {
            Trace.endSection();
        }
    }

    public boolean isFinished() {
        return this.mInputStream == null;
    }

    public String printHexHeader(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(String.format("%02X ", Byte.valueOf(bArr[i])));
            str = outline107.toString();
        }
        GeneratedOutlineSupport1.outline158("hexadecimal header= ", str);
        return str;
    }

    public synchronized int readBytes(ByteBuffer byteBuffer) throws IOException {
        Trace.beginSection("Mp3Extractor readBytes");
        if (this.mInputStream == null) {
            Trace.endSection();
            return -1;
        }
        MpegDataType dataType = getDataType();
        if (dataType != null && dataType.getSampleRate() == this.mStreamDataType.getSampleRate()) {
            this.mTrackCurrentTimeInMs += 576000 / this.mStreamDataType.getSampleRate();
            long currentSystemTime = getCurrentSystemTime();
            int frameLength = dataType.getFrameLength() + (dataType.isPadded() ? 1 : 0);
            int readBytesInternal = readBytesInternal(this.mTransferBytes, frameLength, this.mInputStream);
            long currentSystemTime2 = getCurrentSystemTime() - currentSystemTime;
            long j = this.mTrackCurrentTimeInMs - this.mLastTrackCurrentTimeInMs;
            if (this.mFirstReadOccurred && currentSystemTime2 > j) {
                fireSlowReadAlert(j, currentSystemTime2);
            }
            this.mFirstReadOccurred = true;
            this.mLastTrackCurrentTimeInMs = this.mTrackCurrentTimeInMs;
            byteBuffer.put(this.mTransferBytes, 0, frameLength);
            Trace.endSection();
            return readBytesInternal;
        } else if (advanceToMp3Type(this.mStreamDataType)) {
            int readBytes = readBytes(byteBuffer);
            Trace.endSection();
            return readBytes;
        } else {
            Trace.endSection();
            return -1;
        }
    }

    public synchronized void release() {
        Trace.beginSection("Mp3Extractor release");
        this.mMediaFormat = null;
        closeInputStream();
        this.mInputStream = null;
        this.mStreamDataType = null;
        this.mExtractorAlertsListener = null;
        Trace.endSection();
    }

    public synchronized void setDataSource(InputStream inputStream) throws IOException {
        Trace.beginSection("Mp3Extractor setDataSource");
        if (inputStream != null) {
            closeInputStream();
            this.mSlowReadAlerted = false;
            this.mFirstReadOccurred = false;
            String str = null;
            this.mInputStream = null;
            this.mInputStream = createBufferedInputStream(inputStream, 1440);
            this.mStreamDataType = null;
            if (advanceToValidMp3()) {
                switchFormats();
                this.mTrackCurrentTimeInMs = 0L;
                String str2 = TAG;
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(this.mStreamDataType.getSampleRate());
                if (this.mChannel != null) {
                    str = this.mChannel.toString();
                }
                objArr[1] = str;
                Log.i(str2, String.format("Format is %d. ChannelMode is %s", objArr));
                Trace.endSection();
            } else {
                throw new IOException("Stream contained no valid mp3 data. Valid mp3 data is MPEG Layer 2 Version III. Supported bitrate is 48kbps. Supported sample rates are 22050Hz, 24000Hz, and 16000Hz. See https://tiny.amazon.com/ncgitm63/deveamazpublsolualexalexdocs for more information.\nWe also temporarily support MPEG version 1 layer III bitrate 256kbps sample rate 48kHz");
            }
        } else {
            throw new IllegalArgumentException("inStream cannot be null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setExtractorAlertsListener(IExtractorAlerts iExtractorAlerts) {
        this.mExtractorAlertsListener = iExtractorAlerts;
    }
}
