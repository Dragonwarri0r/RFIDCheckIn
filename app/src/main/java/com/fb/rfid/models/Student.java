package com.fb.rfid.models;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

/**
 * @author Create By FB
 * @description:
 * @date :2019/11/26 15:08
 */
public class Student extends DataSupport {
    private boolean isHere;
    private String name;
    private boolean isMale;
    private String idc;
    private String phone;
    private String lastTime;

    public boolean isHere() {
        return isHere;
    }

    public void setHere(boolean here) {
        isHere = here;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getIdc() {
        return idc;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
