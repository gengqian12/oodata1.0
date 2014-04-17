package com.oo.model;

import java.math.BigDecimal;
import java.util.Date;

public class IfengNews {
    private Integer id;
    private Integer type;
    private String message;
    private String address;
    private String keyWord;
    private BigDecimal mapx;
    private BigDecimal mapy;
    private String fromSite;
    private Integer status;
    private Date createDate;
    private Date updateDate;
    private Integer createUser;
    private Integer updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public BigDecimal getMapx() {
        return mapx;
    }

    public void setMapx(BigDecimal mapx) {
        this.mapx = mapx;
    }

    public BigDecimal getMapy() {
        return mapy;
    }

    public void setMapy(BigDecimal mapy) {
        this.mapy = mapy;
    }

    public String getFromSite() {
        return fromSite;
    }

    public void setFromSite(String fromSite) {
        this.fromSite = fromSite;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}