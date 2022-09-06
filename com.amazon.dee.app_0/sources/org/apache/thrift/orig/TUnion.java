package org.apache.thrift.orig;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.orig.TFieldIdEnum;
import org.apache.thrift.orig.TUnion;
import org.apache.thrift.orig.protocol.TField;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolException;
import org.apache.thrift.orig.protocol.TStruct;
import org.apache.thrift.orig.scheme.IScheme;
import org.apache.thrift.orig.scheme.SchemeFactory;
import org.apache.thrift.orig.scheme.StandardScheme;
import org.apache.thrift.orig.scheme.TupleScheme;
/* loaded from: classes4.dex */
public abstract class TUnion<T extends TUnion<?, ?>, F extends TFieldIdEnum> implements TBase<T, F> {
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();
    protected F setField_;
    protected Object value_;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class TUnionStandardScheme extends StandardScheme<TUnion> {
        private TUnionStandardScheme() {
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, TUnion tUnion) throws TException {
            tUnion.setField_ = null;
            tUnion.value_ = null;
            tProtocol.readStructBegin();
            TField readFieldBegin = tProtocol.readFieldBegin();
            tUnion.value_ = tUnion.standardSchemeReadValue(tProtocol, readFieldBegin);
            if (tUnion.value_ != null) {
                tUnion.setField_ = (F) tUnion.enumForId(readFieldBegin.id);
            }
            tProtocol.readFieldEnd();
            tProtocol.readFieldBegin();
            tProtocol.readStructEnd();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, TUnion tUnion) throws TException {
            if (tUnion.getSetField() != null && tUnion.getFieldValue() != null) {
                tProtocol.writeStructBegin(tUnion.getStructDesc());
                tProtocol.writeFieldBegin(tUnion.getFieldDesc(tUnion.setField_));
                tUnion.standardSchemeWriteValue(tProtocol);
                tProtocol.writeFieldEnd();
                tProtocol.writeFieldStop();
                tProtocol.writeStructEnd();
                return;
            }
            throw new TProtocolException("Cannot write a TUnion with no set value!");
        }
    }

    /* loaded from: classes4.dex */
    private static class TUnionStandardSchemeFactory implements SchemeFactory {
        private TUnionStandardSchemeFactory() {
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public TUnionStandardScheme mo12846getScheme() {
            return new TUnionStandardScheme();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class TUnionTupleScheme extends TupleScheme<TUnion> {
        private TUnionTupleScheme() {
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, TUnion tUnion) throws TException {
            tUnion.setField_ = null;
            tUnion.value_ = null;
            short readI16 = tProtocol.readI16();
            tUnion.value_ = tUnion.tupleSchemeReadValue(tProtocol, readI16);
            if (tUnion.value_ != null) {
                tUnion.setField_ = (F) tUnion.enumForId(readI16);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, TUnion tUnion) throws TException {
            if (tUnion.getSetField() != null && tUnion.getFieldValue() != null) {
                tProtocol.writeI16(tUnion.setField_.getThriftFieldId());
                tUnion.tupleSchemeWriteValue(tProtocol);
                return;
            }
            throw new TProtocolException("Cannot write a TUnion with no set value!");
        }
    }

    /* loaded from: classes4.dex */
    private static class TUnionTupleSchemeFactory implements SchemeFactory {
        private TUnionTupleSchemeFactory() {
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public TUnionTupleScheme mo12846getScheme() {
            return new TUnionTupleScheme();
        }
    }

    static {
        schemes.put(StandardScheme.class, new TUnionStandardSchemeFactory());
        schemes.put(TupleScheme.class, new TUnionTupleSchemeFactory());
    }

    protected TUnion() {
        this.setField_ = null;
        this.value_ = null;
    }

    private static List deepCopyList(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object obj : list) {
            arrayList.add(deepCopyObject(obj));
        }
        return arrayList;
    }

    private static Map deepCopyMap(Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            hashMap.put(deepCopyObject(entry.getKey()), deepCopyObject(entry.getValue()));
        }
        return hashMap;
    }

    private static Object deepCopyObject(Object obj) {
        if (obj instanceof TBase) {
            return ((TBase) obj).deepCopy();
        }
        if (obj instanceof ByteBuffer) {
            return TBaseHelper.copyBinary((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return deepCopyList((List) obj);
        }
        if (obj instanceof Set) {
            return deepCopySet((Set) obj);
        }
        return obj instanceof Map ? deepCopyMap((Map) obj) : obj;
    }

    private static Set deepCopySet(Set set) {
        HashSet hashSet = new HashSet();
        for (Object obj : set) {
            hashSet.add(deepCopyObject(obj));
        }
        return hashSet;
    }

    protected abstract void checkType(F f, Object obj) throws ClassCastException;

    @Override // org.apache.thrift.orig.TBase
    public final void clear() {
        this.setField_ = null;
        this.value_ = null;
    }

    protected abstract F enumForId(short s);

    protected abstract TField getFieldDesc(F f);

    public Object getFieldValue() {
        return this.value_;
    }

    public F getSetField() {
        return this.setField_;
    }

    protected abstract TStruct getStructDesc();

    public boolean isSet() {
        return this.setField_ != null;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    @Override // org.apache.thrift.orig.TBase
    public void setFieldValue(F f, Object obj) {
        checkType(f, obj);
        this.setField_ = f;
        this.value_ = obj;
    }

    protected abstract Object standardSchemeReadValue(TProtocol tProtocol, TField tField) throws TException;

    protected abstract void standardSchemeWriteValue(TProtocol tProtocol) throws TException;

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Config.Compare.LESS_THAN);
        outline107.append(TUnion.class.getSimpleName());
        outline107.append(" ");
        if (getSetField() != null) {
            Object fieldValue = getFieldValue();
            outline107.append(getFieldDesc(getSetField()).name);
            outline107.append(":");
            if (fieldValue instanceof ByteBuffer) {
                TBaseHelper.toString((ByteBuffer) fieldValue, outline107);
            } else {
                outline107.append(fieldValue.toString());
            }
        }
        outline107.append(Config.Compare.GREATER_THAN);
        return outline107.toString();
    }

    protected abstract Object tupleSchemeReadValue(TProtocol tProtocol, short s) throws TException;

    protected abstract void tupleSchemeWriteValue(TProtocol tProtocol) throws TException;

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }

    @Override // org.apache.thrift.orig.TBase
    public Object getFieldValue(F f) {
        if (f == this.setField_) {
            return getFieldValue();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.setField_);
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(F f) {
        return this.setField_ == f;
    }

    public boolean isSet(int i) {
        return isSet((TUnion<T, F>) enumForId((short) i));
    }

    protected TUnion(F f, Object obj) {
        setFieldValue((TUnion<T, F>) f, obj);
    }

    public void setFieldValue(int i, Object obj) {
        setFieldValue((TUnion<T, F>) enumForId((short) i), obj);
    }

    public Object getFieldValue(int i) {
        return getFieldValue((TUnion<T, F>) enumForId((short) i));
    }

    protected TUnion(TUnion<T, F> tUnion) {
        if (tUnion.getClass().equals(TUnion.class)) {
            this.setField_ = tUnion.setField_;
            this.value_ = deepCopyObject(tUnion.value_);
            return;
        }
        throw new ClassCastException();
    }
}
