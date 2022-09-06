package javax.servlet;
/* loaded from: classes3.dex */
public class ServletException extends Exception {
    public ServletException() {
    }

    public Throwable getRootCause() {
        return getCause();
    }

    public ServletException(String str) {
        super(str);
    }

    public ServletException(String str, Throwable th) {
        super(str, th);
    }

    public ServletException(Throwable th) {
        super(th);
    }
}
