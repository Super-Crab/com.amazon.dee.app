package amazon.speech.simclient.directive;

import amazon.speech.simclient.directive.IDirectiveHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class DirectiveIntent {
    static final String KEY_DIRECTIVE_HOLDER = "directiveHolder";
    private static final String TAG = GeneratedOutlineSupport1.outline39(DirectiveIntent.class, GeneratedOutlineSupport1.outline107("SPCH-"));

    private DirectiveIntent() {
        throw new UnsupportedOperationException();
    }

    public static Directive fromIntent(Intent intent) {
        return fromIntentExtras(intent.getExtras());
    }

    public static Directive fromIntentExtras(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "No bundle present on intent");
            return null;
        }
        IBinder binder = bundle.getBinder(KEY_DIRECTIVE_HOLDER);
        if (binder != null) {
            try {
                Log.w(TAG, "Attempting to fetch directive from directive holder");
                return IDirectiveHolder.Stub.asInterface(binder).getDirective();
            } catch (RemoteException unused) {
                Log.e(TAG, "Could not get directive from directive callback");
                return null;
            }
        }
        Log.w(TAG, "Directive not present in intent, checking old format");
        return fromV1IntentExtras(bundle);
    }

    private static Directive fromV1IntentExtras(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "No bundle present on v1 intent");
            return null;
        }
        String string = bundle.getString(amazon.speech.model.DirectiveIntent.INTENT_KEY_DIRECTIVE_ID, null);
        String string2 = bundle.getString(amazon.speech.model.DirectiveIntent.INTENT_KEY_SEQUENCE_ID, null);
        String string3 = bundle.getString("namespace", null);
        String string4 = bundle.getString("name", null);
        String string5 = bundle.getString(amazon.speech.model.DirectiveIntent.INTENT_KEY_KEY_MAP, null);
        String string6 = bundle.getString("payload", null);
        String string7 = bundle.getString(amazon.speech.model.DirectiveIntent.INTENT_KEY_CORRELATION_TOKEN, null);
        String string8 = bundle.getString(amazon.speech.model.DirectiveIntent.INTENT_KEY_EVENT_CORRELATION_TOKEN, null);
        String string9 = bundle.getString("endpoint", null);
        if (string == null) {
            Log.e(TAG, "Intent does not have 'directiveId' field");
            return null;
        }
        return new Directive(string, string2, string3, string4, new DirectiveKeys(string5), string6, string7, null, string8, string9);
    }

    public static Intent toIntent(Directive directive) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putBinder(KEY_DIRECTIVE_HOLDER, new DirectiveHolder(directive).asBinder());
        intent.putExtras(bundle);
        return intent;
    }
}
