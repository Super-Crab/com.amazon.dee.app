package javax.portlet;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.namespace.QName;
/* loaded from: classes3.dex */
public abstract class GenericPortlet implements Portlet, PortletConfig, EventPortlet, ResourceServingPortlet {
    private transient PortletConfig config;
    private transient Map<String, Method> processActionHandlingMethodsMap = new HashMap();
    private transient Map<String, Method> processEventHandlingMethodsMap = new HashMap();
    private transient Map<String, Method> renderModeHandlingMethodsMap = new HashMap();

    private void cacheAnnotatedMethods() {
        Method[] methods;
        String name;
        for (Method method : GenericPortlet.class.getMethods()) {
            Annotation[] annotations = method.getAnnotations();
            if (annotations != null) {
                for (Annotation annotation : annotations) {
                    Class<? extends Annotation> annotationType = annotation.annotationType();
                    if (ProcessAction.class.equals(annotationType)) {
                        String name2 = ((ProcessAction) annotation).name();
                        if (name2 != null && name2.length() > 0) {
                            this.processActionHandlingMethodsMap.put(name2, method);
                        }
                    } else if (ProcessEvent.class.equals(annotationType)) {
                        ProcessEvent processEvent = (ProcessEvent) annotation;
                        String qname = processEvent.qname();
                        if (qname != null && qname.length() > 0) {
                            this.processEventHandlingMethodsMap.put(qname, method);
                        } else if (this.config != null) {
                            String name3 = processEvent.name();
                            if (name3 != null && name3.length() > 0) {
                                this.processEventHandlingMethodsMap.put(new QName(this.config.getDefaultNamespace(), name3).toString(), method);
                            }
                        } else {
                            throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
                        }
                    } else if (RenderMode.class.equals(annotationType) && (name = ((RenderMode) annotation).name()) != null && name.length() > 0) {
                        this.renderModeHandlingMethodsMap.put(name.toLowerCase(), method);
                    }
                }
                continue;
            }
        }
    }

    @Override // javax.portlet.Portlet
    public void destroy() {
    }

    protected void doDispatch(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        if (!renderRequest.getWindowState().equals(WindowState.MINIMIZED)) {
            PortletMode portletMode = renderRequest.getPortletMode();
            try {
                Method method = this.renderModeHandlingMethodsMap.get(portletMode.toString());
                if (method != null) {
                    method.invoke(this, renderRequest, renderResponse);
                } else if (portletMode.equals(PortletMode.VIEW)) {
                    doView(renderRequest, renderResponse);
                } else if (portletMode.equals(PortletMode.EDIT)) {
                    doEdit(renderRequest, renderResponse);
                } else if (portletMode.equals(PortletMode.HELP)) {
                    doHelp(renderRequest, renderResponse);
                } else {
                    throw new PortletException("unknown portlet mode: " + portletMode);
                }
            } catch (Exception e) {
                throw new PortletException(e);
            }
        }
    }

    protected void doEdit(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        throw new PortletException("doEdit method not implemented");
    }

    protected void doHeaders(RenderRequest renderRequest, RenderResponse renderResponse) {
    }

    protected void doHelp(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        throw new PortletException("doHelp method not implemented");
    }

    protected void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        throw new PortletException("doView method not implemented");
    }

    @Override // javax.portlet.PortletConfig
    public Map<String, String[]> getContainerRuntimeOptions() {
        return this.config.getContainerRuntimeOptions();
    }

    @Override // javax.portlet.PortletConfig
    public String getDefaultNamespace() {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getDefaultNamespace();
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    @Override // javax.portlet.PortletConfig
    public String getInitParameter(String str) {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getInitParameter(str);
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<String> getInitParameterNames() {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getInitParameterNames();
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    protected Collection<PortletMode> getNextPossiblePortletModes(RenderRequest renderRequest) {
        return null;
    }

    public PortletConfig getPortletConfig() {
        return this.config;
    }

    @Override // javax.portlet.PortletConfig
    public PortletContext getPortletContext() {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getPortletContext();
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    @Override // javax.portlet.PortletConfig
    public String getPortletName() {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getPortletName();
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<QName> getProcessingEventQNames() {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getProcessingEventQNames();
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<String> getPublicRenderParameterNames() {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getPublicRenderParameterNames();
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<QName> getPublishingEventQNames() {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getPublishingEventQNames();
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    @Override // javax.portlet.PortletConfig
    public ResourceBundle getResourceBundle(Locale locale) {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getResourceBundle(locale);
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<Locale> getSupportedLocales() {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getSupportedLocales();
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    protected String getTitle(RenderRequest renderRequest) {
        PortletConfig portletConfig = this.config;
        if (portletConfig != null) {
            return portletConfig.getResourceBundle(renderRequest.getLocale()).getString("javax.portlet.title");
        }
        throw new IllegalStateException("Config is null, please ensure that your init(config) method calls super.init(config)");
    }

    public void init() throws PortletException {
    }

    @Override // javax.portlet.Portlet
    public void init(PortletConfig portletConfig) throws PortletException {
        this.config = portletConfig;
        cacheAnnotatedMethods();
        init();
    }

    @Override // javax.portlet.Portlet
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
        try {
            Method method = this.processActionHandlingMethodsMap.get(actionRequest.getParameter(ActionRequest.ACTION_NAME));
            if (method != null) {
                method.invoke(this, actionRequest, actionResponse);
                return;
            }
            throw new PortletException("processAction method not implemented");
        } catch (Exception e) {
            throw new PortletException(e);
        }
    }

    @Override // javax.portlet.EventPortlet
    public void processEvent(EventRequest eventRequest, EventResponse eventResponse) throws PortletException, IOException {
        String qName = eventRequest.getEvent().getQName().toString();
        try {
            Method method = this.processEventHandlingMethodsMap.get(qName);
            if (method != null) {
                method.invoke(this, eventRequest, eventResponse);
                return;
            }
            int indexOf = qName.indexOf(125);
            for (int lastIndexOf = qName.lastIndexOf(46); lastIndexOf > indexOf; lastIndexOf = qName.lastIndexOf(46, lastIndexOf - 1)) {
                Method method2 = this.processEventHandlingMethodsMap.get(qName.substring(0, lastIndexOf + 1));
                if (method2 != null) {
                    method2.invoke(this, eventRequest, eventResponse);
                    return;
                } else if (lastIndexOf == 0) {
                    break;
                }
            }
            eventResponse.setRenderParameters(eventRequest);
        } catch (Exception e) {
            throw new PortletException(e);
        }
    }

    @Override // javax.portlet.Portlet
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        Object attribute = renderRequest.getAttribute(PortletRequest.RENDER_PART);
        if (attribute != null) {
            if (attribute.equals(PortletRequest.RENDER_HEADERS)) {
                doHeaders(renderRequest, renderResponse);
                Collection<PortletMode> nextPossiblePortletModes = getNextPossiblePortletModes(renderRequest);
                if (nextPossiblePortletModes != null) {
                    renderResponse.setNextPossiblePortletModes(nextPossiblePortletModes);
                }
                renderResponse.setTitle(getTitle(renderRequest));
                return;
            } else if (attribute.equals(PortletRequest.RENDER_MARKUP)) {
                doDispatch(renderRequest, renderResponse);
                return;
            } else {
                throw new PortletException("Unknown value of the 'javax.portlet.render_part' request attribute");
            }
        }
        doHeaders(renderRequest, renderResponse);
        Collection<PortletMode> nextPossiblePortletModes2 = getNextPossiblePortletModes(renderRequest);
        if (nextPossiblePortletModes2 != null) {
            renderResponse.setNextPossiblePortletModes(nextPossiblePortletModes2);
        }
        renderResponse.setTitle(getTitle(renderRequest));
        doDispatch(renderRequest, renderResponse);
    }

    @Override // javax.portlet.ResourceServingPortlet
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException {
        PortletRequestDispatcher requestDispatcher;
        if (resourceRequest.getResourceID() == null || (requestDispatcher = getPortletConfig().getPortletContext().getRequestDispatcher(resourceRequest.getResourceID())) == null) {
            return;
        }
        requestDispatcher.forward(resourceRequest, resourceResponse);
    }
}
