package data;

import java.util.ArrayList;

/**
 * Provides different operations with data, like making Di/Dq/Dr matrix and
 * marking line.
 * 
 * @author <a href="mailto:h_d_f@mail.ru">Voitova Anastasiia</a>
 * 
 */
public class TableManagment {
    private Data data;

    public TableManagment(Data data) {
        this.data = data;
    }

    protected int getSize() {
        return data.getElements().size();
    }

    public ArrayList<Transition> getAllT() {
        ArrayList<Transition> trans = new ArrayList<Transition>();
        int size = getSize();
        for (int i = 0; i < size; i++) {
            if (data.getElements().get(i).getType() == "T")
                trans.add(((Transition) data.getElements().get(i)));
        }
        sortT(trans);
        return trans;
    }

    public ArrayList<Place> getAllP() {
        ArrayList<Place> trans = new ArrayList<Place>();
        int size = getSize();
        for (int i = 0; i < size; i++) {
            if (data.getElements().get(i).getType() == "P") {
                trans.add((Place) data.getElements().get(i));
            }
        }
        sortP(trans);
        return trans;
    }

    protected void sortP(ArrayList<Place> el) {
        boolean isSorted;
        Place temp = null;
        do {
            isSorted = true;
            for (int i = 0; i < el.size() - 1; i++) {
                if (el.get(i).getNo() > el.get(i + 1).getNo()) {
                    temp = el.get(i);
                    el.set(i, el.get(i + 1));
                    el.set(i + 1, temp);
                    isSorted = false;
                }
            }
        } while (!isSorted);
    }

    protected void sortT(ArrayList<Transition> el) {
        boolean isSorted;
        Transition temp = null;
        do {
            isSorted = true;
            for (int i = 0; i < el.size() - 1; i++) {
                if (el.get(i).getNo() > el.get(i + 1).getNo()) {
                    temp = el.get(i);
                    el.set(i, el.get(i + 1));
                    el.set(i + 1, temp);
                    isSorted = false;
                }
            }
        } while (!isSorted);
    }

    public int[] getMarkirovka() {
        ArrayList<Place> poz = getAllP();
        int[] mark = new int[poz.size()];
        for (int i = 0; i < poz.size(); i++) {
            mark[i] = poz.get(i).getNumTokens();
        }
        return mark;
    }

    public int[][] getMatrixDi() {
        int num = 0;
        ArrayList<Transition> tran = getAllT();
        ArrayList<Place> poz = getAllP();
        int[][] inmatr = new int[tran.size()][poz.size()];

        for (int i = 0; i < tran.size(); i++) {
            for (int j = 0; j < poz.size(); j++) {
                inmatr[i][j] = 0;
            }
        }
        for (int i = 0; i < poz.size(); i++) {
            for (int j = 0; j < poz.get(i).getOutputArcs().size(); j++) {
                num = poz.get(i).getOutputArcs().get(j).getTo();
                inmatr[num - 1][i]++;
            }
        }
        return inmatr;
    }

    public int[][] getMatrixDq() {
        int num = 0;
        ArrayList<Transition> tran = getAllT();
        ArrayList<Place> poz = getAllP();
        int[][] outmatr = new int[tran.size()][poz.size()];

        for (int i = 0; i < tran.size(); i++) {
            for (int j = 0; j < poz.size(); j++) {
                outmatr[i][j] = 0;
            }
        }
        for (int i = 0; i < tran.size(); i++) {
            for (int j = 0; j < tran.get(i).getOutputArcs().size(); j++) {
                num = tran.get(i).getOutputArcs().get(j).getTo();
                outmatr[i][num - 1]++;
            }
        }
        return outmatr;
    }

    public int[][] getMatrixDr(int[][] Di, int[][] Dq) {
        ArrayList<Transition> tran = getAllT();
        ArrayList<Place> poz = getAllP();
        int[][] outmatr = new int[tran.size()][poz.size()];

        for (int i = 0; i < tran.size(); i++) {
            for (int j = 0; j < poz.size(); j++) {
                outmatr[i][j] = Dq[i][j] - Di[i][j];
            }
        }
        return outmatr;
    }
}
