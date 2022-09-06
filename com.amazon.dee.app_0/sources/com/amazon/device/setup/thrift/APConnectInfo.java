package com.amazon.device.setup.thrift;

import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.animated.InterpolationAnimatedNode;
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
import org.apache.thrift.orig.meta_data.EnumMetaData;
import org.apache.thrift.orig.meta_data.FieldMetaData;
import org.apache.thrift.orig.meta_data.FieldValueMetaData;
import org.apache.thrift.orig.meta_data.StructMetaData;
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
public class APConnectInfo implements TBase<APConnectInfo, _Fields>, Serializable, Cloneable {
    private static final int __ISTETHEREDNETWORK_ISSET_ID = 0;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private byte __isset_bitfield;
    public String client_cert;
    public String eapmethod;
    public String essid;
    public String identity;
    public boolean isTetheredNetwork;
    private _Fields[] optionals;
    public PKCS7Type password;
    public PKCS7Type preauthCode;
    public String priv_key;
    public SecurityMethod smethod;
    private static final TStruct STRUCT_DESC = new TStruct("APConnectInfo");
    private static final TField ESSID_FIELD_DESC = new TField("essid", (byte) 11, 1);
    private static final TField SMETHOD_FIELD_DESC = new TField("smethod", (byte) 8, 2);
    private static final TField PASSWORD_FIELD_DESC = new TField(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, (byte) 12, 3);
    private static final TField EAPMETHOD_FIELD_DESC = new TField("eapmethod", (byte) 11, 4);
    private static final TField IDENTITY_FIELD_DESC = new TField(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, (byte) 11, 5);
    private static final TField CLIENT_CERT_FIELD_DESC = new TField("client_cert", (byte) 11, 6);
    private static final TField PRIV_KEY_FIELD_DESC = new TField("priv_key", (byte) 11, 7);
    private static final TField PREAUTH_CODE_FIELD_DESC = new TField("preauthCode", (byte) 12, 8);
    private static final TField IS_TETHERED_NETWORK_FIELD_DESC = new TField("isTetheredNetwork", (byte) 2, 9);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.APConnectInfo$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields[_Fields.ESSID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields[_Fields.SMETHOD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields[_Fields.PASSWORD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields[_Fields.EAPMETHOD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields[_Fields.IDENTITY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields[_Fields.CLIENT_CERT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields[_Fields.PRIV_KEY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields[_Fields.PREAUTH_CODE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectInfo$_Fields[_Fields.IS_TETHERED_NETWORK.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class APConnectInfoStandardScheme extends StandardScheme<APConnectInfo> {
        private APConnectInfoStandardScheme() {
        }

        /* synthetic */ APConnectInfoStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, APConnectInfo aPConnectInfo) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    aPConnectInfo.validate();
                    return;
                }
                switch (readFieldBegin.id) {
                    case 1:
                        if (b == 11) {
                            aPConnectInfo.essid = tProtocol.readString();
                            aPConnectInfo.setEssidIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 2:
                        if (b == 8) {
                            aPConnectInfo.smethod = SecurityMethod.findByValue(tProtocol.readI32());
                            aPConnectInfo.setSmethodIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 3:
                        if (b == 12) {
                            aPConnectInfo.password = new PKCS7Type();
                            aPConnectInfo.password.read(tProtocol);
                            aPConnectInfo.setPasswordIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 4:
                        if (b == 11) {
                            aPConnectInfo.eapmethod = tProtocol.readString();
                            aPConnectInfo.setEapmethodIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 5:
                        if (b == 11) {
                            aPConnectInfo.identity = tProtocol.readString();
                            aPConnectInfo.setIdentityIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 6:
                        if (b == 11) {
                            aPConnectInfo.client_cert = tProtocol.readString();
                            aPConnectInfo.setClient_certIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 7:
                        if (b == 11) {
                            aPConnectInfo.priv_key = tProtocol.readString();
                            aPConnectInfo.setPriv_keyIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 8:
                        if (b == 12) {
                            aPConnectInfo.preauthCode = new PKCS7Type();
                            aPConnectInfo.preauthCode.read(tProtocol);
                            aPConnectInfo.setPreauthCodeIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 9:
                        if (b == 2) {
                            aPConnectInfo.isTetheredNetwork = tProtocol.readBool();
                            aPConnectInfo.setIsTetheredNetworkIsSet(true);
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
        public void write(TProtocol tProtocol, APConnectInfo aPConnectInfo) throws TException {
            aPConnectInfo.validate();
            tProtocol.writeStructBegin(APConnectInfo.STRUCT_DESC);
            if (aPConnectInfo.essid != null) {
                tProtocol.writeFieldBegin(APConnectInfo.ESSID_FIELD_DESC);
                tProtocol.writeString(aPConnectInfo.essid);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectInfo.smethod != null) {
                tProtocol.writeFieldBegin(APConnectInfo.SMETHOD_FIELD_DESC);
                tProtocol.writeI32(aPConnectInfo.smethod.getValue());
                tProtocol.writeFieldEnd();
            }
            if (aPConnectInfo.password != null) {
                tProtocol.writeFieldBegin(APConnectInfo.PASSWORD_FIELD_DESC);
                aPConnectInfo.password.write(tProtocol);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectInfo.eapmethod != null) {
                tProtocol.writeFieldBegin(APConnectInfo.EAPMETHOD_FIELD_DESC);
                tProtocol.writeString(aPConnectInfo.eapmethod);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectInfo.identity != null) {
                tProtocol.writeFieldBegin(APConnectInfo.IDENTITY_FIELD_DESC);
                tProtocol.writeString(aPConnectInfo.identity);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectInfo.client_cert != null) {
                tProtocol.writeFieldBegin(APConnectInfo.CLIENT_CERT_FIELD_DESC);
                tProtocol.writeString(aPConnectInfo.client_cert);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectInfo.priv_key != null) {
                tProtocol.writeFieldBegin(APConnectInfo.PRIV_KEY_FIELD_DESC);
                tProtocol.writeString(aPConnectInfo.priv_key);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectInfo.preauthCode != null && aPConnectInfo.isSetPreauthCode()) {
                tProtocol.writeFieldBegin(APConnectInfo.PREAUTH_CODE_FIELD_DESC);
                aPConnectInfo.preauthCode.write(tProtocol);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectInfo.isSetIsTetheredNetwork()) {
                tProtocol.writeFieldBegin(APConnectInfo.IS_TETHERED_NETWORK_FIELD_DESC);
                tProtocol.writeBool(aPConnectInfo.isTetheredNetwork);
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class APConnectInfoStandardSchemeFactory implements SchemeFactory {
        private APConnectInfoStandardSchemeFactory() {
        }

        /* synthetic */ APConnectInfoStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public APConnectInfoStandardScheme mo12846getScheme() {
            return new APConnectInfoStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class APConnectInfoTupleScheme extends TupleScheme<APConnectInfo> {
        private APConnectInfoTupleScheme() {
        }

        /* synthetic */ APConnectInfoTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, APConnectInfo aPConnectInfo) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet readBitSet = tTupleProtocol.readBitSet(9);
            if (readBitSet.get(0)) {
                aPConnectInfo.essid = tTupleProtocol.readString();
                aPConnectInfo.setEssidIsSet(true);
            }
            if (readBitSet.get(1)) {
                aPConnectInfo.smethod = SecurityMethod.findByValue(tTupleProtocol.readI32());
                aPConnectInfo.setSmethodIsSet(true);
            }
            if (readBitSet.get(2)) {
                aPConnectInfo.password = new PKCS7Type();
                aPConnectInfo.password.read(tTupleProtocol);
                aPConnectInfo.setPasswordIsSet(true);
            }
            if (readBitSet.get(3)) {
                aPConnectInfo.eapmethod = tTupleProtocol.readString();
                aPConnectInfo.setEapmethodIsSet(true);
            }
            if (readBitSet.get(4)) {
                aPConnectInfo.identity = tTupleProtocol.readString();
                aPConnectInfo.setIdentityIsSet(true);
            }
            if (readBitSet.get(5)) {
                aPConnectInfo.client_cert = tTupleProtocol.readString();
                aPConnectInfo.setClient_certIsSet(true);
            }
            if (readBitSet.get(6)) {
                aPConnectInfo.priv_key = tTupleProtocol.readString();
                aPConnectInfo.setPriv_keyIsSet(true);
            }
            if (readBitSet.get(7)) {
                aPConnectInfo.preauthCode = new PKCS7Type();
                aPConnectInfo.preauthCode.read(tTupleProtocol);
                aPConnectInfo.setPreauthCodeIsSet(true);
            }
            if (readBitSet.get(8)) {
                aPConnectInfo.isTetheredNetwork = tTupleProtocol.readBool();
                aPConnectInfo.setIsTetheredNetworkIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, APConnectInfo aPConnectInfo) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (aPConnectInfo.isSetEssid()) {
                bitSet.set(0);
            }
            if (aPConnectInfo.isSetSmethod()) {
                bitSet.set(1);
            }
            if (aPConnectInfo.isSetPassword()) {
                bitSet.set(2);
            }
            if (aPConnectInfo.isSetEapmethod()) {
                bitSet.set(3);
            }
            if (aPConnectInfo.isSetIdentity()) {
                bitSet.set(4);
            }
            if (aPConnectInfo.isSetClient_cert()) {
                bitSet.set(5);
            }
            if (aPConnectInfo.isSetPriv_key()) {
                bitSet.set(6);
            }
            if (aPConnectInfo.isSetPreauthCode()) {
                bitSet.set(7);
            }
            if (aPConnectInfo.isSetIsTetheredNetwork()) {
                bitSet.set(8);
            }
            tTupleProtocol.writeBitSet(bitSet, 9);
            if (aPConnectInfo.isSetEssid()) {
                tTupleProtocol.writeString(aPConnectInfo.essid);
            }
            if (aPConnectInfo.isSetSmethod()) {
                tTupleProtocol.writeI32(aPConnectInfo.smethod.getValue());
            }
            if (aPConnectInfo.isSetPassword()) {
                aPConnectInfo.password.write(tTupleProtocol);
            }
            if (aPConnectInfo.isSetEapmethod()) {
                tTupleProtocol.writeString(aPConnectInfo.eapmethod);
            }
            if (aPConnectInfo.isSetIdentity()) {
                tTupleProtocol.writeString(aPConnectInfo.identity);
            }
            if (aPConnectInfo.isSetClient_cert()) {
                tTupleProtocol.writeString(aPConnectInfo.client_cert);
            }
            if (aPConnectInfo.isSetPriv_key()) {
                tTupleProtocol.writeString(aPConnectInfo.priv_key);
            }
            if (aPConnectInfo.isSetPreauthCode()) {
                aPConnectInfo.preauthCode.write(tTupleProtocol);
            }
            if (aPConnectInfo.isSetIsTetheredNetwork()) {
                tTupleProtocol.writeBool(aPConnectInfo.isTetheredNetwork);
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class APConnectInfoTupleSchemeFactory implements SchemeFactory {
        private APConnectInfoTupleSchemeFactory() {
        }

        /* synthetic */ APConnectInfoTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public APConnectInfoTupleScheme mo12846getScheme() {
            return new APConnectInfoTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        ESSID(1, "essid"),
        SMETHOD(2, "smethod"),
        PASSWORD(3, MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD),
        EAPMETHOD(4, "eapmethod"),
        IDENTITY(5, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY),
        CLIENT_CERT(6, "client_cert"),
        PRIV_KEY(7, "priv_key"),
        PREAUTH_CODE(8, "preauthCode"),
        IS_TETHERED_NETWORK(9, "isTetheredNetwork");
        
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
                    return ESSID;
                case 2:
                    return SMETHOD;
                case 3:
                    return PASSWORD;
                case 4:
                    return EAPMETHOD;
                case 5:
                    return IDENTITY;
                case 6:
                    return CLIENT_CERT;
                case 7:
                    return PRIV_KEY;
                case 8:
                    return PREAUTH_CODE;
                case 9:
                    return IS_TETHERED_NETWORK;
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
        schemes.put(StandardScheme.class, new APConnectInfoStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new APConnectInfoTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.ESSID, (_Fields) new FieldMetaData("essid", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.SMETHOD, (_Fields) new FieldMetaData("smethod", (byte) 3, new EnumMetaData((byte) 16, SecurityMethod.class)));
        enumMap.put((EnumMap) _Fields.PASSWORD, (_Fields) new FieldMetaData(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, (byte) 3, new StructMetaData((byte) 12, PKCS7Type.class)));
        enumMap.put((EnumMap) _Fields.EAPMETHOD, (_Fields) new FieldMetaData("eapmethod", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.IDENTITY, (_Fields) new FieldMetaData(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.CLIENT_CERT, (_Fields) new FieldMetaData("client_cert", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.PRIV_KEY, (_Fields) new FieldMetaData("priv_key", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.PREAUTH_CODE, (_Fields) new FieldMetaData("preauthCode", (byte) 2, new StructMetaData((byte) 12, PKCS7Type.class)));
        enumMap.put((EnumMap) _Fields.IS_TETHERED_NETWORK, (_Fields) new FieldMetaData("isTetheredNetwork", (byte) 2, new FieldValueMetaData((byte) 2)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(APConnectInfo.class, metaDataMap);
    }

    public APConnectInfo() {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new _Fields[]{_Fields.PREAUTH_CODE, _Fields.IS_TETHERED_NETWORK};
    }

    public APConnectInfo(APConnectInfo aPConnectInfo) {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new _Fields[]{_Fields.PREAUTH_CODE, _Fields.IS_TETHERED_NETWORK};
        this.__isset_bitfield = aPConnectInfo.__isset_bitfield;
        if (aPConnectInfo.isSetEssid()) {
            this.essid = aPConnectInfo.essid;
        }
        if (aPConnectInfo.isSetSmethod()) {
            this.smethod = aPConnectInfo.smethod;
        }
        if (aPConnectInfo.isSetPassword()) {
            this.password = new PKCS7Type(aPConnectInfo.password);
        }
        if (aPConnectInfo.isSetEapmethod()) {
            this.eapmethod = aPConnectInfo.eapmethod;
        }
        if (aPConnectInfo.isSetIdentity()) {
            this.identity = aPConnectInfo.identity;
        }
        if (aPConnectInfo.isSetClient_cert()) {
            this.client_cert = aPConnectInfo.client_cert;
        }
        if (aPConnectInfo.isSetPriv_key()) {
            this.priv_key = aPConnectInfo.priv_key;
        }
        if (aPConnectInfo.isSetPreauthCode()) {
            this.preauthCode = new PKCS7Type(aPConnectInfo.preauthCode);
        }
        this.isTetheredNetwork = aPConnectInfo.isTetheredNetwork;
    }

    public APConnectInfo(String str, SecurityMethod securityMethod, PKCS7Type pKCS7Type, String str2, String str3, String str4, String str5) {
        this();
        this.essid = str;
        this.smethod = securityMethod;
        this.password = pKCS7Type;
        this.eapmethod = str2;
        this.identity = str3;
        this.client_cert = str4;
        this.priv_key = str5;
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
        this.essid = null;
        this.smethod = null;
        this.password = null;
        this.eapmethod = null;
        this.identity = null;
        this.client_cert = null;
        this.priv_key = null;
        this.preauthCode = null;
        setIsTetheredNetworkIsSet(false);
        this.isTetheredNetwork = false;
    }

    @Override // java.lang.Comparable
    public int compareTo(APConnectInfo aPConnectInfo) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        int compareTo8;
        int compareTo9;
        if (!APConnectInfo.class.equals(aPConnectInfo.getClass())) {
            return APConnectInfo.class.getName().compareTo(APConnectInfo.class.getName());
        }
        int compareTo10 = Boolean.valueOf(isSetEssid()).compareTo(Boolean.valueOf(aPConnectInfo.isSetEssid()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (isSetEssid() && (compareTo9 = TBaseHelper.compareTo(this.essid, aPConnectInfo.essid)) != 0) {
            return compareTo9;
        }
        int compareTo11 = Boolean.valueOf(isSetSmethod()).compareTo(Boolean.valueOf(aPConnectInfo.isSetSmethod()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetSmethod() && (compareTo8 = TBaseHelper.compareTo((Comparable) this.smethod, (Comparable) aPConnectInfo.smethod)) != 0) {
            return compareTo8;
        }
        int compareTo12 = Boolean.valueOf(isSetPassword()).compareTo(Boolean.valueOf(aPConnectInfo.isSetPassword()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetPassword() && (compareTo7 = TBaseHelper.compareTo((Comparable) this.password, (Comparable) aPConnectInfo.password)) != 0) {
            return compareTo7;
        }
        int compareTo13 = Boolean.valueOf(isSetEapmethod()).compareTo(Boolean.valueOf(aPConnectInfo.isSetEapmethod()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetEapmethod() && (compareTo6 = TBaseHelper.compareTo(this.eapmethod, aPConnectInfo.eapmethod)) != 0) {
            return compareTo6;
        }
        int compareTo14 = Boolean.valueOf(isSetIdentity()).compareTo(Boolean.valueOf(aPConnectInfo.isSetIdentity()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetIdentity() && (compareTo5 = TBaseHelper.compareTo(this.identity, aPConnectInfo.identity)) != 0) {
            return compareTo5;
        }
        int compareTo15 = Boolean.valueOf(isSetClient_cert()).compareTo(Boolean.valueOf(aPConnectInfo.isSetClient_cert()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (isSetClient_cert() && (compareTo4 = TBaseHelper.compareTo(this.client_cert, aPConnectInfo.client_cert)) != 0) {
            return compareTo4;
        }
        int compareTo16 = Boolean.valueOf(isSetPriv_key()).compareTo(Boolean.valueOf(aPConnectInfo.isSetPriv_key()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (isSetPriv_key() && (compareTo3 = TBaseHelper.compareTo(this.priv_key, aPConnectInfo.priv_key)) != 0) {
            return compareTo3;
        }
        int compareTo17 = Boolean.valueOf(isSetPreauthCode()).compareTo(Boolean.valueOf(aPConnectInfo.isSetPreauthCode()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (isSetPreauthCode() && (compareTo2 = TBaseHelper.compareTo((Comparable) this.preauthCode, (Comparable) aPConnectInfo.preauthCode)) != 0) {
            return compareTo2;
        }
        int compareTo18 = Boolean.valueOf(isSetIsTetheredNetwork()).compareTo(Boolean.valueOf(aPConnectInfo.isSetIsTetheredNetwork()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (isSetIsTetheredNetwork() && (compareTo = TBaseHelper.compareTo(this.isTetheredNetwork, aPConnectInfo.isTetheredNetwork)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<APConnectInfo, _Fields> deepCopy2() {
        return new APConnectInfo(this);
    }

    public boolean equals(APConnectInfo aPConnectInfo) {
        if (aPConnectInfo == null) {
            return false;
        }
        boolean isSetEssid = isSetEssid();
        boolean isSetEssid2 = aPConnectInfo.isSetEssid();
        if ((isSetEssid || isSetEssid2) && (!isSetEssid || !isSetEssid2 || !this.essid.equals(aPConnectInfo.essid))) {
            return false;
        }
        boolean isSetSmethod = isSetSmethod();
        boolean isSetSmethod2 = aPConnectInfo.isSetSmethod();
        if ((isSetSmethod || isSetSmethod2) && (!isSetSmethod || !isSetSmethod2 || !this.smethod.equals(aPConnectInfo.smethod))) {
            return false;
        }
        boolean isSetPassword = isSetPassword();
        boolean isSetPassword2 = aPConnectInfo.isSetPassword();
        if ((isSetPassword || isSetPassword2) && (!isSetPassword || !isSetPassword2 || !this.password.equals(aPConnectInfo.password))) {
            return false;
        }
        boolean isSetEapmethod = isSetEapmethod();
        boolean isSetEapmethod2 = aPConnectInfo.isSetEapmethod();
        if ((isSetEapmethod || isSetEapmethod2) && (!isSetEapmethod || !isSetEapmethod2 || !this.eapmethod.equals(aPConnectInfo.eapmethod))) {
            return false;
        }
        boolean isSetIdentity = isSetIdentity();
        boolean isSetIdentity2 = aPConnectInfo.isSetIdentity();
        if ((isSetIdentity || isSetIdentity2) && (!isSetIdentity || !isSetIdentity2 || !this.identity.equals(aPConnectInfo.identity))) {
            return false;
        }
        boolean isSetClient_cert = isSetClient_cert();
        boolean isSetClient_cert2 = aPConnectInfo.isSetClient_cert();
        if ((isSetClient_cert || isSetClient_cert2) && (!isSetClient_cert || !isSetClient_cert2 || !this.client_cert.equals(aPConnectInfo.client_cert))) {
            return false;
        }
        boolean isSetPriv_key = isSetPriv_key();
        boolean isSetPriv_key2 = aPConnectInfo.isSetPriv_key();
        if ((isSetPriv_key || isSetPriv_key2) && (!isSetPriv_key || !isSetPriv_key2 || !this.priv_key.equals(aPConnectInfo.priv_key))) {
            return false;
        }
        boolean isSetPreauthCode = isSetPreauthCode();
        boolean isSetPreauthCode2 = aPConnectInfo.isSetPreauthCode();
        if ((isSetPreauthCode || isSetPreauthCode2) && (!isSetPreauthCode || !isSetPreauthCode2 || !this.preauthCode.equals(aPConnectInfo.preauthCode))) {
            return false;
        }
        boolean isSetIsTetheredNetwork = isSetIsTetheredNetwork();
        boolean isSetIsTetheredNetwork2 = aPConnectInfo.isSetIsTetheredNetwork();
        if (!isSetIsTetheredNetwork && !isSetIsTetheredNetwork2) {
            return true;
        }
        return isSetIsTetheredNetwork && isSetIsTetheredNetwork2 && this.isTetheredNetwork == aPConnectInfo.isTetheredNetwork;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof APConnectInfo)) {
            return equals((APConnectInfo) obj);
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.thrift.orig.TBase
    /* renamed from: fieldForId */
    public _Fields mo3968fieldForId(int i) {
        return _Fields.findByThriftId(i);
    }

    public String getClient_cert() {
        return this.client_cert;
    }

    public String getEapmethod() {
        return this.eapmethod;
    }

    public String getEssid() {
        return this.essid;
    }

    @Override // org.apache.thrift.orig.TBase
    public Object getFieldValue(_Fields _fields) {
        switch (_fields.ordinal()) {
            case 0:
                return getEssid();
            case 1:
                return getSmethod();
            case 2:
                return getPassword();
            case 3:
                return getEapmethod();
            case 4:
                return getIdentity();
            case 5:
                return getClient_cert();
            case 6:
                return getPriv_key();
            case 7:
                return getPreauthCode();
            case 8:
                return Boolean.valueOf(isIsTetheredNetwork());
            default:
                throw new IllegalStateException();
        }
    }

    public String getIdentity() {
        return this.identity;
    }

    public PKCS7Type getPassword() {
        return this.password;
    }

    public PKCS7Type getPreauthCode() {
        return this.preauthCode;
    }

    public String getPriv_key() {
        return this.priv_key;
    }

    public SecurityMethod getSmethod() {
        return this.smethod;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isIsTetheredNetwork() {
        return this.isTetheredNetwork;
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(_Fields _fields) {
        if (_fields != null) {
            switch (_fields.ordinal()) {
                case 0:
                    return isSetEssid();
                case 1:
                    return isSetSmethod();
                case 2:
                    return isSetPassword();
                case 3:
                    return isSetEapmethod();
                case 4:
                    return isSetIdentity();
                case 5:
                    return isSetClient_cert();
                case 6:
                    return isSetPriv_key();
                case 7:
                    return isSetPreauthCode();
                case 8:
                    return isSetIsTetheredNetwork();
                default:
                    throw new IllegalStateException();
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetClient_cert() {
        return this.client_cert != null;
    }

    public boolean isSetEapmethod() {
        return this.eapmethod != null;
    }

    public boolean isSetEssid() {
        return this.essid != null;
    }

    public boolean isSetIdentity() {
        return this.identity != null;
    }

    public boolean isSetIsTetheredNetwork() {
        return EncodingUtils.testBit(this.__isset_bitfield, 0);
    }

    public boolean isSetPassword() {
        return this.password != null;
    }

    public boolean isSetPreauthCode() {
        return this.preauthCode != null;
    }

    public boolean isSetPriv_key() {
        return this.priv_key != null;
    }

    public boolean isSetSmethod() {
        return this.smethod != null;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    public APConnectInfo setClient_cert(String str) {
        this.client_cert = str;
        return this;
    }

    public void setClient_certIsSet(boolean z) {
        if (!z) {
            this.client_cert = null;
        }
    }

    public APConnectInfo setEapmethod(String str) {
        this.eapmethod = str;
        return this;
    }

    public void setEapmethodIsSet(boolean z) {
        if (!z) {
            this.eapmethod = null;
        }
    }

    public APConnectInfo setEssid(String str) {
        this.essid = str;
        return this;
    }

    public void setEssidIsSet(boolean z) {
        if (!z) {
            this.essid = null;
        }
    }

    @Override // org.apache.thrift.orig.TBase
    public void setFieldValue(_Fields _fields, Object obj) {
        switch (_fields.ordinal()) {
            case 0:
                if (obj == null) {
                    unsetEssid();
                    return;
                } else {
                    setEssid((String) obj);
                    return;
                }
            case 1:
                if (obj == null) {
                    unsetSmethod();
                    return;
                } else {
                    setSmethod((SecurityMethod) obj);
                    return;
                }
            case 2:
                if (obj == null) {
                    unsetPassword();
                    return;
                } else {
                    setPassword((PKCS7Type) obj);
                    return;
                }
            case 3:
                if (obj == null) {
                    unsetEapmethod();
                    return;
                } else {
                    setEapmethod((String) obj);
                    return;
                }
            case 4:
                if (obj == null) {
                    unsetIdentity();
                    return;
                } else {
                    setIdentity((String) obj);
                    return;
                }
            case 5:
                if (obj == null) {
                    unsetClient_cert();
                    return;
                } else {
                    setClient_cert((String) obj);
                    return;
                }
            case 6:
                if (obj == null) {
                    unsetPriv_key();
                    return;
                } else {
                    setPriv_key((String) obj);
                    return;
                }
            case 7:
                if (obj == null) {
                    unsetPreauthCode();
                    return;
                } else {
                    setPreauthCode((PKCS7Type) obj);
                    return;
                }
            case 8:
                if (obj == null) {
                    unsetIsTetheredNetwork();
                    return;
                } else {
                    setIsTetheredNetwork(((Boolean) obj).booleanValue());
                    return;
                }
            default:
                return;
        }
    }

    public APConnectInfo setIdentity(String str) {
        this.identity = str;
        return this;
    }

    public void setIdentityIsSet(boolean z) {
        if (!z) {
            this.identity = null;
        }
    }

    public APConnectInfo setIsTetheredNetwork(boolean z) {
        this.isTetheredNetwork = z;
        setIsTetheredNetworkIsSet(true);
        return this;
    }

    public void setIsTetheredNetworkIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, z);
    }

    public APConnectInfo setPassword(PKCS7Type pKCS7Type) {
        this.password = pKCS7Type;
        return this;
    }

    public void setPasswordIsSet(boolean z) {
        if (!z) {
            this.password = null;
        }
    }

    public APConnectInfo setPreauthCode(PKCS7Type pKCS7Type) {
        this.preauthCode = pKCS7Type;
        return this;
    }

    public void setPreauthCodeIsSet(boolean z) {
        if (!z) {
            this.preauthCode = null;
        }
    }

    public APConnectInfo setPriv_key(String str) {
        this.priv_key = str;
        return this;
    }

    public void setPriv_keyIsSet(boolean z) {
        if (!z) {
            this.priv_key = null;
        }
    }

    public APConnectInfo setSmethod(SecurityMethod securityMethod) {
        this.smethod = securityMethod;
        return this;
    }

    public void setSmethodIsSet(boolean z) {
        if (!z) {
            this.smethod = null;
        }
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("APConnectInfo(", "essid:");
        String str = this.essid;
        if (str == null) {
            outline112.append("null");
        } else {
            outline112.append(str);
        }
        outline112.append(", ");
        outline112.append("smethod:");
        SecurityMethod securityMethod = this.smethod;
        if (securityMethod == null) {
            outline112.append("null");
        } else {
            outline112.append(securityMethod);
        }
        outline112.append(", ");
        outline112.append("password:");
        PKCS7Type pKCS7Type = this.password;
        if (pKCS7Type == null) {
            outline112.append("null");
        } else {
            outline112.append(pKCS7Type);
        }
        outline112.append(", ");
        outline112.append("eapmethod:");
        String str2 = this.eapmethod;
        if (str2 == null) {
            outline112.append("null");
        } else {
            outline112.append(str2);
        }
        outline112.append(", ");
        outline112.append("identity:");
        String str3 = this.identity;
        if (str3 == null) {
            outline112.append("null");
        } else {
            outline112.append(str3);
        }
        outline112.append(", ");
        outline112.append("client_cert:");
        String str4 = this.client_cert;
        if (str4 == null) {
            outline112.append("null");
        } else {
            outline112.append(str4);
        }
        outline112.append(", ");
        outline112.append("priv_key:");
        String str5 = this.priv_key;
        if (str5 == null) {
            outline112.append("null");
        } else {
            outline112.append(str5);
        }
        if (isSetPreauthCode()) {
            outline112.append(", ");
            outline112.append("preauthCode:");
            PKCS7Type pKCS7Type2 = this.preauthCode;
            if (pKCS7Type2 == null) {
                outline112.append("null");
            } else {
                outline112.append(pKCS7Type2);
            }
        }
        if (isSetIsTetheredNetwork()) {
            outline112.append(", ");
            outline112.append("isTetheredNetwork:");
            outline112.append(this.isTetheredNetwork);
        }
        outline112.append(")");
        return outline112.toString();
    }

    public void unsetClient_cert() {
        this.client_cert = null;
    }

    public void unsetEapmethod() {
        this.eapmethod = null;
    }

    public void unsetEssid() {
        this.essid = null;
    }

    public void unsetIdentity() {
        this.identity = null;
    }

    public void unsetIsTetheredNetwork() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
    }

    public void unsetPassword() {
        this.password = null;
    }

    public void unsetPreauthCode() {
        this.preauthCode = null;
    }

    public void unsetPriv_key() {
        this.priv_key = null;
    }

    public void unsetSmethod() {
        this.smethod = null;
    }

    public void validate() throws TException {
        PKCS7Type pKCS7Type = this.password;
        if (pKCS7Type != null) {
            pKCS7Type.validate();
        }
        PKCS7Type pKCS7Type2 = this.preauthCode;
        if (pKCS7Type2 != null) {
            pKCS7Type2.validate();
        }
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
