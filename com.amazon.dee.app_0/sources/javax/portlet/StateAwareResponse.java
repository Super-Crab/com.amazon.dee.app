package javax.portlet;

import java.io.Serializable;
import java.util.Map;
import javax.xml.namespace.QName;
/* loaded from: classes3.dex */
public interface StateAwareResponse extends PortletResponse {
    PortletMode getPortletMode();

    Map<String, String[]> getRenderParameterMap();

    WindowState getWindowState();

    void removePublicRenderParameter(String str);

    void setEvent(String str, Serializable serializable);

    void setEvent(QName qName, Serializable serializable);

    void setPortletMode(PortletMode portletMode) throws PortletModeException;

    void setRenderParameter(String str, String str2);

    void setRenderParameter(String str, String[] strArr);

    void setRenderParameters(Map<String, String[]> map);

    void setWindowState(WindowState windowState) throws WindowStateException;
}
