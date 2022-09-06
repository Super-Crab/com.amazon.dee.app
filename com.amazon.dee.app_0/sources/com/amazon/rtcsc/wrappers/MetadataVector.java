package com.amazon.rtcsc.wrappers;

import com.amazon.rtcsc.wrappers.RTCCustomMetricInterface;
import java.util.AbstractList;
import java.util.RandomAccess;
/* loaded from: classes13.dex */
public class MetadataVector extends AbstractList<RTCCustomMetricInterface.Metadata> implements RandomAccess {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MetadataVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    private void doAdd(RTCCustomMetricInterface.Metadata metadata) {
        RTCControllerAndroidJNI.MetadataVector_doAdd__SWIG_0(this.swigCPtr, this, RTCCustomMetricInterface.Metadata.getCPtr(metadata), metadata);
    }

    private RTCCustomMetricInterface.Metadata doGet(int i) {
        return new RTCCustomMetricInterface.Metadata(RTCControllerAndroidJNI.MetadataVector_doGet(this.swigCPtr, this, i), false);
    }

    private RTCCustomMetricInterface.Metadata doRemove(int i) {
        return new RTCCustomMetricInterface.Metadata(RTCControllerAndroidJNI.MetadataVector_doRemove(this.swigCPtr, this, i), true);
    }

    private void doRemoveRange(int i, int i2) {
        RTCControllerAndroidJNI.MetadataVector_doRemoveRange(this.swigCPtr, this, i, i2);
    }

    private RTCCustomMetricInterface.Metadata doSet(int i, RTCCustomMetricInterface.Metadata metadata) {
        return new RTCCustomMetricInterface.Metadata(RTCControllerAndroidJNI.MetadataVector_doSet(this.swigCPtr, this, i, RTCCustomMetricInterface.Metadata.getCPtr(metadata), metadata), true);
    }

    private int doSize() {
        return RTCControllerAndroidJNI.MetadataVector_doSize(this.swigCPtr, this);
    }

    protected static long getCPtr(MetadataVector metadataVector) {
        if (metadataVector == null) {
            return 0L;
        }
        return metadataVector.swigCPtr;
    }

    public long capacity() {
        return RTCControllerAndroidJNI.MetadataVector_capacity(this.swigCPtr, this);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        RTCControllerAndroidJNI.MetadataVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                RTCControllerAndroidJNI.delete_MetadataVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return RTCControllerAndroidJNI.MetadataVector_isEmpty(this.swigCPtr, this);
    }

    @Override // java.util.AbstractList
    protected void removeRange(int i, int i2) {
        ((AbstractList) this).modCount++;
        doRemoveRange(i, i2);
    }

    public void reserve(long j) {
        RTCControllerAndroidJNI.MetadataVector_reserve(this.swigCPtr, this, j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return doSize();
    }

    private void doAdd(int i, RTCCustomMetricInterface.Metadata metadata) {
        RTCControllerAndroidJNI.MetadataVector_doAdd__SWIG_1(this.swigCPtr, this, i, RTCCustomMetricInterface.Metadata.getCPtr(metadata), metadata);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: get */
    public RTCCustomMetricInterface.Metadata mo4530get(int i) {
        return doGet(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: remove */
    public RTCCustomMetricInterface.Metadata mo4531remove(int i) {
        ((AbstractList) this).modCount++;
        return doRemove(i);
    }

    @Override // java.util.AbstractList, java.util.List
    public RTCCustomMetricInterface.Metadata set(int i, RTCCustomMetricInterface.Metadata metadata) {
        return doSet(i, metadata);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(RTCCustomMetricInterface.Metadata metadata) {
        ((AbstractList) this).modCount++;
        doAdd(metadata);
        return true;
    }

    public MetadataVector(RTCCustomMetricInterface.Metadata[] metadataArr) {
        this();
        reserve(metadataArr.length);
        for (RTCCustomMetricInterface.Metadata metadata : metadataArr) {
            add(metadata);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, RTCCustomMetricInterface.Metadata metadata) {
        ((AbstractList) this).modCount++;
        doAdd(i, metadata);
    }

    public MetadataVector(Iterable<RTCCustomMetricInterface.Metadata> iterable) {
        this();
        for (RTCCustomMetricInterface.Metadata metadata : iterable) {
            add(metadata);
        }
    }

    public MetadataVector() {
        this(RTCControllerAndroidJNI.new_MetadataVector__SWIG_0(), true);
    }

    public MetadataVector(MetadataVector metadataVector) {
        this(RTCControllerAndroidJNI.new_MetadataVector__SWIG_1(getCPtr(metadataVector), metadataVector), true);
    }

    public MetadataVector(int i, RTCCustomMetricInterface.Metadata metadata) {
        this(RTCControllerAndroidJNI.new_MetadataVector__SWIG_2(i, RTCCustomMetricInterface.Metadata.getCPtr(metadata), metadata), true);
    }
}
