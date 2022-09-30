package CPU;
public class Utils {

    public void generate_opcode(String instruction)
    {
        String operation;
        String Reg;
        String X;
        String I;
        String Address;
        int Address_int;

        String operation_bin;
        String Reg_bin;
        String X_bin;
        String I_bin;
        String Address_bin;

        instruction = instruction.replaceAll("\\s+","");

        operation = instruction.substring(0, 3);
        instruction = instruction.substring(3);

        I = instruction.substring(instruction.length() - 4);

        if (I.equals("[,I]"))
        {
            instruction = instruction.substring(0, instruction.length() - 4);
            I = "1";
        }
        else
        {
            I = "0";
        }

        String[] TempArray = instruction.split(",");

        Reg = TempArray[0];
        X = TempArray[1];
        Address = TempArray[2];


        switch (operation)
        {
            case "LDR" -> operation_bin = "000001";
            case "STR" -> operation_bin = "000010";
            case "LDA" -> operation_bin = "000011";
            case "LDX" -> operation_bin = "101001";
            case "STX" -> operation_bin = "101010";
        }

        switch (Reg)
        {
            case "R0" -> Reg_bin = "00";
            case "R1" -> Reg_bin = "01";
            case "R2" -> Reg_bin = "10";
            case "R3" -> Reg_bin = "11";
        }

        switch (X)
        {
            case "R1" -> X_bin = "01";
            case "R2" -> X_bin = "10";
            case "R3" -> X_bin = "11";
            default -> X_bin = "00";
        }

        Address_int = Integer.parseInt(Address);

        Address_bin = String.format("%6s", Integer.toBinaryString(Address_int)).replace(' ', '0');
    }


    public void execute(String OpCode)
    {
        String operation = OpCode.substring(0, 6);
        String Register = OpCode.substring(6, 8);
        String Index_Reg = OpCode.substring(8, 10);
        String I = OpCode.substring(10, 11);
        String Address = OpCode.substring(11);


        switch(operation)
        {
            case "000001" -> ALU.LDR(Reg_Name, Index_Reg, I, Address)

        }


    }


    public void calculate_effective_address()
    {

    }

}
