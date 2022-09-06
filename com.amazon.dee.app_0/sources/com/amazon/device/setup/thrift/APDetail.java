package com.amazon.device.setup.thrift;

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
import org.apache.thrift.orig.meta_data.EnumMetaData;
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
public class APDetail implements TBase<APDetail, _Fields>, Serializable, Cloneable {
    private static final int __ISTETHEREDNETWORK_ISSET_ID = 3;
    private static final int __SECURED_ISSET_ID = 1;
    private static final int __SIGNAL_ISSET_ID = 0;
    private static final int __SUPPORTED_ISSET_ID = 2;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private byte __isset_bitfield;
    public String essid;
    public boolean isTetheredNetwork;
    public boolean secured;
    public int signal;
    public SecurityMethod smethod;
    public ConnectionState state;
    public boolean supported;
    private static final TStruct STRUCT_DESC = new TStruct("APDetail");
    private static final TField ESSID_FIELD_DESC = new TField("essid", (byte) 11, 1);
    private static final TField SIGNAL_FIELD_DESC = new TField("signal", (byte) 8, 2);
    private static final TField SECURED_FIELD_DESC = new TField("secured", (byte) 2, 3);
    private static final TField SMETHOD_FIELD_DESC = new TField("smethod", (byte) 8, 4);
    private static final TField SUPPORTED_FIELD_DESC = new TField("supported", (byte) 2, 5);
    private static final TField STATE_FIELD_DESC = new TField("state", (byte) 8, 6);
    private static final TField IS_TETHERED_NETWORK_FIELD_DESC = new TField("isTetheredNetwork", (byte) 2, 7);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.APDetail$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$APDetail$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APDetail$_Fields[_Fields.ESSID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APDetail$_Fields[_Fields.SIGNAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APDetail$_Fields[_Fields.SECURED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APDetail$_Fields[_Fields.SMETHOD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APDetail$_Fields[_Fields.SUPPORTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APDetail$_Fields[_Fields.STATE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APDetail$_Fields[_Fields.IS_TETHERED_NETWORK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class APDetailStandardScheme extends StandardScheme<APDetail> {
        private APDetailStandardScheme() {
        }

        /* synthetic */ APDetailStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, APDetail aPDetail) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    aPDetail.validate();
                    return;
                }
                switch (readFieldBegin.id) {
                    case 1:
                        if (b == 11) {
                            aPDetail.essid = tProtocol.readString();
                            aPDetail.setEssidIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 2:
                        if (b == 8) {
                            aPDetail.signal = tProtocol.readI32();
                            aPDetail.setSignalIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 3:
                        if (b == 2) {
                            aPDetail.secured = tProtocol.readBool();
                            aPDetail.setSecuredIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 4:
                        if (b == 8) {
                            aPDetail.smethod = SecurityMethod.findByValue(tProtocol.readI32());
                            aPDetail.setSmethodIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 5:
                        if (b == 2) {
                            aPDetail.supported = tProtocol.readBool();
                            aPDetail.setSupportedIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 6:
                        if (b == 8) {
                            aPDetail.state = ConnectionState.findByValue(tProtocol.readI32());
                            aPDetail.setStateIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 7:
                        if (b == 2) {
                            aPDetail.isTetheredNetwork = tProtocol.readBool();
                            aPDetail.setIsTetheredNetworkIsSet(true);
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
        public void write(TProtocol tProtocol, APDetail aPDetail) throws TException {
            aPDetail.validate();
            tProtocol.writeStructBegin(APDetail.STRUCT_DESC);
            if (aPDetail.essid != null) {
                tProtocol.writeFieldBegin(APDetail.ESSID_FIELD_DESC);
                tProtocol.writeString(aPDetail.essid);
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldBegin(APDetail.SIGNAL_FIELD_DESC);
            tProtocol.writeI32(aPDetail.signal);
            tProtocol.writeFieldEnd();
            tProtocol.writeFieldBegin(APDetail.SECURED_FIELD_DESC);
            tProtocol.writeBool(aPDetail.secured);
            tProtocol.writeFieldEnd();
            if (aPDetail.smethod != null) {
                tProtocol.writeFieldBegin(APDetail.SMETHOD_FIELD_DESC);
                tProtocol.writeI32(aPDetail.smethod.getValue());
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldBegin(APDetail.SUPPORTED_FIELD_DESC);
            tProtocol.writeBool(aPDetail.supported);
            tProtocol.writeFieldEnd();
            if (aPDetail.state != null) {
                tProtocol.writeFieldBegin(APDetail.STATE_FIELD_DESC);
                tProtocol.writeI32(aPDetail.state.getValue());
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldBegin(APDetail.IS_TETHERED_NETWORK_FIELD_DESC);
            tProtocol.writeBool(aPDetail.isTetheredNetwork);
            tProtocol.writeFieldEnd();
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class APDetailStandardSchemeFactory implements SchemeFactory {
        private APDetailStandardSchemeFactory() {
        }

        /* synthetic */ APDetailStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public APDetailStandardScheme mo12846getScheme() {
            return new APDetailStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class APDetailTupleScheme extends TupleScheme<APDetail> {
        private APDetailTupleScheme() {
        }

        /* synthetic */ APDetailTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, APDetail aPDetail) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet readBitSet = tTupleProtocol.readBitSet(7);
            if (readBitSet.get(0)) {
                aPDetail.essid = tTupleProtocol.readString();
                aPDetail.setEssidIsSet(true);
            }
            if (readBitSet.get(1)) {
                aPDetail.signal = tTupleProtocol.readI32();
                aPDetail.setSignalIsSet(true);
            }
            if (readBitSet.get(2)) {
                aPDetail.secured = tTupleProtocol.readBool();
                aPDetail.setSecuredIsSet(true);
            }
            if (readBitSet.get(3)) {
                aPDetail.smethod = SecurityMethod.findByValue(tTupleProtocol.readI32());
                aPDetail.setSmethodIsSet(true);
            }
            if (readBitSet.get(4)) {
                aPDetail.supported = tTupleProtocol.readBool();
                aPDetail.setSupportedIsSet(true);
            }
            if (readBitSet.get(5)) {
                aPDetail.state = ConnectionState.findByValue(tTupleProtocol.readI32());
                aPDetail.setStateIsSet(true);
            }
            if (readBitSet.get(6)) {
                aPDetail.isTetheredNetwork = tTupleProtocol.readBool();
                aPDetail.setIsTetheredNetworkIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, APDetail aPDetail) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (aPDetail.isSetEssid()) {
                bitSet.set(0);
            }
            if (aPDetail.isSetSignal()) {
                bitSet.set(1);
            }
            if (aPDetail.isSetSecured()) {
                bitSet.set(2);
            }
            if (aPDetail.isSetSmethod()) {
                bitSet.set(3);
            }
            if (aPDetail.isSetSupported()) {
                bitSet.set(4);
            }
            if (aPDetail.isSetState()) {
                bitSet.set(5);
            }
            if (aPDetail.isSetIsTetheredNetwork()) {
                bitSet.set(6);
            }
            tTupleProtocol.writeBitSet(bitSet, 7);
            if (aPDetail.isSetEssid()) {
                tTupleProtocol.writeString(aPDetail.essid);
            }
            if (aPDetail.isSetSignal()) {
                tTupleProtocol.writeI32(aPDetail.signal);
            }
            if (aPDetail.isSetSecured()) {
                tTupleProtocol.writeBool(aPDetail.secured);
            }
            if (aPDetail.isSetSmethod()) {
                tTupleProtocol.writeI32(aPDetail.smethod.getValue());
            }
            if (aPDetail.isSetSupported()) {
                tTupleProtocol.writeBool(aPDetail.supported);
            }
            if (aPDetail.isSetState()) {
                tTupleProtocol.writeI32(aPDetail.state.getValue());
            }
            if (aPDetail.isSetIsTetheredNetwork()) {
                tTupleProtocol.writeBool(aPDetail.isTetheredNetwork);
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class APDetailTupleSchemeFactory implements SchemeFactory {
        private APDetailTupleSchemeFactory() {
        }

        /* synthetic */ APDetailTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public APDetailTupleScheme mo12846getScheme() {
            return new APDetailTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        ESSID(1, "essid"),
        SIGNAL(2, "signal"),
        SECURED(3, "secured"),
        SMETHOD(4, "smethod"),
        SUPPORTED(5, "supported"),
        STATE(6, "state"),
        IS_TETHERED_NETWORK(7, "isTetheredNetwork");
        
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
                    return SIGNAL;
                case 3:
                    return SECURED;
                case 4:
                    return SMETHOD;
                case 5:
                    return SUPPORTED;
                case 6:
                    return STATE;
                case 7:
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
        schemes.put(StandardScheme.class, new APDetailStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new APDetailTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.ESSID, (_Fields) new FieldMetaData("essid", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.SIGNAL, (_Fields) new FieldMetaData("signal", (byte) 3, new FieldValueMetaData((byte) 8)));
        enumMap.put((EnumMap) _Fields.SECURED, (_Fields) new FieldMetaData("secured", (byte) 3, new FieldValueMetaData((byte) 2)));
        enumMap.put((EnumMap) _Fields.SMETHOD, (_Fields) new FieldMetaData("smethod", (byte) 3, new EnumMetaData((byte) 16, SecurityMethod.class)));
        enumMap.put((EnumMap) _Fields.SUPPORTED, (_Fields) new FieldMetaData("supported", (byte) 3, new FieldValueMetaData((byte) 2)));
        enumMap.put((EnumMap) _Fields.STATE, (_Fields) new FieldMetaData("state", (byte) 3, new EnumMetaData((byte) 16, ConnectionState.class)));
        enumMap.put((EnumMap) _Fields.IS_TETHERED_NETWORK, (_Fields) new FieldMetaData("isTetheredNetwork", (byte) 3, new FieldValueMetaData((byte) 2)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(APDetail.class, metaDataMap);
    }

    public APDetail() {
        this.__isset_bitfield = (byte) 0;
    }

    public APDetail(APDetail aPDetail) {
        this.__isset_bitfield = (byte) 0;
        this.__isset_bitfield = aPDetail.__isset_bitfield;
        if (aPDetail.isSetEssid()) {
            this.essid = aPDetail.essid;
        }
        this.signal = aPDetail.signal;
        this.secured = aPDetail.secured;
        if (aPDetail.isSetSmethod()) {
            this.smethod = aPDetail.smethod;
        }
        this.supported = aPDetail.supported;
        if (aPDetail.isSetState()) {
            this.state = aPDetail.state;
        }
        this.isTetheredNetwork = aPDetail.isTetheredNetwork;
    }

    public APDetail(String str, int i, boolean z, SecurityMethod securityMethod, boolean z2, ConnectionState connectionState, boolean z3) {
        this();
        this.essid = str;
        this.signal = i;
        setSignalIsSet(true);
        this.secured = z;
        setSecuredIsSet(true);
        this.smethod = securityMethod;
        this.supported = z2;
        setSupportedIsSet(true);
        this.state = connectionState;
        this.isTetheredNetwork = z3;
        setIsTetheredNetworkIsSet(true);
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
        setSignalIsSet(false);
        this.signal = 0;
        setSecuredIsSet(false);
        this.secured = false;
        this.smethod = null;
        setSupportedIsSet(false);
        this.supported = false;
        this.state = null;
        setIsTetheredNetworkIsSet(false);
        this.isTetheredNetwork = false;
    }

    @Override // java.lang.Comparable
    public int compareTo(APDetail aPDetail) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        if (!APDetail.class.equals(aPDetail.getClass())) {
            return APDetail.class.getName().compareTo(APDetail.class.getName());
        }
        int compareTo8 = Boolean.valueOf(isSetEssid()).compareTo(Boolean.valueOf(aPDetail.isSetEssid()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (isSetEssid() && (compareTo7 = TBaseHelper.compareTo(this.essid, aPDetail.essid)) != 0) {
            return compareTo7;
        }
        int compareTo9 = Boolean.valueOf(isSetSignal()).compareTo(Boolean.valueOf(aPDetail.isSetSignal()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (isSetSignal() && (compareTo6 = TBaseHelper.compareTo(this.signal, aPDetail.signal)) != 0) {
            return compareTo6;
        }
        int compareTo10 = Boolean.valueOf(isSetSecured()).compareTo(Boolean.valueOf(aPDetail.isSetSecured()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (isSetSecured() && (compareTo5 = TBaseHelper.compareTo(this.secured, aPDetail.secured)) != 0) {
            return compareTo5;
        }
        int compareTo11 = Boolean.valueOf(isSetSmethod()).compareTo(Boolean.valueOf(aPDetail.isSetSmethod()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetSmethod() && (compareTo4 = TBaseHelper.compareTo((Comparable) this.smethod, (Comparable) aPDetail.smethod)) != 0) {
            return compareTo4;
        }
        int compareTo12 = Boolean.valueOf(isSetSupported()).compareTo(Boolean.valueOf(aPDetail.isSetSupported()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetSupported() && (compareTo3 = TBaseHelper.compareTo(this.supported, aPDetail.supported)) != 0) {
            return compareTo3;
        }
        int compareTo13 = Boolean.valueOf(isSetState()).compareTo(Boolean.valueOf(aPDetail.isSetState()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetState() && (compareTo2 = TBaseHelper.compareTo((Comparable) this.state, (Comparable) aPDetail.state)) != 0) {
            return compareTo2;
        }
        int compareTo14 = Boolean.valueOf(isSetIsTetheredNetwork()).compareTo(Boolean.valueOf(aPDetail.isSetIsTetheredNetwork()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetIsTetheredNetwork() && (compareTo = TBaseHelper.compareTo(this.isTetheredNetwork, aPDetail.isTetheredNetwork)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<APDetail, _Fields> deepCopy2() {
        return new APDetail(this);
    }

    public boolean equals(APDetail aPDetail) {
        if (aPDetail == null) {
            return false;
        }
        boolean isSetEssid = isSetEssid();
        boolean isSetEssid2 = aPDetail.isSetEssid();
        if (((isSetEssid || isSetEssid2) && (!isSetEssid || !isSetEssid2 || !this.essid.equals(aPDetail.essid))) || this.signal != aPDetail.signal || this.secured != aPDetail.secured) {
            return false;
        }
        boolean isSetSmethod = isSetSmethod();
        boolean isSetSmethod2 = aPDetail.isSetSmethod();
        if (((isSetSmethod || isSetSmethod2) && (!isSetSmethod || !isSetSmethod2 || !this.smethod.equals(aPDetail.smethod))) || this.supported != aPDetail.supported) {
            return false;
        }
        boolean isSetState = isSetState();
        boolean isSetState2 = aPDetail.isSetState();
        return ((!isSetState && !isSetState2) || (isSetState && isSetState2 && this.state.equals(aPDetail.state))) && this.isTetheredNetwork == aPDetail.isTetheredNetwork;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof APDetail)) {
            return equals((APDetail) obj);
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.thrift.orig.TBase
    /* renamed from: fieldForId */
    public _Fields mo3968fieldForId(int i) {
        return _Fields.findByThriftId(i);
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
                return Integer.valueOf(getSignal());
            case 2:
                return Boolean.valueOf(isSecured());
            case 3:
                return getSmethod();
            case 4:
                return Boolean.valueOf(isSupported());
            case 5:
                return getState();
            case 6:
                return Boolean.valueOf(isIsTetheredNetwork());
            default:
                throw new IllegalStateException();
        }
    }

    public int getSignal() {
        return this.signal;
    }

    public SecurityMethod getSmethod() {
        return this.smethod;
    }

    public ConnectionState getState() {
        return this.state;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isIsTetheredNetwork() {
        return this.isTetheredNetwork;
    }

    public boolean isSecured() {
        return this.secured;
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(_Fields _fields) {
        if (_fields != null) {
            switch (_fields.ordinal()) {
                case 0:
                    return isSetEssid();
                case 1:
                    return isSetSignal();
                case 2:
                    return isSetSecured();
                case 3:
                    return isSetSmethod();
                case 4:
                    return isSetSupported();
                case 5:
                    return isSetState();
                case 6:
                    return isSetIsTetheredNetwork();
                default:
                    throw new IllegalStateException();
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetEssid() {
        return this.essid != null;
    }

    public boolean isSetIsTetheredNetwork() {
        return EncodingUtils.testBit(this.__isset_bitfield, 3);
    }

    public boolean isSetSecured() {
        return EncodingUtils.testBit(this.__isset_bitfield, 1);
    }

    public boolean isSetSignal() {
        return EncodingUtils.testBit(this.__isset_bitfield, 0);
    }

    public boolean isSetSmethod() {
        return this.smethod != null;
    }

    public boolean isSetState() {
        return this.state != null;
    }

    public boolean isSetSupported() {
        return EncodingUtils.testBit(this.__isset_bitfield, 2);
    }

    public boolean isSupported() {
        return this.supported;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    public APDetail setEssid(String str) {
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
                    unsetSignal();
                    return;
                } else {
                    setSignal(((Integer) obj).intValue());
                    return;
                }
            case 2:
                if (obj == null) {
                    unsetSecured();
                    return;
                } else {
                    setSecured(((Boolean) obj).booleanValue());
                    return;
                }
            case 3:
                if (obj == null) {
                    unsetSmethod();
                    return;
                } else {
                    setSmethod((SecurityMethod) obj);
                    return;
                }
            case 4:
                if (obj == null) {
                    unsetSupported();
                    return;
                } else {
                    setSupported(((Boolean) obj).booleanValue());
                    return;
                }
            case 5:
                if (obj == null) {
                    unsetState();
                    return;
                } else {
                    setState((ConnectionState) obj);
                    return;
                }
            case 6:
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

    public APDetail setIsTetheredNetwork(boolean z) {
        this.isTetheredNetwork = z;
        setIsTetheredNetworkIsSet(true);
        return this;
    }

    public void setIsTetheredNetworkIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, z);
    }

    public APDetail setSecured(boolean z) {
        this.secured = z;
        setSecuredIsSet(true);
        return this;
    }

    public void setSecuredIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, z);
    }

    public APDetail setSignal(int i) {
        this.signal = i;
        setSignalIsSet(true);
        return this;
    }

    public void setSignalIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, z);
    }

    public APDetail setSmethod(SecurityMethod securityMethod) {
        this.smethod = securityMethod;
        return this;
    }

    public void setSmethodIsSet(boolean z) {
        if (!z) {
            this.smethod = null;
        }
    }

    public APDetail setState(ConnectionState connectionState) {
        this.state = connectionState;
        return this;
    }

    public void setStateIsSet(boolean z) {
        if (!z) {
            this.state = null;
        }
    }

    public APDetail setSupported(boolean z) {
        this.supported = z;
        setSupportedIsSet(true);
        return this;
    }

    public void setSupportedIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, z);
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("APDetail(", "essid:");
        String str = this.essid;
        if (str == null) {
            outline112.append("null");
        } else {
            outline112.append(str);
        }
        outline112.append(", ");
        outline112.append("signal:");
        outline112.append(this.signal);
        outline112.append(", ");
        outline112.append("secured:");
        outline112.append(this.secured);
        outline112.append(", ");
        outline112.append("smethod:");
        SecurityMethod securityMethod = this.smethod;
        if (securityMethod == null) {
            outline112.append("null");
        } else {
            outline112.append(securityMethod);
        }
        outline112.append(", ");
        outline112.append("supported:");
        outline112.append(this.supported);
        outline112.append(", ");
        outline112.append("state:");
        ConnectionState connectionState = this.state;
        if (connectionState == null) {
            outline112.append("null");
        } else {
            outline112.append(connectionState);
        }
        outline112.append(", ");
        outline112.append("isTetheredNetwork:");
        return GeneratedOutlineSupport1.outline97(outline112, this.isTetheredNetwork, ")");
    }

    public void unsetEssid() {
        this.essid = null;
    }

    public void unsetIsTetheredNetwork() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
    }

    public void unsetSecured() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
    }

    public void unsetSignal() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
    }

    public void unsetSmethod() {
        this.smethod = null;
    }

    public void unsetState() {
        this.state = null;
    }

    public void unsetSupported() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
    }

    public void validate() throws TException {
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
