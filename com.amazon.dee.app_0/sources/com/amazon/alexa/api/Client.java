package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import java.util.UUID;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes6.dex */
public class Client implements Parcelable {
    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() { // from class: com.amazon.alexa.api.Client.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public Client createFromParcel(Parcel parcel) {
            return new Client(ClientRole.CREATOR.createFromParcel(parcel), parcel.readString(), AlexaApiVersion.CREATOR.createFromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public Client[] newArray(int i) {
            return new Client[i];
        }
    };
    private final ClientRole clientRole;
    private final String id;
    private final AlexaApiVersion version;

    /* JADX INFO: Access modifiers changed from: protected */
    public Client(ClientRole clientRole, String str, AlexaApiVersion alexaApiVersion) {
        Preconditions.notNull(clientRole, "Client role is not defined");
        Preconditions.notNull(str, "id is not defined");
        Preconditions.notNull(alexaApiVersion, "version is not defined");
        this.id = str;
        this.clientRole = clientRole;
        this.version = alexaApiVersion;
    }

    public Client(String str) {
        this(generateId(str), ClientRole.USER);
    }

    public Client(String str, ClientRole clientRole) {
        this(clientRole, generateId(str), Versions.CURRENT_API_VERSION);
    }

    private static String generateId(String str) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ProcessIdUtil.DEFAULT_PROCESSID);
        outline113.append(new ParcelUuid(UUID.randomUUID()));
        return outline113.toString();
    }

    public static Client overrideVersion(Client client, AlexaApiVersion alexaApiVersion) {
        return new Client(client.clientRole, client.id, alexaApiVersion);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Client.class == obj.getClass()) {
            return Objects.equals(this.id, ((Client) obj).id);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientRole getClientRole() {
        return this.clientRole;
    }

    public String getId() {
        return this.id;
    }

    public AlexaApiVersion getVersion() {
        return this.version;
    }

    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        this.clientRole.writeToParcel(parcel, i);
        this.version.writeToParcel(parcel, i);
    }
}
