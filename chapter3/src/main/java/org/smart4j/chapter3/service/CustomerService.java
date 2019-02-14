package org.smart4j.chapter3.service;


import org.smart4j.chapter3.model.Customer;

import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.bean.FileParam;
import org.smart4j.framework.helper.DatabaseHelper;
import org.smart4j.framework.helper.UploadHelper;


import java.io.File;
import java.util.List;
import java.util.Map;
@Service
public class CustomerService {
    /*
    * 获取用户列表
    * */
    public List<Customer> getCustomerList(){
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }
    /*
    * 获取客户
    * */
    public Customer getCustomer(Long id){
        String sql = "SELECT * FROM customer where id = ?";
        return DatabaseHelper.queryEntity(Customer.class,sql,id);
    }
    /*
    * 删除客户
    * */
    public boolean deleteCustomer(Long id){
        return DatabaseHelper.deleteEntity(Customer.class,id);
    }
    /*
    * 更新客户
    * */
    public boolean updateCustomer(Long id,Map<String,Object> fieldMap){
        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    /*
    * 创建客户
    * */
    public boolean createCustomer(Map<String,Object> fieldMap, FileParam fileParam){
        boolean result = DatabaseHelper.insertEntity(Customer.class, fieldMap);
        if(result){
            UploadHelper.uploadFile("/tmp/upload/", fileParam);
        }
        return result;
    }
}
