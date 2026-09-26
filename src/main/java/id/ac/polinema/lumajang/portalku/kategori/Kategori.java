package id.ac.polinema.lumajang.portalku.kategori;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import id.ac.polinema.lumajang.portalku.pengumuman.Pengumuman;

@Entity
@Table(name = "kategori")
@Getter
@Setter
@NoArgsConstructor
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    private String nama;

    @OneToMany(
        mappedBy = "kategori",
        fetch = FetchType.LAZY
    )
    private List<Pengumuman> daftarPengumuman = new ArrayList<>();
}