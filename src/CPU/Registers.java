
package CPU;
public class Registers {

    String R0 = "00000000000000004975982375989";
    String R1 = "0000000000000000";
    String R2 = "0000000000000000";
    String R3 = "0000000000000000";

    String idx = "0000000000000000";

    String CC = "0000";

    String MBR = "0000000000000000";
    String MAB = "0000000000000000";

    String PC = "0000000000000000";

    String X1 = "0000000000000000";
    String X2 = "0000000000000000";
    String X3 = "0000000000000000";

    public void reset_registers(){
        R0 = "0000000000000000";
        R1 = "0000000000000000";
        R2 = "0000000000000000";
        R3 = "0000000000000000";

        idx = "0000000000000000";

        CC = "0000";

        MBR = "0000000000000000";
        MAB = "0000000000000000";

        PC = "0000000000000000";

        X1 = "0000000000000000";
        X2 = "0000000000000000";
        X3 = "0000000000000000";
    }


     public void update_registers(String reg_name, int val)
    {
        if (reg_name.equals("R0"))
        {
            R0 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("R1"))
        {
            R1 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("R2"))
        {
            R2 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("R3"))
        {
            R3 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("idx"))
        {
            idx = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("MBR"))
        {
            MBR = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("MAB"))
        {
            MAB = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("PC"))
        {
            PC = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("X1"))
        {
            X1 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("X2"))
        {
            X2 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("X3"))
        {
            X3 = String.format("%16s", Integer.toBinaryString(val)).replace(' ', '0');
        }

        if (reg_name.equals("CC"))
        {
            CC = String.format("%4s", Integer.toBinaryString(val)).replace(' ', '0');
        }
    }


    public String get_register_value_string(String reg_name)
    {
        return switch (reg_name) {
            case "R0" -> R0;
            case "R1" -> R1;
            case "R2" -> R2;
            case "R3" -> R3;
            case "idx" -> idx;
            case "MBR" -> MBR;
            case "MAB" -> MAB;
            case "PC" -> PC;
            case "X1" -> X1;
            case "X2" -> X2;
            case "X3" -> X3;
            case "CC" -> CC;
            default -> new String("Invalid input");
        };
    }


    public int get_register_value_int(String reg_name)
    {
        return switch (reg_name) {
            case "R0" -> Integer.parseInt(R0, 2);
            case "R1" -> Integer.parseInt(R1, 2);
            case "R2" -> Integer.parseInt(R2, 2);
            case "R3" -> Integer.parseInt(R3, 2);
            case "idx" -> Integer.parseInt(idx, 2);
            case "MBR" -> Integer.parseInt(MBR, 2);
            case "MAB" -> Integer.parseInt(MAB, 2);
            case "PC" -> Integer.parseInt(PC, 2);
            case "X1" -> Integer.parseInt(X1, 2);
            case "X2" -> Integer.parseInt(X2, 2);
            case "X3" -> Integer.parseInt(X3, 2);
            case "CC" -> Integer.parseInt(CC, 2);
            default -> 0;
        };
    }

}
