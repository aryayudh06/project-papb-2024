package ap.mobile.projeksimpeg2;

public class Pegawai {
    public String namaPegawai;
    public String gender;
    public int fotoId;
    public String jabatan;
    public String ktp;

    public Pegawai(String namaPegawai, String gender, String jabatan, String ktp, int fotoId) {
        this.namaPegawai = namaPegawai;
        this.gender = gender;
        this.jabatan = jabatan;
        this.ktp = ktp;
        this.fotoId = fotoId;
    }
}
