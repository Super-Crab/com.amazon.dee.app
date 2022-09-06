package lombok.launch;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/* loaded from: classes4.dex */
class ShadowClassLoader extends ClassLoader {
    private static final String SELF_NAME = "lombok/launch/ShadowClassLoader.class";
    private final String SELF_BASE;
    private final File SELF_BASE_FILE;
    private final int SELF_BASE_LENGTH;
    private Map<String, Boolean> fileRootCache;
    private final List<String> highlanders;
    private Map<String, Boolean> jarLocCache;
    private final Map<String, Object> mapJarPathToTracker;
    private final List<File> override;
    private final List<String> parentExclusion;
    private final String sclSuffix;
    private static final ConcurrentMap<String, Class<?>> highlanderMap = new ConcurrentHashMap();
    private static final Map<Object, String> mapTrackerToJarPath = new WeakHashMap();
    private static final Map<Object, Set<String>> mapTrackerToJarContents = new WeakHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShadowClassLoader(ClassLoader classLoader, String str, String str2, List<String> list, List<String> list2) {
        super(classLoader);
        String[] split;
        this.override = new ArrayList();
        this.parentExclusion = new ArrayList();
        this.highlanders = new ArrayList();
        this.mapJarPathToTracker = new HashMap();
        this.fileRootCache = new HashMap();
        this.jarLocCache = new HashMap();
        this.sclSuffix = str;
        if (list != null) {
            for (String str3 : list) {
                String replace = str3.replace(".", "/");
                if (!replace.endsWith("/")) {
                    replace = replace + "/";
                }
                this.parentExclusion.add(replace);
            }
        }
        if (list2 != null) {
            for (String str4 : list2) {
                this.highlanders.add(str4);
            }
        }
        if (str2 != null) {
            this.SELF_BASE = str2;
            this.SELF_BASE_LENGTH = str2.length();
        } else {
            URL resource = ShadowClassLoader.class.getResource("ShadowClassLoader.class");
            String url = resource == null ? null : resource.toString();
            if (url != null && url.endsWith(SELF_NAME)) {
                this.SELF_BASE_LENGTH = url.length() - 37;
                this.SELF_BASE = urlDecode(url.substring(0, this.SELF_BASE_LENGTH));
            } else {
                ClassLoader classLoader2 = ShadowClassLoader.class.getClassLoader();
                StringBuilder sb = new StringBuilder("ShadowLoader can't find itself. SCL loader type: ");
                sb.append(classLoader2 == null ? "*NULL*" : classLoader2.getClass().toString());
                throw new RuntimeException(sb.toString());
            }
        }
        if (this.SELF_BASE.startsWith("jar:file:") && this.SELF_BASE.endsWith("!/")) {
            this.SELF_BASE_FILE = new File(GeneratedOutlineSupport1.outline50(this.SELF_BASE, -2, 9));
        } else if (this.SELF_BASE.startsWith("file:")) {
            this.SELF_BASE_FILE = new File(this.SELF_BASE.substring(5));
        } else {
            this.SELF_BASE_FILE = new File(this.SELF_BASE);
        }
        String property = System.getProperty("shadow.override." + str);
        if (property == null || property.isEmpty()) {
            return;
        }
        StringBuilder sb2 = new StringBuilder("\\s*");
        sb2.append(File.pathSeparatorChar == ';' ? ";" : ":");
        sb2.append("\\s*");
        for (String str5 : property.split(sb2.toString())) {
            if (!str5.endsWith("/*")) {
                if (!str5.endsWith(String.valueOf(File.separator) + "*")) {
                    addOverrideClasspathEntry(str5);
                }
            }
            addOverrideJarDir(GeneratedOutlineSupport1.outline50(str5, -2, 0));
        }
    }

    private boolean exclusionListMatch(String str) {
        for (String str2 : this.parentExclusion) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    private Set<String> getJarMemberSet(String str) {
        try {
            JarFile jarFile = new JarFile(str);
            int highestOneBit = Integer.highestOneBit(jarFile.size());
            if (highestOneBit != jarFile.size()) {
                highestOneBit <<= 1;
            }
            if (highestOneBit == 0) {
                highestOneBit = 1;
            }
            HashSet hashSet = new HashSet(highestOneBit >> 1, 2);
            try {
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry nextElement = entries.nextElement();
                    if (!nextElement.isDirectory()) {
                        hashSet.add(nextElement.getName());
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                jarFile.close();
                throw th;
            }
            jarFile.close();
            return hashSet;
        } catch (Exception unused2) {
            return Collections.emptySet();
        }
    }

    private Set<String> getOrMakeJarListing(String str) {
        synchronized (mapTrackerToJarPath) {
            Object obj = this.mapJarPathToTracker.get(str);
            if (obj != null) {
                return mapTrackerToJarContents.get(obj);
            }
            for (Map.Entry<Object, String> entry : mapTrackerToJarPath.entrySet()) {
                if (entry.getValue().equals(str)) {
                    Object key = entry.getKey();
                    this.mapJarPathToTracker.put(str, key);
                    return mapTrackerToJarContents.get(key);
                }
            }
            Object obj2 = new Object();
            Set<String> jarMemberSet = getJarMemberSet(str);
            mapTrackerToJarContents.put(obj2, jarMemberSet);
            mapTrackerToJarPath.put(obj2, str);
            this.mapJarPathToTracker.put(str, obj2);
            return jarMemberSet;
        }
    }

    private URL getResourceFromLocation(String str, String str2, File file) {
        File absoluteFile;
        if (file.isDirectory()) {
            if (str2 != null) {
                try {
                    File file2 = new File(file, str2);
                    if (file2.isFile() && file2.canRead()) {
                        return file2.toURI().toURL();
                    }
                } catch (MalformedURLException unused) {
                }
            }
            File file3 = new File(file, str);
            if (file3.isFile() && file3.canRead()) {
                return file3.toURI().toURL();
            }
            return null;
        }
        if (file.isFile() && file.canRead()) {
            try {
                absoluteFile = file.getCanonicalFile();
            } catch (Exception unused2) {
                absoluteFile = file.getAbsoluteFile();
            }
            Set<String> orMakeJarListing = getOrMakeJarListing(absoluteFile.getAbsolutePath());
            String uri = absoluteFile.toURI().toString();
            try {
                if (orMakeJarListing.contains(str2)) {
                    return new URI("jar:" + uri + "!/" + str2).toURL();
                }
            } catch (Exception unused3) {
            }
            try {
                if (orMakeJarListing.contains(str)) {
                    return new URI("jar:" + uri + "!/" + str).toURL();
                }
            } catch (Exception unused4) {
            }
        }
        return null;
    }

    private URL getResourceSkippingSelf(String str) throws IOException {
        URL resource = super.getResource(str);
        if (resource == null) {
            return null;
        }
        if (!partOfShadow(resource.toString(), str)) {
            return resource;
        }
        Enumeration<URL> resources = super.getResources(str);
        while (resources.hasMoreElements()) {
            URL nextElement = resources.nextElement();
            if (!partOfShadow(nextElement.toString(), str)) {
                return nextElement;
            }
        }
        return null;
    }

    private URL getResource_(String str, boolean z) {
        URL resource;
        String str2 = str.endsWith(".class") ? String.valueOf(str.substring(0, str.length() - 6)) + ".SCL." + this.sclSuffix : null;
        for (File file : this.override) {
            URL resourceFromLocation = getResourceFromLocation(str, str2, file);
            if (resourceFromLocation != null) {
                return resourceFromLocation;
            }
        }
        if (!this.override.isEmpty()) {
            if (z) {
                return null;
            }
            if (str2 != null) {
                try {
                    URL resourceSkippingSelf = getResourceSkippingSelf(str2);
                    if (resourceSkippingSelf != null) {
                        return resourceSkippingSelf;
                    }
                } catch (IOException unused) {
                }
            }
            try {
                return getResourceSkippingSelf(str);
            } catch (IOException unused2) {
                return null;
            }
        }
        URL resourceFromLocation2 = getResourceFromLocation(str, str2, this.SELF_BASE_FILE);
        if (resourceFromLocation2 != null) {
            return resourceFromLocation2;
        }
        if (str2 != null && (resource = super.getResource(str2)) != null && (!z || partOfShadow(resource.toString(), str2))) {
            return resource;
        }
        URL resource2 = super.getResource(str);
        if (resource2 != null && (!z || partOfShadow(resource2.toString(), str))) {
            return resource2;
        }
        return null;
    }

    private boolean inOwnBase(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.length() == str2.length() + this.SELF_BASE_LENGTH && this.SELF_BASE.regionMatches(0, str, 0, this.SELF_BASE_LENGTH);
    }

    private boolean isPartOfShadowSuffix(String str, String str2, String str3) {
        int indexOf;
        if (str == null) {
            return false;
        }
        if (str.startsWith("file:/")) {
            String urlDecode = urlDecode(str.substring(5));
            if (urlDecode.length() > str2.length() && urlDecode.endsWith(str2) && urlDecode.charAt((urlDecode.length() - str2.length()) - 1) == '/') {
                return isPartOfShadowSuffixFileBased(urlDecode.substring(0, (urlDecode.length() - str2.length()) - 1), str3);
            }
            return false;
        } else if (str.startsWith("jar:") && (indexOf = str.indexOf(33)) != -1) {
            return isPartOfShadowSuffixJarBased(str.substring(4, indexOf), str3);
        } else {
            return false;
        }
    }

    private boolean isPartOfShadowSuffixFileBased(String str, String str2) {
        String outline91 = GeneratedOutlineSupport1.outline91(new StringBuilder(String.valueOf(str)), "::", str2);
        Boolean bool = this.fileRootCache.get(outline91);
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(String.valueOf(str) + "/META-INF/ShadowClassLoader"));
            boolean sclFileContainsSuffix = sclFileContainsSuffix(fileInputStream, str2);
            this.fileRootCache.put(outline91, Boolean.valueOf(sclFileContainsSuffix));
            fileInputStream.close();
            return sclFileContainsSuffix;
        } catch (FileNotFoundException unused) {
            this.fileRootCache.put(outline91, false);
            return false;
        } catch (IOException unused2) {
            this.fileRootCache.put(outline91, false);
            return false;
        }
    }

    private boolean isPartOfShadowSuffixJarBased(String str, String str2) {
        String outline91 = GeneratedOutlineSupport1.outline91(new StringBuilder(String.valueOf(str)), "::", str2);
        Boolean bool = this.jarLocCache.get(outline91);
        if (bool != null) {
            return bool.booleanValue();
        }
        if (str.startsWith("file:/")) {
            str = urlDecode(str.substring(5));
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    this.jarLocCache.put(outline91, false);
                    fileInputStream.close();
                    return false;
                } else if ("META-INF/ShadowClassLoader".equals(nextEntry.getName())) {
                    boolean sclFileContainsSuffix = sclFileContainsSuffix(zipInputStream, str2);
                    this.jarLocCache.put(outline91, Boolean.valueOf(sclFileContainsSuffix));
                    fileInputStream.close();
                    return sclFileContainsSuffix;
                }
            }
        } catch (FileNotFoundException unused) {
            this.jarLocCache.put(outline91, false);
            return false;
        } catch (IOException unused2) {
            this.jarLocCache.put(outline91, false);
            return false;
        }
    }

    private boolean partOfShadow(String str, String str2) {
        if (str2.startsWith("java/") || str2.startsWith("sun/")) {
            return false;
        }
        return inOwnBase(str, str2) || isPartOfShadowSuffix(str, str2, this.sclSuffix);
    }

    private static boolean sclFileContainsSuffix(InputStream inputStream, String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return false;
            }
            String trim = readLine.trim();
            if (!trim.isEmpty() && trim.charAt(0) != '#' && trim.equals(str)) {
                return true;
            }
        }
    }

    private static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new InternalError("UTF-8 not supported");
        }
    }

    public void addOverrideClasspathEntry(String str) {
        this.override.add(new File(str));
    }

    public void addOverrideJarDir(String str) {
        File[] listFiles;
        for (File file : new File(str).listFiles()) {
            if (file.getName().toLowerCase().endsWith(".jar") && file.canRead() && file.isFile()) {
                this.override.add(file);
            }
        }
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String str) {
        return getResource_(str, false);
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> getResources(String str) throws IOException {
        URL resourceFromLocation;
        String str2 = str.endsWith(".class") ? String.valueOf(str.substring(0, str.length() - 6)) + ".SCL." + this.sclSuffix : null;
        Vector vector = new Vector();
        for (File file : this.override) {
            URL resourceFromLocation2 = getResourceFromLocation(str, str2, file);
            if (resourceFromLocation2 != null) {
                vector.add(resourceFromLocation2);
            }
        }
        if (this.override.isEmpty() && (resourceFromLocation = getResourceFromLocation(str, str2, this.SELF_BASE_FILE)) != null) {
            vector.add(resourceFromLocation);
        }
        Enumeration<URL> resources = super.getResources(str);
        while (resources.hasMoreElements()) {
            URL nextElement = resources.nextElement();
            if (isPartOfShadowSuffix(nextElement.toString(), str, this.sclSuffix)) {
                vector.add(nextElement);
            }
        }
        if (str2 != null) {
            Enumeration<URL> resources2 = super.getResources(str2);
            while (resources2.hasMoreElements()) {
                URL nextElement2 = resources2.nextElement();
                if (isPartOfShadowSuffix(nextElement2.toString(), str2, this.sclSuffix)) {
                    vector.add(nextElement2);
                }
            }
        }
        return vector.elements();
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3;
        Class<?> cls4;
        Class<?> findLoadedClass = findLoadedClass(str);
        if (findLoadedClass != null) {
            return findLoadedClass;
        }
        if (this.highlanders.contains(str) && (cls4 = highlanderMap.get(str)) != null) {
            return cls4;
        }
        String str2 = String.valueOf(str.replace(".", "/")) + ".class";
        URL resource_ = getResource_(str2, true);
        if (resource_ == null && !exclusionListMatch(str2)) {
            try {
                return super.loadClass(str, z);
            } catch (ClassNotFoundException e) {
                resource_ = getResource_("secondaryLoading.SCL." + this.sclSuffix + "/" + str.replace(".", "/") + ".SCL." + this.sclSuffix, true);
                if (resource_ == null) {
                    throw e;
                }
            }
        }
        if (resource_ != null) {
            try {
                InputStream openStream = resource_.openStream();
                byte[] bArr = new byte[65536];
                int i = 0;
                while (true) {
                    int read = openStream.read(bArr, i, bArr.length - i);
                    if (read == -1) {
                        break;
                    }
                    i += read;
                    if (i == bArr.length) {
                        byte[] bArr2 = new byte[bArr.length * 2];
                        System.arraycopy(bArr, 0, bArr2, 0, i);
                        bArr = bArr2;
                    }
                }
                openStream.close();
                try {
                    cls = defineClass(str, bArr, 0, i);
                } catch (LinkageError e2) {
                    if (this.highlanders.contains(str) && (cls2 = highlanderMap.get(str)) != null) {
                        return cls2;
                    }
                    try {
                        Class<?> findLoadedClass2 = findLoadedClass(str);
                        if (findLoadedClass2 == null) {
                            throw e2;
                        }
                        cls = findLoadedClass2;
                    } catch (LinkageError unused) {
                        throw e2;
                    }
                }
                if (!this.highlanders.contains(str) || (cls3 = highlanderMap.putIfAbsent(str, cls)) == null) {
                    cls3 = cls;
                }
                if (z) {
                    resolveClass(cls3);
                }
                return cls3;
            } catch (IOException e3) {
                throw new ClassNotFoundException("I/O exception reading class " + str, e3);
            }
        }
        throw new ClassNotFoundException(str);
    }
}
