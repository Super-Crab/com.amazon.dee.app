package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.ConfigString;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class SerializedConfigValue extends AbstractConfigValue implements Externalizable {
    private static final long serialVersionUID = 1;
    private ConfigValue value;
    private boolean wasConfig;

    /* renamed from: com.typesafe.config.impl.SerializedConfigValue$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField;
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType = new int[SerializedValueType.values().length];

        static {
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[SerializedValueType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[SerializedValueType.NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[SerializedValueType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[SerializedValueType.LONG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[SerializedValueType.DOUBLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[SerializedValueType.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[SerializedValueType.LIST.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedValueType[SerializedValueType.OBJECT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField = new int[SerializedField.values().length];
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_DESCRIPTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_LINE_NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_END_LINE_NUMBER.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_URL.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_RESOURCE.ordinal()] = 6;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_COMMENTS.ordinal()] = 7;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_NULL_URL.ordinal()] = 8;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_NULL_RESOURCE.ordinal()] = 9;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ORIGIN_NULL_COMMENTS.ordinal()] = 10;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.END_MARKER.ordinal()] = 11;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ROOT_VALUE.ordinal()] = 12;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.ROOT_WAS_CONFIG.ordinal()] = 13;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.VALUE_DATA.ordinal()] = 14;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.VALUE_ORIGIN.ordinal()] = 15;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedField.UNKNOWN.ordinal()] = 16;
            } catch (NoSuchFieldError unused24) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class FieldOut {
        final SerializedField code;
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        final DataOutput data = new DataOutputStream(this.bytes);

        FieldOut(SerializedField serializedField) {
            this.code = serializedField;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum SerializedField {
        UNKNOWN,
        END_MARKER,
        ROOT_VALUE,
        ROOT_WAS_CONFIG,
        VALUE_DATA,
        VALUE_ORIGIN,
        ORIGIN_DESCRIPTION,
        ORIGIN_LINE_NUMBER,
        ORIGIN_END_LINE_NUMBER,
        ORIGIN_TYPE,
        ORIGIN_URL,
        ORIGIN_COMMENTS,
        ORIGIN_NULL_URL,
        ORIGIN_NULL_COMMENTS,
        ORIGIN_RESOURCE,
        ORIGIN_NULL_RESOURCE;

        static SerializedField forInt(int i) {
            if (i < values().length) {
                return values()[i];
            }
            return UNKNOWN;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum SerializedValueType {
        NULL(ConfigValueType.NULL),
        BOOLEAN(ConfigValueType.BOOLEAN),
        INT(ConfigValueType.NUMBER),
        LONG(ConfigValueType.NUMBER),
        DOUBLE(ConfigValueType.NUMBER),
        STRING(ConfigValueType.STRING),
        LIST(ConfigValueType.LIST),
        OBJECT(ConfigValueType.OBJECT);
        
        ConfigValueType configType;

        SerializedValueType(ConfigValueType configValueType) {
            this.configType = configValueType;
        }

        static SerializedValueType forInt(int i) {
            if (i < values().length) {
                return values()[i];
            }
            return null;
        }

        static SerializedValueType forValue(ConfigValue configValue) {
            SerializedValueType[] values;
            ConfigValueType valueType = configValue.valueType();
            if (valueType == ConfigValueType.NUMBER) {
                if (configValue instanceof ConfigInt) {
                    return INT;
                }
                if (configValue instanceof ConfigLong) {
                    return LONG;
                }
                if (configValue instanceof ConfigDouble) {
                    return DOUBLE;
                }
            } else {
                for (SerializedValueType serializedValueType : values()) {
                    if (serializedValueType.configType == valueType) {
                        return serializedValueType;
                    }
                }
            }
            throw new ConfigException.BugOrBroken("don't know how to serialize " + configValue);
        }
    }

    public SerializedConfigValue() {
        super(null);
    }

    private DataInput fieldIn(ObjectInput objectInput) throws IOException {
        byte[] bArr = new byte[objectInput.readInt()];
        objectInput.readFully(bArr);
        return new DataInputStream(new ByteArrayInputStream(bArr));
    }

    private static SerializedField readCode(DataInput dataInput) throws IOException {
        int readUnsignedByte = dataInput.readUnsignedByte();
        if (readUnsignedByte != SerializedField.UNKNOWN.ordinal()) {
            return SerializedField.forInt(readUnsignedByte);
        }
        throw new IOException(GeneratedOutlineSupport1.outline52("field code ", readUnsignedByte, " is not supposed to be on the wire"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleConfigOrigin readOrigin(DataInput dataInput, SimpleConfigOrigin simpleConfigOrigin) throws IOException {
        EnumMap enumMap = new EnumMap(SerializedField.class);
        while (true) {
            Object obj = null;
            SerializedField readCode = readCode(dataInput);
            switch (readCode.ordinal()) {
                case 0:
                    skipField(dataInput);
                    break;
                case 1:
                    return SimpleConfigOrigin.fromBase(simpleConfigOrigin, enumMap);
                case 2:
                case 3:
                case 4:
                case 5:
                    throw new IOException("Not expecting this field here: " + readCode);
                case 6:
                    dataInput.readInt();
                    obj = dataInput.readUTF();
                    break;
                case 7:
                    dataInput.readInt();
                    obj = Integer.valueOf(dataInput.readInt());
                    break;
                case 8:
                    dataInput.readInt();
                    obj = Integer.valueOf(dataInput.readInt());
                    break;
                case 9:
                    dataInput.readInt();
                    obj = Integer.valueOf(dataInput.readUnsignedByte());
                    break;
                case 10:
                    dataInput.readInt();
                    obj = dataInput.readUTF();
                    break;
                case 11:
                    dataInput.readInt();
                    int readInt = dataInput.readInt();
                    ArrayList arrayList = new ArrayList(readInt);
                    for (int i = 0; i < readInt; i++) {
                        arrayList.add(dataInput.readUTF());
                    }
                    obj = arrayList;
                    break;
                case 12:
                case 13:
                case 15:
                    dataInput.readInt();
                    obj = "";
                    break;
                case 14:
                    dataInput.readInt();
                    obj = dataInput.readUTF();
                    break;
            }
            if (obj != null) {
                enumMap.put((EnumMap) readCode, (SerializedField) obj);
            }
        }
    }

    private Object readResolve() throws ObjectStreamException {
        if (this.wasConfig) {
            return ((ConfigObject) this.value).mo10165toConfig();
        }
        return this.value;
    }

    private static AbstractConfigValue readValue(DataInput dataInput, SimpleConfigOrigin simpleConfigOrigin) throws IOException {
        AbstractConfigValue abstractConfigValue = null;
        SimpleConfigOrigin simpleConfigOrigin2 = null;
        while (true) {
            SerializedField readCode = readCode(dataInput);
            if (readCode == SerializedField.END_MARKER) {
                if (abstractConfigValue == null) {
                    throw new IOException("No value data found in serialization of value");
                }
                return abstractConfigValue;
            } else if (readCode == SerializedField.VALUE_DATA) {
                if (simpleConfigOrigin2 != null) {
                    dataInput.readInt();
                    abstractConfigValue = readValueData(dataInput, simpleConfigOrigin2);
                } else {
                    throw new IOException("Origin must be stored before value data");
                }
            } else if (readCode == SerializedField.VALUE_ORIGIN) {
                dataInput.readInt();
                simpleConfigOrigin2 = readOrigin(dataInput, simpleConfigOrigin);
            } else {
                skipField(dataInput);
            }
        }
    }

    private static AbstractConfigValue readValueData(DataInput dataInput, SimpleConfigOrigin simpleConfigOrigin) throws IOException {
        int readUnsignedByte = dataInput.readUnsignedByte();
        SerializedValueType forInt = SerializedValueType.forInt(readUnsignedByte);
        if (forInt != null) {
            int i = 0;
            switch (forInt.ordinal()) {
                case 0:
                    return new ConfigNull(simpleConfigOrigin);
                case 1:
                    return new ConfigBoolean(simpleConfigOrigin, dataInput.readBoolean());
                case 2:
                    return new ConfigInt(simpleConfigOrigin, dataInput.readInt(), dataInput.readUTF());
                case 3:
                    return new ConfigLong(simpleConfigOrigin, dataInput.readLong(), dataInput.readUTF());
                case 4:
                    return new ConfigDouble(simpleConfigOrigin, dataInput.readDouble(), dataInput.readUTF());
                case 5:
                    return new ConfigString.Quoted(simpleConfigOrigin, dataInput.readUTF());
                case 6:
                    int readInt = dataInput.readInt();
                    ArrayList arrayList = new ArrayList(readInt);
                    while (i < readInt) {
                        arrayList.add(readValue(dataInput, simpleConfigOrigin));
                        i++;
                    }
                    return new SimpleConfigList(simpleConfigOrigin, arrayList);
                case 7:
                    int readInt2 = dataInput.readInt();
                    HashMap hashMap = new HashMap(readInt2);
                    while (i < readInt2) {
                        hashMap.put(dataInput.readUTF(), readValue(dataInput, simpleConfigOrigin));
                        i++;
                    }
                    return new SimpleConfigObject(simpleConfigOrigin, hashMap);
                default:
                    throw new IOException("Unhandled serialized value type: " + forInt);
            }
        }
        throw new IOException(GeneratedOutlineSupport1.outline49("Unknown serialized value type: ", readUnsignedByte));
    }

    private static ConfigException shouldNotBeUsed() {
        return new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline40(SerializedConfigValue.class, new StringBuilder(), " should not exist outside of serialization"));
    }

    private static void skipField(DataInput dataInput) throws IOException {
        int readInt = dataInput.readInt();
        int skipBytes = dataInput.skipBytes(readInt);
        if (skipBytes < readInt) {
            dataInput.readFully(new byte[readInt - skipBytes]);
        }
    }

    private static void writeEndMarker(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(SerializedField.END_MARKER.ordinal());
    }

    private static void writeField(DataOutput dataOutput, FieldOut fieldOut) throws IOException {
        byte[] byteArray = fieldOut.bytes.toByteArray();
        dataOutput.writeByte(fieldOut.code.ordinal());
        dataOutput.writeInt(byteArray.length);
        dataOutput.write(byteArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeOrigin(DataOutput dataOutput, SimpleConfigOrigin simpleConfigOrigin, SimpleConfigOrigin simpleConfigOrigin2) throws IOException {
        Map<SerializedField, Object> emptyMap;
        if (simpleConfigOrigin != null) {
            emptyMap = simpleConfigOrigin.toFieldsDelta(simpleConfigOrigin2);
        } else {
            emptyMap = Collections.emptyMap();
        }
        for (Map.Entry<SerializedField, Object> entry : emptyMap.entrySet()) {
            FieldOut fieldOut = new FieldOut(entry.getKey());
            writeOriginField(fieldOut.data, fieldOut.code, entry.getValue());
            writeField(dataOutput, fieldOut);
        }
        writeEndMarker(dataOutput);
    }

    private static void writeOriginField(DataOutput dataOutput, SerializedField serializedField, Object obj) throws IOException {
        switch (serializedField.ordinal()) {
            case 6:
                dataOutput.writeUTF((String) obj);
                return;
            case 7:
                dataOutput.writeInt(((Integer) obj).intValue());
                return;
            case 8:
                dataOutput.writeInt(((Integer) obj).intValue());
                return;
            case 9:
                dataOutput.writeByte(((Integer) obj).intValue());
                return;
            case 10:
                dataOutput.writeUTF((String) obj);
                return;
            case 11:
                List<String> list = (List) obj;
                dataOutput.writeInt(list.size());
                for (String str : list) {
                    dataOutput.writeUTF(str);
                }
                return;
            case 12:
            case 13:
            case 15:
                return;
            case 14:
                dataOutput.writeUTF((String) obj);
                return;
            default:
                throw new IOException("Unhandled field from origin: " + serializedField);
        }
    }

    private static void writeValue(DataOutput dataOutput, ConfigValue configValue, SimpleConfigOrigin simpleConfigOrigin) throws IOException {
        FieldOut fieldOut = new FieldOut(SerializedField.VALUE_ORIGIN);
        writeOrigin(fieldOut.data, (SimpleConfigOrigin) configValue.mo10176origin(), simpleConfigOrigin);
        writeField(dataOutput, fieldOut);
        FieldOut fieldOut2 = new FieldOut(SerializedField.VALUE_DATA);
        writeValueData(fieldOut2.data, configValue);
        writeField(dataOutput, fieldOut2);
        writeEndMarker(dataOutput);
    }

    private static void writeValueData(DataOutput dataOutput, ConfigValue configValue) throws IOException {
        SerializedValueType forValue = SerializedValueType.forValue(configValue);
        dataOutput.writeByte(forValue.ordinal());
        switch (forValue.ordinal()) {
            case 0:
            default:
                return;
            case 1:
                dataOutput.writeBoolean(((ConfigBoolean) configValue).mo10253unwrapped().booleanValue());
                return;
            case 2:
                dataOutput.writeInt(((ConfigInt) configValue).mo10253unwrapped().intValue());
                dataOutput.writeUTF(((ConfigNumber) configValue).transformToString());
                return;
            case 3:
                dataOutput.writeLong(((ConfigLong) configValue).mo10253unwrapped().longValue());
                dataOutput.writeUTF(((ConfigNumber) configValue).transformToString());
                return;
            case 4:
                dataOutput.writeDouble(((ConfigDouble) configValue).mo10253unwrapped().doubleValue());
                dataOutput.writeUTF(((ConfigNumber) configValue).transformToString());
                return;
            case 5:
                dataOutput.writeUTF(((ConfigString) configValue).mo10253unwrapped());
                return;
            case 6:
                ConfigList<ConfigValue> configList = (ConfigList) configValue;
                dataOutput.writeInt(configList.size());
                for (ConfigValue configValue2 : configList) {
                    writeValue(dataOutput, configValue2, (SimpleConfigOrigin) configList.mo10176origin());
                }
                return;
            case 7:
                ConfigObject configObject = (ConfigObject) configValue;
                dataOutput.writeInt(configObject.size());
                for (Map.Entry<String, ConfigValue> entry : configObject.entrySet()) {
                    dataOutput.writeUTF(entry.getKey());
                    writeValue(dataOutput, entry.getValue(), (SimpleConfigOrigin) configObject.mo10176origin());
                }
                return;
        }
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean equals(Object obj) {
        if (!(obj instanceof SerializedConfigValue) || !canEqual(obj)) {
            return false;
        }
        SerializedConfigValue serializedConfigValue = (SerializedConfigValue) obj;
        return this.wasConfig == serializedConfigValue.wasConfig && this.value.equals(serializedConfigValue.value);
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public int hashCode() {
        return (((this.value.hashCode() + 41) * 41) + (this.wasConfig ? 1 : 0)) * 41;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        while (true) {
            SerializedField readCode = readCode(objectInput);
            if (readCode == SerializedField.END_MARKER) {
                return;
            }
            DataInput fieldIn = fieldIn(objectInput);
            if (readCode == SerializedField.ROOT_VALUE) {
                this.value = readValue(fieldIn, null);
            } else if (readCode == SerializedField.ROOT_WAS_CONFIG) {
                this.wasConfig = fieldIn.readBoolean();
            }
        }
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SerializedConfigValue.class.getSimpleName());
        sb.append("(value=");
        sb.append(this.value);
        sb.append(",wasConfig=");
        return GeneratedOutlineSupport1.outline97(sb, this.wasConfig, ")");
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped */
    public Object mo10253unwrapped() {
        throw shouldNotBeUsed();
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        throw shouldNotBeUsed();
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        if (((AbstractConfigValue) this.value).resolveStatus() == ResolveStatus.RESOLVED) {
            FieldOut fieldOut = new FieldOut(SerializedField.ROOT_VALUE);
            writeValue(fieldOut.data, this.value, null);
            writeField(objectOutput, fieldOut);
            FieldOut fieldOut2 = new FieldOut(SerializedField.ROOT_WAS_CONFIG);
            fieldOut2.data.writeBoolean(this.wasConfig);
            writeField(objectOutput, fieldOut2);
            writeEndMarker(objectOutput);
            return;
        }
        throw new NotSerializableException("tried to serialize a value with unresolved substitutions, need to Config#resolve() first, see API docs");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerializedConfigValue(ConfigValue configValue) {
        this();
        this.value = configValue;
        this.wasConfig = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy  reason: collision with other method in class */
    public SerializedConfigValue mo10238newCopy(ConfigOrigin configOrigin) {
        throw shouldNotBeUsed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerializedConfigValue(Config config) {
        this(config.mo10232root());
        this.wasConfig = true;
    }
}
