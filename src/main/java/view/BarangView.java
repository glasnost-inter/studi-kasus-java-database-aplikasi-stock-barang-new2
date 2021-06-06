package view;

import entity.Barang;
import service.BarangService;
import util.InputUtil;

public class BarangView {

    BarangService barangService;

    public BarangView(BarangService barangService) {
        this.barangService = barangService;
    }

    public void menu(){
        while (true){
            System.out.println(".:: Menu Barang ::.");
            System.out.println(" ");
            System.out.println("1. Daftar Semua Barang");
            System.out.println("2. Detail Barang");
            System.out.println("3. Tambah Barang");
            System.out.println("4. Update Barang");
            System.out.println("5. Hapus Barang");
            System.out.println("x. Keluar");

            var input = InputUtil.input("pilih");

            if(input.equals("1")){
                getAllBarang();
            }else if(input.equals("2")){
                getBarangById();
            }else if(input.equals("3")){
                addBarang();
            }else if(input.equals("4")){
                updateBarangByid();
            }else if(input.equals("5")){
                deleteBarangByid();
            }else if(input.equals("x")){
                System.out.println("Bye Bye");
                break;
            }else{
                System.out.println("Pilihan tidak tersedia");
            }

        }
    }

    public void getAllBarang(){
        Barang[] barangs = barangService.getAll();

        System.out.println("DAFTAR BARANG");

        for(var i = 0; i < barangs.length; i++){
            var barang= barangs[i];
            var no = i + 1;

            if(barang != null){
                System.out.println(barang.getKdBrg() + ". " + barang.getNmBrg());
            }
        }

        if(barangs.length == 0){
            System.out.println("Barang belum ada, silahkan diisi");
        }
    }

    public void getBarangById(){
        var input = InputUtil.input("Masukan kode barang");
        Barang[] barangs = barangService.getById(input);

        for(var i = 0; i < barangs.length; i++){
            var barang= barangs[i];
            var no = i + 1;

            if(barang != null){
                System.out.println("Nama Barang = " + barang.getNmBrg());
                System.out.println("Jenis Satuan = " + barang.getKdSat());
                System.out.println("Jumlah = " + barang.getJml());
            }
        }

        if(barangs.length == 0){
            System.out.println("Kode Barang ,"+input+" tidak ditemukan, silahkan cari yang lain");
        }
    }

    public void addBarang(){

        var kdBrg = InputUtil.input("Isikan Kode Barang");
        var nmBrg = InputUtil.input("Isikan Nama Barang");
        var kdSat = InputUtil.input("Isikan Kode Satuan");
        var jml = InputUtil.input("Isikan Jumlah");

        Barang barang = new Barang();

        barang.setKdBrg(kdBrg);
        barang.setNmBrg(nmBrg);
        barang.setKdSat(kdSat);
        barang.setJml(Integer.valueOf(jml));

        var result = barangService.add(barang);

        if (result==1){
            System.out.println("Barang "+nmBrg+" berhasil disimpan");
        }else if(result==-1){
            System.out.println("Barang "+nmBrg+" gagal disimpan");
        }

    }

    public void updateBarangByid(){
        var oldNmbrg="";
        var oldKdSat="";
        var oldJml=0;
        var input = InputUtil.input("Masukan kode barang");
        Barang[] barangs = barangService.getById(input);

        for(var i = 0; i < barangs.length; i++){
            var barang= barangs[i];
            var no = i + 1;

            if(barang != null){
                System.out.println("Nama Barang = " + barang.getNmBrg());
                System.out.println("Jenis Satuan = " + barang.getKdSat());
                System.out.println("Jumlah = " + barang.getJml());

                oldNmbrg = barang.getNmBrg();
                oldKdSat = barang.getKdSat();
                oldJml = barang.getJml();
            }
        }

        if (barangs.length == 1) {
            var nmBrg = InputUtil.input("Isikan Nama Barang Baru(isi jika berubah)");
            var kdSat = InputUtil.input("Isikan Kode Satuan Baru(isi jika berubah)");
            var jml = InputUtil.input("Isikan Jumlah Baru(isi jika berubah)");

            Barang barang = new Barang();
            barang.setKdBrg(input);
            barang.setNmBrg(nmBrg.isBlank()||nmBrg.isEmpty() ? oldNmbrg : nmBrg);
            barang.setKdSat(kdSat.isBlank()||kdSat.isEmpty() ? oldKdSat : kdSat );
            barang.setJml(jml.isBlank()||jml.isEmpty() ? oldJml : Integer.valueOf(jml));

            var result = barangService.updateById(barang);

            if (result == 1) {
                System.out.println("Barang " + nmBrg + " berhasil diupdate");
            } else if (result == -1) {
                System.out.println("Barang " + oldNmbrg + " gagal diupdate");
            }
        }else if(barangs.length==0){
            System.out.println("Kode Barang ,"+input+" tidak ditemukan, silahkan cari yang lain");
        }
    }

    public void deleteBarangByid(){
        var input = InputUtil.input("Masukan kode barang");
        var result = barangService.removeById(input);

        if(result==0){
            System.out.println("Kode Barang ,"+input+" tidak ditemukan, silahkan cari yang lain");
        }
        else if(result==1){
            System.out.println("Kode Barang ,"+input+" berhasil dihapus");
        }
    }
}
