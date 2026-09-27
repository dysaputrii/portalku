package id.ac.polinema.lumajang.portalku.config.lampiran;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.ac.polinema.lumajang.portalku.config.lampiran.Lampiran;

@Service
@Transactional(readOnly = true)
public class LampiranService {

    private final LampiranRepository lampiranRepository;

    public LampiranService(LampiranRepository lampiranRepository) {
        this.lampiranRepository = lampiranRepository;
    }

    public List<Lampiran> cariSemua() {
        return lampiranRepository.findAll();
    }

    public Lampiran cariSatu(Integer id) {
        return lampiranRepository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public Lampiran tambah(Lampiran lampiran) {
        return lampiranRepository.save(lampiran);
    }

    @Transactional
    public void hapus(Integer id) {
        lampiranRepository.deleteById(id);
    }
}