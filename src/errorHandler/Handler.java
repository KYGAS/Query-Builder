package errorHandler;

import observer.Notification;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImplementation;

import java.util.ArrayList;

public class Handler extends PublisherImplementation {

    private static Handler instance = null;

    private ArrayList<String> errors = new ArrayList<>();

    private Handler() {

    }

    public static Handler getInstance(){
        if (instance==null){
            instance=new Handler();
        }
        return instance;
    }

    public void notifyErrorStack(){
        this.notifySubscribers(new Notification(NotificationCode.ERROR_STACK, this.errors));
        errors.clear();
    }

    public void addError(ErrorType e, String error, String valid){
        String err = "";

        switch (e){
            case NODATA:
                err += "Ocekivan kod.\n";
                break;
            case NONAME:
                err += "Ocekivano ime promenljive.\n";
                break;
            case NOOPERATION:
                err += "Ocekivana operacija dodele.\n";
                break;
            case NOALLOCATION:
                err += "Ocekivana alokacija memorije.\n";
                break;
            case NOQUERY:
                err += "Ocekivan zadat query.\n";
                break;
            case NOTAVALIDNAME:
                err += "Ime : " + error + " nije validno. Ime se moze sastojati " + valid + "\n";
                break;
            case NOTAVALIDTYPE:
                err += "Rec : " + error + " nije validan tip za deklaraciju. Da li ste mislili " + valid + "\n";
                break;//let/var/string?
            case NOTAVALIDOPERATION:
                err += "Simbol : " + error + " nije validan. Da li ste mislili " + valid + "\n";
                break;
            case NOTAVALIDKEYWORD:
                err += "Rec : " + error + " nije validna. Da li ste mislili " + valid + "\n";
                break;
            case NOTAFUNCTION:
                err += "Funkcija : " + error + " ne postoji. Za postojece funkcije, pogledajte dokumentaciju.";
                break;
            case INVALIDARGUMENTTYPE:
                err += "Funkciji : " + error + " je dat neispravan tip argumenta. Za tipove argumenata pogledajte dokumentaciju.";
                break;
            case INVALIDARGUMENTLENGTH:
                err += "Funkciji : " + error + " je data neispravna kolicina argumenata. Za kolicinu argumenata pogledajte dokumentaciju.";
                break;
            case EMPTYQUERY:
                err += "Vas Query <" + error + ">  nije vratio nikakve podatke. Proverite imena i/ili tipove kolona!";
                break;
            case UNHANDLED:
                err += "Greska nije dosada naisla. Prijavite developeru vas proces.\n" + error + "\n" + valid + "\n";
                break;
            default:
                err += "\n -NEOCEKIVANA GRESKA - \n" + error + "\n" + valid + "\n";
                break;
        }
        errors.add(err);
    }


}
