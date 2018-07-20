package sample;

public class Line {
    private int number;
    //private String date;
    private String sum_plat, osn_dolg, nach_percent, ostatok;

    public Line(int number, String sum_plat, String osn_dolg, String nach_percent, String ostatok) {
        this.number = number;
        this.sum_plat = sum_plat;
        this.osn_dolg = osn_dolg;
        this.nach_percent = nach_percent;
        this.ostatok = ostatok;
    }

    public int getNumber() {
        return number;
    }

    public String getSum_plat() {
        return sum_plat;
    }

    public String getOsn_dolg() {
        return osn_dolg;
    }

    public String getNach_percent() {
        return nach_percent;
    }

    public String getOstatok() {
        return ostatok;
    }
}
