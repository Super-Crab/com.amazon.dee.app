package amazon.communication;
/* loaded from: classes.dex */
public class ImpossibleToHandleMessageException extends RuntimeException {
    private static final long serialVersionUID = -6511578409390306348L;

    public ImpossibleToHandleMessageException(String str) {
        super(str);
    }

    public ImpossibleToHandleMessageException(Throwable th) {
        super(th);
    }

    public ImpossibleToHandleMessageException(String str, Throwable th) {
        super(str, th);
    }
}
