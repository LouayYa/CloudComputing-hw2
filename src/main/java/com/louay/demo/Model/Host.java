package com.louay.demo.Model;

import java.util.*;

public class Host {
    private int id;
    private int pe;
    private int ram;
    private List<VM> vms;
    private int nextVmId;

    public Host() {
        this.vms = new ArrayList<>();
        this.nextVmId = 1;
    }

    public Host(int pe, int ram) {
        this.pe = pe;
        this.ram = ram;
        this.vms = new ArrayList<>();
        this.nextVmId = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPe() {
        return pe;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public List<VM> getVms() {
        return new ArrayList<>(vms); 
    }

    public VM getVm(int vmId) {
        for (VM vm : vms) {
            if (vm.getId() == vmId) {
                return vm;
            }
        }
        return null; 
    }

    public VM addVm(VM vm) {
        if (vm.getPe() > this.pe || vm.getRam() > this.ram) {
            return null;
        }
        this.pe -= vm.getPe();
        this.ram -= vm.getRam();
        vm.setId(nextVmId++);
        this.vms.add(vm);
        return vm;
    }

    public boolean deleteVm(int vmId) {
        if (vms.isEmpty()) {
            return false;
        }

        for (int i = 0; i < vms.size(); i++) {
            VM vm = vms.get(i);
            if (vm.getId() == vmId) {
                this.pe += vm.getPe();
                this.ram += vm.getRam();
                vms.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateVm(VM updatedVm) {
        for (int i = 0; i < vms.size(); i++) {
            VM vm = vms.get(i);
            if (vm.getId() == updatedVm.getId()) {
                int peDifference = updatedVm.getPe() - vm.getPe();
                int ramDifference = updatedVm.getRam() - vm.getRam();

                if (peDifference <= this.pe && ramDifference <= this.ram) {
                    this.pe -= peDifference;
                    this.ram -= ramDifference;
                    vm.setPe(updatedVm.getPe());
                    vm.setRam(updatedVm.getRam());
                    return true;
                }
            }
        }
        return false;
    }
}
