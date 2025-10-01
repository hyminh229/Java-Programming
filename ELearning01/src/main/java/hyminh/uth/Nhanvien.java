package hyminh.uth;

abstract class Nhanvien {
    protected String maso;
    protected String hoten;
    protected double luongCB;
    protected ITienthuong phuongthucTinhThuong;


    public Nhanvien(String maso, String hoten, double luongCB) {
        this.maso = maso;
        this.hoten = hoten;
        this.luongCB = luongCB;
    }
    //constructor khong tham so
    public Nhanvien() {

    }

    @Override
    public String toString() {
        return "Nhanvien{" +
                "maso=" + maso +
                ", hoten=" + hoten +
                ", luongCB=" + luongCB +
                ", phuongthucTinhThuong=" + phuongthucTinhThuong +
                '}';
    }

    //phuong thuc getTienthong tra ve double
    public double getTienthuong(){
        return luongCB;
    }

    //generate getter
    public String getMaso() {
        return maso;
    }

    public String getHoten() {
        return hoten;
    }

    public double getLuongCB() {
        return luongCB;
    }

    public ITienthuong getPhuongthucTinhThuong() {
        return phuongthucTinhThuong;
    }

    // generate setter
    public void setMaso(String maso) {
        this.maso = maso;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setLuongCB(double luongCB) {
        this.luongCB = luongCB;
    }

    public void setPhuongthucTinhThuong(ITienthuong phuongthucTinhThuong) {
        this.phuongthucTinhThuong = phuongthucTinhThuong;
    }


}
