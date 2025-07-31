/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.DAO_Peminjaman_2011500432_SyahbaniHoir;
import Model.Peminjaman_2011500432_SyahbaniHoir;
import View.TPeminjamanBuku_2011500432_SyahbaniHoir;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author hoirs
 */
public class Controller_Peminjaman_2011500432_SyahbaniHoir {
    TPeminjamanBuku_2011500432_SyahbaniHoir form;
    DAO_Peminjaman_2011500432_SyahbaniHoir model;
    
    public Controller_Peminjaman_2011500432_SyahbaniHoir(TPeminjamanBuku_2011500432_SyahbaniHoir form) {
        this.form = form;
        model = new DAO_Peminjaman_2011500432_SyahbaniHoir();
    }
    
    //untuk memanggil autonumber pada DAO_Peminjaman
    public void tampilurutankode(){
        form.getTxtnopinjam().setText(model.autonumber2());
    }
    
    //untuk membersihkan objek inputan 
    public void reset(){
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        form.getTxttglpinjam().setText(String.valueOf(tgl.format(new Date())));
        form.getCmbjudulbuku().setSelectedIndex(0);
        form.getTxtnobuku().setText("");

        SimpleDateFormat tglkembali = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7); // Menambahkan 7 hari ke tanggal sekarang
        Date newDate = calendar.getTime();
        form.getTxttglkembali().setText(tglkembali.format(newDate));
        
        form.getTxtnopinjam().setText("");
        form.getTxtnobuku().requestFocus();
        isicombojudulbuku();
        tampilurutankode();
        
    }
    
    public void reset2(){
        form.getTxtnobuku().setText("");
        form.getTxtnopinjam().setText("");
        form.getTxttglpinjam().setText("");
        form.getTxttglkembali().setText("");
        form.getTxtnobuku().requestFocus();
         
    }
//    
//    public void reset3(){
//        form.getCmbjudulbuku().setSelectedIndex(0);
//        form.getTxtnobuku().requestFocus();
//        form.getTxtnopinjam().setText("");
//        form.getTxttglkembali().setText("");
//        form.getTxttglpinjam().setText("");
//    }
    
    //Combo isicombojudulbuku
    public void isicombojudulbuku() {
        form.getCmbjudulbuku().removeAllItems();
        form.getCmbjudulbuku().addItem("-Pilih-");
        for(Peminjaman_2011500432_SyahbaniHoir b : model.isicombojudulbuku()){
            form.getCmbjudulbuku().addItem(b.getJudulbuku());
        }
    }
    //menampilkan no buku berdasarkan inputan combo judul buku
    public void tampilnobuku(){
        if(form.getCmbjudulbuku().getSelectedIndex() !=0){
            for(Peminjaman_2011500432_SyahbaniHoir b : model.getNobuku(form.getCmbjudulbuku().getSelectedItem().toString())){
                form.getTxtnobuku().setText(String.valueOf(b.getNobuku()));
            }
        }
    }
    
    //menampilkan Judul buku berdasarkan inputan combo no buku
    public void tampiljudulbuku(){
        for(Peminjaman_2011500432_SyahbaniHoir b : model.getJudulbuku(form.getTxtnobuku().getText())){
            form.getCmbjudulbuku().setSelectedItem(b.getJudulbuku());
        }
    }
    
    public void simpan_peminjaman(){
        Peminjaman_2011500432_SyahbaniHoir B = new Peminjaman_2011500432_SyahbaniHoir();
        B.setNopinjam(form.getTxtnopinjam().getText());
        B.setTglpinjam(form.getTxttglpinjam().getText());
        B.setNobuku(form.getTxtnobuku().getText());
        model.insert(B);
    }
      
       public void simpan_detil(){
        Peminjaman_2011500432_SyahbaniHoir B = new Peminjaman_2011500432_SyahbaniHoir();
        B.setNopinjam(form.getTxtnopinjam().getText());
        B.setNobuku(form.getTxtnobuku().getText());
        B.setTglkembali(form.getTxttglkembali().getText());
        model.insert_detiltransaksi(B);
    }
    
}
