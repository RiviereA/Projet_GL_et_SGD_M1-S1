/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.sgd.vueControleur;

import static com.mongodb.client.model.Filters.eq;
import fr.ufrsciencestech.sgd.modele.GestionBD;
import fr.ufrsciencestech.sgd.modele.Modele;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class AdminTest {
    
    private GestionBD gbd = new GestionBD("tf872254");
    private Modele m = new Modele(gbd);
    private static RechercheUtilisateur ru;
    private static Admin adm;
    
    private JButton btnRech;
    private JList listeJeu;
    
    private JTextField titre;
    private JTextField pegi;
    private JTextField categ;
    private JTextField jour;
    private JTextField mois;
    private JTextField annee;
    private JButton btnAjoutCateg;
    private JList listeCateg;
    private JButton btnVal;
    private JButton btnAnnul;
    
    @Before
    public void setUp() {
        this.ru = new RechercheUtilisateur(m);
        this.adm = new Admin(gbd);
        this.btnRech = (JButton)TestUtils.getChildNamed(ru, "btnRecherche");
        this.listeJeu = (JList)TestUtils.getChildNamed(ru, "listeJeu");
        
        this.titre = (JTextField)TestUtils.getChildNamed(adm, "titre");
        this.pegi = (JTextField)TestUtils.getChildNamed(adm, "pegi");
        this.categ = (JTextField)TestUtils.getChildNamed(adm, "categ");
        this.jour = (JTextField)TestUtils.getChildNamed(adm, "jour");
        this.mois = (JTextField)TestUtils.getChildNamed(adm, "mois");
        this.annee = (JTextField)TestUtils.getChildNamed(adm, "annee");
        this.listeCateg = (JList)TestUtils.getChildNamed(adm, "listeCateg");
        this.btnAjoutCateg = (JButton)TestUtils.getChildNamed(adm, "btnAjoutCateg");
        this.btnVal = (JButton)TestUtils.getChildNamed(adm, "btnVal");
        this.btnAnnul = (JButton)TestUtils.getChildNamed(adm, "btnAnnul");
    }

    @Test
    public void testAjoutCateg() {
        assertNotNull(categ);
        assertNotNull(btnAjoutCateg);
        assertNotNull(listeCateg);
        
        this.categ.setText("beta");
        this.btnAjoutCateg.doClick();
        
        assertTrue(this.listeCateg.getModel().getElementAt(this.listeCateg.getModel().getSize()-1).equals("beta"));
    }
    
    @Test
    public void testAnnuler() {
        assertNotNull(btnAnnul);
        
        this.btnAnnul.doClick();
        
        assertEquals(adm.isVisible(), false);
    }
    
    @Test
    public void testAjoutJeu() {
        assertNotNull(titre);
        assertNotNull(pegi);
        assertNotNull(categ);
        assertNotNull(jour);
        assertNotNull(mois);
        assertNotNull(annee);
        assertNotNull(btnAjoutCateg);
        assertNotNull(btnVal);
        
        this.titre.setText("test");
        this.pegi.setText("10");
        this.categ.setText("beta");
        this.btnAjoutCateg.doClick();
        this.categ.setText("solo");
        this.btnAjoutCateg.doClick();
        this.jour.setText("01");
        this.mois.setText("01");
        this.annee.setText("2019");
        this.btnVal.doClick();
        
        this.btnRech.doClick();
        assertTrue(this.listeJeu.getModel().getElementAt(this.listeJeu.getModel().getSize()-1).equals("test"));
        
        gbd.suppressionDocument("jeuxvideo", eq("nom", "test"));
    }
    
    @Test
    public void testPegiInc() {
        assertNotNull(titre);
        assertNotNull(pegi);
        assertNotNull(categ);
        assertNotNull(jour);
        assertNotNull(mois);
        assertNotNull(annee);
        assertNotNull(btnAjoutCateg);
        assertNotNull(btnVal);
        
        this.btnRech.doClick();
        int taille = this.listeJeu.getModel().getSize();
        
        this.titre.setText("test");
        this.pegi.setText("10.5");
        this.categ.setText("beta");
        this.btnAjoutCateg.doClick();
        this.categ.setText("solo");
        this.btnAjoutCateg.doClick();
        this.jour.setText("01");
        this.mois.setText("01");
        this.annee.setText("2019");
        this.btnVal.doClick();
        
        this.btnRech.doClick();
        assertEquals(this.listeJeu.getModel().getSize(), taille);
    }
    
    @Test
    public void testJourInc() {
        assertNotNull(titre);
        assertNotNull(pegi);
        assertNotNull(categ);
        assertNotNull(jour);
        assertNotNull(mois);
        assertNotNull(annee);
        assertNotNull(btnAjoutCateg);
        assertNotNull(btnVal);
        
        this.btnRech.doClick();
        int taille = this.listeJeu.getModel().getSize();
        
        this.titre.setText("test");
        this.pegi.setText("10");
        this.categ.setText("beta");
        this.btnAjoutCateg.doClick();
        this.categ.setText("solo");
        this.btnAjoutCateg.doClick();
        this.jour.setText("a");
        this.mois.setText("01");
        this.annee.setText("2019");
        this.btnVal.doClick();
        
        this.btnRech.doClick();
        assertEquals(this.listeJeu.getModel().getSize(), taille);
    }
    
    @Test
    public void testMoisInc() {
        assertNotNull(titre);
        assertNotNull(pegi);
        assertNotNull(categ);
        assertNotNull(jour);
        assertNotNull(mois);
        assertNotNull(annee);
        assertNotNull(btnAjoutCateg);
        assertNotNull(btnVal);
        
        this.btnRech.doClick();
        int taille = this.listeJeu.getModel().getSize();
        
        this.titre.setText("test");
        this.pegi.setText("10");
        this.categ.setText("beta");
        this.btnAjoutCateg.doClick();
        this.categ.setText("solo");
        this.btnAjoutCateg.doClick();
        this.jour.setText("01");
        this.mois.setText("b");
        this.annee.setText("2019");
        this.btnVal.doClick();
        
        this.btnRech.doClick();
        assertEquals(this.listeJeu.getModel().getSize(), taille);
    }
    
    @Test
    public void testAnneeInc() {
        assertNotNull(titre);
        assertNotNull(pegi);
        assertNotNull(categ);
        assertNotNull(jour);
        assertNotNull(mois);
        assertNotNull(annee);
        assertNotNull(btnAjoutCateg);
        assertNotNull(btnVal);
        
        this.btnRech.doClick();
        int taille = this.listeJeu.getModel().getSize();
        
        this.titre.setText("test");
        this.pegi.setText("10");
        this.categ.setText("beta");
        this.btnAjoutCateg.doClick();
        this.categ.setText("solo");
        this.btnAjoutCateg.doClick();
        this.jour.setText("01");
        this.mois.setText("01");
        this.annee.setText("c");
        this.btnVal.doClick();
        
        this.btnRech.doClick();
        assertEquals(this.listeJeu.getModel().getSize(), taille);
    }
}
