package id.ac.polinema.lumajang.portalku.kurikulum;

public record KurikulumResponse(

        Integer id,
        String kode,
        Integer tahunBerlaku,
        String status,
        Integer idProdi,
        String namaProdi

) {
}