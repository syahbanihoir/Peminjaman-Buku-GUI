/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Koneksi.Database_2011500432_SyahbaniHoir;
import Model.Peminjaman_2011500432_SyahbaniHoir;
import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author hoirs
 */
public class DAO_Peminjaman_2011500432_SyahbaniHoir implements Model_DAO_2011500432_SyahbaniHoir<Peminjaman_2011500432_SyahbaniHoir> {
    
    Connection connection;
    public DAO_Peminjaman_2011500432_SyahbaniHoir() {
        connection = Database_2011500432_SyahbaniHoir.KoneksiDB();
    }

    @Override
    public int autonumber(Peminjaman_2011500432_SyahbaniHoir object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    //Method ini untuk simpan data ke dalam tabel peminjaman_2011500432
    public void insert(Peminjaman_2011500432_SyahbaniHoir object) {
        PreparedStatement statement;
        try{
            String SELECT = "SELECT * FROM peminjaman_2011500432 WHERE NoPinjam=?";
            statement =  connection.prepareStatement(SELECT);
            statement.setString(1, object.getNopinjam());
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null,"data sudah pernah disimpan");
            }else{
                PreparedStatement statement2;
                String INSERT = "INSERT INTO peminjaman_2011500432 (NoPinjam, TglPinjam) VALUES (?,?)";
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getNopinjam());
                statement2.setString(2, object.getTglpinjam());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null,"data berhasil disimpan");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Method ini digunakan untuk menyimpan data kedalam tabel detil_2011500432
    public void insert_detiltransaksi(Peminjaman_2011500432_SyahbaniHoir object) {
        PreparedStatement statement;
        try{
            String INSERTDETIL = "INSERT INTO detil_2011500432 (NoPinjam, NoBuku, TglKembali) VALUES (?,?,?)";
            statement =  connection.prepareStatement(INSERTDETIL);
            statement.setString(1, object.getNopinjam());
            statement.setString(2, object.getNobuku());
            statement.setString(3, object.getTglkembali());
            
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //Method ini digunakan untuk membuat penomoran peminjaman secara otomatis.
    public String autonumber2(){
        PreparedStatement statement ;
        int nomor_berikutnya = 0;
        String urutan = "";
        try{
            String COUNTER = "SELECT ifnull(max(convert(right(NoPinjam,2), signed integer)),0) as kode," 
                    + "ifnull(length(max(convert(right(NoPinjam,5)+1,signed integer))),0)as panjang from peminjaman_2011500432";
            statement =  connection.prepareStatement(COUNTER);
            ResultSet rs2 = statement.executeQuery();
            if(rs2.next()){
                nomor_berikutnya = rs2.getInt("kode")+1;
                if(rs2.getInt("kode")!=0){
                    if(rs2.getInt("panjang") == 1){
                        urutan = "P" + "00" + nomor_berikutnya;
                    } if(rs2.getInt("panjang") == 2){
                        urutan = "P" + "00" + nomor_berikutnya;
                    } if(rs2.getInt("panjang") == 3){
                        urutan = "P" + "00" + nomor_berikutnya;
                    } if(rs2.getInt("panjang") == 4){
                        urutan = "P" + "00" + nomor_berikutnya;
                    } if(rs2.getInt("panjang") == 5){
                        urutan = "P" + "00" + nomor_berikutnya;
                    }
                }else{
                    urutan = "P" + "001";
                }
            }else{
                JOptionPane.showMessageDialog(null,"Data Tidak Ditemukan");
            }
                  
        }catch (Exception e){
            e.printStackTrace();
        }
        return urutan;
    }
    
    //Combo Judul Buku
    //Method ini digunakan untuk manampung judul buku kedalam combo judul buku
    public List<Peminjaman_2011500432_SyahbaniHoir> isicombojudulbuku() {
        PreparedStatement statement;
        List<Peminjaman_2011500432_SyahbaniHoir> list = null;
        try{
            list = new ArrayList();
            String TAMPILPELANGAN = "SELECT * FROM buku_2011500432 order by Judul";
            statement =  connection.prepareStatement(TAMPILPELANGAN);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Peminjaman_2011500432_SyahbaniHoir b = new Peminjaman_2011500432_SyahbaniHoir();
                b.setJudulbuku(rs.getString("Judul"));
                list.add(b);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    //Method ini digunakan untuk menampilkan kode judul buku berdasarkan inputan combo nama pelanggan
    public List<Peminjaman_2011500432_SyahbaniHoir> getNobuku(String judulbuku) {
        PreparedStatement statement ;
        List<Peminjaman_2011500432_SyahbaniHoir> list = null;
        try{
            list = new ArrayList();
            String CARIPELANGAN2 = "SELECT * FROM buku_2011500432 WHERE Judul=?";
            statement =  connection.prepareStatement(CARIPELANGAN2);
            statement.setString(1, judulbuku);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Peminjaman_2011500432_SyahbaniHoir b = new Peminjaman_2011500432_SyahbaniHoir();
                b.setNobuku(rs.getString("NoBuku"));
                list.add(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    //untuk menampilkan nama pelanggan berdasarkan inputan text kode pelanggan
    public List<Peminjaman_2011500432_SyahbaniHoir> getJudulbuku(String nobuku) {
        PreparedStatement statement ;
        List<Peminjaman_2011500432_SyahbaniHoir> list = null;
        try{
            list = new ArrayList();
            String CARIPELANGGAN = "SELECT * FROM buku_2011500432 WHERE NoBuku=?";
            statement =  connection.prepareStatement(CARIPELANGGAN);
            statement.setString(1, nobuku);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null,"data tidak ditemukan"); 
            }else{
                Peminjaman_2011500432_SyahbaniHoir b = new Peminjaman_2011500432_SyahbaniHoir();
                b.setJudulbuku(rs.getString("Judul"));
                list.add(b);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    
    @Override
    public void update(Peminjaman_2011500432_SyahbaniHoir object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer kode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Peminjaman_2011500432_SyahbaniHoir> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Peminjaman_2011500432_SyahbaniHoir> getCari(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String no) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
