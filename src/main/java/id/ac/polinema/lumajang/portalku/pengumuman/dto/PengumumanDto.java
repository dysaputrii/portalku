package id.ac.polinema.lumajang.portalku.pengumuman.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PengumumanDto {

    private Integer id;
    private String judul;
    private String isi;
    private LocalDate tanggalTerbit;
    private Integer jumlahDilihat;
    private String namaKategori;
    private int jumlahLampiran;
}
