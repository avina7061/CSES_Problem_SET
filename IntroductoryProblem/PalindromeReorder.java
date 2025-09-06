

import java.util.*;
import java.util.Scanner;

public class PalindromeReorder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


            String s =sc.next();
            HashMap<Character,Integer> map = new HashMap<>();
            for(int i=0; i<s.length(); i++){
                map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
            }
            int cnt=0;
            boolean bc=true;
            for(char i: map.keySet()){
                 if(map.get(i)%2!=0){
                     cnt++;
                 }
                 if(cnt>1) {
                     bc=false;
                     System.out.println("NO SOLUTION");
                     break;
                 };
            }

           if(bc){
               String s1="";
               String s2="";
               String s3="";

               for(char i: map.keySet()){
                   int val= map.get(i);
                   if(val%2!=0){
                       s3+=i;
                       val--;
                       boolean bt =true;
                       for(int j=0; j<val; j++){
                           if(bt){
                               s1+=i;
                               bt=false;
                           }
                           else{
                               s2+=i;
                               bt=true;
                           }
                       }
                   }
                   else{
                       boolean bt =true;
                       for(int j=0; j<val; j++){
                           if(bt){
                               s1+=i;
                               bt=false;
                           }
                           else{
                               s2+=i;
                               bt=true;
                           }
                       }
                   }
               }
               String ans="";
               if(s3.length()>0){
                   ans+=s1+s3;
               }
               else{
                   ans+=s1;
               }
               for (int i=s2.length()-1;i>=0;i--){
                   ans+= s2.charAt(i);
               }
               System.out.println(ans);
           }


    }
}
