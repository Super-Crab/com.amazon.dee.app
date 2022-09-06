package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class Path {
    private final String first;
    private final Path remainder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path(String str, Path path) {
        this.first = str;
        this.remainder = path;
    }

    private void appendToStringBuilder(StringBuilder sb) {
        if (!hasFunkyChars(this.first) && !this.first.isEmpty()) {
            sb.append(this.first);
        } else {
            sb.append(ConfigImplUtil.renderJsonString(this.first));
        }
        if (this.remainder != null) {
            sb.append(".");
            this.remainder.appendToStringBuilder(sb);
        }
    }

    static boolean hasFunkyChars(String str) {
        int length = str.length();
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (!Character.isLetterOrDigit(charAt) && charAt != '-' && charAt != '_') {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Path newKey(String str) {
        return new Path(str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Path newPath(String str) {
        return PathParser.parsePath(str);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Path) {
            Path path = (Path) obj;
            return this.first.equals(path.first) && ConfigImplUtil.equalsHandlingNull(this.remainder, path.remainder);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String first() {
        return this.first;
    }

    public int hashCode() {
        int outline7 = GeneratedOutlineSupport1.outline7(this.first, 41, 41);
        Path path = this.remainder;
        return outline7 + (path == null ? 0 : path.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String last() {
        Path path = this;
        while (true) {
            Path path2 = path.remainder;
            if (path2 != null) {
                path = path2;
            } else {
                return path.first;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int length() {
        int i = 1;
        for (Path path = this.remainder; path != null; path = path.remainder) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path parent() {
        if (this.remainder == null) {
            return null;
        }
        PathBuilder pathBuilder = new PathBuilder();
        for (Path path = this; path.remainder != null; path = path.remainder) {
            pathBuilder.appendKey(path.first);
        }
        return pathBuilder.result();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path prepend(Path path) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.appendPath(path);
        pathBuilder.appendPath(this);
        return pathBuilder.result();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path remainder() {
        return this.remainder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String render() {
        StringBuilder sb = new StringBuilder();
        appendToStringBuilder(sb);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean startsWith(Path path) {
        if (path.length() <= length()) {
            Path path2 = this;
            while (path != null) {
                if (!path.first().equals(path2.first())) {
                    return false;
                }
                path2 = path2.remainder();
                path = path.remainder();
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path subPath(int i) {
        int i2 = i;
        Path path = this;
        while (path != null && i2 > 0) {
            i2--;
            path = path.remainder;
        }
        return path;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Path(");
        appendToStringBuilder(outline107);
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path subPath(int i, int i2) {
        if (i2 >= i) {
            Path subPath = subPath(i);
            PathBuilder pathBuilder = new PathBuilder();
            int i3 = i2 - i;
            while (i3 > 0) {
                i3--;
                pathBuilder.appendKey(subPath.first());
                subPath = subPath.remainder();
                if (subPath == null) {
                    throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline49("subPath lastIndex out of range ", i2));
                }
            }
            return pathBuilder.result();
        }
        throw new ConfigException.BugOrBroken("bad call to subPath");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path(String... strArr) {
        if (strArr.length != 0) {
            this.first = strArr[0];
            if (strArr.length > 1) {
                PathBuilder pathBuilder = new PathBuilder();
                for (int i = 1; i < strArr.length; i++) {
                    pathBuilder.appendKey(strArr[i]);
                }
                this.remainder = pathBuilder.result();
                return;
            }
            this.remainder = null;
            return;
        }
        throw new ConfigException.BugOrBroken("empty path");
    }

    Path(List<Path> list) {
        this(list.iterator());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path(Iterator<Path> it2) {
        if (it2.hasNext()) {
            Path next = it2.next();
            this.first = next.first;
            PathBuilder pathBuilder = new PathBuilder();
            Path path = next.remainder;
            if (path != null) {
                pathBuilder.appendPath(path);
            }
            while (it2.hasNext()) {
                pathBuilder.appendPath(it2.next());
            }
            this.remainder = pathBuilder.result();
            return;
        }
        throw new ConfigException.BugOrBroken("empty path");
    }
}
