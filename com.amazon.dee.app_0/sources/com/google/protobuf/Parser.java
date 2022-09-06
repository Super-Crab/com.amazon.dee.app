package com.google.protobuf;

import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes3.dex */
public interface Parser<MessageType> {
    /* renamed from: parseDelimitedFrom */
    MessageType mo8394parseDelimitedFrom(InputStream inputStream) throws InvalidProtocolBufferException;

    /* renamed from: parseDelimitedFrom */
    MessageType mo8395parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8396parseFrom(ByteString byteString) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8397parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8398parseFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8399parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8400parseFrom(InputStream inputStream) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8401parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8402parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8403parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8404parseFrom(byte[] bArr) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8405parseFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8406parseFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo8407parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialDelimitedFrom */
    MessageType mo8408parsePartialDelimitedFrom(InputStream inputStream) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialDelimitedFrom */
    MessageType mo8409parsePartialDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo8410parsePartialFrom(ByteString byteString) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo8411parsePartialFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo8412parsePartialFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo8413parsePartialFrom(InputStream inputStream) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo8414parsePartialFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo8415parsePartialFrom(byte[] bArr) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo8416parsePartialFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo8417parsePartialFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo8418parsePartialFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;
}
