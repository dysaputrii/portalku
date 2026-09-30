package id.ac.polinema.lumajang.portalku.prodi;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.ac.polinema.lumajang.portalku.prodi.Prodi;
import id.ac.polinema.lumajang.portalku.prodi.ProdiRepository;

@Service
public class ProdiService {

    private final ProdiRepository repository;

    public ProdiService(ProdiRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ProdiRingkasDto getRingkas(Integer id) {
        Prodi prodi = repository.findById(id).orElseThrow();

        int jumlahKurikulum = prodi.getDaftarKurikulum().size();

        return new ProdiRingkasDto(
                prodi.getId(),
                prodi.getKode(),
                prodi.getNama(),
                prodi.getJenjang(),
                jumlahKurikulum
        );
    }
}