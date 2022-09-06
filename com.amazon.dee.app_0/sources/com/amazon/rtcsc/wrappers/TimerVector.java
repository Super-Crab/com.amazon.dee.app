package com.amazon.rtcsc.wrappers;

import com.amazon.rtcsc.wrappers.RTCCustomMetricInterface;
import java.util.AbstractList;
import java.util.RandomAccess;
/* loaded from: classes13.dex */
public class TimerVector extends AbstractList<RTCCustomMetricInterface.Timer> implements RandomAccess {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TimerVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    private void doAdd(RTCCustomMetricInterface.Timer timer) {
        RTCControllerAndroidJNI.TimerVector_doAdd__SWIG_0(this.swigCPtr, this, RTCCustomMetricInterface.Timer.getCPtr(timer), timer);
    }

    private RTCCustomMetricInterface.Timer doGet(int i) {
        return new RTCCustomMetricInterface.Timer(RTCControllerAndroidJNI.TimerVector_doGet(this.swigCPtr, this, i), false);
    }

    private RTCCustomMetricInterface.Timer doRemove(int i) {
        return new RTCCustomMetricInterface.Timer(RTCControllerAndroidJNI.TimerVector_doRemove(this.swigCPtr, this, i), true);
    }

    private void doRemoveRange(int i, int i2) {
        RTCControllerAndroidJNI.TimerVector_doRemoveRange(this.swigCPtr, this, i, i2);
    }

    private RTCCustomMetricInterface.Timer doSet(int i, RTCCustomMetricInterface.Timer timer) {
        return new RTCCustomMetricInterface.Timer(RTCControllerAndroidJNI.TimerVector_doSet(this.swigCPtr, this, i, RTCCustomMetricInterface.Timer.getCPtr(timer), timer), true);
    }

    private int doSize() {
        return RTCControllerAndroidJNI.TimerVector_doSize(this.swigCPtr, this);
    }

    protected static long getCPtr(TimerVector timerVector) {
        if (timerVector == null) {
            return 0L;
        }
        return timerVector.swigCPtr;
    }

    public long capacity() {
        return RTCControllerAndroidJNI.TimerVector_capacity(this.swigCPtr, this);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        RTCControllerAndroidJNI.TimerVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                RTCControllerAndroidJNI.delete_TimerVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return RTCControllerAndroidJNI.TimerVector_isEmpty(this.swigCPtr, this);
    }

    @Override // java.util.AbstractList
    protected void removeRange(int i, int i2) {
        ((AbstractList) this).modCount++;
        doRemoveRange(i, i2);
    }

    public void reserve(long j) {
        RTCControllerAndroidJNI.TimerVector_reserve(this.swigCPtr, this, j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return doSize();
    }

    private void doAdd(int i, RTCCustomMetricInterface.Timer timer) {
        RTCControllerAndroidJNI.TimerVector_doAdd__SWIG_1(this.swigCPtr, this, i, RTCCustomMetricInterface.Timer.getCPtr(timer), timer);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: get */
    public RTCCustomMetricInterface.Timer mo4532get(int i) {
        return doGet(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: remove */
    public RTCCustomMetricInterface.Timer mo4533remove(int i) {
        ((AbstractList) this).modCount++;
        return doRemove(i);
    }

    @Override // java.util.AbstractList, java.util.List
    public RTCCustomMetricInterface.Timer set(int i, RTCCustomMetricInterface.Timer timer) {
        return doSet(i, timer);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(RTCCustomMetricInterface.Timer timer) {
        ((AbstractList) this).modCount++;
        doAdd(timer);
        return true;
    }

    public TimerVector(RTCCustomMetricInterface.Timer[] timerArr) {
        this();
        reserve(timerArr.length);
        for (RTCCustomMetricInterface.Timer timer : timerArr) {
            add(timer);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, RTCCustomMetricInterface.Timer timer) {
        ((AbstractList) this).modCount++;
        doAdd(i, timer);
    }

    public TimerVector(Iterable<RTCCustomMetricInterface.Timer> iterable) {
        this();
        for (RTCCustomMetricInterface.Timer timer : iterable) {
            add(timer);
        }
    }

    public TimerVector() {
        this(RTCControllerAndroidJNI.new_TimerVector__SWIG_0(), true);
    }

    public TimerVector(TimerVector timerVector) {
        this(RTCControllerAndroidJNI.new_TimerVector__SWIG_1(getCPtr(timerVector), timerVector), true);
    }

    public TimerVector(int i, RTCCustomMetricInterface.Timer timer) {
        this(RTCControllerAndroidJNI.new_TimerVector__SWIG_2(i, RTCCustomMetricInterface.Timer.getCPtr(timer), timer), true);
    }
}
