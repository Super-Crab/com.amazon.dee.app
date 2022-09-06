package com.google.android.exoplayer2.extractor.ts;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.AESUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* loaded from: classes2.dex */
public final class AdtsReader implements ElementaryStreamReader {
    private static final int CRC_SIZE = 2;
    private static final int ENCRYPTED_BLOCKS_STATE = 1;
    private static final int HEADER_SIZE = 5;
    private static final int ID3_HEADER_SIZE = 10;
    private static final byte[] ID3_IDENTIFIER = {73, 68, 51};
    private static final int ID3_SIZE_OFFSET = 6;
    private static final int MATCH_STATE_FF = 512;
    private static final int MATCH_STATE_I = 768;
    private static final int MATCH_STATE_ID = 1024;
    private static final int MATCH_STATE_START = 256;
    private static final int MATCH_STATE_VALUE_SHIFT = 8;
    private static final int STATE_CHECKING_ADTS_HEADER = 1;
    private static final int STATE_FINDING_SAMPLE = 0;
    private static final int STATE_READING_ADTS_HEADER = 3;
    private static final int STATE_READING_ID3_HEADER = 2;
    private static final int STATE_READING_SAMPLE = 4;
    private static final String TAG = "AdtsReader";
    private static final int UNENCRYPTED_LEADER_SIZE = 16;
    private static final int UNENCRYPTED_LEADER_STATE = 0;
    private static final int UNENCRYPTED_TRAILER_STATE = 2;
    private static final int VERSION_UNSET = -1;
    private final ParsableBitArray adtsScratch;
    private int aesEncryptedReadingState;
    private byte[] aesEncryptionIv;
    private byte[] aesEncryptionKey;
    private AESUtil aesUtil;
    private int bytesRead;
    private int currentFrameVersion;
    private TrackOutput currentOutput;
    private long currentSampleDuration;
    private ParsableByteArray decryptedScratch;
    private int encryptedBlocksReadSize;
    private int encryptedBlocksSize;
    private final boolean exposeId3;
    private int firstFrameSampleRateIndex;
    private int firstFrameVersion;
    private String formatId;
    private boolean foundFirstFrame;
    private boolean hasCrc;
    private boolean hasOutputFormat;
    private final ParsableByteArray id3HeaderBuffer;
    private TrackOutput id3Output;
    @Nullable
    private final String language;
    private int matchState;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;
    private ParsableByteArray unencryptedScratch;
    private int unencryptedTrailerSize;

    public AdtsReader(boolean z) {
        this(z, null, null, null);
    }

    @EnsuresNonNull({ContactsModuleConstants.OUTPUT, "currentOutput", "id3Output"})
    private void assertTracksCreated() {
        Assertions.checkNotNull(this.output);
        Util.castNonNull(this.currentOutput);
        Util.castNonNull(this.id3Output);
    }

    private void checkAdtsHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() == 0) {
            return;
        }
        this.adtsScratch.data[0] = parsableByteArray.getData()[parsableByteArray.getPosition()];
        this.adtsScratch.setPosition(2);
        int readBits = this.adtsScratch.readBits(4);
        int i = this.firstFrameSampleRateIndex;
        if (i != -1 && readBits != i) {
            resetSync();
            return;
        }
        if (!this.foundFirstFrame) {
            this.foundFirstFrame = true;
            this.firstFrameVersion = this.currentFrameVersion;
            this.firstFrameSampleRateIndex = readBits;
        }
        setReadingAdtsHeaderState();
    }

    private boolean checkSyncPositionValid(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 1);
        if (!tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
            return false;
        }
        this.adtsScratch.setPosition(4);
        int readBits = this.adtsScratch.readBits(1);
        int i2 = this.firstFrameVersion;
        if (i2 != -1 && readBits != i2) {
            return false;
        }
        if (this.firstFrameSampleRateIndex != -1) {
            if (!tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
                return true;
            }
            this.adtsScratch.setPosition(2);
            if (this.adtsScratch.readBits(4) != this.firstFrameSampleRateIndex) {
                return false;
            }
            parsableByteArray.setPosition(i + 2);
        }
        if (!tryRead(parsableByteArray, this.adtsScratch.data, 4)) {
            return true;
        }
        this.adtsScratch.setPosition(14);
        int readBits2 = this.adtsScratch.readBits(13);
        if (readBits2 < 7) {
            return false;
        }
        byte[] data = parsableByteArray.getData();
        int limit = parsableByteArray.limit();
        int i3 = i + readBits2;
        if (i3 >= limit) {
            return true;
        }
        if (data[i3] == -1) {
            int i4 = i3 + 1;
            if (i4 == limit) {
                return true;
            }
            return isAdtsSyncBytes((byte) -1, data[i4]) && ((data[i4] & 8) >> 3) == readBits;
        } else if (data[i3] != 73) {
            return false;
        } else {
            int i5 = i3 + 1;
            if (i5 == limit) {
                return true;
            }
            if (data[i5] != 68) {
                return false;
            }
            int i6 = i3 + 2;
            return i6 == limit || data[i6] == 51;
        }
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        int min = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        this.bytesRead += min;
        return this.bytesRead == i;
    }

    private void findNextSample(ParsableByteArray parsableByteArray) {
        byte[] data = parsableByteArray.getData();
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        while (position < limit) {
            int i = position + 1;
            int i2 = data[position] & 255;
            if (this.matchState == 512 && isAdtsSyncBytes((byte) -1, (byte) i2) && (this.foundFirstFrame || checkSyncPositionValid(parsableByteArray, i - 2))) {
                this.currentFrameVersion = (i2 & 8) >> 3;
                boolean z = true;
                if ((i2 & 1) != 0) {
                    z = false;
                }
                this.hasCrc = z;
                if (!this.foundFirstFrame) {
                    setCheckingAdtsHeaderState();
                } else {
                    setReadingAdtsHeaderState();
                }
                parsableByteArray.setPosition(i);
                return;
            }
            int i3 = this.matchState;
            int i4 = i2 | i3;
            if (i4 == 329) {
                this.matchState = 768;
            } else if (i4 == 511) {
                this.matchState = 512;
            } else if (i4 == 836) {
                this.matchState = 1024;
            } else if (i4 == 1075) {
                setReadingId3HeaderState();
                parsableByteArray.setPosition(i);
                return;
            } else if (i3 != 256) {
                this.matchState = 256;
                i--;
            }
            position = i;
        }
        parsableByteArray.setPosition(position);
    }

    private boolean isAdtsSyncBytes(byte b, byte b2) {
        return isAdtsSyncWord(((b & 255) << 8) | (b2 & 255));
    }

    public static boolean isAdtsSyncWord(int i) {
        return (i & 65526) == 65520;
    }

    @RequiresNonNull({ContactsModuleConstants.OUTPUT})
    private void parseAdtsHeader() throws ParserException {
        this.adtsScratch.setPosition(0);
        if (!this.hasOutputFormat) {
            int readBits = this.adtsScratch.readBits(2) + 1;
            if (readBits != 2) {
                Log.w(TAG, "Detected audio object type: " + readBits + ", but assuming AAC LC.");
                readBits = 2;
            }
            this.adtsScratch.skipBits(5);
            byte[] buildAudioSpecificConfig = AacUtil.buildAudioSpecificConfig(readBits, this.firstFrameSampleRateIndex, this.adtsScratch.readBits(3));
            AacUtil.Config parseAudioSpecificConfig = AacUtil.parseAudioSpecificConfig(buildAudioSpecificConfig);
            Format build = new Format.Builder().setId(this.formatId).setSampleMimeType(MimeTypes.AUDIO_AAC).setCodecs(parseAudioSpecificConfig.codecs).setChannelCount(parseAudioSpecificConfig.channelCount).setSampleRate(parseAudioSpecificConfig.sampleRateHz).setInitializationData(Collections.singletonList(buildAudioSpecificConfig)).setLanguage(this.language).build();
            this.sampleDurationUs = 1024000000 / build.sampleRate;
            this.output.format(build);
            this.hasOutputFormat = true;
        } else {
            this.adtsScratch.skipBits(10);
        }
        this.adtsScratch.skipBits(4);
        int readBits2 = (this.adtsScratch.readBits(13) - 2) - 5;
        if (this.hasCrc) {
            readBits2 -= 2;
        }
        setReadingSampleState(this.output, this.sampleDurationUs, 0, readBits2);
    }

    @RequiresNonNull({"id3Output"})
    private void parseId3Header() {
        this.id3Output.sampleData(this.id3HeaderBuffer, 10);
        this.id3HeaderBuffer.setPosition(6);
        setReadingSampleState(this.id3Output, 0L, 10, this.id3HeaderBuffer.readSynchSafeInt() + 10);
    }

    @RequiresNonNull({"currentOutput"})
    private void readEncryptedSample(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.aesEncryptedReadingState;
            if (i != 0) {
                if (i == 1) {
                    int i2 = this.encryptedBlocksSize - this.encryptedBlocksReadSize;
                    if (i2 == 0) {
                        this.unencryptedScratch.setPosition(0);
                        this.aesEncryptedReadingState = 2;
                    } else {
                        int min = Math.min(i2, parsableByteArray.bytesLeft());
                        byte[] decryptCBCPartial = this.aesUtil.decryptCBCPartial(parsableByteArray.getData(), parsableByteArray.getPosition(), min);
                        parsableByteArray.skipBytes(min);
                        this.encryptedBlocksReadSize += min;
                        if (decryptCBCPartial != null && decryptCBCPartial.length != 0) {
                            this.decryptedScratch.reset(decryptCBCPartial, decryptCBCPartial.length);
                            TrackOutput trackOutput = this.currentOutput;
                            ParsableByteArray parsableByteArray2 = this.decryptedScratch;
                            trackOutput.sampleData(parsableByteArray2, parsableByteArray2.bytesLeft());
                        }
                    }
                } else if (i != 2) {
                    continue;
                } else {
                    int position = this.unencryptedScratch.getPosition();
                    int i3 = this.unencryptedTrailerSize;
                    if (position >= i3) {
                        this.unencryptedScratch.setPosition(0);
                        this.currentOutput.sampleData(this.unencryptedScratch, this.unencryptedTrailerSize);
                        this.aesEncryptedReadingState = 0;
                        this.currentOutput.sampleMetadata(this.timeUs, 1, this.sampleSize, 0, null);
                        this.timeUs += this.currentSampleDuration;
                        setFindingSampleState();
                        return;
                    }
                    int min2 = Math.min(i3 - this.unencryptedScratch.getPosition(), parsableByteArray.bytesLeft());
                    parsableByteArray.readBytes(this.unencryptedScratch.getData(), this.unencryptedScratch.getPosition(), min2);
                    this.unencryptedScratch.skipBytes(min2);
                }
            } else if (this.unencryptedScratch.getPosition() >= 16) {
                this.unencryptedScratch.setPosition(0);
                this.currentOutput.sampleData(this.unencryptedScratch, 16);
                this.aesEncryptedReadingState = 1;
                this.aesUtil.resetIV(this.aesEncryptionIv);
                this.encryptedBlocksReadSize = 0;
            } else {
                int min3 = Math.min(16 - this.unencryptedScratch.getPosition(), parsableByteArray.bytesLeft());
                parsableByteArray.readBytes(this.unencryptedScratch.getData(), this.unencryptedScratch.getPosition(), min3);
                this.unencryptedScratch.skipBytes(min3);
            }
        }
    }

    private void readSample(ParsableByteArray parsableByteArray) {
        if (this.currentOutput.equals(this.output) && this.aesEncryptionIv != null && this.aesEncryptionKey != null) {
            readEncryptedSample(parsableByteArray);
            return;
        }
        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
        this.currentOutput.sampleData(parsableByteArray, min);
        this.bytesRead += min;
        int i = this.bytesRead;
        int i2 = this.sampleSize;
        if (i != i2) {
            return;
        }
        this.currentOutput.sampleMetadata(this.timeUs, 1, i2, 0, null);
        this.timeUs += this.currentSampleDuration;
        setFindingSampleState();
    }

    private void resetSync() {
        this.foundFirstFrame = false;
        setFindingSampleState();
    }

    private void setCheckingAdtsHeaderState() {
        this.state = 1;
        this.bytesRead = 0;
    }

    private void setFindingSampleState() {
        this.state = 0;
        this.bytesRead = 0;
        this.matchState = 256;
    }

    private void setReadingAdtsHeaderState() {
        this.state = 3;
        this.bytesRead = 0;
    }

    private void setReadingId3HeaderState() {
        this.state = 2;
        this.bytesRead = ID3_IDENTIFIER.length;
        this.sampleSize = 0;
        this.id3HeaderBuffer.setPosition(0);
    }

    private void setReadingSampleState(TrackOutput trackOutput, long j, int i, int i2) {
        this.state = 4;
        this.bytesRead = i;
        this.currentOutput = trackOutput;
        this.currentSampleDuration = j;
        this.sampleSize = i2;
        if (!trackOutput.equals(this.output) || this.aesEncryptionKey == null || this.aesEncryptionIv == null) {
            return;
        }
        this.unencryptedScratch.setPosition(0);
        int i3 = i2 - 16;
        this.encryptedBlocksSize = i3 - (i3 % 16);
        this.unencryptedTrailerSize = i3 - this.encryptedBlocksSize;
    }

    private boolean tryRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        if (parsableByteArray.bytesLeft() < i) {
            return false;
        }
        parsableByteArray.readBytes(bArr, 0, i);
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        assertTracksCreated();
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i == 0) {
                findNextSample(parsableByteArray);
            } else if (i == 1) {
                checkAdtsHeader(parsableByteArray);
            } else if (i != 2) {
                if (i == 3) {
                    if (continueRead(parsableByteArray, this.adtsScratch.data, this.hasCrc ? 7 : 5)) {
                        parseAdtsHeader();
                    }
                } else if (i == 4) {
                    readSample(parsableByteArray);
                } else {
                    throw new IllegalStateException();
                }
            } else if (continueRead(parsableByteArray, this.id3HeaderBuffer.getData(), 10)) {
                parseId3Header();
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
        this.currentOutput = this.output;
        if (this.exposeId3) {
            trackIdGenerator.generateNewId();
            this.id3Output = extractorOutput.track(trackIdGenerator.getTrackId(), 5);
            this.id3Output.format(new Format.Builder().setId(trackIdGenerator.getFormatId()).setSampleMimeType(MimeTypes.APPLICATION_ID3).build());
            return;
        }
        this.id3Output = new DummyTrackOutput();
    }

    public long getSampleDurationUs() {
        return this.sampleDurationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        this.timeUs = j;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        resetSync();
    }

    public AdtsReader(boolean z, byte[] bArr, byte[] bArr2) {
        this(z, null, bArr, bArr2);
    }

    public AdtsReader(boolean z, String str) {
        this(z, str, null, null);
    }

    public AdtsReader(boolean z, String str, byte[] bArr, byte[] bArr2) {
        this.unencryptedScratch = new ParsableByteArray(16);
        this.decryptedScratch = new ParsableByteArray();
        this.aesEncryptedReadingState = 0;
        this.adtsScratch = new ParsableBitArray(new byte[7]);
        this.id3HeaderBuffer = new ParsableByteArray(Arrays.copyOf(ID3_IDENTIFIER, 10));
        setFindingSampleState();
        this.exposeId3 = z;
        this.language = str;
        this.aesEncryptionIv = bArr;
        this.aesEncryptionKey = bArr2;
        if (this.aesEncryptionKey != null && this.aesEncryptionIv != null) {
            this.aesUtil = new AESUtil();
            this.aesUtil.initKey(this.aesEncryptionKey);
        }
        this.firstFrameVersion = -1;
        this.firstFrameSampleRateIndex = -1;
        this.sampleDurationUs = C.TIME_UNSET;
    }
}
