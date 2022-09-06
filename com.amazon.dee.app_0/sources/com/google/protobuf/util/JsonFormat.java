package com.google.protobuf.util;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.protobuf.Any;
import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.Duration;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FloatValue;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ListValue;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.NullValue;
import com.google.protobuf.StringValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Timestamp;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt64Value;
import com.google.protobuf.Value;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes3.dex */
public class JsonFormat {
    private static final Logger logger = Logger.getLogger(JsonFormat.class.getName());

    /* renamed from: com.google.protobuf.util.JsonFormat$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = new int[Descriptors.FieldDescriptor.Type.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    /* loaded from: classes3.dex */
    private static final class CompactTextGenerator implements TextGenerator {
        private final Appendable output;

        private CompactTextGenerator(Appendable appendable) {
            this.output = appendable;
        }

        /* synthetic */ CompactTextGenerator(Appendable appendable, AnonymousClass1 anonymousClass1) {
            this(appendable);
        }

        @Override // com.google.protobuf.util.JsonFormat.TextGenerator
        public void indent() {
        }

        @Override // com.google.protobuf.util.JsonFormat.TextGenerator
        public void outdent() {
        }

        @Override // com.google.protobuf.util.JsonFormat.TextGenerator
        public void print(CharSequence charSequence) throws IOException {
            this.output.append(charSequence);
        }
    }

    /* loaded from: classes3.dex */
    public static class Parser {
        private static final int DEFAULT_RECURSION_LIMIT = 100;
        private final boolean ignoringUnknownFields;
        private final int recursionLimit;
        private final TypeRegistry registry;

        private Parser(TypeRegistry typeRegistry, boolean z, int i) {
            this.registry = typeRegistry;
            this.ignoringUnknownFields = z;
            this.recursionLimit = i;
        }

        /* synthetic */ Parser(TypeRegistry typeRegistry, boolean z, int i, AnonymousClass1 anonymousClass1) {
            this(typeRegistry, z, i);
        }

        public Parser ignoringUnknownFields() {
            return new Parser(this.registry, true, this.recursionLimit);
        }

        public void merge(Reader reader, Message.Builder builder) throws IOException {
            new ParserImpl(this.registry, this.ignoringUnknownFields, this.recursionLimit).merge(reader, builder);
        }

        public void merge(String str, Message.Builder builder) throws InvalidProtocolBufferException {
            new ParserImpl(this.registry, this.ignoringUnknownFields, this.recursionLimit).merge(str, builder);
        }

        Parser usingRecursionLimit(int i) {
            return new Parser(this.registry, this.ignoringUnknownFields, i);
        }

        public Parser usingTypeRegistry(TypeRegistry typeRegistry) {
            if (this.registry == TypeRegistry.getEmptyTypeRegistry()) {
                return new Parser(typeRegistry, this.ignoringUnknownFields, this.recursionLimit);
            }
            throw new IllegalArgumentException("Only one registry is allowed.");
        }
    }

    /* loaded from: classes3.dex */
    private static class ParserImpl {
        private static final double EPSILON = 1.0E-6d;
        private final boolean ignoringUnknownFields;
        private final int recursionLimit;
        private final TypeRegistry registry;
        private static final Map<String, WellKnownTypeParser> wellKnownTypeParsers = buildWellKnownTypeParsers();
        private static final BigInteger MAX_UINT64 = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        private static final BigDecimal MORE_THAN_ONE = new BigDecimal(String.valueOf(1.000001d));
        private static final BigDecimal MAX_DOUBLE = new BigDecimal(String.valueOf(Double.MAX_VALUE)).multiply(MORE_THAN_ONE);
        private static final BigDecimal MIN_DOUBLE = new BigDecimal(String.valueOf(-1.7976931348623157E308d)).multiply(MORE_THAN_ONE);
        private final Map<Descriptors.Descriptor, Map<String, Descriptors.FieldDescriptor>> fieldNameMaps = new HashMap();
        private final JsonParser jsonParser = new JsonParser();
        private int currentDepth = 0;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public interface WellKnownTypeParser {
            void merge(ParserImpl parserImpl, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException;
        }

        ParserImpl(TypeRegistry typeRegistry, boolean z, int i) {
            this.registry = typeRegistry;
            this.ignoringUnknownFields = z;
            this.recursionLimit = i;
        }

        private static Map<String, WellKnownTypeParser> buildWellKnownTypeParsers() {
            HashMap hashMap = new HashMap();
            hashMap.put(Any.getDescriptor().getFullName(), new WellKnownTypeParser() { // from class: com.google.protobuf.util.JsonFormat.ParserImpl.1
                @Override // com.google.protobuf.util.JsonFormat.ParserImpl.WellKnownTypeParser
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeAny(jsonElement, builder);
                }
            });
            WellKnownTypeParser wellKnownTypeParser = new WellKnownTypeParser() { // from class: com.google.protobuf.util.JsonFormat.ParserImpl.2
                @Override // com.google.protobuf.util.JsonFormat.ParserImpl.WellKnownTypeParser
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeWrapper(jsonElement, builder);
                }
            };
            hashMap.put(BoolValue.getDescriptor().getFullName(), wellKnownTypeParser);
            hashMap.put(Int32Value.getDescriptor().getFullName(), wellKnownTypeParser);
            hashMap.put(UInt32Value.getDescriptor().getFullName(), wellKnownTypeParser);
            hashMap.put(Int64Value.getDescriptor().getFullName(), wellKnownTypeParser);
            hashMap.put(UInt64Value.getDescriptor().getFullName(), wellKnownTypeParser);
            hashMap.put(StringValue.getDescriptor().getFullName(), wellKnownTypeParser);
            hashMap.put(BytesValue.getDescriptor().getFullName(), wellKnownTypeParser);
            hashMap.put(FloatValue.getDescriptor().getFullName(), wellKnownTypeParser);
            hashMap.put(DoubleValue.getDescriptor().getFullName(), wellKnownTypeParser);
            hashMap.put(Timestamp.getDescriptor().getFullName(), new WellKnownTypeParser() { // from class: com.google.protobuf.util.JsonFormat.ParserImpl.3
                @Override // com.google.protobuf.util.JsonFormat.ParserImpl.WellKnownTypeParser
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeTimestamp(jsonElement, builder);
                }
            });
            hashMap.put(Duration.getDescriptor().getFullName(), new WellKnownTypeParser() { // from class: com.google.protobuf.util.JsonFormat.ParserImpl.4
                @Override // com.google.protobuf.util.JsonFormat.ParserImpl.WellKnownTypeParser
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeDuration(jsonElement, builder);
                }
            });
            hashMap.put(FieldMask.getDescriptor().getFullName(), new WellKnownTypeParser() { // from class: com.google.protobuf.util.JsonFormat.ParserImpl.5
                @Override // com.google.protobuf.util.JsonFormat.ParserImpl.WellKnownTypeParser
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeFieldMask(jsonElement, builder);
                }
            });
            hashMap.put(Struct.getDescriptor().getFullName(), new WellKnownTypeParser() { // from class: com.google.protobuf.util.JsonFormat.ParserImpl.6
                @Override // com.google.protobuf.util.JsonFormat.ParserImpl.WellKnownTypeParser
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeStruct(jsonElement, builder);
                }
            });
            hashMap.put(ListValue.getDescriptor().getFullName(), new WellKnownTypeParser() { // from class: com.google.protobuf.util.JsonFormat.ParserImpl.7
                @Override // com.google.protobuf.util.JsonFormat.ParserImpl.WellKnownTypeParser
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeListValue(jsonElement, builder);
                }
            });
            hashMap.put(Value.getDescriptor().getFullName(), new WellKnownTypeParser() { // from class: com.google.protobuf.util.JsonFormat.ParserImpl.8
                @Override // com.google.protobuf.util.JsonFormat.ParserImpl.WellKnownTypeParser
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeValue(jsonElement, builder);
                }
            });
            return hashMap;
        }

        private Map<String, Descriptors.FieldDescriptor> getFieldNameMap(Descriptors.Descriptor descriptor) {
            if (!this.fieldNameMaps.containsKey(descriptor)) {
                HashMap hashMap = new HashMap();
                for (Descriptors.FieldDescriptor fieldDescriptor : descriptor.getFields()) {
                    hashMap.put(fieldDescriptor.getName(), fieldDescriptor);
                    hashMap.put(fieldDescriptor.getJsonName(), fieldDescriptor);
                }
                this.fieldNameMaps.put(descriptor, hashMap);
                return hashMap;
            }
            return this.fieldNameMaps.get(descriptor);
        }

        private void merge(JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            WellKnownTypeParser wellKnownTypeParser = wellKnownTypeParsers.get(builder.getDescriptorForType().getFullName());
            if (wellKnownTypeParser != null) {
                wellKnownTypeParser.merge(this, jsonElement, builder);
            } else {
                mergeMessage(jsonElement, builder, false);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAny(JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            Descriptors.Descriptor descriptorForType = builder.getDescriptorForType();
            Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName("type_url");
            Descriptors.FieldDescriptor findFieldByName2 = descriptorForType.findFieldByName("value");
            if (findFieldByName == null || findFieldByName2 == null || findFieldByName.getType() != Descriptors.FieldDescriptor.Type.STRING || findFieldByName2.getType() != Descriptors.FieldDescriptor.Type.BYTES) {
                throw new InvalidProtocolBufferException("Invalid Any type.");
            }
            if (!(jsonElement instanceof JsonObject)) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Expect message object but got: ", jsonElement));
            }
            JsonObject jsonObject = (JsonObject) jsonElement;
            if (jsonObject.entrySet().isEmpty()) {
                return;
            }
            JsonElement jsonElement2 = jsonObject.get(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE);
            if (jsonElement2 == null) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Missing type url when parsing: ", jsonElement));
            }
            String asString = jsonElement2.getAsString();
            Descriptors.Descriptor find = this.registry.find(JsonFormat.getTypeName(asString));
            if (find == null) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline72("Cannot resolve type: ", asString));
            }
            builder.mo10100setField(findFieldByName, asString);
            DynamicMessage.Builder mo10079newBuilderForType = DynamicMessage.getDefaultInstance(find).mo10079newBuilderForType();
            WellKnownTypeParser wellKnownTypeParser = wellKnownTypeParsers.get(find.getFullName());
            if (wellKnownTypeParser != null) {
                JsonElement jsonElement3 = jsonObject.get("value");
                if (jsonElement3 != null) {
                    wellKnownTypeParser.merge(this, jsonElement3, mo10079newBuilderForType);
                }
            } else {
                mergeMessage(jsonElement, mo10079newBuilderForType, true);
            }
            builder.mo10100setField(findFieldByName2, mo10079newBuilderForType.mo10084build().toByteString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDuration(JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            try {
                builder.mo9962mergeFrom(Durations.parse(jsonElement.getAsString()).toByteString());
            } catch (ParseException unused) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Failed to parse duration: ", jsonElement));
            }
        }

        private void mergeField(Descriptors.FieldDescriptor fieldDescriptor, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            if (fieldDescriptor.isRepeated()) {
                if (builder.getRepeatedFieldCount(fieldDescriptor) > 0) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Field ");
                    outline107.append(fieldDescriptor.getFullName());
                    outline107.append(" has already been set.");
                    throw new InvalidProtocolBufferException(outline107.toString());
                }
            } else if (builder.hasField(fieldDescriptor)) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Field ");
                outline1072.append(fieldDescriptor.getFullName());
                outline1072.append(" has already been set.");
                throw new InvalidProtocolBufferException(outline1072.toString());
            } else if (fieldDescriptor.getContainingOneof() != null && builder.getOneofFieldDescriptor(fieldDescriptor.getContainingOneof()) != null) {
                Descriptors.FieldDescriptor oneofFieldDescriptor = builder.getOneofFieldDescriptor(fieldDescriptor.getContainingOneof());
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Cannot set field ");
                outline1073.append(fieldDescriptor.getFullName());
                outline1073.append(" because another field ");
                outline1073.append(oneofFieldDescriptor.getFullName());
                outline1073.append(" belonging to the same oneof has already been set ");
                throw new InvalidProtocolBufferException(outline1073.toString());
            }
            if (!fieldDescriptor.isRepeated() || !(jsonElement instanceof JsonNull)) {
                if (fieldDescriptor.isMapField()) {
                    mergeMapField(fieldDescriptor, jsonElement, builder);
                } else if (fieldDescriptor.isRepeated()) {
                    mergeRepeatedField(fieldDescriptor, jsonElement, builder);
                } else {
                    Object parseFieldValue = parseFieldValue(fieldDescriptor, jsonElement, builder);
                    if (parseFieldValue == null) {
                        return;
                    }
                    builder.mo10100setField(fieldDescriptor, parseFieldValue);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFieldMask(JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            builder.mo9962mergeFrom(FieldMaskUtil.fromJsonString(jsonElement.getAsString()).toByteString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeListValue(JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            Descriptors.FieldDescriptor findFieldByName = builder.getDescriptorForType().findFieldByName("values");
            if (findFieldByName != null) {
                mergeRepeatedField(findFieldByName, jsonElement, builder);
                return;
            }
            throw new InvalidProtocolBufferException("Invalid ListValue type.");
        }

        private void mergeMapField(Descriptors.FieldDescriptor fieldDescriptor, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            if (jsonElement instanceof JsonObject) {
                Descriptors.Descriptor messageType = fieldDescriptor.getMessageType();
                Descriptors.FieldDescriptor findFieldByName = messageType.findFieldByName("key");
                Descriptors.FieldDescriptor findFieldByName2 = messageType.findFieldByName("value");
                if (findFieldByName == null || findFieldByName2 == null) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map field: ");
                    outline107.append(fieldDescriptor.getFullName());
                    throw new InvalidProtocolBufferException(outline107.toString());
                }
                for (Map.Entry<String, JsonElement> entry : ((JsonObject) jsonElement).entrySet()) {
                    Message.Builder mo9371newBuilderForField = builder.mo9371newBuilderForField(fieldDescriptor);
                    Object parseFieldValue = parseFieldValue(findFieldByName, new JsonPrimitive(entry.getKey()), mo9371newBuilderForField);
                    Object parseFieldValue2 = parseFieldValue(findFieldByName2, entry.getValue(), mo9371newBuilderForField);
                    if (parseFieldValue2 == null) {
                        throw new InvalidProtocolBufferException("Map value cannot be null.");
                    }
                    mo9371newBuilderForField.mo10100setField(findFieldByName, parseFieldValue);
                    mo9371newBuilderForField.mo10100setField(findFieldByName2, parseFieldValue2);
                    builder.mo10083addRepeatedField(fieldDescriptor, mo9371newBuilderForField.mo10084build());
                }
                return;
            }
            throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Expect a map object but found: ", jsonElement));
        }

        private void mergeMessage(JsonElement jsonElement, Message.Builder builder, boolean z) throws InvalidProtocolBufferException {
            if (jsonElement instanceof JsonObject) {
                Map<String, Descriptors.FieldDescriptor> fieldNameMap = getFieldNameMap(builder.getDescriptorForType());
                for (Map.Entry<String, JsonElement> entry : ((JsonObject) jsonElement).entrySet()) {
                    if (!z || !entry.getKey().equals(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE)) {
                        Descriptors.FieldDescriptor fieldDescriptor = fieldNameMap.get(entry.getKey());
                        if (fieldDescriptor != null) {
                            mergeField(fieldDescriptor, entry.getValue(), builder);
                        } else if (!this.ignoringUnknownFields) {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot find field: ");
                            outline107.append(entry.getKey());
                            outline107.append(" in message ");
                            outline107.append(builder.getDescriptorForType().getFullName());
                            throw new InvalidProtocolBufferException(outline107.toString());
                        }
                    }
                }
                return;
            }
            throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Expect message object but got: ", jsonElement));
        }

        private void mergeRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            if (jsonElement instanceof JsonArray) {
                JsonArray jsonArray = (JsonArray) jsonElement;
                for (int i = 0; i < jsonArray.size(); i++) {
                    Object parseFieldValue = parseFieldValue(fieldDescriptor, jsonArray.get(i), builder);
                    if (parseFieldValue == null) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Repeated field elements cannot be null in field: ");
                        outline107.append(fieldDescriptor.getFullName());
                        throw new InvalidProtocolBufferException(outline107.toString());
                    }
                    builder.mo10083addRepeatedField(fieldDescriptor, parseFieldValue);
                }
                return;
            }
            throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Expect an array but found: ", jsonElement));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStruct(JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            Descriptors.FieldDescriptor findFieldByName = builder.getDescriptorForType().findFieldByName("fields");
            if (findFieldByName != null) {
                mergeMapField(findFieldByName, jsonElement, builder);
                return;
            }
            throw new InvalidProtocolBufferException("Invalid Struct type.");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeTimestamp(JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            try {
                builder.mo9962mergeFrom(Timestamps.parse(jsonElement.getAsString()).toByteString());
            } catch (ParseException unused) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Failed to parse timestamp: ", jsonElement));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeValue(JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            String str;
            Descriptors.FieldDescriptor findFieldByName;
            Object mo10084build;
            Descriptors.Descriptor descriptorForType = builder.getDescriptorForType();
            if (jsonElement instanceof JsonPrimitive) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElement;
                if (jsonPrimitive.isBoolean()) {
                    findFieldByName = descriptorForType.findFieldByName("bool_value");
                    mo10084build = Boolean.valueOf(jsonPrimitive.getAsBoolean());
                } else if (jsonPrimitive.isNumber()) {
                    findFieldByName = descriptorForType.findFieldByName("number_value");
                    mo10084build = Double.valueOf(jsonPrimitive.getAsDouble());
                } else {
                    findFieldByName = descriptorForType.findFieldByName("string_value");
                    mo10084build = jsonPrimitive.getAsString();
                }
            } else {
                if (jsonElement instanceof JsonObject) {
                    str = "struct_value";
                } else if (!(jsonElement instanceof JsonArray)) {
                    if (!(jsonElement instanceof JsonNull)) {
                        throw new IllegalStateException(GeneratedOutlineSupport1.outline61("Unexpected json data: ", jsonElement));
                    }
                    builder.mo10100setField(descriptorForType.findFieldByName("null_value"), NullValue.NULL_VALUE.getValueDescriptor());
                    return;
                } else {
                    str = "list_value";
                }
                findFieldByName = descriptorForType.findFieldByName(str);
                Message.Builder mo9371newBuilderForField = builder.mo9371newBuilderForField(findFieldByName);
                merge(jsonElement, mo9371newBuilderForField);
                mo10084build = mo9371newBuilderForField.mo10084build();
            }
            builder.mo10100setField(findFieldByName, mo10084build);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeWrapper(JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            Descriptors.Descriptor descriptorForType = builder.getDescriptorForType();
            Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName("value");
            if (findFieldByName != null) {
                builder.mo10100setField(findFieldByName, parseFieldValue(findFieldByName, jsonElement, builder));
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid wrapper type: ");
            outline107.append(descriptorForType.getFullName());
            throw new InvalidProtocolBufferException(outline107.toString());
        }

        private boolean parseBool(JsonElement jsonElement) throws InvalidProtocolBufferException {
            if (jsonElement.getAsString().equals("true")) {
                return true;
            }
            if (!jsonElement.getAsString().equals(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE)) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Invalid bool value: ", jsonElement));
            }
            return false;
        }

        private ByteString parseBytes(JsonElement jsonElement) throws InvalidProtocolBufferException {
            try {
                return ByteString.copyFrom(BaseEncoding.base64().decode(jsonElement.getAsString()));
            } catch (IllegalArgumentException unused) {
                return ByteString.copyFrom(BaseEncoding.base64Url().decode(jsonElement.getAsString()));
            }
        }

        private double parseDouble(JsonElement jsonElement) throws InvalidProtocolBufferException {
            if (jsonElement.getAsString().equals("NaN")) {
                return Double.NaN;
            }
            if (jsonElement.getAsString().equals("Infinity")) {
                return Double.POSITIVE_INFINITY;
            }
            if (jsonElement.getAsString().equals("-Infinity")) {
                return Double.NEGATIVE_INFINITY;
            }
            try {
                BigDecimal bigDecimal = new BigDecimal(jsonElement.getAsString());
                if (bigDecimal.compareTo(MAX_DOUBLE) <= 0 && bigDecimal.compareTo(MIN_DOUBLE) >= 0) {
                    return bigDecimal.doubleValue();
                }
                throw new InvalidProtocolBufferException("Out of range double value: " + jsonElement);
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Exception unused) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Not an double value: ", jsonElement));
            }
        }

        private Descriptors.EnumValueDescriptor parseEnum(Descriptors.EnumDescriptor enumDescriptor, JsonElement jsonElement) throws InvalidProtocolBufferException {
            String asString = jsonElement.getAsString();
            Descriptors.EnumValueDescriptor findValueByName = enumDescriptor.findValueByName(asString);
            if (findValueByName == null) {
                try {
                    int parseInt32 = parseInt32(jsonElement);
                    findValueByName = enumDescriptor.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3 ? enumDescriptor.findValueByNumberCreatingIfUnknown(parseInt32) : enumDescriptor.mo9850findValueByNumber(parseInt32);
                } catch (InvalidProtocolBufferException unused) {
                }
                if (findValueByName == null) {
                    StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Invalid enum value: ", asString, " for enum type: ");
                    outline115.append(enumDescriptor.getFullName());
                    throw new InvalidProtocolBufferException(outline115.toString());
                }
            }
            return findValueByName;
        }

        private Object parseFieldValue(Descriptors.FieldDescriptor fieldDescriptor, JsonElement jsonElement, Message.Builder builder) throws InvalidProtocolBufferException {
            if (jsonElement instanceof JsonNull) {
                if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE && fieldDescriptor.getMessageType().getFullName().equals(Value.getDescriptor().getFullName())) {
                    return builder.mo9371newBuilderForField(fieldDescriptor).mo9962mergeFrom(Value.newBuilder().setNullValueValue(0).mo10084build().toByteString()).mo10084build();
                } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM && fieldDescriptor.mo9296getEnumType().getFullName().equals(NullValue.getDescriptor().getFullName())) {
                    return fieldDescriptor.mo9296getEnumType().mo9850findValueByNumber(0);
                } else {
                    return null;
                }
            }
            switch (fieldDescriptor.getType().ordinal()) {
                case 0:
                    return Double.valueOf(parseDouble(jsonElement));
                case 1:
                    return Float.valueOf(parseFloat(jsonElement));
                case 2:
                case 15:
                case 17:
                    return Long.valueOf(parseInt64(jsonElement));
                case 3:
                case 5:
                    return Long.valueOf(parseUint64(jsonElement));
                case 4:
                case 14:
                case 16:
                    return Integer.valueOf(parseInt32(jsonElement));
                case 6:
                case 12:
                    return Integer.valueOf(parseUint32(jsonElement));
                case 7:
                    return Boolean.valueOf(parseBool(jsonElement));
                case 8:
                    return parseString(jsonElement);
                case 9:
                case 10:
                    int i = this.currentDepth;
                    if (i >= this.recursionLimit) {
                        throw new InvalidProtocolBufferException("Hit recursion limit.");
                    }
                    this.currentDepth = i + 1;
                    Message.Builder mo9371newBuilderForField = builder.mo9371newBuilderForField(fieldDescriptor);
                    merge(jsonElement, mo9371newBuilderForField);
                    this.currentDepth--;
                    return mo9371newBuilderForField.mo10084build();
                case 11:
                    return parseBytes(jsonElement);
                case 13:
                    return parseEnum(fieldDescriptor.mo9296getEnumType(), jsonElement);
                default:
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid field type: ");
                    outline107.append(fieldDescriptor.getType());
                    throw new InvalidProtocolBufferException(outline107.toString());
            }
        }

        private float parseFloat(JsonElement jsonElement) throws InvalidProtocolBufferException {
            if (jsonElement.getAsString().equals("NaN")) {
                return Float.NaN;
            }
            if (jsonElement.getAsString().equals("Infinity")) {
                return Float.POSITIVE_INFINITY;
            }
            if (jsonElement.getAsString().equals("-Infinity")) {
                return Float.NEGATIVE_INFINITY;
            }
            try {
                double parseDouble = Double.parseDouble(jsonElement.getAsString());
                if (parseDouble <= 3.402826869208755E38d && parseDouble >= -3.402826869208755E38d) {
                    return (float) parseDouble;
                }
                throw new InvalidProtocolBufferException("Out of range float value: " + jsonElement);
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Exception unused) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Not a float value: ", jsonElement));
            }
        }

        private int parseInt32(JsonElement jsonElement) throws InvalidProtocolBufferException {
            try {
                try {
                    return Integer.parseInt(jsonElement.getAsString());
                } catch (Exception unused) {
                    return new BigDecimal(jsonElement.getAsString()).intValueExact();
                }
            } catch (Exception unused2) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Not an int32 value: ", jsonElement));
            }
        }

        private long parseInt64(JsonElement jsonElement) throws InvalidProtocolBufferException {
            try {
                try {
                    return Long.parseLong(jsonElement.getAsString());
                } catch (Exception unused) {
                    return new BigDecimal(jsonElement.getAsString()).longValueExact();
                }
            } catch (Exception unused2) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Not an int64 value: ", jsonElement));
            }
        }

        private String parseString(JsonElement jsonElement) {
            return jsonElement.getAsString();
        }

        private int parseUint32(JsonElement jsonElement) throws InvalidProtocolBufferException {
            try {
                try {
                    long parseLong = Long.parseLong(jsonElement.getAsString());
                    if (parseLong >= 0 && parseLong <= BodyPartID.bodyIdMax) {
                        return (int) parseLong;
                    }
                    throw new InvalidProtocolBufferException("Out of range uint32 value: " + jsonElement);
                } catch (InvalidProtocolBufferException e) {
                    throw e;
                } catch (Exception unused) {
                    BigInteger bigIntegerExact = new BigDecimal(jsonElement.getAsString()).toBigIntegerExact();
                    if (bigIntegerExact.signum() >= 0 && bigIntegerExact.compareTo(new BigInteger("FFFFFFFF", 16)) <= 0) {
                        return bigIntegerExact.intValue();
                    }
                    throw new InvalidProtocolBufferException("Out of range uint32 value: " + jsonElement);
                }
            } catch (InvalidProtocolBufferException e2) {
                throw e2;
            } catch (Exception unused2) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Not an uint32 value: ", jsonElement));
            }
        }

        private long parseUint64(JsonElement jsonElement) throws InvalidProtocolBufferException {
            try {
                BigInteger bigIntegerExact = new BigDecimal(jsonElement.getAsString()).toBigIntegerExact();
                if (bigIntegerExact.compareTo(BigInteger.ZERO) >= 0 && bigIntegerExact.compareTo(MAX_UINT64) <= 0) {
                    return bigIntegerExact.longValue();
                }
                throw new InvalidProtocolBufferException("Out of range uint64 value: " + jsonElement);
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Exception unused) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline61("Not an uint64 value: ", jsonElement));
            }
        }

        void merge(Reader reader, Message.Builder builder) throws IOException {
            try {
                JsonReader jsonReader = new JsonReader(reader);
                jsonReader.setLenient(false);
                merge(this.jsonParser.parse(jsonReader), builder);
            } catch (JsonIOException e) {
                if (!(e.getCause() instanceof IOException)) {
                    throw new InvalidProtocolBufferException(e.getMessage());
                }
                throw ((IOException) e.getCause());
            } catch (InvalidProtocolBufferException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new InvalidProtocolBufferException(e3.getMessage());
            }
        }

        void merge(String str, Message.Builder builder) throws InvalidProtocolBufferException {
            try {
                JsonReader jsonReader = new JsonReader(new StringReader(str));
                jsonReader.setLenient(false);
                merge(this.jsonParser.parse(jsonReader), builder);
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Exception e2) {
                throw new InvalidProtocolBufferException(e2.getMessage());
            }
        }
    }

    /* loaded from: classes3.dex */
    private static final class PrettyTextGenerator implements TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;

        private PrettyTextGenerator(Appendable appendable) {
            this.indent = new StringBuilder();
            this.atStartOfLine = true;
            this.output = appendable;
        }

        /* synthetic */ PrettyTextGenerator(Appendable appendable, AnonymousClass1 anonymousClass1) {
            this(appendable);
        }

        private void write(CharSequence charSequence) throws IOException {
            if (charSequence.length() == 0) {
                return;
            }
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.indent);
            }
            this.output.append(charSequence);
        }

        @Override // com.google.protobuf.util.JsonFormat.TextGenerator
        public void indent() {
            this.indent.append("  ");
        }

        @Override // com.google.protobuf.util.JsonFormat.TextGenerator
        public void outdent() {
            int length = this.indent.length();
            if (length >= 2) {
                this.indent.delete(length - 2, length);
                return;
            }
            throw new IllegalArgumentException(" Outdent() without matching Indent().");
        }

        @Override // com.google.protobuf.util.JsonFormat.TextGenerator
        public void print(CharSequence charSequence) throws IOException {
            int length = charSequence.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (charSequence.charAt(i2) == '\n') {
                    int i3 = i2 + 1;
                    write(charSequence.subSequence(i, i3));
                    this.atStartOfLine = true;
                    i = i3;
                }
            }
            write(charSequence.subSequence(i, length));
        }
    }

    /* loaded from: classes3.dex */
    public static class Printer {
        private boolean alwaysOutputDefaultValueFields;
        private Set<Descriptors.FieldDescriptor> includingDefaultValueFields;
        private final boolean omittingInsignificantWhitespace;
        private final boolean preservingProtoFieldNames;
        private final TypeRegistry registry;

        private Printer(TypeRegistry typeRegistry, boolean z, Set<Descriptors.FieldDescriptor> set, boolean z2, boolean z3) {
            this.registry = typeRegistry;
            this.alwaysOutputDefaultValueFields = z;
            this.includingDefaultValueFields = set;
            this.preservingProtoFieldNames = z2;
            this.omittingInsignificantWhitespace = z3;
        }

        /* synthetic */ Printer(TypeRegistry typeRegistry, boolean z, Set set, boolean z2, boolean z3, AnonymousClass1 anonymousClass1) {
            this(typeRegistry, z, set, z2, z3);
        }

        private void checkUnsetIncludingDefaultValueFields() {
            if (this.alwaysOutputDefaultValueFields || !this.includingDefaultValueFields.isEmpty()) {
                throw new IllegalStateException("JsonFormat includingDefaultValueFields has already been set.");
            }
        }

        public void appendTo(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
            new PrinterImpl(this.registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, this.preservingProtoFieldNames, appendable, this.omittingInsignificantWhitespace).print(messageOrBuilder);
        }

        public Printer includingDefaultValueFields() {
            checkUnsetIncludingDefaultValueFields();
            return new Printer(this.registry, true, Collections.emptySet(), this.preservingProtoFieldNames, this.omittingInsignificantWhitespace);
        }

        public Printer includingDefaultValueFields(Set<Descriptors.FieldDescriptor> set) {
            Preconditions.checkArgument(set != null && !set.isEmpty(), "Non-empty Set must be supplied for includingDefaultValueFields.");
            checkUnsetIncludingDefaultValueFields();
            return new Printer(this.registry, false, set, this.preservingProtoFieldNames, this.omittingInsignificantWhitespace);
        }

        public Printer omittingInsignificantWhitespace() {
            return new Printer(this.registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, this.preservingProtoFieldNames, true);
        }

        public Printer preservingProtoFieldNames() {
            return new Printer(this.registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, true, this.omittingInsignificantWhitespace);
        }

        public String print(MessageOrBuilder messageOrBuilder) throws InvalidProtocolBufferException {
            try {
                StringBuilder sb = new StringBuilder();
                appendTo(messageOrBuilder, sb);
                return sb.toString();
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new IllegalStateException(e2);
            }
        }

        public Printer usingTypeRegistry(TypeRegistry typeRegistry) {
            if (this.registry == TypeRegistry.getEmptyTypeRegistry()) {
                return new Printer(typeRegistry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, this.preservingProtoFieldNames, this.omittingInsignificantWhitespace);
            }
            throw new IllegalArgumentException("Only one registry is allowed.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class PrinterImpl {
        private static final Map<String, WellKnownTypePrinter> wellKnownTypePrinters = buildWellKnownTypePrinters();
        private final boolean alwaysOutputDefaultValueFields;
        private final CharSequence blankOrNewLine;
        private final CharSequence blankOrSpace;
        private final TextGenerator generator;
        private final Gson gson = GsonHolder.DEFAULT_GSON;
        private final Set<Descriptors.FieldDescriptor> includingDefaultValueFields;
        private final boolean preservingProtoFieldNames;
        private final TypeRegistry registry;

        /* loaded from: classes3.dex */
        private static class GsonHolder {
            private static final Gson DEFAULT_GSON = new GsonBuilder().disableHtmlEscaping().create();

            private GsonHolder() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public interface WellKnownTypePrinter {
            void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException;
        }

        PrinterImpl(TypeRegistry typeRegistry, boolean z, Set<Descriptors.FieldDescriptor> set, boolean z2, Appendable appendable, boolean z3) {
            String str;
            this.registry = typeRegistry;
            this.alwaysOutputDefaultValueFields = z;
            this.includingDefaultValueFields = set;
            this.preservingProtoFieldNames = z2;
            if (z3) {
                this.generator = new CompactTextGenerator(appendable, null);
                str = "";
                this.blankOrSpace = str;
            } else {
                this.generator = new PrettyTextGenerator(appendable, null);
                this.blankOrSpace = " ";
                str = "\n";
            }
            this.blankOrNewLine = str;
        }

        private static Map<String, WellKnownTypePrinter> buildWellKnownTypePrinters() {
            HashMap hashMap = new HashMap();
            hashMap.put(Any.getDescriptor().getFullName(), new WellKnownTypePrinter() { // from class: com.google.protobuf.util.JsonFormat.PrinterImpl.1
                @Override // com.google.protobuf.util.JsonFormat.PrinterImpl.WellKnownTypePrinter
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printAny(messageOrBuilder);
                }
            });
            WellKnownTypePrinter wellKnownTypePrinter = new WellKnownTypePrinter() { // from class: com.google.protobuf.util.JsonFormat.PrinterImpl.2
                @Override // com.google.protobuf.util.JsonFormat.PrinterImpl.WellKnownTypePrinter
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printWrapper(messageOrBuilder);
                }
            };
            hashMap.put(BoolValue.getDescriptor().getFullName(), wellKnownTypePrinter);
            hashMap.put(Int32Value.getDescriptor().getFullName(), wellKnownTypePrinter);
            hashMap.put(UInt32Value.getDescriptor().getFullName(), wellKnownTypePrinter);
            hashMap.put(Int64Value.getDescriptor().getFullName(), wellKnownTypePrinter);
            hashMap.put(UInt64Value.getDescriptor().getFullName(), wellKnownTypePrinter);
            hashMap.put(StringValue.getDescriptor().getFullName(), wellKnownTypePrinter);
            hashMap.put(BytesValue.getDescriptor().getFullName(), wellKnownTypePrinter);
            hashMap.put(FloatValue.getDescriptor().getFullName(), wellKnownTypePrinter);
            hashMap.put(DoubleValue.getDescriptor().getFullName(), wellKnownTypePrinter);
            hashMap.put(Timestamp.getDescriptor().getFullName(), new WellKnownTypePrinter() { // from class: com.google.protobuf.util.JsonFormat.PrinterImpl.3
                @Override // com.google.protobuf.util.JsonFormat.PrinterImpl.WellKnownTypePrinter
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printTimestamp(messageOrBuilder);
                }
            });
            hashMap.put(Duration.getDescriptor().getFullName(), new WellKnownTypePrinter() { // from class: com.google.protobuf.util.JsonFormat.PrinterImpl.4
                @Override // com.google.protobuf.util.JsonFormat.PrinterImpl.WellKnownTypePrinter
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printDuration(messageOrBuilder);
                }
            });
            hashMap.put(FieldMask.getDescriptor().getFullName(), new WellKnownTypePrinter() { // from class: com.google.protobuf.util.JsonFormat.PrinterImpl.5
                @Override // com.google.protobuf.util.JsonFormat.PrinterImpl.WellKnownTypePrinter
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printFieldMask(messageOrBuilder);
                }
            });
            hashMap.put(Struct.getDescriptor().getFullName(), new WellKnownTypePrinter() { // from class: com.google.protobuf.util.JsonFormat.PrinterImpl.6
                @Override // com.google.protobuf.util.JsonFormat.PrinterImpl.WellKnownTypePrinter
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printStruct(messageOrBuilder);
                }
            });
            hashMap.put(Value.getDescriptor().getFullName(), new WellKnownTypePrinter() { // from class: com.google.protobuf.util.JsonFormat.PrinterImpl.7
                @Override // com.google.protobuf.util.JsonFormat.PrinterImpl.WellKnownTypePrinter
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printValue(messageOrBuilder);
                }
            });
            hashMap.put(ListValue.getDescriptor().getFullName(), new WellKnownTypePrinter() { // from class: com.google.protobuf.util.JsonFormat.PrinterImpl.8
                @Override // com.google.protobuf.util.JsonFormat.PrinterImpl.WellKnownTypePrinter
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printListValue(messageOrBuilder);
                }
            });
            return hashMap;
        }

        private void print(MessageOrBuilder messageOrBuilder, String str) throws IOException {
            boolean z;
            Map<Descriptors.FieldDescriptor, Object> map;
            TextGenerator textGenerator = this.generator;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
            outline107.append((Object) this.blankOrNewLine);
            textGenerator.print(outline107.toString());
            this.generator.indent();
            if (str != null) {
                TextGenerator textGenerator2 = this.generator;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("\"@type\":");
                outline1072.append((Object) this.blankOrSpace);
                outline1072.append(this.gson.toJson(str));
                textGenerator2.print(outline1072.toString());
                z = true;
            } else {
                z = false;
            }
            if (this.alwaysOutputDefaultValueFields || !this.includingDefaultValueFields.isEmpty()) {
                TreeMap treeMap = new TreeMap(messageOrBuilder.getAllFields());
                for (Descriptors.FieldDescriptor fieldDescriptor : messageOrBuilder.getDescriptorForType().getFields()) {
                    if (fieldDescriptor.isOptional()) {
                        if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE || messageOrBuilder.hasField(fieldDescriptor)) {
                            if (fieldDescriptor.getContainingOneof() != null && !messageOrBuilder.hasField(fieldDescriptor)) {
                            }
                        }
                    }
                    if (!treeMap.containsKey(fieldDescriptor) && (this.alwaysOutputDefaultValueFields || this.includingDefaultValueFields.contains(fieldDescriptor))) {
                        treeMap.put(fieldDescriptor, messageOrBuilder.getField(fieldDescriptor));
                    }
                }
                map = treeMap;
            } else {
                map = messageOrBuilder.getAllFields();
            }
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
                if (z) {
                    TextGenerator textGenerator3 = this.generator;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(",");
                    outline1073.append((Object) this.blankOrNewLine);
                    textGenerator3.print(outline1073.toString());
                } else {
                    z = true;
                }
                printField(entry.getKey(), entry.getValue());
            }
            if (z) {
                this.generator.print(this.blankOrNewLine);
            }
            this.generator.outdent();
            this.generator.print(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printAny(MessageOrBuilder messageOrBuilder) throws IOException {
            if (Any.getDefaultInstance().equals(messageOrBuilder)) {
                this.generator.print("{}");
                return;
            }
            Descriptors.Descriptor descriptorForType = messageOrBuilder.getDescriptorForType();
            Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName("type_url");
            Descriptors.FieldDescriptor findFieldByName2 = descriptorForType.findFieldByName("value");
            if (findFieldByName == null || findFieldByName2 == null || findFieldByName.getType() != Descriptors.FieldDescriptor.Type.STRING || findFieldByName2.getType() != Descriptors.FieldDescriptor.Type.BYTES) {
                throw new InvalidProtocolBufferException("Invalid Any type.");
            }
            String str = (String) messageOrBuilder.getField(findFieldByName);
            String typeName = JsonFormat.getTypeName(str);
            Descriptors.Descriptor find = this.registry.find(typeName);
            if (find == null) {
                throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline72("Cannot find type for url: ", str));
            }
            DynamicMessage mo8396parseFrom = DynamicMessage.getDefaultInstance(find).mo9954getParserForType().mo8396parseFrom((ByteString) messageOrBuilder.getField(findFieldByName2));
            WellKnownTypePrinter wellKnownTypePrinter = wellKnownTypePrinters.get(typeName);
            if (wellKnownTypePrinter == null) {
                print(mo8396parseFrom, str);
                return;
            }
            TextGenerator textGenerator = this.generator;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
            outline107.append((Object) this.blankOrNewLine);
            textGenerator.print(outline107.toString());
            this.generator.indent();
            TextGenerator textGenerator2 = this.generator;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("\"@type\":");
            outline1072.append((Object) this.blankOrSpace);
            outline1072.append(this.gson.toJson(str));
            outline1072.append(",");
            outline1072.append((Object) this.blankOrNewLine);
            textGenerator2.print(outline1072.toString());
            TextGenerator textGenerator3 = this.generator;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("\"value\":");
            outline1073.append((Object) this.blankOrSpace);
            textGenerator3.print(outline1073.toString());
            wellKnownTypePrinter.print(this, mo8396parseFrom);
            this.generator.print(this.blankOrNewLine);
            this.generator.outdent();
            this.generator.print(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printDuration(MessageOrBuilder messageOrBuilder) throws IOException {
            Duration parseFrom = Duration.parseFrom(toByteString(messageOrBuilder));
            TextGenerator textGenerator = this.generator;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline107.append(Durations.toString(parseFrom));
            outline107.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            textGenerator.print(outline107.toString());
        }

        private void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) throws IOException {
            TextGenerator textGenerator;
            StringBuilder outline107;
            String jsonName;
            if (this.preservingProtoFieldNames) {
                textGenerator = this.generator;
                outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                jsonName = fieldDescriptor.getName();
            } else {
                textGenerator = this.generator;
                outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                jsonName = fieldDescriptor.getJsonName();
            }
            outline107.append(jsonName);
            outline107.append("\":");
            outline107.append((Object) this.blankOrSpace);
            textGenerator.print(outline107.toString());
            if (fieldDescriptor.isMapField()) {
                printMapFieldValue(fieldDescriptor, obj);
            } else if (fieldDescriptor.isRepeated()) {
                printRepeatedFieldValue(fieldDescriptor, obj);
            } else {
                printSingleFieldValue(fieldDescriptor, obj);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printFieldMask(MessageOrBuilder messageOrBuilder) throws IOException {
            FieldMask parseFrom = FieldMask.parseFrom(toByteString(messageOrBuilder));
            TextGenerator textGenerator = this.generator;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline107.append(FieldMaskUtil.toJsonString(parseFrom));
            outline107.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            textGenerator.print(outline107.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printListValue(MessageOrBuilder messageOrBuilder) throws IOException {
            Descriptors.FieldDescriptor findFieldByName = messageOrBuilder.getDescriptorForType().findFieldByName("values");
            if (findFieldByName != null) {
                printRepeatedFieldValue(findFieldByName, messageOrBuilder.getField(findFieldByName));
                return;
            }
            throw new InvalidProtocolBufferException("Invalid ListValue type.");
        }

        private void printMapFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj) throws IOException {
            Descriptors.Descriptor messageType = fieldDescriptor.getMessageType();
            Descriptors.FieldDescriptor findFieldByName = messageType.findFieldByName("key");
            Descriptors.FieldDescriptor findFieldByName2 = messageType.findFieldByName("value");
            if (findFieldByName == null || findFieldByName2 == null) {
                throw new InvalidProtocolBufferException("Invalid map field.");
            }
            TextGenerator textGenerator = this.generator;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
            outline107.append((Object) this.blankOrNewLine);
            textGenerator.print(outline107.toString());
            this.generator.indent();
            boolean z = false;
            for (Message message : (List) obj) {
                Object field = message.getField(findFieldByName);
                Object field2 = message.getField(findFieldByName2);
                if (z) {
                    TextGenerator textGenerator2 = this.generator;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(",");
                    outline1072.append((Object) this.blankOrNewLine);
                    textGenerator2.print(outline1072.toString());
                } else {
                    z = true;
                }
                printSingleFieldValue(findFieldByName, field, true);
                TextGenerator textGenerator3 = this.generator;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(":");
                outline1073.append((Object) this.blankOrSpace);
                textGenerator3.print(outline1073.toString());
                printSingleFieldValue(findFieldByName2, field2);
            }
            if (z) {
                this.generator.print(this.blankOrNewLine);
            }
            this.generator.outdent();
            this.generator.print(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        }

        private void printRepeatedFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj) throws IOException {
            this.generator.print("[");
            boolean z = false;
            for (Object obj2 : (List) obj) {
                if (z) {
                    TextGenerator textGenerator = this.generator;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(",");
                    outline107.append((Object) this.blankOrSpace);
                    textGenerator.print(outline107.toString());
                } else {
                    z = true;
                }
                printSingleFieldValue(fieldDescriptor, obj2);
            }
            this.generator.print("]");
        }

        private void printSingleFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj) throws IOException {
            printSingleFieldValue(fieldDescriptor, obj, false);
        }

        /* JADX WARN: Code restructure failed: missing block: B:48:0x010e, code lost:
            if (r7.floatValue() < 0.0f) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0140, code lost:
            if (r7.doubleValue() < com.amazon.comms.ringservice.webrtc.FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x0142, code lost:
            r5.generator.print("\"-Infinity\"");
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x0148, code lost:
            r5.generator.print("\"Infinity\"");
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:?, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void printSingleFieldValue(com.google.protobuf.Descriptors.FieldDescriptor r6, java.lang.Object r7, boolean r8) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 398
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.util.JsonFormat.PrinterImpl.printSingleFieldValue(com.google.protobuf.Descriptors$FieldDescriptor, java.lang.Object, boolean):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printStruct(MessageOrBuilder messageOrBuilder) throws IOException {
            Descriptors.FieldDescriptor findFieldByName = messageOrBuilder.getDescriptorForType().findFieldByName("fields");
            if (findFieldByName != null) {
                printMapFieldValue(findFieldByName, messageOrBuilder.getField(findFieldByName));
                return;
            }
            throw new InvalidProtocolBufferException("Invalid Struct type.");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printTimestamp(MessageOrBuilder messageOrBuilder) throws IOException {
            Timestamp parseFrom = Timestamp.parseFrom(toByteString(messageOrBuilder));
            TextGenerator textGenerator = this.generator;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline107.append(Timestamps.toString(parseFrom));
            outline107.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            textGenerator.print(outline107.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printValue(MessageOrBuilder messageOrBuilder) throws IOException {
            Map<Descriptors.FieldDescriptor, Object> allFields = messageOrBuilder.getAllFields();
            if (allFields.isEmpty()) {
                this.generator.print("null");
            } else if (allFields.size() != 1) {
                throw new InvalidProtocolBufferException("Invalid Value type.");
            } else {
                for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : allFields.entrySet()) {
                    printSingleFieldValue(entry.getKey(), entry.getValue());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printWrapper(MessageOrBuilder messageOrBuilder) throws IOException {
            Descriptors.FieldDescriptor findFieldByName = messageOrBuilder.getDescriptorForType().findFieldByName("value");
            if (findFieldByName != null) {
                printSingleFieldValue(findFieldByName, messageOrBuilder.getField(findFieldByName));
                return;
            }
            throw new InvalidProtocolBufferException("Invalid Wrapper type.");
        }

        private ByteString toByteString(MessageOrBuilder messageOrBuilder) {
            return (messageOrBuilder instanceof Message ? (Message) messageOrBuilder : ((Message.Builder) messageOrBuilder).mo10084build()).toByteString();
        }

        void print(MessageOrBuilder messageOrBuilder) throws IOException {
            WellKnownTypePrinter wellKnownTypePrinter = wellKnownTypePrinters.get(messageOrBuilder.getDescriptorForType().getFullName());
            if (wellKnownTypePrinter != null) {
                wellKnownTypePrinter.print(this, messageOrBuilder);
            } else {
                print(messageOrBuilder, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface TextGenerator {
        void indent();

        void outdent();

        void print(CharSequence charSequence) throws IOException;
    }

    /* loaded from: classes3.dex */
    public static class TypeRegistry {
        private final Map<String, Descriptors.Descriptor> types;

        /* loaded from: classes3.dex */
        public static class Builder {
            private final Set<String> files;
            private Map<String, Descriptors.Descriptor> types;

            private Builder() {
                this.files = new HashSet();
                this.types = new HashMap();
            }

            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private void addFile(Descriptors.FileDescriptor fileDescriptor) {
                if (!this.files.add(fileDescriptor.getFullName())) {
                    return;
                }
                for (Descriptors.FileDescriptor fileDescriptor2 : fileDescriptor.getDependencies()) {
                    addFile(fileDescriptor2);
                }
                for (Descriptors.Descriptor descriptor : fileDescriptor.getMessageTypes()) {
                    addMessage(descriptor);
                }
            }

            private void addMessage(Descriptors.Descriptor descriptor) {
                for (Descriptors.Descriptor descriptor2 : descriptor.getNestedTypes()) {
                    addMessage(descriptor2);
                }
                if (!this.types.containsKey(descriptor.getFullName())) {
                    this.types.put(descriptor.getFullName(), descriptor);
                    return;
                }
                Logger logger = JsonFormat.logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Type ");
                outline107.append(descriptor.getFullName());
                outline107.append(" is added multiple times.");
                logger.warning(outline107.toString());
            }

            public Builder add(Descriptors.Descriptor descriptor) {
                if (this.types != null) {
                    addFile(descriptor.getFile());
                    return this;
                }
                throw new IllegalStateException("A TypeRegistry.Builer can only be used once.");
            }

            public Builder add(Iterable<Descriptors.Descriptor> iterable) {
                if (this.types != null) {
                    for (Descriptors.Descriptor descriptor : iterable) {
                        addFile(descriptor.getFile());
                    }
                    return this;
                }
                throw new IllegalStateException("A TypeRegistry.Builer can only be used once.");
            }

            public TypeRegistry build() {
                TypeRegistry typeRegistry = new TypeRegistry(this.types, null);
                this.types = null;
                return typeRegistry;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class EmptyTypeRegistryHolder {
            private static final TypeRegistry EMPTY = new TypeRegistry(Collections.emptyMap(), null);

            private EmptyTypeRegistryHolder() {
            }
        }

        private TypeRegistry(Map<String, Descriptors.Descriptor> map) {
            this.types = map;
        }

        /* synthetic */ TypeRegistry(Map map, AnonymousClass1 anonymousClass1) {
            this(map);
        }

        public static TypeRegistry getEmptyTypeRegistry() {
            return EmptyTypeRegistryHolder.EMPTY;
        }

        public static Builder newBuilder() {
            return new Builder(null);
        }

        public Descriptors.Descriptor find(String str) {
            return this.types.get(str);
        }
    }

    private JsonFormat() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getTypeName(String str) throws InvalidProtocolBufferException {
        String[] split = str.split("/");
        if (split.length != 1) {
            return split[split.length - 1];
        }
        throw new InvalidProtocolBufferException(GeneratedOutlineSupport1.outline72("Invalid type url found: ", str));
    }

    public static Parser parser() {
        return new Parser(TypeRegistry.getEmptyTypeRegistry(), false, 100, null);
    }

    public static Printer printer() {
        return new Printer(TypeRegistry.getEmptyTypeRegistry(), false, Collections.emptySet(), false, false, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String unsignedToString(int i) {
        return i >= 0 ? Integer.toString(i) : Long.toString(i & BodyPartID.bodyIdMax);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String unsignedToString(long j) {
        return j >= 0 ? Long.toString(j) : BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }
}
