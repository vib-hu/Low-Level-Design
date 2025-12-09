package atm.services;

public class NetworkServiceFactory {
    public static NetworkService getVisaService(){
        return new VisaService();
    }

    public static NetworkService getMasterCardService(){
        return new MasterCardService();
    }
}
