package id.ac.polinema.lumajang.portalku.kurikulum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record KurikulumRequest(

        @NotBlank(message = "Kode tidak boleh kosong")
        String kode,

        @NotNull(message = "Tahun berlaku tidak boleh kosong")
        Integer tahunBerlaku,

        @NotBlank(message = "Status tidak boleh kosong")
        String status,

        @NotNull(message = "Id prodi tidak boleh kosong")
        Integer idProdi

) {
}