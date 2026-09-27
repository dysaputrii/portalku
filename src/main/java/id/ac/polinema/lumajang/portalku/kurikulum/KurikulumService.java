package id.ac.polinema.lumajang.portalku.kurikulum;

import java.util.List;

import org.springframework.stereotype.Service;

import id.ac.polinema.lumajang.portalku.prodi.Prodi;
import id.ac.polinema.lumajang.portalku.prodi.ProdiRepository;

@Service
public class KurikulumService {

    private final KurikulumRepository repository;
    private final ProdiRepository prodiRepository;
    private final KurikulumMapper mapper;

    public KurikulumService(
            KurikulumRepository repository,
            ProdiRepository prodiRepository,
            KurikulumMapper mapper) {

        this.repository = repository;
        this.prodiRepository = prodiRepository;
        this.mapper = mapper;
    }

    public List<KurikulumResponse> semua() {
        return repository.findAll()
                .stream()
                .map(mapper::keResponse)
                .toList();
    }

    public KurikulumResponse berdasarkanId(Integer id) {

        Kurikulum kurikulum = repository.findById(id)
                .orElseThrow(() ->
                        new KurikulumTidakDitemukanException(id));

        return mapper.keResponse(kurikulum);
    }

    public KurikulumResponse simpan(KurikulumRequest request) {

        Prodi prodi = prodiRepository.findById(request.idProdi())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Prodi tidak ditemukan"));

        Kurikulum kurikulum =
                mapper.keEntity(request, prodi);

        return mapper.keResponse(
                repository.save(kurikulum));
    }

    public KurikulumResponse ubah(
            Integer id,
            KurikulumRequest request) {

        Kurikulum kurikulum = repository.findById(id)
                .orElseThrow(() ->
                        new KurikulumTidakDitemukanException(id));

        Prodi prodi = prodiRepository.findById(request.idProdi())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Prodi tidak ditemukan"));

        mapper.terapkan(
                request,
                kurikulum,
                prodi);

        return mapper.keResponse(
                repository.save(kurikulum));
    }

    public void hapus(Integer id) {

        Kurikulum kurikulum = repository.findById(id)
                .orElseThrow(() ->
                        new KurikulumTidakDitemukanException(id));

        repository.delete(kurikulum);
    }
}