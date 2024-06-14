/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.FormMahasiswaDb;
import model.Mahasiswa;
import dao.MahasiswaDb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ORYZA CONSEVA
 */
public class MahasiswaControllerDb {
    FormMahasiswaDb formMahasiswaDb;
    Mahasiswa mahasiswa;
    MahasiswaDb mahasiswaDb;
    
    public MahasiswaControllerDb(FormMahasiswaDb formMahasiswa){
        this.formMahasiswaDb = formMahasiswa;
        mahasiswaDb = new MahasiswaDb();
    }

    public void cancel(){
        formMahasiswaDb.getTxtNobp().setText("");
        formMahasiswaDb.getTxtNama().setText("");
        formMahasiswaDb.getTxtAlamat().setText("");
        formMahasiswaDb.getTxtTanggalLahir().setText("");    
        isiJenisKelamin();
    } 
    
    public void isiJenisKelamin(){
        formMahasiswaDb.getCboJenisKelamin().removeAllItems();
        formMahasiswaDb.getCboJenisKelamin().addItem("L");
        formMahasiswaDb.getCboJenisKelamin().addItem("P");
    }
    
    public void insert(){
        try {
            mahasiswa = new Mahasiswa();
            mahasiswa.setNobp(formMahasiswaDb.getTxtNobp().getText());
            mahasiswa.setNama(formMahasiswaDb.getTxtNama().getText());
            mahasiswa.setAlamat(formMahasiswaDb.getTxtAlamat().getText());
            mahasiswa.setJenisKelamin(formMahasiswaDb.getCboJenisKelamin().getSelectedItem().toString());
            mahasiswa.setTgllahir(formMahasiswaDb.getTxtTanggalLahir().getText());
            mahasiswaDb.insert(mahasiswa);
            JOptionPane.showMessageDialog(formMahasiswaDb, "Entri Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formMahasiswaDb, "Error  "+ex.getMessage());
        }
    }
    
    public void viewData(){
        try {
            DefaultTableModel model = (DefaultTableModel)formMahasiswaDb.getTabelMahasiswaDb().getModel();
            model.setNumRows(0);
            List<Mahasiswa> list = mahasiswaDb.getAllMahasiswa();
            for(Mahasiswa mahasiswa : list){
                Object[] data = {
                    mahasiswa.getNobp(),
                    mahasiswa.getNama(),
                    mahasiswa.getAlamat(),
                    mahasiswa.getJenisKelamin(),
                    mahasiswa.getTgllahir()
                };
                model.addRow(data);
            }
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void actionClickTabel(){
        try {
            String nobp = formMahasiswaDb.getTabelMahasiswaDb()
                    .getValueAt(formMahasiswaDb.getTabelMahasiswaDb().getSelectedRow(), 0).toString();
            mahasiswa = mahasiswaDb.getMahasiswa(nobp);
            formMahasiswaDb.getTxtNobp().setText(mahasiswa.getNobp());
            formMahasiswaDb.getTxtNama().setText(mahasiswa.getNama());
            formMahasiswaDb.getTxtAlamat().setText(mahasiswa.getAlamat());
            formMahasiswaDb.getCboJenisKelamin().setSelectedItem(mahasiswa.getJenisKelamin());
            formMahasiswaDb.getTxtTanggalLahir().setText(mahasiswa.getTgllahir());
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            mahasiswa.setNobp(formMahasiswaDb.getTxtNobp().getText());
            mahasiswa.setNama(formMahasiswaDb.getTxtNama().getText());
            mahasiswa.setAlamat(formMahasiswaDb.getTxtAlamat().getText());
            mahasiswa.setJenisKelamin(formMahasiswaDb.getCboJenisKelamin()
                    .getSelectedItem().toString());
            mahasiswa.setTgllahir(formMahasiswaDb.getTxtTanggalLahir().getText());
            mahasiswaDb.update(mahasiswa);
            JOptionPane.showMessageDialog(formMahasiswaDb, "Update Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            String nobp = formMahasiswaDb.getTabelMahasiswaDb()
                    .getValueAt(formMahasiswaDb.getTabelMahasiswaDb().getSelectedRow(), 0).toString();
            mahasiswaDb.delete(nobp);
            JOptionPane.showMessageDialog(formMahasiswaDb, "Delete Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
