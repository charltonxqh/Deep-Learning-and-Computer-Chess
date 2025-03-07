package org.example;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnHolder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/fen_positions.txt"))) {
            List<String> pgnFiles = Arrays.asList(
                    "resources/Alekhine Alexander 2259 Games from John Pandis.PGN",
                    "resources/Geller Efim 2579 Games from W. G. Sanderse.PGN",
                    "resources/Gligoric Svetosar (YUG) 3034 Games.PGN",
                    "resources/Hort Vlastimil 2521 Games, from W. G. Sanderse.PGN",
                    "resources/Ivkov Borislav (YUG) 2503 Games.PGN",
                    "resources/Karpov Anatoli GM 3079 Games.PGN",
                    "resources/Kasparov Garry 1973-1998 1878 Games.PGN",
                    "resources/Keres Paul (EST, 1916-1975) 2324 Games expanded by Jan Malmstrom.PGN",
                    "resources/Korchnoi Viktor 3995 Games, from W. G. Sanderse..PGN",
                    "resources/Ljubojevic Ljubomir (YUG) 2070 Games.PGN",
                    "resources/Miles Anthony J. (ENG) 2336 Games.PGN",
                    "resources/Petrosian Tigran 1850 Games.PGN",
                    "resources/Polugaevsky Lev 2050 Games, 1953-1994.PGN",
                    "resources/Portisch Lajos (HUN) 2380 Games.PGN",
                    "resources/The History of the World Championship (Male and Female) 3531 Games WCH Matches, Candidate matches, Candidate tournaments From W. G. Sanderse.PGN"

            );

            for (String pgnFile: pgnFiles) {
                PgnHolder pgn = new PgnHolder(pgnFile);
                pgn.loadPgn();
                System.out.println("Processing file: " + pgnFile);

                for (Game game: pgn.getGames()) {
                    game.loadMoveText();
                    MoveList moves = game.getHalfMoves();
                    Board board = new Board();

                    String fen = moves.getFen();

                    if (fen != null && !fen.isEmpty()) {
                        board.loadFromFen(fen);
                        writer.write(board.getFen());
                        writer.newLine();
                    }

                    writer.write(board.getFen());
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}