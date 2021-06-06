package service;

import entity.Barang;

public interface BarangService {

    Barang[] getAll();
    Barang[] getById(String KdBrg);
    int add(Barang barang);
    int removeById(String KdBrg);
    int updateById(Barang barang);

    /*
    Barang[] getAll();

    Barang[] getById(String KdBrg);

    int add(Barang barang);

    int removeById(String KdBrg);

    int updateById(Barang barang);
    * */

}
