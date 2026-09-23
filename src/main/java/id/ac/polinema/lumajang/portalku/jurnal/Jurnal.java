package id.ac.polinema.lumajang.portalku.jurnal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jurnal")
public class Jurnal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String judul;

    @Column(nullable = false, length = 100)
    private String penerbit;

    @Column(name = "tahun_terbit", nullable = false)
    private Integer tahunTerbit;

    @Column(length = 20)
    private String issn;

    public Jurnal() {}

    public Jurnal(Integer id, String judul, String penerbit, Integer tahunTerbit, String issn) {
        this.id = id;
        this.judul = judul;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
        this.issn = issn;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getPenerbit() { return penerbit; }
    public void setPenerbit(String penerbit) { this.penerbit = penerbit; }

    public Integer getTahunTerbit() { return tahunTerbit; }
    public void setTahunTerbit(Integer tahunTerbit) { this.tahunTerbit = tahunTerbit; }

    public String getIssn() { return issn; }
    public void setIssn(String issn) { this.issn = issn; }
}