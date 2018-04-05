package co.aca.service.imp;

import co.aca.model.Admin;

import java.util.List;

public interface IAdminService {

    List<Admin> getAdminList();

    Admin getAdminById(int id);

    boolean addAdmin(Admin admin);
}