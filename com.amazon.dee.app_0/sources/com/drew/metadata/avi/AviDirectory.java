package com.drew.metadata.avi;

import com.amazon.deecomms.common.Constants;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class AviDirectory extends Directory {
    public static final String CHUNK_MAIN_HEADER = "avih";
    public static final String CHUNK_STREAM_HEADER = "strh";
    public static final String FORMAT = "AVI ";
    public static final String LIST_HEADER = "hdrl";
    public static final String LIST_STREAM_HEADER = "strl";
    public static final int TAG_AUDIO_CODEC = 5;
    public static final int TAG_DURATION = 3;
    public static final int TAG_FRAMES_PER_SECOND = 1;
    public static final int TAG_HEIGHT = 7;
    public static final int TAG_SAMPLES_PER_SECOND = 2;
    public static final int TAG_STREAMS = 8;
    public static final int TAG_VIDEO_CODEC = 4;
    public static final int TAG_WIDTH = 6;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        _tagNameMap.put(1, "Frames Per Second");
        _tagNameMap.put(2, "Samples Per Second");
        _tagNameMap.put(3, Constants.CALL_DURATION_KEY);
        _tagNameMap.put(4, "Video Codec");
        _tagNameMap.put(5, "Audio Codec");
        _tagNameMap.put(6, "Width");
        _tagNameMap.put(7, "Height");
        _tagNameMap.put(8, "Stream Count");
    }

    public AviDirectory() {
        setDescriptor(new AviDescriptor(this));
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "AVI";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
