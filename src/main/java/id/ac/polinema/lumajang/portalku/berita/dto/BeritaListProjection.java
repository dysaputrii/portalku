package id.ac.polinema.lumajang.portalku.berita.dto;

import java.time.LocalDate;

public interface BeritaListProjection {

    Integer getId();

    String getJudul();

    LocalDate getTanggalTerbit();

    Integer getJumlahDilihat();

    String getNamaKategori();
}