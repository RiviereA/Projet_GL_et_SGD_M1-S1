/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.sgd.vueControleur;

import fr.ufrsciencestech.sgd.modele.GestionBD;
import fr.ufrsciencestech.sgd.modele.Modele;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ar948052
 */
public class RechercheUtilisateurTest {
    
    private GestionBD gbd = new GestionBD("tf872254");
    private Modele m = new Modele(gbd);
    private static RechercheUtilisateur ru;
    
    private JComboBox att0;
    private JTextField champ0;
    private JComboBox categ0;
    private JButton btnRech;
    private JList listeJeu;
    
    @Before
    public void setUp() {
        this.ru = new RechercheUtilisateur(m);
        this.att0 = (JComboBox)TestUtils.getChildNamed(ru, "att0");
        this.champ0 = (JTextField)TestUtils.getChildNamed(ru, "champ0");
        this.categ0 = (JComboBox)TestUtils.getChildNamed(ru, "categ0");
        this.btnRech = (JButton)TestUtils.getChildNamed(ru, "btnRecherche");
        this.listeJeu = (JList)TestUtils.getChildNamed(ru, "listeJeu");
    }

    @Test
    public void testRechercheNom() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("nom");
        champ0.setText("Darkest Dungeon");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()==1);
    }
    
    @Test
    public void testRechercheCateg() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("categorie");
        champ0.setText("multijoueur");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRecherchePegi() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("pegi");
        champ0.setText("16");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRecherchePegiErreur() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("pegi");
        champ0.setText("seize");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()==0);
    }
    
    @Test
    public void testRecherchePegiVide() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("pegi");
        champ0.setText("");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()==0);
    }
    
    @Test
    public void testRechercheDispo() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("disponibilite");
        champ0.setText("1");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRechercheDispoErreur() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("disponibilite");
        champ0.setText("dispo");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()==0);
    }
    
    @Test
    public void testRechercheDispoVide() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("disponibilite");
        champ0.setText("");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()==0);
    }
    
    @Test
    public void testRechercheSerie() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("serie");
        champ0.setText("warcraft");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRechercheSerieVide() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("serie");
        champ0.setText("");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRechercheNoteSup() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("note >");
        champ0.setText("10");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRechercheNoteInf() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("note <");
        champ0.setText("10");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRechercheNoteEg() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("note =");
        champ0.setText("20");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRechercheNoteErreur() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("note =");
        champ0.setText("dix");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getElementAt(0).equals("Mauvais argument"));
    }
    
    @Test
    public void testRechercheNoteVide() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("note >");
        champ0.setText("");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getElementAt(0).equals("Mauvais argument"));
    }
    
    @Test
    public void testRecherche2Crit() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        JCheckBox check = (JCheckBox)TestUtils.getChildNamed(ru, "check1");
        assertNotNull(check);
        JRadioButton et = (JRadioButton)TestUtils.getChildNamed(ru, "et1");
        assertNotNull(et);
        JComboBox att1 = (JComboBox)TestUtils.getChildNamed(ru, "att1");
        assertNotNull(att1);
        JTextField champ1 = (JTextField)TestUtils.getChildNamed(ru, "champ1");
        assertNotNull(champ1);
        JComboBox categ1 = (JComboBox)TestUtils.getChildNamed(ru, "categ1");
        assertNotNull(categ1);
        
        att0.setSelectedItem("nom");
        champ0.setText("w");
        check.doClick();
        et.doClick();
        att1.setSelectedItem("categorie");
        categ1.setSelectedItem("multijoueur");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRecherche6Crit() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        JCheckBox check1 = (JCheckBox)TestUtils.getChildNamed(ru, "check1");
        assertNotNull(check1);
        JRadioButton ou1 = (JRadioButton)TestUtils.getChildNamed(ru, "ou1");
        assertNotNull(ou1);
        JComboBox att1 = (JComboBox)TestUtils.getChildNamed(ru, "att1");
        assertNotNull(att1);
        JTextField champ1 = (JTextField)TestUtils.getChildNamed(ru, "champ1");
        assertNotNull(champ1);
        
        JCheckBox check2 = (JCheckBox)TestUtils.getChildNamed(ru, "check2");
        assertNotNull(check2);
        JRadioButton et2 = (JRadioButton)TestUtils.getChildNamed(ru, "et2");
        assertNotNull(et2);
        JComboBox att2 = (JComboBox)TestUtils.getChildNamed(ru, "att2");
        assertNotNull(att2);
        JComboBox categ2 = (JComboBox)TestUtils.getChildNamed(ru, "categ2");
        assertNotNull(categ2);
        
        JCheckBox check3 = (JCheckBox)TestUtils.getChildNamed(ru, "check3");
        assertNotNull(check3);
        JRadioButton et3 = (JRadioButton)TestUtils.getChildNamed(ru, "et3");
        assertNotNull(et3);
        JComboBox att3 = (JComboBox)TestUtils.getChildNamed(ru, "att3");
        assertNotNull(att3);
        JTextField champ3 = (JTextField)TestUtils.getChildNamed(ru, "champ3");
        assertNotNull(champ3);
        
        JCheckBox check4 = (JCheckBox)TestUtils.getChildNamed(ru, "check4");
        assertNotNull(check4);
        JRadioButton et4 = (JRadioButton)TestUtils.getChildNamed(ru, "et4");
        assertNotNull(et4);
        JComboBox att4 = (JComboBox)TestUtils.getChildNamed(ru, "att4");
        assertNotNull(att4);
        JTextField champ4 = (JTextField)TestUtils.getChildNamed(ru, "champ4");
        assertNotNull(champ4);
        
        JCheckBox check5 = (JCheckBox)TestUtils.getChildNamed(ru, "check5");
        assertNotNull(check5);
        JRadioButton ou5 = (JRadioButton)TestUtils.getChildNamed(ru, "ou5");
        assertNotNull(ou5);
        JComboBox att5 = (JComboBox)TestUtils.getChildNamed(ru, "att5");
        assertNotNull(att5);
        JTextField champ5 = (JTextField)TestUtils.getChildNamed(ru, "champ5");
        assertNotNull(champ5);
        
        att0.setSelectedItem("serie");
        champ0.setText("warcraft");
        
        check1.doClick();
        ou1.doClick();
        att1.setSelectedItem("serie");
        champ1.setText("battlefield");
        
        check2.doClick();
        et2.doClick();
        att2.setSelectedItem("categorie");
        categ2.setSelectedItem("multijoueur");
        
        check3.doClick();
        et3.doClick();
        att3.setSelectedItem("pegi");
        champ3.setText("12");
        
        check4.doClick();
        et4.doClick();
        att4.setSelectedItem("note >");
        champ4.setText("10");
        
        check5.doClick();
        ou5.doClick();
        att5.setSelectedItem("note =");
        champ5.setText("10");
        
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getSize()>0);
    }
    
    @Test
    public void testRechercheEchec() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        
        att0.setSelectedItem("note >");
        btnRech.doClick();
        
        assertTrue(listeJeu.getModel().getElementAt(this.listeJeu.getModel().getSize()-1).equals("Mauvais argument"));
    }
    
    @Test
    public void testAfficheJeu() {
        assertNotNull(ru);
        assertNotNull(att0);
        assertNotNull(champ0);
        assertNotNull(btnRech);
        assertNotNull(listeJeu);
        
        JTextArea detailsJeu = (JTextArea)TestUtils.getChildNamed(ru, "detailsJeu");
        assertNotNull(detailsJeu);
        
        att0.setSelectedItem("nom");
        champ0.setText("w");
        btnRech.doClick();
        
        try {
            Robot rob = new Robot(ru.getGraphicsConfiguration().getDevice());
            
            Point pt=new Point(btnRech.getLocation());
            SwingUtilities.convertPointToScreen(pt, btnRech.getParent());
            
            //rob.mouseMove(800, 70);
            rob.mouseMove(pt.x, pt.y);
            
            rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            
            this.wait(100);
            assertNotEquals(detailsJeu.getText(), "");
        } catch (Exception e) {
            
        }
    }
    
    @Test
    public void testNbAvis() {
        assertNotNull(ru);
        
        JTextArea detailsJeu = (JTextArea)TestUtils.getChildNamed(ru, "detailsJeu");
        assertNotNull(detailsJeu);
        JButton btnAvis = (JButton)TestUtils.getChildNamed(ru, "btnAvis");
        assertNotNull(btnAvis);
        
        btnAvis.doClick();
        
        assertTrue(detailsJeu.getText().contains("Pseudo : "));
    }
    
    @Test
    public void testMoy() {
        assertNotNull(ru);
        
        JTextArea detailsJeu = (JTextArea)TestUtils.getChildNamed(ru, "detailsJeu");
        assertNotNull(detailsJeu);
        JButton btnMoy = (JButton)TestUtils.getChildNamed(ru, "btnMoy");
        assertNotNull(btnMoy);
        
        btnMoy.doClick();
        
        assertTrue((detailsJeu.getText().contains("Nom : ") && detailsJeu.getText().contains("Moyenne des notes : ")));
    }
}
