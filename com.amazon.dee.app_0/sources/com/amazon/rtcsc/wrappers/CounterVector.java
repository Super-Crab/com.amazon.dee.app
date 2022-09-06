package com.amazon.rtcsc.wrappers;

import com.amazon.rtcsc.wrappers.RTCCustomMetricInterface;
import java.util.AbstractList;
import java.util.RandomAccess;
/* loaded from: classes13.dex */
public class CounterVector extends AbstractList<RTCCustomMetricInterface.Counter> implements RandomAccess {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public CounterVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    private void doAdd(RTCCustomMetricInterface.Counter counter) {
        RTCControllerAndroidJNI.CounterVector_doAdd__SWIG_0(this.swigCPtr, this, RTCCustomMetricInterface.Counter.getCPtr(counter), counter);
    }

    private RTCCustomMetricInterface.Counter doGet(int i) {
        return new RTCCustomMetricInterface.Counter(RTCControllerAndroidJNI.CounterVector_doGet(this.swigCPtr, this, i), false);
    }

    private RTCCustomMetricInterface.Counter doRemove(int i) {
        return new RTCCustomMetricInterface.Counter(RTCControllerAndroidJNI.CounterVector_doRemove(this.swigCPtr, this, i), true);
    }

    private void doRemoveRange(int i, int i2) {
        RTCControllerAndroidJNI.CounterVector_doRemoveRange(this.swigCPtr, this, i, i2);
    }

    private RTCCustomMetricInterface.Counter doSet(int i, RTCCustomMetricInterface.Counter counter) {
        return new RTCCustomMetricInterface.Counter(RTCControllerAndroidJNI.CounterVector_doSet(this.swigCPtr, this, i, RTCCustomMetricInterface.Counter.getCPtr(counter), counter), true);
    }

    private int doSize() {
        return RTCControllerAndroidJNI.CounterVector_doSize(this.swigCPtr, this);
    }

    protected static long getCPtr(CounterVector counterVector) {
        if (counterVector == null) {
            return 0L;
        }
        return counterVector.swigCPtr;
    }

    public long capacity() {
        return RTCControllerAndroidJNI.CounterVector_capacity(this.swigCPtr, this);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        RTCControllerAndroidJNI.CounterVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                RTCControllerAndroidJNI.delete_CounterVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return RTCControllerAndroidJNI.CounterVector_isEmpty(this.swigCPtr, this);
    }

    @Override // java.util.AbstractList
    protected void removeRange(int i, int i2) {
        ((AbstractList) this).modCount++;
        doRemoveRange(i, i2);
    }

    public void reserve(long j) {
        RTCControllerAndroidJNI.CounterVector_reserve(this.swigCPtr, this, j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return doSize();
    }

    private void doAdd(int i, RTCCustomMetricInterface.Counter counter) {
        RTCControllerAndroidJNI.CounterVector_doAdd__SWIG_1(this.swigCPtr, this, i, RTCCustomMetricInterface.Counter.getCPtr(counter), counter);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: get */
    public RTCCustomMetricInterface.Counter mo4528get(int i) {
        return doGet(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: remove */
    public RTCCustomMetricInterface.Counter mo4529remove(int i) {
        ((AbstractList) this).modCount++;
        return doRemove(i);
    }

    @Override // java.util.AbstractList, java.util.List
    public RTCCustomMetricInterface.Counter set(int i, RTCCustomMetricInterface.Counter counter) {
        return doSet(i, counter);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(RTCCustomMetricInterface.Counter counter) {
        ((AbstractList) this).modCount++;
        doAdd(counter);
        return true;
    }

    public CounterVector(RTCCustomMetricInterface.Counter[] counterArr) {
        this();
        reserve(counterArr.length);
        for (RTCCustomMetricInterface.Counter counter : counterArr) {
            add(counter);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, RTCCustomMetricInterface.Counter counter) {
        ((AbstractList) this).modCount++;
        doAdd(i, counter);
    }

    public CounterVector(Iterable<RTCCustomMetricInterface.Counter> iterable) {
        this();
        for (RTCCustomMetricInterface.Counter counter : iterable) {
            add(counter);
        }
    }

    public CounterVector() {
        this(RTCControllerAndroidJNI.new_CounterVector__SWIG_0(), true);
    }

    public CounterVector(CounterVector counterVector) {
        this(RTCControllerAndroidJNI.new_CounterVector__SWIG_1(getCPtr(counterVector), counterVector), true);
    }

    public CounterVector(int i, RTCCustomMetricInterface.Counter counter) {
        this(RTCControllerAndroidJNI.new_CounterVector__SWIG_2(i, RTCCustomMetricInterface.Counter.getCPtr(counter), counter), true);
    }
}
