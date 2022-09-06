package org.apache.logging.log4j;

import java.io.Serializable;
/* loaded from: classes4.dex */
public interface Marker extends Serializable {
    Marker addParents(Marker... markerArr);

    boolean equals(Object obj);

    String getName();

    Marker[] getParents();

    boolean hasParents();

    int hashCode();

    boolean isInstanceOf(String str);

    boolean isInstanceOf(Marker marker);

    boolean remove(Marker marker);

    Marker setParents(Marker... markerArr);
}
