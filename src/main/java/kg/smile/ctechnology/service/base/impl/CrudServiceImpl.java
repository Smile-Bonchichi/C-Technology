package kg.smile.ctechnology.service.base.impl;

import kg.smile.ctechnology.service.base.CrudService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(isolation = Isolation.SERIALIZABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrudServiceImpl<T> implements CrudService<T> {
    final CrudRepository<T, Long> crudRepository;

    public CrudServiceImpl(CrudRepository<T, Long> crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public T saveOrUpdate(T t) {
        return crudRepository.save(t);
    }

    @Override
    public T delete(Long id) {
        T t = getById(id);
        if (t != null)
            crudRepository.delete(t);
        return t;
    }

    @Override
    public T getById(Long id) {
        return crudRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) crudRepository.findAll();
    }
}