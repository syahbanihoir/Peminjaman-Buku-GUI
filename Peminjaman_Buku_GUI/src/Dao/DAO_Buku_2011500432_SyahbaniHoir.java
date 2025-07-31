/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Koneksi.Database_2011500432_SyahbaniHoir;
import Model.Buku_2011500432_SyahbaniHoir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hoirs
 */
public class DAO_Buku_2011500432_SyahbaniHoir implements Model_DAO_2011500432_SyahbaniHoir<Buku_2011500432_SyahbaniHoir>{

    public DAO_Buku_2011500432_SyahbaniHoir() {
        connection = Database_2011500432_SyahbaniHoir.KoneksiDB();
    }
    Connection connection;
    String INSERT = "INSERT INTO buku_2011500432(`NoBuku`, `Judul`, `Tahun`) VALUES (?,?,?)";
    String UPDATE = "UPDATE buku_2011500432 SET Judul=?, Tahun=? WHERE NoBuku=?";
    String DELETE = "DELETE FROM buku_2011500432 WHERE NoBuku=?";
    String SELECT = "SELECT * FROM buku_2011500432";
    String CARI = "SELECT * FROM buku_2011500432 WHERE NoBuku LIKE ?";
    String COUNTER = "SELECT max(NoBuku) AS Kode FROM buku_2011500432";

    @Override
    public int autonumber(Buku_2011500432_SyahbaniHoir object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Buku_2011500432_SyahbaniHoir object) {
        PreparedStatement statement = null ;
        try{
            statement =  connection.prepareStatement(CARI);
            statement.setString(1, object.getNo());
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                JOptionPane.showMessageDialog(null,"data sudah pernah disimpan");
        else{
                PreparedStatement statement2 = null ;
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getNo());
                statement2.setString(2, object.getJudul());
                statement2.setString(3, object.getTahun());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null,"data berhasil disimpan");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch (SQLException ex){
                Logger.getLogger(DAO_Buku_2011500432_SyahbaniHoir.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
    public String autonumber2(){
        PreparedStatement statement ;
        int nomor_berikutnya = 0;
        String urutan = "";
        try{
            String COUNTER = "SELECT ifnull(max(convert(right(NoBuku,2), signed integer)),0) as kode," 
                    + "ifnull(length(max(convert(right(NoBuku,5)+1,signed integer))),0)as panjang from buku_2011500432";
            statement =  connection.prepareStatement(COUNTER);
            ResultSet rs2 = statement.executeQuery();
            if(rs2.next()){
                nomor_berikutnya = rs2.getInt("kode")+1;
                if(rs2.getInt("kode")!=0){
                    if(rs2.getInt("panjang") == 1){
                        urutan = "B" + "00" + nomor_berikutnya;
                    } if(rs2.getInt("panjang") == 2){
                        urutan = "B" + "00" + nomor_berikutnya;
                    } if(rs2.getInt("panjang") == 3){
                        urutan = "B" + "00" + nomor_berikutnya;
                    } if(rs2.getInt("panjang") == 4){
                        urutan = "B" + "00" + nomor_berikutnya;
                    } if(rs2.getInt("panjang") == 5){
                        urutan = "B" + "00" + nomor_berikutnya;
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

    @Override
    public void update(Buku_2011500432_SyahbaniHoir object) {
        PreparedStatement statement = null ;
        try{
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, object.getJudul());
            statement.setString(2, object.getTahun());
            statement.setString(3, object.getNo());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"data berhasil diubah");
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch (SQLException ex){
                Logger.getLogger(DAO_Buku_2011500432_SyahbaniHoir.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }

    @Override
    public void delete(Integer kode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(String no) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setString(1, no);
            int deletedRows = statement.executeUpdate();
            if (deletedRows > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            } else {
                JOptionPane.showMessageDialog(null, "Data dengan No Buku " + no + " tidak ditemukan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Buku_2011500432_SyahbaniHoir.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Buku_2011500432_SyahbaniHoir> getAll() {
        List<Buku_2011500432_SyahbaniHoir> list = null;
        PreparedStatement statement = null ;
        try{
            list = new ArrayList<Buku_2011500432_SyahbaniHoir>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Buku_2011500432_SyahbaniHoir p = new Buku_2011500432_SyahbaniHoir();
                p.setNo(rs.getString("NoBuku"));
                p.setJudul(rs.getString("Judul"));
                p.setTahun(rs.getString("Tahun"));
                list.add(p);
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Buku_2011500432_SyahbaniHoir> getCari(String key) {
        List<Buku_2011500432_SyahbaniHoir> list = null;
        PreparedStatement statement = null ;
        try{
            list = new ArrayList<Buku_2011500432_SyahbaniHoir>();
            statement = connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Buku_2011500432_SyahbaniHoir p = new Buku_2011500432_SyahbaniHoir();
                p.setNo(rs.getString("NoBuku"));
                p.setJudul(rs.getString("Judul"));
                p.setTahun(rs.getString("Tahun"));
                list.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    
    
}
