package org.apache.thrift.orig.transport;

import org.apache.thrift.orig.TException;
/* loaded from: classes4.dex */
public class TTransportException extends TException {
    public static final int ALREADY_OPEN = 2;
    public static final int END_OF_FILE = 4;
    public static final int NOT_OPEN = 1;
    public static final int TIMED_OUT = 3;
    public static final int UNKNOWN = 0;
    private static final long serialVersionUID = 1;
    protected int type_;

    public TTransportException() {
        this.type_ = 0;
    }

    public int getType() {
        return this.type_;
    }

    public TTransportException(int i) {
        this.type_ = 0;
        this.type_ = i;
    }

    public TTransportException(int i, String str) {
        super(str);
        this.type_ = 0;
        this.type_ = i;
    }

    public TTransportException(String str) {
        super(str);
        this.type_ = 0;
    }

    public TTransportException(int i, Throwable th) {
        super(th);
        this.type_ = 0;
        this.type_ = i;
    }

    public TTransportException(Throwable th) {
        super(th);
        this.type_ = 0;
    }

    public TTransportException(String str, Throwable th) {
        super(str, th);
        this.type_ = 0;
    }

    public TTransportException(int i, String str, Throwable th) {
        super(str, th);
        this.type_ = 0;
        this.type_ = i;
    }
}
