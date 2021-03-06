package backend.classes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;

public class Nutzer implements Serializable {

  private long nutzerID;
  private String email,vorname,nachname,geschlecht,geburtstag;
  private String passwort;
  private ArrayList<String> zahlungsmethoden;
  private ArrayList<Bestellung> bestellungen;

  public Nutzer (){}

  public Nutzer (long nutzerID, String email, String vorname, String nachname,String geburtstag, String geschlecht,String passwort, ArrayList<String> zahlungsmethoden, ArrayList<Bestellung> bestellungen){
    this.nutzerID = nutzerID;
    this.vorname = vorname;
    this.nachname = nachname;
    this.email = email;
    this.geburtstag = geburtstag;
    this.geschlecht = geschlecht;
    this.passwort = passwort;
    this.zahlungsmethoden = zahlungsmethoden;
    this.bestellungen = bestellungen;
  }//K

  public long getNutzerID() {
    return nutzerID;
  }

  public void setNutzerID(long nutzerID) {
    this.nutzerID = nutzerID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  public String getGeschlecht() {
    return geschlecht;
  }

  public void setGeschlecht(String geschlecht) {
    this.geschlecht = geschlecht;
  }

  public String getGeburtstag() {
    return geburtstag;
  }

  public void setGeburtstag(String geburtstag) {
    this.geburtstag = geburtstag;
  }

  public String getPasswort() {
    return passwort;
  }

  public void setPasswort(String passwort) {
    this.passwort = passwort;
  }

  public ArrayList<String> getZahlungsmethoden() {
    return zahlungsmethoden;
  }

  public void setZahlungsmethoden(ArrayList<String> zahlungsmethoden) {
    this.zahlungsmethoden = zahlungsmethoden;
  }

  public ArrayList<Bestellung> getBestellungen() {
    return bestellungen;
  }

  public void setBestellungen(ArrayList<Bestellung> bestellungen) {
    this.bestellungen = bestellungen;
  }

  public Bestellung getBestellung (int bestellungsnummer){
    Bestellung erg = null;
    for (Bestellung bestellung : bestellungen){
      if (bestellung.getBesetellungsnummer().equals(bestellungsnummer))erg = bestellung;
    }//for
    return erg;
  }//getBestellungen

  public void addBestellung (Bestellung bestellung){
    if (!this.bestellungen.contains(bestellung))bestellungen.add(bestellung);
  }//addBestellung

  public boolean equals (Nutzer nutzer){
    return this.nutzerID == nutzer.getNutzerID();
  }//nutzer

  public String hashPasswordWithRSA(String pass){
    String cString = "";
    for (int i = 0;i<pass.length();i++){
      int cInt = (int)pass.charAt(i);
      cString = cString+cInt;
    }
    BigInteger m = new BigInteger(cString);
    BigInteger n = new BigInteger("504914405039895215503198398425544756986467776458303795911103534681442521322959162525324628706863371066095587683843432958314326766637789168238555684962020122862769749666219775097798179795106514947178943312661535392317713065932322894730811899823518375837413796689590803952589473156227988799810656180011429042102520722043921387092017498651110275869098954091941139606861740238767756029844169485290688705785810590296079172703030857917797252369569147266588576839878956431149833167547708473625883125136953051807884722782048332791871746662803459952930183069151672385080155559101603476799411736589621060187845894506450433654736297269736605692358341120135567261677900385063827449454101924843962160447855607668574389078411246820132982967861522691224592588862042540887682293129604768745557228283836335707754237773832132850462840787106022589701602224209075319714917708510775831776182541395739752773456713067895701892761369679305049117676192503760817005134053261852626499428728028795961627152605478710243967522805947140768286094370153727605765086495306706364306996290161210987459653052166776081846368147197324118109333944455424654062694352556735725895982045564995449263159042446889598868568655674352809432960796178618677894383616035935831373512683936633259605896800225711294891659972853986448266246377820295770249386020483925506369783958604516708287734600747946126992683220031718747357342611344362967464270258065480804817401860119855285945499477763965993700873461407866225912948638883039801846444644605737091319772082818225596594463915636404367951959861539111651267130712905499489730740509518098750593017264055132357440299353901129081212564780542004031142660560024274019931024313219238839699120829694640997500159127914831849868989633847719396069092034012491389426081867434688836611946538551863437550380363123519294370853228615508444684388012455065525457318861522381582518980714411768382275286485085534413506985422246033662736205229049106208091338829778909122237794049541819974531527675462840907091757135918192733376226386848655979731046769568144605862445097236692930411458397870697377844825244902218384334922774855116659366088810926149402226477669340833933913099487631988638628288284112379256788117425067054554108159920687070805354324924284514184242530753648855903470267917261134298141827922055809145668896609642325617373467226429013860420856734872781334236255843474381469674544259457574057379912877465938276406965031961377893431958597203135077240443344647699938553747539673952927");
    BigInteger e = new BigInteger("91336632679361461824742789657973964326492436168619990984611750825651299364752872691717075698957347460143763261814725339865413789276018937277652237834423067576879979668337661595063072298285974447604587070217592571411748117730591522843718228490856330569385678160160239531576814298463039674011739236551367878805");
    return m.modPow(e,n).longValue()+"";
  }

  public void set (String key, Object o){
    switch (key){
      case "nutzerID": this.nutzerID = Long.parseLong(o.toString());
        break;
      case "vorname": this.vorname = (String) o;
        break;
      case "nachname": this.nachname = (String) o;
        break;
      case "geschlecht": this.geschlecht = (String) o;
        break;
      case "geburtstag": this.geburtstag = (String) o;
        break;
      case "passwort": this.passwort = (String) o;
        break;
      case "email": this.email = (String) o;
        break;
      case "zahlungsmethoden": this.zahlungsmethoden = (ArrayList<String>) o;
        break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

  public String toMapString (){
    String erg = "{";
    if (email!=null)erg += "\"email\":\""+email+"\",";
    if (geburtstag!=null)erg += "\"geburtstag\":\""+geburtstag+"\",";
    if (geschlecht!=null)erg += "\"geschlecht\":\""+geschlecht+"\",";
    if (nachname!=null)erg+= "\"nachname\":\""+nachname+"\",";
    if (nutzerID>0)erg+= "\"nutzerID\":"+nutzerID+",";
    if (passwort!=null)erg += "\"passwort\":\""+passwort+"\",";
    if (vorname!=null)erg+= "\"vorname\":\""+vorname+"\",";
    if (zahlungsmethoden!=null){
      erg += "\"zahlungsmethoden\":[";
      for (String z : zahlungsmethoden){
        erg+= "\""+z+"\",";
      }//for
      erg = erg.substring(0,erg.lastIndexOf(','));
      erg += "],";
    }//then
    erg = erg.substring(0,erg.lastIndexOf(','));
    erg += "}";
    return erg;
  }//toMapString

}//class
