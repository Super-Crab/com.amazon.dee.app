package com.amazon.alexa.voice.ui.onedesign.util;

import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.ui.locale.GetLocaleAuthority;
import com.amazon.regulator.Component;
import com.amazon.regulator.internal.Preconditions;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class ComponentUtils {
    private ComponentUtils() {
    }

    public static Locale getLocale(Component component) {
        if (component.isRegistered(AlexaLocaleAuthority.class)) {
            return ((AlexaLocaleAuthority) component.get(AlexaLocaleAuthority.class)).getLocale();
        }
        return ((GetLocaleAuthority) component.get(GetLocaleAuthority.class)).getLocale();
    }

    public static void removeDependency(Component component, Class cls, Object obj) {
        Preconditions.nonNull(component, "Component cannot be null");
        Preconditions.nonNull(cls, "Class cannot be null");
        Preconditions.nonNull(obj, "Dependency object cannot be null");
        try {
            if (obj != component.get(cls)) {
                return;
            }
            component.remove(cls);
        } catch (IllegalStateException unused) {
        }
    }
}
