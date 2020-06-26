package controller;

import model.dto.AdminModel;
import model.service.AdminService;
import java.util.List;

public class AdminServices {
    private AdminService adminService=new AdminService();
    private AdminModel adminModel=new AdminModel();

    public void add(){
        adminService.add(adminModel);
    }

    public boolean ifExist(String username, String password){
        List<AdminModel> adminModels=adminService.getAll(adminModel);

        for (AdminModel adminModel1:adminModels){
            if (adminModel1.getPassword().equals(password)&&adminModel1.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public List<AdminModel> getAdmModesl(){
        return adminService.getAll(adminModel);
    }
}
