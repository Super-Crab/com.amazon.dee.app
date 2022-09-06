package javax.portlet;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.namespace.QName;
/* loaded from: classes3.dex */
public interface PortletConfig {
    Map<String, String[]> getContainerRuntimeOptions();

    String getDefaultNamespace();

    String getInitParameter(String str);

    Enumeration<String> getInitParameterNames();

    PortletContext getPortletContext();

    String getPortletName();

    Enumeration<QName> getProcessingEventQNames();

    Enumeration<String> getPublicRenderParameterNames();

    Enumeration<QName> getPublishingEventQNames();

    ResourceBundle getResourceBundle(Locale locale);

    Enumeration<Locale> getSupportedLocales();
}
