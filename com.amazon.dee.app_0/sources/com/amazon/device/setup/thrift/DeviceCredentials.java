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
import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.TBaseHelper;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.TFieldIdEnum;
import org.apache.thrift.orig.meta_data.EnumMetaData;
import org.apache.thrift.orig.meta_data.FieldMetaData;
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
public class DeviceCredentials implements TBase<DeviceCredentials, _Fields>, Serializable, Cloneable {
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public LinkCodeSource codeSource;
    public ReturnError errorStatus;
    public PKCS7Type oauthToken;
    private _Fields[] optionals;
    private static final TStruct STRUCT_DESC = new TStruct("DeviceCredentials");
    private static final TField OAUTH_TOKEN_FIELD_DESC = new TField("oauthToken", (byte) 12, 1);
    private static final TField ERROR_STATUS_FIELD_DESC = new TField("errorStatus", (byte) 8, 2);
    private static final TField CODE_SOURCE_FIELD_DESC = new TField("codeSource", (byte) 8, 3);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.DeviceCredentials$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$DeviceCredentials$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceCredentials$_Fields[_Fields.OAUTH_TOKEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceCredentials$_Fields[_Fields.ERROR_STATUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$DeviceCredentials$_Fields[_Fields.CODE_SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class DeviceCredentialsStandardScheme extends StandardScheme<DeviceCredentials> {
        private DeviceCredentialsStandardScheme() {
        }

        /* synthetic */ DeviceCredentialsStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, DeviceCredentials deviceCredentials) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    deviceCredentials.validate();
                    return;
                }
                short s = readFieldBegin.id;
                if (s == 1) {
                    if (b == 12) {
                        deviceCredentials.oauthToken = new PKCS7Type();
                        deviceCredentials.oauthToken.read(tProtocol);
                        deviceCredentials.setOauthTokenIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                } else if (s != 2) {
                    if (s == 3 && b == 8) {
                        deviceCredentials.codeSource = LinkCodeSource.findByValue(tProtocol.readI32());
                        deviceCredentials.setCodeSourceIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                } else {
                    if (b == 8) {
                        deviceCredentials.errorStatus = ReturnError.findByValue(tProtocol.readI32());
                        deviceCredentials.setErrorStatusIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, DeviceCredentials deviceCredentials) throws TException {
            deviceCredentials.validate();
            tProtocol.writeStructBegin(DeviceCredentials.STRUCT_DESC);
            if (deviceCredentials.oauthToken != null) {
                tProtocol.writeFieldBegin(DeviceCredentials.OAUTH_TOKEN_FIELD_DESC);
                deviceCredentials.oauthToken.write(tProtocol);
                tProtocol.writeFieldEnd();
            }
            if (deviceCredentials.errorStatus != null) {
                tProtocol.writeFieldBegin(DeviceCredentials.ERROR_STATUS_FIELD_DESC);
                tProtocol.writeI32(deviceCredentials.errorStatus.getValue());
                tProtocol.writeFieldEnd();
            }
            if (deviceCredentials.codeSource != null && deviceCredentials.isSetCodeSource()) {
                tProtocol.writeFieldBegin(DeviceCredentials.CODE_SOURCE_FIELD_DESC);
                tProtocol.writeI32(deviceCredentials.codeSource.getValue());
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class DeviceCredentialsStandardSchemeFactory implements SchemeFactory {
        private DeviceCredentialsStandardSchemeFactory() {
        }

        /* synthetic */ DeviceCredentialsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public DeviceCredentialsStandardScheme mo12846getScheme() {
            return new DeviceCredentialsStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class DeviceCredentialsTupleScheme extends TupleScheme<DeviceCredentials> {
        private DeviceCredentialsTupleScheme() {
        }

        /* synthetic */ DeviceCredentialsTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, DeviceCredentials deviceCredentials) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet readBitSet = tTupleProtocol.readBitSet(3);
            if (readBitSet.get(0)) {
                deviceCredentials.oauthToken = new PKCS7Type();
                deviceCredentials.oauthToken.read(tTupleProtocol);
                deviceCredentials.setOauthTokenIsSet(true);
            }
            if (readBitSet.get(1)) {
                deviceCredentials.errorStatus = ReturnError.findByValue(tTupleProtocol.readI32());
                deviceCredentials.setErrorStatusIsSet(true);
            }
            if (readBitSet.get(2)) {
                deviceCredentials.codeSource = LinkCodeSource.findByValue(tTupleProtocol.readI32());
                deviceCredentials.setCodeSourceIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, DeviceCredentials deviceCredentials) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (deviceCredentials.isSetOauthToken()) {
                bitSet.set(0);
            }
            if (deviceCredentials.isSetErrorStatus()) {
                bitSet.set(1);
            }
            if (deviceCredentials.isSetCodeSource()) {
                bitSet.set(2);
            }
            tTupleProtocol.writeBitSet(bitSet, 3);
            if (deviceCredentials.isSetOauthToken()) {
                deviceCredentials.oauthToken.write(tTupleProtocol);
            }
            if (deviceCredentials.isSetErrorStatus()) {
                tTupleProtocol.writeI32(deviceCredentials.errorStatus.getValue());
            }
            if (deviceCredentials.isSetCodeSource()) {
                tTupleProtocol.writeI32(deviceCredentials.codeSource.getValue());
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class DeviceCredentialsTupleSchemeFactory implements SchemeFactory {
        private DeviceCredentialsTupleSchemeFactory() {
        }

        /* synthetic */ DeviceCredentialsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public DeviceCredentialsTupleScheme mo12846getScheme() {
            return new DeviceCredentialsTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        OAUTH_TOKEN(1, "oauthToken"),
        ERROR_STATUS(2, "errorStatus"),
        CODE_SOURCE(3, "codeSource");
        
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
                    return ERROR_STATUS;
                }
                if (i == 3) {
                    return CODE_SOURCE;
                }
                return null;
            }
            return OAUTH_TOKEN;
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
        schemes.put(StandardScheme.class, new DeviceCredentialsStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new DeviceCredentialsTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.OAUTH_TOKEN, (_Fields) new FieldMetaData("oauthToken", (byte) 3, new StructMetaData((byte) 12, PKCS7Type.class)));
        enumMap.put((EnumMap) _Fields.ERROR_STATUS, (_Fields) new FieldMetaData("errorStatus", (byte) 3, new EnumMetaData((byte) 16, ReturnError.class)));
        enumMap.put((EnumMap) _Fields.CODE_SOURCE, (_Fields) new FieldMetaData("codeSource", (byte) 2, new EnumMetaData((byte) 16, LinkCodeSource.class)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(DeviceCredentials.class, metaDataMap);
    }

    public DeviceCredentials() {
        this.optionals = new _Fields[]{_Fields.CODE_SOURCE};
    }

    public DeviceCredentials(DeviceCredentials deviceCredentials) {
        this.optionals = new _Fields[]{_Fields.CODE_SOURCE};
        if (deviceCredentials.isSetOauthToken()) {
            this.oauthToken = new PKCS7Type(deviceCredentials.oauthToken);
        }
        if (deviceCredentials.isSetErrorStatus()) {
            this.errorStatus = deviceCredentials.errorStatus;
        }
        if (deviceCredentials.isSetCodeSource()) {
            this.codeSource = deviceCredentials.codeSource;
        }
    }

    public DeviceCredentials(PKCS7Type pKCS7Type, ReturnError returnError) {
        this();
        this.oauthToken = pKCS7Type;
        this.errorStatus = returnError;
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

    @Override // org.apache.thrift.orig.TBase
    public void clear() {
        this.oauthToken = null;
        this.errorStatus = null;
        this.codeSource = null;
    }

    @Override // java.lang.Comparable
    public int compareTo(DeviceCredentials deviceCredentials) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        if (!DeviceCredentials.class.equals(deviceCredentials.getClass())) {
            return DeviceCredentials.class.getName().compareTo(DeviceCredentials.class.getName());
        }
        int compareTo4 = Boolean.valueOf(isSetOauthToken()).compareTo(Boolean.valueOf(deviceCredentials.isSetOauthToken()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (isSetOauthToken() && (compareTo3 = TBaseHelper.compareTo((Comparable) this.oauthToken, (Comparable) deviceCredentials.oauthToken)) != 0) {
            return compareTo3;
        }
        int compareTo5 = Boolean.valueOf(isSetErrorStatus()).compareTo(Boolean.valueOf(deviceCredentials.isSetErrorStatus()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (isSetErrorStatus() && (compareTo2 = TBaseHelper.compareTo((Comparable) this.errorStatus, (Comparable) deviceCredentials.errorStatus)) != 0) {
            return compareTo2;
        }
        int compareTo6 = Boolean.valueOf(isSetCodeSource()).compareTo(Boolean.valueOf(deviceCredentials.isSetCodeSource()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (isSetCodeSource() && (compareTo = TBaseHelper.compareTo((Comparable) this.codeSource, (Comparable) deviceCredentials.codeSource)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<DeviceCredentials, _Fields> deepCopy2() {
        return new DeviceCredentials(this);
    }

    public boolean equals(DeviceCredentials deviceCredentials) {
        if (deviceCredentials == null) {
            return false;
        }
        boolean isSetOauthToken = isSetOauthToken();
        boolean isSetOauthToken2 = deviceCredentials.isSetOauthToken();
        if ((isSetOauthToken || isSetOauthToken2) && (!isSetOauthToken || !isSetOauthToken2 || !this.oauthToken.equals(deviceCredentials.oauthToken))) {
            return false;
        }
        boolean isSetErrorStatus = isSetErrorStatus();
        boolean isSetErrorStatus2 = deviceCredentials.isSetErrorStatus();
        if ((isSetErrorStatus || isSetErrorStatus2) && (!isSetErrorStatus || !isSetErrorStatus2 || !this.errorStatus.equals(deviceCredentials.errorStatus))) {
            return false;
        }
        boolean isSetCodeSource = isSetCodeSource();
        boolean isSetCodeSource2 = deviceCredentials.isSetCodeSource();
        if (!isSetCodeSource && !isSetCodeSource2) {
            return true;
        }
        return isSetCodeSource && isSetCodeSource2 && this.codeSource.equals(deviceCredentials.codeSource);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof DeviceCredentials)) {
            return equals((DeviceCredentials) obj);
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

    public ReturnError getErrorStatus() {
        return this.errorStatus;
    }

    @Override // org.apache.thrift.orig.TBase
    public Object getFieldValue(_Fields _fields) {
        int ordinal = _fields.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return getErrorStatus();
            }
            if (ordinal != 2) {
                throw new IllegalStateException();
            }
            return getCodeSource();
        }
        return getOauthToken();
    }

    public PKCS7Type getOauthToken() {
        return this.oauthToken;
    }

    public int hashCode() {
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(_Fields _fields) {
        if (_fields != null) {
            int ordinal = _fields.ordinal();
            if (ordinal == 0) {
                return isSetOauthToken();
            }
            if (ordinal == 1) {
                return isSetErrorStatus();
            }
            if (ordinal != 2) {
                throw new IllegalStateException();
            }
            return isSetCodeSource();
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetCodeSource() {
        return this.codeSource != null;
    }

    public boolean isSetErrorStatus() {
        return this.errorStatus != null;
    }

    public boolean isSetOauthToken() {
        return this.oauthToken != null;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    public DeviceCredentials setCodeSource(LinkCodeSource linkCodeSource) {
        this.codeSource = linkCodeSource;
        return this;
    }

    public void setCodeSourceIsSet(boolean z) {
        if (!z) {
            this.codeSource = null;
        }
    }

    public DeviceCredentials setErrorStatus(ReturnError returnError) {
        this.errorStatus = returnError;
        return this;
    }

    public void setErrorStatusIsSet(boolean z) {
        if (!z) {
            this.errorStatus = null;
        }
    }

    @Override // org.apache.thrift.orig.TBase
    public void setFieldValue(_Fields _fields, Object obj) {
        int ordinal = _fields.ordinal();
        if (ordinal == 0) {
            if (obj == null) {
                unsetOauthToken();
            } else {
                setOauthToken((PKCS7Type) obj);
            }
        } else if (ordinal == 1) {
            if (obj == null) {
                unsetErrorStatus();
            } else {
                setErrorStatus((ReturnError) obj);
            }
        } else if (ordinal != 2) {
        } else {
            if (obj == null) {
                unsetCodeSource();
            } else {
                setCodeSource((LinkCodeSource) obj);
            }
        }
    }

    public DeviceCredentials setOauthToken(PKCS7Type pKCS7Type) {
        this.oauthToken = pKCS7Type;
        return this;
    }

    public void setOauthTokenIsSet(boolean z) {
        if (!z) {
            this.oauthToken = null;
        }
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("DeviceCredentials(", "oauthToken:");
        PKCS7Type pKCS7Type = this.oauthToken;
        if (pKCS7Type == null) {
            outline112.append("null");
        } else {
            outline112.append(pKCS7Type);
        }
        outline112.append(", ");
        outline112.append("errorStatus:");
        ReturnError returnError = this.errorStatus;
        if (returnError == null) {
            outline112.append("null");
        } else {
            outline112.append(returnError);
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

    public void unsetErrorStatus() {
        this.errorStatus = null;
    }

    public void unsetOauthToken() {
        this.oauthToken = null;
    }

    public void validate() throws TException {
        PKCS7Type pKCS7Type = this.oauthToken;
        if (pKCS7Type != null) {
            pKCS7Type.validate();
        }
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
