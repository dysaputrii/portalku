package id.ac.polinema.lumajang.portalku.berita;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.ac.polinema.lumajang.portalku.berita.dto.BeritaListProjection;

public interface BeritaRepository extends JpaRepository<Berita, Integer> {

    @Query(
        value = """
            SELECT
                b.id AS id,
                b.judul AS judul,
                b.tanggalTerbit AS tanggalTerbit,
                b.jumlahDilihat AS jumlahDilihat,
                k.nama AS namaKategori
            FROM Berita b
            JOIN b.kategori k
            WHERE (:kategori = '' OR k.nama = :kategori)
              AND (:cari = '' OR LOWER(b.judul) LIKE LOWER(CONCAT('%', :cari, '%')))
            """,
        countQuery = """
            SELECT COUNT(b)
            FROM Berita b
            JOIN b.kategori k
            WHERE (:kategori = '' OR k.nama = :kategori)
              AND (:cari = '' OR LOWER(b.judul) LIKE LOWER(CONCAT('%', :cari, '%')))
            """
    )
    Page<BeritaListProjection> findAllForList(
        @Param("kategori") String kategori,
        @Param("cari") String cari,
        Pageable pageable);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        UPDATE Berita b
        SET b.jumlahDilihat = b.jumlahDilihat + 1
        WHERE b.id = :id
        """)
    int naikkanJumlahDilihat(@Param("id") Integer id);
}