package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox;
import com.google.android.exoplayer2.offline.FilterableManifest;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
/* loaded from: classes2.dex */
public class SsManifest implements FilterableManifest<SsManifest> {
    public static final int UNSET_LOOKAHEAD = -1;
    public final long durationUs;
    public final long dvrWindowLengthUs;
    public final boolean isLive;
    public final int lookAheadCount;
    public final int majorVersion;
    public final int minorVersion;
    @Nullable
    public final ProtectionElement protectionElement;
    public final StreamElement[] streamElements;

    /* loaded from: classes2.dex */
    public static class ProtectionElement {
        public final byte[] data;
        public final TrackEncryptionBox[] trackEncryptionBoxes;
        public final UUID uuid;

        public ProtectionElement(UUID uuid, byte[] bArr, TrackEncryptionBox[] trackEncryptionBoxArr) {
            this.uuid = uuid;
            this.data = bArr;
            this.trackEncryptionBoxes = trackEncryptionBoxArr;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SsManifest(int r22, int r23, long r24, long r26, long r28, int r30, boolean r31, @androidx.annotation.Nullable com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest.ProtectionElement r32, com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest.StreamElement[] r33) {
        /*
            r21 = this;
            r0 = 0
            int r2 = (r26 > r0 ? 1 : (r26 == r0 ? 0 : -1))
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r2 != 0) goto Ld
            r2 = r8
            goto L18
        Ld:
            r4 = 1000000(0xf4240, double:4.940656E-318)
            r2 = r26
            r6 = r24
            long r2 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r2, r4, r6)
        L18:
            int r0 = (r28 > r0 ? 1 : (r28 == r0 ? 0 : -1))
            if (r0 != 0) goto L1d
            goto L28
        L1d:
            r12 = 1000000(0xf4240, double:4.940656E-318)
            r10 = r28
            r14 = r24
            long r8 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r10, r12, r14)
        L28:
            r15 = r8
            r10 = r21
            r11 = r22
            r12 = r23
            r13 = r2
            r17 = r30
            r18 = r31
            r19 = r32
            r20 = r33
            r10.<init>(r11, r12, r13, r15, r17, r18, r19, r20)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest.<init>(int, int, long, long, long, int, boolean, com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest$ProtectionElement, com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest$StreamElement[]):void");
    }

    @Override // com.google.android.exoplayer2.offline.FilterableManifest
    /* renamed from: copy  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ SsManifest mo7425copy(List list) {
        return mo7425copy((List<StreamKey>) list);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.offline.FilterableManifest
    /* renamed from: copy */
    public final SsManifest mo7425copy(List<StreamKey> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        StreamElement streamElement = null;
        int i = 0;
        while (i < arrayList.size()) {
            StreamKey streamKey = (StreamKey) arrayList.get(i);
            StreamElement streamElement2 = this.streamElements[streamKey.groupIndex];
            if (streamElement2 != streamElement && streamElement != null) {
                arrayList2.add(streamElement.copy((Format[]) arrayList3.toArray(new Format[0])));
                arrayList3.clear();
            }
            arrayList3.add(streamElement2.formats[streamKey.trackIndex]);
            i++;
            streamElement = streamElement2;
        }
        if (streamElement != null) {
            arrayList2.add(streamElement.copy((Format[]) arrayList3.toArray(new Format[0])));
        }
        return new SsManifest(this.majorVersion, this.minorVersion, this.durationUs, this.dvrWindowLengthUs, this.lookAheadCount, this.isLive, this.protectionElement, (StreamElement[]) arrayList2.toArray(new StreamElement[0]));
    }

    /* loaded from: classes2.dex */
    public static class StreamElement {
        private static final String URL_PLACEHOLDER_BITRATE_1 = "{bitrate}";
        private static final String URL_PLACEHOLDER_BITRATE_2 = "{Bitrate}";
        private static final String URL_PLACEHOLDER_START_TIME_1 = "{start time}";
        private static final String URL_PLACEHOLDER_START_TIME_2 = "{start_time}";
        private final String baseUri;
        public final int chunkCount;
        private final List<Long> chunkStartTimes;
        private final long[] chunkStartTimesUs;
        private final String chunkTemplate;
        public final int displayHeight;
        public final int displayWidth;
        public final Format[] formats;
        @Nullable
        public final String language;
        private final long lastChunkDurationUs;
        public final int maxHeight;
        public final int maxWidth;
        public final String name;
        public final String subType;
        public final long timescale;
        public final int type;

        public StreamElement(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, @Nullable String str5, Format[] formatArr, List<Long> list, long j2) {
            this(str, str2, i, str3, j, str4, i2, i3, i4, i5, str5, formatArr, list, Util.scaleLargeTimestamps(list, 1000000L, j), Util.scaleLargeTimestamp(j2, 1000000L, j));
        }

        public Uri buildRequestUri(int i, int i2) {
            boolean z = true;
            Assertions.checkState(this.formats != null);
            Assertions.checkState(this.chunkStartTimes != null);
            if (i2 >= this.chunkStartTimes.size()) {
                z = false;
            }
            Assertions.checkState(z);
            String num = Integer.toString(this.formats[i].bitrate);
            String l = this.chunkStartTimes.get(i2).toString();
            return UriUtil.resolveToUri(this.baseUri, this.chunkTemplate.replace(URL_PLACEHOLDER_BITRATE_1, num).replace(URL_PLACEHOLDER_BITRATE_2, num).replace(URL_PLACEHOLDER_START_TIME_1, l).replace(URL_PLACEHOLDER_START_TIME_2, l));
        }

        public StreamElement copy(Format[] formatArr) {
            return new StreamElement(this.baseUri, this.chunkTemplate, this.type, this.subType, this.timescale, this.name, this.maxWidth, this.maxHeight, this.displayWidth, this.displayHeight, this.language, formatArr, this.chunkStartTimes, this.chunkStartTimesUs, this.lastChunkDurationUs);
        }

        public long getChunkDurationUs(int i) {
            if (i == this.chunkCount - 1) {
                return this.lastChunkDurationUs;
            }
            long[] jArr = this.chunkStartTimesUs;
            return jArr[i + 1] - jArr[i];
        }

        public int getChunkIndex(long j) {
            return Util.binarySearchFloor(this.chunkStartTimesUs, j, true, true);
        }

        public long getStartTimeUs(int i) {
            return this.chunkStartTimesUs[i];
        }

        private StreamElement(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, @Nullable String str5, Format[] formatArr, List<Long> list, long[] jArr, long j2) {
            this.baseUri = str;
            this.chunkTemplate = str2;
            this.type = i;
            this.subType = str3;
            this.timescale = j;
            this.name = str4;
            this.maxWidth = i2;
            this.maxHeight = i3;
            this.displayWidth = i4;
            this.displayHeight = i5;
            this.language = str5;
            this.formats = formatArr;
            this.chunkStartTimes = list;
            this.chunkStartTimesUs = jArr;
            this.lastChunkDurationUs = j2;
            this.chunkCount = list.size();
        }
    }

    private SsManifest(int i, int i2, long j, long j2, int i3, boolean z, @Nullable ProtectionElement protectionElement, StreamElement[] streamElementArr) {
        this.majorVersion = i;
        this.minorVersion = i2;
        this.durationUs = j;
        this.dvrWindowLengthUs = j2;
        this.lookAheadCount = i3;
        this.isLive = z;
        this.protectionElement = protectionElement;
        this.streamElements = streamElementArr;
    }
}
