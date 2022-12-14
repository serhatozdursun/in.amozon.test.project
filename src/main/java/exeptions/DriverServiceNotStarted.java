package exeptions;

public class DriverServiceNotStarted extends RuntimeException{

    final String message;

    public DriverServiceNotStarted(String browser,String message) {

        this.message = String.format("%s driver service couldn't started:\n%s",browser,message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
