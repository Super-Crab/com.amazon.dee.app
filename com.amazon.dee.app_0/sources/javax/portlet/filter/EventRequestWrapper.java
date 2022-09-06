package javax.portlet.filter;

import javax.portlet.Event;
import javax.portlet.EventRequest;
/* loaded from: classes3.dex */
public class EventRequestWrapper extends PortletRequestWrapper implements EventRequest {
    EventRequest request;

    public EventRequestWrapper(EventRequest eventRequest) {
        super(eventRequest);
        this.request = eventRequest;
    }

    @Override // javax.portlet.EventRequest
    public Event getEvent() {
        return this.request.getEvent();
    }

    @Override // javax.portlet.EventRequest
    public String getMethod() {
        return this.request.getMethod();
    }

    public void setRequest(EventRequest eventRequest) {
        if (eventRequest != null) {
            this.request = eventRequest;
            return;
        }
        throw new IllegalArgumentException("Request is null");
    }

    @Override // javax.portlet.filter.PortletRequestWrapper
    /* renamed from: getRequest */
    public EventRequest mo10373getRequest() {
        return this.request;
    }
}
