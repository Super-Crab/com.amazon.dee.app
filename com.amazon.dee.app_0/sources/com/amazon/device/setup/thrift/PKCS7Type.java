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
public class PKCS7Type implements TBase<PKCS7Type, _Fields>, Serializable, Cloneable {
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public String text;
    private static final TStruct STRUCT_DESC = new TStruct("PKCS7Type");
    private static final TField TEXT_FIELD_DESC = new TField("text", (byte) 11, 1);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.PKCS7Type$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$PKCS7Type$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$PKCS7Type$_Fields[_Fields.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class PKCS7TypeStandardScheme extends StandardScheme<PKCS7Type> {
        private PKCS7TypeStandardScheme() {
        }

        /* synthetic */ PKCS7TypeStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, PKCS7Type pKCS7Type) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    pKCS7Type.validate();
                    return;
                }
                if (readFieldBegin.id == 1 && b == 11) {
                    pKCS7Type.text = tProtocol.readString();
                    pKCS7Type.setTextIsSet(true);
                } else {
                    TProtocolUtil.skip(tProtocol, b);
                }
                tProtocol.readFieldEnd();
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, PKCS7Type pKCS7Type) throws TException {
            pKCS7Type.validate();
            tProtocol.writeStructBegin(PKCS7Type.STRUCT_DESC);
            if (pKCS7Type.text != null) {
                tProtocol.writeFieldBegin(PKCS7Type.TEXT_FIELD_DESC);
                tProtocol.writeString(pKCS7Type.text);
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class PKCS7TypeStandardSchemeFactory implements SchemeFactory {
        private PKCS7TypeStandardSchemeFactory() {
        }

        /* synthetic */ PKCS7TypeStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public PKCS7TypeStandardScheme mo12846getScheme() {
            return new PKCS7TypeStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class PKCS7TypeTupleScheme extends TupleScheme<PKCS7Type> {
        private PKCS7TypeTupleScheme() {
        }

        /* synthetic */ PKCS7TypeTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, PKCS7Type pKCS7Type) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            if (tTupleProtocol.readBitSet(1).get(0)) {
                pKCS7Type.text = tTupleProtocol.readString();
                pKCS7Type.setTextIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, PKCS7Type pKCS7Type) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (pKCS7Type.isSetText()) {
                bitSet.set(0);
            }
            tTupleProtocol.writeBitSet(bitSet, 1);
            if (pKCS7Type.isSetText()) {
                tTupleProtocol.writeString(pKCS7Type.text);
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class PKCS7TypeTupleSchemeFactory implements SchemeFactory {
        private PKCS7TypeTupleSchemeFactory() {
        }

        /* synthetic */ PKCS7TypeTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public PKCS7TypeTupleScheme mo12846getScheme() {
            return new PKCS7TypeTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        TEXT(1, "text");
        
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
            return TEXT;
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
        schemes.put(StandardScheme.class, new PKCS7TypeStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new PKCS7TypeTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.TEXT, (_Fields) new FieldMetaData("text", (byte) 3, new FieldValueMetaData((byte) 11)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(PKCS7Type.class, metaDataMap);
    }

    public PKCS7Type() {
    }

    public PKCS7Type(PKCS7Type pKCS7Type) {
        if (pKCS7Type.isSetText()) {
            this.text = pKCS7Type.text;
        }
    }

    public PKCS7Type(String str) {
        this();
        this.text = str;
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
        this.text = null;
    }

    @Override // java.lang.Comparable
    public int compareTo(PKCS7Type pKCS7Type) {
        int compareTo;
        if (!PKCS7Type.class.equals(pKCS7Type.getClass())) {
            return PKCS7Type.class.getName().compareTo(PKCS7Type.class.getName());
        }
        int compareTo2 = Boolean.valueOf(isSetText()).compareTo(Boolean.valueOf(pKCS7Type.isSetText()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (isSetText() && (compareTo = TBaseHelper.compareTo(this.text, pKCS7Type.text)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<PKCS7Type, _Fields> deepCopy2() {
        return new PKCS7Type(this);
    }

    public boolean equals(PKCS7Type pKCS7Type) {
        if (pKCS7Type == null) {
            return false;
        }
        boolean isSetText = isSetText();
        boolean isSetText2 = pKCS7Type.isSetText();
        if (!isSetText && !isSetText2) {
            return true;
        }
        return isSetText && isSetText2 && this.text.equals(pKCS7Type.text);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof PKCS7Type)) {
            return equals((PKCS7Type) obj);
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
            return getText();
        }
        throw new IllegalStateException();
    }

    public String getText() {
        return this.text;
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
            return isSetText();
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetText() {
        return this.text != null;
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
            unsetText();
        } else {
            setText((String) obj);
        }
    }

    public PKCS7Type setText(String str) {
        this.text = str;
        return this;
    }

    public void setTextIsSet(boolean z) {
        if (!z) {
            this.text = null;
        }
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("PKCS7Type(", "text:");
        String str = this.text;
        if (str == null) {
            str = "null";
        }
        return GeneratedOutlineSupport1.outline91(outline112, str, ")");
    }

    public void unsetText() {
        this.text = null;
    }

    public void validate() throws TException {
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
