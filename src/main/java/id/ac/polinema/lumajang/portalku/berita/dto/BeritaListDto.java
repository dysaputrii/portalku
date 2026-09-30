package id.ac.polinema.lumajang.portalku.berita.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BeritaListDto {

    private Integer id;
    private String judul;
    private LocalDate tanggalTerbit;
    private Integer jumlahDilihat;
    private String namaKategori;
}