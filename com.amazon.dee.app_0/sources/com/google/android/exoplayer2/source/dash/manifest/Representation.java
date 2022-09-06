package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.dash.DashSegmentIndex;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class Representation {
    public static final long REVISION_ID_DEFAULT = -1;
    public final String baseUrl;
    public final Format format;
    public final List<Descriptor> inbandEventStreams;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;
    public final long revisionId;

    /* loaded from: classes2.dex */
    public static class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        @VisibleForTesting
        final SegmentBase.MultiSegmentBase segmentBase;

        public MultiSegmentRepresentation(long j, Format format, String str, SegmentBase.MultiSegmentBase multiSegmentBase, @Nullable List<Descriptor> list) {
            super(j, format, str, multiSegmentBase, list);
            this.segmentBase = multiSegmentBase;
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public int getAvailableSegmentCount(long j, long j2) {
            return this.segmentBase.getAvailableSegmentCount(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        @Nullable
        public String getCacheKey() {
            return null;
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getDurationUs(long j, long j2) {
            return this.segmentBase.getSegmentDurationUs(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getFirstAvailableSegmentNum(long j, long j2) {
            return this.segmentBase.getFirstAvailableSegmentNum(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getFirstSegmentNum() {
            return this.segmentBase.getFirstSegmentNum();
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        public DashSegmentIndex getIndex() {
            return this;
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        @Nullable
        public RangedUri getIndexUri() {
            return null;
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getNextSegmentAvailableTimeUs(long j, long j2) {
            return this.segmentBase.getNextSegmentAvailableTimeUs(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public int getSegmentCount(long j) {
            return this.segmentBase.getSegmentCount(j);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getSegmentNum(long j, long j2) {
            return this.segmentBase.getSegmentNum(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public RangedUri getSegmentUrl(long j) {
            return this.segmentBase.getSegmentUrl(this, j);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public long getTimeUs(long j) {
            return this.segmentBase.getSegmentTimeUs(j);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashSegmentIndex
        public boolean isExplicit() {
            return this.segmentBase.isExplicit();
        }
    }

    /* loaded from: classes2.dex */
    public static class SingleSegmentRepresentation extends Representation {
        @Nullable
        private final String cacheKey;
        public final long contentLength;
        @Nullable
        private final RangedUri indexUri;
        @Nullable
        private final SingleSegmentIndex segmentIndex;
        public final Uri uri;

        public SingleSegmentRepresentation(long j, Format format, String str, SegmentBase.SingleSegmentBase singleSegmentBase, @Nullable List<Descriptor> list, @Nullable String str2, long j2) {
            super(j, format, str, singleSegmentBase, list);
            this.uri = Uri.parse(str);
            this.indexUri = singleSegmentBase.getIndex();
            this.cacheKey = str2;
            this.contentLength = j2;
            this.segmentIndex = this.indexUri != null ? null : new SingleSegmentIndex(new RangedUri(null, 0L, j2));
        }

        public static SingleSegmentRepresentation newInstance(long j, Format format, String str, long j2, long j3, long j4, long j5, List<Descriptor> list, @Nullable String str2, long j6) {
            return new SingleSegmentRepresentation(j, format, str, new SegmentBase.SingleSegmentBase(new RangedUri(null, j2, (j3 - j2) + 1), 1L, 0L, j4, (j5 - j4) + 1), list, str2, j6);
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        @Nullable
        public String getCacheKey() {
            return this.cacheKey;
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        @Nullable
        public DashSegmentIndex getIndex() {
            return this.segmentIndex;
        }

        @Override // com.google.android.exoplayer2.source.dash.manifest.Representation
        @Nullable
        public RangedUri getIndexUri() {
            return this.indexUri;
        }
    }

    public static Representation newInstance(long j, Format format, String str, SegmentBase segmentBase) {
        return newInstance(j, format, str, segmentBase, null);
    }

    @Nullable
    public abstract String getCacheKey();

    @Nullable
    public abstract DashSegmentIndex getIndex();

    @Nullable
    public abstract RangedUri getIndexUri();

    @Nullable
    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }

    private Representation(long j, Format format, String str, SegmentBase segmentBase, @Nullable List<Descriptor> list) {
        List<Descriptor> unmodifiableList;
        this.revisionId = j;
        this.format = format;
        this.baseUrl = str;
        if (list == null) {
            unmodifiableList = Collections.emptyList();
        } else {
            unmodifiableList = Collections.unmodifiableList(list);
        }
        this.inbandEventStreams = unmodifiableList;
        this.initializationUri = segmentBase.getInitialization(this);
        this.presentationTimeOffsetUs = segmentBase.getPresentationTimeOffsetUs();
    }

    public static Representation newInstance(long j, Format format, String str, SegmentBase segmentBase, @Nullable List<Descriptor> list) {
        return newInstance(j, format, str, segmentBase, list, null);
    }

    public static Representation newInstance(long j, Format format, String str, SegmentBase segmentBase, @Nullable List<Descriptor> list, @Nullable String str2) {
        if (segmentBase instanceof SegmentBase.SingleSegmentBase) {
            return new SingleSegmentRepresentation(j, format, str, (SegmentBase.SingleSegmentBase) segmentBase, list, str2, -1L);
        }
        if (segmentBase instanceof SegmentBase.MultiSegmentBase) {
            return new MultiSegmentRepresentation(j, format, str, (SegmentBase.MultiSegmentBase) segmentBase, list);
        }
        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }
}
