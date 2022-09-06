package com.google.common.reflect;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes3.dex */
public final class ClassPath {
    private static final String CLASS_FILE_NAME_EXTENSION = ".class";
    private final ImmutableSet<ResourceInfo> resources;
    private static final Logger logger = Logger.getLogger(ClassPath.class.getName());
    private static final Predicate<ClassInfo> IS_TOP_LEVEL = new Predicate<ClassInfo>() { // from class: com.google.common.reflect.ClassPath.1
        @Override // com.google.common.base.Predicate
        public boolean apply(ClassInfo classInfo) {
            return classInfo.className.indexOf(36) == -1;
        }
    };
    private static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.on(" ").omitEmptyStrings();

    @Beta
    /* loaded from: classes3.dex */
    public static final class ClassInfo extends ResourceInfo {
        private final String className;

        ClassInfo(File file, String str, ClassLoader classLoader) {
            super(file, str, classLoader);
            this.className = ClassPath.getClassName(str);
        }

        public String getName() {
            return this.className;
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.className);
        }

        public String getSimpleName() {
            int lastIndexOf = this.className.lastIndexOf(36);
            if (lastIndexOf != -1) {
                return CharMatcher.inRange('0', '9').trimLeadingFrom(this.className.substring(lastIndexOf + 1));
            }
            String packageName = getPackageName();
            if (packageName.isEmpty()) {
                return this.className;
            }
            return this.className.substring(packageName.length() + 1);
        }

        public Class<?> load() {
            try {
                return this.loader.loadClass(this.className);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.reflect.ClassPath.ResourceInfo
        public String toString() {
            return this.className;
        }
    }

    @VisibleForTesting
    /* loaded from: classes3.dex */
    static final class DefaultScanner extends Scanner {
        private final SetMultimap<ClassLoader, ResourceInfo> resources = MultimapBuilder.hashKeys().linkedHashSetValues().mo7950build();

        DefaultScanner() {
        }

        ImmutableSet<ResourceInfo> getResources() {
            return ImmutableSet.copyOf((Collection) this.resources.mo7876values());
        }

        @Override // com.google.common.reflect.ClassPath.Scanner
        protected void scanResource(ResourceInfo resourceInfo) {
            this.resources.put(resourceInfo.loader, resourceInfo);
        }
    }

    @Beta
    /* loaded from: classes3.dex */
    public static class ResourceInfo {
        private final File file;
        final ClassLoader loader;
        private final String resourceName;

        ResourceInfo(File file, String str, ClassLoader classLoader) {
            this.file = (File) Preconditions.checkNotNull(file);
            this.resourceName = (String) Preconditions.checkNotNull(str);
            this.loader = (ClassLoader) Preconditions.checkNotNull(classLoader);
        }

        static ResourceInfo of(File file, String str, ClassLoader classLoader) {
            if (str.endsWith(ClassPath.CLASS_FILE_NAME_EXTENSION)) {
                return new ClassInfo(file, str, classLoader);
            }
            return new ResourceInfo(file, str, classLoader);
        }

        public final ByteSource asByteSource() {
            return Resources.asByteSource(url());
        }

        public final CharSource asCharSource(Charset charset) {
            return Resources.asCharSource(url(), charset);
        }

        public boolean equals(Object obj) {
            if (obj instanceof ResourceInfo) {
                ResourceInfo resourceInfo = (ResourceInfo) obj;
                return this.resourceName.equals(resourceInfo.resourceName) && this.loader == resourceInfo.loader;
            }
            return false;
        }

        final File getFile() {
            return this.file;
        }

        public final String getResourceName() {
            return this.resourceName;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public String toString() {
            return this.resourceName;
        }

        public final URL url() {
            URL resource = this.loader.getResource(this.resourceName);
            if (resource != null) {
                return resource;
            }
            throw new NoSuchElementException(this.resourceName);
        }
    }

    private ClassPath(ImmutableSet<ResourceInfo> immutableSet) {
        this.resources = immutableSet;
    }

    public static ClassPath from(ClassLoader classLoader) throws IOException {
        DefaultScanner defaultScanner = new DefaultScanner();
        defaultScanner.scan(classLoader);
        return new ClassPath(defaultScanner.getResources());
    }

    @VisibleForTesting
    static String getClassName(String str) {
        return str.substring(0, str.length() - 6).replace('/', '.');
    }

    @VisibleForTesting
    static File toFile(URL url) {
        Preconditions.checkArgument(url.getProtocol().equals("file"));
        try {
            return new File(url.toURI());
        } catch (URISyntaxException unused) {
            return new File(url.getPath());
        }
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.resources;
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).filter(IS_TOP_LEVEL).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String str) {
        Preconditions.checkNotNull(str);
        StringBuilder sb = new StringBuilder(str.length() + 1);
        sb.append(str);
        sb.append('.');
        String sb2 = sb.toString();
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> mo8029iterator = getTopLevelClasses().mo8029iterator();
        while (mo8029iterator.hasNext()) {
            ClassInfo next = mo8029iterator.next();
            if (next.getName().startsWith(sb2)) {
                builder.mo7849add((ImmutableSet.Builder) next);
            }
        }
        return builder.mo7852build();
    }

    /* loaded from: classes3.dex */
    static abstract class Scanner {
        private final Set<File> scannedUris = Sets.newConcurrentHashSet();

        Scanner() {
        }

        private static ImmutableList<URL> getClassLoaderUrls(ClassLoader classLoader) {
            if (classLoader instanceof URLClassLoader) {
                return ImmutableList.copyOf(((URLClassLoader) classLoader).getURLs());
            }
            if (classLoader.equals(ClassLoader.getSystemClassLoader())) {
                return parseJavaClassPath();
            }
            return ImmutableList.of();
        }

        @VisibleForTesting
        static ImmutableMap<File, ClassLoader> getClassPathEntries(ClassLoader classLoader) {
            LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
            ClassLoader parent = classLoader.getParent();
            if (parent != null) {
                newLinkedHashMap.putAll(getClassPathEntries(parent));
            }
            UnmodifiableIterator<URL> mo8029iterator = getClassLoaderUrls(classLoader).mo8029iterator();
            while (mo8029iterator.hasNext()) {
                URL next = mo8029iterator.next();
                if (next.getProtocol().equals("file")) {
                    File file = ClassPath.toFile(next);
                    if (!newLinkedHashMap.containsKey(file)) {
                        newLinkedHashMap.put(file, classLoader);
                    }
                }
            }
            return ImmutableMap.copyOf((Map) newLinkedHashMap);
        }

        @VisibleForTesting
        static URL getClassPathEntry(File file, String str) throws MalformedURLException {
            return new URL(file.toURI().toURL(), str);
        }

        @VisibleForTesting
        static ImmutableSet<File> getClassPathFromManifest(File file, @NullableDecl Manifest manifest) {
            if (manifest == null) {
                return ImmutableSet.of();
            }
            ImmutableSet.Builder builder = ImmutableSet.builder();
            String value = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
            if (value != null) {
                for (String str : ClassPath.CLASS_PATH_ATTRIBUTE_SEPARATOR.split(value)) {
                    try {
                        URL classPathEntry = getClassPathEntry(file, str);
                        if (classPathEntry.getProtocol().equals("file")) {
                            builder.mo7849add((ImmutableSet.Builder) ClassPath.toFile(classPathEntry));
                        }
                    } catch (MalformedURLException unused) {
                        Logger logger = ClassPath.logger;
                        String valueOf = String.valueOf(str);
                        logger.warning(valueOf.length() != 0 ? "Invalid Class-Path entry: ".concat(valueOf) : new String("Invalid Class-Path entry: "));
                    }
                }
            }
            return builder.mo7852build();
        }

        @VisibleForTesting
        static ImmutableList<URL> parseJavaClassPath() {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (String str : Splitter.on(StandardSystemProperty.PATH_SEPARATOR.value()).split(StandardSystemProperty.JAVA_CLASS_PATH.value())) {
                try {
                    try {
                        builder.mo7849add((ImmutableList.Builder) new File(str).toURI().toURL());
                    } catch (SecurityException unused) {
                        builder.mo7849add((ImmutableList.Builder) new URL("file", (String) null, new File(str).getAbsolutePath()));
                    }
                } catch (MalformedURLException e) {
                    Logger logger = ClassPath.logger;
                    Level level = Level.WARNING;
                    String valueOf = String.valueOf(str);
                    logger.log(level, valueOf.length() != 0 ? "malformed classpath entry: ".concat(valueOf) : new String("malformed classpath entry: "), (Throwable) e);
                }
            }
            return builder.mo7852build();
        }

        private void scanDirectory(ClassLoader classLoader, File file) throws IOException {
            HashSet hashSet = new HashSet();
            hashSet.add(file.getCanonicalFile());
            scanDirectory(file, classLoader, "", hashSet);
        }

        private void scanJar(File file, ClassLoader classLoader) throws IOException {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    UnmodifiableIterator<File> mo8029iterator = getClassPathFromManifest(file, jarFile.getManifest()).mo8029iterator();
                    while (mo8029iterator.hasNext()) {
                        scan(mo8029iterator.next(), classLoader);
                    }
                    scanJarFile(classLoader, jarFile);
                } finally {
                    try {
                        jarFile.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (IOException unused2) {
            }
        }

        public final void scan(ClassLoader classLoader) throws IOException {
            UnmodifiableIterator<Map.Entry<File, ClassLoader>> mo8029iterator = getClassPathEntries(classLoader).mo7737entrySet().mo8029iterator();
            while (mo8029iterator.hasNext()) {
                Map.Entry<File, ClassLoader> next = mo8029iterator.next();
                scan(next.getKey(), next.getValue());
            }
        }

        protected void scanFrom(File file, ClassLoader classLoader) throws IOException {
            try {
                if (!file.exists()) {
                    return;
                }
                if (file.isDirectory()) {
                    scanDirectory(classLoader, file);
                } else {
                    scanJar(file, classLoader);
                }
            } catch (SecurityException e) {
                Logger logger = ClassPath.logger;
                String valueOf = String.valueOf(file);
                String valueOf2 = String.valueOf(e);
                logger.warning(GeneratedOutlineSupport1.outline31(valueOf2.length() + valueOf.length() + 16, "Cannot access ", valueOf, RealTimeTextConstants.COLON_SPACE, valueOf2));
            }
        }

        @VisibleForTesting
        void scanJarFile(ClassLoader classLoader, JarFile jarFile) throws IOException {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                if (!nextElement.isDirectory() && !nextElement.getName().equals("META-INF/MANIFEST.MF")) {
                    scanResource(ResourceInfo.of(new File(jarFile.getName()), nextElement.getName(), classLoader));
                }
            }
        }

        protected abstract void scanResource(ResourceInfo resourceInfo) throws IOException;

        @VisibleForTesting
        final void scan(File file, ClassLoader classLoader) throws IOException {
            if (this.scannedUris.add(file.getCanonicalFile())) {
                scanFrom(file, classLoader);
            }
        }

        private void scanDirectory(File file, ClassLoader classLoader, String str, Set<File> set) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                Logger logger = ClassPath.logger;
                String valueOf = String.valueOf(file);
                StringBuilder sb = new StringBuilder(valueOf.length() + 22);
                sb.append("Cannot read directory ");
                sb.append(valueOf);
                logger.warning(sb.toString());
                return;
            }
            for (File file2 : listFiles) {
                String name = file2.getName();
                if (file2.isDirectory()) {
                    File canonicalFile = file2.getCanonicalFile();
                    if (set.add(canonicalFile)) {
                        scanDirectory(canonicalFile, classLoader, GeneratedOutlineSupport1.outline30(GeneratedOutlineSupport1.outline6(name, GeneratedOutlineSupport1.outline6(str, 1)), str, name, "/"), set);
                        set.remove(canonicalFile);
                    }
                } else {
                    String valueOf2 = String.valueOf(str);
                    String valueOf3 = String.valueOf(name);
                    String concat = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
                    if (!concat.equals("META-INF/MANIFEST.MF")) {
                        scanResource(ResourceInfo.of(file2, concat, classLoader));
                    }
                }
            }
        }
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String str) {
        Preconditions.checkNotNull(str);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> mo8029iterator = getTopLevelClasses().mo8029iterator();
        while (mo8029iterator.hasNext()) {
            ClassInfo next = mo8029iterator.next();
            if (next.getPackageName().equals(str)) {
                builder.mo7849add((ImmutableSet.Builder) next);
            }
        }
        return builder.mo7852build();
    }
}
