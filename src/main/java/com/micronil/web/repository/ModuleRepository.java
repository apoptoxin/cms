package com.micronil.web.repository;

import com.micronil.web.entity.Module;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by apoptoxin on 2018/3/28.
 */
public interface ModuleRepository extends CrudRepository<Module, Long> {
    public List<Module> findAllSubNodeByKeyLike(String key);

    public List<Module> findByIdIn(Collection<Long> list);

    @Query("select bean.url from Module bean")
    public List<String> findAllUrls();

    public Module findOneByUrl(String url);
}
