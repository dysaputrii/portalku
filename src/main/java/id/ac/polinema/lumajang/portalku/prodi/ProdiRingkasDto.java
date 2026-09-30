package id.ac.polinema.lumajang.portalku.prodi;

import id.ac.polinema.lumajang.portalku.prodi.Prodi;

public record ProdiRingkasDto(
        Integer id,
        String kode,
        String nama,
        Prodi.Jenjang jenjang,
        int jumlahKurikulum
) {
}