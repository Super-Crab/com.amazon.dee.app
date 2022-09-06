package javax.servlet.http;
/* loaded from: classes3.dex */
public class HttpSessionBindingEvent extends HttpSessionEvent {
    private String name;
    private Object value;

    public HttpSessionBindingEvent(HttpSession httpSession, String str) {
        super(httpSession);
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    @Override // javax.servlet.http.HttpSessionEvent
    public HttpSession getSession() {
        return super.getSession();
    }

    public Object getValue() {
        return this.value;
    }

    public HttpSessionBindingEvent(HttpSession httpSession, String str, Object obj) {
        super(httpSession);
        this.name = str;
        this.value = obj;
    }
}
