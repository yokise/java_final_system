public class Accout {
    
    public String account ; 

    public String password ;

    public Accout(String a , String b)
    {
        this.account = a ;
        this.password = b ;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String accountformatString() 
        
        {String a = this.account + "                       ";
        return a.substring(0,12) + " ";
    }
    public String passwordformatString() {
        
        {String a = this.password + "                       ";
        return a.substring(0,12) + " ";
    }
}
}
