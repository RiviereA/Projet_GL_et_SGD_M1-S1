/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.sgd.modele;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;
import static com.mongodb.client.model.Filters.where;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Set;
import org.bson.Document;

/**
 *
 * @author tf872254
 */
public class Modele extends Observable{
    
    private String zoneDeTexte;
    private ArrayList<String> listeJeux;
    final private GestionBD gbd;
    
    public Modele(GestionBD g){
        gbd = g;
        listeJeux = new ArrayList();
        zoneDeTexte = "Pas de jeu sélectionné";
    }
    
    public void setZoneDeTexte(String s){
        zoneDeTexte = s;
    }
    
    public String getZoneDeTexte(){
        return this.zoneDeTexte;
    }
    
    public ArrayList<String> getListeJeux(){
        return this.listeJeux;
    }
    
    public GestionBD getBD(){
        return this.gbd;
    }
    
    public void requeteBD(String categorie, String query){
          
        MongoCursor mc = null;
        char comp = 'a';

        if(!categorie.contains("note")) {
            //if (categorie.equals("date de parution")) categorie = "dateSortie";
            switch(categorie){
                case "nom":
                    mc = gbd.rechercheDocument("jeuxvideo", where("this.nom.contains(\""+query+"\");"));
                    break;
                case "categorie":
                    mc = gbd.rechercheDocument("jeuxvideo", eq(categorie, query));
                    break;
                case "serie":
                    mc = gbd.rechercheDocument("jeuxvideo", ne("serie", null));                    
                    break;
                case "pegi":
                    mc = gbd.rechercheDocument("jeuxvideo", where("this.pegi.contains(\""+query+"\");"));
                    break;
            }
        }
        else if(categorie.contains("<") ){
            categorie = "note";
            comp = '<';
            mc = gbd.rechercheDocument("jeuxvideo", ne("avis", null));
        }
        else if(categorie.contains(">") ){
            categorie = "note";
            comp = '>';
            mc = gbd.rechercheDocument("jeuxvideo", ne("avis", null));
        }
        else if(categorie.contains("=") ){
            categorie = "note";
            comp = '=';
            mc = gbd.rechercheDocument("jeuxvideo", ne("avis", null));
        }
        
        String tmp;
        while(mc.hasNext()){
            switch (categorie) {
                case "serie":
                    Document result = (Document) mc.next();
                    Document target = (Document) result.get("serie");
                    tmp = (String) target.get("nom");
                    if(tmp.contains(query)) listeJeux.add((String) result.get("nom"));
                    break;
                case "note":
                    try{
                        float somme = 0, moyenne, i;
                        int nb = 0;
                        Document avis = (Document) mc.next();
                        ArrayList<Document> alNote = (avis != null)? (ArrayList)avis.get("avis"): new ArrayList();
                        for(Document doc: alNote){
                            somme += Float.parseFloat((String) doc.get("note"));
                            nb++;
                        }

                        i = Float.parseFloat(query);
                    
                        moyenne = (nb > 0)? somme/nb : -1;
                        switch(comp){
                            case '<':
                                if(moyenne < i){
                                    tmp = (String) avis.get("nom");
                                    listeJeux.add(tmp);
                                }
                                break;
                            case '>':
                                if(moyenne > i){
                                    tmp = (String) avis.get("nom");
                                    listeJeux.add(tmp);
                                }
                                break;
                            case '=':
                                if(moyenne == i){
                                    tmp = (String) avis.get("nom");
                                    listeJeux.add(tmp);
                                }
                                break;
                        }
                    
                    }catch(NumberFormatException nfe){listeJeux.clear(); listeJeux.add("Mauvais argument");}
                    
                    break;
                default:
                    Document d = (Document) mc.next();
                    tmp = (String) d.get("nom");
                    listeJeux.add(tmp);
                    break;
            }
        }
        
        //this.setChanged();
        //this.notifyObservers();
        
    }
    
    public void afficherJeu(String nom){
        MongoCursor mc = gbd.rechercheDocument("jeuxvideo", eq("nom", nom));
        String tmp = "";
        ArrayList<String> alCat;
        ArrayList<Document> alAvis;
        double f, somme = 0, moyenne;
        int nbNotes = 0;
        
        while(mc.hasNext()){
            Document d = (Document) mc.next();
            tmp += "Nom du jeu: " + (String) d.get("nom") + "\n\t";
            
            Document serie = (Document) d.get("serie");
            tmp += (serie != null)? "De la série: " + serie.get("nom") + "\n\t" : "";
            
            alCat = (ArrayList) d.get("categorie");            
            tmp += "Catégorie: ";            
            for(String s: alCat){
                tmp += s + " - ";
            }            
            tmp += "\n\tPEGI: " + (String) d.get("pegi") + "\n\t";
            
            alAvis = (d.get("avis") != null)? (ArrayList) d.get("avis") : new ArrayList();
            for (Document avis : alAvis) {
                Document docUser = (Document) avis.get("utilisateur");
                tmp += "Avis de " + (String) docUser.get("pseudo") + ": \n\t\t";
                tmp += (String) avis.get("commentaire") + "\n\t\t";
                tmp += "Note de l'utilisateur: " + String.format("%.1f", avis.get("note")) + "\n\t";
                f = (double) avis.get("note");
                somme += f;
                nbNotes++;
            }
            moyenne = (nbNotes > 0)? somme/nbNotes : -1;
            tmp += (moyenne >= 0)? "Note moyenne: " + moyenne + "\n\t" : "Pas d'avis disponible";     
        }
        
        this.zoneDeTexte = tmp;
        
        this.setChanged();
        this.notifyObservers();
    }

    public void preparerRequete(boolean[] criteres, String[] conditions, ArrayList<String> listeCategorie, ArrayList<String> listeContenu) {
        
        String cat, cont;
        listeJeux.clear();
        
        for(int i = 0; i < criteres.length; i++){
            if(criteres[i]){
                cat = listeCategorie.get(0);
                cont = listeContenu.get(0);
                this.requeteBD(cat, cont);
                
                listeCategorie.remove(0);
                listeContenu.remove(0);
                
                if(conditions[i].equals("et")){
                    ArrayList<String> copie = new ArrayList();
                    Set<String> set = new HashSet<>();
                    for(String c : listeJeux){
                        if(!set.add(c)){
                            copie.add(c);
                        }
                    }

                    listeJeux = copie;                    
                }else{
                    Set<String> s = new LinkedHashSet(listeJeux);
                    listeJeux = new ArrayList(s);
                }
                
            }
        }
        
        this.setChanged();
        this.notifyObservers();        
    }    

    public void ajouterAvis(String jeu, float note, String avis, String pseudo, String mail) {
        this.gbd.miseAJour("jeuxvideo", jeu, note, avis, pseudo, mail);
    }
}
