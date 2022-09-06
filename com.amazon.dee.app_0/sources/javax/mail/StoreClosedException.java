package javax.mail;
/* loaded from: classes3.dex */
public class StoreClosedException extends MessagingException {
    private static final long serialVersionUID = -3145392336120082655L;
    private transient Store store;

    public StoreClosedException(Store store) {
        this(store, null);
    }

    public Store getStore() {
        return this.store;
    }

    public StoreClosedException(Store store, String str) {
        super(str);
        this.store = store;
    }

    public StoreClosedException(Store store, String str, Exception exc) {
        super(str, exc);
        this.store = store;
    }
}
