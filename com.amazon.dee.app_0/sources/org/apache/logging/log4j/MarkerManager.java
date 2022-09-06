package org.apache.logging.log4j;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StringBuilderFormattable;
/* loaded from: classes4.dex */
public final class MarkerManager {
    private static final ConcurrentMap<String, Marker> MARKERS = new ConcurrentHashMap();

    private MarkerManager() {
    }

    public static void clear() {
        MARKERS.clear();
    }

    public static boolean exists(String str) {
        return MARKERS.containsKey(str);
    }

    public static Marker getMarker(String str) {
        Marker marker = MARKERS.get(str);
        if (marker == null) {
            MARKERS.putIfAbsent(str, new Log4jMarker(str));
            return MARKERS.get(str);
        }
        return marker;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void requireNonNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(str);
    }

    /* loaded from: classes4.dex */
    public static class Log4jMarker implements Marker, StringBuilderFormattable {
        private static final long serialVersionUID = 100;
        private final String name;
        private volatile Marker[] parents;

        private Log4jMarker() {
            this.name = null;
            this.parents = null;
        }

        @PerformanceSensitive({"allocation"})
        private static void addParentInfo(StringBuilder sb, Marker... markerArr) {
            sb.append("[ ");
            int length = markerArr.length;
            boolean z = true;
            int i = 0;
            while (i < length) {
                Marker marker = markerArr[i];
                if (!z) {
                    sb.append(", ");
                }
                sb.append(marker.getName());
                Marker[] parents = marker instanceof Log4jMarker ? ((Log4jMarker) marker).parents : marker.getParents();
                if (parents != null) {
                    addParentInfo(sb, parents);
                }
                i++;
                z = false;
            }
            sb.append(" ]");
        }

        @PerformanceSensitive({"allocation", "unrolled"})
        private static boolean checkParent(Marker marker, Marker marker2) {
            if (marker == marker2) {
                return true;
            }
            Marker[] parents = marker instanceof Log4jMarker ? ((Log4jMarker) marker).parents : marker.getParents();
            if (parents != null) {
                int length = parents.length;
                if (length == 1) {
                    return checkParent(parents[0], marker2);
                }
                if (length == 2) {
                    return checkParent(parents[0], marker2) || checkParent(parents[1], marker2);
                }
                for (Marker marker3 : parents) {
                    if (checkParent(marker3, marker2)) {
                        return true;
                    }
                }
            }
            return false;
        }

        @PerformanceSensitive({"allocation"})
        private static boolean contains(Marker marker, Marker... markerArr) {
            for (Marker marker2 : markerArr) {
                if (marker2 == marker) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.apache.logging.log4j.Marker
        public synchronized Marker addParents(Marker... markerArr) {
            MarkerManager.requireNonNull(markerArr, "A parent marker must be specified");
            Marker[] markerArr2 = this.parents;
            int length = markerArr.length;
            if (markerArr2 != null) {
                int i = 0;
                for (Marker marker : markerArr) {
                    if (!contains(marker, markerArr2) && !marker.isInstanceOf(this)) {
                        i++;
                    }
                }
                if (i == 0) {
                    return this;
                }
                length = markerArr2.length + i;
            }
            Marker[] markerArr3 = new Marker[length];
            if (markerArr2 != null) {
                System.arraycopy(markerArr2, 0, markerArr3, 0, markerArr2.length);
            }
            int length2 = markerArr2 == null ? 0 : markerArr2.length;
            for (Marker marker2 : markerArr) {
                if (markerArr2 == null || (!contains(marker2, markerArr2) && !marker2.isInstanceOf(this))) {
                    markerArr3[length2] = marker2;
                    length2++;
                }
            }
            this.parents = markerArr3;
            return this;
        }

        @Override // org.apache.logging.log4j.Marker
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof Marker)) {
                return this.name.equals(((Marker) obj).getName());
            }
            return false;
        }

        @Override // org.apache.logging.log4j.util.StringBuilderFormattable
        public void formatTo(StringBuilder sb) {
            sb.append(this.name);
            Marker[] markerArr = this.parents;
            if (markerArr != null) {
                addParentInfo(sb, markerArr);
            }
        }

        @Override // org.apache.logging.log4j.Marker
        public String getName() {
            return this.name;
        }

        @Override // org.apache.logging.log4j.Marker
        public Marker[] getParents() {
            if (this.parents == null) {
                return null;
            }
            return (Marker[]) Arrays.copyOf(this.parents, this.parents.length);
        }

        @Override // org.apache.logging.log4j.Marker
        public boolean hasParents() {
            return this.parents != null;
        }

        @Override // org.apache.logging.log4j.Marker
        public int hashCode() {
            return this.name.hashCode();
        }

        @Override // org.apache.logging.log4j.Marker
        @PerformanceSensitive({"allocation", "unrolled"})
        public boolean isInstanceOf(Marker marker) {
            MarkerManager.requireNonNull(marker, "A marker parameter is required");
            if (this == marker) {
                return true;
            }
            Marker[] markerArr = this.parents;
            if (markerArr != null) {
                int length = markerArr.length;
                if (length == 1) {
                    return checkParent(markerArr[0], marker);
                }
                if (length == 2) {
                    return checkParent(markerArr[0], marker) || checkParent(markerArr[1], marker);
                }
                for (Marker marker2 : markerArr) {
                    if (checkParent(marker2, marker)) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // org.apache.logging.log4j.Marker
        public synchronized boolean remove(Marker marker) {
            MarkerManager.requireNonNull(marker, "A parent marker must be specified");
            Marker[] markerArr = this.parents;
            if (markerArr == null) {
                return false;
            }
            int length = markerArr.length;
            if (length == 1) {
                if (!markerArr[0].equals(marker)) {
                    return false;
                }
                this.parents = null;
                return true;
            }
            int i = length - 1;
            Marker[] markerArr2 = new Marker[i];
            int i2 = 0;
            for (Marker marker2 : markerArr) {
                if (!marker2.equals(marker)) {
                    if (i2 == i) {
                        return false;
                    }
                    int i3 = i2 + 1;
                    markerArr2[i2] = marker2;
                    i2 = i3;
                }
            }
            this.parents = markerArr2;
            return true;
        }

        @Override // org.apache.logging.log4j.Marker
        public Marker setParents(Marker... markerArr) {
            if (markerArr != null && markerArr.length != 0) {
                Marker[] markerArr2 = new Marker[markerArr.length];
                System.arraycopy(markerArr, 0, markerArr2, 0, markerArr.length);
                this.parents = markerArr2;
            } else {
                this.parents = null;
            }
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            formatTo(sb);
            return sb.toString();
        }

        public Log4jMarker(String str) {
            MarkerManager.requireNonNull(str, "Marker name cannot be null.");
            this.name = str;
            this.parents = null;
        }

        @Override // org.apache.logging.log4j.Marker
        @PerformanceSensitive({"allocation", "unrolled"})
        public boolean isInstanceOf(String str) {
            Marker[] markerArr;
            MarkerManager.requireNonNull(str, "A marker name is required");
            if (str.equals(getName())) {
                return true;
            }
            Marker marker = (Marker) MarkerManager.MARKERS.get(str);
            if (marker != null && (markerArr = this.parents) != null) {
                int length = markerArr.length;
                if (length == 1) {
                    return checkParent(markerArr[0], marker);
                }
                if (length == 2) {
                    return checkParent(markerArr[0], marker) || checkParent(markerArr[1], marker);
                }
                for (Marker marker2 : markerArr) {
                    if (checkParent(marker2, marker)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Deprecated
    public static Marker getMarker(String str, String str2) {
        Marker marker = MARKERS.get(str2);
        if (marker != null) {
            return getMarker(str, marker);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Parent Marker ", str2, " has not been defined"));
    }

    @Deprecated
    public static Marker getMarker(String str, Marker marker) {
        return getMarker(str).addParents(marker);
    }
}
