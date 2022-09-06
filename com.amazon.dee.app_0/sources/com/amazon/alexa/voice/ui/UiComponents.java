package com.amazon.alexa.voice.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes11.dex */
public final class UiComponents {
    public static final String ALEXA_CREATE_SHORTCUT_NAME = CreateShortcutActivity.class.getName();
    public static final String ALEXA_LAUNCH_COMPONENT_NAME = "com.amazon.dee.app.ui.voice.LaunchAlexaActivity";
    public static final String ALEXA_SHORTCUT_COMPONENT_NAME = "com.amazon.dee.app.voice.AlexaShortcutActivity";
    public static final String ALEXA_VOICE_COMMAND_COMPONENT_NAME = "com.amazon.alexa.voice.VoiceCommandActivity";
    public static final String ALEXA_VOICE_HANDSFREE_SEARCH_COMPONENT_NAME = "com.amazon.alexa.voice.VoiceHandsFreeSearchActivity";
    public static final Set<String> EXTERNAL_COMPONENTS = new HashSet(Arrays.asList(ALEXA_LAUNCH_COMPONENT_NAME, ALEXA_SHORTCUT_COMPONENT_NAME, ALEXA_VOICE_COMMAND_COMPONENT_NAME, ALEXA_VOICE_HANDSFREE_SEARCH_COMPONENT_NAME, ALEXA_CREATE_SHORTCUT_NAME));

    private UiComponents() {
    }
}
