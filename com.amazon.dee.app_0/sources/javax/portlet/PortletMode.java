package javax.portlet;

import com.amazon.alexa.routing.data.RouteName;
import java.util.Locale;
/* loaded from: classes3.dex */
public class PortletMode {
    private String _name;
    public static final PortletMode VIEW = new PortletMode("view");
    public static final PortletMode EDIT = new PortletMode("edit");
    public static final PortletMode HELP = new PortletMode(RouteName.HELP);

    public PortletMode(String str) {
        if (str != null) {
            this._name = str.toLowerCase(Locale.ENGLISH);
            return;
        }
        throw new IllegalArgumentException("PortletMode name can not be NULL");
    }

    public boolean equals(Object obj) {
        if (obj instanceof PortletMode) {
            return this._name.equals(((PortletMode) obj)._name);
        }
        return false;
    }

    public int hashCode() {
        return this._name.hashCode();
    }

    public String toString() {
        return this._name;
    }
}
