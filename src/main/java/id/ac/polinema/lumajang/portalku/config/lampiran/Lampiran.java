package id.ac.polinema.lumajang.portalku.config.lampiran;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import id.ac.polinema.lumajang.portalku.pengumuman.Pengumuman;

@Entity
@Table(name = "lampiran")
@Getter
@Setter
@NoArgsConstructor
public class Lampiran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_berkas", nullable = false, length = 255)
    private String namaBerkas;

    @Column(nullable = false)
    private Long ukuran;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pengumuman", nullable = false)
    private Pengumuman pengumuman;
}