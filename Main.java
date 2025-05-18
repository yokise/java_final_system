import java.util.*;
import java.io.*;

public class Main {
    public static String[] conf = new String[10];
    public static String[] word = new String[10];
    public static List<Accout> acc = new ArrayList<>();
    public static List<Man> man = new ArrayList<>();
    public static List<String> catalog = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in) ;
    public static void main(String[] args) {
        

        String op = "98";
        datas();
        catalogs();
        config(conf);
        login();
        
        doMainMenu(true);
        op = sc.nextLine() ;
        
        while (!op.equals("99")) {
            doop(op);
            doMainMenu(true);
            op = sc.nextLine();

        }
    }

    public static void doMainMenu(boolean showMainManu) {
        System.out.print("****************************************\n" +
                "1. Show_a 2. Show_p 3.Show_by_c 4. Search 5.Mod 6.Del 7.Add_cont\n" +
                "8.Add_cat 9. Show_cat 10.Set_field 11.Set_page 12.Set_order 13.Set_sort\n" +
                "14. Show_r 15.Opt 16. Show_acc 17.Add_acc 18.Del_acc 19.Mod_acc 20. Logout 99. Exit\n" +
                "****************************************\n");
    }

    public static void backorexit() {

        System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
        String a = sc.nextLine();
        if (a.equals("0")) {
            ;
        } else
        {   
            System.exit(0);
        }
    }

    public static void login() {

        try {
            acc.clear();
            Scanner s = new Scanner(new File("account.txt"));

            while (s.hasNext()) {
                String a = s.nextLine().trim();
                String b = s.nextLine().trim();
                acc.add(new Accout(a, b));
            }

            s.close();
            
            String b, c, d;

            int count = 0;
            Boolean success = false;
            while (true) {
                if (count < 3) {
                    System.out.println("Account:");
                    b = sc.nextLine().trim();
                    System.out.println("Password:");
                    c = sc.nextLine().trim();
                    System.out.println("Verify_string:" + conf[8]);
                    System.out.println("Input_Verify_string:");
                    d = sc.nextLine().trim();
                    for (Accout a : acc) {

                        if (b.equals(a.getAccount().trim()) && c.equals(a.getPassword().trim()) && d.equals(conf[8])) {
                            success = true;
                            break;

                        }
                    }
                    if (success)
                        {   System.out.println("Login_success");
                            break;}
                    else {
                        System.out.println("Error_wrong_account_password_or_verify_string");
                        count++;
                    }
                } else
                    System.exit(0);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();;
        }
    }

    public static void doop(String a) {
        switch (a) {
            case "1":
                doShowAll();
                backorexit();
                break;
            case "2":
                doShowPerPage();
                break;
            case "3":
                doShowByCatalog();
                break;
            case "4":
                doSearch();
                break;
            case "5":
                doModify();
                backorexit();
                break;
            case "6":
                doDelete();
                backorexit();
                break;
            case "7":
                doAddContact();
                backorexit();
                break;
            case "8":
                doAddCatalog();
                backorexit();
                break;
            case "9":
                doShowAllCatalog();
                break;
            case "10":
                doSetDisplayField();
                confupdate();
                break;
            case "11":
                doSetViewPerpage();
                confupdate();
                backorexit();
                break;
            case "12":
                doSetOrder();
                confupdate();
                break;
            case "13":
                dosetsort();
                confupdate();
                backorexit();
                break;
            case "14":
                doshowr();
                backorexit();
                break;
            case "15":
                doOpt();
                backorexit();
                break;
            case "16":
                doshowacc();
                backorexit();
                break;
            case "17":
                doaddacc();
                backorexit();
                break;
            case "18":
                dodelete();
                backorexit();
                break;
            case "19":
                domodacc();
                backorexit();
                break;
            case "20":
                dologout();
                break;
            case "99":
                sc.close();
                System.exit(0);
                break;
            default:
                System.out.println("Error_wrong_command");
                System.out.println("Please_enter_again:");
                doMainMenu(false);
                break;

        }

    }

    // 把CAT資料存進LIST
    public static void catalogs() {
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
    public static void datas() {
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

    // 建立一個能得到定義好的comparator
    public static Comparator<Man> getcomp() {

        Comparator<Man> comp = new Comparator<Man>() {
            @Override
            public int compare(Man one, Man two) {
                int direc = 1;
                if (conf[1].equals("asc"))
                    ;
                else
                    direc = -1;
                if (conf[9].equals("id"))
                    return direc * Integer.valueOf(one.getId()).compareTo(Integer.valueOf(two.getId()));
                else {
                    if (one.getvalue(conf[9]).equals(two.getvalue(conf[9])))
                        return direc * Integer.valueOf(one.getId()).compareTo(Integer.valueOf(two.getId()));
                    else
                        return direc * one.getvalue(conf[9]).compareTo(two.getvalue(conf[9]));
                }
            }

        };
        return comp;
    }

    // 印出資料
    public static void show(Man a, String b) {
        if (b.equals("Condition")) {
            System.out.println(a.idformatString() + a.nameformatString(Boolean.parseBoolean(conf[3]))
                    + a.phoneformatString(Boolean.parseBoolean(conf[2]))
                    + a.catalogformatString(Boolean.parseBoolean(conf[5]))
                    + a.emailformatString(Boolean.parseBoolean(conf[6]))
                    + a.birthformatString(Boolean.parseBoolean(conf[4])));
        } else {
            System.out.println(a.idformatString() + a.nameformatString(true)
                    + a.phoneformatString(true)
                    + a.catalogformatString(true)
                    + a.emailformatString(true)
                    + a.birthformatString(true));
        }

    }

    // 1.建一個list去依據順序或反序輸出
    public static void doShowAll() {
        System.out.print(getPeopleTitle(Boolean.parseBoolean(conf[3]), Boolean.parseBoolean(conf[2]),
                Boolean.parseBoolean(conf[5]),
                Boolean.parseBoolean(conf[6]), Boolean.parseBoolean(conf[4])));
        List<Man> newman = new ArrayList<>(man);
        Collections.sort(newman, getcomp());
        for (Man a : newman) {
            show(a, "Condition");
        }

    }

    // 2
    public static void doShowPerPage() {
        System.out.println("Choose_show_per_page:");
        System.out.println("[3].3_data_per_page [5].5_data_per_page [10].10_data_per_page");
        System.out.println("[d].default [0].Go_back_to_main_menu [99].Exit_system");
        String a = sc.nextLine();
        String choice;
        boolean restart = true;
        List<Man> newman = new ArrayList<>(man) ;
        Collections.sort(newman,getcomp());
        while (restart) {
            int now = 0;
            int count = 0 ;
            if (a.equals("3") || a.equals("5") || a.equals("10")) {
                if(Integer.valueOf(a)>=newman.size())
                {
                    for(Man m :newman)
                    {
                        show(m,"Condition") ;
                        
                    }
                    System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
                    String be = sc.nextLine() ;
                    if(be.equals("0"))
                    return ;
                    else
                    System.exit(0);
                }
                
                while (now < newman.size()) {
                    show(newman.get(now), "Condition");
                    now++;
                    count++ ;
                    if (now == Integer.valueOf(a) && now < newman.size()) {
                        System.out.println("[2].Next_page [0].Go_back_to_main_menu [99].Exit_system");
                        choice = sc.nextLine();
                        if (choice.equals("2"))
                        {   count = 0 ;
                            continue;
                        }
                        else if (choice.equals("0"))
                            return;
                        else
                            System.exit(0);
                    } else if (count % Integer.valueOf(a) == 0 && now < newman.size()) {
                        System.out.println("[1].Last_page [2].Next_page [0].Go_back_to_main_menu [99].Exit_system");
                        choice = sc.nextLine();
                        if (choice.equals("2"))
                            {   count = 0 ;
                                continue;}
                        else if (choice.equals("1")) {
                            {now -= (Integer.valueOf(a)*2) ;
                             count = 0 ;   
                            }
                        } else if (choice.equals("0"))
                            return;
                        else
                            System.exit(0);
                    } else if(now==newman.size())
                        {
                            System.out.println("[1].Last_page [0].Go_back_to_main_menu [99].Exit_system");
                        choice = sc.nextLine();
                         if (choice.equals("1")) {
                            {now -= (Integer.valueOf(a)+count) ;
                                count = 0 ;
                            }
                        } else if (choice.equals("0"))
                            return;
                        else
                            System.exit(0); 
                        }
                    else
                    ;

                }

            } else if (a.equals("d")) {
                if(Integer.valueOf(conf[7])>=newman.size())
                {
                    for(Man m :newman)
                    {
                        show(m,"Condition") ;
                        
                    }
                    System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
                    String be = sc.nextLine() ;
                    if(be.equals("0"))
                    return ;
                    else
                    System.exit(0);
                }
                while (now < man.size()) {
                    show(newman.get(now), "Condition");
                    now++;
                    count++ ;
                    if (now == Integer.valueOf(conf[7]) && now < newman.size()) {
                        System.out.println("[2].Next_page [0].Go_back_to_main_menu [99].Exit_system");
                        choice = sc.nextLine();
                        if (choice.equals("2"))
                           {count = 0 ; 
                            continue;}
                        else if (choice.equals("0"))
                            return;
                        else
                            System.exit(0);
                    } else if (count % Integer.valueOf(conf[7]) == 0 && now< newman.size()) {
                        System.out.println("[1].Last_page [2].Next_page [0].Go_back_to_main_menu [99].Exit_system");
                        choice = sc.nextLine();
                        if (choice.equals("2"))
                            {   count = 0 ;
                                continue;}
                        else if (choice.equals("1")) {
                           { now -= (Integer.valueOf(conf[7])*2) ;
                            count = 0  ;
                           }
                        } else if (choice.equals("0"))
                            return;
                        else
                            System.exit(0);
                    } else if(now==newman.size())
                        {
                            System.out.println("[1].Last_page [0].Go_back_to_main_menu [99].Exit_system");
                        choice = sc.nextLine();
                         if (choice.equals("1")) {
                            {now -= (Integer.valueOf(conf[7])+count) ;
                            count = 0 ;
                            }
                        } else if (choice.equals("0"))
                            return;
                        else
                            System.exit(0); 
                        }
                    else
                    ;
                }

            } else if (a.equals("0"))
                return;
            else
                System.exit(0);
            System.out.println("[1].Restart_search [0].Go_back_to_main_menu [99].Exit_system");
            a = sc.nextLine();
            if (a.equals("1")) {
                System.out.println("Choose_show_per_page:");
                System.out.println("[3].3_data_per_page [5].5_data_per_page [10].10_data_per_page");
                System.out.println("[d].default [0].Go_back_to_main_menu [99].Exit_system");
                a = sc.nextLine();
            } else if (a.equals("0"))
                restart = false;
            else
                System.exit(0);
        }
    }

    // 3
    public static void doShowByCatalog() {
        System.out.print("Catalog:");
        char list = 'a';
        for (String a : catalog) {
            System.out.print("[" + list + "]" + a + " ");
            list += 1;
        }
        System.out.println("\n[0].Go_back_to_main_menu [99].Exit_system");
        System.out.println("Input_catalog_to_show:");
        String cat;
        while (true) {
            cat = sc.nextLine();
            if (!cat.equals("0") && !cat.equals("99") && !(cat.charAt(0) < list)) {
                System.out.println("Error_wrong_data\n" + "Please_input_again:");
            } else
                break;
        }
        if (cat.equals("0"))
            return;
        else if (cat.equals("99"))
            System.exit(0);
        else {
            int index = cat.charAt(0) - 'a';
            System.out.print(getPeopleTitle(Boolean.parseBoolean(conf[3]), Boolean.parseBoolean(conf[2]),
                    Boolean.parseBoolean(conf[5]),
                    Boolean.parseBoolean(conf[6]), Boolean.parseBoolean(conf[4])));
            List<Man> newman = new ArrayList<>(man);
            Collections.sort(newman, getcomp());
            for (Man m : newman) {

                if (m.getCatalog().equals(catalog.get(index)))
                    show(m, "Condition");
            }
        }
        backorexit();
    }

    // 4
    public static void doSearch() {
        List<Man> result = new ArrayList<>();
        String b;
        Boolean restart = true;
        boolean find = false;
        while (restart) {
            System.out.println("Search by:\n" +
                    "[1].ID [2].Name [3].Birthday \n" +
                    "[0].Go_back_to_main_menu [99].Exit_system");
            String a = sc.nextLine();
            if (a.equals("1") || a.equals("2") || a.equals("3")) {
                if (a.equals("1")) {
                    System.out.println("Input_target:");
                    while (true) {
                        b = sc.nextLine();
                        if (!b.matches("[0-9]{4}")) {
                            System.out.println("Error_wrong_data\nPlease_input_again:");
                        } else
                            break;
                    }
                    for (Man x : man) {
                        if (Integer.valueOf(b) == Integer.valueOf(x.getId())) {
                            result.add(x);
                            find = true;
                        }
                    }
                } else if (a.equals("2")) {
                    System.out.println("Input_target:");
                    while (true) {
                        b = sc.nextLine();
                        if (!b.matches("[^0-9]{1,12}")) {
                            System.out.println("Error_wrong_data\nPlease_input_again:");
                        } else
                            break;
                    }
                    for (Man x : man) {
                        if (b.equals(x.getName())) {
                            result.add(x);
                            find = true;
                        }
                    }
                } else {
                    System.out.println("Input_target:");
                    while (true) {
                        b = sc.nextLine();
                        if (b.matches("([0][13578]|[1][0]|[1][2])([0][1-9]|[1][0-9]|[2][0-9]|[3][0-1])")
                                || b.matches("([0][469]|[1][1])([0][1-9]|[1][0-9]|[2][0-9]|[3][0])")
                                || b.matches("([0][2])([0][1-9]|[1][0-9]|[2][1-9])")) {
                            break;
                        } else
                            System.out.println("Error_wrong_data\nPlease_input_again:");
                    }
                    for (Man x : man) {
                        if (b.equals(x.getBirth())) {
                            result.add(x);
                            find = true;
                        }
                    }
                }
                if (!find)
                    System.out.println("Error_no_result");
                else {
                    System.out.print(getPeopleTitle(find, find, find, find, find));
                    for (Man y : result) {
                        show(y, "All");
                    }
                    result.clear();
                }
                System.out.println("[1].Restart_search [0].Go_back_to_main_menu [99].Exit_system");
                find = false;
                String r = sc.nextLine();
                if (r.equals("1"))
                    ;
                else if (r.equals("0"))
                    restart = false;
                else
                    System.exit(0);
            }

            else if (a.equals("0")) {
                restart = false;
            } else if (a.equals("99")) {
                System.exit(0);
            } else {
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }

        }
    }

    // 讀檔案內的十種資料存到conf的陣列內
    public static void config(String[] conf)

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

    // 標題的輸出與判斷
    public static String getPeopleTitle(boolean show_name, boolean show_phone, boolean show_catalog, boolean show_email,
            boolean show_birthday) {
        String title = "[ID] ";
        if (show_name) {
            {
                String a = "[Name]" + "                       ";
                title += a.substring(0, 12) + " ";
            }
        }
        if (show_phone) {
            String a = "[Phone]" + "                       ";
            title += a.substring(0, 11) + " ";
        }
        if (show_catalog) {
            String a = "[Catalog]" + "                       ";
            title += a.substring(0, 12) + " ";
        }
        if (show_email) {
            String a = "[Email]" + "                       ";
            title += a.substring(0, 24) + " ";
        }
        if (show_birthday) {
            String a = "[BD]" + "                       ";
            title += a.substring(0, 4) + "\n";
        }
        return title;

    }

    // 更新myconfig.txt檔案
    public static void confupdate() {
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

    // 更新catalog.txt檔案
    public static void updatecatalog() {
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

    // 5
    public static void doModify() {
        String id;
        String name;
        String phone;
        String cat;
        String email;
        String birth;
        int index = -1;
        System.out.println("Input_ID_to_be_modified:");
        id = sc.nextLine();
        id = sc.nextLine();
        for (Man m : man) {
            if (Integer.valueOf(id) == Integer.valueOf(m.getId())) {
                index = man.indexOf(m);
            }
        }
        System.out.println("New_name:");
        name = sc.nextLine();
        if (name.isEmpty())
            ;
        else
            man.get(index).setName(name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
        System.out.println("New_phone:");
        phone = sc.nextLine();
        if (phone.isEmpty())
            ;
        else
            man.get(index).setPhone(phone);
        System.out.print("Catalog:");
        char list = 'a';
        for (String a : catalog) {
            System.out.print("[" + list + "]" + a + " ");
            list += 1;
        }
        System.out.println("\nCatalog:");
        cat = sc.nextLine();

        if (cat.isEmpty())
            ;
        else {
            int newcat = cat.charAt(0) - 'a';
            man.get(index).setCatalog(catalog.get(newcat));
        }
        System.out.println("New_email:");
        email = sc.nextLine();
        if (email.isEmpty())
            ;
        else
            man.get(index).setEmail(email);
        System.out.println("New_birthday:");
        birth = sc.nextLine();
        if (birth.isEmpty())
            ;
        else
            man.get(index).setBirth(birth);
        System.out.println("Modify_data_success");
    }

    // 6
    public static void doDelete() {
        boolean find = false;
        System.out.println("Input_ID_to_be_deleted:");
        String a = sc.nextLine().trim();
        int index = -1;
        for (Man m : man) {
            if (Integer.valueOf(a) == Integer.valueOf(m.getId())) {
                index = man.indexOf(m);
                find = true;
            }

        }
        if (find) {
            man.remove(index);
            System.out.println("Delete_data_success");
        } else
            System.out.println("Error_no_such_data");
    }

    // 7
    public static void doAddContact() {
        String name;
        String phone;
        String cat;
        String email;
        String birth;

        System.out.println("Name:");
        while (true) {
            name = sc.nextLine();
            if (name.matches("[^0-9]{1,12}")) {
                name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
                break;
            } else {
                System.out.println("Error_wrong_data\n" + "Please_input_again:");
            }

        }
        System.out.println("Phone_number:");
        while (true) {
            phone = sc.nextLine();
            if (phone.matches("[0-9]{4}-[0-9]{6}"))
                break;
            else {
                System.out.println("Error_wrong_data\n" + "Please_input_again:");
            }
        }

        System.out.print("Catalog:");
        char index = 'a';
        for (String a : catalog) {
            System.out.print("[" + index + "]" + a + " ");
            index += 1;
        }
        System.out.println("\nCatalog:");
        while (true) {
            cat = sc.nextLine().toLowerCase();
            if (cat.matches("[a-z]{1}")) {
                int num = cat.charAt(0) - 'a';
                if (num < catalog.size()) {
                    cat = catalog.get(num);
                    break;
                } else
                    System.out.println("Error_wrong_data\n" + "Please_input_again:");
            } else {
                System.out.println("Error_wrong_data\n" + "Please_input_again:");
            }
        }

        System.out.println("Email:");
        while (true) {
            email = sc.nextLine();
            if (email.indexOf("@") != -1 && email.indexOf(".") != -1 && email.length() <= 24)
                break;
            else {
                System.out.println("Error_wrong_data\n" + "Please_input_again:");
            }
        }
        System.out.println("Birthday:");
        while (true) {
            birth = sc.nextLine();
            if (birth.matches("([0][13578]|[1][0]|[1][2])([0][1-9]|[1][0-9]|[2][0-9]|[3][0-1])")
                    || birth.matches("([0][469]|[1][1])([0][1-9]|[1][0-9]|[2][0-9]|[3][0])")
                    || birth.matches("([0][2])([0][1-9]|[1][0-9]|[2][1-9])"))
                break;
            else {
                System.out.println("Error_wrong_data\n" + "Please_input_again:");
            }
        }
        int a = 0;
        for (Man x : man) {
            a = Math.max(a, Integer.valueOf(x.getId()));
        }
        man.add(new Man(Integer.toString(a + 1), name, phone, cat, email, birth));
        System.out.println("Add_contact_success");
    }

    // 8
    public static void doAddCatalog() {
        int length = 12;
        boolean exist = false;
        String a;
        System.out.println("Please_input_new_catalog:");
        while (true) {
            a = sc.nextLine().trim();
            a = a.substring(0, 1).toUpperCase() + a.substring(1, a.length());
            if (a.length() <= length) {
                for (String x : catalog) {
                    if (x.equals(a)) {
                        exist = true;
                        break;
                    } else
                        ;
                }
                if (exist) {
                    System.out.println("Error_catalog_existed");
                    System.out.println("Please_input_new_catalog:");
                } else {

                    catalog.add(a);
                    System.out.println("Add_catalog_" + a + "_success");
                    updatecatalog();
                    break;
                }
            } else {
                System.out.println("Error_catalog_too_long");
                System.out.println("Please_input_new_catalog:");
            }
        }
    }

    // 9
    public static void doShowAllCatalog() {
        System.out.println("[Catalog]");
        for (String temp : catalog) {
            System.out.println(temp);
        }
    }

    // 10
    public static void doSetDisplayField() {
        String in;
        System.out.println(
                "[1].Show_name:" + stoi(conf[3]) + " " + "[2].Show_phone:" + stoi(conf[2]) + " " + "[3].Show_cat:"
                        + stoi(conf[5]) + " " + "[4].Show_cat:" + stoi(conf[6]) + " " + "[5].Show_bd:" + stoi(conf[4]));
        System.out.print("New_show_name(0/1):");
        in = sc.nextLine();
        conf[3] = stos(in) ;
        System.out.print("New_show_phone(0/1):");
        in = sc.nextLine();
        conf[2] = stos(in) ;
        System.out.print("New_show_cat(0/1):");
        in = sc.nextLine();
        conf[5] = stos(in);
        System.out.print("New_show_email(0/1):");
        in = sc.nextLine();
        conf[6] = stos(in) ;
        System.out.print("New_show_bd(0/1):");
        in = sc.nextLine();
        conf[4] = stos(in) ;
        System.out.println();
    }

    // 10-1 stringto int
    public static int stoi(String a) {
        return (a.equals("true") ? 1 : 0);
    }
    //10-2 STRING 1/0 TO String true false
    public static String stos(String a){
        return (a.equals("0")?"false":"true") ;
    }

    // 11
    public static void doSetViewPerpage() {
        System.out.println("show_defalt_perpage:" + conf[7]);
        System.out.println("new_show_defalt_perpage:");
        conf[7] = sc.nextLine();
        System.out.println("show_defalt_perpage:" + conf[7]);
    }

    // 12.
    public static void doSetOrder() {
        System.out.println("show_sort_order:" + conf[1]);
        System.out.println("Please_input_new_sort_order:");
        while (true) {
            String a = sc.nextLine().trim();
            if (a.equals("asc") || a.equals("des")) {
                conf[1] = a;
                break;
            } else
                System.out.println("Input_error_plaese_input_asc_or_des:");
        }
        System.out.println("show_sort_order:" + conf[1]);
    }

    // 13
    public static void dosetsort() {
        System.out.println(
                "[1].ID [2].Name [3].Phone [4].Cat [5].Email [6].Bd\n[0].Go_back_to_main_menu [99].Exit_system");
        String a = sc.nextLine();
        while (!a.matches("[0-6]|99")) {
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
            a = sc.nextLine();
        }
        switch (a) {
            case "1":
                System.out.println("Sorted_by:ID");
                conf[9] = "id";
                break;
            case "2":
                System.out.println("Sorted_by:Name");
                conf[9] = "name";
                break;
            case "3":
                System.out.println("Sorted_by:Phone");
                conf[9] = "phone";
                break;
            case "4":
                System.out.println("Sorted_by:Cat");
                conf[9] = "catalog";
                break;
            case "5":
                System.out.println("Sorted_by:Email");
                conf[9] = "email";
                break;
            case "6":
                System.out.println("Sorted_by:Bd");
                conf[9] = "birth";
                break;
            case "0":
                break;
            default:
                System.exit(0);

        }
    }

    // 14
    public static void doshowr() {

        try {
            System.out.print(getPeopleTitle(true, true, true, true, true));
            Scanner s = new Scanner(new File("data.txt"));
            while (s.hasNextLine()) {
                String a = s.nextLine();
                Man b = new Man(a);
                show(b, "All");
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // 15
    public static void doOpt() {
        System.out.println("Please_confirm_data_optimize_y_or_n:");
        String a = sc.nextLine();
        while (!a.matches("y|n")) {
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
            a = sc.nextLine();
        }
        if (a.equals("y")) {
            try {
                FileWriter f = new FileWriter("test.txt");
                List<Man> newman = new ArrayList<>(man);
                Collections.sort(newman, getcomp());
                f.write(getPeopleTitle(Boolean.parseBoolean(conf[3]), Boolean.parseBoolean(conf[2]),
                        Boolean.parseBoolean(conf[5]),
                        Boolean.parseBoolean(conf[6]), Boolean.parseBoolean(conf[4])));
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
        } else
        System.out.println("Data_optimize_denied");
    }

    // 16.
    public static void doshowacc() {

        System.out.println("[Account]    [Password]");
        for (Accout a : acc) {
            System.out.println(a.accountformatString() + a.passwordformatString());
        }
    }

    // 17
    public static void doaddacc() {
        int acclen = 100;
        int passlen = 100;
        String a;
        String b;
        System.out.println("New_account:");
        while (true) {
            a = sc.nextLine().trim();
            if (a.length() <= acclen)
                break;
            else
                System.out.println("Too_long_please_try_again:");
        }
        System.out.println("New_password:");
        while (true) {
            b = sc.nextLine().trim();
            if (b.length() <= passlen)
                break;
            else
                System.out.println("Too_long_please_try_again:");
        }
        acc.add(new Accout(a, b));
        updateacc();
    }

    public static void updateacc() {
        try {
            FileWriter w = new FileWriter("account.txt");

            for (Accout ac : acc) {
                w.write(ac.getAccount() + "\n" + ac.getPassword() + "\n");
            }
            w.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // 18
    public static void dodelete() {
        System.out.println("Delete_account:");
        while (true) {
            String b = sc.nextLine();
            for (Accout c : acc) {

                if (c.getAccount().equals(b)) {
                    acc.remove(c);
                    System.out.println("Delete_account_success");
                    updateacc();
                    return;
                }
            }
            System.out.println("No_account_please_try_again:");
        }
    }

    // 19
    public static void domodacc() {
        System.out.println("Modify_account:");
        int index = -1;
        boolean find = false;
        while (!find) {
            String target = sc.nextLine();
            for (Accout a : acc) {
                if (a.getAccount().equals(target)) {
                    index = acc.indexOf(a);
                    find = true;
                }
            }
            if (!find)
                System.out.println("No_account_please_try_again:");
        }
        int acclen = 100;
        int passlen = 100;
        String a = sc.nextLine();
        System.out.println("New_account:");
        while (true) {
            a = sc.nextLine().trim();
            if (a.isEmpty())
                break;
            else if (a.length() <= acclen) {
                acc.get(index).setAccount(a);
                break;
            } else
                System.out.println("Too_long_please_try_again:");
        }
        System.out.println("New_password");
        while (true) {
            a = sc.nextLine().trim();
            if (a.isEmpty())
                break;
            else if (a.length() <= passlen) {
                acc.get(index).setPassword(a);
                break;
            } else
                System.out.println("Too_long_please_try_again:");
        }
        System.out.println("Modify_account_success");
        updateacc();
    }

    // 20
    public static void dologout() {
        System.out.println("Please_confirm_to_logout_y_or_n:");

        String a = sc.nextLine() ;
        while (!a.equals("y") && !a.equals("n")) {
           
            System.out.println("Error_input");
            System.out.println("Please_input_again:");
            a = sc.nextLine() ;
        }
        if (a.equals("y"))
            login();
        else
            ;
    }

}
