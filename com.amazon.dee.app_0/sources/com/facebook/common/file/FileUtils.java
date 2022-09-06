package com.facebook.common.file;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.internal.Preconditions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class FileUtils {

    /* loaded from: classes2.dex */
    public static class CreateDirectoryException extends IOException {
        public CreateDirectoryException(String message) {
            super(message);
        }

        public CreateDirectoryException(String message, Throwable innerException) {
            super(message);
            initCause(innerException);
        }
    }

    /* loaded from: classes2.dex */
    public static class FileDeleteException extends IOException {
        public FileDeleteException(String message) {
            super(message);
        }
    }

    /* loaded from: classes2.dex */
    public static class ParentDirNotFoundException extends FileNotFoundException {
        public ParentDirNotFoundException(String message) {
            super(message);
        }
    }

    /* loaded from: classes2.dex */
    public static class RenameException extends IOException {
        public RenameException(String message) {
            super(message);
        }

        public RenameException(String message, @Nullable Throwable innerException) {
            super(message);
            initCause(innerException);
        }
    }

    public static void mkdirs(File directory) throws CreateDirectoryException {
        if (directory.exists()) {
            if (directory.isDirectory()) {
                return;
            }
            if (!directory.delete()) {
                throw new CreateDirectoryException(directory.getAbsolutePath(), new FileDeleteException(directory.getAbsolutePath()));
            }
        }
        if (directory.mkdirs() || directory.isDirectory()) {
            return;
        }
        throw new CreateDirectoryException(directory.getAbsolutePath());
    }

    public static void rename(File source, File target) throws RenameException {
        Preconditions.checkNotNull(source);
        Preconditions.checkNotNull(target);
        target.delete();
        if (source.renameTo(target)) {
            return;
        }
        Throwable th = null;
        if (!target.exists()) {
            if (source.getParentFile().exists()) {
                if (!source.exists()) {
                    th = new FileNotFoundException(source.getAbsolutePath());
                }
            } else {
                th = new ParentDirNotFoundException(source.getAbsolutePath());
            }
        } else {
            th = new FileDeleteException(target.getAbsolutePath());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown error renaming ");
        outline107.append(source.getAbsolutePath());
        outline107.append(" to ");
        outline107.append(target.getAbsolutePath());
        throw new RenameException(outline107.toString(), th);
    }
}
