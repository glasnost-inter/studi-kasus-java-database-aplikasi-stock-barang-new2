package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Barang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseUtil;
import static org.junit.jupiter.api.Assertions.*;

public class BarangRepositoryImplTest {

    private HikariDataSource dataSource;
    private BarangRepository barangRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        barangRepository = new BarangRepositoryImpl(dataSource);
    }

    @Test
    void testAdd1_success() {
        Barang barang = new Barang();

        barang.setKdBrg("001");
        barang.setNmBrg("Laptop");
        barang.setKdSat("pcs");
        barang.setJml(5);

        var result = barangRepository.add(barang);
        assertEquals(1,result);
    }

    @Test
    void testAdd2_success() {
        Barang barang = new Barang();

        barang.setKdBrg("002");
        barang.setNmBrg("Monitor");
        barang.setKdSat("pcs");
        barang.setJml(2);

        var result = barangRepository.add(barang);
        assertEquals(1,result);
    }

    @Test
    void testAdd3_failed() {
        Barang barang = new Barang();

        barang.setKdBrg("007");
        barang.setNmBrg("Laptop");
        barang.setKdSat("pcs");
        barang.setJml(5);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setKdBrg("007");
        barang.setNmBrg("Kantor");
        barang.setKdSat("pcs");
        barang.setJml(6);

        result = barangRepository.add(barang);
        assertEquals(-1,result);
        /*
        * kode barang 001 sudah diinsert sebelumnya, primary key violated
        * */
    }


    @Test
    void testDelete1_success() {

        Barang barang = new Barang();

        barang.setKdBrg("003");
        barang.setNmBrg("Mouse");
        barang.setKdSat("pcs");
        barang.setJml(6);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        result = barangRepository.removeById("003");
        assertEquals(1,result);
    }

    @Test
    void testDelete2_success() {

        Barang barang = new Barang();

        barang.setKdBrg("004");
        barang.setNmBrg("Pulpen");
        barang.setKdSat("pcs");
        barang.setJml(10);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        result = barangRepository.removeById("004");
        assertEquals(1,result);
    }

    @Test
    void testDelete3_failed() {

        Barang barang = new Barang();

        barang.setKdBrg("005");
        barang.setNmBrg("Kabel Roll");
        barang.setKdSat("pcs");
        barang.setJml(1);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        result = barangRepository.removeById("006");
        assertEquals(0,result);
    }


    @Test
    void testUpdate1_success() {

        Barang barang = new Barang();

        barang.setKdBrg("008");
        barang.setNmBrg("Table");
        barang.setKdSat("pcs");
        barang.setJml(8);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setKdBrg("008");
        barang.setNmBrg("Tablet");
        barang.setKdSat("pcs");
        barang.setJml(18);


        result = barangRepository.updateById(barang);
        assertEquals(1,result);
    }

    @Test
    void testUpdate2_success() {

        Barang barang = new Barang();

        barang.setKdBrg("009");
        barang.setNmBrg("Buku");
        barang.setKdSat("pcs");
        barang.setJml(11);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setKdBrg("009");
        barang.setNmBrg("Manual Book");
        barang.setKdSat("pcs");
        barang.setJml(3);


        result = barangRepository.updateById(barang);
        assertEquals(1,result);
    }

    @Test
    void testUpdate3_failed() {

        Barang barang = new Barang();

        barang.setKdBrg("010");
        barang.setNmBrg("Speaker");
        barang.setKdSat("pcs");
        barang.setJml(7);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setKdBrg("011");
        barang.setNmBrg("Speaker Aktif");
        barang.setKdSat("pcs");
        barang.setJml(8);


        result = barangRepository.updateById(barang);
        assertEquals(0,result);
    }


    @Test
    void testGetById1_success() {

        Barang barang = new Barang();

        barang.setKdBrg("012");
        barang.setNmBrg("Karpet");
        barang.setKdSat("pcs");
        barang.setJml(4);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setKdBrg("013");
        barang.setNmBrg("Susu UHT");
        barang.setKdSat("pcs");
        barang.setJml(9);

        result = barangRepository.add(barang);
        assertEquals(1,result);


        Barang[] barangs = barangRepository.getById("012");
        assertEquals(1,barangs.length);
    }

    @Test
    void testGetById2_failed() {

        Barang barang = new Barang();

        barang.setKdBrg("014");
        barang.setNmBrg("Pintu");
        barang.setKdSat("pcs");
        barang.setJml(2);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setKdBrg("015");
        barang.setNmBrg("Tas");
        barang.setKdSat("pcs");
        barang.setJml(1);

        result = barangRepository.add(barang);
        assertEquals(1,result);


        Barang[] barangs = barangRepository.getById("016");
        assertEquals(0,barangs.length);
    }

    @Test
    void testGetAll1_success() {

        Barang barang = new Barang();

        barang.setKdBrg("017");
        barang.setNmBrg("Kursi");
        barang.setKdSat("pcs");
        barang.setJml(4);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setKdBrg("018");
        barang.setNmBrg("Jendela");
        barang.setKdSat("pcs");
        barang.setJml(13);

        result = barangRepository.add(barang);
        assertEquals(1,result);


        Barang[] barangs = barangRepository.getById("012");
        assertTrue(barangs.length > 0);
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }
}
