/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.DAO_Buku_2011500432_SyahbaniHoir;
import Dao.Model_DAO_2011500432_SyahbaniHoir;
import Model.Buku_2011500432_SyahbaniHoir;
import View.MBuku_2011500432_SyahbaniHoir;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hoirs
 */
public class Controller_Buku_2011500432_SyahbaniHoir {
    MBuku_2011500432_SyahbaniHoir form;
    Model_DAO_2011500432_SyahbaniHoir<Buku_2011500432_SyahbaniHoir> model;
    List<Buku_2011500432_SyahbaniHoir> list;
    String[] header;
    
    
    public Controller_Buku_2011500432_SyahbaniHoir(MBuku_2011500432_SyahbaniHoir form){
        this.form = form;
        model = new DAO_Buku_2011500432_SyahbaniHoir();
        list = model.getAll();
        header = new String[]{"NO BUKU", "JUDUL","TAHUN"};
        
        form.getTblbuku().setShowGrid(true);
        form.getTblbuku().setShowHorizontalLines(true);
        form.getTblbuku().setGridColor(Color.blue);
    }
    
    public void tampilurutankode() {
        form.getTxtnobuku().setText(model.autonumber2());
    }
    
    public void reset(){
        form.getTxtnobuku().setText("");
        form.getTxtjudulbuku().setText("");
        form.getTxttahun().setText("");
        form.getTxtjudulbuku().requestFocus();
        tampilurutankode();
        isiTable();
    }
    
    public void isiTable() {
        list = model.getAll();
        
       DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{},header){
           public boolean isCellEditable (int rowIndex, int columnIndex){
               return false;
                       }
        };
       
        Object[] data = new Object[header.length];
        for(Buku_2011500432_SyahbaniHoir p : list){
            data[0] = p.getNo();
            data[1] = p.getJudul();
            data[2] = p.getTahun();
            tblModel.addRow(data);
        }
        form.getTblbuku().setModel(tblModel);
    }
    
    public void isiField(int row){
        form.getTxtnobuku().setText(String.valueOf(list.get(row).getNo()));
        form.getTxtjudulbuku().setText(list.get(row).getJudul());
        form.getTxttahun().setText(list.get(row).getTahun());
    }
    
    public void insert(){
        Buku_2011500432_SyahbaniHoir p = new Buku_2011500432_SyahbaniHoir();
        p.setNo(form.getTxtnobuku().getText());
        p.setJudul(form.getTxtjudulbuku().getText());
        p.setTahun(form.getTxttahun().getText());
        
        model.insert(p);
       }
    
    public void update(){
        Buku_2011500432_SyahbaniHoir p = new Buku_2011500432_SyahbaniHoir();
        p.setNo(form.getTxtnobuku().getText());
        p.setJudul(form.getTxtjudulbuku().getText());
        p.setTahun(form.getTxttahun().getText());
        
        model.update(p);
    }
    
//    public void delete(){
//        if(!form.getTxtnobuku().getText().trim().isEmpty()&& !form.getTxtjudulbuku().getText().trim().isEmpty()&&
//        !form.getTxttahun().getText().trim().isEmpty() ){
//            int no = Integer.parseInt(form.getTxtnobuku().getText());
//            model.delete(no);
//        }else{
//            JOptionPane.showMessageDialog(form,"Pilih data yang akan dihapus");
//        }
//    }
    
    public void delete() {
        if(!form.getTxtnobuku().getText().trim().isEmpty()&& !form.getTxtjudulbuku().getText().trim().isEmpty()&&
        !form.getTxttahun().getText().trim().isEmpty() ){
            String no = form.getTxtnobuku().getText();
            model.delete(no);
        }else{
            JOptionPane.showMessageDialog(form,"Pilih data yang akan dihapus");
        }
    }
    
    public void isiTableCari(){
        list = model.getCari(form.getTxtnobuku().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{},header);
        Object[] data = new Object[header.length];
        for(Buku_2011500432_SyahbaniHoir p : list){
            data[0] = p.getNo();
            data[1] = p.getJudul();
            data[2] = p.getTahun();
            tblModel.addRow(data);
        }
        form.getTblbuku().setModel(tblModel);
    }
}
