package com.amazon.device.setup.thrift;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.orig.EncodingUtils;
import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.TBaseHelper;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.TFieldIdEnum;
import org.apache.thrift.orig.meta_data.EnumMetaData;
import org.apache.thrift.orig.meta_data.FieldMetaData;
import org.apache.thrift.orig.meta_data.FieldValueMetaData;
import org.apache.thrift.orig.meta_data.ListMetaData;
import org.apache.thrift.orig.meta_data.StructMetaData;
import org.apache.thrift.orig.protocol.TCompactProtocol;
import org.apache.thrift.orig.protocol.TField;
import org.apache.thrift.orig.protocol.TList;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolUtil;
import org.apache.thrift.orig.protocol.TStruct;
import org.apache.thrift.orig.protocol.TTupleProtocol;
import org.apache.thrift.orig.scheme.IScheme;
import org.apache.thrift.orig.scheme.SchemeFactory;
import org.apache.thrift.orig.scheme.StandardScheme;
import org.apache.thrift.orig.scheme.TupleScheme;
import org.apache.thrift.orig.transport.TIOStreamTransport;
/* loaded from: classes12.dex */
public class DeviceDetails implements TBase<DeviceDetails, _Fields>, Serializable, Cloneable {
    private static final int __TETHERINGSUPPORTED_ISSET_ID = 0;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public String IPAddress;
    public String MAC;
    public String SWVersion;
    private byte __isset_bitfield;
    public LinkCodeSource codeSource;
    public APDetail currentAP;
    public CERTIFICATE deviceCertificate;
    public String deviceSerialNumber;
    public String deviceType;
    private _Fields[] optionals;
    public List<String> supportedCountryCodes;
    public List<String> supportedLocales;
    public List<String> supportedWakeWords;
    public boolean tetheringSupported;
    private static final TStruct STRUCT_DESC = new TStruct("DeviceDetails");
    private static final TField SWVERSION_FIELD_DESC = new TField("SWVersion", (byte) 11, 1);
    private static final TField MAC_FIELD_DESC = new TField("MAC", (byte) 11, 2);
    private static final TField CURRENT_AP_FIELD_DESC = new TField("currentAP", (byte) 12, 3);
    private static final TField IPADDRESS_FIELD_DESC = new TField("IPAddress", (byte) 11, 4);
    private static final TField DEVICE_CERTIFICATE_FIELD_DESC = new TField("deviceCertificate", (byte) 12, 5);
    private static final TField DEVICE_SERIAL_NUMBER_FIELD_DESC = new TField("deviceSerialNumber", (byte) 11, 6);
    private static final TField TETHERING_SUPPORTED_FIELD_DESC = new TField("tetheringSupported", (byte) 2, 7);
    private static final TField SUPPORTED_LOCALES_FIELD_DESC = new TField("supportedLocales", (byte) 15, 8);
    private static final TField SUPPORTED_COUNTRY_CODES_FIELD_DESC = new TField("supportedCountryCodes", (byte) 15, 9);
    private static final TField SUPPORTED_WAKE_WORDS_FIELD_DESC = new TField("supportedWakeWords", (byte) 15, 10);
    private static final TField DEVICE_TYPE_FIELD_DESC = new TField("deviceType", (byte) 11, 11);
    private static final TField CODE_SOURCE_FIELD_DESC = new TField("codeSource", (byte) 8, 12);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.DeviceDetails$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.SWVERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.MAC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.CURRENT_AP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.IPADDRESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.DEVICE_CERTIFICATE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.DEVICE_SERIAL_NUMBER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.TETHERING_SUPPORTED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.SUPPORTED_LOCALES.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.SUPPORTED_COUNTRY_CODES.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.SUPPORTED_WAKE_WORDS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.DEVICE_TYPE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceDetails$_Fields[_Fields.CODE_SOURCE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class DeviceDetailsStandardScheme extends StandardScheme<DeviceDetails> {
        private DeviceDetailsStandardScheme() {
        }

        /* synthetic */ DeviceDetailsStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, DeviceDetails deviceDetails) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    deviceDetails.validate();
                    return;
                }
                int i = 0;
                switch (readFieldBegin.id) {
                    case 1:
                        if (b == 11) {
                            deviceDetails.SWVersion = tProtocol.readString();
                            deviceDetails.setSWVersionIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 2:
                        if (b == 11) {
                            deviceDetails.MAC = tProtocol.readString();
                            deviceDetails.setMACIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 3:
                        if (b == 12) {
                            deviceDetails.currentAP = new APDetail();
                            deviceDetails.currentAP.read(tProtocol);
                            deviceDetails.setCurrentAPIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 4:
                        if (b == 11) {
                            deviceDetails.IPAddress = tProtocol.readString();
                            deviceDetails.setIPAddressIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 5:
                        if (b == 12) {
                            deviceDetails.deviceCertificate = new CERTIFICATE();
                            deviceDetails.deviceCertificate.read(tProtocol);
                            deviceDetails.setDeviceCertificateIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 6:
                        if (b == 11) {
                            deviceDetails.deviceSerialNumber = tProtocol.readString();
                            deviceDetails.setDeviceSerialNumberIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 7:
                        if (b == 2) {
                            deviceDetails.tetheringSupported = tProtocol.readBool();
                            deviceDetails.setTetheringSupportedIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 8:
                        if (b == 15) {
                            TList readListBegin = tProtocol.readListBegin();
                            deviceDetails.supportedLocales = new ArrayList(readListBegin.size);
                            while (i < readListBegin.size) {
                                deviceDetails.supportedLocales.add(tProtocol.readString());
                                i++;
                            }
                            tProtocol.readListEnd();
                            deviceDetails.setSupportedLocalesIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 9:
                        if (b == 15) {
                            TList readListBegin2 = tProtocol.readListBegin();
                            deviceDetails.supportedCountryCodes = new ArrayList(readListBegin2.size);
                            while (i < readListBegin2.size) {
                                deviceDetails.supportedCountryCodes.add(tProtocol.readString());
                                i++;
                            }
                            tProtocol.readListEnd();
                            deviceDetails.setSupportedCountryCodesIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 10:
                        if (b == 15) {
                            TList readListBegin3 = tProtocol.readListBegin();
                            deviceDetails.supportedWakeWords = new ArrayList(readListBegin3.size);
                            while (i < readListBegin3.size) {
                                deviceDetails.supportedWakeWords.add(tProtocol.readString());
                                i++;
                            }
                            tProtocol.readListEnd();
                            deviceDetails.setSupportedWakeWordsIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 11:
                        if (b == 11) {
                            deviceDetails.deviceType = tProtocol.readString();
                            deviceDetails.setDeviceTypeIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 12:
                        if (b == 8) {
                            deviceDetails.codeSource = LinkCodeSource.findByValue(tProtocol.readI32());
                            deviceDetails.setCodeSourceIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                }
                TProtocolUtil.skip(tProtocol, b);
                tProtocol.readFieldEnd();
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, DeviceDetails deviceDetails) throws TException {
            deviceDetails.validate();
            tProtocol.writeStructBegin(DeviceDetails.STRUCT_DESC);
            if (deviceDetails.SWVersion != null) {
                tProtocol.writeFieldBegin(DeviceDetails.SWVERSION_FIELD_DESC);
                tProtocol.writeString(deviceDetails.SWVersion);
                tProtocol.writeFieldEnd();
            }
            if (deviceDetails.MAC != null) {
                tProtocol.writeFieldBegin(DeviceDetails.MAC_FIELD_DESC);
                tProtocol.writeString(deviceDetails.MAC);
                tProtocol.writeFieldEnd();
            }
            if (deviceDetails.currentAP != null) {
                tProtocol.writeFieldBegin(DeviceDetails.CURRENT_AP_FIELD_DESC);
                deviceDetails.currentAP.write(tProtocol);
                tProtocol.writeFieldEnd();
            }
            if (deviceDetails.IPAddress != null) {
                tProtocol.writeFieldBegin(DeviceDetails.IPADDRESS_FIELD_DESC);
                tProtocol.writeString(deviceDetails.IPAddress);
                tProtocol.writeFieldEnd();
            }
            if (deviceDetails.deviceCertificate != null) {
                tProtocol.writeFieldBegin(DeviceDetails.DEVICE_CERTIFICATE_FIELD_DESC);
                deviceDetails.deviceCertificate.write(tProtocol);
                tProtocol.writeFieldEnd();
            }
            if (deviceDetails.deviceSerialNumber != null && deviceDetails.isSetDeviceSerialNumber()) {
                tProtocol.writeFieldBegin(DeviceDetails.DEVICE_SERIAL_NUMBER_FIELD_DESC);
                tProtocol.writeString(deviceDetails.deviceSerialNumber);
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldBegin(DeviceDetails.TETHERING_SUPPORTED_FIELD_DESC);
            tProtocol.writeBool(deviceDetails.tetheringSupported);
            tProtocol.writeFieldEnd();
            if (deviceDetails.supportedLocales != null && deviceDetails.isSetSupportedLocales()) {
                tProtocol.writeFieldBegin(DeviceDetails.SUPPORTED_LOCALES_FIELD_DESC);
                tProtocol.writeListBegin(new TList((byte) 11, deviceDetails.supportedLocales.size()));
                for (String str : deviceDetails.supportedLocales) {
                    tProtocol.writeString(str);
                }
                tProtocol.writeListEnd();
                tProtocol.writeFieldEnd();
            }
            if (deviceDetails.supportedCountryCodes != null && deviceDetails.isSetSupportedCountryCodes()) {
                tProtocol.writeFieldBegin(DeviceDetails.SUPPORTED_COUNTRY_CODES_FIELD_DESC);
                tProtocol.writeListBegin(new TList((byte) 11, deviceDetails.supportedCountryCodes.size()));
                for (String str2 : deviceDetails.supportedCountryCodes) {
                    tProtocol.writeString(str2);
                }
                tProtocol.writeListEnd();
                tProtocol.writeFieldEnd();
            }
            if (deviceDetails.supportedWakeWords != null && deviceDetails.isSetSupportedWakeWords()) {
                tProtocol.writeFieldBegin(DeviceDetails.SUPPORTED_WAKE_WORDS_FIELD_DESC);
                tProtocol.writeListBegin(new TList((byte) 11, deviceDetails.supportedWakeWords.size()));
                for (String str3 : deviceDetails.supportedWakeWords) {
                    tProtocol.writeString(str3);
                }
                tProtocol.writeListEnd();
                tProtocol.writeFieldEnd();
            }
            if (deviceDetails.deviceType != null && deviceDetails.isSetDeviceType()) {
                tProtocol.writeFieldBegin(DeviceDetails.DEVICE_TYPE_FIELD_DESC);
                tProtocol.writeString(deviceDetails.deviceType);
                tProtocol.writeFieldEnd();
            }
            if (deviceDetails.codeSource != null && deviceDetails.isSetCodeSource()) {
                tProtocol.writeFieldBegin(DeviceDetails.CODE_SOURCE_FIELD_DESC);
                tProtocol.writeI32(deviceDetails.codeSource.getValue());
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class DeviceDetailsStandardSchemeFactory implements SchemeFactory {
        private DeviceDetailsStandardSchemeFactory() {
        }

        /* synthetic */ DeviceDetailsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public DeviceDetailsStandardScheme mo12846getScheme() {
            return new DeviceDetailsStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class DeviceDetailsTupleScheme extends TupleScheme<DeviceDetails> {
        private DeviceDetailsTupleScheme() {
        }

        /* synthetic */ DeviceDetailsTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, DeviceDetails deviceDetails) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet readBitSet = tTupleProtocol.readBitSet(12);
            if (readBitSet.get(0)) {
                deviceDetails.SWVersion = tTupleProtocol.readString();
                deviceDetails.setSWVersionIsSet(true);
            }
            if (readBitSet.get(1)) {
                deviceDetails.MAC = tTupleProtocol.readString();
                deviceDetails.setMACIsSet(true);
            }
            if (readBitSet.get(2)) {
                deviceDetails.currentAP = new APDetail();
                deviceDetails.currentAP.read(tTupleProtocol);
                deviceDetails.setCurrentAPIsSet(true);
            }
            if (readBitSet.get(3)) {
                deviceDetails.IPAddress = tTupleProtocol.readString();
                deviceDetails.setIPAddressIsSet(true);
            }
            if (readBitSet.get(4)) {
                deviceDetails.deviceCertificate = new CERTIFICATE();
                deviceDetails.deviceCertificate.read(tTupleProtocol);
                deviceDetails.setDeviceCertificateIsSet(true);
            }
            if (readBitSet.get(5)) {
                deviceDetails.deviceSerialNumber = tTupleProtocol.readString();
                deviceDetails.setDeviceSerialNumberIsSet(true);
            }
            if (readBitSet.get(6)) {
                deviceDetails.tetheringSupported = tTupleProtocol.readBool();
                deviceDetails.setTetheringSupportedIsSet(true);
            }
            if (readBitSet.get(7)) {
                TList tList = new TList((byte) 11, tTupleProtocol.readI32());
                deviceDetails.supportedLocales = new ArrayList(tList.size);
                for (int i = 0; i < tList.size; i++) {
                    deviceDetails.supportedLocales.add(tTupleProtocol.readString());
                }
                deviceDetails.setSupportedLocalesIsSet(true);
            }
            if (readBitSet.get(8)) {
                TList tList2 = new TList((byte) 11, tTupleProtocol.readI32());
                deviceDetails.supportedCountryCodes = new ArrayList(tList2.size);
                for (int i2 = 0; i2 < tList2.size; i2++) {
                    deviceDetails.supportedCountryCodes.add(tTupleProtocol.readString());
                }
                deviceDetails.setSupportedCountryCodesIsSet(true);
            }
            if (readBitSet.get(9)) {
                TList tList3 = new TList((byte) 11, tTupleProtocol.readI32());
                deviceDetails.supportedWakeWords = new ArrayList(tList3.size);
                for (int i3 = 0; i3 < tList3.size; i3++) {
                    deviceDetails.supportedWakeWords.add(tTupleProtocol.readString());
                }
                deviceDetails.setSupportedWakeWordsIsSet(true);
            }
            if (readBitSet.get(10)) {
                deviceDetails.deviceType = tTupleProtocol.readString();
                deviceDetails.setDeviceTypeIsSet(true);
            }
            if (readBitSet.get(11)) {
                deviceDetails.codeSource = LinkCodeSource.findByValue(tTupleProtocol.readI32());
                deviceDetails.setCodeSourceIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, DeviceDetails deviceDetails) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (deviceDetails.isSetSWVersion()) {
                bitSet.set(0);
            }
            if (deviceDetails.isSetMAC()) {
                bitSet.set(1);
            }
            if (deviceDetails.isSetCurrentAP()) {
                bitSet.set(2);
            }
            if (deviceDetails.isSetIPAddress()) {
                bitSet.set(3);
            }
            if (deviceDetails.isSetDeviceCertificate()) {
                bitSet.set(4);
            }
            if (deviceDetails.isSetDeviceSerialNumber()) {
                bitSet.set(5);
            }
            if (deviceDetails.isSetTetheringSupported()) {
                bitSet.set(6);
            }
            if (deviceDetails.isSetSupportedLocales()) {
                bitSet.set(7);
            }
            if (deviceDetails.isSetSupportedCountryCodes()) {
                bitSet.set(8);
            }
            if (deviceDetails.isSetSupportedWakeWords()) {
                bitSet.set(9);
            }
            if (deviceDetails.isSetDeviceType()) {
                bitSet.set(10);
            }
            if (deviceDetails.isSetCodeSource()) {
                bitSet.set(11);
            }
            tTupleProtocol.writeBitSet(bitSet, 12);
            if (deviceDetails.isSetSWVersion()) {
                tTupleProtocol.writeString(deviceDetails.SWVersion);
            }
            if (deviceDetails.isSetMAC()) {
                tTupleProtocol.writeString(deviceDetails.MAC);
            }
            if (deviceDetails.isSetCurrentAP()) {
                deviceDetails.currentAP.write(tTupleProtocol);
            }
            if (deviceDetails.isSetIPAddress()) {
                tTupleProtocol.writeString(deviceDetails.IPAddress);
            }
            if (deviceDetails.isSetDeviceCertificate()) {
                deviceDetails.deviceCertificate.write(tTupleProtocol);
            }
            if (deviceDetails.isSetDeviceSerialNumber()) {
                tTupleProtocol.writeString(deviceDetails.deviceSerialNumber);
            }
            if (deviceDetails.isSetTetheringSupported()) {
                tTupleProtocol.writeBool(deviceDetails.tetheringSupported);
            }
            if (deviceDetails.isSetSupportedLocales()) {
                tTupleProtocol.writeI32(deviceDetails.supportedLocales.size());
                for (String str : deviceDetails.supportedLocales) {
                    tTupleProtocol.writeString(str);
                }
            }
            if (deviceDetails.isSetSupportedCountryCodes()) {
                tTupleProtocol.writeI32(deviceDetails.supportedCountryCodes.size());
                for (String str2 : deviceDetails.supportedCountryCodes) {
                    tTupleProtocol.writeString(str2);
                }
            }
            if (deviceDetails.isSetSupportedWakeWords()) {
                tTupleProtocol.writeI32(deviceDetails.supportedWakeWords.size());
                for (String str3 : deviceDetails.supportedWakeWords) {
                    tTupleProtocol.writeString(str3);
                }
            }
            if (deviceDetails.isSetDeviceType()) {
                tTupleProtocol.writeString(deviceDetails.deviceType);
            }
            if (deviceDetails.isSetCodeSource()) {
                tTupleProtocol.writeI32(deviceDetails.codeSource.getValue());
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class DeviceDetailsTupleSchemeFactory implements SchemeFactory {
        private DeviceDetailsTupleSchemeFactory() {
        }

        /* synthetic */ DeviceDetailsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public DeviceDetailsTupleScheme mo12846getScheme() {
            return new DeviceDetailsTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        SWVERSION(1, "SWVersion"),
        MAC(2, "MAC"),
        CURRENT_AP(3, "currentAP"),
        IPADDRESS(4, "IPAddress"),
        DEVICE_CERTIFICATE(5, "deviceCertificate"),
        DEVICE_SERIAL_NUMBER(6, "deviceSerialNumber"),
        TETHERING_SUPPORTED(7, "tetheringSupported"),
        SUPPORTED_LOCALES(8, "supportedLocales"),
        SUPPORTED_COUNTRY_CODES(9, "supportedCountryCodes"),
        SUPPORTED_WAKE_WORDS(10, "supportedWakeWords"),
        DEVICE_TYPE(11, "deviceType"),
        CODE_SOURCE(12, "codeSource");
        
        private static final Map<String, _Fields> byName = new HashMap();
        private final String _fieldName;
        private final short _thriftId;

        static {
            Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
            while (it2.hasNext()) {
                _Fields _fields = (_Fields) it2.next();
                byName.put(_fields.getFieldName(), _fields);
            }
        }

        _Fields(short s, String str) {
            this._thriftId = s;
            this._fieldName = str;
        }

        public static _Fields findByName(String str) {
            return byName.get(str);
        }

        public static _Fields findByThriftId(int i) {
            switch (i) {
                case 1:
                    return SWVERSION;
                case 2:
                    return MAC;
                case 3:
                    return CURRENT_AP;
                case 4:
                    return IPADDRESS;
                case 5:
                    return DEVICE_CERTIFICATE;
                case 6:
                    return DEVICE_SERIAL_NUMBER;
                case 7:
                    return TETHERING_SUPPORTED;
                case 8:
                    return SUPPORTED_LOCALES;
                case 9:
                    return SUPPORTED_COUNTRY_CODES;
                case 10:
                    return SUPPORTED_WAKE_WORDS;
                case 11:
                    return DEVICE_TYPE;
                case 12:
                    return CODE_SOURCE;
                default:
                    return null;
            }
        }

        public static _Fields findByThriftIdOrThrow(int i) {
            _Fields findByThriftId = findByThriftId(i);
            if (findByThriftId != null) {
                return findByThriftId;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
        }

        @Override // org.apache.thrift.orig.TFieldIdEnum
        public String getFieldName() {
            return this._fieldName;
        }

        @Override // org.apache.thrift.orig.TFieldIdEnum
        public short getThriftFieldId() {
            return this._thriftId;
        }
    }

    static {
        schemes.put(StandardScheme.class, new DeviceDetailsStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new DeviceDetailsTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.SWVERSION, (_Fields) new FieldMetaData("SWVersion", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.MAC, (_Fields) new FieldMetaData("MAC", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.CURRENT_AP, (_Fields) new FieldMetaData("currentAP", (byte) 3, new StructMetaData((byte) 12, APDetail.class)));
        enumMap.put((EnumMap) _Fields.IPADDRESS, (_Fields) new FieldMetaData("IPAddress", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.DEVICE_CERTIFICATE, (_Fields) new FieldMetaData("deviceCertificate", (byte) 3, new StructMetaData((byte) 12, CERTIFICATE.class)));
        enumMap.put((EnumMap) _Fields.DEVICE_SERIAL_NUMBER, (_Fields) new FieldMetaData("deviceSerialNumber", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.TETHERING_SUPPORTED, (_Fields) new FieldMetaData("tetheringSupported", (byte) 3, new FieldValueMetaData((byte) 2)));
        enumMap.put((EnumMap) _Fields.SUPPORTED_LOCALES, (_Fields) new FieldMetaData("supportedLocales", (byte) 2, new ListMetaData((byte) 15, new FieldValueMetaData((byte) 11))));
        enumMap.put((EnumMap) _Fields.SUPPORTED_COUNTRY_CODES, (_Fields) new FieldMetaData("supportedCountryCodes", (byte) 2, new ListMetaData((byte) 15, new FieldValueMetaData((byte) 11))));
        enumMap.put((EnumMap) _Fields.SUPPORTED_WAKE_WORDS, (_Fields) new FieldMetaData("supportedWakeWords", (byte) 2, new ListMetaData((byte) 15, new FieldValueMetaData((byte) 11))));
        enumMap.put((EnumMap) _Fields.DEVICE_TYPE, (_Fields) new FieldMetaData("deviceType", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.CODE_SOURCE, (_Fields) new FieldMetaData("codeSource", (byte) 2, new EnumMetaData((byte) 16, LinkCodeSource.class)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(DeviceDetails.class, metaDataMap);
    }

    public DeviceDetails() {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new _Fields[]{_Fields.DEVICE_SERIAL_NUMBER, _Fields.SUPPORTED_LOCALES, _Fields.SUPPORTED_COUNTRY_CODES, _Fields.SUPPORTED_WAKE_WORDS, _Fields.DEVICE_TYPE, _Fields.CODE_SOURCE};
    }

    public DeviceDetails(DeviceDetails deviceDetails) {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new _Fields[]{_Fields.DEVICE_SERIAL_NUMBER, _Fields.SUPPORTED_LOCALES, _Fields.SUPPORTED_COUNTRY_CODES, _Fields.SUPPORTED_WAKE_WORDS, _Fields.DEVICE_TYPE, _Fields.CODE_SOURCE};
        this.__isset_bitfield = deviceDetails.__isset_bitfield;
        if (deviceDetails.isSetSWVersion()) {
            this.SWVersion = deviceDetails.SWVersion;
        }
        if (deviceDetails.isSetMAC()) {
            this.MAC = deviceDetails.MAC;
        }
        if (deviceDetails.isSetCurrentAP()) {
            this.currentAP = new APDetail(deviceDetails.currentAP);
        }
        if (deviceDetails.isSetIPAddress()) {
            this.IPAddress = deviceDetails.IPAddress;
        }
        if (deviceDetails.isSetDeviceCertificate()) {
            this.deviceCertificate = new CERTIFICATE(deviceDetails.deviceCertificate);
        }
        if (deviceDetails.isSetDeviceSerialNumber()) {
            this.deviceSerialNumber = deviceDetails.deviceSerialNumber;
        }
        this.tetheringSupported = deviceDetails.tetheringSupported;
        if (deviceDetails.isSetSupportedLocales()) {
            ArrayList arrayList = new ArrayList();
            for (String str : deviceDetails.supportedLocales) {
                arrayList.add(str);
            }
            this.supportedLocales = arrayList;
        }
        if (deviceDetails.isSetSupportedCountryCodes()) {
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : deviceDetails.supportedCountryCodes) {
                arrayList2.add(str2);
            }
            this.supportedCountryCodes = arrayList2;
        }
        if (deviceDetails.isSetSupportedWakeWords()) {
            ArrayList arrayList3 = new ArrayList();
            for (String str3 : deviceDetails.supportedWakeWords) {
                arrayList3.add(str3);
            }
            this.supportedWakeWords = arrayList3;
        }
        if (deviceDetails.isSetDeviceType()) {
            this.deviceType = deviceDetails.deviceType;
        }
        if (deviceDetails.isSetCodeSource()) {
            this.codeSource = deviceDetails.codeSource;
        }
    }

    public DeviceDetails(String str, String str2, APDetail aPDetail, String str3, CERTIFICATE certificate, boolean z) {
        this();
        this.SWVersion = str;
        this.MAC = str2;
        this.currentAP = aPDetail;
        this.IPAddress = str3;
        this.deviceCertificate = certificate;
        this.tetheringSupported = z;
        setTetheringSupportedIsSet(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.__isset_bitfield = (byte) 0;
            read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
        } catch (TException e) {
            throw new IOException(e);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
        } catch (TException e) {
            throw new IOException(e);
        }
    }

    public void addToSupportedCountryCodes(String str) {
        if (this.supportedCountryCodes == null) {
            this.supportedCountryCodes = new ArrayList();
        }
        this.supportedCountryCodes.add(str);
    }

    public void addToSupportedLocales(String str) {
        if (this.supportedLocales == null) {
            this.supportedLocales = new ArrayList();
        }
        this.supportedLocales.add(str);
    }

    public void addToSupportedWakeWords(String str) {
        if (this.supportedWakeWords == null) {
            this.supportedWakeWords = new ArrayList();
        }
        this.supportedWakeWords.add(str);
    }

    @Override // org.apache.thrift.orig.TBase
    public void clear() {
        this.SWVersion = null;
        this.MAC = null;
        this.currentAP = null;
        this.IPAddress = null;
        this.deviceCertificate = null;
        this.deviceSerialNumber = null;
        setTetheringSupportedIsSet(false);
        this.tetheringSupported = false;
        this.supportedLocales = null;
        this.supportedCountryCodes = null;
        this.supportedWakeWords = null;
        this.deviceType = null;
        this.codeSource = null;
    }

    @Override // java.lang.Comparable
    public int compareTo(DeviceDetails deviceDetails) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        int compareTo8;
        int compareTo9;
        int compareTo10;
        int compareTo11;
        int compareTo12;
        if (!DeviceDetails.class.equals(deviceDetails.getClass())) {
            return DeviceDetails.class.getName().compareTo(DeviceDetails.class.getName());
        }
        int compareTo13 = Boolean.valueOf(isSetSWVersion()).compareTo(Boolean.valueOf(deviceDetails.isSetSWVersion()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetSWVersion() && (compareTo12 = TBaseHelper.compareTo(this.SWVersion, deviceDetails.SWVersion)) != 0) {
            return compareTo12;
        }
        int compareTo14 = Boolean.valueOf(isSetMAC()).compareTo(Boolean.valueOf(deviceDetails.isSetMAC()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetMAC() && (compareTo11 = TBaseHelper.compareTo(this.MAC, deviceDetails.MAC)) != 0) {
            return compareTo11;
        }
        int compareTo15 = Boolean.valueOf(isSetCurrentAP()).compareTo(Boolean.valueOf(deviceDetails.isSetCurrentAP()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetCurrentAP() && (compareTo10 = TBaseHelper.compareTo((Comparable) this.currentAP, (Comparable) deviceDetails.currentAP)) != 0) {
            return compareTo10;
        }
        int compareTo16 = Boolean.valueOf(isSetIPAddress()).compareTo(Boolean.valueOf(deviceDetails.isSetIPAddress()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (isSetIPAddress() && (compareTo9 = TBaseHelper.compareTo(this.IPAddress, deviceDetails.IPAddress)) != 0) {
            return compareTo9;
        }
        int compareTo17 = Boolean.valueOf(isSetDeviceCertificate()).compareTo(Boolean.valueOf(deviceDetails.isSetDeviceCertificate()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (isSetDeviceCertificate() && (compareTo8 = TBaseHelper.compareTo((Comparable) this.deviceCertificate, (Comparable) deviceDetails.deviceCertificate)) != 0) {
            return compareTo8;
        }
        int compareTo18 = Boolean.valueOf(isSetDeviceSerialNumber()).compareTo(Boolean.valueOf(deviceDetails.isSetDeviceSerialNumber()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (isSetDeviceSerialNumber() && (compareTo7 = TBaseHelper.compareTo(this.deviceSerialNumber, deviceDetails.deviceSerialNumber)) != 0) {
            return compareTo7;
        }
        int compareTo19 = Boolean.valueOf(isSetTetheringSupported()).compareTo(Boolean.valueOf(deviceDetails.isSetTetheringSupported()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (isSetTetheringSupported() && (compareTo6 = TBaseHelper.compareTo(this.tetheringSupported, deviceDetails.tetheringSupported)) != 0) {
            return compareTo6;
        }
        int compareTo20 = Boolean.valueOf(isSetSupportedLocales()).compareTo(Boolean.valueOf(deviceDetails.isSetSupportedLocales()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (isSetSupportedLocales() && (compareTo5 = TBaseHelper.compareTo((List) this.supportedLocales, (List) deviceDetails.supportedLocales)) != 0) {
            return compareTo5;
        }
        int compareTo21 = Boolean.valueOf(isSetSupportedCountryCodes()).compareTo(Boolean.valueOf(deviceDetails.isSetSupportedCountryCodes()));
        if (compareTo21 != 0) {
            return compareTo21;
        }
        if (isSetSupportedCountryCodes() && (compareTo4 = TBaseHelper.compareTo((List) this.supportedCountryCodes, (List) deviceDetails.supportedCountryCodes)) != 0) {
            return compareTo4;
        }
        int compareTo22 = Boolean.valueOf(isSetSupportedWakeWords()).compareTo(Boolean.valueOf(deviceDetails.isSetSupportedWakeWords()));
        if (compareTo22 != 0) {
            return compareTo22;
        }
        if (isSetSupportedWakeWords() && (compareTo3 = TBaseHelper.compareTo((List) this.supportedWakeWords, (List) deviceDetails.supportedWakeWords)) != 0) {
            return compareTo3;
        }
        int compareTo23 = Boolean.valueOf(isSetDeviceType()).compareTo(Boolean.valueOf(deviceDetails.isSetDeviceType()));
        if (compareTo23 != 0) {
            return compareTo23;
        }
        if (isSetDeviceType() && (compareTo2 = TBaseHelper.compareTo(this.deviceType, deviceDetails.deviceType)) != 0) {
            return compareTo2;
        }
        int compareTo24 = Boolean.valueOf(isSetCodeSource()).compareTo(Boolean.valueOf(deviceDetails.isSetCodeSource()));
        if (compareTo24 != 0) {
            return compareTo24;
        }
        if (isSetCodeSource() && (compareTo = TBaseHelper.compareTo((Comparable) this.codeSource, (Comparable) deviceDetails.codeSource)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<DeviceDetails, _Fields> deepCopy2() {
        return new DeviceDetails(this);
    }

    public boolean equals(DeviceDetails deviceDetails) {
        if (deviceDetails == null) {
            return false;
        }
        boolean isSetSWVersion = isSetSWVersion();
        boolean isSetSWVersion2 = deviceDetails.isSetSWVersion();
        if ((isSetSWVersion || isSetSWVersion2) && (!isSetSWVersion || !isSetSWVersion2 || !this.SWVersion.equals(deviceDetails.SWVersion))) {
            return false;
        }
        boolean isSetMAC = isSetMAC();
        boolean isSetMAC2 = deviceDetails.isSetMAC();
        if ((isSetMAC || isSetMAC2) && (!isSetMAC || !isSetMAC2 || !this.MAC.equals(deviceDetails.MAC))) {
            return false;
        }
        boolean isSetCurrentAP = isSetCurrentAP();
        boolean isSetCurrentAP2 = deviceDetails.isSetCurrentAP();
        if ((isSetCurrentAP || isSetCurrentAP2) && (!isSetCurrentAP || !isSetCurrentAP2 || !this.currentAP.equals(deviceDetails.currentAP))) {
            return false;
        }
        boolean isSetIPAddress = isSetIPAddress();
        boolean isSetIPAddress2 = deviceDetails.isSetIPAddress();
        if ((isSetIPAddress || isSetIPAddress2) && (!isSetIPAddress || !isSetIPAddress2 || !this.IPAddress.equals(deviceDetails.IPAddress))) {
            return false;
        }
        boolean isSetDeviceCertificate = isSetDeviceCertificate();
        boolean isSetDeviceCertificate2 = deviceDetails.isSetDeviceCertificate();
        if ((isSetDeviceCertificate || isSetDeviceCertificate2) && (!isSetDeviceCertificate || !isSetDeviceCertificate2 || !this.deviceCertificate.equals(deviceDetails.deviceCertificate))) {
            return false;
        }
        boolean isSetDeviceSerialNumber = isSetDeviceSerialNumber();
        boolean isSetDeviceSerialNumber2 = deviceDetails.isSetDeviceSerialNumber();
        if (((isSetDeviceSerialNumber || isSetDeviceSerialNumber2) && (!isSetDeviceSerialNumber || !isSetDeviceSerialNumber2 || !this.deviceSerialNumber.equals(deviceDetails.deviceSerialNumber))) || this.tetheringSupported != deviceDetails.tetheringSupported) {
            return false;
        }
        boolean isSetSupportedLocales = isSetSupportedLocales();
        boolean isSetSupportedLocales2 = deviceDetails.isSetSupportedLocales();
        if ((isSetSupportedLocales || isSetSupportedLocales2) && (!isSetSupportedLocales || !isSetSupportedLocales2 || !this.supportedLocales.equals(deviceDetails.supportedLocales))) {
            return false;
        }
        boolean isSetSupportedCountryCodes = isSetSupportedCountryCodes();
        boolean isSetSupportedCountryCodes2 = deviceDetails.isSetSupportedCountryCodes();
        if ((isSetSupportedCountryCodes || isSetSupportedCountryCodes2) && (!isSetSupportedCountryCodes || !isSetSupportedCountryCodes2 || !this.supportedCountryCodes.equals(deviceDetails.supportedCountryCodes))) {
            return false;
        }
        boolean isSetSupportedWakeWords = isSetSupportedWakeWords();
        boolean isSetSupportedWakeWords2 = deviceDetails.isSetSupportedWakeWords();
        if ((isSetSupportedWakeWords || isSetSupportedWakeWords2) && (!isSetSupportedWakeWords || !isSetSupportedWakeWords2 || !this.supportedWakeWords.equals(deviceDetails.supportedWakeWords))) {
            return false;
        }
        boolean isSetDeviceType = isSetDeviceType();
        boolean isSetDeviceType2 = deviceDetails.isSetDeviceType();
        if ((isSetDeviceType || isSetDeviceType2) && (!isSetDeviceType || !isSetDeviceType2 || !this.deviceType.equals(deviceDetails.deviceType))) {
            return false;
        }
        boolean isSetCodeSource = isSetCodeSource();
        boolean isSetCodeSource2 = deviceDetails.isSetCodeSource();
        if (!isSetCodeSource && !isSetCodeSource2) {
            return true;
        }
        return isSetCodeSource && isSetCodeSource2 && this.codeSource.equals(deviceDetails.codeSource);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof DeviceDetails)) {
            return equals((DeviceDetails) obj);
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.thrift.orig.TBase
    /* renamed from: fieldForId */
    public _Fields mo3968fieldForId(int i) {
        return _Fields.findByThriftId(i);
    }

    public LinkCodeSource getCodeSource() {
        return this.codeSource;
    }

    public APDetail getCurrentAP() {
        return this.currentAP;
    }

    public CERTIFICATE getDeviceCertificate() {
        return this.deviceCertificate;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // org.apache.thrift.orig.TBase
    public Object getFieldValue(_Fields _fields) {
        switch (_fields.ordinal()) {
            case 0:
                return getSWVersion();
            case 1:
                return getMAC();
            case 2:
                return getCurrentAP();
            case 3:
                return getIPAddress();
            case 4:
                return getDeviceCertificate();
            case 5:
                return getDeviceSerialNumber();
            case 6:
                return Boolean.valueOf(isTetheringSupported());
            case 7:
                return getSupportedLocales();
            case 8:
                return getSupportedCountryCodes();
            case 9:
                return getSupportedWakeWords();
            case 10:
                return getDeviceType();
            case 11:
                return getCodeSource();
            default:
                throw new IllegalStateException();
        }
    }

    public String getIPAddress() {
        return this.IPAddress;
    }

    public String getMAC() {
        return this.MAC;
    }

    public String getSWVersion() {
        return this.SWVersion;
    }

    public List<String> getSupportedCountryCodes() {
        return this.supportedCountryCodes;
    }

    public Iterator<String> getSupportedCountryCodesIterator() {
        List<String> list = this.supportedCountryCodes;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public int getSupportedCountryCodesSize() {
        List<String> list = this.supportedCountryCodes;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<String> getSupportedLocales() {
        return this.supportedLocales;
    }

    public Iterator<String> getSupportedLocalesIterator() {
        List<String> list = this.supportedLocales;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public int getSupportedLocalesSize() {
        List<String> list = this.supportedLocales;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<String> getSupportedWakeWords() {
        return this.supportedWakeWords;
    }

    public Iterator<String> getSupportedWakeWordsIterator() {
        List<String> list = this.supportedWakeWords;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public int getSupportedWakeWordsSize() {
        List<String> list = this.supportedWakeWords;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int hashCode() {
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(_Fields _fields) {
        if (_fields != null) {
            switch (_fields.ordinal()) {
                case 0:
                    return isSetSWVersion();
                case 1:
                    return isSetMAC();
                case 2:
                    return isSetCurrentAP();
                case 3:
                    return isSetIPAddress();
                case 4:
                    return isSetDeviceCertificate();
                case 5:
                    return isSetDeviceSerialNumber();
                case 6:
                    return isSetTetheringSupported();
                case 7:
                    return isSetSupportedLocales();
                case 8:
                    return isSetSupportedCountryCodes();
                case 9:
                    return isSetSupportedWakeWords();
                case 10:
                    return isSetDeviceType();
                case 11:
                    return isSetCodeSource();
                default:
                    throw new IllegalStateException();
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetCodeSource() {
        return this.codeSource != null;
    }

    public boolean isSetCurrentAP() {
        return this.currentAP != null;
    }

    public boolean isSetDeviceCertificate() {
        return this.deviceCertificate != null;
    }

    public boolean isSetDeviceSerialNumber() {
        return this.deviceSerialNumber != null;
    }

    public boolean isSetDeviceType() {
        return this.deviceType != null;
    }

    public boolean isSetIPAddress() {
        return this.IPAddress != null;
    }

    public boolean isSetMAC() {
        return this.MAC != null;
    }

    public boolean isSetSWVersion() {
        return this.SWVersion != null;
    }

    public boolean isSetSupportedCountryCodes() {
        return this.supportedCountryCodes != null;
    }

    public boolean isSetSupportedLocales() {
        return this.supportedLocales != null;
    }

    public boolean isSetSupportedWakeWords() {
        return this.supportedWakeWords != null;
    }

    public boolean isSetTetheringSupported() {
        return EncodingUtils.testBit(this.__isset_bitfield, 0);
    }

    public boolean isTetheringSupported() {
        return this.tetheringSupported;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    public DeviceDetails setCodeSource(LinkCodeSource linkCodeSource) {
        this.codeSource = linkCodeSource;
        return this;
    }

    public void setCodeSourceIsSet(boolean z) {
        if (!z) {
            this.codeSource = null;
        }
    }

    public DeviceDetails setCurrentAP(APDetail aPDetail) {
        this.currentAP = aPDetail;
        return this;
    }

    public void setCurrentAPIsSet(boolean z) {
        if (!z) {
            this.currentAP = null;
        }
    }

    public DeviceDetails setDeviceCertificate(CERTIFICATE certificate) {
        this.deviceCertificate = certificate;
        return this;
    }

    public void setDeviceCertificateIsSet(boolean z) {
        if (!z) {
            this.deviceCertificate = null;
        }
    }

    public DeviceDetails setDeviceSerialNumber(String str) {
        this.deviceSerialNumber = str;
        return this;
    }

    public void setDeviceSerialNumberIsSet(boolean z) {
        if (!z) {
            this.deviceSerialNumber = null;
        }
    }

    public DeviceDetails setDeviceType(String str) {
        this.deviceType = str;
        return this;
    }

    public void setDeviceTypeIsSet(boolean z) {
        if (!z) {
            this.deviceType = null;
        }
    }

    @Override // org.apache.thrift.orig.TBase
    public void setFieldValue(_Fields _fields, Object obj) {
        switch (_fields.ordinal()) {
            case 0:
                if (obj == null) {
                    unsetSWVersion();
                    return;
                } else {
                    setSWVersion((String) obj);
                    return;
                }
            case 1:
                if (obj == null) {
                    unsetMAC();
                    return;
                } else {
                    setMAC((String) obj);
                    return;
                }
            case 2:
                if (obj == null) {
                    unsetCurrentAP();
                    return;
                } else {
                    setCurrentAP((APDetail) obj);
                    return;
                }
            case 3:
                if (obj == null) {
                    unsetIPAddress();
                    return;
                } else {
                    setIPAddress((String) obj);
                    return;
                }
            case 4:
                if (obj == null) {
                    unsetDeviceCertificate();
                    return;
                } else {
                    setDeviceCertificate((CERTIFICATE) obj);
                    return;
                }
            case 5:
                if (obj == null) {
                    unsetDeviceSerialNumber();
                    return;
                } else {
                    setDeviceSerialNumber((String) obj);
                    return;
                }
            case 6:
                if (obj == null) {
                    unsetTetheringSupported();
                    return;
                } else {
                    setTetheringSupported(((Boolean) obj).booleanValue());
                    return;
                }
            case 7:
                if (obj == null) {
                    unsetSupportedLocales();
                    return;
                } else {
                    setSupportedLocales((List) obj);
                    return;
                }
            case 8:
                if (obj == null) {
                    unsetSupportedCountryCodes();
                    return;
                } else {
                    setSupportedCountryCodes((List) obj);
                    return;
                }
            case 9:
                if (obj == null) {
                    unsetSupportedWakeWords();
                    return;
                } else {
                    setSupportedWakeWords((List) obj);
                    return;
                }
            case 10:
                if (obj == null) {
                    unsetDeviceType();
                    return;
                } else {
                    setDeviceType((String) obj);
                    return;
                }
            case 11:
                if (obj == null) {
                    unsetCodeSource();
                    return;
                } else {
                    setCodeSource((LinkCodeSource) obj);
                    return;
                }
            default:
                return;
        }
    }

    public DeviceDetails setIPAddress(String str) {
        this.IPAddress = str;
        return this;
    }

    public void setIPAddressIsSet(boolean z) {
        if (!z) {
            this.IPAddress = null;
        }
    }

    public DeviceDetails setMAC(String str) {
        this.MAC = str;
        return this;
    }

    public void setMACIsSet(boolean z) {
        if (!z) {
            this.MAC = null;
        }
    }

    public DeviceDetails setSWVersion(String str) {
        this.SWVersion = str;
        return this;
    }

    public void setSWVersionIsSet(boolean z) {
        if (!z) {
            this.SWVersion = null;
        }
    }

    public DeviceDetails setSupportedCountryCodes(List<String> list) {
        this.supportedCountryCodes = list;
        return this;
    }

    public void setSupportedCountryCodesIsSet(boolean z) {
        if (!z) {
            this.supportedCountryCodes = null;
        }
    }

    public DeviceDetails setSupportedLocales(List<String> list) {
        this.supportedLocales = list;
        return this;
    }

    public void setSupportedLocalesIsSet(boolean z) {
        if (!z) {
            this.supportedLocales = null;
        }
    }

    public DeviceDetails setSupportedWakeWords(List<String> list) {
        this.supportedWakeWords = list;
        return this;
    }

    public void setSupportedWakeWordsIsSet(boolean z) {
        if (!z) {
            this.supportedWakeWords = null;
        }
    }

    public DeviceDetails setTetheringSupported(boolean z) {
        this.tetheringSupported = z;
        setTetheringSupportedIsSet(true);
        return this;
    }

    public void setTetheringSupportedIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, z);
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("DeviceDetails(", "SWVersion:");
        String str = this.SWVersion;
        if (str == null) {
            outline112.append("null");
        } else {
            outline112.append(str);
        }
        outline112.append(", ");
        outline112.append("MAC:");
        String str2 = this.MAC;
        if (str2 == null) {
            outline112.append("null");
        } else {
            outline112.append(str2);
        }
        outline112.append(", ");
        outline112.append("currentAP:");
        APDetail aPDetail = this.currentAP;
        if (aPDetail == null) {
            outline112.append("null");
        } else {
            outline112.append(aPDetail);
        }
        outline112.append(", ");
        outline112.append("IPAddress:");
        String str3 = this.IPAddress;
        if (str3 == null) {
            outline112.append("null");
        } else {
            outline112.append(str3);
        }
        outline112.append(", ");
        outline112.append("deviceCertificate:");
        CERTIFICATE certificate = this.deviceCertificate;
        if (certificate == null) {
            outline112.append("null");
        } else {
            outline112.append(certificate);
        }
        if (isSetDeviceSerialNumber()) {
            outline112.append(", ");
            outline112.append("deviceSerialNumber:");
            String str4 = this.deviceSerialNumber;
            if (str4 == null) {
                outline112.append("null");
            } else {
                outline112.append(str4);
            }
        }
        outline112.append(", ");
        outline112.append("tetheringSupported:");
        outline112.append(this.tetheringSupported);
        if (isSetSupportedLocales()) {
            outline112.append(", ");
            outline112.append("supportedLocales:");
            List<String> list = this.supportedLocales;
            if (list == null) {
                outline112.append("null");
            } else {
                outline112.append(list);
            }
        }
        if (isSetSupportedCountryCodes()) {
            outline112.append(", ");
            outline112.append("supportedCountryCodes:");
            List<String> list2 = this.supportedCountryCodes;
            if (list2 == null) {
                outline112.append("null");
            } else {
                outline112.append(list2);
            }
        }
        if (isSetSupportedWakeWords()) {
            outline112.append(", ");
            outline112.append("supportedWakeWords:");
            List<String> list3 = this.supportedWakeWords;
            if (list3 == null) {
                outline112.append("null");
            } else {
                outline112.append(list3);
            }
        }
        if (isSetDeviceType()) {
            outline112.append(", ");
            outline112.append("deviceType:");
            String str5 = this.deviceType;
            if (str5 == null) {
                outline112.append("null");
            } else {
                outline112.append(str5);
            }
        }
        if (isSetCodeSource()) {
            outline112.append(", ");
            outline112.append("codeSource:");
            LinkCodeSource linkCodeSource = this.codeSource;
            if (linkCodeSource == null) {
                outline112.append("null");
            } else {
                outline112.append(linkCodeSource);
            }
        }
        outline112.append(")");
        return outline112.toString();
    }

    public void unsetCodeSource() {
        this.codeSource = null;
    }

    public void unsetCurrentAP() {
        this.currentAP = null;
    }

    public void unsetDeviceCertificate() {
        this.deviceCertificate = null;
    }

    public void unsetDeviceSerialNumber() {
        this.deviceSerialNumber = null;
    }

    public void unsetDeviceType() {
        this.deviceType = null;
    }

    public void unsetIPAddress() {
        this.IPAddress = null;
    }

    public void unsetMAC() {
        this.MAC = null;
    }

    public void unsetSWVersion() {
        this.SWVersion = null;
    }

    public void unsetSupportedCountryCodes() {
        this.supportedCountryCodes = null;
    }

    public void unsetSupportedLocales() {
        this.supportedLocales = null;
    }

    public void unsetSupportedWakeWords() {
        this.supportedWakeWords = null;
    }

    public void unsetTetheringSupported() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
    }

    public void validate() throws TException {
        APDetail aPDetail = this.currentAP;
        if (aPDetail != null) {
            aPDetail.validate();
        }
        CERTIFICATE certificate = this.deviceCertificate;
        if (certificate != null) {
            certificate.validate();
        }
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
