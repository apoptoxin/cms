package com.micronil.web.service;

import com.micronil.web.entity.Module;
import com.micronil.web.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public Module save(Module module, Long parentModuleId) {
        Optional<Module> opRole = moduleRepository.findById(parentModuleId);
        Module queryModule = null;
        if (opRole.isPresent()) {
            queryModule = opRole.get();
        } else {
            queryModule = null;
        }
        return save(module, queryModule);
    }

    public Module save(Module module, Module parentModule) {
        if (parentModule == null) {
            module.setKey("");
            module.setLevel(1);
            module.setParentModuleId(Long.parseLong("0"));
            module.setReverseKey("");
        } else {
            module.setKey(parentModule.getKey()+parentModule.getId()+"-");
            module.setLevel(parentModule.getLevel()+1);
            module.setParentModuleId(parentModule.getId());
            module.setReverseKey(parentModule.getKey()+parentModule.getId());
        }
        return moduleRepository.save(module);
    }

    public Module findModuleById(Long moduleId) {
        Optional<Module> opRole = moduleRepository.findById(moduleId);
        return opRole.isPresent()?opRole.get():null;
    }

    public List<Module> findAllSubnodeByModule(Module module) {
        return moduleRepository.findAllSubNodeByKeyLike(module.getKey()+"-"+module.getId()+"-%");
    }

    public List<Module> findAllParentNodeByModule(Module module) {
        if (module == null) {
            return new ArrayList<>();
        }
        Module queryModule = moduleRepository.findById(module.getId()).get();
        String[] array = queryModule.getReverseKey().split("-");
        Collection<Long> collection = new HashSet<>();
        for (int i = 0; i < array.length ; i++) {
            if (array[i].length() > 0) {
                collection.add(Long.parseLong(array[i]));
            }
        }
        return moduleRepository.findByIdIn(collection);
    }

    public List<String> findAllUrls() {
        List<String> urls = moduleRepository.findAllUrls();
        if (urls == null) {
            return new ArrayList<>();
        } else {
            return urls;
        }
    }
}
