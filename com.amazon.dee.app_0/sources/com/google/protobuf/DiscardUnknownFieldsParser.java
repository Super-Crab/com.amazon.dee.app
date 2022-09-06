package com.google.protobuf;
/* loaded from: classes3.dex */
public final class DiscardUnknownFieldsParser {
    private DiscardUnknownFieldsParser() {
    }

    public static final <T extends Message> Parser<T> wrap(final Parser<T> parser) {
        return new AbstractParser<T>() { // from class: com.google.protobuf.DiscardUnknownFieldsParser.1
            /* JADX WARN: Incorrect return type in method signature: (Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/ExtensionRegistryLite;)TT; */
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public Message mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                try {
                    codedInputStream.discardUnknownFields();
                    return (Message) Parser.this.mo10082parsePartialFrom(codedInputStream, extensionRegistryLite);
                } finally {
                    codedInputStream.unsetDiscardUnknownFields();
                }
            }
        };
    }
}
