package org.apache.logging.log4j.util;

import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import java.net.URL;
import java.security.Permission;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.apache.logging.log4j.spi.Provider;
import org.apache.logging.log4j.status.StatusLogger;
import org.osgi.framework.AdaptPermission;
import org.osgi.framework.AdminPermission;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.SynchronousBundleListener;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;
/* loaded from: classes4.dex */
public class Activator implements BundleActivator, SynchronousBundleListener {
    private boolean lockingProviderUtil;
    private static final SecurityManager SECURITY_MANAGER = System.getSecurityManager();
    private static final Logger LOGGER = StatusLogger.getLogger();

    private static void checkPermission(Permission permission) {
        SecurityManager securityManager = SECURITY_MANAGER;
        if (securityManager != null) {
            securityManager.checkPermission(permission);
        }
    }

    private void loadProvider(Bundle bundle) {
        if (bundle.getState() == 1) {
            return;
        }
        try {
            checkPermission(new AdminPermission(bundle, "resource"));
            checkPermission(new AdaptPermission(BundleWiring.class.getName(), bundle, "adapt"));
            BundleContext bundleContext = bundle.getBundleContext();
            if (bundleContext == null) {
                LOGGER.debug("Bundle {} has no context (state={}), skipping loading provider", bundle.getSymbolicName(), toStateString(bundle.getState()));
            } else {
                loadProvider(bundleContext, (BundleWiring) bundle.adapt(BundleWiring.class));
            }
        } catch (SecurityException e) {
            LOGGER.debug("Cannot access bundle [{}] contents. Ignoring.", bundle.getSymbolicName(), e);
        } catch (Exception e2) {
            LOGGER.warn("Problem checking bundle {} for Log4j 2 provider.", bundle.getSymbolicName(), e2);
        }
    }

    private String toStateString(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? i != 32 ? Integer.toString(i) : "ACTIVE" : PresenceBleService.ServiceState.STOPPING : PresenceBleService.ServiceState.STARTING : "RESOLVED" : QuebecAPIConstants.QUEBEC_BROADCAST_EXTRA_INSTALLED : "UNINSTALLED";
    }

    private void unlockIfReady() {
        if (!this.lockingProviderUtil || ProviderUtil.PROVIDERS.isEmpty()) {
            return;
        }
        ProviderUtil.STARTUP_LOCK.unlock();
        this.lockingProviderUtil = false;
    }

    public void bundleChanged(BundleEvent bundleEvent) {
        if (bundleEvent.getType() != 2) {
            return;
        }
        loadProvider(bundleEvent.getBundle());
        unlockIfReady();
    }

    public void start(BundleContext bundleContext) throws Exception {
        ProviderUtil.STARTUP_LOCK.lock();
        this.lockingProviderUtil = true;
        for (BundleWire bundleWire : ((BundleWiring) bundleContext.getBundle().adapt(BundleWiring.class)).getRequiredWires(LoggerContextFactory.class.getName())) {
            loadProvider(bundleContext, bundleWire.getProviderWiring());
        }
        bundleContext.addBundleListener(this);
        for (Bundle bundle : bundleContext.getBundles()) {
            loadProvider(bundle);
        }
        unlockIfReady();
    }

    public void stop(BundleContext bundleContext) throws Exception {
        bundleContext.removeBundleListener(this);
        unlockIfReady();
    }

    private void loadProvider(BundleContext bundleContext, BundleWiring bundleWiring) {
        try {
            Provider provider = null;
            for (ServiceReference serviceReference : bundleContext.getServiceReferences(Provider.class, "(APIVersion>=2.6.0)")) {
                Provider provider2 = (Provider) bundleContext.getService(serviceReference);
                if (provider == null || provider2.getPriority().intValue() > provider.getPriority().intValue()) {
                    provider = provider2;
                }
            }
            if (provider != null) {
                ProviderUtil.addProvider(provider);
            }
        } catch (InvalidSyntaxException e) {
            LOGGER.error("Invalid service filter: (APIVersion>=2.6.0)", e);
        }
        for (URL url : bundleWiring.findEntries("META-INF", "log4j-provider.properties", 0)) {
            ProviderUtil.loadProvider(url, bundleWiring.getClassLoader());
        }
    }
}
