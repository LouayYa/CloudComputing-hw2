package com.louay.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VM {
    @Id
    private int id;
    private int pe;
    private int ram;

    public VM() {
    }

    public VM(int pe, int ram) {
        this.pe = pe;
        this.ram = ram;
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
}