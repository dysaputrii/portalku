package id.ac.polinema.lumajang.portalku.jurnal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataAwalJurnal implements CommandLineRunner {

    private final JurnalRepository jurnalRepository;

    public DataAwalJurnal(JurnalRepository jurnalRepository) {
        this.jurnalRepository = jurnalRepository;
    }

    @Override
    public void run(String... args) {
        if (jurnalRepository.count() > 0) {
            return; // Jika tabel sudah ada isinya, jangan isi lagi
        }

        jurnalRepository.save(new Jurnal(null, "JISEBI", "Universitas Airlangga", 2015, "2442-3955"));
        jurnalRepository.save(new Jurnal(null, "Jurnal Informatika Polinema", "Politeknik Negeri Malang", 2015, "2407-070X"));
    }
}