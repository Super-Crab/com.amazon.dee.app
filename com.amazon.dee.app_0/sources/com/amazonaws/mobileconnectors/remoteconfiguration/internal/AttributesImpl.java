package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.RemoteConfigurationException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.Checks;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes13.dex */
public class AttributesImpl implements Attributes {
    private static final String KEY_APP_IDENTIFIER = "_applicationIdentifier";
    public static final String KEY_APP_VERSION = "_applicationVersion";
    private static final String KEY_COUNTRY = "_localeCountryCode";
    private static final String KEY_LANGUAGE = "_localeLanguage";
    private static final String KEY_PLATFORM = "_platform";
    private static final int MAX_ALLOWED_CUSTOM_ATTRIBUTES = 100;
    private static final String PLATFORM_ANDROID = "Android";
    private static final String PLATFORM_FIRE_OS = "FireOS";
    private static final String TAG = AttributesImpl.class.getSimpleName();
    private final ConcurrentMap<String, Object> mCustomAttributes = new ConcurrentHashMap(5, 0.9f, 1);
    private final String mPackageName;
    private final Integer mPackageVersion;

    public AttributesImpl(Context context) {
        this.mPackageName = context.getPackageName();
        this.mPackageVersion = getPackageVersionCode(context);
    }

    private void addStandardAttributesTo(Map<String, Object> map) {
        map.put(KEY_PLATFORM, getPlatform());
        map.put(KEY_LANGUAGE, getLanguage());
        map.put(KEY_COUNTRY, getCountry());
        map.put(KEY_APP_IDENTIFIER, getPackageName());
        map.put(KEY_APP_VERSION, getPackageVersionCode());
    }

    private synchronized <T> T getObjectPrivate(String str, Class<T> cls) {
        Checks.checkNotNull(str, "attrKey cannot be null");
        Checks.checkNotNull(cls, "The class cannot be null");
        Object standardAttribute = str.startsWith("_") ? getStandardAttribute(str) : this.mCustomAttributes.get(str);
        if (standardAttribute != null) {
            if (cls.isAssignableFrom(standardAttribute.getClass())) {
                return cls.cast(standardAttribute);
            }
            throw new RemoteConfigurationException("Unable to retrieve value associated with attrKey = " + str + " as a " + cls.getSimpleName() + ".");
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Object getStandardAttribute(String str) {
        char c;
        switch (str.hashCode()) {
            case -1987945465:
                if (str.equals(KEY_APP_VERSION)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1563395599:
                if (str.equals(KEY_LANGUAGE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -794057366:
                if (str.equals(KEY_COUNTRY)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -527144166:
                if (str.equals(KEY_APP_IDENTIFIER)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1965201874:
                if (str.equals(KEY_PLATFORM)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c == 1) {
                return getLanguage();
            }
            if (c == 2) {
                return getCountry();
            }
            if (c == 3) {
                return getPackageName();
            }
            if (c != 4) {
                GeneratedOutlineSupport1.outline164("Unrecognized standard attribute: ", str, TAG);
                return null;
            }
            return getPackageVersionCode();
        }
        return getPlatform();
    }

    private static final boolean nullSafeEquals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return obj2 == null;
    }

    private static final int nullSafeHash(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, String str2) {
        addAttributePrivate(str, str2);
    }

    public synchronized void addAttributePrivate(String str, Object obj) {
        Checks.checkNotNull(str, "attrKey cannot be null");
        Checks.checkNotNull(obj, "attrValue cannot be null");
        Checks.checkArgument(!str.startsWith("_"), "Custom attributes cannot begin with _");
        if (this.mCustomAttributes.size() < 100) {
            new HashMap(this.mCustomAttributes).put(str, obj);
            this.mCustomAttributes.put(str, obj);
        } else {
            throw new RemoteConfigurationException("Custom attributes limit 100 reached");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AttributesImpl.class != obj.getClass()) {
            return false;
        }
        AttributesImpl attributesImpl = (AttributesImpl) obj;
        if (!nullSafeEquals(this.mCustomAttributes, attributesImpl.mCustomAttributes) || !nullSafeEquals(getCountry(), attributesImpl.getCountry()) || !nullSafeEquals(getLanguage(), attributesImpl.getLanguage()) || !nullSafeEquals(getPlatform(), attributesImpl.getPlatform()) || !nullSafeEquals(getPackageName(), attributesImpl.getPackageName())) {
            return false;
        }
        return nullSafeEquals(getPackageVersionCode(), attributesImpl.getPackageVersionCode());
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public synchronized Map<String, Object> getAllAttributes() {
        HashMap hashMap;
        hashMap = new HashMap();
        addStandardAttributesTo(hashMap);
        hashMap.putAll(this.mCustomAttributes);
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public Boolean getBoolean(String str) {
        return (Boolean) getObjectPrivate(str, Boolean.class);
    }

    protected String getCountry() {
        return Locale.getDefault().getCountry();
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public Double getDouble(String str) {
        return (Double) getObjectPrivate(str, Double.class);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public Integer getInt(String str) {
        return (Integer) getObjectPrivate(str, Integer.class);
    }

    protected String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public Long getLong(String str) {
        return (Long) getObjectPrivate(str, Long.class);
    }

    protected String getPackageName() {
        return this.mPackageName;
    }

    protected Integer getPackageVersionCode() {
        return this.mPackageVersion;
    }

    protected String getPlatform() {
        return "Android";
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public String getString(String str) {
        return (String) getObjectPrivate(str, String.class);
    }

    public int hashCode() {
        return (((((((((nullSafeHash(this.mCustomAttributes) * 31) + nullSafeHash(getCountry())) * 31) + nullSafeHash(getLanguage())) * 31) + nullSafeHash(getPlatform())) * 31) + nullSafeHash(getPackageName())) * 31) + nullSafeHash(getPackageVersionCode());
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public synchronized Object remove(String str) {
        Checks.checkNotNull(str, "attrKey cannot be null");
        Checks.checkArgument(!str.startsWith("_"), "Unable to delete attributes with _ prefix");
        if (!this.mCustomAttributes.containsKey(str)) {
            return null;
        }
        new HashMap(this.mCustomAttributes).remove(str);
        return this.mCustomAttributes.remove(str);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, Integer num) {
        addAttributePrivate(str, num);
    }

    protected Integer getPackageVersionCode(Context context) {
        try {
            return Integer.valueOf(context.getPackageManager().getPackageInfo(this.mPackageName, 0).versionCode);
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Package not found for: ");
            outline107.append(this.mPackageName);
            Log.wtf(str, outline107.toString(), e);
            return null;
        }
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, Long l) {
        addAttributePrivate(str, l);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    /* renamed from: clone  reason: collision with other method in class */
    public synchronized AttributesImpl mo6694clone() {
        return new AttributesImpl(this.mPackageName, this.mPackageVersion, this.mCustomAttributes);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, Double d) {
        if (d != null && (d.isNaN() || d.isInfinite())) {
            throw new IllegalArgumentException("Invalid attribute value: " + d);
        }
        addAttributePrivate(str, d);
    }

    private AttributesImpl(String str, Integer num, Map<String, Object> map) {
        this.mPackageName = str;
        this.mPackageVersion = num;
        this.mCustomAttributes.putAll(map);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Attributes
    public void addAttribute(String str, Boolean bool) {
        addAttributePrivate(str, bool);
    }
}
