package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.InputStream;
/* loaded from: classes4.dex */
public interface Parser<MessageType> {
    /* renamed from: parseDelimitedFrom */
    MessageType mo11985parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo11986parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parseFrom */
    MessageType mo11987parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    /* renamed from: parsePartialFrom */
    MessageType mo11977parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;
}
