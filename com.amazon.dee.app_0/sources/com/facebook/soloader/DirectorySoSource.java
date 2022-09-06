package com.facebook.soloader;

import android.os.StrictMode;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes2.dex */
public class DirectorySoSource extends SoSource {
    public static final int ON_LD_LIBRARY_PATH = 2;
    public static final int RESOLVE_DEPENDENCIES = 1;
    protected final int flags;
    protected final File soDirectory;

    public DirectorySoSource(File file, int i) {
        this.soDirectory = file;
        this.flags = i;
    }

    private static String[] getDependencies(File file) throws IOException {
        if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
            Api18TraceUtils.beginTraceSection("SoLoader.getElfDependencies[", file.getName(), "]");
        }
        try {
            return MinElf.extract_DT_NEEDED(file);
        } finally {
            if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
                Api18TraceUtils.endSection();
            }
        }
    }

    private static void loadDependencies(File file, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        String[] dependencies = getDependencies(file);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Loading lib dependencies: ");
        outline107.append(Arrays.toString(dependencies));
        outline107.toString();
        for (String str : dependencies) {
            if (!str.startsWith("/")) {
                SoLoader.loadLibraryBySoName(str, i | 1, threadPolicy);
            }
        }
    }

    @Override // com.facebook.soloader.SoSource
    public void addToLdLibraryPath(Collection<String> collection) {
        collection.add(this.soDirectory.getAbsolutePath());
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public String[] getLibraryDependencies(String str) throws IOException {
        File file = new File(this.soDirectory, str);
        if (file.exists()) {
            return getDependencies(file);
        }
        return null;
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public String getLibraryPath(String str) throws IOException {
        File file = new File(this.soDirectory, str);
        if (file.exists()) {
            return file.getCanonicalPath();
        }
        return null;
    }

    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return loadLibraryFrom(str, i, this.soDirectory, threadPolicy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int loadLibraryFrom(String str, int i, File file, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, " not found on ");
            outline113.append(file.getCanonicalPath());
            outline113.toString();
            return 0;
        }
        StringBuilder outline1132 = GeneratedOutlineSupport1.outline113(str, " found on ");
        outline1132.append(file.getCanonicalPath());
        outline1132.toString();
        if ((i & 1) != 0 && (this.flags & 2) != 0) {
            GeneratedOutlineSupport1.outline158(str, " loaded implicitly");
            return 2;
        }
        if ((this.flags & 1) != 0) {
            loadDependencies(file2, i, threadPolicy);
        } else {
            GeneratedOutlineSupport1.outline158("Not resolving dependencies for ", str);
        }
        try {
            SoLoader.sSoFileLoader.load(file2.getAbsolutePath(), i);
            return 1;
        } catch (UnsatisfiedLinkError e) {
            if (!e.getMessage().contains("bad ELF magic")) {
                throw e;
            }
            return 3;
        }
    }

    @Override // com.facebook.soloader.SoSource
    public String toString() {
        String name;
        try {
            name = String.valueOf(this.soDirectory.getCanonicalPath());
        } catch (IOException unused) {
            name = this.soDirectory.getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[root = ");
        sb.append(name);
        sb.append(" flags = ");
        return GeneratedOutlineSupport1.outline85(sb, this.flags, JsonReaderKt.END_LIST);
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public File unpackLibrary(String str) throws IOException {
        File file = new File(this.soDirectory, str);
        if (file.exists()) {
            return file;
        }
        return null;
    }
}
