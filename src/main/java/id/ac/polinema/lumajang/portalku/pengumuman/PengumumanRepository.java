package id.ac.polinema.lumajang.portalku.pengumuman;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PengumumanRepository extends JpaRepository<Pengumuman, Integer> {

    List<Pengumuman> findByKategoriId(Integer idKategori);

    @Query("""
        SELECT DISTINCT p
        FROM Pengumuman p
        JOIN FETCH p.kategori
        LEFT JOIN FETCH p.daftarLampiran
        """)
    List<Pengumuman> findAllWithKategori();

    @Modifying
    @Query("""
        UPDATE Pengumuman p
        SET p.jumlahDilihat = p.jumlahDilihat + 1
        WHERE p.id = :id
        """)
    int tambahJumlahDilihat(@Param("id") Integer id);
}