/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Voitova Anastasiia.
    
    This file is part of Petri nets Emulator.
    
    Petri nets Emulator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    Petri nets Emulator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with Petri nets Emulator. If not, see <http://www.gnu.org/licenses/>.
*/

package data.modeling;

import java.util.ArrayList;

import data.Data;
import data.Marking;
import data.elements.Transition;

/**
 * Class for analysis destinations of marks.
 * 
 * @author Jacky
 */
public class TreeofPetriNet {
    /**
     * max number of repeated marks
     */
    final int Size = 8000;

    /**
     * max number of tree connections
     */
    final int Inside = 8000;

    final int limit = 5;

    /**
     * number of positions
     */
    int Np;

    /**
     * number of transitions
     */
    int Nt;

    /**
     * Matrix entrance-transitions
     */
    int[][] DI;

    /**
     * Matrix of exit-transitions
     */
    int[][] DQ;

    /**
     * 1- moment transition(Holds type of transitions)
     */
    int[] TypeCrossing;

    /**
     * Start Mark
     */
    int[] M0;

    /**
     * Holds Last Moment Crossing
     */
    int[] LastMCrossing;

    /**
     * Holds Last Crossing
     */
    int LastCross;

    /**
     * Size of repeated Marks.
     */
    public static int RepeatCount;

    /**
     * List of repeated Marks
     */
    int[][] RepeatList;

    /**
     * Current Mark
     */
    int[] Curr;

    /**
     * Current Mark 1
     */
    int[] Curr1;

    /**
     * Previous Mark
     */
    int[] Prev;

    /**
     * Number of Previous Mark
     */
    int PrevNum;

    /**
     * Number of List position
     */
    public static int Number;

    private TransitionsTable transTable;

    private Data data;

    /**
     * @return the crossTable
     */
    public TransitionsTable getTransTable() {
        return transTable;
    }

    // private ArrayList<String> typeT= new ArrayList<String>();
    // private ArrayList<Integer> numTypeT= new ArrayList<Integer>();
    private ArrayList<Transition> workedT = new ArrayList<Transition>();

    /**
     * for Graph of Markov
     */
    TreeConnection[] Z;

    /**
     * for Graph of destinations
     */
    TreeConnection[] Z1;

    /**
     * Number of branch of the tree
     */
    int Level = 1;

    /**
     * Number of yarus of the tree
     */
    int Yarus = 0;

    /**
     * String that holds all triggered moment transitions
     */
    String momentSequance = "";

    /**
     * Constructor
     * 
     * @param np
     * @param nt
     * @param di
     * @param dq
     * @param m0
     * @param typecrossing
     */
    public TreeofPetriNet(int np, int nt, int[][] di, int[][] dq, int[] m0,
            int[] typecrossing, Data data) {
        Nt = nt;
        Np = np;
        DI = new int[Nt][Np];
        save2DArray(DI, di);
        DQ = new int[Nt][Np];
        save2DArray(DQ, dq);
        M0 = new int[Np];
        saveArray(M0, m0);
        TypeCrossing = new int[Nt];
        saveArray(TypeCrossing, typecrossing);

        this.transTable = new TransitionsTable();
        this.data = data;
    }

    private void getVuhodNumber(TreeConnection[] Z1, int Num2) {
        for (int j = 0; j < Number - 3; j++) {
            if ((Z1[j].getNameVhod()) == Num2) {
                Z1[Number - 2].addVuhod(j);
                return;
            }
        }
        if (Number >= 3)
            Z1[Number - 2].addVuhod(Z1[Number - 3].getElementVhod(0));
    }

    private boolean IsCrossingEnabled(int[] M, int T) {
        int i;
        for (i = 0; i < Np; i++){
        	int fq=0;
        	int fi=0;
        	for (int j = 0; j < Np; j++) {
				if(DQ[T][j]!=0)
					fq=1;
				if (DI[T][j]!=0)
					fi=1;
			}
            if ((M[i] < DI[T][i])||(fq==0)||(fi==0))
                return false;
        }
        return true;
    }

    private boolean IsMarkerImpasse(int[] M) {
        int i;
        for (i = 0; i < Nt; i++)
            if (IsCrossingEnabled(M, i))
                return false;
        return true;
    }

    private int[] RunCrossing(int[] M, int[] C) {
        int i, j, temp;
        int[] Res = new int[Np];
        for (i = 0; i < Np; i++)
            Res[i] = M[i];
        for (i = 0; i < Np; i++) {
            temp = 0;
            for (j = 0; j < Nt; j++)
                temp = temp + C[j] * (DQ[j][i] - DI[j][i]);
            Res[i] = Res[i] + temp;
        }

        return Res;
    }

    // ---------------------------------------------------------
    private int[] RunMomentCrossing(int[] M) {
        int i;
        int[] Res = new int[Np];
        boolean flag = true;
        int[] C = new int[Nt];

        for (i = 0; i < Np; i++)
            Res[i] = M[i];

        while (flag) {
            flag = false;
            for (i = 0; i < Nt; i++) {
                if (TypeCrossing[i] == 1)
                    if (IsCrossingEnabled(Res, i)&&(flag==false)) {
                        C[i] = 1;
                        momentSequance = momentSequance + "m" + (i + 1);

                        workedT.add(data.getTransitionWithNo(i + 1));
                        // typeT.add("m");
                        // numTypeT.add(i+1);
                        flag = true;
                    } else
                        C[i] = 0;
                else
                    C[i] = 0;
            }
            if (flag)
                Res = RunCrossing(Res, C);
            for (i = 0; i < Nt; i++)
                LastMCrossing[i] = C[i];
        }
        return Res;
    }

    private boolean Equal(int[] A, int[] B) {
        int i;
        for (i = 0; i < A.length; i++)
            if (A[i] != B[i])
                return false;
        return true;
    }

    private int GetMarkerRepeat(int[] M) {
        int i;
        for (i = 0; i < RepeatCount; i++)
            if (Equal(M, RepeatList[i]))
                return i;
        return -1;
    }

    // private String getStringValue(int[] M, int N) {
    // int i;
    // String Marker = "";
    // for (i = 0; i < N; i++)
    // Marker = Marker.concat(Integer.toString(M[i]));
    // return Marker;
    // }

    private void WriteMarker(int branch, int nextMNo, int[] nextM, int prevMNo,
            int[] prevM, MarkType markType, int level) {

        int no = Number;
        Marking prevMarking = new Marking(prevMNo, prevM);
        Marking nextMarking = new Marking(nextMNo, nextM);
        ArrayList<Transition> workedTrans = workedT;

        TransitionsTableRow curRow = new TransitionsTableRow(no, branch, level,
                prevMarking, nextMarking, workedTrans, markType);
        transTable.add(curRow);

        // Logging:
        // System.out.print(no + "  ");
        // System.out.print(branch + "  ");
        // System.out.print(prevMarking.toString() + "   ");
        // System.out.print(nextMarking.toString() + "   ");
        // System.out.print(MarkType.toString(markType) + "   ");
        // System.out.println(level);
        // int i;
        // String S = "";
        // if (RepeatCount != 0) {
        // S = "";
        // if (LastCross != 0)
        // for (i = 0; i < Nt; i++)
        // if (LastMCrossing[i] == 1)
        // S = S + " m" + Integer.toString(i);
        // System.out.print(momentSequance + " ");
        // }

        workedT = new ArrayList<Transition>();
        Number++;
        if (RepeatCount >= 0) {
            Z1[Number - 2].addNameVhod(nextMNo);
            Z1[Number - 2].addNameVuhod(prevMNo);
            Z1[Number - 2].addVhod(Number - 2);
            if ((Number - 2) == 0) {
                Z1[Number - 2].addVuhod(Number - 2);
            }
            getVuhodNumber(Z1, prevMNo);
            if (nextMNo < prevMNo)
                Z1[Number - 2].addNapryam(-1);
            if (nextMNo >= prevMNo)
                Z1[Number - 2].addNapryam(1);
        }
        if (RepeatCount != 0) {
            Z[RepeatCount - 1].addVhod(nextMNo);
            Z[RepeatCount - 1].addVuhod(prevMNo);
            if (nextMNo < prevMNo)
                Z[RepeatCount - 1].addNapryam(-1);
            if (nextMNo >= prevMNo)
                Z[RepeatCount - 1].addNapryam(1);
        }
    }

    private int[] GetCrossingByNumber(int Num) {
        int i;
        int[] C = new int[Nt];
        for (i = 0; i < Nt; i++)
            C[i] = 0;
        C[Num] = 1;
        return C;
    }

    /**
     * Saves B into A
     * 
     * @param A
     * @param B
     */
    public void saveArray(int[] A, int[] B) {
        int i;
        for (i = 0; i < A.length; i++)
            A[i] = B[i];
    }

    /**
     * Saves B into A
     * 
     * @param A
     * @param B
     */
    public void save2DArray(int[][] A, int[][] B) {
        int i, j;
        for (i = 0; i < A.length; i++)
            for (j = 0; j < A[i].length; j++)
                A[i][j] = B[i][j];
    }

    public void Limit(int[] Curr) {
        for (int i = 0; i < Curr.length; i++) {
            if (Curr[i] > limit)
                Curr[i] = limit;
        }
    }

    /**
     * Recursive procedure for making Tree
     * 
     * @param Curr2
     * @param Prev1
     * @param PrevNum1
     * @param yarus1
     */
    private void Next(int[] Curr2, int[] Prev1, int PrevNum1, int yarus1) {
        int i, temp;
        int yarus = yarus1;
        int[] SaveCurr = new int[Np];
        int[] Curr = new int[Np];
        int[] Prev = new int[Np];
        int PrevNum = PrevNum1;
        saveArray(Curr, Curr2);
        saveArray(Prev, Prev1);
        if (Np==0){
        	WriteMarker(Level, RepeatCount, Curr, PrevNum, Prev,
                    MarkType.DEADLOCK, yarus);
        	return;
        }
        if (RepeatCount == 0) {
            temp = GetMarkerRepeat(Prev);
        } else {
            Limit(Curr);
            temp = GetMarkerRepeat(Curr);
        }
        if (temp != -1) {
            WriteMarker(Level, temp, Curr, PrevNum, Prev, MarkType.REPEATED,
                    yarus);
            momentSequance = "";
            Level++;
            return;
        }

        if (IsMarkerImpasse(Curr)) {
            RepeatList[RepeatCount] = Curr;
            WriteMarker(Level, RepeatCount, Curr, PrevNum, Prev,
                    MarkType.DEADLOCK, yarus);
            RepeatCount++;
            Level++;
            momentSequance = "";
            //System.out.println(Np);
            return;
        }
        if (RepeatCount == 0) {
            RepeatList[RepeatCount] = M0;
        } else {
            RepeatList[RepeatCount] = Curr;
        }
        if ((RepeatCount == 0) || (RepeatCount == 1)) {
            if (RepeatCount == 0) {
                WriteMarker(0, 0, Prev, 0, Prev, MarkType.ROOT, 0);
            }
            if (RepeatCount == 1) {
                WriteMarker(Level, RepeatCount, Curr, PrevNum, Curr1,
                        MarkType.INTERNAL, yarus);
                momentSequance = "";
            }
        } else {
            WriteMarker(Level, RepeatCount, Curr, PrevNum, Prev,
                    MarkType.INTERNAL, yarus);
            momentSequance = "";
        }
        Prev = Curr;
        PrevNum = RepeatCount;
        RepeatCount++;
        yarus++;
        for (i = 0; i < Nt; i++) {
            if (IsCrossingEnabled(Curr, i)) {
                saveArray(SaveCurr, Curr);
                Curr = RunCrossing(Curr, GetCrossingByNumber(i));
                LastCross = i + 1;
                momentSequance = momentSequance + " t"
                        + Integer.toString(LastCross);
                // typeT.add("t");
                workedT.add(data.getTransitionWithNo(LastCross));
                if(Np!=0)
                Curr = RunMomentCrossing(Curr);
                Next(Curr, Prev, PrevNum, yarus);
                saveArray(Curr, SaveCurr);
            }
        }

    }

    /**
     * 
     * @param ZZ
     *            -information for building tree of Markov
     * @return information for building tree of destinations
     */
    public TreeConnection[] WriteResult(int numb) {
        LastMCrossing = new int[Nt];
        RepeatList = new int[Size][Np];
        Curr = new int[Np];
        Prev = new int[Np];
        Curr1 = new int[Np];
        Z = new TreeConnection[Inside];
        Z1 = new TreeConnection[Inside];
        int i;
        for (i = 0; i < Inside; i++) {
            Z[i] = new TreeConnection();
            Z1[i] = new TreeConnection();
        }
        Number = 1;
        RepeatCount = 0;
        saveArray(Prev, M0);
        saveArray(Curr1, M0);
        saveArray(Curr, M0);
        if(Np!=0)
        Curr = RunMomentCrossing(Curr);
        Level = 1;
        Next(Curr, Prev, PrevNum, Yarus);
        if (numb == 1) {
            return Z1;
        } else {
            return Z;
        }
    }
}
