package id.ac.polinema.lumajang.portalku.prodi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prodi")
public class ProdiController {

    private final ProdiService service;

    public ProdiController(ProdiService service) {
        this.service = service;
    }

    @GetMapping("/uji")
    public ProdiRingkasDto uji() {
        return service.getRingkas(1);
    }
}