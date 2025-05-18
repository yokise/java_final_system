

public class Man {
    
    public String id ;
    public String email ;
    public String catalog ;
    public String phone ;
    public String birth ; 
    public String name ;

public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
     public String getBirth() {
        return birth;
    }

    public void setBirth(String name) {
        this.birth = name;
    }

public Man(String a,String b,String c,String d,String e,String f){

   this.id = a; 
   this.name = b;  
    this.phone = c ; 
    this.catalog = d ; 
    this.email = e ; 
    this.birth = f ; 
}
public Man(String x)
{
    String[] tokens = x.split("\\s+");
    this.id = tokens[0].trim() ;
    this.name = tokens[1].trim();
    this.phone =  tokens[2].trim();
    this.catalog = tokens[3].trim() ;
    this.email = tokens[4].trim() ;
    this.birth = tokens[5].trim() ;
    
}
public  String idformatString() {
    String x = String.format("%4s", this.id).replace(" ","0") ;
    String a = x + "                       ";
    return a.substring(0,4) + " ";
}
public String nameformatString(boolean t) {
    if(t)
    {String a = this.name + "                       ";
    return a.substring(0,12) + " ";
}
else
return "" 
;}
public  String phoneformatString(boolean t ) {
    if(t)
    {String a = this.phone + "                       ";
    return a.substring(0,11) + " ";
}
else 
return ""
;}
public String catalogformatString(boolean t) {
    if(t)
    {String a = this.catalog + "                       ";
    return a.substring(0,12) + " ";
}
else return ""
;
}
public String emailformatString(boolean t) {
    if( t)
   { String a = this.email + "                       ";
    return a.substring(0,24) + " ";
}
else return ""
;
}
public  String birthformatString(boolean t) {
    if(t)
    {String a = this.birth + "                       ";
    return a.substring(0,4) + " ";
}
else return ""
;
}
public String getvalue(String a)
{
    String value ; 
    switch(a){

        case"id":
        value = getId() ;
        break;
        case"name":
        value = getName();
        break ;
        case"phone":
        value = getPhone() ;
        break ;
        case"catalog":
        value = getCatalog();
        break ;
        case"birth":
        value = getBirth();
        break ;
        case"email":
        value = getEmail() ;
        break ; 
        default:
        value = getId();
        break ;
    }
    return value ;
}
}
