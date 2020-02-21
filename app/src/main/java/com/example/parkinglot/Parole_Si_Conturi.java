package com.example.parkinglot;

public class Parole_Si_Conturi  {
    public String [] parole ={
      "1","2","3","4","5"
    };
    public String [] conturi = {
            "1","2","3","4","5"
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
