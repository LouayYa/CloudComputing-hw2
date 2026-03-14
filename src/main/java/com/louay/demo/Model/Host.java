package com.louay.demo.Model;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pe;
    private int ram;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<VM> vms;

    public Host() {
        this.vms = new ArrayList<>();
    }

    public Host(int pe, int ram) {
        this.pe = pe;
        this.ram = ram;
        this.vms = new ArrayList<>();
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
        vm.setId(vms.size() + 1);
        this.pe -= vm.getPe();
        this.ram -= vm.getRam();
        this.vms.add(vm);
        return vm;
    }

    public boolean deleteVm(int vmId) {
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
