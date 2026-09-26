package id.ac.polinema.lumajang.portalku.pengumuman.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PengumumanRequest(

        @NotBlank(message = "Judul tidak boleh kosong")
        String judul,

        @NotBlank(message = "Isi tidak boleh kosong")
        String isi,

        @NotNull(message = "Tanggal terbit tidak boleh kosong")
        LocalDate tanggalTerbit,

        @NotNull(message = "Kategori tidak boleh kosong")
        Integer idKategori) {

}