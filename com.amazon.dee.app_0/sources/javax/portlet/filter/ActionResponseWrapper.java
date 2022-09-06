package javax.portlet.filter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.xml.namespace.QName;
/* loaded from: classes3.dex */
public class ActionResponseWrapper extends PortletResponseWrapper implements ActionResponse {
    ActionResponse response;

    public ActionResponseWrapper(ActionResponse actionResponse) {
        super(actionResponse);
        this.response = actionResponse;
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

    @Override // javax.portlet.ActionResponse
    public void sendRedirect(String str) throws IOException {
        this.response.sendRedirect(str);
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

    public void setResponse(ActionResponse actionResponse) {
        if (actionResponse != null) {
            this.response = actionResponse;
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
    public ActionResponse mo10374getResponse() {
        return this.response;
    }

    @Override // javax.portlet.ActionResponse
    public void sendRedirect(String str, String str2) throws IOException {
        this.response.sendRedirect(str, str2);
    }

    @Override // javax.portlet.StateAwareResponse
    public void setEvent(String str, Serializable serializable) {
        this.response.setEvent(str, serializable);
    }

    @Override // javax.portlet.StateAwareResponse
    public void setRenderParameter(String str, String[] strArr) {
        this.response.setRenderParameter(str, strArr);
    }
}
