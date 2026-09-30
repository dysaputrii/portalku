package id.ac.polinema.lumajang.portalku.pengumuman;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import id.ac.polinema.lumajang.portalku.kategori.Kategori;
import id.ac.polinema.lumajang.portalku.lampiran.Lampiran;
import id.ac.polinema.lumajang.portalku.prodi.Prodi;

@Entity
@Table(
name = "pengumuman",
uniqueConstraints = @UniqueConstraint(columnNames = "judul")
)
@Getter
@Setter
@NoArgsConstructor
public class Pengumuman {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column(nullable = false, length = 200, unique = true)
private String judul;

@Column(nullable = false)
private String isi;

@Column(name = "tanggal_terbit", nullable = false)
private LocalDate tanggalTerbit;

@Column(name = "jumlah_dilihat", nullable = false)
private Integer jumlahDilihat = 0;

@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "id_kategori", nullable = false)
private Kategori kategori;

@OneToMany(
    mappedBy = "pengumuman",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
)
private List<Lampiran> daftarLampiran = new ArrayList<>();

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(
    name = "pengumuman_prodi",
    joinColumns = @JoinColumn(name = "id_pengumuman"),
    inverseJoinColumns = @JoinColumn(name = "id_prodi")
)
private Set<Prodi> targetProdi = new HashSet<>();

}