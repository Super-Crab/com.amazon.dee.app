package com.drew.metadata.wav;

import amazon.speech.simclient.SimError;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.whisperjoin.provisioning.constants.BluetoothConstants;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusFocusInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SonyType1MakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import com.google.android.exoplayer2.audio.WavUtil;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class WavDirectory extends Directory {
    public static final String CHUNK_DATA = "data";
    public static final String CHUNK_FORMAT = "fmt ";
    public static final String FORMAT = "WAVE";
    public static final String LIST_INFO = "INFO";
    public static final int TAG_ARTIST = 7;
    public static final int TAG_BITS_PER_SAMPLE = 6;
    public static final int TAG_BLOCK_ALIGNMENT = 5;
    public static final int TAG_BYTES_PER_SEC = 4;
    public static final int TAG_CHANNELS = 2;
    public static final int TAG_COMMENTS = 13;
    public static final int TAG_COPYRIGHT = 14;
    public static final int TAG_DATE_CREATED = 11;
    public static final int TAG_DURATION = 16;
    public static final int TAG_FORMAT = 1;
    public static final int TAG_GENRE = 12;
    public static final int TAG_PRODUCT = 9;
    public static final int TAG_SAMPLES_PER_SEC = 3;
    public static final int TAG_SOFTWARE = 15;
    public static final int TAG_TITLE = 8;
    public static final int TAG_TRACK_NUMBER = 10;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();
    @NotNull
    protected static final transient HashMap<String, Integer> _tagIntegerMap = new HashMap<>();
    @NotNull
    protected static final transient HashMap<Integer, String> _audioEncodingMap = new HashMap<>();

    static {
        _tagIntegerMap.put("IART", 7);
        _tagIntegerMap.put("INAM", 8);
        _tagIntegerMap.put("IPRD", 9);
        _tagIntegerMap.put("ITRK", 10);
        _tagIntegerMap.put("ICRD", 11);
        _tagIntegerMap.put("IGNR", 12);
        _tagIntegerMap.put("ICMT", 13);
        _tagIntegerMap.put("ICOP", 14);
        _tagIntegerMap.put("ISFT", 15);
        _tagNameMap.put(1, "Format");
        _tagNameMap.put(2, "Channels");
        _tagNameMap.put(3, "Samples Per Second");
        _tagNameMap.put(4, "Bytes Per Second");
        _tagNameMap.put(5, "Block Alignment");
        _tagNameMap.put(6, "Bits Per Sample");
        _tagNameMap.put(7, ExifInterface.TAG_ARTIST);
        _tagNameMap.put(8, "Title");
        _tagNameMap.put(9, DataRecordKey.PRODUCT);
        _tagNameMap.put(10, "Track Number");
        _tagNameMap.put(11, "Date Created");
        _tagNameMap.put(12, "Genre");
        _tagNameMap.put(13, "Comments");
        _tagNameMap.put(14, ExifInterface.TAG_COPYRIGHT);
        _tagNameMap.put(15, ExifInterface.TAG_SOFTWARE);
        _tagNameMap.put(16, Constants.CALL_DURATION_KEY);
        _audioEncodingMap.put(1, "Microsoft PCM");
        _audioEncodingMap.put(2, "Microsoft ADPCM");
        _audioEncodingMap.put(3, "Microsoft IEEE float");
        _audioEncodingMap.put(4, "Compaq VSELP");
        _audioEncodingMap.put(5, "IBM CVSD");
        _audioEncodingMap.put(6, "Microsoft a-Law");
        _audioEncodingMap.put(7, "Microsoft u-Law");
        _audioEncodingMap.put(8, "Microsoft DTS");
        _audioEncodingMap.put(9, "DRM");
        _audioEncodingMap.put(10, "WMA 9 Speech");
        _audioEncodingMap.put(11, "Microsoft Windows Media RT Voice");
        _audioEncodingMap.put(16, "OKI-ADPCM");
        _audioEncodingMap.put(17, "Intel IMA/DVI-ADPCM");
        _audioEncodingMap.put(18, "Videologic Mediaspace ADPCM");
        _audioEncodingMap.put(19, "Sierra ADPCM");
        _audioEncodingMap.put(20, "Antex G.723 ADPCM");
        _audioEncodingMap.put(21, "DSP Solutions DIGISTD");
        _audioEncodingMap.put(22, "DSP Solutions DIGIFIX");
        _audioEncodingMap.put(23, "Dialoic OKI ADPCM");
        _audioEncodingMap.put(24, "Media Vision ADPCM");
        _audioEncodingMap.put(25, "HP CU");
        _audioEncodingMap.put(26, "HP Dynamic Voice");
        _audioEncodingMap.put(32, "Yamaha ADPCM");
        _audioEncodingMap.put(33, "SONARC Speech Compression");
        _audioEncodingMap.put(34, "DSP Group True Speech");
        _audioEncodingMap.put(35, "Echo Speech Corp.");
        _audioEncodingMap.put(36, "Virtual Music Audiofile AF36");
        _audioEncodingMap.put(37, "Audio Processing Tech.");
        _audioEncodingMap.put(38, "Virtual Music Audiofile AF10");
        _audioEncodingMap.put(39, "Aculab Prosody 1612");
        _audioEncodingMap.put(40, "Merging Tech. LRC");
        _audioEncodingMap.put(48, "Dolby AC2");
        _audioEncodingMap.put(49, "Microsoft GSM610");
        _audioEncodingMap.put(50, "MSN Audio");
        _audioEncodingMap.put(51, "Antex ADPCME");
        _audioEncodingMap.put(52, "Control Resources VQLPC");
        _audioEncodingMap.put(53, "DSP Solutions DIGIREAL");
        _audioEncodingMap.put(54, "DSP Solutions DIGIADPCM");
        _audioEncodingMap.put(55, "Control Resources CR10");
        _audioEncodingMap.put(56, "Natural MicroSystems VBX ADPCM");
        _audioEncodingMap.put(57, "Crystal Semiconductor IMA ADPCM");
        _audioEncodingMap.put(58, "Echo Speech ECHOSC3");
        _audioEncodingMap.put(59, "Rockwell ADPCM");
        _audioEncodingMap.put(60, "Rockwell DIGITALK");
        _audioEncodingMap.put(61, "Xebec Multimedia");
        _audioEncodingMap.put(64, "Antex G.721 ADPCM");
        _audioEncodingMap.put(65, "Antex G.728 CELP");
        _audioEncodingMap.put(66, "Microsoft MSG723");
        _audioEncodingMap.put(67, "IBM AVC ADPCM");
        _audioEncodingMap.put(69, "ITU-T G.726");
        _audioEncodingMap.put(80, "Microsoft MPEG");
        _audioEncodingMap.put(81, "RT23 or PAC");
        _audioEncodingMap.put(82, "InSoft RT24");
        _audioEncodingMap.put(83, "InSoft PAC");
        _audioEncodingMap.put(85, "MP3");
        _audioEncodingMap.put(89, "Cirrus");
        _audioEncodingMap.put(96, "Cirrus Logic");
        _audioEncodingMap.put(97, "ESS Tech. PCM");
        _audioEncodingMap.put(98, "Voxware Inc.");
        _audioEncodingMap.put(99, "Canopus ATRAC");
        _audioEncodingMap.put(100, "APICOM G.726 ADPCM");
        _audioEncodingMap.put(101, "APICOM G.722 ADPCM");
        _audioEncodingMap.put(102, "Microsoft DSAT");
        _audioEncodingMap.put(103, "Micorsoft DSAT DISPLAY");
        _audioEncodingMap.put(105, "Voxware Byte Aligned");
        _audioEncodingMap.put(112, "Voxware AC8");
        _audioEncodingMap.put(113, "Voxware AC10");
        _audioEncodingMap.put(114, "Voxware AC16");
        _audioEncodingMap.put(115, "Voxware AC20");
        _audioEncodingMap.put(116, "Voxware MetaVoice");
        _audioEncodingMap.put(117, "Voxware MetaSound");
        _audioEncodingMap.put(118, "Voxware RT29HW");
        _audioEncodingMap.put(119, "Voxware VR12");
        _audioEncodingMap.put(120, "Voxware VR18");
        _audioEncodingMap.put(121, "Voxware TQ40");
        _audioEncodingMap.put(122, "Voxware SC3");
        _audioEncodingMap.put(123, "Voxware SC3");
        _audioEncodingMap.put(128, "Soundsoft");
        _audioEncodingMap.put(129, "Voxware TQ60");
        _audioEncodingMap.put(130, "Microsoft MSRT24");
        _audioEncodingMap.put(131, "AT&T G.729A");
        _audioEncodingMap.put(132, "Motion Pixels MVI MV12");
        _audioEncodingMap.put(133, "DataFusion G.726");
        _audioEncodingMap.put(134, "DataFusion GSM610");
        _audioEncodingMap.put(136, "Iterated Systems Audio");
        _audioEncodingMap.put(137, "Onlive");
        _audioEncodingMap.put(138, "Multitude, Inc. FT SX20");
        _audioEncodingMap.put(139, "Infocom ITS A/S G.721 ADPCM");
        _audioEncodingMap.put(140, "Convedia G729");
        _audioEncodingMap.put(141, "Not specified congruency, Inc.");
        _audioEncodingMap.put(145, "Siemens SBC24");
        _audioEncodingMap.put(146, "Sonic Foundry Dolby AC3 APDIF");
        _audioEncodingMap.put(147, "MediaSonic G.723");
        _audioEncodingMap.put(148, "Aculab Prosody 8kbps");
        _audioEncodingMap.put(151, "ZyXEL ADPCM");
        _audioEncodingMap.put(152, "Philips LPCBB");
        _audioEncodingMap.put(153, "Studer Professional Audio Packed");
        _audioEncodingMap.put(160, "Malden PhonyTalk");
        _audioEncodingMap.put(161, "Racal Recorder GSM");
        _audioEncodingMap.put(162, "Racal Recorder G720.a");
        _audioEncodingMap.put(163, "Racal G723.1");
        _audioEncodingMap.put(164, "Racal Tetra ACELP");
        _audioEncodingMap.put(176, "NEC AAC NEC Corporation");
        _audioEncodingMap.put(255, "AAC");
        _audioEncodingMap.put(256, "Rhetorex ADPCM");
        _audioEncodingMap.put(257, "IBM u-Law");
        _audioEncodingMap.put(258, "IBM a-Law");
        _audioEncodingMap.put(259, "IBM ADPCM");
        _audioEncodingMap.put(273, "Vivo G.723");
        _audioEncodingMap.put(274, "Vivo Siren");
        _audioEncodingMap.put(288, "Philips Speech Processing CELP");
        _audioEncodingMap.put(289, "Philips Speech Processing GRUNDIG");
        _audioEncodingMap.put(291, "Digital G.723");
        _audioEncodingMap.put(293, "Sanyo LD ADPCM");
        _audioEncodingMap.put(304, "Sipro Lab ACEPLNET");
        _audioEncodingMap.put(305, "Sipro Lab ACELP4800");
        _audioEncodingMap.put(306, "Sipro Lab ACELP8V3");
        _audioEncodingMap.put(307, "Sipro Lab G.729");
        _audioEncodingMap.put(308, "Sipro Lab G.729A");
        _audioEncodingMap.put(309, "Sipro Lab Kelvin");
        _audioEncodingMap.put(310, "VoiceAge AMR");
        _audioEncodingMap.put(Integer.valueOf((int) DeviceConfigConstants.VIDEO_WIDTH_320), "Dictaphone G.726 ADPCM");
        _audioEncodingMap.put(Integer.valueOf((int) IptcDirectory.TAG_TIME_SENT), "Qualcomm PureVoice");
        _audioEncodingMap.put(337, "Qualcomm HalfRate");
        _audioEncodingMap.put(341, "Ring Zero Systems TUBGSM");
        _audioEncodingMap.put(352, "Microsoft Audio1");
        _audioEncodingMap.put(353, "Windows Media Audio V2 V7 V8 V9 / DivX audio (WMA) / Alex AC3 Audio");
        _audioEncodingMap.put(354, "Windows Media Audio Professional V9");
        _audioEncodingMap.put(355, "Windows Media Audio Lossless V9");
        _audioEncodingMap.put(356, "WMA Pro over S/PDIF");
        _audioEncodingMap.put(368, "UNISYS NAP ADPCM");
        _audioEncodingMap.put(Integer.valueOf((int) BluetoothConstants.AMAZON_MANUFACTUROR_BLUETOOTH_SIG_CODE), "UNISYS NAP ULAW");
        _audioEncodingMap.put(370, "UNISYS NAP ALAW");
        _audioEncodingMap.put(371, "UNISYS NAP 16K");
        _audioEncodingMap.put(372, "MM SYCOM ACM SYC008 SyCom Technologies");
        _audioEncodingMap.put(373, "MM SYCOM ACM SYC701 G726L SyCom Technologies");
        _audioEncodingMap.put(374, "MM SYCOM ACM SYC701 CELP54 SyCom Technologies");
        _audioEncodingMap.put(375, "MM SYCOM ACM SYC701 CELP68 SyCom Technologies");
        _audioEncodingMap.put(Integer.valueOf((int) IptcDirectory.TAG_ARM_IDENTIFIER), "Knowledge Adventure ADPCM");
        _audioEncodingMap.put(Integer.valueOf((int) BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT), "Fraunhofer IIS MPEG2AAC");
        _audioEncodingMap.put(400, "Digital Theater Systems DTS DS");
        _audioEncodingMap.put(512, "Creative Labs ADPCM");
        _audioEncodingMap.put(514, "Creative Labs FASTSPEECH8");
        _audioEncodingMap.put(515, "Creative Labs FASTSPEECH10");
        _audioEncodingMap.put(528, "UHER ADPCM");
        _audioEncodingMap.put(533, "Ulead DV ACM");
        _audioEncodingMap.put(534, "Ulead DV ACM");
        _audioEncodingMap.put(544, "Quarterdeck Corp.");
        _audioEncodingMap.put(560, "I-Link VC");
        _audioEncodingMap.put(576, "Aureal Semiconductor Raw Sport");
        _audioEncodingMap.put(Integer.valueOf((int) IptcDirectory.TAG_ORIGINATING_PROGRAM), "ESST AC3");
        _audioEncodingMap.put(Integer.valueOf((int) IptcDirectory.TAG_BY_LINE), "Interactive Products HSX");
        _audioEncodingMap.put(593, "Interactive Products RPELP");
        _audioEncodingMap.put(608, "Consistent CS2");
        _audioEncodingMap.put(624, "Sony SCX");
        _audioEncodingMap.put(625, "Sony SCY");
        _audioEncodingMap.put(626, "Sony ATRAC3");
        _audioEncodingMap.put(Integer.valueOf((int) IptcDirectory.TAG_SOURCE), "Sony SPC");
        _audioEncodingMap.put(640, "TELUM Telum Inc.");
        _audioEncodingMap.put(641, "TELUMIA Telum Inc.");
        _audioEncodingMap.put(645, "Norcom Voice Systems ADPCM");
        _audioEncodingMap.put(768, "Fujitsu FM TOWNS SND");
        _audioEncodingMap.put(769, "Fujitsu (not specified)");
        _audioEncodingMap.put(770, "Fujitsu (not specified)");
        _audioEncodingMap.put(771, "Fujitsu (not specified)");
        _audioEncodingMap.put(772, "Fujitsu (not specified)");
        _audioEncodingMap.put(773, "Fujitsu (not specified)");
        _audioEncodingMap.put(774, "Fujitsu (not specified)");
        _audioEncodingMap.put(Integer.valueOf((int) OlympusCameraSettingsMakernoteDirectory.TagAfFineTuneAdj), "Fujitsu (not specified)");
        _audioEncodingMap.put(Integer.valueOf((int) OlympusFocusInfoMakernoteDirectory.TagAfPoint), "Fujitsu (not specified)");
        _audioEncodingMap.put(848, "Micronas Semiconductors, Inc. Development");
        _audioEncodingMap.put(849, "Micronas Semiconductors, Inc. CELP833");
        _audioEncodingMap.put(1024, "Brooktree Digital");
        _audioEncodingMap.put(1025, "Intel Music Coder (IMC)");
        _audioEncodingMap.put(1026, "Ligos Indeo Audio");
        _audioEncodingMap.put(Integer.valueOf((int) SimError.LISTEN_ERROR_BUSY), "QDesign Music");
        _audioEncodingMap.put(1280, "On2 VP7 On2 Technologies");
        _audioEncodingMap.put(1281, "On2 VP6 On2 Technologies");
        _audioEncodingMap.put(1664, "AT&T VME VMPCM");
        _audioEncodingMap.put(1665, "AT&T TCP");
        _audioEncodingMap.put(1792, "YMPEG Alpha (dummy for MPEG-2 compressor)");
        _audioEncodingMap.put(2222, "ClearJump LiteWave (lossless)");
        _audioEncodingMap.put(4096, "Olivetti GSM");
        _audioEncodingMap.put(4097, "Olivetti ADPCM");
        _audioEncodingMap.put(4098, "Olivetti CELP");
        _audioEncodingMap.put(4099, "Olivetti SBC");
        _audioEncodingMap.put(4100, "Olivetti OPR");
        _audioEncodingMap.put(Integer.valueOf((int) FujifilmMakernoteDirectory.TAG_AUTO_BRACKETING), "Lernout & Hauspie");
        _audioEncodingMap.put(Integer.valueOf((int) FujifilmMakernoteDirectory.TAG_SEQUENCE_NUMBER), "Lernout & Hauspie CELP codec");
        _audioEncodingMap.put(4354, "Lernout & Hauspie SBC codec");
        _audioEncodingMap.put(Integer.valueOf((int) OlympusImageProcessingMakernoteDirectory.TagUnknownBlock3), "Lernout & Hauspie SBC codec");
        _audioEncodingMap.put(Integer.valueOf((int) OlympusImageProcessingMakernoteDirectory.TagUnknownBlock4), "Lernout & Hauspie SBC codec");
        _audioEncodingMap.put(Integer.valueOf((int) FujifilmMakernoteDirectory.TAG_DYNAMIC_RANGE), "Norris Comm. Inc.");
        _audioEncodingMap.put(Integer.valueOf((int) FujifilmMakernoteDirectory.TAG_FILM_MODE), "ISIAudio");
        _audioEncodingMap.put(Integer.valueOf((int) OlympusFocusInfoMakernoteDirectory.TagSensorTemperature), "AT&T Soundspace Music Compression");
        _audioEncodingMap.put(6172, "VoxWare RT24 speech codec");
        _audioEncodingMap.put(6174, "Lucent elemedia AX24000P Music codec");
        _audioEncodingMap.put(6513, "Sonic Foundry LOSSLESS");
        _audioEncodingMap.put(6521, "Innings Telecom Inc. ADPCM");
        _audioEncodingMap.put(7175, "Lucent SX8300P speech codec");
        _audioEncodingMap.put(7180, "Lucent SX5363S G.723 compliant codec");
        _audioEncodingMap.put(7939, "CUseeMe DigiTalk (ex-Rocwell)");
        _audioEncodingMap.put(8132, "NCT Soft ALF2CD ACM");
        _audioEncodingMap.put(8192, "FAST Multimedia DVM");
        _audioEncodingMap.put(8193, "Dolby DTS (Digital Theater System)");
        _audioEncodingMap.put(8194, "RealAudio 1 / 2 14.4");
        _audioEncodingMap.put(8195, "RealAudio 1 / 2 28.8");
        _audioEncodingMap.put(Integer.valueOf((int) SonyType1MakernoteDirectory.TAG_CONTRAST), "RealAudio G2 / 8 Cook (low bitrate)");
        _audioEncodingMap.put(Integer.valueOf((int) SonyType1MakernoteDirectory.TAG_SATURATION), "RealAudio 3 / 4 / 5 Music (DNET)");
        _audioEncodingMap.put(Integer.valueOf((int) SonyType1MakernoteDirectory.TAG_SHARPNESS), "RealAudio 10 AAC (RAAC)");
        _audioEncodingMap.put(Integer.valueOf((int) SonyType1MakernoteDirectory.TAG_BRIGHTNESS), "RealAudio 10 AAC+ (RACP)");
        _audioEncodingMap.put(9472, "Reserved range to 0x2600 Microsoft");
        _audioEncodingMap.put(13075, "makeAVIS (ffvfw fake AVI sound from AviSynth scripts)");
        _audioEncodingMap.put(16707, "Divio MPEG-4 AAC audio");
        _audioEncodingMap.put(16897, "Nokia adaptive multirate");
        _audioEncodingMap.put(16963, "Divio G726 Divio, Inc.");
        _audioEncodingMap.put(17228, "LEAD Speech");
        _audioEncodingMap.put(22092, "LEAD Vorbis");
        _audioEncodingMap.put(22358, "WavPack Audio");
        _audioEncodingMap.put(26447, "Ogg Vorbis (mode 1)");
        _audioEncodingMap.put(26448, "Ogg Vorbis (mode 2)");
        _audioEncodingMap.put(26449, "Ogg Vorbis (mode 3)");
        _audioEncodingMap.put(26479, "Ogg Vorbis (mode 1+)");
        _audioEncodingMap.put(26480, "Ogg Vorbis (mode 2+)");
        _audioEncodingMap.put(26481, "Ogg Vorbis (mode 3+)");
        _audioEncodingMap.put(28672, "3COM NBX 3Com Corporation");
        _audioEncodingMap.put(28781, "FAAD AAC");
        _audioEncodingMap.put(31265, "GSM-AMR (CBR, no SID)");
        _audioEncodingMap.put(31266, "GSM-AMR (VBR, including SID)");
        _audioEncodingMap.put(41216, "Comverse Infosys Ltd. G723 1");
        _audioEncodingMap.put(41217, "Comverse Infosys Ltd. AVQSBC");
        _audioEncodingMap.put(41218, "Comverse Infosys Ltd. OLDSBC");
        _audioEncodingMap.put(41219, "Symbol Technologies G729A");
        _audioEncodingMap.put(41220, "VoiceAge AMR WB VoiceAge Corporation");
        _audioEncodingMap.put(41221, "Ingenient Technologies Inc. G726");
        _audioEncodingMap.put(41222, "ISO/MPEG-4 advanced audio Coding");
        _audioEncodingMap.put(41223, "Encore Software Ltd G726");
        _audioEncodingMap.put(41225, "Speex ACM Codec xiph.org");
        _audioEncodingMap.put(57260, "DebugMode SonicFoundry Vegas FrameServer ACM Codec");
        _audioEncodingMap.put(59144, "Unknown -");
        _audioEncodingMap.put(61868, "Free Lossless Audio Codec FLAC");
        _audioEncodingMap.put(Integer.valueOf((int) WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE), "Extensible");
        _audioEncodingMap.put(65535, "Development");
    }

    public WavDirectory() {
        setDescriptor(new WavDescriptor(this));
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "WAV";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
