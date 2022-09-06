package com.amazon.alexa.accessory.speechapi.context;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoryContextProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH&¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/context/AccessoryContextProvider;", "", "activate", "", "clearActiveAccessoryMicrophone", "deactivate", "setActiveAccessoryMicrophone", "name", "", "token", "firmwareVersion", "identifier", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AccessoryContextProvider {
    void activate();

    void clearActiveAccessoryMicrophone();

    void deactivate();

    void setActiveAccessoryMicrophone(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4);
}
