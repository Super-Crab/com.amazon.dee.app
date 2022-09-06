package com.drew.imaging.png;

import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import java.io.IOException;
/* loaded from: classes2.dex */
public class PngHeader {
    private byte _bitsPerSample;
    @NotNull
    private PngColorType _colorType;
    private byte _compressionType;
    private byte _filterMethod;
    private int _imageHeight;
    private int _imageWidth;
    private byte _interlaceMethod;

    public PngHeader(@NotNull byte[] bArr) throws PngProcessingException {
        if (bArr.length == 13) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            try {
                this._imageWidth = sequentialByteArrayReader.getInt32();
                this._imageHeight = sequentialByteArrayReader.getInt32();
                this._bitsPerSample = sequentialByteArrayReader.getInt8();
                byte int8 = sequentialByteArrayReader.getInt8();
                PngColorType fromNumericValue = PngColorType.fromNumericValue(int8);
                if (fromNumericValue == null) {
                    throw new PngProcessingException("Unexpected PNG color type: " + ((int) int8));
                }
                this._colorType = fromNumericValue;
                this._compressionType = sequentialByteArrayReader.getInt8();
                this._filterMethod = sequentialByteArrayReader.getInt8();
                this._interlaceMethod = sequentialByteArrayReader.getInt8();
                return;
            } catch (IOException e) {
                throw new PngProcessingException(e);
            }
        }
        throw new PngProcessingException("PNG header chunk must have 13 data bytes");
    }

    public byte getBitsPerSample() {
        return this._bitsPerSample;
    }

    @NotNull
    public PngColorType getColorType() {
        return this._colorType;
    }

    public byte getCompressionType() {
        return this._compressionType;
    }

    public byte getFilterMethod() {
        return this._filterMethod;
    }

    public int getImageHeight() {
        return this._imageHeight;
    }

    public int getImageWidth() {
        return this._imageWidth;
    }

    public byte getInterlaceMethod() {
        return this._interlaceMethod;
    }
}
