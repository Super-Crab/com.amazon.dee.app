package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes3.dex */
public abstract class AbstractParser<MessageType extends MessageLite> implements Parser<MessageType> {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();

    private MessageType checkMessageInitialized(MessageType messagetype) throws InvalidProtocolBufferException {
        if (messagetype == null || messagetype.isInitialized()) {
            return messagetype;
        }
        throw newUninitializedMessageException(messagetype).asInvalidProtocolBufferException().setUnfinishedMessage(messagetype);
    }

    private UninitializedMessageException newUninitializedMessageException(MessageType messagetype) {
        return messagetype instanceof AbstractMessageLite ? ((AbstractMessageLite) messagetype).newUninitializedMessageException() : new UninitializedMessageException(messagetype);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseDelimitedFrom */
    public MessageType mo8394parseDelimitedFrom(InputStream inputStream) throws InvalidProtocolBufferException {
        return mo8395parseDelimitedFrom(inputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseDelimitedFrom */
    public MessageType mo8395parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(mo8409parsePartialDelimitedFrom(inputStream, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8396parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return mo8397parseFrom(byteString, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8397parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(mo8411parsePartialFrom(byteString, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8398parseFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return mo8399parseFrom(codedInputStream, EMPTY_REGISTRY);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8399parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MessageType) checkMessageInitialized((MessageLite) mo10082parsePartialFrom(codedInputStream, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8400parseFrom(InputStream inputStream) throws InvalidProtocolBufferException {
        return mo8401parseFrom(inputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8401parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(mo8414parsePartialFrom(inputStream, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8402parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return mo8403parseFrom(byteBuffer, EMPTY_REGISTRY);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8403parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream newInstance = CodedInputStream.newInstance(byteBuffer);
            MessageLite messageLite = (MessageLite) mo10082parsePartialFrom(newInstance, extensionRegistryLite);
            try {
                newInstance.checkLastTagWas(0);
                return (MessageType) checkMessageInitialized(messageLite);
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(messageLite);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8404parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return mo8407parseFrom(bArr, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8405parseFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
        return mo8406parseFrom(bArr, i, i2, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8406parseFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return checkMessageInitialized(mo8417parsePartialFrom(bArr, i, i2, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parseFrom */
    public MessageType mo8407parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return mo8406parseFrom(bArr, 0, bArr.length, extensionRegistryLite);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialDelimitedFrom */
    public MessageType mo8408parsePartialDelimitedFrom(InputStream inputStream) throws InvalidProtocolBufferException {
        return mo8409parsePartialDelimitedFrom(inputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialDelimitedFrom */
    public MessageType mo8409parsePartialDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            int read = inputStream.read();
            if (read != -1) {
                return mo8414parsePartialFrom((InputStream) new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(read, inputStream)), extensionRegistryLite);
            }
            return null;
        } catch (IOException e) {
            throw new InvalidProtocolBufferException(e);
        }
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialFrom */
    public MessageType mo8410parsePartialFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return mo8411parsePartialFrom(byteString, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialFrom */
    public MessageType mo8411parsePartialFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream newCodedInput = byteString.newCodedInput();
            MessageType messagetype = (MessageType) mo10082parsePartialFrom(newCodedInput, extensionRegistryLite);
            try {
                newCodedInput.checkLastTagWas(0);
                return messagetype;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(messagetype);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialFrom */
    public MessageType mo8412parsePartialFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return (MessageType) mo10082parsePartialFrom(codedInputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialFrom */
    public MessageType mo8413parsePartialFrom(InputStream inputStream) throws InvalidProtocolBufferException {
        return mo8414parsePartialFrom(inputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialFrom */
    public MessageType mo8414parsePartialFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
        MessageType messagetype = (MessageType) mo10082parsePartialFrom(newInstance, extensionRegistryLite);
        try {
            newInstance.checkLastTagWas(0);
            return messagetype;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(messagetype);
        }
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialFrom */
    public MessageType mo8415parsePartialFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return mo8417parsePartialFrom(bArr, 0, bArr.length, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialFrom */
    public MessageType mo8416parsePartialFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
        return mo8417parsePartialFrom(bArr, i, i2, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialFrom */
    public MessageType mo8417parsePartialFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream newInstance = CodedInputStream.newInstance(bArr, i, i2);
            MessageType messagetype = (MessageType) mo10082parsePartialFrom(newInstance, extensionRegistryLite);
            try {
                newInstance.checkLastTagWas(0);
                return messagetype;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(messagetype);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    @Override // com.google.protobuf.Parser
    /* renamed from: parsePartialFrom */
    public MessageType mo8418parsePartialFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return mo8417parsePartialFrom(bArr, 0, bArr.length, extensionRegistryLite);
    }
}
