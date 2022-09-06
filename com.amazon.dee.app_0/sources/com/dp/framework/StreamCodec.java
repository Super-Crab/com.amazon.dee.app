package com.dp.framework;

import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public interface StreamCodec {
    String decodeAsciiString(InputStream inputStream, int i) throws CodecException;

    boolean decodeBool(InputStream inputStream) throws CodecException;

    byte[] decodeByteArray(InputStream inputStream) throws CodecException;

    int decodeInt(InputStream inputStream) throws CodecException;

    long decodeLong(InputStream inputStream) throws CodecException;

    String decodeString(InputStream inputStream) throws CodecException;

    void encodeAsciiString(String str, OutputStream outputStream) throws CodecException;

    void encodeBool(boolean z, OutputStream outputStream) throws CodecException;

    void encodeByteArray(byte[] bArr, OutputStream outputStream) throws CodecException;

    void encodeInt(int i, OutputStream outputStream) throws CodecException;

    void encodeLong(long j, OutputStream outputStream) throws CodecException;

    void encodeString(String str, OutputStream outputStream) throws CodecException;

    String getName();

    int getSizeOfBool();

    int getSizeOfDelimiter();

    @Deprecated
    int getSizeOfEncodedMaxInteger();

    int getSizeOfInt();

    int getSizeOfLengthEncoding();

    int getSizeOfLong();
}
