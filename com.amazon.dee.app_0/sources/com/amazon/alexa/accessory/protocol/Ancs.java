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
public final class Ancs {

    /* renamed from: com.amazon.alexa.accessory.protocol.Ancs$1  reason: invalid class name */
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
    public enum Attribute implements Internal.EnumLite {
        APP_IDENTIFIER(0),
        TITLE(1),
        SUBTITLE(2),
        CONTENT(3),
        DATE(4),
        POSITIVE_ACTION_LABEL(5),
        NEGATIVE_ACTION_LABEL(6),
        CONTENT_SIZE(7),
        UNRECOGNIZED(-1);
        
        public static final int APP_IDENTIFIER_VALUE = 0;
        public static final int CONTENT_SIZE_VALUE = 7;
        public static final int CONTENT_VALUE = 3;
        public static final int DATE_VALUE = 4;
        public static final int NEGATIVE_ACTION_LABEL_VALUE = 6;
        public static final int POSITIVE_ACTION_LABEL_VALUE = 5;
        public static final int SUBTITLE_VALUE = 2;
        public static final int TITLE_VALUE = 1;
        private static final Internal.EnumLiteMap<Attribute> internalValueMap = new Internal.EnumLiteMap<Attribute>() { // from class: com.amazon.alexa.accessory.protocol.Ancs.Attribute.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public Attribute mo9850findValueByNumber(int i) {
                return Attribute.forNumber(i);
            }
        };
        private final int value;

        Attribute(int i) {
            this.value = i;
        }

        public static Attribute forNumber(int i) {
            switch (i) {
                case 0:
                    return APP_IDENTIFIER;
                case 1:
                    return TITLE;
                case 2:
                    return SUBTITLE;
                case 3:
                    return CONTENT;
                case 4:
                    return DATE;
                case 5:
                    return POSITIVE_ACTION_LABEL;
                case 6:
                    return NEGATIVE_ACTION_LABEL;
                case 7:
                    return CONTENT_SIZE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Attribute> internalGetValueMap() {
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
        public static Attribute valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class CentralNotificationAppAttributes extends GeneratedMessageLite<CentralNotificationAppAttributes, Builder> implements CentralNotificationAppAttributesOrBuilder {
        public static final int APP_IDENTIFIER_FIELD_NUMBER = 1;
        private static final CentralNotificationAppAttributes DEFAULT_INSTANCE = new CentralNotificationAppAttributes();
        public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
        private static volatile Parser<CentralNotificationAppAttributes> PARSER;
        private String appIdentifier_ = "";
        private String displayName_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CentralNotificationAppAttributes, Builder> implements CentralNotificationAppAttributesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAppIdentifier() {
                copyOnWrite();
                ((CentralNotificationAppAttributes) this.instance).clearAppIdentifier();
                return this;
            }

            public Builder clearDisplayName() {
                copyOnWrite();
                ((CentralNotificationAppAttributes) this.instance).clearDisplayName();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAppAttributesOrBuilder
            public String getAppIdentifier() {
                return ((CentralNotificationAppAttributes) this.instance).getAppIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAppAttributesOrBuilder
            public ByteString getAppIdentifierBytes() {
                return ((CentralNotificationAppAttributes) this.instance).getAppIdentifierBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAppAttributesOrBuilder
            public String getDisplayName() {
                return ((CentralNotificationAppAttributes) this.instance).getDisplayName();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAppAttributesOrBuilder
            public ByteString getDisplayNameBytes() {
                return ((CentralNotificationAppAttributes) this.instance).getDisplayNameBytes();
            }

            public Builder setAppIdentifier(String str) {
                copyOnWrite();
                ((CentralNotificationAppAttributes) this.instance).setAppIdentifier(str);
                return this;
            }

            public Builder setAppIdentifierBytes(ByteString byteString) {
                copyOnWrite();
                ((CentralNotificationAppAttributes) this.instance).setAppIdentifierBytes(byteString);
                return this;
            }

            public Builder setDisplayName(String str) {
                copyOnWrite();
                ((CentralNotificationAppAttributes) this.instance).setDisplayName(str);
                return this;
            }

            public Builder setDisplayNameBytes(ByteString byteString) {
                copyOnWrite();
                ((CentralNotificationAppAttributes) this.instance).setDisplayNameBytes(byteString);
                return this;
            }

            private Builder() {
                super(CentralNotificationAppAttributes.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CentralNotificationAppAttributes() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppIdentifier() {
            this.appIdentifier_ = getDefaultInstance().getAppIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDisplayName() {
            this.displayName_ = getDefaultInstance().getDisplayName();
        }

        public static CentralNotificationAppAttributes getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CentralNotificationAppAttributes parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CentralNotificationAppAttributes parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CentralNotificationAppAttributes> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppIdentifier(String str) {
            if (str != null) {
                this.appIdentifier_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppIdentifierBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.appIdentifier_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisplayName(String str) {
            if (str != null) {
                this.displayName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.displayName_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CentralNotificationAppAttributes centralNotificationAppAttributes = (CentralNotificationAppAttributes) obj2;
                    this.appIdentifier_ = visitor.visitString(!this.appIdentifier_.isEmpty(), this.appIdentifier_, !centralNotificationAppAttributes.appIdentifier_.isEmpty(), centralNotificationAppAttributes.appIdentifier_);
                    this.displayName_ = visitor.visitString(!this.displayName_.isEmpty(), this.displayName_, true ^ centralNotificationAppAttributes.displayName_.isEmpty(), centralNotificationAppAttributes.displayName_);
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
                                if (readTag == 10) {
                                    this.appIdentifier_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.displayName_ = codedInputStream.readStringRequireUtf8();
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
                    return new CentralNotificationAppAttributes();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CentralNotificationAppAttributes.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAppAttributesOrBuilder
        public String getAppIdentifier() {
            return this.appIdentifier_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAppAttributesOrBuilder
        public ByteString getAppIdentifierBytes() {
            return ByteString.copyFromUtf8(this.appIdentifier_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAppAttributesOrBuilder
        public String getDisplayName() {
            return this.displayName_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAppAttributesOrBuilder
        public ByteString getDisplayNameBytes() {
            return ByteString.copyFromUtf8(this.displayName_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.appIdentifier_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getAppIdentifier());
            }
            if (!this.displayName_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getDisplayName());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.appIdentifier_.isEmpty()) {
                codedOutputStream.writeString(1, getAppIdentifier());
            }
            if (!this.displayName_.isEmpty()) {
                codedOutputStream.writeString(2, getDisplayName());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CentralNotificationAppAttributes centralNotificationAppAttributes) {
            return DEFAULT_INSTANCE.createBuilder(centralNotificationAppAttributes);
        }

        public static CentralNotificationAppAttributes parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CentralNotificationAppAttributes parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CentralNotificationAppAttributes parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CentralNotificationAppAttributes parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CentralNotificationAppAttributes parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CentralNotificationAppAttributes parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CentralNotificationAppAttributes parseFrom(InputStream inputStream) throws IOException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CentralNotificationAppAttributes parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CentralNotificationAppAttributes parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CentralNotificationAppAttributes parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CentralNotificationAppAttributesOrBuilder extends MessageLiteOrBuilder {
        String getAppIdentifier();

        ByteString getAppIdentifierBytes();

        String getDisplayName();

        ByteString getDisplayNameBytes();
    }

    /* loaded from: classes6.dex */
    public static final class CentralNotificationAttributes extends GeneratedMessageLite<CentralNotificationAttributes, Builder> implements CentralNotificationAttributesOrBuilder {
        public static final int APP_IDENTIFIER_FIELD_NUMBER = 2;
        public static final int CONTENT_FIELD_NUMBER = 5;
        public static final int DATE_FIELD_NUMBER = 6;
        private static final CentralNotificationAttributes DEFAULT_INSTANCE = new CentralNotificationAttributes();
        public static final int HAS_MORE_FIELD_NUMBER = 9;
        public static final int NEGATIVE_ACTION_LABEL_FIELD_NUMBER = 8;
        private static volatile Parser<CentralNotificationAttributes> PARSER = null;
        public static final int POSITIVE_ACTION_LABEL_FIELD_NUMBER = 7;
        public static final int SUBTITLE_FIELD_NUMBER = 4;
        public static final int TITLE_FIELD_NUMBER = 3;
        public static final int UID_FIELD_NUMBER = 1;
        private ByteString appIdentifier_;
        private ByteString content_;
        private ByteString date_;
        private boolean hasMore_;
        private ByteString negativeActionLabel_;
        private ByteString positiveActionLabel_;
        private ByteString subtitle_;
        private ByteString title_;
        private int uid_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CentralNotificationAttributes, Builder> implements CentralNotificationAttributesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAppIdentifier() {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).clearAppIdentifier();
                return this;
            }

            public Builder clearContent() {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).clearContent();
                return this;
            }

            public Builder clearDate() {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).clearDate();
                return this;
            }

            public Builder clearHasMore() {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).clearHasMore();
                return this;
            }

            public Builder clearNegativeActionLabel() {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).clearNegativeActionLabel();
                return this;
            }

            public Builder clearPositiveActionLabel() {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).clearPositiveActionLabel();
                return this;
            }

            public Builder clearSubtitle() {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).clearSubtitle();
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).clearTitle();
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).clearUid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
            public ByteString getAppIdentifier() {
                return ((CentralNotificationAttributes) this.instance).getAppIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
            public ByteString getContent() {
                return ((CentralNotificationAttributes) this.instance).getContent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
            public ByteString getDate() {
                return ((CentralNotificationAttributes) this.instance).getDate();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
            public boolean getHasMore() {
                return ((CentralNotificationAttributes) this.instance).getHasMore();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
            public ByteString getNegativeActionLabel() {
                return ((CentralNotificationAttributes) this.instance).getNegativeActionLabel();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
            public ByteString getPositiveActionLabel() {
                return ((CentralNotificationAttributes) this.instance).getPositiveActionLabel();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
            public ByteString getSubtitle() {
                return ((CentralNotificationAttributes) this.instance).getSubtitle();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
            public ByteString getTitle() {
                return ((CentralNotificationAttributes) this.instance).getTitle();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
            public int getUid() {
                return ((CentralNotificationAttributes) this.instance).getUid();
            }

            public Builder setAppIdentifier(ByteString byteString) {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).setAppIdentifier(byteString);
                return this;
            }

            public Builder setContent(ByteString byteString) {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).setContent(byteString);
                return this;
            }

            public Builder setDate(ByteString byteString) {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).setDate(byteString);
                return this;
            }

            public Builder setHasMore(boolean z) {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).setHasMore(z);
                return this;
            }

            public Builder setNegativeActionLabel(ByteString byteString) {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).setNegativeActionLabel(byteString);
                return this;
            }

            public Builder setPositiveActionLabel(ByteString byteString) {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).setPositiveActionLabel(byteString);
                return this;
            }

            public Builder setSubtitle(ByteString byteString) {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).setSubtitle(byteString);
                return this;
            }

            public Builder setTitle(ByteString byteString) {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).setTitle(byteString);
                return this;
            }

            public Builder setUid(int i) {
                copyOnWrite();
                ((CentralNotificationAttributes) this.instance).setUid(i);
                return this;
            }

            private Builder() {
                super(CentralNotificationAttributes.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CentralNotificationAttributes() {
            ByteString byteString = ByteString.EMPTY;
            this.appIdentifier_ = byteString;
            this.title_ = byteString;
            this.subtitle_ = byteString;
            this.content_ = byteString;
            this.date_ = byteString;
            this.positiveActionLabel_ = byteString;
            this.negativeActionLabel_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppIdentifier() {
            this.appIdentifier_ = getDefaultInstance().getAppIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContent() {
            this.content_ = getDefaultInstance().getContent();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDate() {
            this.date_ = getDefaultInstance().getDate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHasMore() {
            this.hasMore_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNegativeActionLabel() {
            this.negativeActionLabel_ = getDefaultInstance().getNegativeActionLabel();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPositiveActionLabel() {
            this.positiveActionLabel_ = getDefaultInstance().getPositiveActionLabel();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSubtitle() {
            this.subtitle_ = getDefaultInstance().getSubtitle();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTitle() {
            this.title_ = getDefaultInstance().getTitle();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUid() {
            this.uid_ = 0;
        }

        public static CentralNotificationAttributes getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CentralNotificationAttributes parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CentralNotificationAttributes parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CentralNotificationAttributes> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppIdentifier(ByteString byteString) {
            if (byteString != null) {
                this.appIdentifier_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContent(ByteString byteString) {
            if (byteString != null) {
                this.content_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDate(ByteString byteString) {
            if (byteString != null) {
                this.date_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHasMore(boolean z) {
            this.hasMore_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNegativeActionLabel(ByteString byteString) {
            if (byteString != null) {
                this.negativeActionLabel_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPositiveActionLabel(ByteString byteString) {
            if (byteString != null) {
                this.positiveActionLabel_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubtitle(ByteString byteString) {
            if (byteString != null) {
                this.subtitle_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTitle(ByteString byteString) {
            if (byteString != null) {
                this.title_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUid(int i) {
            this.uid_ = i;
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
                    CentralNotificationAttributes centralNotificationAttributes = (CentralNotificationAttributes) obj2;
                    this.uid_ = visitor.visitInt(this.uid_ != 0, this.uid_, centralNotificationAttributes.uid_ != 0, centralNotificationAttributes.uid_);
                    this.appIdentifier_ = visitor.visitByteString(this.appIdentifier_ != ByteString.EMPTY, this.appIdentifier_, centralNotificationAttributes.appIdentifier_ != ByteString.EMPTY, centralNotificationAttributes.appIdentifier_);
                    this.title_ = visitor.visitByteString(this.title_ != ByteString.EMPTY, this.title_, centralNotificationAttributes.title_ != ByteString.EMPTY, centralNotificationAttributes.title_);
                    this.subtitle_ = visitor.visitByteString(this.subtitle_ != ByteString.EMPTY, this.subtitle_, centralNotificationAttributes.subtitle_ != ByteString.EMPTY, centralNotificationAttributes.subtitle_);
                    this.content_ = visitor.visitByteString(this.content_ != ByteString.EMPTY, this.content_, centralNotificationAttributes.content_ != ByteString.EMPTY, centralNotificationAttributes.content_);
                    this.date_ = visitor.visitByteString(this.date_ != ByteString.EMPTY, this.date_, centralNotificationAttributes.date_ != ByteString.EMPTY, centralNotificationAttributes.date_);
                    this.positiveActionLabel_ = visitor.visitByteString(this.positiveActionLabel_ != ByteString.EMPTY, this.positiveActionLabel_, centralNotificationAttributes.positiveActionLabel_ != ByteString.EMPTY, centralNotificationAttributes.positiveActionLabel_);
                    boolean z2 = this.negativeActionLabel_ != ByteString.EMPTY;
                    ByteString byteString = this.negativeActionLabel_;
                    if (centralNotificationAttributes.negativeActionLabel_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.negativeActionLabel_ = visitor.visitByteString(z2, byteString, z, centralNotificationAttributes.negativeActionLabel_);
                    boolean z3 = this.hasMore_;
                    boolean z4 = centralNotificationAttributes.hasMore_;
                    this.hasMore_ = visitor.visitBoolean(z3, z3, z4, z4);
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
                                    this.uid_ = codedInputStream.readUInt32();
                                } else if (readTag == 18) {
                                    this.appIdentifier_ = codedInputStream.readBytes();
                                } else if (readTag == 26) {
                                    this.title_ = codedInputStream.readBytes();
                                } else if (readTag == 34) {
                                    this.subtitle_ = codedInputStream.readBytes();
                                } else if (readTag == 42) {
                                    this.content_ = codedInputStream.readBytes();
                                } else if (readTag == 50) {
                                    this.date_ = codedInputStream.readBytes();
                                } else if (readTag == 58) {
                                    this.positiveActionLabel_ = codedInputStream.readBytes();
                                } else if (readTag == 66) {
                                    this.negativeActionLabel_ = codedInputStream.readBytes();
                                } else if (readTag != 72) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.hasMore_ = codedInputStream.readBool();
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
                    return new CentralNotificationAttributes();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CentralNotificationAttributes.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
        public ByteString getAppIdentifier() {
            return this.appIdentifier_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
        public ByteString getContent() {
            return this.content_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
        public ByteString getDate() {
            return this.date_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
        public boolean getHasMore() {
            return this.hasMore_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
        public ByteString getNegativeActionLabel() {
            return this.negativeActionLabel_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
        public ByteString getPositiveActionLabel() {
            return this.positiveActionLabel_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.uid_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (!this.appIdentifier_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(2, this.appIdentifier_);
            }
            if (!this.title_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(3, this.title_);
            }
            if (!this.subtitle_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(4, this.subtitle_);
            }
            if (!this.content_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(5, this.content_);
            }
            if (!this.date_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(6, this.date_);
            }
            if (!this.positiveActionLabel_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(7, this.positiveActionLabel_);
            }
            if (!this.negativeActionLabel_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(8, this.negativeActionLabel_);
            }
            boolean z = this.hasMore_;
            if (z) {
                i2 += CodedOutputStream.computeBoolSize(9, z);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
        public ByteString getSubtitle() {
            return this.subtitle_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
        public ByteString getTitle() {
            return this.title_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.CentralNotificationAttributesOrBuilder
        public int getUid() {
            return this.uid_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.uid_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (!this.appIdentifier_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.appIdentifier_);
            }
            if (!this.title_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.title_);
            }
            if (!this.subtitle_.isEmpty()) {
                codedOutputStream.writeBytes(4, this.subtitle_);
            }
            if (!this.content_.isEmpty()) {
                codedOutputStream.writeBytes(5, this.content_);
            }
            if (!this.date_.isEmpty()) {
                codedOutputStream.writeBytes(6, this.date_);
            }
            if (!this.positiveActionLabel_.isEmpty()) {
                codedOutputStream.writeBytes(7, this.positiveActionLabel_);
            }
            if (!this.negativeActionLabel_.isEmpty()) {
                codedOutputStream.writeBytes(8, this.negativeActionLabel_);
            }
            boolean z = this.hasMore_;
            if (z) {
                codedOutputStream.writeBool(9, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CentralNotificationAttributes centralNotificationAttributes) {
            return DEFAULT_INSTANCE.createBuilder(centralNotificationAttributes);
        }

        public static CentralNotificationAttributes parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CentralNotificationAttributes parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CentralNotificationAttributes parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CentralNotificationAttributes parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CentralNotificationAttributes parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CentralNotificationAttributes parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CentralNotificationAttributes parseFrom(InputStream inputStream) throws IOException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CentralNotificationAttributes parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CentralNotificationAttributes parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CentralNotificationAttributes parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CentralNotificationAttributesOrBuilder extends MessageLiteOrBuilder {
        ByteString getAppIdentifier();

        ByteString getContent();

        ByteString getDate();

        boolean getHasMore();

        ByteString getNegativeActionLabel();

        ByteString getPositiveActionLabel();

        ByteString getSubtitle();

        ByteString getTitle();

        int getUid();
    }

    /* loaded from: classes6.dex */
    public static final class GetCentralNotificationAppAttributes extends GeneratedMessageLite<GetCentralNotificationAppAttributes, Builder> implements GetCentralNotificationAppAttributesOrBuilder {
        public static final int APP_IDENTIFIER_FIELD_NUMBER = 1;
        public static final int ATTRIBUTES_FIELD_NUMBER = 2;
        private static volatile Parser<GetCentralNotificationAppAttributes> PARSER;
        private int attributesMemoizedSerializedSize;
        private int bitField0_;
        private static final Internal.ListAdapter.Converter<Integer, Attribute> attributes_converter_ = new Internal.ListAdapter.Converter<Integer, Attribute>() { // from class: com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributes.1
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public Attribute convert(Integer num) {
                Attribute forNumber = Attribute.forNumber(num.intValue());
                return forNumber == null ? Attribute.UNRECOGNIZED : forNumber;
            }
        };
        private static final GetCentralNotificationAppAttributes DEFAULT_INSTANCE = new GetCentralNotificationAppAttributes();
        private String appIdentifier_ = "";
        private Internal.IntList attributes_ = GeneratedMessageLite.emptyIntList();

        /* loaded from: classes6.dex */
        public enum Attribute implements Internal.EnumLite {
            DISPLAY_NAME(0),
            UNRECOGNIZED(-1);
            
            public static final int DISPLAY_NAME_VALUE = 0;
            private static final Internal.EnumLiteMap<Attribute> internalValueMap = new Internal.EnumLiteMap<Attribute>() { // from class: com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributes.Attribute.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public Attribute mo9850findValueByNumber(int i) {
                    return Attribute.forNumber(i);
                }
            };
            private final int value;

            Attribute(int i) {
                this.value = i;
            }

            public static Attribute forNumber(int i) {
                if (i != 0) {
                    return null;
                }
                return DISPLAY_NAME;
            }

            public static Internal.EnumLiteMap<Attribute> internalGetValueMap() {
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
            public static Attribute valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetCentralNotificationAppAttributes, Builder> implements GetCentralNotificationAppAttributesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllAttributes(Iterable<? extends Attribute> iterable) {
                copyOnWrite();
                ((GetCentralNotificationAppAttributes) this.instance).addAllAttributes(iterable);
                return this;
            }

            public Builder addAllAttributesValue(Iterable<Integer> iterable) {
                copyOnWrite();
                ((GetCentralNotificationAppAttributes) this.instance).addAllAttributesValue(iterable);
                return this;
            }

            public Builder addAttributes(Attribute attribute) {
                copyOnWrite();
                ((GetCentralNotificationAppAttributes) this.instance).addAttributes(attribute);
                return this;
            }

            public Builder addAttributesValue(int i) {
                ((GetCentralNotificationAppAttributes) this.instance).addAttributesValue(i);
                return this;
            }

            public Builder clearAppIdentifier() {
                copyOnWrite();
                ((GetCentralNotificationAppAttributes) this.instance).clearAppIdentifier();
                return this;
            }

            public Builder clearAttributes() {
                copyOnWrite();
                ((GetCentralNotificationAppAttributes) this.instance).clearAttributes();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
            public String getAppIdentifier() {
                return ((GetCentralNotificationAppAttributes) this.instance).getAppIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
            public ByteString getAppIdentifierBytes() {
                return ((GetCentralNotificationAppAttributes) this.instance).getAppIdentifierBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
            public Attribute getAttributes(int i) {
                return ((GetCentralNotificationAppAttributes) this.instance).getAttributes(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
            public int getAttributesCount() {
                return ((GetCentralNotificationAppAttributes) this.instance).getAttributesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
            public List<Attribute> getAttributesList() {
                return ((GetCentralNotificationAppAttributes) this.instance).getAttributesList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
            public int getAttributesValue(int i) {
                return ((GetCentralNotificationAppAttributes) this.instance).getAttributesValue(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
            public List<Integer> getAttributesValueList() {
                return Collections.unmodifiableList(((GetCentralNotificationAppAttributes) this.instance).getAttributesValueList());
            }

            public Builder setAppIdentifier(String str) {
                copyOnWrite();
                ((GetCentralNotificationAppAttributes) this.instance).setAppIdentifier(str);
                return this;
            }

            public Builder setAppIdentifierBytes(ByteString byteString) {
                copyOnWrite();
                ((GetCentralNotificationAppAttributes) this.instance).setAppIdentifierBytes(byteString);
                return this;
            }

            public Builder setAttributes(int i, Attribute attribute) {
                copyOnWrite();
                ((GetCentralNotificationAppAttributes) this.instance).setAttributes(i, attribute);
                return this;
            }

            public Builder setAttributesValue(int i, int i2) {
                copyOnWrite();
                ((GetCentralNotificationAppAttributes) this.instance).setAttributesValue(i, i2);
                return this;
            }

            private Builder() {
                super(GetCentralNotificationAppAttributes.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetCentralNotificationAppAttributes() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAttributes(Iterable<? extends Attribute> iterable) {
            ensureAttributesIsMutable();
            for (Attribute attribute : iterable) {
                this.attributes_.addInt(attribute.getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAttributesValue(Iterable<Integer> iterable) {
            ensureAttributesIsMutable();
            for (Integer num : iterable) {
                this.attributes_.addInt(num.intValue());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttributes(Attribute attribute) {
            if (attribute != null) {
                ensureAttributesIsMutable();
                this.attributes_.addInt(attribute.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttributesValue(int i) {
            ensureAttributesIsMutable();
            this.attributes_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppIdentifier() {
            this.appIdentifier_ = getDefaultInstance().getAppIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAttributes() {
            this.attributes_ = GeneratedMessageLite.emptyIntList();
        }

        private void ensureAttributesIsMutable() {
            if (!this.attributes_.isModifiable()) {
                this.attributes_ = GeneratedMessageLite.mutableCopy(this.attributes_);
            }
        }

        public static GetCentralNotificationAppAttributes getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetCentralNotificationAppAttributes parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCentralNotificationAppAttributes parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetCentralNotificationAppAttributes> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppIdentifier(String str) {
            if (str != null) {
                this.appIdentifier_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppIdentifierBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.appIdentifier_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAttributes(int i, Attribute attribute) {
            if (attribute != null) {
                ensureAttributesIsMutable();
                this.attributes_.setInt(i, attribute.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAttributesValue(int i, int i2) {
            ensureAttributesIsMutable();
            this.attributes_.setInt(i, i2);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    GetCentralNotificationAppAttributes getCentralNotificationAppAttributes = (GetCentralNotificationAppAttributes) obj2;
                    this.appIdentifier_ = visitor.visitString(!this.appIdentifier_.isEmpty(), this.appIdentifier_, true ^ getCentralNotificationAppAttributes.appIdentifier_.isEmpty(), getCentralNotificationAppAttributes.appIdentifier_);
                    this.attributes_ = visitor.visitIntList(this.attributes_, getCentralNotificationAppAttributes.attributes_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= getCentralNotificationAppAttributes.bitField0_;
                    }
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
                                if (readTag == 10) {
                                    this.appIdentifier_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 16) {
                                    if (!this.attributes_.isModifiable()) {
                                        this.attributes_ = GeneratedMessageLite.mutableCopy(this.attributes_);
                                    }
                                    this.attributes_.addInt(codedInputStream.readEnum());
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    if (!this.attributes_.isModifiable()) {
                                        this.attributes_ = GeneratedMessageLite.mutableCopy(this.attributes_);
                                    }
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.attributes_.addInt(codedInputStream.readEnum());
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
                    this.attributes_.makeImmutable();
                    return null;
                case 6:
                    return new GetCentralNotificationAppAttributes();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetCentralNotificationAppAttributes.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
        public String getAppIdentifier() {
            return this.appIdentifier_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
        public ByteString getAppIdentifierBytes() {
            return ByteString.copyFromUtf8(this.appIdentifier_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
        public Attribute getAttributes(int i) {
            return attributes_converter_.convert(Integer.valueOf(this.attributes_.getInt(i)));
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
        public int getAttributesCount() {
            return this.attributes_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
        public List<Attribute> getAttributesList() {
            return new Internal.ListAdapter(this.attributes_, attributes_converter_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
        public int getAttributesValue(int i) {
            return this.attributes_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAppAttributesOrBuilder
        public List<Integer> getAttributesValueList() {
            return this.attributes_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = !this.appIdentifier_.isEmpty() ? CodedOutputStream.computeStringSize(1, getAppIdentifier()) + 0 : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.attributes_.size(); i3++) {
                i2 += CodedOutputStream.computeEnumSizeNoTag(this.attributes_.getInt(i3));
            }
            int i4 = computeStringSize + i2;
            if (!getAttributesList().isEmpty()) {
                i4 = i4 + 1 + CodedOutputStream.computeUInt32SizeNoTag(i2);
            }
            this.attributesMemoizedSerializedSize = i2;
            int serializedSize = this.unknownFields.getSerializedSize() + i4;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if (!this.appIdentifier_.isEmpty()) {
                codedOutputStream.writeString(1, getAppIdentifier());
            }
            if (getAttributesList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(18);
                codedOutputStream.writeUInt32NoTag(this.attributesMemoizedSerializedSize);
            }
            for (int i = 0; i < this.attributes_.size(); i++) {
                codedOutputStream.writeEnumNoTag(this.attributes_.getInt(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetCentralNotificationAppAttributes getCentralNotificationAppAttributes) {
            return DEFAULT_INSTANCE.createBuilder(getCentralNotificationAppAttributes);
        }

        public static GetCentralNotificationAppAttributes parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCentralNotificationAppAttributes parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetCentralNotificationAppAttributes parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetCentralNotificationAppAttributes parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetCentralNotificationAppAttributes parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetCentralNotificationAppAttributes parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetCentralNotificationAppAttributes parseFrom(InputStream inputStream) throws IOException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCentralNotificationAppAttributes parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCentralNotificationAppAttributes parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetCentralNotificationAppAttributes parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCentralNotificationAppAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetCentralNotificationAppAttributesOrBuilder extends MessageLiteOrBuilder {
        String getAppIdentifier();

        ByteString getAppIdentifierBytes();

        GetCentralNotificationAppAttributes.Attribute getAttributes(int i);

        int getAttributesCount();

        List<GetCentralNotificationAppAttributes.Attribute> getAttributesList();

        int getAttributesValue(int i);

        List<Integer> getAttributesValueList();
    }

    /* loaded from: classes6.dex */
    public static final class GetCentralNotificationAttributes extends GeneratedMessageLite<GetCentralNotificationAttributes, Builder> implements GetCentralNotificationAttributesOrBuilder {
        public static final int ATTRIBUTES_FIELD_NUMBER = 2;
        private static volatile Parser<GetCentralNotificationAttributes> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int attributesMemoizedSerializedSize;
        private Internal.IntList attributes_ = GeneratedMessageLite.emptyIntList();
        private int bitField0_;
        private int uid_;
        private static final Internal.ListAdapter.Converter<Integer, Attribute> attributes_converter_ = new Internal.ListAdapter.Converter<Integer, Attribute>() { // from class: com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributes.1
            @Override // com.google.protobuf.Internal.ListAdapter.Converter
            public Attribute convert(Integer num) {
                Attribute forNumber = Attribute.forNumber(num.intValue());
                return forNumber == null ? Attribute.UNRECOGNIZED : forNumber;
            }
        };
        private static final GetCentralNotificationAttributes DEFAULT_INSTANCE = new GetCentralNotificationAttributes();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetCentralNotificationAttributes, Builder> implements GetCentralNotificationAttributesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllAttributes(Iterable<? extends Attribute> iterable) {
                copyOnWrite();
                ((GetCentralNotificationAttributes) this.instance).addAllAttributes(iterable);
                return this;
            }

            public Builder addAllAttributesValue(Iterable<Integer> iterable) {
                copyOnWrite();
                ((GetCentralNotificationAttributes) this.instance).addAllAttributesValue(iterable);
                return this;
            }

            public Builder addAttributes(Attribute attribute) {
                copyOnWrite();
                ((GetCentralNotificationAttributes) this.instance).addAttributes(attribute);
                return this;
            }

            public Builder addAttributesValue(int i) {
                ((GetCentralNotificationAttributes) this.instance).addAttributesValue(i);
                return this;
            }

            public Builder clearAttributes() {
                copyOnWrite();
                ((GetCentralNotificationAttributes) this.instance).clearAttributes();
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((GetCentralNotificationAttributes) this.instance).clearUid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
            public Attribute getAttributes(int i) {
                return ((GetCentralNotificationAttributes) this.instance).getAttributes(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
            public int getAttributesCount() {
                return ((GetCentralNotificationAttributes) this.instance).getAttributesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
            public List<Attribute> getAttributesList() {
                return ((GetCentralNotificationAttributes) this.instance).getAttributesList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
            public int getAttributesValue(int i) {
                return ((GetCentralNotificationAttributes) this.instance).getAttributesValue(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
            public List<Integer> getAttributesValueList() {
                return Collections.unmodifiableList(((GetCentralNotificationAttributes) this.instance).getAttributesValueList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
            public int getUid() {
                return ((GetCentralNotificationAttributes) this.instance).getUid();
            }

            public Builder setAttributes(int i, Attribute attribute) {
                copyOnWrite();
                ((GetCentralNotificationAttributes) this.instance).setAttributes(i, attribute);
                return this;
            }

            public Builder setAttributesValue(int i, int i2) {
                copyOnWrite();
                ((GetCentralNotificationAttributes) this.instance).setAttributesValue(i, i2);
                return this;
            }

            public Builder setUid(int i) {
                copyOnWrite();
                ((GetCentralNotificationAttributes) this.instance).setUid(i);
                return this;
            }

            private Builder() {
                super(GetCentralNotificationAttributes.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetCentralNotificationAttributes() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAttributes(Iterable<? extends Attribute> iterable) {
            ensureAttributesIsMutable();
            for (Attribute attribute : iterable) {
                this.attributes_.addInt(attribute.getNumber());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAttributesValue(Iterable<Integer> iterable) {
            ensureAttributesIsMutable();
            for (Integer num : iterable) {
                this.attributes_.addInt(num.intValue());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttributes(Attribute attribute) {
            if (attribute != null) {
                ensureAttributesIsMutable();
                this.attributes_.addInt(attribute.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttributesValue(int i) {
            ensureAttributesIsMutable();
            this.attributes_.addInt(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAttributes() {
            this.attributes_ = GeneratedMessageLite.emptyIntList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUid() {
            this.uid_ = 0;
        }

        private void ensureAttributesIsMutable() {
            if (!this.attributes_.isModifiable()) {
                this.attributes_ = GeneratedMessageLite.mutableCopy(this.attributes_);
            }
        }

        public static GetCentralNotificationAttributes getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetCentralNotificationAttributes parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCentralNotificationAttributes parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetCentralNotificationAttributes> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAttributes(int i, Attribute attribute) {
            if (attribute != null) {
                ensureAttributesIsMutable();
                this.attributes_.setInt(i, attribute.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAttributesValue(int i, int i2) {
            ensureAttributesIsMutable();
            this.attributes_.setInt(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUid(int i) {
            this.uid_ = i;
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
                    GetCentralNotificationAttributes getCentralNotificationAttributes = (GetCentralNotificationAttributes) obj2;
                    boolean z2 = this.uid_ != 0;
                    int i = this.uid_;
                    if (getCentralNotificationAttributes.uid_ != 0) {
                        z = true;
                    }
                    this.uid_ = visitor.visitInt(z2, i, z, getCentralNotificationAttributes.uid_);
                    this.attributes_ = visitor.visitIntList(this.attributes_, getCentralNotificationAttributes.attributes_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= getCentralNotificationAttributes.bitField0_;
                    }
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
                                    this.uid_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    if (!this.attributes_.isModifiable()) {
                                        this.attributes_ = GeneratedMessageLite.mutableCopy(this.attributes_);
                                    }
                                    this.attributes_.addInt(codedInputStream.readEnum());
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    if (!this.attributes_.isModifiable()) {
                                        this.attributes_ = GeneratedMessageLite.mutableCopy(this.attributes_);
                                    }
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.attributes_.addInt(codedInputStream.readEnum());
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
                    this.attributes_.makeImmutable();
                    return null;
                case 6:
                    return new GetCentralNotificationAttributes();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetCentralNotificationAttributes.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
        public Attribute getAttributes(int i) {
            return attributes_converter_.convert(Integer.valueOf(this.attributes_.getInt(i)));
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
        public int getAttributesCount() {
            return this.attributes_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
        public List<Attribute> getAttributesList() {
            return new Internal.ListAdapter(this.attributes_, attributes_converter_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
        public int getAttributesValue(int i) {
            return this.attributes_.getInt(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
        public List<Integer> getAttributesValueList() {
            return this.attributes_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.uid_;
            int computeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) + 0 : 0;
            int i3 = 0;
            for (int i4 = 0; i4 < this.attributes_.size(); i4++) {
                i3 += CodedOutputStream.computeEnumSizeNoTag(this.attributes_.getInt(i4));
            }
            int i5 = computeUInt32Size + i3;
            if (!getAttributesList().isEmpty()) {
                i5 = i5 + 1 + CodedOutputStream.computeUInt32SizeNoTag(i3);
            }
            this.attributesMemoizedSerializedSize = i3;
            int serializedSize = this.unknownFields.getSerializedSize() + i5;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.GetCentralNotificationAttributesOrBuilder
        public int getUid() {
            return this.uid_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            int i = this.uid_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (getAttributesList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(18);
                codedOutputStream.writeUInt32NoTag(this.attributesMemoizedSerializedSize);
            }
            for (int i2 = 0; i2 < this.attributes_.size(); i2++) {
                codedOutputStream.writeEnumNoTag(this.attributes_.getInt(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetCentralNotificationAttributes getCentralNotificationAttributes) {
            return DEFAULT_INSTANCE.createBuilder(getCentralNotificationAttributes);
        }

        public static GetCentralNotificationAttributes parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCentralNotificationAttributes parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetCentralNotificationAttributes parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetCentralNotificationAttributes parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetCentralNotificationAttributes parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetCentralNotificationAttributes parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetCentralNotificationAttributes parseFrom(InputStream inputStream) throws IOException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCentralNotificationAttributes parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCentralNotificationAttributes parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetCentralNotificationAttributes parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetCentralNotificationAttributesOrBuilder extends MessageLiteOrBuilder {
        Attribute getAttributes(int i);

        int getAttributesCount();

        List<Attribute> getAttributesList();

        int getAttributesValue(int i);

        List<Integer> getAttributesValueList();

        int getUid();
    }

    /* loaded from: classes6.dex */
    public static final class NotificationCenterInformation extends GeneratedMessageLite<NotificationCenterInformation, Builder> implements NotificationCenterInformationOrBuilder {
        private static final NotificationCenterInformation DEFAULT_INSTANCE = new NotificationCenterInformation();
        private static volatile Parser<NotificationCenterInformation> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotificationCenterInformation, Builder> implements NotificationCenterInformationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(NotificationCenterInformation.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotificationCenterInformation() {
        }

        public static NotificationCenterInformation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotificationCenterInformation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotificationCenterInformation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotificationCenterInformation> parser() {
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
                    NotificationCenterInformation notificationCenterInformation = (NotificationCenterInformation) obj2;
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
                    return new NotificationCenterInformation();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotificationCenterInformation.class) {
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

        public static Builder newBuilder(NotificationCenterInformation notificationCenterInformation) {
            return DEFAULT_INSTANCE.createBuilder(notificationCenterInformation);
        }

        public static NotificationCenterInformation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotificationCenterInformation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotificationCenterInformation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static NotificationCenterInformation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NotificationCenterInformation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotificationCenterInformation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotificationCenterInformation parseFrom(InputStream inputStream) throws IOException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotificationCenterInformation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotificationCenterInformation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotificationCenterInformation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationCenterInformation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotificationCenterInformationOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class PerformCentralNotificationAction extends GeneratedMessageLite<PerformCentralNotificationAction, Builder> implements PerformCentralNotificationActionOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 2;
        private static final PerformCentralNotificationAction DEFAULT_INSTANCE = new PerformCentralNotificationAction();
        private static volatile Parser<PerformCentralNotificationAction> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int action_;
        private int uid_;

        /* loaded from: classes6.dex */
        public enum Action implements Internal.EnumLite {
            POSITIVE(0),
            NEGATIVE(1),
            UNRECOGNIZED(-1);
            
            public static final int NEGATIVE_VALUE = 1;
            public static final int POSITIVE_VALUE = 0;
            private static final Internal.EnumLiteMap<Action> internalValueMap = new Internal.EnumLiteMap<Action>() { // from class: com.amazon.alexa.accessory.protocol.Ancs.PerformCentralNotificationAction.Action.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public Action mo9850findValueByNumber(int i) {
                    return Action.forNumber(i);
                }
            };
            private final int value;

            Action(int i) {
                this.value = i;
            }

            public static Action forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return NEGATIVE;
                    }
                    return null;
                }
                return POSITIVE;
            }

            public static Internal.EnumLiteMap<Action> internalGetValueMap() {
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
            public static Action valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<PerformCentralNotificationAction, Builder> implements PerformCentralNotificationActionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((PerformCentralNotificationAction) this.instance).clearAction();
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((PerformCentralNotificationAction) this.instance).clearUid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PerformCentralNotificationActionOrBuilder
            public Action getAction() {
                return ((PerformCentralNotificationAction) this.instance).getAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PerformCentralNotificationActionOrBuilder
            public int getActionValue() {
                return ((PerformCentralNotificationAction) this.instance).getActionValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PerformCentralNotificationActionOrBuilder
            public int getUid() {
                return ((PerformCentralNotificationAction) this.instance).getUid();
            }

            public Builder setAction(Action action) {
                copyOnWrite();
                ((PerformCentralNotificationAction) this.instance).setAction(action);
                return this;
            }

            public Builder setActionValue(int i) {
                copyOnWrite();
                ((PerformCentralNotificationAction) this.instance).setActionValue(i);
                return this;
            }

            public Builder setUid(int i) {
                copyOnWrite();
                ((PerformCentralNotificationAction) this.instance).setUid(i);
                return this;
            }

            private Builder() {
                super(PerformCentralNotificationAction.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private PerformCentralNotificationAction() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAction() {
            this.action_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUid() {
            this.uid_ = 0;
        }

        public static PerformCentralNotificationAction getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static PerformCentralNotificationAction parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PerformCentralNotificationAction parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<PerformCentralNotificationAction> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAction(Action action) {
            if (action != null) {
                this.action_ = action.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActionValue(int i) {
            this.action_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUid(int i) {
            this.uid_ = i;
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
                    PerformCentralNotificationAction performCentralNotificationAction = (PerformCentralNotificationAction) obj2;
                    this.uid_ = visitor.visitInt(this.uid_ != 0, this.uid_, performCentralNotificationAction.uid_ != 0, performCentralNotificationAction.uid_);
                    boolean z2 = this.action_ != 0;
                    int i = this.action_;
                    if (performCentralNotificationAction.action_ != 0) {
                        z = true;
                    }
                    this.action_ = visitor.visitInt(z2, i, z, performCentralNotificationAction.action_);
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
                                    this.uid_ = codedInputStream.readUInt32();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.action_ = codedInputStream.readEnum();
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
                    return new PerformCentralNotificationAction();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (PerformCentralNotificationAction.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PerformCentralNotificationActionOrBuilder
        public Action getAction() {
            Action forNumber = Action.forNumber(this.action_);
            return forNumber == null ? Action.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PerformCentralNotificationActionOrBuilder
        public int getActionValue() {
            return this.action_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.uid_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (this.action_ != Action.POSITIVE.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.action_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PerformCentralNotificationActionOrBuilder
        public int getUid() {
            return this.uid_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.uid_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.action_ != Action.POSITIVE.getNumber()) {
                codedOutputStream.writeEnum(2, this.action_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(PerformCentralNotificationAction performCentralNotificationAction) {
            return DEFAULT_INSTANCE.createBuilder(performCentralNotificationAction);
        }

        public static PerformCentralNotificationAction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PerformCentralNotificationAction parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static PerformCentralNotificationAction parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static PerformCentralNotificationAction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PerformCentralNotificationAction parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PerformCentralNotificationAction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PerformCentralNotificationAction parseFrom(InputStream inputStream) throws IOException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PerformCentralNotificationAction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PerformCentralNotificationAction parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PerformCentralNotificationAction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PerformCentralNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface PerformCentralNotificationActionOrBuilder extends MessageLiteOrBuilder {
        PerformCentralNotificationAction.Action getAction();

        int getActionValue();

        int getUid();
    }

    /* loaded from: classes6.dex */
    public static final class PublishCentralNotification extends GeneratedMessageLite<PublishCentralNotification, Builder> implements PublishCentralNotificationOrBuilder {
        public static final int CATEGORY_COUNT_FIELD_NUMBER = 2;
        public static final int CATEGORY_FIELD_NUMBER = 3;
        private static final PublishCentralNotification DEFAULT_INSTANCE = new PublishCentralNotification();
        public static final int EVENT_FIELD_NUMBER = 4;
        public static final int FLAGS_FIELD_NUMBER = 5;
        private static volatile Parser<PublishCentralNotification> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int categoryCount_;
        private int category_;
        private int event_;
        private Flags flags_;
        private int uid_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<PublishCentralNotification, Builder> implements PublishCentralNotificationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).clearCategory();
                return this;
            }

            public Builder clearCategoryCount() {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).clearCategoryCount();
                return this;
            }

            public Builder clearEvent() {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).clearEvent();
                return this;
            }

            public Builder clearFlags() {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).clearFlags();
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).clearUid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
            public Category getCategory() {
                return ((PublishCentralNotification) this.instance).getCategory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
            public int getCategoryCount() {
                return ((PublishCentralNotification) this.instance).getCategoryCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
            public int getCategoryValue() {
                return ((PublishCentralNotification) this.instance).getCategoryValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
            public Event getEvent() {
                return ((PublishCentralNotification) this.instance).getEvent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
            public int getEventValue() {
                return ((PublishCentralNotification) this.instance).getEventValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
            public Flags getFlags() {
                return ((PublishCentralNotification) this.instance).getFlags();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
            public int getUid() {
                return ((PublishCentralNotification) this.instance).getUid();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
            public boolean hasFlags() {
                return ((PublishCentralNotification) this.instance).hasFlags();
            }

            public Builder mergeFlags(Flags flags) {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).mergeFlags(flags);
                return this;
            }

            public Builder setCategory(Category category) {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).setCategory(category);
                return this;
            }

            public Builder setCategoryCount(int i) {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).setCategoryCount(i);
                return this;
            }

            public Builder setCategoryValue(int i) {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).setCategoryValue(i);
                return this;
            }

            public Builder setEvent(Event event) {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).setEvent(event);
                return this;
            }

            public Builder setEventValue(int i) {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).setEventValue(i);
                return this;
            }

            public Builder setFlags(Flags flags) {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).setFlags(flags);
                return this;
            }

            public Builder setUid(int i) {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).setUid(i);
                return this;
            }

            private Builder() {
                super(PublishCentralNotification.DEFAULT_INSTANCE);
            }

            public Builder setFlags(Flags.Builder builder) {
                copyOnWrite();
                ((PublishCentralNotification) this.instance).setFlags(builder);
                return this;
            }
        }

        /* loaded from: classes6.dex */
        public enum Category implements Internal.EnumLite {
            OTHER(0),
            INCOMING_CALL(1),
            MISSED_CALL(2),
            VOICE_MAIL(3),
            SOCIAL(4),
            SCHEDULE(5),
            EMAIL(6),
            NEWS(7),
            HEALTH_AND_FITNESS(8),
            BUSINESS_AND_FINANCE(9),
            LOCATION(10),
            ENTERTAINMENT(11),
            UNRECOGNIZED(-1);
            
            public static final int BUSINESS_AND_FINANCE_VALUE = 9;
            public static final int EMAIL_VALUE = 6;
            public static final int ENTERTAINMENT_VALUE = 11;
            public static final int HEALTH_AND_FITNESS_VALUE = 8;
            public static final int INCOMING_CALL_VALUE = 1;
            public static final int LOCATION_VALUE = 10;
            public static final int MISSED_CALL_VALUE = 2;
            public static final int NEWS_VALUE = 7;
            public static final int OTHER_VALUE = 0;
            public static final int SCHEDULE_VALUE = 5;
            public static final int SOCIAL_VALUE = 4;
            public static final int VOICE_MAIL_VALUE = 3;
            private static final Internal.EnumLiteMap<Category> internalValueMap = new Internal.EnumLiteMap<Category>() { // from class: com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.Category.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public Category mo9850findValueByNumber(int i) {
                    return Category.forNumber(i);
                }
            };
            private final int value;

            Category(int i) {
                this.value = i;
            }

            public static Category forNumber(int i) {
                switch (i) {
                    case 0:
                        return OTHER;
                    case 1:
                        return INCOMING_CALL;
                    case 2:
                        return MISSED_CALL;
                    case 3:
                        return VOICE_MAIL;
                    case 4:
                        return SOCIAL;
                    case 5:
                        return SCHEDULE;
                    case 6:
                        return EMAIL;
                    case 7:
                        return NEWS;
                    case 8:
                        return HEALTH_AND_FITNESS;
                    case 9:
                        return BUSINESS_AND_FINANCE;
                    case 10:
                        return LOCATION;
                    case 11:
                        return ENTERTAINMENT;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Category> internalGetValueMap() {
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
            public static Category valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public enum Event implements Internal.EnumLite {
            ADDED(0),
            MODIFIED(1),
            REMOVED(2),
            UNRECOGNIZED(-1);
            
            public static final int ADDED_VALUE = 0;
            public static final int MODIFIED_VALUE = 1;
            public static final int REMOVED_VALUE = 2;
            private static final Internal.EnumLiteMap<Event> internalValueMap = new Internal.EnumLiteMap<Event>() { // from class: com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.Event.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public Event mo9850findValueByNumber(int i) {
                    return Event.forNumber(i);
                }
            };
            private final int value;

            Event(int i) {
                this.value = i;
            }

            public static Event forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return MODIFIED;
                    }
                    if (i == 2) {
                        return REMOVED;
                    }
                    return null;
                }
                return ADDED;
            }

            public static Internal.EnumLiteMap<Event> internalGetValueMap() {
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
            public static Event valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public static final class Flags extends GeneratedMessageLite<Flags, Builder> implements FlagsOrBuilder {
            private static final Flags DEFAULT_INSTANCE = new Flags();
            public static final int IMPORTANT_FIELD_NUMBER = 2;
            public static final int NEGATIVE_ACTION_FIELD_NUMBER = 5;
            private static volatile Parser<Flags> PARSER = null;
            public static final int POSITIVE_ACTION_FIELD_NUMBER = 4;
            public static final int PRE_EXISTING_FIELD_NUMBER = 3;
            public static final int SILENT_FIELD_NUMBER = 1;
            private boolean important_;
            private boolean negativeAction_;
            private boolean positiveAction_;
            private boolean preExisting_;
            private boolean silent_;

            /* loaded from: classes6.dex */
            public static final class Builder extends GeneratedMessageLite.Builder<Flags, Builder> implements FlagsOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    this();
                }

                public Builder clearImportant() {
                    copyOnWrite();
                    ((Flags) this.instance).clearImportant();
                    return this;
                }

                public Builder clearNegativeAction() {
                    copyOnWrite();
                    ((Flags) this.instance).clearNegativeAction();
                    return this;
                }

                public Builder clearPositiveAction() {
                    copyOnWrite();
                    ((Flags) this.instance).clearPositiveAction();
                    return this;
                }

                public Builder clearPreExisting() {
                    copyOnWrite();
                    ((Flags) this.instance).clearPreExisting();
                    return this;
                }

                public Builder clearSilent() {
                    copyOnWrite();
                    ((Flags) this.instance).clearSilent();
                    return this;
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
                public boolean getImportant() {
                    return ((Flags) this.instance).getImportant();
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
                public boolean getNegativeAction() {
                    return ((Flags) this.instance).getNegativeAction();
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
                public boolean getPositiveAction() {
                    return ((Flags) this.instance).getPositiveAction();
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
                public boolean getPreExisting() {
                    return ((Flags) this.instance).getPreExisting();
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
                public boolean getSilent() {
                    return ((Flags) this.instance).getSilent();
                }

                public Builder setImportant(boolean z) {
                    copyOnWrite();
                    ((Flags) this.instance).setImportant(z);
                    return this;
                }

                public Builder setNegativeAction(boolean z) {
                    copyOnWrite();
                    ((Flags) this.instance).setNegativeAction(z);
                    return this;
                }

                public Builder setPositiveAction(boolean z) {
                    copyOnWrite();
                    ((Flags) this.instance).setPositiveAction(z);
                    return this;
                }

                public Builder setPreExisting(boolean z) {
                    copyOnWrite();
                    ((Flags) this.instance).setPreExisting(z);
                    return this;
                }

                public Builder setSilent(boolean z) {
                    copyOnWrite();
                    ((Flags) this.instance).setSilent(z);
                    return this;
                }

                private Builder() {
                    super(Flags.DEFAULT_INSTANCE);
                }
            }

            static {
                DEFAULT_INSTANCE.makeImmutable();
            }

            private Flags() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearImportant() {
                this.important_ = false;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearNegativeAction() {
                this.negativeAction_ = false;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearPositiveAction() {
                this.positiveAction_ = false;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearPreExisting() {
                this.preExisting_ = false;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearSilent() {
                this.silent_ = false;
            }

            public static Flags getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Flags parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Flags) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Flags parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<Flags> parser() {
                return DEFAULT_INSTANCE.mo9954getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setImportant(boolean z) {
                this.important_ = z;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setNegativeAction(boolean z) {
                this.negativeAction_ = z;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setPositiveAction(boolean z) {
                this.positiveAction_ = z;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setPreExisting(boolean z) {
                this.preExisting_ = z;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setSilent(boolean z) {
                this.silent_ = z;
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                switch (methodToInvoke.ordinal()) {
                    case 0:
                        return DEFAULT_INSTANCE;
                    case 1:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                        Flags flags = (Flags) obj2;
                        boolean z = this.silent_;
                        boolean z2 = flags.silent_;
                        this.silent_ = visitor.visitBoolean(z, z, z2, z2);
                        boolean z3 = this.important_;
                        boolean z4 = flags.important_;
                        this.important_ = visitor.visitBoolean(z3, z3, z4, z4);
                        boolean z5 = this.preExisting_;
                        boolean z6 = flags.preExisting_;
                        this.preExisting_ = visitor.visitBoolean(z5, z5, z6, z6);
                        boolean z7 = this.positiveAction_;
                        boolean z8 = flags.positiveAction_;
                        this.positiveAction_ = visitor.visitBoolean(z7, z7, z8, z8);
                        boolean z9 = this.negativeAction_;
                        boolean z10 = flags.negativeAction_;
                        this.negativeAction_ = visitor.visitBoolean(z9, z9, z10, z10);
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
                        boolean z11 = false;
                        while (!z11) {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.silent_ = codedInputStream.readBool();
                                    } else if (readTag == 16) {
                                        this.important_ = codedInputStream.readBool();
                                    } else if (readTag == 24) {
                                        this.preExisting_ = codedInputStream.readBool();
                                    } else if (readTag == 32) {
                                        this.positiveAction_ = codedInputStream.readBool();
                                    } else if (readTag != 40) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.negativeAction_ = codedInputStream.readBool();
                                    }
                                }
                                z11 = true;
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
                        return new Flags();
                    case 7:
                        return new Builder(null);
                    case 8:
                        break;
                    case 9:
                        if (PARSER == null) {
                            synchronized (Flags.class) {
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

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
            public boolean getImportant() {
                return this.important_;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
            public boolean getNegativeAction() {
                return this.negativeAction_;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
            public boolean getPositiveAction() {
                return this.positiveAction_;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
            public boolean getPreExisting() {
                return this.preExisting_;
            }

            @Override // com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                boolean z = this.silent_;
                if (z) {
                    i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
                }
                boolean z2 = this.important_;
                if (z2) {
                    i2 += CodedOutputStream.computeBoolSize(2, z2);
                }
                boolean z3 = this.preExisting_;
                if (z3) {
                    i2 += CodedOutputStream.computeBoolSize(3, z3);
                }
                boolean z4 = this.positiveAction_;
                if (z4) {
                    i2 += CodedOutputStream.computeBoolSize(4, z4);
                }
                boolean z5 = this.negativeAction_;
                if (z5) {
                    i2 += CodedOutputStream.computeBoolSize(5, z5);
                }
                int serializedSize = this.unknownFields.getSerializedSize() + i2;
                this.memoizedSerializedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotification.FlagsOrBuilder
            public boolean getSilent() {
                return this.silent_;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                boolean z = this.silent_;
                if (z) {
                    codedOutputStream.writeBool(1, z);
                }
                boolean z2 = this.important_;
                if (z2) {
                    codedOutputStream.writeBool(2, z2);
                }
                boolean z3 = this.preExisting_;
                if (z3) {
                    codedOutputStream.writeBool(3, z3);
                }
                boolean z4 = this.positiveAction_;
                if (z4) {
                    codedOutputStream.writeBool(4, z4);
                }
                boolean z5 = this.negativeAction_;
                if (z5) {
                    codedOutputStream.writeBool(5, z5);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public static Builder newBuilder(Flags flags) {
                return DEFAULT_INSTANCE.createBuilder(flags);
            }

            public static Flags parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Flags) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Flags parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Flags parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Flags parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Flags parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Flags parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Flags parseFrom(InputStream inputStream) throws IOException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Flags parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Flags parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Flags parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Flags) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes6.dex */
        public interface FlagsOrBuilder extends MessageLiteOrBuilder {
            boolean getImportant();

            boolean getNegativeAction();

            boolean getPositiveAction();

            boolean getPreExisting();

            boolean getSilent();
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private PublishCentralNotification() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCategoryCount() {
            this.categoryCount_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEvent() {
            this.event_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFlags() {
            this.flags_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUid() {
            this.uid_ = 0;
        }

        public static PublishCentralNotification getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFlags(Flags flags) {
            Flags flags2 = this.flags_;
            if (flags2 != null && flags2 != Flags.getDefaultInstance()) {
                this.flags_ = Flags.newBuilder(this.flags_).mergeFrom((Flags.Builder) flags).mo10085buildPartial();
            } else {
                this.flags_ = flags;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static PublishCentralNotification parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PublishCentralNotification) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PublishCentralNotification parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<PublishCentralNotification> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategory(Category category) {
            if (category != null) {
                this.category_ = category.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategoryCount(int i) {
            this.categoryCount_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCategoryValue(int i) {
            this.category_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEvent(Event event) {
            if (event != null) {
                this.event_ = event.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEventValue(int i) {
            this.event_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFlags(Flags flags) {
            if (flags != null) {
                this.flags_ = flags;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUid(int i) {
            this.uid_ = i;
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
                    PublishCentralNotification publishCentralNotification = (PublishCentralNotification) obj2;
                    this.uid_ = visitor.visitInt(this.uid_ != 0, this.uid_, publishCentralNotification.uid_ != 0, publishCentralNotification.uid_);
                    this.categoryCount_ = visitor.visitInt(this.categoryCount_ != 0, this.categoryCount_, publishCentralNotification.categoryCount_ != 0, publishCentralNotification.categoryCount_);
                    this.category_ = visitor.visitInt(this.category_ != 0, this.category_, publishCentralNotification.category_ != 0, publishCentralNotification.category_);
                    boolean z2 = this.event_ != 0;
                    int i = this.event_;
                    if (publishCentralNotification.event_ != 0) {
                        z = true;
                    }
                    this.event_ = visitor.visitInt(z2, i, z, publishCentralNotification.event_);
                    this.flags_ = (Flags) visitor.visitMessage(this.flags_, publishCentralNotification.flags_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.uid_ = codedInputStream.readUInt32();
                                    } else if (readTag == 16) {
                                        this.categoryCount_ = codedInputStream.readUInt32();
                                    } else if (readTag == 24) {
                                        this.category_ = codedInputStream.readEnum();
                                    } else if (readTag == 32) {
                                        this.event_ = codedInputStream.readEnum();
                                    } else if (readTag != 42) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        Flags.Builder mo10081toBuilder = this.flags_ != null ? this.flags_.mo10081toBuilder() : null;
                                        this.flags_ = (Flags) codedInputStream.readMessage(Flags.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder != null) {
                                            mo10081toBuilder.mergeFrom((Flags.Builder) this.flags_);
                                            this.flags_ = mo10081toBuilder.mo10085buildPartial();
                                        }
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
                    return new PublishCentralNotification();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (PublishCentralNotification.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
        public Category getCategory() {
            Category forNumber = Category.forNumber(this.category_);
            return forNumber == null ? Category.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
        public int getCategoryCount() {
            return this.categoryCount_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
        public int getCategoryValue() {
            return this.category_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
        public Event getEvent() {
            Event forNumber = Event.forNumber(this.event_);
            return forNumber == null ? Event.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
        public int getEventValue() {
            return this.event_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
        public Flags getFlags() {
            Flags flags = this.flags_;
            return flags == null ? Flags.getDefaultInstance() : flags;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.uid_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int i4 = this.categoryCount_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
            }
            if (this.category_ != Category.OTHER.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.category_);
            }
            if (this.event_ != Event.ADDED.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(4, this.event_);
            }
            if (this.flags_ != null) {
                i2 += CodedOutputStream.computeMessageSize(5, getFlags());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
        public int getUid() {
            return this.uid_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.PublishCentralNotificationOrBuilder
        public boolean hasFlags() {
            return this.flags_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.uid_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.categoryCount_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            if (this.category_ != Category.OTHER.getNumber()) {
                codedOutputStream.writeEnum(3, this.category_);
            }
            if (this.event_ != Event.ADDED.getNumber()) {
                codedOutputStream.writeEnum(4, this.event_);
            }
            if (this.flags_ != null) {
                codedOutputStream.writeMessage(5, getFlags());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(PublishCentralNotification publishCentralNotification) {
            return DEFAULT_INSTANCE.createBuilder(publishCentralNotification);
        }

        public static PublishCentralNotification parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PublishCentralNotification) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PublishCentralNotification parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static PublishCentralNotification parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFlags(Flags.Builder builder) {
            this.flags_ = builder.mo10084build();
        }

        public static PublishCentralNotification parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PublishCentralNotification parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PublishCentralNotification parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PublishCentralNotification parseFrom(InputStream inputStream) throws IOException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PublishCentralNotification parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PublishCentralNotification parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PublishCentralNotification parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PublishCentralNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface PublishCentralNotificationOrBuilder extends MessageLiteOrBuilder {
        PublishCentralNotification.Category getCategory();

        int getCategoryCount();

        int getCategoryValue();

        PublishCentralNotification.Event getEvent();

        int getEventValue();

        PublishCentralNotification.Flags getFlags();

        int getUid();

        boolean hasFlags();
    }

    /* loaded from: classes6.dex */
    public static final class SubscribeNotificationCenter extends GeneratedMessageLite<SubscribeNotificationCenter, Builder> implements SubscribeNotificationCenterOrBuilder {
        private static final SubscribeNotificationCenter DEFAULT_INSTANCE = new SubscribeNotificationCenter();
        private static volatile Parser<SubscribeNotificationCenter> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SubscribeNotificationCenter, Builder> implements SubscribeNotificationCenterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(SubscribeNotificationCenter.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SubscribeNotificationCenter() {
        }

        public static SubscribeNotificationCenter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SubscribeNotificationCenter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SubscribeNotificationCenter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SubscribeNotificationCenter> parser() {
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
                    SubscribeNotificationCenter subscribeNotificationCenter = (SubscribeNotificationCenter) obj2;
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
                    return new SubscribeNotificationCenter();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SubscribeNotificationCenter.class) {
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

        public static Builder newBuilder(SubscribeNotificationCenter subscribeNotificationCenter) {
            return DEFAULT_INSTANCE.createBuilder(subscribeNotificationCenter);
        }

        public static SubscribeNotificationCenter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SubscribeNotificationCenter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SubscribeNotificationCenter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SubscribeNotificationCenter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SubscribeNotificationCenter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SubscribeNotificationCenter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SubscribeNotificationCenter parseFrom(InputStream inputStream) throws IOException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SubscribeNotificationCenter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SubscribeNotificationCenter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SubscribeNotificationCenter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SubscribeNotificationCenterOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class UnsubscribeNotificationCenter extends GeneratedMessageLite<UnsubscribeNotificationCenter, Builder> implements UnsubscribeNotificationCenterOrBuilder {
        private static final UnsubscribeNotificationCenter DEFAULT_INSTANCE = new UnsubscribeNotificationCenter();
        private static volatile Parser<UnsubscribeNotificationCenter> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UnsubscribeNotificationCenter, Builder> implements UnsubscribeNotificationCenterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(UnsubscribeNotificationCenter.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UnsubscribeNotificationCenter() {
        }

        public static UnsubscribeNotificationCenter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UnsubscribeNotificationCenter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnsubscribeNotificationCenter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UnsubscribeNotificationCenter> parser() {
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
                    UnsubscribeNotificationCenter unsubscribeNotificationCenter = (UnsubscribeNotificationCenter) obj2;
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
                    return new UnsubscribeNotificationCenter();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UnsubscribeNotificationCenter.class) {
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

        public static Builder newBuilder(UnsubscribeNotificationCenter unsubscribeNotificationCenter) {
            return DEFAULT_INSTANCE.createBuilder(unsubscribeNotificationCenter);
        }

        public static UnsubscribeNotificationCenter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnsubscribeNotificationCenter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UnsubscribeNotificationCenter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UnsubscribeNotificationCenter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UnsubscribeNotificationCenter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UnsubscribeNotificationCenter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UnsubscribeNotificationCenter parseFrom(InputStream inputStream) throws IOException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnsubscribeNotificationCenter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnsubscribeNotificationCenter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UnsubscribeNotificationCenter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnsubscribeNotificationCenter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UnsubscribeNotificationCenterOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class UpdateCentralNotificationAttributes extends GeneratedMessageLite<UpdateCentralNotificationAttributes, Builder> implements UpdateCentralNotificationAttributesOrBuilder {
        public static final int ATTRIBUTEVALUE_FIELD_NUMBER = 4;
        private static final UpdateCentralNotificationAttributes DEFAULT_INSTANCE = new UpdateCentralNotificationAttributes();
        public static final int HAS_MORE_FIELD_NUMBER = 3;
        public static final int ORDER_FIELD_NUMBER = 2;
        private static volatile Parser<UpdateCentralNotificationAttributes> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private Internal.ProtobufList<AttributeValue> attributeValue_ = GeneratedMessageLite.emptyProtobufList();
        private int bitField0_;
        private boolean hasMore_;
        private int order_;
        private int uid_;

        /* loaded from: classes6.dex */
        public static final class AttributeValue extends GeneratedMessageLite<AttributeValue, Builder> implements AttributeValueOrBuilder {
            public static final int ACTION_FIELD_NUMBER = 3;
            public static final int ATTRIBUTE_FIELD_NUMBER = 1;
            private static final AttributeValue DEFAULT_INSTANCE = new AttributeValue();
            private static volatile Parser<AttributeValue> PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 2;
            private int action_;
            private int attribute_;
            private ByteString value_ = ByteString.EMPTY;

            /* loaded from: classes6.dex */
            public enum Action implements Internal.EnumLite {
                REPLACE(0),
                APPEND(1),
                UNRECOGNIZED(-1);
                
                public static final int APPEND_VALUE = 1;
                public static final int REPLACE_VALUE = 0;
                private static final Internal.EnumLiteMap<Action> internalValueMap = new Internal.EnumLiteMap<Action>() { // from class: com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValue.Action.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    /* renamed from: findValueByNumber */
                    public Action mo9850findValueByNumber(int i) {
                        return Action.forNumber(i);
                    }
                };
                private final int value;

                Action(int i) {
                    this.value = i;
                }

                public static Action forNumber(int i) {
                    if (i != 0) {
                        if (i == 1) {
                            return APPEND;
                        }
                        return null;
                    }
                    return REPLACE;
                }

                public static Internal.EnumLiteMap<Action> internalGetValueMap() {
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
                public static Action valueOf(int i) {
                    return forNumber(i);
                }
            }

            /* loaded from: classes6.dex */
            public static final class Builder extends GeneratedMessageLite.Builder<AttributeValue, Builder> implements AttributeValueOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    this();
                }

                public Builder clearAction() {
                    copyOnWrite();
                    ((AttributeValue) this.instance).clearAction();
                    return this;
                }

                public Builder clearAttribute() {
                    copyOnWrite();
                    ((AttributeValue) this.instance).clearAttribute();
                    return this;
                }

                public Builder clearValue() {
                    copyOnWrite();
                    ((AttributeValue) this.instance).clearValue();
                    return this;
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
                public Action getAction() {
                    return ((AttributeValue) this.instance).getAction();
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
                public int getActionValue() {
                    return ((AttributeValue) this.instance).getActionValue();
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
                public Attribute getAttribute() {
                    return ((AttributeValue) this.instance).getAttribute();
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
                public int getAttributeValue() {
                    return ((AttributeValue) this.instance).getAttributeValue();
                }

                @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
                public ByteString getValue() {
                    return ((AttributeValue) this.instance).getValue();
                }

                public Builder setAction(Action action) {
                    copyOnWrite();
                    ((AttributeValue) this.instance).setAction(action);
                    return this;
                }

                public Builder setActionValue(int i) {
                    copyOnWrite();
                    ((AttributeValue) this.instance).setActionValue(i);
                    return this;
                }

                public Builder setAttribute(Attribute attribute) {
                    copyOnWrite();
                    ((AttributeValue) this.instance).setAttribute(attribute);
                    return this;
                }

                public Builder setAttributeValue(int i) {
                    copyOnWrite();
                    ((AttributeValue) this.instance).setAttributeValue(i);
                    return this;
                }

                public Builder setValue(ByteString byteString) {
                    copyOnWrite();
                    ((AttributeValue) this.instance).setValue(byteString);
                    return this;
                }

                private Builder() {
                    super(AttributeValue.DEFAULT_INSTANCE);
                }
            }

            static {
                DEFAULT_INSTANCE.makeImmutable();
            }

            private AttributeValue() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearAction() {
                this.action_ = 0;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearAttribute() {
                this.attribute_ = 0;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearValue() {
                this.value_ = getDefaultInstance().getValue();
            }

            public static AttributeValue getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static AttributeValue parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (AttributeValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static AttributeValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Parser<AttributeValue> parser() {
                return DEFAULT_INSTANCE.mo9954getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setAction(Action action) {
                if (action != null) {
                    this.action_ = action.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setActionValue(int i) {
                this.action_ = i;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setAttribute(Attribute attribute) {
                if (attribute != null) {
                    this.attribute_ = attribute.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setAttributeValue(int i) {
                this.attribute_ = i;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setValue(ByteString byteString) {
                if (byteString != null) {
                    this.value_ = byteString;
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
                        AttributeValue attributeValue = (AttributeValue) obj2;
                        this.attribute_ = visitor.visitInt(this.attribute_ != 0, this.attribute_, attributeValue.attribute_ != 0, attributeValue.attribute_);
                        this.value_ = visitor.visitByteString(this.value_ != ByteString.EMPTY, this.value_, attributeValue.value_ != ByteString.EMPTY, attributeValue.value_);
                        boolean z2 = this.action_ != 0;
                        int i = this.action_;
                        if (attributeValue.action_ != 0) {
                            z = true;
                        }
                        this.action_ = visitor.visitInt(z2, i, z, attributeValue.action_);
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
                                        this.attribute_ = codedInputStream.readEnum();
                                    } else if (readTag == 18) {
                                        this.value_ = codedInputStream.readBytes();
                                    } else if (readTag != 24) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.action_ = codedInputStream.readEnum();
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
                        return new AttributeValue();
                    case 7:
                        return new Builder(null);
                    case 8:
                        break;
                    case 9:
                        if (PARSER == null) {
                            synchronized (AttributeValue.class) {
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

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
            public Action getAction() {
                Action forNumber = Action.forNumber(this.action_);
                return forNumber == null ? Action.UNRECOGNIZED : forNumber;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
            public int getActionValue() {
                return this.action_;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
            public Attribute getAttribute() {
                Attribute forNumber = Attribute.forNumber(this.attribute_);
                return forNumber == null ? Attribute.UNRECOGNIZED : forNumber;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
            public int getAttributeValue() {
                return this.attribute_;
            }

            @Override // com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if (this.attribute_ != Attribute.APP_IDENTIFIER.getNumber()) {
                    i2 = 0 + CodedOutputStream.computeEnumSize(1, this.attribute_);
                }
                if (!this.value_.isEmpty()) {
                    i2 += CodedOutputStream.computeBytesSize(2, this.value_);
                }
                if (this.action_ != Action.REPLACE.getNumber()) {
                    i2 += CodedOutputStream.computeEnumSize(3, this.action_);
                }
                int serializedSize = this.unknownFields.getSerializedSize() + i2;
                this.memoizedSerializedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributes.AttributeValueOrBuilder
            public ByteString getValue() {
                return this.value_;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (this.attribute_ != Attribute.APP_IDENTIFIER.getNumber()) {
                    codedOutputStream.writeEnum(1, this.attribute_);
                }
                if (!this.value_.isEmpty()) {
                    codedOutputStream.writeBytes(2, this.value_);
                }
                if (this.action_ != Action.REPLACE.getNumber()) {
                    codedOutputStream.writeEnum(3, this.action_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public static Builder newBuilder(AttributeValue attributeValue) {
                return DEFAULT_INSTANCE.createBuilder(attributeValue);
            }

            public static AttributeValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (AttributeValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static AttributeValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static AttributeValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static AttributeValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static AttributeValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static AttributeValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static AttributeValue parseFrom(InputStream inputStream) throws IOException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static AttributeValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static AttributeValue parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static AttributeValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (AttributeValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes6.dex */
        public interface AttributeValueOrBuilder extends MessageLiteOrBuilder {
            AttributeValue.Action getAction();

            int getActionValue();

            Attribute getAttribute();

            int getAttributeValue();

            ByteString getValue();
        }

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UpdateCentralNotificationAttributes, Builder> implements UpdateCentralNotificationAttributesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllAttributeValue(Iterable<? extends AttributeValue> iterable) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).addAllAttributeValue(iterable);
                return this;
            }

            public Builder addAttributeValue(AttributeValue attributeValue) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).addAttributeValue(attributeValue);
                return this;
            }

            public Builder clearAttributeValue() {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).clearAttributeValue();
                return this;
            }

            public Builder clearHasMore() {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).clearHasMore();
                return this;
            }

            public Builder clearOrder() {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).clearOrder();
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).clearUid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
            public AttributeValue getAttributeValue(int i) {
                return ((UpdateCentralNotificationAttributes) this.instance).getAttributeValue(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
            public int getAttributeValueCount() {
                return ((UpdateCentralNotificationAttributes) this.instance).getAttributeValueCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
            public List<AttributeValue> getAttributeValueList() {
                return Collections.unmodifiableList(((UpdateCentralNotificationAttributes) this.instance).getAttributeValueList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
            public boolean getHasMore() {
                return ((UpdateCentralNotificationAttributes) this.instance).getHasMore();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
            public int getOrder() {
                return ((UpdateCentralNotificationAttributes) this.instance).getOrder();
            }

            @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
            public int getUid() {
                return ((UpdateCentralNotificationAttributes) this.instance).getUid();
            }

            public Builder removeAttributeValue(int i) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).removeAttributeValue(i);
                return this;
            }

            public Builder setAttributeValue(int i, AttributeValue attributeValue) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).setAttributeValue(i, attributeValue);
                return this;
            }

            public Builder setHasMore(boolean z) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).setHasMore(z);
                return this;
            }

            public Builder setOrder(int i) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).setOrder(i);
                return this;
            }

            public Builder setUid(int i) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).setUid(i);
                return this;
            }

            private Builder() {
                super(UpdateCentralNotificationAttributes.DEFAULT_INSTANCE);
            }

            public Builder addAttributeValue(int i, AttributeValue attributeValue) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).addAttributeValue(i, attributeValue);
                return this;
            }

            public Builder setAttributeValue(int i, AttributeValue.Builder builder) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).setAttributeValue(i, builder);
                return this;
            }

            public Builder addAttributeValue(AttributeValue.Builder builder) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).addAttributeValue(builder);
                return this;
            }

            public Builder addAttributeValue(int i, AttributeValue.Builder builder) {
                copyOnWrite();
                ((UpdateCentralNotificationAttributes) this.instance).addAttributeValue(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UpdateCentralNotificationAttributes() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAttributeValue(Iterable<? extends AttributeValue> iterable) {
            ensureAttributeValueIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.attributeValue_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttributeValue(AttributeValue attributeValue) {
            if (attributeValue != null) {
                ensureAttributeValueIsMutable();
                this.attributeValue_.add(attributeValue);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAttributeValue() {
            this.attributeValue_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHasMore() {
            this.hasMore_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOrder() {
            this.order_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUid() {
            this.uid_ = 0;
        }

        private void ensureAttributeValueIsMutable() {
            if (!this.attributeValue_.isModifiable()) {
                this.attributeValue_ = GeneratedMessageLite.mutableCopy(this.attributeValue_);
            }
        }

        public static UpdateCentralNotificationAttributes getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UpdateCentralNotificationAttributes parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateCentralNotificationAttributes parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UpdateCentralNotificationAttributes> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAttributeValue(int i) {
            ensureAttributeValueIsMutable();
            this.attributeValue_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAttributeValue(int i, AttributeValue attributeValue) {
            if (attributeValue != null) {
                ensureAttributeValueIsMutable();
                this.attributeValue_.set(i, attributeValue);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHasMore(boolean z) {
            this.hasMore_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOrder(int i) {
            this.order_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUid(int i) {
            this.uid_ = i;
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
                    UpdateCentralNotificationAttributes updateCentralNotificationAttributes = (UpdateCentralNotificationAttributes) obj2;
                    this.uid_ = visitor.visitInt(this.uid_ != 0, this.uid_, updateCentralNotificationAttributes.uid_ != 0, updateCentralNotificationAttributes.uid_);
                    boolean z2 = this.order_ != 0;
                    int i = this.order_;
                    if (updateCentralNotificationAttributes.order_ != 0) {
                        z = true;
                    }
                    this.order_ = visitor.visitInt(z2, i, z, updateCentralNotificationAttributes.order_);
                    boolean z3 = this.hasMore_;
                    boolean z4 = updateCentralNotificationAttributes.hasMore_;
                    this.hasMore_ = visitor.visitBoolean(z3, z3, z4, z4);
                    this.attributeValue_ = visitor.visitList(this.attributeValue_, updateCentralNotificationAttributes.attributeValue_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= updateCentralNotificationAttributes.bitField0_;
                    }
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.uid_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.order_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.hasMore_ = codedInputStream.readBool();
                                } else if (readTag != 34) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    if (!this.attributeValue_.isModifiable()) {
                                        this.attributeValue_ = GeneratedMessageLite.mutableCopy(this.attributeValue_);
                                    }
                                    this.attributeValue_.add(codedInputStream.readMessage(AttributeValue.parser(), extensionRegistryLite));
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
                    this.attributeValue_.makeImmutable();
                    return null;
                case 6:
                    return new UpdateCentralNotificationAttributes();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UpdateCentralNotificationAttributes.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
        public AttributeValue getAttributeValue(int i) {
            return this.attributeValue_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
        public int getAttributeValueCount() {
            return this.attributeValue_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
        public List<AttributeValue> getAttributeValueList() {
            return this.attributeValue_;
        }

        public AttributeValueOrBuilder getAttributeValueOrBuilder(int i) {
            return this.attributeValue_.get(i);
        }

        public List<? extends AttributeValueOrBuilder> getAttributeValueOrBuilderList() {
            return this.attributeValue_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
        public boolean getHasMore() {
            return this.hasMore_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
        public int getOrder() {
            return this.order_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.uid_;
            int computeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) + 0 : 0;
            int i3 = this.order_;
            if (i3 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
            }
            boolean z = this.hasMore_;
            if (z) {
                computeUInt32Size += CodedOutputStream.computeBoolSize(3, z);
            }
            for (int i4 = 0; i4 < this.attributeValue_.size(); i4++) {
                computeUInt32Size += CodedOutputStream.computeMessageSize(4, this.attributeValue_.get(i4));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeUInt32Size;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Ancs.UpdateCentralNotificationAttributesOrBuilder
        public int getUid() {
            return this.uid_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.uid_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.order_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            boolean z = this.hasMore_;
            if (z) {
                codedOutputStream.writeBool(3, z);
            }
            for (int i3 = 0; i3 < this.attributeValue_.size(); i3++) {
                codedOutputStream.writeMessage(4, this.attributeValue_.get(i3));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UpdateCentralNotificationAttributes updateCentralNotificationAttributes) {
            return DEFAULT_INSTANCE.createBuilder(updateCentralNotificationAttributes);
        }

        public static UpdateCentralNotificationAttributes parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateCentralNotificationAttributes parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UpdateCentralNotificationAttributes parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttributeValue(int i, AttributeValue attributeValue) {
            if (attributeValue != null) {
                ensureAttributeValueIsMutable();
                this.attributeValue_.add(i, attributeValue);
                return;
            }
            throw new NullPointerException();
        }

        public static UpdateCentralNotificationAttributes parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAttributeValue(int i, AttributeValue.Builder builder) {
            ensureAttributeValueIsMutable();
            this.attributeValue_.set(i, builder.mo10084build());
        }

        public static UpdateCentralNotificationAttributes parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UpdateCentralNotificationAttributes parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttributeValue(AttributeValue.Builder builder) {
            ensureAttributeValueIsMutable();
            this.attributeValue_.add(builder.mo10084build());
        }

        public static UpdateCentralNotificationAttributes parseFrom(InputStream inputStream) throws IOException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateCentralNotificationAttributes parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAttributeValue(int i, AttributeValue.Builder builder) {
            ensureAttributeValueIsMutable();
            this.attributeValue_.add(i, builder.mo10084build());
        }

        public static UpdateCentralNotificationAttributes parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UpdateCentralNotificationAttributes parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateCentralNotificationAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UpdateCentralNotificationAttributesOrBuilder extends MessageLiteOrBuilder {
        UpdateCentralNotificationAttributes.AttributeValue getAttributeValue(int i);

        int getAttributeValueCount();

        List<UpdateCentralNotificationAttributes.AttributeValue> getAttributeValueList();

        boolean getHasMore();

        int getOrder();

        int getUid();
    }

    private Ancs() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
