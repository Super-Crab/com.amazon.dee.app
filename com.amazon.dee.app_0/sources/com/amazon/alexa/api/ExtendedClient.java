package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes6.dex */
public class ExtendedClient {
    private static final String DEFAULT_DEBUG_TAG = "";
    private static final String ID_FORMAT = "%s-%s";
    private static final String KEY_ACTIVE_SUBCLIENT = "activeSubclient";
    private static final String KEY_DEBUG_TAG = "debugTag";
    private static final String KEY_ID = "id";
    private static final String KEY_PACKAGENAME = "packageName";
    private static final String KEY_ROLE = "roles";
    private static final String KEY_SUBCLIENTS = "subclients";
    private static final String KEY_VERSION = "version";
    private static final String TAG = "ExtendedClient";
    String activeSubClientId;
    final ClientRole clientRole;
    final String debugTag;
    final String id;
    final String packageName;
    final ArrayList<ExtendedClient> subclients;
    final AlexaApiVersion version;

    public ExtendedClient(Bundle bundle) {
        String string = bundle.getString("id");
        String string2 = bundle.getString("packageName", "UNKNOWN");
        ClientRole clientRole = (ClientRole) bundle.getParcelable(KEY_ROLE);
        AlexaApiVersion alexaApiVersion = (AlexaApiVersion) bundle.getParcelable("version");
        List<Bundle> parcelableArrayList = bundle.getParcelableArrayList(KEY_SUBCLIENTS);
        String string3 = bundle.getString(KEY_ACTIVE_SUBCLIENT);
        String string4 = bundle.getString(KEY_DEBUG_TAG, "");
        if (string == null) {
            Log.w(TAG, "ID was null. Generating new ID from package name and debug tag");
            string = generateId(string2, string4);
        }
        if (clientRole == null) {
            Log.w(TAG, "Role was null. Defaulting Role to USER");
            clientRole = ClientRole.USER;
        }
        if (alexaApiVersion == null) {
            Log.w(TAG, "Version was null. Defaulting to current API version");
            alexaApiVersion = Versions.CURRENT_API_VERSION;
        }
        parcelableArrayList = parcelableArrayList == null ? Collections.emptyList() : parcelableArrayList;
        this.id = string;
        this.packageName = string2;
        this.clientRole = clientRole;
        this.version = alexaApiVersion;
        this.activeSubClientId = string3;
        this.subclients = new ArrayList<>();
        this.debugTag = string4;
        for (Bundle bundle2 : parcelableArrayList) {
            this.subclients.add(new ExtendedClient(bundle2));
        }
    }

    protected ExtendedClient(ClientRole clientRole, String str, String str2, AlexaApiVersion alexaApiVersion, @Nullable ArrayList<ExtendedClient> arrayList, @Nullable String str3) {
        this(clientRole, str, str2, alexaApiVersion, arrayList, str3, "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ExtendedClient(ClientRole clientRole, String str, String str2, AlexaApiVersion alexaApiVersion, @Nullable ArrayList<ExtendedClient> arrayList, @Nullable String str3, @Nullable String str4) {
        Preconditions.notNull(clientRole, "Client role is not defined");
        Preconditions.notNull(str, "id is not defined");
        Preconditions.notNull(alexaApiVersion, "version is not defined");
        this.id = str;
        this.clientRole = clientRole;
        this.version = alexaApiVersion;
        this.packageName = str2;
        this.subclients = arrayList == null ? new ArrayList<>() : arrayList;
        this.activeSubClientId = str3;
        this.debugTag = str4 == null ? "" : str4;
    }

    public ExtendedClient(String str) {
        this(str, ClientRole.USER);
    }

    public ExtendedClient(String str, ClientRole clientRole) {
        this(str, clientRole, "");
    }

    public ExtendedClient(String str, ClientRole clientRole, @Nullable String str2) {
        this(clientRole, generateId(str, str2), str, Versions.CURRENT_API_VERSION, null, null, str2);
    }

    public ExtendedClient(String str, @Nullable String str2) {
        this(str, ClientRole.USER, str2);
    }

    public static ExtendedClient from(Client client) {
        return new ExtendedClient(client.getClientRole(), client.getId(), getPackageNameFromClient(client.getId()), client.getVersion(), null, null, null);
    }

    private static String generateId(String str) {
        return generateId(str, "");
    }

    private static String generateId(String str, @Nullable String str2) {
        return TextUtils.isEmpty(str2) ? String.format("%s-%s", str, UUID.randomUUID().toString()) : String.format("%s-%s", GeneratedOutlineSupport1.outline75(str, "/", str2), UUID.randomUUID().toString().substring(30));
    }

    private static String getPackageNameFromClient(String str) {
        String[] split = str.split("\\-");
        return split.length > 1 ? split[0] : "unknown";
    }

    public static ExtendedClient overrideVersion(ExtendedClient extendedClient, AlexaApiVersion alexaApiVersion) {
        return new ExtendedClient(extendedClient.clientRole, extendedClient.id, extendedClient.packageName, alexaApiVersion, extendedClient.subclients, extendedClient.activeSubClientId, extendedClient.debugTag);
    }

    public void addSubClient(ExtendedClient extendedClient) {
        this.subclients.add(extendedClient);
    }

    public Client asClient() {
        return new Client(this.clientRole, this.id, this.version);
    }

    public void clearSubClients() {
        this.subclients.clear();
        this.activeSubClientId = null;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof ExtendedClient)) {
            return Objects.equals(this.id, ((ExtendedClient) obj).id);
        }
        return false;
    }

    public ExtendedClient getActiveSubClient() {
        if (this.activeSubClientId == null) {
            return this;
        }
        Iterator<ExtendedClient> it2 = this.subclients.iterator();
        while (it2.hasNext()) {
            ExtendedClient next = it2.next();
            if (this.activeSubClientId.equals(next.getId())) {
                return next.getActiveSubClient();
            }
        }
        return this;
    }

    @Nullable
    public String getActiveSubClientId() {
        return this.activeSubClientId;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.id);
        bundle.putString(KEY_DEBUG_TAG, this.debugTag);
        bundle.putString("packageName", this.packageName);
        bundle.putParcelable(KEY_ROLE, this.clientRole);
        bundle.putParcelable("version", this.version);
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.subclients.size());
        Iterator<ExtendedClient> it2 = this.subclients.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().getBundle());
        }
        bundle.putParcelableArrayList(KEY_SUBCLIENTS, arrayList);
        bundle.putString(KEY_ACTIVE_SUBCLIENT, this.activeSubClientId);
        return bundle;
    }

    public ClientRole getClientRole() {
        return this.clientRole;
    }

    public String getDebugTag() {
        return this.debugTag;
    }

    public String getId() {
        return this.id;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public List<ExtendedClient> getSubClients() {
        ArrayList arrayList = new ArrayList(this.subclients.size());
        arrayList.addAll(this.subclients);
        return arrayList;
    }

    public AlexaApiVersion getVersion() {
        return this.version;
    }

    public int hashCode() {
        return Objects.hash(this.id);
    }

    public void removeSubClient(ExtendedClient extendedClient) {
        this.subclients.remove(extendedClient);
        if (extendedClient.getId().equals(this.activeSubClientId)) {
            this.activeSubClientId = null;
        }
    }

    public void setActiveSubClient(ExtendedClient extendedClient) {
        this.activeSubClientId = extendedClient.getId();
        if (!this.subclients.contains(extendedClient)) {
            this.subclients.add(extendedClient);
        }
    }
}
