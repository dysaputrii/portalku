package id.ac.polinema.lumajang.portalku.berita;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.ac.polinema.lumajang.portalku.berita.dto.BeritaListProjection;

@Service
public class BeritaService {

    private final BeritaRepository repository;

    public BeritaService(BeritaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<BeritaListProjection> getAll(
            String kategori,
            String cari,
            Pageable pageable) {

        return repository.findAllForList(
                kategori,
                cari,
                pageable);
    }

    @Transactional
    public int naikkanJumlahDilihat(Integer id) {
        return repository.naikkanJumlahDilihat(id);
    }
}