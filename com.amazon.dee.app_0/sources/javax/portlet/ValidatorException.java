package javax.portlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
/* loaded from: classes3.dex */
public class ValidatorException extends PortletException {
    private static final long serialVersionUID = 1;
    private transient ArrayList<String> failedKeyVector;

    private ValidatorException() {
        this.failedKeyVector = new ArrayList<>();
    }

    public Enumeration<String> getFailedKeys() {
        return Collections.enumeration(this.failedKeyVector);
    }

    public ValidatorException(String str, Collection<String> collection) {
        super(str);
        this.failedKeyVector = new ArrayList<>();
        if (collection != null) {
            this.failedKeyVector.addAll(collection);
        }
    }

    public ValidatorException(String str, Throwable th, Collection<String> collection) {
        super(str, th);
        this.failedKeyVector = new ArrayList<>();
        if (collection != null) {
            this.failedKeyVector.addAll(collection);
        }
    }

    public ValidatorException(Throwable th, Collection<String> collection) {
        super(th);
        this.failedKeyVector = new ArrayList<>();
        if (collection != null) {
            this.failedKeyVector.addAll(collection);
        }
    }
}
