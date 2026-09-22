package id.ac.polinema.lumajang.portalku.jurnal;

import org.springframework.stereotype.Service;
import java.util.List;

import id.ac.polinema.lumajang.portalku.jurnal.JurnalTidakDitemukanException;
import id.ac.polinema.lumajang.portalku.jurnal.Jurnal;

@Service
public class JurnalService {

    private final JurnalRepository jurnalRepository;

    public JurnalService(JurnalRepository jurnalRepository) {
        this.jurnalRepository = jurnalRepository;
    }

    public List<Jurnal> cariSemua() {
        return jurnalRepository.findAll();
    }

    public Jurnal cariSatu(Integer id) {
        return jurnalRepository.findById(id)
                .orElseThrow(() -> new JurnalTidakDitemukanException(id));
    }

    public Jurnal tambah(Jurnal jurnal) {
        return jurnalRepository.save(jurnal);
    }

    public void hapus(Integer id) {
        if (!jurnalRepository.existsById(id)) {
            throw new JurnalTidakDitemukanException(id);
        }
        jurnalRepository.deleteById(id);
    }
}