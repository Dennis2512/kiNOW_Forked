package com.example.demo;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.net.URL;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lukas.java_classes.Film;
import lukas.java_classes.Nutzer;
import lukas.java_classes.Parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

  static Firestore db;
  static Parser ps;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
    try {
      //Pfad muss angepasst werden ggf. in Java einfügen
      String path = "serviceAccountKey.json";
      URL url = DemoApplication.class.getClassLoader().getResource(path);
      //Datenbankverbindung erstellen
      FileInputStream serviceAccount =
          new FileInputStream(url.getPath());

      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl("https://kinow-46514.firebaseio.com")
          .build();

      FirebaseApp.initializeApp(options);
    } catch (Exception e) {
      e.printStackTrace();
    }//catch
    db = FirestoreClient.getFirestore();
    SimpleController sc = new SimpleController();
    sc.lukasTest();

  }//main

  @RestController
  public static class SimpleController {

    /**
    @RequestMapping(value = "/Person")
    public ResponseEntity<Nutzer> getPerson() {
      return new ResponseEntity<Nutzer>(
          new Nutzer(1, "Hans", "peter", new Date(), "email@email.com", "1234"), HttpStatus.OK);
    }

    @RequestMapping(value = "/Kino")
    public ResponseEntity<Object> getKino(@RequestHeader("kinoID") int kino) {
      Kino k = new Kino(kino, "HAns", "Pad");
      Map<Integer, Kino> KinoRepo = new HashMap<>();
      KinoRepo.put(k.getKinoID(), k);
      return new ResponseEntity<>(KinoRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/KinoBody")
    public ResponseEntity<Object> getKino(@RequestBody() String kino) {
      Gson gson = new Gson();
      Map<Integer, Kino> KinoRepo = new HashMap<>();
      try {
        Kino kino1 = gson.fromJson(kino, Kino.class);
        KinoRepo.put(kino1.getKinoID(), kino1);
        return new ResponseEntity<>(KinoRepo.values(), HttpStatus.OK);
      } catch (Exception e) {
      }
      return new ResponseEntity<>(KinoRepo.values(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/setNutzer")
    public void setData() {
      ApiFuture<QuerySnapshot> query = db.collection("Nutzer").get();
      DocumentReference docRef = db.collection("Nutzer").document();
      QuerySnapshot querySnapshot = null;
      try {
        int nutzerID;
        querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        nutzerID = documents.size() + 1;
        Nutzer n = new Nutzer(nutzerID, "Hans", "Peter", new Date(), "Waveboardlukas@Sand.de", "1234");
        //Nutzer nutzer=gson.fromJson(body,Nutzer.class);
        ApiFuture<WriteResult> result = docRef.set(n);
        System.out.println(result.toString());
      } catch (InterruptedException e) {
        System.out.println("InterruptException");
        e.printStackTrace();
      } catch (ExecutionException e) {
        System.out.println("Exception");
        e.printStackTrace();
      }

    }//setNutzer

     **/

    @RequestMapping(value = "/lukasTest")
    public void lukasTest (){
      ApiFuture<QuerySnapshot> query = db.collection("Nutzer").get();
      QuerySnapshot querySnapshot = null;
      try {
        int filmID = 1;
        Film film =  new Film();
        String eintrag = "" + film;
        DocumentReference docRef = db.collection("Filme").document(eintrag);
        ApiFuture<WriteResult> result = docRef.set(film);
      } catch (Exception e){
        e.printStackTrace();
      }
    }//lukasTest


    @RequestMapping(value = "/getAllData")
    public ResponseEntity<Object> getAllData(@RequestHeader("head") String head) {
      // asynchronously retrieve all documents of an collection
      ApiFuture<QuerySnapshot> query = db.collection(head).get();
      Map<String, Object> data = new HashMap<>();
// ...
// query.get() blocks on response
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
          data.put(document.getId(), document.getData());
        }//for
      } catch (Exception e) {
      }//catch

      return new ResponseEntity<>(data.values(), HttpStatus.ACCEPTED);
    }//getAllData

    /**
    @RequestMapping(value = "/getNutzerbyName")
    public ResponseEntity<ArrayList> getNutzer(@RequestHeader("name") String name) {
      // asynchronously retrieve all users
      Query query = db.collection("Nutzer").whereEqualTo("nachname", name);
      ApiFuture<QuerySnapshot> querySnapshot = query.get();
      ArrayList<Nutzer> list = new ArrayList<>();
      try {
        Nutzer nutzer = new Nutzer();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
          nutzer = document.toObject(Nutzer.class);
          list.add(nutzer);
        }
// ...
// query.get() blocks on response
        return new ResponseEntity<ArrayList>(list, HttpStatus.ACCEPTED);
      } catch (Exception e) {
      }
      return new ResponseEntity<ArrayList>(list, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getNutzerbyID")
    public ResponseEntity<ArrayList> getNutzerbyID(@RequestHeader("id") int id) {
      // asynchronously retrieve all users
      Query query = db.collection("Nutzer").whereEqualTo("nid", id);
      ApiFuture<QuerySnapshot> querySnapshot = query.get();
      ArrayList<Nutzer> list = new ArrayList<>();
      try {
        Nutzer nutzer = new Nutzer();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
          nutzer = document.toObject(Nutzer.class);
          list.add(nutzer);
        }
// ...
// query.get() blocks on response
        System.out.println("Anfrage von id:" + id);
        return new ResponseEntity<ArrayList>(list, HttpStatus.ACCEPTED);
      } catch (Exception e) {
      }
      return new ResponseEntity<ArrayList>(list, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getFilmInKino")
    public ResponseEntity<ArrayList> getFilmInKino(@RequestHeader("kino") String kino) {
      // asynchronously retrieve all users
      Query query = db.collection("Filme").whereEqualTo("nachname", kino);
      ApiFuture<QuerySnapshot> querySnapshot = query.get();
      ArrayList<Nutzer> list = new ArrayList<>();
      try {
        Nutzer nutzer = new Nutzer();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
          nutzer = document.toObject(Nutzer.class);
          list.add(nutzer);
        }
// ...
// query.get() blocks on response
        return new ResponseEntity<ArrayList>(list, HttpStatus.ACCEPTED);
      } catch (Exception e) {
      }
      return new ResponseEntity<ArrayList>(list, HttpStatus.BAD_REQUEST);

    }//getFilmKino
    **/

  }//Controller
}//class
