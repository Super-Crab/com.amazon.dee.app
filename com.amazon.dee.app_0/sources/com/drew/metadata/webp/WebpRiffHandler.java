package com.drew.metadata.webp;

import com.drew.imaging.riff.RiffHandler;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.xmp.XmpReader;
import java.io.IOException;
/* loaded from: classes2.dex */
public class WebpRiffHandler implements RiffHandler {
    @NotNull
    private final Metadata _metadata;

    public WebpRiffHandler(@NotNull Metadata metadata) {
        this._metadata = metadata;
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public void processChunk(@NotNull String str, @NotNull byte[] bArr) {
        WebpDirectory webpDirectory = new WebpDirectory();
        if (str.equals(WebpDirectory.CHUNK_EXIF)) {
            new ExifReader().extract(new ByteArrayReader(bArr), this._metadata);
        } else if (str.equals(WebpDirectory.CHUNK_ICCP)) {
            new IccReader().extract(new ByteArrayReader(bArr), this._metadata);
        } else if (str.equals(WebpDirectory.CHUNK_XMP)) {
            new XmpReader().extract(bArr, this._metadata);
        } else {
            try {
                if (str.equals(WebpDirectory.CHUNK_VP8X) && bArr.length == 10) {
                    ByteArrayReader byteArrayReader = new ByteArrayReader(bArr);
                    byteArrayReader.setMotorolaByteOrder(false);
                    boolean bit = byteArrayReader.getBit(1);
                    boolean bit2 = byteArrayReader.getBit(4);
                    int int24 = byteArrayReader.getInt24(4);
                    int int242 = byteArrayReader.getInt24(7);
                    webpDirectory.setInt(2, int24 + 1);
                    webpDirectory.setInt(1, int242 + 1);
                    webpDirectory.setBoolean(3, bit2);
                    webpDirectory.setBoolean(4, bit);
                    this._metadata.addDirectory(webpDirectory);
                } else if (!str.equals(WebpDirectory.CHUNK_VP8L) || bArr.length <= 4) {
                    if (!str.equals(WebpDirectory.CHUNK_VP8) || bArr.length <= 9) {
                        return;
                    }
                    ByteArrayReader byteArrayReader2 = new ByteArrayReader(bArr);
                    byteArrayReader2.setMotorolaByteOrder(false);
                    try {
                        if (byteArrayReader2.getUInt8(3) == 157 && byteArrayReader2.getUInt8(4) == 1 && byteArrayReader2.getUInt8(5) == 42) {
                            int uInt16 = byteArrayReader2.getUInt16(6);
                            int uInt162 = byteArrayReader2.getUInt16(8);
                            webpDirectory.setInt(2, uInt16);
                            webpDirectory.setInt(1, uInt162);
                            this._metadata.addDirectory(webpDirectory);
                        }
                    } catch (IOException e) {
                        webpDirectory.addError(e.getMessage());
                    }
                } else {
                    ByteArrayReader byteArrayReader3 = new ByteArrayReader(bArr);
                    byteArrayReader3.setMotorolaByteOrder(false);
                    if (byteArrayReader3.getInt8(0) != 47) {
                        return;
                    }
                    short uInt8 = byteArrayReader3.getUInt8(1);
                    short uInt82 = byteArrayReader3.getUInt8(2);
                    short uInt83 = byteArrayReader3.getUInt8(3);
                    webpDirectory.setInt(2, (uInt8 | ((uInt82 & 63) << 8)) + 1);
                    webpDirectory.setInt(1, (((byteArrayReader3.getUInt8(4) & 15) << 10) | (uInt83 << 2) | ((uInt82 & 192) >> 6)) + 1);
                    this._metadata.addDirectory(webpDirectory);
                }
            } catch (IOException e2) {
                e2.printStackTrace(System.err);
            }
        }
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public boolean shouldAcceptChunk(@NotNull String str) {
        return str.equals(WebpDirectory.CHUNK_VP8X) || str.equals(WebpDirectory.CHUNK_VP8L) || str.equals(WebpDirectory.CHUNK_VP8) || str.equals(WebpDirectory.CHUNK_EXIF) || str.equals(WebpDirectory.CHUNK_ICCP) || str.equals(WebpDirectory.CHUNK_XMP);
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public boolean shouldAcceptList(@NotNull String str) {
        return false;
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public boolean shouldAcceptRiffIdentifier(@NotNull String str) {
        return str.equals(WebpDirectory.FORMAT);
    }
}
