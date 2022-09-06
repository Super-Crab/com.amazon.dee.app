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
public class UpdateManifest implements TBase<UpdateManifest, _Fields>, Serializable, Cloneable {
    private static final int __ID_ISSET_ID = 0;
    private static final int __ISFORCED_ISSET_ID = 1;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private byte __isset_bitfield;
    public long id;
    public boolean isForced;
    public List<Update> update;
    public UpdateState updateState;
    private static final TStruct STRUCT_DESC = new TStruct("UpdateManifest");
    private static final TField ID_FIELD_DESC = new TField("id", (byte) 10, 1);
    private static final TField UPDATE_STATE_FIELD_DESC = new TField("updateState", (byte) 8, 2);
    private static final TField IS_FORCED_FIELD_DESC = new TField("isForced", (byte) 2, 3);
    private static final TField UPDATE_FIELD_DESC = new TField("update", (byte) 15, 4);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.UpdateManifest$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$UpdateManifest$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$UpdateManifest$_Fields[_Fields.ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$UpdateManifest$_Fields[_Fields.UPDATE_STATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$UpdateManifest$_Fields[_Fields.IS_FORCED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$UpdateManifest$_Fields[_Fields.UPDATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class UpdateManifestStandardScheme extends StandardScheme<UpdateManifest> {
        private UpdateManifestStandardScheme() {
        }

        /* synthetic */ UpdateManifestStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, UpdateManifest updateManifest) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    updateManifest.validate();
                    return;
                }
                short s = readFieldBegin.id;
                if (s == 1) {
                    if (b == 10) {
                        updateManifest.id = tProtocol.readI64();
                        updateManifest.setIdIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                } else if (s == 2) {
                    if (b == 8) {
                        updateManifest.updateState = UpdateState.findByValue(tProtocol.readI32());
                        updateManifest.setUpdateStateIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                } else if (s != 3) {
                    if (s == 4 && b == 15) {
                        TList readListBegin = tProtocol.readListBegin();
                        updateManifest.update = new ArrayList(readListBegin.size);
                        for (int i = 0; i < readListBegin.size; i++) {
                            Update update = new Update();
                            update.read(tProtocol);
                            updateManifest.update.add(update);
                        }
                        tProtocol.readListEnd();
                        updateManifest.setUpdateIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                } else {
                    if (b == 2) {
                        updateManifest.isForced = tProtocol.readBool();
                        updateManifest.setIsForcedIsSet(true);
                        tProtocol.readFieldEnd();
                    }
                    TProtocolUtil.skip(tProtocol, b);
                    tProtocol.readFieldEnd();
                }
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, UpdateManifest updateManifest) throws TException {
            updateManifest.validate();
            tProtocol.writeStructBegin(UpdateManifest.STRUCT_DESC);
            tProtocol.writeFieldBegin(UpdateManifest.ID_FIELD_DESC);
            tProtocol.writeI64(updateManifest.id);
            tProtocol.writeFieldEnd();
            if (updateManifest.updateState != null) {
                tProtocol.writeFieldBegin(UpdateManifest.UPDATE_STATE_FIELD_DESC);
                tProtocol.writeI32(updateManifest.updateState.getValue());
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldBegin(UpdateManifest.IS_FORCED_FIELD_DESC);
            tProtocol.writeBool(updateManifest.isForced);
            tProtocol.writeFieldEnd();
            if (updateManifest.update != null) {
                tProtocol.writeFieldBegin(UpdateManifest.UPDATE_FIELD_DESC);
                tProtocol.writeListBegin(new TList((byte) 12, updateManifest.update.size()));
                for (Update update : updateManifest.update) {
                    update.write(tProtocol);
                }
                tProtocol.writeListEnd();
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class UpdateManifestStandardSchemeFactory implements SchemeFactory {
        private UpdateManifestStandardSchemeFactory() {
        }

        /* synthetic */ UpdateManifestStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public UpdateManifestStandardScheme mo12846getScheme() {
            return new UpdateManifestStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class UpdateManifestTupleScheme extends TupleScheme<UpdateManifest> {
        private UpdateManifestTupleScheme() {
        }

        /* synthetic */ UpdateManifestTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, UpdateManifest updateManifest) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet readBitSet = tTupleProtocol.readBitSet(4);
            if (readBitSet.get(0)) {
                updateManifest.id = tTupleProtocol.readI64();
                updateManifest.setIdIsSet(true);
            }
            if (readBitSet.get(1)) {
                updateManifest.updateState = UpdateState.findByValue(tTupleProtocol.readI32());
                updateManifest.setUpdateStateIsSet(true);
            }
            if (readBitSet.get(2)) {
                updateManifest.isForced = tTupleProtocol.readBool();
                updateManifest.setIsForcedIsSet(true);
            }
            if (readBitSet.get(3)) {
                TList tList = new TList((byte) 12, tTupleProtocol.readI32());
                updateManifest.update = new ArrayList(tList.size);
                for (int i = 0; i < tList.size; i++) {
                    Update update = new Update();
                    update.read(tTupleProtocol);
                    updateManifest.update.add(update);
                }
                updateManifest.setUpdateIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, UpdateManifest updateManifest) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (updateManifest.isSetId()) {
                bitSet.set(0);
            }
            if (updateManifest.isSetUpdateState()) {
                bitSet.set(1);
            }
            if (updateManifest.isSetIsForced()) {
                bitSet.set(2);
            }
            if (updateManifest.isSetUpdate()) {
                bitSet.set(3);
            }
            tTupleProtocol.writeBitSet(bitSet, 4);
            if (updateManifest.isSetId()) {
                tTupleProtocol.writeI64(updateManifest.id);
            }
            if (updateManifest.isSetUpdateState()) {
                tTupleProtocol.writeI32(updateManifest.updateState.getValue());
            }
            if (updateManifest.isSetIsForced()) {
                tTupleProtocol.writeBool(updateManifest.isForced);
            }
            if (updateManifest.isSetUpdate()) {
                tTupleProtocol.writeI32(updateManifest.update.size());
                for (Update update : updateManifest.update) {
                    update.write(tTupleProtocol);
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class UpdateManifestTupleSchemeFactory implements SchemeFactory {
        private UpdateManifestTupleSchemeFactory() {
        }

        /* synthetic */ UpdateManifestTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public UpdateManifestTupleScheme mo12846getScheme() {
            return new UpdateManifestTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        ID(1, "id"),
        UPDATE_STATE(2, "updateState"),
        IS_FORCED(3, "isForced"),
        UPDATE(4, "update");
        
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
                    return UPDATE_STATE;
                }
                if (i == 3) {
                    return IS_FORCED;
                }
                if (i == 4) {
                    return UPDATE;
                }
                return null;
            }
            return ID;
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
        schemes.put(StandardScheme.class, new UpdateManifestStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new UpdateManifestTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.ID, (_Fields) new FieldMetaData("id", (byte) 3, new FieldValueMetaData((byte) 10)));
        enumMap.put((EnumMap) _Fields.UPDATE_STATE, (_Fields) new FieldMetaData("updateState", (byte) 3, new EnumMetaData((byte) 16, UpdateState.class)));
        enumMap.put((EnumMap) _Fields.IS_FORCED, (_Fields) new FieldMetaData("isForced", (byte) 3, new FieldValueMetaData((byte) 2)));
        enumMap.put((EnumMap) _Fields.UPDATE, (_Fields) new FieldMetaData("update", (byte) 3, new ListMetaData((byte) 15, new StructMetaData((byte) 12, Update.class))));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(UpdateManifest.class, metaDataMap);
    }

    public UpdateManifest() {
        this.__isset_bitfield = (byte) 0;
    }

    public UpdateManifest(long j, UpdateState updateState, boolean z, List<Update> list) {
        this();
        this.id = j;
        setIdIsSet(true);
        this.updateState = updateState;
        this.isForced = z;
        setIsForcedIsSet(true);
        this.update = list;
    }

    public UpdateManifest(UpdateManifest updateManifest) {
        this.__isset_bitfield = (byte) 0;
        this.__isset_bitfield = updateManifest.__isset_bitfield;
        this.id = updateManifest.id;
        if (updateManifest.isSetUpdateState()) {
            this.updateState = updateManifest.updateState;
        }
        this.isForced = updateManifest.isForced;
        if (updateManifest.isSetUpdate()) {
            ArrayList arrayList = new ArrayList();
            for (Update update : updateManifest.update) {
                arrayList.add(new Update(update));
            }
            this.update = arrayList;
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

    public void addToUpdate(Update update) {
        if (this.update == null) {
            this.update = new ArrayList();
        }
        this.update.add(update);
    }

    @Override // org.apache.thrift.orig.TBase
    public void clear() {
        setIdIsSet(false);
        this.id = 0L;
        this.updateState = null;
        setIsForcedIsSet(false);
        this.isForced = false;
        this.update = null;
    }

    @Override // java.lang.Comparable
    public int compareTo(UpdateManifest updateManifest) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        if (!UpdateManifest.class.equals(updateManifest.getClass())) {
            return UpdateManifest.class.getName().compareTo(UpdateManifest.class.getName());
        }
        int compareTo5 = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(updateManifest.isSetId()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (isSetId() && (compareTo4 = TBaseHelper.compareTo(this.id, updateManifest.id)) != 0) {
            return compareTo4;
        }
        int compareTo6 = Boolean.valueOf(isSetUpdateState()).compareTo(Boolean.valueOf(updateManifest.isSetUpdateState()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (isSetUpdateState() && (compareTo3 = TBaseHelper.compareTo((Comparable) this.updateState, (Comparable) updateManifest.updateState)) != 0) {
            return compareTo3;
        }
        int compareTo7 = Boolean.valueOf(isSetIsForced()).compareTo(Boolean.valueOf(updateManifest.isSetIsForced()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (isSetIsForced() && (compareTo2 = TBaseHelper.compareTo(this.isForced, updateManifest.isForced)) != 0) {
            return compareTo2;
        }
        int compareTo8 = Boolean.valueOf(isSetUpdate()).compareTo(Boolean.valueOf(updateManifest.isSetUpdate()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (isSetUpdate() && (compareTo = TBaseHelper.compareTo((List) this.update, (List) updateManifest.update)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<UpdateManifest, _Fields> deepCopy2() {
        return new UpdateManifest(this);
    }

    public boolean equals(UpdateManifest updateManifest) {
        if (updateManifest != null && this.id == updateManifest.id) {
            boolean isSetUpdateState = isSetUpdateState();
            boolean isSetUpdateState2 = updateManifest.isSetUpdateState();
            if (((isSetUpdateState || isSetUpdateState2) && (!isSetUpdateState || !isSetUpdateState2 || !this.updateState.equals(updateManifest.updateState))) || this.isForced != updateManifest.isForced) {
                return false;
            }
            boolean isSetUpdate = isSetUpdate();
            boolean isSetUpdate2 = updateManifest.isSetUpdate();
            if (!isSetUpdate && !isSetUpdate2) {
                return true;
            }
            return isSetUpdate && isSetUpdate2 && this.update.equals(updateManifest.update);
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof UpdateManifest)) {
            return equals((UpdateManifest) obj);
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
        int ordinal = _fields.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return getUpdateState();
            }
            if (ordinal == 2) {
                return Boolean.valueOf(isIsForced());
            }
            if (ordinal != 3) {
                throw new IllegalStateException();
            }
            return getUpdate();
        }
        return Long.valueOf(getId());
    }

    public long getId() {
        return this.id;
    }

    public List<Update> getUpdate() {
        return this.update;
    }

    public Iterator<Update> getUpdateIterator() {
        List<Update> list = this.update;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public int getUpdateSize() {
        List<Update> list = this.update;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public UpdateState getUpdateState() {
        return this.updateState;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isIsForced() {
        return this.isForced;
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(_Fields _fields) {
        if (_fields != null) {
            int ordinal = _fields.ordinal();
            if (ordinal == 0) {
                return isSetId();
            }
            if (ordinal == 1) {
                return isSetUpdateState();
            }
            if (ordinal == 2) {
                return isSetIsForced();
            }
            if (ordinal != 3) {
                throw new IllegalStateException();
            }
            return isSetUpdate();
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetId() {
        return EncodingUtils.testBit(this.__isset_bitfield, 0);
    }

    public boolean isSetIsForced() {
        return EncodingUtils.testBit(this.__isset_bitfield, 1);
    }

    public boolean isSetUpdate() {
        return this.update != null;
    }

    public boolean isSetUpdateState() {
        return this.updateState != null;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    @Override // org.apache.thrift.orig.TBase
    public void setFieldValue(_Fields _fields, Object obj) {
        int ordinal = _fields.ordinal();
        if (ordinal == 0) {
            if (obj == null) {
                unsetId();
            } else {
                setId(((Long) obj).longValue());
            }
        } else if (ordinal == 1) {
            if (obj == null) {
                unsetUpdateState();
            } else {
                setUpdateState((UpdateState) obj);
            }
        } else if (ordinal == 2) {
            if (obj == null) {
                unsetIsForced();
            } else {
                setIsForced(((Boolean) obj).booleanValue());
            }
        } else if (ordinal != 3) {
        } else {
            if (obj == null) {
                unsetUpdate();
            } else {
                setUpdate((List) obj);
            }
        }
    }

    public UpdateManifest setId(long j) {
        this.id = j;
        setIdIsSet(true);
        return this;
    }

    public void setIdIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, z);
    }

    public UpdateManifest setIsForced(boolean z) {
        this.isForced = z;
        setIsForcedIsSet(true);
        return this;
    }

    public void setIsForcedIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, z);
    }

    public UpdateManifest setUpdate(List<Update> list) {
        this.update = list;
        return this;
    }

    public void setUpdateIsSet(boolean z) {
        if (!z) {
            this.update = null;
        }
    }

    public UpdateManifest setUpdateState(UpdateState updateState) {
        this.updateState = updateState;
        return this;
    }

    public void setUpdateStateIsSet(boolean z) {
        if (!z) {
            this.updateState = null;
        }
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("UpdateManifest(", "id:");
        outline112.append(this.id);
        outline112.append(", ");
        outline112.append("updateState:");
        UpdateState updateState = this.updateState;
        if (updateState == null) {
            outline112.append("null");
        } else {
            outline112.append(updateState);
        }
        outline112.append(", ");
        outline112.append("isForced:");
        outline112.append(this.isForced);
        outline112.append(", ");
        outline112.append("update:");
        List<Update> list = this.update;
        if (list == null) {
            outline112.append("null");
        } else {
            outline112.append(list);
        }
        outline112.append(")");
        return outline112.toString();
    }

    public void unsetId() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
    }

    public void unsetIsForced() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
    }

    public void unsetUpdate() {
        this.update = null;
    }

    public void unsetUpdateState() {
        this.updateState = null;
    }

    public void validate() throws TException {
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
