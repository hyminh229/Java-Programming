package hyminh.uth;
// extends: ke thua tu mot class truu tuong
class NhanvienKiemthu extends Nhanvien{
    public NhanvienKiemthu(){
        super();   //constructor khong tham so va thua huong
    }
    public NhanvienKiemthu(String maso, String hoten, double luongCB){
        super(maso, hoten, luongCB);
    }
}
