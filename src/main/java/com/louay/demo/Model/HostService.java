package com.louay.demo.Model;
import java.util.*;


public class HostService {
    private Map<Integer, Host> hosts;
    private int nextHostId;

    public HostService() {
        hosts = new HashMap<>();
        this.nextHostId = 1;
    }

    public List<Host> getHosts() {
        return new ArrayList<>(hosts.values());
    }
   
    public Host addHost(Host ahost) {
        ahost.setId(nextHostId++);
        hosts.put(ahost.getId(), ahost);
        return ahost;
    }

    public Host getHost(int id) {
        return hosts.get(id);
    }

    public Host updateHost(Host ahost){
        if (hosts.containsKey(ahost.getId())) {
            Host temp = hosts.get(ahost.getId());
            temp.setPe(ahost.getPe());
            temp.setRam(ahost.getRam());
            return temp;
        }
        return null;
    }

    public boolean removeHost(int id) {
        if (hosts.containsKey(id)) {
            hosts.remove(id);
            return true;
        }
        return false;
    }

    public VM addVmToHost(int hostId, VM vm){
        Host host = hosts.get(hostId);
        if (host != null) {
            return host.addVm(vm);
        }
        return null;
    }

    public boolean deleteVmFromHost(int hostId, int vmId){
        Host host = hosts.get(hostId);
        if (host != null) {
            return host.deleteVm(vmId);
        }
        return false;
    }
    public boolean updateVmInHost(int hostId, VM vm){
        Host host = hosts.get(hostId);
        if (host != null) {
            return host.updateVm(vm);
        }
        return false;
    }
    public VM getVmFromHost(int hostId, int vmId){
        Host host = hosts.get(hostId);
        if (host != null) {
            return host.getVm(vmId);
        }
        return null;
    }
    public List<VM> getVmsFromHost(int hostId){
        Host host = hosts.get(hostId);
        if (host != null) {
            return host.getVms();
        }
        return new ArrayList<>();
    }
}
