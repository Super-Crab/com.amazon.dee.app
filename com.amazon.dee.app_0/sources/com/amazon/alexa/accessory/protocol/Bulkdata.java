package com.amazon.alexa.accessory.protocol;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes6.dex */
public final class Bulkdata {

    /* renamed from: com.amazon.alexa.accessory.protocol.Bulkdata$1  reason: invalid class name */
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
    public static final class BulkDataManifestEntry extends GeneratedMessageLite<BulkDataManifestEntry, Builder> implements BulkDataManifestEntryOrBuilder {
        public static final int CATEGORY_FIELD_NUMBER = 2;
        public static final int CATEGORY_SPECIFIC_DATA_FIELD_NUMBER = 5;
        private static final BulkDataManifestEntry DEFAULT_INSTANCE = new BulkDataManifestEntry();
        public static final int IDENTIFIER_FIELD_NUMBER = 1;
        public static final int MANIFEST_COUNT_FIELD_NUMBER = 4;
        public static final int MANIFEST_INDEX_FIELD_NUMBER = 3;
        private static volatile Parser<BulkDataManifestEntry> PARSER;
        private ByteString categorySpecificData_;
        private int category_;
        private ByteString identifier_;
        private int manifestCount_;
        private int manifestIndex_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<BulkDataManifestEntry, Builder> implements BulkDataManifestEntryOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).clearCategory();
                return this;
            }

            public Builder clearCategorySpecificData() {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).clearCategorySpecificData();
                return this;
            }

            public Builder clearIdentifier() {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).clearIdentifier();
                return this;
            }

            public Builder clearManifestCount() {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).clearManifestCount();
                return this;
            }

            public Builder clearManifestIndex() {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).clearManifestIndex();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
            public int getCategory() {
                return ((BulkDataManifestEntry) this.instance).getCategory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
            public ByteString getCategorySpecificData() {
                return ((BulkDataManifestEntry) this.instance).getCategorySpecificData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
            public ByteString getIdentifier() {
                return ((BulkDataManifestEntry) this.instance).getIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
            public int getManifestCount() {
                return ((BulkDataManifestEntry) this.instance).getManifestCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
            public int getManifestIndex() {
                return ((BulkDataManifestEntry) this.instance).getManifestIndex();
            }

            public Builder setCategory(int i) {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).setCategory(i);
                return this;
            }

            public Builder setCategorySpecificData(ByteString byteString) {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).setCategorySpecificData(byteString);
                return this;
            }

            public Builder setIdentifier(ByteString byteString) {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).setIdentifier(byteString);
                return this;
            }

            public Builder setManifestCount(int i) {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).setManifestCount(i);
                return this;
            }

            public Builder setManifestIndex(int i) {
                copyOnWrite();
                ((BulkDataManifestEntry) this.instance).setManifestIndex(i);
                return this;
            }

            private Builder() {
                super(BulkDataManifestEntry.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private BulkDataManifestEntry() {
            ByteString byteString = ByteString.EMPTY;
            this.identifier_ = byteString;
            this.categorySpecificData_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategorySpecificData() {
            this.categorySpecificData_ = getDefaultInstance().getCategorySpecificData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIdentifier() {
            this.identifier_ = getDefaultInstance().getIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearManifestCount() {
            this.manifestCount_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearManifestIndex() {
            this.manifestIndex_ = 0;
        }

        public static BulkDataManifestEntry getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static BulkDataManifestEntry parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BulkDataManifestEntry parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<BulkDataManifestEntry> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategory(int i) {
            this.category_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategorySpecificData(ByteString byteString) {
            if (byteString != null) {
                this.categorySpecificData_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdentifier(ByteString byteString) {
            if (byteString != null) {
                this.identifier_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManifestCount(int i) {
            this.manifestCount_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManifestIndex(int i) {
            this.manifestIndex_ = i;
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
                    BulkDataManifestEntry bulkDataManifestEntry = (BulkDataManifestEntry) obj2;
                    this.identifier_ = visitor.visitByteString(this.identifier_ != ByteString.EMPTY, this.identifier_, bulkDataManifestEntry.identifier_ != ByteString.EMPTY, bulkDataManifestEntry.identifier_);
                    this.category_ = visitor.visitInt(this.category_ != 0, this.category_, bulkDataManifestEntry.category_ != 0, bulkDataManifestEntry.category_);
                    this.manifestIndex_ = visitor.visitInt(this.manifestIndex_ != 0, this.manifestIndex_, bulkDataManifestEntry.manifestIndex_ != 0, bulkDataManifestEntry.manifestIndex_);
                    this.manifestCount_ = visitor.visitInt(this.manifestCount_ != 0, this.manifestCount_, bulkDataManifestEntry.manifestCount_ != 0, bulkDataManifestEntry.manifestCount_);
                    boolean z2 = this.categorySpecificData_ != ByteString.EMPTY;
                    ByteString byteString = this.categorySpecificData_;
                    if (bulkDataManifestEntry.categorySpecificData_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.categorySpecificData_ = visitor.visitByteString(z2, byteString, z, bulkDataManifestEntry.categorySpecificData_);
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
                                    this.identifier_ = codedInputStream.readBytes();
                                } else if (readTag == 16) {
                                    this.category_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.manifestIndex_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.manifestCount_ = codedInputStream.readUInt32();
                                } else if (readTag != 42) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.categorySpecificData_ = codedInputStream.readBytes();
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
                    return new BulkDataManifestEntry();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (BulkDataManifestEntry.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
        public int getCategory() {
            return this.category_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
        public ByteString getCategorySpecificData() {
            return this.categorySpecificData_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
        public ByteString getIdentifier() {
            return this.identifier_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
        public int getManifestCount() {
            return this.manifestCount_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataManifestEntryOrBuilder
        public int getManifestIndex() {
            return this.manifestIndex_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.identifier_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.identifier_);
            }
            int i3 = this.category_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = this.manifestIndex_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i4);
            }
            int i5 = this.manifestCount_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(4, i5);
            }
            if (!this.categorySpecificData_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(5, this.categorySpecificData_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.identifier_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.identifier_);
            }
            int i = this.category_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            int i2 = this.manifestIndex_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(3, i2);
            }
            int i3 = this.manifestCount_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(4, i3);
            }
            if (!this.categorySpecificData_.isEmpty()) {
                codedOutputStream.writeBytes(5, this.categorySpecificData_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(BulkDataManifestEntry bulkDataManifestEntry) {
            return DEFAULT_INSTANCE.createBuilder(bulkDataManifestEntry);
        }

        public static BulkDataManifestEntry parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BulkDataManifestEntry parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static BulkDataManifestEntry parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static BulkDataManifestEntry parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static BulkDataManifestEntry parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static BulkDataManifestEntry parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static BulkDataManifestEntry parseFrom(InputStream inputStream) throws IOException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BulkDataManifestEntry parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BulkDataManifestEntry parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static BulkDataManifestEntry parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BulkDataManifestEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface BulkDataManifestEntryOrBuilder extends MessageLiteOrBuilder {
        int getCategory();

        ByteString getCategorySpecificData();

        ByteString getIdentifier();

        int getManifestCount();

        int getManifestIndex();
    }

    /* loaded from: classes6.dex */
    public static final class BulkDataTransferComplete extends GeneratedMessageLite<BulkDataTransferComplete, Builder> implements BulkDataTransferCompleteOrBuilder {
        public static final int CATEGORY_FIELD_NUMBER = 2;
        public static final int CHECKSUM_FIELD_NUMBER = 3;
        private static final BulkDataTransferComplete DEFAULT_INSTANCE = new BulkDataTransferComplete();
        public static final int IDENTIFIER_FIELD_NUMBER = 1;
        private static volatile Parser<BulkDataTransferComplete> PARSER;
        private int category_;
        private ByteString checksum_;
        private ByteString identifier_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<BulkDataTransferComplete, Builder> implements BulkDataTransferCompleteOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((BulkDataTransferComplete) this.instance).clearCategory();
                return this;
            }

            public Builder clearChecksum() {
                copyOnWrite();
                ((BulkDataTransferComplete) this.instance).clearChecksum();
                return this;
            }

            public Builder clearIdentifier() {
                copyOnWrite();
                ((BulkDataTransferComplete) this.instance).clearIdentifier();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferCompleteOrBuilder
            public int getCategory() {
                return ((BulkDataTransferComplete) this.instance).getCategory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferCompleteOrBuilder
            public ByteString getChecksum() {
                return ((BulkDataTransferComplete) this.instance).getChecksum();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferCompleteOrBuilder
            public ByteString getIdentifier() {
                return ((BulkDataTransferComplete) this.instance).getIdentifier();
            }

            public Builder setCategory(int i) {
                copyOnWrite();
                ((BulkDataTransferComplete) this.instance).setCategory(i);
                return this;
            }

            public Builder setChecksum(ByteString byteString) {
                copyOnWrite();
                ((BulkDataTransferComplete) this.instance).setChecksum(byteString);
                return this;
            }

            public Builder setIdentifier(ByteString byteString) {
                copyOnWrite();
                ((BulkDataTransferComplete) this.instance).setIdentifier(byteString);
                return this;
            }

            private Builder() {
                super(BulkDataTransferComplete.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private BulkDataTransferComplete() {
            ByteString byteString = ByteString.EMPTY;
            this.identifier_ = byteString;
            this.checksum_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearChecksum() {
            this.checksum_ = getDefaultInstance().getChecksum();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIdentifier() {
            this.identifier_ = getDefaultInstance().getIdentifier();
        }

        public static BulkDataTransferComplete getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static BulkDataTransferComplete parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BulkDataTransferComplete parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<BulkDataTransferComplete> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategory(int i) {
            this.category_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setChecksum(ByteString byteString) {
            if (byteString != null) {
                this.checksum_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdentifier(ByteString byteString) {
            if (byteString != null) {
                this.identifier_ = byteString;
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
                    BulkDataTransferComplete bulkDataTransferComplete = (BulkDataTransferComplete) obj2;
                    this.identifier_ = visitor.visitByteString(this.identifier_ != ByteString.EMPTY, this.identifier_, bulkDataTransferComplete.identifier_ != ByteString.EMPTY, bulkDataTransferComplete.identifier_);
                    this.category_ = visitor.visitInt(this.category_ != 0, this.category_, bulkDataTransferComplete.category_ != 0, bulkDataTransferComplete.category_);
                    boolean z2 = this.checksum_ != ByteString.EMPTY;
                    ByteString byteString = this.checksum_;
                    if (bulkDataTransferComplete.checksum_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.checksum_ = visitor.visitByteString(z2, byteString, z, bulkDataTransferComplete.checksum_);
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
                                    this.identifier_ = codedInputStream.readBytes();
                                } else if (readTag == 16) {
                                    this.category_ = codedInputStream.readUInt32();
                                } else if (readTag != 26) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.checksum_ = codedInputStream.readBytes();
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
                    return new BulkDataTransferComplete();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (BulkDataTransferComplete.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferCompleteOrBuilder
        public int getCategory() {
            return this.category_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferCompleteOrBuilder
        public ByteString getChecksum() {
            return this.checksum_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferCompleteOrBuilder
        public ByteString getIdentifier() {
            return this.identifier_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.identifier_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.identifier_);
            }
            int i3 = this.category_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            if (!this.checksum_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(3, this.checksum_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.identifier_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.identifier_);
            }
            int i = this.category_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            if (!this.checksum_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.checksum_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(BulkDataTransferComplete bulkDataTransferComplete) {
            return DEFAULT_INSTANCE.createBuilder(bulkDataTransferComplete);
        }

        public static BulkDataTransferComplete parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BulkDataTransferComplete parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static BulkDataTransferComplete parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static BulkDataTransferComplete parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static BulkDataTransferComplete parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static BulkDataTransferComplete parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static BulkDataTransferComplete parseFrom(InputStream inputStream) throws IOException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BulkDataTransferComplete parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BulkDataTransferComplete parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static BulkDataTransferComplete parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BulkDataTransferComplete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface BulkDataTransferCompleteOrBuilder extends MessageLiteOrBuilder {
        int getCategory();

        ByteString getChecksum();

        ByteString getIdentifier();
    }

    /* loaded from: classes6.dex */
    public static final class BulkDataTransferStart extends GeneratedMessageLite<BulkDataTransferStart, Builder> implements BulkDataTransferStartOrBuilder {
        public static final int BLOCK_COUNT_FIELD_NUMBER = 5;
        public static final int BLOCK_INDEX_FIELD_NUMBER = 4;
        public static final int CATEGORY_FIELD_NUMBER = 2;
        private static final BulkDataTransferStart DEFAULT_INSTANCE = new BulkDataTransferStart();
        public static final int IDENTIFIER_FIELD_NUMBER = 1;
        public static final int METADATA_FIELD_NUMBER = 6;
        private static volatile Parser<BulkDataTransferStart> PARSER = null;
        public static final int SIZE_FIELD_NUMBER = 3;
        private int blockCount_;
        private int blockIndex_;
        private int category_;
        private ByteString identifier_;
        private ByteString metadata_;
        private int size_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<BulkDataTransferStart, Builder> implements BulkDataTransferStartOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearBlockCount() {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).clearBlockCount();
                return this;
            }

            public Builder clearBlockIndex() {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).clearBlockIndex();
                return this;
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).clearCategory();
                return this;
            }

            public Builder clearIdentifier() {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).clearIdentifier();
                return this;
            }

            public Builder clearMetadata() {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).clearMetadata();
                return this;
            }

            public Builder clearSize() {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).clearSize();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
            public int getBlockCount() {
                return ((BulkDataTransferStart) this.instance).getBlockCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
            public int getBlockIndex() {
                return ((BulkDataTransferStart) this.instance).getBlockIndex();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
            public int getCategory() {
                return ((BulkDataTransferStart) this.instance).getCategory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
            public ByteString getIdentifier() {
                return ((BulkDataTransferStart) this.instance).getIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
            public ByteString getMetadata() {
                return ((BulkDataTransferStart) this.instance).getMetadata();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
            public int getSize() {
                return ((BulkDataTransferStart) this.instance).getSize();
            }

            public Builder setBlockCount(int i) {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).setBlockCount(i);
                return this;
            }

            public Builder setBlockIndex(int i) {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).setBlockIndex(i);
                return this;
            }

            public Builder setCategory(int i) {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).setCategory(i);
                return this;
            }

            public Builder setIdentifier(ByteString byteString) {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).setIdentifier(byteString);
                return this;
            }

            public Builder setMetadata(ByteString byteString) {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).setMetadata(byteString);
                return this;
            }

            public Builder setSize(int i) {
                copyOnWrite();
                ((BulkDataTransferStart) this.instance).setSize(i);
                return this;
            }

            private Builder() {
                super(BulkDataTransferStart.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private BulkDataTransferStart() {
            ByteString byteString = ByteString.EMPTY;
            this.identifier_ = byteString;
            this.metadata_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBlockCount() {
            this.blockCount_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBlockIndex() {
            this.blockIndex_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIdentifier() {
            this.identifier_ = getDefaultInstance().getIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMetadata() {
            this.metadata_ = getDefaultInstance().getMetadata();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSize() {
            this.size_ = 0;
        }

        public static BulkDataTransferStart getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static BulkDataTransferStart parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BulkDataTransferStart parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<BulkDataTransferStart> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBlockCount(int i) {
            this.blockCount_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBlockIndex(int i) {
            this.blockIndex_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategory(int i) {
            this.category_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdentifier(ByteString byteString) {
            if (byteString != null) {
                this.identifier_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMetadata(ByteString byteString) {
            if (byteString != null) {
                this.metadata_ = byteString;
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
                    BulkDataTransferStart bulkDataTransferStart = (BulkDataTransferStart) obj2;
                    this.identifier_ = visitor.visitByteString(this.identifier_ != ByteString.EMPTY, this.identifier_, bulkDataTransferStart.identifier_ != ByteString.EMPTY, bulkDataTransferStart.identifier_);
                    this.category_ = visitor.visitInt(this.category_ != 0, this.category_, bulkDataTransferStart.category_ != 0, bulkDataTransferStart.category_);
                    this.size_ = visitor.visitInt(this.size_ != 0, this.size_, bulkDataTransferStart.size_ != 0, bulkDataTransferStart.size_);
                    this.blockIndex_ = visitor.visitInt(this.blockIndex_ != 0, this.blockIndex_, bulkDataTransferStart.blockIndex_ != 0, bulkDataTransferStart.blockIndex_);
                    this.blockCount_ = visitor.visitInt(this.blockCount_ != 0, this.blockCount_, bulkDataTransferStart.blockCount_ != 0, bulkDataTransferStart.blockCount_);
                    boolean z2 = this.metadata_ != ByteString.EMPTY;
                    ByteString byteString = this.metadata_;
                    if (bulkDataTransferStart.metadata_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.metadata_ = visitor.visitByteString(z2, byteString, z, bulkDataTransferStart.metadata_);
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
                                    this.identifier_ = codedInputStream.readBytes();
                                } else if (readTag == 16) {
                                    this.category_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.size_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.blockIndex_ = codedInputStream.readUInt32();
                                } else if (readTag == 40) {
                                    this.blockCount_ = codedInputStream.readUInt32();
                                } else if (readTag != 50) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.metadata_ = codedInputStream.readBytes();
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
                    return new BulkDataTransferStart();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (BulkDataTransferStart.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
        public int getBlockCount() {
            return this.blockCount_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
        public int getBlockIndex() {
            return this.blockIndex_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
        public int getCategory() {
            return this.category_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
        public ByteString getIdentifier() {
            return this.identifier_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
        public ByteString getMetadata() {
            return this.metadata_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.identifier_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.identifier_);
            }
            int i3 = this.category_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = this.size_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i4);
            }
            int i5 = this.blockIndex_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(4, i5);
            }
            int i6 = this.blockCount_;
            if (i6 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(5, i6);
            }
            if (!this.metadata_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(6, this.metadata_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.BulkDataTransferStartOrBuilder
        public int getSize() {
            return this.size_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.identifier_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.identifier_);
            }
            int i = this.category_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            int i2 = this.size_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(3, i2);
            }
            int i3 = this.blockIndex_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(4, i3);
            }
            int i4 = this.blockCount_;
            if (i4 != 0) {
                codedOutputStream.writeUInt32(5, i4);
            }
            if (!this.metadata_.isEmpty()) {
                codedOutputStream.writeBytes(6, this.metadata_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(BulkDataTransferStart bulkDataTransferStart) {
            return DEFAULT_INSTANCE.createBuilder(bulkDataTransferStart);
        }

        public static BulkDataTransferStart parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BulkDataTransferStart parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static BulkDataTransferStart parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static BulkDataTransferStart parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static BulkDataTransferStart parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static BulkDataTransferStart parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static BulkDataTransferStart parseFrom(InputStream inputStream) throws IOException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BulkDataTransferStart parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BulkDataTransferStart parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static BulkDataTransferStart parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BulkDataTransferStart) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface BulkDataTransferStartOrBuilder extends MessageLiteOrBuilder {
        int getBlockCount();

        int getBlockIndex();

        int getCategory();

        ByteString getIdentifier();

        ByteString getMetadata();

        int getSize();
    }

    /* loaded from: classes6.dex */
    public static final class GetBulkDataManifest extends GeneratedMessageLite<GetBulkDataManifest, Builder> implements GetBulkDataManifestOrBuilder {
        public static final int CATEGORY_FIELD_NUMBER = 1;
        private static final GetBulkDataManifest DEFAULT_INSTANCE = new GetBulkDataManifest();
        public static final int MANIFEST_OPTIONS_FIELD_NUMBER = 2;
        private static volatile Parser<GetBulkDataManifest> PARSER;
        private int category_;
        private ByteString manifestOptions_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetBulkDataManifest, Builder> implements GetBulkDataManifestOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((GetBulkDataManifest) this.instance).clearCategory();
                return this;
            }

            public Builder clearManifestOptions() {
                copyOnWrite();
                ((GetBulkDataManifest) this.instance).clearManifestOptions();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.GetBulkDataManifestOrBuilder
            public int getCategory() {
                return ((GetBulkDataManifest) this.instance).getCategory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.GetBulkDataManifestOrBuilder
            public ByteString getManifestOptions() {
                return ((GetBulkDataManifest) this.instance).getManifestOptions();
            }

            public Builder setCategory(int i) {
                copyOnWrite();
                ((GetBulkDataManifest) this.instance).setCategory(i);
                return this;
            }

            public Builder setManifestOptions(ByteString byteString) {
                copyOnWrite();
                ((GetBulkDataManifest) this.instance).setManifestOptions(byteString);
                return this;
            }

            private Builder() {
                super(GetBulkDataManifest.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetBulkDataManifest() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearManifestOptions() {
            this.manifestOptions_ = getDefaultInstance().getManifestOptions();
        }

        public static GetBulkDataManifest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetBulkDataManifest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetBulkDataManifest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetBulkDataManifest> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategory(int i) {
            this.category_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setManifestOptions(ByteString byteString) {
            if (byteString != null) {
                this.manifestOptions_ = byteString;
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
                    GetBulkDataManifest getBulkDataManifest = (GetBulkDataManifest) obj2;
                    this.category_ = visitor.visitInt(this.category_ != 0, this.category_, getBulkDataManifest.category_ != 0, getBulkDataManifest.category_);
                    boolean z2 = this.manifestOptions_ != ByteString.EMPTY;
                    ByteString byteString = this.manifestOptions_;
                    if (getBulkDataManifest.manifestOptions_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.manifestOptions_ = visitor.visitByteString(z2, byteString, z, getBulkDataManifest.manifestOptions_);
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
                                    this.category_ = codedInputStream.readUInt32();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.manifestOptions_ = codedInputStream.readBytes();
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
                    return new GetBulkDataManifest();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetBulkDataManifest.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.GetBulkDataManifestOrBuilder
        public int getCategory() {
            return this.category_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.GetBulkDataManifestOrBuilder
        public ByteString getManifestOptions() {
            return this.manifestOptions_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.category_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (!this.manifestOptions_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(2, this.manifestOptions_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.category_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (!this.manifestOptions_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.manifestOptions_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetBulkDataManifest getBulkDataManifest) {
            return DEFAULT_INSTANCE.createBuilder(getBulkDataManifest);
        }

        public static GetBulkDataManifest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetBulkDataManifest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetBulkDataManifest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetBulkDataManifest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetBulkDataManifest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetBulkDataManifest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetBulkDataManifest parseFrom(InputStream inputStream) throws IOException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetBulkDataManifest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetBulkDataManifest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetBulkDataManifest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetBulkDataManifest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetBulkDataManifestOrBuilder extends MessageLiteOrBuilder {
        int getCategory();

        ByteString getManifestOptions();
    }

    /* loaded from: classes6.dex */
    public static final class NotifyBulkDataAvailable extends GeneratedMessageLite<NotifyBulkDataAvailable, Builder> implements NotifyBulkDataAvailableOrBuilder {
        public static final int CATEGORY_FIELD_NUMBER = 2;
        private static final NotifyBulkDataAvailable DEFAULT_INSTANCE = new NotifyBulkDataAvailable();
        public static final int IDENTIFIER_FIELD_NUMBER = 1;
        private static volatile Parser<NotifyBulkDataAvailable> PARSER;
        private int category_;
        private ByteString identifier_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotifyBulkDataAvailable, Builder> implements NotifyBulkDataAvailableOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((NotifyBulkDataAvailable) this.instance).clearCategory();
                return this;
            }

            public Builder clearIdentifier() {
                copyOnWrite();
                ((NotifyBulkDataAvailable) this.instance).clearIdentifier();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.NotifyBulkDataAvailableOrBuilder
            public int getCategory() {
                return ((NotifyBulkDataAvailable) this.instance).getCategory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.NotifyBulkDataAvailableOrBuilder
            public ByteString getIdentifier() {
                return ((NotifyBulkDataAvailable) this.instance).getIdentifier();
            }

            public Builder setCategory(int i) {
                copyOnWrite();
                ((NotifyBulkDataAvailable) this.instance).setCategory(i);
                return this;
            }

            public Builder setIdentifier(ByteString byteString) {
                copyOnWrite();
                ((NotifyBulkDataAvailable) this.instance).setIdentifier(byteString);
                return this;
            }

            private Builder() {
                super(NotifyBulkDataAvailable.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotifyBulkDataAvailable() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIdentifier() {
            this.identifier_ = getDefaultInstance().getIdentifier();
        }

        public static NotifyBulkDataAvailable getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotifyBulkDataAvailable parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyBulkDataAvailable parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotifyBulkDataAvailable> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategory(int i) {
            this.category_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdentifier(ByteString byteString) {
            if (byteString != null) {
                this.identifier_ = byteString;
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
                    NotifyBulkDataAvailable notifyBulkDataAvailable = (NotifyBulkDataAvailable) obj2;
                    this.identifier_ = visitor.visitByteString(this.identifier_ != ByteString.EMPTY, this.identifier_, notifyBulkDataAvailable.identifier_ != ByteString.EMPTY, notifyBulkDataAvailable.identifier_);
                    boolean z2 = this.category_ != 0;
                    int i = this.category_;
                    if (notifyBulkDataAvailable.category_ != 0) {
                        z = true;
                    }
                    this.category_ = visitor.visitInt(z2, i, z, notifyBulkDataAvailable.category_);
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
                                    this.identifier_ = codedInputStream.readBytes();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.category_ = codedInputStream.readUInt32();
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
                    return new NotifyBulkDataAvailable();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotifyBulkDataAvailable.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.NotifyBulkDataAvailableOrBuilder
        public int getCategory() {
            return this.category_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.NotifyBulkDataAvailableOrBuilder
        public ByteString getIdentifier() {
            return this.identifier_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.identifier_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.identifier_);
            }
            int i3 = this.category_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.identifier_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.identifier_);
            }
            int i = this.category_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotifyBulkDataAvailable notifyBulkDataAvailable) {
            return DEFAULT_INSTANCE.createBuilder(notifyBulkDataAvailable);
        }

        public static NotifyBulkDataAvailable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyBulkDataAvailable parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotifyBulkDataAvailable parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static NotifyBulkDataAvailable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NotifyBulkDataAvailable parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyBulkDataAvailable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotifyBulkDataAvailable parseFrom(InputStream inputStream) throws IOException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyBulkDataAvailable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotifyBulkDataAvailable parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotifyBulkDataAvailable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotifyBulkDataAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotifyBulkDataAvailableOrBuilder extends MessageLiteOrBuilder {
        int getCategory();

        ByteString getIdentifier();
    }

    /* loaded from: classes6.dex */
    public static final class RequestBulkDataTransfer extends GeneratedMessageLite<RequestBulkDataTransfer, Builder> implements RequestBulkDataTransferOrBuilder {
        public static final int BLOCK_START_INDEX_FIELD_NUMBER = 3;
        public static final int CATEGORY_FIELD_NUMBER = 2;
        private static final RequestBulkDataTransfer DEFAULT_INSTANCE = new RequestBulkDataTransfer();
        public static final int IDENTIFIER_FIELD_NUMBER = 1;
        private static volatile Parser<RequestBulkDataTransfer> PARSER = null;
        public static final int VALIDATE_INTEGRITY_FIELD_NUMBER = 4;
        private int blockStartIndex_;
        private int category_;
        private ByteString identifier_ = ByteString.EMPTY;
        private boolean validateIntegrity_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<RequestBulkDataTransfer, Builder> implements RequestBulkDataTransferOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearBlockStartIndex() {
                copyOnWrite();
                ((RequestBulkDataTransfer) this.instance).clearBlockStartIndex();
                return this;
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((RequestBulkDataTransfer) this.instance).clearCategory();
                return this;
            }

            public Builder clearIdentifier() {
                copyOnWrite();
                ((RequestBulkDataTransfer) this.instance).clearIdentifier();
                return this;
            }

            public Builder clearValidateIntegrity() {
                copyOnWrite();
                ((RequestBulkDataTransfer) this.instance).clearValidateIntegrity();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.RequestBulkDataTransferOrBuilder
            public int getBlockStartIndex() {
                return ((RequestBulkDataTransfer) this.instance).getBlockStartIndex();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.RequestBulkDataTransferOrBuilder
            public int getCategory() {
                return ((RequestBulkDataTransfer) this.instance).getCategory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.RequestBulkDataTransferOrBuilder
            public ByteString getIdentifier() {
                return ((RequestBulkDataTransfer) this.instance).getIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Bulkdata.RequestBulkDataTransferOrBuilder
            public boolean getValidateIntegrity() {
                return ((RequestBulkDataTransfer) this.instance).getValidateIntegrity();
            }

            public Builder setBlockStartIndex(int i) {
                copyOnWrite();
                ((RequestBulkDataTransfer) this.instance).setBlockStartIndex(i);
                return this;
            }

            public Builder setCategory(int i) {
                copyOnWrite();
                ((RequestBulkDataTransfer) this.instance).setCategory(i);
                return this;
            }

            public Builder setIdentifier(ByteString byteString) {
                copyOnWrite();
                ((RequestBulkDataTransfer) this.instance).setIdentifier(byteString);
                return this;
            }

            public Builder setValidateIntegrity(boolean z) {
                copyOnWrite();
                ((RequestBulkDataTransfer) this.instance).setValidateIntegrity(z);
                return this;
            }

            private Builder() {
                super(RequestBulkDataTransfer.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private RequestBulkDataTransfer() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBlockStartIndex() {
            this.blockStartIndex_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIdentifier() {
            this.identifier_ = getDefaultInstance().getIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValidateIntegrity() {
            this.validateIntegrity_ = false;
        }

        public static RequestBulkDataTransfer getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static RequestBulkDataTransfer parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RequestBulkDataTransfer parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<RequestBulkDataTransfer> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBlockStartIndex(int i) {
            this.blockStartIndex_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategory(int i) {
            this.category_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdentifier(ByteString byteString) {
            if (byteString != null) {
                this.identifier_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValidateIntegrity(boolean z) {
            this.validateIntegrity_ = z;
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
                    RequestBulkDataTransfer requestBulkDataTransfer = (RequestBulkDataTransfer) obj2;
                    this.identifier_ = visitor.visitByteString(this.identifier_ != ByteString.EMPTY, this.identifier_, requestBulkDataTransfer.identifier_ != ByteString.EMPTY, requestBulkDataTransfer.identifier_);
                    this.category_ = visitor.visitInt(this.category_ != 0, this.category_, requestBulkDataTransfer.category_ != 0, requestBulkDataTransfer.category_);
                    boolean z2 = this.blockStartIndex_ != 0;
                    int i = this.blockStartIndex_;
                    if (requestBulkDataTransfer.blockStartIndex_ != 0) {
                        z = true;
                    }
                    this.blockStartIndex_ = visitor.visitInt(z2, i, z, requestBulkDataTransfer.blockStartIndex_);
                    boolean z3 = this.validateIntegrity_;
                    boolean z4 = requestBulkDataTransfer.validateIntegrity_;
                    this.validateIntegrity_ = visitor.visitBoolean(z3, z3, z4, z4);
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
                                    this.identifier_ = codedInputStream.readBytes();
                                } else if (readTag == 16) {
                                    this.category_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.blockStartIndex_ = codedInputStream.readUInt32();
                                } else if (readTag != 32) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.validateIntegrity_ = codedInputStream.readBool();
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
                    return new RequestBulkDataTransfer();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (RequestBulkDataTransfer.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.RequestBulkDataTransferOrBuilder
        public int getBlockStartIndex() {
            return this.blockStartIndex_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.RequestBulkDataTransferOrBuilder
        public int getCategory() {
            return this.category_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.RequestBulkDataTransferOrBuilder
        public ByteString getIdentifier() {
            return this.identifier_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.identifier_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.identifier_);
            }
            int i3 = this.category_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = this.blockStartIndex_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i4);
            }
            boolean z = this.validateIntegrity_;
            if (z) {
                i2 += CodedOutputStream.computeBoolSize(4, z);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Bulkdata.RequestBulkDataTransferOrBuilder
        public boolean getValidateIntegrity() {
            return this.validateIntegrity_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.identifier_.isEmpty()) {
                codedOutputStream.writeBytes(1, this.identifier_);
            }
            int i = this.category_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            int i2 = this.blockStartIndex_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(3, i2);
            }
            boolean z = this.validateIntegrity_;
            if (z) {
                codedOutputStream.writeBool(4, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(RequestBulkDataTransfer requestBulkDataTransfer) {
            return DEFAULT_INSTANCE.createBuilder(requestBulkDataTransfer);
        }

        public static RequestBulkDataTransfer parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RequestBulkDataTransfer parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static RequestBulkDataTransfer parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static RequestBulkDataTransfer parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static RequestBulkDataTransfer parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static RequestBulkDataTransfer parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static RequestBulkDataTransfer parseFrom(InputStream inputStream) throws IOException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RequestBulkDataTransfer parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RequestBulkDataTransfer parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static RequestBulkDataTransfer parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RequestBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface RequestBulkDataTransferOrBuilder extends MessageLiteOrBuilder {
        int getBlockStartIndex();

        int getCategory();

        ByteString getIdentifier();

        boolean getValidateIntegrity();
    }

    /* loaded from: classes6.dex */
    public static final class StopBulkDataTransfer extends GeneratedMessageLite<StopBulkDataTransfer, Builder> implements StopBulkDataTransferOrBuilder {
        private static final StopBulkDataTransfer DEFAULT_INSTANCE = new StopBulkDataTransfer();
        private static volatile Parser<StopBulkDataTransfer> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<StopBulkDataTransfer, Builder> implements StopBulkDataTransferOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(StopBulkDataTransfer.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private StopBulkDataTransfer() {
        }

        public static StopBulkDataTransfer getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static StopBulkDataTransfer parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StopBulkDataTransfer parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<StopBulkDataTransfer> parser() {
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
                    StopBulkDataTransfer stopBulkDataTransfer = (StopBulkDataTransfer) obj2;
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
                    return new StopBulkDataTransfer();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (StopBulkDataTransfer.class) {
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

        public static Builder newBuilder(StopBulkDataTransfer stopBulkDataTransfer) {
            return DEFAULT_INSTANCE.createBuilder(stopBulkDataTransfer);
        }

        public static StopBulkDataTransfer parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StopBulkDataTransfer parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static StopBulkDataTransfer parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static StopBulkDataTransfer parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static StopBulkDataTransfer parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static StopBulkDataTransfer parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static StopBulkDataTransfer parseFrom(InputStream inputStream) throws IOException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static StopBulkDataTransfer parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static StopBulkDataTransfer parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static StopBulkDataTransfer parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StopBulkDataTransfer) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface StopBulkDataTransferOrBuilder extends MessageLiteOrBuilder {
    }

    private Bulkdata() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
