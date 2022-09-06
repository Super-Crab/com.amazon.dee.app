package com.drew.imaging.png;

import com.drew.lang.annotations.NotNull;
/* loaded from: classes2.dex */
public class PngChunk {
    @NotNull
    private final byte[] _bytes;
    @NotNull
    private final PngChunkType _chunkType;

    public PngChunk(@NotNull PngChunkType pngChunkType, @NotNull byte[] bArr) {
        this._chunkType = pngChunkType;
        this._bytes = bArr;
    }

    @NotNull
    public byte[] getBytes() {
        return this._bytes;
    }

    @NotNull
    public PngChunkType getType() {
        return this._chunkType;
    }
}
