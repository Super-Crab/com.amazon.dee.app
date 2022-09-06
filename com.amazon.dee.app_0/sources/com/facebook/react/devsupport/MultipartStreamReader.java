package com.facebook.react.devsupport;

import com.amazon.alexa.fitness.utils.FullScreenUpdaterUtilKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
/* loaded from: classes2.dex */
public class MultipartStreamReader {
    private static final String CRLF = "\r\n";
    private final String mBoundary;
    private long mLastProgressEvent;
    private final BufferedSource mSource;

    /* loaded from: classes2.dex */
    public interface ChunkListener {
        void onChunkComplete(Map<String, String> map, Buffer buffer, boolean z) throws IOException;

        void onChunkProgress(Map<String, String> map, long j, long j2) throws IOException;
    }

    public MultipartStreamReader(BufferedSource bufferedSource, String str) {
        this.mSource = bufferedSource;
        this.mBoundary = str;
    }

    private void emitChunk(Buffer buffer, boolean z, ChunkListener chunkListener) throws IOException {
        ByteString encodeUtf8 = ByteString.encodeUtf8("\r\n\r\n");
        long indexOf = buffer.indexOf(encodeUtf8);
        if (indexOf == -1) {
            chunkListener.onChunkComplete(null, buffer, z);
            return;
        }
        Buffer buffer2 = new Buffer();
        Buffer buffer3 = new Buffer();
        buffer.read(buffer2, indexOf);
        buffer.skip(encodeUtf8.size());
        buffer.readAll(buffer3);
        chunkListener.onChunkComplete(parseHeaders(buffer2), buffer3, z);
    }

    private void emitProgress(Map<String, String> map, long j, boolean z, ChunkListener chunkListener) throws IOException {
        if (map == null || chunkListener == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastProgressEvent <= 16 && !z) {
            return;
        }
        this.mLastProgressEvent = currentTimeMillis;
        chunkListener.onChunkProgress(map, j, map.get("Content-Length") != null ? Long.parseLong(map.get("Content-Length")) : 0L);
    }

    private Map<String, String> parseHeaders(Buffer buffer) {
        String[] split;
        HashMap hashMap = new HashMap();
        for (String str : buffer.readUtf8().split("\r\n")) {
            int indexOf = str.indexOf(":");
            if (indexOf != -1) {
                hashMap.put(str.substring(0, indexOf).trim(), str.substring(indexOf + 1).trim());
            }
        }
        return hashMap;
    }

    public boolean readAllParts(ChunkListener chunkListener) throws IOException {
        boolean z;
        boolean z2;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\r\n--");
        outline107.append(this.mBoundary);
        outline107.append("\r\n");
        ByteString encodeUtf8 = ByteString.encodeUtf8(outline107.toString());
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("\r\n--");
        outline1072.append(this.mBoundary);
        outline1072.append(FullScreenUpdaterUtilKt.DEFAULT_DATA);
        outline1072.append("\r\n");
        ByteString encodeUtf82 = ByteString.encodeUtf8(outline1072.toString());
        ByteString encodeUtf83 = ByteString.encodeUtf8("\r\n\r\n");
        Buffer buffer = new Buffer();
        Map<String, String> map = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        while (true) {
            long max = Math.max(j - encodeUtf82.size(), j2);
            long indexOf = buffer.indexOf(encodeUtf8, max);
            if (indexOf == -1) {
                indexOf = buffer.indexOf(encodeUtf82, max);
                z = true;
            } else {
                z = false;
            }
            if (indexOf == -1) {
                long size = buffer.size();
                if (map == null) {
                    long indexOf2 = buffer.indexOf(encodeUtf83, max);
                    if (indexOf2 >= 0) {
                        this.mSource.read(buffer, indexOf2);
                        Buffer buffer2 = new Buffer();
                        buffer.copyTo(buffer2, max, indexOf2 - max);
                        map = parseHeaders(buffer2);
                        j3 = buffer2.size() + encodeUtf83.size();
                    }
                } else {
                    emitProgress(map, buffer.size() - j3, false, chunkListener);
                }
                if (this.mSource.read(buffer, 4096) <= 0) {
                    return false;
                }
                j = size;
            } else {
                long j4 = indexOf - j2;
                if (j2 > 0) {
                    Buffer buffer3 = new Buffer();
                    buffer.skip(j2);
                    buffer.read(buffer3, j4);
                    emitProgress(map, buffer3.size() - j3, true, chunkListener);
                    z2 = z;
                    emitChunk(buffer3, z2, chunkListener);
                    map = null;
                    j3 = 0;
                } else {
                    z2 = z;
                    buffer.skip(indexOf);
                }
                if (z2) {
                    return true;
                }
                j2 = encodeUtf8.size();
                j = j2;
            }
        }
    }
}
