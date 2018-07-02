package ntdw.views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JWindow;

import ntdw.model.AutoCompleteModel;


public class AutoComplete extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * La zone de texte dans laquelle on fait la recherche
	 */
	public JTextField zoneTexte;
	
	/**
	 * La Jwindow dans laquelle s'affichent les resultats de la recherche
	 */
	private JWindow fenetreRecherche;
	
	/**
	 * La liste des resultats de la recherche
	 */
	private JList resultats;
	
	/**
	 * Le modele de la liste des resultats
	 */
	private DefaultListModel modelListe;
	
	/**
	 * Le modele de l'autocomplete
	 */
	private AutoCompleteModel model;

	/**
	 * Constructeur de l'autocomplete
	 * @param model
	 * 	Le modele de l'autocomplete
	 */
	public AutoComplete(AutoCompleteModel model){
		this.model=model;
		zoneTexte = new JTextField();
		modelListe = new DefaultListModel();
		resultats = new JList(modelListe);
		resultats.setBorder(BorderFactory.createEtchedBorder());
		fenetreRecherche = new JWindow();
		fenetreRecherche.add(new JScrollPane(resultats));
		zoneTexte.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					fenetreRecherche.setVisible(false);
				}
				else if(e.getKeyCode()==KeyEvent.VK_DOWN){
					if(resultats.getSelectedIndex()<resultats.getModel().getSize()){
						resultats.setSelectedIndex(resultats.getSelectedIndex()+1);
						resultats.setSelectionBackground(Color.LIGHT_GRAY);
					}
					else {
						resultats.setSelectedIndex(0);
						resultats.setSelectionBackground(Color.LIGHT_GRAY);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP){
					if(resultats.getSelectedIndex()!=resultats.getModel().getSize()){
						resultats.setSelectedIndex(resultats.getSelectedIndex()-1);
						resultats.setSelectionBackground(Color.LIGHT_GRAY);
					}
					else {
						resultats.setSelectedIndex(resultats.getModel().getSize());
						resultats.setSelectionBackground(Color.LIGHT_GRAY);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_ENTER){
					zoneTexte.setText(resultats.getSelectedValue().toString());
					fenetreRecherche.setVisible(false);
				}
				else {
					update();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}
		});
		zoneTexte.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (zoneTexte.getText().length() >= 30 ) // limit textfield to 3 characters
		            e.consume(); 
		    }  
		});
		zoneTexte.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                List<String> correspondants = model.getToutesChaines();
                modelListe.clear();
                if(correspondants.size()==0){
                	fenetreRecherche.setVisible(false);
                	//modelListe.addElement("(No-match)");
                }
                else{
                	for(String s : correspondants){
                		modelListe.addElement(s);
                		
                	}
                	fenetreRecherche.setBounds((int)getLocationOnScreen().getX(), (int)getLocationOnScreen().getY()+zoneTexte.getHeight(), getWidth(), 3*zoneTexte.getHeight());
            		fenetreRecherche.setVisible(true);
                	resultats.setSelectedIndex(0);
            		resultats.setSelectionBackground(Color.LIGHT_GRAY);
                }
            }});
		
		
		FocusListener l = new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				List<String> correspondants = new ArrayList<String>();
				if(zoneTexte.getText().length()>0) {
					correspondants = model.getChainesCorrespondates(zoneTexte.getText());
				}else {
					correspondants = model.getToutesChaines();
				}
				
                modelListe.clear();
                if(correspondants.size()==0){
                	fenetreRecherche.setVisible(false);
                	//modelListe.addElement("(No-match)");
                }
                else{
                	for(String s : correspondants){
                		modelListe.addElement(s);
                		
                	}
                	fenetreRecherche.setBounds((int)getLocationOnScreen().getX(), (int)getLocationOnScreen().getY()+zoneTexte.getHeight(), getWidth(), 3*zoneTexte.getHeight());
            		fenetreRecherche.setVisible(true);
                	resultats.setSelectedIndex(0);
            		resultats.setSelectionBackground(Color.LIGHT_GRAY);
                }
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				fenetreRecherche.setVisible(false);
				
			}
			
		};
		zoneTexte.addFocusListener(l);
		setLayout(new GridLayout(1, 0));
		add(zoneTexte);
	}

	/**
	 * Fonction qui permet de mettre a jour les resultats de la recherche
	 */
	public void update(){
		List<String> correspondants = model.getChainesCorrespondates(zoneTexte.getText());
		modelListe.clear();
		if(correspondants.size()==0){
			fenetreRecherche.setVisible(false);
			//modelListe.addElement("(No-match)");
		}
		else{
			for(String s : correspondants){
				modelListe.addElement(s);
				fenetreRecherche.setBounds((int)getLocationOnScreen().getX(), (int)getLocationOnScreen().getY()+zoneTexte.getHeight(), getWidth(), 3*zoneTexte.getHeight());
				fenetreRecherche.setVisible(true);
				resultats.setSelectedIndex(1);
				resultats.setSelectionBackground(Color.LIGHT_GRAY);
			}
		}
		
	}

	public void setText(String oldVal) {
		zoneTexte.setText(oldVal);
	}


}
