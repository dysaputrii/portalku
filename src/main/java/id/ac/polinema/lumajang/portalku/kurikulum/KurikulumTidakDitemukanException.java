package id.ac.polinema.lumajang.portalku.kurikulum;

import id.ac.polinema.lumajang.portalku.shared.SumberDayaTidakDitemukanException;

public class KurikulumTidakDitemukanException
        extends SumberDayaTidakDitemukanException {

    public KurikulumTidakDitemukanException(Integer id) {
        super("Kurikulum", id);
    }
}