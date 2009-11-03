package data;

public class TreeofPetriNet {
    final static int Size = 8000;

    final static int Inside = 8000;

    static int Np;

    static int Nt;

    static int[][] DI;

    static int[][] DQ;

    static int[] TypeCrossing;

    static int[] M0;

    static int[] LastMCrossing;

    static int LastCross;

    public static int RepeatCount;

    static int[][] RepeatList;

    static int[] Curr;

    static int[] Prev;

    static int PrevNum;

    static int Number;

    static TreeConnection[] Z;

    static boolean canedit = false;

    static int Level = 1;

    public TreeofPetriNet(int np, int nt, int[][] di, int[][] dq, int[] m0,
            int[] typecrossing) {
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
    }

    // ������ �������� ������������� ����������� ���������.
    // ������� tj ���������� ����������� � ���������� �, ���� �>=I(tj)
    private static boolean IsCrossingEnabled(int[] M, int T) {
        int i;
        for (i = 0; i < Np; i++)
            if (M[i] < DI[T][i])
                return false;
        return true;
    }

    private static boolean IsMarkerImpasse(int[] M) {
        int i;
        for (i = 0; i < Nt; i++)
            if (IsCrossingEnabled(M, i))
                return false;
        return true;
    }

    // ��������� ������������ ��������. ������������ �������� ���������� ����
    private static int[] RunCrossing(int[] M, int[] C) {
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

    private static int[] RunMomentCrossing(int[] M) {
        int i;
        int[] Res = new int[Np];
        boolean flag = false;
        int[] C = new int[Nt];

        for (i = 0; i < Np; i++)
            Res[i] = M[i];

        for (i = 0; i < Nt; i++) {
            if (TypeCrossing[i] == 1)
                if (IsCrossingEnabled(Res, i)) {
                    C[i] = 1;
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
        return Res;
    }

    private static boolean Equal(int[] A, int[] B) {
        int i;
        for (i = 0; i < A.length; i++)
            if (A[i] != B[i])
                return false;
        return true;
    }

    private static int GetMarkerRepeat(int[] M) {
        int i;
        for (i = 1; i < RepeatCount; i++)
            if (Equal(M, RepeatList[i]))
                return i;
        return -1;
    }

    private static String getStringValue(int[] M, int N) {
        int i;
        String Marker = "";
        for (i = 0; i < N; i++)
            Marker = Marker.concat(Integer.toString(M[i]));
        return Marker;
    }

    private static void WriteMarker(int Level, int Num, int[] M, int Num2,
            int[] P, String Str) {
        int i;
        String S = "";
        // MyWindow.tbTree.setValueAt(Integer.toString(Number),Number-1,0);
        // MyWindow.tbTree.setValueAt(Integer.toString(Level),Number-1,1);
        System.out.print(Integer.toString(Number) + "  ");
        System.out.print(Integer.toString(Level) + "  ");
        // MyWindow.tbTree.setValueAt("M"+ Integer.toString(Num2)+
        // "("+getStringValue(P,Np)+")", Number-1,2);
        System.out.print("M" + Integer.toString(Num2) + "("
                + getStringValue(P, Np) + ")  ");
        if (RepeatCount != 0) {
            S = ""; // getStringValue(LastMCrossing,Nt);
            if (LastCross != 0)
                S = S + " t" + Integer.toString(LastCross);
            for (i = 0; i < Nt; i++)
                if (LastMCrossing[i] == 1)
                    S = S + " m" + Integer.toString(i);
            // MyWindow.tbTree.setValueAt(S, Number - 1, 3);
            System.out.print(S + " ");
        }
        // MyWindow.tbTree.setValueAt("M"+ Integer.toString(Num)+
        // "("+getStringValue(M,Np)+")",Number-1,4);
        System.out.print("M" + Integer.toString(Num) + "("
                + getStringValue(M, Np) + ")  ");
        // MyWindow.tbTree.setValueAt(Str,Number-1,5);
        System.out.println(Str);
        Number++;
        if (RepeatCount != 0) {
            Z[RepeatCount - 1].addVhod(Num);
            Z[RepeatCount - 1].addVuhod(Num2);
            if (Num < Num2)
                Z[RepeatCount - 1].addNapryam(-1);
            if (Num >= Num2)
                Z[RepeatCount - 1].addNapryam(1);
        }
    }

    private static int[] GetCrossingByNumber(int Num) {
        int i;
        int[] C = new int[Nt];
        for (i = 0; i < Nt; i++)
            C[i] = 0;
        C[Num] = 1;
        return C;
    }

    public static void saveArray(int[] A, int[] B) {
        int i;
        for (i = 0; i < A.length; i++)
            A[i] = B[i];
    }

    public static void save2DArray(int[][] A, int[][] B) {
        int i, j;
        for (i = 0; i < A.length; i++)
            for (j = 0; j < A[i].length; j++)
                A[i][j] = B[i][j];
    }

    private static void Next() {
        int i, temp;
        int[] SaveCurr = new int[Np];

        temp = GetMarkerRepeat(Curr);
        if (temp != -1) {
            WriteMarker(Level, temp, Curr, PrevNum, Prev, "Povtorilas`");
            return;
        }

        if (IsMarkerImpasse(Curr)) {
            RepeatList[RepeatCount] = Curr;
            WriteMarker(Level, RepeatCount, Curr, PrevNum, Prev, "Typikovaja");
            RepeatCount++;
            return;
        }

        RepeatList[RepeatCount] = Curr;
        if (RepeatCount == 0)
            WriteMarker(0, 0, Curr, 0, Prev, "");
        else
            WriteMarker(Level, RepeatCount, Curr, PrevNum, Prev, "Vnutrenya");
        Prev = Curr;
        PrevNum = RepeatCount;
        RepeatCount++;

        for (i = 0; i < Nt; i++) {
            if (IsCrossingEnabled(Curr, i)) {
                canedit = true;
                saveArray(SaveCurr, Curr);
                Curr = RunCrossing(Curr, GetCrossingByNumber(i));
                LastCross = i;
                Curr = RunMomentCrossing(Curr);
                Next();
                saveArray(Curr, SaveCurr);
            }
        }
        if (canedit) {
            Level++;
            canedit = false;
        }
        ;

    }

    public TreeConnection[] WriteResult() {
        LastMCrossing = new int[Nt];
        RepeatList = new int[Size][Np];
        Curr = new int[Np];
        Prev = new int[Np];
        Z = new TreeConnection[Inside];
        int i;
        for (i = 0; i < Inside; i++)
            Z[i] = new TreeConnection();
        Number = 1;
        RepeatCount = 0;
        saveArray(Prev, M0);
        saveArray(Curr, M0);
        Curr = RunMomentCrossing(Curr);
        Level = 1;
        Next();
        return Z;
    }
}
