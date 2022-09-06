package org.aspectj.runtime.internal;

import org.aspectj.lang.ProceedingJoinPoint;
/* loaded from: classes4.dex */
public abstract class AroundClosure {
    protected int bitflags = 1048576;
    protected Object[] preInitializationState;
    protected Object[] state;

    public AroundClosure() {
    }

    public int getFlags() {
        return this.bitflags;
    }

    public Object[] getPreInitializationState() {
        return this.preInitializationState;
    }

    public Object[] getState() {
        return this.state;
    }

    public ProceedingJoinPoint linkClosureAndJoinPoint() {
        Object[] objArr = this.state;
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) objArr[objArr.length - 1];
        proceedingJoinPoint.set$AroundClosure(this);
        return proceedingJoinPoint;
    }

    public abstract Object run(Object[] objArr) throws Throwable;

    public AroundClosure(Object[] objArr) {
        this.state = objArr;
    }

    public ProceedingJoinPoint linkClosureAndJoinPoint(int i) {
        Object[] objArr = this.state;
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) objArr[objArr.length - 1];
        proceedingJoinPoint.set$AroundClosure(this);
        this.bitflags = i;
        return proceedingJoinPoint;
    }
}
