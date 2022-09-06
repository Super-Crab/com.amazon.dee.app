package javax.portlet;

import java.io.Serializable;
import javax.xml.namespace.QName;
/* loaded from: classes3.dex */
public interface Event {
    String getName();

    QName getQName();

    Serializable getValue();
}
