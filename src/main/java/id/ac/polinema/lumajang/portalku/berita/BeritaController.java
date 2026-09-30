package id.ac.polinema.lumajang.portalku.berita;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import id.ac.polinema.lumajang.portalku.berita.dto.BeritaListProjection;
import id.ac.polinema.lumajang.portalku.berita.dto.PageResponse;

@RestController
@RequestMapping("/api/berita")
public class BeritaController {

    private static final Set<String> SORT_FIELDS = Set.of(
        "id",
        "judul",
        "tanggalTerbit",
        "jumlahDilihat"
    );

    private final BeritaService service;

    public BeritaController(BeritaService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponse<BeritaListProjection> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String kategori,
            @RequestParam(required = false) String cari) {

        int ukuran = Math.min(size, 50);

        if (kategori == null) {
            kategori = "";
        }

        if (cari == null) {
            cari = "";
        }

        Sort sorting;

        if (sort == null || sort.isBlank()) {

            sorting = Sort.by(
                Sort.Order.desc("tanggalTerbit"),
                Sort.Order.asc("id")
            );

        } else {

            String[] bagian = sort.split(",");

            String field = bagian[0];
            String arah = bagian.length > 1 ? bagian[1] : "asc";

            if (!SORT_FIELDS.contains(field)) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Field sort tidak valid: " + field
                );
            }

            if (!arah.equalsIgnoreCase("asc")
                    && !arah.equalsIgnoreCase("desc")) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Arah sort harus asc atau desc"
                );
            }

            sorting = Sort.by(
                "desc".equalsIgnoreCase(arah)
                    ? Sort.Order.desc(field)
                    : Sort.Order.asc(field)
            );
        }

        Pageable pageable = PageRequest.of(
                page,
                ukuran,
                sorting
        );

        Page<BeritaListProjection> hasil =
                service.getAll(kategori, cari, pageable);

        return new PageResponse<>(
                hasil.getContent(),
                hasil.getNumber(),
                hasil.getSize(),
                hasil.getTotalElements(),
                hasil.getTotalPages()
        );
    }

    @PostMapping("/{id}/lihat")
    public void lihat(@PathVariable Integer id) {
        service.naikkanJumlahDilihat(id);
    }
}