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

    //Gets Size of global arraylist with all P and T
    protected int getSize() {
        return data.getElements().size();
    }

    //Gets all T from global arraylist, but T are unordered
    protected ArrayList<Transition> getChaosT(){
        ArrayList<Transition> trans = new ArrayList<Transition>();
        int size = getSize();
        for (int i = 0; i < size; i++) {
            if (data.getElements().get(i).getType() == "T")
                trans.add(((Transition) data.getElements().get(i)));
        }
        return trans;
    }
    
    //Returns all T, ordered in their numbers/names
    public ArrayList<Transition> getAllT() {
        ArrayList<Transition> trans = new ArrayList<Transition>();
        trans = getChaosT();
        sortT(trans);
        return trans;
    }

    //Gets all P from global arraylist, but P are unordered
    protected ArrayList<Place> getChaosP(){
        ArrayList<Place> place = new ArrayList<Place>();
        int size = getSize();
        for (int i = 0; i < size; i++) {
            if (data.getElements().get(i).getType() == "P") {
            	place.add((Place) data.getElements().get(i));
            }
        }
        return place;
    }
    
    //Returns all P, ordered in their numbers/names
    public ArrayList<Place> getAllP() {
        ArrayList<Place> place = new ArrayList<Place>();
        place = getChaosP();
        sortP(place);
        return place;
    }
    
    //Returns number of P in ordered arraylist by number in not-ordered(chaotically)
    protected int getRealNumOfP(int num){
    	int rnum = 0;
    	for (int i = 0; i < getChaosP().size(); i++){
    		if (getChaosP().get(i).getNo() == num){
    			for (int j = 0; j < getAllP().size(); j++){
    				if (getAllP().get(j) == getChaosP().get(i)){
    					rnum = j;
    				}
    			}
    		}
    	}
    	return rnum;
    }
    
    //Returns number of T in ordered arraylist by number in not-ordered(chaotically)
    protected int getRealNumOfT(int num){
    	int rnum = 0;
    	for (int i = 0; i < getChaosT().size(); i++){
    		if (getChaosT().get(i).getNo() == num){
    			for (int j = 0; j < getAllT().size(); j++){
    				if (getAllT().get(j) == getChaosT().get(i)){
    					rnum = j;
    				}
    			}
    		}
    	}
    	return rnum;
    }

    //Sort P, ordered in their numbers/names
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

    //Sort T, ordered in their numbers/names
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
        int num = 0, rnum;
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
                rnum = getRealNumOfT(num);
                inmatr[rnum][i]++;
            }
        }
        return inmatr;
    }

    public int[][] getMatrixDq() {
        int num = 0, rnum = 0;
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
                rnum = getRealNumOfP(num);
                outmatr[i][rnum]++;
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
