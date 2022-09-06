package com.drew.metadata.mp4.media;

import com.drew.lang.annotations.NotNull;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class Mp4SoundDirectory extends Mp4MediaDirectory {
    public static final int TAG_AUDIO_FORMAT = 101;
    public static final int TAG_AUDIO_SAMPLE_RATE = 104;
    public static final int TAG_AUDIO_SAMPLE_SIZE = 103;
    public static final int TAG_NUMBER_OF_CHANNELS = 102;
    public static final int TAG_SOUND_BALANCE = 105;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        Mp4MediaDirectory.addMp4MediaTags(_tagNameMap);
        _tagNameMap.put(101, "Format");
        _tagNameMap.put(102, "Number of Channels");
        _tagNameMap.put(103, "Sample Size");
        _tagNameMap.put(104, "Sample Rate");
        _tagNameMap.put(105, "Balance");
    }

    public Mp4SoundDirectory() {
        setDescriptor(new Mp4SoundDescriptor(this));
    }

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "MP4 Sound";
    }

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
