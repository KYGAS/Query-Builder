package app;

import database.Database;
import database.DatabaseImplementation;
import database.MSSQLrepository;
import database.settings.Settings;
import database.settings.SettingsImplementation;
import observer.Notification;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImplementation;
import utils.Constants;

public class AppCore extends PublisherImplementation {

    private Database database;
    private Settings settings;


    public AppCore() {
        this.settings = initSettings();
        this.database = new DatabaseImplementation(new MSSQLrepository(this.settings));
    }

    private Settings initSettings() {
        Settings settingsImplementation = new SettingsImplementation();
        settingsImplementation.addParameter("mssql_ip", Constants.MSSQL_IP);
        settingsImplementation.addParameter("mssql_database", Constants.MSSQL_DATABASE);
        settingsImplementation.addParameter("mssql_username", Constants.MSSQL_USERNAME);
        settingsImplementation.addParameter("mssql_password", Constants.MSSQL_PASSWORD);
        return settingsImplementation;
    }

    public void readDataFromTable(String fromTable){

        //Zasto ova linija moze da ostane zakomentarisana?
        this.notifySubscribers(new Notification(NotificationCode.DATA_UPDATED, this.database.readDataFromTable(fromTable)));
    }

    public void readDataFromQuery(String fromTable, String query) {

        //Zasto ova linija moze da ostane zakomentarisana?
        this.notifySubscribers(new Notification(NotificationCode.DATA_UPDATED, this.database.readDataFromQuery(fromTable, query)));
    }

}
