package mate.service.impl;

import java.util.List;
import mate.dao.ManufacturerDao;
import mate.lib.Inject;
import mate.lib.Service;
import mate.model.Manufacturer;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer add(Manufacturer manufacturer) {
        return manufacturerDao.add(manufacturer);
    }

    @Override
    public Manufacturer getById(Long id) {
        return manufacturerDao.getById(id).get();
    }

    @Override
    public Manufacturer update(Long id, Manufacturer manufacturer) {
        return manufacturerDao.update(id, manufacturer);
    }

    @Override
    public boolean deleteById(Long id) {
        return manufacturerDao.deleteById(id);
    }

    @Override
    public boolean delete(Manufacturer manufacturer) {
        return manufacturerDao.delete(manufacturer);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.getAll();
    }
}
