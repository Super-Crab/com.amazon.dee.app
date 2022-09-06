package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes3.dex */
public interface Message extends MessageLite, MessageOrBuilder {

    /* loaded from: classes3.dex */
    public interface Builder extends MessageLite.Builder, MessageOrBuilder {
        /* renamed from: addRepeatedField */
        Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj);

        @Override // 
        /* renamed from: build */
        Message mo10084build();

        @Override // 
        /* renamed from: buildPartial */
        Message mo10085buildPartial();

        @Override // 
        /* renamed from: clear */
        Builder mo10087clear();

        /* renamed from: clearField */
        Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor);

        /* renamed from: clearOneof */
        Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor);

        @Override // 
        /* renamed from: clone */
        Builder mo10093clone();

        Descriptors.Descriptor getDescriptorForType();

        Builder getFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor);

        Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor, int i);

        @Override // 
        boolean mergeDelimitedFrom(InputStream inputStream) throws IOException;

        @Override // 
        boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo9962mergeFrom(ByteString byteString) throws InvalidProtocolBufferException;

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo9963mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo9964mergeFrom(CodedInputStream codedInputStream) throws IOException;

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo10097mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        /* renamed from: mergeFrom */
        Builder mo10096mergeFrom(Message message);

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo9967mergeFrom(InputStream inputStream) throws IOException;

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo9968mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo9969mergeFrom(byte[] bArr) throws InvalidProtocolBufferException;

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo9970mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo9971mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        @Override // 
        /* renamed from: mergeFrom */
        Builder mo9972mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        /* renamed from: mergeUnknownFields */
        Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet);

        /* renamed from: newBuilderForField */
        Builder mo9371newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor);

        /* renamed from: setField */
        Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj);

        /* renamed from: setRepeatedField */
        Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj);

        /* renamed from: setUnknownFields */
        Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet);
    }

    boolean equals(Object obj);

    @Override // 
    /* renamed from: getParserForType */
    Parser<? extends Message> mo9954getParserForType();

    int hashCode();

    @Override // 
    /* renamed from: newBuilderForType */
    Builder mo10079newBuilderForType();

    @Override // 
    /* renamed from: toBuilder */
    Builder mo10081toBuilder();

    String toString();
}
