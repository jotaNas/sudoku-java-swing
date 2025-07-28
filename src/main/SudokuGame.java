package sudoku;

import javax.swing.*;

public class SudokuGame extends JFrame {

	private static final long serialVersionUID = 1L;

	public SudokuGame() {
        setTitle("Sudoku");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGame::new);
    }
}

