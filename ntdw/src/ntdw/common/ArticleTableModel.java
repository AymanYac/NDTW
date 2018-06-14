package ntdw.common;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ntdw.model.Article;

public class ArticleTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Article> articles = new ArrayList<>();
	private String[] columnNames = { "Article ID", "Short description", "Family", "Class", "Manufact.", "Prio.",
			"Target","Quest.", "Status" };

	public ArticleTableModel(List<Article> articles) {
		this.articles = articles;
	}

	public ArticleTableModel(List<Article> articles2, String login) {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return articles.size();
	}

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Article article = articles.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return article.getId();
		case 1:
			return article.getDescription();
		case 2:
			return article.getFamily();
		case 3:
			return article.getArtClass();
		case 4:
			return article.getManufact();
		case 5:
			return article.getPrio();
		case 6:
			return article.getTarget();
		case 7:
			return article.getQuestion();
		case 8:
			return article.getStatus();
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ButtonColumn.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return int.class;
		case 6:
			return String.class;
		case 7:
			return String.class;
		case 8:
			return String.class;
		}
		return String.class;
	}
}
