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
public final class Input {

    /* renamed from: com.amazon.alexa.accessory.protocol.Input$1  reason: invalid class name */
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
    public static final class GetInputBehavior extends GeneratedMessageLite<GetInputBehavior, Builder> implements GetInputBehaviorOrBuilder {
        private static final GetInputBehavior DEFAULT_INSTANCE = new GetInputBehavior();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<GetInputBehavior> PARSER;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetInputBehavior, Builder> implements GetInputBehaviorOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((GetInputBehavior) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.GetInputBehaviorOrBuilder
            public int getDeviceId() {
                return ((GetInputBehavior) this.instance).getDeviceId();
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((GetInputBehavior) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(GetInputBehavior.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetInputBehavior() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        public static GetInputBehavior getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetInputBehavior parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetInputBehavior) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetInputBehavior parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetInputBehavior> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceId(int i) {
            this.deviceId_ = i;
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
                    GetInputBehavior getInputBehavior = (GetInputBehavior) obj2;
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (getInputBehavior.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, getInputBehavior.deviceId_);
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
                                        this.deviceId_ = codedInputStream.readUInt32();
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
                    return new GetInputBehavior();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetInputBehavior.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Input.GetInputBehaviorOrBuilder
        public int getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.deviceId_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.deviceId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetInputBehavior getInputBehavior) {
            return DEFAULT_INSTANCE.createBuilder(getInputBehavior);
        }

        public static GetInputBehavior parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetInputBehavior) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetInputBehavior parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetInputBehavior parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetInputBehavior parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetInputBehavior parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetInputBehavior parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetInputBehavior parseFrom(InputStream inputStream) throws IOException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetInputBehavior parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetInputBehavior parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetInputBehavior parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetInputBehaviorOrBuilder extends MessageLiteOrBuilder {
        int getDeviceId();
    }

    /* loaded from: classes6.dex */
    public enum InputAction implements Internal.EnumLite {
        INPUT_ACTION_OTHER(0),
        INPUT_ACTION_TAP(1),
        INPUT_ACTION_DOUBLE_TAP(2),
        INPUT_ACTION_TAP_HOLD(3),
        INPUT_ACTION_PRESS(4),
        INPUT_ACTION_LONG_PRESS(5),
        INPUT_ACTION_RELEASE(6),
        INPUT_ACTION_SWIPE_FORWARD(7),
        INPUT_ACTION_SWIPE_BACK(8),
        INPUT_ACTION_SWIPE_UP(9),
        INPUT_ACTION_SWIPE_DOWN(10),
        INPUT_ACTION_SWIPE_LEFT(11),
        INPUT_ACTION_SWIPE_RIGHT(12),
        INPUT_ACTION_TRIPLE_TAP(13),
        UNRECOGNIZED(-1);
        
        public static final int INPUT_ACTION_DOUBLE_TAP_VALUE = 2;
        public static final int INPUT_ACTION_LONG_PRESS_VALUE = 5;
        public static final int INPUT_ACTION_OTHER_VALUE = 0;
        public static final int INPUT_ACTION_PRESS_VALUE = 4;
        public static final int INPUT_ACTION_RELEASE_VALUE = 6;
        public static final int INPUT_ACTION_SWIPE_BACK_VALUE = 8;
        public static final int INPUT_ACTION_SWIPE_DOWN_VALUE = 10;
        public static final int INPUT_ACTION_SWIPE_FORWARD_VALUE = 7;
        public static final int INPUT_ACTION_SWIPE_LEFT_VALUE = 11;
        public static final int INPUT_ACTION_SWIPE_RIGHT_VALUE = 12;
        public static final int INPUT_ACTION_SWIPE_UP_VALUE = 9;
        public static final int INPUT_ACTION_TAP_HOLD_VALUE = 3;
        public static final int INPUT_ACTION_TAP_VALUE = 1;
        public static final int INPUT_ACTION_TRIPLE_TAP_VALUE = 13;
        private static final Internal.EnumLiteMap<InputAction> internalValueMap = new Internal.EnumLiteMap<InputAction>() { // from class: com.amazon.alexa.accessory.protocol.Input.InputAction.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public InputAction mo9850findValueByNumber(int i) {
                return InputAction.forNumber(i);
            }
        };
        private final int value;

        InputAction(int i) {
            this.value = i;
        }

        public static InputAction forNumber(int i) {
            switch (i) {
                case 0:
                    return INPUT_ACTION_OTHER;
                case 1:
                    return INPUT_ACTION_TAP;
                case 2:
                    return INPUT_ACTION_DOUBLE_TAP;
                case 3:
                    return INPUT_ACTION_TAP_HOLD;
                case 4:
                    return INPUT_ACTION_PRESS;
                case 5:
                    return INPUT_ACTION_LONG_PRESS;
                case 6:
                    return INPUT_ACTION_RELEASE;
                case 7:
                    return INPUT_ACTION_SWIPE_FORWARD;
                case 8:
                    return INPUT_ACTION_SWIPE_BACK;
                case 9:
                    return INPUT_ACTION_SWIPE_UP;
                case 10:
                    return INPUT_ACTION_SWIPE_DOWN;
                case 11:
                    return INPUT_ACTION_SWIPE_LEFT;
                case 12:
                    return INPUT_ACTION_SWIPE_RIGHT;
                case 13:
                    return INPUT_ACTION_TRIPLE_TAP;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<InputAction> internalGetValueMap() {
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
        public static InputAction valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public enum InputBehavior implements Internal.EnumLite {
        INPUT_BEHAVIOR_UNDEFINED(0),
        INPUT_BEHAVIOR_MEDIA_PLAY_PAUSE(1),
        INPUT_BEHAVIOR_MEDIA_NEXT(2),
        INPUT_BEHAVIOR_MEDIA_PREVIOUS(3),
        INPUT_BEHAVIOR_MEDIA_SMART_PLAY_PAUSE(4),
        INPUT_BEHAVIOR_VOICE_ALEXA(10),
        INPUT_BEHAVIOR_VOICE_PLATFORM(11),
        INPUT_BEHAVIOR_PASSTHROUGH_MODE(15),
        INPUT_BEHAVIOR_ANC(16),
        INPUT_BEHAVIOR_MICROPHONE_MUTE(17),
        INPUT_BEHAVIOR_PASSTHROUGH_ANC(18),
        INPUT_BEHAVIOR_VOLUME_UP(19),
        INPUT_BEHAVIOR_VOLUME_DOWN(20),
        INPUT_BEHAVIOR_TRIGGER_ROUTINE(21),
        INPUT_BEHAVIOR_VIP_FILTER_POSITIVE_RESPONSE(22),
        INPUT_BEHAVIOR_VIP_FILTER_NEGATIVE_RESPONSE(23),
        INPUT_BEHAVIOR_VIP_FILTER_DISTRACTION_MODE(24),
        INPUT_BEHAVIOR_VIP_FILTER_PHONE_NOTIFICATION_MODE(25),
        INPUT_BEHAVIOR_VIP_FILTER_READ_LAST_NOTIFICATION(26),
        INPUT_BEHAVIOR_TOP_CONTACT_CALLING(27),
        UNRECOGNIZED(-1);
        
        public static final int INPUT_BEHAVIOR_ANC_VALUE = 16;
        public static final int INPUT_BEHAVIOR_MEDIA_NEXT_VALUE = 2;
        public static final int INPUT_BEHAVIOR_MEDIA_PLAY_PAUSE_VALUE = 1;
        public static final int INPUT_BEHAVIOR_MEDIA_PREVIOUS_VALUE = 3;
        public static final int INPUT_BEHAVIOR_MEDIA_SMART_PLAY_PAUSE_VALUE = 4;
        public static final int INPUT_BEHAVIOR_MICROPHONE_MUTE_VALUE = 17;
        public static final int INPUT_BEHAVIOR_PASSTHROUGH_ANC_VALUE = 18;
        public static final int INPUT_BEHAVIOR_PASSTHROUGH_MODE_VALUE = 15;
        public static final int INPUT_BEHAVIOR_TOP_CONTACT_CALLING_VALUE = 27;
        public static final int INPUT_BEHAVIOR_TRIGGER_ROUTINE_VALUE = 21;
        public static final int INPUT_BEHAVIOR_UNDEFINED_VALUE = 0;
        public static final int INPUT_BEHAVIOR_VIP_FILTER_DISTRACTION_MODE_VALUE = 24;
        public static final int INPUT_BEHAVIOR_VIP_FILTER_NEGATIVE_RESPONSE_VALUE = 23;
        public static final int INPUT_BEHAVIOR_VIP_FILTER_PHONE_NOTIFICATION_MODE_VALUE = 25;
        public static final int INPUT_BEHAVIOR_VIP_FILTER_POSITIVE_RESPONSE_VALUE = 22;
        public static final int INPUT_BEHAVIOR_VIP_FILTER_READ_LAST_NOTIFICATION_VALUE = 26;
        public static final int INPUT_BEHAVIOR_VOICE_ALEXA_VALUE = 10;
        public static final int INPUT_BEHAVIOR_VOICE_PLATFORM_VALUE = 11;
        public static final int INPUT_BEHAVIOR_VOLUME_DOWN_VALUE = 20;
        public static final int INPUT_BEHAVIOR_VOLUME_UP_VALUE = 19;
        private static final Internal.EnumLiteMap<InputBehavior> internalValueMap = new Internal.EnumLiteMap<InputBehavior>() { // from class: com.amazon.alexa.accessory.protocol.Input.InputBehavior.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public InputBehavior mo9850findValueByNumber(int i) {
                return InputBehavior.forNumber(i);
            }
        };
        private final int value;

        InputBehavior(int i) {
            this.value = i;
        }

        public static InputBehavior forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return INPUT_BEHAVIOR_MEDIA_PLAY_PAUSE;
                }
                if (i == 2) {
                    return INPUT_BEHAVIOR_MEDIA_NEXT;
                }
                if (i == 3) {
                    return INPUT_BEHAVIOR_MEDIA_PREVIOUS;
                }
                if (i == 4) {
                    return INPUT_BEHAVIOR_MEDIA_SMART_PLAY_PAUSE;
                }
                if (i == 10) {
                    return INPUT_BEHAVIOR_VOICE_ALEXA;
                }
                if (i != 11) {
                    switch (i) {
                        case 15:
                            return INPUT_BEHAVIOR_PASSTHROUGH_MODE;
                        case 16:
                            return INPUT_BEHAVIOR_ANC;
                        case 17:
                            return INPUT_BEHAVIOR_MICROPHONE_MUTE;
                        case 18:
                            return INPUT_BEHAVIOR_PASSTHROUGH_ANC;
                        case 19:
                            return INPUT_BEHAVIOR_VOLUME_UP;
                        case 20:
                            return INPUT_BEHAVIOR_VOLUME_DOWN;
                        case 21:
                            return INPUT_BEHAVIOR_TRIGGER_ROUTINE;
                        case 22:
                            return INPUT_BEHAVIOR_VIP_FILTER_POSITIVE_RESPONSE;
                        case 23:
                            return INPUT_BEHAVIOR_VIP_FILTER_NEGATIVE_RESPONSE;
                        case 24:
                            return INPUT_BEHAVIOR_VIP_FILTER_DISTRACTION_MODE;
                        case 25:
                            return INPUT_BEHAVIOR_VIP_FILTER_PHONE_NOTIFICATION_MODE;
                        case 26:
                            return INPUT_BEHAVIOR_VIP_FILTER_READ_LAST_NOTIFICATION;
                        case 27:
                            return INPUT_BEHAVIOR_TOP_CONTACT_CALLING;
                        default:
                            return null;
                    }
                }
                return INPUT_BEHAVIOR_VOICE_PLATFORM;
            }
            return INPUT_BEHAVIOR_UNDEFINED;
        }

        public static Internal.EnumLiteMap<InputBehavior> internalGetValueMap() {
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
        public static InputBehavior valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class InputBehaviorConfiguration extends GeneratedMessageLite<InputBehaviorConfiguration, Builder> implements InputBehaviorConfigurationOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 1;
        public static final int BEHAVIOR_FIELD_NUMBER = 3;
        private static final InputBehaviorConfiguration DEFAULT_INSTANCE = new InputBehaviorConfiguration();
        private static volatile Parser<InputBehaviorConfiguration> PARSER = null;
        public static final int SOURCE_FIELD_NUMBER = 2;
        private int action_;
        private int behavior_;
        private int source_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<InputBehaviorConfiguration, Builder> implements InputBehaviorConfigurationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((InputBehaviorConfiguration) this.instance).clearAction();
                return this;
            }

            public Builder clearBehavior() {
                copyOnWrite();
                ((InputBehaviorConfiguration) this.instance).clearBehavior();
                return this;
            }

            public Builder clearSource() {
                copyOnWrite();
                ((InputBehaviorConfiguration) this.instance).clearSource();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
            public InputAction getAction() {
                return ((InputBehaviorConfiguration) this.instance).getAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
            public int getActionValue() {
                return ((InputBehaviorConfiguration) this.instance).getActionValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
            public InputBehavior getBehavior() {
                return ((InputBehaviorConfiguration) this.instance).getBehavior();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
            public int getBehaviorValue() {
                return ((InputBehaviorConfiguration) this.instance).getBehaviorValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
            public InputSource getSource() {
                return ((InputBehaviorConfiguration) this.instance).getSource();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
            public int getSourceValue() {
                return ((InputBehaviorConfiguration) this.instance).getSourceValue();
            }

            public Builder setAction(InputAction inputAction) {
                copyOnWrite();
                ((InputBehaviorConfiguration) this.instance).setAction(inputAction);
                return this;
            }

            public Builder setActionValue(int i) {
                copyOnWrite();
                ((InputBehaviorConfiguration) this.instance).setActionValue(i);
                return this;
            }

            public Builder setBehavior(InputBehavior inputBehavior) {
                copyOnWrite();
                ((InputBehaviorConfiguration) this.instance).setBehavior(inputBehavior);
                return this;
            }

            public Builder setBehaviorValue(int i) {
                copyOnWrite();
                ((InputBehaviorConfiguration) this.instance).setBehaviorValue(i);
                return this;
            }

            public Builder setSource(InputSource inputSource) {
                copyOnWrite();
                ((InputBehaviorConfiguration) this.instance).setSource(inputSource);
                return this;
            }

            public Builder setSourceValue(int i) {
                copyOnWrite();
                ((InputBehaviorConfiguration) this.instance).setSourceValue(i);
                return this;
            }

            private Builder() {
                super(InputBehaviorConfiguration.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private InputBehaviorConfiguration() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAction() {
            this.action_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBehavior() {
            this.behavior_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSource() {
            this.source_ = 0;
        }

        public static InputBehaviorConfiguration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static InputBehaviorConfiguration parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InputBehaviorConfiguration parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<InputBehaviorConfiguration> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAction(InputAction inputAction) {
            if (inputAction != null) {
                this.action_ = inputAction.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActionValue(int i) {
            this.action_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBehavior(InputBehavior inputBehavior) {
            if (inputBehavior != null) {
                this.behavior_ = inputBehavior.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBehaviorValue(int i) {
            this.behavior_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSource(InputSource inputSource) {
            if (inputSource != null) {
                this.source_ = inputSource.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSourceValue(int i) {
            this.source_ = i;
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
                    InputBehaviorConfiguration inputBehaviorConfiguration = (InputBehaviorConfiguration) obj2;
                    this.action_ = visitor.visitInt(this.action_ != 0, this.action_, inputBehaviorConfiguration.action_ != 0, inputBehaviorConfiguration.action_);
                    this.source_ = visitor.visitInt(this.source_ != 0, this.source_, inputBehaviorConfiguration.source_ != 0, inputBehaviorConfiguration.source_);
                    boolean z2 = this.behavior_ != 0;
                    int i = this.behavior_;
                    if (inputBehaviorConfiguration.behavior_ != 0) {
                        z = true;
                    }
                    this.behavior_ = visitor.visitInt(z2, i, z, inputBehaviorConfiguration.behavior_);
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
                                    this.action_ = codedInputStream.readEnum();
                                } else if (readTag == 16) {
                                    this.source_ = codedInputStream.readEnum();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.behavior_ = codedInputStream.readEnum();
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
                    return new InputBehaviorConfiguration();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (InputBehaviorConfiguration.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
        public InputAction getAction() {
            InputAction forNumber = InputAction.forNumber(this.action_);
            return forNumber == null ? InputAction.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
        public int getActionValue() {
            return this.action_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
        public InputBehavior getBehavior() {
            InputBehavior forNumber = InputBehavior.forNumber(this.behavior_);
            return forNumber == null ? InputBehavior.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
        public int getBehaviorValue() {
            return this.behavior_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.action_ != InputAction.INPUT_ACTION_OTHER.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.action_);
            }
            if (this.source_ != InputSource.INPUT_SOURCE_OTHER.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.source_);
            }
            if (this.behavior_ != InputBehavior.INPUT_BEHAVIOR_UNDEFINED.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.behavior_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
        public InputSource getSource() {
            InputSource forNumber = InputSource.forNumber(this.source_);
            return forNumber == null ? InputSource.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationOrBuilder
        public int getSourceValue() {
            return this.source_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.action_ != InputAction.INPUT_ACTION_OTHER.getNumber()) {
                codedOutputStream.writeEnum(1, this.action_);
            }
            if (this.source_ != InputSource.INPUT_SOURCE_OTHER.getNumber()) {
                codedOutputStream.writeEnum(2, this.source_);
            }
            if (this.behavior_ != InputBehavior.INPUT_BEHAVIOR_UNDEFINED.getNumber()) {
                codedOutputStream.writeEnum(3, this.behavior_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(InputBehaviorConfiguration inputBehaviorConfiguration) {
            return DEFAULT_INSTANCE.createBuilder(inputBehaviorConfiguration);
        }

        public static InputBehaviorConfiguration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static InputBehaviorConfiguration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static InputBehaviorConfiguration parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static InputBehaviorConfiguration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static InputBehaviorConfiguration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InputBehaviorConfiguration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static InputBehaviorConfiguration parseFrom(InputStream inputStream) throws IOException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InputBehaviorConfiguration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static InputBehaviorConfiguration parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static InputBehaviorConfiguration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InputBehaviorConfiguration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface InputBehaviorConfigurationOrBuilder extends MessageLiteOrBuilder {
        InputAction getAction();

        int getActionValue();

        InputBehavior getBehavior();

        int getBehaviorValue();

        InputSource getSource();

        int getSourceValue();
    }

    /* loaded from: classes6.dex */
    public static final class InputBehaviorConfigurationSet extends GeneratedMessageLite<InputBehaviorConfigurationSet, Builder> implements InputBehaviorConfigurationSetOrBuilder {
        public static final int CONFIGURATIONS_FIELD_NUMBER = 1;
        private static final InputBehaviorConfigurationSet DEFAULT_INSTANCE = new InputBehaviorConfigurationSet();
        private static volatile Parser<InputBehaviorConfigurationSet> PARSER;
        private Internal.ProtobufList<InputBehaviorConfiguration> configurations_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<InputBehaviorConfigurationSet, Builder> implements InputBehaviorConfigurationSetOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllConfigurations(Iterable<? extends InputBehaviorConfiguration> iterable) {
                copyOnWrite();
                ((InputBehaviorConfigurationSet) this.instance).addAllConfigurations(iterable);
                return this;
            }

            public Builder addConfigurations(InputBehaviorConfiguration inputBehaviorConfiguration) {
                copyOnWrite();
                ((InputBehaviorConfigurationSet) this.instance).addConfigurations(inputBehaviorConfiguration);
                return this;
            }

            public Builder clearConfigurations() {
                copyOnWrite();
                ((InputBehaviorConfigurationSet) this.instance).clearConfigurations();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationSetOrBuilder
            public InputBehaviorConfiguration getConfigurations(int i) {
                return ((InputBehaviorConfigurationSet) this.instance).getConfigurations(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationSetOrBuilder
            public int getConfigurationsCount() {
                return ((InputBehaviorConfigurationSet) this.instance).getConfigurationsCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationSetOrBuilder
            public List<InputBehaviorConfiguration> getConfigurationsList() {
                return Collections.unmodifiableList(((InputBehaviorConfigurationSet) this.instance).getConfigurationsList());
            }

            public Builder removeConfigurations(int i) {
                copyOnWrite();
                ((InputBehaviorConfigurationSet) this.instance).removeConfigurations(i);
                return this;
            }

            public Builder setConfigurations(int i, InputBehaviorConfiguration inputBehaviorConfiguration) {
                copyOnWrite();
                ((InputBehaviorConfigurationSet) this.instance).setConfigurations(i, inputBehaviorConfiguration);
                return this;
            }

            private Builder() {
                super(InputBehaviorConfigurationSet.DEFAULT_INSTANCE);
            }

            public Builder addConfigurations(int i, InputBehaviorConfiguration inputBehaviorConfiguration) {
                copyOnWrite();
                ((InputBehaviorConfigurationSet) this.instance).addConfigurations(i, inputBehaviorConfiguration);
                return this;
            }

            public Builder setConfigurations(int i, InputBehaviorConfiguration.Builder builder) {
                copyOnWrite();
                ((InputBehaviorConfigurationSet) this.instance).setConfigurations(i, builder);
                return this;
            }

            public Builder addConfigurations(InputBehaviorConfiguration.Builder builder) {
                copyOnWrite();
                ((InputBehaviorConfigurationSet) this.instance).addConfigurations(builder);
                return this;
            }

            public Builder addConfigurations(int i, InputBehaviorConfiguration.Builder builder) {
                copyOnWrite();
                ((InputBehaviorConfigurationSet) this.instance).addConfigurations(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private InputBehaviorConfigurationSet() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllConfigurations(Iterable<? extends InputBehaviorConfiguration> iterable) {
            ensureConfigurationsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.configurations_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addConfigurations(InputBehaviorConfiguration inputBehaviorConfiguration) {
            if (inputBehaviorConfiguration != null) {
                ensureConfigurationsIsMutable();
                this.configurations_.add(inputBehaviorConfiguration);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConfigurations() {
            this.configurations_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureConfigurationsIsMutable() {
            if (!this.configurations_.isModifiable()) {
                this.configurations_ = GeneratedMessageLite.mutableCopy(this.configurations_);
            }
        }

        public static InputBehaviorConfigurationSet getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static InputBehaviorConfigurationSet parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InputBehaviorConfigurationSet parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<InputBehaviorConfigurationSet> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeConfigurations(int i) {
            ensureConfigurationsIsMutable();
            this.configurations_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfigurations(int i, InputBehaviorConfiguration inputBehaviorConfiguration) {
            if (inputBehaviorConfiguration != null) {
                ensureConfigurationsIsMutable();
                this.configurations_.set(i, inputBehaviorConfiguration);
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
                    this.configurations_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.configurations_, ((InputBehaviorConfigurationSet) obj2).configurations_);
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
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag != 10) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    if (!this.configurations_.isModifiable()) {
                                        this.configurations_ = GeneratedMessageLite.mutableCopy(this.configurations_);
                                    }
                                    this.configurations_.add(codedInputStream.readMessage(InputBehaviorConfiguration.parser(), extensionRegistryLite));
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
                    this.configurations_.makeImmutable();
                    return null;
                case 6:
                    return new InputBehaviorConfigurationSet();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (InputBehaviorConfigurationSet.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationSetOrBuilder
        public InputBehaviorConfiguration getConfigurations(int i) {
            return this.configurations_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationSetOrBuilder
        public int getConfigurationsCount() {
            return this.configurations_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.InputBehaviorConfigurationSetOrBuilder
        public List<InputBehaviorConfiguration> getConfigurationsList() {
            return this.configurations_;
        }

        public InputBehaviorConfigurationOrBuilder getConfigurationsOrBuilder(int i) {
            return this.configurations_.get(i);
        }

        public List<? extends InputBehaviorConfigurationOrBuilder> getConfigurationsOrBuilderList() {
            return this.configurations_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.configurations_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.configurations_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.configurations_.size(); i++) {
                codedOutputStream.writeMessage(1, this.configurations_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(InputBehaviorConfigurationSet inputBehaviorConfigurationSet) {
            return DEFAULT_INSTANCE.createBuilder(inputBehaviorConfigurationSet);
        }

        public static InputBehaviorConfigurationSet parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static InputBehaviorConfigurationSet parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static InputBehaviorConfigurationSet parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addConfigurations(int i, InputBehaviorConfiguration inputBehaviorConfiguration) {
            if (inputBehaviorConfiguration != null) {
                ensureConfigurationsIsMutable();
                this.configurations_.add(i, inputBehaviorConfiguration);
                return;
            }
            throw new NullPointerException();
        }

        public static InputBehaviorConfigurationSet parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfigurations(int i, InputBehaviorConfiguration.Builder builder) {
            ensureConfigurationsIsMutable();
            this.configurations_.set(i, builder.mo10084build());
        }

        public static InputBehaviorConfigurationSet parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InputBehaviorConfigurationSet parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addConfigurations(InputBehaviorConfiguration.Builder builder) {
            ensureConfigurationsIsMutable();
            this.configurations_.add(builder.mo10084build());
        }

        public static InputBehaviorConfigurationSet parseFrom(InputStream inputStream) throws IOException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InputBehaviorConfigurationSet parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addConfigurations(int i, InputBehaviorConfiguration.Builder builder) {
            ensureConfigurationsIsMutable();
            this.configurations_.add(i, builder.mo10084build());
        }

        public static InputBehaviorConfigurationSet parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static InputBehaviorConfigurationSet parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InputBehaviorConfigurationSet) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface InputBehaviorConfigurationSetOrBuilder extends MessageLiteOrBuilder {
        InputBehaviorConfiguration getConfigurations(int i);

        int getConfigurationsCount();

        List<InputBehaviorConfiguration> getConfigurationsList();
    }

    /* loaded from: classes6.dex */
    public enum InputSource implements Internal.EnumLite {
        INPUT_SOURCE_OTHER(0),
        INPUT_SOURCE_TOUCHPAD(1),
        INPUT_SOURCE_VOLUME_UP(2),
        INPUT_SOURCE_VOLUME_DOWN(3),
        INPUT_SOURCE_ACTION(4),
        INPUT_SOURCE_FRONT_RIGHT(5),
        INPUT_SOURCE_BACK_RIGHT(6),
        UNRECOGNIZED(-1);
        
        public static final int INPUT_SOURCE_ACTION_VALUE = 4;
        public static final int INPUT_SOURCE_BACK_RIGHT_VALUE = 6;
        public static final int INPUT_SOURCE_FRONT_RIGHT_VALUE = 5;
        public static final int INPUT_SOURCE_OTHER_VALUE = 0;
        public static final int INPUT_SOURCE_TOUCHPAD_VALUE = 1;
        public static final int INPUT_SOURCE_VOLUME_DOWN_VALUE = 3;
        public static final int INPUT_SOURCE_VOLUME_UP_VALUE = 2;
        private static final Internal.EnumLiteMap<InputSource> internalValueMap = new Internal.EnumLiteMap<InputSource>() { // from class: com.amazon.alexa.accessory.protocol.Input.InputSource.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public InputSource mo9850findValueByNumber(int i) {
                return InputSource.forNumber(i);
            }
        };
        private final int value;

        InputSource(int i) {
            this.value = i;
        }

        public static InputSource forNumber(int i) {
            switch (i) {
                case 0:
                    return INPUT_SOURCE_OTHER;
                case 1:
                    return INPUT_SOURCE_TOUCHPAD;
                case 2:
                    return INPUT_SOURCE_VOLUME_UP;
                case 3:
                    return INPUT_SOURCE_VOLUME_DOWN;
                case 4:
                    return INPUT_SOURCE_ACTION;
                case 5:
                    return INPUT_SOURCE_FRONT_RIGHT;
                case 6:
                    return INPUT_SOURCE_BACK_RIGHT;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<InputSource> internalGetValueMap() {
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
        public static InputSource valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class IssueInputEvent extends GeneratedMessageLite<IssueInputEvent, Builder> implements IssueInputEventOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 2;
        public static final int BEHAVIOR_FIELD_NUMBER = 4;
        private static final IssueInputEvent DEFAULT_INSTANCE = new IssueInputEvent();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<IssueInputEvent> PARSER = null;
        public static final int SOURCE_FIELD_NUMBER = 3;
        private int action_;
        private int behavior_;
        private int deviceId_;
        private int source_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<IssueInputEvent, Builder> implements IssueInputEventOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((IssueInputEvent) this.instance).clearAction();
                return this;
            }

            public Builder clearBehavior() {
                copyOnWrite();
                ((IssueInputEvent) this.instance).clearBehavior();
                return this;
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((IssueInputEvent) this.instance).clearDeviceId();
                return this;
            }

            public Builder clearSource() {
                copyOnWrite();
                ((IssueInputEvent) this.instance).clearSource();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
            public InputAction getAction() {
                return ((IssueInputEvent) this.instance).getAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
            public int getActionValue() {
                return ((IssueInputEvent) this.instance).getActionValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
            public InputBehavior getBehavior() {
                return ((IssueInputEvent) this.instance).getBehavior();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
            public int getBehaviorValue() {
                return ((IssueInputEvent) this.instance).getBehaviorValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
            public int getDeviceId() {
                return ((IssueInputEvent) this.instance).getDeviceId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
            public InputSource getSource() {
                return ((IssueInputEvent) this.instance).getSource();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
            public int getSourceValue() {
                return ((IssueInputEvent) this.instance).getSourceValue();
            }

            public Builder setAction(InputAction inputAction) {
                copyOnWrite();
                ((IssueInputEvent) this.instance).setAction(inputAction);
                return this;
            }

            public Builder setActionValue(int i) {
                copyOnWrite();
                ((IssueInputEvent) this.instance).setActionValue(i);
                return this;
            }

            public Builder setBehavior(InputBehavior inputBehavior) {
                copyOnWrite();
                ((IssueInputEvent) this.instance).setBehavior(inputBehavior);
                return this;
            }

            public Builder setBehaviorValue(int i) {
                copyOnWrite();
                ((IssueInputEvent) this.instance).setBehaviorValue(i);
                return this;
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((IssueInputEvent) this.instance).setDeviceId(i);
                return this;
            }

            public Builder setSource(InputSource inputSource) {
                copyOnWrite();
                ((IssueInputEvent) this.instance).setSource(inputSource);
                return this;
            }

            public Builder setSourceValue(int i) {
                copyOnWrite();
                ((IssueInputEvent) this.instance).setSourceValue(i);
                return this;
            }

            private Builder() {
                super(IssueInputEvent.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private IssueInputEvent() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAction() {
            this.action_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBehavior() {
            this.behavior_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSource() {
            this.source_ = 0;
        }

        public static IssueInputEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static IssueInputEvent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (IssueInputEvent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueInputEvent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<IssueInputEvent> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAction(InputAction inputAction) {
            if (inputAction != null) {
                this.action_ = inputAction.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActionValue(int i) {
            this.action_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBehavior(InputBehavior inputBehavior) {
            if (inputBehavior != null) {
                this.behavior_ = inputBehavior.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBehaviorValue(int i) {
            this.behavior_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceId(int i) {
            this.deviceId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSource(InputSource inputSource) {
            if (inputSource != null) {
                this.source_ = inputSource.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSourceValue(int i) {
            this.source_ = i;
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
                    IssueInputEvent issueInputEvent = (IssueInputEvent) obj2;
                    this.deviceId_ = visitor.visitInt(this.deviceId_ != 0, this.deviceId_, issueInputEvent.deviceId_ != 0, issueInputEvent.deviceId_);
                    this.action_ = visitor.visitInt(this.action_ != 0, this.action_, issueInputEvent.action_ != 0, issueInputEvent.action_);
                    this.source_ = visitor.visitInt(this.source_ != 0, this.source_, issueInputEvent.source_ != 0, issueInputEvent.source_);
                    boolean z2 = this.behavior_ != 0;
                    int i = this.behavior_;
                    if (issueInputEvent.behavior_ != 0) {
                        z = true;
                    }
                    this.behavior_ = visitor.visitInt(z2, i, z, issueInputEvent.behavior_);
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
                                    this.deviceId_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.action_ = codedInputStream.readEnum();
                                } else if (readTag == 24) {
                                    this.source_ = codedInputStream.readEnum();
                                } else if (readTag != 32) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.behavior_ = codedInputStream.readEnum();
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
                    return new IssueInputEvent();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (IssueInputEvent.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
        public InputAction getAction() {
            InputAction forNumber = InputAction.forNumber(this.action_);
            return forNumber == null ? InputAction.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
        public int getActionValue() {
            return this.action_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
        public InputBehavior getBehavior() {
            InputBehavior forNumber = InputBehavior.forNumber(this.behavior_);
            return forNumber == null ? InputBehavior.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
        public int getBehaviorValue() {
            return this.behavior_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
        public int getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.deviceId_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (this.action_ != InputAction.INPUT_ACTION_OTHER.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(2, this.action_);
            }
            if (this.source_ != InputSource.INPUT_SOURCE_OTHER.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(3, this.source_);
            }
            if (this.behavior_ != InputBehavior.INPUT_BEHAVIOR_UNDEFINED.getNumber()) {
                i2 += CodedOutputStream.computeEnumSize(4, this.behavior_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
        public InputSource getSource() {
            InputSource forNumber = InputSource.forNumber(this.source_);
            return forNumber == null ? InputSource.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.IssueInputEventOrBuilder
        public int getSourceValue() {
            return this.source_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.deviceId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.action_ != InputAction.INPUT_ACTION_OTHER.getNumber()) {
                codedOutputStream.writeEnum(2, this.action_);
            }
            if (this.source_ != InputSource.INPUT_SOURCE_OTHER.getNumber()) {
                codedOutputStream.writeEnum(3, this.source_);
            }
            if (this.behavior_ != InputBehavior.INPUT_BEHAVIOR_UNDEFINED.getNumber()) {
                codedOutputStream.writeEnum(4, this.behavior_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(IssueInputEvent issueInputEvent) {
            return DEFAULT_INSTANCE.createBuilder(issueInputEvent);
        }

        public static IssueInputEvent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueInputEvent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueInputEvent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static IssueInputEvent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static IssueInputEvent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static IssueInputEvent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static IssueInputEvent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static IssueInputEvent parseFrom(InputStream inputStream) throws IOException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static IssueInputEvent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static IssueInputEvent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static IssueInputEvent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (IssueInputEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface IssueInputEventOrBuilder extends MessageLiteOrBuilder {
        InputAction getAction();

        int getActionValue();

        InputBehavior getBehavior();

        int getBehaviorValue();

        int getDeviceId();

        InputSource getSource();

        int getSourceValue();
    }

    /* loaded from: classes6.dex */
    public static final class ResetInputBehavior extends GeneratedMessageLite<ResetInputBehavior, Builder> implements ResetInputBehaviorOrBuilder {
        private static final ResetInputBehavior DEFAULT_INSTANCE = new ResetInputBehavior();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<ResetInputBehavior> PARSER;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ResetInputBehavior, Builder> implements ResetInputBehaviorOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((ResetInputBehavior) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.ResetInputBehaviorOrBuilder
            public int getDeviceId() {
                return ((ResetInputBehavior) this.instance).getDeviceId();
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((ResetInputBehavior) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(ResetInputBehavior.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ResetInputBehavior() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        public static ResetInputBehavior getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ResetInputBehavior parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ResetInputBehavior) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetInputBehavior parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ResetInputBehavior> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceId(int i) {
            this.deviceId_ = i;
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
                    ResetInputBehavior resetInputBehavior = (ResetInputBehavior) obj2;
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (resetInputBehavior.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, resetInputBehavior.deviceId_);
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
                                        this.deviceId_ = codedInputStream.readUInt32();
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
                    return new ResetInputBehavior();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ResetInputBehavior.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Input.ResetInputBehaviorOrBuilder
        public int getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.deviceId_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.deviceId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ResetInputBehavior resetInputBehavior) {
            return DEFAULT_INSTANCE.createBuilder(resetInputBehavior);
        }

        public static ResetInputBehavior parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetInputBehavior) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetInputBehavior parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ResetInputBehavior parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ResetInputBehavior parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ResetInputBehavior parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ResetInputBehavior parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ResetInputBehavior parseFrom(InputStream inputStream) throws IOException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ResetInputBehavior parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ResetInputBehavior parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ResetInputBehavior parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ResetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ResetInputBehaviorOrBuilder extends MessageLiteOrBuilder {
        int getDeviceId();
    }

    /* loaded from: classes6.dex */
    public static final class SetInputBehavior extends GeneratedMessageLite<SetInputBehavior, Builder> implements SetInputBehaviorOrBuilder {
        public static final int CONFIGURATION_FIELD_NUMBER = 2;
        private static final SetInputBehavior DEFAULT_INSTANCE = new SetInputBehavior();
        public static final int DEVICE_ID_FIELD_NUMBER = 1;
        private static volatile Parser<SetInputBehavior> PARSER;
        private InputBehaviorConfiguration configuration_;
        private int deviceId_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SetInputBehavior, Builder> implements SetInputBehaviorOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearConfiguration() {
                copyOnWrite();
                ((SetInputBehavior) this.instance).clearConfiguration();
                return this;
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((SetInputBehavior) this.instance).clearDeviceId();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.SetInputBehaviorOrBuilder
            public InputBehaviorConfiguration getConfiguration() {
                return ((SetInputBehavior) this.instance).getConfiguration();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.SetInputBehaviorOrBuilder
            public int getDeviceId() {
                return ((SetInputBehavior) this.instance).getDeviceId();
            }

            @Override // com.amazon.alexa.accessory.protocol.Input.SetInputBehaviorOrBuilder
            public boolean hasConfiguration() {
                return ((SetInputBehavior) this.instance).hasConfiguration();
            }

            public Builder mergeConfiguration(InputBehaviorConfiguration inputBehaviorConfiguration) {
                copyOnWrite();
                ((SetInputBehavior) this.instance).mergeConfiguration(inputBehaviorConfiguration);
                return this;
            }

            public Builder setConfiguration(InputBehaviorConfiguration inputBehaviorConfiguration) {
                copyOnWrite();
                ((SetInputBehavior) this.instance).setConfiguration(inputBehaviorConfiguration);
                return this;
            }

            public Builder setDeviceId(int i) {
                copyOnWrite();
                ((SetInputBehavior) this.instance).setDeviceId(i);
                return this;
            }

            private Builder() {
                super(SetInputBehavior.DEFAULT_INSTANCE);
            }

            public Builder setConfiguration(InputBehaviorConfiguration.Builder builder) {
                copyOnWrite();
                ((SetInputBehavior) this.instance).setConfiguration(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SetInputBehavior() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConfiguration() {
            this.configuration_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = 0;
        }

        public static SetInputBehavior getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeConfiguration(InputBehaviorConfiguration inputBehaviorConfiguration) {
            InputBehaviorConfiguration inputBehaviorConfiguration2 = this.configuration_;
            if (inputBehaviorConfiguration2 != null && inputBehaviorConfiguration2 != InputBehaviorConfiguration.getDefaultInstance()) {
                this.configuration_ = InputBehaviorConfiguration.newBuilder(this.configuration_).mergeFrom((InputBehaviorConfiguration.Builder) inputBehaviorConfiguration).mo10085buildPartial();
            } else {
                this.configuration_ = inputBehaviorConfiguration;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SetInputBehavior parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SetInputBehavior) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetInputBehavior parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SetInputBehavior> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfiguration(InputBehaviorConfiguration inputBehaviorConfiguration) {
            if (inputBehaviorConfiguration != null) {
                this.configuration_ = inputBehaviorConfiguration;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceId(int i) {
            this.deviceId_ = i;
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
                    SetInputBehavior setInputBehavior = (SetInputBehavior) obj2;
                    boolean z2 = this.deviceId_ != 0;
                    int i = this.deviceId_;
                    if (setInputBehavior.deviceId_ != 0) {
                        z = true;
                    }
                    this.deviceId_ = visitor.visitInt(z2, i, z, setInputBehavior.deviceId_);
                    this.configuration_ = (InputBehaviorConfiguration) visitor.visitMessage(this.configuration_, setInputBehavior.configuration_);
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
                                    this.deviceId_ = codedInputStream.readUInt32();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    InputBehaviorConfiguration.Builder mo10081toBuilder = this.configuration_ != null ? this.configuration_.mo10081toBuilder() : null;
                                    this.configuration_ = (InputBehaviorConfiguration) codedInputStream.readMessage(InputBehaviorConfiguration.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((InputBehaviorConfiguration.Builder) this.configuration_);
                                        this.configuration_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new SetInputBehavior();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SetInputBehavior.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Input.SetInputBehaviorOrBuilder
        public InputBehaviorConfiguration getConfiguration() {
            InputBehaviorConfiguration inputBehaviorConfiguration = this.configuration_;
            return inputBehaviorConfiguration == null ? InputBehaviorConfiguration.getDefaultInstance() : inputBehaviorConfiguration;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.SetInputBehaviorOrBuilder
        public int getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.deviceId_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (this.configuration_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getConfiguration());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Input.SetInputBehaviorOrBuilder
        public boolean hasConfiguration() {
            return this.configuration_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.deviceId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.configuration_ != null) {
                codedOutputStream.writeMessage(2, getConfiguration());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SetInputBehavior setInputBehavior) {
            return DEFAULT_INSTANCE.createBuilder(setInputBehavior);
        }

        public static SetInputBehavior parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetInputBehavior) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetInputBehavior parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SetInputBehavior parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfiguration(InputBehaviorConfiguration.Builder builder) {
            this.configuration_ = builder.mo10084build();
        }

        public static SetInputBehavior parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SetInputBehavior parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SetInputBehavior parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SetInputBehavior parseFrom(InputStream inputStream) throws IOException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetInputBehavior parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetInputBehavior parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SetInputBehavior parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetInputBehavior) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SetInputBehaviorOrBuilder extends MessageLiteOrBuilder {
        InputBehaviorConfiguration getConfiguration();

        int getDeviceId();

        boolean hasConfiguration();
    }

    private Input() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
