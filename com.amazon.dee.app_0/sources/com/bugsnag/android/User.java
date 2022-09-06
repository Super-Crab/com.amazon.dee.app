package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.NativeInterface;
import java.io.IOException;
import java.util.Observable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class User extends Observable implements JsonStream.Streamable {
    @Nullable
    private String email;
    @Nullable
    private String id;
    @Nullable
    private String name;

    /* JADX INFO: Access modifiers changed from: package-private */
    public User() {
    }

    @Nullable
    public String getEmail() {
        return this.email;
    }

    @Nullable
    public String getId() {
        return this.id;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    public void setEmail(@Nullable String str) {
        this.email = str;
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_USER_EMAIL, str));
    }

    public void setId(@Nullable String str) {
        this.id = str;
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_USER_ID, str));
    }

    public void setName(@Nullable String str) {
        this.name = str;
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_USER_NAME, str));
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginObject();
        jsonStream.mo6745name("id").value(this.id);
        jsonStream.mo6745name("email").value(this.email);
        jsonStream.mo6745name("name").value(this.name);
        jsonStream.endObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public User(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.id = str;
        this.email = str2;
        this.name = str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public User(@NonNull User user) {
        this(user.id, user.email, user.name);
    }
}
