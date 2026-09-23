package id.ac.polinema.lumajang.portalku.config.kurikulum;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.ac.polinema.lumajang.portalku.kurikulum.Kurikulum;
import id.ac.polinema.lumajang.portalku.kurikulum.KurikulumRepository;

@RestController
@RequestMapping("/api/kurikulum")
public class KurikulumController {

    private final KurikulumRepository repository;

    public KurikulumController(KurikulumRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/uji")
    public List<Kurikulum> uji() {
     return repository.findAllWithProdi();
    }
}