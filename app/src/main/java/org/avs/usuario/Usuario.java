package org.avs.usuario;

import android.os.Parcel;
import android.os.Parcelable;

import static android.content.ComponentName.readFromParcel;

/**
 * Created by Administrador on 17/Jun/2015.
 */
public class Usuario implements Parcelable {

    private int id;
    private String phone;
    private String areaCod;
    private String country;
    private String imei;
    private String serialSim;
    private String status;
    private String photo;
    private String born;
    private String countryCod;

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
    public String getBorn() {

        return photo;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getCountryCod() {

        return countryCod;
    }

    public void setCountryCod(String countryCod) {
        this.countryCod = countryCod;
    }

    //necessario para passar entre intent
    public Usuario(){

    }

    public Usuario(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(nome);
        out.writeString(phone);
        out.writeString(areaCod);
        out.writeString(country);
        out.writeString(imei);
        out.writeString(serialSim);
        out.writeString(status);
        out.writeString(photo);
        out.writeString(born);
        out.writeString(countryCod);
    }

    private void readFromParcel(Parcel in) {

        id = in.readInt();
        nome = in.readString();
        phone = in.readString();
        areaCod = in.readString();
        country = in.readString();
        imei = in.readString();
        serialSim = in.readString();
        status = in.readString();
        photo = in.readString();
        born = in.readString();
        countryCod =in.readString();
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
