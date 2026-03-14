package com.louay.demo.Controller;

import com.louay.demo.Model.*;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/hosts")
public class HostController {

    @Autowired
    private HostService hservice;

    @GetMapping
    public List<Host> getAllHosts() {
        return hservice.getHosts();
    }

    @GetMapping("/{id}")
    public Host getHost(@PathVariable("id") int id) {
        return hservice.getHost(id);
    }

    @PostMapping
    public Host addHost(@RequestBody Host ahost){
        return hservice.addHost(ahost);
    }

    @PutMapping("/{id}")
    public Host updateHost(@PathVariable("id") int id, @RequestBody Host ahost){
        ahost.setId(id);
        return hservice.updateHost(ahost);
    }

    @DeleteMapping("/{id}")
    public boolean removeHost(@PathVariable("id") int id){
        return hservice.removeHost(id);
    }
    
    @PostMapping("/{id}/vms")
    public VM addVm(@PathVariable("id") int id, @RequestBody VM vm){
        return hservice.addVmToHost(id, vm);
    }

    @GetMapping("/{id}/vms")
    public List<VM> getVms(@PathVariable("id") int id){
        return hservice.getVmsFromHost(id);
    }

    @GetMapping("/{id}/vms/{vmId}")
    public VM getVm(@PathVariable("id") int id, @PathVariable("vmId") int vmId){
        return hservice.getVmFromHost(id, vmId);
    }

    @DeleteMapping("/{id}/vms/{vmId}")
    public boolean deleteVm(@PathVariable("id") int id, @PathVariable("vmId") int vmId){
        return hservice.deleteVmFromHost(id, vmId);
    }

    @PutMapping("/{id}/vms/{vmId}")
    public boolean updateVm(@PathVariable("id") int id, @PathVariable("vmId") int vmId, @RequestBody VM vm){
        vm.setId(vmId);
        return hservice.updateVmInHost(id, vm);
    }

}

