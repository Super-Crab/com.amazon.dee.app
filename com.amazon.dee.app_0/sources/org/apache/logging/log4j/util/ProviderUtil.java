package org.apache.logging.log4j.util;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.Provider;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;
/* loaded from: classes4.dex */
public final class ProviderUtil {
    private static final String API_VERSION = "Log4jAPIVersion";
    protected static final String PROVIDER_RESOURCE = "META-INF/log4j-provider.properties";
    private static volatile ProviderUtil instance;
    protected static final Collection<Provider> PROVIDERS = new HashSet();
    protected static final Lock STARTUP_LOCK = new ReentrantLock();
    private static final String[] COMPATIBLE_API_VERSIONS = {"2.0.0", "2.1.0", "2.2.0", "2.3.0", "2.4.0", "2.5.0", "2.6.0"};
    private static final Logger LOGGER = StatusLogger.getLogger();

    private ProviderUtil() {
        ClassLoader[] classLoaders;
        for (ClassLoader classLoader : LoaderUtil.getClassLoaders()) {
            try {
                loadProviders(classLoader);
            } catch (Throwable th) {
                LOGGER.debug("Unable to retrieve provider from ClassLoader {}", classLoader, th);
            }
        }
        for (LoaderUtil.UrlResource urlResource : LoaderUtil.findUrlResources(PROVIDER_RESOURCE)) {
            loadProvider(urlResource.getUrl(), urlResource.getClassLoader());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void addProvider(Provider provider) {
        PROVIDERS.add(provider);
        LOGGER.debug("Loaded Provider {}", provider);
    }

    public static ClassLoader findClassLoader() {
        return LoaderUtil.getThreadContextClassLoader();
    }

    public static Iterable<Provider> getProviders() {
        lazyInit();
        return PROVIDERS;
    }

    public static boolean hasProviders() {
        lazyInit();
        return !PROVIDERS.isEmpty();
    }

    protected static void lazyInit() {
        if (instance == null) {
            try {
                STARTUP_LOCK.lockInterruptibly();
                if (instance == null) {
                    instance = new ProviderUtil();
                }
                STARTUP_LOCK.unlock();
            } catch (InterruptedException e) {
                LOGGER.fatal("Interrupted before Log4j Providers could be loaded.", (Throwable) e);
                Thread.currentThread().interrupt();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void loadProvider(URL url, ClassLoader classLoader) {
        try {
            Properties loadClose = PropertiesUtil.loadClose(url.openStream(), url);
            if (!validVersion(loadClose.getProperty(API_VERSION))) {
                return;
            }
            Provider provider = new Provider(loadClose, url, classLoader);
            PROVIDERS.add(provider);
            LOGGER.debug("Loaded Provider {}", provider);
        } catch (IOException e) {
            LOGGER.error("Unable to open {}", url, e);
        }
    }

    protected static void loadProviders(ClassLoader classLoader) {
        Iterator it2 = ServiceLoader.load(Provider.class, classLoader).iterator();
        while (it2.hasNext()) {
            Provider provider = (Provider) it2.next();
            if (validVersion(provider.getVersions()) && !PROVIDERS.contains(provider)) {
                PROVIDERS.add(provider);
            }
        }
    }

    private static boolean validVersion(String str) {
        for (String str2 : COMPATIBLE_API_VERSIONS) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    protected static void loadProviders(Enumeration<URL> enumeration, ClassLoader classLoader) {
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                loadProvider(enumeration.nextElement(), classLoader);
            }
        }
    }
}
