package id.ac.polinema.lumajang.portalku.config.lampiran;

import org.springframework.stereotype.Component;

@Component
public class LampiranMapper {

    public LampiranResponse toResponse(Lampiran lampiran) {
        return new LampiranResponse(
                lampiran.getId(),
                lampiran.getNamaBerkas(),
                lampiran.getUkuran()
        );
    }

    public Lampiran toEntity(LampiranRequest req) {
        Lampiran lampiran = new Lampiran();

        lampiran.setNamaBerkas(req.namaBerkas());
        lampiran.setUkuran(req.ukuran());

        return lampiran;
    }
}