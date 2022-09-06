package javax.portlet;
/* loaded from: classes3.dex */
public interface PortletURL extends BaseURL {
    PortletMode getPortletMode();

    WindowState getWindowState();

    void removePublicRenderParameter(String str);

    void setPortletMode(PortletMode portletMode) throws PortletModeException;

    void setWindowState(WindowState windowState) throws WindowStateException;
}
