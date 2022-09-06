package kotlin.reflect.jvm.internal.impl.name;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class Name implements Comparable<Name> {
    @NotNull
    private final String name;
    private final boolean special;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2) ? 2 : 3];
        if (i == 1 || i == 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/name/Name";
        } else {
            objArr[0] = "name";
        }
        if (i == 1) {
            objArr[1] = "asString";
        } else if (i != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/name/Name";
        } else {
            objArr[1] = "getIdentifier";
        }
        switch (i) {
            case 1:
            case 2:
                break;
            case 3:
                objArr[2] = "identifier";
                break;
            case 4:
                objArr[2] = "isValidIdentifier";
                break;
            case 5:
                objArr[2] = "special";
                break;
            case 6:
                objArr[2] = "guessByFirstCharacter";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i == 1 || i == 2) {
            throw new IllegalStateException(format);
        }
    }

    private Name(@NotNull String str, boolean z) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        this.name = str;
        this.special = z;
    }

    @NotNull
    public static Name guessByFirstCharacter(@NotNull String str) {
        if (str == null) {
            $$$reportNull$$$0(6);
        }
        if (str.startsWith(Config.Compare.LESS_THAN)) {
            return special(str);
        }
        return identifier(str);
    }

    @NotNull
    public static Name identifier(@NotNull String str) {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        return new Name(str, false);
    }

    public static boolean isValidIdentifier(@NotNull String str) {
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        if (str.isEmpty() || str.startsWith(Config.Compare.LESS_THAN)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '.' || charAt == '/' || charAt == '\\') {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static Name special(@NotNull String str) {
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        if (str.startsWith(Config.Compare.LESS_THAN)) {
            return new Name(str, true);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("special name must start with '<': ", str));
    }

    @NotNull
    public String asString() {
        String str = this.name;
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        return str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Name)) {
            return false;
        }
        Name name = (Name) obj;
        return this.special == name.special && this.name.equals(name.name);
    }

    @NotNull
    public String getIdentifier() {
        if (!this.special) {
            String asString = asString();
            if (asString == null) {
                $$$reportNull$$$0(2);
            }
            return asString;
        }
        throw new IllegalStateException("not identifier: " + this);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + (this.special ? 1 : 0);
    }

    public boolean isSpecial() {
        return this.special;
    }

    public String toString() {
        return this.name;
    }

    @Override // java.lang.Comparable
    public int compareTo(Name name) {
        return this.name.compareTo(name.name);
    }
}
