package co.aca.dao.imp;

import co.aca.model.Admin;

import java.util.List;

public interface IAdminDAO {

    List<Admin> getAdminList();

    Admin getAdminById(int id);

    void addAdmin(Admin admin);
}
