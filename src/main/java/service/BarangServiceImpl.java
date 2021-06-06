package service;

import entity.Barang;
import repository.BarangRepository;

public class BarangServiceImpl implements BarangService{

    BarangRepository barangRepository;

    public BarangServiceImpl(BarangRepository barangRepository) {
        this.barangRepository = barangRepository;
    }

    @Override
    public Barang[] getAll() {

        Barang[] barangs = barangRepository.getAll();

        return barangs;
    }

    @Override
    public Barang[] getById(String KdBrg) {
        Barang[] barangs = barangRepository.getById(KdBrg);

        return barangs;
    }

    @Override
    public int add(Barang barang) {
        return barangRepository.add(barang);
    }

    @Override
    public int removeById(String KdBrg) {
        return barangRepository.removeById(KdBrg);
    }

    @Override
    public int updateById(Barang barang) {
        return barangRepository.updateById(barang);
    }
}
