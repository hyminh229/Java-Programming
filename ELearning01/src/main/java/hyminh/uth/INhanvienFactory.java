package hyminh.uth;

// gom co 2 methods khai vao voi tu khoa truy cap public
// create nhan vien 4 tham so va 1 tham so
public interface INhanvienFactory {
    public Nhanvien createNhanvien(String loaiNV, String hoten, String maso, double luongCB);
    public Nhanvien createNhanvien(String loaiNV);
}
