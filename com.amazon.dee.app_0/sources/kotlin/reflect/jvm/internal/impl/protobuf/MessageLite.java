package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
/* loaded from: classes4.dex */
public interface MessageLite extends MessageLiteOrBuilder {

    /* loaded from: classes4.dex */
    public interface Builder extends Cloneable, MessageLiteOrBuilder {
        /* renamed from: build */
        MessageLite mo11978build();

        /* renamed from: mergeFrom */
        Builder mo11984mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;
    }

    Parser<? extends MessageLite> getParserForType();

    int getSerializedSize();

    /* renamed from: newBuilderForType */
    Builder mo11975newBuilderForType();

    /* renamed from: toBuilder */
    Builder mo11976toBuilder();

    void writeTo(CodedOutputStream codedOutputStream) throws IOException;
}
