package amazon.alexa.locale;

import android.app.Application;
/* loaded from: classes.dex */
public class AlexaLocaleApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new AlexaLocaleActivityLifecycleCallbacks());
    }
}
