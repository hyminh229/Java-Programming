package hyminh.uth;
// extends: ke thua tu mot class truu tuong
class ChuyenvienPhantich extends Nhanvien{
    public ChuyenvienPhantich(){
        super();   //constructor khong tham so va thua huong
    }
    public ChuyenvienPhantich(String maso, String hoten, double luongCB){
        super(maso, hoten, luongCB);
    }
}
