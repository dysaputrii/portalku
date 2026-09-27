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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import id.ac.polinema.lumajang.portalku.config.lampiran.Lampiran;
import id.ac.polinema.lumajang.portalku.config.lampiran.LampiranMapper;
import id.ac.polinema.lumajang.portalku.config.lampiran.LampiranRequest;
import id.ac.polinema.lumajang.portalku.config.lampiran.LampiranResponse;
import id.ac.polinema.lumajang.portalku.config.lampiran.LampiranService;

import id.ac.polinema.lumajang.portalku.pengumuman.dto.PengumumanRequest;
import id.ac.polinema.lumajang.portalku.pengumuman.dto.PengumumanResponse;
import id.ac.polinema.lumajang.portalku.pengumuman.dto.PengumumanRingkasResponse;
import id.ac.polinema.lumajang.portalku.pengumuman.service.PengumumanService;

@RestController
@RequestMapping("/api/pengumuman")
public class PengumumanController {

    private final PengumumanService pengumumanService;
    private final LampiranService lampiranService;
    private final LampiranMapper lampiranMapper;

    public PengumumanController(
            PengumumanService pengumumanService,
            LampiranService lampiranService,
            LampiranMapper lampiranMapper) {

        this.pengumumanService = pengumumanService;
        this.lampiranService = lampiranService;
        this.lampiranMapper = lampiranMapper;
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

    @GetMapping("/{id}/lampiran")
    @Operation(
            summary = "Menampilkan lampiran pengumuman",
            description = "Mengambil seluruh lampiran yang dimiliki oleh pengumuman berdasarkan ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Data lampiran berhasil diambil"
    )
    public List<LampiranResponse> semuaLampiran(
            @PathVariable Integer id) {

        return lampiranService
                .cariBerdasarkanPengumuman(id)
                .stream()
                .map(lampiranMapper::toResponse)
                .toList();
    }

    @PostMapping("/{id}/lampiran")
    @Operation(
            summary = "Menambahkan lampiran pengumuman",
            description = "Menambahkan lampiran baru pada pengumuman berdasarkan ID."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Lampiran berhasil ditambahkan"
    )
    public ResponseEntity<LampiranResponse> tambahLampiran(
            @PathVariable Integer id,
            @Valid @RequestBody LampiranRequest req) {

        Lampiran lampiran = lampiranMapper.toEntity(req);

        lampiran.setPengumuman(
                pengumumanService.cariEntity(id)
        );

        Lampiran hasil = lampiranService.tambah(lampiran);

        LampiranResponse response =
                lampiranMapper.toResponse(hasil);

        URI lokasi = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hasil.getId())
                .toUri();

        return ResponseEntity
                .created(lokasi)
                .body(response);
    }

    @PatchMapping("/{id}/dilihat")
    @Operation(
            summary = "Menambah jumlah dilihat pengumuman",
            description = "Menaikkan jumlah dilihat pengumuman sebanyak satu. Endpoint menggunakan PATCH karena operasi ini mengubah data pada server, sehingga tidak boleh menggunakan GET."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Jumlah dilihat berhasil ditambahkan"
    )
    public ResponseEntity<Void> tambahDilihat(
            @PathVariable Integer id) {

        pengumumanService.tambahDilihat(id);

        return ResponseEntity.noContent().build();
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

        PengumumanResponse hasil =
                pengumumanService.tambah(req);

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