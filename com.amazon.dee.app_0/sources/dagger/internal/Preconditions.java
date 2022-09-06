package dagger.internal;
/* loaded from: classes3.dex */
public final class Preconditions {
    private Preconditions() {
    }

    public static <T> void checkBuilderRequirement(T requirement, Class<T> clazz) {
        if (requirement != null) {
            return;
        }
        throw new IllegalStateException(clazz.getCanonicalName() + " must be set");
    }

    public static <T> T checkNotNull(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }

    public static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(errorMessage);
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object errorMessageArg) {
        String valueOf;
        if (reference == null) {
            if (errorMessageTemplate.contains("%s")) {
                if (errorMessageTemplate.indexOf("%s") == errorMessageTemplate.lastIndexOf("%s")) {
                    if (errorMessageArg instanceof Class) {
                        valueOf = ((Class) errorMessageArg).getCanonicalName();
                    } else {
                        valueOf = String.valueOf(errorMessageArg);
                    }
                    throw new NullPointerException(errorMessageTemplate.replace("%s", valueOf));
                }
                throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
            }
            throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
        }
        return reference;
    }
}
