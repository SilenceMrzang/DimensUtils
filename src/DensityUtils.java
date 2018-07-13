import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author mr.zang
 * @data 2018/07/13
 * 换算工具类
 */
public class DensityUtils {
    //页眉
    private static final String DIMENS_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<resources>\r\n";
    //页脚
    private static final String DIMENS_FOOTER = "</resources>\r\n";

    private static final String VALUES_NAME = "values_sw%1$ddp";

    private static final String DIMENS = "dimens.xml";

    private static final String DIMENS_ITEM = "<dimen name=\"DIMEN_%1$d_PX\">%2$.2fdp</dimen>\r\n";

    //最大宽度
    private static final int MAX_WIDTH = 1440;

    /**
     * 生成文件
     *
     * @param filePath
     * @param screenDensities
     */
    public static void initFolder(String filePath, ScreenDensity[] screenDensities) {
        if (isEmpty(filePath)) throw new NullPointerException("文件地址为空");

        File fileSuperFolder = new File(filePath);
        if (!fileSuperFolder.exists()) {
            fileSuperFolder.mkdirs();
        }

        for (int i = 0; i < screenDensities.length; i++) {

            String fileFolderName = fileSuperFolder.getAbsolutePath() + File.separator + VALUES_NAME;
            fileFolderName = String.format(fileFolderName, screenDensities[i].getDensity());
            File fileFolder = new File(fileFolderName);

            if (!fileFolder.exists()) {
                fileFolder.mkdirs();
            }

            String fileName = fileFolder.getAbsolutePath() + File.separator + DIMENS;

            try {
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                StringBuffer buffer = new StringBuffer();
                buffer.append(DIMENS_HEADER);
                //添加主体
                makeAllDimens(screenDensities[i], buffer);

                buffer.append(DIMENS_FOOTER);

                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(buffer.toString().getBytes());
                outputStream.flush();
                outputStream.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 生成所有Dimens
     *
     * @param screenDensity
     * @param buffer
     */
    private static void makeAllDimens(ScreenDensity screenDensity, StringBuffer buffer) {
        for (int i = 1; i <= MAX_WIDTH; i++) {
            buffer.append(String.format(DIMENS_ITEM, i, px2dp(i, screenDensity.getDensity())));
        }
    }

    /**
     * @param str
     * @return
     */
    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * @param pxValue
     * @param sw
     * @return
     */
    public static float px2dp(float pxValue, int sw) {
        float dpValue = (pxValue / (sw / 160)) + 0.5f;
        BigDecimal bigDecimal = new BigDecimal(dpValue);
        float finDp = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return finDp;
    }
}
