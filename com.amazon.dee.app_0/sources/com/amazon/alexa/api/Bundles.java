package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class Bundles {
    private static final String TAG = "Bundles";

    /* loaded from: classes6.dex */
    public interface Key {
        String name();
    }

    private Bundles() {
    }

    public static <T extends AlexaDataSink> T getAlexaDataSink(Bundle bundle, Key key, Class<T> cls) {
        T t = (T) getOptionalAlexaDataSink(bundle, key, cls);
        Preconditions.notNull(t, "failed to extract " + key + " from the bundle");
        return t;
    }

    public static IBinder getBinder(Bundle bundle, Key key) {
        IBinder optionalBinder = getOptionalBinder(bundle, key);
        Preconditions.notNull(optionalBinder, "failed to extract " + key + " from the bundle");
        return optionalBinder;
    }

    public static boolean getBoolean(Bundle bundle, Key key) {
        validate(bundle, key);
        return bundle.getBoolean(key.name());
    }

    public static Bundle getBundle(Bundle bundle, Key key) {
        Bundle optionalBundle = getOptionalBundle(bundle, key);
        Preconditions.notNull(optionalBundle, "failed to extract " + key + " from the bundle");
        return optionalBundle;
    }

    public static ExtendedClient getClient(Bundle bundle) {
        Bundle optionalBundle = getOptionalBundle(bundle, AudioProviderManagerArgumentKey.EXTENDED_CLIENT);
        return optionalBundle == null ? ExtendedClient.from((Client) getParcelable(bundle, AudioProviderManagerArgumentKey.CLIENT, Client.class)) : new ExtendedClient(optionalBundle);
    }

    public static int getInteger(Bundle bundle, Key key) {
        validate(bundle, key);
        return bundle.getInt(key.name());
    }

    public static java.util.Locale getLocale(Bundle bundle, Key key) {
        java.util.Locale optionalLocale = getOptionalLocale(bundle, key);
        Preconditions.notNull(optionalLocale, "failed to extract " + key + " from the bundle");
        return optionalLocale;
    }

    public static long getLong(Bundle bundle, Key key) {
        validate(bundle, key);
        return bundle.getLong(key.name());
    }

    @Nullable
    public static <T extends AlexaDataSink> T getOptionalAlexaDataSink(Bundle bundle, Key key, Class<T> cls) {
        ParcelFileDescriptor parcelFileDescriptor;
        String str;
        StringBuilder sb;
        String str2;
        validate(bundle, key);
        if (bundle.containsKey(key.name()) && (parcelFileDescriptor = (ParcelFileDescriptor) bundle.getParcelable(key.name())) != null) {
            try {
                return cls.getConstructor(ParcelFileDescriptor.class, ParcelFileDescriptor.class).newInstance(parcelFileDescriptor, null);
            } catch (IllegalAccessException e) {
                e = e;
                str = TAG;
                sb = new StringBuilder();
                str2 = "Illegal constructor access for ";
                sb.append(str2);
                sb.append(cls);
                Log.e(str, sb.toString(), e);
                return null;
            } catch (InstantiationException e2) {
                e = e2;
                str = TAG;
                sb = new StringBuilder();
                str2 = "Instantiation failed for ";
                sb.append(str2);
                sb.append(cls);
                Log.e(str, sb.toString(), e);
                return null;
            } catch (NoSuchMethodException e3) {
                e = e3;
                str = TAG;
                sb = new StringBuilder();
                str2 = "Unable to find constructor for ";
                sb.append(str2);
                sb.append(cls);
                Log.e(str, sb.toString(), e);
                return null;
            } catch (InvocationTargetException e4) {
                e = e4;
                str = TAG;
                sb = new StringBuilder();
                str2 = "Invocation target exception for ";
                sb.append(str2);
                sb.append(cls);
                Log.e(str, sb.toString(), e);
                return null;
            }
        }
        return null;
    }

    @Nullable
    public static IBinder getOptionalBinder(Bundle bundle, Key key) {
        validate(bundle, key);
        return bundle.getBinder(key.name());
    }

    @Nullable
    public static Bundle getOptionalBundle(Bundle bundle, Key key) {
        validate(bundle, key);
        return bundle.getBundle(key.name());
    }

    @Nullable
    public static java.util.Locale getOptionalLocale(Bundle bundle, Key key) {
        validate(bundle, key);
        String string = bundle.getString(key.name());
        if (string != null) {
            return java.util.Locale.forLanguageTag(string);
        }
        return null;
    }

    @Nullable
    public static <T extends Parcelable> T getOptionalParcelable(Bundle bundle, Key key, Class<T> cls) {
        validate(bundle, key);
        return (T) bundle.getParcelable(key.name());
    }

    @Nullable
    public static <T extends Parcelable> List<T> getOptionalParcelableList(Bundle bundle, Key key, Class<T> cls) {
        validate(bundle, key);
        return bundle.getParcelableArrayList(key.name());
    }

    @Nullable
    public static <T extends Serializable> T getOptionalSerializable(Bundle bundle, Key key, Class<T> cls) {
        Serializable serializable;
        validate(bundle, key);
        if (bundle.containsKey(key.name()) && (serializable = bundle.getSerializable(key.name())) != null) {
            try {
                return cls.cast(serializable);
            } catch (ClassCastException unused) {
                String str = TAG;
                Log.e(str, "Failed to extract " + key + " from the bundle");
            }
        }
        return null;
    }

    @Nullable
    public static String getOptionalString(Bundle bundle, Key key) {
        validate(bundle, key);
        return bundle.getString(key.name());
    }

    @Nullable
    public static String[] getOptionalStringArray(Bundle bundle, Key key) {
        validate(bundle, key);
        return bundle.getStringArray(key.name());
    }

    @Nullable
    public static ArrayList<String> getOptionalStringArrayList(Bundle bundle, Key key) {
        validate(bundle, key);
        return bundle.getStringArrayList(key.name());
    }

    @Nullable
    public static List<String> getOptionalStringList(Bundle bundle, Key key) {
        validate(bundle, key);
        return bundle.getStringArrayList(key.name());
    }

    public static <T extends Parcelable> T getParcelable(Bundle bundle, Key key, Class<T> cls) {
        T t = (T) getOptionalParcelable(bundle, key, cls);
        Preconditions.notNull(t, "failed to extract " + key + " from the bundle");
        return t;
    }

    public static <T extends Parcelable> List<T> getParcelableList(Bundle bundle, Key key, Class<T> cls) {
        List<T> optionalParcelableList = getOptionalParcelableList(bundle, key, cls);
        Preconditions.notNull(optionalParcelableList, "failed to extract " + key + " from the bundle");
        return optionalParcelableList;
    }

    public static String getString(Bundle bundle, Key key) {
        String optionalString = getOptionalString(bundle, key);
        Preconditions.notNull(optionalString, "failed to extract " + key + " from the bundle");
        return optionalString;
    }

    public static String[] getStringArray(Bundle bundle, Key key) {
        String[] optionalStringArray = getOptionalStringArray(bundle, key);
        Preconditions.notNull(optionalStringArray, "failed to extract " + key + " from the bundle");
        return optionalStringArray;
    }

    public static ArrayList<String> getStringArrayList(Bundle bundle, Key key) {
        ArrayList<String> optionalStringArrayList = getOptionalStringArrayList(bundle, key);
        Preconditions.notNull(optionalStringArrayList, "failed to extract " + key + " from the bundle");
        return optionalStringArrayList;
    }

    public static List<String> getStringList(Bundle bundle, Key key) {
        List<String> optionalStringList = getOptionalStringList(bundle, key);
        Preconditions.notNull(optionalStringList, "failed to extract " + key + " from the bundle");
        return optionalStringList;
    }

    private static void validate(Bundle bundle, Key key) {
        Preconditions.notNull(bundle, "bundle can't be null");
        Preconditions.notNull(key, "key can't be null");
    }
}
