package org.smart4j.chapter3.model;

public class Customer {

    /*
    * ID
    * */
    private long id;

    /*
    * 姓名
    * */
    private String name;

    /*
    * 电话号码
    * */
    private String telephone;

    /*
    * 联系人
    * */
    private String contact;

    /*
    * 邮箱地址
    * */
    private String email;

    /*
    * 备注
    * */
    private String remark;

    public Customer() {
    }

    public Customer(long id,String name,String telephone,String contact,String email,String remark){
        this.id = id;
        this.name=name;
        this.telephone=telephone;
        this.contact=contact;
        this.email=email;
        this.remark=remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
