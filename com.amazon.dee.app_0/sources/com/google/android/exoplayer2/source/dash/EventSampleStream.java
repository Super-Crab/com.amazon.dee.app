package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.metadata.emsg.EventMessageEncoder;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.dash.manifest.EventStream;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
/* loaded from: classes2.dex */
final class EventSampleStream implements SampleStream {
    private int currentIndex;
    private EventStream eventStream;
    private boolean eventStreamAppendable;
    private long[] eventTimesUs;
    private boolean isFormatSentDownstream;
    private final Format upstreamFormat;
    private final EventMessageEncoder eventMessageEncoder = new EventMessageEncoder();
    private long pendingSeekPositionUs = C.TIME_UNSET;

    public EventSampleStream(EventStream eventStream, Format format, boolean z) {
        this.upstreamFormat = format;
        this.eventStream = eventStream;
        this.eventTimesUs = eventStream.presentationTimesUs;
        updateEventStream(eventStream, z);
    }

    public String eventStreamId() {
        return this.eventStream.id();
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public boolean isReady() {
        return true;
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public void maybeThrowError() throws IOException {
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (!z && this.isFormatSentDownstream) {
            int i = this.currentIndex;
            if (i == this.eventTimesUs.length) {
                if (this.eventStreamAppendable) {
                    return -3;
                }
                decoderInputBuffer.setFlags(4);
                return -4;
            }
            this.currentIndex = i + 1;
            byte[] encode = this.eventMessageEncoder.encode(this.eventStream.events[i]);
            decoderInputBuffer.ensureSpaceForWrite(encode.length);
            decoderInputBuffer.data.put(encode);
            decoderInputBuffer.timeUs = this.eventTimesUs[i];
            decoderInputBuffer.setFlags(1);
            return -4;
        }
        formatHolder.format = this.upstreamFormat;
        this.isFormatSentDownstream = true;
        return -5;
    }

    public void seekToUs(long j) {
        boolean z = false;
        this.currentIndex = Util.binarySearchCeil(this.eventTimesUs, j, true, false);
        if (this.eventStreamAppendable && this.currentIndex == this.eventTimesUs.length) {
            z = true;
        }
        if (!z) {
            j = C.TIME_UNSET;
        }
        this.pendingSeekPositionUs = j;
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public int skipData(long j) {
        int max = Math.max(this.currentIndex, Util.binarySearchCeil(this.eventTimesUs, j, true, false));
        int i = max - this.currentIndex;
        this.currentIndex = max;
        return i;
    }

    public void updateEventStream(EventStream eventStream, boolean z) {
        int i = this.currentIndex;
        long j = i == 0 ? -9223372036854775807L : this.eventTimesUs[i - 1];
        this.eventStreamAppendable = z;
        this.eventStream = eventStream;
        this.eventTimesUs = eventStream.presentationTimesUs;
        long j2 = this.pendingSeekPositionUs;
        if (j2 != C.TIME_UNSET) {
            seekToUs(j2);
        } else if (j == C.TIME_UNSET) {
        } else {
            this.currentIndex = Util.binarySearchCeil(this.eventTimesUs, j, false, false);
        }
    }
}
