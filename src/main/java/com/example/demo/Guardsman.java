
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aldri
 */
public class Guardsman {
    private int idNum;
    private String name;
    private int contactInfo;
    private String address;
    private boolean policeRecord;
    private boolean medicalRecord;
    
    Guardsman(int idNum, String name, int contactInfo, String address, boolean policeRecord, boolean medicalRecord){
        this.idNum = idNum;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.policeRecord = policeRecord;
        this.medicalRecord = medicalRecord;
    }
    public int getIdNum(){
        return idNum;
    }
    public String getName(){
        return name;
    }
    public int getContactInfo(){
        return contactInfo;
    }
    public String getAddress(){
        return address;
    }
    public boolean getPoliceRecord(){
        return policeRecord;
    }
    public boolean getMedicalRecord(){
        return medicalRecord;
    }
    @Override
    public String toString(){
        return idNum+" "+name+" "+contactInfo+" "+address+" "+policeRecord+" "+medicalRecord;
    }
    
}
class demo{
    public static void main(String[] args){
        
        Collection<Guardsman> c =new ArrayList<Guardsman>();
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        int ch;
        do{
            System.out.println("1. input");
            System.out.println("2. display");
            System.out.println("3. Search");
            System.out.println("Enter your choice :");
            ch = s.nextInt();
            
            switch(ch){
                case 1:
                    System.out.println("enter idNum : ");
                    int idNum = s.nextInt();
                    System.out.println("enter name : ");
                    String name = s1.nextLine();
                    System.out.println("enter contactInfo : ");
                    int contactInfo = s.nextInt();
                    System.out.println("enter address : ");
                    String address = s1.nextLine();
                    System.out.println("enter policeRecord : ");
                    boolean policeRecord = s2.nextBoolean();
                    System.out.println("enter medicalRecord : ");
                    boolean medicalRecord = s2.nextBoolean();
                    
                    c.add(new Guardsman(idNum, name, contactInfo, address, policeRecord, medicalRecord));
                break;
                case 2:
                System.out.println("----------------------");
                Iterator<Guardsman> i = c.iterator();
                while(i.hasNext()){
                    Guardsman g = i.next();
                    System.out.println(g);
                    
                }
                System.out.println("-----------------------");
                break;
                case 3:
                    boolean found = false;
                    System.out.println("Enter idNum to search : ");
                    idNum = s.nextInt();
                    System.out.println("----------------------");
                    i = c.iterator();
                while(i.hasNext()){
                    Guardsman g = i.next();
                    if(g.getIdNum() == idNum){
                        System.out.println(g);
                        found = true;
                    }
                }
                if(!found){
                  System.out.println("Record Not Found");
                }
                System.out.println("-----------------------");
                break;
                
            }
        }while(ch!=0);
    } 
}
