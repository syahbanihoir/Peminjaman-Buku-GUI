/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import java.util.List;
/**
 *
 * @author hoirs
 */
public interface Model_DAO_2011500432_SyahbaniHoir <A>{
    public int autonumber (A object);
    public void insert(A object);
    public void update(A object);
    public void delete(Integer kode);
    public void delete(String no);
    public List<A> getAll();
    public List<A> getCari(String key);
    public String autonumber2();
}
