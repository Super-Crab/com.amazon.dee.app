package javax.portlet.filter;

import java.io.Serializable;
import java.util.Map;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.xml.namespace.QName;
/* loaded from: classes3.dex */
public class EventResponseWrapper extends PortletResponseWrapper implements EventResponse {
    EventResponse response;

    public EventResponseWrapper(EventResponse eventResponse) {
        super(eventResponse);
        this.response = eventResponse;
    }

    @Override // javax.portlet.StateAwareResponse
    public PortletMode getPortletMode() {
        return this.response.getPortletMode();
    }

    @Override // javax.portlet.StateAwareResponse
    public Map<String, String[]> getRenderParameterMap() {
        return this.response.getRenderParameterMap();
    }

    @Override // javax.portlet.StateAwareResponse
    public WindowState getWindowState() {
        return this.response.getWindowState();
    }

    @Override // javax.portlet.StateAwareResponse
    public void removePublicRenderParameter(String str) {
        this.response.removePublicRenderParameter(str);
    }

    @Override // javax.portlet.StateAwareResponse
    public void setEvent(QName qName, Serializable serializable) {
        this.response.setEvent(qName, serializable);
    }

    @Override // javax.portlet.StateAwareResponse
    public void setPortletMode(PortletMode portletMode) throws PortletModeException {
        this.response.setPortletMode(portletMode);
    }

    @Override // javax.portlet.StateAwareResponse
    public void setRenderParameter(String str, String str2) {
        this.response.setRenderParameter(str, str2);
    }

    @Override // javax.portlet.StateAwareResponse
    public void setRenderParameters(Map<String, String[]> map) {
        this.response.setRenderParameters(map);
    }

    public void setResponse(EventResponse eventResponse) {
        if (eventResponse != null) {
            this.response = eventResponse;
            return;
        }
        throw new IllegalArgumentException("Response is null");
    }

    @Override // javax.portlet.StateAwareResponse
    public void setWindowState(WindowState windowState) throws WindowStateException {
        this.response.setWindowState(windowState);
    }

    @Override // javax.portlet.filter.PortletResponseWrapper
    /* renamed from: getResponse */
    public EventResponse mo10374getResponse() {
        return this.response;
    }

    @Override // javax.portlet.StateAwareResponse
    public void setEvent(String str, Serializable serializable) {
        this.response.setEvent(str, serializable);
    }

    @Override // javax.portlet.StateAwareResponse
    public void setRenderParameter(String str, String[] strArr) {
        this.response.setRenderParameter(str, strArr);
    }

    @Override // javax.portlet.EventResponse
    public void setRenderParameters(EventRequest eventRequest) {
        this.response.setRenderParameters(eventRequest);
    }
}
