package org.apache.commons.io;

import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
/* loaded from: classes4.dex */
public class FileDeleteStrategy {
    private final String name;
    public static final FileDeleteStrategy NORMAL = new FileDeleteStrategy(DCMEndpoint.Priority.NORMAL);
    public static final FileDeleteStrategy FORCE = new ForceFileDeleteStrategy();

    /* loaded from: classes4.dex */
    static class ForceFileDeleteStrategy extends FileDeleteStrategy {
        ForceFileDeleteStrategy() {
            super("Force");
        }

        @Override // org.apache.commons.io.FileDeleteStrategy
        protected boolean doDelete(File file) throws IOException {
            FileUtils.forceDelete(file);
            return true;
        }
    }

    protected FileDeleteStrategy(String str) {
        this.name = str;
    }

    public void delete(File file) throws IOException {
        if (!file.exists() || doDelete(file)) {
            return;
        }
        throw new IOException(GeneratedOutlineSupport1.outline63("Deletion failed: ", file));
    }

    public boolean deleteQuietly(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        try {
            return doDelete(file);
        } catch (IOException unused) {
            return false;
        }
    }

    protected boolean doDelete(File file) throws IOException {
        return file.delete();
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("FileDeleteStrategy["), this.name, "]");
    }
}
