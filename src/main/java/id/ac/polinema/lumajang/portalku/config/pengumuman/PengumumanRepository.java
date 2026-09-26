package id.ac.polinema.lumajang.portalku.pengumuman;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PengumumanRepository extends JpaRepository<Pengumuman, Integer> {

    List<Pengumuman> findByKategoriId(Integer idKategori);

    @Query("""
        SELECT DISTINCT p
        FROM Pengumuman p
        JOIN FETCH p.kategori
        LEFT JOIN FETCH p.daftarLampiran
        """)
    List<Pengumuman> findAllWithKategori();
}
