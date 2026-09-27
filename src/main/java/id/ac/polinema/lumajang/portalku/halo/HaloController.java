package id.ac.polinema.lumajang.portalku.halo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/halo")
public class HaloController {

    @GetMapping
    @Operation(
            summary = "Cek status layanan Portalku",
            description = "Mengembalikan pesan bahwa layanan Portalku sedang berjalan."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Layanan berjalan dengan baik"
    )
    public String halo() {
        return "Layanan Portalku berjalan.";
    }
}