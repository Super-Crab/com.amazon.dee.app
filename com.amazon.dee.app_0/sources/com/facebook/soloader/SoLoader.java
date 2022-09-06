package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.soloader.nativeloader.NativeLoader;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
@ThreadSafe
/* loaded from: classes2.dex */
public class SoLoader {
    static final boolean DEBUG = false;
    public static final int SOLOADER_ALLOW_ASYNC_INIT = 2;
    public static final int SOLOADER_DISABLE_BACKUP_SOSOURCE = 8;
    public static final int SOLOADER_DONT_TREAT_AS_SYSTEMAPP = 32;
    public static final int SOLOADER_ENABLE_EXOPACKAGE = 1;
    public static final int SOLOADER_LOOK_IN_ZIP = 4;
    public static final int SOLOADER_SKIP_MERGED_JNI_ONLOAD = 16;
    private static final String SO_STORE_NAME_MAIN = "lib-main";
    private static final String SO_STORE_NAME_SPLIT = "lib-";
    static final boolean SYSTRACE_LIBRARY_LOADING;
    static final String TAG = "SoLoader";
    private static boolean isSystemApp;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static ApplicationSoSource sApplicationSoSource;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static UnpackingSoSource[] sBackupSoSources;
    @GuardedBy("sSoSourcesLock")
    private static int sFlags;
    @Nullable
    static SoFileLoader sSoFileLoader;
    private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static SoSource[] sSoSources = null;
    @GuardedBy("sSoSourcesLock")
    private static volatile int sSoSourcesVersion = 0;
    @GuardedBy("SoLoader.class")
    private static final HashSet<String> sLoadedLibraries = new HashSet<>();
    @GuardedBy("SoLoader.class")
    private static final Map<String, Object> sLoadingLibraries = new HashMap();
    private static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    @Nullable
    private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper = null;

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(14)
    /* loaded from: classes2.dex */
    public static class Api14Utils {
        private Api14Utils() {
        }

        public static String getClassLoaderLdLoadLibrary() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader != null && !(classLoader instanceof BaseDexClassLoader)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClassLoader ");
                outline107.append(classLoader.getClass().getName());
                outline107.append(" should be of type BaseDexClassLoader");
                throw new IllegalStateException(outline107.toString());
            }
            try {
                return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
            } catch (Exception e) {
                throw new RuntimeException("Cannot call getLdLibraryPath", e);
            }
        }
    }

    @NotThreadSafe
    /* loaded from: classes2.dex */
    static class TestOnlyUtils {
        TestOnlyUtils() {
        }

        static void resetStatus() {
            synchronized (SoLoader.class) {
                SoLoader.sLoadedLibraries.clear();
                SoLoader.sLoadingLibraries.clear();
                SoLoader.sSoFileLoader = null;
            }
            setSoSources(null);
        }

        static void setSoFileLoader(SoFileLoader soFileLoader) {
            SoLoader.sSoFileLoader = soFileLoader;
        }

        static void setSoSources(SoSource[] soSourceArr) {
            SoLoader.sSoSourcesLock.writeLock().lock();
            try {
                SoSource[] unused = SoLoader.sSoSources = soSourceArr;
                SoLoader.access$208();
            } finally {
                SoLoader.sSoSourcesLock.writeLock().unlock();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class WrongAbiError extends UnsatisfiedLinkError {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        WrongAbiError(java.lang.Throwable r3, java.lang.String r4) {
            /*
                r2 = this;
                java.lang.String r0 = "APK was built for a different platform. Supported ABIs: "
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
                java.lang.String[] r1 = com.facebook.soloader.SysUtil.getSupportedAbis()
                java.lang.String r1 = java.util.Arrays.toString(r1)
                r0.append(r1)
                java.lang.String r1 = " error: "
                r0.append(r1)
                r0.append(r4)
                java.lang.String r4 = r0.toString()
                r2.<init>(r4)
                r2.initCause(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.WrongAbiError.<init>(java.lang.Throwable, java.lang.String):void");
        }
    }

    static {
        boolean z = false;
        try {
            int i = Build.VERSION.SDK_INT;
            z = true;
        } catch (NoClassDefFoundError | UnsatisfiedLinkError unused) {
        }
        SYSTRACE_LIBRARY_LOADING = z;
    }

    static /* synthetic */ int access$208() {
        int i = sSoSourcesVersion;
        sSoSourcesVersion = i + 1;
        return i;
    }

    public static boolean areSoSourcesAbisSupported() {
        String[] soSourceAbis;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources != null) {
                String[] supportedAbis = SysUtil.getSupportedAbis();
                for (SoSource soSource : sSoSources) {
                    for (String str : soSource.getSoSourceAbis()) {
                        boolean z = false;
                        for (int i = 0; i < supportedAbis.length && !z; i++) {
                            z = str.equals(supportedAbis[i]);
                        }
                        if (!z) {
                            Log.e(TAG, "abi not supported: " + str);
                        }
                    }
                }
                sSoSourcesLock.readLock().unlock();
                return true;
            }
            return false;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    private static void assertInitialized() {
        if (isInitialized()) {
            return;
        }
        throw new IllegalStateException("SoLoader.init() not yet called");
    }

    private static boolean checkIfSystemApp(Context context, int i) {
        return ((i & 32) != 0 || context == null || (context.getApplicationInfo().flags & 129) == 0) ? false : true;
    }

    public static void deinitForTest() {
        TestOnlyUtils.setSoSources(null);
    }

    private static void doLoadLibraryBySoName(String str, int i, @Nullable StrictMode.ThreadPolicy threadPolicy) throws UnsatisfiedLinkError {
        StrictMode.ThreadPolicy threadPolicy2;
        boolean z;
        int i2;
        UnpackingSoSource[] unpackingSoSourceArr;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources != null) {
                sSoSourcesLock.readLock().unlock();
                if (threadPolicy == null) {
                    threadPolicy2 = StrictMode.allowThreadDiskReads();
                    z = true;
                } else {
                    threadPolicy2 = threadPolicy;
                    z = false;
                }
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.beginTraceSection("SoLoader.loadLibrary[", str, "]");
                }
                try {
                    sSoSourcesLock.readLock().lock();
                    i2 = 0;
                    for (int i3 = 0; i2 == 0 && i3 < sSoSources.length; i3++) {
                        i2 = sSoSources[i3].loadLibrary(str, i, threadPolicy2);
                        if (i2 == 3 && sBackupSoSources != null) {
                            String str2 = "Trying backup SoSource for " + str;
                            for (UnpackingSoSource unpackingSoSource : sBackupSoSources) {
                                unpackingSoSource.prepare(str);
                                int loadLibrary = unpackingSoSource.loadLibrary(str, i, threadPolicy2);
                                if (loadLibrary == 1) {
                                    i2 = loadLibrary;
                                    break;
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    i2 = 0;
                }
                try {
                    if (SYSTRACE_LIBRARY_LOADING) {
                        Api18TraceUtils.endSection();
                    }
                    if (z) {
                        StrictMode.setThreadPolicy(threadPolicy2);
                    }
                    if (i2 != 0 && i2 != 3) {
                        return;
                    }
                    StringBuilder outline113 = GeneratedOutlineSupport1.outline113("couldn't find DSO to load: ", str);
                    sSoSourcesLock.readLock().lock();
                    for (int i4 = 0; i4 < sSoSources.length; i4++) {
                        outline113.append("\n\tSoSource ");
                        outline113.append(i4);
                        outline113.append(RealTimeTextConstants.COLON_SPACE);
                        outline113.append(sSoSources[i4].toString());
                    }
                    ApplicationSoSource applicationSoSource = sApplicationSoSource;
                    if (applicationSoSource != null) {
                        File nativeLibDirFromContext = ApplicationSoSource.getNativeLibDirFromContext(applicationSoSource.getUpdatedContext());
                        outline113.append("\n\tNative lib dir: ");
                        outline113.append(nativeLibDirFromContext.getAbsolutePath());
                        outline113.append("\n");
                    }
                    sSoSourcesLock.readLock().unlock();
                    outline113.append(" result: ");
                    outline113.append(i2);
                    String sb = outline113.toString();
                    Log.e(TAG, sb);
                    throw new UnsatisfiedLinkError(sb);
                } catch (Throwable th2) {
                    th = th2;
                    if (SYSTRACE_LIBRARY_LOADING) {
                        Api18TraceUtils.endSection();
                    }
                    if (z) {
                        StrictMode.setThreadPolicy(threadPolicy2);
                    }
                    if (i2 != 0 && i2 != 3) {
                        return;
                    }
                    StringBuilder outline1132 = GeneratedOutlineSupport1.outline113("couldn't find DSO to load: ", str);
                    String message = th.getMessage();
                    if (message == null) {
                        message = th.toString();
                    }
                    outline1132.append(" caused by: ");
                    outline1132.append(message);
                    th.printStackTrace();
                    outline1132.append(" result: ");
                    outline1132.append(i2);
                    String sb2 = outline1132.toString();
                    Log.e(TAG, sb2);
                    UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError(sb2);
                    unsatisfiedLinkError.initCause(th);
                    throw unsatisfiedLinkError;
                }
            }
            Log.e(TAG, "Could not load: " + str + " because no SO source exists");
            throw new UnsatisfiedLinkError("couldn't find DSO to load: " + str);
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static String[] getLibraryDependencies(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String[] strArr = null;
            if (sSoSources != null) {
                int i = 0;
                while (strArr == null) {
                    if (i >= sSoSources.length) {
                        break;
                    }
                    strArr = sSoSources[i].getLibraryDependencies(str);
                    i++;
                }
            }
            return strArr;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static String getLibraryPath(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String str2 = null;
            if (sSoSources != null) {
                int i = 0;
                while (str2 == null) {
                    if (i >= sSoSources.length) {
                        break;
                    }
                    str2 = sSoSources[i].getLibraryPath(str);
                    i++;
                }
            }
            return str2;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    private static Method getNativeLoadRuntimeMethod() {
        if (Build.VERSION.SDK_INT > 27) {
            return null;
        }
        try {
            Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException | SecurityException e) {
            Log.w(TAG, "Cannot get nativeLoad method", e);
            return null;
        }
    }

    public static int getSoSourcesVersion() {
        return sSoSourcesVersion;
    }

    public static void init(Context context, int i) throws IOException {
        init(context, i, null);
    }

    private static synchronized void initSoLoader(@Nullable SoFileLoader soFileLoader) {
        synchronized (SoLoader.class) {
            if (soFileLoader != null) {
                sSoFileLoader = soFileLoader;
                return;
            }
            final Runtime runtime = Runtime.getRuntime();
            final Method nativeLoadRuntimeMethod = getNativeLoadRuntimeMethod();
            final boolean z = nativeLoadRuntimeMethod != null;
            final String classLoaderLdLoadLibrary = z ? Api14Utils.getClassLoaderLdLoadLibrary() : null;
            final String makeNonZipPath = makeNonZipPath(classLoaderLdLoadLibrary);
            sSoFileLoader = new SoFileLoader() { // from class: com.facebook.soloader.SoLoader.1
                private String getLibHash(String str) {
                    try {
                        File file = new File(str);
                        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read > 0) {
                                    messageDigest.update(bArr, 0, read);
                                } else {
                                    String format = String.format("%32x", new BigInteger(1, messageDigest.digest()));
                                    fileInputStream.close();
                                    return format;
                                }
                            }
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                                throw th2;
                            }
                        }
                    } catch (IOException e) {
                        return e.toString();
                    } catch (SecurityException e2) {
                        return e2.toString();
                    } catch (NoSuchAlgorithmException e3) {
                        return e3.toString();
                    }
                }

                /* JADX WARN: Removed duplicated region for block: B:49:0x00a7  */
                @Override // com.facebook.soloader.SoFileLoader
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void load(java.lang.String r9, int r10) {
                    /*
                        Method dump skipped, instructions count: 204
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.AnonymousClass1.load(java.lang.String, int):void");
                }
            };
        }
    }

    private static void initSoSources(Context context, int i, @Nullable SoFileLoader soFileLoader) throws IOException {
        String[] split;
        int i2;
        ApkSoSource apkSoSource;
        ApkSoSource apkSoSource2;
        sSoSourcesLock.writeLock().lock();
        try {
            if (sSoSources == null) {
                sFlags = i;
                ArrayList arrayList = new ArrayList();
                String str = System.getenv("LD_LIBRARY_PATH");
                if (str == null) {
                    str = SysUtil.is64Bit() ? "/vendor/lib64:/system/lib64" : "/vendor/lib:/system/lib";
                }
                for (String str2 : str.split(":")) {
                    String str3 = "adding system library source: " + str2;
                    arrayList.add(new DirectorySoSource(new File(str2), 2));
                }
                if (context != null) {
                    if ((i & 1) != 0) {
                        sBackupSoSources = null;
                        arrayList.add(0, new ExoSoSource(context, SO_STORE_NAME_MAIN));
                    } else {
                        if (isSystemApp) {
                            i2 = 0;
                        } else {
                            int i3 = Build.VERSION.SDK_INT;
                            sApplicationSoSource = new ApplicationSoSource(context, 0);
                            String str4 = "adding application source: " + sApplicationSoSource.toString();
                            arrayList.add(0, sApplicationSoSource);
                            i2 = 1;
                        }
                        if ((sFlags & 8) != 0) {
                            sBackupSoSources = null;
                        } else {
                            File file = new File(context.getApplicationInfo().sourceDir);
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(new ApkSoSource(context, file, SO_STORE_NAME_MAIN, i2));
                            String str5 = "adding backup source from : " + apkSoSource.toString();
                            int i4 = Build.VERSION.SDK_INT;
                            if (context.getApplicationInfo().splitSourceDirs != null) {
                                String[] strArr = context.getApplicationInfo().splitSourceDirs;
                                int length = strArr.length;
                                int i5 = 0;
                                int i6 = 0;
                                while (i5 < length) {
                                    File file2 = new File(strArr[i5]);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(SO_STORE_NAME_SPLIT);
                                    sb.append(i6);
                                    String str6 = "adding backup source: " + apkSoSource2.toString();
                                    arrayList2.add(new ApkSoSource(context, file2, sb.toString(), i2));
                                    i5++;
                                    i6++;
                                }
                            }
                            sBackupSoSources = (UnpackingSoSource[]) arrayList2.toArray(new UnpackingSoSource[arrayList2.size()]);
                            arrayList.addAll(0, arrayList2);
                        }
                    }
                }
                SoSource[] soSourceArr = (SoSource[]) arrayList.toArray(new SoSource[arrayList.size()]);
                int makePrepareFlags = makePrepareFlags();
                int length2 = soSourceArr.length;
                while (true) {
                    int i7 = length2 - 1;
                    if (length2 <= 0) {
                        break;
                    }
                    String str7 = "Preparing SO source: " + soSourceArr[i7];
                    soSourceArr[i7].prepare(makePrepareFlags);
                    length2 = i7;
                }
                sSoSources = soSourceArr;
                sSoSourcesVersion++;
                String str8 = "init finish: " + sSoSources.length + " SO sources prepared";
            }
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    public static boolean isInitialized() {
        sSoSourcesLock.readLock().lock();
        try {
            return sSoSources != null;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static boolean loadLibrary(String str) {
        return loadLibrary(str, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadLibraryBySoName(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        loadLibraryBySoNameImpl(str, null, null, i, threadPolicy);
    }

    private static boolean loadLibraryBySoNameImpl(String str, @Nullable String str2, @Nullable String str3, int i, @Nullable StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        Object obj;
        boolean z2 = false;
        if (TextUtils.isEmpty(str2) || !sLoadedAndMergedLibraries.contains(str2)) {
            synchronized (SoLoader.class) {
                if (!sLoadedLibraries.contains(str)) {
                    z = false;
                } else if (str3 == null) {
                    return false;
                } else {
                    z = true;
                }
                if (sLoadingLibraries.containsKey(str)) {
                    obj = sLoadingLibraries.get(str);
                } else {
                    obj = new Object();
                    sLoadingLibraries.put(str, obj);
                }
                sSoSourcesLock.readLock().lock();
                try {
                    synchronized (obj) {
                        if (!z) {
                            synchronized (SoLoader.class) {
                                if (sLoadedLibraries.contains(str)) {
                                    if (str3 == null) {
                                        return false;
                                    }
                                    z = true;
                                }
                                if (!z) {
                                    try {
                                        String str4 = "About to load: " + str;
                                        doLoadLibraryBySoName(str, i, threadPolicy);
                                        synchronized (SoLoader.class) {
                                            String str5 = "Loaded: " + str;
                                            sLoadedLibraries.add(str);
                                        }
                                    } catch (UnsatisfiedLinkError e) {
                                        String message = e.getMessage();
                                        if (message != null && message.contains("unexpected e_machine:")) {
                                            throw new WrongAbiError(e, message.substring(message.lastIndexOf("unexpected e_machine:")));
                                        }
                                        throw e;
                                    }
                                }
                            }
                        }
                        if ((i & 16) == 0) {
                            if (!TextUtils.isEmpty(str2) && sLoadedAndMergedLibraries.contains(str2)) {
                                z2 = true;
                            }
                            if (str3 != null && !z2) {
                                if (SYSTRACE_LIBRARY_LOADING) {
                                    Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", str2, "]");
                                }
                                try {
                                    try {
                                        String str6 = "About to merge: " + str2 + " / " + str;
                                        MergedSoMapping.invokeJniOnload(str2);
                                        sLoadedAndMergedLibraries.add(str2);
                                    } catch (UnsatisfiedLinkError e2) {
                                        throw new RuntimeException("Failed to call JNI_OnLoad from '" + str2 + "', which has been merged into '" + str + "'.  See comment for details.", e2);
                                    }
                                } finally {
                                    if (SYSTRACE_LIBRARY_LOADING) {
                                        Api18TraceUtils.endSection();
                                    }
                                }
                            }
                        }
                        sSoSourcesLock.readLock().unlock();
                        return !z;
                    }
                } finally {
                    sSoSourcesLock.readLock().unlock();
                }
            }
        }
        return false;
    }

    public static String makeLdLibraryPath() {
        sSoSourcesLock.readLock().lock();
        try {
            assertInitialized();
            ArrayList arrayList = new ArrayList();
            SoSource[] soSourceArr = sSoSources;
            if (soSourceArr != null) {
                for (SoSource soSource : soSourceArr) {
                    soSource.addToLdLibraryPath(arrayList);
                }
            }
            String join = TextUtils.join(":", arrayList);
            String str = "makeLdLibraryPath final path: " + join;
            return join;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static String makeNonZipPath(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":");
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            if (!str2.contains("!")) {
                arrayList.add(str2);
            }
        }
        return TextUtils.join(":", arrayList);
    }

    private static int makePrepareFlags() {
        sSoSourcesLock.writeLock().lock();
        try {
            return (sFlags & 2) != 0 ? 1 : 0;
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    public static void prependSoSource(SoSource soSource) throws IOException {
        sSoSourcesLock.writeLock().lock();
        try {
            String str = "Prepending to SO sources: " + soSource;
            assertInitialized();
            soSource.prepare(makePrepareFlags());
            SoSource[] soSourceArr = new SoSource[sSoSources.length + 1];
            soSourceArr[0] = soSource;
            System.arraycopy(sSoSources, 0, soSourceArr, 1, sSoSources.length);
            sSoSources = soSourceArr;
            sSoSourcesVersion++;
            String str2 = "Prepended to SO sources: " + soSource;
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    public static void setInTestMode() {
        TestOnlyUtils.setSoSources(new SoSource[]{new NoopSoSource()});
    }

    public static void setSystemLoadLibraryWrapper(SystemLoadLibraryWrapper systemLoadLibraryWrapper) {
        sSystemLoadLibraryWrapper = systemLoadLibraryWrapper;
    }

    public static File unpackLibraryAndDependencies(String str) throws UnsatisfiedLinkError {
        assertInitialized();
        try {
            return unpackLibraryBySoName(System.mapLibraryName(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static File unpackLibraryBySoName(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            for (SoSource soSource : sSoSources) {
                File unpackLibrary = soSource.unpackLibrary(str);
                if (unpackLibrary != null) {
                    return unpackLibrary;
                }
            }
            sSoSourcesLock.readLock().unlock();
            throw new FileNotFoundException(str);
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static void init(Context context, int i, @Nullable SoFileLoader soFileLoader) throws IOException {
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            isSystemApp = checkIfSystemApp(context, i);
            initSoLoader(soFileLoader);
            initSoSources(context, i, soFileLoader);
            NativeLoader.initIfUninitialized(new NativeLoaderToSoLoaderDelegate());
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }

    public static boolean loadLibrary(String str, int i) throws UnsatisfiedLinkError {
        SystemLoadLibraryWrapper systemLoadLibraryWrapper;
        boolean z;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    assertInitialized();
                } else {
                    synchronized (SoLoader.class) {
                        z = !sLoadedLibraries.contains(str);
                        if (z) {
                            if (sSystemLoadLibraryWrapper != null) {
                                sSystemLoadLibraryWrapper.loadLibrary(str);
                            } else {
                                System.loadLibrary(str);
                            }
                        }
                    }
                    return z;
                }
            }
            sSoSourcesLock.readLock().unlock();
            if (isSystemApp && (systemLoadLibraryWrapper = sSystemLoadLibraryWrapper) != null) {
                systemLoadLibraryWrapper.loadLibrary(str);
                return true;
            }
            String mapLibName = MergedSoMapping.mapLibName(str);
            return loadLibraryBySoName(System.mapLibraryName(mapLibName != null ? mapLibName : str), str, mapLibName, i, null);
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    private static boolean loadLibraryBySoName(String str, @Nullable String str2, @Nullable String str3, int i, @Nullable StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        boolean z2 = false;
        do {
            try {
                z2 = loadLibraryBySoNameImpl(str, str2, str3, i, threadPolicy);
                z = false;
                continue;
            } catch (UnsatisfiedLinkError e) {
                int i2 = sSoSourcesVersion;
                sSoSourcesLock.writeLock().lock();
                try {
                    try {
                        z = true;
                        if (sApplicationSoSource == null || !sApplicationSoSource.checkAndMaybeUpdate()) {
                            z = false;
                        } else {
                            Log.w(TAG, "sApplicationSoSource updated during load: " + str + ", attempting load again.");
                            sSoSourcesVersion = sSoSourcesVersion + 1;
                        }
                        sSoSourcesLock.writeLock().unlock();
                        if (sSoSourcesVersion == i2) {
                            throw e;
                        }
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                } catch (Throwable th) {
                    sSoSourcesLock.writeLock().unlock();
                    throw th;
                }
            }
        } while (z);
        return z2;
    }

    public static void init(Context context, boolean z) {
        try {
            init(context, z ? 1 : 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
