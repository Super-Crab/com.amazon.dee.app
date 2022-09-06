package com.drew.metadata;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public final class Metadata {
    @NotNull
    private final List<Directory> _directories = new ArrayList();

    public <T extends Directory> void addDirectory(@NotNull T t) {
        this._directories.add(t);
    }

    public boolean containsDirectoryOfType(Class<? extends Directory> cls) {
        for (Directory directory : this._directories) {
            if (cls.isAssignableFrom(directory.getClass())) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public Iterable<Directory> getDirectories() {
        return this._directories;
    }

    @NotNull
    public <T extends Directory> Collection<T> getDirectoriesOfType(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        for (Directory directory : this._directories) {
            if (cls.isAssignableFrom(directory.getClass())) {
                arrayList.add(directory);
            }
        }
        return arrayList;
    }

    public int getDirectoryCount() {
        return this._directories.size();
    }

    @Nullable
    public <T extends Directory> T getFirstDirectoryOfType(@NotNull Class<T> cls) {
        Iterator<Directory> it2 = this._directories.iterator();
        while (it2.hasNext()) {
            T t = (T) it2.next();
            if (cls.isAssignableFrom(t.getClass())) {
                return t;
            }
        }
        return null;
    }

    public boolean hasErrors() {
        for (Directory directory : getDirectories()) {
            if (directory.hasErrors()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        int directoryCount = getDirectoryCount();
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(directoryCount);
        objArr[1] = directoryCount == 1 ? "directory" : "directories";
        return String.format("Metadata (%d %s)", objArr);
    }
}
