package amazon.speech.simclient.metrics.upl;
/* loaded from: classes.dex */
public class UPLConstants {

    /* loaded from: classes.dex */
    public static class Channel {
        public static final String AUDIO = "audio";
        public static final String BOTH = "both";
        public static final String VISUAL = "visual";
    }

    /* loaded from: classes.dex */
    public static class IntentKey {
        public static final String INTENT_KEY_DEVICE_REQUEST_TIME = "device_request_time";
        public static final String INTENT_KEY_DIRECTIVE_DISPATCH_TIME = "directive_dispatch_time";
        public static final String INTENT_KEY_DIRECTIVE_FIRST_BYTE_TIME = "directive_first_byte_time";
        public static final String INTENT_KEY_DIRECTIVE_PARSE_COMPLETE_TIME = "directive_parse_complete_time";
        public static final String INTENT_KEY_END_SPEECH_DETECTION_TIME = "end_of_speech_time";
        public static final String INTENT_KEY_EVENT_ID = "event_id";
        public static final String INTENT_KEY_EVENT_TYPE = "event_type";
        public static final String INTENT_KEY_INTENT_NAME = "intent_name";
        public static final String INTENT_KEY_INTERACTION_LABEL = "interaction_label";
        public static final String INTENT_KEY_INTERACTION_START_TIME = "interaction_start_time";
        public static final String INTENT_KEY_LAST_AUDIO_DIRECTIVE_TIME = "last_audio_directive_time";
        public static final String INTENT_KEY_LAST_MEDIA_DIRECTIVE_TIME = "last_media_directive_time";
        public static final String INTENT_KEY_LAST_VISUAL_DIRECTIVE_TIME = "last_visual_directive_time";
        public static final String INTENT_KEY_METRICS_PROGRAM_NAME = "metrics_program_name";
        public static final String INTENT_KEY_NAME = "name";
        public static final String INTENT_KEY_NAMESPACE = "namespace";
        @Deprecated
        public static final String INTENT_KEY_PREVIOUS_DIRECTIVE = "previous_directive_complete_time";
        public static final String INTENT_KEY_STOP_CAPTURE_TIME = "stop_capture_time";
        public static final String INTENT_KEY_WAKE_WORD_DETECTION_TIME = "wake_word_detection_time";
    }

    /* loaded from: classes.dex */
    public static class MetadataKey {
        public static final String METADATA_KEY_DIRECTIVE_NAME = "directiveName";
        public static final String METADATA_KEY_DIRECTIVE_NAMESPACE = "directiveNamespace";
        public static final String METADATA_KEY_EVENT_ID = "eventId";
        public static final String METADATA_KEY_INTERACTION_LABEL = "interactionLabel";
        public static final String METADATA_KEY_SPEECH_INTENT_NAME = "intentName";
    }
}
