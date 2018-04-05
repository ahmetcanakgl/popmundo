package co.aca.service;

import co.aca.dao.imp.IAdminDAO;
import co.aca.model.Admin;
import co.aca.service.imp.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IAdminDAO adminDAO;

    @Override
    public Admin getAdminById(int id) {
        Admin obj = adminDAO.getAdminById(id);
        return obj;
    }

    @Override
    public List<Admin> getAdminList() {
        return adminDAO.getAdminList();
    }

    @Override
    public synchronized boolean addAdmin(Admin admin) {

            adminDAO.addAdmin(admin);
            return true;

    }
}
