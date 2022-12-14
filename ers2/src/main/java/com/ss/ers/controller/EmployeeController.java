package com.ss.ers.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.SizeLimitExceededException;
import javax.validation.ConstraintViolationException;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ss.ers.aop.LogingAop;
import com.ss.ers.entity.Employee;
import com.ss.ers.entity.LogInfo;
import com.ss.ers.entity.domain.UserDetailsImpl;
import com.ss.ers.form.MultipartForm;
import com.ss.ers.form.RegisterForm;
import com.ss.ers.service.EmployeeService;
import com.ss.ers.service.EmployeeServiceCustom;
import com.ss.ers.service.LogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping ("/emp")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private EmployeeServiceCustom employeeServiceCustom;
    
    @Autowired
    private LogService logService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping ("/list")
    public String findAll (Model model, @ModelAttribute RegisterForm form,
            @ModelAttribute MultipartForm multipartForm) {
        
        List<Employee> employees = employeeService.findAll ();
        
        model.addAttribute ("employees", employees);
        
        log.info ("???????????????");
        
        return "/emp/list";
    }
    
    @GetMapping ("/andSearch")
    public String andSearch (@ModelAttribute RegisterForm form, Model model) {
        
        Employee employee = modelMapper.map (form, Employee.class);
        
        String lastName = employee.getLastName ().strip ();
        String firstName = employee.getFirstName ().strip ();
        String gender = employee.getGender ();
        String dept = employee.getDept ();
        LocalDate birth = employee.getBirth ();
        String email = employee.getEmail ().strip ();
        String tel = employee.getTel ().strip ();
        String zipcode = employee.getZipcode ().strip ();
        String address1 = employee.getAddress1 ().strip ();
        String address2 = employee.getAddress2 ().strip ();
        String address3 = employee.getAddress3 ().strip ();
        String school1 = employee.getSchool1 ().strip ();
        String school2 = employee.getSchool2 ().strip ();
        String skill = employee.getSkill ().strip ();
        
        List<Employee> employees = null;
        
        if ("".equals (lastName) && "".equals (firstName) && gender == null && "".equals (dept) && (birth == null)
                && "".equals (email) && "".equals (tel) && "".equals (zipcode) && "".equals (address1)
                && "".equals (address2) && "".equals (address3) && "".equals (school1) && "".equals (school2)
                && "".equals (skill)) {
            
            employees = employeeService.findAll ();
            
            model.addAttribute ("employees", employees);
        } else {
            
            employees = employeeServiceCustom.andSearch (lastName, firstName, gender, dept, birth, email, tel, zipcode,
                    address1, address2, address3, school1, school2, skill);
            
            model.addAttribute ("employees", employees);
        }
        
        return "/emp/employeeTable :: employeeTable";
    }
    
    @GetMapping ("/searchAll")
    public String searchAll (@ModelAttribute RegisterForm form, @RequestParam ("searchall") String searchall,
            Model model) {
        
        String searchWord = searchall.strip ();
        
        List<Employee> tempEmployees = employeeService.findAll ();
        
        List<Employee> employees = tempEmployees.stream ()
                .filter (
                        e -> e.getLastName ().contains (searchWord) || e.getFirstName ().contains (searchWord)
                                || e.getGender ().contains (searchWord) || e.getDept ().contains (searchWord)
                                || e.getBirth ().format (DateTimeFormatter.ofPattern ("yyyy-MM-dd"))
                                        .contains (searchWord)
                                || e.getEmail ().contains (searchWord) || e.getTel ().contains (searchWord)
                                || e.getZipcode ().contains (searchWord) || e.getAddress1 ().contains (searchWord)
                                || e.getAddress2 ().contains (searchWord) || e.getAddress3 ().contains (searchWord)
                                || e.getSchool1 ().contains (searchWord) || e.getSchool2 ().contains (searchWord)
                                || e.getSkill ().contains (searchWord))
                .collect (Collectors.toList ());
        
        model.addAttribute ("employees", employees);
        
        return "/emp/employeeTable :: employeeTable";
    }
    
    @GetMapping ("/register")
    public String getRegisterForm (@ModelAttribute RegisterForm form) {
        
        log.info ("???????????????????????? ?????????: " + form.toString ());
        
        return "/emp/register";
    }
    
    @GetMapping ("/detail/{id}")
    public String findById (@PathVariable ("id") Integer id, Model model) {
        
        String id_S = String.valueOf (id);
        
        log.info (id_S);
        
        Optional<Employee> option = employeeService.findById (id);
        
        Employee employee = option.orElse (null);
        
        if (employee == null) {
            
            return "redirect:/emp/list.html";
        }
        
        model.addAttribute ("employee", employee);
        
        return "/emp/detail";
    }
    
    @GetMapping ("/updateForm/{id}")
    public String getUpdatePage (@PathVariable ("id") Integer id, Model model,
            @ModelAttribute RegisterForm registerForm) {
        
        log.info ("????????????????????????");
        
        String id_S = String.valueOf (id);
        
        log.info (id_S);
        
        Optional<Employee> option = employeeService.findById (id);
        
        Employee employee = option.orElse (null);
        
        if (employee == null) {
            
            return "redirect:/emp/list";
        }
        
        log.info (employee.encodeBase64 ());
        
        model.addAttribute ("employee", employee);
        
        return "/emp/update";
    }
    
    @PostMapping ("/detail/{id}")
    public String deleteById (@PathVariable ("id") Integer id,
            
            Model model, @AuthenticationPrincipal UserDetailsImpl user) {
        
        Optional<Employee> option = employeeService.findById (id);
        
        Employee delEmployee = option.orElse (null);
        
        employeeService.deleteById (id);
        
        // ???????????????
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("delete")
                .target (delEmployee.getLastName () + " " + delEmployee.getFirstName ()).result ("success")
                .date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        String id_S = String.valueOf (id);
        log.info ("??????????????????" + id_S);
        log.info ("name : " + user.getUsername ());
        
        return "redirect:/emp/list";
        
    }
    
    @LogingAop
    @PostMapping ("/detailDelete/{id}")
    public String deleteByIdFromDetail (@PathVariable ("id") Integer id,
            
            Model model, @AuthenticationPrincipal UserDetailsImpl user) {
        
        Optional<Employee> option = employeeService.findById (id);
        
        Employee delEmployee = option.orElse (null);
        
        if (delEmployee != null) {
            employeeService.deleteById (id);
            
            // ???????????????
            LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("delete")
                    .target (delEmployee.getLastName () + " " + delEmployee.getFirstName ()).result ("success")
                    .date (LocalDate.now ()).build ();
            
            logService.save (logInfo);
            
            String id_S = String.valueOf (id);
            log.info ("??????????????????" + id_S);
            log.info ("name : " + user.getUsername ());
        }
        
        List<Employee> employees = employeeService.findAll ();
        
        model.addAttribute ("employees", employees);
        
        return "redirect:/emp/list";
        
    }
    
    @PostMapping ("/update")
    @LogingAop
    public String update (@ModelAttribute @Validated Employee employee, BindingResult bindingResult, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        if (bindingResult.hasErrors ()) {
            model.addAttribute ("employee", employee);
            
            return "/emp/update";
            
        }
        
        Employee tempEmployee = employeeService.findByEmail (employee.getEmail ());
        if (tempEmployee == null || (tempEmployee != null && employee.getId ().equals (tempEmployee.getId ()))) {
            
            try {
                employeeService.update (employee);
            } catch (Exception e) {
                // ????????????
                e.printStackTrace ();
            }
            
            log.info ("?????????????????????");
            
            LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("update")
                    .target (employee.getLastName () + " " + employee.getFirstName ()).result ("success")
                    .date (LocalDate.now ()).build ();
            
            logService.save (logInfo);
            
            return "redirect:/emp/list";
            
        } else {
            String errorMessage = "????????????????????????";
            
            LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("update")
                    .target (employee.getLastName () + " " + employee.getFirstName ()).result ("error")
                    .date (LocalDate.now ()).build ();
            
            logService.save (logInfo);
            
            model.addAttribute ("employee", employee);
            model.addAttribute ("errorMessage", errorMessage);
            
            return "/emp/update";
        }
    }
    
    @PostMapping ("/register")
    @LogingAop
    public String save (@ModelAttribute @Validated RegisterForm form, BindingResult bindingResult, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        if (bindingResult.hasErrors ()) {
            return "/emp/register";
        }
        
        Employee tempEmployee = employeeService.findByEmail (form.getEmail ());
        
        if (tempEmployee == null) {
            
            log.info ("??????????????????");
            
            Employee employee = modelMapper.map (form, Employee.class);
            
            employee.setImage ("");
            
            employeeService.save (employee);
            
            LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("Registration")
                    .target (employee.getLastName () + " " + employee.getFirstName ()).result ("success")
                    .date (LocalDate.now ()).build ();
            
            logService.save (logInfo);
            
            return "redirect:/emp/list";
        } else {
            String errorMessage = "????????????????????????";
            
            model.addAttribute ("errorMessage", errorMessage);
            
            return "/emp/register";
            
        }
        
    }
    
    // csv import
    @PostMapping (value = "/import", params = "upload_file")
    @LogingAop
    public String importFile (MultipartForm form, @AuthenticationPrincipal UserDetailsImpl user,
            @ModelAttribute RegisterForm registerForm, Model model) {
        
        MultipartFile file = form.getFile ();
        
        String contentType = file.getContentType ();
        log.info (contentType);
        
        int errorCount = 0;
        
        // csv??????
        if (contentType.equals ("text/csv")) {
            
            try (BufferedReader br = new BufferedReader (
                    new InputStreamReader (file.getInputStream (), StandardCharsets.UTF_8))) {
                String line;
                
                // ?????????
                while ((line = br.readLine ()) != null) {
                    final String[] column = line.split (",");
                    
                    boolean a = (column[2].equals ("???") || column[2].equals ("???"));
                    boolean b = (column[3].equals ("G1") || column[3].equals ("G2") || column[3].equals ("G3")
                            || column[3].equals ("G4") || column[3].equals ("G5"));
                    if (a && b) {
                        
                        if (column.length == 14) {
                            final Employee employee = Employee.builder ().lastName (column[0].replace ("???", " "))
                                    .firstName (column[1].replace ("???", " ")).gender (column[2].replace ("???", " "))
                                    .dept (column[3].replace ("???", " "))
                                    .birth (LocalDate.parse (column[4], DateTimeFormatter.ofPattern ("yyyy-MM-dd")))
                                    .email (column[5].replace ("???", " "))
                                    .tel (column[6].replace ("-", "").replace ("???", " "))
                                    .zipcode (column[7].replace ("???", " ")).address1 (column[8].replace ("???", " "))
                                    .address2 (column[9].replace ("???", " ")).address3 (column[10].replace ("???", " "))
                                    .school1 (column[11].replace ("???", " ")).school2 (column[12].replace ("???", " "))
                                    .skill (column[13]).image ("").build ();
                            
                            employeeService.save (employee);
                            
                            LogInfo logInfo = LogInfo.builder ().name (user.getUsername ())
                                    .operation ("Registration(CSV)")
                                    .target (employee.getLastName () + " " + employee.getFirstName ())
                                    .result ("success").date (LocalDate.now ()).build ();
                            logService.save (logInfo);
                            
                        } else {
                            throw new ArrayIndexOutOfBoundsException ();
                        }
                        
                    } else {
                        throw new ConstraintViolationException (null);
                    }
                    
                }
                
            } catch (IOException e) {
                
                e.printStackTrace ();
            }
            
        } else {
            String errorMessage = "CSV??????????????????????????????";
            
            List<Employee> employees = employeeService.findAll ();
            
            model.addAttribute ("employees", employees);
            model.addAttribute ("multipartForm", form);
            model.addAttribute ("errorMessage", errorMessage);
            
            LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("Registration(CSV)")
                    .target ("Not CSV").result ("error").date (LocalDate.now ()).build ();
            logService.save (logInfo);
            
            return "/emp/list";
            
        }
        
        return "redirect:/emp/list";
    }
    
    // ????????????????????????
    
    @ExceptionHandler (DateTimeParseException.class)
    public String dateTimeParseE (DateTimeParseException e, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("Registration(CSV)")
                .target ("CSVrow").result ("DateParseError").date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        // ????????????????????????????????????Model?????????
        model.addAttribute ("error", "??????????????????????????????ExceptionHandler");
        
        // ????????????????????????????????????Model?????????
        model.addAttribute ("message",
                "DateTimeParseException????????????????????????" + "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
        
        // HTTP????????????????????????500??????Model?????????
        model.addAttribute ("status", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return "error";
    }
    
    @ExceptionHandler (SQLIntegrityConstraintViolationException.class)
    public String sqlIntegrityE (SQLIntegrityConstraintViolationException e, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("Registration(CSV)")
                .target ("CSVrow").result ("EmailError").date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        // ????????????????????????????????????Model?????????
        model.addAttribute ("error", "??????????????????????????????DB??????ExceptionHandler");
        
        // ????????????????????????????????????Model?????????
        model.addAttribute ("message", "SQLIntegrityConstraintViolationException????????????????????????"
                + "????????????????????????????????????????????????????????????NG??????????????????????????????????????????" + "??????????????????????????????");
        
        // HTTP????????????????????????500??????Model?????????
        model.addAttribute ("status", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return "error";
    }
    
    @ExceptionHandler (FileSizeLimitExceededException.class)
    public String fileSizeE (FileSizeLimitExceededException e, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("Registration(CSV)")
                .target ("CSVrow").result ("FileSizeError").date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        // ????????????????????????????????????Model?????????
        model.addAttribute ("error", "??????????????????????????????DB??????ExceptionHandler");
        
        // ????????????????????????????????????Model?????????
        model.addAttribute ("message", "FileSizeLimitExceededException????????????????????????????????????????????????????????????????????????");
        
        // HTTP????????????????????????500??????Model?????????
        model.addAttribute ("status", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return "error";
    }
    
    @ExceptionHandler (SizeLimitExceededException.class)
    public String fileSizeE2 (SizeLimitExceededException e, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("Registration(CSV)").target ("File")
                .result ("FileSizeError").date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        // ????????????????????????????????????Model?????????
        model.addAttribute ("error", "??????????????????????????????DB??????ExceptionHandler");
        
        // ????????????????????????????????????Model?????????
        model.addAttribute ("message", "SizeLimitExceededException????????????????????????????????????????????????????????????????????????");
        
        // HTTP????????????????????????500??????Model?????????
        model.addAttribute ("status", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return "error";
    }
    
    @ExceptionHandler (ConstraintViolationException.class)
    public String spaceContainsColumnsE (ConstraintViolationException e, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("Registration(CSV)").target ("File")
                .result ("ValidationError").date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        model.addAttribute ("error", "????????????????????????");
        
        model.addAttribute ("message",
                "ConstraintViolationException????????????????????????" + " ????????????????????????????????????????????????????????????????????????????????? ????????????????????????????????????????????????");
        
        model.addAttribute ("status", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return "error";
    }
    
    @ExceptionHandler (ArrayIndexOutOfBoundsException.class)
    public String spaceContainsColumnsE (ArrayIndexOutOfBoundsException e, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("Registration(CSV)")
                .target ("CSVrow").result ("ColumnSizeError").date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        model.addAttribute ("error", "????????????????????????");
        
        model.addAttribute ("message",
                "ArrayIndexOutOfBoundsException????????????????????????" + "?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
        
        model.addAttribute ("status", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return "error";
    }
    
    @ExceptionHandler (EmptyResultDataAccessException.class)
    public String spaceContainsColumnsE (EmptyResultDataAccessException e, Model model,
            @AuthenticationPrincipal UserDetailsImpl user) {
        
        LogInfo logInfo = LogInfo.builder ().name (user.getUsername ()).operation ("delete").target ("Empty")
                .result ("NotExistError").date (LocalDate.now ()).build ();
        
        logService.save (logInfo);
        
        model.addAttribute ("error", "????????????????????????");
        
        model.addAttribute ("message", "EmptyResultDataAccessException????????????????????????" + "????????????????????????????????????????????????????????????");
        
        model.addAttribute ("status", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return "error";
    }
    
}
