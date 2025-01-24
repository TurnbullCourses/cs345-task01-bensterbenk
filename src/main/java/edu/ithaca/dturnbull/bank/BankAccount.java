package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        else {
            String[] splitMail=email.split("@");
            String prefix=splitMail[0];
            String end=splitMail[1];
            if(splitMail[0]==""){
                return false;
            }
            if(prefix.charAt(0)=='-' || prefix.charAt(0)=='.'){
                return false;
            }
            if(prefix.charAt(prefix.length()-1)=='-' || prefix.charAt(prefix.length()-1)=='.'){
                return false;
            }
            for(int i=0;i<prefix.length();i++){
                if(prefix.charAt(i)=='.'){
                    if(prefix.charAt(i+1)=='.'){
                        return false;
                    }
                }
                if(prefix.charAt(i)=='#'){
                    return false;
                }
            }
            int counter=0;
            int index=-1;
            for(int i=0;i<end.length();i++){
                if(end.charAt(i)=='.'){
                    if(end.charAt(i+1)=='.'){
                        return false;
                    }
                    counter++;
                    index=i;
                }
                if(end.charAt(i)=='#'){
                    return false;
                }
            }
            if(counter==0){
                return false;
            }
            String address=end.substring(index);
            if(address.equals(".com") || address.equals(".org") || address.equals(".cc")){
                return true;
            }
            return false;
        }
    }
}