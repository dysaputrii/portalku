package id.ac.polinema.lumajang.portalku.kurikulum;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/kurikulum")
public class KurikulumController {

    private final KurikulumService service;

    public KurikulumController(KurikulumService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Menampilkan semua kurikulum",
            description = "Mengambil seluruh data kurikulum."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Data kurikulum berhasil diambil"
    )
    public List<KurikulumResponse> semua() {
        return service.semua();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Menampilkan satu kurikulum",
            description = "Mengambil data kurikulum berdasarkan ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Data kurikulum berhasil ditemukan"
    )
    public KurikulumResponse satu(@PathVariable Integer id) {
        return service.berdasarkanId(id);
    }

    @PostMapping
    @Operation(
            summary = "Menambahkan kurikulum",
            description = "Menambahkan data kurikulum baru."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Data kurikulum berhasil ditambahkan"
    )
    public ResponseEntity<KurikulumResponse> tambah(
            @Valid @RequestBody KurikulumRequest request) {

        KurikulumResponse hasil = service.simpan(request);

        URI lokasi = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hasil.id())
                .toUri();

        return ResponseEntity
                .created(lokasi)
                .body(hasil);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Mengubah kurikulum",
            description = "Mengubah data kurikulum berdasarkan ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Data kurikulum berhasil diubah"
    )
    public KurikulumResponse ubah(
            @PathVariable Integer id,
            @Valid @RequestBody KurikulumRequest request) {

        return service.ubah(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Menghapus kurikulum",
            description = "Menghapus data kurikulum berdasarkan ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Data kurikulum berhasil dihapus"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hapus(@PathVariable Integer id) {
        service.hapus(id);
    }
}