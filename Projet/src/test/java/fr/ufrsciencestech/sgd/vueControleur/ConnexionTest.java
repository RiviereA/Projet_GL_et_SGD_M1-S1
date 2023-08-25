/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.sgd.vueControleur;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ar948052
 */
public class ConnexionTest {
    
    private static Connexion co;
    
    @Before
    public void setUp() {
        this.co = new Connexion();
    }
    
    @Test
    public void testConnexion() {
        assertNotNull(co);
        JTextField login = (JTextField)TestUtils.getChildNamed(co, "login");
        assertNotNull(login);
        JPasswordField pwd = (JPasswordField)TestUtils.getChildNamed(co, "password");
        assertNotNull(pwd);
        final JButton btnCo = (JButton)TestUtils.getChildNamed(co, "btnConnexion");
        assertNotNull(btnCo);
        
        login.setText("rafil");
        pwd.setText("0000");
        btnCo.doClick();
        
        assertEquals(co.isVisible(), false);
        
    }
    
    @Test
    public void testConnexionEchec() {
        assertNotNull(co);
        JTextField login = (JTextField)TestUtils.getChildNamed(co, "login");
        assertNotNull(login);
        JPasswordField pwd = (JPasswordField)TestUtils.getChildNamed(co, "password");
        assertNotNull(pwd);
        final JButton btnCo = (JButton)TestUtils.getChildNamed(co, "btnConnexion");
        assertNotNull(btnCo);
        JLabel lab = (JLabel)TestUtils.getChildNamed(co, "labCoFail");
        
        login.setText("toto");
        pwd.setText("toto");
        btnCo.doClick();
        
        assertEquals(lab.isVisible(), true);
    }
    
}
