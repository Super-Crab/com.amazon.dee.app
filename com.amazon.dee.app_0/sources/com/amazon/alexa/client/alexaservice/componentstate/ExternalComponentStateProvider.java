package com.amazon.alexa.client.alexaservice.componentstate;

import android.os.SystemClock;
import android.util.Log;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.core.messages.Namespace;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class ExternalComponentStateProvider {
    public static final String TAG = "ExternalComponentStateProvider";
    public final AlexaContextsProvider alexaContextsProvider;
    public final ExtendedClient extendedClient;
    public Set<Namespace> namespaces;

    public ExternalComponentStateProvider(AlexaContextsProvider alexaContextsProvider, ExtendedClient extendedClient) {
        this.alexaContextsProvider = alexaContextsProvider;
        this.extendedClient = extendedClient;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ExternalComponentStateProvider) {
            return Objects.equals(this.alexaContextsProvider, ((ExternalComponentStateProvider) obj).alexaContextsProvider);
        }
        return false;
    }

    public Set<Namespace> getComponentStateNamespaces() {
        Set<Namespace> set = this.namespaces;
        if (set != null) {
            return set;
        }
        this.namespaces = new HashSet();
        Set<AlexaContext> alexaContexts = this.alexaContextsProvider.getAlexaContexts();
        if (alexaContexts != null) {
            for (AlexaContext alexaContext : alexaContexts) {
                this.namespaces.add(Namespace.create(alexaContext.getAlexaHeader().getNamespace()));
            }
        }
        return this.namespaces;
    }

    public String getPackageName() {
        return this.extendedClient.getPackageName();
    }

    public Set<ComponentState> getStates() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Set<AlexaContext> alexaContexts = this.alexaContextsProvider.getAlexaContexts();
        if (alexaContexts != null) {
            HashSet hashSet = new HashSet(alexaContexts.size());
            for (AlexaContext alexaContext : alexaContexts) {
                if (alexaContext != null && alexaContext.getAlexaPayload() != null) {
                    try {
                        new JSONObject(alexaContext.getAlexaPayload().getPayload());
                        ComponentState create = ComponentState.create(alexaContext);
                        if (create == null) {
                            String str = TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Received malformed context from ");
                            sb.append(this.extendedClient.getId());
                            Log.e(str, sb.toString());
                        } else {
                            hashSet.add(create);
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
            String str2 = TAG;
            StringBuilder zZm = C0179Pya.zZm("Fetching component states from ");
            zZm.append(this.extendedClient.getId());
            zZm.append(" took ");
            zZm.append(elapsedRealtime2);
            GeneratedOutlineSupport1.outline179(zZm, "ms", str2);
            return hashSet;
        }
        return Collections.emptySet();
    }

    public int hashCode() {
        return Objects.hashCode(this.alexaContextsProvider);
    }
}
