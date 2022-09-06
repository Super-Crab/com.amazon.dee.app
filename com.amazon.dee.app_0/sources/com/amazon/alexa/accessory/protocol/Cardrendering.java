package com.amazon.alexa.accessory.protocol;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes6.dex */
public final class Cardrendering {

    /* renamed from: com.amazon.alexa.accessory.protocol.Cardrendering$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class DisplayContent extends GeneratedMessageLite<DisplayContent, Builder> implements DisplayContentOrBuilder {
        private static final DisplayContent DEFAULT_INSTANCE = new DisplayContent();
        public static final int INDEX_FIELD_NUMBER = 6;
        private static volatile Parser<DisplayContent> PARSER = null;
        public static final int PAYLOAD_FIELD_NUMBER = 2;
        public static final int SEQUENCE_ID_FIELD_NUMBER = 3;
        public static final int SUBSEQUENCE_ID_FIELD_NUMBER = 4;
        public static final int TOTAL_CHUNKS_FIELD_NUMBER = 5;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int index_;
        private ByteString payload_ = ByteString.EMPTY;
        private int sequenceId_;
        private int subsequenceId_;
        private int totalChunks_;
        private int type_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<DisplayContent, Builder> implements DisplayContentOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearIndex() {
                copyOnWrite();
                ((DisplayContent) this.instance).clearIndex();
                return this;
            }

            public Builder clearPayload() {
                copyOnWrite();
                ((DisplayContent) this.instance).clearPayload();
                return this;
            }

            public Builder clearSequenceId() {
                copyOnWrite();
                ((DisplayContent) this.instance).clearSequenceId();
                return this;
            }

            public Builder clearSubsequenceId() {
                copyOnWrite();
                ((DisplayContent) this.instance).clearSubsequenceId();
                return this;
            }

            public Builder clearTotalChunks() {
                copyOnWrite();
                ((DisplayContent) this.instance).clearTotalChunks();
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((DisplayContent) this.instance).clearType();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
            public int getIndex() {
                return ((DisplayContent) this.instance).getIndex();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
            public ByteString getPayload() {
                return ((DisplayContent) this.instance).getPayload();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
            public int getSequenceId() {
                return ((DisplayContent) this.instance).getSequenceId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
            public int getSubsequenceId() {
                return ((DisplayContent) this.instance).getSubsequenceId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
            public int getTotalChunks() {
                return ((DisplayContent) this.instance).getTotalChunks();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
            public DisplayContentType getType() {
                return ((DisplayContent) this.instance).getType();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
            public int getTypeValue() {
                return ((DisplayContent) this.instance).getTypeValue();
            }

            public Builder setIndex(int i) {
                copyOnWrite();
                ((DisplayContent) this.instance).setIndex(i);
                return this;
            }

            public Builder setPayload(ByteString byteString) {
                copyOnWrite();
                ((DisplayContent) this.instance).setPayload(byteString);
                return this;
            }

            public Builder setSequenceId(int i) {
                copyOnWrite();
                ((DisplayContent) this.instance).setSequenceId(i);
                return this;
            }

            public Builder setSubsequenceId(int i) {
                copyOnWrite();
                ((DisplayContent) this.instance).setSubsequenceId(i);
                return this;
            }

            public Builder setTotalChunks(int i) {
                copyOnWrite();
                ((DisplayContent) this.instance).setTotalChunks(i);
                return this;
            }

            public Builder setType(DisplayContentType displayContentType) {
                copyOnWrite();
                ((DisplayContent) this.instance).setType(displayContentType);
                return this;
            }

            public Builder setTypeValue(int i) {
                copyOnWrite();
                ((DisplayContent) this.instance).setTypeValue(i);
                return this;
            }

            private Builder() {
                super(DisplayContent.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private DisplayContent() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIndex() {
            this.index_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPayload() {
            this.payload_ = getDefaultInstance().getPayload();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSequenceId() {
            this.sequenceId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSubsequenceId() {
            this.subsequenceId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTotalChunks() {
            this.totalChunks_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.type_ = 0;
        }

        public static DisplayContent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DisplayContent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DisplayContent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DisplayContent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DisplayContent> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIndex(int i) {
            this.index_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPayload(ByteString byteString) {
            if (byteString != null) {
                this.payload_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSequenceId(int i) {
            this.sequenceId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubsequenceId(int i) {
            this.subsequenceId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTotalChunks(int i) {
            this.totalChunks_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(DisplayContentType displayContentType) {
            if (displayContentType != null) {
                this.type_ = displayContentType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTypeValue(int i) {
            this.type_ = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    DisplayContent displayContent = (DisplayContent) obj2;
                    this.type_ = visitor.visitInt(this.type_ != 0, this.type_, displayContent.type_ != 0, displayContent.type_);
                    this.payload_ = visitor.visitByteString(this.payload_ != ByteString.EMPTY, this.payload_, displayContent.payload_ != ByteString.EMPTY, displayContent.payload_);
                    this.sequenceId_ = visitor.visitInt(this.sequenceId_ != 0, this.sequenceId_, displayContent.sequenceId_ != 0, displayContent.sequenceId_);
                    this.subsequenceId_ = visitor.visitInt(this.subsequenceId_ != 0, this.subsequenceId_, displayContent.subsequenceId_ != 0, displayContent.subsequenceId_);
                    this.totalChunks_ = visitor.visitInt(this.totalChunks_ != 0, this.totalChunks_, displayContent.totalChunks_ != 0, displayContent.totalChunks_);
                    boolean z2 = this.index_ != 0;
                    int i = this.index_;
                    if (displayContent.index_ != 0) {
                        z = true;
                    }
                    this.index_ = visitor.visitInt(z2, i, z, displayContent.index_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    if (((ExtensionRegistryLite) obj2) == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.type_ = codedInputStream.readEnum();
                                } else if (readTag == 18) {
                                    this.payload_ = codedInputStream.readBytes();
                                } else if (readTag == 24) {
                                    this.sequenceId_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.subsequenceId_ = codedInputStream.readUInt32();
                                } else if (readTag == 40) {
                                    this.totalChunks_ = codedInputStream.readUInt32();
                                } else if (readTag != 48) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.index_ = codedInputStream.readUInt32();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new DisplayContent();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (DisplayContent.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
        public int getIndex() {
            return this.index_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
        public ByteString getPayload() {
            return this.payload_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
        public int getSequenceId() {
            return this.sequenceId_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.type_ != DisplayContentType.TYPE_UNKNOWN.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
            }
            if (!this.payload_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(2, this.payload_);
            }
            int i3 = this.sequenceId_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i3);
            }
            int i4 = this.subsequenceId_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(4, i4);
            }
            int i5 = this.totalChunks_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(5, i5);
            }
            int i6 = this.index_;
            if (i6 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(6, i6);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
        public int getSubsequenceId() {
            return this.subsequenceId_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
        public int getTotalChunks() {
            return this.totalChunks_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
        public DisplayContentType getType() {
            DisplayContentType forNumber = DisplayContentType.forNumber(this.type_);
            return forNumber == null ? DisplayContentType.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentOrBuilder
        public int getTypeValue() {
            return this.type_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.type_ != DisplayContentType.TYPE_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(1, this.type_);
            }
            if (!this.payload_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.payload_);
            }
            int i = this.sequenceId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(3, i);
            }
            int i2 = this.subsequenceId_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(4, i2);
            }
            int i3 = this.totalChunks_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(5, i3);
            }
            int i4 = this.index_;
            if (i4 != 0) {
                codedOutputStream.writeUInt32(6, i4);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(DisplayContent displayContent) {
            return DEFAULT_INSTANCE.createBuilder(displayContent);
        }

        public static DisplayContent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisplayContent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DisplayContent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DisplayContent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DisplayContent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DisplayContent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DisplayContent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DisplayContent parseFrom(InputStream inputStream) throws IOException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DisplayContent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DisplayContent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DisplayContent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisplayContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface DisplayContentOrBuilder extends MessageLiteOrBuilder {
        int getIndex();

        ByteString getPayload();

        int getSequenceId();

        int getSubsequenceId();

        int getTotalChunks();

        DisplayContentType getType();

        int getTypeValue();
    }

    /* loaded from: classes6.dex */
    public enum DisplayContentType implements Internal.EnumLite {
        TYPE_UNKNOWN(0),
        TYPE_CARD(1),
        TYPE_CAPTION(2),
        TYPE_ASR(3),
        UNRECOGNIZED(-1);
        
        public static final int TYPE_ASR_VALUE = 3;
        public static final int TYPE_CAPTION_VALUE = 2;
        public static final int TYPE_CARD_VALUE = 1;
        public static final int TYPE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<DisplayContentType> internalValueMap = new Internal.EnumLiteMap<DisplayContentType>() { // from class: com.amazon.alexa.accessory.protocol.Cardrendering.DisplayContentType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public DisplayContentType mo9850findValueByNumber(int i) {
                return DisplayContentType.forNumber(i);
            }
        };
        private final int value;

        DisplayContentType(int i) {
            this.value = i;
        }

        public static DisplayContentType forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return TYPE_CARD;
                }
                if (i == 2) {
                    return TYPE_CAPTION;
                }
                if (i == 3) {
                    return TYPE_ASR;
                }
                return null;
            }
            return TYPE_UNKNOWN;
        }

        public static Internal.EnumLiteMap<DisplayContentType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static DisplayContentType valueOf(int i) {
            return forNumber(i);
        }
    }

    private Cardrendering() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
