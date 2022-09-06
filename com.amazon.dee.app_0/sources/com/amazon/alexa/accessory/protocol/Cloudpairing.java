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
public final class Cloudpairing {

    /* renamed from: com.amazon.alexa.accessory.protocol.Cloudpairing$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$CloudPairingKeys$KeysCase = new int[CloudPairingKeys.KeysCase.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Seed$SeedTypesCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$CloudPairingKeys$KeysCase[CloudPairingKeys.KeysCase.BLUETOOTH_KEYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$CloudPairingKeys$KeysCase[CloudPairingKeys.KeysCase.KEYS_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Seed$SeedTypesCase = new int[Seed.SeedTypesCase.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Seed$SeedTypesCase[Seed.SeedTypesCase.BLUETOOTH_SEED.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Seed$SeedTypesCase[Seed.SeedTypesCase.SEEDTYPES_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 10;
            } catch (NoSuchFieldError unused14) {
            }
            $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase = new int[Capability.CapabilityTypesCase.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase[Capability.CapabilityTypesCase.BLUETOOTH_SERVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase[Capability.CapabilityTypesCase.BLUETOOTH_TRANSPORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase[Capability.CapabilityTypesCase.ACCESSORY_FEATURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase[Capability.CapabilityTypesCase.ACCESSORY_CATEGORY.ordinal()] = 4;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase[Capability.CapabilityTypesCase.CAPABILITYTYPES_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class BluetoothKeys extends GeneratedMessageLite<BluetoothKeys, Builder> implements BluetoothKeysOrBuilder {
        private static final BluetoothKeys DEFAULT_INSTANCE = new BluetoothKeys();
        private static volatile Parser<BluetoothKeys> PARSER = null;
        public static final int SEED_FIELD_NUMBER = 1;
        public static final int SYMMETRIC_KEY_FIELD_NUMBER = 2;
        private Seed seed_;
        private ByteString symmetricKey_ = ByteString.EMPTY;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<BluetoothKeys, Builder> implements BluetoothKeysOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearSeed() {
                copyOnWrite();
                ((BluetoothKeys) this.instance).clearSeed();
                return this;
            }

            public Builder clearSymmetricKey() {
                copyOnWrite();
                ((BluetoothKeys) this.instance).clearSymmetricKey();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.BluetoothKeysOrBuilder
            public Seed getSeed() {
                return ((BluetoothKeys) this.instance).getSeed();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.BluetoothKeysOrBuilder
            public ByteString getSymmetricKey() {
                return ((BluetoothKeys) this.instance).getSymmetricKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.BluetoothKeysOrBuilder
            public boolean hasSeed() {
                return ((BluetoothKeys) this.instance).hasSeed();
            }

            public Builder mergeSeed(Seed seed) {
                copyOnWrite();
                ((BluetoothKeys) this.instance).mergeSeed(seed);
                return this;
            }

            public Builder setSeed(Seed seed) {
                copyOnWrite();
                ((BluetoothKeys) this.instance).setSeed(seed);
                return this;
            }

            public Builder setSymmetricKey(ByteString byteString) {
                copyOnWrite();
                ((BluetoothKeys) this.instance).setSymmetricKey(byteString);
                return this;
            }

            private Builder() {
                super(BluetoothKeys.DEFAULT_INSTANCE);
            }

            public Builder setSeed(Seed.Builder builder) {
                copyOnWrite();
                ((BluetoothKeys) this.instance).setSeed(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private BluetoothKeys() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSeed() {
            this.seed_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSymmetricKey() {
            this.symmetricKey_ = getDefaultInstance().getSymmetricKey();
        }

        public static BluetoothKeys getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSeed(Seed seed) {
            Seed seed2 = this.seed_;
            if (seed2 != null && seed2 != Seed.getDefaultInstance()) {
                this.seed_ = Seed.newBuilder(this.seed_).mergeFrom((Seed.Builder) seed).mo10085buildPartial();
            } else {
                this.seed_ = seed;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static BluetoothKeys parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BluetoothKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BluetoothKeys parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<BluetoothKeys> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSeed(Seed seed) {
            if (seed != null) {
                this.seed_ = seed;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSymmetricKey(ByteString byteString) {
            if (byteString != null) {
                this.symmetricKey_ = byteString;
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
                    BluetoothKeys bluetoothKeys = (BluetoothKeys) obj2;
                    this.seed_ = (Seed) visitor.visitMessage(this.seed_, bluetoothKeys.seed_);
                    boolean z2 = this.symmetricKey_ != ByteString.EMPTY;
                    ByteString byteString = this.symmetricKey_;
                    if (bluetoothKeys.symmetricKey_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.symmetricKey_ = visitor.visitByteString(z2, byteString, z, bluetoothKeys.symmetricKey_);
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
                                if (readTag == 10) {
                                    Seed.Builder mo10081toBuilder = this.seed_ != null ? this.seed_.mo10081toBuilder() : null;
                                    this.seed_ = (Seed) codedInputStream.readMessage(Seed.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Seed.Builder) this.seed_);
                                        this.seed_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.symmetricKey_ = codedInputStream.readBytes();
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
                    return new BluetoothKeys();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (BluetoothKeys.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.BluetoothKeysOrBuilder
        public Seed getSeed() {
            Seed seed = this.seed_;
            return seed == null ? Seed.getDefaultInstance() : seed;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.seed_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getSeed());
            }
            if (!this.symmetricKey_.isEmpty()) {
                i2 += CodedOutputStream.computeBytesSize(2, this.symmetricKey_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.BluetoothKeysOrBuilder
        public ByteString getSymmetricKey() {
            return this.symmetricKey_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.BluetoothKeysOrBuilder
        public boolean hasSeed() {
            return this.seed_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.seed_ != null) {
                codedOutputStream.writeMessage(1, getSeed());
            }
            if (!this.symmetricKey_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.symmetricKey_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(BluetoothKeys bluetoothKeys) {
            return DEFAULT_INSTANCE.createBuilder(bluetoothKeys);
        }

        public static BluetoothKeys parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BluetoothKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BluetoothKeys parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static BluetoothKeys parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSeed(Seed.Builder builder) {
            this.seed_ = builder.mo10084build();
        }

        public static BluetoothKeys parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static BluetoothKeys parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static BluetoothKeys parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static BluetoothKeys parseFrom(InputStream inputStream) throws IOException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BluetoothKeys parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static BluetoothKeys parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static BluetoothKeys parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BluetoothKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface BluetoothKeysOrBuilder extends MessageLiteOrBuilder {
        Seed getSeed();

        ByteString getSymmetricKey();

        boolean hasSeed();
    }

    /* loaded from: classes6.dex */
    public static final class Capability extends GeneratedMessageLite<Capability, Builder> implements CapabilityOrBuilder {
        public static final int ACCESSORY_CATEGORY_FIELD_NUMBER = 4;
        public static final int ACCESSORY_FEATURE_FIELD_NUMBER = 3;
        public static final int BLUETOOTH_SERVICE_FIELD_NUMBER = 1;
        public static final int BLUETOOTH_TRANSPORT_FIELD_NUMBER = 2;
        private static final Capability DEFAULT_INSTANCE = new Capability();
        private static volatile Parser<Capability> PARSER;
        private int capabilityTypesCase_ = 0;
        private Object capabilityTypes_;

        /* loaded from: classes6.dex */
        public enum AccessoryCategory implements Internal.EnumLite {
            HEARABLE(0),
            WEARABLE(1),
            UNRECOGNIZED(-1);
            
            public static final int HEARABLE_VALUE = 0;
            public static final int WEARABLE_VALUE = 1;
            private static final Internal.EnumLiteMap<AccessoryCategory> internalValueMap = new Internal.EnumLiteMap<AccessoryCategory>() { // from class: com.amazon.alexa.accessory.protocol.Cloudpairing.Capability.AccessoryCategory.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public AccessoryCategory mo9850findValueByNumber(int i) {
                    return AccessoryCategory.forNumber(i);
                }
            };
            private final int value;

            AccessoryCategory(int i) {
                this.value = i;
            }

            public static AccessoryCategory forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return WEARABLE;
                    }
                    return null;
                }
                return HEARABLE;
            }

            public static Internal.EnumLiteMap<AccessoryCategory> internalGetValueMap() {
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
            public static AccessoryCategory valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public enum AccessoryFeature implements Internal.EnumLite {
            FIND_MY(0),
            SEAMLESS_SWITCHING(1),
            UNRECOGNIZED(-1);
            
            public static final int FIND_MY_VALUE = 0;
            public static final int SEAMLESS_SWITCHING_VALUE = 1;
            private static final Internal.EnumLiteMap<AccessoryFeature> internalValueMap = new Internal.EnumLiteMap<AccessoryFeature>() { // from class: com.amazon.alexa.accessory.protocol.Cloudpairing.Capability.AccessoryFeature.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public AccessoryFeature mo9850findValueByNumber(int i) {
                    return AccessoryFeature.forNumber(i);
                }
            };
            private final int value;

            AccessoryFeature(int i) {
                this.value = i;
            }

            public static AccessoryFeature forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return SEAMLESS_SWITCHING;
                    }
                    return null;
                }
                return FIND_MY;
            }

            public static Internal.EnumLiteMap<AccessoryFeature> internalGetValueMap() {
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
            public static AccessoryFeature valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public enum BluetoothService implements Internal.EnumLite {
            HFP(0),
            A2DP(1),
            AVRCP(2),
            UNRECOGNIZED(-1);
            
            public static final int A2DP_VALUE = 1;
            public static final int AVRCP_VALUE = 2;
            public static final int HFP_VALUE = 0;
            private static final Internal.EnumLiteMap<BluetoothService> internalValueMap = new Internal.EnumLiteMap<BluetoothService>() { // from class: com.amazon.alexa.accessory.protocol.Cloudpairing.Capability.BluetoothService.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public BluetoothService mo9850findValueByNumber(int i) {
                    return BluetoothService.forNumber(i);
                }
            };
            private final int value;

            BluetoothService(int i) {
                this.value = i;
            }

            public static BluetoothService forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return A2DP;
                    }
                    if (i == 2) {
                        return AVRCP;
                    }
                    return null;
                }
                return HFP;
            }

            public static Internal.EnumLiteMap<BluetoothService> internalGetValueMap() {
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
            public static BluetoothService valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public enum BluetoothTransport implements Internal.EnumLite {
            BT_CLASSIC(0),
            BLE(1),
            UNRECOGNIZED(-1);
            
            public static final int BLE_VALUE = 1;
            public static final int BT_CLASSIC_VALUE = 0;
            private static final Internal.EnumLiteMap<BluetoothTransport> internalValueMap = new Internal.EnumLiteMap<BluetoothTransport>() { // from class: com.amazon.alexa.accessory.protocol.Cloudpairing.Capability.BluetoothTransport.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                /* renamed from: findValueByNumber */
                public BluetoothTransport mo9850findValueByNumber(int i) {
                    return BluetoothTransport.forNumber(i);
                }
            };
            private final int value;

            BluetoothTransport(int i) {
                this.value = i;
            }

            public static BluetoothTransport forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return BLE;
                    }
                    return null;
                }
                return BT_CLASSIC;
            }

            public static Internal.EnumLiteMap<BluetoothTransport> internalGetValueMap() {
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
            public static BluetoothTransport valueOf(int i) {
                return forNumber(i);
            }
        }

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Capability, Builder> implements CapabilityOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAccessoryCategory() {
                copyOnWrite();
                ((Capability) this.instance).clearAccessoryCategory();
                return this;
            }

            public Builder clearAccessoryFeature() {
                copyOnWrite();
                ((Capability) this.instance).clearAccessoryFeature();
                return this;
            }

            public Builder clearBluetoothService() {
                copyOnWrite();
                ((Capability) this.instance).clearBluetoothService();
                return this;
            }

            public Builder clearBluetoothTransport() {
                copyOnWrite();
                ((Capability) this.instance).clearBluetoothTransport();
                return this;
            }

            public Builder clearCapabilityTypes() {
                copyOnWrite();
                ((Capability) this.instance).clearCapabilityTypes();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
            public AccessoryCategory getAccessoryCategory() {
                return ((Capability) this.instance).getAccessoryCategory();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
            public int getAccessoryCategoryValue() {
                return ((Capability) this.instance).getAccessoryCategoryValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
            public AccessoryFeature getAccessoryFeature() {
                return ((Capability) this.instance).getAccessoryFeature();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
            public int getAccessoryFeatureValue() {
                return ((Capability) this.instance).getAccessoryFeatureValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
            public BluetoothService getBluetoothService() {
                return ((Capability) this.instance).getBluetoothService();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
            public int getBluetoothServiceValue() {
                return ((Capability) this.instance).getBluetoothServiceValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
            public BluetoothTransport getBluetoothTransport() {
                return ((Capability) this.instance).getBluetoothTransport();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
            public int getBluetoothTransportValue() {
                return ((Capability) this.instance).getBluetoothTransportValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
            public CapabilityTypesCase getCapabilityTypesCase() {
                return ((Capability) this.instance).getCapabilityTypesCase();
            }

            public Builder setAccessoryCategory(AccessoryCategory accessoryCategory) {
                copyOnWrite();
                ((Capability) this.instance).setAccessoryCategory(accessoryCategory);
                return this;
            }

            public Builder setAccessoryCategoryValue(int i) {
                copyOnWrite();
                ((Capability) this.instance).setAccessoryCategoryValue(i);
                return this;
            }

            public Builder setAccessoryFeature(AccessoryFeature accessoryFeature) {
                copyOnWrite();
                ((Capability) this.instance).setAccessoryFeature(accessoryFeature);
                return this;
            }

            public Builder setAccessoryFeatureValue(int i) {
                copyOnWrite();
                ((Capability) this.instance).setAccessoryFeatureValue(i);
                return this;
            }

            public Builder setBluetoothService(BluetoothService bluetoothService) {
                copyOnWrite();
                ((Capability) this.instance).setBluetoothService(bluetoothService);
                return this;
            }

            public Builder setBluetoothServiceValue(int i) {
                copyOnWrite();
                ((Capability) this.instance).setBluetoothServiceValue(i);
                return this;
            }

            public Builder setBluetoothTransport(BluetoothTransport bluetoothTransport) {
                copyOnWrite();
                ((Capability) this.instance).setBluetoothTransport(bluetoothTransport);
                return this;
            }

            public Builder setBluetoothTransportValue(int i) {
                copyOnWrite();
                ((Capability) this.instance).setBluetoothTransportValue(i);
                return this;
            }

            private Builder() {
                super(Capability.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        public enum CapabilityTypesCase implements Internal.EnumLite {
            BLUETOOTH_SERVICE(1),
            BLUETOOTH_TRANSPORT(2),
            ACCESSORY_FEATURE(3),
            ACCESSORY_CATEGORY(4),
            CAPABILITYTYPES_NOT_SET(0);
            
            private final int value;

            CapabilityTypesCase(int i) {
                this.value = i;
            }

            public static CapabilityTypesCase forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return BLUETOOTH_SERVICE;
                    }
                    if (i == 2) {
                        return BLUETOOTH_TRANSPORT;
                    }
                    if (i == 3) {
                        return ACCESSORY_FEATURE;
                    }
                    if (i == 4) {
                        return ACCESSORY_CATEGORY;
                    }
                    return null;
                }
                return CAPABILITYTYPES_NOT_SET;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static CapabilityTypesCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Capability() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAccessoryCategory() {
            if (this.capabilityTypesCase_ == 4) {
                this.capabilityTypesCase_ = 0;
                this.capabilityTypes_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAccessoryFeature() {
            if (this.capabilityTypesCase_ == 3) {
                this.capabilityTypesCase_ = 0;
                this.capabilityTypes_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBluetoothService() {
            if (this.capabilityTypesCase_ == 1) {
                this.capabilityTypesCase_ = 0;
                this.capabilityTypes_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBluetoothTransport() {
            if (this.capabilityTypesCase_ == 2) {
                this.capabilityTypesCase_ = 0;
                this.capabilityTypes_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCapabilityTypes() {
            this.capabilityTypesCase_ = 0;
            this.capabilityTypes_ = null;
        }

        public static Capability getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Capability parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Capability) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Capability parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Capability> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccessoryCategory(AccessoryCategory accessoryCategory) {
            if (accessoryCategory != null) {
                this.capabilityTypesCase_ = 4;
                this.capabilityTypes_ = Integer.valueOf(accessoryCategory.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccessoryCategoryValue(int i) {
            this.capabilityTypesCase_ = 4;
            this.capabilityTypes_ = Integer.valueOf(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccessoryFeature(AccessoryFeature accessoryFeature) {
            if (accessoryFeature != null) {
                this.capabilityTypesCase_ = 3;
                this.capabilityTypes_ = Integer.valueOf(accessoryFeature.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccessoryFeatureValue(int i) {
            this.capabilityTypesCase_ = 3;
            this.capabilityTypes_ = Integer.valueOf(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothService(BluetoothService bluetoothService) {
            if (bluetoothService != null) {
                this.capabilityTypesCase_ = 1;
                this.capabilityTypes_ = Integer.valueOf(bluetoothService.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothServiceValue(int i) {
            this.capabilityTypesCase_ = 1;
            this.capabilityTypes_ = Integer.valueOf(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothTransport(BluetoothTransport bluetoothTransport) {
            if (bluetoothTransport != null) {
                this.capabilityTypesCase_ = 2;
                this.capabilityTypes_ = Integer.valueOf(bluetoothTransport.getNumber());
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothTransportValue(int i) {
            this.capabilityTypesCase_ = 2;
            this.capabilityTypes_ = Integer.valueOf(i);
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
                    Capability capability = (Capability) obj2;
                    int ordinal = capability.getCapabilityTypesCase().ordinal();
                    if (ordinal == 0) {
                        if (this.capabilityTypesCase_ == 1) {
                            z = true;
                        }
                        this.capabilityTypes_ = visitor.visitOneofInt(z, this.capabilityTypes_, capability.capabilityTypes_);
                    } else if (ordinal == 1) {
                        if (this.capabilityTypesCase_ == 2) {
                            z = true;
                        }
                        this.capabilityTypes_ = visitor.visitOneofInt(z, this.capabilityTypes_, capability.capabilityTypes_);
                    } else if (ordinal == 2) {
                        if (this.capabilityTypesCase_ == 3) {
                            z = true;
                        }
                        this.capabilityTypes_ = visitor.visitOneofInt(z, this.capabilityTypes_, capability.capabilityTypes_);
                    } else if (ordinal == 3) {
                        if (this.capabilityTypesCase_ == 4) {
                            z = true;
                        }
                        this.capabilityTypes_ = visitor.visitOneofInt(z, this.capabilityTypes_, capability.capabilityTypes_);
                    } else if (ordinal == 4) {
                        if (this.capabilityTypesCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = capability.capabilityTypesCase_) != 0) {
                        this.capabilityTypesCase_ = i;
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
                                    int readEnum = codedInputStream.readEnum();
                                    this.capabilityTypesCase_ = 1;
                                    this.capabilityTypes_ = Integer.valueOf(readEnum);
                                } else if (readTag == 16) {
                                    int readEnum2 = codedInputStream.readEnum();
                                    this.capabilityTypesCase_ = 2;
                                    this.capabilityTypes_ = Integer.valueOf(readEnum2);
                                } else if (readTag == 24) {
                                    int readEnum3 = codedInputStream.readEnum();
                                    this.capabilityTypesCase_ = 3;
                                    this.capabilityTypes_ = Integer.valueOf(readEnum3);
                                } else if (readTag != 32) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    int readEnum4 = codedInputStream.readEnum();
                                    this.capabilityTypesCase_ = 4;
                                    this.capabilityTypes_ = Integer.valueOf(readEnum4);
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
                    return new Capability();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (Capability.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
        public AccessoryCategory getAccessoryCategory() {
            if (this.capabilityTypesCase_ == 4) {
                AccessoryCategory forNumber = AccessoryCategory.forNumber(((Integer) this.capabilityTypes_).intValue());
                return forNumber == null ? AccessoryCategory.UNRECOGNIZED : forNumber;
            }
            return AccessoryCategory.HEARABLE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
        public int getAccessoryCategoryValue() {
            if (this.capabilityTypesCase_ == 4) {
                return ((Integer) this.capabilityTypes_).intValue();
            }
            return 0;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
        public AccessoryFeature getAccessoryFeature() {
            if (this.capabilityTypesCase_ == 3) {
                AccessoryFeature forNumber = AccessoryFeature.forNumber(((Integer) this.capabilityTypes_).intValue());
                return forNumber == null ? AccessoryFeature.UNRECOGNIZED : forNumber;
            }
            return AccessoryFeature.FIND_MY;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
        public int getAccessoryFeatureValue() {
            if (this.capabilityTypesCase_ == 3) {
                return ((Integer) this.capabilityTypes_).intValue();
            }
            return 0;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
        public BluetoothService getBluetoothService() {
            if (this.capabilityTypesCase_ == 1) {
                BluetoothService forNumber = BluetoothService.forNumber(((Integer) this.capabilityTypes_).intValue());
                return forNumber == null ? BluetoothService.UNRECOGNIZED : forNumber;
            }
            return BluetoothService.HFP;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
        public int getBluetoothServiceValue() {
            if (this.capabilityTypesCase_ == 1) {
                return ((Integer) this.capabilityTypes_).intValue();
            }
            return 0;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
        public BluetoothTransport getBluetoothTransport() {
            if (this.capabilityTypesCase_ == 2) {
                BluetoothTransport forNumber = BluetoothTransport.forNumber(((Integer) this.capabilityTypes_).intValue());
                return forNumber == null ? BluetoothTransport.UNRECOGNIZED : forNumber;
            }
            return BluetoothTransport.BT_CLASSIC;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
        public int getBluetoothTransportValue() {
            if (this.capabilityTypesCase_ == 2) {
                return ((Integer) this.capabilityTypes_).intValue();
            }
            return 0;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CapabilityOrBuilder
        public CapabilityTypesCase getCapabilityTypesCase() {
            return CapabilityTypesCase.forNumber(this.capabilityTypesCase_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.capabilityTypesCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, ((Integer) this.capabilityTypes_).intValue());
            }
            if (this.capabilityTypesCase_ == 2) {
                i2 += CodedOutputStream.computeEnumSize(2, ((Integer) this.capabilityTypes_).intValue());
            }
            if (this.capabilityTypesCase_ == 3) {
                i2 += CodedOutputStream.computeEnumSize(3, ((Integer) this.capabilityTypes_).intValue());
            }
            if (this.capabilityTypesCase_ == 4) {
                i2 += CodedOutputStream.computeEnumSize(4, ((Integer) this.capabilityTypes_).intValue());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.capabilityTypesCase_ == 1) {
                codedOutputStream.writeEnum(1, ((Integer) this.capabilityTypes_).intValue());
            }
            if (this.capabilityTypesCase_ == 2) {
                codedOutputStream.writeEnum(2, ((Integer) this.capabilityTypes_).intValue());
            }
            if (this.capabilityTypesCase_ == 3) {
                codedOutputStream.writeEnum(3, ((Integer) this.capabilityTypes_).intValue());
            }
            if (this.capabilityTypesCase_ == 4) {
                codedOutputStream.writeEnum(4, ((Integer) this.capabilityTypes_).intValue());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(Capability capability) {
            return DEFAULT_INSTANCE.createBuilder(capability);
        }

        public static Capability parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Capability) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Capability parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Capability parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Capability parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Capability parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Capability parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Capability parseFrom(InputStream inputStream) throws IOException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Capability parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Capability parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Capability parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Capability) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CapabilityOrBuilder extends MessageLiteOrBuilder {
        Capability.AccessoryCategory getAccessoryCategory();

        int getAccessoryCategoryValue();

        Capability.AccessoryFeature getAccessoryFeature();

        int getAccessoryFeatureValue();

        Capability.BluetoothService getBluetoothService();

        int getBluetoothServiceValue();

        Capability.BluetoothTransport getBluetoothTransport();

        int getBluetoothTransportValue();

        Capability.CapabilityTypesCase getCapabilityTypesCase();
    }

    /* loaded from: classes6.dex */
    public static final class CloudPairingAttributes extends GeneratedMessageLite<CloudPairingAttributes, Builder> implements CloudPairingAttributesOrBuilder {
        public static final int CAPABILITIES_FIELD_NUMBER = 3;
        private static final CloudPairingAttributes DEFAULT_INSTANCE = new CloudPairingAttributes();
        public static final int IDENTIFIER_FIELD_NUMBER = 2;
        private static volatile Parser<CloudPairingAttributes> PARSER = null;
        public static final int VERSION_FIELD_NUMBER = 1;
        private int bitField0_;
        private int version_;
        private ByteString identifier_ = ByteString.EMPTY;
        private Internal.ProtobufList<Capability> capabilities_ = GeneratedMessageLite.emptyProtobufList();

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CloudPairingAttributes, Builder> implements CloudPairingAttributesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addAllCapabilities(Iterable<? extends Capability> iterable) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).addAllCapabilities(iterable);
                return this;
            }

            public Builder addCapabilities(Capability capability) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).addCapabilities(capability);
                return this;
            }

            public Builder clearCapabilities() {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).clearCapabilities();
                return this;
            }

            public Builder clearIdentifier() {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).clearIdentifier();
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).clearVersion();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
            public Capability getCapabilities(int i) {
                return ((CloudPairingAttributes) this.instance).getCapabilities(i);
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
            public int getCapabilitiesCount() {
                return ((CloudPairingAttributes) this.instance).getCapabilitiesCount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
            public List<Capability> getCapabilitiesList() {
                return Collections.unmodifiableList(((CloudPairingAttributes) this.instance).getCapabilitiesList());
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
            public ByteString getIdentifier() {
                return ((CloudPairingAttributes) this.instance).getIdentifier();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
            public int getVersion() {
                return ((CloudPairingAttributes) this.instance).getVersion();
            }

            public Builder removeCapabilities(int i) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).removeCapabilities(i);
                return this;
            }

            public Builder setCapabilities(int i, Capability capability) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).setCapabilities(i, capability);
                return this;
            }

            public Builder setIdentifier(ByteString byteString) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).setIdentifier(byteString);
                return this;
            }

            public Builder setVersion(int i) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).setVersion(i);
                return this;
            }

            private Builder() {
                super(CloudPairingAttributes.DEFAULT_INSTANCE);
            }

            public Builder addCapabilities(int i, Capability capability) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).addCapabilities(i, capability);
                return this;
            }

            public Builder setCapabilities(int i, Capability.Builder builder) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).setCapabilities(i, builder);
                return this;
            }

            public Builder addCapabilities(Capability.Builder builder) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).addCapabilities(builder);
                return this;
            }

            public Builder addCapabilities(int i, Capability.Builder builder) {
                copyOnWrite();
                ((CloudPairingAttributes) this.instance).addCapabilities(i, builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CloudPairingAttributes() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllCapabilities(Iterable<? extends Capability> iterable) {
            ensureCapabilitiesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.capabilities_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCapabilities(Capability capability) {
            if (capability != null) {
                ensureCapabilitiesIsMutable();
                this.capabilities_.add(capability);
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCapabilities() {
            this.capabilities_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIdentifier() {
            this.identifier_ = getDefaultInstance().getIdentifier();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersion() {
            this.version_ = 0;
        }

        private void ensureCapabilitiesIsMutable() {
            if (!this.capabilities_.isModifiable()) {
                this.capabilities_ = GeneratedMessageLite.mutableCopy(this.capabilities_);
            }
        }

        public static CloudPairingAttributes getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CloudPairingAttributes parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CloudPairingAttributes parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CloudPairingAttributes> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeCapabilities(int i) {
            ensureCapabilitiesIsMutable();
            this.capabilities_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCapabilities(int i, Capability capability) {
            if (capability != null) {
                ensureCapabilitiesIsMutable();
                this.capabilities_.set(i, capability);
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
        public void setVersion(int i) {
            this.version_ = i;
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
                    CloudPairingAttributes cloudPairingAttributes = (CloudPairingAttributes) obj2;
                    this.version_ = visitor.visitInt(this.version_ != 0, this.version_, cloudPairingAttributes.version_ != 0, cloudPairingAttributes.version_);
                    boolean z2 = this.identifier_ != ByteString.EMPTY;
                    ByteString byteString = this.identifier_;
                    if (cloudPairingAttributes.identifier_ != ByteString.EMPTY) {
                        z = true;
                    }
                    this.identifier_ = visitor.visitByteString(z2, byteString, z, cloudPairingAttributes.identifier_);
                    this.capabilities_ = visitor.visitList(this.capabilities_, cloudPairingAttributes.capabilities_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= cloudPairingAttributes.bitField0_;
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
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.version_ = codedInputStream.readUInt32();
                                    } else if (readTag == 18) {
                                        this.identifier_ = codedInputStream.readBytes();
                                    } else if (readTag != 26) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        if (!this.capabilities_.isModifiable()) {
                                            this.capabilities_ = GeneratedMessageLite.mutableCopy(this.capabilities_);
                                        }
                                        this.capabilities_.add(codedInputStream.readMessage(Capability.parser(), extensionRegistryLite));
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            }
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    this.capabilities_.makeImmutable();
                    return null;
                case 6:
                    return new CloudPairingAttributes();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CloudPairingAttributes.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
        public Capability getCapabilities(int i) {
            return this.capabilities_.get(i);
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
        public int getCapabilitiesCount() {
            return this.capabilities_.size();
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
        public List<Capability> getCapabilitiesList() {
            return this.capabilities_;
        }

        public CapabilityOrBuilder getCapabilitiesOrBuilder(int i) {
            return this.capabilities_.get(i);
        }

        public List<? extends CapabilityOrBuilder> getCapabilitiesOrBuilderList() {
            return this.capabilities_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
        public ByteString getIdentifier() {
            return this.identifier_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.version_;
            int computeUInt32Size = i2 != 0 ? CodedOutputStream.computeUInt32Size(1, i2) + 0 : 0;
            if (!this.identifier_.isEmpty()) {
                computeUInt32Size += CodedOutputStream.computeBytesSize(2, this.identifier_);
            }
            for (int i3 = 0; i3 < this.capabilities_.size(); i3++) {
                computeUInt32Size += CodedOutputStream.computeMessageSize(3, this.capabilities_.get(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeUInt32Size;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingAttributesOrBuilder
        public int getVersion() {
            return this.version_;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.version_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (!this.identifier_.isEmpty()) {
                codedOutputStream.writeBytes(2, this.identifier_);
            }
            for (int i2 = 0; i2 < this.capabilities_.size(); i2++) {
                codedOutputStream.writeMessage(3, this.capabilities_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CloudPairingAttributes cloudPairingAttributes) {
            return DEFAULT_INSTANCE.createBuilder(cloudPairingAttributes);
        }

        public static CloudPairingAttributes parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CloudPairingAttributes parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CloudPairingAttributes parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCapabilities(int i, Capability capability) {
            if (capability != null) {
                ensureCapabilitiesIsMutable();
                this.capabilities_.add(i, capability);
                return;
            }
            throw new NullPointerException();
        }

        public static CloudPairingAttributes parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCapabilities(int i, Capability.Builder builder) {
            ensureCapabilitiesIsMutable();
            this.capabilities_.set(i, builder.mo10084build());
        }

        public static CloudPairingAttributes parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CloudPairingAttributes parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCapabilities(Capability.Builder builder) {
            ensureCapabilitiesIsMutable();
            this.capabilities_.add(builder.mo10084build());
        }

        public static CloudPairingAttributes parseFrom(InputStream inputStream) throws IOException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CloudPairingAttributes parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCapabilities(int i, Capability.Builder builder) {
            ensureCapabilitiesIsMutable();
            this.capabilities_.add(i, builder.mo10084build());
        }

        public static CloudPairingAttributes parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CloudPairingAttributes parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CloudPairingAttributesOrBuilder extends MessageLiteOrBuilder {
        Capability getCapabilities(int i);

        int getCapabilitiesCount();

        List<Capability> getCapabilitiesList();

        ByteString getIdentifier();

        int getVersion();
    }

    /* loaded from: classes6.dex */
    public static final class CloudPairingKeys extends GeneratedMessageLite<CloudPairingKeys, Builder> implements CloudPairingKeysOrBuilder {
        public static final int BLUETOOTH_KEYS_FIELD_NUMBER = 1;
        private static final CloudPairingKeys DEFAULT_INSTANCE = new CloudPairingKeys();
        private static volatile Parser<CloudPairingKeys> PARSER;
        private int keysCase_ = 0;
        private Object keys_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CloudPairingKeys, Builder> implements CloudPairingKeysOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearBluetoothKeys() {
                copyOnWrite();
                ((CloudPairingKeys) this.instance).clearBluetoothKeys();
                return this;
            }

            public Builder clearKeys() {
                copyOnWrite();
                ((CloudPairingKeys) this.instance).clearKeys();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingKeysOrBuilder
            public BluetoothKeys getBluetoothKeys() {
                return ((CloudPairingKeys) this.instance).getBluetoothKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingKeysOrBuilder
            public KeysCase getKeysCase() {
                return ((CloudPairingKeys) this.instance).getKeysCase();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingKeysOrBuilder
            public boolean hasBluetoothKeys() {
                return ((CloudPairingKeys) this.instance).hasBluetoothKeys();
            }

            public Builder mergeBluetoothKeys(BluetoothKeys bluetoothKeys) {
                copyOnWrite();
                ((CloudPairingKeys) this.instance).mergeBluetoothKeys(bluetoothKeys);
                return this;
            }

            public Builder setBluetoothKeys(BluetoothKeys bluetoothKeys) {
                copyOnWrite();
                ((CloudPairingKeys) this.instance).setBluetoothKeys(bluetoothKeys);
                return this;
            }

            private Builder() {
                super(CloudPairingKeys.DEFAULT_INSTANCE);
            }

            public Builder setBluetoothKeys(BluetoothKeys.Builder builder) {
                copyOnWrite();
                ((CloudPairingKeys) this.instance).setBluetoothKeys(builder);
                return this;
            }
        }

        /* loaded from: classes6.dex */
        public enum KeysCase implements Internal.EnumLite {
            BLUETOOTH_KEYS(1),
            KEYS_NOT_SET(0);
            
            private final int value;

            KeysCase(int i) {
                this.value = i;
            }

            public static KeysCase forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return BLUETOOTH_KEYS;
                    }
                    return null;
                }
                return KEYS_NOT_SET;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static KeysCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CloudPairingKeys() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBluetoothKeys() {
            if (this.keysCase_ == 1) {
                this.keysCase_ = 0;
                this.keys_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKeys() {
            this.keysCase_ = 0;
            this.keys_ = null;
        }

        public static CloudPairingKeys getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBluetoothKeys(BluetoothKeys bluetoothKeys) {
            if (this.keysCase_ == 1 && this.keys_ != BluetoothKeys.getDefaultInstance()) {
                this.keys_ = BluetoothKeys.newBuilder((BluetoothKeys) this.keys_).mergeFrom((BluetoothKeys.Builder) bluetoothKeys).mo10085buildPartial();
            } else {
                this.keys_ = bluetoothKeys;
            }
            this.keysCase_ = 1;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CloudPairingKeys parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CloudPairingKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CloudPairingKeys parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CloudPairingKeys> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothKeys(BluetoothKeys bluetoothKeys) {
            if (bluetoothKeys != null) {
                this.keys_ = bluetoothKeys;
                this.keysCase_ = 1;
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
                    CloudPairingKeys cloudPairingKeys = (CloudPairingKeys) obj2;
                    int ordinal = cloudPairingKeys.getKeysCase().ordinal();
                    if (ordinal == 0) {
                        if (this.keysCase_ == 1) {
                            z = true;
                        }
                        this.keys_ = visitor.visitOneofMessage(z, this.keys_, cloudPairingKeys.keys_);
                    } else if (ordinal == 1) {
                        if (this.keysCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = cloudPairingKeys.keysCase_) != 0) {
                        this.keysCase_ = i;
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
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 10) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        BluetoothKeys.Builder mo10081toBuilder = this.keysCase_ == 1 ? ((BluetoothKeys) this.keys_).mo10081toBuilder() : null;
                                        this.keys_ = codedInputStream.readMessage(BluetoothKeys.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder != null) {
                                            mo10081toBuilder.mergeFrom((BluetoothKeys.Builder) ((BluetoothKeys) this.keys_));
                                            this.keys_ = mo10081toBuilder.mo10085buildPartial();
                                        }
                                        this.keysCase_ = 1;
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw new RuntimeException(e.setUnfinishedMessage(this));
                            }
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new CloudPairingKeys();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CloudPairingKeys.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingKeysOrBuilder
        public BluetoothKeys getBluetoothKeys() {
            if (this.keysCase_ == 1) {
                return (BluetoothKeys) this.keys_;
            }
            return BluetoothKeys.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingKeysOrBuilder
        public KeysCase getKeysCase() {
            return KeysCase.forNumber(this.keysCase_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.keysCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, (BluetoothKeys) this.keys_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingKeysOrBuilder
        public boolean hasBluetoothKeys() {
            return this.keysCase_ == 1;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.keysCase_ == 1) {
                codedOutputStream.writeMessage(1, (BluetoothKeys) this.keys_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CloudPairingKeys cloudPairingKeys) {
            return DEFAULT_INSTANCE.createBuilder(cloudPairingKeys);
        }

        public static CloudPairingKeys parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CloudPairingKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CloudPairingKeys parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CloudPairingKeys parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CloudPairingKeys parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothKeys(BluetoothKeys.Builder builder) {
            this.keys_ = builder.mo10084build();
            this.keysCase_ = 1;
        }

        public static CloudPairingKeys parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CloudPairingKeys parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CloudPairingKeys parseFrom(InputStream inputStream) throws IOException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CloudPairingKeys parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CloudPairingKeys parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CloudPairingKeys parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CloudPairingKeysOrBuilder extends MessageLiteOrBuilder {
        BluetoothKeys getBluetoothKeys();

        CloudPairingKeys.KeysCase getKeysCase();

        boolean hasBluetoothKeys();
    }

    /* loaded from: classes6.dex */
    public static final class CloudPairingStatus extends GeneratedMessageLite<CloudPairingStatus, Builder> implements CloudPairingStatusOrBuilder {
        private static final CloudPairingStatus DEFAULT_INSTANCE = new CloudPairingStatus();
        public static final int PAIRING_STATUS_FIELD_NUMBER = 1;
        private static volatile Parser<CloudPairingStatus> PARSER;
        private boolean pairingStatus_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CloudPairingStatus, Builder> implements CloudPairingStatusOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearPairingStatus() {
                copyOnWrite();
                ((CloudPairingStatus) this.instance).clearPairingStatus();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingStatusOrBuilder
            public boolean getPairingStatus() {
                return ((CloudPairingStatus) this.instance).getPairingStatus();
            }

            public Builder setPairingStatus(boolean z) {
                copyOnWrite();
                ((CloudPairingStatus) this.instance).setPairingStatus(z);
                return this;
            }

            private Builder() {
                super(CloudPairingStatus.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CloudPairingStatus() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPairingStatus() {
            this.pairingStatus_ = false;
        }

        public static CloudPairingStatus getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static CloudPairingStatus parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CloudPairingStatus) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CloudPairingStatus parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<CloudPairingStatus> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPairingStatus(boolean z) {
            this.pairingStatus_ = z;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    boolean z = this.pairingStatus_;
                    boolean z2 = ((CloudPairingStatus) obj2).pairingStatus_;
                    this.pairingStatus_ = ((GeneratedMessageLite.Visitor) obj).visitBoolean(z, z, z2, z2);
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
                    boolean z3 = false;
                    while (!z3) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag != 8) {
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                        }
                                    } else {
                                        this.pairingStatus_ = codedInputStream.readBool();
                                    }
                                }
                                z3 = true;
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
                    return new CloudPairingStatus();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (CloudPairingStatus.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.CloudPairingStatusOrBuilder
        public boolean getPairingStatus() {
            return this.pairingStatus_;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            boolean z = this.pairingStatus_;
            if (z) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, z);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            boolean z = this.pairingStatus_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(CloudPairingStatus cloudPairingStatus) {
            return DEFAULT_INSTANCE.createBuilder(cloudPairingStatus);
        }

        public static CloudPairingStatus parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CloudPairingStatus) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CloudPairingStatus parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CloudPairingStatus parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CloudPairingStatus parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CloudPairingStatus parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CloudPairingStatus parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CloudPairingStatus parseFrom(InputStream inputStream) throws IOException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CloudPairingStatus parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CloudPairingStatus parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CloudPairingStatus parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface CloudPairingStatusOrBuilder extends MessageLiteOrBuilder {
        boolean getPairingStatus();
    }

    /* loaded from: classes6.dex */
    public static final class GetCloudPairingAttributes extends GeneratedMessageLite<GetCloudPairingAttributes, Builder> implements GetCloudPairingAttributesOrBuilder {
        private static final GetCloudPairingAttributes DEFAULT_INSTANCE = new GetCloudPairingAttributes();
        private static volatile Parser<GetCloudPairingAttributes> PARSER;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetCloudPairingAttributes, Builder> implements GetCloudPairingAttributesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(GetCloudPairingAttributes.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetCloudPairingAttributes() {
        }

        public static GetCloudPairingAttributes getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetCloudPairingAttributes parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCloudPairingAttributes parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetCloudPairingAttributes> parser() {
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
                    GetCloudPairingAttributes getCloudPairingAttributes = (GetCloudPairingAttributes) obj2;
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
                    return new GetCloudPairingAttributes();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetCloudPairingAttributes.class) {
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

        public static Builder newBuilder(GetCloudPairingAttributes getCloudPairingAttributes) {
            return DEFAULT_INSTANCE.createBuilder(getCloudPairingAttributes);
        }

        public static GetCloudPairingAttributes parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCloudPairingAttributes parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetCloudPairingAttributes parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetCloudPairingAttributes parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetCloudPairingAttributes parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetCloudPairingAttributes parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetCloudPairingAttributes parseFrom(InputStream inputStream) throws IOException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCloudPairingAttributes parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCloudPairingAttributes parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetCloudPairingAttributes parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCloudPairingAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetCloudPairingAttributesOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: classes6.dex */
    public static final class GetCloudPairingStatus extends GeneratedMessageLite<GetCloudPairingStatus, Builder> implements GetCloudPairingStatusOrBuilder {
        private static final GetCloudPairingStatus DEFAULT_INSTANCE = new GetCloudPairingStatus();
        private static volatile Parser<GetCloudPairingStatus> PARSER = null;
        public static final int SEED_FIELD_NUMBER = 1;
        private Seed seed_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<GetCloudPairingStatus, Builder> implements GetCloudPairingStatusOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearSeed() {
                copyOnWrite();
                ((GetCloudPairingStatus) this.instance).clearSeed();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.GetCloudPairingStatusOrBuilder
            public Seed getSeed() {
                return ((GetCloudPairingStatus) this.instance).getSeed();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.GetCloudPairingStatusOrBuilder
            public boolean hasSeed() {
                return ((GetCloudPairingStatus) this.instance).hasSeed();
            }

            public Builder mergeSeed(Seed seed) {
                copyOnWrite();
                ((GetCloudPairingStatus) this.instance).mergeSeed(seed);
                return this;
            }

            public Builder setSeed(Seed seed) {
                copyOnWrite();
                ((GetCloudPairingStatus) this.instance).setSeed(seed);
                return this;
            }

            private Builder() {
                super(GetCloudPairingStatus.DEFAULT_INSTANCE);
            }

            public Builder setSeed(Seed.Builder builder) {
                copyOnWrite();
                ((GetCloudPairingStatus) this.instance).setSeed(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private GetCloudPairingStatus() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSeed() {
            this.seed_ = null;
        }

        public static GetCloudPairingStatus getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSeed(Seed seed) {
            Seed seed2 = this.seed_;
            if (seed2 != null && seed2 != Seed.getDefaultInstance()) {
                this.seed_ = Seed.newBuilder(this.seed_).mergeFrom((Seed.Builder) seed).mo10085buildPartial();
            } else {
                this.seed_ = seed;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static GetCloudPairingStatus parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCloudPairingStatus parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<GetCloudPairingStatus> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSeed(Seed seed) {
            if (seed != null) {
                this.seed_ = seed;
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
                    this.seed_ = (Seed) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.seed_, ((GetCloudPairingStatus) obj2).seed_);
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
                                    Seed.Builder mo10081toBuilder = this.seed_ != null ? this.seed_.mo10081toBuilder() : null;
                                    this.seed_ = (Seed) codedInputStream.readMessage(Seed.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Seed.Builder) this.seed_);
                                        this.seed_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new GetCloudPairingStatus();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (GetCloudPairingStatus.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.GetCloudPairingStatusOrBuilder
        public Seed getSeed() {
            Seed seed = this.seed_;
            return seed == null ? Seed.getDefaultInstance() : seed;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.seed_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getSeed());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.GetCloudPairingStatusOrBuilder
        public boolean hasSeed() {
            return this.seed_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.seed_ != null) {
                codedOutputStream.writeMessage(1, getSeed());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(GetCloudPairingStatus getCloudPairingStatus) {
            return DEFAULT_INSTANCE.createBuilder(getCloudPairingStatus);
        }

        public static GetCloudPairingStatus parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCloudPairingStatus parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static GetCloudPairingStatus parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSeed(Seed.Builder builder) {
            this.seed_ = builder.mo10084build();
        }

        public static GetCloudPairingStatus parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static GetCloudPairingStatus parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetCloudPairingStatus parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static GetCloudPairingStatus parseFrom(InputStream inputStream) throws IOException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetCloudPairingStatus parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static GetCloudPairingStatus parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static GetCloudPairingStatus parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GetCloudPairingStatus) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface GetCloudPairingStatusOrBuilder extends MessageLiteOrBuilder {
        Seed getSeed();

        boolean hasSeed();
    }

    /* loaded from: classes6.dex */
    public static final class RemoveCloudPairingKeys extends GeneratedMessageLite<RemoveCloudPairingKeys, Builder> implements RemoveCloudPairingKeysOrBuilder {
        private static final RemoveCloudPairingKeys DEFAULT_INSTANCE = new RemoveCloudPairingKeys();
        private static volatile Parser<RemoveCloudPairingKeys> PARSER = null;
        public static final int SEED_FIELD_NUMBER = 1;
        private Seed seed_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<RemoveCloudPairingKeys, Builder> implements RemoveCloudPairingKeysOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearSeed() {
                copyOnWrite();
                ((RemoveCloudPairingKeys) this.instance).clearSeed();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.RemoveCloudPairingKeysOrBuilder
            public Seed getSeed() {
                return ((RemoveCloudPairingKeys) this.instance).getSeed();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.RemoveCloudPairingKeysOrBuilder
            public boolean hasSeed() {
                return ((RemoveCloudPairingKeys) this.instance).hasSeed();
            }

            public Builder mergeSeed(Seed seed) {
                copyOnWrite();
                ((RemoveCloudPairingKeys) this.instance).mergeSeed(seed);
                return this;
            }

            public Builder setSeed(Seed seed) {
                copyOnWrite();
                ((RemoveCloudPairingKeys) this.instance).setSeed(seed);
                return this;
            }

            private Builder() {
                super(RemoveCloudPairingKeys.DEFAULT_INSTANCE);
            }

            public Builder setSeed(Seed.Builder builder) {
                copyOnWrite();
                ((RemoveCloudPairingKeys) this.instance).setSeed(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private RemoveCloudPairingKeys() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSeed() {
            this.seed_ = null;
        }

        public static RemoveCloudPairingKeys getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSeed(Seed seed) {
            Seed seed2 = this.seed_;
            if (seed2 != null && seed2 != Seed.getDefaultInstance()) {
                this.seed_ = Seed.newBuilder(this.seed_).mergeFrom((Seed.Builder) seed).mo10085buildPartial();
            } else {
                this.seed_ = seed;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static RemoveCloudPairingKeys parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RemoveCloudPairingKeys parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<RemoveCloudPairingKeys> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSeed(Seed seed) {
            if (seed != null) {
                this.seed_ = seed;
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
                    this.seed_ = (Seed) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.seed_, ((RemoveCloudPairingKeys) obj2).seed_);
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
                                    Seed.Builder mo10081toBuilder = this.seed_ != null ? this.seed_.mo10081toBuilder() : null;
                                    this.seed_ = (Seed) codedInputStream.readMessage(Seed.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Seed.Builder) this.seed_);
                                        this.seed_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new RemoveCloudPairingKeys();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (RemoveCloudPairingKeys.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.RemoveCloudPairingKeysOrBuilder
        public Seed getSeed() {
            Seed seed = this.seed_;
            return seed == null ? Seed.getDefaultInstance() : seed;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.seed_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getSeed());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.RemoveCloudPairingKeysOrBuilder
        public boolean hasSeed() {
            return this.seed_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.seed_ != null) {
                codedOutputStream.writeMessage(1, getSeed());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(RemoveCloudPairingKeys removeCloudPairingKeys) {
            return DEFAULT_INSTANCE.createBuilder(removeCloudPairingKeys);
        }

        public static RemoveCloudPairingKeys parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RemoveCloudPairingKeys parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static RemoveCloudPairingKeys parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSeed(Seed.Builder builder) {
            this.seed_ = builder.mo10084build();
        }

        public static RemoveCloudPairingKeys parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static RemoveCloudPairingKeys parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static RemoveCloudPairingKeys parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static RemoveCloudPairingKeys parseFrom(InputStream inputStream) throws IOException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RemoveCloudPairingKeys parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RemoveCloudPairingKeys parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static RemoveCloudPairingKeys parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RemoveCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface RemoveCloudPairingKeysOrBuilder extends MessageLiteOrBuilder {
        Seed getSeed();

        boolean hasSeed();
    }

    /* loaded from: classes6.dex */
    public static final class ReplaceCloudPairingKeys extends GeneratedMessageLite<ReplaceCloudPairingKeys, Builder> implements ReplaceCloudPairingKeysOrBuilder {
        public static final int CURRENT_SEED_FIELD_NUMBER = 1;
        private static final ReplaceCloudPairingKeys DEFAULT_INSTANCE = new ReplaceCloudPairingKeys();
        public static final int NEW_KEYS_FIELD_NUMBER = 2;
        private static volatile Parser<ReplaceCloudPairingKeys> PARSER;
        private Seed currentSeed_;
        private CloudPairingKeys newKeys_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ReplaceCloudPairingKeys, Builder> implements ReplaceCloudPairingKeysOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearCurrentSeed() {
                copyOnWrite();
                ((ReplaceCloudPairingKeys) this.instance).clearCurrentSeed();
                return this;
            }

            public Builder clearNewKeys() {
                copyOnWrite();
                ((ReplaceCloudPairingKeys) this.instance).clearNewKeys();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.ReplaceCloudPairingKeysOrBuilder
            public Seed getCurrentSeed() {
                return ((ReplaceCloudPairingKeys) this.instance).getCurrentSeed();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.ReplaceCloudPairingKeysOrBuilder
            public CloudPairingKeys getNewKeys() {
                return ((ReplaceCloudPairingKeys) this.instance).getNewKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.ReplaceCloudPairingKeysOrBuilder
            public boolean hasCurrentSeed() {
                return ((ReplaceCloudPairingKeys) this.instance).hasCurrentSeed();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.ReplaceCloudPairingKeysOrBuilder
            public boolean hasNewKeys() {
                return ((ReplaceCloudPairingKeys) this.instance).hasNewKeys();
            }

            public Builder mergeCurrentSeed(Seed seed) {
                copyOnWrite();
                ((ReplaceCloudPairingKeys) this.instance).mergeCurrentSeed(seed);
                return this;
            }

            public Builder mergeNewKeys(CloudPairingKeys cloudPairingKeys) {
                copyOnWrite();
                ((ReplaceCloudPairingKeys) this.instance).mergeNewKeys(cloudPairingKeys);
                return this;
            }

            public Builder setCurrentSeed(Seed seed) {
                copyOnWrite();
                ((ReplaceCloudPairingKeys) this.instance).setCurrentSeed(seed);
                return this;
            }

            public Builder setNewKeys(CloudPairingKeys cloudPairingKeys) {
                copyOnWrite();
                ((ReplaceCloudPairingKeys) this.instance).setNewKeys(cloudPairingKeys);
                return this;
            }

            private Builder() {
                super(ReplaceCloudPairingKeys.DEFAULT_INSTANCE);
            }

            public Builder setCurrentSeed(Seed.Builder builder) {
                copyOnWrite();
                ((ReplaceCloudPairingKeys) this.instance).setCurrentSeed(builder);
                return this;
            }

            public Builder setNewKeys(CloudPairingKeys.Builder builder) {
                copyOnWrite();
                ((ReplaceCloudPairingKeys) this.instance).setNewKeys(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ReplaceCloudPairingKeys() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCurrentSeed() {
            this.currentSeed_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNewKeys() {
            this.newKeys_ = null;
        }

        public static ReplaceCloudPairingKeys getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCurrentSeed(Seed seed) {
            Seed seed2 = this.currentSeed_;
            if (seed2 != null && seed2 != Seed.getDefaultInstance()) {
                this.currentSeed_ = Seed.newBuilder(this.currentSeed_).mergeFrom((Seed.Builder) seed).mo10085buildPartial();
            } else {
                this.currentSeed_ = seed;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNewKeys(CloudPairingKeys cloudPairingKeys) {
            CloudPairingKeys cloudPairingKeys2 = this.newKeys_;
            if (cloudPairingKeys2 != null && cloudPairingKeys2 != CloudPairingKeys.getDefaultInstance()) {
                this.newKeys_ = CloudPairingKeys.newBuilder(this.newKeys_).mergeFrom((CloudPairingKeys.Builder) cloudPairingKeys).mo10085buildPartial();
            } else {
                this.newKeys_ = cloudPairingKeys;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ReplaceCloudPairingKeys parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReplaceCloudPairingKeys parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ReplaceCloudPairingKeys> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurrentSeed(Seed seed) {
            if (seed != null) {
                this.currentSeed_ = seed;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNewKeys(CloudPairingKeys cloudPairingKeys) {
            if (cloudPairingKeys != null) {
                this.newKeys_ = cloudPairingKeys;
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
                    ReplaceCloudPairingKeys replaceCloudPairingKeys = (ReplaceCloudPairingKeys) obj2;
                    this.currentSeed_ = (Seed) visitor.visitMessage(this.currentSeed_, replaceCloudPairingKeys.currentSeed_);
                    this.newKeys_ = (CloudPairingKeys) visitor.visitMessage(this.newKeys_, replaceCloudPairingKeys.newKeys_);
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
                                if (readTag == 10) {
                                    Seed.Builder mo10081toBuilder = this.currentSeed_ != null ? this.currentSeed_.mo10081toBuilder() : null;
                                    this.currentSeed_ = (Seed) codedInputStream.readMessage(Seed.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Seed.Builder) this.currentSeed_);
                                        this.currentSeed_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    CloudPairingKeys.Builder mo10081toBuilder2 = this.newKeys_ != null ? this.newKeys_.mo10081toBuilder() : null;
                                    this.newKeys_ = (CloudPairingKeys) codedInputStream.readMessage(CloudPairingKeys.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder2 != null) {
                                        mo10081toBuilder2.mergeFrom((CloudPairingKeys.Builder) this.newKeys_);
                                        this.newKeys_ = mo10081toBuilder2.mo10085buildPartial();
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
                    return new ReplaceCloudPairingKeys();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ReplaceCloudPairingKeys.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.ReplaceCloudPairingKeysOrBuilder
        public Seed getCurrentSeed() {
            Seed seed = this.currentSeed_;
            return seed == null ? Seed.getDefaultInstance() : seed;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.ReplaceCloudPairingKeysOrBuilder
        public CloudPairingKeys getNewKeys() {
            CloudPairingKeys cloudPairingKeys = this.newKeys_;
            return cloudPairingKeys == null ? CloudPairingKeys.getDefaultInstance() : cloudPairingKeys;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.currentSeed_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getCurrentSeed());
            }
            if (this.newKeys_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getNewKeys());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.ReplaceCloudPairingKeysOrBuilder
        public boolean hasCurrentSeed() {
            return this.currentSeed_ != null;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.ReplaceCloudPairingKeysOrBuilder
        public boolean hasNewKeys() {
            return this.newKeys_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.currentSeed_ != null) {
                codedOutputStream.writeMessage(1, getCurrentSeed());
            }
            if (this.newKeys_ != null) {
                codedOutputStream.writeMessage(2, getNewKeys());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ReplaceCloudPairingKeys replaceCloudPairingKeys) {
            return DEFAULT_INSTANCE.createBuilder(replaceCloudPairingKeys);
        }

        public static ReplaceCloudPairingKeys parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReplaceCloudPairingKeys parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ReplaceCloudPairingKeys parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurrentSeed(Seed.Builder builder) {
            this.currentSeed_ = builder.mo10084build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNewKeys(CloudPairingKeys.Builder builder) {
            this.newKeys_ = builder.mo10084build();
        }

        public static ReplaceCloudPairingKeys parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ReplaceCloudPairingKeys parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ReplaceCloudPairingKeys parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ReplaceCloudPairingKeys parseFrom(InputStream inputStream) throws IOException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReplaceCloudPairingKeys parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReplaceCloudPairingKeys parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ReplaceCloudPairingKeys parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReplaceCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ReplaceCloudPairingKeysOrBuilder extends MessageLiteOrBuilder {
        Seed getCurrentSeed();

        CloudPairingKeys getNewKeys();

        boolean hasCurrentSeed();

        boolean hasNewKeys();
    }

    /* loaded from: classes6.dex */
    public static final class Seed extends GeneratedMessageLite<Seed, Builder> implements SeedOrBuilder {
        public static final int BLUETOOTH_SEED_FIELD_NUMBER = 1;
        private static final Seed DEFAULT_INSTANCE = new Seed();
        private static volatile Parser<Seed> PARSER;
        private int seedTypesCase_ = 0;
        private Object seedTypes_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Seed, Builder> implements SeedOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearBluetoothSeed() {
                copyOnWrite();
                ((Seed) this.instance).clearBluetoothSeed();
                return this;
            }

            public Builder clearSeedTypes() {
                copyOnWrite();
                ((Seed) this.instance).clearSeedTypes();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.SeedOrBuilder
            public ByteString getBluetoothSeed() {
                return ((Seed) this.instance).getBluetoothSeed();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.SeedOrBuilder
            public SeedTypesCase getSeedTypesCase() {
                return ((Seed) this.instance).getSeedTypesCase();
            }

            public Builder setBluetoothSeed(ByteString byteString) {
                copyOnWrite();
                ((Seed) this.instance).setBluetoothSeed(byteString);
                return this;
            }

            private Builder() {
                super(Seed.DEFAULT_INSTANCE);
            }
        }

        /* loaded from: classes6.dex */
        public enum SeedTypesCase implements Internal.EnumLite {
            BLUETOOTH_SEED(1),
            SEEDTYPES_NOT_SET(0);
            
            private final int value;

            SeedTypesCase(int i) {
                this.value = i;
            }

            public static SeedTypesCase forNumber(int i) {
                if (i != 0) {
                    if (i == 1) {
                        return BLUETOOTH_SEED;
                    }
                    return null;
                }
                return SEEDTYPES_NOT_SET;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static SeedTypesCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Seed() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBluetoothSeed() {
            if (this.seedTypesCase_ == 1) {
                this.seedTypesCase_ = 0;
                this.seedTypes_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSeedTypes() {
            this.seedTypesCase_ = 0;
            this.seedTypes_ = null;
        }

        public static Seed getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Seed parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Seed) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Seed parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Seed> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBluetoothSeed(ByteString byteString) {
            if (byteString != null) {
                this.seedTypesCase_ = 1;
                this.seedTypes_ = byteString;
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
                    Seed seed = (Seed) obj2;
                    int ordinal = seed.getSeedTypesCase().ordinal();
                    if (ordinal == 0) {
                        if (this.seedTypesCase_ == 1) {
                            z = true;
                        }
                        this.seedTypes_ = visitor.visitOneofByteString(z, this.seedTypes_, seed.seedTypes_);
                    } else if (ordinal == 1) {
                        if (this.seedTypesCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = seed.seedTypesCase_) != 0) {
                        this.seedTypesCase_ = i;
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
                                if (readTag != 10) {
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                } else {
                                    this.seedTypesCase_ = 1;
                                    this.seedTypes_ = codedInputStream.readBytes();
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
                    return new Seed();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (Seed.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.SeedOrBuilder
        public ByteString getBluetoothSeed() {
            if (this.seedTypesCase_ == 1) {
                return (ByteString) this.seedTypes_;
            }
            return ByteString.EMPTY;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.SeedOrBuilder
        public SeedTypesCase getSeedTypesCase() {
            return SeedTypesCase.forNumber(this.seedTypesCase_);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.seedTypesCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeBytesSize(1, (ByteString) this.seedTypes_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.seedTypesCase_ == 1) {
                codedOutputStream.writeBytes(1, (ByteString) this.seedTypes_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(Seed seed) {
            return DEFAULT_INSTANCE.createBuilder(seed);
        }

        public static Seed parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Seed) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Seed parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Seed parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Seed parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Seed parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Seed parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Seed parseFrom(InputStream inputStream) throws IOException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Seed parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Seed parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Seed parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Seed) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SeedOrBuilder extends MessageLiteOrBuilder {
        ByteString getBluetoothSeed();

        Seed.SeedTypesCase getSeedTypesCase();
    }

    /* loaded from: classes6.dex */
    public static final class SetCloudPairingKeys extends GeneratedMessageLite<SetCloudPairingKeys, Builder> implements SetCloudPairingKeysOrBuilder {
        private static final SetCloudPairingKeys DEFAULT_INSTANCE = new SetCloudPairingKeys();
        public static final int KEYS_FIELD_NUMBER = 1;
        private static volatile Parser<SetCloudPairingKeys> PARSER;
        private CloudPairingKeys keys_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<SetCloudPairingKeys, Builder> implements SetCloudPairingKeysOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearKeys() {
                copyOnWrite();
                ((SetCloudPairingKeys) this.instance).clearKeys();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.SetCloudPairingKeysOrBuilder
            public CloudPairingKeys getKeys() {
                return ((SetCloudPairingKeys) this.instance).getKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.SetCloudPairingKeysOrBuilder
            public boolean hasKeys() {
                return ((SetCloudPairingKeys) this.instance).hasKeys();
            }

            public Builder mergeKeys(CloudPairingKeys cloudPairingKeys) {
                copyOnWrite();
                ((SetCloudPairingKeys) this.instance).mergeKeys(cloudPairingKeys);
                return this;
            }

            public Builder setKeys(CloudPairingKeys cloudPairingKeys) {
                copyOnWrite();
                ((SetCloudPairingKeys) this.instance).setKeys(cloudPairingKeys);
                return this;
            }

            private Builder() {
                super(SetCloudPairingKeys.DEFAULT_INSTANCE);
            }

            public Builder setKeys(CloudPairingKeys.Builder builder) {
                copyOnWrite();
                ((SetCloudPairingKeys) this.instance).setKeys(builder);
                return this;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SetCloudPairingKeys() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKeys() {
            this.keys_ = null;
        }

        public static SetCloudPairingKeys getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeKeys(CloudPairingKeys cloudPairingKeys) {
            CloudPairingKeys cloudPairingKeys2 = this.keys_;
            if (cloudPairingKeys2 != null && cloudPairingKeys2 != CloudPairingKeys.getDefaultInstance()) {
                this.keys_ = CloudPairingKeys.newBuilder(this.keys_).mergeFrom((CloudPairingKeys.Builder) cloudPairingKeys).mo10085buildPartial();
            } else {
                this.keys_ = cloudPairingKeys;
            }
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static SetCloudPairingKeys parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetCloudPairingKeys parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<SetCloudPairingKeys> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeys(CloudPairingKeys cloudPairingKeys) {
            if (cloudPairingKeys != null) {
                this.keys_ = cloudPairingKeys;
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
                    this.keys_ = (CloudPairingKeys) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.keys_, ((SetCloudPairingKeys) obj2).keys_);
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
                                    CloudPairingKeys.Builder mo10081toBuilder = this.keys_ != null ? this.keys_.mo10081toBuilder() : null;
                                    this.keys_ = (CloudPairingKeys) codedInputStream.readMessage(CloudPairingKeys.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((CloudPairingKeys.Builder) this.keys_);
                                        this.keys_ = mo10081toBuilder.mo10085buildPartial();
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
                    return new SetCloudPairingKeys();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (SetCloudPairingKeys.class) {
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

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.SetCloudPairingKeysOrBuilder
        public CloudPairingKeys getKeys() {
            CloudPairingKeys cloudPairingKeys = this.keys_;
            return cloudPairingKeys == null ? CloudPairingKeys.getDefaultInstance() : cloudPairingKeys;
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.keys_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getKeys());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Cloudpairing.SetCloudPairingKeysOrBuilder
        public boolean hasKeys() {
            return this.keys_ != null;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.keys_ != null) {
                codedOutputStream.writeMessage(1, getKeys());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(SetCloudPairingKeys setCloudPairingKeys) {
            return DEFAULT_INSTANCE.createBuilder(setCloudPairingKeys);
        }

        public static SetCloudPairingKeys parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetCloudPairingKeys parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static SetCloudPairingKeys parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeys(CloudPairingKeys.Builder builder) {
            this.keys_ = builder.mo10084build();
        }

        public static SetCloudPairingKeys parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static SetCloudPairingKeys parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SetCloudPairingKeys parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static SetCloudPairingKeys parseFrom(InputStream inputStream) throws IOException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetCloudPairingKeys parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static SetCloudPairingKeys parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static SetCloudPairingKeys parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (SetCloudPairingKeys) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface SetCloudPairingKeysOrBuilder extends MessageLiteOrBuilder {
        CloudPairingKeys getKeys();

        boolean hasKeys();
    }

    private Cloudpairing() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
