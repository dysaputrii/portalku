package id.ac.polinema.lumajang.portalku.jurnal; 
 
import java.util.List; 
import lombok.RequiredArgsConstructor; 
 
import io.swagger.v3.oas.annotations.Operation; 
import io.swagger.v3.oas.annotations.responses.ApiResponse; 
 
import org.springframework.web.bind.annotation.*; 
 
@RestController 
@RequestMapping("/api/jurnal") 
@RequiredArgsConstructor 
public class JurnalController { 
 
    private final JurnalService jurnalService; 
 
    @GetMapping 
    @Operation( 
            summary = "Menampilkan semua jurnal", 
            description = "Mengambil seluruh data jurnal yang tersedia." 
    ) 
    @ApiResponse( 
            responseCode = "200", 
            description = "Data jurnal berhasil diambil" 
    ) 
    public List<Jurnal> semua() { 
        return jurnalService.cariSemua(); 
    } 
 
    @GetMapping("/{id}") 
    @Operation( 
            summary = "Menampilkan satu jurnal", 
            description = "Mengambil data jurnal berdasarkan ID." 
    ) 
    @ApiResponse( 
            responseCode = "200", 
            description = "Data jurnal berhasil ditemukan" 
    ) 
    public Jurnal satu(@PathVariable Integer id) { 
        return jurnalService.cariSatu(id); 
    } 
 
    @PostMapping 
    @Operation( 
            summary = "Menambahkan jurnal", 
            description = "Menambahkan data jurnal baru ke dalam sistem." 
    ) 
    @ApiResponse( 
            responseCode = "200", 
            description = "Data jurnal berhasil ditambahkan" 
    ) 
    public Jurnal tambah(@RequestBody Jurnal jurnal) { 
        return jurnalService.tambah(jurnal); 
    } 
 
    @DeleteMapping("/{id}") 
    @Operation( 
            summary = "Menghapus jurnal", 
            description = "Menghapus data jurnal berdasarkan ID." 
    ) 
    @ApiResponse( 
            responseCode = "204", 
            description = "Data jurnal berhasil dihapus" 
    ) 
    public void hapus(@PathVariable Integer id) { 
        jurnalService.hapus(id); 
    } 
}