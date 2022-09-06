package javax.portlet;

import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import java.util.Locale;
/* loaded from: classes3.dex */
public class WindowState {
    private String _name;
    public static final WindowState NORMAL = new WindowState(BaseTemplateProvider.NON_INVITATION_KEY);
    public static final WindowState MAXIMIZED = new WindowState("maximized");
    public static final WindowState MINIMIZED = new WindowState("minimized");

    public WindowState(String str) {
        if (str != null) {
            this._name = str.toLowerCase(Locale.ENGLISH);
            return;
        }
        throw new IllegalArgumentException("WindowState name can not be NULL");
    }

    public boolean equals(Object obj) {
        if (obj instanceof WindowState) {
            return this._name.equals(((WindowState) obj)._name);
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
