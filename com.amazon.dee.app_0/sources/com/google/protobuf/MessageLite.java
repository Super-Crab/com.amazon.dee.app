package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes3.dex */
public interface MessageLite extends MessageLiteOrBuilder {

    /* loaded from: classes3.dex */
    public interface Builder extends MessageLiteOrBuilder, Cloneable {
        /* renamed from: build */
        MessageLite mo10084build();

        /* renamed from: buildPartial */
        MessageLite mo10085buildPartial();

        /* renamed from: clear */
        Builder mo10087clear();

        /* renamed from: clone */
        Builder mo10093clone();

        boolean mergeDelimitedFrom(InputStream inputStream) throws IOException;

        boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        /* renamed from: mergeFrom */
        Builder mo9962mergeFrom(ByteString byteString) throws InvalidProtocolBufferException;

        /* renamed from: mergeFrom */
        Builder mo9963mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        /* renamed from: mergeFrom */
        Builder mo9964mergeFrom(CodedInputStream codedInputStream) throws IOException;

        /* renamed from: mergeFrom */
        Builder mo10097mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        /* renamed from: mergeFrom */
        Builder mo9966mergeFrom(MessageLite messageLite);

        /* renamed from: mergeFrom */
        Builder mo9967mergeFrom(InputStream inputStream) throws IOException;

        /* renamed from: mergeFrom */
        Builder mo9968mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        /* renamed from: mergeFrom */
        Builder mo9969mergeFrom(byte[] bArr) throws InvalidProtocolBufferException;

        /* renamed from: mergeFrom */
        Builder mo9970mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

        /* renamed from: mergeFrom */
        Builder mo9971mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        /* renamed from: mergeFrom */
        Builder mo9972mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;
    }

    /* renamed from: getParserForType */
    Parser<? extends MessageLite> mo9954getParserForType();

    int getSerializedSize();

    /* renamed from: newBuilderForType */
    Builder mo10079newBuilderForType();

    /* renamed from: toBuilder */
    Builder mo10081toBuilder();

    byte[] toByteArray();

    ByteString toByteString();

    void writeDelimitedTo(OutputStream outputStream) throws IOException;

    void writeTo(CodedOutputStream codedOutputStream) throws IOException;

    void writeTo(OutputStream outputStream) throws IOException;
}
