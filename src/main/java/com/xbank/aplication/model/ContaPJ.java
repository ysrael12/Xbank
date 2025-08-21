package com.xbank.aplication.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PJ")
public class ContaPJ extends Accounts {
    private String cnpjEmpresa;
    private String nomeEmpresa;

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
}