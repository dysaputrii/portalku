package id.ac.polinema.lumajang.portalku.config.lampiran;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LampiranRepository extends JpaRepository<Lampiran, Integer> {

    List<Lampiran> findByPengumumanId(Integer idPengumuman);
}