package id.ac.polinema.lumajang.portalku.pengumuman.controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import id.ac.polinema.lumajang.portalku.pengumuman.dto.PengumumanRequest;
import id.ac.polinema.lumajang.portalku.pengumuman.dto.PengumumanResponse;
import id.ac.polinema.lumajang.portalku.pengumuman.dto.PengumumanRingkasResponse;
import id.ac.polinema.lumajang.portalku.pengumuman.service.PengumumanService;

@RestController
@RequestMapping("/api/pengumuman")
public class PengumumanController {

    private final PengumumanService pengumumanService;

    public PengumumanController(PengumumanService pengumumanService) {
        this.pengumumanService = pengumumanService;
    }

    @GetMapping
    @Operation(
            summary = "Menampilkan semua pengumuman",
            description = "Mengambil seluruh data pengumuman yang tersedia."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Data pengumuman berhasil diambil"
    )
    public List<PengumumanRingkasResponse> semua() {
        return pengumumanService.cariSemua();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Menampilkan satu pengumuman",
            description = "Mengambil data pengumuman berdasarkan ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Data pengumuman berhasil ditemukan"
    )
    public PengumumanResponse satu(@PathVariable Integer id) {
        return pengumumanService.cariSatu(id);
    }

    @PostMapping
    @Operation(
            summary = "Menambahkan pengumuman",
            description = "Menambahkan data pengumuman baru ke dalam sistem."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Data pengumuman berhasil ditambahkan"
    )
    public ResponseEntity<PengumumanResponse> tambah(
            @Valid @RequestBody PengumumanRequest req) {

        PengumumanResponse hasil = pengumumanService.tambah(req);

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
            summary = "Mengubah pengumuman",
            description = "Mengubah data pengumuman berdasarkan ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Data pengumuman berhasil diubah"
    )
    public PengumumanResponse ubah(
            @PathVariable Integer id,
            @Valid @RequestBody PengumumanRequest req) {

        return pengumumanService.ubah(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Menghapus pengumuman",
            description = "Menghapus data pengumuman berdasarkan ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Data pengumuman berhasil dihapus"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hapus(@PathVariable Integer id) {
        pengumumanService.hapus(id);
    }
}