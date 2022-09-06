package com.amazon.alexa.accessory.protocol;

import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public final class Fitness {

    /* renamed from: com.amazon.alexa.accessory.protocol.Fitness$1  reason: invalid class name */
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
    public static final class FitnessData extends GeneratedMessageLite<FitnessData, Builder> implements FitnessDataOrBuilder {
        public static final int CONTINUATION_TOKEN_FIELD_NUMBER = 4;
        private static final FitnessData DEFAULT_INSTANCE = new FitnessData();
        public static final int FORMAT_FIELD_NUMBER = 2;
        private static volatile Parser<FitnessData> PARSER = null;
        public static final int SHA_256_CHECKSUM_FIELD_NUMBER = 3;
        public static final int SIZE_FIELD_NUMBER = 1;
        private ByteString continuationToken_;
        private int format_;
        private ByteString sha256Checksum_;
        private int size_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FitnessData, Builder> implements FitnessDataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearContinuationToken() {
                copyOnWrite();
                ((FitnessData) this.instance).clearContinuationToken();
                return this;
            }

            public Builder clearFormat() {
                copyOnWrite();
                ((FitnessData) this.instance).clearFormat();
                return this;
            }

            public Builder clearSha256Checksum() {
                copyOnWrite();
                ((FitnessData) this.instance).clearSha256Checksum();
                return this;
            }

            public Builder clearSize() {
                copyOnWrite();
                ((FitnessData) this.instance).clearSize();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
            public ByteString getContinuationToken() {
                return ((FitnessData) this.instance).getContinuationToken();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
            public FitnessDataFormat getFormat() {
                return ((FitnessData) this.instance).getFormat();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
            public int getFormatValue() {
                return ((FitnessData) this.instance).getFormatValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
            public ByteString getSha256Checksum() {
                return ((FitnessData) this.instance).getSha256Checksum();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
            public int getSize() {
                return ((FitnessData) this.instance).getSize();
            }

            public Builder setContinuationToken(ByteString byteString) {
                copyOnWrite();
                ((FitnessData) this.instance).setContinuationToken(byteString);
                return this;
            }

            public Builder setFormat(FitnessDataFormat fitnessDataFormat) {
                copyOnWrite();
                ((FitnessData) this.instance).setFormat(fitnessDataFormat);
                return this;
            }

            public Builder setFormatValue(int i) {
                copyOnWrite();
                ((FitnessData) this.instance).setFormatValue(i);
                return this;
            }

            public Builder setSha256Checksum(ByteString byteString) {
                copyOnWrite();
                ((FitnessData) this.instance).setSha256Checksum(byteString);
                return this;
            }

            public Builder setSize(int i) {
                copyOnWrite();
                ((FitnessData) this.instance).setSize(i);
                return this;
            }

            private Builder() {
                super(FitnessData.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private FitnessData() {
            ByteString byteString = ByteString.EMPTY;
            this.sha256Checksum_ = byteString;
            this.continuationToken_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContinuationToken() {
            this.continuationToken_ = getDefaultInstance().getContinuationToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFormat() {
            this.format_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSha256Checksum() {
            this.sha256Checksum_ = getDefaultInstance().getSha256Checksum();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSize() {
            this.size_ = 0;
        }

        public static FitnessData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static FitnessData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FitnessData parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<FitnessData> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContinuationToken(ByteString byteString) {
            if (byteString != null) {
                this.continuationToken_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFormat(FitnessDataFormat fitnessDataFormat) {
            if (fitnessDataFormat != null) {
                this.format_ = fitnessDataFormat.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFormatValue(int i) {
            this.format_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSha256Checksum(ByteString byteString) {
            if (byteString != null) {
                this.sha256Checksum_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSize(int i) {
            this.size_ = i;
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
                    FitnessData fitnessData = (FitnessData) obj2;
                    this.size_ = visitor.visitInt(this.size_ != 0, this.size_, fitnessData.size_ != 0, fitnessData.size_);
                    this.format_ = visitor.visitInt(this.format_ != 0, this.format_, fitnessData.format_ != 0, fitnessData.format_);
                    this.sha256Checksum_ = visitor.visitByteString(this.sha256Checksum_ != ByteString.EMPTY, this.sha256Checksum_, fitnessData.sha256Checksum_ != ByteString.EMPTY, fitnessData.sha256Checksum_);
                    boolean z2 = this.continuationToken_ != ByteString.EMPTY;
                    ByteString byteString = this.continuationToken_;
                    if (fitnessData.continuationToken_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.continuationToken_ = visitor.visitByteString(z2, byteString, z, fitnessData.continuationToken_);
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
                                    this.size_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.format_ = codedInputStream.readEnum();
                                } else if (readTag == 26) {
                                    this.sha256Checksum_ = codedInputStream.readBytes();
                                } else if (readTag != 34) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.continuationToken_ = codedInputStream.readBytes();
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
                    return new FitnessData();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (FitnessData.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
        public ByteString getContinuationToken() {
            return this.continuationToken_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
        public FitnessDataFormat getFormat() {
            FitnessDataFormat forNumber = FitnessDataFormat.forNumber(this.format_);
            return forNumber == null ? FitnessDataFormat.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
        public int getFormatValue() {
            return this.format_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.size_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (this.format_ != FitnessDataFormat.FITNESS_DATA_FORMAT_UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.format_);
            }
            if (!this.sha256Checksum_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(3, this.sha256Checksum_);
            }
            if (!this.continuationToken_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(4, this.continuationToken_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
        public ByteString getSha256Checksum() {
            return this.sha256Checksum_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.FitnessDataOrBuilder
        public int getSize() {
            return this.size_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.size_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.format_ != FitnessDataFormat.FITNESS_DATA_FORMAT_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(2, this.format_);
            }
            if (!this.sha256Checksum_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.sha256Checksum_);
            }
            if (!this.continuationToken_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.continuationToken_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(FitnessData fitnessData) {
            return DEFAULT_INSTANCE.createBuilder(fitnessData);
        }

        public static FitnessData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FitnessData parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FitnessData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FitnessData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FitnessData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FitnessData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FitnessData parseFrom(InputStream inputStream) throws IOException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FitnessData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FitnessData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FitnessData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public enum FitnessDataFormat implements Internal.EnumLite {
        FITNESS_DATA_FORMAT_UNKNOWN(0),
        FITNESS_DATA_FORMAT_ABS(1),
        FITNESS_DATA_FORMAT_RCD(2),
        UNRECOGNIZED(-1);
        
        public static final int FITNESS_DATA_FORMAT_ABS_VALUE = 1;
        public static final int FITNESS_DATA_FORMAT_RCD_VALUE = 2;
        public static final int FITNESS_DATA_FORMAT_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<FitnessDataFormat> internalValueMap = new Internal.EnumLiteMap<FitnessDataFormat>() { // from class: com.amazon.alexa.accessory.protocol.Fitness.FitnessDataFormat.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public FitnessDataFormat mo9850findValueByNumber(int i) {
                return FitnessDataFormat.forNumber(i);
            }
        };
        private final int value;

        FitnessDataFormat(int i) {
            this.value = i;
        }

        public static FitnessDataFormat forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return FITNESS_DATA_FORMAT_ABS;
                }
                if (i == 2) {
                    return FITNESS_DATA_FORMAT_RCD;
                }
                return null;
            }
            return FITNESS_DATA_FORMAT_UNKNOWN;
        }

        public static Internal.EnumLiteMap<FitnessDataFormat> internalGetValueMap() {
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
        public static FitnessDataFormat valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public interface FitnessDataOrBuilder extends MessageLiteOrBuilder {
        ByteString getContinuationToken();

        FitnessDataFormat getFormat();

        int getFormatValue();

        ByteString getSha256Checksum();

        int getSize();
    }

    /* loaded from: classes6.dex */
    public static final class GetFitnessData extends GeneratedMessageLite<GetFitnessData, Builder> implements GetFitnessDataOrBuilder {
        public static final int CONTINUATION_TOKEN_FIELD_NUMBER = 1;
        private static final GetFitnessData DEFAULT_INSTANCE = new GetFitnessData();
        public static final int NEXT_SYNC_INTERVAL_MILLISECONDS_FIELD_NUMBER = 2;
        private static volatile Parser<GetFitnessData> PARSER;
        private ByteString continuationToken_ = ByteString.EMPTY;
        private int nextSyncIntervalMilliseconds_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetFitnessData, Builder> implements GetFitnessDataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearContinuationToken() {
                copyOnWrite();
                ((GetFitnessData) this.instance).clearContinuationToken();
                return this;
            }

            public Builder clearNextSyncIntervalMilliseconds() {
                copyOnWrite();
                ((GetFitnessData) this.instance).clearNextSyncIntervalMilliseconds();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.GetFitnessDataOrBuilder
            public ByteString getContinuationToken() {
                return ((GetFitnessData) this.instance).getContinuationToken();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.GetFitnessDataOrBuilder
            public int getNextSyncIntervalMilliseconds() {
                return ((GetFitnessData) this.instance).getNextSyncIntervalMilliseconds();
            }

            public Builder setContinuationToken(ByteString byteString) {
                copyOnWrite();
                ((GetFitnessData) this.instance).setContinuationToken(byteString);
                return this;
            }

            public Builder setNextSyncIntervalMilliseconds(int i) {
                copyOnWrite();
                ((GetFitnessData) this.instance).setNextSyncIntervalMilliseconds(i);
                return this;
            }

            private Builder() {
                super(GetFitnessData.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetFitnessData() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContinuationToken() {
            this.continuationToken_ = getDefaultInstance().getContinuationToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNextSyncIntervalMilliseconds() {
            this.nextSyncIntervalMilliseconds_ = 0;
        }

        public static GetFitnessData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetFitnessData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetFitnessData parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetFitnessData> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContinuationToken(ByteString byteString) {
            if (byteString != null) {
                this.continuationToken_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNextSyncIntervalMilliseconds(int i) {
            this.nextSyncIntervalMilliseconds_ = i;
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
                    GetFitnessData getFitnessData = (GetFitnessData) obj2;
                    this.continuationToken_ = visitor.visitByteString(this.continuationToken_ != ByteString.EMPTY, this.continuationToken_, getFitnessData.continuationToken_ != ByteString.EMPTY, getFitnessData.continuationToken_);
                    boolean z2 = this.nextSyncIntervalMilliseconds_ != 0;
                    int i = this.nextSyncIntervalMilliseconds_;
                    if (getFitnessData.nextSyncIntervalMilliseconds_ != 0) {
                        z = true;
                    }
                    this.nextSyncIntervalMilliseconds_ = visitor.visitInt(z2, i, z, getFitnessData.nextSyncIntervalMilliseconds_);
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
                                if (readTag == 10) {
                                    this.continuationToken_ = codedInputStream.readBytes();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.nextSyncIntervalMilliseconds_ = codedInputStream.readUInt32();
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
                    return new GetFitnessData();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetFitnessData.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Fitness.GetFitnessDataOrBuilder
        public ByteString getContinuationToken() {
            return this.continuationToken_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.GetFitnessDataOrBuilder
        public int getNextSyncIntervalMilliseconds() {
            return this.nextSyncIntervalMilliseconds_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.continuationToken_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.continuationToken_);
            }
            int i3 = this.nextSyncIntervalMilliseconds_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.continuationToken_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.continuationToken_);
            }
            int i = this.nextSyncIntervalMilliseconds_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetFitnessData getFitnessData) {
            return DEFAULT_INSTANCE.createBuilder(getFitnessData);
        }

        public static GetFitnessData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetFitnessData parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetFitnessData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetFitnessData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetFitnessData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetFitnessData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetFitnessData parseFrom(InputStream inputStream) throws IOException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetFitnessData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetFitnessData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetFitnessData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetFitnessDataOrBuilder extends MessageLiteOrBuilder {
        ByteString getContinuationToken();

        int getNextSyncIntervalMilliseconds();
    }

    /* loaded from: classes6.dex */
    public static final class LiveFitnessData extends GeneratedMessageLite<LiveFitnessData, Builder> implements LiveFitnessDataOrBuilder {
        public static final int DATA_FIELD_NUMBER = 1;
        private static final LiveFitnessData DEFAULT_INSTANCE = new LiveFitnessData();
        private static volatile Parser<LiveFitnessData> PARSER;
        private ByteString data_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<LiveFitnessData, Builder> implements LiveFitnessDataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearData() {
                copyOnWrite();
                ((LiveFitnessData) this.instance).clearData();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.LiveFitnessDataOrBuilder
            public ByteString getData() {
                return ((LiveFitnessData) this.instance).getData();
            }

            public Builder setData(ByteString byteString) {
                copyOnWrite();
                ((LiveFitnessData) this.instance).setData(byteString);
                return this;
            }

            private Builder() {
                super(LiveFitnessData.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private LiveFitnessData() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearData() {
            this.data_ = getDefaultInstance().getData();
        }

        public static LiveFitnessData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static LiveFitnessData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LiveFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LiveFitnessData parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<LiveFitnessData> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setData(ByteString byteString) {
            if (byteString != null) {
                this.data_ = byteString;
                return;
            }
            throw new NullPointerException();
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
                    LiveFitnessData liveFitnessData = (LiveFitnessData) obj2;
                    boolean z2 = this.data_ != ByteString.EMPTY;
                    ByteString byteString = this.data_;
                    if (liveFitnessData.data_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.data_ = visitor.visitByteString(z2, byteString, z, liveFitnessData.data_);
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
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 10) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.data_ = codedInputStream.readBytes();
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this));
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new LiveFitnessData();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (LiveFitnessData.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Fitness.LiveFitnessDataOrBuilder
        public ByteString getData() {
            return this.data_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.data_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.data_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.data_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.data_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(LiveFitnessData liveFitnessData) {
            return DEFAULT_INSTANCE.createBuilder(liveFitnessData);
        }

        public static LiveFitnessData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LiveFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LiveFitnessData parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static LiveFitnessData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static LiveFitnessData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static LiveFitnessData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static LiveFitnessData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static LiveFitnessData parseFrom(InputStream inputStream) throws IOException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LiveFitnessData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static LiveFitnessData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static LiveFitnessData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface LiveFitnessDataOrBuilder extends MessageLiteOrBuilder {
        ByteString getData();
    }

    /* loaded from: classes6.dex */
    public static final class NotifyFitnessDataAvailable extends GeneratedMessageLite<NotifyFitnessDataAvailable, Builder> implements NotifyFitnessDataAvailableOrBuilder {
        private static final NotifyFitnessDataAvailable DEFAULT_INSTANCE = new NotifyFitnessDataAvailable();
        private static volatile Parser<NotifyFitnessDataAvailable> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotifyFitnessDataAvailable, Builder> implements NotifyFitnessDataAvailableOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(NotifyFitnessDataAvailable.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotifyFitnessDataAvailable() {
        }

        public static NotifyFitnessDataAvailable getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotifyFitnessDataAvailable parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyFitnessDataAvailable parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotifyFitnessDataAvailable> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    NotifyFitnessDataAvailable notifyFitnessDataAvailable = (NotifyFitnessDataAvailable) obj2;
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
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag == 0 || !parseUnknownField(readTag, codedInputStream)) {
                                z = true;
                            }
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
                    return new NotifyFitnessDataAvailable();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotifyFitnessDataAvailable.class) {
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

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotifyFitnessDataAvailable notifyFitnessDataAvailable) {
            return DEFAULT_INSTANCE.createBuilder(notifyFitnessDataAvailable);
        }

        public static NotifyFitnessDataAvailable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyFitnessDataAvailable parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotifyFitnessDataAvailable parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static NotifyFitnessDataAvailable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NotifyFitnessDataAvailable parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyFitnessDataAvailable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotifyFitnessDataAvailable parseFrom(InputStream inputStream) throws IOException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyFitnessDataAvailable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyFitnessDataAvailable parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotifyFitnessDataAvailable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyFitnessDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotifyFitnessDataAvailableOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class StartLiveFitnessData extends GeneratedMessageLite<StartLiveFitnessData, Builder> implements StartLiveFitnessDataOrBuilder {
        public static final int CATEGORY_FIELD_NUMBER = 1;
        private static final StartLiveFitnessData DEFAULT_INSTANCE = new StartLiveFitnessData();
        private static volatile Parser<StartLiveFitnessData> PARSER;
        private int categoryMemoizedSerializedSize = -1;
        private Internal.IntList category_ = GeneratedMessageLite.emptyIntList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<StartLiveFitnessData, Builder> implements StartLiveFitnessDataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllCategory(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((StartLiveFitnessData) this.instance).addAllCategory(iterable);
                return this;
            }

            public Builder addCategory(int i) {
                copyOnWrite();
                ((StartLiveFitnessData) this.instance).addCategory(i);
                return this;
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((StartLiveFitnessData) this.instance).clearCategory();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.StartLiveFitnessDataOrBuilder
            public int getCategory(int i) {
                return ((StartLiveFitnessData) this.instance).getCategory(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.StartLiveFitnessDataOrBuilder
            public int getCategoryCount() {
                return ((StartLiveFitnessData) this.instance).getCategoryCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.StartLiveFitnessDataOrBuilder
            public List<Integer> getCategoryList() {
                return Collections.unmodifiableList(((StartLiveFitnessData) this.instance).getCategoryList());
            }

            public Builder setCategory(int i, int i2) {
                copyOnWrite();
                ((StartLiveFitnessData) this.instance).setCategory(i, i2);
                return this;
            }

            private Builder() {
                super(StartLiveFitnessData.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private StartLiveFitnessData() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllCategory(Iterable<? extends Integer> iterable) {
            ensureCategoryIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.category_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCategory(int i) {
            ensureCategoryIsMutable();
            this.category_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = GeneratedMessageLite.emptyIntList();
        }

        private void ensureCategoryIsMutable() {
            if (!this.category_.isModifiable()) {
                this.category_ = GeneratedMessageLite.mutableCopy(this.category_);
            }
        }

        public static StartLiveFitnessData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static StartLiveFitnessData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StartLiveFitnessData parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<StartLiveFitnessData> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategory(int i, int i2) {
            ensureCategoryIsMutable();
            this.category_.setInt(i, i2);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    this.category_ = ((GeneratedMessageLite.Visitor) obj).visitIntList(this.category_, ((StartLiveFitnessData) obj2).category_);
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
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    if (!this.category_.isModifiable()) {
                                        this.category_ = GeneratedMessageLite.mutableCopy(this.category_);
                                    }
                                    this.category_.addInt(codedInputStream.readUInt32());
                                } else if (readTag != 10) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if (!this.category_.isModifiable() && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.category_ = GeneratedMessageLite.mutableCopy(this.category_);
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.category_.addInt(codedInputStream.readUInt32());
                                    }
                                    codedInputStream.popLimit(pushLimit);
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
                    this.category_.makeImmutable();
                    return null;
                case 6:
                    return new StartLiveFitnessData();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (StartLiveFitnessData.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Fitness.StartLiveFitnessDataOrBuilder
        public int getCategory(int i) {
            return this.category_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.StartLiveFitnessDataOrBuilder
        public int getCategoryCount() {
            return this.category_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.StartLiveFitnessDataOrBuilder
        public List<Integer> getCategoryList() {
            return this.category_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.category_.size(); i3++) {
                i2 += CodedOutputStream.computeUInt32SizeNoTag(this.category_.getInt(i3));
            }
            int i4 = 0 + i2;
            if (!getCategoryList().isEmpty()) {
                i4 = i4 + 1 + CodedOutputStream.computeInt32SizeNoTag(i2);
            }
            this.categoryMemoizedSerializedSize = i2;
            int serializedSize = this.unknownFields.getSerializedSize() + i4;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if (getCategoryList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(10);
                codedOutputStream.writeUInt32NoTag(this.categoryMemoizedSerializedSize);
            }
            for (int i = 0; i < this.category_.size(); i++) {
                codedOutputStream.writeUInt32NoTag(this.category_.getInt(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(StartLiveFitnessData startLiveFitnessData) {
            return DEFAULT_INSTANCE.createBuilder(startLiveFitnessData);
        }

        public static StartLiveFitnessData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StartLiveFitnessData parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StartLiveFitnessData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StartLiveFitnessData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StartLiveFitnessData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StartLiveFitnessData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StartLiveFitnessData parseFrom(InputStream inputStream) throws IOException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StartLiveFitnessData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StartLiveFitnessData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StartLiveFitnessData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StartLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface StartLiveFitnessDataOrBuilder extends MessageLiteOrBuilder {
        int getCategory(int i);

        int getCategoryCount();

        List<Integer> getCategoryList();
    }

    /* loaded from: classes6.dex */
    public static final class StopFitnessData extends GeneratedMessageLite<StopFitnessData, Builder> implements StopFitnessDataOrBuilder {
        private static final StopFitnessData DEFAULT_INSTANCE = new StopFitnessData();
        private static volatile Parser<StopFitnessData> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<StopFitnessData, Builder> implements StopFitnessDataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(StopFitnessData.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private StopFitnessData() {
        }

        public static StopFitnessData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static StopFitnessData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StopFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StopFitnessData parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<StopFitnessData> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    StopFitnessData stopFitnessData = (StopFitnessData) obj2;
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
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag == 0 || !parseUnknownField(readTag, codedInputStream)) {
                                z = true;
                            }
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
                    return new StopFitnessData();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (StopFitnessData.class) {
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

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(StopFitnessData stopFitnessData) {
            return DEFAULT_INSTANCE.createBuilder(stopFitnessData);
        }

        public static StopFitnessData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StopFitnessData parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StopFitnessData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StopFitnessData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StopFitnessData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StopFitnessData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StopFitnessData parseFrom(InputStream inputStream) throws IOException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StopFitnessData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StopFitnessData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StopFitnessData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface StopFitnessDataOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class StopLiveFitnessData extends GeneratedMessageLite<StopLiveFitnessData, Builder> implements StopLiveFitnessDataOrBuilder {
        public static final int CATEGORY_FIELD_NUMBER = 1;
        private static final StopLiveFitnessData DEFAULT_INSTANCE = new StopLiveFitnessData();
        private static volatile Parser<StopLiveFitnessData> PARSER;
        private int categoryMemoizedSerializedSize = -1;
        private Internal.IntList category_ = GeneratedMessageLite.emptyIntList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<StopLiveFitnessData, Builder> implements StopLiveFitnessDataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllCategory(Iterable<? extends Integer> iterable) {
                copyOnWrite();
                ((StopLiveFitnessData) this.instance).addAllCategory(iterable);
                return this;
            }

            public Builder addCategory(int i) {
                copyOnWrite();
                ((StopLiveFitnessData) this.instance).addCategory(i);
                return this;
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((StopLiveFitnessData) this.instance).clearCategory();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.StopLiveFitnessDataOrBuilder
            public int getCategory(int i) {
                return ((StopLiveFitnessData) this.instance).getCategory(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.StopLiveFitnessDataOrBuilder
            public int getCategoryCount() {
                return ((StopLiveFitnessData) this.instance).getCategoryCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.StopLiveFitnessDataOrBuilder
            public List<Integer> getCategoryList() {
                return Collections.unmodifiableList(((StopLiveFitnessData) this.instance).getCategoryList());
            }

            public Builder setCategory(int i, int i2) {
                copyOnWrite();
                ((StopLiveFitnessData) this.instance).setCategory(i, i2);
                return this;
            }

            private Builder() {
                super(StopLiveFitnessData.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private StopLiveFitnessData() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllCategory(Iterable<? extends Integer> iterable) {
            ensureCategoryIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.category_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCategory(int i) {
            ensureCategoryIsMutable();
            this.category_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = GeneratedMessageLite.emptyIntList();
        }

        private void ensureCategoryIsMutable() {
            if (!this.category_.isModifiable()) {
                this.category_ = GeneratedMessageLite.mutableCopy(this.category_);
            }
        }

        public static StopLiveFitnessData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static StopLiveFitnessData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StopLiveFitnessData parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<StopLiveFitnessData> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategory(int i, int i2) {
            ensureCategoryIsMutable();
            this.category_.setInt(i, i2);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    this.category_ = ((GeneratedMessageLite.Visitor) obj).visitIntList(this.category_, ((StopLiveFitnessData) obj2).category_);
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
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    if (!this.category_.isModifiable()) {
                                        this.category_ = GeneratedMessageLite.mutableCopy(this.category_);
                                    }
                                    this.category_.addInt(codedInputStream.readUInt32());
                                } else if (readTag != 10) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if (!this.category_.isModifiable() && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.category_ = GeneratedMessageLite.mutableCopy(this.category_);
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.category_.addInt(codedInputStream.readUInt32());
                                    }
                                    codedInputStream.popLimit(pushLimit);
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
                    this.category_.makeImmutable();
                    return null;
                case 6:
                    return new StopLiveFitnessData();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (StopLiveFitnessData.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Fitness.StopLiveFitnessDataOrBuilder
        public int getCategory(int i) {
            return this.category_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.StopLiveFitnessDataOrBuilder
        public int getCategoryCount() {
            return this.category_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.StopLiveFitnessDataOrBuilder
        public List<Integer> getCategoryList() {
            return this.category_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.category_.size(); i3++) {
                i2 += CodedOutputStream.computeUInt32SizeNoTag(this.category_.getInt(i3));
            }
            int i4 = 0 + i2;
            if (!getCategoryList().isEmpty()) {
                i4 = i4 + 1 + CodedOutputStream.computeInt32SizeNoTag(i2);
            }
            this.categoryMemoizedSerializedSize = i2;
            int serializedSize = this.unknownFields.getSerializedSize() + i4;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if (getCategoryList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(10);
                codedOutputStream.writeUInt32NoTag(this.categoryMemoizedSerializedSize);
            }
            for (int i = 0; i < this.category_.size(); i++) {
                codedOutputStream.writeUInt32NoTag(this.category_.getInt(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(StopLiveFitnessData stopLiveFitnessData) {
            return DEFAULT_INSTANCE.createBuilder(stopLiveFitnessData);
        }

        public static StopLiveFitnessData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StopLiveFitnessData parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StopLiveFitnessData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StopLiveFitnessData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StopLiveFitnessData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StopLiveFitnessData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StopLiveFitnessData parseFrom(InputStream inputStream) throws IOException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StopLiveFitnessData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StopLiveFitnessData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StopLiveFitnessData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopLiveFitnessData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface StopLiveFitnessDataOrBuilder extends MessageLiteOrBuilder {
        int getCategory(int i);

        int getCategoryCount();

        List<Integer> getCategoryList();
    }

    /* loaded from: classes6.dex */
    public static final class SyncFitnessSession extends GeneratedMessageLite<SyncFitnessSession, Builder> implements SyncFitnessSessionOrBuilder {
        private static final SyncFitnessSession DEFAULT_INSTANCE = new SyncFitnessSession();
        private static volatile Parser<SyncFitnessSession> PARSER = null;
        public static final int SEQUENCE_ID_FIELD_NUMBER = 2;
        public static final int SESSION_ID_FIELD_NUMBER = 1;
        public static final int WORKOUT_STATE_FIELD_NUMBER = 3;
        private int sequenceId_;
        private ByteString sessionId_ = ByteString.EMPTY;
        private int workoutState_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SyncFitnessSession, Builder> implements SyncFitnessSessionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearSequenceId() {
                copyOnWrite();
                ((SyncFitnessSession) this.instance).clearSequenceId();
                return this;
            }

            public Builder clearSessionId() {
                copyOnWrite();
                ((SyncFitnessSession) this.instance).clearSessionId();
                return this;
            }

            public Builder clearWorkoutState() {
                copyOnWrite();
                ((SyncFitnessSession) this.instance).clearWorkoutState();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.SyncFitnessSessionOrBuilder
            public int getSequenceId() {
                return ((SyncFitnessSession) this.instance).getSequenceId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.SyncFitnessSessionOrBuilder
            public ByteString getSessionId() {
                return ((SyncFitnessSession) this.instance).getSessionId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.SyncFitnessSessionOrBuilder
            public WorkoutState getWorkoutState() {
                return ((SyncFitnessSession) this.instance).getWorkoutState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Fitness.SyncFitnessSessionOrBuilder
            public int getWorkoutStateValue() {
                return ((SyncFitnessSession) this.instance).getWorkoutStateValue();
            }

            public Builder setSequenceId(int i) {
                copyOnWrite();
                ((SyncFitnessSession) this.instance).setSequenceId(i);
                return this;
            }

            public Builder setSessionId(ByteString byteString) {
                copyOnWrite();
                ((SyncFitnessSession) this.instance).setSessionId(byteString);
                return this;
            }

            public Builder setWorkoutState(WorkoutState workoutState) {
                copyOnWrite();
                ((SyncFitnessSession) this.instance).setWorkoutState(workoutState);
                return this;
            }

            public Builder setWorkoutStateValue(int i) {
                copyOnWrite();
                ((SyncFitnessSession) this.instance).setWorkoutStateValue(i);
                return this;
            }

            private Builder() {
                super(SyncFitnessSession.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SyncFitnessSession() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSequenceId() {
            this.sequenceId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSessionId() {
            this.sessionId_ = getDefaultInstance().getSessionId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWorkoutState() {
            this.workoutState_ = 0;
        }

        public static SyncFitnessSession getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SyncFitnessSession parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SyncFitnessSession) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SyncFitnessSession parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SyncFitnessSession> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSequenceId(int i) {
            this.sequenceId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSessionId(ByteString byteString) {
            if (byteString != null) {
                this.sessionId_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWorkoutState(WorkoutState workoutState) {
            if (workoutState != null) {
                this.workoutState_ = workoutState.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWorkoutStateValue(int i) {
            this.workoutState_ = i;
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
                    SyncFitnessSession syncFitnessSession = (SyncFitnessSession) obj2;
                    this.sessionId_ = visitor.visitByteString(this.sessionId_ != ByteString.EMPTY, this.sessionId_, syncFitnessSession.sessionId_ != ByteString.EMPTY, syncFitnessSession.sessionId_);
                    this.sequenceId_ = visitor.visitInt(this.sequenceId_ != 0, this.sequenceId_, syncFitnessSession.sequenceId_ != 0, syncFitnessSession.sequenceId_);
                    boolean z2 = this.workoutState_ != 0;
                    int i = this.workoutState_;
                    if (syncFitnessSession.workoutState_ != 0) {
                        z = true;
                    }
                    this.workoutState_ = visitor.visitInt(z2, i, z, syncFitnessSession.workoutState_);
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
                                if (readTag == 10) {
                                    this.sessionId_ = codedInputStream.readBytes();
                                } else if (readTag == 16) {
                                    this.sequenceId_ = codedInputStream.readUInt32();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.workoutState_ = codedInputStream.readEnum();
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
                    return new SyncFitnessSession();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SyncFitnessSession.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Fitness.SyncFitnessSessionOrBuilder
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
            if (!this.sessionId_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.sessionId_);
            }
            int i3 = this.sequenceId_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            if (this.workoutState_ != WorkoutState.WORKOUT_STATE_UNKNOWN.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.workoutState_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.SyncFitnessSessionOrBuilder
        public ByteString getSessionId() {
            return this.sessionId_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.SyncFitnessSessionOrBuilder
        public WorkoutState getWorkoutState() {
            WorkoutState forNumber = WorkoutState.forNumber(this.workoutState_);
            return forNumber == null ? WorkoutState.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Fitness.SyncFitnessSessionOrBuilder
        public int getWorkoutStateValue() {
            return this.workoutState_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.sessionId_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.sessionId_);
            }
            int i = this.sequenceId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            if (this.workoutState_ != WorkoutState.WORKOUT_STATE_UNKNOWN.getNumber()) {
                codedOutputStream.writeEnum(3, this.workoutState_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SyncFitnessSession syncFitnessSession) {
            return DEFAULT_INSTANCE.createBuilder(syncFitnessSession);
        }

        public static SyncFitnessSession parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SyncFitnessSession) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SyncFitnessSession parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SyncFitnessSession parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SyncFitnessSession parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SyncFitnessSession parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SyncFitnessSession parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SyncFitnessSession parseFrom(InputStream inputStream) throws IOException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SyncFitnessSession parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SyncFitnessSession parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SyncFitnessSession parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SyncFitnessSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SyncFitnessSessionOrBuilder extends MessageLiteOrBuilder {
        int getSequenceId();

        ByteString getSessionId();

        WorkoutState getWorkoutState();

        int getWorkoutStateValue();
    }

    /* loaded from: classes6.dex */
    public enum WorkoutState implements Internal.EnumLite {
        WORKOUT_STATE_UNKNOWN(0),
        WORKOUT_STATE_IDLE(1),
        WORKOUT_STATE_ACTIVE(2),
        WORKOUT_STATE_PAUSED(3),
        UNRECOGNIZED(-1);
        
        public static final int WORKOUT_STATE_ACTIVE_VALUE = 2;
        public static final int WORKOUT_STATE_IDLE_VALUE = 1;
        public static final int WORKOUT_STATE_PAUSED_VALUE = 3;
        public static final int WORKOUT_STATE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<WorkoutState> internalValueMap = new Internal.EnumLiteMap<WorkoutState>() { // from class: com.amazon.alexa.accessory.protocol.Fitness.WorkoutState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public WorkoutState mo9850findValueByNumber(int i) {
                return WorkoutState.forNumber(i);
            }
        };
        private final int value;

        WorkoutState(int i) {
            this.value = i;
        }

        public static WorkoutState forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return WORKOUT_STATE_IDLE;
                }
                if (i == 2) {
                    return WORKOUT_STATE_ACTIVE;
                }
                if (i == 3) {
                    return WORKOUT_STATE_PAUSED;
                }
                return null;
            }
            return WORKOUT_STATE_UNKNOWN;
        }

        public static Internal.EnumLiteMap<WorkoutState> internalGetValueMap() {
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
        public static WorkoutState valueOf(int i) {
            return forNumber(i);
        }
    }

    private Fitness() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
