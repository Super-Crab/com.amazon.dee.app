package com.drew.metadata.mp4;

import com.drew.imaging.mp4.Mp4Handler;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.boxes.HandlerBox;
import com.drew.metadata.mp4.media.Mp4HintHandler;
import com.drew.metadata.mp4.media.Mp4MetaHandler;
import com.drew.metadata.mp4.media.Mp4SoundHandler;
import com.drew.metadata.mp4.media.Mp4TextHandler;
import com.drew.metadata.mp4.media.Mp4VideoHandler;
/* loaded from: classes2.dex */
public class Mp4HandlerFactory {
    private static final String HANDLER_HINT_MEDIA = "hint";
    private static final String HANDLER_META_MEDIA = "meta";
    public static Long HANDLER_PARAM_CREATION_TIME = null;
    public static Long HANDLER_PARAM_DURATION = null;
    public static String HANDLER_PARAM_LANGUAGE = null;
    public static Long HANDLER_PARAM_MODIFICATION_TIME = null;
    public static Long HANDLER_PARAM_TIME_SCALE = null;
    private static final String HANDLER_SOUND_MEDIA = "soun";
    private static final String HANDLER_TEXT_MEDIA = "text";
    private static final String HANDLER_VIDEO_MEDIA = "vide";
    private Mp4Handler caller;

    public Mp4HandlerFactory(Mp4Handler mp4Handler) {
        this.caller = mp4Handler;
    }

    public Mp4Handler getHandler(HandlerBox handlerBox, Metadata metadata) {
        String handlerType = handlerBox.getHandlerType();
        return handlerType.equals(HANDLER_SOUND_MEDIA) ? new Mp4SoundHandler(metadata) : handlerType.equals(HANDLER_VIDEO_MEDIA) ? new Mp4VideoHandler(metadata) : handlerType.equals("hint") ? new Mp4HintHandler(metadata) : handlerType.equals("text") ? new Mp4TextHandler(metadata) : handlerType.equals("meta") ? new Mp4MetaHandler(metadata) : this.caller;
    }
}
