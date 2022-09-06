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
public class OTADetails implements TBase<OTADetails, _Fields>, Serializable, Cloneable {
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public List<UpdateManifest> updateManifestList;
    private static final TStruct STRUCT_DESC = new TStruct("OTADetails");
    private static final TField UPDATE_MANIFEST_LIST_FIELD_DESC = new TField("updateManifestList", (byte) 15, 1);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.OTADetails$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$OTADetails$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$OTADetails$_Fields[_Fields.UPDATE_MANIFEST_LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class OTADetailsStandardScheme extends StandardScheme<OTADetails> {
        private OTADetailsStandardScheme() {
        }

        /* synthetic */ OTADetailsStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, OTADetails oTADetails) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    oTADetails.validate();
                    return;
                }
                if (readFieldBegin.id == 1 && b == 15) {
                    TList readListBegin = tProtocol.readListBegin();
                    oTADetails.updateManifestList = new ArrayList(readListBegin.size);
                    for (int i = 0; i < readListBegin.size; i++) {
                        UpdateManifest updateManifest = new UpdateManifest();
                        updateManifest.read(tProtocol);
                        oTADetails.updateManifestList.add(updateManifest);
                    }
                    tProtocol.readListEnd();
                    oTADetails.setUpdateManifestListIsSet(true);
                } else {
                    TProtocolUtil.skip(tProtocol, b);
                }
                tProtocol.readFieldEnd();
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, OTADetails oTADetails) throws TException {
            oTADetails.validate();
            tProtocol.writeStructBegin(OTADetails.STRUCT_DESC);
            if (oTADetails.updateManifestList != null) {
                tProtocol.writeFieldBegin(OTADetails.UPDATE_MANIFEST_LIST_FIELD_DESC);
                tProtocol.writeListBegin(new TList((byte) 12, oTADetails.updateManifestList.size()));
                for (UpdateManifest updateManifest : oTADetails.updateManifestList) {
                    updateManifest.write(tProtocol);
                }
                tProtocol.writeListEnd();
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class OTADetailsStandardSchemeFactory implements SchemeFactory {
        private OTADetailsStandardSchemeFactory() {
        }

        /* synthetic */ OTADetailsStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public OTADetailsStandardScheme mo12846getScheme() {
            return new OTADetailsStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class OTADetailsTupleScheme extends TupleScheme<OTADetails> {
        private OTADetailsTupleScheme() {
        }

        /* synthetic */ OTADetailsTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, OTADetails oTADetails) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            if (tTupleProtocol.readBitSet(1).get(0)) {
                TList tList = new TList((byte) 12, tTupleProtocol.readI32());
                oTADetails.updateManifestList = new ArrayList(tList.size);
                for (int i = 0; i < tList.size; i++) {
                    UpdateManifest updateManifest = new UpdateManifest();
                    updateManifest.read(tTupleProtocol);
                    oTADetails.updateManifestList.add(updateManifest);
                }
                oTADetails.setUpdateManifestListIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, OTADetails oTADetails) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (oTADetails.isSetUpdateManifestList()) {
                bitSet.set(0);
            }
            tTupleProtocol.writeBitSet(bitSet, 1);
            if (oTADetails.isSetUpdateManifestList()) {
                tTupleProtocol.writeI32(oTADetails.updateManifestList.size());
                for (UpdateManifest updateManifest : oTADetails.updateManifestList) {
                    updateManifest.write(tTupleProtocol);
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class OTADetailsTupleSchemeFactory implements SchemeFactory {
        private OTADetailsTupleSchemeFactory() {
        }

        /* synthetic */ OTADetailsTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public OTADetailsTupleScheme mo12846getScheme() {
            return new OTADetailsTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        UPDATE_MANIFEST_LIST(1, "updateManifestList");
        
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
            return UPDATE_MANIFEST_LIST;
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
        schemes.put(StandardScheme.class, new OTADetailsStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new OTADetailsTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.UPDATE_MANIFEST_LIST, (_Fields) new FieldMetaData("updateManifestList", (byte) 3, new ListMetaData((byte) 15, new StructMetaData((byte) 12, UpdateManifest.class))));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(OTADetails.class, metaDataMap);
    }

    public OTADetails() {
    }

    public OTADetails(OTADetails oTADetails) {
        if (oTADetails.isSetUpdateManifestList()) {
            ArrayList arrayList = new ArrayList();
            for (UpdateManifest updateManifest : oTADetails.updateManifestList) {
                arrayList.add(new UpdateManifest(updateManifest));
            }
            this.updateManifestList = arrayList;
        }
    }

    public OTADetails(List<UpdateManifest> list) {
        this();
        this.updateManifestList = list;
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

    public void addToUpdateManifestList(UpdateManifest updateManifest) {
        if (this.updateManifestList == null) {
            this.updateManifestList = new ArrayList();
        }
        this.updateManifestList.add(updateManifest);
    }

    @Override // org.apache.thrift.orig.TBase
    public void clear() {
        this.updateManifestList = null;
    }

    @Override // java.lang.Comparable
    public int compareTo(OTADetails oTADetails) {
        int compareTo;
        if (!OTADetails.class.equals(oTADetails.getClass())) {
            return OTADetails.class.getName().compareTo(OTADetails.class.getName());
        }
        int compareTo2 = Boolean.valueOf(isSetUpdateManifestList()).compareTo(Boolean.valueOf(oTADetails.isSetUpdateManifestList()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (isSetUpdateManifestList() && (compareTo = TBaseHelper.compareTo((List) this.updateManifestList, (List) oTADetails.updateManifestList)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<OTADetails, _Fields> deepCopy2() {
        return new OTADetails(this);
    }

    public boolean equals(OTADetails oTADetails) {
        if (oTADetails == null) {
            return false;
        }
        boolean isSetUpdateManifestList = isSetUpdateManifestList();
        boolean isSetUpdateManifestList2 = oTADetails.isSetUpdateManifestList();
        if (!isSetUpdateManifestList && !isSetUpdateManifestList2) {
            return true;
        }
        return isSetUpdateManifestList && isSetUpdateManifestList2 && this.updateManifestList.equals(oTADetails.updateManifestList);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof OTADetails)) {
            return equals((OTADetails) obj);
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
            return getUpdateManifestList();
        }
        throw new IllegalStateException();
    }

    public List<UpdateManifest> getUpdateManifestList() {
        return this.updateManifestList;
    }

    public Iterator<UpdateManifest> getUpdateManifestListIterator() {
        List<UpdateManifest> list = this.updateManifestList;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public int getUpdateManifestListSize() {
        List<UpdateManifest> list = this.updateManifestList;
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
            if (_fields.ordinal() != 0) {
                throw new IllegalStateException();
            }
            return isSetUpdateManifestList();
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetUpdateManifestList() {
        return this.updateManifestList != null;
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
            unsetUpdateManifestList();
        } else {
            setUpdateManifestList((List) obj);
        }
    }

    public OTADetails setUpdateManifestList(List<UpdateManifest> list) {
        this.updateManifestList = list;
        return this;
    }

    public void setUpdateManifestListIsSet(boolean z) {
        if (!z) {
            this.updateManifestList = null;
        }
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("OTADetails(", "updateManifestList:");
        List<UpdateManifest> list = this.updateManifestList;
        if (list == null) {
            outline112.append("null");
        } else {
            outline112.append(list);
        }
        outline112.append(")");
        return outline112.toString();
    }

    public void unsetUpdateManifestList() {
        this.updateManifestList = null;
    }

    public void validate() throws TException {
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
