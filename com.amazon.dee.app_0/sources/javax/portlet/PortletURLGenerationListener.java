package javax.portlet;
/* loaded from: classes3.dex */
public interface PortletURLGenerationListener {
    void filterActionURL(PortletURL portletURL);

    void filterRenderURL(PortletURL portletURL);

    void filterResourceURL(ResourceURL resourceURL);
}
