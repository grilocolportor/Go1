package org.avs.usuario;

/**
 * Created by Administrador on 17/Jun/2015.
 */
public class Usuario {

    private int id;
    private String phone;
    private String areaCod;
    private String country;
    private String imei;
    private String serialSim;
    private String status;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSerialSim() {
        return serialSim;
    }

    public void setSerialSim(String serialSim) {
        this.serialSim = serialSim;
    }

    public String getAreaCod() {

        return areaCod;
    }

    public void setAreaCod(String areaCod) {
        this.areaCod = areaCod;
    }

    public String getPhoto() {

        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
