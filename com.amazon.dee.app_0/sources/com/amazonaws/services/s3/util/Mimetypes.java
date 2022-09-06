package com.amazonaws.services.s3.util;

import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.amazonaws.regions.ServiceAbbreviations;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/* loaded from: classes13.dex */
public final class Mimetypes {
    public static final String MIMETYPE_GZIP = "application/x-gzip";
    public static final String MIMETYPE_HTML = "text/html";
    public static final String MIMETYPE_OCTET_STREAM = "application/octet-stream";
    public static final String MIMETYPE_XML = "application/xml";
    private static final Log log = LogFactory.getLog(Mimetypes.class);
    private static Mimetypes mimetypes = null;
    private final HashMap<String, String> extensionToMimetypeMap = new HashMap<>();

    Mimetypes() {
        this.extensionToMimetypeMap.put("3gp", MimeTypes.VIDEO_H263);
        this.extensionToMimetypeMap.put("ai", "application/postscript");
        this.extensionToMimetypeMap.put("aif", "audio/x-aiff");
        this.extensionToMimetypeMap.put("aifc", "audio/x-aiff");
        this.extensionToMimetypeMap.put("aiff", "audio/x-aiff");
        this.extensionToMimetypeMap.put(AppUrl.ACMS.QueryParam.Values.ASCENDING, Constants.TEXT_PLAIN_MEDIA_TYPE);
        this.extensionToMimetypeMap.put("atom", "application/atom+xml");
        this.extensionToMimetypeMap.put("au", "audio/basic");
        this.extensionToMimetypeMap.put("avi", "video/x-msvideo");
        this.extensionToMimetypeMap.put("bcpio", "application/x-bcpio");
        this.extensionToMimetypeMap.put("bin", "application/octet-stream");
        this.extensionToMimetypeMap.put("bmp", "image/bmp");
        this.extensionToMimetypeMap.put("cdf", "application/x-netcdf");
        this.extensionToMimetypeMap.put("cgm", "image/cgm");
        this.extensionToMimetypeMap.put("class", "application/octet-stream");
        this.extensionToMimetypeMap.put("cpio", "application/x-cpio");
        this.extensionToMimetypeMap.put("cpt", "application/mac-compactpro");
        this.extensionToMimetypeMap.put("csh", "application/x-csh");
        this.extensionToMimetypeMap.put("css", "text/css");
        this.extensionToMimetypeMap.put("dcr", "application/x-director");
        this.extensionToMimetypeMap.put("dif", "video/x-dv");
        this.extensionToMimetypeMap.put("dir", "application/x-director");
        this.extensionToMimetypeMap.put("djv", "image/vnd.djvu");
        this.extensionToMimetypeMap.put("djvu", "image/vnd.djvu");
        this.extensionToMimetypeMap.put("dll", "application/octet-stream");
        this.extensionToMimetypeMap.put("dmg", "application/octet-stream");
        this.extensionToMimetypeMap.put("dms", "application/octet-stream");
        this.extensionToMimetypeMap.put("doc", "application/msword");
        this.extensionToMimetypeMap.put("dtd", "application/xml-dtd");
        this.extensionToMimetypeMap.put("dv", "video/x-dv");
        this.extensionToMimetypeMap.put("dvi", "application/x-dvi");
        this.extensionToMimetypeMap.put("dxr", "application/x-director");
        this.extensionToMimetypeMap.put("eps", "application/postscript");
        this.extensionToMimetypeMap.put("etx", "text/x-setext");
        this.extensionToMimetypeMap.put("exe", "application/octet-stream");
        this.extensionToMimetypeMap.put("ez", "application/andrew-inset");
        this.extensionToMimetypeMap.put("flv", MimeTypes.VIDEO_FLV);
        this.extensionToMimetypeMap.put("gif", "image/gif");
        this.extensionToMimetypeMap.put("gram", "application/srgs");
        this.extensionToMimetypeMap.put("grxml", "application/srgs+xml");
        this.extensionToMimetypeMap.put("gtar", "application/x-gtar");
        this.extensionToMimetypeMap.put("gz", MIMETYPE_GZIP);
        this.extensionToMimetypeMap.put("hdf", "application/x-hdf");
        this.extensionToMimetypeMap.put("hqx", "application/mac-binhex40");
        this.extensionToMimetypeMap.put("htm", "text/html");
        this.extensionToMimetypeMap.put("html", "text/html");
        this.extensionToMimetypeMap.put("ice", "x-conference/x-cooltalk");
        this.extensionToMimetypeMap.put("ico", "image/x-icon");
        this.extensionToMimetypeMap.put("ics", "text/calendar");
        this.extensionToMimetypeMap.put("ief", "image/ief");
        this.extensionToMimetypeMap.put("ifb", "text/calendar");
        this.extensionToMimetypeMap.put("iges", "model/iges");
        this.extensionToMimetypeMap.put("igs", "model/iges");
        this.extensionToMimetypeMap.put("jnlp", "application/x-java-jnlp-file");
        this.extensionToMimetypeMap.put("jp2", "image/jp2");
        this.extensionToMimetypeMap.put("jpe", MimeTypes.IMAGE_JPEG);
        this.extensionToMimetypeMap.put("jpeg", MimeTypes.IMAGE_JPEG);
        this.extensionToMimetypeMap.put(ImageType.JPG, MimeTypes.IMAGE_JPEG);
        this.extensionToMimetypeMap.put("js", "application/x-javascript");
        this.extensionToMimetypeMap.put("kar", "audio/midi");
        this.extensionToMimetypeMap.put("latex", "application/x-latex");
        this.extensionToMimetypeMap.put("lha", "application/octet-stream");
        this.extensionToMimetypeMap.put("lzh", "application/octet-stream");
        this.extensionToMimetypeMap.put("m3u", "audio/x-mpegurl");
        this.extensionToMimetypeMap.put("m4a", MimeTypes.AUDIO_AAC);
        this.extensionToMimetypeMap.put("m4p", MimeTypes.AUDIO_AAC);
        this.extensionToMimetypeMap.put("m4u", "video/vnd.mpegurl");
        this.extensionToMimetypeMap.put("m4v", "video/x-m4v");
        this.extensionToMimetypeMap.put("mac", "image/x-macpaint");
        this.extensionToMimetypeMap.put("man", "application/x-troff-man");
        this.extensionToMimetypeMap.put("mathml", "application/mathml+xml");
        this.extensionToMimetypeMap.put("me", "application/x-troff-me");
        this.extensionToMimetypeMap.put("mesh", "model/mesh");
        this.extensionToMimetypeMap.put("mid", "audio/midi");
        this.extensionToMimetypeMap.put("midi", "audio/midi");
        this.extensionToMimetypeMap.put("mif", "application/vnd.mif");
        this.extensionToMimetypeMap.put("mov", "video/quicktime");
        this.extensionToMimetypeMap.put("movie", "video/x-sgi-movie");
        this.extensionToMimetypeMap.put("mp2", MimeTypes.AUDIO_MPEG);
        this.extensionToMimetypeMap.put("mp3", MimeTypes.AUDIO_MPEG);
        this.extensionToMimetypeMap.put("mp4", MimeTypes.VIDEO_MP4);
        this.extensionToMimetypeMap.put("mpe", MimeTypes.VIDEO_MPEG);
        this.extensionToMimetypeMap.put("mpeg", MimeTypes.VIDEO_MPEG);
        this.extensionToMimetypeMap.put("mpg", MimeTypes.VIDEO_MPEG);
        this.extensionToMimetypeMap.put("mpga", MimeTypes.AUDIO_MPEG);
        this.extensionToMimetypeMap.put("ms", "application/x-troff-ms");
        this.extensionToMimetypeMap.put("msh", "model/mesh");
        this.extensionToMimetypeMap.put("mxu", "video/vnd.mpegurl");
        this.extensionToMimetypeMap.put("nc", "application/x-netcdf");
        this.extensionToMimetypeMap.put("oda", "application/oda");
        this.extensionToMimetypeMap.put("ogg", "application/ogg");
        this.extensionToMimetypeMap.put("ogv", "video/ogv");
        this.extensionToMimetypeMap.put("pbm", "image/x-portable-bitmap");
        this.extensionToMimetypeMap.put("pct", "image/pict");
        this.extensionToMimetypeMap.put("pdb", "chemical/x-pdb");
        this.extensionToMimetypeMap.put("pdf", "application/pdf");
        this.extensionToMimetypeMap.put("pgm", "image/x-portable-graymap");
        this.extensionToMimetypeMap.put("pgn", "application/x-chess-pgn");
        this.extensionToMimetypeMap.put("pic", "image/pict");
        this.extensionToMimetypeMap.put("pict", "image/pict");
        this.extensionToMimetypeMap.put(ImageType.PNG, "image/png");
        this.extensionToMimetypeMap.put("pnm", "image/x-portable-anymap");
        this.extensionToMimetypeMap.put("pnt", "image/x-macpaint");
        this.extensionToMimetypeMap.put("pntg", "image/x-macpaint");
        this.extensionToMimetypeMap.put("ppm", "image/x-portable-pixmap");
        this.extensionToMimetypeMap.put("ppt", "application/vnd.ms-powerpoint");
        this.extensionToMimetypeMap.put("ps", "application/postscript");
        this.extensionToMimetypeMap.put("qt", "video/quicktime");
        this.extensionToMimetypeMap.put("qti", "image/x-quicktime");
        this.extensionToMimetypeMap.put("qtif", "image/x-quicktime");
        this.extensionToMimetypeMap.put("ra", "audio/x-pn-realaudio");
        this.extensionToMimetypeMap.put("ram", "audio/x-pn-realaudio");
        this.extensionToMimetypeMap.put("ras", "image/x-cmu-raster");
        this.extensionToMimetypeMap.put("rdf", "application/rdf+xml");
        this.extensionToMimetypeMap.put("rgb", "image/x-rgb");
        this.extensionToMimetypeMap.put("rm", "application/vnd.rn-realmedia");
        this.extensionToMimetypeMap.put("roff", "application/x-troff");
        this.extensionToMimetypeMap.put("rtf", "text/rtf");
        this.extensionToMimetypeMap.put("rtx", "text/richtext");
        this.extensionToMimetypeMap.put("sgm", "text/sgml");
        this.extensionToMimetypeMap.put("sgml", "text/sgml");
        this.extensionToMimetypeMap.put("sh", "application/x-sh");
        this.extensionToMimetypeMap.put("shar", "application/x-shar");
        this.extensionToMimetypeMap.put("silo", "model/mesh");
        this.extensionToMimetypeMap.put("sit", "application/x-stuffit");
        this.extensionToMimetypeMap.put("skd", "application/x-koan");
        this.extensionToMimetypeMap.put("skm", "application/x-koan");
        this.extensionToMimetypeMap.put("skp", "application/x-koan");
        this.extensionToMimetypeMap.put("skt", "application/x-koan");
        this.extensionToMimetypeMap.put("smi", "application/smil");
        this.extensionToMimetypeMap.put("smil", "application/smil");
        this.extensionToMimetypeMap.put("snd", "audio/basic");
        this.extensionToMimetypeMap.put("so", "application/octet-stream");
        this.extensionToMimetypeMap.put("spl", "application/x-futuresplash");
        this.extensionToMimetypeMap.put("src", "application/x-wais-source");
        this.extensionToMimetypeMap.put("sv4cpio", "application/x-sv4cpio");
        this.extensionToMimetypeMap.put("sv4crc", "application/x-sv4crc");
        this.extensionToMimetypeMap.put("svg", "image/svg+xml");
        this.extensionToMimetypeMap.put(ServiceAbbreviations.SimpleWorkflow, "application/x-shockwave-flash");
        this.extensionToMimetypeMap.put("t", "application/x-troff");
        this.extensionToMimetypeMap.put("tar", "application/x-tar");
        this.extensionToMimetypeMap.put("tcl", "application/x-tcl");
        this.extensionToMimetypeMap.put("tex", "application/x-tex");
        this.extensionToMimetypeMap.put("texi", "application/x-texinfo");
        this.extensionToMimetypeMap.put("texinfo", "application/x-texinfo");
        this.extensionToMimetypeMap.put("tif", "image/tiff");
        this.extensionToMimetypeMap.put("tiff", "image/tiff");
        this.extensionToMimetypeMap.put("tr", "application/x-troff");
        this.extensionToMimetypeMap.put("tsv", "text/tab-separated-values");
        this.extensionToMimetypeMap.put("txt", Constants.TEXT_PLAIN_MEDIA_TYPE);
        this.extensionToMimetypeMap.put("ustar", "application/x-ustar");
        this.extensionToMimetypeMap.put("vcd", "application/x-cdlink");
        this.extensionToMimetypeMap.put("vrml", "model/vrml");
        this.extensionToMimetypeMap.put("vxml", "application/voicexml+xml");
        this.extensionToMimetypeMap.put("wav", "audio/x-wav");
        this.extensionToMimetypeMap.put("wbmp", "image/vnd.wap.wbmp");
        this.extensionToMimetypeMap.put("wbxml", "application/vnd.wap.wbxml");
        this.extensionToMimetypeMap.put("webm", MimeTypes.VIDEO_WEBM);
        this.extensionToMimetypeMap.put("wml", "text/vnd.wap.wml");
        this.extensionToMimetypeMap.put("wmlc", "application/vnd.wap.wmlc");
        this.extensionToMimetypeMap.put("wmls", "text/vnd.wap.wmlscript");
        this.extensionToMimetypeMap.put("wmlsc", "application/vnd.wap.wmlscriptc");
        this.extensionToMimetypeMap.put("wmv", "video/x-ms-wmv");
        this.extensionToMimetypeMap.put("wrl", "model/vrml");
        this.extensionToMimetypeMap.put("xbm", "image/x-xbitmap");
        this.extensionToMimetypeMap.put("xht", "application/xhtml+xml");
        this.extensionToMimetypeMap.put("xhtml", "application/xhtml+xml");
        this.extensionToMimetypeMap.put("xls", "application/vnd.ms-excel");
        this.extensionToMimetypeMap.put("xml", MIMETYPE_XML);
        this.extensionToMimetypeMap.put("xpm", "image/x-xpixmap");
        this.extensionToMimetypeMap.put("xsl", MIMETYPE_XML);
        this.extensionToMimetypeMap.put("xslt", "application/xslt+xml");
        this.extensionToMimetypeMap.put("xul", "application/vnd.mozilla.xul+xml");
        this.extensionToMimetypeMap.put("xwd", "image/x-xwindowdump");
        this.extensionToMimetypeMap.put("xyz", "chemical/x-xyz");
        this.extensionToMimetypeMap.put("zip", "application/zip");
    }

    public static synchronized Mimetypes getInstance() {
        synchronized (Mimetypes.class) {
            if (mimetypes != null) {
                return mimetypes;
            }
            mimetypes = new Mimetypes();
            if (log.isDebugEnabled()) {
                HashMap<String, String> hashMap = mimetypes.extensionToMimetypeMap;
                for (String str : hashMap.keySet()) {
                    Log log2 = log;
                    log2.debug("Setting mime type for extension '" + str + "' to '" + hashMap.get(str) + "'");
                }
            }
            return mimetypes;
        }
    }

    public String getMimetype(String str) {
        int i;
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf > 0 && (i = lastIndexOf + 1) < str.length()) {
            String lowerCase = StringUtils.lowerCase(str.substring(i));
            if (this.extensionToMimetypeMap.containsKey(lowerCase)) {
                String str2 = this.extensionToMimetypeMap.get(lowerCase);
                if (log.isDebugEnabled()) {
                    log.debug(GeneratedOutlineSupport1.outline77("Recognised extension '", lowerCase, "', mimetype is: '", str2, "'"));
                }
                return str2;
            } else if (log.isDebugEnabled()) {
                log.debug(GeneratedOutlineSupport1.outline77("Extension '", lowerCase, "' is unrecognized in mime type listing, using default mime type: '", "application/octet-stream", "'"));
            }
        } else if (log.isDebugEnabled()) {
            Log log2 = log;
            log2.debug("File name has no extension, mime type cannot be recognised for: " + str);
        }
        return "application/octet-stream";
    }

    public void loadAndReplaceMimetypes(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StringUtils.UTF8));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                String trim = readLine.trim();
                if (!trim.startsWith(MqttTopic.MULTI_LEVEL_WILDCARD) && trim.length() != 0) {
                    StringTokenizer stringTokenizer = new StringTokenizer(trim, " \t");
                    if (stringTokenizer.countTokens() > 1) {
                        String nextToken = stringTokenizer.nextToken();
                        while (stringTokenizer.hasMoreTokens()) {
                            String nextToken2 = stringTokenizer.nextToken();
                            this.extensionToMimetypeMap.put(StringUtils.lowerCase(nextToken2), nextToken);
                            if (log.isDebugEnabled()) {
                                Log log2 = log;
                                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Setting mime type for extension '");
                                outline107.append(StringUtils.lowerCase(nextToken2));
                                outline107.append("' to '");
                                outline107.append(nextToken);
                                outline107.append("'");
                                log2.debug(outline107.toString());
                            }
                        }
                    } else if (log.isDebugEnabled()) {
                        Log log3 = log;
                        log3.debug("Ignoring mimetype with no associated file extensions: '" + trim + "'");
                    }
                } else {
                    log.debug("Ignoring comments and empty lines.");
                }
            } else {
                return;
            }
        }
    }

    public void registerMimetype(String str, String str2) {
        this.extensionToMimetypeMap.put(StringUtils.lowerCase(str), str2);
    }

    public String getMimetype(File file) {
        return getMimetype(file.getName());
    }
}
