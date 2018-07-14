public class Main {

    public static void main(String[] args) {
        //生成适配文件
        ScreenUtils.getInstance().initFolder("../dimens", ScreenDensityEnum.values());
    }
}
