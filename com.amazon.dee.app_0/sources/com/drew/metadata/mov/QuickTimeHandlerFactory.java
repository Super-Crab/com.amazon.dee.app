package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.media.QuickTimeMusicHandler;
import com.drew.metadata.mov.media.QuickTimeSoundHandler;
import com.drew.metadata.mov.media.QuickTimeSubtitleHandler;
import com.drew.metadata.mov.media.QuickTimeTextHandler;
import com.drew.metadata.mov.media.QuickTimeTimecodeHandler;
import com.drew.metadata.mov.media.QuickTimeVideoHandler;
import com.drew.metadata.mov.metadata.QuickTimeDataHandler;
import com.drew.metadata.mov.metadata.QuickTimeDirectoryHandler;
/* loaded from: classes2.dex */
public class QuickTimeHandlerFactory {
    private static final String HANDLER_METADATA_DATA = "mdta";
    private static final String HANDLER_METADATA_DIRECTORY = "mdir";
    private static final String HANDLER_MUSIC_MEDIA = "musi";
    public static Long HANDLER_PARAM_CREATION_TIME = null;
    public static Long HANDLER_PARAM_DURATION = null;
    public static Long HANDLER_PARAM_MODIFICATION_TIME = null;
    public static Long HANDLER_PARAM_TIME_SCALE = null;
    private static final String HANDLER_SOUND_MEDIA = "soun";
    private static final String HANDLER_SUBTITLE_MEDIA = "sbtl";
    private static final String HANDLER_TEXT_MEDIA = "text";
    private static final String HANDLER_TIMECODE_MEDIA = "tmcd";
    private static final String HANDLER_VIDEO_MEDIA = "vide";
    private QuickTimeHandler caller;

    public QuickTimeHandlerFactory(QuickTimeHandler quickTimeHandler) {
        this.caller = quickTimeHandler;
    }

    public QuickTimeHandler getHandler(String str, Metadata metadata) {
        return str.equals(HANDLER_METADATA_DIRECTORY) ? new QuickTimeDirectoryHandler(metadata) : str.equals(HANDLER_METADATA_DATA) ? new QuickTimeDataHandler(metadata) : str.equals(HANDLER_SOUND_MEDIA) ? new QuickTimeSoundHandler(metadata) : str.equals(HANDLER_VIDEO_MEDIA) ? new QuickTimeVideoHandler(metadata) : str.equals(HANDLER_TIMECODE_MEDIA) ? new QuickTimeTimecodeHandler(metadata) : str.equals("text") ? new QuickTimeTextHandler(metadata) : str.equals("sbtl") ? new QuickTimeSubtitleHandler(metadata) : str.equals(HANDLER_MUSIC_MEDIA) ? new QuickTimeMusicHandler(metadata) : this.caller;
    }
}
