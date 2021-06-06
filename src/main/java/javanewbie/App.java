package javanewbie;

import com.zaxxer.hikari.HikariDataSource;
import entity.Barang;
import repository.BarangRepository;
import repository.BarangRepositoryImpl;
import service.BarangService;
import service.BarangServiceImpl;
import util.DatabaseUtil;
import view.BarangView;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HikariDataSource dataSource = DatabaseUtil.getDataSource();

        BarangRepository barangRepository = new BarangRepositoryImpl(dataSource);
        BarangService barangService = new BarangServiceImpl(barangRepository);
        BarangView barangView = new BarangView(barangService);

        barangView.menu();

    }
}
