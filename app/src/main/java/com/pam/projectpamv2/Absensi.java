package ap.mobile.projeksimpeg2;
public class Absensi {
    public String nama;
    public String tanggal;
    public int fotoResourceId;
    public String jam;
    public String lokasi;
    public Absensi(String nama, String tanggal, String jam, String lokasi, int fotoResourceId) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.jam = jam;
        this.lokasi = lokasi;
        this.fotoResourceId = fotoResourceId;
    }

}
