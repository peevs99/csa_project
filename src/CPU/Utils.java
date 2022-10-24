package CPU;
import CPU.Registers;
import CPU.Memory;
import java.math.BigInteger;

public class Utils {


    public static String[] InstructionSet1 = {"LDR", "STR", "LDA", "LDX", "STX", "JZ", "JNE", "JCC", "JMA", "JSR", "RFS", "SOB", "JGE", "AMR", "SMR", "AIR", "SIR"};
    public static String[] InstructionSet2 = {"MLT", "DVD", "TRR", "AND", "ORR", "NOT"};
    public static String[] InstructionSet3 = {"SRC", "RRC"};
    public static String[] InstructionSet4 = {"IN", "OUT", "CHK"};

    public static String generate_opcode(String instruction) throws Exception
    {
        String operation = "none";
        String operand1 = "None";
        String operand2 = "none";
        String operand3 = "None";
        String InstructionCode = "0000000000000000";
        int DevID_int;
        int Address_int;
        int Count_int;
        int x = 0;

        boolean R_update = false;
        boolean X_update = false;

        String operation_bin = "000000";
        String Reg_bin = "00";
        String Reg2_bin = "00";
        String X_bin = "00";
        String I_bin = "0";
        String Address_bin = "00000";
        String DevId_bin = "00000";
        String AL = "0";
        String LR = "0";
        String Count_bin = "00000";

        String[] TempOperands = instruction.split("\\s+");

        for (int i = 0; i < TempOperands.length; i++) {
            String TempString = TempOperands[i];

            TempString = TempString.replaceAll("\\s+", "");

            if (TempString.charAt(TempString.length() - 1) == ',') {
                TempOperands[i] = TempString.substring(0, TempString.length() - 1);
            } else {
                TempOperands[i] = TempString;
            }
        }

        operation = TempOperands[0];

        for (int temp_var = 0; temp_var < InstructionSet1.length; temp_var++) {
            if (InstructionSet1[temp_var].equals(operation)) {
                if (TempOperands[TempOperands.length - 1].equals("I")) {
                    I_bin = "1";

                    try {
                        Address_int = Integer.parseInt(TempOperands[TempOperands.length - 1]);
                        Address_bin = String.format("%5s", Integer.toBinaryString(Address_int)).replace(' ', '0');
                    } catch (Exception e) {
                        try {
                            Address_int = Integer.parseInt(TempOperands[TempOperands.length - 2]);
                            Address_bin = String.format("%5s", Integer.toBinaryString(Address_int)).replace(' ', '0');
                        } catch (Exception e1) {
                            Address_int = 0;
                            Address_bin = "00000";
                            x = 1;
                        }
                    }


                    int Tempcounter = 0;
                    for (int i = 1; i < TempOperands.length - 2 + x; i++) {
                        switch (Tempcounter) {
                            case 0:
                                operand1 = TempOperands[i];
                                Tempcounter++;
                                break;
                            case 1:
                                operand2 = TempOperands[i];
                                Tempcounter++;
                                break;
                        }
                    }
                } else {
                    I_bin = "0";

                    try {
                        Address_int = Integer.parseInt(TempOperands[TempOperands.length - 1]);
                        Address_bin = String.format("%5s", Integer.toBinaryString(Address_int)).replace(' ', '0');
                    } catch (Exception e) {
                        Address_int = 0;
                        Address_bin = "00000";
                        x = 1;
                    }

                    int Tempcounter = 0;
                    for (int i = 1; i < TempOperands.length - 1 + x; i++) {
                        switch (Tempcounter) {
                            case 0:
                                operand1 = TempOperands[i];
                                Tempcounter++;
                                break;
                            case 1:
                                operand2 = TempOperands[i];
                                Tempcounter++;
                                break;
                        }
                    }
                }

                if (operand1.charAt(0) == operand2.charAt(0)) {
                    throw new Exception("Two values of X or R were given");
                }


                if (operand1.charAt(0) == 'R') {
                    switch (operand1) {
                        case "R0":
                            Reg_bin = "00";
                            R_update = true;
                            break;
                        case "R1":
                            Reg_bin = "01";
                            R_update = true;
                            break;
                        case "R2":
                            Reg_bin = "10";
                            R_update = true;
                            break;
                        case "R3":
                            Reg_bin = "11";
                            R_update = true;
                            break;
                        default:
                            Reg_bin = "00";
                    }
                } else if (operand1.charAt(0) == 'X') {
                    switch (operand1) {
                        case "X1":
                            X_bin = "01";
                            X_update = true;
                            break;
                        case "X2":
                            X_bin = "10";
                            X_update = true;
                            break;
                        case "X3":
                            X_bin = "11";
                            X_update = true;
                            break;
                        default:
                            X_bin = "00";
                            X_update = true;
                            break;
                    }

                }


                if ((operand2.charAt(0) == 'R') & (!R_update)) {
                    switch (operand2) {
                        case "R0":
                            Reg_bin = "00";
                            R_update = true;
                            break;
                        case "R1":
                            Reg_bin = "01";
                            R_update = true;
                            break;
                        case "R2":
                            Reg_bin = "10";
                            R_update = true;
                            break;
                        case "R3":
                            Reg_bin = "11";
                            R_update = true;
                            break;
                    }
                } else if ((operand2.charAt(0) == 'X') & (!X_update)) {
                    switch (operand2) {
                        case "X1":
                            X_bin = "01";
                            X_update = true;
                            break;
                        case "X2":
                            X_bin = "10";
                            X_update = true;
                            break;
                        case "X3":
                            X_bin = "11";
                            X_update = true;
                            break;
                        default:
                            X_bin = "00";
                            X_update = true;
                            break;
                    }

                }


                switch (operation) {
                    case "LDR":
                        operation_bin = "000001";
                        break;
                    case "STR":
                        operation_bin = "000010";
                        break;
                    case "LDA":
                        operation_bin = "000011";
                        break;
                    case "LDX":
                        operation_bin = "101001";
                        break;
                    case "STX":
                        operation_bin = "101010";
                        break;
                    case "JZ":
                        operation_bin = "001010";
                        break;
                    case "JNE":
                        operation_bin = "001011";
                        break;
                    case "JCC":
                        operation_bin = "001100";
                        break;
                    case "JMA":
                        operation_bin = "001101";
                        break;
                    case "JSR":
                        operation_bin = "001110";
                        break;
                    case "RFS":
                        operation_bin = "001111";
                        break;
                    case "SOB":
                        operation_bin = "010000";
                        break;
                    case "JGE":
                        operation_bin = "010001";
                        break;
                    case "AMR":
                        operation_bin = "000100";
                        break;
                    case "SMR":
                        operation_bin = "000101";
                        break;
                    case "AIR":
                        operation_bin = "000110";
                        break;
                    case "SIR":
                        operation_bin = "000111";
                        break;
                }

                InstructionCode = operation_bin + Reg_bin + X_bin + I_bin + Address_bin;

                return InstructionCode;
            }
        }







        for (int temp_var = 0; temp_var < InstructionSet2.length; temp_var++) {
            if (InstructionSet2[temp_var].equals(operation)) {


                int Tempcounter = 0;
                for (int i = 1; i < TempOperands.length; i++) {
                    switch (Tempcounter) {
                        case 0:
                            operand1 = TempOperands[i];
                            Tempcounter++;
                            break;
                        case 1:
                            operand2 = TempOperands[i];
                            Tempcounter++;
                            break;
                    }
                }
            }

            if ((operand1.charAt(0) == 'X') | (operand2.charAt(0) == 'X')) {

                throw new Exception("Operation does not support X Registers");
            }

            switch (operand1) {
                case "R0":
                    Reg_bin = "00";
                    break;
                case "R1":
                    Reg_bin = "01";
                    break;
                case "R2":
                    Reg_bin = "10";
                    break;
                case "R3":
                    Reg_bin = "11";
                    break;
                default:
                    Reg_bin = "00";
            }

            switch (operand2) {
                case "R0":
                    Reg2_bin = "00";
                    break;
                case "R1":
                    Reg2_bin = "01";
                    break;
                case "R2":
                    Reg2_bin = "10";
                    break;
                case "R3":
                    Reg2_bin = "11";
                    break;
            }


            switch (operation) {
                case "MLT":
                    operation_bin = "010100";
                    break;
                case "DVD":
                    operation_bin = "010101";
                    break;
                case "TRR":
                    operation_bin = "010110";
                    break;
                case "AND":
                    operation_bin = "010111";
                    break;
                case "ORR":
                    operation_bin = "011000";
                    break;
                case "NOT":
                    operation_bin = "011001";
                    break;
            }

            InstructionCode = operation_bin + Reg_bin + Reg2_bin + "000000";
            }









        for (int temp_var = 0; temp_var < InstructionSet3.length; temp_var++) {
            if (InstructionSet3[temp_var].equals(operation)) {


                try {
                    Count_int = Integer.parseInt(TempOperands[TempOperands.length - 1]);
                    Count_bin = String.format("%4s", Integer.toBinaryString(Count_int)).replace(' ', '0');
                } catch (Exception e) {
                    throw new Exception("No Count given");
                }

                int Tempcounter = 0;

                try {

                    for (int i = 1; i < TempOperands.length; i++) {
                        switch (Tempcounter) {
                            case 0:
                                operand1 = TempOperands[i];
                                Tempcounter++;
                                break;
                            case 1:
                                operand2 = TempOperands[i];
                                Tempcounter++;
                                break;
                            case 2:
                                operand3 = TempOperands[i];
                                Tempcounter++;
                                break;
                        }
                    }
                }
                catch (Exception e){
                    throw new Exception("Not Enough Operands");
                }
            }

            switch (operand1) {
                case "R0":
                    Reg_bin = "00";
                    break;
                case "R1":
                    Reg_bin = "01";
                    break;
                case "R2":
                    Reg_bin = "10";
                    break;
                case "R3":
                    Reg_bin = "11";
                    break;
                default:
                    Reg_bin = "00";
            }

            AL = operand2;
            LR = operand3;


            switch (operation) {
                case "SRC":
                    operation_bin = "011111";
                    break;
                case "RRC":
                    operation_bin = "100000";
                    break;
            }

            InstructionCode = operation_bin + Reg_bin + AL + LR + "00" + Count_bin;
        }




        for (int temp_var = 0; temp_var < InstructionSet4.length; temp_var++) {
            if (InstructionSet4[temp_var].equals(operation)) {

                try {
                    DevID_int = Integer.parseInt(TempOperands[TempOperands.length - 1]);
                    DevId_bin = String.format("%5s", Integer.toBinaryString(DevID_int)).replace(' ', '0');
                } catch (Exception e) {
                    throw new Exception("No DevId Given");
                }


                operand1 = TempOperands[1];
            }

            if ((operand1.charAt(0) != 'R')) {

                throw new Exception("Wrong Register Given");
            }

            switch (operand1) {
                case "R0":
                    Reg_bin = "00";
                    break;
                case "R1":
                    Reg_bin = "01";
                    break;
                case "R2":
                    Reg_bin = "10";
                    break;
                case "R3":
                    Reg_bin = "11";
                    break;
                default:
                    Reg_bin = "00";
            }



            switch (operation) {
                case "IN":
                    operation_bin = "111101";
                    break;
                case "OUT":
                    operation_bin = "111110";
                    break;
                case "CHK":
                    operation_bin = "111111";
                    break;
            }

            InstructionCode = operation_bin + Reg_bin + "000" + DevId_bin;
        }

        return InstructionCode;
    }


    public static void execute(String OpCode) {
        String operation = OpCode.substring(0, 6);
        String operation_name = "None";

        switch (operation)
        {
            case "000001" : operation_name = "LDR"; break;
            case "000010" : operation_name = "STR"; break;
            case "000011" : operation_name = "LDA"; break;
            case "101001" : operation_name = "LDX"; break;
            case "101010" : operation_name = "STX"; break;
            case "001010" : operation_name = "JZ"; break;
            case "001011" : operation_name = "JNE"; break;
            case "001100" : operation_name = "JCC"; break;
            case "001101" : operation_name = "JMA"; break;
            case "001110" : operation_name = "JSR"; break;
            case "001111" : operation_name = "RFS"; break;
            case "010000" : operation_name = "SOB"; break;
            case "010001" : operation_name = "JGE"; break;
            case "000100" : operation_name = "AMR"; break;
            case "000101" : operation_name = "SMR"; break;
            case "000110" : operation_name = "AIR"; break;
            case "000111" : operation_name = "SIR"; break;
            case "010100" : operation_name = "MLT"; break;
            case "010101" : operation_name = "DVD"; break;
            case "010110" : operation_name = "TRR"; break;
            case "010111" : operation_name = "AND"; break;
            case "011000" : operation_name = "ORR"; break;
            case "011001" : operation_name = "NOT"; break;
            case "011111" : operation_name = "SRC"; break;
            case "100000" : operation_name = "RRC"; break;
            case "111101" : operation_name = "IN"; break;
            case "111110" : operation_name = "OUT"; break;
            case "111111" : operation_name = "CHK"; break;
        }





        for (int temp_var = 0; temp_var < InstructionSet1.length; temp_var++) {
            if (InstructionSet1[temp_var].equals(operation_name)) {
                String R = OpCode.substring(6, 8);
                String X = OpCode.substring(8, 10);
                String I = OpCode.substring(10, 11);
                String Address = OpCode.substring(11);

                switch (operation) {
                    case "000000":
                        ALU.HLT();
                        break;
                    case "000001":
                        ALU.LDR(R, X, I, Address);
                        break;
                    case "000010":
                        ALU.STR(R, X, I, Address);
                        break;
                    case "000011":
                        ALU.LDA(R, X, I, Address);
                        break;
                    case "101001":
                        ALU.LDX(R, X, I, Address);
                        break;
                    case "101010":
                        ALU.STX(R, X, I, Address);
                        break;
                    case "001010":
                        ALU.JZ(R, X, I, Address);
                        break;
                    case "001011":
                        ALU.JNE(R, X, I, Address);
                        break;
                    case "001100":
                        ALU.JCC(R, X, I, Address);
                        break;
                    case "001101":
                        ALU.JMA(R, X, I, Address);
                        break;
                    case "001110":
                        ALU.JSR(R, X, I, Address);
                        break;
                    case "001111":
                        ALU.RFS(R, X, I, Address);
                        break;
                    case "010000":
                        ALU.SOB(R, X, I, Address);
                        break;
                    case "010001":
                        ALU.JGE(R, X, I, Address);
                        break;
                    case "000100":
                        ALU.AMR(R, X, I, Address);
                        break;
                    case "000101":
                        ALU.SMR(R, X, I, Address);
                        break;
                    case "000110":
                        ALU.AIR(R, X, I, Address);
                        break; 
                    case "000111":
                        ALU.SIR(R, X, I, Address);
                        break;      

                }
            }
        }



        for (int temp_var = 0; temp_var < InstructionSet2.length; temp_var++) {
            if (InstructionSet2[temp_var].equals(operation_name)) {
                String R1 = OpCode.substring(6, 8);
                String R2 = OpCode.substring(8, 10);

                switch (operation) {
                    case "010100":
                        ALU.MLT(R1, R2);
                        break;
                    case "010101":
                        ALU.DVD(R1, R2);
                        break;
                    case "010110":
                        ALU.TRR(R1, R2);
                        break;
                    case "010111":
                        ALU.AND(R1, R2);
                        break;
                    case "011000":
                        ALU.ORR(R1, R2);
                        break;
                    case "011001":
                        ALU.NOT(R1, R2);
                        break;
                }
            }
        }



        for (int temp_var = 0; temp_var < InstructionSet3.length; temp_var++) {
            if (InstructionSet3[temp_var].equals(operation_name)) {
                String R1 = OpCode.substring(6, 8);
                String AL = OpCode.substring(8, 9);
                String RL = OpCode.substring(9, 10);
                String Count = OpCode.substring(13);

                switch (operation) {
                    case "011111":
                        ALU.SRC(R1, AL, RL, Count);
                        break;
                    case "100000":
                        ALU.RRC(R1, AL, RL, Count);
                        break;
                }
            }
        }



        for (int temp_var = 0; temp_var < InstructionSet3.length; temp_var++) {
            if (InstructionSet3[temp_var].equals(operation_name)) {
                String R1 = OpCode.substring(6, 8);
                String DevId = OpCode.substring(11);

                switch (operation) {
                    case "111101":
                        ALU.IN(R1, DevId);
                        break;
                    case "111110":
                        ALU.OUT(R1, DevId);
                        break;
                    case "111111":
                        ALU.CHK(R1, DevId);
                        break;
                }
            }
        }






    }


    public static int calculate_effective_address(String I, String X, String Address) {
        int EA = 0;
        Registers reg = new Registers();
        Memory mem = new Memory();
    
        if (I.equals("0")) {
            switch (X) {
                case "00":
                    EA = Integer.parseInt(Address, 2);
                    break;
                case "01":
                    EA = reg.get_register_value_int("X1") + Integer.parseInt(Address, 2);
                    break;
                case "10":
                    EA = reg.get_register_value_int("X2") + Integer.parseInt(Address, 2);
                    break;
                case "11":
                    EA = reg.get_register_value_int("X3") + Integer.parseInt(Address, 2);
                    break;
            }
        } else if (I.equals("1")) {
            switch (X) {
                case "00":
                    EA = mem.get_from_memory_int(Integer.parseInt(Address, 2));
                    break;
                case "01":
                    EA = mem.get_from_memory_int(reg.get_register_value_int("X1")) + Integer.parseInt(Address, 2);
                    break;
                case "10":
                    EA = mem.get_from_memory_int(reg.get_register_value_int("X2")) + Integer.parseInt(Address, 2);
                    break;
                case "11":
                    EA = mem.get_from_memory_int(reg.get_register_value_int("X3")) + Integer.parseInt(Address, 2);
                    break;
            }
        }
        return EA;
    }

    public static String increasePC(){
        Registers reg = new Registers();
        
        reg.update_registers("PC", reg.get_register_value_int("PC")+1);
        return reg.get_register_value_string("PC");
    }

    public static int setPC(int value) {
        this.PC = value;
    }

    public static String getPC() {
        return PC;
    }


    public String getR0() {
        return R0;
    }

    public static void setR0(int immed) {
        this.R0 = immed;
    }

    public String getR1() {
        return R1;
    }

    public static void setR1(String R1) {
        this.R1 = R1;
    }

    public String getR2() {
        return R2;
    }

    public static void setR2(String R2) {
        this.R2 = R2;
    }

    public static String getR3() {
        return R3;
    }

    public static void setR3(String R3) {
        this.R3 = R3;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Utils.generate_opcode("IN R3, 22"));
    }

    public void setPrinterBuffer(String printerBuffer) {
		this.printerBuffer = printerBuffer;
	}

    public static void setRnByNum(String r2, int r) {
        if (r2 == 0)
            this.R0 = r;
        if (r2 == 1)
            this.R1 = r;
        if (r2 == 2)
            this.R2 = r;
        if (r2 == 3)
            this.R3 = r;
    }

    /**
     * @param r
     * @return
     */
    public static int getRnByNum(String r) {
        if (r == 0)
            return this.R0;
        if (r == 1)
            return this.R1;
        if (r == 2)
            return this.R2;
        if (r == 3)
            return this.R3;
        return 0;
    }

    public boolean getCCElementByBit(int bitNum) {
        return ((this.CC & (1 << bitNum)) != 0);
    }

    public void setCCElementByBit(int bitNum, boolean flag) {
        if (flag) {
            this.CC = (this.CC | (1 << bitNum));
        } else {
            int mask = ~(1 << bitNum);
            this.CC = this.CC & mask;
        }
    }

    public int getMAR() {
        return MAR;
    }

    public static void setMAR(int MAR) {
        this.MAR = MAR;
    }

    public int getMBR() {
        return MBR;
    }

    public void setMBR(int MBR) {
        this.MBR = MBR;
    }

    public int getMFR() {
        return MFR;
    }

    public void setMFR(int MFR) {
        this.MFR = MFR;
    }

    public int getMSR() {
        return MSR;
    }

    public void setMSR(int MSR) {
        this.MSR = MSR;
    }
    

}
