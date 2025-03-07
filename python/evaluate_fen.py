from stockfish import Stockfish
import csv

stockfish_path = "C:/Users/User/Downloads/stockfish-windows-x86-64-avx2/stockfish/stockfish-windows-x86-64-avx2.exe"

stockfish = Stockfish(stockfish_path)

def categorize_evaluation(c):
    if c >= 5:
        return "White is winning"
    elif 5 > c >= 3:
        return "White has a decisive advantage"
    elif 3 > c >= 2:
        return "White is much better"
    elif 2 > c >= 1:
        return "White is better"
    elif -1 < c < 1:
        return "Even"
    elif -2 < c <= -1:
        return "Black is better"
    elif -3 < c <= -2:
        return "Black is much better"
    elif -5 < c <= -3:
        return "Black has a decisive advantage"
    elif c <= -5:
        return "Black is winning"
    return "Uncategorized"

with open("../output/fen_positions.txt", "r") as file, open("../output/evaluations.csv", "a", newline='') as csvfile:
    fieldnames = ['FEN', 'Evaluation', 'Category']
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

    if csvfile.tell() == 0:
        writer.writeheader()

    for line in file:
        fen = line.strip()
        stockfish.set_fen_position(fen)
        evaluation = stockfish.get_evaluation()['value'] / 100.0
        category = categorize_evaluation(evaluation)
        writer.writerow({'FEN': fen, 'Evaluation': evaluation, 'Category': category})
