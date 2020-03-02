package exceptions;


abstract public class HearthstoneException extends Exception {
    HearthstoneException(){
        super();
    }
    HearthstoneException(String s){
        super(s);
    }
}
