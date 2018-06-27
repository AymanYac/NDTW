package ntdw.views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
				if(zoneTexte.getText()==null || zoneTexte.getText().length()==0){
					fenetreRecherche.setVisible(false);
				}
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					fenetreRecherche.setVisible(false);
				}
				else if(e.getKeyCode()==KeyEvent.VK_DOWN){
					if(resultats.getSelectedIndex()<resultats.getModel().getSize()){
						resultats.setSelectedIndex(resultats.getSelectedIndex()+1);
						resultats.setSelectionBackground(Color.red);
					}
					else {
						resultats.setSelectedIndex(0);
						resultats.setSelectionBackground(Color.red);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP){
					if(resultats.getSelectedIndex()!=resultats.getModel().getSize()){
						resultats.setSelectedIndex(resultats.getSelectedIndex()-1);
						resultats.setSelectionBackground(Color.red);
					}
					else {
						resultats.setSelectedIndex(resultats.getModel().getSize());
						resultats.setSelectionBackground(Color.red);
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
				resultats.setSelectedIndex(0);
				resultats.setSelectionBackground(Color.red);
			}
		}
		
	}

	public void setText(String oldVal) {
		zoneTexte.setText(oldVal);
	}


}
