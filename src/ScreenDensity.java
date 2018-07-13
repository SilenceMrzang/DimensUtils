/**
 * 屏幕密度枚举参数
 *
 * @author mr.zang
 * @date 2018/07/13
 */
public enum ScreenDensity {
//    DENSITY_300(300),
//    DENSITY_310(310),
    DENSITY_320(320),
    DENSITY_330(330),
    DENSITY_340(340),
    DENSITY_350(350),
    DENSITY_360(360),
    DENSITY_370(370),
    DENSITY_380(380),
    DENSITY_390(390),
    DENSITY_400(400);


    private int density;

    ScreenDensity(int density) {
        this.density = density;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }
}
