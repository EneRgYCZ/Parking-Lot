package com.example.parkinglot;

public class Parole_Si_Conturi  {
    public String [] parole ={
      "admin","12345","3","parola","5"
    };
    public String [] conturi = {
            "admin@admin.com","catalin.zaharia2013@gmail.com","3","popescu.ion@gmail.com","5"
    };
    public int n=0;
   public void addInfo(String x,String y)
   {
       parole[n]=x;
       conturi[n]=y;
       n++;
   }
   public boolean checkInfo(String x,String y)
   {
       for(int i=0;i<5;i++)
       {
           if(x.equals(parole[i]) && y.equals(conturi[i]))
           {
               return true;
           }
       }
       return false;
   }
}
