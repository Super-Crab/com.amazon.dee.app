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
import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.TBaseHelper;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.TFieldIdEnum;
import org.apache.thrift.orig.meta_data.FieldMetaData;
import org.apache.thrift.orig.meta_data.FieldValueMetaData;
import org.apache.thrift.orig.meta_data.ListMetaData;
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
public class APConnectExtendedInfo implements TBase<APConnectExtendedInfo, _Fields>, Serializable, Cloneable {
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public List<String> dns;
    public String gateway;
    public String ipaddress;
    public String subnet;
    private static final TStruct STRUCT_DESC = new TStruct("APConnectExtendedInfo");
    private static final TField IPADDRESS_FIELD_DESC = new TField("ipaddress", (byte) 11, 1);
    private static final TField SUBNET_FIELD_DESC = new TField("subnet", (byte) 11, 2);
    private static final TField GATEWAY_FIELD_DESC = new TField("gateway", (byte) 11, 3);
    private static final TField DNS_FIELD_DESC = new TField("dns", (byte) 15, 4);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.APConnectExtendedInfo$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$APConnectExtendedInfo$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectExtendedInfo$_Fields[_Fields.IPADDRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectExtendedInfo$_Fields[_Fields.SUBNET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectExtendedInfo$_Fields[_Fields.GATEWAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$APConnectExtendedInfo$_Fields[_Fields.DNS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class APConnectExtendedInfoStandardScheme extends StandardScheme<APConnectExtendedInfo> {
        private APConnectExtendedInfoStandardScheme() {
        }

        /* synthetic */ APConnectExtendedInfoStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, APConnectExtendedInfo aPConnectExtendedInfo) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    aPConnectExtendedInfo.validate();
                    return;
                }
                short s = readFieldBegin.id;
                if (s == 1) {
                    if (b == 11) {
                        aPConnectExtendedInfo.ipaddress = tProtocol.readString();
                        aPConnectExtendedInfo.setIpaddressIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                } else if (s == 2) {
                    if (b == 11) {
                        aPConnectExtendedInfo.subnet = tProtocol.readString();
                        aPConnectExtendedInfo.setSubnetIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                } else if (s != 3) {
                    if (s == 4 && b == 15) {
                        TList readListBegin = tProtocol.readListBegin();
                        aPConnectExtendedInfo.dns = new ArrayList(readListBegin.size);
                        for (int i = 0; i < readListBegin.size; i++) {
                            aPConnectExtendedInfo.dns.add(tProtocol.readString());
                        }
                        tProtocol.readListEnd();
                        aPConnectExtendedInfo.setDnsIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                } else {
                    if (b == 11) {
                        aPConnectExtendedInfo.gateway = tProtocol.readString();
                        aPConnectExtendedInfo.setGatewayIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, APConnectExtendedInfo aPConnectExtendedInfo) throws TException {
            aPConnectExtendedInfo.validate();
            tProtocol.writeStructBegin(APConnectExtendedInfo.STRUCT_DESC);
            if (aPConnectExtendedInfo.ipaddress != null) {
                tProtocol.writeFieldBegin(APConnectExtendedInfo.IPADDRESS_FIELD_DESC);
                tProtocol.writeString(aPConnectExtendedInfo.ipaddress);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectExtendedInfo.subnet != null) {
                tProtocol.writeFieldBegin(APConnectExtendedInfo.SUBNET_FIELD_DESC);
                tProtocol.writeString(aPConnectExtendedInfo.subnet);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectExtendedInfo.gateway != null) {
                tProtocol.writeFieldBegin(APConnectExtendedInfo.GATEWAY_FIELD_DESC);
                tProtocol.writeString(aPConnectExtendedInfo.gateway);
                tProtocol.writeFieldEnd();
            }
            if (aPConnectExtendedInfo.dns != null) {
                tProtocol.writeFieldBegin(APConnectExtendedInfo.DNS_FIELD_DESC);
                tProtocol.writeListBegin(new TList((byte) 11, aPConnectExtendedInfo.dns.size()));
                for (String str : aPConnectExtendedInfo.dns) {
                    tProtocol.writeString(str);
                }
                tProtocol.writeListEnd();
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class APConnectExtendedInfoStandardSchemeFactory implements SchemeFactory {
        private APConnectExtendedInfoStandardSchemeFactory() {
        }

        /* synthetic */ APConnectExtendedInfoStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public APConnectExtendedInfoStandardScheme mo12846getScheme() {
            return new APConnectExtendedInfoStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class APConnectExtendedInfoTupleScheme extends TupleScheme<APConnectExtendedInfo> {
        private APConnectExtendedInfoTupleScheme() {
        }

        /* synthetic */ APConnectExtendedInfoTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, APConnectExtendedInfo aPConnectExtendedInfo) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet readBitSet = tTupleProtocol.readBitSet(4);
            if (readBitSet.get(0)) {
                aPConnectExtendedInfo.ipaddress = tTupleProtocol.readString();
                aPConnectExtendedInfo.setIpaddressIsSet(true);
            }
            if (readBitSet.get(1)) {
                aPConnectExtendedInfo.subnet = tTupleProtocol.readString();
                aPConnectExtendedInfo.setSubnetIsSet(true);
            }
            if (readBitSet.get(2)) {
                aPConnectExtendedInfo.gateway = tTupleProtocol.readString();
                aPConnectExtendedInfo.setGatewayIsSet(true);
            }
            if (readBitSet.get(3)) {
                TList tList = new TList((byte) 11, tTupleProtocol.readI32());
                aPConnectExtendedInfo.dns = new ArrayList(tList.size);
                for (int i = 0; i < tList.size; i++) {
                    aPConnectExtendedInfo.dns.add(tTupleProtocol.readString());
                }
                aPConnectExtendedInfo.setDnsIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, APConnectExtendedInfo aPConnectExtendedInfo) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (aPConnectExtendedInfo.isSetIpaddress()) {
                bitSet.set(0);
            }
            if (aPConnectExtendedInfo.isSetSubnet()) {
                bitSet.set(1);
            }
            if (aPConnectExtendedInfo.isSetGateway()) {
                bitSet.set(2);
            }
            if (aPConnectExtendedInfo.isSetDns()) {
                bitSet.set(3);
            }
            tTupleProtocol.writeBitSet(bitSet, 4);
            if (aPConnectExtendedInfo.isSetIpaddress()) {
                tTupleProtocol.writeString(aPConnectExtendedInfo.ipaddress);
            }
            if (aPConnectExtendedInfo.isSetSubnet()) {
                tTupleProtocol.writeString(aPConnectExtendedInfo.subnet);
            }
            if (aPConnectExtendedInfo.isSetGateway()) {
                tTupleProtocol.writeString(aPConnectExtendedInfo.gateway);
            }
            if (aPConnectExtendedInfo.isSetDns()) {
                tTupleProtocol.writeI32(aPConnectExtendedInfo.dns.size());
                for (String str : aPConnectExtendedInfo.dns) {
                    tTupleProtocol.writeString(str);
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class APConnectExtendedInfoTupleSchemeFactory implements SchemeFactory {
        private APConnectExtendedInfoTupleSchemeFactory() {
        }

        /* synthetic */ APConnectExtendedInfoTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public APConnectExtendedInfoTupleScheme mo12846getScheme() {
            return new APConnectExtendedInfoTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        IPADDRESS(1, "ipaddress"),
        SUBNET(2, "subnet"),
        GATEWAY(3, "gateway"),
        DNS(4, "dns");
        
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
            if (i != 1) {
                if (i == 2) {
                    return SUBNET;
                }
                if (i == 3) {
                    return GATEWAY;
                }
                if (i == 4) {
                    return DNS;
                }
                return null;
            }
            return IPADDRESS;
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
        schemes.put(StandardScheme.class, new APConnectExtendedInfoStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new APConnectExtendedInfoTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.IPADDRESS, (_Fields) new FieldMetaData("ipaddress", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.SUBNET, (_Fields) new FieldMetaData("subnet", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.GATEWAY, (_Fields) new FieldMetaData("gateway", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.DNS, (_Fields) new FieldMetaData("dns", (byte) 3, new ListMetaData((byte) 15, new FieldValueMetaData((byte) 11))));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(APConnectExtendedInfo.class, metaDataMap);
    }

    public APConnectExtendedInfo() {
    }

    public APConnectExtendedInfo(APConnectExtendedInfo aPConnectExtendedInfo) {
        if (aPConnectExtendedInfo.isSetIpaddress()) {
            this.ipaddress = aPConnectExtendedInfo.ipaddress;
        }
        if (aPConnectExtendedInfo.isSetSubnet()) {
            this.subnet = aPConnectExtendedInfo.subnet;
        }
        if (aPConnectExtendedInfo.isSetGateway()) {
            this.gateway = aPConnectExtendedInfo.gateway;
        }
        if (aPConnectExtendedInfo.isSetDns()) {
            ArrayList arrayList = new ArrayList();
            for (String str : aPConnectExtendedInfo.dns) {
                arrayList.add(str);
            }
            this.dns = arrayList;
        }
    }

    public APConnectExtendedInfo(String str, String str2, String str3, List<String> list) {
        this();
        this.ipaddress = str;
        this.subnet = str2;
        this.gateway = str3;
        this.dns = list;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
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

    public void addToDns(String str) {
        if (this.dns == null) {
            this.dns = new ArrayList();
        }
        this.dns.add(str);
    }

    @Override // org.apache.thrift.orig.TBase
    public void clear() {
        this.ipaddress = null;
        this.subnet = null;
        this.gateway = null;
        this.dns = null;
    }

    @Override // java.lang.Comparable
    public int compareTo(APConnectExtendedInfo aPConnectExtendedInfo) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        if (!APConnectExtendedInfo.class.equals(aPConnectExtendedInfo.getClass())) {
            return APConnectExtendedInfo.class.getName().compareTo(APConnectExtendedInfo.class.getName());
        }
        int compareTo5 = Boolean.valueOf(isSetIpaddress()).compareTo(Boolean.valueOf(aPConnectExtendedInfo.isSetIpaddress()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (isSetIpaddress() && (compareTo4 = TBaseHelper.compareTo(this.ipaddress, aPConnectExtendedInfo.ipaddress)) != 0) {
            return compareTo4;
        }
        int compareTo6 = Boolean.valueOf(isSetSubnet()).compareTo(Boolean.valueOf(aPConnectExtendedInfo.isSetSubnet()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (isSetSubnet() && (compareTo3 = TBaseHelper.compareTo(this.subnet, aPConnectExtendedInfo.subnet)) != 0) {
            return compareTo3;
        }
        int compareTo7 = Boolean.valueOf(isSetGateway()).compareTo(Boolean.valueOf(aPConnectExtendedInfo.isSetGateway()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (isSetGateway() && (compareTo2 = TBaseHelper.compareTo(this.gateway, aPConnectExtendedInfo.gateway)) != 0) {
            return compareTo2;
        }
        int compareTo8 = Boolean.valueOf(isSetDns()).compareTo(Boolean.valueOf(aPConnectExtendedInfo.isSetDns()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (isSetDns() && (compareTo = TBaseHelper.compareTo((List) this.dns, (List) aPConnectExtendedInfo.dns)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<APConnectExtendedInfo, _Fields> deepCopy2() {
        return new APConnectExtendedInfo(this);
    }

    public boolean equals(APConnectExtendedInfo aPConnectExtendedInfo) {
        if (aPConnectExtendedInfo == null) {
            return false;
        }
        boolean isSetIpaddress = isSetIpaddress();
        boolean isSetIpaddress2 = aPConnectExtendedInfo.isSetIpaddress();
        if ((isSetIpaddress || isSetIpaddress2) && (!isSetIpaddress || !isSetIpaddress2 || !this.ipaddress.equals(aPConnectExtendedInfo.ipaddress))) {
            return false;
        }
        boolean isSetSubnet = isSetSubnet();
        boolean isSetSubnet2 = aPConnectExtendedInfo.isSetSubnet();
        if ((isSetSubnet || isSetSubnet2) && (!isSetSubnet || !isSetSubnet2 || !this.subnet.equals(aPConnectExtendedInfo.subnet))) {
            return false;
        }
        boolean isSetGateway = isSetGateway();
        boolean isSetGateway2 = aPConnectExtendedInfo.isSetGateway();
        if ((isSetGateway || isSetGateway2) && (!isSetGateway || !isSetGateway2 || !this.gateway.equals(aPConnectExtendedInfo.gateway))) {
            return false;
        }
        boolean isSetDns = isSetDns();
        boolean isSetDns2 = aPConnectExtendedInfo.isSetDns();
        if (!isSetDns && !isSetDns2) {
            return true;
        }
        return isSetDns && isSetDns2 && this.dns.equals(aPConnectExtendedInfo.dns);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof APConnectExtendedInfo)) {
            return equals((APConnectExtendedInfo) obj);
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.thrift.orig.TBase
    /* renamed from: fieldForId */
    public _Fields mo3968fieldForId(int i) {
        return _Fields.findByThriftId(i);
    }

    public List<String> getDns() {
        return this.dns;
    }

    public Iterator<String> getDnsIterator() {
        List<String> list = this.dns;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public int getDnsSize() {
        List<String> list = this.dns;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // org.apache.thrift.orig.TBase
    public Object getFieldValue(_Fields _fields) {
        int ordinal = _fields.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return getSubnet();
            }
            if (ordinal == 2) {
                return getGateway();
            }
            if (ordinal != 3) {
                throw new IllegalStateException();
            }
            return getDns();
        }
        return getIpaddress();
    }

    public String getGateway() {
        return this.gateway;
    }

    public String getIpaddress() {
        return this.ipaddress;
    }

    public String getSubnet() {
        return this.subnet;
    }

    public int hashCode() {
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(_Fields _fields) {
        if (_fields != null) {
            int ordinal = _fields.ordinal();
            if (ordinal == 0) {
                return isSetIpaddress();
            }
            if (ordinal == 1) {
                return isSetSubnet();
            }
            if (ordinal == 2) {
                return isSetGateway();
            }
            if (ordinal != 3) {
                throw new IllegalStateException();
            }
            return isSetDns();
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetDns() {
        return this.dns != null;
    }

    public boolean isSetGateway() {
        return this.gateway != null;
    }

    public boolean isSetIpaddress() {
        return this.ipaddress != null;
    }

    public boolean isSetSubnet() {
        return this.subnet != null;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    public APConnectExtendedInfo setDns(List<String> list) {
        this.dns = list;
        return this;
    }

    public void setDnsIsSet(boolean z) {
        if (!z) {
            this.dns = null;
        }
    }

    @Override // org.apache.thrift.orig.TBase
    public void setFieldValue(_Fields _fields, Object obj) {
        int ordinal = _fields.ordinal();
        if (ordinal == 0) {
            if (obj == null) {
                unsetIpaddress();
            } else {
                setIpaddress((String) obj);
            }
        } else if (ordinal == 1) {
            if (obj == null) {
                unsetSubnet();
            } else {
                setSubnet((String) obj);
            }
        } else if (ordinal == 2) {
            if (obj == null) {
                unsetGateway();
            } else {
                setGateway((String) obj);
            }
        } else if (ordinal != 3) {
        } else {
            if (obj == null) {
                unsetDns();
            } else {
                setDns((List) obj);
            }
        }
    }

    public APConnectExtendedInfo setGateway(String str) {
        this.gateway = str;
        return this;
    }

    public void setGatewayIsSet(boolean z) {
        if (!z) {
            this.gateway = null;
        }
    }

    public APConnectExtendedInfo setIpaddress(String str) {
        this.ipaddress = str;
        return this;
    }

    public void setIpaddressIsSet(boolean z) {
        if (!z) {
            this.ipaddress = null;
        }
    }

    public APConnectExtendedInfo setSubnet(String str) {
        this.subnet = str;
        return this;
    }

    public void setSubnetIsSet(boolean z) {
        if (!z) {
            this.subnet = null;
        }
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("APConnectExtendedInfo(", "ipaddress:");
        String str = this.ipaddress;
        if (str == null) {
            outline112.append("null");
        } else {
            outline112.append(str);
        }
        outline112.append(", ");
        outline112.append("subnet:");
        String str2 = this.subnet;
        if (str2 == null) {
            outline112.append("null");
        } else {
            outline112.append(str2);
        }
        outline112.append(", ");
        outline112.append("gateway:");
        String str3 = this.gateway;
        if (str3 == null) {
            outline112.append("null");
        } else {
            outline112.append(str3);
        }
        outline112.append(", ");
        outline112.append("dns:");
        List<String> list = this.dns;
        if (list == null) {
            outline112.append("null");
        } else {
            outline112.append(list);
        }
        outline112.append(")");
        return outline112.toString();
    }

    public void unsetDns() {
        this.dns = null;
    }

    public void unsetGateway() {
        this.gateway = null;
    }

    public void unsetIpaddress() {
        this.ipaddress = null;
    }

    public void unsetSubnet() {
        this.subnet = null;
    }

    public void validate() throws TException {
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
