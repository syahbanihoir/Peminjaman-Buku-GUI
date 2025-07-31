/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.sql.Connection;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author hoirs
 */
public class Controller_LapBuku_2011500432_SyahbaniHoir {
    public void cetak_preview() {
        try {
            Connection conn = Koneksi.Database_2011500432_SyahbaniHoir.KoneksiDB();
            String reportPath = "src/Report/RepLapBuku.jasper";
            JasperPrint print=JasperFillManager.fillReport(reportPath,null,conn);
            JasperViewer.viewReport(print);
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak!! "+ex.getMessage(),
            "Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cetak_excel() {
        try {
            Connection conn = Koneksi.Database_2011500432_SyahbaniHoir.KoneksiDB();
            String path = "src/Report/RepLapBuku.jasper";
            File xlsx = new File("C:/LapBuku_2011500432_SyahbaniHoir.xlsx");
            JasperPrint print = JasperFillManager.fillReport(path,null,conn);
            
            JRXlsxExporter Xlsxexporter = new JRXlsxExporter();
            Xlsxexporter.setParameter(JRExporterParameter.JASPER_PRINT,print);
            Xlsxexporter.setParameter(JRExporterParameter.OUTPUT_FILE,xlsx);
            Xlsxexporter.exportReport();
            
            JOptionPane.showMessageDialog(null, "Cetak file pada drive C:/LapBuku_2011500432_SyahbaniHoir.xlsx");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak!! "+ex.getMessage(),
            "Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
}
