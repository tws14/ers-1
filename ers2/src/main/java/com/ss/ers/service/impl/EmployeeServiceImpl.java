package com.ss.ers.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ss.ers.dao.EmployeeRepository;
import com.ss.ers.entity.Employee;
import com.ss.ers.service.EmployeeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    // 画像保存用定数フィールド
    private static final String PROFILE_IMAGE = "C:\\img\\profile\\";
    
    @Override
    public void save (Employee employee) {
        
        employeeRepository.save (employee);
    }
    
    @Override
    public List<Employee> findAll () {
        
        employeeRepository.findAll ();
        
        return employeeRepository.findAll ();
    }
    
    @Override
    public Optional<Employee> findById (Integer id) {
        
        return employeeRepository.findById (id);
    }
    
    @Override
    public void deleteById (Integer id) {
        
        employeeRepository.deleteById (id);
    }
    
    @Override
    public void update (Employee employee) throws IllegalStateException, IOException {
        
        MultipartFile uploadFile = employee.getImageForm ();
        
        // 絶対パス
        // separator バックスラッシュ
        String fullPath = PROFILE_IMAGE + employee.getId () + File.separator;
        
        // ディレクトリ作成
        if (!uploadFile.isEmpty ()) {
            File directory = new File (fullPath);
            if (directory.exists ()) {
                // 他に画像があれば削除
                for (File target : directory.listFiles ()) {
                    target.delete ();
                }
            } else {
                // mkdirsでprofileフォルダから作成
                directory.mkdirs ();
            }
        
        // 保存先
        File dest = new File (fullPath + uploadFile.getOriginalFilename ());
        uploadFile.transferTo (dest);
        
        // ユーザーオブジェクトに入れる getabsolutepath でファイル名も含めたfullpathを取得
        employee.setImage (dest.getAbsolutePath ());
        
        }
        employeeRepository.save (employee);
    }

    @Override
    public Employee findByEmail (String Email) {
        
       Optional<Employee> option  =  employeeRepository.findByEmail(Email);
       
       Employee  employee = option.orElse (null);
       
        return employee;
    }
    
}
