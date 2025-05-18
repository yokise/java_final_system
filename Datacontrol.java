import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.xml.catalog.Catalog;

public class Datacontrol {
    public Datacontrol() {
        
        ;
    }
    

    public static void catalogs(List<String> catalog) {
        try {
            Scanner sca = new Scanner(new File("catalog.txt"));
            String temp;
            while (sca.hasNextLine()) {
                temp = sca.nextLine();
                catalog.add(temp);
            }
            sca.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // 把資料存進list
    public static void datas(List<Man> man) {
        try {
            Scanner sca = new Scanner(new File("data.txt"));
            String temp;
            while (sca.hasNextLine()) {
                temp = sca.nextLine();
                man.add(new Man(temp));
            }
            sca.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
     // 讀檔案內的十種資料存到conf的陣列內
     public static void config(String[] conf,String[] word)

     {
         try {
             Scanner s = new Scanner(new File("config.txt"));
             String temp;
             int count = 0;
 
             while (count < 10) {
                 word[count] = s.next().trim();
                 temp = s.next();
                 conf[count] = temp.trim();
                 count++;
             }
             s.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
             System.exit(0);
         }
 
     }

     public static void inputaccount(List<Accout> acc){

        try {
            acc.clear();
            Scanner s = new Scanner(new File("account.txt"));

            while (s.hasNext()) {
                String a = s.next().trim();
                String b = s.nextLine().trim();
                acc.add(new Accout(a, b));
            }

            s.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();;
        }


     }
     public static List<Man> getrawlist() {
        List<Man> raw = new ArrayList<>() ;
        try {
            Scanner s = new Scanner(new File("data.txt"));
            while (s.hasNextLine()) {
                String a = s.nextLine();
                raw.add(new Man(a));
               
            }
            s.close();
            return raw ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
            return raw ;
     }
      public static void updatecata(List<String> catalog) {
        try {
            FileWriter f = new FileWriter("catalog.txt");
            for (String x : catalog) {
                f.write(x + "\n");
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static void confupdate(String[] word ,String[] conf) {
        try {
            FileWriter w = new FileWriter("config.txt");

            for (int i = 0; i < 10; i++) {
                w.write(word[i] + " " + conf[i] + "\n");

            }
            w.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
    public static void updateacc(List<Accout> acc) {
        try {
            FileWriter w = new FileWriter("account.txt");

            for (Accout ac : acc) {
                w.write(ac.getAccount() + " "  + ac.getPassword() + "\n");
            }
            w.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static void opt(String[] conf ,Comparator<Man> comp, List<Man> man){


         try {
                FileWriter f = new FileWriter("data.txt");
                List<Man> newman = new ArrayList<>(man);
                Collections.sort(newman, comp);
                for (Man m : newman) {
                    f.write((m.idformatString() + m.nameformatString(Boolean.parseBoolean(conf[3]))
                            + m.phoneformatString(Boolean.parseBoolean(conf[2]))
                            + m.catalogformatString(Boolean.parseBoolean(conf[5]))
                            + m.emailformatString(Boolean.parseBoolean(conf[6]))
                            + m.birthformatString(Boolean.parseBoolean(conf[4]))) + "\n");
                }
                f.close();
                System.out.println("Data_optimize_success");
            } catch (IOException e) {
                ;
            }
    }

}