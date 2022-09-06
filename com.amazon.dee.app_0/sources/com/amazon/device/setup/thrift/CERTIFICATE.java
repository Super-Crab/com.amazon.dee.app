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
public class CERTIFICATE implements TBase<CERTIFICATE, _Fields>, Serializable, Cloneable {
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public String pem_text;
    private static final TStruct STRUCT_DESC = new TStruct("CERTIFICATE");
    private static final TField PEM_TEXT_FIELD_DESC = new TField("pem_text", (byte) 11, 1);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.CERTIFICATE$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$CERTIFICATE$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$CERTIFICATE$_Fields[_Fields.PEM_TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class CERTIFICATEStandardScheme extends StandardScheme<CERTIFICATE> {
        private CERTIFICATEStandardScheme() {
        }

        /* synthetic */ CERTIFICATEStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, CERTIFICATE certificate) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    certificate.validate();
                    return;
                }
                if (readFieldBegin.id == 1 && b == 11) {
                    certificate.pem_text = tProtocol.readString();
                    certificate.setPem_textIsSet(true);
                } else {
                    TProtocolUtil.skip(tProtocol, b);
                }
                tProtocol.readFieldEnd();
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, CERTIFICATE certificate) throws TException {
            certificate.validate();
            tProtocol.writeStructBegin(CERTIFICATE.STRUCT_DESC);
            if (certificate.pem_text != null) {
                tProtocol.writeFieldBegin(CERTIFICATE.PEM_TEXT_FIELD_DESC);
                tProtocol.writeString(certificate.pem_text);
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class CERTIFICATEStandardSchemeFactory implements SchemeFactory {
        private CERTIFICATEStandardSchemeFactory() {
        }

        /* synthetic */ CERTIFICATEStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public CERTIFICATEStandardScheme mo12846getScheme() {
            return new CERTIFICATEStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class CERTIFICATETupleScheme extends TupleScheme<CERTIFICATE> {
        private CERTIFICATETupleScheme() {
        }

        /* synthetic */ CERTIFICATETupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, CERTIFICATE certificate) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            if (tTupleProtocol.readBitSet(1).get(0)) {
                certificate.pem_text = tTupleProtocol.readString();
                certificate.setPem_textIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, CERTIFICATE certificate) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (certificate.isSetPem_text()) {
                bitSet.set(0);
            }
            tTupleProtocol.writeBitSet(bitSet, 1);
            if (certificate.isSetPem_text()) {
                tTupleProtocol.writeString(certificate.pem_text);
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class CERTIFICATETupleSchemeFactory implements SchemeFactory {
        private CERTIFICATETupleSchemeFactory() {
        }

        /* synthetic */ CERTIFICATETupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public CERTIFICATETupleScheme mo12846getScheme() {
            return new CERTIFICATETupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        PEM_TEXT(1, "pem_text");
        
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
                return null;
            }
            return PEM_TEXT;
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
        schemes.put(StandardScheme.class, new CERTIFICATEStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new CERTIFICATETupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.PEM_TEXT, (_Fields) new FieldMetaData("pem_text", (byte) 3, new FieldValueMetaData((byte) 11)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(CERTIFICATE.class, metaDataMap);
    }

    public CERTIFICATE() {
    }

    public CERTIFICATE(CERTIFICATE certificate) {
        if (certificate.isSetPem_text()) {
            this.pem_text = certificate.pem_text;
        }
    }

    public CERTIFICATE(String str) {
        this();
        this.pem_text = str;
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
        this.pem_text = null;
    }

    @Override // java.lang.Comparable
    public int compareTo(CERTIFICATE certificate) {
        int compareTo;
        if (!CERTIFICATE.class.equals(certificate.getClass())) {
            return CERTIFICATE.class.getName().compareTo(CERTIFICATE.class.getName());
        }
        int compareTo2 = Boolean.valueOf(isSetPem_text()).compareTo(Boolean.valueOf(certificate.isSetPem_text()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (isSetPem_text() && (compareTo = TBaseHelper.compareTo(this.pem_text, certificate.pem_text)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<CERTIFICATE, _Fields> deepCopy2() {
        return new CERTIFICATE(this);
    }

    public boolean equals(CERTIFICATE certificate) {
        if (certificate == null) {
            return false;
        }
        boolean isSetPem_text = isSetPem_text();
        boolean isSetPem_text2 = certificate.isSetPem_text();
        if (!isSetPem_text && !isSetPem_text2) {
            return true;
        }
        return isSetPem_text && isSetPem_text2 && this.pem_text.equals(certificate.pem_text);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof CERTIFICATE)) {
            return equals((CERTIFICATE) obj);
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.thrift.orig.TBase
    /* renamed from: fieldForId */
    public _Fields mo3968fieldForId(int i) {
        return _Fields.findByThriftId(i);
    }

    @Override // org.apache.thrift.orig.TBase
    public Object getFieldValue(_Fields _fields) {
        if (_fields.ordinal() == 0) {
            return getPem_text();
        }
        throw new IllegalStateException();
    }

    public String getPem_text() {
        return this.pem_text;
    }

    public int hashCode() {
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(_Fields _fields) {
        if (_fields != null) {
            if (_fields.ordinal() != 0) {
                throw new IllegalStateException();
            }
            return isSetPem_text();
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetPem_text() {
        return this.pem_text != null;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    @Override // org.apache.thrift.orig.TBase
    public void setFieldValue(_Fields _fields, Object obj) {
        if (_fields.ordinal() != 0) {
            return;
        }
        if (obj == null) {
            unsetPem_text();
        } else {
            setPem_text((String) obj);
        }
    }

    public CERTIFICATE setPem_text(String str) {
        this.pem_text = str;
        return this;
    }

    public void setPem_textIsSet(boolean z) {
        if (!z) {
            this.pem_text = null;
        }
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("CERTIFICATE(", "pem_text:");
        String str = this.pem_text;
        if (str == null) {
            str = "null";
        }
        return GeneratedOutlineSupport1.outline91(outline112, str, ")");
    }

    public void unsetPem_text() {
        this.pem_text = null;
    }

    public void validate() throws TException {
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
