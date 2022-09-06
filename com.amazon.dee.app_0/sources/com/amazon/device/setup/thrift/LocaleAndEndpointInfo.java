package com.amazon.device.setup.thrift;

import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.orig.EncodingUtils;
import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.TBaseHelper;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.TFieldIdEnum;
import org.apache.thrift.orig.meta_data.FieldMetaData;
import org.apache.thrift.orig.meta_data.FieldValueMetaData;
import org.apache.thrift.orig.protocol.TCompactProtocol;
import org.apache.thrift.orig.protocol.TField;
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
public class LocaleAndEndpointInfo implements TBase<LocaleAndEndpointInfo, _Fields>, Serializable, Cloneable {
    private static final int __ALEXAPORT_ISSET_ID = 0;
    private static final int __COMPANIONAPPPORT_ISSET_ID = 1;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public String FIRSEndpoint;
    private byte __isset_bitfield;
    public String alexaEndpoint;
    public int alexaPort;
    public String captivePortalEndpoint;
    public String companionAppEndpoint;
    public int companionAppPort;
    public String countryOfResidence;
    public String locale;
    private _Fields[] optionals;
    public String pandaEndpoint;
    public String todoEndpoint;
    public String wakeWord;
    private static final TStruct STRUCT_DESC = new TStruct("LocaleAndEndpointInfo");
    private static final TField LOCALE_FIELD_DESC = new TField("locale", (byte) 11, 1);
    private static final TField COUNTRY_OF_RESIDENCE_FIELD_DESC = new TField(MetricsConfiguration.COUNTRY_OF_RESIDENCE, (byte) 11, 2);
    private static final TField WAKE_WORD_FIELD_DESC = new TField(AlexaMetricsConstants.EventConstants.WAKE_WORD, (byte) 11, 3);
    private static final TField ALEXA_ENDPOINT_FIELD_DESC = new TField("alexaEndpoint", (byte) 11, 4);
    private static final TField ALEXA_PORT_FIELD_DESC = new TField("alexaPort", (byte) 8, 5);
    private static final TField COMPANION_APP_ENDPOINT_FIELD_DESC = new TField("companionAppEndpoint", (byte) 11, 6);
    private static final TField COMPANION_APP_PORT_FIELD_DESC = new TField("companionAppPort", (byte) 8, 7);
    private static final TField PANDA_ENDPOINT_FIELD_DESC = new TField("pandaEndpoint", (byte) 11, 8);
    private static final TField TODO_ENDPOINT_FIELD_DESC = new TField("todoEndpoint", (byte) 11, 9);
    private static final TField FIRSENDPOINT_FIELD_DESC = new TField("FIRSEndpoint", (byte) 11, 10);
    private static final TField CAPTIVE_PORTAL_ENDPOINT_FIELD_DESC = new TField("captivePortalEndpoint", (byte) 11, 11);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.LocaleAndEndpointInfo$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.LOCALE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.COUNTRY_OF_RESIDENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.WAKE_WORD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.ALEXA_ENDPOINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.ALEXA_PORT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.COMPANION_APP_ENDPOINT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.COMPANION_APP_PORT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.PANDA_ENDPOINT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.TODO_ENDPOINT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.FIRSENDPOINT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$LocaleAndEndpointInfo$_Fields[_Fields.CAPTIVE_PORTAL_ENDPOINT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class LocaleAndEndpointInfoStandardScheme extends StandardScheme<LocaleAndEndpointInfo> {
        private LocaleAndEndpointInfoStandardScheme() {
        }

        /* synthetic */ LocaleAndEndpointInfoStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, LocaleAndEndpointInfo localeAndEndpointInfo) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    localeAndEndpointInfo.validate();
                    return;
                }
                switch (readFieldBegin.id) {
                    case 1:
                        if (b == 11) {
                            localeAndEndpointInfo.locale = tProtocol.readString();
                            localeAndEndpointInfo.setLocaleIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 2:
                        if (b == 11) {
                            localeAndEndpointInfo.countryOfResidence = tProtocol.readString();
                            localeAndEndpointInfo.setCountryOfResidenceIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 3:
                        if (b == 11) {
                            localeAndEndpointInfo.wakeWord = tProtocol.readString();
                            localeAndEndpointInfo.setWakeWordIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 4:
                        if (b == 11) {
                            localeAndEndpointInfo.alexaEndpoint = tProtocol.readString();
                            localeAndEndpointInfo.setAlexaEndpointIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 5:
                        if (b == 8) {
                            localeAndEndpointInfo.alexaPort = tProtocol.readI32();
                            localeAndEndpointInfo.setAlexaPortIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 6:
                        if (b == 11) {
                            localeAndEndpointInfo.companionAppEndpoint = tProtocol.readString();
                            localeAndEndpointInfo.setCompanionAppEndpointIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 7:
                        if (b == 8) {
                            localeAndEndpointInfo.companionAppPort = tProtocol.readI32();
                            localeAndEndpointInfo.setCompanionAppPortIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 8:
                        if (b == 11) {
                            localeAndEndpointInfo.pandaEndpoint = tProtocol.readString();
                            localeAndEndpointInfo.setPandaEndpointIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 9:
                        if (b == 11) {
                            localeAndEndpointInfo.todoEndpoint = tProtocol.readString();
                            localeAndEndpointInfo.setTodoEndpointIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 10:
                        if (b == 11) {
                            localeAndEndpointInfo.FIRSEndpoint = tProtocol.readString();
                            localeAndEndpointInfo.setFIRSEndpointIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 11:
                        if (b == 11) {
                            localeAndEndpointInfo.captivePortalEndpoint = tProtocol.readString();
                            localeAndEndpointInfo.setCaptivePortalEndpointIsSet(true);
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
        public void write(TProtocol tProtocol, LocaleAndEndpointInfo localeAndEndpointInfo) throws TException {
            localeAndEndpointInfo.validate();
            tProtocol.writeStructBegin(LocaleAndEndpointInfo.STRUCT_DESC);
            if (localeAndEndpointInfo.locale != null && localeAndEndpointInfo.isSetLocale()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.LOCALE_FIELD_DESC);
                tProtocol.writeString(localeAndEndpointInfo.locale);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.countryOfResidence != null && localeAndEndpointInfo.isSetCountryOfResidence()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.COUNTRY_OF_RESIDENCE_FIELD_DESC);
                tProtocol.writeString(localeAndEndpointInfo.countryOfResidence);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.wakeWord != null && localeAndEndpointInfo.isSetWakeWord()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.WAKE_WORD_FIELD_DESC);
                tProtocol.writeString(localeAndEndpointInfo.wakeWord);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.alexaEndpoint != null && localeAndEndpointInfo.isSetAlexaEndpoint()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.ALEXA_ENDPOINT_FIELD_DESC);
                tProtocol.writeString(localeAndEndpointInfo.alexaEndpoint);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.isSetAlexaPort()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.ALEXA_PORT_FIELD_DESC);
                tProtocol.writeI32(localeAndEndpointInfo.alexaPort);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.companionAppEndpoint != null && localeAndEndpointInfo.isSetCompanionAppEndpoint()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.COMPANION_APP_ENDPOINT_FIELD_DESC);
                tProtocol.writeString(localeAndEndpointInfo.companionAppEndpoint);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.isSetCompanionAppPort()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.COMPANION_APP_PORT_FIELD_DESC);
                tProtocol.writeI32(localeAndEndpointInfo.companionAppPort);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.pandaEndpoint != null && localeAndEndpointInfo.isSetPandaEndpoint()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.PANDA_ENDPOINT_FIELD_DESC);
                tProtocol.writeString(localeAndEndpointInfo.pandaEndpoint);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.todoEndpoint != null && localeAndEndpointInfo.isSetTodoEndpoint()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.TODO_ENDPOINT_FIELD_DESC);
                tProtocol.writeString(localeAndEndpointInfo.todoEndpoint);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.FIRSEndpoint != null && localeAndEndpointInfo.isSetFIRSEndpoint()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.FIRSENDPOINT_FIELD_DESC);
                tProtocol.writeString(localeAndEndpointInfo.FIRSEndpoint);
                tProtocol.writeFieldEnd();
            }
            if (localeAndEndpointInfo.captivePortalEndpoint != null && localeAndEndpointInfo.isSetCaptivePortalEndpoint()) {
                tProtocol.writeFieldBegin(LocaleAndEndpointInfo.CAPTIVE_PORTAL_ENDPOINT_FIELD_DESC);
                tProtocol.writeString(localeAndEndpointInfo.captivePortalEndpoint);
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class LocaleAndEndpointInfoStandardSchemeFactory implements SchemeFactory {
        private LocaleAndEndpointInfoStandardSchemeFactory() {
        }

        /* synthetic */ LocaleAndEndpointInfoStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public LocaleAndEndpointInfoStandardScheme mo12846getScheme() {
            return new LocaleAndEndpointInfoStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class LocaleAndEndpointInfoTupleScheme extends TupleScheme<LocaleAndEndpointInfo> {
        private LocaleAndEndpointInfoTupleScheme() {
        }

        /* synthetic */ LocaleAndEndpointInfoTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, LocaleAndEndpointInfo localeAndEndpointInfo) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet readBitSet = tTupleProtocol.readBitSet(11);
            if (readBitSet.get(0)) {
                localeAndEndpointInfo.locale = tTupleProtocol.readString();
                localeAndEndpointInfo.setLocaleIsSet(true);
            }
            if (readBitSet.get(1)) {
                localeAndEndpointInfo.countryOfResidence = tTupleProtocol.readString();
                localeAndEndpointInfo.setCountryOfResidenceIsSet(true);
            }
            if (readBitSet.get(2)) {
                localeAndEndpointInfo.wakeWord = tTupleProtocol.readString();
                localeAndEndpointInfo.setWakeWordIsSet(true);
            }
            if (readBitSet.get(3)) {
                localeAndEndpointInfo.alexaEndpoint = tTupleProtocol.readString();
                localeAndEndpointInfo.setAlexaEndpointIsSet(true);
            }
            if (readBitSet.get(4)) {
                localeAndEndpointInfo.alexaPort = tTupleProtocol.readI32();
                localeAndEndpointInfo.setAlexaPortIsSet(true);
            }
            if (readBitSet.get(5)) {
                localeAndEndpointInfo.companionAppEndpoint = tTupleProtocol.readString();
                localeAndEndpointInfo.setCompanionAppEndpointIsSet(true);
            }
            if (readBitSet.get(6)) {
                localeAndEndpointInfo.companionAppPort = tTupleProtocol.readI32();
                localeAndEndpointInfo.setCompanionAppPortIsSet(true);
            }
            if (readBitSet.get(7)) {
                localeAndEndpointInfo.pandaEndpoint = tTupleProtocol.readString();
                localeAndEndpointInfo.setPandaEndpointIsSet(true);
            }
            if (readBitSet.get(8)) {
                localeAndEndpointInfo.todoEndpoint = tTupleProtocol.readString();
                localeAndEndpointInfo.setTodoEndpointIsSet(true);
            }
            if (readBitSet.get(9)) {
                localeAndEndpointInfo.FIRSEndpoint = tTupleProtocol.readString();
                localeAndEndpointInfo.setFIRSEndpointIsSet(true);
            }
            if (readBitSet.get(10)) {
                localeAndEndpointInfo.captivePortalEndpoint = tTupleProtocol.readString();
                localeAndEndpointInfo.setCaptivePortalEndpointIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, LocaleAndEndpointInfo localeAndEndpointInfo) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (localeAndEndpointInfo.isSetLocale()) {
                bitSet.set(0);
            }
            if (localeAndEndpointInfo.isSetCountryOfResidence()) {
                bitSet.set(1);
            }
            if (localeAndEndpointInfo.isSetWakeWord()) {
                bitSet.set(2);
            }
            if (localeAndEndpointInfo.isSetAlexaEndpoint()) {
                bitSet.set(3);
            }
            if (localeAndEndpointInfo.isSetAlexaPort()) {
                bitSet.set(4);
            }
            if (localeAndEndpointInfo.isSetCompanionAppEndpoint()) {
                bitSet.set(5);
            }
            if (localeAndEndpointInfo.isSetCompanionAppPort()) {
                bitSet.set(6);
            }
            if (localeAndEndpointInfo.isSetPandaEndpoint()) {
                bitSet.set(7);
            }
            if (localeAndEndpointInfo.isSetTodoEndpoint()) {
                bitSet.set(8);
            }
            if (localeAndEndpointInfo.isSetFIRSEndpoint()) {
                bitSet.set(9);
            }
            if (localeAndEndpointInfo.isSetCaptivePortalEndpoint()) {
                bitSet.set(10);
            }
            tTupleProtocol.writeBitSet(bitSet, 11);
            if (localeAndEndpointInfo.isSetLocale()) {
                tTupleProtocol.writeString(localeAndEndpointInfo.locale);
            }
            if (localeAndEndpointInfo.isSetCountryOfResidence()) {
                tTupleProtocol.writeString(localeAndEndpointInfo.countryOfResidence);
            }
            if (localeAndEndpointInfo.isSetWakeWord()) {
                tTupleProtocol.writeString(localeAndEndpointInfo.wakeWord);
            }
            if (localeAndEndpointInfo.isSetAlexaEndpoint()) {
                tTupleProtocol.writeString(localeAndEndpointInfo.alexaEndpoint);
            }
            if (localeAndEndpointInfo.isSetAlexaPort()) {
                tTupleProtocol.writeI32(localeAndEndpointInfo.alexaPort);
            }
            if (localeAndEndpointInfo.isSetCompanionAppEndpoint()) {
                tTupleProtocol.writeString(localeAndEndpointInfo.companionAppEndpoint);
            }
            if (localeAndEndpointInfo.isSetCompanionAppPort()) {
                tTupleProtocol.writeI32(localeAndEndpointInfo.companionAppPort);
            }
            if (localeAndEndpointInfo.isSetPandaEndpoint()) {
                tTupleProtocol.writeString(localeAndEndpointInfo.pandaEndpoint);
            }
            if (localeAndEndpointInfo.isSetTodoEndpoint()) {
                tTupleProtocol.writeString(localeAndEndpointInfo.todoEndpoint);
            }
            if (localeAndEndpointInfo.isSetFIRSEndpoint()) {
                tTupleProtocol.writeString(localeAndEndpointInfo.FIRSEndpoint);
            }
            if (localeAndEndpointInfo.isSetCaptivePortalEndpoint()) {
                tTupleProtocol.writeString(localeAndEndpointInfo.captivePortalEndpoint);
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class LocaleAndEndpointInfoTupleSchemeFactory implements SchemeFactory {
        private LocaleAndEndpointInfoTupleSchemeFactory() {
        }

        /* synthetic */ LocaleAndEndpointInfoTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public LocaleAndEndpointInfoTupleScheme mo12846getScheme() {
            return new LocaleAndEndpointInfoTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        LOCALE(1, "locale"),
        COUNTRY_OF_RESIDENCE(2, MetricsConfiguration.COUNTRY_OF_RESIDENCE),
        WAKE_WORD(3, AlexaMetricsConstants.EventConstants.WAKE_WORD),
        ALEXA_ENDPOINT(4, "alexaEndpoint"),
        ALEXA_PORT(5, "alexaPort"),
        COMPANION_APP_ENDPOINT(6, "companionAppEndpoint"),
        COMPANION_APP_PORT(7, "companionAppPort"),
        PANDA_ENDPOINT(8, "pandaEndpoint"),
        TODO_ENDPOINT(9, "todoEndpoint"),
        FIRSENDPOINT(10, "FIRSEndpoint"),
        CAPTIVE_PORTAL_ENDPOINT(11, "captivePortalEndpoint");
        
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
                    return LOCALE;
                case 2:
                    return COUNTRY_OF_RESIDENCE;
                case 3:
                    return WAKE_WORD;
                case 4:
                    return ALEXA_ENDPOINT;
                case 5:
                    return ALEXA_PORT;
                case 6:
                    return COMPANION_APP_ENDPOINT;
                case 7:
                    return COMPANION_APP_PORT;
                case 8:
                    return PANDA_ENDPOINT;
                case 9:
                    return TODO_ENDPOINT;
                case 10:
                    return FIRSENDPOINT;
                case 11:
                    return CAPTIVE_PORTAL_ENDPOINT;
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
        schemes.put(StandardScheme.class, new LocaleAndEndpointInfoStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new LocaleAndEndpointInfoTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.LOCALE, (_Fields) new FieldMetaData("locale", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.COUNTRY_OF_RESIDENCE, (_Fields) new FieldMetaData(MetricsConfiguration.COUNTRY_OF_RESIDENCE, (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.WAKE_WORD, (_Fields) new FieldMetaData(AlexaMetricsConstants.EventConstants.WAKE_WORD, (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.ALEXA_ENDPOINT, (_Fields) new FieldMetaData("alexaEndpoint", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.ALEXA_PORT, (_Fields) new FieldMetaData("alexaPort", (byte) 2, new FieldValueMetaData((byte) 8)));
        enumMap.put((EnumMap) _Fields.COMPANION_APP_ENDPOINT, (_Fields) new FieldMetaData("companionAppEndpoint", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.COMPANION_APP_PORT, (_Fields) new FieldMetaData("companionAppPort", (byte) 2, new FieldValueMetaData((byte) 8)));
        enumMap.put((EnumMap) _Fields.PANDA_ENDPOINT, (_Fields) new FieldMetaData("pandaEndpoint", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.TODO_ENDPOINT, (_Fields) new FieldMetaData("todoEndpoint", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.FIRSENDPOINT, (_Fields) new FieldMetaData("FIRSEndpoint", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.CAPTIVE_PORTAL_ENDPOINT, (_Fields) new FieldMetaData("captivePortalEndpoint", (byte) 2, new FieldValueMetaData((byte) 11)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(LocaleAndEndpointInfo.class, metaDataMap);
    }

    public LocaleAndEndpointInfo() {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new _Fields[]{_Fields.LOCALE, _Fields.COUNTRY_OF_RESIDENCE, _Fields.WAKE_WORD, _Fields.ALEXA_ENDPOINT, _Fields.ALEXA_PORT, _Fields.COMPANION_APP_ENDPOINT, _Fields.COMPANION_APP_PORT, _Fields.PANDA_ENDPOINT, _Fields.TODO_ENDPOINT, _Fields.FIRSENDPOINT, _Fields.CAPTIVE_PORTAL_ENDPOINT};
    }

    public LocaleAndEndpointInfo(LocaleAndEndpointInfo localeAndEndpointInfo) {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new _Fields[]{_Fields.LOCALE, _Fields.COUNTRY_OF_RESIDENCE, _Fields.WAKE_WORD, _Fields.ALEXA_ENDPOINT, _Fields.ALEXA_PORT, _Fields.COMPANION_APP_ENDPOINT, _Fields.COMPANION_APP_PORT, _Fields.PANDA_ENDPOINT, _Fields.TODO_ENDPOINT, _Fields.FIRSENDPOINT, _Fields.CAPTIVE_PORTAL_ENDPOINT};
        this.__isset_bitfield = localeAndEndpointInfo.__isset_bitfield;
        if (localeAndEndpointInfo.isSetLocale()) {
            this.locale = localeAndEndpointInfo.locale;
        }
        if (localeAndEndpointInfo.isSetCountryOfResidence()) {
            this.countryOfResidence = localeAndEndpointInfo.countryOfResidence;
        }
        if (localeAndEndpointInfo.isSetWakeWord()) {
            this.wakeWord = localeAndEndpointInfo.wakeWord;
        }
        if (localeAndEndpointInfo.isSetAlexaEndpoint()) {
            this.alexaEndpoint = localeAndEndpointInfo.alexaEndpoint;
        }
        this.alexaPort = localeAndEndpointInfo.alexaPort;
        if (localeAndEndpointInfo.isSetCompanionAppEndpoint()) {
            this.companionAppEndpoint = localeAndEndpointInfo.companionAppEndpoint;
        }
        this.companionAppPort = localeAndEndpointInfo.companionAppPort;
        if (localeAndEndpointInfo.isSetPandaEndpoint()) {
            this.pandaEndpoint = localeAndEndpointInfo.pandaEndpoint;
        }
        if (localeAndEndpointInfo.isSetTodoEndpoint()) {
            this.todoEndpoint = localeAndEndpointInfo.todoEndpoint;
        }
        if (localeAndEndpointInfo.isSetFIRSEndpoint()) {
            this.FIRSEndpoint = localeAndEndpointInfo.FIRSEndpoint;
        }
        if (localeAndEndpointInfo.isSetCaptivePortalEndpoint()) {
            this.captivePortalEndpoint = localeAndEndpointInfo.captivePortalEndpoint;
        }
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

    @Override // org.apache.thrift.orig.TBase
    public void clear() {
        this.locale = null;
        this.countryOfResidence = null;
        this.wakeWord = null;
        this.alexaEndpoint = null;
        setAlexaPortIsSet(false);
        this.alexaPort = 0;
        this.companionAppEndpoint = null;
        setCompanionAppPortIsSet(false);
        this.companionAppPort = 0;
        this.pandaEndpoint = null;
        this.todoEndpoint = null;
        this.FIRSEndpoint = null;
        this.captivePortalEndpoint = null;
    }

    @Override // java.lang.Comparable
    public int compareTo(LocaleAndEndpointInfo localeAndEndpointInfo) {
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
        if (!LocaleAndEndpointInfo.class.equals(localeAndEndpointInfo.getClass())) {
            return LocaleAndEndpointInfo.class.getName().compareTo(LocaleAndEndpointInfo.class.getName());
        }
        int compareTo12 = Boolean.valueOf(isSetLocale()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetLocale()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetLocale() && (compareTo11 = TBaseHelper.compareTo(this.locale, localeAndEndpointInfo.locale)) != 0) {
            return compareTo11;
        }
        int compareTo13 = Boolean.valueOf(isSetCountryOfResidence()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetCountryOfResidence()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetCountryOfResidence() && (compareTo10 = TBaseHelper.compareTo(this.countryOfResidence, localeAndEndpointInfo.countryOfResidence)) != 0) {
            return compareTo10;
        }
        int compareTo14 = Boolean.valueOf(isSetWakeWord()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetWakeWord()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetWakeWord() && (compareTo9 = TBaseHelper.compareTo(this.wakeWord, localeAndEndpointInfo.wakeWord)) != 0) {
            return compareTo9;
        }
        int compareTo15 = Boolean.valueOf(isSetAlexaEndpoint()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetAlexaEndpoint()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetAlexaEndpoint() && (compareTo8 = TBaseHelper.compareTo(this.alexaEndpoint, localeAndEndpointInfo.alexaEndpoint)) != 0) {
            return compareTo8;
        }
        int compareTo16 = Boolean.valueOf(isSetAlexaPort()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetAlexaPort()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (isSetAlexaPort() && (compareTo7 = TBaseHelper.compareTo(this.alexaPort, localeAndEndpointInfo.alexaPort)) != 0) {
            return compareTo7;
        }
        int compareTo17 = Boolean.valueOf(isSetCompanionAppEndpoint()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetCompanionAppEndpoint()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (isSetCompanionAppEndpoint() && (compareTo6 = TBaseHelper.compareTo(this.companionAppEndpoint, localeAndEndpointInfo.companionAppEndpoint)) != 0) {
            return compareTo6;
        }
        int compareTo18 = Boolean.valueOf(isSetCompanionAppPort()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetCompanionAppPort()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (isSetCompanionAppPort() && (compareTo5 = TBaseHelper.compareTo(this.companionAppPort, localeAndEndpointInfo.companionAppPort)) != 0) {
            return compareTo5;
        }
        int compareTo19 = Boolean.valueOf(isSetPandaEndpoint()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetPandaEndpoint()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (isSetPandaEndpoint() && (compareTo4 = TBaseHelper.compareTo(this.pandaEndpoint, localeAndEndpointInfo.pandaEndpoint)) != 0) {
            return compareTo4;
        }
        int compareTo20 = Boolean.valueOf(isSetTodoEndpoint()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetTodoEndpoint()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (isSetTodoEndpoint() && (compareTo3 = TBaseHelper.compareTo(this.todoEndpoint, localeAndEndpointInfo.todoEndpoint)) != 0) {
            return compareTo3;
        }
        int compareTo21 = Boolean.valueOf(isSetFIRSEndpoint()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetFIRSEndpoint()));
        if (compareTo21 != 0) {
            return compareTo21;
        }
        if (isSetFIRSEndpoint() && (compareTo2 = TBaseHelper.compareTo(this.FIRSEndpoint, localeAndEndpointInfo.FIRSEndpoint)) != 0) {
            return compareTo2;
        }
        int compareTo22 = Boolean.valueOf(isSetCaptivePortalEndpoint()).compareTo(Boolean.valueOf(localeAndEndpointInfo.isSetCaptivePortalEndpoint()));
        if (compareTo22 != 0) {
            return compareTo22;
        }
        if (isSetCaptivePortalEndpoint() && (compareTo = TBaseHelper.compareTo(this.captivePortalEndpoint, localeAndEndpointInfo.captivePortalEndpoint)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<LocaleAndEndpointInfo, _Fields> deepCopy2() {
        return new LocaleAndEndpointInfo(this);
    }

    public boolean equals(LocaleAndEndpointInfo localeAndEndpointInfo) {
        if (localeAndEndpointInfo == null) {
            return false;
        }
        boolean isSetLocale = isSetLocale();
        boolean isSetLocale2 = localeAndEndpointInfo.isSetLocale();
        if ((isSetLocale || isSetLocale2) && (!isSetLocale || !isSetLocale2 || !this.locale.equals(localeAndEndpointInfo.locale))) {
            return false;
        }
        boolean isSetCountryOfResidence = isSetCountryOfResidence();
        boolean isSetCountryOfResidence2 = localeAndEndpointInfo.isSetCountryOfResidence();
        if ((isSetCountryOfResidence || isSetCountryOfResidence2) && (!isSetCountryOfResidence || !isSetCountryOfResidence2 || !this.countryOfResidence.equals(localeAndEndpointInfo.countryOfResidence))) {
            return false;
        }
        boolean isSetWakeWord = isSetWakeWord();
        boolean isSetWakeWord2 = localeAndEndpointInfo.isSetWakeWord();
        if ((isSetWakeWord || isSetWakeWord2) && (!isSetWakeWord || !isSetWakeWord2 || !this.wakeWord.equals(localeAndEndpointInfo.wakeWord))) {
            return false;
        }
        boolean isSetAlexaEndpoint = isSetAlexaEndpoint();
        boolean isSetAlexaEndpoint2 = localeAndEndpointInfo.isSetAlexaEndpoint();
        if ((isSetAlexaEndpoint || isSetAlexaEndpoint2) && (!isSetAlexaEndpoint || !isSetAlexaEndpoint2 || !this.alexaEndpoint.equals(localeAndEndpointInfo.alexaEndpoint))) {
            return false;
        }
        boolean isSetAlexaPort = isSetAlexaPort();
        boolean isSetAlexaPort2 = localeAndEndpointInfo.isSetAlexaPort();
        if ((isSetAlexaPort || isSetAlexaPort2) && (!isSetAlexaPort || !isSetAlexaPort2 || this.alexaPort != localeAndEndpointInfo.alexaPort)) {
            return false;
        }
        boolean isSetCompanionAppEndpoint = isSetCompanionAppEndpoint();
        boolean isSetCompanionAppEndpoint2 = localeAndEndpointInfo.isSetCompanionAppEndpoint();
        if ((isSetCompanionAppEndpoint || isSetCompanionAppEndpoint2) && (!isSetCompanionAppEndpoint || !isSetCompanionAppEndpoint2 || !this.companionAppEndpoint.equals(localeAndEndpointInfo.companionAppEndpoint))) {
            return false;
        }
        boolean isSetCompanionAppPort = isSetCompanionAppPort();
        boolean isSetCompanionAppPort2 = localeAndEndpointInfo.isSetCompanionAppPort();
        if ((isSetCompanionAppPort || isSetCompanionAppPort2) && (!isSetCompanionAppPort || !isSetCompanionAppPort2 || this.companionAppPort != localeAndEndpointInfo.companionAppPort)) {
            return false;
        }
        boolean isSetPandaEndpoint = isSetPandaEndpoint();
        boolean isSetPandaEndpoint2 = localeAndEndpointInfo.isSetPandaEndpoint();
        if ((isSetPandaEndpoint || isSetPandaEndpoint2) && (!isSetPandaEndpoint || !isSetPandaEndpoint2 || !this.pandaEndpoint.equals(localeAndEndpointInfo.pandaEndpoint))) {
            return false;
        }
        boolean isSetTodoEndpoint = isSetTodoEndpoint();
        boolean isSetTodoEndpoint2 = localeAndEndpointInfo.isSetTodoEndpoint();
        if ((isSetTodoEndpoint || isSetTodoEndpoint2) && (!isSetTodoEndpoint || !isSetTodoEndpoint2 || !this.todoEndpoint.equals(localeAndEndpointInfo.todoEndpoint))) {
            return false;
        }
        boolean isSetFIRSEndpoint = isSetFIRSEndpoint();
        boolean isSetFIRSEndpoint2 = localeAndEndpointInfo.isSetFIRSEndpoint();
        if ((isSetFIRSEndpoint || isSetFIRSEndpoint2) && (!isSetFIRSEndpoint || !isSetFIRSEndpoint2 || !this.FIRSEndpoint.equals(localeAndEndpointInfo.FIRSEndpoint))) {
            return false;
        }
        boolean isSetCaptivePortalEndpoint = isSetCaptivePortalEndpoint();
        boolean isSetCaptivePortalEndpoint2 = localeAndEndpointInfo.isSetCaptivePortalEndpoint();
        if (!isSetCaptivePortalEndpoint && !isSetCaptivePortalEndpoint2) {
            return true;
        }
        return isSetCaptivePortalEndpoint && isSetCaptivePortalEndpoint2 && this.captivePortalEndpoint.equals(localeAndEndpointInfo.captivePortalEndpoint);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof LocaleAndEndpointInfo)) {
            return equals((LocaleAndEndpointInfo) obj);
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.thrift.orig.TBase
    /* renamed from: fieldForId */
    public _Fields mo3968fieldForId(int i) {
        return _Fields.findByThriftId(i);
    }

    public String getAlexaEndpoint() {
        return this.alexaEndpoint;
    }

    public int getAlexaPort() {
        return this.alexaPort;
    }

    public String getCaptivePortalEndpoint() {
        return this.captivePortalEndpoint;
    }

    public String getCompanionAppEndpoint() {
        return this.companionAppEndpoint;
    }

    public int getCompanionAppPort() {
        return this.companionAppPort;
    }

    public String getCountryOfResidence() {
        return this.countryOfResidence;
    }

    public String getFIRSEndpoint() {
        return this.FIRSEndpoint;
    }

    @Override // org.apache.thrift.orig.TBase
    public Object getFieldValue(_Fields _fields) {
        int alexaPort;
        switch (_fields.ordinal()) {
            case 0:
                return getLocale();
            case 1:
                return getCountryOfResidence();
            case 2:
                return getWakeWord();
            case 3:
                return getAlexaEndpoint();
            case 4:
                alexaPort = getAlexaPort();
                break;
            case 5:
                return getCompanionAppEndpoint();
            case 6:
                alexaPort = getCompanionAppPort();
                break;
            case 7:
                return getPandaEndpoint();
            case 8:
                return getTodoEndpoint();
            case 9:
                return getFIRSEndpoint();
            case 10:
                return getCaptivePortalEndpoint();
            default:
                throw new IllegalStateException();
        }
        return Integer.valueOf(alexaPort);
    }

    public String getLocale() {
        return this.locale;
    }

    public String getPandaEndpoint() {
        return this.pandaEndpoint;
    }

    public String getTodoEndpoint() {
        return this.todoEndpoint;
    }

    public String getWakeWord() {
        return this.wakeWord;
    }

    public int hashCode() {
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(_Fields _fields) {
        if (_fields != null) {
            switch (_fields.ordinal()) {
                case 0:
                    return isSetLocale();
                case 1:
                    return isSetCountryOfResidence();
                case 2:
                    return isSetWakeWord();
                case 3:
                    return isSetAlexaEndpoint();
                case 4:
                    return isSetAlexaPort();
                case 5:
                    return isSetCompanionAppEndpoint();
                case 6:
                    return isSetCompanionAppPort();
                case 7:
                    return isSetPandaEndpoint();
                case 8:
                    return isSetTodoEndpoint();
                case 9:
                    return isSetFIRSEndpoint();
                case 10:
                    return isSetCaptivePortalEndpoint();
                default:
                    throw new IllegalStateException();
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetAlexaEndpoint() {
        return this.alexaEndpoint != null;
    }

    public boolean isSetAlexaPort() {
        return EncodingUtils.testBit(this.__isset_bitfield, 0);
    }

    public boolean isSetCaptivePortalEndpoint() {
        return this.captivePortalEndpoint != null;
    }

    public boolean isSetCompanionAppEndpoint() {
        return this.companionAppEndpoint != null;
    }

    public boolean isSetCompanionAppPort() {
        return EncodingUtils.testBit(this.__isset_bitfield, 1);
    }

    public boolean isSetCountryOfResidence() {
        return this.countryOfResidence != null;
    }

    public boolean isSetFIRSEndpoint() {
        return this.FIRSEndpoint != null;
    }

    public boolean isSetLocale() {
        return this.locale != null;
    }

    public boolean isSetPandaEndpoint() {
        return this.pandaEndpoint != null;
    }

    public boolean isSetTodoEndpoint() {
        return this.todoEndpoint != null;
    }

    public boolean isSetWakeWord() {
        return this.wakeWord != null;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    public LocaleAndEndpointInfo setAlexaEndpoint(String str) {
        this.alexaEndpoint = str;
        return this;
    }

    public void setAlexaEndpointIsSet(boolean z) {
        if (!z) {
            this.alexaEndpoint = null;
        }
    }

    public LocaleAndEndpointInfo setAlexaPort(int i) {
        this.alexaPort = i;
        setAlexaPortIsSet(true);
        return this;
    }

    public void setAlexaPortIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, z);
    }

    public LocaleAndEndpointInfo setCaptivePortalEndpoint(String str) {
        this.captivePortalEndpoint = str;
        return this;
    }

    public void setCaptivePortalEndpointIsSet(boolean z) {
        if (!z) {
            this.captivePortalEndpoint = null;
        }
    }

    public LocaleAndEndpointInfo setCompanionAppEndpoint(String str) {
        this.companionAppEndpoint = str;
        return this;
    }

    public void setCompanionAppEndpointIsSet(boolean z) {
        if (!z) {
            this.companionAppEndpoint = null;
        }
    }

    public LocaleAndEndpointInfo setCompanionAppPort(int i) {
        this.companionAppPort = i;
        setCompanionAppPortIsSet(true);
        return this;
    }

    public void setCompanionAppPortIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, z);
    }

    public LocaleAndEndpointInfo setCountryOfResidence(String str) {
        this.countryOfResidence = str;
        return this;
    }

    public void setCountryOfResidenceIsSet(boolean z) {
        if (!z) {
            this.countryOfResidence = null;
        }
    }

    public LocaleAndEndpointInfo setFIRSEndpoint(String str) {
        this.FIRSEndpoint = str;
        return this;
    }

    public void setFIRSEndpointIsSet(boolean z) {
        if (!z) {
            this.FIRSEndpoint = null;
        }
    }

    @Override // org.apache.thrift.orig.TBase
    public void setFieldValue(_Fields _fields, Object obj) {
        switch (_fields.ordinal()) {
            case 0:
                if (obj == null) {
                    unsetLocale();
                    return;
                } else {
                    setLocale((String) obj);
                    return;
                }
            case 1:
                if (obj == null) {
                    unsetCountryOfResidence();
                    return;
                } else {
                    setCountryOfResidence((String) obj);
                    return;
                }
            case 2:
                if (obj == null) {
                    unsetWakeWord();
                    return;
                } else {
                    setWakeWord((String) obj);
                    return;
                }
            case 3:
                if (obj == null) {
                    unsetAlexaEndpoint();
                    return;
                } else {
                    setAlexaEndpoint((String) obj);
                    return;
                }
            case 4:
                if (obj == null) {
                    unsetAlexaPort();
                    return;
                } else {
                    setAlexaPort(((Integer) obj).intValue());
                    return;
                }
            case 5:
                if (obj == null) {
                    unsetCompanionAppEndpoint();
                    return;
                } else {
                    setCompanionAppEndpoint((String) obj);
                    return;
                }
            case 6:
                if (obj == null) {
                    unsetCompanionAppPort();
                    return;
                } else {
                    setCompanionAppPort(((Integer) obj).intValue());
                    return;
                }
            case 7:
                if (obj == null) {
                    unsetPandaEndpoint();
                    return;
                } else {
                    setPandaEndpoint((String) obj);
                    return;
                }
            case 8:
                if (obj == null) {
                    unsetTodoEndpoint();
                    return;
                } else {
                    setTodoEndpoint((String) obj);
                    return;
                }
            case 9:
                if (obj == null) {
                    unsetFIRSEndpoint();
                    return;
                } else {
                    setFIRSEndpoint((String) obj);
                    return;
                }
            case 10:
                if (obj == null) {
                    unsetCaptivePortalEndpoint();
                    return;
                } else {
                    setCaptivePortalEndpoint((String) obj);
                    return;
                }
            default:
                return;
        }
    }

    public LocaleAndEndpointInfo setLocale(String str) {
        this.locale = str;
        return this;
    }

    public void setLocaleIsSet(boolean z) {
        if (!z) {
            this.locale = null;
        }
    }

    public LocaleAndEndpointInfo setPandaEndpoint(String str) {
        this.pandaEndpoint = str;
        return this;
    }

    public void setPandaEndpointIsSet(boolean z) {
        if (!z) {
            this.pandaEndpoint = null;
        }
    }

    public LocaleAndEndpointInfo setTodoEndpoint(String str) {
        this.todoEndpoint = str;
        return this;
    }

    public void setTodoEndpointIsSet(boolean z) {
        if (!z) {
            this.todoEndpoint = null;
        }
    }

    public LocaleAndEndpointInfo setWakeWord(String str) {
        this.wakeWord = str;
        return this;
    }

    public void setWakeWordIsSet(boolean z) {
        if (!z) {
            this.wakeWord = null;
        }
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("LocaleAndEndpointInfo(");
        if (isSetLocale()) {
            sb.append("locale:");
            String str = this.locale;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (isSetCountryOfResidence()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("countryOfResidence:");
            String str2 = this.countryOfResidence;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
            z = false;
        }
        if (isSetWakeWord()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("wakeWord:");
            String str3 = this.wakeWord;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
            z = false;
        }
        if (isSetAlexaEndpoint()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("alexaEndpoint:");
            String str4 = this.alexaEndpoint;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
            z = false;
        }
        if (isSetAlexaPort()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("alexaPort:");
            sb.append(this.alexaPort);
            z = false;
        }
        if (isSetCompanionAppEndpoint()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("companionAppEndpoint:");
            String str5 = this.companionAppEndpoint;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
            z = false;
        }
        if (isSetCompanionAppPort()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("companionAppPort:");
            sb.append(this.companionAppPort);
            z = false;
        }
        if (isSetPandaEndpoint()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("pandaEndpoint:");
            String str6 = this.pandaEndpoint;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
            z = false;
        }
        if (isSetTodoEndpoint()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("todoEndpoint:");
            String str7 = this.todoEndpoint;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
            z = false;
        }
        if (isSetFIRSEndpoint()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("FIRSEndpoint:");
            String str8 = this.FIRSEndpoint;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
            z = false;
        }
        if (isSetCaptivePortalEndpoint()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("captivePortalEndpoint:");
            String str9 = this.captivePortalEndpoint;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void unsetAlexaEndpoint() {
        this.alexaEndpoint = null;
    }

    public void unsetAlexaPort() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
    }

    public void unsetCaptivePortalEndpoint() {
        this.captivePortalEndpoint = null;
    }

    public void unsetCompanionAppEndpoint() {
        this.companionAppEndpoint = null;
    }

    public void unsetCompanionAppPort() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
    }

    public void unsetCountryOfResidence() {
        this.countryOfResidence = null;
    }

    public void unsetFIRSEndpoint() {
        this.FIRSEndpoint = null;
    }

    public void unsetLocale() {
        this.locale = null;
    }

    public void unsetPandaEndpoint() {
        this.pandaEndpoint = null;
    }

    public void unsetTodoEndpoint() {
        this.todoEndpoint = null;
    }

    public void unsetWakeWord() {
        this.wakeWord = null;
    }

    public void validate() throws TException {
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
