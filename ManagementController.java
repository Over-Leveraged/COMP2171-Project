/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author D'Janae
 */
public class ManagementController {
    private Long id;
    private String name;
    private String contact;
    private String company;
    
   
    public ManagementController(Long id, String name,String contact,String company){
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.company = company;
        
    }
    
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getContact(){
        return contact;
    }
     public String getCompany(){
        return company;
    }
    
}