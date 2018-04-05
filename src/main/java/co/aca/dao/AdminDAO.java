package co.aca.dao;

import co.aca.dao.imp.IAdminDAO;
import co.aca.model.Admin;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class AdminDAO implements IAdminDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Admin getAdminById(int id) {
        return entityManager.find(Admin.class, id);
    }

    @Override
    public List<Admin> getAdminList() {
        String hql = "FROM Admin as adm ORDER BY adm.id";
        return (List<Admin>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public void addAdmin(Admin admin) {
        entityManager.persist(admin);
    }
}
