package com.xbank.aplication.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PF")
public class ContaPF extends Accounts {
    private String cpfTitular;

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }



	
}