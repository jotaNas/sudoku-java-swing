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
    	String[] difficulties = {"Facil", "Medio", "Dificil"};
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

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));

        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                JPanel subGrid = new JPanel(new GridLayout(3, 3));
                subGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int row = blockRow * 3 + i;
                        int col = blockCol * 3 + j;

                        JTextField cell = new JTextField();
                        cell.setHorizontalAlignment(JTextField.CENTER);
                        cell.setFont(font);
                        cells[row][col] = cell;
                        subGrid.add(cell);
                    }
                }

                gridPanel.add(subGrid);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        
        startNewGame("facil");
        setVisible(true);
        
        JButton checkButton = new JButton("Verificar");
        add(checkButton, BorderLayout.SOUTH);

        checkButton.addActionListener(e -> checkSolution());
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
    private void checkSolution() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String text = cells[i][j].getText();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ha celulas vazias.");
                    return;
                }
                try {
                    int value = Integer.parseInt(text);
                    if (value != solution[i][j]) {
                        JOptionPane.showMessageDialog(this, "Solucao incorreta.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Valor invalido em (" + (i + 1) + "," + (j + 1) + ")");
                    return;
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Parabens! Voce completou o Sudoku!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGame::new);
    }
}


