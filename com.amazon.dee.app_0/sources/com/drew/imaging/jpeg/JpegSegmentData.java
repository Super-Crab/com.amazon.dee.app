package com.drew.imaging.jpeg;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes2.dex */
public class JpegSegmentData {
    @NotNull
    private final HashMap<Byte, List<byte[]>> _segmentDataMap = new HashMap<>(10);

    @NotNull
    private List<byte[]> getOrCreateSegmentList(byte b) {
        if (this._segmentDataMap.containsKey(Byte.valueOf(b))) {
            return this._segmentDataMap.get(Byte.valueOf(b));
        }
        ArrayList arrayList = new ArrayList();
        this._segmentDataMap.put(Byte.valueOf(b), arrayList);
        return arrayList;
    }

    @Nullable
    private List<byte[]> getSegmentList(byte b) {
        return this._segmentDataMap.get(Byte.valueOf(b));
    }

    public void addSegment(byte b, @NotNull byte[] bArr) {
        getOrCreateSegmentList(b).add(bArr);
    }

    public boolean containsSegment(byte b) {
        return this._segmentDataMap.containsKey(Byte.valueOf(b));
    }

    public boolean containsSegment(@NotNull JpegSegmentType jpegSegmentType) {
        return containsSegment(jpegSegmentType.byteValue);
    }

    @Nullable
    public byte[] getSegment(byte b) {
        return getSegment(b, 0);
    }

    @Nullable
    public byte[] getSegment(byte b, int i) {
        List<byte[]> segmentList = getSegmentList(b);
        if (segmentList == null || segmentList.size() <= i) {
            return null;
        }
        return segmentList.get(i);
    }

    @Nullable
    public byte[] getSegment(@NotNull JpegSegmentType jpegSegmentType) {
        return getSegment(jpegSegmentType.byteValue, 0);
    }

    @Nullable
    public byte[] getSegment(@NotNull JpegSegmentType jpegSegmentType, int i) {
        return getSegment(jpegSegmentType.byteValue, i);
    }

    public int getSegmentCount(byte b) {
        List<byte[]> segmentList = getSegmentList(b);
        if (segmentList == null) {
            return 0;
        }
        return segmentList.size();
    }

    public int getSegmentCount(@NotNull JpegSegmentType jpegSegmentType) {
        return getSegmentCount(jpegSegmentType.byteValue);
    }

    public Iterable<JpegSegmentType> getSegmentTypes() {
        HashSet hashSet = new HashSet();
        for (Byte b : this._segmentDataMap.keySet()) {
            JpegSegmentType fromByte = JpegSegmentType.fromByte(b.byteValue());
            if (fromByte == null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Should not have a segmentTypeByte that is not in the enum: ");
                outline107.append(Integer.toHexString(b.byteValue()));
                throw new IllegalStateException(outline107.toString());
            }
            hashSet.add(fromByte);
        }
        return hashSet;
    }

    @NotNull
    public Iterable<byte[]> getSegments(byte b) {
        List<byte[]> segmentList = getSegmentList(b);
        return segmentList == null ? new ArrayList() : segmentList;
    }

    @NotNull
    public Iterable<byte[]> getSegments(@NotNull JpegSegmentType jpegSegmentType) {
        return getSegments(jpegSegmentType.byteValue);
    }

    public void removeSegment(byte b) {
        this._segmentDataMap.remove(Byte.valueOf(b));
    }

    public void removeSegment(@NotNull JpegSegmentType jpegSegmentType) {
        removeSegment(jpegSegmentType.byteValue);
    }

    public void removeSegmentOccurrence(byte b, int i) {
        this._segmentDataMap.get(Byte.valueOf(b)).remove(i);
    }

    public void removeSegmentOccurrence(@NotNull JpegSegmentType jpegSegmentType, int i) {
        removeSegmentOccurrence(jpegSegmentType.byteValue, i);
    }
}
