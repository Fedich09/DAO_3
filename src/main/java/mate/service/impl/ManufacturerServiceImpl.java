package mate.service.impl;

import java.util.List;
import java.util.Optional;
import mate.dao.ManufacturerDao;
import mate.lib.Inject;
import mate.lib.Service;
import mate.model.Manufacturer;
import mate.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer add(Manufacturer manufacturer) {
        return manufacturerDao.add(manufacturer);
    }

    @Override
    public Optional<Manufacturer> getById(Long id) {
        return manufacturerDao.getById(id);
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return manufacturerDao.deleteById(id);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.getAll();
    }
}
