package example.config;

import java.io.File;


public final class TestDataProvider {
    public static final String ABS_PATH = System.getProperty("user.dir") + "/";
    public static final File  AVATAR_FILE = new File(ABS_PATH + "src" +File.separator + "main" + File.separator + "resources" + File.separator
            + "test-content" + File.separator + "picture.jpg");
    public static final File AVATAR_FILE_JPEG =  new File(ABS_PATH + "src" +File.separator + "main" + File.separator + "resources" + File.separator
            + "test-content" + File.separator + "picturejpeg.jpeg");
    public static final File AVATAR_FILE_PNG =  new File(ABS_PATH + "src" +File.separator + "main" + File.separator + "resources" + File.separator
            + "test-content" + File.separator + "picturepng.png");
    public static final File SAMPLE_MP3_FILE = new File(ABS_PATH + "src" +File.separator + "main" + File.separator + "resources" + File.separator
            + "test-content" + File.separator +"sample-15s.mp3");
    public static final File INVALID_MP3_FILE = new File(ABS_PATH + "src" +File.separator + "main" + File.separator + "resources" + File.separator
            + "test-content" + File.separator +"invalid-track.mp3");
    public static final File RADIOACTIVE_MP3 = new File(ABS_PATH + "src" +File.separator + "main" + File.separator + "resources" + File.separator
            + "test-content" + File.separator +"Radioactive.mp3");
    public static final File CAT_GIF = new File(ABS_PATH + "src" +File.separator + "main" + File.separator + "resources" + File.separator
            + "test-content" + File.separator +"CatGif.gif");
    public static final File Tiff = new File(ABS_PATH + "src" +File.separator + "main" + File.separator + "resources" + File.separator
            + "test-content" + File.separator +"Tiff.webp");
}
