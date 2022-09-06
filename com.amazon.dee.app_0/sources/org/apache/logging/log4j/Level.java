package org.apache.logging.log4j;

import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.wav.WavDirectory;
import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.spi.StandardLevel;
import org.apache.logging.log4j.util.Strings;
/* loaded from: classes4.dex */
public final class Level implements Comparable<Level>, Serializable {
    public static final String CATEGORY = "Level";
    private static final long serialVersionUID = 1581082;
    private final int intLevel;
    private final String name;
    private final StandardLevel standardLevel;
    private static final ConcurrentMap<String, Level> LEVELS = new ConcurrentHashMap();
    public static final Level OFF = new Level("OFF", StandardLevel.OFF.intLevel());
    public static final Level FATAL = new Level("FATAL", StandardLevel.FATAL.intLevel());
    public static final Level ERROR = new Level("ERROR", StandardLevel.ERROR.intLevel());
    public static final Level WARN = new Level(DriveModeVoxUiConstants.WARN, StandardLevel.WARN.intLevel());
    public static final Level INFO = new Level(WavDirectory.LIST_INFO, StandardLevel.INFO.intLevel());
    public static final Level DEBUG = new Level("DEBUG", StandardLevel.DEBUG.intLevel());
    public static final Level TRACE = new Level("TRACE", StandardLevel.TRACE.intLevel());
    public static final Level ALL = new Level("ALL", StandardLevel.ALL.intLevel());

    private Level(String str, int i) {
        if (!Strings.isEmpty(str)) {
            if (i >= 0) {
                this.name = str;
                this.intLevel = i;
                this.standardLevel = StandardLevel.getStandardLevel(i);
                if (LEVELS.putIfAbsent(str, this) != null) {
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline75("Level ", str, " has already been defined."));
                }
                return;
            }
            throw new IllegalArgumentException("Illegal Level int less than zero.");
        }
        throw new IllegalArgumentException("Illegal null or empty Level name.");
    }

    public static Level forName(String str, int i) {
        Level level = LEVELS.get(str);
        if (level != null) {
            return level;
        }
        try {
            return new Level(str, i);
        } catch (IllegalStateException unused) {
            return LEVELS.get(str);
        }
    }

    public static Level getLevel(String str) {
        return LEVELS.get(str);
    }

    public static Level toLevel(String str) {
        return toLevel(str, DEBUG);
    }

    private static String toUpperCase(String str) {
        return str.toUpperCase(Locale.ENGLISH);
    }

    public static Level valueOf(String str) {
        Objects.requireNonNull(str, "No level name given.");
        String upperCase = toUpperCase(str.trim());
        Level level = LEVELS.get(upperCase);
        if (level != null) {
            return level;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Unknown level constant [", upperCase, "]."));
    }

    public static Level[] values() {
        Collection<Level> values = LEVELS.values();
        return (Level[]) values.toArray(new Level[values.size()]);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Level) && obj == this;
    }

    public Class<Level> getDeclaringClass() {
        return Level.class;
    }

    public StandardLevel getStandardLevel() {
        return this.standardLevel;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public int intLevel() {
        return this.intLevel;
    }

    public boolean isInRange(Level level, Level level2) {
        int i = this.intLevel;
        return i >= level.intLevel && i <= level2.intLevel;
    }

    public boolean isLessSpecificThan(Level level) {
        return this.intLevel >= level.intLevel;
    }

    public boolean isMoreSpecificThan(Level level) {
        return this.intLevel <= level.intLevel;
    }

    public String name() {
        return this.name;
    }

    protected Object readResolve() {
        return valueOf(this.name);
    }

    public String toString() {
        return this.name;
    }

    public static Level toLevel(String str, Level level) {
        Level level2;
        return (str == null || (level2 = LEVELS.get(toUpperCase(str.trim()))) == null) ? level : level2;
    }

    public Level clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override // java.lang.Comparable
    public int compareTo(Level level) {
        int i = this.intLevel;
        int i2 = level.intLevel;
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public static <T extends Enum<T>> T valueOf(Class<T> cls, String str) {
        return (T) Enum.valueOf(cls, str);
    }
}
