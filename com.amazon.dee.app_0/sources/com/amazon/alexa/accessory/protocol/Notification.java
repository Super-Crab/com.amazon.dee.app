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
/* loaded from: classes6.dex */
public final class Notification {

    /* renamed from: com.amazon.alexa.accessory.protocol.Notification$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Notification$NotificationActionPayload$ActionPayloadTypesCase = new int[NotificationActionPayload.ActionPayloadTypesCase.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Notification$NotificationPayload$PayloadTypesCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Notification$NotificationActionPayload$ActionPayloadTypesCase[NotificationActionPayload.ActionPayloadTypesCase.GENERAL_NOTIFICATION_ACTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Notification$NotificationActionPayload$ActionPayloadTypesCase[NotificationActionPayload.ActionPayloadTypesCase.CALENDAR_NOTIFICATION_ACTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Notification$NotificationActionPayload$ActionPayloadTypesCase[NotificationActionPayload.ActionPayloadTypesCase.ACTIONPAYLOADTYPES_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$alexa$accessory$protocol$Notification$NotificationPayload$PayloadTypesCase = new int[NotificationPayload.PayloadTypesCase.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Notification$NotificationPayload$PayloadTypesCase[NotificationPayload.PayloadTypesCase.GENERAL_NOTIFICATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Notification$NotificationPayload$PayloadTypesCase[NotificationPayload.PayloadTypesCase.CALENDAR_NOTIFICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Notification$NotificationPayload$PayloadTypesCase[NotificationPayload.PayloadTypesCase.PAYLOADTYPES_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 9;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 10;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class AddNotification extends GeneratedMessageLite<AddNotification, Builder> implements AddNotificationOrBuilder {
        private static final AddNotification DEFAULT_INSTANCE = new AddNotification();
        public static final int NOTIFICATION_CONTENT_FIELD_NUMBER = 2;
        public static final int NOTIFICATION_UID_FIELD_NUMBER = 1;
        private static volatile Parser<AddNotification> PARSER;
        private NotificationContent notificationContent_;
        private int notificationUid_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<AddNotification, Builder> implements AddNotificationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearNotificationContent() {
                copyOnWrite();
                ((AddNotification) this.instance).clearNotificationContent();
                return this;
            }

            public Builder clearNotificationUid() {
                copyOnWrite();
                ((AddNotification) this.instance).clearNotificationUid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.AddNotificationOrBuilder
            public NotificationContent getNotificationContent() {
                return ((AddNotification) this.instance).getNotificationContent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.AddNotificationOrBuilder
            public int getNotificationUid() {
                return ((AddNotification) this.instance).getNotificationUid();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.AddNotificationOrBuilder
            public boolean hasNotificationContent() {
                return ((AddNotification) this.instance).hasNotificationContent();
            }

            public Builder mergeNotificationContent(NotificationContent notificationContent) {
                copyOnWrite();
                ((AddNotification) this.instance).mergeNotificationContent(notificationContent);
                return this;
            }

            public Builder setNotificationContent(NotificationContent notificationContent) {
                copyOnWrite();
                ((AddNotification) this.instance).setNotificationContent(notificationContent);
                return this;
            }

            public Builder setNotificationUid(int i) {
                copyOnWrite();
                ((AddNotification) this.instance).setNotificationUid(i);
                return this;
            }

            private Builder() {
                super(AddNotification.DEFAULT_INSTANCE);
            }

            public Builder setNotificationContent(NotificationContent.Builder builder) {
                copyOnWrite();
                ((AddNotification) this.instance).setNotificationContent(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private AddNotification() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationContent() {
            this.notificationContent_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationUid() {
            this.notificationUid_ = 0;
        }

        public static AddNotification getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotificationContent(NotificationContent notificationContent) {
            NotificationContent notificationContent2 = this.notificationContent_;
            if (notificationContent2 != null && notificationContent2 != NotificationContent.getDefaultInstance()) {
                this.notificationContent_ = NotificationContent.newBuilder(this.notificationContent_).mergeFrom((NotificationContent.Builder) notificationContent).mo10085buildPartial();
            } else {
                this.notificationContent_ = notificationContent;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static AddNotification parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AddNotification) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AddNotification parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<AddNotification> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationContent(NotificationContent notificationContent) {
            if (notificationContent != null) {
                this.notificationContent_ = notificationContent;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationUid(int i) {
            this.notificationUid_ = i;
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
                    AddNotification addNotification = (AddNotification) obj2;
                    boolean z2 = this.notificationUid_ != 0;
                    int i = this.notificationUid_;
                    if (addNotification.notificationUid_ != 0) {
                        z = true;
                    }
                    this.notificationUid_ = visitor.visitInt(z2, i, z, addNotification.notificationUid_);
                    this.notificationContent_ = (NotificationContent) visitor.visitMessage(this.notificationContent_, addNotification.notificationContent_);
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
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.notificationUid_ = codedInputStream.readUInt32();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    NotificationContent.Builder mo10081toBuilder = this.notificationContent_ != null ? this.notificationContent_.mo10081toBuilder() : null;
                                    this.notificationContent_ = (NotificationContent) codedInputStream.readMessage(NotificationContent.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((NotificationContent.Builder) this.notificationContent_);
                                        this.notificationContent_ = mo10081toBuilder.mo10085buildPartial();
                                    }
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
                    return new AddNotification();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (AddNotification.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.AddNotificationOrBuilder
        public NotificationContent getNotificationContent() {
            NotificationContent notificationContent = this.notificationContent_;
            return notificationContent == null ? NotificationContent.getDefaultInstance() : notificationContent;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.AddNotificationOrBuilder
        public int getNotificationUid() {
            return this.notificationUid_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.notificationUid_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (this.notificationContent_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getNotificationContent());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.AddNotificationOrBuilder
        public boolean hasNotificationContent() {
            return this.notificationContent_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.notificationUid_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.notificationContent_ != null) {
                codedOutputStream.writeMessage(2, getNotificationContent());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(AddNotification addNotification) {
            return DEFAULT_INSTANCE.createBuilder(addNotification);
        }

        public static AddNotification parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AddNotification) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AddNotification parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static AddNotification parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationContent(NotificationContent.Builder builder) {
            this.notificationContent_ = builder.mo10084build();
        }

        public static AddNotification parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AddNotification parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AddNotification parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AddNotification parseFrom(InputStream inputStream) throws IOException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AddNotification parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AddNotification parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AddNotification parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AddNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface AddNotificationOrBuilder extends MessageLiteOrBuilder {
        NotificationContent getNotificationContent();

        int getNotificationUid();

        boolean hasNotificationContent();
    }

    /* loaded from: classes6.dex */
    public static final class CalendarNotificationActionPayload extends GeneratedMessageLite<CalendarNotificationActionPayload, Builder> implements CalendarNotificationActionPayloadOrBuilder {
        public static final int ACTION_TYPE_FIELD_NUMBER = 1;
        private static final CalendarNotificationActionPayload DEFAULT_INSTANCE = new CalendarNotificationActionPayload();
        private static volatile Parser<CalendarNotificationActionPayload> PARSER = null;
        public static final int REMIND_IN_SECONDS_FIELD_NUMBER = 2;
        private int actionType_;
        private int remindInSeconds_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CalendarNotificationActionPayload, Builder> implements CalendarNotificationActionPayloadOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearActionType() {
                copyOnWrite();
                ((CalendarNotificationActionPayload) this.instance).clearActionType();
                return this;
            }

            public Builder clearRemindInSeconds() {
                copyOnWrite();
                ((CalendarNotificationActionPayload) this.instance).clearRemindInSeconds();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationActionPayloadOrBuilder
            public CalendarNotificationActionType getActionType() {
                return ((CalendarNotificationActionPayload) this.instance).getActionType();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationActionPayloadOrBuilder
            public int getActionTypeValue() {
                return ((CalendarNotificationActionPayload) this.instance).getActionTypeValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationActionPayloadOrBuilder
            public int getRemindInSeconds() {
                return ((CalendarNotificationActionPayload) this.instance).getRemindInSeconds();
            }

            public Builder setActionType(CalendarNotificationActionType calendarNotificationActionType) {
                copyOnWrite();
                ((CalendarNotificationActionPayload) this.instance).setActionType(calendarNotificationActionType);
                return this;
            }

            public Builder setActionTypeValue(int i) {
                copyOnWrite();
                ((CalendarNotificationActionPayload) this.instance).setActionTypeValue(i);
                return this;
            }

            public Builder setRemindInSeconds(int i) {
                copyOnWrite();
                ((CalendarNotificationActionPayload) this.instance).setRemindInSeconds(i);
                return this;
            }

            private Builder() {
                super(CalendarNotificationActionPayload.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        public enum CalendarNotificationActionType implements Internal.EnumLite {
            REMIND(0),
            UNRECOGNIZED(-1);
            
            public static final int REMIND_VALUE = 0;
            private static final Internal.EnumLiteMap<CalendarNotificationActionType> internalValueMap = new Internal.EnumLiteMap<CalendarNotificationActionType>() { // from class: com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationActionPayload.CalendarNotificationActionType.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public CalendarNotificationActionType mo9850findValueByNumber(int i) {
                    return CalendarNotificationActionType.forNumber(i);
                }
            };
            private final int value;

            CalendarNotificationActionType(int i) {
                this.value = i;
            }

            public static CalendarNotificationActionType forNumber(int i) {
                if (i != 0) {
                    return null;
                }
                return REMIND;
            }

            public static Internal.EnumLiteMap<CalendarNotificationActionType> internalGetValueMap() {
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
            public static CalendarNotificationActionType valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CalendarNotificationActionPayload() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearActionType() {
            this.actionType_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRemindInSeconds() {
            this.remindInSeconds_ = 0;
        }

        public static CalendarNotificationActionPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CalendarNotificationActionPayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CalendarNotificationActionPayload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CalendarNotificationActionPayload> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActionType(CalendarNotificationActionType calendarNotificationActionType) {
            if (calendarNotificationActionType != null) {
                this.actionType_ = calendarNotificationActionType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActionTypeValue(int i) {
            this.actionType_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemindInSeconds(int i) {
            this.remindInSeconds_ = i;
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
                    CalendarNotificationActionPayload calendarNotificationActionPayload = (CalendarNotificationActionPayload) obj2;
                    this.actionType_ = visitor.visitInt(this.actionType_ != 0, this.actionType_, calendarNotificationActionPayload.actionType_ != 0, calendarNotificationActionPayload.actionType_);
                    boolean z2 = this.remindInSeconds_ != 0;
                    int i = this.remindInSeconds_;
                    if (calendarNotificationActionPayload.remindInSeconds_ != 0) {
                        z = true;
                    }
                    this.remindInSeconds_ = visitor.visitInt(z2, i, z, calendarNotificationActionPayload.remindInSeconds_);
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
                                    this.actionType_ = codedInputStream.readEnum();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.remindInSeconds_ = codedInputStream.readUInt32();
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
                    return new CalendarNotificationActionPayload();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CalendarNotificationActionPayload.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationActionPayloadOrBuilder
        public CalendarNotificationActionType getActionType() {
            CalendarNotificationActionType forNumber = CalendarNotificationActionType.forNumber(this.actionType_);
            return forNumber == null ? CalendarNotificationActionType.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationActionPayloadOrBuilder
        public int getActionTypeValue() {
            return this.actionType_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationActionPayloadOrBuilder
        public int getRemindInSeconds() {
            return this.remindInSeconds_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.actionType_ != CalendarNotificationActionType.REMIND.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.actionType_);
            }
            int i3 = this.remindInSeconds_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.actionType_ != CalendarNotificationActionType.REMIND.getNumber()) {
                codedOutputStream.writeEnum(1, this.actionType_);
            }
            int i = this.remindInSeconds_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CalendarNotificationActionPayload calendarNotificationActionPayload) {
            return DEFAULT_INSTANCE.createBuilder(calendarNotificationActionPayload);
        }

        public static CalendarNotificationActionPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CalendarNotificationActionPayload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CalendarNotificationActionPayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CalendarNotificationActionPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CalendarNotificationActionPayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CalendarNotificationActionPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CalendarNotificationActionPayload parseFrom(InputStream inputStream) throws IOException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CalendarNotificationActionPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CalendarNotificationActionPayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CalendarNotificationActionPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CalendarNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CalendarNotificationActionPayloadOrBuilder extends MessageLiteOrBuilder {
        CalendarNotificationActionPayload.CalendarNotificationActionType getActionType();

        int getActionTypeValue();

        int getRemindInSeconds();
    }

    /* loaded from: classes6.dex */
    public static final class CalendarNotificationPayload extends GeneratedMessageLite<CalendarNotificationPayload, Builder> implements CalendarNotificationPayloadOrBuilder {
        private static final CalendarNotificationPayload DEFAULT_INSTANCE = new CalendarNotificationPayload();
        public static final int LOCATION_FIELD_NUMBER = 2;
        public static final int NOTIFICATION_MESSAGE_FIELD_NUMBER = 4;
        private static volatile Parser<CalendarNotificationPayload> PARSER = null;
        public static final int TIME_FIELD_NUMBER = 3;
        public static final int TITLE_FIELD_NUMBER = 1;
        private String title_ = "";
        private String location_ = "";
        private String time_ = "";
        private String notificationMessage_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CalendarNotificationPayload, Builder> implements CalendarNotificationPayloadOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearLocation() {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).clearLocation();
                return this;
            }

            public Builder clearNotificationMessage() {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).clearNotificationMessage();
                return this;
            }

            public Builder clearTime() {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).clearTime();
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).clearTitle();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
            public String getLocation() {
                return ((CalendarNotificationPayload) this.instance).getLocation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
            public ByteString getLocationBytes() {
                return ((CalendarNotificationPayload) this.instance).getLocationBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
            public String getNotificationMessage() {
                return ((CalendarNotificationPayload) this.instance).getNotificationMessage();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
            public ByteString getNotificationMessageBytes() {
                return ((CalendarNotificationPayload) this.instance).getNotificationMessageBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
            public String getTime() {
                return ((CalendarNotificationPayload) this.instance).getTime();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
            public ByteString getTimeBytes() {
                return ((CalendarNotificationPayload) this.instance).getTimeBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
            public String getTitle() {
                return ((CalendarNotificationPayload) this.instance).getTitle();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
            public ByteString getTitleBytes() {
                return ((CalendarNotificationPayload) this.instance).getTitleBytes();
            }

            public Builder setLocation(String str) {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).setLocation(str);
                return this;
            }

            public Builder setLocationBytes(ByteString byteString) {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).setLocationBytes(byteString);
                return this;
            }

            public Builder setNotificationMessage(String str) {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).setNotificationMessage(str);
                return this;
            }

            public Builder setNotificationMessageBytes(ByteString byteString) {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).setNotificationMessageBytes(byteString);
                return this;
            }

            public Builder setTime(String str) {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).setTime(str);
                return this;
            }

            public Builder setTimeBytes(ByteString byteString) {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).setTimeBytes(byteString);
                return this;
            }

            public Builder setTitle(String str) {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).setTitle(str);
                return this;
            }

            public Builder setTitleBytes(ByteString byteString) {
                copyOnWrite();
                ((CalendarNotificationPayload) this.instance).setTitleBytes(byteString);
                return this;
            }

            private Builder() {
                super(CalendarNotificationPayload.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CalendarNotificationPayload() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLocation() {
            this.location_ = getDefaultInstance().getLocation();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationMessage() {
            this.notificationMessage_ = getDefaultInstance().getNotificationMessage();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTime() {
            this.time_ = getDefaultInstance().getTime();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTitle() {
            this.title_ = getDefaultInstance().getTitle();
        }

        public static CalendarNotificationPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CalendarNotificationPayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CalendarNotificationPayload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CalendarNotificationPayload> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocation(String str) {
            if (str != null) {
                this.location_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocationBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.location_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationMessage(String str) {
            if (str != null) {
                this.notificationMessage_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationMessageBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.notificationMessage_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTime(String str) {
            if (str != null) {
                this.time_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.time_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTitle(String str) {
            if (str != null) {
                this.title_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTitleBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.title_ = byteString.toStringUtf8();
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
                    CalendarNotificationPayload calendarNotificationPayload = (CalendarNotificationPayload) obj2;
                    this.title_ = visitor.visitString(!this.title_.isEmpty(), this.title_, !calendarNotificationPayload.title_.isEmpty(), calendarNotificationPayload.title_);
                    this.location_ = visitor.visitString(!this.location_.isEmpty(), this.location_, !calendarNotificationPayload.location_.isEmpty(), calendarNotificationPayload.location_);
                    this.time_ = visitor.visitString(!this.time_.isEmpty(), this.time_, !calendarNotificationPayload.time_.isEmpty(), calendarNotificationPayload.time_);
                    this.notificationMessage_ = visitor.visitString(!this.notificationMessage_.isEmpty(), this.notificationMessage_, true ^ calendarNotificationPayload.notificationMessage_.isEmpty(), calendarNotificationPayload.notificationMessage_);
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
                                    this.title_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.location_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 26) {
                                    this.time_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 34) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.notificationMessage_ = codedInputStream.readStringRequireUtf8();
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
                    return new CalendarNotificationPayload();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CalendarNotificationPayload.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
        public String getLocation() {
            return this.location_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
        public ByteString getLocationBytes() {
            return ByteString.copyFromUtf8(this.location_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
        public String getNotificationMessage() {
            return this.notificationMessage_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
        public ByteString getNotificationMessageBytes() {
            return ByteString.copyFromUtf8(this.notificationMessage_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.title_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getTitle());
            }
            if (!this.location_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getLocation());
            }
            if (!this.time_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getTime());
            }
            if (!this.notificationMessage_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(4, getNotificationMessage());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
        public String getTime() {
            return this.time_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
        public ByteString getTimeBytes() {
            return ByteString.copyFromUtf8(this.time_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
        public String getTitle() {
            return this.title_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.CalendarNotificationPayloadOrBuilder
        public ByteString getTitleBytes() {
            return ByteString.copyFromUtf8(this.title_);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.title_.isEmpty()) {
                codedOutputStream.writeString(1, getTitle());
            }
            if (!this.location_.isEmpty()) {
                codedOutputStream.writeString(2, getLocation());
            }
            if (!this.time_.isEmpty()) {
                codedOutputStream.writeString(3, getTime());
            }
            if (!this.notificationMessage_.isEmpty()) {
                codedOutputStream.writeString(4, getNotificationMessage());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CalendarNotificationPayload calendarNotificationPayload) {
            return DEFAULT_INSTANCE.createBuilder(calendarNotificationPayload);
        }

        public static CalendarNotificationPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CalendarNotificationPayload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CalendarNotificationPayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CalendarNotificationPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CalendarNotificationPayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CalendarNotificationPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CalendarNotificationPayload parseFrom(InputStream inputStream) throws IOException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CalendarNotificationPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CalendarNotificationPayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CalendarNotificationPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CalendarNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CalendarNotificationPayloadOrBuilder extends MessageLiteOrBuilder {
        String getLocation();

        ByteString getLocationBytes();

        String getNotificationMessage();

        ByteString getNotificationMessageBytes();

        String getTime();

        ByteString getTimeBytes();

        String getTitle();

        ByteString getTitleBytes();
    }

    /* loaded from: classes6.dex */
    public static final class ExecuteNotificationAction extends GeneratedMessageLite<ExecuteNotificationAction, Builder> implements ExecuteNotificationActionOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 2;
        private static final ExecuteNotificationAction DEFAULT_INSTANCE = new ExecuteNotificationAction();
        public static final int NOTIFICATION_UID_FIELD_NUMBER = 1;
        private static volatile Parser<ExecuteNotificationAction> PARSER;
        private NotificationActionPayload action_;
        private int notificationUid_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ExecuteNotificationAction, Builder> implements ExecuteNotificationActionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((ExecuteNotificationAction) this.instance).clearAction();
                return this;
            }

            public Builder clearNotificationUid() {
                copyOnWrite();
                ((ExecuteNotificationAction) this.instance).clearNotificationUid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.ExecuteNotificationActionOrBuilder
            public NotificationActionPayload getAction() {
                return ((ExecuteNotificationAction) this.instance).getAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.ExecuteNotificationActionOrBuilder
            public int getNotificationUid() {
                return ((ExecuteNotificationAction) this.instance).getNotificationUid();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.ExecuteNotificationActionOrBuilder
            public boolean hasAction() {
                return ((ExecuteNotificationAction) this.instance).hasAction();
            }

            public Builder mergeAction(NotificationActionPayload notificationActionPayload) {
                copyOnWrite();
                ((ExecuteNotificationAction) this.instance).mergeAction(notificationActionPayload);
                return this;
            }

            public Builder setAction(NotificationActionPayload notificationActionPayload) {
                copyOnWrite();
                ((ExecuteNotificationAction) this.instance).setAction(notificationActionPayload);
                return this;
            }

            public Builder setNotificationUid(int i) {
                copyOnWrite();
                ((ExecuteNotificationAction) this.instance).setNotificationUid(i);
                return this;
            }

            private Builder() {
                super(ExecuteNotificationAction.DEFAULT_INSTANCE);
            }

            public Builder setAction(NotificationActionPayload.Builder builder) {
                copyOnWrite();
                ((ExecuteNotificationAction) this.instance).setAction(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ExecuteNotificationAction() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAction() {
            this.action_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationUid() {
            this.notificationUid_ = 0;
        }

        public static ExecuteNotificationAction getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAction(NotificationActionPayload notificationActionPayload) {
            NotificationActionPayload notificationActionPayload2 = this.action_;
            if (notificationActionPayload2 != null && notificationActionPayload2 != NotificationActionPayload.getDefaultInstance()) {
                this.action_ = NotificationActionPayload.newBuilder(this.action_).mergeFrom((NotificationActionPayload.Builder) notificationActionPayload).mo10085buildPartial();
            } else {
                this.action_ = notificationActionPayload;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ExecuteNotificationAction parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExecuteNotificationAction parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ExecuteNotificationAction> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAction(NotificationActionPayload notificationActionPayload) {
            if (notificationActionPayload != null) {
                this.action_ = notificationActionPayload;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationUid(int i) {
            this.notificationUid_ = i;
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
                    ExecuteNotificationAction executeNotificationAction = (ExecuteNotificationAction) obj2;
                    boolean z2 = this.notificationUid_ != 0;
                    int i = this.notificationUid_;
                    if (executeNotificationAction.notificationUid_ != 0) {
                        z = true;
                    }
                    this.notificationUid_ = visitor.visitInt(z2, i, z, executeNotificationAction.notificationUid_);
                    this.action_ = (NotificationActionPayload) visitor.visitMessage(this.action_, executeNotificationAction.action_);
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
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.notificationUid_ = codedInputStream.readUInt32();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    NotificationActionPayload.Builder mo10081toBuilder = this.action_ != null ? this.action_.mo10081toBuilder() : null;
                                    this.action_ = (NotificationActionPayload) codedInputStream.readMessage(NotificationActionPayload.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((NotificationActionPayload.Builder) this.action_);
                                        this.action_ = mo10081toBuilder.mo10085buildPartial();
                                    }
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
                    return new ExecuteNotificationAction();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ExecuteNotificationAction.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.ExecuteNotificationActionOrBuilder
        public NotificationActionPayload getAction() {
            NotificationActionPayload notificationActionPayload = this.action_;
            return notificationActionPayload == null ? NotificationActionPayload.getDefaultInstance() : notificationActionPayload;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.ExecuteNotificationActionOrBuilder
        public int getNotificationUid() {
            return this.notificationUid_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.notificationUid_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (this.action_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getAction());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.ExecuteNotificationActionOrBuilder
        public boolean hasAction() {
            return this.action_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.notificationUid_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.action_ != null) {
                codedOutputStream.writeMessage(2, getAction());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ExecuteNotificationAction executeNotificationAction) {
            return DEFAULT_INSTANCE.createBuilder(executeNotificationAction);
        }

        public static ExecuteNotificationAction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExecuteNotificationAction parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ExecuteNotificationAction parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAction(NotificationActionPayload.Builder builder) {
            this.action_ = builder.mo10084build();
        }

        public static ExecuteNotificationAction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ExecuteNotificationAction parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ExecuteNotificationAction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ExecuteNotificationAction parseFrom(InputStream inputStream) throws IOException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExecuteNotificationAction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExecuteNotificationAction parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ExecuteNotificationAction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExecuteNotificationAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ExecuteNotificationActionOrBuilder extends MessageLiteOrBuilder {
        NotificationActionPayload getAction();

        int getNotificationUid();

        boolean hasAction();
    }

    /* loaded from: classes6.dex */
    public static final class GeneralNotificationActionPayload extends GeneratedMessageLite<GeneralNotificationActionPayload, Builder> implements GeneralNotificationActionPayloadOrBuilder {
        public static final int ACTION_TYPE_FIELD_NUMBER = 1;
        private static final GeneralNotificationActionPayload DEFAULT_INSTANCE = new GeneralNotificationActionPayload();
        private static volatile Parser<GeneralNotificationActionPayload> PARSER;
        private int actionType_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GeneralNotificationActionPayload, Builder> implements GeneralNotificationActionPayloadOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearActionType() {
                copyOnWrite();
                ((GeneralNotificationActionPayload) this.instance).clearActionType();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationActionPayloadOrBuilder
            public GeneralNotificationActionType getActionType() {
                return ((GeneralNotificationActionPayload) this.instance).getActionType();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationActionPayloadOrBuilder
            public int getActionTypeValue() {
                return ((GeneralNotificationActionPayload) this.instance).getActionTypeValue();
            }

            public Builder setActionType(GeneralNotificationActionType generalNotificationActionType) {
                copyOnWrite();
                ((GeneralNotificationActionPayload) this.instance).setActionType(generalNotificationActionType);
                return this;
            }

            public Builder setActionTypeValue(int i) {
                copyOnWrite();
                ((GeneralNotificationActionPayload) this.instance).setActionTypeValue(i);
                return this;
            }

            private Builder() {
                super(GeneralNotificationActionPayload.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        public enum GeneralNotificationActionType implements Internal.EnumLite {
            DISMISS(0),
            UNRECOGNIZED(-1);
            
            public static final int DISMISS_VALUE = 0;
            private static final Internal.EnumLiteMap<GeneralNotificationActionType> internalValueMap = new Internal.EnumLiteMap<GeneralNotificationActionType>() { // from class: com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationActionPayload.GeneralNotificationActionType.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public GeneralNotificationActionType mo9850findValueByNumber(int i) {
                    return GeneralNotificationActionType.forNumber(i);
                }
            };
            private final int value;

            GeneralNotificationActionType(int i) {
                this.value = i;
            }

            public static GeneralNotificationActionType forNumber(int i) {
                if (i != 0) {
                    return null;
                }
                return DISMISS;
            }

            public static Internal.EnumLiteMap<GeneralNotificationActionType> internalGetValueMap() {
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
            public static GeneralNotificationActionType valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GeneralNotificationActionPayload() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearActionType() {
            this.actionType_ = 0;
        }

        public static GeneralNotificationActionPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GeneralNotificationActionPayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GeneralNotificationActionPayload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GeneralNotificationActionPayload> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActionType(GeneralNotificationActionType generalNotificationActionType) {
            if (generalNotificationActionType != null) {
                this.actionType_ = generalNotificationActionType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActionTypeValue(int i) {
            this.actionType_ = i;
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
                    GeneralNotificationActionPayload generalNotificationActionPayload = (GeneralNotificationActionPayload) obj2;
                    boolean z2 = this.actionType_ != 0;
                    int i = this.actionType_;
                    if (generalNotificationActionPayload.actionType_ != 0) {
                        z = true;
                    }
                    this.actionType_ = visitor.visitInt(z2, i, z, generalNotificationActionPayload.actionType_);
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
                                    if (readTag != 8) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.actionType_ = codedInputStream.readEnum();
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
                    return new GeneralNotificationActionPayload();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GeneralNotificationActionPayload.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationActionPayloadOrBuilder
        public GeneralNotificationActionType getActionType() {
            GeneralNotificationActionType forNumber = GeneralNotificationActionType.forNumber(this.actionType_);
            return forNumber == null ? GeneralNotificationActionType.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationActionPayloadOrBuilder
        public int getActionTypeValue() {
            return this.actionType_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.actionType_ != GeneralNotificationActionType.DISMISS.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.actionType_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.actionType_ != GeneralNotificationActionType.DISMISS.getNumber()) {
                codedOutputStream.writeEnum(1, this.actionType_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GeneralNotificationActionPayload generalNotificationActionPayload) {
            return DEFAULT_INSTANCE.createBuilder(generalNotificationActionPayload);
        }

        public static GeneralNotificationActionPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GeneralNotificationActionPayload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GeneralNotificationActionPayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GeneralNotificationActionPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GeneralNotificationActionPayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GeneralNotificationActionPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GeneralNotificationActionPayload parseFrom(InputStream inputStream) throws IOException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GeneralNotificationActionPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GeneralNotificationActionPayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GeneralNotificationActionPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GeneralNotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GeneralNotificationActionPayloadOrBuilder extends MessageLiteOrBuilder {
        GeneralNotificationActionPayload.GeneralNotificationActionType getActionType();

        int getActionTypeValue();
    }

    /* loaded from: classes6.dex */
    public static final class GeneralNotificationPayload extends GeneratedMessageLite<GeneralNotificationPayload, Builder> implements GeneralNotificationPayloadOrBuilder {
        private static final GeneralNotificationPayload DEFAULT_INSTANCE = new GeneralNotificationPayload();
        public static final int NOTIFICATION_MESSAGE_FIELD_NUMBER = 3;
        private static volatile Parser<GeneralNotificationPayload> PARSER = null;
        public static final int SUBTITLE_FIELD_NUMBER = 2;
        public static final int TITLE_FIELD_NUMBER = 1;
        private String title_ = "";
        private String subtitle_ = "";
        private String notificationMessage_ = "";

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GeneralNotificationPayload, Builder> implements GeneralNotificationPayloadOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearNotificationMessage() {
                copyOnWrite();
                ((GeneralNotificationPayload) this.instance).clearNotificationMessage();
                return this;
            }

            public Builder clearSubtitle() {
                copyOnWrite();
                ((GeneralNotificationPayload) this.instance).clearSubtitle();
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((GeneralNotificationPayload) this.instance).clearTitle();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
            public String getNotificationMessage() {
                return ((GeneralNotificationPayload) this.instance).getNotificationMessage();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
            public ByteString getNotificationMessageBytes() {
                return ((GeneralNotificationPayload) this.instance).getNotificationMessageBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
            public String getSubtitle() {
                return ((GeneralNotificationPayload) this.instance).getSubtitle();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
            public ByteString getSubtitleBytes() {
                return ((GeneralNotificationPayload) this.instance).getSubtitleBytes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
            public String getTitle() {
                return ((GeneralNotificationPayload) this.instance).getTitle();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
            public ByteString getTitleBytes() {
                return ((GeneralNotificationPayload) this.instance).getTitleBytes();
            }

            public Builder setNotificationMessage(String str) {
                copyOnWrite();
                ((GeneralNotificationPayload) this.instance).setNotificationMessage(str);
                return this;
            }

            public Builder setNotificationMessageBytes(ByteString byteString) {
                copyOnWrite();
                ((GeneralNotificationPayload) this.instance).setNotificationMessageBytes(byteString);
                return this;
            }

            public Builder setSubtitle(String str) {
                copyOnWrite();
                ((GeneralNotificationPayload) this.instance).setSubtitle(str);
                return this;
            }

            public Builder setSubtitleBytes(ByteString byteString) {
                copyOnWrite();
                ((GeneralNotificationPayload) this.instance).setSubtitleBytes(byteString);
                return this;
            }

            public Builder setTitle(String str) {
                copyOnWrite();
                ((GeneralNotificationPayload) this.instance).setTitle(str);
                return this;
            }

            public Builder setTitleBytes(ByteString byteString) {
                copyOnWrite();
                ((GeneralNotificationPayload) this.instance).setTitleBytes(byteString);
                return this;
            }

            private Builder() {
                super(GeneralNotificationPayload.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GeneralNotificationPayload() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationMessage() {
            this.notificationMessage_ = getDefaultInstance().getNotificationMessage();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSubtitle() {
            this.subtitle_ = getDefaultInstance().getSubtitle();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTitle() {
            this.title_ = getDefaultInstance().getTitle();
        }

        public static GeneralNotificationPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GeneralNotificationPayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GeneralNotificationPayload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GeneralNotificationPayload> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationMessage(String str) {
            if (str != null) {
                this.notificationMessage_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationMessageBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.notificationMessage_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubtitle(String str) {
            if (str != null) {
                this.subtitle_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubtitleBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.subtitle_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTitle(String str) {
            if (str != null) {
                this.title_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTitleBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.title_ = byteString.toStringUtf8();
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
                    GeneralNotificationPayload generalNotificationPayload = (GeneralNotificationPayload) obj2;
                    this.title_ = visitor.visitString(!this.title_.isEmpty(), this.title_, !generalNotificationPayload.title_.isEmpty(), generalNotificationPayload.title_);
                    this.subtitle_ = visitor.visitString(!this.subtitle_.isEmpty(), this.subtitle_, !generalNotificationPayload.subtitle_.isEmpty(), generalNotificationPayload.subtitle_);
                    this.notificationMessage_ = visitor.visitString(!this.notificationMessage_.isEmpty(), this.notificationMessage_, true ^ generalNotificationPayload.notificationMessage_.isEmpty(), generalNotificationPayload.notificationMessage_);
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
                                    this.title_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.subtitle_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 26) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.notificationMessage_ = codedInputStream.readStringRequireUtf8();
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
                    return new GeneralNotificationPayload();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GeneralNotificationPayload.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
        public String getNotificationMessage() {
            return this.notificationMessage_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
        public ByteString getNotificationMessageBytes() {
            return ByteString.copyFromUtf8(this.notificationMessage_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.title_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getTitle());
            }
            if (!this.subtitle_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getSubtitle());
            }
            if (!this.notificationMessage_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getNotificationMessage());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
        public String getSubtitle() {
            return this.subtitle_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
        public ByteString getSubtitleBytes() {
            return ByteString.copyFromUtf8(this.subtitle_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
        public String getTitle() {
            return this.title_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.GeneralNotificationPayloadOrBuilder
        public ByteString getTitleBytes() {
            return ByteString.copyFromUtf8(this.title_);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.title_.isEmpty()) {
                codedOutputStream.writeString(1, getTitle());
            }
            if (!this.subtitle_.isEmpty()) {
                codedOutputStream.writeString(2, getSubtitle());
            }
            if (!this.notificationMessage_.isEmpty()) {
                codedOutputStream.writeString(3, getNotificationMessage());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GeneralNotificationPayload generalNotificationPayload) {
            return DEFAULT_INSTANCE.createBuilder(generalNotificationPayload);
        }

        public static GeneralNotificationPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GeneralNotificationPayload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GeneralNotificationPayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GeneralNotificationPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GeneralNotificationPayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GeneralNotificationPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GeneralNotificationPayload parseFrom(InputStream inputStream) throws IOException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GeneralNotificationPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GeneralNotificationPayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GeneralNotificationPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GeneralNotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GeneralNotificationPayloadOrBuilder extends MessageLiteOrBuilder {
        String getNotificationMessage();

        ByteString getNotificationMessageBytes();

        String getSubtitle();

        ByteString getSubtitleBytes();

        String getTitle();

        ByteString getTitleBytes();
    }

    /* loaded from: classes6.dex */
    public static final class NotificationActionPayload extends GeneratedMessageLite<NotificationActionPayload, Builder> implements NotificationActionPayloadOrBuilder {
        public static final int CALENDAR_NOTIFICATION_ACTION_FIELD_NUMBER = 2;
        private static final NotificationActionPayload DEFAULT_INSTANCE = new NotificationActionPayload();
        public static final int GENERAL_NOTIFICATION_ACTION_FIELD_NUMBER = 1;
        private static volatile Parser<NotificationActionPayload> PARSER;
        private int actionPayloadTypesCase_ = 0;
        private Object actionPayloadTypes_;

        /* loaded from: classes6.dex */
        public enum ActionPayloadTypesCase implements Internal.EnumLite {
            GENERAL_NOTIFICATION_ACTION(1),
            CALENDAR_NOTIFICATION_ACTION(2),
            ACTIONPAYLOADTYPES_NOT_SET(0);
            
            private final int value;

            ActionPayloadTypesCase(int i) {
                this.value = i;
            }

            public static ActionPayloadTypesCase forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return GENERAL_NOTIFICATION_ACTION;
                    }
                    if (i == 2) {
                        return CALENDAR_NOTIFICATION_ACTION;
                    }
                    return null;
                }
                return ACTIONPAYLOADTYPES_NOT_SET;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ActionPayloadTypesCase valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotificationActionPayload, Builder> implements NotificationActionPayloadOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearActionPayloadTypes() {
                copyOnWrite();
                ((NotificationActionPayload) this.instance).clearActionPayloadTypes();
                return this;
            }

            public Builder clearCalendarNotificationAction() {
                copyOnWrite();
                ((NotificationActionPayload) this.instance).clearCalendarNotificationAction();
                return this;
            }

            public Builder clearGeneralNotificationAction() {
                copyOnWrite();
                ((NotificationActionPayload) this.instance).clearGeneralNotificationAction();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
            public ActionPayloadTypesCase getActionPayloadTypesCase() {
                return ((NotificationActionPayload) this.instance).getActionPayloadTypesCase();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
            public CalendarNotificationActionPayload getCalendarNotificationAction() {
                return ((NotificationActionPayload) this.instance).getCalendarNotificationAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
            public GeneralNotificationActionPayload getGeneralNotificationAction() {
                return ((NotificationActionPayload) this.instance).getGeneralNotificationAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
            public boolean hasCalendarNotificationAction() {
                return ((NotificationActionPayload) this.instance).hasCalendarNotificationAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
            public boolean hasGeneralNotificationAction() {
                return ((NotificationActionPayload) this.instance).hasGeneralNotificationAction();
            }

            public Builder mergeCalendarNotificationAction(CalendarNotificationActionPayload calendarNotificationActionPayload) {
                copyOnWrite();
                ((NotificationActionPayload) this.instance).mergeCalendarNotificationAction(calendarNotificationActionPayload);
                return this;
            }

            public Builder mergeGeneralNotificationAction(GeneralNotificationActionPayload generalNotificationActionPayload) {
                copyOnWrite();
                ((NotificationActionPayload) this.instance).mergeGeneralNotificationAction(generalNotificationActionPayload);
                return this;
            }

            public Builder setCalendarNotificationAction(CalendarNotificationActionPayload calendarNotificationActionPayload) {
                copyOnWrite();
                ((NotificationActionPayload) this.instance).setCalendarNotificationAction(calendarNotificationActionPayload);
                return this;
            }

            public Builder setGeneralNotificationAction(GeneralNotificationActionPayload generalNotificationActionPayload) {
                copyOnWrite();
                ((NotificationActionPayload) this.instance).setGeneralNotificationAction(generalNotificationActionPayload);
                return this;
            }

            private Builder() {
                super(NotificationActionPayload.DEFAULT_INSTANCE);
            }

            public Builder setCalendarNotificationAction(CalendarNotificationActionPayload.Builder builder) {
                copyOnWrite();
                ((NotificationActionPayload) this.instance).setCalendarNotificationAction(builder);
                return this;
            }

            public Builder setGeneralNotificationAction(GeneralNotificationActionPayload.Builder builder) {
                copyOnWrite();
                ((NotificationActionPayload) this.instance).setGeneralNotificationAction(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotificationActionPayload() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearActionPayloadTypes() {
            this.actionPayloadTypesCase_ = 0;
            this.actionPayloadTypes_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCalendarNotificationAction() {
            if (this.actionPayloadTypesCase_ == 2) {
                this.actionPayloadTypesCase_ = 0;
                this.actionPayloadTypes_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGeneralNotificationAction() {
            if (this.actionPayloadTypesCase_ == 1) {
                this.actionPayloadTypesCase_ = 0;
                this.actionPayloadTypes_ = null;
            }
        }

        public static NotificationActionPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCalendarNotificationAction(CalendarNotificationActionPayload calendarNotificationActionPayload) {
            if (this.actionPayloadTypesCase_ == 2 && this.actionPayloadTypes_ != CalendarNotificationActionPayload.getDefaultInstance()) {
                this.actionPayloadTypes_ = CalendarNotificationActionPayload.newBuilder((CalendarNotificationActionPayload) this.actionPayloadTypes_).mergeFrom((CalendarNotificationActionPayload.Builder) calendarNotificationActionPayload).mo10085buildPartial();
            } else {
                this.actionPayloadTypes_ = calendarNotificationActionPayload;
            }
            this.actionPayloadTypesCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGeneralNotificationAction(GeneralNotificationActionPayload generalNotificationActionPayload) {
            if (this.actionPayloadTypesCase_ == 1 && this.actionPayloadTypes_ != GeneralNotificationActionPayload.getDefaultInstance()) {
                this.actionPayloadTypes_ = GeneralNotificationActionPayload.newBuilder((GeneralNotificationActionPayload) this.actionPayloadTypes_).mergeFrom((GeneralNotificationActionPayload.Builder) generalNotificationActionPayload).mo10085buildPartial();
            } else {
                this.actionPayloadTypes_ = generalNotificationActionPayload;
            }
            this.actionPayloadTypesCase_ = 1;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotificationActionPayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotificationActionPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotificationActionPayload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotificationActionPayload> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCalendarNotificationAction(CalendarNotificationActionPayload calendarNotificationActionPayload) {
            if (calendarNotificationActionPayload != null) {
                this.actionPayloadTypes_ = calendarNotificationActionPayload;
                this.actionPayloadTypesCase_ = 2;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGeneralNotificationAction(GeneralNotificationActionPayload generalNotificationActionPayload) {
            if (generalNotificationActionPayload != null) {
                this.actionPayloadTypes_ = generalNotificationActionPayload;
                this.actionPayloadTypesCase_ = 1;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            int i;
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    NotificationActionPayload notificationActionPayload = (NotificationActionPayload) obj2;
                    int ordinal = notificationActionPayload.getActionPayloadTypesCase().ordinal();
                    if (ordinal == 0) {
                        if (this.actionPayloadTypesCase_ == 1) {
                            z = true;
                        }
                        this.actionPayloadTypes_ = visitor.visitOneofMessage(z, this.actionPayloadTypes_, notificationActionPayload.actionPayloadTypes_);
                    } else if (ordinal == 1) {
                        if (this.actionPayloadTypesCase_ == 2) {
                            z = true;
                        }
                        this.actionPayloadTypes_ = visitor.visitOneofMessage(z, this.actionPayloadTypes_, notificationActionPayload.actionPayloadTypes_);
                    } else if (ordinal == 2) {
                        if (this.actionPayloadTypesCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = notificationActionPayload.actionPayloadTypesCase_) != 0) {
                        this.actionPayloadTypesCase_ = i;
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
                                if (readTag == 10) {
                                    GeneralNotificationActionPayload.Builder mo10081toBuilder = this.actionPayloadTypesCase_ == 1 ? ((GeneralNotificationActionPayload) this.actionPayloadTypes_).mo10081toBuilder() : null;
                                    this.actionPayloadTypes_ = codedInputStream.readMessage(GeneralNotificationActionPayload.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((GeneralNotificationActionPayload.Builder) ((GeneralNotificationActionPayload) this.actionPayloadTypes_));
                                        this.actionPayloadTypes_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                    this.actionPayloadTypesCase_ = 1;
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    CalendarNotificationActionPayload.Builder mo10081toBuilder2 = this.actionPayloadTypesCase_ == 2 ? ((CalendarNotificationActionPayload) this.actionPayloadTypes_).mo10081toBuilder() : null;
                                    this.actionPayloadTypes_ = codedInputStream.readMessage(CalendarNotificationActionPayload.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder2 != null) {
                                        mo10081toBuilder2.mergeFrom((CalendarNotificationActionPayload.Builder) ((CalendarNotificationActionPayload) this.actionPayloadTypes_));
                                        this.actionPayloadTypes_ = mo10081toBuilder2.mo10085buildPartial();
                                    }
                                    this.actionPayloadTypesCase_ = 2;
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
                    return new NotificationActionPayload();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotificationActionPayload.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
        public ActionPayloadTypesCase getActionPayloadTypesCase() {
            return ActionPayloadTypesCase.forNumber(this.actionPayloadTypesCase_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
        public CalendarNotificationActionPayload getCalendarNotificationAction() {
            if (this.actionPayloadTypesCase_ == 2) {
                return (CalendarNotificationActionPayload) this.actionPayloadTypes_;
            }
            return CalendarNotificationActionPayload.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
        public GeneralNotificationActionPayload getGeneralNotificationAction() {
            if (this.actionPayloadTypesCase_ == 1) {
                return (GeneralNotificationActionPayload) this.actionPayloadTypes_;
            }
            return GeneralNotificationActionPayload.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.actionPayloadTypesCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, (GeneralNotificationActionPayload) this.actionPayloadTypes_);
            }
            if (this.actionPayloadTypesCase_ == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, (CalendarNotificationActionPayload) this.actionPayloadTypes_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
        public boolean hasCalendarNotificationAction() {
            return this.actionPayloadTypesCase_ == 2;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationActionPayloadOrBuilder
        public boolean hasGeneralNotificationAction() {
            return this.actionPayloadTypesCase_ == 1;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.actionPayloadTypesCase_ == 1) {
                codedOutputStream.writeMessage(1, (GeneralNotificationActionPayload) this.actionPayloadTypes_);
            }
            if (this.actionPayloadTypesCase_ == 2) {
                codedOutputStream.writeMessage(2, (CalendarNotificationActionPayload) this.actionPayloadTypes_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotificationActionPayload notificationActionPayload) {
            return DEFAULT_INSTANCE.createBuilder(notificationActionPayload);
        }

        public static NotificationActionPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationActionPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotificationActionPayload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotificationActionPayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static NotificationActionPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCalendarNotificationAction(CalendarNotificationActionPayload.Builder builder) {
            this.actionPayloadTypes_ = builder.mo10084build();
            this.actionPayloadTypesCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGeneralNotificationAction(GeneralNotificationActionPayload.Builder builder) {
            this.actionPayloadTypes_ = builder.mo10084build();
            this.actionPayloadTypesCase_ = 1;
        }

        public static NotificationActionPayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotificationActionPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotificationActionPayload parseFrom(InputStream inputStream) throws IOException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotificationActionPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotificationActionPayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotificationActionPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationActionPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotificationActionPayloadOrBuilder extends MessageLiteOrBuilder {
        NotificationActionPayload.ActionPayloadTypesCase getActionPayloadTypesCase();

        CalendarNotificationActionPayload getCalendarNotificationAction();

        GeneralNotificationActionPayload getGeneralNotificationAction();

        boolean hasCalendarNotificationAction();

        boolean hasGeneralNotificationAction();
    }

    /* loaded from: classes6.dex */
    public static final class NotificationContent extends GeneratedMessageLite<NotificationContent, Builder> implements NotificationContentOrBuilder {
        private static final NotificationContent DEFAULT_INSTANCE = new NotificationContent();
        public static final int NOTIFICATION_IMPORTANCE_FIELD_NUMBER = 4;
        public static final int NOTIFICATION_PAYLOAD_FIELD_NUMBER = 5;
        private static volatile Parser<NotificationContent> PARSER = null;
        public static final int TIMESTAMP_MILLISECONDS_HI_FIELD_NUMBER = 1;
        public static final int TIMESTAMP_MILLISECONDS_LO_FIELD_NUMBER = 2;
        public static final int TIME_TO_LIVE_MILLISECONDS_FIELD_NUMBER = 3;
        private int notificationImportance_;
        private NotificationPayload notificationPayload_;
        private int timeToLiveMilliseconds_;
        private int timestampMillisecondsHi_;
        private int timestampMillisecondsLo_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotificationContent, Builder> implements NotificationContentOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearNotificationImportance() {
                copyOnWrite();
                ((NotificationContent) this.instance).clearNotificationImportance();
                return this;
            }

            public Builder clearNotificationPayload() {
                copyOnWrite();
                ((NotificationContent) this.instance).clearNotificationPayload();
                return this;
            }

            public Builder clearTimeToLiveMilliseconds() {
                copyOnWrite();
                ((NotificationContent) this.instance).clearTimeToLiveMilliseconds();
                return this;
            }

            public Builder clearTimestampMillisecondsHi() {
                copyOnWrite();
                ((NotificationContent) this.instance).clearTimestampMillisecondsHi();
                return this;
            }

            public Builder clearTimestampMillisecondsLo() {
                copyOnWrite();
                ((NotificationContent) this.instance).clearTimestampMillisecondsLo();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
            public NotificationImportance getNotificationImportance() {
                return ((NotificationContent) this.instance).getNotificationImportance();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
            public int getNotificationImportanceValue() {
                return ((NotificationContent) this.instance).getNotificationImportanceValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
            public NotificationPayload getNotificationPayload() {
                return ((NotificationContent) this.instance).getNotificationPayload();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
            public int getTimeToLiveMilliseconds() {
                return ((NotificationContent) this.instance).getTimeToLiveMilliseconds();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
            public int getTimestampMillisecondsHi() {
                return ((NotificationContent) this.instance).getTimestampMillisecondsHi();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
            public int getTimestampMillisecondsLo() {
                return ((NotificationContent) this.instance).getTimestampMillisecondsLo();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
            public boolean hasNotificationPayload() {
                return ((NotificationContent) this.instance).hasNotificationPayload();
            }

            public Builder mergeNotificationPayload(NotificationPayload notificationPayload) {
                copyOnWrite();
                ((NotificationContent) this.instance).mergeNotificationPayload(notificationPayload);
                return this;
            }

            public Builder setNotificationImportance(NotificationImportance notificationImportance) {
                copyOnWrite();
                ((NotificationContent) this.instance).setNotificationImportance(notificationImportance);
                return this;
            }

            public Builder setNotificationImportanceValue(int i) {
                copyOnWrite();
                ((NotificationContent) this.instance).setNotificationImportanceValue(i);
                return this;
            }

            public Builder setNotificationPayload(NotificationPayload notificationPayload) {
                copyOnWrite();
                ((NotificationContent) this.instance).setNotificationPayload(notificationPayload);
                return this;
            }

            public Builder setTimeToLiveMilliseconds(int i) {
                copyOnWrite();
                ((NotificationContent) this.instance).setTimeToLiveMilliseconds(i);
                return this;
            }

            public Builder setTimestampMillisecondsHi(int i) {
                copyOnWrite();
                ((NotificationContent) this.instance).setTimestampMillisecondsHi(i);
                return this;
            }

            public Builder setTimestampMillisecondsLo(int i) {
                copyOnWrite();
                ((NotificationContent) this.instance).setTimestampMillisecondsLo(i);
                return this;
            }

            private Builder() {
                super(NotificationContent.DEFAULT_INSTANCE);
            }

            public Builder setNotificationPayload(NotificationPayload.Builder builder) {
                copyOnWrite();
                ((NotificationContent) this.instance).setNotificationPayload(builder);
                return this;
            }
        }

        /* loaded from: classes6.dex */
        public enum NotificationImportance implements Internal.EnumLite {
            NOTIFICATION_IMPORTANCE_DEFAULT(0),
            NOTIFICATION_IMPORTANCE_MINIMUM(1),
            NOTIFICATION_IMPORTANCE_LOW(2),
            NOTIFICATION_IMPORTANCE_HIGH(3),
            NOTIFICATION_IMPORTANCE_MAXIMUM(4),
            UNRECOGNIZED(-1);
            
            public static final int NOTIFICATION_IMPORTANCE_DEFAULT_VALUE = 0;
            public static final int NOTIFICATION_IMPORTANCE_HIGH_VALUE = 3;
            public static final int NOTIFICATION_IMPORTANCE_LOW_VALUE = 2;
            public static final int NOTIFICATION_IMPORTANCE_MAXIMUM_VALUE = 4;
            public static final int NOTIFICATION_IMPORTANCE_MINIMUM_VALUE = 1;
            private static final Internal.EnumLiteMap<NotificationImportance> internalValueMap = new Internal.EnumLiteMap<NotificationImportance>() { // from class: com.amazon.alexa.accessory.protocol.Notification.NotificationContent.NotificationImportance.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public NotificationImportance mo9850findValueByNumber(int i) {
                    return NotificationImportance.forNumber(i);
                }
            };
            private final int value;

            NotificationImportance(int i) {
                this.value = i;
            }

            public static NotificationImportance forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return NOTIFICATION_IMPORTANCE_MINIMUM;
                    }
                    if (i == 2) {
                        return NOTIFICATION_IMPORTANCE_LOW;
                    }
                    if (i == 3) {
                        return NOTIFICATION_IMPORTANCE_HIGH;
                    }
                    if (i == 4) {
                        return NOTIFICATION_IMPORTANCE_MAXIMUM;
                    }
                    return null;
                }
                return NOTIFICATION_IMPORTANCE_DEFAULT;
            }

            public static Internal.EnumLiteMap<NotificationImportance> internalGetValueMap() {
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
            public static NotificationImportance valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotificationContent() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationImportance() {
            this.notificationImportance_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationPayload() {
            this.notificationPayload_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimeToLiveMilliseconds() {
            this.timeToLiveMilliseconds_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimestampMillisecondsHi() {
            this.timestampMillisecondsHi_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimestampMillisecondsLo() {
            this.timestampMillisecondsLo_ = 0;
        }

        public static NotificationContent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotificationPayload(NotificationPayload notificationPayload) {
            NotificationPayload notificationPayload2 = this.notificationPayload_;
            if (notificationPayload2 != null && notificationPayload2 != NotificationPayload.getDefaultInstance()) {
                this.notificationPayload_ = NotificationPayload.newBuilder(this.notificationPayload_).mergeFrom((NotificationPayload.Builder) notificationPayload).mo10085buildPartial();
            } else {
                this.notificationPayload_ = notificationPayload;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotificationContent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotificationContent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotificationContent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotificationContent> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationImportance(NotificationImportance notificationImportance) {
            if (notificationImportance != null) {
                this.notificationImportance_ = notificationImportance.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationImportanceValue(int i) {
            this.notificationImportance_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationPayload(NotificationPayload notificationPayload) {
            if (notificationPayload != null) {
                this.notificationPayload_ = notificationPayload;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimeToLiveMilliseconds(int i) {
            this.timeToLiveMilliseconds_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestampMillisecondsHi(int i) {
            this.timestampMillisecondsHi_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestampMillisecondsLo(int i) {
            this.timestampMillisecondsLo_ = i;
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
                    NotificationContent notificationContent = (NotificationContent) obj2;
                    this.timestampMillisecondsHi_ = visitor.visitInt(this.timestampMillisecondsHi_ != 0, this.timestampMillisecondsHi_, notificationContent.timestampMillisecondsHi_ != 0, notificationContent.timestampMillisecondsHi_);
                    this.timestampMillisecondsLo_ = visitor.visitInt(this.timestampMillisecondsLo_ != 0, this.timestampMillisecondsLo_, notificationContent.timestampMillisecondsLo_ != 0, notificationContent.timestampMillisecondsLo_);
                    this.timeToLiveMilliseconds_ = visitor.visitInt(this.timeToLiveMilliseconds_ != 0, this.timeToLiveMilliseconds_, notificationContent.timeToLiveMilliseconds_ != 0, notificationContent.timeToLiveMilliseconds_);
                    boolean z2 = this.notificationImportance_ != 0;
                    int i = this.notificationImportance_;
                    if (notificationContent.notificationImportance_ != 0) {
                        z = true;
                    }
                    this.notificationImportance_ = visitor.visitInt(z2, i, z, notificationContent.notificationImportance_);
                    this.notificationPayload_ = (NotificationPayload) visitor.visitMessage(this.notificationPayload_, notificationContent.notificationPayload_);
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
                                        this.timestampMillisecondsHi_ = codedInputStream.readUInt32();
                                    } else if (readTag == 16) {
                                        this.timestampMillisecondsLo_ = codedInputStream.readUInt32();
                                    } else if (readTag == 24) {
                                        this.timeToLiveMilliseconds_ = codedInputStream.readUInt32();
                                    } else if (readTag == 32) {
                                        this.notificationImportance_ = codedInputStream.readEnum();
                                    } else if (readTag != 42) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        NotificationPayload.Builder mo10081toBuilder = this.notificationPayload_ != null ? this.notificationPayload_.mo10081toBuilder() : null;
                                        this.notificationPayload_ = (NotificationPayload) codedInputStream.readMessage(NotificationPayload.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder != null) {
                                            mo10081toBuilder.mergeFrom((NotificationPayload.Builder) this.notificationPayload_);
                                            this.notificationPayload_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new NotificationContent();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotificationContent.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
        public NotificationImportance getNotificationImportance() {
            NotificationImportance forNumber = NotificationImportance.forNumber(this.notificationImportance_);
            return forNumber == null ? NotificationImportance.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
        public int getNotificationImportanceValue() {
            return this.notificationImportance_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
        public NotificationPayload getNotificationPayload() {
            NotificationPayload notificationPayload = this.notificationPayload_;
            return notificationPayload == null ? NotificationPayload.getDefaultInstance() : notificationPayload;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.timestampMillisecondsHi_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int i4 = this.timestampMillisecondsLo_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i4);
            }
            int i5 = this.timeToLiveMilliseconds_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i5);
            }
            if (this.notificationImportance_ != NotificationImportance.NOTIFICATION_IMPORTANCE_DEFAULT.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(4, this.notificationImportance_);
            }
            if (this.notificationPayload_ != null) {
                i2 += CodedOutputStream.computeMessageSize(5, getNotificationPayload());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
        public int getTimeToLiveMilliseconds() {
            return this.timeToLiveMilliseconds_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
        public int getTimestampMillisecondsHi() {
            return this.timestampMillisecondsHi_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
        public int getTimestampMillisecondsLo() {
            return this.timestampMillisecondsLo_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationContentOrBuilder
        public boolean hasNotificationPayload() {
            return this.notificationPayload_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.timestampMillisecondsHi_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.timestampMillisecondsLo_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            int i3 = this.timeToLiveMilliseconds_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(3, i3);
            }
            if (this.notificationImportance_ != NotificationImportance.NOTIFICATION_IMPORTANCE_DEFAULT.getNumber()) {
                codedOutputStream.writeEnum(4, this.notificationImportance_);
            }
            if (this.notificationPayload_ != null) {
                codedOutputStream.writeMessage(5, getNotificationPayload());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotificationContent notificationContent) {
            return DEFAULT_INSTANCE.createBuilder(notificationContent);
        }

        public static NotificationContent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationContent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotificationContent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotificationContent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationPayload(NotificationPayload.Builder builder) {
            this.notificationPayload_ = builder.mo10084build();
        }

        public static NotificationContent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NotificationContent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotificationContent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotificationContent parseFrom(InputStream inputStream) throws IOException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotificationContent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotificationContent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotificationContent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotificationContentOrBuilder extends MessageLiteOrBuilder {
        NotificationContent.NotificationImportance getNotificationImportance();

        int getNotificationImportanceValue();

        NotificationPayload getNotificationPayload();

        int getTimeToLiveMilliseconds();

        int getTimestampMillisecondsHi();

        int getTimestampMillisecondsLo();

        boolean hasNotificationPayload();
    }

    /* loaded from: classes6.dex */
    public static final class NotificationPayload extends GeneratedMessageLite<NotificationPayload, Builder> implements NotificationPayloadOrBuilder {
        public static final int CALENDAR_NOTIFICATION_FIELD_NUMBER = 2;
        private static final NotificationPayload DEFAULT_INSTANCE = new NotificationPayload();
        public static final int GENERAL_NOTIFICATION_FIELD_NUMBER = 1;
        private static volatile Parser<NotificationPayload> PARSER;
        private int payloadTypesCase_ = 0;
        private Object payloadTypes_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<NotificationPayload, Builder> implements NotificationPayloadOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCalendarNotification() {
                copyOnWrite();
                ((NotificationPayload) this.instance).clearCalendarNotification();
                return this;
            }

            public Builder clearGeneralNotification() {
                copyOnWrite();
                ((NotificationPayload) this.instance).clearGeneralNotification();
                return this;
            }

            public Builder clearPayloadTypes() {
                copyOnWrite();
                ((NotificationPayload) this.instance).clearPayloadTypes();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
            public CalendarNotificationPayload getCalendarNotification() {
                return ((NotificationPayload) this.instance).getCalendarNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
            public GeneralNotificationPayload getGeneralNotification() {
                return ((NotificationPayload) this.instance).getGeneralNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
            public PayloadTypesCase getPayloadTypesCase() {
                return ((NotificationPayload) this.instance).getPayloadTypesCase();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
            public boolean hasCalendarNotification() {
                return ((NotificationPayload) this.instance).hasCalendarNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
            public boolean hasGeneralNotification() {
                return ((NotificationPayload) this.instance).hasGeneralNotification();
            }

            public Builder mergeCalendarNotification(CalendarNotificationPayload calendarNotificationPayload) {
                copyOnWrite();
                ((NotificationPayload) this.instance).mergeCalendarNotification(calendarNotificationPayload);
                return this;
            }

            public Builder mergeGeneralNotification(GeneralNotificationPayload generalNotificationPayload) {
                copyOnWrite();
                ((NotificationPayload) this.instance).mergeGeneralNotification(generalNotificationPayload);
                return this;
            }

            public Builder setCalendarNotification(CalendarNotificationPayload calendarNotificationPayload) {
                copyOnWrite();
                ((NotificationPayload) this.instance).setCalendarNotification(calendarNotificationPayload);
                return this;
            }

            public Builder setGeneralNotification(GeneralNotificationPayload generalNotificationPayload) {
                copyOnWrite();
                ((NotificationPayload) this.instance).setGeneralNotification(generalNotificationPayload);
                return this;
            }

            private Builder() {
                super(NotificationPayload.DEFAULT_INSTANCE);
            }

            public Builder setCalendarNotification(CalendarNotificationPayload.Builder builder) {
                copyOnWrite();
                ((NotificationPayload) this.instance).setCalendarNotification(builder);
                return this;
            }

            public Builder setGeneralNotification(GeneralNotificationPayload.Builder builder) {
                copyOnWrite();
                ((NotificationPayload) this.instance).setGeneralNotification(builder);
                return this;
            }
        }

        /* loaded from: classes6.dex */
        public enum PayloadTypesCase implements Internal.EnumLite {
            GENERAL_NOTIFICATION(1),
            CALENDAR_NOTIFICATION(2),
            PAYLOADTYPES_NOT_SET(0);
            
            private final int value;

            PayloadTypesCase(int i) {
                this.value = i;
            }

            public static PayloadTypesCase forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return GENERAL_NOTIFICATION;
                    }
                    if (i == 2) {
                        return CALENDAR_NOTIFICATION;
                    }
                    return null;
                }
                return PAYLOADTYPES_NOT_SET;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static PayloadTypesCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private NotificationPayload() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCalendarNotification() {
            if (this.payloadTypesCase_ == 2) {
                this.payloadTypesCase_ = 0;
                this.payloadTypes_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGeneralNotification() {
            if (this.payloadTypesCase_ == 1) {
                this.payloadTypesCase_ = 0;
                this.payloadTypes_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPayloadTypes() {
            this.payloadTypesCase_ = 0;
            this.payloadTypes_ = null;
        }

        public static NotificationPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCalendarNotification(CalendarNotificationPayload calendarNotificationPayload) {
            if (this.payloadTypesCase_ == 2 && this.payloadTypes_ != CalendarNotificationPayload.getDefaultInstance()) {
                this.payloadTypes_ = CalendarNotificationPayload.newBuilder((CalendarNotificationPayload) this.payloadTypes_).mergeFrom((CalendarNotificationPayload.Builder) calendarNotificationPayload).mo10085buildPartial();
            } else {
                this.payloadTypes_ = calendarNotificationPayload;
            }
            this.payloadTypesCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGeneralNotification(GeneralNotificationPayload generalNotificationPayload) {
            if (this.payloadTypesCase_ == 1 && this.payloadTypes_ != GeneralNotificationPayload.getDefaultInstance()) {
                this.payloadTypes_ = GeneralNotificationPayload.newBuilder((GeneralNotificationPayload) this.payloadTypes_).mergeFrom((GeneralNotificationPayload.Builder) generalNotificationPayload).mo10085buildPartial();
            } else {
                this.payloadTypes_ = generalNotificationPayload;
            }
            this.payloadTypesCase_ = 1;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NotificationPayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotificationPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotificationPayload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NotificationPayload> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCalendarNotification(CalendarNotificationPayload calendarNotificationPayload) {
            if (calendarNotificationPayload != null) {
                this.payloadTypes_ = calendarNotificationPayload;
                this.payloadTypesCase_ = 2;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGeneralNotification(GeneralNotificationPayload generalNotificationPayload) {
            if (generalNotificationPayload != null) {
                this.payloadTypes_ = generalNotificationPayload;
                this.payloadTypesCase_ = 1;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            int i;
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    NotificationPayload notificationPayload = (NotificationPayload) obj2;
                    int ordinal = notificationPayload.getPayloadTypesCase().ordinal();
                    if (ordinal == 0) {
                        if (this.payloadTypesCase_ == 1) {
                            z = true;
                        }
                        this.payloadTypes_ = visitor.visitOneofMessage(z, this.payloadTypes_, notificationPayload.payloadTypes_);
                    } else if (ordinal == 1) {
                        if (this.payloadTypesCase_ == 2) {
                            z = true;
                        }
                        this.payloadTypes_ = visitor.visitOneofMessage(z, this.payloadTypes_, notificationPayload.payloadTypes_);
                    } else if (ordinal == 2) {
                        if (this.payloadTypesCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = notificationPayload.payloadTypesCase_) != 0) {
                        this.payloadTypesCase_ = i;
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
                                if (readTag == 10) {
                                    GeneralNotificationPayload.Builder mo10081toBuilder = this.payloadTypesCase_ == 1 ? ((GeneralNotificationPayload) this.payloadTypes_).mo10081toBuilder() : null;
                                    this.payloadTypes_ = codedInputStream.readMessage(GeneralNotificationPayload.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((GeneralNotificationPayload.Builder) ((GeneralNotificationPayload) this.payloadTypes_));
                                        this.payloadTypes_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                    this.payloadTypesCase_ = 1;
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    CalendarNotificationPayload.Builder mo10081toBuilder2 = this.payloadTypesCase_ == 2 ? ((CalendarNotificationPayload) this.payloadTypes_).mo10081toBuilder() : null;
                                    this.payloadTypes_ = codedInputStream.readMessage(CalendarNotificationPayload.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder2 != null) {
                                        mo10081toBuilder2.mergeFrom((CalendarNotificationPayload.Builder) ((CalendarNotificationPayload) this.payloadTypes_));
                                        this.payloadTypes_ = mo10081toBuilder2.mo10085buildPartial();
                                    }
                                    this.payloadTypesCase_ = 2;
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
                    return new NotificationPayload();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (NotificationPayload.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
        public CalendarNotificationPayload getCalendarNotification() {
            if (this.payloadTypesCase_ == 2) {
                return (CalendarNotificationPayload) this.payloadTypes_;
            }
            return CalendarNotificationPayload.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
        public GeneralNotificationPayload getGeneralNotification() {
            if (this.payloadTypesCase_ == 1) {
                return (GeneralNotificationPayload) this.payloadTypes_;
            }
            return GeneralNotificationPayload.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
        public PayloadTypesCase getPayloadTypesCase() {
            return PayloadTypesCase.forNumber(this.payloadTypesCase_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.payloadTypesCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, (GeneralNotificationPayload) this.payloadTypes_);
            }
            if (this.payloadTypesCase_ == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, (CalendarNotificationPayload) this.payloadTypes_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
        public boolean hasCalendarNotification() {
            return this.payloadTypesCase_ == 2;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.NotificationPayloadOrBuilder
        public boolean hasGeneralNotification() {
            return this.payloadTypesCase_ == 1;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.payloadTypesCase_ == 1) {
                codedOutputStream.writeMessage(1, (GeneralNotificationPayload) this.payloadTypes_);
            }
            if (this.payloadTypesCase_ == 2) {
                codedOutputStream.writeMessage(2, (CalendarNotificationPayload) this.payloadTypes_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(NotificationPayload notificationPayload) {
            return DEFAULT_INSTANCE.createBuilder(notificationPayload);
        }

        public static NotificationPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationPayload) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotificationPayload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NotificationPayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static NotificationPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCalendarNotification(CalendarNotificationPayload.Builder builder) {
            this.payloadTypes_ = builder.mo10084build();
            this.payloadTypesCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGeneralNotification(GeneralNotificationPayload.Builder builder) {
            this.payloadTypes_ = builder.mo10084build();
            this.payloadTypesCase_ = 1;
        }

        public static NotificationPayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotificationPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NotificationPayload parseFrom(InputStream inputStream) throws IOException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotificationPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NotificationPayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NotificationPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NotificationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface NotificationPayloadOrBuilder extends MessageLiteOrBuilder {
        CalendarNotificationPayload getCalendarNotification();

        GeneralNotificationPayload getGeneralNotification();

        NotificationPayload.PayloadTypesCase getPayloadTypesCase();

        boolean hasCalendarNotification();

        boolean hasGeneralNotification();
    }

    /* loaded from: classes6.dex */
    public static final class RemoveNotification extends GeneratedMessageLite<RemoveNotification, Builder> implements RemoveNotificationOrBuilder {
        private static final RemoveNotification DEFAULT_INSTANCE = new RemoveNotification();
        public static final int NOTIFICATION_UID_FIELD_NUMBER = 1;
        private static volatile Parser<RemoveNotification> PARSER;
        private int notificationUid_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<RemoveNotification, Builder> implements RemoveNotificationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearNotificationUid() {
                copyOnWrite();
                ((RemoveNotification) this.instance).clearNotificationUid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.RemoveNotificationOrBuilder
            public int getNotificationUid() {
                return ((RemoveNotification) this.instance).getNotificationUid();
            }

            public Builder setNotificationUid(int i) {
                copyOnWrite();
                ((RemoveNotification) this.instance).setNotificationUid(i);
                return this;
            }

            private Builder() {
                super(RemoveNotification.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private RemoveNotification() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationUid() {
            this.notificationUid_ = 0;
        }

        public static RemoveNotification getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static RemoveNotification parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoveNotification) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RemoveNotification parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<RemoveNotification> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationUid(int i) {
            this.notificationUid_ = i;
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
                    RemoveNotification removeNotification = (RemoveNotification) obj2;
                    boolean z2 = this.notificationUid_ != 0;
                    int i = this.notificationUid_;
                    if (removeNotification.notificationUid_ != 0) {
                        z = true;
                    }
                    this.notificationUid_ = visitor.visitInt(z2, i, z, removeNotification.notificationUid_);
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
                                    if (readTag != 8) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.notificationUid_ = codedInputStream.readUInt32();
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
                    return new RemoveNotification();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (RemoveNotification.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.RemoveNotificationOrBuilder
        public int getNotificationUid() {
            return this.notificationUid_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.notificationUid_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.notificationUid_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(RemoveNotification removeNotification) {
            return DEFAULT_INSTANCE.createBuilder(removeNotification);
        }

        public static RemoveNotification parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoveNotification) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RemoveNotification parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static RemoveNotification parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static RemoveNotification parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static RemoveNotification parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static RemoveNotification parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static RemoveNotification parseFrom(InputStream inputStream) throws IOException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RemoveNotification parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RemoveNotification parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static RemoveNotification parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoveNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface RemoveNotificationOrBuilder extends MessageLiteOrBuilder {
        int getNotificationUid();
    }

    /* loaded from: classes6.dex */
    public static final class UpdateNotification extends GeneratedMessageLite<UpdateNotification, Builder> implements UpdateNotificationOrBuilder {
        private static final UpdateNotification DEFAULT_INSTANCE = new UpdateNotification();
        public static final int NOTIFICATION_CONTENT_FIELD_NUMBER = 2;
        public static final int NOTIFICATION_UID_FIELD_NUMBER = 1;
        private static volatile Parser<UpdateNotification> PARSER;
        private NotificationContent notificationContent_;
        private int notificationUid_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UpdateNotification, Builder> implements UpdateNotificationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearNotificationContent() {
                copyOnWrite();
                ((UpdateNotification) this.instance).clearNotificationContent();
                return this;
            }

            public Builder clearNotificationUid() {
                copyOnWrite();
                ((UpdateNotification) this.instance).clearNotificationUid();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.UpdateNotificationOrBuilder
            public NotificationContent getNotificationContent() {
                return ((UpdateNotification) this.instance).getNotificationContent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.UpdateNotificationOrBuilder
            public int getNotificationUid() {
                return ((UpdateNotification) this.instance).getNotificationUid();
            }

            @Override // com.amazon.alexa.accessory.protocol.Notification.UpdateNotificationOrBuilder
            public boolean hasNotificationContent() {
                return ((UpdateNotification) this.instance).hasNotificationContent();
            }

            public Builder mergeNotificationContent(NotificationContent notificationContent) {
                copyOnWrite();
                ((UpdateNotification) this.instance).mergeNotificationContent(notificationContent);
                return this;
            }

            public Builder setNotificationContent(NotificationContent notificationContent) {
                copyOnWrite();
                ((UpdateNotification) this.instance).setNotificationContent(notificationContent);
                return this;
            }

            public Builder setNotificationUid(int i) {
                copyOnWrite();
                ((UpdateNotification) this.instance).setNotificationUid(i);
                return this;
            }

            private Builder() {
                super(UpdateNotification.DEFAULT_INSTANCE);
            }

            public Builder setNotificationContent(NotificationContent.Builder builder) {
                copyOnWrite();
                ((UpdateNotification) this.instance).setNotificationContent(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private UpdateNotification() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationContent() {
            this.notificationContent_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationUid() {
            this.notificationUid_ = 0;
        }

        public static UpdateNotification getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotificationContent(NotificationContent notificationContent) {
            NotificationContent notificationContent2 = this.notificationContent_;
            if (notificationContent2 != null && notificationContent2 != NotificationContent.getDefaultInstance()) {
                this.notificationContent_ = NotificationContent.newBuilder(this.notificationContent_).mergeFrom((NotificationContent.Builder) notificationContent).mo10085buildPartial();
            } else {
                this.notificationContent_ = notificationContent;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static UpdateNotification parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateNotification) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateNotification parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<UpdateNotification> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationContent(NotificationContent notificationContent) {
            if (notificationContent != null) {
                this.notificationContent_ = notificationContent;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationUid(int i) {
            this.notificationUid_ = i;
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
                    UpdateNotification updateNotification = (UpdateNotification) obj2;
                    boolean z2 = this.notificationUid_ != 0;
                    int i = this.notificationUid_;
                    if (updateNotification.notificationUid_ != 0) {
                        z = true;
                    }
                    this.notificationUid_ = visitor.visitInt(z2, i, z, updateNotification.notificationUid_);
                    this.notificationContent_ = (NotificationContent) visitor.visitMessage(this.notificationContent_, updateNotification.notificationContent_);
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
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.notificationUid_ = codedInputStream.readUInt32();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    NotificationContent.Builder mo10081toBuilder = this.notificationContent_ != null ? this.notificationContent_.mo10081toBuilder() : null;
                                    this.notificationContent_ = (NotificationContent) codedInputStream.readMessage(NotificationContent.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((NotificationContent.Builder) this.notificationContent_);
                                        this.notificationContent_ = mo10081toBuilder.mo10085buildPartial();
                                    }
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
                    return new UpdateNotification();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (UpdateNotification.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Notification.UpdateNotificationOrBuilder
        public NotificationContent getNotificationContent() {
            NotificationContent notificationContent = this.notificationContent_;
            return notificationContent == null ? NotificationContent.getDefaultInstance() : notificationContent;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.UpdateNotificationOrBuilder
        public int getNotificationUid() {
            return this.notificationUid_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.notificationUid_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (this.notificationContent_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getNotificationContent());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Notification.UpdateNotificationOrBuilder
        public boolean hasNotificationContent() {
            return this.notificationContent_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.notificationUid_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.notificationContent_ != null) {
                codedOutputStream.writeMessage(2, getNotificationContent());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(UpdateNotification updateNotification) {
            return DEFAULT_INSTANCE.createBuilder(updateNotification);
        }

        public static UpdateNotification parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateNotification) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateNotification parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UpdateNotification parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationContent(NotificationContent.Builder builder) {
            this.notificationContent_ = builder.mo10084build();
        }

        public static UpdateNotification parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UpdateNotification parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UpdateNotification parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UpdateNotification parseFrom(InputStream inputStream) throws IOException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UpdateNotification parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UpdateNotification parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UpdateNotification parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateNotification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface UpdateNotificationOrBuilder extends MessageLiteOrBuilder {
        NotificationContent getNotificationContent();

        int getNotificationUid();

        boolean hasNotificationContent();
    }

    private Notification() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
