package CPU;


public class ALU
{
   
    public static void HLT()
    {
        Frame.run = false;
    }

    public static void LDR(String R, String X, String I, String Address)
    {
        Registers reg = new Registers();
        Memory mem = new Memory();
        int val = mem.get_from_memory_int(Utils.calculate_effective_address(I, X, Address));

        switch (R)
        {
            case "00" : reg.update_registers("R0", val); break;
            case "01" : reg.update_registers("R1", val); break;
            case "10" : reg.update_registers("R2", val); break;
            case "11" : reg.update_registers("R3", val); break;
        }
    }

    public static void STR(String R, String X, String I, String Address)
    {
        Registers reg = new Registers();
        Memory mem = new Memory();
        int val = 0;

        switch (R)
        {
            case "00" : val = reg.get_register_value_int("R0"); break;
            case "01" : val = reg.get_register_value_int("R1"); break;
            case "10" : val = reg.get_register_value_int("R2"); break;
            case "11" : val = reg.get_register_value_int("R3"); break;
        }

        mem.store_to_memory(Utils.calculate_effective_address(I, X, Address), val);

    }

    public static void LDA(String R, String X, String I, String Address)
    {
        int val = Utils.calculate_effective_address(I, X, Address);
        Registers reg = new Registers();
        Memory mem = new Memory();

        switch (R)
        {
            case "00" : reg.update_registers("R0", val); break;
            case "01" : reg.update_registers("R1", val); break;
            case "10" : reg.update_registers("R2", val); break;
            case "11" : reg.update_registers("R3", val); break;
        }

    }

    public static void LDX(String R, String X, String I, String Address)
    {
        int val = Utils.calculate_effective_address(I, X, Address);
        Registers reg = new Registers();
        Memory mem = new Memory();

        switch (X)
        {
            case "01" : reg.update_registers("X1", val); break;
            case "10" : reg.update_registers("X2", val); break;
            case "11" : reg.update_registers("X3", val); break;
        }

    }

    public static void STX(String R, String X, String I, String Address)
    {
        int val = 0;
        Registers reg = new Registers();
        Memory mem = new Memory();

        switch (X)
        {
            case "01" : val = reg.get_register_value_int("X1"); break;
            case "10" : val = reg.get_register_value_int("X2"); break;
            case "11" : val = reg.get_register_value_int("X3"); break;
        }

        mem.store_to_memory(Utils.calculate_effective_address(I, X, Address), val);

    }    

    public static void JZ(String R, String X, String I, String Address)
    {
        Registers reg = new Registers();
        Memory mem = new Memory();
        int val = 0;
        int value = Utils.calculate_effective_address(I, X, Address);

        switch (R)
        {
            case "00" : val = reg.get_register_value_int("R0");
                        if(val==0){
                            Utils.setPC(value);
                        }
                        else{
                            Utils.increasePC();
                        }
            break;
            case "01" : val = reg.get_register_value_int("R1");
                        if(val==0){
                            Utils.setPC(value);
                        }
                        else{
                            Utils.increasePC();
                        }
            break;
            case "10" : val = reg.get_register_value_int("R2");
                        if(val==0){
                            Utils.setPC(value);
                        }
                        else{
                            Utils.increasePC();
                        }
            break;
            case "11" : val = reg.get_register_value_int("R3");
                        if(val==0){
                            Utils.setPC(value);
                        }
                        else{
                            Utils.increasePC();
                        }
            break;
        }

    }  

    /**
     * @param R
     * @param X
     * @param I
     * @param Address
     */
    public static void JNE(String R, String X, String I, String Address)
    {
        Registers reg = new Registers();
        Memory mem = new Memory();
        int val = 0;
        int value = Utils.calculate_effective_address(I, X, Address);

        switch (R)
        {
            case "00" : val = reg.get_register_value_int("R0");
                        if(val != 0){
                            Utils.setPC(value);
                        }
                        else{
                            Utils.increasePC();
                        }
            break;
            case "01" : val = reg.get_register_value_int("R1");
                        if(val != 0){
                            Utils.setPC(value);
                        }
                        else{
                            Utils.increasePC();
                        }
            break;
            case "10" : val = reg.get_register_value_int("R2");
                        if(val != 0){
                            Utils.setPC(value);
                        }
                        else{
                            Utils.increasePC();
                        }
            break;
            case "11" : val = reg.get_register_value_int("R3");
                        if(val != 0){
                            Utils.setPC(value);
                        }
                        else{
                            Utils.increasePC();
                        }
            break;
        }
    } 
 

    public static void JCC(String R, String X, String I, String Address)
    {
        int value = Utils.calculate_effective_address(I, X, Address);
        if (Utils.getCCElementByBit(cc)) {
			Utils.setPC(value);
		} else {
			Utils.increasePC();
		}

    }  

    public static void JMA(String R, String X, String I, String Address)
    {
        Registers reg = new Registers();
        Memory mem = new Memory();
        int value = Utils.calculate_effective_address(I, X, Address);

        Utils.setPC(value);

    }  

    public static void JSR(String R, String X, String I, String Address)
    {
        Registers reg = new Registers();
        Memory mem = new Memory();
        int value = Utils.calculate_effective_address(I, X, Address);

        Utils.setR3(Utils.getPC() + 1);
		Utils.setPC(value);

    }  

    public static void RFS(String R, String X, String I, String Address)
    {
        Registers reg = new Registers();
        Memory mem = new Memory();
        int immed = 0;

        immed = stringutils.binaryToDecimal(instruction.substring(11, 16));
        Utils.setR0(immed);
        Utils.setPC(Utils.getR3());


    }  

    public static void SOB(String R, String X, String I, String Address)
    {
        Registers reg = new Registers();
        Memory mem = new Memory();
        int value = Utils.calculate_effective_address(I, X, Address);

        Utils.setRnByNum(R, Utils.getRnByNum(R) - 1);
		if (Utils.getRnByNum(R) > 0) {
			Utils.setPC(value);
		} 
        else {
			Utils.increasePC();
		}
	}
  

    public static void JGE(String R, String X, String I, String Address)
    {
        Registers reg = new Registers();
        Memory mem = new Memory();
        int value = Utils.calculate_effective_address(I, X, Address);

		if (Utils.getRnByNum(R) >= 0) {
			Utils.setPC(value);
		} else {
			Utils.increasePC();
		}
	}


    public static void AMR(String R, String X, String I, String Address)
    {
        //Add code after cache

    } 
    
    public static void SMR(String R, String X, String I, String Address)
    {
        // ADD CODE after cache

    }  

    public static void AIR(String R, String X, String I, String Address)
    {

    // ADD CODE after cache
    }  

    public static void SIR(String R, String X, String I, String Address)
    {
      // ADD CODE after cache

    }  

    public static void MLT(String R1, String R2)
    {
        // Add code for overflow

    }  

    public static void DVD(String R1, String R2)
    {
        int val = 0;

        switch (R)
        {
           // Add code for overflow
        }


    } 
    
    public static void TRR(String R1, String R2)
    {
        // Add code for Const condition code

    }  

    public static void AND(String R1, String R2)
    {
        // Add code for Const condition code

    }  

    public static void ORR(String R1, String R2)
    {
        // Add code for Const condition code

    }  

    public static void NOT(String R1, String R2)
    {
       // Add code for Const condition code
    }  

    public static void SRC(String R1, String AL, String RL, String Count)
    {
        // Add code for Const condition code

    }  

    public static void RRC(String R1, String AL, String RL, String Count)
    {
        // Add code for Const condition code

    }  

    public static void IN(String R1, String DevId)
    {
        // Add code for Const condition code

    }  

    public static void OUT(String R1, String DevId)
     {
        // Add code for Const condition code
    
    
    }


    public static void CHK(String R1, String DevId)
    {
        // Add code for Const condition code

    }  
}
