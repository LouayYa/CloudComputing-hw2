package com.louay.demo.Model;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostService {

    @Autowired
    private HostRepository hostrepo;

    public List<Host> getHosts() {
        return hostrepo.findAll();
    }

    public Host addHost(Host ahost) {
        return hostrepo.save(ahost);
    }

    public Host getHost(int id) {
        return hostrepo.findById(id).orElse(null);
    }

    public Host updateHost(Host ahost){
        Host existingHost = hostrepo.findById(ahost.getId()).orElse(null);
        if (existingHost != null) {
            existingHost.setPe(ahost.getPe());
            existingHost.setRam(ahost.getRam());
            return hostrepo.save(existingHost);
        }
        return null;
    }

    public boolean removeHost(int id) {
        Host host = hostrepo.findById(id).orElse(null);
        if (host != null) {
            hostrepo.delete(host);
            return true;
        }
        return false;
    }

    public VM addVmToHost(int hostId, VM vm){
        Host host = hostrepo.findById(hostId).orElse(null);
        if (host != null) {
            VM addedVm = host.addVm(vm);
            if (addedVm != null) {
                hostrepo.save(host);
                return addedVm;
            }
        }
        return null;
    }

    public boolean deleteVmFromHost(int hostId, int vmId){
        Host host = hostrepo.findById(hostId).orElse(null);
        if (host != null && host.deleteVm(vmId)) {
            hostrepo.save(host);
            return true;
        }
        return false;
    }

    public boolean updateVmInHost(int hostId, VM vm){
        Host host = hostrepo.findById(hostId).orElse(null);
        if (host != null && host.updateVm(vm)) {
            hostrepo.save(host);
            return true;
        }
        return false;
    }

    public VM getVmFromHost(int hostId, int vmId){
        Host host = hostrepo.findById(hostId).orElse(null);
        if (host != null) {
            return host.getVm(vmId);
        }
        return null;
    }

    public List<VM> getVmsFromHost(int hostId){
        Host host = hostrepo.findById(hostId).orElse(null);
        if (host != null) {
            return host.getVms();
        }
        return new ArrayList<>();
    }
}
