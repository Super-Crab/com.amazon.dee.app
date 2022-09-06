package org.apache.logging.log4j.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes4.dex */
public class FilteredObjectInputStream extends ObjectInputStream {
    private static final List<String> REQUIRED_JAVA_CLASSES = Arrays.asList("java.math.BigDecimal", "java.math.BigInteger", "java.rmi.MarshalledObject", "[B");
    private static final List<String> REQUIRED_JAVA_PACKAGES = Arrays.asList("java.lang.", "java.time", "java.util.", "org.apache.logging.log4j.", "[Lorg.apache.logging.log4j.");
    private final Collection<String> allowedClasses;

    public FilteredObjectInputStream() throws IOException, SecurityException {
        this.allowedClasses = new HashSet();
    }

    private static boolean isAllowedByDefault(String str) {
        return isRequiredPackage(str) || REQUIRED_JAVA_CLASSES.contains(str);
    }

    private static boolean isRequiredPackage(String str) {
        for (String str2 : REQUIRED_JAVA_PACKAGES) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public Collection<String> getAllowedClasses() {
        return this.allowedClasses;
    }

    @Override // java.io.ObjectInputStream
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        String name = objectStreamClass.getName();
        if (!isAllowedByDefault(name) && !this.allowedClasses.contains(name)) {
            throw new InvalidObjectException(GeneratedOutlineSupport1.outline72("Class is not allowed for deserialization: ", name));
        }
        return super.resolveClass(objectStreamClass);
    }

    public FilteredObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        this.allowedClasses = new HashSet();
    }

    public FilteredObjectInputStream(Collection<String> collection) throws IOException, SecurityException {
        this.allowedClasses = collection;
    }

    public FilteredObjectInputStream(InputStream inputStream, Collection<String> collection) throws IOException {
        super(inputStream);
        this.allowedClasses = collection;
    }
}
