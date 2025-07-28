package sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuGame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int SIZE = 9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private int[][] board;
    private int[][] solution;


    public SudokuGame() {
    	JPanel topPanel = new JPanel();
    	String[] difficulties = {"Fácil", "Médio", "Difícil"};
    	JComboBox<String> difficultyBox = new JComboBox<>(difficulties);
    	JButton newGameButton = new JButton("Novo Jogo");

    	topPanel.add(new JLabel("Dificuldade:"));
    	topPanel.add(difficultyBox);
    	topPanel.add(newGameButton);
    	add(topPanel, BorderLayout.NORTH);

    	newGameButton.addActionListener(e -> {
    	    String difficulty = (String) difficultyBox.getSelectedItem();
    	    startNewGame(difficulty);
    	});

        setTitle("Sudoku");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel grid = new JPanel(new GridLayout(SIZE, SIZE));
        Font font = new Font("Arial", Font.BOLD, 20);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(font);
                cells[i][j] = cell;
                grid.add(cell);
            }
        }

        add(grid, BorderLayout.CENTER);
        
        startNewGame("fácil");
        setVisible(true);
    }
    private void startNewGame(String difficulty) {
        SudokuGenerator generator = new SudokuGenerator();
        board = generator.generate(difficulty);
        solution = generator.getSolution();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JTextField cell = cells[i][j];
                if (board[i][j] != 0) {
                    cell.setText(String.valueOf(board[i][j]));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                } else {
                    cell.setText("");
                    cell.setEditable(true);
                    cell.setBackground(Color.WHITE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGame::new);
    }
}


