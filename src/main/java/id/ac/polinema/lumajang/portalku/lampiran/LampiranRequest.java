package id.ac.polinema.lumajang.portalku.config.lampiran;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LampiranRequest(

        @NotBlank(message = "Nama berkas tidak boleh kosong")
        String namaBerkas,

        @NotNull(message = "Ukuran tidak boleh kosong")
        Long ukuran

) {
}