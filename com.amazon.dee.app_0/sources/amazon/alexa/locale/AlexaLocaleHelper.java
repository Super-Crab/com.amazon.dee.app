package amazon.alexa.locale;

import amazon.alexa.locale.util.LogUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public final class AlexaLocaleHelper extends BroadcastReceiver {
    private static final String ALEXA_LOCALE_KEY = "alexa_locale";
    private static Locale sDeviceLocale;
    static final Map<Context, ContentObserver> CONTENT_OBSERVER_MAP = new WeakHashMap();
    @SuppressLint({"NewApi"})
    private static final Locale PSEUDOLOC_LOCALE = Locale.forLanguageTag("cs-CZ");
    private static final String LOG_TAG = LogUtil.getTag(AlexaLocaleHelper.class);

    /* loaded from: classes.dex */
    public interface IAlexaLocaleListener {
        void onAlexaLocaleChanged();
    }

    @SuppressLint({"NewApi"})
    public static Locale getAlexaLocale(Context context) {
        String string = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), ALEXA_LOCALE_KEY);
        if (string != null) {
            Locale forLanguageTag = Locale.forLanguageTag(string);
            String str = "Alexa locale: " + forLanguageTag;
            return forLanguageTag;
        }
        Locale currentLocale = getCurrentLocale(context);
        String str2 = "getAlexaLocale() was called but the Alexa locale was null. Using current locale: " + currentLocale;
        return currentLocale;
    }

    public static Locale getCurrentLocale(Context context) {
        return context.getResources().getConfiguration().locale;
    }

    public static Locale getDeviceLocale(Context context) {
        if (sDeviceLocale != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Original locale: ");
            outline107.append(sDeviceLocale);
            outline107.toString();
            return sDeviceLocale;
        }
        Locale currentLocale = getCurrentLocale(context);
        String str = "getOriginalLocale() was called but couldn't find the original locale. Using current locale: " + currentLocale;
        return currentLocale;
    }

    @Deprecated
    public static Locale getOriginalLocale(Context context) {
        return getDeviceLocale(context);
    }

    @SuppressLint({"NewApi"})
    public static void registerListener(Context context, final IAlexaLocaleListener iAlexaLocaleListener) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof Application) {
            Application application = (Application) applicationContext;
            ContentObserver contentObserver = CONTENT_OBSERVER_MAP.get(context);
            if (contentObserver != null) {
                context.getContentResolver().unregisterContentObserver(contentObserver);
            }
            ContentObserver contentObserver2 = new ContentObserver(null) { // from class: amazon.alexa.locale.AlexaLocaleHelper.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    String unused = AlexaLocaleHelper.LOG_TAG;
                    iAlexaLocaleListener.onAlexaLocaleChanged();
                }
            };
            context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(ALEXA_LOCALE_KEY), true, contentObserver2);
            CONTENT_OBSERVER_MAP.put(context, contentObserver2);
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: amazon.alexa.locale.AlexaLocaleHelper.2
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    ContentObserver remove = AlexaLocaleHelper.CONTENT_OBSERVER_MAP.remove(activity);
                    if (remove != null) {
                        activity.getContentResolver().unregisterContentObserver(remove);
                    }
                    Context applicationContext2 = activity.getApplicationContext();
                    if (applicationContext2 instanceof Application) {
                        ((Application) applicationContext2).unregisterActivityLifecycleCallbacks(this);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                }
            });
            return;
        }
        Log.wtf(LOG_TAG, "Could not get Application object from getApplicationContext()");
    }

    static void setDeviceLocale(Locale locale) {
        String str = "Setting cached device locale: " + locale;
        sDeviceLocale = locale;
    }

    public static void useAlexaLocale(Context context) {
        useLocale(context, getAlexaLocale(context));
    }

    public static void useDeviceLocale(Context context) {
        useLocale(context, getDeviceLocale(context));
    }

    static void useLocale(Context context, Locale locale) {
        Locale currentLocale = getCurrentLocale(context);
        if (currentLocale.equals(locale)) {
            String str = "Current locale matches desired locale: " + locale;
        } else if (currentLocale.equals(PSEUDOLOC_LOCALE)) {
            String str2 = "Current locale is pseudoloc locale, ignoring desired locale: " + locale;
        } else {
            if (sDeviceLocale == null) {
                setDeviceLocale(currentLocale);
            }
            Locale.setDefault(locale);
            Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.locale = locale;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            String str3 = "Locale updated to: " + locale;
        }
    }

    @Deprecated
    public static void useOriginalLocale(Context context) {
        useDeviceLocale(context);
    }

    @Override // android.content.BroadcastReceiver
    @SuppressLint({"NewApi"})
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), "android.intent.action.LOCALE_CHANGED")) {
            setDeviceLocale(getCurrentLocale(context));
        }
    }
}
