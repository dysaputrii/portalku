package id.ac.polinema.lumajang.portalku.kurikulum;

import org.springframework.stereotype.Component;

import id.ac.polinema.lumajang.portalku.prodi.Prodi;

@Component
public class KurikulumMapper {

    public KurikulumResponse keResponse(Kurikulum k) {
        return new KurikulumResponse(
                k.getId(),
                k.getKode(),
                k.getTahunBerlaku(),
                k.getStatus().name(),
                k.getProdi().getId(),
                k.getProdi().getNama()
        );
    }

    public Kurikulum keEntity(
            KurikulumRequest req,
            Prodi prodi) {

        Kurikulum k = new Kurikulum();

        k.setKode(req.kode());
        k.setTahunBerlaku(req.tahunBerlaku());
        k.setStatus(
                Kurikulum.Status.valueOf(req.status()));
        k.setProdi(prodi);

        return k;
    }

    public void terapkan(
            KurikulumRequest req,
            Kurikulum k,
            Prodi prodi) {

        k.setKode(req.kode());
        k.setTahunBerlaku(req.tahunBerlaku());
        k.setStatus(
                Kurikulum.Status.valueOf(req.status()));
        k.setProdi(prodi);
    }
}