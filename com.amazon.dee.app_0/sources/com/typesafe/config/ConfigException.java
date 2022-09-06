package com.typesafe.config;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.impl.ConfigImplUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
/* loaded from: classes3.dex */
public abstract class ConfigException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1;
    private final transient ConfigOrigin origin;

    /* loaded from: classes3.dex */
    public static class BadBean extends BugOrBroken {
        private static final long serialVersionUID = 1;

        public BadBean(String str, Throwable th) {
            super(str, th);
        }

        public BadBean(String str) {
            this(str, null);
        }
    }

    /* loaded from: classes3.dex */
    public static class BadPath extends ConfigException {
        private static final long serialVersionUID = 1;

        public BadPath(ConfigOrigin configOrigin, String str, String str2, Throwable th) {
            super(configOrigin, str != null ? GeneratedOutlineSupport1.outline76("Invalid path '", str, "': ", str2) : str2, th);
        }

        public BadPath(ConfigOrigin configOrigin, String str, String str2) {
            this(configOrigin, str, str2, null);
        }

        public BadPath(String str, String str2, Throwable th) {
            super(str != null ? GeneratedOutlineSupport1.outline76("Invalid path '", str, "': ", str2) : str2, th);
        }

        public BadPath(String str, String str2) {
            this(str, str2, (Throwable) null);
        }

        public BadPath(ConfigOrigin configOrigin, String str) {
            this(configOrigin, (String) null, str);
        }
    }

    /* loaded from: classes3.dex */
    public static class BadValue extends ConfigException {
        private static final long serialVersionUID = 1;

        public BadValue(ConfigOrigin configOrigin, String str, String str2, Throwable th) {
            super(configOrigin, GeneratedOutlineSupport1.outline76("Invalid value at '", str, "': ", str2), th);
        }

        public BadValue(ConfigOrigin configOrigin, String str, String str2) {
            this(configOrigin, str, str2, null);
        }

        public BadValue(String str, String str2, Throwable th) {
            super(GeneratedOutlineSupport1.outline76("Invalid value at '", str, "': ", str2), th);
        }

        public BadValue(String str, String str2) {
            this(str, str2, (Throwable) null);
        }
    }

    /* loaded from: classes3.dex */
    public static class BugOrBroken extends ConfigException {
        private static final long serialVersionUID = 1;

        public BugOrBroken(String str, Throwable th) {
            super(str, th);
        }

        public BugOrBroken(String str) {
            this(str, null);
        }
    }

    /* loaded from: classes3.dex */
    public static class Generic extends ConfigException {
        private static final long serialVersionUID = 1;

        public Generic(String str, Throwable th) {
            super(str, th);
        }

        public Generic(String str) {
            this(str, null);
        }
    }

    /* loaded from: classes3.dex */
    public static class IO extends ConfigException {
        private static final long serialVersionUID = 1;

        public IO(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, str, th);
        }

        public IO(ConfigOrigin configOrigin, String str) {
            this(configOrigin, str, null);
        }
    }

    /* loaded from: classes3.dex */
    public static class Missing extends ConfigException {
        private static final long serialVersionUID = 1;

        public Missing(String str, Throwable th) {
            super(GeneratedOutlineSupport1.outline75("No configuration setting found for key '", str, "'"), th);
        }

        public Missing(String str) {
            this(str, (Throwable) null);
        }

        protected Missing(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, str, th);
        }

        protected Missing(ConfigOrigin configOrigin, String str) {
            this(configOrigin, str, null);
        }
    }

    /* loaded from: classes3.dex */
    public static class NotResolved extends BugOrBroken {
        private static final long serialVersionUID = 1;

        public NotResolved(String str, Throwable th) {
            super(str, th);
        }

        public NotResolved(String str) {
            this(str, null);
        }
    }

    /* loaded from: classes3.dex */
    public static class Null extends Missing {
        private static final long serialVersionUID = 1;

        public Null(ConfigOrigin configOrigin, String str, String str2, Throwable th) {
            super(configOrigin, makeMessage(str, str2), th);
        }

        private static String makeMessage(String str, String str2) {
            if (str2 != null) {
                return GeneratedOutlineSupport1.outline76("Configuration key '", str, "' is set to null but expected ", str2);
            }
            return GeneratedOutlineSupport1.outline75("Configuration key '", str, "' is null");
        }

        public Null(ConfigOrigin configOrigin, String str, String str2) {
            this(configOrigin, str, str2, null);
        }
    }

    /* loaded from: classes3.dex */
    public static class Parse extends ConfigException {
        private static final long serialVersionUID = 1;

        public Parse(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, str, th);
        }

        public Parse(ConfigOrigin configOrigin, String str) {
            this(configOrigin, str, null);
        }
    }

    /* loaded from: classes3.dex */
    public static class UnresolvedSubstitution extends Parse {
        private static final long serialVersionUID = 1;

        public UnresolvedSubstitution(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, GeneratedOutlineSupport1.outline72("Could not resolve substitution to a value: ", str), th);
        }

        public UnresolvedSubstitution(ConfigOrigin configOrigin, String str) {
            this(configOrigin, str, null);
        }
    }

    /* loaded from: classes3.dex */
    public static class ValidationFailed extends ConfigException {
        private static final long serialVersionUID = 1;
        private final Iterable<ValidationProblem> problems;

        public ValidationFailed(Iterable<ValidationProblem> iterable) {
            super(makeMessage(iterable), (Throwable) null);
            this.problems = iterable;
        }

        private static String makeMessage(Iterable<ValidationProblem> iterable) {
            StringBuilder sb = new StringBuilder();
            for (ValidationProblem validationProblem : iterable) {
                sb.append(validationProblem.origin().description());
                sb.append(RealTimeTextConstants.COLON_SPACE);
                sb.append(validationProblem.path());
                sb.append(RealTimeTextConstants.COLON_SPACE);
                sb.append(validationProblem.problem());
                sb.append(", ");
            }
            if (sb.length() != 0) {
                sb.setLength(sb.length() - 2);
                return sb.toString();
            }
            throw new BugOrBroken("ValidationFailed must have a non-empty list of problems");
        }

        public Iterable<ValidationProblem> problems() {
            return this.problems;
        }
    }

    /* loaded from: classes3.dex */
    public static class ValidationProblem {
        private final ConfigOrigin origin;
        private final String path;
        private final String problem;

        public ValidationProblem(String str, ConfigOrigin configOrigin, String str2) {
            this.path = str;
            this.origin = configOrigin;
            this.problem = str2;
        }

        public ConfigOrigin origin() {
            return this.origin;
        }

        public String path() {
            return this.path;
        }

        public String problem() {
            return this.problem;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ValidationProblem(");
            outline107.append(this.path);
            outline107.append(",");
            outline107.append(this.origin);
            outline107.append(",");
            return GeneratedOutlineSupport1.outline91(outline107, this.problem, ")");
        }
    }

    /* loaded from: classes3.dex */
    public static class WrongType extends ConfigException {
        private static final long serialVersionUID = 1;

        public WrongType(ConfigOrigin configOrigin, String str, String str2, String str3, Throwable th) {
            super(configOrigin, GeneratedOutlineSupport1.outline77(str, " has type ", str3, " rather than ", str2), th);
        }

        public WrongType(ConfigOrigin configOrigin, String str, String str2, String str3) {
            this(configOrigin, str, str2, str3, null);
        }

        public WrongType(ConfigOrigin configOrigin, String str, Throwable th) {
            super(configOrigin, str, th);
        }

        public WrongType(ConfigOrigin configOrigin, String str) {
            super(configOrigin, str, null);
        }
    }

    protected ConfigException(ConfigOrigin configOrigin, String str, Throwable th) {
        super(configOrigin.description() + RealTimeTextConstants.COLON_SPACE + str, th);
        this.origin = configOrigin;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        ConfigOrigin readOrigin = ConfigImplUtil.readOrigin(objectInputStream);
        try {
            Field declaredField = ConfigException.class.getDeclaredField("origin");
            declaredField.setAccessible(true);
            try {
                declaredField.set(this, readOrigin);
            } catch (IllegalAccessException e) {
                throw new IOException("unable to set origin field", e);
            } catch (IllegalArgumentException e2) {
                throw new IOException("unable to set origin field", e2);
            }
        } catch (NoSuchFieldException e3) {
            throw new IOException("ConfigException has no origin field?", e3);
        } catch (SecurityException e4) {
            throw new IOException("unable to fill out origin field in ConfigException", e4);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        ConfigImplUtil.writeOrigin(objectOutputStream, this.origin);
    }

    public ConfigOrigin origin() {
        return this.origin;
    }

    protected ConfigException(ConfigOrigin configOrigin, String str) {
        this(configOrigin.description() + RealTimeTextConstants.COLON_SPACE + str, (Throwable) null);
    }

    protected ConfigException(String str, Throwable th) {
        super(str, th);
        this.origin = null;
    }

    protected ConfigException(String str) {
        this(str, (Throwable) null);
    }
}
